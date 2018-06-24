package com.smule.singandroid.video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaCodec.CryptoException;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import com.danikula.videocache.HttpProxyCacheServer;
import com.danikula.videocache.HttpProxyCacheServer.Builder;
import com.google.android.exoplayer.DefaultLoadControl;
import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.ExoPlayer.Factory;
import com.google.android.exoplayer.ExoPlayer.Listener;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecSelector;
import com.google.android.exoplayer.MediaCodecTrackRenderer.DecoderInitializationException;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer.EventListener;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.SampleSource;
import com.google.android.exoplayer.TrackRenderer;
import com.google.android.exoplayer.audio.AudioCapabilities;
import com.google.android.exoplayer.audio.AudioTrack.InitializationException;
import com.google.android.exoplayer.audio.AudioTrack.WriteException;
import com.google.android.exoplayer.ext.okhttp.OkHttpDataSource;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorSampleSource;
import com.google.android.exoplayer.hls.AggressiveHlsChunkSource;
import com.google.android.exoplayer.hls.DefaultHlsTrackSelector;
import com.google.android.exoplayer.hls.HlsPlaylist;
import com.google.android.exoplayer.hls.HlsPlaylistParser;
import com.google.android.exoplayer.hls.HlsSampleSource;
import com.google.android.exoplayer.hls.PtsTimestampAdjusterProvider;
import com.google.android.exoplayer.upstream.CounterBandwidthMeter;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;
import com.google.android.exoplayer.util.ManifestFetcher;
import com.google.android.exoplayer.util.ManifestFetcher.ManifestCallback;
import com.google.android.exoplayer.util.Util;
import com.mopub.common.Constants;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.utils.ReferenceMonitor;
import com.smule.singandroid.media_player_service.Playback;
import com.smule.singandroid.media_player_service.Playback.Callback;
import com.smule.singandroid.media_player_service.QueueItem;
import com.smule.singandroid.utils.MediaPlaybackUtils;
import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import okhttp3.OkHttpClient;

public class ExoPlayerPlaybackWrapper extends Playback {
    static HttpProxyCacheServer f25141j;
    private static final String f25142l = ExoPlayerPlaybackWrapper.class.getName();
    private boolean f25143A = false;
    private boolean f25144B = false;
    private int f25145C = 0;
    private Surface f25146D;
    private CounterBandwidthMeter f25147E;
    private int f25148F;
    private boolean f25149G = false;
    private long f25150H;
    private volatile int f25151I;
    private volatile boolean f25152J;
    private Listener f25153K = new C50331(this);
    private int f25154L;
    private final EventListener f25155M = new C50342(this);
    private final MediaCodecAudioTrackRenderer.EventListener f25156N = new C50353(this);
    private AsyncRendererBuilder f25157O;
    private Runnable f25158P = new C50375(this);
    Handler f25159k = new Handler();
    private final String f25160m;
    private TextureView f25161n;
    private SurfaceTexture f25162o;
    private String f25163p;
    private Dimensions f25164q = new Dimensions();
    private Handler f25165r = new Handler(Looper.getMainLooper());
    private boolean f25166s = false;
    private final boolean f25167t;
    private ExoPlayer f25168u;
    private MediaCodecVideoTrackRenderer f25169v;
    private MediaCodecAudioTrackRenderer f25170w;
    private NptSBaseLogger f25171x;
    private int f25172y;
    private int f25173z;

    class C50331 implements Listener {
        final /* synthetic */ ExoPlayerPlaybackWrapper f25121a;

        C50331(ExoPlayerPlaybackWrapper exoPlayerPlaybackWrapper) {
            this.f25121a = exoPlayerPlaybackWrapper;
        }

