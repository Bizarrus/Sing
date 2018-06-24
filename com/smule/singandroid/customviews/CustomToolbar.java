/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.TypedArray
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.ImageView
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  com.smule.singandroid.textviews.AutoResizeTextView
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.ImageUtils;
import com.smule.singandroid.R;
import com.smule.singandroid.customviews.CustomToolBarBase;
import com.smule.singandroid.textviews.AutoResizeTextView;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class CustomToolbar
extends CustomToolBarBase {
    public static final String a = CustomToolbar.class.getName();
    @ViewById
    protected RelativeLayout b;
    @ViewById
    protected ImageView c;
    @ViewById
    protected AutoResizeTextView d;
    @ViewById
    protected ImageView e;
    @ViewById
    protected TextView f;
    protected Drawable o;
    protected boolean p;
    protected Drawable q;
    protected String r;
    private boolean s = false;

    public CustomToolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CustomToolbar(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        context = context.obtainStyledAttributes(attributeSet, R.CustomToolbar, n, 0);
        this.o = context.getDrawable(0);
        this.p = context.getBoolean(1, true);
        this.q = context.getDrawable(2);
        this.r = context.getString(3);
        context.recycle();
    }

    @AfterViews
    protected void a() {
        this.setLeftButtonDrawable(this.o);
        if (this.q != null) {
            this.setRightButtonDrawable(this.q);
            return;
        }
        if (this.r != null) {
            this.setRightButtonText(this.r);
            return;
        }
        this.b();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(SongbookEntry songbookEntry, PerformanceV2 performanceV2) {
        super.a(songbookEntry, performanceV2);
        boolean bl = songbookEntry != null && songbookEntry.g();
        this.s = bl;
    }

    protected void b() {
        if (this.d.getText() != null && this.d.getText().length() > 0) {
            this.c.setVisibility(8);
            this.d.setVisibility(0);
            if (this.s) {
                this.e.setVisibility(0);
                return;
            }
            this.e.setVisibility(4);
            return;
        }
        this.d.setVisibility(8);
        this.e.setVisibility(8);
        if (this.c.getDrawable() == null) {
            this.c.setVisibility(4);
            return;
        }
        this.c.setVisibility(0);
    }

    public void c() {
        ImageUtils.a((View)this.d, this.getResources().getDrawable(2130837643));
        this.d.setClickable(false);
    }

    public void setBackgroundColor(int n) {
        this.b.setBackgroundColor(n);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setLeftButtonDrawable(Drawable drawable2) {
        if (drawable2 != null) {
            this.h.setImageDrawable(drawable2);
        }
        drawable2 = this.h;
        int n = this.p ? 0 : 8;
        drawable2.setVisibility(n);
    }

    public void setLeftButtonOnClickListener(View.OnClickListener onClickListener) {
        this.h.setOnClickListener(onClickListener);
    }

    public void setLeftTitle(String string2) {
        this.f.setText((CharSequence)string2);
        this.f.setVisibility(0);
    }

    public void setRightButtonDrawable(Drawable drawable2) {
        this.c.setImageDrawable(drawable2);
        this.b();
    }

    public void setRightButtonOnClickListener(View.OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
        this.d.setOnClickListener(onClickListener);
    }

    public void setRightButtonText(String string2) {
        this.d.setText((CharSequence)string2);
        this.b();
    }
}

