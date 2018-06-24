package com.smule.singandroid.customviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.logging.Log;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.models.Pitch;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PitchView extends View {
    public static final String f21757a = PitchView.class.getName();
    private double f21758A;
    private final RectF f21759B;
    private final Paint f21760C;
    private final Rect f21761D;
    private final RenderBuffer[] f21762E;
    private ParticleGenerator f21763F;
    private float f21764G;
    private final AccelerateInterpolator f21765H;
    private int f21766I;
    private int f21767J;
    private int f21768K;
    private int[] f21769b;
    private int f21770c;
    private int f21771d;
    private int f21772e;
    private final List<Pitch> f21773f;
    private int f21774g;
    private int f21775h;
    private int f21776i;
    private int f21777j;
    private float f21778k;
    private float f21779l;
    private float f21780m;
    private float f21781n;
    private float f21782o;
    private int f21783p;
    private float f21784q;
    private int f21785r;
    private float f21786s;
    private float f21787t;
    private Bitmap f21788u;
    private int f21789v;
    private float f21790w;
    private PointF f21791x;
    private double f21792y;
    private double f21793z;

    private class RenderBuffer {
        public final Bitmap f21751a;
        public final Canvas f21752b;
        public double f21753c;
        public double f21754d;
        public int f21755e = 0;
        final /* synthetic */ PitchView f21756f;

        public RenderBuffer(PitchView pitchView, int i, int i2) {
            this.f21756f = pitchView;
            this.f21751a = Bitmap.createBitmap(i, i2, Config.ARGB_4444);
            this.f21752b = new Canvas(this.f21751a);
            this.f21753c = -1.0d;
            this.f21754d = -1.0d;
        }

        public void m23348a() {
            this.f21754d = -1.0d;
            this.f21753c = -1.0d;
            this.f21755e = 0;
        }

        public void m23350b() {
            if (!this.f21751a.isRecycled()) {
                this.f21751a.recycle();
            }
        }

        public void m23349a(double d, double d2) {
            Log.b(PitchView.f21757a, "Rendering pitches from " + d + "s to " + d2 + "s");
            this.f21752b.drawColor(0, Mode.CLEAR);
            this.f21753c = d;
            this.f21754d = d2;
            this.f21756f.f21760C.setStrokeWidth(this.f21756f.f21782o);
            int i = this.f21755e;
            while (i < this.f21756f.f21773f.size()) {
                Pitch pitch = (Pitch) this.f21756f.f21773f.get(i);
                double d3 = pitch.f23498b + this.f21756f.f21792y;
                double d4 = pitch.f23499c + this.f21756f.f21792y;
                if (d3 <= this.f21756f.f21792y + d2) {
                    if (d4 > d) {
                        float e = (float) ((d3 - d) * ((double) this.f21756f.f21779l));
                        float e2 = ((float) ((d4 - d3) * ((double) this.f21756f.f21779l))) - this.f21756f.f21782o;
                        this.f21756f.f21759B.top = this.f21756f.m23351a((float) pitch.f23497a) - this.f21756f.f21787t;
                        this.f21756f.f21759B.bottom = this.f21756f.f21759B.top + (this.f21756f.f21787t * 2.0f);
                        this.f21756f.f21759B.left = e;
                        this.f21756f.f21759B.right = e2 + e;
                        this.f21756f.f21760C.setColor(this.f21756f.f21769b[pitch.f23500d]);
                        this.f21756f.f21760C.setStyle(Style.FILL);
                        this.f21752b.drawRect(this.f21756f.f21759B, this.f21756f.f21760C);
                    } else {
                        this.f21755e = i + 1;
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public PitchView(Context context) {
        super(context);
        this.f21769b = new int[4];
        this.f21773f = new LinkedList();
        this.f21785r = 0;
        this.f21786s = -1.0f;
        this.f21791x = new PointF();
        this.f21759B = new RectF();
        this.f21760C = new Paint();
        this.f21761D = new Rect();
        this.f21762E = new RenderBuffer[2];
        this.f21765H = new AccelerateInterpolator();
        this.f21768K = getResources().getColor(C1947R.color.lyrics_other_part);
        m23362d();
        if (isInEditMode()) {
            m23364e();
        }
    }

    public PitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f21769b = new int[4];
        this.f21773f = new LinkedList();
        this.f21785r = 0;
        this.f21786s = -1.0f;
        this.f21791x = new PointF();
        this.f21759B = new RectF();
        this.f21760C = new Paint();
        this.f21761D = new Rect();
        this.f21762E = new RenderBuffer[2];
        this.f21765H = new AccelerateInterpolator();
        this.f21768K = getResources().getColor(C1947R.color.lyrics_other_part);
        m23362d();
        if (isInEditMode()) {
            m23364e();
        }
    }

    public PitchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f21769b = new int[4];
        this.f21773f = new LinkedList();
        this.f21785r = 0;
        this.f21786s = -1.0f;
        this.f21791x = new PointF();
        this.f21759B = new RectF();
        this.f21760C = new Paint();
        this.f21761D = new Rect();
        this.f21762E = new RenderBuffer[2];
        this.f21765H = new AccelerateInterpolator();
        this.f21768K = getResources().getColor(C1947R.color.lyrics_other_part);
        m23362d();
        if (isInEditMode()) {
            m23364e();
        }
    }

    public void m23368a() {
        if (this.f21763F != null) {
            this.f21763F.m23342a();
        }
    }

    public boolean m23371b() {
        return (this.f21773f == null || this.f21773f.isEmpty()) ? false : true;
    }

    public void m23370a(int i, List<Pitch> list) {
        this.f21773f.clear();
        this.f21773f.addAll(list);
        this.f21783p = 0;
        this.f21774g = -1;
        this.f21775h = -1;
        for (Pitch pitch : list) {
            if (this.f21774g == -1 || pitch.f23497a < this.f21774g) {
                this.f21774g = pitch.f23497a;
            }
            if (this.f21775h == -1 || pitch.f23497a > this.f21775h) {
                this.f21775h = pitch.f23497a;
            }
        }
        this.f21774g--;
        this.f21775h++;
        this.f21766I = i;
        if (i == 1) {
            this.f21769b[1] = getResources().getColor(C1947R.color.lyrics_my_part);
            this.f21769b[2] = getResources().getColor(C1947R.color.lyrics_other_part);
        } else if (i == 2) {
            this.f21769b[1] = getResources().getColor(C1947R.color.lyrics_other_part);
            this.f21769b[2] = getResources().getColor(C1947R.color.lyrics_my_part);
        }
    }

    public void setAudioLatency(int i) {
        this.f21792y = (((double) i) / 1000.0d) * 0.33d;
        Log.b(f21757a, "Audio latency set to: " + this.f21792y + "s");
    }

    public void m23372c() {
        if (this.f21773f != null && !this.f21773f.isEmpty()) {
            this.f21763F.m23347b();
            this.f21783p = 0;
            m23357a(0.0d);
            invalidate();
        }
    }

    public int getScore() {
        return this.f21767J;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        int i5 = 0;
        super.onSizeChanged(i, i2, i3, i4);
        if (((float) (this.f21775h - this.f21774g)) >= DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
            if (i != this.f21776i || i2 != this.f21777j) {
                this.f21776i = i;
                this.f21777j = i2;
                this.f21761D.top = getPaddingTop();
                this.f21761D.bottom = i2 - getPaddingBottom();
                this.f21761D.left = 0;
                this.f21761D.right = i;
                this.f21778k = (float) (this.f21761D.height() / (this.f21775h - this.f21774g));
                this.f21779l = ((float) i) / 4.0f;
                this.f21790w = 1.5f * this.f21779l;
                synchronized (this.f21762E) {
                    while (i5 < this.f21762E.length) {
                        if (this.f21762E[i5] != null) {
                            this.f21762E[i5].m23350b();
                        }
                        this.f21762E[i5] = new RenderBuffer(this, i, i2);
                        i5++;
                    }
                }
                m23359b(this.f21793z);
            }
        }
    }

    public void m23369a(double d, float f, float f2) {
        this.f21793z = d;
        double d2 = this.f21758A > 0.0d ? this.f21793z - this.f21758A : 0.0d;
        m23359b(d);
        int i = !this.f21773f.isEmpty() ? ((Pitch) this.f21773f.get(0)).f23500d : -1;
        Object obj = null;
        int i2 = -1;
        int i3 = i;
        for (int i4 = this.f21783p; i4 < this.f21773f.size(); i4++) {
            Pitch pitch = (Pitch) this.f21773f.get(i4);
            double d3 = pitch.f23498b + this.f21792y;
            double d4 = pitch.f23499c + this.f21792y;
            if (d >= d3 && (r10 == -1 || d <= d4)) {
                i2 = pitch.f23497a;
                i3 = pitch.f23500d;
                Object obj2 = (d < d3 || d > d4) ? null : 1;
                obj = obj2;
            }
            if (d3 > d) {
                i = i3;
                break;
            }
            if (d4 < d) {
                this.f21783p = i4 + 1;
            }
        }
        i = i3;
        if (i == -1) {
            i = this.f21785r;
        }
        this.f21785r = i;
        if (f2 > 0.001f) {
            this.f21784q = m23352a(f, (float) i2);
        }
        float max = Math.max(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - (Math.abs(this.f21784q - ((float) i2)) / 6.0f), 0.0f);
        if (obj == null || ((double) max) < 0.5d || this.f21769b[this.f21785r] == this.f21768K) {
            this.f21763F.m23343a(0.0f);
        } else {
            this.f21763F.m23343a(this.f21765H.getInterpolation(max) * this.f21764G);
            double d5 = (double) this.f21767J;
            double d6 = (this.f21785r == this.f21766I || this.f21785r == 3) ? 86.0d * d2 : 0.0d;
            this.f21767J = (int) (d6 + d5);
        }
        max = ((float) this.f21789v) / 2.0f;
        this.f21791x.x = this.f21790w - max;
        max = Math.min(Math.max(m23351a(this.f21784q) - max, ((float) this.f21761D.top) - max), ((float) this.f21761D.bottom) - max);
        if (this.f21791x.y > 0.0f) {
            max -= this.f21791x.y;
            PointF pointF = this.f21791x;
            pointF.y = (max * 0.5f) + pointF.y;
        } else {
            this.f21791x.y = max;
        }
        this.f21763F.m23345a((int) (1.5f * this.f21779l), (int) (m23351a((float) i2) + this.f21787t), this.f21769b[this.f21785r]);
        this.f21758A = this.f21793z;
    }

    public void onDraw(Canvas canvas) {
        Object obj;
        super.onDraw(canvas);
        double d = this.f21793z - 1.5d;
        this.f21760C.setStyle(Style.STROKE);
        this.f21760C.setColor(this.f21772e);
        this.f21760C.setStrokeWidth(this.f21781n);
        int height = getHeight() / 4;
        for (int i = 0; i < 5; i++) {
            int i2 = height * i;
            canvas.drawLine(0.0f, (float) i2, (float) getWidth(), (float) i2, this.f21760C);
        }
        if (this.f21775h - this.f21774g > 0) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            for (RenderBuffer renderBuffer : this.f21762E) {
                canvas.drawBitmap(renderBuffer.f21751a, (float) ((renderBuffer.f21753c - d) * ((double) this.f21779l)), 0.0f, null);
            }
        }
        this.f21760C.setStrokeWidth(this.f21780m);
        this.f21760C.setColor(this.f21771d);
        canvas.drawLine(this.f21790w, 0.0f, this.f21790w, (float) this.f21777j, this.f21760C);
        if (obj != null) {
            this.f21763F.m23346a(canvas);
            this.f21760C.setColor(this.f21770c);
            canvas.drawBitmap(this.f21788u, this.f21791x.x, this.f21791x.y, this.f21760C);
        }
    }

    private void m23362d() {
        int i = 0;
        this.f21769b[0] = getResources().getColor(C1947R.color.lyrics_my_part);
        this.f21769b[1] = getResources().getColor(C1947R.color.lyrics_my_part);
        this.f21769b[2] = getResources().getColor(C1947R.color.lyrics_other_part);
        this.f21769b[3] = getResources().getColor(C1947R.color.lyrics_together);
        this.f21771d = getResources().getColor(C1947R.color.pitch_event_horizon);
        this.f21772e = getResources().getColor(C1947R.color.pitch_static_lines);
        this.f21787t = getResources().getDimension(C1947R.dimen.singing_pitch_line) * 0.5f;
        this.f21780m = getResources().getDimension(C1947R.dimen.singing_pitch_event_horizon_stroke);
        this.f21781n = getResources().getDimension(C1947R.dimen.singing_pitch_score_line_stroke);
        this.f21782o = getResources().getDimension(C1947R.dimen.singing_pitch_line_border);
        this.f21770c = getResources().getColor(C1947R.color.pitch_arrow);
        this.f21789v = (int) getResources().getDimension(C1947R.dimen.singing_pitch_arrow);
        this.f21788u = Bitmap.createBitmap(this.f21789v, this.f21789v, Config.ARGB_8888);
        Canvas canvas = new Canvas(this.f21788u);
        Path a = m23356a(new Point(0, 0), this.f21789v);
        this.f21760C.setColor(this.f21770c);
        this.f21760C.setStyle(Style.FILL);
        this.f21760C.setAntiAlias(true);
        canvas.drawPath(a, this.f21760C);
        float dimension = getResources().getDimension(C1947R.dimen.singing_star_size);
        this.f21763F = new ParticleGenerator(getContext(), C1947R.drawable.star_burst, dimension, dimension, 2.0943951023931953d, 4.1887902047863905d, 1000, 2.0f);
        this.f21764G = (float) getResources().getInteger(C1947R.integer.singing_max_stars_per_second);
        int i2 = (int) ((((double) this.f21764G) * 1.5d) + 0.5d);
        int[] iArr = this.f21769b;
        int length = iArr.length;
        while (i < length) {
            this.f21763F.m23344a(iArr[i], i2);
            i++;
        }
    }

    private void m23364e() {
        double d = -0.5d;
        List arrayList = new ArrayList();
        for (int i = 0; i < 12; i++) {
            if (i % 2 == 0) {
                arrayList.add(new Pitch(i, 0, d, 0.2d + d));
            } else {
                arrayList.add(new Pitch(i, 0, d, 0.1d + d));
                arrayList.add(new Pitch(i, 0, 0.1d + d, 0.2d + d));
            }
            d += 0.2d;
        }
        m23370a(0, arrayList);
        m23369a(0.0d, 0.0f, 0.0f);
        this.f21784q = 4.0f;
    }

    private Path m23356a(Point point, int i) {
        Point point2 = new Point(point.x, point.y + i);
        Point point3 = new Point(point.x + i, point.y + (i / 2));
        Path path = new Path();
        path.moveTo((float) point.x, (float) point.y);
        path.lineTo((float) point2.x, (float) point2.y);
        path.lineTo((float) point3.x, (float) point3.y);
        return path;
    }

    private float m23352a(float f, float f2) {
        float f3 = f <= 0.0f ? this.f21786s > 0.0f ? this.f21786s : (((float) (this.f21775h - this.f21774g)) / 2.0f) + ((float) this.f21774g) : f;
        if (f2 <= 0.0f) {
            f3 = m23353a(f3, (float) this.f21774g, (float) this.f21775h);
        } else {
            f3 = m23353a(f3, f2 - 6.0f, f2 + 6.0f);
        }
        if (Math.abs(f3 - f2) >= DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
            f2 = f3;
        }
        this.f21786s = f2;
        return f2;
    }

    private float m23353a(float f, float f2, float f3) {
        int i = 0;
        if (f > f3) {
            while (f > f3 && i < 5) {
                f = (float) (((double) f) - 12.0d);
                i++;
            }
        } else if (f < f2) {
            while (f < f2 && i < 5) {
                f = (float) (((double) f) + 12.0d);
                i++;
            }
        }
        return f;
    }

    private float m23351a(float f) {
        return ((float) this.f21761D.bottom) - (this.f21778k * (f - ((float) this.f21774g)));
    }

    private void m23357a(double d) {
        for (RenderBuffer renderBuffer : this.f21762E) {
            if (renderBuffer == null) {
                break;
            }
            renderBuffer.m23348a();
        }
        m23359b(d);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m23359b(double r12) {
        /*
        r11 = this;
        r8 = 4616189618054758400; // 0x4010000000000000 float:0.0 double:4.0;
        r1 = r11.f21762E;
        monitor-enter(r1);
        r0 = r11.f21762E;	 Catch:{ all -> 0x004b }
        r2 = 0;
        r0 = r0[r2];	 Catch:{ all -> 0x004b }
        if (r0 == 0) goto L_0x0013;
    L_0x000c:
        r0 = r11.f21762E;	 Catch:{ all -> 0x004b }
        r2 = 1;
        r0 = r0[r2];	 Catch:{ all -> 0x004b }
        if (r0 != 0) goto L_0x0015;
    L_0x0013:
        monitor-exit(r1);	 Catch:{ all -> 0x004b }
    L_0x0014:
        return;
    L_0x0015:
        r2 = 4609434218613702656; // 0x3ff8000000000000 float:0.0 double:1.5;
        r2 = r12 - r2;
        r0 = r11.f21762E;	 Catch:{ all -> 0x004b }
        r4 = 0;
        r0 = r0[r4];	 Catch:{ all -> 0x004b }
        r4 = r0.f21753c;	 Catch:{ all -> 0x004b }
        r6 = -4616189618054758400; // 0xbff0000000000000 float:0.0 double:-1.0;
        r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r0 == 0) goto L_0x0031;
    L_0x0026:
        r0 = r11.f21762E;	 Catch:{ all -> 0x004b }
        r4 = 0;
        r0 = r0[r4];	 Catch:{ all -> 0x004b }
        r4 = r0.f21753c;	 Catch:{ all -> 0x004b }
        r0 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1));
        if (r0 >= 0) goto L_0x004e;
    L_0x0031:
        r0 = r11.f21762E;	 Catch:{ all -> 0x004b }
        r2 = 0;
        r0 = r0[r2];	 Catch:{ all -> 0x004b }
        r2 = 0;
        r4 = 4616189618054758400; // 0x4010000000000000 float:0.0 double:4.0;
        r0.m23349a(r2, r4);	 Catch:{ all -> 0x004b }
        r0 = r11.f21762E;	 Catch:{ all -> 0x004b }
        r2 = 1;
        r0 = r0[r2];	 Catch:{ all -> 0x004b }
        r2 = 4616189618054758400; // 0x4010000000000000 float:0.0 double:4.0;
        r4 = 4620693217682128896; // 0x4020000000000000 float:0.0 double:8.0;
        r0.m23349a(r2, r4);	 Catch:{ all -> 0x004b }
    L_0x0049:
        monitor-exit(r1);	 Catch:{ all -> 0x004b }
        goto L_0x0014;
    L_0x004b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x004b }
        throw r0;
    L_0x004e:
        r0 = r11.f21762E;	 Catch:{ all -> 0x004b }
        r4 = 0;
        r0 = r0[r4];	 Catch:{ all -> 0x004b }
        r4 = r0.f21754d;	 Catch:{ all -> 0x004b }
        r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r0 >= 0) goto L_0x0049;
    L_0x0059:
        r0 = r11.f21762E;	 Catch:{ all -> 0x004b }
        r2 = 0;
        r0 = r0[r2];	 Catch:{ all -> 0x004b }
        r2 = r11.f21762E;	 Catch:{ all -> 0x004b }
        r3 = 0;
        r4 = r11.f21762E;	 Catch:{ all -> 0x004b }
        r5 = 1;
        r4 = r4[r5];	 Catch:{ all -> 0x004b }
        r2[r3] = r4;	 Catch:{ all -> 0x004b }
        r2 = r11.f21762E;	 Catch:{ all -> 0x004b }
        r3 = 1;
        r2[r3] = r0;	 Catch:{ all -> 0x004b }
        r0 = r11.f21762E;	 Catch:{ all -> 0x004b }
        r2 = 1;
        r0 = r0[r2];	 Catch:{ all -> 0x004b }
        r2 = r11.f21762E;	 Catch:{ all -> 0x004b }
        r3 = 0;
        r2 = r2[r3];	 Catch:{ all -> 0x004b }
        r2 = r2.f21754d;	 Catch:{ all -> 0x004b }
        r4 = r11.f21762E;	 Catch:{ all -> 0x004b }
        r5 = 0;
        r4 = r4[r5];	 Catch:{ all -> 0x004b }
        r4 = r4.f21754d;	 Catch:{ all -> 0x004b }
        r4 = r4 + r8;
        r0.m23349a(r2, r4);	 Catch:{ all -> 0x004b }
        goto L_0x0049;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.smule.singandroid.customviews.PitchView.b(double):void");
    }
}
