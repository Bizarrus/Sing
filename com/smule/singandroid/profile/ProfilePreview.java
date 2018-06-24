/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.Animator$AnimatorListener
 *  android.animation.AnimatorSet
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  android.content.res.Resources
 *  android.view.View
 *  android.widget.RelativeLayout
 */
package com.smule.singandroid.profile;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.view.View;
import android.widget.RelativeLayout;
import com.smule.android.ui.TouchInterceptingFrameLayout;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;

public class ProfilePreview {
    private final TouchInterceptingFrameLayout a;
    private final View b;
    private final View c;
    private final View d;
    private final ProfileImageWithVIPBadge e;
    private final ProfileImageWithVIPBadge f;
    private final View g;
    private final Animator.AnimatorListener h;
    private final Animator.AnimatorListener i;
    private AnimatorSet j;
    private AnimatorSet k;

    public ProfilePreview(RelativeLayout relativeLayout, TouchInterceptingFrameLayout touchInterceptingFrameLayout, Animator.AnimatorListener animatorListener, Animator.AnimatorListener animatorListener2) {
        this.a = touchInterceptingFrameLayout;
        this.b = relativeLayout.findViewById(2131756400);
        this.e = (ProfileImageWithVIPBadge)relativeLayout.findViewById(2131756167);
        this.c = relativeLayout.findViewById(2131756394);
        this.d = relativeLayout.findViewById(2131756382);
        this.f = (ProfileImageWithVIPBadge)relativeLayout.findViewById(2131755044);
        this.g = relativeLayout.findViewById(2131756385);
        this.h = animatorListener;
        this.i = animatorListener2;
    }

    private void d() {
        if (this.j == null || this.k == null) {
            Object object = this.a.getResources();
            final float f = object.getDimensionPixelOffset(2131427385);
            ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener(){

                public void onAnimationUpdate(ValueAnimator object) {
                    object = (Float)object.getAnimatedValue();
                    ProfilePreview.this.d.setTranslationY(object.floatValue());
                    ProfilePreview.this.a.setTranslationY(object.floatValue() + f);
                }
            };
            ValueAnimator valueAnimator = ValueAnimator.ofFloat((float[])new float[]{- f, 0.0f});
            valueAnimator.addUpdateListener(animatorUpdateListener);
            ValueAnimator valueAnimator2 = ValueAnimator.ofFloat((float[])new float[]{0.0f, - f});
            valueAnimator2.addUpdateListener(animatorUpdateListener);
            f = object.getDimensionPixelOffset(2131427964);
            object = new ValueAnimator.AnimatorUpdateListener(){

                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ProfilePreview.this.g.setTranslationY(((Float)valueAnimator.getAnimatedValue()).floatValue());
                }
            };
            animatorUpdateListener = ValueAnimator.ofFloat((float[])new float[]{f, 0.0f});
            animatorUpdateListener.addUpdateListener((ValueAnimator.AnimatorUpdateListener)object);
            ValueAnimator valueAnimator3 = ValueAnimator.ofFloat((float[])new float[]{0.0f, f});
            valueAnimator3.addUpdateListener((ValueAnimator.AnimatorUpdateListener)object);
            this.j = new AnimatorSet();
            this.j.setDuration(200);
            this.j.playTogether(new Animator[]{valueAnimator, animatorUpdateListener});
            this.j.addListener(this.h);
            this.k = new AnimatorSet();
            this.k.setDuration(200);
            this.k.playTogether(new Animator[]{valueAnimator2, valueAnimator3});
            this.k.addListener(this.i);
        }
    }

    public void a() {
        if (!this.c()) {
            this.b.setVisibility(4);
            this.c.setVisibility(0);
            this.e.a(true);
            this.f.setVisibility(0);
            this.f.setVIP(true);
            this.f.setImageDrawable(2130838163);
            this.a.setConsumeTouchOnFrameLevel(true);
            this.a.setTouchableViews(new View[]{this.e, this.c, this.f});
            this.d();
            this.j.start();
        }
    }

    public void b() {
        if (!this.c()) {
            this.a.setConsumeTouchOnFrameLevel(false);
            this.b.setVisibility(0);
            this.c.setVisibility(8);
            this.e.a(false);
            this.f.setVisibility(8);
            this.d();
            this.k.start();
        }
    }

    public boolean c() {
        if (this.j != null && this.j.isRunning() && this.k != null && this.k.isRunning()) {
            return true;
        }
        return false;
    }

}

