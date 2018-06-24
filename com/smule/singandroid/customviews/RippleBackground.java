/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.TypedArray
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Paint$Style
 *  android.graphics.Point
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnLayoutChangeListener
 *  android.view.animation.DecelerateInterpolator
 *  android.view.animation.Interpolator
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.smule.singandroid.R;

public class RippleBackground
extends View {
    private Paint a;
    private Point b = new Point();
    private Point c = new Point();
    private float d;
    private float e;
    private float f;
    private int g;
    private boolean h;
    private float i;
    private float j;
    private long k = 0;
    private float l = 0.0f;
    private volatile boolean m = false;
    private Interpolator n;

    public RippleBackground(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a(context, attributeSet);
    }

    public RippleBackground(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.a(context, attributeSet);
    }

    static /* synthetic */ Point a(RippleBackground rippleBackground) {
        return rippleBackground.c;
    }

    static /* synthetic */ Point b(RippleBackground rippleBackground) {
        return rippleBackground.b;
    }

    private float c() {
        return this.e / this.f * (float)this.g - 1.0f;
    }

    public void a() {
        if (!this.m) {
            this.m = true;
            this.k = 0;
            this.l = this.c();
        }
        this.postInvalidate();
    }

    /*
     * Enabled aggressive block sorting
     */
    void a(Context context, AttributeSet attributeSet) {
        context = context.obtainStyledAttributes(attributeSet, R.RippleBackground);
        int n = context.getColor(5, this.getResources().getColor(2131689489));
        this.a = new Paint();
        this.a.setAntiAlias(true);
        this.a.setColor(n);
        switch (context.getInt(0, 2)) {
            default: {
                this.a.setStyle(Paint.Style.FILL);
                this.h = false;
                break;
            }
            case 1: {
                this.a.setStyle(Paint.Style.STROKE);
                this.h = true;
            }
        }
        this.n = new DecelerateInterpolator();
        this.d = context.getFloat(2, 2.0f);
        this.e = context.getDimension(3, 0.0f) / 2.0f;
        this.f = context.getDimension(4, (float)this.getResources().getDimensionPixelSize(2131427814)) / 2.0f;
        this.g = context.getInteger(1, this.getResources().getInteger(2131623971));
        this.i = context.getDimension(6, -1.0f);
        this.j = context.getDimension(7, -1.0f);
        float f = this.i > 0.0f && this.j < 0.0f ? this.i : this.j;
        this.j = f;
        context.recycle();
        this.addOnLayoutChangeListener(new View.OnLayoutChangeListener(){

            public void onLayoutChange(View view, int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8) {
                RippleBackground.a((RippleBackground)RippleBackground.this).x = n3 - n;
                RippleBackground.a((RippleBackground)RippleBackground.this).y = n4 - n2;
                RippleBackground.b((RippleBackground)RippleBackground.this).x = RippleBackground.a((RippleBackground)RippleBackground.this).x / 2;
                RippleBackground.b((RippleBackground)RippleBackground.this).y = RippleBackground.a((RippleBackground)RippleBackground.this).y / 2;
            }
        });
    }

    public void b() {
        this.m = false;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.m = false;
    }

    protected void onDraw(Canvas canvas) {
        if (!this.m) {
            return;
        }
        long l = this.getDrawingTime();
        if (this.k == 0) {
            this.k = l;
        }
        float f = Math.min((float)(l - this.k) / (this.d * 1000.0f), 1000.0f);
        this.k = l;
        this.l += f;
        f = this.l;
        float f2 = (int)this.l;
        int n = 1;
        while ((float)n < Math.min((float)this.g, this.l)) {
            float f3 = this.n.getInterpolation(((float)n + (f - f2)) / (float)this.g);
            if (this.h && this.i > 0.0f) {
                double d = this.j - this.i;
                this.a.setStrokeWidth((float)(d * (double)f3 + (double)this.i));
            }
            this.a.setAlpha((int)(255.0f * (1.0f - f3)));
            canvas.drawCircle((float)this.b.x, (float)this.b.y, f3 * this.f, this.a);
            ++n;
        }
        this.invalidate();
    }

}