        public void onPlayerStateChanged(boolean z, int i) {
            Log.b(ExoPlayerPlaybackWrapper.f25142l, "State changed to " + MediaPlaybackUtils.m25873b(i));
            this.f25121a.f25151I = i;
            if (i == 2) {
                this.f25121a.m24692a(8);
            } else if (i == 4) {
                Log.b(ExoPlayerPlaybackWrapper.f25142l, "ready");
                if (this.f25121a.f25168u != null) {
                    this.f25121a.f25150H = this.f25121a.f25168u.mo4253e();
                    if (this.f25121a.f25144B && this.f25121a.f25145C != 0) {
                        Log.b(ExoPlayerPlaybackWrapper.f25142l, "seeking to point of interest:" + this.f25121a.f25145C);
                        this.f25121a.mo6973a((long) this.f25121a.f25145C);
                    }
                    this.f25121a.f25144B = false;
                    this.f25121a.m26268t();
                    this.f25121a.f25171x.mo7005a(((float) this.f25121a.f25150H) / 1000.0f);
                } else {
                    return;
                }
            } else if (i == 5) {
                Log.a(ExoPlayerPlaybackWrapper.f25142l, "STATE_ENDED");
                if (!this.f25121a.m24714l().m24736f()) {
                    this.f25121a.m24692a(2);
                } else if (!this.f25121a.f25152J) {
                    this.f25121a.f25152J = true;
                    this.f25121a.mo6971a();
                    this.f25121a.f25152J = false;
                }
            } else if (i == 3) {
                this.f25121a.m24692a(6);
            }
            this.f25121a.m24701b(this.f25121a.mo6980g());
            this.f25121a.f25171x.mo7011a(this.f25121a.f25147E.m11279d(), i);
            if (i == 5) {
                this.f25121a.f25147E.m11280e();
            }
        }

        public void onPlayWhenReadyCommitted() {
            Log.a(ExoPlayerPlaybackWrapper.f25142l, "onPlayWhenReadyCommitted");
        }

        public void onPlayerError(ExoPlaybackException exoPlaybackException) {
            Log.e(ExoPlayerPlaybackWrapper.f25142l, "onPlayerError:" + exoPlaybackException);
            this.f25121a.f25171x.mo7012a(this.f25121a.f25147E.m11279d(), exoPlaybackException + ":" + exoPlaybackException.getCause());
        }
    }

    class C50342 implements EventListener {
        final /* synthetic */ ExoPlayerPlaybackWrapper f25122a;

        C50342(ExoPlayerPlaybackWrapper exoPlayerPlaybackWrapper) {
            this.f25122a = exoPlayerPlaybackWrapper;
        }

        public void mo6960a(int i, long j) {
            Log.a(ExoPlayerPlaybackWrapper.f25142l, "onDroppedFrames");
            this.f25122a.f25171x.mo6960a(i, j);
        }

        public void mo6959a(int i, int i2, int i3, float f) {
            Log.b(ExoPlayerPlaybackWrapper.f25142l, "onVideoSizeChanged:" + i + "x" + i2);
            this.f25122a.f25164q.f25138c = i;
            this.f25122a.f25164q.f25139d = i2;
            this.f25122a.m26265q();
            this.f25122a.f25171x.mo6959a(i, i2, i3, f);
        }

        public void mo6962a(Surface surface) {
            if (this.f25122a.m24703c() == 6 && this.f25122a.f25166s) {
                this.f25122a.m24692a(3);
            }
        }

        public void mo6963a(DecoderInitializationException decoderInitializationException) {
            Log.a(ExoPlayerPlaybackWrapper.f25142l, "onDecoderInitializationError");
        }

        public void mo6961a(CryptoException cryptoException) {
            Log.a(ExoPlayerPlaybackWrapper.f25142l, "onCryptoError");
        }

        public void mo6964a(String str, long j, long j2) {
            Log.a(ExoPlayerPlaybackWrapper.f25142l, "onDecoderInitialized");
        }
    }

    class C50353 implements MediaCodecAudioTrackRenderer.EventListener {
        final /* synthetic */ ExoPlayerPlaybackWrapper f25123a;

        C50353(ExoPlayerPlaybackWrapper exoPlayerPlaybackWrapper) {
            this.f25123a = exoPlayerPlaybackWrapper;
        }

