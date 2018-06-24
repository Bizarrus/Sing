package com.smule.singandroid.video;

import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.EventLogger2$ErrorDomain;
import com.smule.android.logging.Log;

public class NptSLogger extends NptSBaseLogger {
    public static final String f25363f = NptSLogger.class.getSimpleName();
    private int f25364g;
    private long f25365h;
    private long f25366i;
    private long f25367j;
    private long f25368k = -1;
    private float f25369l = -1.0f;
    private long f25370m;

    public NptSLogger(String str) {
        super(str);
        mo7004a();
    }

    protected void mo7004a() {
        this.f25364g = 5;
        this.f25365h = -1;
        this.f25366i = -1;
        this.f25367j = -1;
        this.f25370m = 0;
    }

    public void mo7014b() {
        if (this.f25365h == -1) {
            Log.b(f25363f, "npt_s+");
            this.f25365h = System.currentTimeMillis();
        }
    }

    public void mo7005a(float f) {
        if (this.f25369l == -1.0f) {
            this.f25369l = f;
        }
    }

    public void mo7011a(long j, int i) {
        if (i == 4) {
            if (this.f25366i == -1) {
                this.f25366i = System.currentTimeMillis();
            }
        } else if (i == 5) {
            m26520b(j);
        } else if (i == 3 && this.f25364g == 4) {
            this.f25370m++;
        }
        this.f25364g = i;
    }

    public void mo7012a(long j, String str) {
        m26514a(j, EventLogger2$ErrorDomain.PLATFORM, -1, f25363f, str);
    }

    public void m26520b(long j) {
        m26514a(j, EventLogger2$ErrorDomain.NONE, 0, null, null);
    }

    private void m26514a(long j, EventLogger2$ErrorDomain eventLogger2$ErrorDomain, int i, String str, String str2) {
        if (!this.b) {
            if (eventLogger2$ErrorDomain != EventLogger2$ErrorDomain.NONE || (this.f25366i >= 0 && this.f25365h >= 0)) {
                long j2;
                long j3;
                long j4;
                long j5;
                Log.b(f25363f, "npt_s-");
                this.f25367j = System.currentTimeMillis();
                if (this.f25366i < 0 || this.f25365h < 0) {
                    j2 = -1;
                } else {
                    j2 = this.f25366i - this.f25365h;
                }
                if (this.f25368k <= 0 || this.f25369l <= 0.0f) {
                    j3 = -1;
                } else {
                    j3 = (long) (((float) (this.f25368k * 8)) / this.f25369l);
                }
                if (this.d == 0.0d || this.c == 0) {
                    j4 = -1;
                } else {
                    j4 = (long) (this.d / ((double) this.c));
                }
                if (this.f25367j < 0 || this.f25366i < 0) {
                    j5 = -1;
                } else {
                    j5 = this.f25367j - this.f25366i;
                }
                EventLogger2.a(this.a, j2, j3, j4, eventLogger2$ErrorDomain, i, str, str2, j5, this.f25370m, j, this.e);
                mo7004a();
                return;
            }
            Log.b(f25363f, "Not sending NPT_S event; playback not initialized");
        }
    }
}
