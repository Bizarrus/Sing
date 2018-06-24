/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.ImageView
 *  android.widget.ImageView$ScaleType
 *  android.widget.TextView
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.customviews.ActionBarCustomView_;
import com.smule.singandroid.customviews.CustomToolBarBase;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ActionBarCustomView
extends CustomToolBarBase {
    protected View.OnClickListener a;
    @ViewById
    protected View b;
    @ViewById
    protected View c;
    @ViewById
    protected ViewGroup d;
    @ViewById
    protected View e;
    protected final LayoutInflater f;

    public ActionBarCustomView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionBarCustomView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.f = LayoutInflater.from((Context)context);
    }

    public static ActionBarCustomView a(Context context) {
        return ActionBarCustomView_.a(context, null);
    }

    public View a(View view) {
        this.d.addView(view);
        return view;
    }

    public ImageView a(Drawable drawable2) {
        this.e.setVisibility(0);
        ImageView imageView = (ImageView)this.f.inflate(2130903069, this.d, false);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageDrawable(drawable2);
        this.d.addView((View)imageView);
        return imageView;
    }

    public TextView a(CharSequence charSequence) {
        if (this.d.getChildCount() == 0) {
            this.e.setVisibility(8);
        }
        TextView textView = (TextView)this.f.inflate(2130903072, this.d, false);
        textView.setText(charSequence);
        this.d.addView((View)textView);
        return textView;
    }

    public void a() {
        int n = 0;
        while (n < this.d.getChildCount()) {
            int n2 = n;
            if (this.d.getChildAt(n).getId() != 2131755233) {
                this.d.removeViewAt(n);
                n2 = n - 1;
            }
            n = n2 + 1;
        }
        this.e.setVisibility(8);
    }

    @Click
    protected void b() {
        if (this.a != null) {
            this.a.onClick((View)this);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setDisplayUpButton(boolean bl) {
        ImageView imageView = this.h;
        int n = bl ? 2130837592 : 2130838114;
        imageView.setImageResource(n);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setDoneButtonOnClickListener(View.OnClickListener onClickListener) {
        this.b.setOnClickListener(onClickListener);
        View view = this.b;
        int n = onClickListener == null ? 8 : 0;
        view.setVisibility(n);
    }

    public void setEnableUpButton(boolean bl) {
        this.i.setClickable(bl);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.a = onClickListener;
    }

    public void setTopPadding(int n) {
        this.g.setPadding(this.getPaddingLeft(), n, this.getPaddingRight(), this.getPaddingBottom());
    }
}

