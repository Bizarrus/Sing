/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.v7.widget.CardView
 *  android.util.AttributeSet
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.view.ViewParent
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnPreDrawListener
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;

public class AnimatableCardView
extends CardView {
    private float a = 0.0f;
    private float b = 0.0f;
    private ViewTreeObserver.OnPreDrawListener c = null;

    public AnimatableCardView(Context context) {
        super(context);
    }

    public AnimatableCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AnimatableCardView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    public float getXFraction() {
        return this.a;
    }

    public float getYFraction() {
        return this.b;
    }

    public void setXFraction(float f) {
        this.a = f;
        if (((ViewGroup)this.getParent()).getWidth() == 0) {
            if (this.c == null) {
                this.c = new ViewTreeObserver.OnPreDrawListener(){

                    public boolean onPreDraw() {
                        AnimatableCardView.this.getViewTreeObserver().removeOnPreDrawListener(AnimatableCardView.this.c);
                        AnimatableCardView.this.setXFraction(AnimatableCardView.this.a);
                        return true;
                    }
                };
                this.getViewTreeObserver().addOnPreDrawListener(this.c);
            }
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)this.getLayoutParams();
        float f2 = ((ViewGroup)this.getParent()).getWidth();
        float f3 = (float)this.getWidth() * this.getScaleX() / 2.0f;
        this.setTranslationX(Math.max(0.0f, (f2 - ((float)marginLayoutParams.leftMargin + f3)) * f));
    }

    public void setYFraction(float f) {
        this.b = f;
        if (((ViewGroup)this.getParent()).getHeight() == 0) {
            if (this.c == null) {
                this.c = new ViewTreeObserver.OnPreDrawListener(){

                    public boolean onPreDraw() {
                        AnimatableCardView.this.getViewTreeObserver().removeOnPreDrawListener(AnimatableCardView.this.c);
                        AnimatableCardView.this.setYFraction(AnimatableCardView.this.b);
                        return true;
                    }
                };
                this.getViewTreeObserver().addOnPreDrawListener(this.c);
            }
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)this.getLayoutParams();
        this.setTranslationY(Math.max(0.0f, ((float)((ViewGroup)this.getParent()).getHeight() - ((float)this.getHeight() * this.getScaleY() / 2.0f - (float)marginLayoutParams.topMargin)) * f));
    }

}

