/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.ads;

import com.smule.android.logging.Analytics;
import java.util.HashMap;

public abstract class MagicAd {
    private static final String e = MagicAd.class.getSimpleName();
    protected boolean a = true;
    protected AdStatus b = AdStatus.a;
    protected long c = 0;
    protected long d = 0;

    public Analytics a() {
        return null;
    }

    public void a(long l) {
        this.c = l;
    }

    public void a(AdStatus adStatus) {
        this.b = adStatus;
    }

    public void a(MagicAd magicAd) {
    }

    public void a(boolean bl) {
        this.a = bl;
    }

    public String b() {
        return null;
    }

    public void b(long l) {
        this.d = l;
    }

    public String c() {
        return null;
    }

    public int d() {
        return 10;
    }

    public HashMap<String, String> e() {
        return null;
    }

    public AdStatus f() {
        return this.b;
    }

    public boolean g() {
        return this.a;
    }

    public long h() {
        return this.c;
    }

    public long i() {
        return this.d;
    }

    public static enum AdStatus {
        a,
        b,
        c,
        d,
        e,
        f,
        g,
        h,
        i;
        

        private AdStatus() {
        }
    }

}

