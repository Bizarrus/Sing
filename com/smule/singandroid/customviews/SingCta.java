/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.view.animation.Animation
 *  android.view.animation.AnimationUtils
 *  android.widget.Button
 *  android.widget.RelativeLayout
 *  com.smule.singandroid.utils.LayoutUtils
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.smule.android.utils.WeakListener;
import com.smule.singandroid.utils.LayoutUtils;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class SingCta
extends RelativeLayout {
    @ViewById
    protected Button a;
    @ViewById
    protected Button b;
    private WeakListener.OnGlobalLayoutListener c;

    public SingCta(Context context) {
        super(context);
        this.c();
    }

    public SingCta(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c();
    }

    public SingCta(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.c();
    }

    private void c() {
        if (this.c != null) {
            return;
        }
        this.c = new WeakListener.OnGlobalLayoutListener((Object)this, new ViewTreeObserver.OnGlobalLayoutListener(){

            public void onGlobalLayout() {
                LayoutUtils.b((View)SingCta.this, (WeakListener.OnGlobalLayoutListener)SingCta.this.c);
                SingCta.this.d();
            }
        });
        LayoutUtils.a((View)this, (WeakListener.OnGlobalLayoutListener)this.c);
    }

    private void d() {
        Animation animation = AnimationUtils.loadAnimation((Context)this.getContext(), (int)2130968595);
        if (this.a.getVisibility() == 0) {
            this.a.startAnimation(animation);
            return;
        }
        this.b.startAnimation(animation);
    }

    public void a() {
        this.a.setVisibility(0);
        this.b.setVisibility(8);
    }

    public void b() {
        this.b.setVisibility(0);
        this.a.setVisibility(8);
    }

    public void setEnabled(boolean bl) {
        if (this.a != null && this.a.getVisibility() == 0) {
            this.a.setEnabled(bl);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.setSingOnClickListener(onClickListener);
    }

    public void setSingAgainOnClickListener(View.OnClickListener onClickListener) {
        this.b.setOnClickListener(onClickListener);
    }

    public void setSingOnClickListener(View.OnClickListener onClickListener) {
        this.a.setOnClickListener(onClickListener);
    }

    public void setText(int n) {
        this.a.setText((CharSequence)this.getResources().getString(n));
        this.a.setVisibility(0);
        this.b.setVisibility(8);
    }

}

