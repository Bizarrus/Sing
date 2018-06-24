package com.smule.singandroid.video;

import android.opengl.EGLContext;
import android.opengl.Matrix;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.smule.android.logging.Log;
import com.smule.singandroid.utils.MiscUtils.TimeStat;
import com.smule.singandroid.video.gles.EglCore;
import com.smule.singandroid.video.gles.Texture2dProgram;
import com.smule.singandroid.video.gles.WindowSurface;
import java.io.File;
import java.lang.ref.WeakReference;

public class TextureMovieEncoder implements Runnable {
    private static final String f25403a = TextureMovieEncoder.class.getSimpleName();
    private WindowSurface f25404b;
    private EglCore f25405c;
    private Texture2dProgram f25406d;
    private int f25407e;
    private int f25408f;
    private VideoEncoderCore f25409g;
    private volatile EncoderHandler f25410h;
    private final Object f25411i = new Object();
    private boolean f25412j;
    private boolean f25413k;
    private boolean f25414l = false;
    private EncoderConfig f25415m;
    private float[] f25416n = new float[16];
    private int f25417o;
    private int f25418p;
    private final Stats f25419q = new Stats();
    private long f25420r;
    private long f25421s;
    private long f25422t;
    private boolean f25423u;
    private float f25424v;
    private boolean f25425w;

    public interface ErrorListener {
        void mo6990a(Exception exception);
    }

    public static class EncoderConfig {
        final File f25383a;
        final int f25384b;
        final int f25385c;
        final int f25386d;
        final int f25387e;
        final int f25388f;
        final int f25389g;
        final int f25390h;
        final boolean f25391i;
        final int f25392j;
        final EGLContext f25393k;
        final GetAudioTimeCallback f25394l = null;
        final ErrorListener f25395m;

        public EncoderConfig(File file, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, int i8, EGLContext eGLContext, GetAudioTimeCallback getAudioTimeCallback, ErrorListener errorListener) {
            this.f25383a = file;
            this.f25384b = i;
            this.f25385c = i2;
            this.f25386d = i3;
            this.f25387e = i4;
            this.f25388f = i5;
            this.f25389g = i6;
            this.f25390h = i7;
            this.f25391i = z;
            this.f25392j = i8;
            this.f25393k = eGLContext;
            this.f25395m = errorListener;
            Log.b(TextureMovieEncoder.f25403a, "onCreate: filename:" + file + " resolution:" + i + " bitrate:" + this.f25389g + " displayRotation:" + i8);
        }

        public String toString() {
            return "EncoderConfig: " + this.f25384b + "x" + this.f25385c + " @" + this.f25389g + "(" + this.f25386d + "x" + this.f25387e + ") to '" + this.f25383a.toString() + "' orientation:" + this.f25388f + " frameRate:" + this.f25390h + " mirror:" + this.f25391i + " displayRotation:" + this.f25392j + " ctxt=" + this.f25393k;
        }
    }

    private static class EncoderHandler extends Handler {
        private WeakReference<TextureMovieEncoder> f25396a;

        public EncoderHandler(TextureMovieEncoder textureMovieEncoder) {
            this.f25396a = new WeakReference(textureMovieEncoder);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            Object obj = message.obj;
            TextureMovieEncoder textureMovieEncoder = (TextureMovieEncoder) this.f25396a.get();
            if (textureMovieEncoder == null) {
                Log.d(TextureMovieEncoder.f25403a, "EncoderHandler.handleMessage: encoder is null");
                return;
            }
            switch (i) {
                case 0:
                    textureMovieEncoder.m26543b((EncoderConfig) obj);
                    return;
                case 1:
                    textureMovieEncoder.m26547g();
                    return;
                case 2:
                    textureMovieEncoder.m26532a((((long) message.arg1) << 32) | (((long) message.arg2) & 4294967295L));
                    return;
                case 3:
                    textureMovieEncoder.m26540b(message.arg1);
                    return;
                case 4:
                    textureMovieEncoder.m26542b((EGLContext) message.obj);
                    return;
                case 5:
                    Looper.myLooper().quit();
                    return;
                case 6:
                    textureMovieEncoder.m26550j();
                    return;
                case 7:
                    textureMovieEncoder.m26541b(message.arg1, message.arg2);
                    return;
                case 8:
                    textureMovieEncoder.m26531a(((Float) message.obj).floatValue());
                    return;
                default:
                    throw new RuntimeException("Unhandled msg what=" + i);
            }
        }
    }

