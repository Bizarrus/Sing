package com.smule.singandroid.video;

import android.support.annotation.NonNull;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.hls.HlsSampleSource.EventListener;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.EventLogger2$ErrorDomain;
import com.smule.android.logging.Log;
import java.io.IOException;
import java.util.HashMap;

public class NptSHlsLogger extends NptSBaseLogger implements EventListener {
    public static final String f25358f = NptSHlsLogger.class.getSimpleName();
    private int f25359g;
    private long f25360h;
    private final HashMap<Integer, ChunkStats> f25361i = new HashMap();
    private int f25362j = 0;

    private static class ChunkStats {
        public int f25350a;
        public long f25351b;
        public double f25352c;
        public long f25353d;
        public long f25354e;
        public long f25355f;
        public long f25356g;
        public long f25357h;

        private ChunkStats() {
        }

        public String toString() {
            return "[" + this.f25350a + "]" + "numSeen:" + this.f25351b + " bitrate:" + this.f25352c + " numBytes:" + this.f25353d + " downloadDurationMs:" + this.f25354e + " loadStart:" + this.f25355f + " displayStart:" + this.f25356g + " displayEnd:" + this.f25357h;
        }
    }

    public NptSHlsLogger(String str) {
        super(str);
    }

    protected void mo7004a() {
        super.mo7004a();
        this.f25359g = 5;
        this.f25360h = 0;
    }

    @NonNull
    private ChunkStats m26499a(int i) {
        ChunkStats chunkStats = (ChunkStats) this.f25361i.get(Integer.valueOf(i));
        if (chunkStats != null) {
            return chunkStats;
        }
        chunkStats = new ChunkStats();
        chunkStats.f25350a = i;
        this.f25361i.put(Integer.valueOf(i), chunkStats);
        return chunkStats;
    }

    @NonNull
    private ChunkStats m26500a(Format format) {
        if (format != null) {
            return m26499a(format.f8286d * format.f8287e);
        }
        return m26499a(0);
    }

    public void mo7006a(int i, long j, int i2, int i3, Format format, long j2, long j3) {
        ChunkStats a = m26500a(format);
        Log.b(f25358f, "onLoadStarted:" + a);
        if (a.f25355f == 0) {
            a.f25355f = System.currentTimeMillis();
        }
    }

    public void mo7007a(int i, long j, int i2, int i3, Format format, long j2, long j3, long j4, long j5) {
        ChunkStats a = m26500a(format);
        a.f25351b++;
        a.f25352c = (double) (format != null ? format.f8285c : 0);
        a.f25353d += j;
        a.f25354e += j5;
        Log.b(f25358f, "onLoadCompleted:" + a);
    }

    public void a_(int i, long j) {
    }

    public void mo7010a(int i, IOException iOException) {
    }

    public void mo7008a(int i, long j, long j2) {
    }

    public void mo7009a(int i, Format format, int i2, long j) {
    }

    public void mo6959a(int i, int i2, int i3, float f) {
        ChunkStats a;
        if (this.f25362j != 0) {
            a = m26499a(this.f25362j);
            a.f25357h = System.currentTimeMillis();
            Log.b(f25358f, "onVideoSizeChanged:old:" + a);
            m26512a(a);
        }
        this.f25362j = i * i2;
        a = m26499a(this.f25362j);
        a.f25356g = System.currentTimeMillis();
        Log.b(f25358f, "onVideoSizeChanged:new:" + a);
    }

    public void mo7011a(long j, int i) {
        if (i != 4) {
            if (i == 5) {
                ChunkStats a = m26499a(this.f25362j);
                a.f25357h = System.currentTimeMillis();
                m26512a(a);
            } else if (i == 3 && this.f25359g == 4) {
                this.f25360h++;
            }
        }
        this.f25359g = i;
    }

    public void mo7012a(long j, String str) {
        ChunkStats a = m26499a(this.f25362j);
        a.f25357h = System.currentTimeMillis();
        m26501a(a, EventLogger2$ErrorDomain.PLATFORM, -1, f25358f, str);
    }

    public void m26512a(ChunkStats chunkStats) {
        m26501a(chunkStats, EventLogger2$ErrorDomain.NONE, 0, null, null);
    }

    private void m26501a(ChunkStats chunkStats, EventLogger2$ErrorDomain eventLogger2$ErrorDomain, int i, String str, String str2) {
        long j;
        long j2;
        long j3;
        if (chunkStats.f25355f <= 0 || chunkStats.f25356g <= 0 || chunkStats.f25356g < chunkStats.f25355f) {
            j = -1;
        } else {
            j = chunkStats.f25356g - chunkStats.f25355f;
        }
        if (this.d == 0.0d || this.c == 0) {
            j2 = -1;
        } else {
            j2 = (long) (this.d / ((double) this.c));
        }
        if (chunkStats.f25356g <= 0 || chunkStats.f25357h <= 0 || chunkStats.f25357h < chunkStats.f25356g) {
            j3 = -1;
        } else {
            j3 = chunkStats.f25357h - chunkStats.f25356g;
        }
        EventLogger2.a(this.a, j, (long) chunkStats.f25352c, j2, eventLogger2$ErrorDomain, i, str, str2, j3, this.f25360h, chunkStats.f25353d, this.e);
        mo7004a();
    }

    public void mo7014b() {
    }

    public void mo7005a(float f) {
    }
}
