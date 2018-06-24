/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.graphics.Canvas
 *  android.graphics.Color
 *  android.graphics.ColorFilter
 *  android.graphics.LightingColorFilter
 *  android.graphics.Paint
 *  android.graphics.Point
 *  android.graphics.PointF
 *  android.view.animation.AccelerateInterpolator
 *  android.view.animation.Interpolator
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ParticleGenerator {
    private static final String a = ParticleGenerator.class.getName();
    private final List<Particle> b = new LinkedList<Particle>();
    private final List<Particle> c = new LinkedList<Particle>();
    private final Point d = new Point();
    private int e;
    private long f;
    private float g;
    private float h;
    private boolean i = false;
    private double j;
    private double k;
    private int l;
    private float m;
    private final Bitmap n;

    public ParticleGenerator(Context context, int n, float f, float f2, double d, double d2, int n2, float f3) {
        context = BitmapFactory.decodeResource((Resources)context.getResources(), (int)n);
        this.j = d;
        this.k = d2;
        this.l = n2;
        this.m = f3;
        this.n = Bitmap.createScaledBitmap((Bitmap)context, (int)((int)f), (int)((int)f2), (boolean)true);
        context.recycle();
    }

    private double a(double d, double d2) {
        return (d2 - d) * Math.random() + d;
    }

    private int b(int n, int n2) {
        return (int)((double)n + Math.random() * (double)(n2 - n + 1));
    }

    public void a() {
        this.i = true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(float f) {
        synchronized (this) {
            int n;
            long l;
            this.g = f;
            long l2 = System.currentTimeMillis();
            if (this.i) {
                l = 0;
                this.i = false;
            } else {
                l = this.f > 0 ? l2 - this.f : 0;
                l = Math.max(l, 0);
            }
            float f2 = (float)l / 1000.0f;
            this.h += f * f2;
            int n2 = (int)this.h;
            for (n = 0; n < n2; ++n) {
                Object object;
                if (!this.c.isEmpty()) {
                    object = this.c.remove(0);
                    object.a(this.d.x, this.d.y, this.e);
                } else {
                    object = new Particle(this.d.x, this.d.y, this.e, this.j, this.k, this.l, this.m);
                }
                this.b.add((Particle)object);
            }
            this.h -= (float)n2;
            if (!this.b.isEmpty()) {
                for (Particle particle : this.b) {
                    if (!particle.a()) continue;
                    particle.a(f2);
                }
                for (n = this.b.size() - 1; n >= 0; --n) {
                    if (!this.b.get(n).b()) continue;
                    this.c.add(this.b.remove(n));
                }
            }
            this.f = l2;
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(int n, int n2) {
        synchronized (this) {
            Object object = this.c.iterator();
            while (object.hasNext()) {
                object.next().a(0, 0, n);
            }
            int n3 = this.c.size() - n2;
            if (n3 > 0) {
                object = new LinkedList();
                for (n2 = 0; n2 < n3; ++n2) {
                    object.add(new Particle(0, 0, n, this.j, this.k, this.l, this.m));
                }
                this.c.addAll((Collection<Particle>)object);
            }
            return;
        }
    }

    public void a(int n, int n2, int n3) {
        this.d.x = n;
        this.d.y = n2;
        this.e = n3;
    }

    public void a(Canvas canvas) {
        synchronized (this) {
            Iterator<Particle> iterator = this.b.iterator();
            while (iterator.hasNext()) {
                iterator.next().a(canvas);
            }
            return;
        }
    }

    public void b() {
        synchronized (this) {
            int n = this.b.size();
            for (int i = 0; i < n; ++i) {
                this.c.add(this.b.remove(0));
            }
            return;
        }
    }

    public class Particle {
        private final double b;
        private final double c;
        private final Paint d;
        private Interpolator e;
        private final float[] f;
        private Map<Integer, ColorFilter> g;
        private final Point h;
        private final PointF i;
        private double j;
        private double k;
        private int l;
        private long m;
        private float n;
        private int o;
        private ColorFilter p;
        private int q;

        public Particle(int n, int n2, int n3, double d, double d2, int n4, float f) {
            this.d = new Paint();
            this.f = new float[3];
            this.g = new HashMap<Integer, ColorFilter>();
            this.h = new Point();
            this.i = new PointF();
            this.j = 2.0943951023931953;
            this.k = 4.1887902047863905;
            this.b = (double)ParticleGenerator.this.n.getWidth() / 2.0;
            this.c = (double)ParticleGenerator.this.n.getHeight() / 2.0;
            this.j = d;
            this.k = d2;
            this.n = n4;
            this.q = 0;
            this.e = new AccelerateInterpolator(f);
            this.a(n, n2, n3);
        }

        public void a(float f) {
            if (this.l != 1) {
                Point point = this.h;
                point.x = (int)((float)point.x + this.i.x * f);
                point = this.h;
                point.y = (int)((float)point.y + this.i.y * f);
                this.q = (int)((float)this.q + 1000.0f * f);
                if ((float)this.q >= this.n) {
                    this.l = 1;
                }
                f = (float)this.q / this.n;
                int n = (int)Math.max((1.0f - this.e.getInterpolation(f)) * 255.0f, 0.0f);
                this.d.setAlpha(n);
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        public void a(int n, int n2, int n3) {
            this.h.x = n;
            this.h.y = n2;
            this.l = 0;
            this.m = System.currentTimeMillis();
            this.q = 0;
            double d = ParticleGenerator.this.a(this.j, this.k);
            double d2 = ParticleGenerator.this.a(75.0, 300.0);
            this.i.x = (float)(Math.cos(d) * d2);
            this.i.y = (float)(Math.sin(d) * d2);
            if (this.g.containsKey(n3)) {
                this.p = this.g.get(n3);
            } else {
                Color.colorToHSV((int)n3, (float[])this.f);
                this.f[0] = (int)(this.f[0] + ((float)ParticleGenerator.this.b(0, 33) - 16.5f));
                this.f[1] = (float)((double)this.f[1] + (ParticleGenerator.this.a(0.0, 0.5) - 0.25));
                this.f[2] = (float)((double)this.f[2] + (ParticleGenerator.this.a(0.0, 0.4000000059604645) - 0.20000000298023224));
                this.o = Color.HSVToColor((float[])this.f);
                this.p = new LightingColorFilter(this.o, 0);
                this.g.put(n3, this.p);
            }
            this.d.setColor(this.o);
            this.d.setAntiAlias(true);
            this.d.setColorFilter(this.p);
            ParticleGenerator.this.f = this.m;
        }

        public void a(Canvas canvas) {
            int n = (int)((double)this.h.x - this.b);
            int n2 = (int)((double)this.h.y - this.c);
            canvas.drawBitmap(ParticleGenerator.this.n, (float)n, (float)n2, this.d);
        }

        public boolean a() {
            if (this.l == 0) {
                return true;
            }
            return false;
        }

        public boolean b() {
            if (this.l == 1) {
                return true;
            }
            return false;
        }
    }

}