    public static class Stats {
        public Frame f25401a;
        public TimeStat f25402b;

        public static class Frame {
            public int f25397a;
            public int f25398b;
            public int f25399c;
            public int f25400d;

            public Frame() {
                m26527a();
            }

            public Frame(Frame frame) {
                this.f25397a = frame.f25397a;
                this.f25398b = frame.f25398b;
                this.f25399c = frame.f25399c;
                this.f25400d = frame.f25400d;
            }

            public void m26527a() {
                this.f25397a = 0;
                this.f25398b = 0;
                this.f25399c = 0;
                this.f25400d = 0;
            }

            public void m26528a(String str) {
                Log.b(str, "    mSentToEncoder:" + this.f25397a);
                Log.b(str, "    mSentToMuxer:" + this.f25398b);
                Log.b(str, "    mSkippedForFps:" + this.f25399c);
                Log.b(str, "    mSkippedForPause:" + this.f25400d);
            }
        }

        public Stats() {
            this.f25401a = new Frame();
            this.f25402b = new TimeStat();
        }

        public Stats(Stats stats) {
            this.f25401a = new Frame(stats.f25401a);
            this.f25402b = new TimeStat(stats.f25402b);
        }

        public void m26529a() {
            this.f25401a.m26527a();
            this.f25402b.m25875a();
        }

        public void m26530a(String str) {
            Log.b(str, "frame:");
            this.f25401a.m26528a(str);
            Log.b(str, "duration:");
            this.f25402b.m25876a(str);
        }
    }

    public final Stats m26551a() {
        if (this.f25409g != null) {
            this.f25419q.f25401a.f25398b = this.f25409g.m26564b();
        }
        if (this.f25419q.f25402b.f24778a != 0) {
            TimeStat timeStat = this.f25419q.f25402b;
            timeStat.f24780c += System.currentTimeMillis() - this.f25419q.f25402b.f24778a;
            this.f25419q.f25402b.f24778a = 0;
        }
        return this.f25419q;
    }

    public void m26556a(EncoderConfig encoderConfig) {
        Log.b(f25403a, "Encoder: startRecording()");
        synchronized (this.f25411i) {
            if (this.f25413k) {
                Log.d(f25403a, "Encoder thread already running");
                return;
            }
            this.f25413k = true;
            this.f25408f = 0;
            new Thread(this, "TextureMovieEncoder").start();
            while (!this.f25412j) {
                try {
                    this.f25411i.wait();
                } catch (InterruptedException e) {
                }
            }
            this.f25410h.sendMessage(this.f25410h.obtainMessage(0, encoderConfig));
        }
    }

    public void m26558b() {
        synchronized (this.f25411i) {
            if (this.f25410h != null) {
                this.f25410h.sendMessage(this.f25410h.obtainMessage(1));
                this.f25410h.sendMessage(this.f25410h.obtainMessage(5));
            }
        }
    }

