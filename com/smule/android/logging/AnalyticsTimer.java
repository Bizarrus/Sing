/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.logging;

public class AnalyticsTimer {
    private long a;
    private long b;
    private long c;

    public void a() {
        if (this.a == 0) {
            this.a = System.currentTimeMillis();
        }
    }

    public void b() {
        this.b += this.c();
        this.a = 0;
    }

    public long c() {
        long l;
        long l2 = l = this.b;
        if (this.a > 0) {
            l2 = l + (System.currentTimeMillis() - this.a);
        }
        l = l2;
        if (l2 < this.c) {
            l = this.c;
            this.b = this.c;
            this.a = System.currentTimeMillis();
        }
        this.c = l;
        return l;
    }

    public double d() {
        return (double)this.c() / 1000.0;
    }
}

