/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Canvas
 *  android.graphics.drawable.ClipDrawable
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.animation.AccelerateInterpolator
 *  android.view.animation.Interpolator
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

public class CheckmarkAnimation
extends View {
    private ClipDrawable a;
    private int b;
    private int c;
    private long d = 0;
    private boolean e = false;
    private int f;
    private Interpolator g;

    public CheckmarkAnimation(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a(context, attributeSet);
    }

    public CheckmarkAnimation(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.a(context, attributeSet);
    }

    public void a() {
        this.e = true;
        this.d = 0;
        this.f = 0;
        this.postInvalidate();
    }

    void a(Context context, AttributeSet attributeSet) {
        this.g = new AccelerateInterpolator();
        context = context.getResources().getDrawable(2130837824);
        this.b = context.getIntrinsicWidth();
        this.c = context.getIntrinsicHeight();
        this.a = new ClipDrawable((Drawable)context, 3, 1);
    }

    public void b() {
        this.e = false;
        this.postInvalidate();
    }

    public void c() {
        this.e = false;
    }

    protected void onDraw(Canvas canvas) {
        if (!this.e) {
            canvas.drawColor(0);
            return;
        }
        long l = this.getDrawingTime();
        if (this.d == 0) {
            this.d = l;
        }
        float f = l - this.d;
        this.d = l;
        this.f += (int)(f * 50.0f);
        this.a.setLevel(this.f);
        int n = (canvas.getWidth() - this.b) / 2;
        int n2 = this.b;
        int n3 = (canvas.getHeight() - this.c) / 2;
        int n4 = this.c;
        this.a.setBounds(n, n3, n2 + n, n4 + n3);
        this.a.draw(canvas);
        if ((float)this.f >= 10000.0f) {
            this.c();
            return;
        }
        this.invalidate();
    }
}