    public void m26559c() {
        m26558b();
        synchronized (this.f25411i) {
            while (this.f25412j) {
                try {
                    this.f25411i.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public boolean m26560d() {
        boolean z;
        synchronized (this.f25411i) {
            z = this.f25413k;
        }
        return z;
    }

    public void m26555a(EGLContext eGLContext) {
        this.f25410h.sendMessage(this.f25410h.obtainMessage(4, eGLContext));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m26554a(android.graphics.SurfaceTexture r9) {
        /*
        r8 = this;
        r1 = r8.f25411i;
        monitor-enter(r1);
        r0 = r8.f25412j;	 Catch:{ all -> 0x001c }
        if (r0 != 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
    L_0x0008:
        return;
    L_0x0009:
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
        r0 = r9.getTimestamp();
        r2 = 0;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 != 0) goto L_0x001f;
    L_0x0014:
        r0 = f25403a;
        r1 = "SurfaceTexture with timestamp of zero";
        com.smule.android.logging.Log.b(r0, r1);
        goto L_0x0008;
    L_0x001c:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
        throw r0;
    L_0x001f:
        r2 = r8.f25410h;
        if (r2 == 0) goto L_0x0008;
    L_0x0023:
        r2 = r8.f25410h;
        r3 = r8.f25410h;
        r4 = 2;
        r5 = 32;
        r6 = r0 >> r5;
        r5 = (int) r6;
        r0 = (int) r0;
        r1 = 0;
        r0 = r3.obtainMessage(r4, r5, r0, r1);
        r2.sendMessage(r0);
        goto L_0x0008;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.smule.singandroid.video.TextureMovieEncoder.a(android.graphics.SurfaceTexture):void");
    }

    public void m26552a(int i) {
        synchronized (this.f25411i) {
            if (this.f25412j) {
                this.f25410h.sendMessage(this.f25410h.obtainMessage(3, i, 0, null));
                return;
            }
        }
    }

    public void m26561e() {
        synchronized (this.f25411i) {
            if (this.f25412j) {
                this.f25410h.sendMessage(this.f25410h.obtainMessage(6));
                return;
            }
        }
    }

    public void m26557a(Float f) {
        synchronized (this.f25411i) {
            if (this.f25412j) {
                this.f25410h.sendMessage(this.f25410h.obtainMessage(8, 0, 0, f));
                return;
            }
        }
    }

    public void m26553a(int i, int i2) {
        synchronized (this.f25411i) {
            if (this.f25412j) {
                this.f25410h.sendMessage(this.f25410h.obtainMessage(7, i, i2));
                return;
            }
        }
    }

    public void run() {
        Looper.prepare();
        synchronized (this.f25411i) {
            this.f25410h = new EncoderHandler(this);
            this.f25412j = true;
            this.f25411i.notify();
        }
        try {
            Looper.loop();
        } catch (Exception e) {
            if (!(this.f25415m == null || this.f25415m.f25395m == null)) {
                this.f25415m.f25395m.mo6990a(e);
            }
            try {
                m26547g();
            } catch (Exception e2) {
                Log.e(f25403a, "exception cleaning up:" + e2);
            }
        }
        this.f25415m = null;
        Log.b(f25403a, "Encoder thread exiting");
        synchronized (this.f25411i) {
            this.f25413k = false;
            this.f25412j = false;
            this.f25410h = null;
            this.f25411i.notify();
        }
    }

    private void m26543b(EncoderConfig encoderConfig) {
        Log.b(f25403a, "handleStartRecording:" + encoderConfig);
        this.f25419q.m26529a();
        this.f25414l = true;
        m26545c(encoderConfig);
    }

    private void m26532a(long j) {
        if (this.f25408f == 0) {
            this.f25421s = j;
            this.f25420r = j;
            this.f25422t = j;
        }
        long j2 = j - this.f25421s;
        if (j2 >= 0) {
            this.f25421s = j;
        }
        if (j2 >= 0) {
            if (this.f25423u) {
                this.f25423u = false;
                j2 = this.f25422t + ((long) this.f25424v);
                if (this.f25420r < j2) {
                    this.f25420r = j2;
                } else {
                    Log.c(f25403a, "unpause:new time is in the past:" + j2);
                }
                Log.b(f25403a, "unpause:mEncodeTime:" + this.f25420r);
                j2 = 0;
            } else if (this.f25408f == 10 && j - this.f25422t < 2000000) {
                this.f25425w = true;
                this.f25420r = ((this.f25420r - this.f25422t) * 1000) + this.f25422t;
            }
        }
        if (this.f25414l) {
            Frame frame = this.f25419q.f25401a;
            frame.f25400d++;
            return;
        }
        if (this.f25408f == 0) {
            Log.b(f25403a, "mFirstFrameTime:" + this.f25422t);
        }
        if (this.f25419q.f25402b.f24778a == 0) {
            this.f25419q.f25402b.f24778a = System.currentTimeMillis();
        }
        this.f25409g.m26563a(false);
        this.f25408f++;
        this.f25406d.mo7015a(this.f25416n, this.f25407e);
        if (j2 >= 0) {
            if (this.f25425w) {
                this.f25420r = (j2 * 1000) + this.f25420r;
            } else {
                this.f25420r = j2 + this.f25420r;
            }
            this.f25404b.m26606a(this.f25420r);
            this.f25404b.m26609c();
            frame = this.f25419q.f25401a;
            frame.f25397a++;
            return;
        }
        Log.e(f25403a, "ts:" + j + " in the past.  Dropping frame");
    }

    private void m26547g() {
        Log.b(f25403a, "handleStopRecording");
        try {
            if (this.f25409g != null) {
                this.f25419q.f25401a.f25398b = this.f25409g.m26564b();
                this.f25409g.m26563a(true);
            }
        } catch (Exception e) {
            Log.e(f25403a, "handleStopRecording:drainEncoder exception:" + e);
        }
        try {
            m26549i();
        } catch (Exception e2) {
            Log.e(f25403a, "handleStopRecording:releaseEncoder exception:" + e2);
        }
    }

    private void m26540b(int i) {
        this.f25407e = i;
    }

    private void m26542b(EGLContext eGLContext) {
        Log.b(f25403a, "handleUpdatedSharedContext " + eGLContext);
        this.f25404b.m26605a();
        this.f25406d.mo7016c();
        this.f25405c.m26599a();
        this.f25405c = new EglCore(eGLContext, 1);
        this.f25404b.m26615a(this.f25405c);
        this.f25404b.m26608b();
        this.f25406d = new Texture2dProgram();
    }

    private void m26545c(EncoderConfig encoderConfig) {
        this.f25415m = encoderConfig;
        try {
            this.f25409g = new VideoEncoderCore(this.f25415m.f25384b, this.f25415m.f25385c, this.f25415m.f25389g, this.f25415m.f25390h, this.f25415m.f25383a);
            this.f25405c = new EglCore(this.f25415m.f25393k, 1);
            this.f25404b = new WindowSurface(this.f25405c, this.f25409g.m26562a(), true);
            this.f25404b.m26608b();
            this.f25406d = new Texture2dProgram();
            this.f25418p = 0;
            this.f25417o = 0;
            m26548h();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private void m26548h() {
        CameraUtils.m26207a(this.f25416n, this.f25415m.f25391i, this.f25415m.f25388f, this.f25415m.f25392j, this.f25415m.f25386d, this.f25415m.f25387e, this.f25415m.f25384b, this.f25415m.f25385c);
        if (this.f25417o != 0 && this.f25418p != 0) {
            Matrix.translateM(this.f25416n, 0, 0.0f, ((float) this.f25417o) / ((float) this.f25418p), 0.0f);
        }
    }

    private void m26549i() {
        if (this.f25409g != null) {
            this.f25419q.f25401a.f25398b = this.f25409g.m26564b();
            this.f25409g.m26565c();
            this.f25409g = null;
        }
        if (this.f25404b != null) {
            this.f25404b.m26616d();
            this.f25404b = null;
        }
        if (this.f25406d != null) {
            this.f25406d.mo7016c();
            this.f25406d = null;
        }
        if (this.f25405c != null) {
            this.f25405c.m26599a();
            this.f25405c = null;
        }
    }

    private void m26550j() {
        Log.b(f25403a, "handlePause");
        this.f25414l = true;
        if (this.f25419q.f25402b.f24778a != 0) {
            TimeStat timeStat = this.f25419q.f25402b;
            timeStat.f24780c += System.currentTimeMillis() - this.f25419q.f25402b.f24778a;
            this.f25419q.f25402b.f24778a = 0;
        }
        if (this.f25409g != null) {
            this.f25419q.f25401a.f25398b = this.f25409g.m26564b();
        }
    }

    private void m26531a(float f) {
        this.f25414l = false;
        this.f25423u = true;
        this.f25424v = 1.0E9f * f;
    }

    private void m26541b(int i, int i2) {
        this.f25417o = i;
        this.f25418p = i2;
        m26548h();
    }
}
