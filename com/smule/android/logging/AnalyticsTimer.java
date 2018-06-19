package com.smule.android.logging;

public class AnalyticsTimer {
    private long f16326a;
    private long f16327b;
    private long f16328c;

    public void m17909a() {
        if (this.f16326a == 0) {
            this.f16326a = System.currentTimeMillis();
        }
    }

    public void m17910b() {
        this.f16327b += m17911c();
        this.f16326a = 0;
    }

    public long m17911c() {
        long j = this.f16327b;
        if (this.f16326a > 0) {
            j += System.currentTimeMillis() - this.f16326a;
        }
        if (j < this.f16328c) {
            j = this.f16328c;
            this.f16327b = this.f16328c;
            this.f16326a = System.currentTimeMillis();
        }
        this.f16328c = j;
        return j;
    }

    public double m17912d() {
        return ((double) m17911c()) / 1000.0d;
    }
}
