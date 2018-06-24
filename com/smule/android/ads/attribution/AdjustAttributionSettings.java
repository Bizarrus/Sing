/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  com.adjust.sdk.Adjust
 *  com.adjust.sdk.AdjustEvent
 */
package com.smule.android.ads.attribution;

import android.content.Context;
import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustEvent;
import com.smule.android.ads.attribution.AdjustAttributionSettings;
import com.smule.android.ads.attribution.AdjustInstallReferrerLogger;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.NotificationCenter;
import java.util.Observer;

public class AdjustAttributionSettings {
    private static AdjustAttributionSettings h;
    private String a;
    private String b;
    private long[] c;
    private String d;
    private String e;
    private String f;
    private AdjustInstallReferrerLogger g;

    public AdjustAttributionSettings(String string2, String string3) {
        if (h != null) {
            throw new RuntimeException("AdjustAttributionSettings should only be created once");
        }
        this.a = string2;
        this.b = string3;
        NotificationCenter.a().a("USER_LOGGED_IN_EVENT", new Observer(this){
            final /* synthetic */ AdjustAttributionSettings a;
            {
                this.a = adjustAttributionSettings;
            }

            public void update(java.util.Observable observable, Object object) {
                AdjustAttributionSettings.a(this.a);
            }
        });
        this.i();
        h = this;
    }

    static /* synthetic */ void a(AdjustAttributionSettings adjustAttributionSettings) {
        adjustAttributionSettings.i();
    }

    static /* varargs */ void a(String string2, String ... arrstring) {
        string2 = new AdjustEvent(string2);
        int n = 0;
        do {
            if (n >= arrstring.length) break;
            string2.addCallbackParameter(arrstring[n], arrstring[n + 1]);
            n += 2;
        } while (true);
        try {
            Adjust.trackEvent((AdjustEvent)string2);
            return;
        }
        catch (Exception exception) {
            MagicCrittercism.a(exception);
            return;
        }
    }

    public static void g() {
        if (h != null) {
            String string2 = h.d();
            if (string2 == null) {
                throw new IllegalArgumentException("missing register token");
            }
            AdjustAttributionSettings.a(string2, new String[0]);
        }
    }

    public static void h() {
        if (h != null) {
            String string2 = h.c();
            if (string2 == null) {
                throw new IllegalArgumentException("missing purchase token");
            }
            AdjustAttributionSettings.a(string2, new String[0]);
        }
    }

    private void i() {
        UserManager userManager = UserManager.a();
        long l = userManager.f();
        if (l > 0) {
            Adjust.addSessionCallbackParameter((String)"sacctid", (String)String.valueOf(l));
        }
        if ((l = userManager.g()) > 0) {
            Adjust.addSessionCallbackParameter((String)"splyrid", (String)String.valueOf(l));
        }
    }

    public String a() {
        return this.a;
    }

    public void a(String string2) {
        this.e = string2;
    }

    public void a(String string2, Context context) {
        this.g = new AdjustInstallReferrerLogger(string2, context);
    }

    public void a(long[] arrl) {
        if (arrl != null && arrl.length != 5) {
            throw new IllegalArgumentException("invalid length");
        }
        this.c = arrl;
    }

    public String b() {
        return this.b;
    }

    public void b(String string2) {
        this.d = string2;
    }

    public String c() {
        return this.e;
    }

    public String d() {
        return this.d;
    }

    public long[] e() {
        return this.c;
    }

    public String f() {
        return this.f;
    }
}

