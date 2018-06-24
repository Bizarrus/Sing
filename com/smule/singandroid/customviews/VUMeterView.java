/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Canvas
 *  android.graphics.Color
 *  android.graphics.Paint
 *  android.graphics.Paint$Style
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.animation.DecelerateInterpolator
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class VUMeterView
extends View {
    private final int[] a = new int[19];
    private final Paint b = new Paint();
    private final DecelerateInterpolator c = new DecelerateInterpolator(2.0f);
    private int d;

    public VUMeterView(Context context) {
        super(context);
        this.a();
    }

    public VUMeterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a();
    }

    public VUMeterView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.a();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a() {
        float[] arrf = new float[3];
        float[] arrf2 = new float[3];
        float[] arrf3 = new float[3];
        Color.colorToHSV((int)this.getResources().getColor(2131689990), (float[])arrf);
        Color.colorToHSV((int)this.getResources().getColor(2131689989), (float[])arrf2);
        float f = Math.abs(arrf2[0] - arrf[0]) / 18.0f;
        float f2 = Math.abs(arrf[0] - arrf2[0]) / 18.0f;
        f = f > f2 ? Math.signum(arrf[0] - arrf2[0]) * f2 : (f *= Math.signum(arrf2[0] - arrf[0]));
        f2 = (arrf2[1] - arrf[1]) / 18.0f;
        int n = arrf[1] < arrf2[1] ? 1 : -1;
        float f3 = n;
        float f4 = (arrf2[2] - arrf[2]) / 18.0f;
        n = arrf[2] < arrf2[2] ? 1 : -1;
        float f5 = n;
        n = 0;
        do {
            if (n >= 19) {
                this.b.setAntiAlias(true);
                this.b.setStyle(Paint.Style.FILL);
                return;
            }
            arrf3[0] = arrf[0] + (float)n * f;
            arrf3[1] = arrf[1] + (float)n * (f2 * f3);
            arrf3[2] = arrf[2] + (float)n * (f4 * f5);
            this.a[n] = Color.HSVToColor((float[])arrf3);
            ++n;
        } while (true);
    }

    public void a(double d) {
        int n = Math.min((int)((double)(this.c.getInterpolation((float)d) * 19.0f) + 0.5), 19);
        if (n != this.d) {
            this.d = n;
            this.invalidate();
        }
    }

    public float getVolumeFraction() {
        return (float)this.d / 19.0f;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = canvas.getWidth();
        float f2 = (float)canvas.getHeight() / 19.0f;
        float f3 = f < f2 ? f : f2;
        f3 = 0.5f * (f3 * 0.75f);
        int n = 0;
        while (n < this.d) {
            this.b.setColor(this.a[n]);
            canvas.drawCircle(f /= 2.0f, (float)canvas.getHeight() - ((float)n * f2 + f3), f3, this.b);
            ++n;
        }
    }
}

