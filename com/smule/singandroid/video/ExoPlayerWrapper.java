package com.smule.singandroid.video;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.media.MediaCodec.CryptoException;
import android.net.Uri;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.ExoPlayer.Factory;
import com.google.android.exoplayer.ExoPlayer.Listener;
import com.google.android.exoplayer.MediaCodecSelector;
import com.google.android.exoplayer.MediaCodecTrackRenderer.DecoderInitializationException;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer.EventListener;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorSampleSource;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.upstream.CounterBandwidthMeter;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;
import com.google.android.gms.gcm.Task;
import com.mopub.common.Constants;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.audio.AudioDefs.MonitoringMode;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.singandroid.DeviceSettings;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.video.gles.EglCore;
import com.smule.singandroid.video.gles.GlUtil;
import com.smule.singandroid.video.gles.Texture2dProgram;
import com.smule.singandroid.video.gles.WindowSurface;
import java.lang.ref.WeakReference;
import jp.co.cyberagent.android.gpuimage.GPUImageExternalTexture;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFrameBuffer;
import jp.co.cyberagent.android.gpuimage.GPUImageFrameBufferCache;

public class ExoPlayerWrapper {
    private static final String f25221a = ExoPlayerWrapper.class.getSimpleName();
    private Listener f25222A = new C50401(this);
    private long f25223B;
    private long f25224C;
    private final EventListener f25225D = new C50412(this);
    private Context f25226b;
    private GetAudioTimeCallback f25227c;
    private TextureView f25228d;
    private Handler f25229e;
    private String f25230f;
    private boolean f25231g;
    private RenderThread f25232h;
    private MediaCodecVideoTrackRenderer f25233i;
    private Dimensions f25234j = new Dimensions();
    private ExoPlayerInternalErrorListener f25235k;
    private ExoPlayerStateChangeListener f25236l;
    private float f25237m;
    private float f25238n;
    private ScalingForAspectRatio f25239o = ScalingForAspectRatio.CENTER_WITH_CROP;
    private final NptSLogger f25240p;
    private boolean f25241q;
    private float f25242r;
    private SurfaceTextureListener f25243s;
    private ExoPlayer f25244t;
    private ExtractorSampleSource f25245u;
    private CounterBandwidthMeter f25246v;
    private float f25247w;
    private GPUImageFilterGallery f25248x;
    private boolean f25249y;
    private boolean f25250z;

    public interface ExoPlayerStateChangeListener {
        void mo6398a(int i);
    }

    public interface ExoPlayerInternalErrorListener {
        void mo6586E();
    }

    class C50401 implements Listener {
        final /* synthetic */ ExoPlayerWrapper f25177a;

        class C50392 implements Runnable {
            final /* synthetic */ C50401 f25176a;

            C50392(C50401 c50401) {
                this.f25176a = c50401;
            }

            public void run() {
                this.f25176a.f25177a.f25235k.mo6586E();
            }
        }

        C50401(ExoPlayerWrapper exoPlayerWrapper) {
            this.f25177a = exoPlayerWrapper;
        }

        public void onPlayerStateChanged(boolean z, final int i) {
            Log.b(ExoPlayerWrapper.f25221a, "onPlayerStateChanged:" + z + " " + i);
            if (i == 4) {
                Log.b(ExoPlayerWrapper.f25221a, "ready");
                if (this.f25177a.f25244t != null && this.f25177a.f25245u != null) {
                    this.f25177a.f25247w = ((float) this.f25177a.f25244t.mo4253e()) / 1000.0f;
                    Log.b(ExoPlayerWrapper.f25221a, "duration:" + this.f25177a.f25247w);
                    if (this.f25177a.f25232h != null) {
                        this.f25177a.f25232h.m26337a().m26307i();
                    }
                    this.f25177a.f25240p.mo7005a(this.f25177a.f25247w);
                } else {
                    return;
                }
            }
            if (this.f25177a.f25236l != null) {
                this.f25177a.f25229e.post(new Runnable(this) {
                    final /* synthetic */ C50401 f25175b;

                    public void run() {
                        if (this.f25175b.f25177a.f25244t == null) {
                            Log.b(ExoPlayerWrapper.f25221a, "no player not sending message");
                        } else {
                            this.f25175b.f25177a.f25236l.mo6398a(i);
                        }
                    }
                });
            }
            this.f25177a.f25240p.mo7011a(this.f25177a.f25246v.m11279d(), i);
            if (i == 5) {
                this.f25177a.f25246v.m11280e();
            }
        }

