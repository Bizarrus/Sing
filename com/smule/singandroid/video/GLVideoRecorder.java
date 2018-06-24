package com.smule.singandroid.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.EGL14;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Handler;
import android.os.Message;
import com.google.android.gms.gcm.Task;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.logging.Log;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils.TimeStat;
import com.smule.singandroid.video.CameraUtils.Config;
import com.smule.singandroid.video.TextureMovieEncoder.EncoderConfig;
import com.smule.singandroid.video.TextureMovieEncoder.ErrorListener;
import com.smule.singandroid.video.VideoFilterDatabase.FilterType;
import com.smule.singandroid.video.gles.GlUtil;
import com.smule.singandroid.video.gles.Texture2dProgram;
import java.io.File;
import java.lang.ref.WeakReference;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import jp.co.cyberagent.android.gpuimage.GPUImageExternalTexture;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFrameBuffer;
import jp.co.cyberagent.android.gpuimage.GPUImageFrameBufferCache;
import jp.co.cyberagent.android.gpuimage.GPUImageSwitchableFilterChain;

@TargetApi(19)
public class GLVideoRecorder implements OnFrameAvailableListener {
    private static final String f25293a = GLVideoRecorder.class.getSimpleName();
    private static TextureMovieEncoder f25294g = new TextureMovieEncoder();
    private RecordDelegate f25295b;
    private GLSurfaceView f25296c;
    private MainHandler f25297d;
    private RendererInterface f25298e;
    private String f25299f;
    private GPUImageFilterGallery f25300h;
    private GPUImageSwitchableFilterChain f25301i;
    private FilterType[] f25302j;
    private boolean f25303k;
    private boolean f25304l;
    private Point f25305m;
    private int f25306n;
    private boolean f25307o = true;

    public interface RecordDelegate {
        void mo6634a(Exception exception);

        void ae();
    }

    class C50443 implements Runnable {
        final /* synthetic */ GLVideoRecorder f25256a;

        C50443(GLVideoRecorder gLVideoRecorder) {
            this.f25256a = gLVideoRecorder;
        }

        public void run() {
            this.f25256a.f25298e.mo6983a();
        }
    }

    class C50465 implements Runnable {
        final /* synthetic */ GLVideoRecorder f25259a;

        C50465(GLVideoRecorder gLVideoRecorder) {
            this.f25259a = gLVideoRecorder;
        }

        public void run() {
            this.f25259a.f25298e.mo6989c();
        }
    }

    private static class MainHandler extends Handler {
        private WeakReference<GLVideoRecorder> f25262a;

        public MainHandler(GLVideoRecorder gLVideoRecorder) {
            this.f25262a = new WeakReference(gLVideoRecorder);
        }

        public void m26378a() {
            this.f25262a.clear();
        }

        public void handleMessage(Message message) {
            GLVideoRecorder gLVideoRecorder = (GLVideoRecorder) this.f25262a.get();
            if (gLVideoRecorder == null) {
                Log.b(GLVideoRecorder.f25293a, "Got message for dead object");
                return;
            }
            switch (message.what) {
                case 1:
                    gLVideoRecorder.m26411a((SurfaceTexture) message.obj);
                    return;
                case 2:
                    gLVideoRecorder.m26414a((Exception) message.obj);
                    return;
                default:
                    throw new RuntimeException("Unknown message " + message.what);
            }
        }
    }

    private interface RendererInterface extends android.opengl.GLSurfaceView.Renderer {
        void mo6983a();

        void mo6984a(Config config);

        void mo6985a(boolean z);

        void mo6986a(boolean z, int i);

        Stats mo6987b();

        void mo6988b(boolean z);

        void mo6989c();
    }

    private static final class NoOpRenderer implements RendererInterface {
        private NoOpRenderer() {
        }

        public void mo6983a() {
        }

        public void mo6984a(Config config) {
        }

        public void mo6985a(boolean z) {
        }

        public void mo6986a(boolean z, int i) {
        }

