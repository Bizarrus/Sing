/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.drawable.Drawable
 *  android.view.View
 *  android.view.animation.AlphaAnimation
 *  android.view.animation.Animation
 *  android.widget.ProgressBar
 *  android.widget.TextView
 */
package com.smule.singandroid.dialogs;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.ui.dialogs.SmuleDialog;

public class BusyScreenDialog
extends SmuleDialog {
    protected TextView b;
    protected TextView c;
    protected View d;

    public BusyScreenDialog(Context context, int n) {
        this(context, context.getResources().getString(n));
    }

    public BusyScreenDialog(Context context, String string2) {
        this(context, string2, null);
    }

    public BusyScreenDialog(Context context, String string2, String string3) {
        super(context, 2131493339, false);
        this.setContentView(2130903096);
        this.d = this.findViewById(2131755221);
        ((ProgressBar)this.findViewById(2131755344)).getIndeterminateDrawable().setColorFilter(context.getResources().getColor(2131689579), PorterDuff.Mode.SRC_ATOP);
        this.b = (TextView)this.findViewById(2131755345);
        this.c = (TextView)this.findViewById(2131755229);
        this.b.setText((CharSequence)string2);
        if (string3 != null) {
            this.c.setText((CharSequence)string3);
        }
    }

    public void onBackPressed() {
    }

    public void show() {
        super.show();
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setStartOffset(1000);
        alphaAnimation.setDuration(200);
        alphaAnimation.setFillAfter(true);
        this.d.startAnimation((Animation)alphaAnimation);
    }
}