        public void onPlayWhenReadyCommitted() {
        }

        public void onPlayerError(ExoPlaybackException exoPlaybackException) {
            Log.e(ExoPlayerWrapper.f25221a, "onPlayerError:" + exoPlaybackException);
            if (this.f25177a.f25229e != null && this.f25177a.f25235k != null) {
                this.f25177a.f25229e.post(new C50392(this));
                this.f25177a.f25240p.mo7012a(this.f25177a.f25246v.m11279d(), exoPlaybackException + ":" + exoPlaybackException.getCause());
            }
        }
    }

    class C50412 implements EventListener {
        final /* synthetic */ ExoPlayerWrapper f25178a;

        C50412(ExoPlayerWrapper exoPlayerWrapper) {
            this.f25178a = exoPlayerWrapper;
        }

        public void mo6960a(int i, long j) {
            this.f25178a.f25240p.mo6960a(i, j);
        }

        public void mo6959a(int i, int i2, int i3, float f) {
            Log.b(ExoPlayerWrapper.f25221a, "onVideoSizeChanged:" + i + "x" + i2);
            this.f25178a.f25234j.f25181c = i;
            this.f25178a.f25234j.f25182d = i2;
            this.f25178a.m26360m();
            this.f25178a.m26358l();
            if (this.f25178a.f25232h != null) {
                Log.b(ExoPlayerWrapper.f25221a, "triggering video size changed render");
                this.f25178a.f25232h.m26337a().m26301c();
            }
        }

        public void mo6962a(Surface surface) {
            Log.b(ExoPlayerWrapper.f25221a, "MCVTR.EL:onDrawnToSurface");
            long currentTimeMillis = System.currentTimeMillis();
            int i = -1;
            if (this.f25178a.f25223B > 0) {
                i = (int) (currentTimeMillis - this.f25178a.f25223B);
            } else {
                Log.e(ExoPlayerWrapper.f25221a, "MCVTR.EL:onDrawnToSurface: unable to determine first frame time");
            }
            if (this.f25178a.f25232h != null) {
                this.f25178a.f25232h.m26337a().m26298a((int) this.f25178a.f25224C, i);
            }
        }

        public void mo6963a(DecoderInitializationException decoderInitializationException) {
        }

        public void mo6961a(CryptoException cryptoException) {
        }

        public void mo6964a(String str, long j, long j2) {
            Log.b(ExoPlayerWrapper.f25221a, "MCVTR.EL:onDecoderInitialized:" + str + " elapsed:" + j + " initDur:" + j2);
            this.f25178a.f25223B = System.currentTimeMillis();
            this.f25178a.f25224C = j2;
        }
    }

    private static class Dimensions {
        public int f25179a = -1;
        public int f25180b = -1;
        public int f25181c = -1;
        public int f25182d = -1;

        Dimensions() {
        }

        boolean m26295a() {
            return this.f25179a >= 0 && this.f25180b >= 0 && this.f25181c >= 0 && this.f25182d >= 0;
        }

        void m26294a(Dimensions dimensions) {
            this.f25179a = dimensions.f25179a;
            this.f25180b = dimensions.f25180b;
            this.f25181c = dimensions.f25181c;
            this.f25182d = dimensions.f25182d;
        }

        public String toString() {
            return "video w:" + this.f25181c + " h:" + this.f25182d + " surface w:" + this.f25179a + " h:" + this.f25180b;
        }
    }

    private static class RenderHandler extends Handler {
        private WeakReference<RenderThread> f25183a;

