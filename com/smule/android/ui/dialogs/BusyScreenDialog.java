/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.animation.AlphaAnimation
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 */
package com.smule.android.ui.dialogs;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.smule.android.R;
import com.smule.android.ui.dialogs.SmuleDialog;

public class BusyScreenDialog
extends SmuleDialog {
    protected View a;

    public BusyScreenDialog(Context context) {
        super(context);
        this.a(context);
    }

    private void a(Context context) {
        this.setContentView(R.layout.cm_busy_screen_dialog);
        this.a = this.findViewById(R.id.root);
    }

    public void a(long l) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setStartOffset(l);
        alphaAnimation.setDuration(200);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener(){

            public void onAnimationEnd(Animation animation) {
                BusyScreenDialog.this.dismiss();
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        this.a.startAnimation((Animation)alphaAnimation);
    }

    public void show() {
        super.show();
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setStartOffset(1000);
        alphaAnimation.setDuration(200);
        alphaAnimation.setFillAfter(true);
        this.a.startAnimation((Animation)alphaAnimation);
    }

}