        public void mo6988b(boolean z) {
        }

        public Stats mo6987b() {
            return new Stats();
        }

        public void mo6989c() {
        }

        public void onDrawFrame(GL10 gl10) {
        }

        public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        }

        public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        }
    }

    private static class Renderer implements RendererInterface, ErrorListener {
        static final float[] f25263a = new float[]{-1.0f, -1.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, -1.0f, -1.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT};
        private static final Stats f25264t = new Stats();
        GPUImageExternalTexture f25265b;
        GPUImageFilter f25266c;
        GLSurfaceView f25267d;
        private MainHandler f25268e;
        private GetAudioTimeCallback f25269f;
        private int f25270g;
        private int f25271h;
        private int f25272i;
        private int f25273j;
        private int f25274k;
        private boolean f25275l;
        private int f25276m;
        private int f25277n;
        private int f25278o;
        private SurfaceTexture f25279p = null;
        private float[] f25280q = new float[16];
        private final float[] f25281r = new float[16];
        private File f25282s = null;
        private int f25283u = -1;
        private int f25284v = 0;
        private Texture2dProgram f25285w;
        private int f25286x = 0;
        private boolean f25287y = false;

        public Renderer(MainHandler mainHandler, String str, int i, GetAudioTimeCallback getAudioTimeCallback, GPUImageFilter gPUImageFilter, GLSurfaceView gLSurfaceView) {
            this.f25267d = gLSurfaceView;
            this.f25268e = mainHandler;
            this.f25269f = getAudioTimeCallback;
            if (str != null) {
                this.f25282s = new File(str);
            }
            this.f25276m = i;
            this.f25265b = new GPUImageExternalTexture();
            this.f25266c = gPUImageFilter;
            this.f25265b.mo6993a(this.f25266c);
        }

        public void mo6983a() {
            Log.b(GLVideoRecorder.f25293a, "notifyPausing");
            if (this.f25279p != null) {
                this.f25279p.release();
                this.f25279p = null;
            }
            if (this.f25266c != null && this.f25266c.m26460n()) {
                this.f25266c.m26457k();
            }
            if (this.f25265b != null && this.f25265b.m26460n()) {
                this.f25265b.m26457k();
            }
            if (this.f25285w != null) {
                this.f25285w.mo7016c();
                this.f25285w = null;
            }
            m26397g();
        }

        public void mo6984a(Config config) {
            Log.b(GLVideoRecorder.f25293a, "setCameraConfig:" + config.toString());
            this.f25274k = config.f25108a;
            this.f25275l = config.f25111d;
            this.f25270g = config.f25109b;
            this.f25271h = config.f25110c;
            this.f25272i = config.f25109b;
            this.f25273j = config.f25110c;
        }

        public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
            Log.b(GLVideoRecorder.f25293a, "onSurfaceCreated");
            if (this.f25266c == null) {
                this.f25285w = new Texture2dProgram();
                this.f25284v = this.f25285w.mo7017d();
                this.f25279p = new SurfaceTexture(this.f25284v);
            } else {
                m26394d();
            }
            if (GLVideoRecorder.f25294g.m26560d()) {
                this.f25286x = 2;
            } else {
                f25264t.m26408a();
            }
            f25264t.f25292b.f24778a = System.currentTimeMillis();
            f25264t.f25292b.f24779b = 0;
            this.f25268e.sendMessage(this.f25268e.obtainMessage(1, this.f25279p));
        }

        private void m26394d() {
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            GLES20.glDisable(2929);
            Object obj = new int[1];
            GLES20.glGenTextures(1, obj, 0);
            GlUtil.m26613a("glGenTextures");
            GLES20.glBindTexture(36197, obj[0]);
            GlUtil.m26613a("glBindTexture " + obj);
            GLES20.glTexParameterf(36197, 10241, 9729.0f);
            GLES20.glTexParameterf(36197, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
            GLES20.glTexParameteri(36197, 10242, 33071);
            GLES20.glTexParameteri(36197, 10243, 33071);
            GlUtil.m26613a("glTexParameter");
            this.f25279p = new SurfaceTexture(obj[0]);
            this.f25283u = obj[0];
            GPUImageFrameBufferCache.m27343b();
            this.f25265b.m26456j();
            this.f25266c.m26456j();
        }

        public void onSurfaceChanged(GL10 gl10, int i, int i2) {
            Point a = LayoutUtils.m25847a(this.f25267d.getDisplay());
            Log.b(GLVideoRecorder.f25293a, "onSurfaceChanged");
            Log.b(GLVideoRecorder.f25293a, "  camera:" + this.f25272i + "x" + this.f25273j + " sensor orientation:" + this.f25274k);
            Log.b(GLVideoRecorder.f25293a, "  glSurfaceView:" + i + "x" + i2 + " display rotation:" + this.f25276m);
            Log.b(GLVideoRecorder.f25293a, "  using full display size:" + a.x + "x" + a.y);
            this.f25277n = a.x;
            this.f25278o = a.y;
            GLES20.glViewport(0, 0, this.f25277n, this.f25278o);
            if (this.f25266c != null) {
                GLES20.glUseProgram(this.f25266c.m26463q());
                this.f25265b.mo6992a(this.f25272i, this.f25273j);
                this.f25266c.mo6992a(this.f25272i, this.f25273j);
                this.f25266c.mo6997b(this.f25277n, this.f25278o);
            }
            mo6985a(false);
        }

        public void mo6985a(boolean z) {
            mo6986a(z, this.f25276m);
        }

        public void mo6986a(boolean z, int i) {
            int i2 = 1;
            this.f25276m = i;
            Log.b(GLVideoRecorder.f25293a, "updateFilterMatrix flip:" + z);
            Log.b(GLVideoRecorder.f25293a, "  camera:" + this.f25272i + "x" + this.f25273j + " " + this.f25274k);
            Log.b(GLVideoRecorder.f25293a, "  window:" + this.f25277n + "x" + this.f25278o + " " + this.f25276m);
            if (this.f25266c == null) {
                int i3;
                float[] fArr = this.f25280q;
                boolean z2 = this.f25275l;
                int i4 = this.f25274k;
                int i5 = this.f25276m;
                if (z) {
                    i3 = 180;
                } else {
                    i3 = 0;
                }
                CameraUtils.m26207a(fArr, z2, i4, i3 + i5, this.f25272i, this.f25273j, this.f25277n, this.f25278o);
                return;
            }
            Matrix.setIdentityM(this.f25280q, 0);
            if (this.f25275l) {
                if ((this.f25274k + this.f25276m) % 180 == 0) {
                    i2 = 0;
                }
            } else if (((this.f25274k - this.f25276m) + 360) % 360 == 0) {
                i2 = 0;
            }
            float f = ((float) this.f25272i) / ((float) this.f25273j);
            if (i2 != 0) {
                f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT / f;
            }
            float f2 = ((float) this.f25277n) / ((float) this.f25278o);
            float f3;
            if (f2 > f) {
                f /= f2;
                if (i2 != 0) {
                    f3 = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT / f;
                } else {
                    f3 = f;
                }
                Log.b(GLVideoRecorder.f25293a, "scale:Y:" + f3);
                Matrix.scaleM(this.f25280q, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, f3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            } else {
                f = f2 / f;
                if (i2 != 0) {
                    f3 = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT / f;
                } else {
                    f3 = f;
                }
                Log.b(GLVideoRecorder.f25293a, "scale:X:" + f3);
                Matrix.scaleM(this.f25280q, 0, f3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            }
            if (z) {
                Matrix.rotateM(this.f25280q, 0, 180.0f, 0.0f, 0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            }
            this.f25265b.m27328a(this.f25280q);
        }

        public void mo6988b(boolean z) {
            Log.b(GLVideoRecorder.f25293a, "startRecording:" + this.f25287y + "->" + z);
            this.f25287y = z;
            if (!this.f25287y) {
                Log.b(GLVideoRecorder.f25293a, "Stopping encoder");
                GLVideoRecorder.f25294g.m26558b();
                this.f25286x = 0;
            }
        }

        public void onDrawFrame(GL10 gl10) {
            if (this.f25279p == null) {
                Log.e(GLVideoRecorder.f25293a, "mCameraTexture invalid");
                return;
            }
            Frame frame = f25264t.f25291a;
            frame.f25288a++;
            this.f25279p.getTimestamp();
            this.f25279p.updateTexImage();
            if (this.f25282s != null) {
                m26396f();
            }
            m26395e();
        }

        private void m26395e() {
            if (this.f25266c != null) {
                this.f25279p.getTransformMatrix(this.f25281r);
                this.f25265b.m27329b(this.f25281r);
                this.f25265b.m26445a(new GPUImageFrameBuffer(this.f25283u, this.f25277n, this.f25278o));
                if (GLES20.glGetError() != 0) {
                    Log.e(GLVideoRecorder.f25293a, String.format("GlError:%d", new Object[]{Integer.valueOf(r0)}));
                    return;
                }
                return;
            }
            GlUtil.m26613a("draw start");
            this.f25285w.mo7015a(this.f25280q, this.f25284v);
            GlUtil.m26613a("draw done");
        }

        private void m26396f() {
            if (this.f25287y) {
                Frame frame = f25264t.f25291a;
                frame.f25289b++;
                switch (this.f25286x) {
                    case 0:
                        Log.b(GLVideoRecorder.f25293a, "START recording");
                        GLVideoRecorder.f25294g.m26556a(new EncoderConfig(this.f25282s, VideoUtils.m26581c(), VideoUtils.m26581c(), this.f25270g, this.f25271h, this.f25274k, VideoUtils.m26580b(), VideoUtils.m26579a(), this.f25275l, this.f25276m, EGL14.eglGetCurrentContext(), this.f25269f, this));
                        this.f25286x = 1;
                        break;
                    case 1:
                        break;
                    case 2:
                        Log.b(GLVideoRecorder.f25293a, "RESUME recording");
                        GLVideoRecorder.f25294g.m26555a(EGL14.eglGetCurrentContext());
                        this.f25286x = 1;
                        break;
                    default:
                        throw new RuntimeException("unknown status " + this.f25286x);
                }
            }
            frame = f25264t.f25291a;
            frame.f25290c++;
            switch (this.f25286x) {
                case 0:
                    break;
                case 1:
                case 2:
                    Log.b(GLVideoRecorder.f25293a, "STOP recording");
                    GLVideoRecorder.f25294g.m26558b();
                    this.f25286x = 0;
                    break;
                default:
                    throw new RuntimeException("unknown status " + this.f25286x);
            }
            if (this.f25266c != null) {
                GLVideoRecorder.f25294g.m26552a(this.f25283u);
            } else {
                GLVideoRecorder.f25294g.m26552a(this.f25284v);
            }
            GLVideoRecorder.f25294g.m26554a(this.f25279p);
        }

        public final Stats mo6987b() {
            m26397g();
            return f25264t;
        }

        public void mo6989c() {
            f25264t.m26408a();
            f25264t.f25292b.f24778a = System.currentTimeMillis();
            f25264t.f25292b.f24779b = 0;
        }

        private synchronized void m26397g() {
            if (f25264t.f25292b.f24779b == 0) {
                f25264t.f25292b.f24779b = System.currentTimeMillis();
                TimeStat timeStat = f25264t.f25292b;
                timeStat.f24780c += f25264t.f25292b.f24779b - f25264t.f25292b.f24778a;
            }
        }

        public void mo6990a(Exception exception) {
            if (this.f25268e != null) {
                this.f25268e.sendMessage(this.f25268e.obtainMessage(2, exception));
            }
        }
    }

    public static class Stats {
        public Frame f25291a;
        public TimeStat f25292b;

        public static class Frame {
            public long f25288a;
            public long f25289b;
            public long f25290c;

            public Frame() {
                m26406a();
            }

            public Frame(Frame frame) {
                this.f25288a = frame.f25288a;
                this.f25289b = frame.f25289b;
                this.f25290c = frame.f25290c;
            }

            void m26406a() {
                this.f25288a = 0;
                this.f25289b = 0;
                this.f25290c = 0;
            }

            public void m26407a(String str) {
                Log.b(str, "    mFromCamera:" + this.f25288a);
                Log.b(str, "    mWhileRecording:" + this.f25289b);
                Log.b(str, "    mWhileNotRecording:" + this.f25290c);
            }
        }

        public Stats() {
            this.f25291a = new Frame();
            this.f25292b = new TimeStat();
        }

        public Stats(Stats stats) {
            this.f25291a = new Frame(stats.f25291a);
            this.f25292b = new TimeStat(stats.f25292b);
        }

        public void m26408a() {
            this.f25291a.m26406a();
            this.f25292b.m25875a();
        }

        public void m26409a(String str) {
            Log.b(str, "frame:");
            this.f25291a.m26407a(str);
            Log.b(str, "time:");
            this.f25292b.m25876a(str);
        }
    }

    public void m26422a(RecordDelegate recordDelegate, GLSurfaceView gLSurfaceView, String str, int i, GetAudioTimeCallback getAudioTimeCallback, FilterType[] filterTypeArr, String str2, Boolean bool, boolean z, boolean z2, Point point) {
        GPUImageFilter gPUImageFilter;
        this.f25297d = new MainHandler(this);
        this.f25299f = str;
        this.f25295b = recordDelegate;
        this.f25296c = gLSurfaceView;
        this.f25296c.setEGLContextClientVersion(2);
        this.f25302j = filterTypeArr;
        this.f25303k = z;
        this.f25304l = z2;
        this.f25305m = point;
        this.f25306n = i;
        VideoFilterDatabase.f25443a = this.f25305m;
        Context applicationContext = SingApplication.g().getApplicationContext();
        if (this.f25302j.length > 1) {
            this.f25300h = new GPUImageFilterGallery(applicationContext, this.f25302j, str2);
            this.f25300h.f25341c = this.f25303k;
            this.f25300h.f25340b = z2;
            gPUImageFilter = this.f25300h;
        } else if (this.f25302j.length != 1) {
            gPUImageFilter = null;
        } else if (this.f25303k) {
            this.f25301i = VideoFilterDatabase.m26568a(applicationContext, this.f25302j[0], this.f25304l);
            gPUImageFilter = this.f25301i;
        } else {
            gPUImageFilter = VideoFilterDatabase.m26567a(applicationContext, this.f25302j[0]);
        }
        try {
            final Config a = m26419a(bool.booleanValue(), this.f25305m);
            this.f25298e = new Renderer(this.f25297d, str, i, getAudioTimeCallback, gPUImageFilter, this.f25296c);
            this.f25296c.setRenderer(this.f25298e);
            this.f25296c.setRenderMode(0);
            this.f25296c.onResume();
            this.f25296c.queueEvent(new Runnable(this) {
                final /* synthetic */ GLVideoRecorder f25252b;

                public void run() {
                    this.f25252b.f25298e.mo6984a(a);
                    this.f25252b.f25298e.mo6985a(false);
                }
            });
            if (this.f25299f != null) {
                m26416c(true);
            }
        } catch (Exception e) {
            this.f25298e = new NoOpRenderer();
            this.f25296c.setRenderer(this.f25298e);
            throw e;
        }
    }

    public void m26424a(boolean z) {
        if (this.f25300h != null) {
            this.f25300h.m26476a(z);
        } else if (this.f25301i != null) {
            this.f25301i.m27450a(z);
        }
    }

    public void m26425a(boolean z, boolean z2) {
        m26426a(z, z2, this.f25306n, this.f25305m);
    }

    public void m26426a(boolean z, final boolean z2, int i, Point point) {
        Log.b(f25293a, "restartPreview");
        this.f25305m = point;
        VideoFilterDatabase.f25443a = this.f25305m;
        this.f25306n = i;
        final Config a = m26419a(z, this.f25305m);
        this.f25296c.onResume();
        this.f25296c.queueEvent(new Runnable(this) {
            final /* synthetic */ GLVideoRecorder f25255c;

            public void run() {
                this.f25255c.f25298e.mo6984a(a);
                this.f25255c.f25298e.mo6986a(z2, this.f25255c.f25306n);
            }
        });
        if (this.f25299f != null) {
            m26416c(true);
        }
    }

    public Config m26419a(boolean z, Point point) {
        Log.b(f25293a, "acquireCamera:");
        if (!this.f25307o) {
            CameraUtils.m26202a().m26214b();
            this.f25307o = true;
        }
        Config a = CameraUtils.m26202a().m26211a(z, false, 0, point);
        this.f25307o = false;
        return a;
    }

    public GPUImageFilterGallery m26420a() {
        return this.f25300h;
    }

    public void m26427b() {
        Log.b(f25293a, "onPause");
        CameraUtils.m26202a().m26214b();
        this.f25307o = true;
        this.f25296c.queueEvent(new C50443(this));
        this.f25296c.onPause();
        m26432f();
    }

    public void m26428b(final boolean z) {
        Log.b(f25293a, "updateAspect");
        this.f25296c.queueEvent(new Runnable(this) {
            final /* synthetic */ GLVideoRecorder f25258b;

            public void run() {
                this.f25258b.f25298e.mo6985a(z);
            }
        });
    }

    public void m26429c() {
        Log.b(f25293a, "onDestroy");
        this.f25297d.m26378a();
    }

    public void m26430d() {
        Log.b(f25293a, "stopRecording");
        m26416c(false);
    }

    public void m26431e() {
        Log.b(f25293a, "restartRecording");
        f25294g.m26559c();
        m26416c(false);
        m26416c(true);
        this.f25296c.queueEvent(new C50465(this));
    }

    public void m26432f() {
        Log.b(f25293a, "pauseEncoder");
        f25294g.m26561e();
    }

    public void m26423a(Float f) {
        Log.b(f25293a, "unpauseEncoder");
        f25294g.m26557a(f);
    }

    private void m26411a(SurfaceTexture surfaceTexture) {
        Log.b(f25293a, "handleSetSurfaceTexture");
        surfaceTexture.setOnFrameAvailableListener(this);
        try {
            CameraUtils.m26202a().m26212a(this.f25306n);
            CameraUtils.m26202a().m26213a(surfaceTexture);
            if (this.f25295b != null) {
                this.f25295b.ae();
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private void m26414a(Exception exception) {
        Log.e(f25293a, "encoder exception:" + exception);
        m26416c(false);
        if (this.f25295b != null) {
            this.f25295b.mo6634a(exception);
        }
    }

    private void m26416c(final boolean z) {
        Log.b(f25293a, "changeRecordingState:" + z);
        this.f25296c.queueEvent(new Runnable(this) {
            final /* synthetic */ GLVideoRecorder f25261b;

            public void run() {
                this.f25261b.f25298e.mo6988b(z);
            }
        });
    }

    public void m26421a(int i, int i2) {
        f25294g.m26553a(i, i2);
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.f25296c.requestRender();
    }

    public final Stats m26433g() {
        if (this.f25298e != null) {
            return new Stats(this.f25298e.mo6987b());
        }
        return new Stats();
    }

    public final com.smule.singandroid.video.TextureMovieEncoder.Stats m26434h() {
        if (f25294g != null) {
            return new com.smule.singandroid.video.TextureMovieEncoder.Stats(f25294g.m26551a());
        }
        return new com.smule.singandroid.video.TextureMovieEncoder.Stats();
    }
}