        public RenderHandler(RenderThread renderThread) {
            this.f25183a = new WeakReference(renderThread);
        }

        public void m26296a() {
            sendMessage(obtainMessage(3));
        }

        public void m26300b() {
            sendMessage(obtainMessage(4));
        }

        public void m26301c() {
            sendMessage(obtainMessage(14));
        }

        public void m26302d() {
            sendMessageDelayed(obtainMessage(4), 30);
        }

        public void m26303e() {
            sendMessage(obtainMessage(5));
        }

        public void m26304f() {
            sendMessage(obtainMessage(0));
        }

        public void m26299a(Dimensions dimensions) {
            sendMessage(obtainMessage(9, dimensions));
        }

        public void m26305g() {
            sendMessage(obtainMessage(10));
        }

        public void m26306h() {
            sendMessage(obtainMessage(11));
        }

        public void m26307i() {
            sendMessage(obtainMessage(8));
        }

        public void m26297a(float f) {
            sendMessage(obtainMessage(12, Float.valueOf(f)));
        }

        public void m26298a(int i, int i2) {
            sendMessage(obtainMessage(13, i, i2));
        }

        public void handleMessage(Message message) {
            int i = message.what;
            RenderThread renderThread = (RenderThread) this.f25183a.get();
            if (renderThread == null) {
                Log.b(ExoPlayerWrapper.f25221a, "RenderHandler.handleMessage: weak ref is null");
                return;
            }
            switch (i) {
                case 0:
                    renderThread.m26324g();
                    return;
                case 3:
                    renderThread.m26320e();
                    return;
                case 4:
                    renderThread.m26334m();
                    return;
                case 5:
                    renderThread.m26332k();
                    return;
                case 8:
                    renderThread.m26333l();
                    return;
                case 9:
                    renderThread.m26311a((Dimensions) message.obj);
                    return;
                case 10:
                    renderThread.m26316c();
                    return;
                case 11:
                    renderThread.m26318d();
                    return;
                case 12:
                    renderThread.m26309a(((Float) message.obj).floatValue());
                    return;
                case 13:
                    renderThread.m26310a(message.arg1, message.arg2);
                    return;
                case 14:
                    renderThread.m26328i();
                    return;
                default:
                    throw new RuntimeException("unknown message " + i);
            }
        }
    }

    private static class RenderThread extends Thread implements OnFrameAvailableListener {
        static final float[] f25189a = new float[]{-1.0f, -1.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, -1.0f, -1.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT};
        private SurfaceTexture f25190A;
        private final float[] f25191B = new float[16];
        GPUImageExternalTexture f25192b;
        GPUImageFilter f25193c;
        private volatile RenderHandler f25194d;
        private State f25195e;
        private final Object f25196f = new Object();
        private boolean f25197g = false;
        private boolean f25198h = false;
        private int f25199i = 0;
        private boolean f25200j;
        private SurfaceTexture f25201k;
        private MediaCodecVideoTrackRenderer f25202l;
        private GetAudioTimeCallback f25203m;
        private ExoPlayer f25204n;
        private float f25205o;
        private float f25206p;
        private float f25207q;
        private int f25208r;
        private Surface f25209s;
        private float f25210t;
        private int f25211u;
        private Dimensions f25212v = new Dimensions();
        private EglCore f25213w;
        private WindowSurface f25214x;
        private Texture2dProgram f25215y;
        private int f25216z;

        private enum State {
            STOPPED,
            PAUSED,
            SEEKING,
            PLAYING
        }

        public RenderThread(ExoPlayer exoPlayer, MediaCodecVideoTrackRenderer mediaCodecVideoTrackRenderer, GetAudioTimeCallback getAudioTimeCallback, SurfaceTexture surfaceTexture, float f, float f2, boolean z, GPUImageFilter gPUImageFilter) {
            this.f25204n = exoPlayer;
            this.f25202l = mediaCodecVideoTrackRenderer;
            this.f25203m = getAudioTimeCallback;
            this.f25201k = surfaceTexture;
            this.f25205o = f;
            this.f25206p = f2;
            this.f25195e = State.STOPPED;
            this.f25200j = z;
            this.f25207q = m26336o();
            this.f25209s = null;
            this.f25210t = 0.5f;
            this.f25211u = 0;
            this.f25192b = new GPUImageExternalTexture();
            this.f25193c = gPUImageFilter;
            this.f25192b.mo6993a(this.f25193c);
        }

