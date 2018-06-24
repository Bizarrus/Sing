package com.smule.singandroid.video;

import android.media.MediaCodec.CryptoException;
import android.view.Surface;
import com.google.android.exoplayer.MediaCodecTrackRenderer.DecoderInitializationException;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer.EventListener;
import com.google.android.exoplayer.upstream.BandwidthMeter;
import com.mopub.common.Constants;

public abstract class NptSBaseLogger implements EventListener, BandwidthMeter.EventListener {
    protected final String f25345a;
    protected final boolean f25346b;
    protected long f25347c;
    protected double f25348d;
    protected long f25349e;

    public abstract void mo7005a(float f);

    public abstract void mo7011a(long j, int i);

    public abstract void mo7012a(long j, String str);

    public abstract void mo7014b();

    public NptSBaseLogger(String str) {
        this.f25345a = str;
        this.f25346b = !this.f25345a.contains(Constants.HTTP);
        mo7004a();
    }

    protected void mo7004a() {
        this.f25347c = 0;
        this.f25348d = 0.0d;
        this.f25349e = 0;
    }

    public void mo7003b(int i, long j, long j2) {
        this.f25347c++;
        this.f25348d += (double) j2;
    }

    public void mo7002a(long j) {
    }

    public void mo6960a(int i, long j) {
        this.f25349e++;
    }

    public void mo6959a(int i, int i2, int i3, float f) {
    }

    public void mo6962a(Surface surface) {
    }

    public void mo6963a(DecoderInitializationException decoderInitializationException) {
    }

    public void mo6961a(CryptoException cryptoException) {
    }

    public void mo6964a(String str, long j, long j2) {
    }
}