        public void mo6966a(InitializationException initializationException) {
            Log.a(ExoPlayerPlaybackWrapper.f25142l, "onAudioTrackInitializationError");
        }

        public void mo6967a(WriteException writeException) {
            Log.a(ExoPlayerPlaybackWrapper.f25142l, "onAudioTrackWriteError");
        }

        public void mo6965a(int i, long j, long j2) {
            Log.a(ExoPlayerPlaybackWrapper.f25142l, "onAudioTrackUnderrun");
        }

        public void mo6963a(DecoderInitializationException decoderInitializationException) {
            Log.a(ExoPlayerPlaybackWrapper.f25142l, "onDecoderInitializationError");
        }

        public void mo6961a(CryptoException cryptoException) {
            Log.a(ExoPlayerPlaybackWrapper.f25142l, "onCryptoError");
        }

        @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
        public void mo6964a(String str, long j, long j2) {
            Log.a(ExoPlayerPlaybackWrapper.f25142l, "onDecoderInitialized:" + this.f25123a + " " + this.f25123a.f25168u);
            if (this.f25123a.f25168u != null) {
                int a = this.f25123a.f25168u.mo4241a(this.f25123a.f25173z);
                for (int i = 0; i < a; i++) {
                    MediaFormat a2 = this.f25123a.f25168u.mo4242a(this.f25123a.f25173z, i);
                    if (a2 != null && a2.t != null) {
                        String[] split = a2.t.split("[ :]");
                        int i2 = 0;
                        while (i2 < split.length - 1) {
                            if (split[i2] == null || !split[i2].equals("highlight_start") || split[i2 + 1] == null) {
                                i2++;
                            } else {
                                try {
                                    this.f25123a.f25145C = Integer.parseInt(split[i2 + 1]);
                                    Log.b(ExoPlayerPlaybackWrapper.f25142l, "point of interest:" + this.f25123a.f25145C);
                                    break;
                                } catch (NumberFormatException e) {
                                    this.f25123a.f25145C = 0;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    class C50375 implements Runnable {
        long f25125a = 333;
        long f25126b = 1000;
        final /* synthetic */ ExoPlayerPlaybackWrapper f25127c;
        private long f25128d = 50;
        private float f25129e = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;

        C50375(ExoPlayerPlaybackWrapper exoPlayerPlaybackWrapper) {
            this.f25127c = exoPlayerPlaybackWrapper;
        }

        public void run() {
            if (this.f25127c.f25166s) {
                long g = this.f25127c.mo6980g();
                float f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
                if (g <= this.f25125a) {
                    f = ((float) g) / ((float) this.f25125a);
                } else if (this.f25127c.f25150H - g < this.f25126b) {
                    f = ((float) (this.f25127c.f25150H - g)) / ((float) this.f25126b);
                }
                if (f != this.f25129e) {
                    this.f25129e = f;
                    this.f25127c.mo6972a(f);
                }
                this.f25127c.f25159k.postDelayed(this, this.f25128d);
            }
        }
    }

    private static final class AsyncRendererBuilder implements ManifestCallback<HlsPlaylist> {
        private final Context f25130a;
        private final String f25131b;
        private final String f25132c;
        private final ManifestFetcher<HlsPlaylist> f25133d;
        private final ExoPlayerPlaybackWrapper f25134e;
        private boolean f25135f;

        public AsyncRendererBuilder(Context context, String str, String str2, ExoPlayerPlaybackWrapper exoPlayerPlaybackWrapper) {
            this.f25130a = context;
            this.f25131b = str;
            this.f25132c = str2;
            this.f25134e = exoPlayerPlaybackWrapper;
            this.f25133d = new ManifestFetcher(str2, new DefaultUriDataSource(context, str), new HlsPlaylistParser());
        }

        public void m26229a() {
            this.f25133d.m11443a(this.f25134e.f25165r.getLooper(), (ManifestCallback) this);
        }

        public void m26233b() {
            this.f25135f = true;
        }

        public void mo6969a(IOException iOException) {
            if (!this.f25135f) {
                Log.e(ExoPlayerPlaybackWrapper.f25142l, "onSingleManifestError:" + iOException);
                this.f25134e.m26243a((Exception) iOException);
            }
        }

        public void m26230a(HlsPlaylist hlsPlaylist) {
            if (!this.f25135f) {
                Log.b(ExoPlayerPlaybackWrapper.f25142l, "onSingleManifest");
                DefaultLoadControl defaultLoadControl = new DefaultLoadControl(new DefaultAllocator(65536));
                HlsPlaylist hlsPlaylist2 = hlsPlaylist;
                DefaultLoadControl defaultLoadControl2 = defaultLoadControl;
                this.f25134e.m26240a(new HlsSampleSource(new AggressiveHlsChunkSource(true, new DefaultUriDataSource(this.f25130a, this.f25134e.f25147E, this.f25131b), this.f25132c, hlsPlaylist2, DefaultHlsTrackSelector.m10888a(this.f25130a), this.f25134e.f25147E, new PtsTimestampAdjusterProvider(), 1, 5000, 20000), defaultLoadControl2, 393216, this.f25134e.f25165r, (NptSHlsLogger) this.f25134e.f25171x, 0));
            }
        }
    }

    private static class Dimensions {
        public int f25136a = -1;
        public int f25137b = -1;
        public int f25138c = -1;
        public int f25139d = -1;

        Dimensions() {
        }

        boolean m26234a() {
            return this.f25136a >= 0 && this.f25137b >= 0 && this.f25138c >= 0 && this.f25139d >= 0;
        }
    }

    private class TexLis implements SurfaceTextureListener {
        final /* synthetic */ ExoPlayerPlaybackWrapper f25140a;

        private TexLis(ExoPlayerPlaybackWrapper exoPlayerPlaybackWrapper) {
            this.f25140a = exoPlayerPlaybackWrapper;
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            Log.b(ExoPlayerPlaybackWrapper.f25142l, "TexLis.onSurfaceTextureAvailable (" + i + "x" + i2 + ")");
            this.f25140a.f25164q.f25136a = i;
            this.f25140a.f25164q.f25137b = i2;
            this.f25140a.f25162o = surfaceTexture;
            this.f25140a.m26265q();
            this.f25140a.m26267s();
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            Log.b(ExoPlayerPlaybackWrapper.f25142l, "onSurfaceTextureSizeChanged (" + i + "x" + i2 + ")");
            this.f25140a.f25164q.f25136a = i;
            this.f25140a.f25164q.f25137b = i2;
            this.f25140a.m26265q();
            this.f25140a.m26267s();
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            Log.b(ExoPlayerPlaybackWrapper.f25142l, "onSurfaceTextureDestroyed");
            this.f25140a.m26283f(true);
            return true;
        }

        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            this.f25140a.f25154L = this.f25140a.f25154L + 1;
        }
    }

    public ExoPlayerPlaybackWrapper(Context context, Callback callback, boolean z) {
        super(context, callback);
        this.f25167t = z;
        this.f25160m = MagicNetwork.a().a("playback");
        ReferenceMonitor.a().a(this);
    }

    protected void mo6981h() {
        QueueItem l = m24714l();
        if (this.f25143A) {
            this.f25163p = l.m24726a(this.f25167t);
        } else {
            if (this.f25167t) {
                this.f25163p = l.m24734d();
            }
            if (this.f25163p == null) {
                this.f25163p = l.m24733c();
            }
        }
        Log.b(f25142l, "source: " + this.f25163p + ", mediaId: " + m24713k());
        this.f25165r = new Handler(Looper.getMainLooper());
        mo6974a(this.f25161n);
        m26266r();
        m24692a(6);
    }

    public void m26280e(boolean z) {
        this.f25144B = z;
    }

    public void mo6974a(TextureView textureView) {
        Log.a(f25142l, "setVideoPlaybackView");
        if (this.f25161n == textureView) {
            Log.a(f25142l, "setVideoPlaybackView bailing cause they were the same");
            return;
        }
        if (this.f25161n != null) {
            Log.a(f25142l, "setVideoPlaybackView: had a previous surface");
            this.f25161n.setSurfaceTextureListener(null);
        }
        this.f25161n = textureView;
        this.f25162o = null;
        if (this.f25161n == null) {
            m26283f(true);
        } else {
            m26287n();
        }
    }

    protected void m26287n() {
        if (this.f25161n != null) {
            Log.a(f25142l, "configureTextureView");
            Object texLis = new TexLis();
            this.f25161n.setSurfaceTextureListener(texLis);
            if (this.f25161n != null && this.f25161n.isAvailable()) {
                texLis.onSurfaceTextureAvailable(this.f25161n.getSurfaceTexture(), this.f25161n.getWidth(), this.f25161n.getHeight());
            }
        }
    }

    public void mo6973a(long j) {
        Log.b(f25142l, "Seeking:" + j);
        if (this.f25168u == null) {
            Log.e(f25142l, "seekTo: mExoPlayer was null. This is probably a timing error");
            return;
        }
        this.f25168u.mo4246a(this.f25166s);
        this.f25168u.mo4243a(j);
    }

    public void mo6971a() {
        if (this.f25168u == null) {
            Log.b(f25142l, "start tried to run before ExoPlayer was created.");
            this.i = true;
            return;
        }
        Log.a(f25142l, "start+ state: " + MediaPlaybackUtils.m25873b(this.f25168u.mo4240a()));
        if (this.f25168u.mo4240a() == 5) {
            mo6973a(0);
            this.f25168u.mo4246a(true);
        } else {
            m24692a(8);
            this.f25168u.mo4246a(true);
        }
        this.f25166s = true;
        this.f25171x.mo7014b();
        if (this.f25143A && !this.f25149G) {
            m26272x();
        }
        Log.a(f25142l, "start-");
    }

    public void mo6982i() {
        if (this.f25168u == null) {
            Log.b(f25142l, "pause tried to run before ExoPlayer was created.");
            this.i = false;
            return;
        }
        Log.b(f25142l, "pause+");
        this.f25166s = false;
        this.f25168u.mo4246a(false);
        Log.b(f25142l, "pause-");
    }

    public void mo6975a(boolean z) {
        Log.b(f25142l, "stop+");
        if (this.f25168u != null) {
            this.f25168u.mo4251c();
        }
        this.f25166s = false;
        this.f25148F = 0;
        m24692a(1);
        Log.b(f25142l, "stop-");
    }

    public void mo6972a(float f) {
        if (this.f25149G) {
            f = 0.0f;
        }
        if (this.f25168u != null) {
            this.f25168u.mo4244a(this.f25170w, 1, Float.valueOf(f));
        }
    }

    public void mo6976b() {
        Log.a(f25142l, "release");
        if (this.f25168u != null) {
            Log.b(f25142l, "shutdown for realsies");
            this.f25166s = false;
            this.f25148F = 0;
            m26283f(true);
            m26271w();
            this.f25168u.mo4251c();
            this.f25168u.mo4252d();
            Log.b(f25142l, "release:" + this + " " + this.f25168u);
            this.f25168u = null;
            Log.b(f25142l, "duration:" + this.f25150H + " drawn:" + this.f25154L);
            this.f25171x.mo7011a(this.f25147E.m11279d(), 5);
        }
        m24692a(0);
    }

    public long mo6979f() {
        return this.f25150H;
    }

    public long mo6980g() {
        return this.f25168u != null ? this.f25168u.mo4254f() : 0;
    }

    public boolean mo6977d() {
        return this.f25151I == 4 && this.f25166s;
    }

    public boolean mo6978e() {
        return this.f25151I == 5;
    }

    private void m26265q() {
        Log.a(f25142l, "scaleForAspect");
        if (this.f25164q.m26234a() && this.f25161n != null) {
            int i;
            int i2;
            int i3 = this.f25164q.f25138c;
            int i4 = this.f25164q.f25139d;
            int i5 = this.f25164q.f25136a;
            int i6 = this.f25164q.f25137b;
            double d = ((double) i4) / ((double) i3);
            if (i6 > ((int) (((double) i5) * d))) {
                i = (int) (((double) i6) / d);
                i2 = i6;
            } else {
                i2 = (int) (d * ((double) i5));
                i = i5;
            }
            int i7 = (i5 - i) / 2;
            Log.a(f25142l, "scaleForAspect: video=" + i3 + "x" + i4 + " view=" + i5 + "x" + i6 + " newView=" + i + "x" + i2 + " off=" + i7 + "," + 0);
            Matrix matrix = new Matrix();
            this.f25161n.getTransform(matrix);
            matrix.setScale(((float) i) / ((float) i5), ((float) i2) / ((float) i6));
            matrix.postTranslate((float) i7, (float) null);
            this.f25161n.setTransform(matrix);
        }
    }

    private void m26266r() {
        Log.b(f25142l, "play:mMediaUrl:" + this.f25163p);
        if (this.f25163p == null) {
            throw new RuntimeException("mMediaUrl not found");
        } else if (this.f25163p.endsWith("m3u8")) {
            m26270v();
        } else {
            m26269u();
        }
    }

    private void m26267s() {
        Log.b(f25142l, "videoInit+");
        if (this.f25169v != null) {
            if (this.f25146D != null) {
                this.f25146D.release();
            }
            this.f25146D = this.f25162o != null ? new Surface(this.f25162o) : null;
            Log.b(f25142l, "videoInit - mSurface: " + (this.f25146D != null ? "valid surface" : "null"));
            if (this.f25168u != null) {
                if (this.f25146D != null) {
                    if (m24703c() == 3 && this.f25168u.mo4250b()) {
                        Log.b(f25142l, "setTextureView setState STATE_BUFFERING");
                        m24692a(6);
                    }
                    this.f25168u.mo4249b(this.f25169v, 1, this.f25146D);
                }
                this.f25168u.mo4248b(this.f25172y, this.f25162o != null ? 0 : -1);
            }
            Log.b(f25142l, "videoInit-");
        }
    }

    public void m26283f(boolean z) {
        Log.b(f25142l, "clearSurface+");
        if (this.f25161n != null) {
            this.f25161n.setSurfaceTextureListener(null);
        }
        if (!(this.f25168u == null || this.f25169v == null)) {
            if (z) {
                this.f25168u.mo4248b(this.f25172y, -1);
            }
            this.f25168u.mo4249b(this.f25169v, 1, null);
        }
        if (this.f25146D != null) {
            this.f25146D.release();
            this.f25146D = null;
        }
        m24701b(mo6980g());
        Log.b(f25142l, "clearSurface-");
    }

    private void m26268t() {
        Log.a(f25142l, "playerReady");
        if (m24703c() == 6) {
            if (this.f25168u.mo4250b()) {
                m24692a(3);
            } else {
                m24692a(2);
            }
        } else if (this.f25168u.mo4250b()) {
            m24692a(3);
        } else if (this.f25166s) {
            m24692a(3);
        } else {
            m24692a(2);
        }
    }

    public static HttpProxyCacheServer m26238a(Context context) {
        if (f25141j == null) {
            f25141j = new Builder(context.getApplicationContext()).a(new File(context.getCacheDir(), "videoproxy")).a((long) Constants.TEN_MB).a(60000).a(MagicNetwork.a().a("videoproxy")).a();
        }
        return f25141j;
    }

    public static void m26262o() {
        if (f25141j != null) {
            f25141j.a();
            f25141j = null;
        }
    }

    private void m26269u() {
        String a;
        DataSource defaultUriDataSource;
        Log.b(f25142l, "setupNormalPlayback");
        this.f25171x = new NptSLogger(this.f25163p);
        this.f25147E = new CounterBandwidthMeter(this.f25165r, this.f25171x);
        if (this.f25143A) {
            a = m26238a(this.g).a(this.f25163p);
            Log.b(f25142l, "Proxy URL: " + a);
            String a2 = Util.m11533a(this.g, "SingAndroid");
            defaultUriDataSource = new DefaultUriDataSource(this.g, null, new OkHttpDataSource(new OkHttpClient.Builder().m27800a(Proxy.NO_PROXY).m27801a(MagicNetwork.c).m27812b(), a2, null, null));
        } else {
            a = this.f25163p;
            defaultUriDataSource = new DefaultUriDataSource(this.g, this.f25147E, this.f25160m);
        }
        m26240a(new ExtractorSampleSource(Uri.parse(a), defaultUriDataSource, new DefaultAllocator(65536), 393216, new Extractor[0]));
    }

    private void m26270v() {
        Log.b(f25142l, "setupHlsPlayback");
        this.f25171x = new NptSHlsLogger(this.f25163p);
        this.f25147E = new CounterBandwidthMeter(this.f25165r, this.f25171x);
        this.f25157O = new AsyncRendererBuilder(this.g, this.f25160m, this.f25163p, this);
        this.f25157O.m26229a();
    }

    private void m26271w() {
        Log.b(f25142l, "cancelHlsSetup");
        if (this.f25157O != null) {
            this.f25157O.m26233b();
            this.f25157O = null;
        }
    }

    private void m26243a(Exception exception) {
        Log.e(f25142l, "onRenderersError:" + exception);
        this.f25171x.mo7012a(0, exception + " cause:" + exception.getCause());
    }

    private void m26240a(SampleSource sampleSource) {
        int i;
        Log.b(f25142l, "onSourceCreated");
        this.f25170w = new MediaCodecAudioTrackRenderer(this, sampleSource, MediaCodecSelector.f8041a, null, true, this.f25165r, this.f25156N, AudioCapabilities.m9960a(this.g), 3) {
            final /* synthetic */ ExoPlayerPlaybackWrapper f25124c;

            protected void mo6968b(int i) {
                super.mo6968b(i);
                this.f25124c.f25148F = i;
                this.f25124c.h.mo6890b(i);
            }
        };
        this.f25173z = 0;
        if (this.f25167t) {
            this.f25169v = new MediaCodecVideoTrackRenderer(this.g, sampleSource, MediaCodecSelector.f8041a, 1, 5000, null, true, this.f25165r, this.f25155M, 50);
            i = 2;
            this.f25172y = 1;
        } else {
            this.f25169v = null;
            this.f25172y = -1;
            i = 1;
        }
        if (this.f25168u != null) {
            this.f25166s = false;
            this.f25148F = 0;
            this.f25168u.mo4251c();
            this.f25168u.mo4252d();
        }
        this.f25168u = Factory.m9679a(i, 1000, DefaultRetryPolicy.DEFAULT_TIMEOUT_MS);
        this.f25168u.mo4246a(this.i);
        this.f25168u.mo4245a(this.f25153K);
        this.f25150H = -1;
        TrackRenderer[] trackRendererArr = new TrackRenderer[i];
        trackRendererArr[this.f25173z] = this.f25170w;
        if (this.f25167t) {
            this.f25168u.mo4248b(this.f25172y, this.f25162o != null ? 0 : -1);
            trackRendererArr[this.f25172y] = this.f25169v;
        }
        this.f25168u.mo4248b(this.f25173z, 0);
        this.f25168u.mo4247a(trackRendererArr);
        if (this.f25169v != null) {
            m26267s();
        }
        if (this.i) {
            mo6971a();
        } else {
            m24692a(2);
        }
    }

    private void m26272x() {
        this.f25159k.removeCallbacks(this.f25158P);
        this.f25158P.run();
    }
}
