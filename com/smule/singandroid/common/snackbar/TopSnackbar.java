/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.os.Handler
 *  android.support.v4.view.ViewCompat
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  android.view.animation.TranslateAnimation
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.common.snackbar;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.utils.NotificationCenter;
import java.util.Observable;
import java.util.Observer;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class TopSnackbar
extends RelativeLayout {
    @ViewById
    protected TextView a;
    private Observer b;
    private final Handler c = new Handler();
    private Runnable d;

    public TopSnackbar(Context context) {
        super(context);
        this.d = new Runnable(){

            @Override
            public void run() {
                if (!ViewCompat.isAttachedToWindow((View)TopSnackbar.this)) {
                    return;
                }
                TopSnackbar.this.d();
            }
        };
    }

    public TopSnackbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new ;
    }

    public TopSnackbar(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.d = new ;
    }

    private void b() {
        this.b = new Observer(){

            @Override
            public void update(Observable observable, Object object) {
                if (object instanceof String) {
                    TopSnackbar.this.a((String)object);
                }
            }
        };
        NotificationCenter.a().a("SNACKBAR_MESSAGE_KEY", this.b);
    }

    private void c() {
        NotificationCenter.a().b("SNACKBAR_MESSAGE_KEY", this.b);
    }

    private void d() {
        if (!ViewCompat.isAttachedToWindow((View)this)) {
            return;
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float)(this.getHeight() * -1));
        translateAnimation.setDuration(500);
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new Animation.AnimationListener(){

            public void onAnimationEnd(Animation animation) {
                TopSnackbar.this.clearAnimation();
                TopSnackbar.this.setVisibility(8);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        this.startAnimation((Animation)translateAnimation);
    }

    @Click
    protected void a() {
        this.c.removeCallbacks(this.d);
        this.d();
    }

    public void a(String string2) {
        if (!ViewCompat.isAttachedToWindow((View)this)) {
            return;
        }
        this.a.setText((CharSequence)string2);
        this.setVisibility(0);
        this.setAlpha(0.0f);
        string2 = new TranslateAnimation(0.0f, 0.0f, (float)(this.getContext().getResources().getDimensionPixelSize(2131427820) * -1), 0.0f);
        string2.setDuration(500);
        string2.setFillAfter(true);
        string2.setAnimationListener(new Animation.AnimationListener(){

            public void onAnimationEnd(Animation animation) {
                TopSnackbar.this.c.postDelayed(TopSnackbar.this.d, 7000);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                TopSnackbar.this.setAlpha(1.0f);
            }
        });
        this.startAnimation((Animation)string2);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.b();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.c();
    }

}

