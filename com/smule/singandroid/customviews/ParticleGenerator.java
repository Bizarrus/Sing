package com.smule.singandroid.customviews;

import android.content.Context;
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
import com.mopub.volley.DefaultRetryPolicy;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ParticleGenerator {
    private static final String f21737a = ParticleGenerator.class.getName();
    private final List<Particle> f21738b = new LinkedList();
    private final List<Particle> f21739c = new LinkedList();
    private final Point f21740d = new Point();
    private int f21741e;
    private long f21742f;
    private float f21743g;
    private float f21744h;
    private boolean f21745i = false;
    private double f21746j;
    private double f21747k;
    private int f21748l;
    private float f21749m;
    private final Bitmap f21750n;

    public class Particle {
        final /* synthetic */ ParticleGenerator f21720a;
        private final double f21721b;
        private final double f21722c;
        private final Paint f21723d = new Paint();
        private Interpolator f21724e;
        private final float[] f21725f = new float[3];
        private Map<Integer, ColorFilter> f21726g = new HashMap();
        private final Point f21727h = new Point();
        private final PointF f21728i = new PointF();
        private double f21729j = 2.0943951023931953d;
        private double f21730k = 4.1887902047863905d;
        private int f21731l;
        private long f21732m;
        private float f21733n;
        private int f21734o;
        private ColorFilter f21735p;
        private int f21736q;

        public boolean m23334a() {
            return this.f21731l == 0;
        }

        public boolean m23335b() {
            return this.f21731l == 1;
        }

        public Particle(ParticleGenerator particleGenerator, int i, int i2, int i3, double d, double d2, int i4, float f) {
            this.f21720a = particleGenerator;
            this.f21721b = ((double) particleGenerator.f21750n.getWidth()) / 2.0d;
            this.f21722c = ((double) particleGenerator.f21750n.getHeight()) / 2.0d;
            this.f21729j = d;
            this.f21730k = d2;
            this.f21733n = (float) i4;
            this.f21736q = 0;
            this.f21724e = new AccelerateInterpolator(f);
            m23332a(i, i2, i3);
        }

        public void m23332a(int i, int i2, int i3) {
            this.f21727h.x = i;
            this.f21727h.y = i2;
            this.f21731l = 0;
            this.f21732m = System.currentTimeMillis();
            this.f21736q = 0;
            double a = this.f21720a.m23336a(this.f21729j, this.f21730k);
            double a2 = this.f21720a.m23336a(75.0d, 300.0d);
            this.f21728i.x = (float) (Math.cos(a) * a2);
            this.f21728i.y = (float) (Math.sin(a) * a2);
            if (this.f21726g.containsKey(Integer.valueOf(i3))) {
                this.f21735p = (ColorFilter) this.f21726g.get(Integer.valueOf(i3));
            } else {
                Color.colorToHSV(i3, this.f21725f);
                this.f21725f[0] = (float) ((int) (this.f21725f[0] + (((float) this.f21720a.m23341b(0, 33)) - 16.5f)));
                this.f21725f[1] = (float) (((double) this.f21725f[1]) + (this.f21720a.m23336a(0.0d, 0.5d) - 0.25d));
                this.f21725f[2] = (float) (((double) this.f21725f[2]) + (this.f21720a.m23336a(0.0d, 0.4000000059604645d) - 0.20000000298023224d));
                this.f21734o = Color.HSVToColor(this.f21725f);
                this.f21735p = new LightingColorFilter(this.f21734o, 0);
                this.f21726g.put(Integer.valueOf(i3), this.f21735p);
            }
            this.f21723d.setColor(this.f21734o);
            this.f21723d.setAntiAlias(true);
            this.f21723d.setColorFilter(this.f21735p);
            this.f21720a.f21742f = this.f21732m;
        }

        public void m23331a(float f) {
            if (this.f21731l != 1) {
                Point point = this.f21727h;
                point.x = (int) (((float) point.x) + (this.f21728i.x * f));
                point = this.f21727h;
                point.y = (int) (((float) point.y) + (this.f21728i.y * f));
                this.f21736q = (int) (((float) this.f21736q) + (1000.0f * f));
                if (((float) this.f21736q) >= this.f21733n) {
                    this.f21731l = 1;
                }
                this.f21723d.setAlpha((int) Math.max((DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - this.f21724e.getInterpolation(((float) this.f21736q) / this.f21733n)) * 255.0f, 0.0f));
            }
        }

        public void m23333a(Canvas canvas) {
            canvas.drawBitmap(this.f21720a.f21750n, (float) ((int) (((double) this.f21727h.x) - this.f21721b)), (float) ((int) (((double) this.f21727h.y) - this.f21722c)), this.f21723d);
        }
    }

    public ParticleGenerator(Context context, int i, float f, float f2, double d, double d2, int i2, float f3) {
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), i);
        this.f21746j = d;
        this.f21747k = d2;
        this.f21748l = i2;
        this.f21749m = f3;
        this.f21750n = Bitmap.createScaledBitmap(decodeResource, (int) f, (int) f2, true);
        decodeResource.recycle();
    }

    public void m23345a(int i, int i2, int i3) {
        this.f21740d.x = i;
        this.f21740d.y = i2;
        this.f21741e = i3;
    }

    public synchronized void m23344a(int i, int i2) {
        for (Particle a : this.f21739c) {
            a.m23332a(0, 0, i);
        }
        int size = this.f21739c.size() - i2;
        if (size > 0) {
            Collection linkedList = new LinkedList();
            for (int i3 = 0; i3 < size; i3++) {
                linkedList.add(new Particle(this, 0, 0, i, this.f21746j, this.f21747k, this.f21748l, this.f21749m));
            }
            this.f21739c.addAll(linkedList);
        }
    }

    public void m23342a() {
        this.f21745i = true;
    }

    public synchronized void m23347b() {
        synchronized (this) {
            int size = this.f21738b.size();
            for (int i = 0; i < size; i++) {
                this.f21739c.add(this.f21738b.remove(0));
            }
        }
    }

    public synchronized void m23343a(float f) {
        long j;
        this.f21743g = f;
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f21745i) {
            j = 0;
            this.f21745i = false;
        } else {
            j = Math.max(this.f21742f > 0 ? currentTimeMillis - this.f21742f : 0, 0);
        }
        float f2 = ((float) j) / 1000.0f;
        this.f21744h += f * f2;
        int i = (int) this.f21744h;
        for (int i2 = 0; i2 < i; i2++) {
            Object obj;
            if (this.f21739c.isEmpty()) {
                Particle particle = new Particle(this, this.f21740d.x, this.f21740d.y, this.f21741e, this.f21746j, this.f21747k, this.f21748l, this.f21749m);
            } else {
                obj = (Particle) this.f21739c.remove(0);
                obj.m23332a(this.f21740d.x, this.f21740d.y, this.f21741e);
            }
            this.f21738b.add(obj);
        }
        this.f21744h -= (float) i;
        if (!this.f21738b.isEmpty()) {
            for (Particle particle2 : this.f21738b) {
                if (particle2.m23334a()) {
                    particle2.m23331a(f2);
                }
            }
            for (int size = this.f21738b.size() - 1; size >= 0; size--) {
                if (((Particle) this.f21738b.get(size)).m23335b()) {
                    this.f21739c.add(this.f21738b.remove(size));
                }
            }
        }
        this.f21742f = currentTimeMillis;
    }

    public synchronized void m23346a(Canvas canvas) {
        for (Particle a : this.f21738b) {
            a.m23333a(canvas);
        }
    }

    private int m23341b(int i, int i2) {
        return (int) (((double) i) + (Math.random() * ((double) ((i2 - i) + 1))));
    }

    private double m23336a(double d, double d2) {
        return ((d2 - d) * Math.random()) + d;
    }
}
