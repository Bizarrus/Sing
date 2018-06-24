/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$Config
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Paint$Style
 *  android.graphics.Path
 *  android.graphics.Point
 *  android.graphics.PointF
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.Rect
 *  android.graphics.RectF
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.animation.AccelerateInterpolator
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import com.smule.android.logging.Log;
import com.smule.singandroid.customviews.ParticleGenerator;
import com.smule.singandroid.models.Pitch;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PitchView
extends View {
    public static final String a = PitchView.class.getName();
    private double A;
    private final RectF B = new RectF();
    private final Paint C = new Paint();
    private final Rect D = new Rect();
    private final RenderBuffer[] E = new RenderBuffer[2];
    private ParticleGenerator F;
    private float G;
    private final AccelerateInterpolator H = new AccelerateInterpolator();
    private int I;
    private int J;
    private int K;
    private int[] b = new int[4];
    private int c;
    private int d;
    private int e;
    private final List<Pitch> f = new LinkedList<Pitch>();
    private int g;
    private int h;
    private int i;
    private int j;
    private float k;
    private float l;
    private float m;
    private float n;
    private float o;
    private int p;
    private float q;
    private int r = 0;
    private float s = -1.0f;
    private float t;
    private Bitmap u;
    private int v;
    private float w;
    private PointF x = new PointF();
    private double y;
    private double z;

    public PitchView(Context context) {
        super(context);
        this.K = this.getResources().getColor(2131689784);
        this.d();
        if (this.isInEditMode()) {
            this.e();
        }
    }

    public PitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.K = this.getResources().getColor(2131689784);
        this.d();
        if (this.isInEditMode()) {
            this.e();
        }
    }

    public PitchView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.K = this.getResources().getColor(2131689784);
        this.d();
        if (this.isInEditMode()) {
            this.e();
        }
    }

    private float a(float f) {
        return (float)this.D.bottom - this.k * (f - (float)this.g);
    }

    /*
     * Enabled aggressive block sorting
     */
    private float a(float f, float f2) {
        if (f <= 0.0f) {
            f = this.s > 0.0f ? this.s : (float)(this.h - this.g) / 2.0f + (float)this.g;
        }
        f = f2 <= 0.0f ? this.a(f, (float)this.g, (float)this.h) : this.a(f, f2 - 6.0f, f2 + 6.0f);
        if (Math.abs(f - f2) >= 1.0f) {
            f2 = f;
        }
        this.s = f2;
        return f2;
    }

    private float a(float f, float f2, float f3) {
        float f4;
        int n = 0;
        int n2 = 0;
        if (f > f3) {
            do {
                f4 = f;
                if (f > f3) {
                    f4 = f;
                    if (n2 < 5) {
                        f = (float)((double)f - 12.0);
                        ++n2;
                        continue;
                    }
                }
                break;
            } while (true);
        } else {
            f4 = f;
            if (f < f2) {
                n2 = n;
                do {
                    f4 = f;
                    if (f >= f2) break;
                    f4 = f;
                    if (n2 >= 5) break;
                    f = (float)((double)f + 12.0);
                    ++n2;
                } while (true);
            }
        }
        return f4;
    }

    private Path a(Point point, int n) {
        Point point2 = new Point(point.x, point.y + n);
        Point point3 = new Point(point.x + n, point.y + n / 2);
        Path path = new Path();
        path.moveTo((float)point.x, (float)point.y);
        path.lineTo((float)point2.x, (float)point2.y);
        path.lineTo((float)point3.x, (float)point3.y);
        return path;
    }

    private void a(double d) {
        RenderBuffer[] arrrenderBuffer = this.E;
        int n = arrrenderBuffer.length;
        int n2 = 0;
        do {
            RenderBuffer renderBuffer;
            if (n2 >= n || (renderBuffer = arrrenderBuffer[n2]) == null) {
                this.b(d);
                return;
            }
            renderBuffer.a();
            ++n2;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void b(double d) {
        RenderBuffer[] arrrenderBuffer = this.E;
        synchronized (arrrenderBuffer) {
            if (this.E[0] == null || this.E[1] == null) {
                return;
            }
            if (this.E[0].c == -1.0 || d < this.E[0].c) {
                this.E[0].a(0.0, 4.0);
                this.E[1].a(4.0, 8.0);
            } else if (this.E[0].d < d - 1.5) {
                RenderBuffer renderBuffer = this.E[0];
                this.E[0] = this.E[1];
                this.E[1] = renderBuffer;
                this.E[1].a(this.E[0].d, this.E[0].d + 4.0);
            }
            return;
        }
    }

    private void d() {
        this.b[0] = this.getResources().getColor(2131689782);
        this.b[1] = this.getResources().getColor(2131689782);
        this.b[2] = this.getResources().getColor(2131689784);
        this.b[3] = this.getResources().getColor(2131689787);
        this.d = this.getResources().getColor(2131689836);
        this.e = this.getResources().getColor(2131689837);
        this.t = this.getResources().getDimension(2131427872) * 0.5f;
        this.m = this.getResources().getDimension(2131427871);
        this.n = this.getResources().getDimension(2131427874);
        this.o = this.getResources().getDimension(2131427873);
        this.c = this.getResources().getColor(2131689835);
        this.v = (int)this.getResources().getDimension(2131427870);
        this.u = Bitmap.createBitmap((int)this.v, (int)this.v, (Bitmap.Config)Bitmap.Config.ARGB_8888);
        int[] arrn = new int[](this.u);
        Path path = this.a(new Point(0, 0), this.v);
        this.C.setColor(this.c);
        this.C.setStyle(Paint.Style.FILL);
        this.C.setAntiAlias(true);
        arrn.drawPath(path, this.C);
        float f = this.getResources().getDimension(2131427885);
        this.F = new ParticleGenerator(this.getContext(), 2130838212, f, f, 2.0943951023931953, 4.1887902047863905, 1000, 2.0f);
        this.G = this.getResources().getInteger(2131623973);
        int n = (int)((double)this.G * 1.5 + 0.5);
        for (int n2 : this.b) {
            this.F.a(n2, n);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void e() {
        double d = -0.5;
        ArrayList<Pitch> arrayList = new ArrayList<Pitch>();
        int n = 0;
        do {
            if (n >= 12) {
                this.a(0, arrayList);
                this.a(0.0, 0.0f, 0.0f);
                this.q = 4.0f;
                return;
            }
            if (n % 2 == 0) {
                arrayList.add(new Pitch(n, 0, d, 0.2 + d));
            } else {
                arrayList.add(new Pitch(n, 0, d, 0.1 + d));
                arrayList.add(new Pitch(n, 0, 0.1 + d, 0.2 + d));
            }
            d += 0.2;
            ++n;
        } while (true);
    }

    public void a() {
        if (this.F == null) {
            return;
        }
        this.F.a();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(double d, float f, float f2) {
        double d2;
        int n;
        int n2;
        double d3;
        Pitch pitch;
        int n3;
        block13 : {
            this.z = d;
            d2 = this.A > 0.0 ? this.z - this.A : 0.0;
            this.b(d);
            n3 = !this.f.isEmpty() ? this.f.get((int)0).d : -1;
            n = 0;
            n2 = -1;
            int n4 = n3;
            n3 = n2;
            for (int i = this.p; i < this.f.size(); ++i) {
                int n5;
                int n6;
                double d4;
                block14 : {
                    block15 : {
                        pitch = this.f.get(i);
                        d3 = pitch.b + this.y;
                        d4 = pitch.c + this.y;
                        n5 = n;
                        n6 = n4;
                        n2 = n3;
                        if (d < d3) break block14;
                        if (n3 == -1) break block15;
                        n5 = n;
                        n6 = n4;
                        n2 = n3;
                        if (d > d4) break block14;
                    }
                    n2 = pitch.a;
                    n6 = pitch.d;
                    n3 = d >= d3 && d <= d4 ? 1 : 0;
                    n5 = n3;
                }
                if (d3 > d) {
                    n3 = n2;
                    n = n5;
                    n2 = n6;
                    break block13;
                }
                if (d4 < d) {
                    this.p = i + 1;
                }
                n = n5;
                n4 = n6;
                n3 = n2;
            }
            n2 = n4;
        }
        if (n2 == -1) {
            n2 = this.r;
        }
        this.r = n2;
        if (f2 > 0.001f) {
            this.q = this.a(f, (float)n3);
        }
        f = Math.max(1.0f - Math.abs(this.q - (float)n3) / 6.0f, 0.0f);
        if (n != 0 && (double)f >= 0.5 && this.b[this.r] != this.K) {
            f = this.H.getInterpolation(f);
            this.F.a(f * this.G);
            d3 = this.J;
            d = this.r == this.I || this.r == 3 ? 86.0 * d2 : 0.0;
            this.J = (int)(d + d3);
        } else {
            this.F.a(0.0f);
        }
        f = (float)this.v / 2.0f;
        this.x.x = this.w - f;
        f = Math.min(Math.max(this.a(this.q) - f, (float)this.D.top - f), (float)this.D.bottom - f);
        if (this.x.y > 0.0f) {
            f2 = this.x.y;
            pitch = this.x;
            pitch.y = (f - f2) * 0.5f + pitch.y;
        } else {
            this.x.y = f;
        }
        f = this.a(n3);
        n3 = (int)(1.5f * this.l);
        n2 = (int)(f + this.t);
        this.F.a(n3, n2, this.b[this.r]);
        this.A = this.z;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(int n, List<Pitch> iterator) {
        this.f.clear();
        this.f.addAll((Collection<Pitch>)((Object)iterator));
        this.p = 0;
        this.g = -1;
        this.h = -1;
        iterator = iterator.iterator();
        while (iterator.hasNext()) {
            Pitch pitch = (Pitch)iterator.next();
            if (this.g == -1 || pitch.a < this.g) {
                this.g = pitch.a;
            }
            if (this.h != -1 && pitch.a <= this.h) continue;
            this.h = pitch.a;
        }
        --this.g;
        ++this.h;
        this.I = n;
        if (n == 1) {
            this.b[1] = this.getResources().getColor(2131689782);
            this.b[2] = this.getResources().getColor(2131689784);
            return;
        } else {
            if (n != 2) return;
            {
                this.b[1] = this.getResources().getColor(2131689784);
                this.b[2] = this.getResources().getColor(2131689782);
                return;
            }
        }
    }

    public boolean b() {
        if (this.f != null && !this.f.isEmpty()) {
            return true;
        }
        return false;
    }

    public void c() {
        if (this.f == null || this.f.isEmpty()) {
            return;
        }
        this.F.b();
        this.p = 0;
        this.a(0.0);
        this.invalidate();
    }

    public int getScore() {
        return this.J;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onDraw(Canvas canvas) {
        int n;
        super.onDraw(canvas);
        double d = this.z;
        this.C.setStyle(Paint.Style.STROKE);
        this.C.setColor(this.e);
        this.C.setStrokeWidth(this.n);
        int n2 = this.getHeight() / 4;
        for (n = 0; n < 5; ++n) {
            int n3 = n2 * n;
            canvas.drawLine(0.0f, (float)n3, (float)this.getWidth(), (float)n3, this.C);
        }
        n = this.h - this.g > 0 ? 1 : 0;
        if (n != 0) {
            for (RenderBuffer renderBuffer : this.E) {
                float f = (float)((renderBuffer.c - (d - 1.5)) * (double)this.l);
                canvas.drawBitmap(renderBuffer.a, f, 0.0f, null);
            }
        }
        this.C.setStrokeWidth(this.m);
        this.C.setColor(this.d);
        canvas.drawLine(this.w, 0.0f, this.w, (float)this.j, this.C);
        if (n != 0) {
            this.F.a(canvas);
            this.C.setColor(this.c);
            canvas.drawBitmap(this.u, this.x.x, this.x.y, this.C);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void onSizeChanged(int n, int n2, int n3, int n4) {
        int n5 = 0;
        super.onSizeChanged(n, n2, n3, n4);
        if ((float)(this.h - this.g) < 1.0f || n == this.i && n2 == this.j) {
            return;
        }
        this.i = n;
        this.j = n2;
        this.D.top = this.getPaddingTop();
        this.D.bottom = n2 - this.getPaddingBottom();
        this.D.left = 0;
        this.D.right = n;
        this.k = this.D.height() / (this.h - this.g);
        this.l = (float)n / 4.0f;
        this.w = 1.5f * this.l;
        RenderBuffer[] arrrenderBuffer = this.E;
        synchronized (arrrenderBuffer) {
            n3 = n5;
            do {
                if (n3 >= this.E.length) {
                    // MONITOREXIT [3, 4, 6] lbl19 : MonitorExitStatement: MONITOREXIT : var6_6
                    this.b(this.z);
                    return;
                }
                if (this.E[n3] != null) {
                    this.E[n3].b();
                }
                this.E[n3] = new RenderBuffer(n, n2);
                ++n3;
            } while (true);
        }
    }

    public void setAudioLatency(int n) {
        this.y = (double)n / 1000.0 * 0.33;
        Log.b(a, "Audio latency set to: " + this.y + "s");
    }

    private class RenderBuffer {
        public final Bitmap a;
        public final Canvas b;
        public double c;
        public double d;
        public int e;

        public RenderBuffer(int n, int n2) {
            this.e = 0;
            this.a = Bitmap.createBitmap((int)n, (int)n2, (Bitmap.Config)Bitmap.Config.ARGB_4444);
            this.b = new Canvas(this.a);
            this.c = -1.0;
            this.d = -1.0;
        }

        public void a() {
            this.d = -1.0;
            this.c = -1.0;
            this.e = 0;
        }

        /*
         * Enabled aggressive block sorting
         */
        public void a(double d, double d2) {
            Log.b(PitchView.a, "Rendering pitches from " + d + "s to " + d2 + "s");
            this.b.drawColor(0, PorterDuff.Mode.CLEAR);
            this.c = d;
            this.d = d2;
            PitchView.this.C.setStrokeWidth(PitchView.this.o);
            int n = this.e;
            do {
                double d3;
                double d4;
                block7 : {
                    block6 : {
                        if (n >= PitchView.this.f.size()) break block6;
                        Pitch pitch = (Pitch)PitchView.this.f.get(n);
                        d3 = pitch.b + PitchView.this.y;
                        d4 = pitch.c + PitchView.this.y;
                        if (d3 <= PitchView.this.y + d2) break block7;
                    }
                    return;
                }
                if (d4 > d) {
                    float f = (float)((d3 - d) * (double)PitchView.this.l);
                    float f2 = (float)((d4 - d3) * (double)PitchView.this.l);
                    float f3 = PitchView.this.o;
                    PitchView.f((PitchView)PitchView.this).top = PitchView.this.a(pitch.a) - PitchView.this.t;
                    PitchView.f((PitchView)PitchView.this).bottom = PitchView.f((PitchView)PitchView.this).top + PitchView.this.t * 2.0f;
                    PitchView.f((PitchView)PitchView.this).left = f;
                    PitchView.f((PitchView)PitchView.this).right = f2 - f3 + f;
                    PitchView.this.C.setColor(PitchView.this.b[pitch.d]);
                    PitchView.this.C.setStyle(Paint.Style.FILL);
                    this.b.drawRect(PitchView.this.B, PitchView.this.C);
                } else {
                    this.e = n + 1;
                }
                ++n;
            } while (true);
        }

        public void b() {
            if (!this.a.isRecycled()) {
                this.a.recycle();
            }
        }
    }

}

