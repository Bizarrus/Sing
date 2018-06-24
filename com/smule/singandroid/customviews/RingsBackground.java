/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Point
 *  android.view.View
 *  android.view.View$OnLayoutChangeListener
 *  android.view.animation.Interpolator
 */
package com.smule.singandroid.customviews;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;
import android.view.animation.Interpolator;

public class RingsBackground
extends View {
    private Paint a;
    private Point b;
    private Point c;
    private float d;
    private float e;
    private int f;
    private long g;
    private float h;
    private float i;
    private boolean j;
    private Interpolator k;

    static /* synthetic */ Point a(RingsBackground ringsBackground) {
        return ringsBackground.c;
    }

    static /* synthetic */ Point b(RingsBackground ringsBackground) {
        return ringsBackground.b;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onDraw(Canvas canvas) {
        if (!this.j) {
            return;
        }
        long l = this.getDrawingTime();
        if (this.g == 0) {
            this.g = l;
        }
        float f = Math.min((float)(l - this.g) / (this.d * 1000.0f), 1000.0f);
        this.g = l;
        this.h += f;
        f = this.h;
        float f2 = (int)this.h;
        int n = 0;
        do {
            if ((float)n >= Math.min((float)this.f, this.h)) {
                this.invalidate();
                return;
            }
            float f3 = this.k.getInterpolation(((float)n + (f - f2)) / (float)this.f);
            if ((double)f3 < 0.3) {
                this.a.setAlpha((int)((double)this.i * ((double)f3 / 0.3)));
            } else {
                this.a.setAlpha((int)((double)(this.i * (1.0f - f3)) / 0.7));
            }
            canvas.drawCircle((float)this.b.x, (float)this.b.y, f3 * this.e, this.a);
            ++n;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setMaxAlpha(float f) {
        float f2;
        if (f < 0.0f) {
            f2 = 0.0f;
        } else {
            f2 = f;
            if (f > 1.0f) {
                f2 = 1.0f;
            }
        }
        this.i = 102.0f * f2;
    }

}

