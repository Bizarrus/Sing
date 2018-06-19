package com.smule.android.ads.attribution;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustEvent;
import com.smule.android.logging.MagicCrittercism;

public class AdjustAttributionSettings {
    static AdjustAttributionSettings f6667e;
    String f6668a;
    String f6669b;
    String f6670c;
    String f6671d;

    public AdjustAttributionSettings(String str, String str2) {
        if (f6667e != null) {
            throw new RuntimeException("AdjustAttributionSettings should only be created once");
        }
        this.f6668a = str;
        this.f6669b = str2;
        f6667e = this;
    }

    public String m7656a() {
        return this.f6668a;
    }

    public String m7658b() {
        return this.f6669b;
    }

    public String m7660c() {
        if (this.f6671d != null) {
            return this.f6671d;
        }
        throw new RuntimeException("mPurchaseToken must not be null");
    }

    public void m7657a(String str) {
        this.f6671d = str;
    }

    public String m7661d() {
        if (this.f6670c != null) {
            return this.f6670c;
        }
        throw new RuntimeException("mRegisterToken must not be null");
    }

    public void m7659b(String str) {
        this.f6670c = str;
    }

    public static void m7654e() {
        if (f6667e != null) {
            m7653c(f6667e.m7661d());
        }
    }

    public static void m7655f() {
        if (f6667e != null) {
            m7653c(f6667e.m7660c());
        }
    }

    private static void m7653c(String str) {
        try {
            Adjust.m1517a(new AdjustEvent(str));
        } catch (Throwable e) {
            MagicCrittercism.m7780a(e);
        }
    }
}
