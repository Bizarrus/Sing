/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$Config
 *  android.graphics.Canvas
 *  android.graphics.ColorFilter
 *  android.graphics.Paint
 *  android.graphics.Paint$Cap
 *  android.graphics.Paint$Join
 *  android.graphics.Paint$Style
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.PorterDuffColorFilter
 *  android.support.v4.graphics.ColorUtils
 *  android.util.AttributeSet
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$MeasureSpec
 */
package com.smule.singandroid;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.v4.graphics.ColorUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.smule.android.logging.Log;
import com.smule.android.utils.NotificationCenter;
import java.util.Arrays;

public class WaveformView
extends View {
    private static final String a = WaveformView.class.getName();
    private int b;
    private int c;
    private int d;
    private int e;
    private float[] f;
    private float[] g;
    private Bitmap h;
    private Paint i;
    private int j = 0;
    private Paint k;
    private Paint l;
    private float m;
    private float n;
    private float o;
    private ColorFilter p;
    private int q = -16777216;
    private int r = -7829368;
    private int s = -16776961;
    private boolean t = true;
    private boolean u = true;
    private boolean v = false;

    public WaveformView(Context context) {
        super(context);
        this.a();
        this.n = 0.0f;
        this.o = 0.5f;
    }

    public WaveformView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a();
        this.n = 0.0f;
        this.o = 0.5f;
    }

    public WaveformView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.a();
        this.n = 0.0f;
        this.o = 0.5f;
    }

    private void a(float f, float f2) {
        this.m = f;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(Canvas canvas) {
        if (this.h == null) {
            Log.d(a, "Background not created");
            return;
        } else {
            canvas.clipRect(0, 0, this.getWidth(), this.getHeight());
            canvas.drawBitmap(this.h, 0.0f, 0.0f, this.i);
            if (this.u) return;
            {
                this.b(canvas);
                return;
            }
        }
    }

    private void a(Canvas canvas, float f, float f2, float f3, float f4) {
        canvas.clipRect(f, f2, f3, f4);
        this.l.setColorFilter(this.p);
        canvas.drawBitmap(this.h, f, f2, this.l);
    }

    private void b(float f, float f2) {
        if (Math.abs(f - this.m) >= 4.0f) {
            this.m = f;
        }
    }

    private void b(Canvas canvas) {
        this.a(canvas, 0.0f, 0.0f, this.m, this.getHeight());
    }

    private float c(float f) {
        return Math.min(Math.max(0.0f, f), (float)this.getWidth());
    }

    private void c() {
        NotificationCenter.a().a("USER_MODIFIED_SEEK_TIME_EVENT", (Object)new Float(this.b(this.m)));
    }

    private void c(Canvas canvas) {
        if (this.u) {
            canvas.clipRect(0, 0, this.getWidth(), this.getHeight());
            float f = this.getHeight() / 2;
            this.k.setStyle(Paint.Style.STROKE);
            this.k.setColor(-1);
            this.k.setStrokeWidth((float)this.d);
            canvas.drawLine(this.m, 0.0f, this.m, (float)this.getHeight(), this.k);
            this.k.setStyle(Paint.Style.FILL);
            canvas.drawCircle(this.m, f, (float)this.e, this.k);
        }
    }

    private float d(float f) {
        return Math.min(Math.max(0.0f, f), (float)this.getHeight());
    }

    public float a(float f) {
        if (f == 0.0f || this.getWidth() == 0) {
            return 0.0f;
        }
        return Math.min(Math.max(0.0f, f / this.n * (float)this.getWidth()), (float)this.getWidth());
    }

    protected void a() {
        this.k = new Paint();
        this.k.setAntiAlias(true);
        this.k.setDither(true);
        this.k.setColor(-16777216);
        this.k.setStyle(Paint.Style.STROKE);
        this.k.setStrokeJoin(Paint.Join.ROUND);
        this.k.setStrokeCap(Paint.Cap.ROUND);
        this.k.setStrokeWidth(3.0f);
        this.i = new Paint(4);
        this.i.setFilterBitmap(true);
        this.i.setStrokeCap(Paint.Cap.ROUND);
        this.l = new Paint();
        this.l.setDither(true);
        this.l.setAntiAlias(true);
        this.l.setStyle(Paint.Style.FILL);
        Resources resources = this.getResources();
        this.b = resources.getDimensionPixelOffset(2131427974);
        this.c = resources.getDimensionPixelOffset(2131427975);
        this.d = resources.getDimensionPixelOffset(2131427977);
        this.e = resources.getDimensionPixelOffset(2131427976);
    }

    public void a(short[] arrs, int n, int n2) {
        if (arrs == null || arrs.length == 0) {
            return;
        }
        n = n2 = arrs.length;
        if (n2 % 2 != 0) {
            Log.b(a, "Number of short values should be even; decrementing by one");
            n = n2 - 1;
        }
        n2 = n / 2;
        this.f = new float[n2];
        for (n = 0; n < n2; ++n) {
            this.f[n] = (float)arrs[n * 2] / 32767.0f;
        }
        this.b();
        this.postInvalidate();
    }

    /*
     * Enabled aggressive block sorting
     */
    public float[] a(float[] arrf, int n) {
        if (arrf == null) {
            return null;
        }
        if (arrf.length == n) {
            return Arrays.copyOf(arrf, arrf.length);
        }
        int n2 = arrf.length;
        float[] arrf2 = new float[n];
        float f = (float)n2 / (float)n;
        int n3 = 0;
        do {
            float[] arrf3 = arrf2;
            if (n3 >= n) return arrf3;
            float f2 = (float)n3 * f;
            int n4 = (int)f2;
            float f3 = f2 - (float)n4;
            arrf2[n3] = f2 <= 0.0f ? arrf[0] : (n4 >= n2 - 1 ? arrf[n2 - 1] : (1.0f - f3) * arrf[n4] + arrf[n4 + 1] * f3);
            ++n3;
        } while (true);
    }

    public float b(float f) {
        if (f == 0.0f || this.getWidth() == 0) {
            return 0.0f;
        }
        float f2 = f / (float)this.getWidth() * this.n;
        Log.b(a, "x = " + f + "; mTotalDurationSec = " + this.n + "; returning = " + Math.min(Math.max(0.0f, f2), this.n));
        return Math.min(Math.max(0.0f, f2), this.n);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void b() {
        float f;
        Canvas canvas;
        float f2;
        int n;
        int n2;
        int n3;
        int n4;
        block8 : {
            block7 : {
                if (this.getWidth() == 0 || this.getHeight() == 0 || this.f == null) break block7;
                if (this.h != null) {
                    this.h.recycle();
                }
                this.h = Bitmap.createBitmap((int)this.getWidth(), (int)this.getHeight(), (Bitmap.Config)Bitmap.Config.ARGB_8888);
                canvas = new Canvas(this.h);
                this.h.eraseColor(0);
                this.j = this.h.getHeight();
                this.i.setStrokeWidth((float)this.b);
                this.i.setStyle(Paint.Style.FILL);
                Paint paint = this.i;
                n3 = this.u ? this.q : ColorUtils.setAlphaComponent((int)this.q, (int)127);
                paint.setColor(n3);
                this.i.setAntiAlias(true);
                this.p = new PorterDuffColorFilter(this.q, PorterDuff.Mode.SRC_ATOP);
                n = this.b + this.c;
                n2 = this.getWidth() / n;
                if (this.g == null || this.g.length != this.getWidth()) {
                    this.g = this.a(this.f, this.getWidth());
                }
                f2 = this.o;
                f = 0.5f * (float)this.getHeight();
                n4 = this.b * 2;
                if (this.g != null) break block8;
            }
            return;
        }
        n3 = 0;
        while (n3 < n2) {
            int n5 = n3 * n;
            float f3 = (this.g[n5] * (4.0f * f2) + 1.0f) * f;
            float f4 = 2.0f * f - f3;
            float f5 = f3;
            float f6 = f4;
            if (f4 - f3 < (float)n4) {
                f5 = f - (float)n4;
                f6 = f + (float)n4;
            }
            canvas.drawLine((float)n5, f5, (float)n5, f6, this.i);
            ++n3;
        }
    }

    public float getCurrentPositionSec() {
        return this.b(this.m);
    }

    public float getTotalDurationSec() {
        return this.n;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.a(canvas);
        this.c(canvas);
    }

    protected void onMeasure(int n, int n2) {
        this.setMeasuredDimension(View.MeasureSpec.getSize((int)n), View.MeasureSpec.getSize((int)n2));
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onSizeChanged(int n, int n2, int n3, int n4) {
        if (this.h == null || (float)n2 > (float)this.j * 1.25f) {
            this.b();
            return;
        } else {
            Bitmap bitmap = Bitmap.createScaledBitmap((Bitmap)this.h, (int)n, (int)n2, (boolean)false);
            if (bitmap == this.h) return;
            {
                this.h.recycle();
                this.h = bitmap;
                return;
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.t) {
            float f = this.c(motionEvent.getX());
            float f2 = this.d(motionEvent.getY());
            switch (motionEvent.getAction()) {
                default: {
                    return true;
                }
                case 0: {
                    this.a(f, f2);
                    this.invalidate();
                    this.v = true;
                    return true;
                }
                case 2: {
                    this.b(f, f2);
                    this.invalidate();
                    return true;
                }
                case 1: 
            }
            this.c();
            this.invalidate();
            this.v = false;
            return true;
        }
        return false;
    }

    public void setCurrentPositionSec(float f) {
        if (!this.v) {
            this.m = this.a(f);
            this.postInvalidate();
            return;
        }
        Log.b(a, "ignoring position change while dragging");
    }

    public void setDrawScrubberCircle(boolean bl) {
        this.u = bl;
    }

    public void setForegroundVolume(float f) {
        this.o = f;
    }

    public void setSeekColor(int n) {
        this.s = n;
        this.postInvalidate();
    }

    public void setTotalDurationSec(float f) {
        this.n = f;
    }

    public void setTouchable(boolean bl) {
        this.t = bl;
    }

    public void setWaveformColor(int n) {
        this.q = n;
        this.postInvalidate();
    }
}

