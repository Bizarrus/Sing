/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.drawable.Drawable
 *  android.support.v4.content.ContextCompat
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.ImageView
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.singandroid.customviews.CyclableStateItemViewModel;
import com.smule.singandroid.customviews.IconFontView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class CyclableStateItemView
extends RelativeLayout {
    public static final String a = CyclableStateItemView.class.getName();
    @ViewById
    protected ImageView b;
    @ViewById
    protected IconFontView c;
    @ViewById
    protected TextView d;
    private boolean e;
    private final List<CyclableStateItemViewModel> f = new ArrayList<CyclableStateItemViewModel>();
    private int g = -1;

    public CyclableStateItemView(Context context) {
        super(context);
    }

    public CyclableStateItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public CyclableStateItemView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    public void a() {
        ++this.g;
        if (this.g == this.f.size()) {
            this.g = 0;
        }
        this.a(this.g);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(int n) {
        if (n < 0 || n > this.f.size() - 1) {
            Log.d(a, "out of range", new IllegalArgumentException());
        }
        this.g = n;
        CyclableStateItemViewModel cyclableStateItemViewModel = this.f.get(this.g);
        this.d.setText((CharSequence)cyclableStateItemViewModel.c());
        n = cyclableStateItemViewModel.a();
        Drawable drawable2 = n == ContextCompat.getColor((Context)this.getContext(), (int)2131689991) ? ContextCompat.getDrawable((Context)this.getContext(), (int)2130837807).mutate() : ContextCompat.getDrawable((Context)this.getContext(), (int)2130837808).mutate();
        this.b.setImageDrawable(drawable2);
        this.b.setColorFilter(n, PorterDuff.Mode.MULTIPLY);
        this.c.setText(cyclableStateItemViewModel.b());
    }

    public void a(List<CyclableStateItemViewModel> list, int n) {
        this.setGravity(17);
        this.f.clear();
        this.f.addAll(list);
        this.a(n);
    }

    public int getPosition() {
        return this.g;
    }

    public void setOnClickListener(final View.OnClickListener onClickListener) {
        super.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (CyclableStateItemView.this.e) {
                    CyclableStateItemView.this.a();
                }
                onClickListener.onClick(view);
            }
        });
    }

    public void setShowNextIcon(boolean bl) {
        this.e = bl;
    }

}