        public RenderHandler m26337a() {
            return this.f25194d;
        }

        public void m26338b() {
            synchronized (this.f25196f) {
                while (!this.f25197g) {
                    try {
                        this.f25196f.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }

        private void m26316c() {
            Log.b(ExoPlayerWrapper.f25221a, "renderStart");
            this.f25198h = true;
        }

        private void m26318d() {
            Log.b(ExoPlayerWrapper.f25221a, "renderStop");
            this.f25198h = false;
            this.f25204n.mo4246a(false);
            this.f25195e = State.STOPPED;
        }

        private void m26320e() {
            Log.b(ExoPlayerWrapper.f25221a, "shutdown");
            Looper.myLooper().quit();
        }

        public void run() {
            Looper.prepare();
            this.f25194d = new RenderHandler(this);
            synchronized (this.f25196f) {
                this.f25197g = true;
                this.f25196f.notify();
            }
            this.f25194d.m26304f();
            Looper.loop();
            Log.b(ExoPlayerWrapper.f25221a, "looper quit");
            m26330j();
            synchronized (this.f25196f) {
                this.f25197g = false;
            }
        }

        private void m26311a(Dimensions dimensions) {
            Log.b(ExoPlayerWrapper.f25221a, "updateDimensions:" + dimensions.toString());
            this.f25212v.m26294a(dimensions);
            if (this.f25212v.m26295a() && this.f25193c != null) {
                GLES20.glUseProgram(this.f25193c.m26463q());
                this.f25192b.mo6992a(this.f25212v.f25181c, this.f25212v.f25182d);
                this.f25193c.mo6992a(this.f25212v.f25181c, this.f25212v.f25182d);
                this.f25193c.mo6997b(this.f25212v.f25179a, this.f25212v.f25180b);
            }
        }

        private Surface m26322f() {
            return this.f25209s;
        }

        private void m26324g() {
            Log.b(ExoPlayerWrapper.f25221a, "init+");
            if (this.f25209s != null) {
                this.f25209s.release();
            }
            if (this.f25201k == null) {
                this.f25209s = null;
            } else if (this.f25193c != null) {
                this.f25209s = m26326h();
            } else {
                this.f25209s = new Surface(this.f25201k);
            }
            this.f25204n.mo4244a(this.f25202l, 1, this.f25209s);
            Log.b(ExoPlayerWrapper.f25221a, "init-");
        }

        private Surface m26326h() {
            this.f25213w = new EglCore(null, 0);
            this.f25214x = new WindowSurface(this.f25213w, this.f25201k);
            this.f25214x.m26608b();
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
            this.f25216z = obj[0];
            this.f25190A = new SurfaceTexture(this.f25216z);
            GPUImageFrameBufferCache.m27343b();
            this.f25192b.m26456j();
            this.f25193c.m26456j();
            this.f25190A.setOnFrameAvailableListener(this);
            return new Surface(this.f25190A);
        }

        public void onFrameAvailable(SurfaceTexture surfaceTexture) {
            this.f25190A.updateTexImage();
            m26334m();
            m26328i();
        }

        private void m26328i() {
            if (this.f25190A != null && this.f25212v.m26295a()) {
                this.f25190A.getTransformMatrix(this.f25191B);
                GPUImageFrameBuffer gPUImageFrameBuffer = new GPUImageFrameBuffer(this.f25216z, this.f25212v.f25179a, this.f25212v.f25180b);
                this.f25192b.m27329b(this.f25191B);
                this.f25192b.m26445a(gPUImageFrameBuffer);
                int glGetError = GLES20.glGetError();
                if (glGetError != 0) {
                    Log.e(ExoPlayerWrapper.f25221a, "gl error: " + glGetError);
                }
                this.f25214x.m26609c();
            }
        }

        private void m26330j() {
            GlUtil.m26613a("releaseGl start");
            if (this.f25214x != null) {
                this.f25214x.m26616d();
                this.f25214x = null;
            }
            if (this.f25215y != null) {
                this.f25215y.mo7016c();
                this.f25215y = null;
            }
            if (this.f25193c != null && this.f25193c.m26460n()) {
                this.f25193c.m26457k();
            }
            if (this.f25192b != null && this.f25192b.m26460n()) {
                this.f25192b.m26457k();
            }
            if (this.f25213w != null) {
                this.f25213w.m26602b();
                this.f25213w.m26599a();
                GlUtil.m26613a("releaseGl");
            }
            if (this.f25190A != null) {
                this.f25190A.setOnFrameAvailableListener(null);
            }
        }

        private void m26332k() {
            Log.b(ExoPlayerWrapper.f25221a, "reset+");
            this.f25210t = 0.5f;
            this.f25211u = 0;
            int a = (int) ((this.f25203m.mo6397a() + this.f25210t) * 1000.0f);
            this.f25204n.mo4246a(false);
            if (this.f25204n.mo4254f() == ((long) a)) {
                Log.b(ExoPlayerWrapper.f25221a, "already at position");
                this.f25195e = State.PAUSED;
                this.f25194d.m26302d();
            } else {
                Log.b(ExoPlayerWrapper.f25221a, "Seeking:" + a);
                this.f25204n.mo4243a((long) a);
                this.f25195e = State.SEEKING;
            }
            Log.b(ExoPlayerWrapper.f25221a, "reset-");
        }

        private void m26333l() {
            State state = this.f25195e;
            if (this.f25195e == State.SEEKING || this.f25195e == State.PAUSED) {
                this.f25195e = State.PAUSED;
                this.f25194d.m26302d();
            }
            Log.b(ExoPlayerWrapper.f25221a, "seekDone:" + state + "->" + this.f25195e);
        }

        private void m26334m() {
            if (this.f25198h && this.f25212v.m26295a()) {
                float a = this.f25203m.mo6397a() - this.f25207q;
                float f = ((float) this.f25204n.mo4254f()) / 1000.0f;
                if (this.f25195e == State.PLAYING) {
                    if (a - f > this.f25205o) {
                        int i = (int) ((a + this.f25210t) * 1000.0f);
                        Log.b(ExoPlayerWrapper.f25221a, "video far behind. seek:" + i);
                        Log.b(ExoPlayerWrapper.f25221a, "mCurHop:" + this.f25210t);
                        this.f25210t += 0.5f;
                        if (this.f25210t >= this.f25206p) {
                            this.f25210t = this.f25206p;
                        }
                        this.f25211u = 0;
                        this.f25195e = State.SEEKING;
                        this.f25204n.mo4246a(false);
                        this.f25204n.mo4243a((long) i);
                        this.f25208r++;
                    } else if (f > a + this.f25205o) {
                        Log.b(ExoPlayerWrapper.f25221a, "video ahead. pause");
                        this.f25204n.mo4246a(false);
                        this.f25195e = State.PAUSED;
                        this.f25194d.m26302d();
                    } else {
                        this.f25211u++;
                        if (this.f25211u > 3 && this.f25210t != 0.5f) {
                            Log.b(ExoPlayerWrapper.f25221a, "reset hop");
                            this.f25210t = 0.5f;
                        }
                    }
                } else if (this.f25195e != State.SEEKING && this.f25195e == State.PAUSED) {
                    if (f <= a) {
                        Log.b(ExoPlayerWrapper.f25221a, "setting play");
                        this.f25204n.mo4246a(true);
                        this.f25195e = State.PLAYING;
                    }
                    this.f25194d.m26302d();
                }
                this.f25199i++;
            }
        }

        private void m26309a(float f) {
            Log.b(ExoPlayerWrapper.f25221a, "seekTo:" + f);
            this.f25204n.mo4246a(false);
            this.f25204n.mo4243a((long) ((int) (1000.0f * f)));
        }

        private boolean m26335n() {
            return Build.MODEL != null && Build.MODEL.equals("Nexus 7");
        }

        private void m26310a(int i, int i2) {
            Log.b(ExoPlayerWrapper.f25221a, "processExoInitData:initTime:" + i + " initToDisplay:" + i2 + " tot:" + (i + i2));
            if (i <= 0 || i2 <= 0) {
                Log.e(ExoPlayerWrapper.f25221a, "Exo timing invalid.  Return defaults");
                this.f25207q = m26336o();
            } else if (m26335n()) {
                this.f25207q = m26336o();
            } else if (DeviceSettings.n() != MonitoringMode.NONE) {
                this.f25207q = 0.06f;
            } else if (i < 60) {
                this.f25207q = m26336o();
            } else {
                this.f25207q = ((float) (i + i2)) / 1000.0f;
                if (this.f25207q >= 0.25f) {
                    this.f25207q = 0.8f * this.f25207q;
                } else {
                    this.f25207q = 0.66f * this.f25207q;
                }
                if (this.f25207q < 0.1f) {
                    this.f25207q = 0.1f;
                } else if (this.f25207q > 0.3f) {
                    this.f25207q = 0.3f;
                }
            }
            Log.b(ExoPlayerWrapper.f25221a, "Adjust Exo overhead:" + this.f25207q);
            SingAnalytics.m26058a(i, i2, (int) (this.f25207q * 1000.0f), this.f25200j);
        }

        private float m26336o() {
            return this.f25205o + 0.06f;
        }
    }

    public enum ScalingForAspectRatio {
        CENTER_WITH_CROP,
        FIT_FOR_FILMSTRIP
    }

    private class TexLis implements SurfaceTextureListener {
        final /* synthetic */ ExoPlayerWrapper f25220a;

        private TexLis(ExoPlayerWrapper exoPlayerWrapper) {
            this.f25220a = exoPlayerWrapper;
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            Log.b(ExoPlayerWrapper.f25221a, "onSurfaceTextureAvailable (" + i + "x" + i2 + ")");
            Dimensions a = this.f25220a.f25234j;
            this.f25220a.f25234j.f25179a = i;
            a.f25181c = i;
            a = this.f25220a.f25234j;
            this.f25220a.f25234j.f25180b = i2;
            a.f25182d = i2;
            this.f25220a.m26342a(surfaceTexture);
            this.f25220a.m26360m();
            this.f25220a.m26358l();
            if (this.f25220a.f25241q) {
                this.f25220a.m26369b(this.f25220a.f25242r);
                this.f25220a.f25241q = false;
            }
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            Log.b(ExoPlayerWrapper.f25221a, "onSurfaceTextureSizeChanged (" + i + "x" + i2 + ")");
            this.f25220a.f25234j.f25179a = i;
            this.f25220a.f25234j.f25180b = i2;
            this.f25220a.m26360m();
            this.f25220a.m26358l();
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            this.f25220a.m26368b();
            return true;
        }

        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            if (this.f25220a.f25232h != null) {
                this.f25220a.f25232h.m26337a().m26300b();
                if (this.f25220a.f25241q) {
                    this.f25220a.m26369b(this.f25220a.f25242r);
                    this.f25220a.f25241q = false;
                }
            }
        }
    }

    public ExoPlayerWrapper(Context context, TextureView textureView, Handler handler, String str, GetAudioTimeCallback getAudioTimeCallback, float f, float f2, ExoPlayerInternalErrorListener exoPlayerInternalErrorListener, ExoPlayerStateChangeListener exoPlayerStateChangeListener, String str2, boolean z, boolean z2) {
        this.f25226b = context;
        this.f25227c = getAudioTimeCallback;
        this.f25229e = handler;
        this.f25230f = str;
        this.f25231g = !this.f25230f.contains(Constants.HTTP);
        this.f25235k = exoPlayerInternalErrorListener;
        this.f25236l = exoPlayerStateChangeListener;
        this.f25237m = f;
        this.f25238n = f2;
        this.f25249y = z;
        this.f25250z = z2;
        if (!TextUtils.isEmpty(str2)) {
            this.f25248x = new GPUImageFilterGallery(context, VideoFilterDatabase.m26569a(), str2);
            this.f25248x.f25341c = this.f25249y;
            m26367a(this.f25250z);
        }
        Log.b(f25221a, "skip threshold:" + this.f25237m);
        Log.b(f25221a, "jump length:" + this.f25238n);
        this.f25232h = null;
        this.f25228d = textureView;
        if (this.f25228d != null) {
            this.f25243s = new TexLis();
            this.f25228d.setSurfaceTextureListener(this.f25243s);
        } else {
            m26342a(null);
        }
        this.f25240p = new NptSLogger(this.f25230f);
    }

    public void m26367a(boolean z) {
        if (this.f25248x != null && this.f25249y) {
            this.f25248x.m26476a(z);
            m26364a();
        }
    }

    public void m26365a(float f) {
        this.f25241q = true;
        this.f25242r = f;
    }

    private void m26358l() {
        if (this.f25232h != null) {
            this.f25232h.m26337a().m26299a(this.f25234j);
        }
    }

    public void m26364a() {
        if (this.f25232h != null) {
            this.f25232h.m26337a().m26301c();
        }
    }

    public void m26368b() {
        if (this.f25244t != null) {
            Surface a;
            Log.b(f25221a, "shutdown");
            if (this.f25232h != null) {
                a = this.f25232h.m26322f();
                this.f25232h.m26337a().m26296a();
                this.f25232h = null;
            } else {
                a = null;
            }
            this.f25244t.mo4251c();
            this.f25244t.mo4252d();
            this.f25244t = null;
            if (a != null) {
                a.release();
            }
            if (this.f25228d != null) {
                this.f25228d.setSurfaceTextureListener(null);
            }
            Log.b(f25221a, "duration:" + this.f25247w);
            this.f25240p.mo7011a(this.f25246v.m11279d(), 5);
        }
    }

    public void m26370c() {
        Log.b(f25221a, "start+");
        if (this.f25228d != null && this.f25228d.getSurfaceTextureListener() == null) {
            this.f25243s = new TexLis();
            this.f25228d.setSurfaceTextureListener(this.f25243s);
        }
        if (this.f25232h != null) {
            RenderHandler a = this.f25232h.m26337a();
            a.m26306h();
            a.m26303e();
            a.m26305g();
        }
        this.f25240p.mo7014b();
        Log.b(f25221a, "start-");
    }

    public void m26371d() {
        Log.b(f25221a, "forceStart+");
        if (this.f25228d != null && this.f25228d.getSurfaceTextureListener() == null) {
            this.f25243s = new TexLis();
            this.f25228d.setSurfaceTextureListener(this.f25243s);
        }
        if (this.f25232h == null) {
            SurfaceTexture surfaceTexture = this.f25228d.getSurfaceTexture();
            if (surfaceTexture == null) {
                Log.b(f25221a, "forceStart- Wait for st.");
                return;
            }
            int width = this.f25228d.getWidth();
            int height = this.f25228d.getHeight();
            Log.b(f25221a, "forceStart (" + width + "x" + height + ")");
            this.f25234j.f25179a = width;
            this.f25234j.f25180b = height;
            m26342a(surfaceTexture);
            m26360m();
            m26358l();
        }
        m26370c();
        Log.b(f25221a, "forceStart-");
    }

    public void m26372e() {
        Log.b(f25221a, "stop+");
        if (this.f25232h != null) {
            this.f25232h.m26337a().m26306h();
        }
        Log.b(f25221a, "stop-");
    }

    public GPUImageFilterGallery m26373f() {
        return this.f25248x;
    }

    public float m26374g() {
        return this.f25247w;
    }

    public ExtractorSampleSource m26375h() {
        return this.f25245u;
    }

    public void m26369b(float f) {
        if (this.f25232h != null) {
            this.f25232h.m26337a().m26297a(f);
        }
    }

    public int m26376i() {
        return this.f25232h != null ? this.f25232h.f25208r : 0;
    }

    public void m26366a(ScalingForAspectRatio scalingForAspectRatio) {
        if (this.f25239o != scalingForAspectRatio) {
            this.f25239o = scalingForAspectRatio;
            m26360m();
        }
    }

    private void m26360m() {
        if (this.f25234j.m26295a()) {
            int i;
            int i2;
            int i3;
            int i4 = this.f25234j.f25181c;
            int i5 = this.f25234j.f25182d;
            int i6 = this.f25234j.f25179a;
            int i7 = this.f25234j.f25180b;
            double d = ((double) i5) / ((double) i4);
            if (this.f25239o == ScalingForAspectRatio.FIT_FOR_FILMSTRIP) {
                i = (int) (((double) i7) / d);
                if (i7 > ((int) (d * ((double) i6)))) {
                    i2 = 0;
                    i3 = i;
                    i = i7;
                } else {
                    i2 = (i6 - i) / 2;
                    i3 = i;
                    i = i7;
                }
            } else {
                if (i7 > ((int) (((double) i6) * d))) {
                    i = (int) (((double) i7) / d);
                    i2 = i7;
                } else {
                    i2 = (int) (d * ((double) i6));
                    i = i6;
                }
                int i8 = (i6 - i) / 2;
                i3 = i;
                i = i2;
                i2 = i8;
            }
            int i9 = (i7 - i) / 2;
            Log.a(f25221a, "scaleForAspect: video=" + i4 + "x" + i5 + " view=" + i6 + "x" + i7 + " newView=" + i3 + "x" + i + " off=" + i2 + "," + i9);
            Matrix matrix = new Matrix();
            this.f25228d.getTransform(matrix);
            matrix.setScale(((float) i3) / ((float) i6), ((float) i) / ((float) i7));
            matrix.postTranslate((float) i2, (float) i9);
            this.f25228d.setTransform(matrix);
        }
    }

    private void m26342a(SurfaceTexture surfaceTexture) {
        Log.b(f25221a, "play:videoUrl:" + this.f25230f);
        if (this.f25230f == null) {
            Log.e(f25221a, "videoUrl not found");
            this.f25228d.setVisibility(8);
            return;
        }
        try {
            this.f25244t = Factory.m9679a(1, 1000, 5000);
            this.f25244t.mo4245a(this.f25222A);
            this.f25247w = -1.0f;
            Uri parse = Uri.parse(this.f25230f);
            Allocator defaultAllocator = new DefaultAllocator(65536);
            this.f25246v = new CounterBandwidthMeter(this.f25229e, this.f25240p);
            this.f25245u = new ExtractorSampleSource(parse, new DefaultUriDataSource(this.f25226b, this.f25246v, MagicNetwork.a().a("sing")), defaultAllocator, 262144, new Extractor[0]);
            this.f25233i = new MediaCodecVideoTrackRenderer(this.f25226b, this.f25245u, MediaCodecSelector.f8041a, 1, 5000, null, true, this.f25229e, this.f25225D, 50);
            this.f25244t.mo4247a(this.f25233i);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e2) {
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            e3.printStackTrace();
        }
        this.f25232h = new RenderThread(this.f25244t, this.f25233i, this.f25227c, surfaceTexture, this.f25237m, this.f25238n, this.f25231g, this.f25248x);
        this.f25232h.setName("TexFromCam Render");
        this.f25232h.start();
        this.f25232h.m26338b();
        m26370c();
    }

    public void m26377j() {
        if (this.f25232h != null) {
            this.f25232h.m26337a().m26303e();
        }
        this.f25240p.mo7014b();
    }
}
