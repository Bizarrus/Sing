/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Path
 *  android.util.AttributeSet
 *  android.view.View
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class VisualizerView
extends View {
    static final float a;
    static final float b;
    static final float c;
    static final float d;
    static final float e;
    static final float[][] f;
    private static final String g;
    private double h;
    private float i;
    private final Paint j = new Paint();
    private final Path k = new Path();
    private float l;
    private float m;
    private float n;

    static {
        g = VisualizerView.class.getName();
        a = (float)Math.sqrt(2.0);
        b = 2.0f * a / 7.0f - 0.14285715f;
        c = 4.0f * a / 7.0f - 0.2857143f;
        d = a / 2.0f;
        e = 3.0f * a / 7.0f + 0.2857143f;
        float[] arrf = new float[]{b, c, d};
        float[] arrf2 = new float[]{e, 1.0f, 1.0f};
        float[] arrf3 = new float[]{1.0f, e, d};
        float[] arrf4 = new float[]{c, b, 0.0f};
        float[] arrf5 = new float[]{- b, - c, - d};
        float[] arrf6 = new float[]{- e, -1.0f, -1.0f};
        float f = - e;
        float f2 = - d;
        float[] arrf7 = new float[]{- c, - b, 0.0f};
        VisualizerView.f = new float[][]{arrf, arrf2, arrf3, arrf4, arrf5, arrf6, {-1.0f, f, f2}, arrf7};
    }

    public VisualizerView(Context context) {
        super(context);
        this.a(null, 0);
    }

    public VisualizerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a(attributeSet, 0);
    }

    public VisualizerView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.a(attributeSet, n);
    }

    private static float a(float f, float f2, float f3) {
        if (f < 0.0f || f > f3) {
            return 0.0f;
        }
        return (float)((double)f2 * 0.5 * (1.0 - Math.cos(6.2831855f * f / f3)));
    }

    private float a(float f, float f2, float f3, float f4, float f5) {
        return Math.min(Math.max((f - f2) * (f5 - f4) / (f3 - f2) + f4, f4), f5);
    }

    private void a(Path path, float f, float f2, float f3) {
        path.reset();
        float f4 = (float)(this.h % 0.5 / 0.5) * this.l - this.l;
        path.moveTo(f4, f);
        int n = 0;
        int n2 = 0;
        while (n < 32) {
            if (f4 > - this.n && f4 < f2) {
                VisualizerView.a(path, f4, this.m, f2, f, f3, VisualizerView.f[n2]);
            }
            float f5 = this.n;
            ++n;
            n2 = (n2 + 1) % 8;
            f4 += f5;
        }
        path.lineTo(f4, f);
    }

    private static void a(Path path, float f, float f2, float f3, float f4, float f5, float[] arrf) {
        float f6 = (f += f2) + f2;
        f2 = f6 + f2;
        path.cubicTo(f, f4 + VisualizerView.a(f, arrf[0] * f5, f3), f6, f4 + VisualizerView.a(f6, arrf[1] * f5, f3), f2, f4 + VisualizerView.a(f2, arrf[2] * f5, f3));
    }

    private void a(AttributeSet attributeSet, int n) {
        this.j.setAntiAlias(true);
        this.j.setColor(-1);
    }

    public void a(double d, float f) {
        this.h = d;
        this.i = f;
        this.invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int n = canvas.getWidth();
        int n2 = canvas.getHeight();
        float f = (float)n2 / 2.0f;
        float f2 = n2;
        float f3 = this.a(this.i, -30.0f, -6.0f, 0.05f, 1.0f);
        this.a(this.k, f, n, f3 * (f2 * 0.5f));
        canvas.drawPath(this.k, this.j);
    }

    protected void onSizeChanged(int n, int n2, int n3, int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        this.l = n / 3;
        this.m = this.l / 24.0f;
        this.n = this.m * 3.0f;
    }
}

