/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Resources
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.Rect
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.support.v7.widget.CardView
 *  android.util.AttributeSet
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewParent
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnPreDrawListener
 *  android.view.animation.AlphaAnimation
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  android.view.animation.AnimationSet
 *  android.view.animation.ScaleAnimation
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.TextView
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.singandroid.SingApplication;

public class BubbleTooltipViewWithDropShadow
extends LinearLayout
implements ViewTreeObserver.OnPreDrawListener {
    ImageView a;
    TextView b;
    ImageView c;
    CardView d;
    View e;
    int f;
    private int g;
    private int h;
    private boolean i;
    private String j;

    public BubbleTooltipViewWithDropShadow(Context context) {
        super(context);
        this.c();
    }

    public BubbleTooltipViewWithDropShadow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c();
    }

    public BubbleTooltipViewWithDropShadow(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.c();
    }

    private void c() {
        LayoutInflater.from((Context)this.getContext()).inflate(2130903351, (ViewGroup)this, true);
        this.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)this);
        this.a = (ImageView)this.findViewById(2131756150);
        this.b = (TextView)this.findViewById(2131756151);
        this.c = (ImageView)this.findViewById(2131755294);
        this.d = (CardView)this.findViewById(2131756256);
        this.c.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (BubbleTooltipViewWithDropShadow.this.j != null) {
                    SingApplication.g().getSharedPreferences(BubbleTooltipViewWithDropShadow.class.getName(), 0).edit().putBoolean(BubbleTooltipViewWithDropShadow.this.j, true).apply();
                    BubbleTooltipViewWithDropShadow.this.b();
                    BubbleTooltipViewWithDropShadow.this.i = true;
                }
            }
        });
    }

    public void a() {
        if (this.a.getVisibility() == 0 || this.i) {
            return;
        }
        this.d.setVisibility(0);
        this.a.setVisibility(0);
        this.a.clearAnimation();
        this.d.clearAnimation();
        AnimationSet animationSet = new AnimationSet(false);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setFillAfter(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.3f, 1.0f, 0.3f, 1.0f, 1, 1.0f, 1, 0.0f);
        scaleAnimation.setDuration(300);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation((Animation)alphaAnimation);
        animationSet.addAnimation((Animation)scaleAnimation);
        animationSet.setStartOffset(200);
        alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setStartOffset(200);
        animationSet.setAnimationListener(new Animation.AnimationListener(){

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        this.d.startAnimation((Animation)animationSet);
        this.a.startAnimation((Animation)alphaAnimation);
    }

    public void b() {
        if (this.a.getVisibility() == 8 || this.i) {
            return;
        }
        this.a.clearAnimation();
        this.d.clearAnimation();
        AnimationSet animationSet = new AnimationSet(false);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setFillAfter(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.3f, 1.0f, 0.3f, 1, 1.0f, 1, 0.0f);
        scaleAnimation.setDuration(300);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation((Animation)alphaAnimation);
        animationSet.addAnimation((Animation)scaleAnimation);
        animationSet.setStartOffset(200);
        alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setStartOffset(200);
        animationSet.setAnimationListener(new Animation.AnimationListener(){

            public void onAnimationEnd(Animation animation) {
                BubbleTooltipViewWithDropShadow.this.d.setVisibility(8);
                BubbleTooltipViewWithDropShadow.this.a.setVisibility(8);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        this.d.startAnimation((Animation)animationSet);
        this.a.startAnimation((Animation)alphaAnimation);
    }

    public boolean onPreDraw() {
        this.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)this);
        if (this.e != null) {
            int[] arrn = new int[2];
            this.e.getLocationOnScreen(arrn);
            int[] arrn2 = new int[]();
            this.e.getWindowVisibleDisplayFrame((Rect)arrn2);
            arrn2 = new int[2];
            ((View)this.getParent()).getLocationOnScreen(arrn2);
            int n = this.e.getMeasuredWidth();
            int n2 = this.e.getMeasuredHeight();
            this.h = arrn[0] - arrn2[0];
            this.g = arrn[1] - arrn2[1];
            int n3 = this.h;
            this.setTranslationY((float)Math.max(0, this.g + n2 + this.getResources().getDimensionPixelSize(2131428182)));
            n2 = this.a.getMeasuredWidth();
            this.a.setX((float)((n /= 2) + n3 - n2 / 2 - (int)this.getX()));
            this.a.bringToFront();
            if (Build.VERSION.SDK_INT >= 21) {
                this.d.setElevation(5.0f);
                this.a.setElevation(10.0f);
            }
        }
        return true;
    }

    public void setAnchoringView(View view) {
        this.e = view;
    }

    public void setColor(int n) {
        this.f = n;
        this.a.setColorFilter(this.f, PorterDuff.Mode.MULTIPLY);
        this.d.setCardBackgroundColor(this.f);
    }

    public void setSharedPreferenceKey(String string2) {
        this.j = string2;
        this.i = SingApplication.g().getSharedPreferences(BubbleTooltipViewWithDropShadow.class.getName(), 0).getBoolean(string2, false);
    }

    public void setText(int n) {
        this.b.setText(n);
    }

}

