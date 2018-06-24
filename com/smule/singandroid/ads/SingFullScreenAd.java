/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 */
package com.smule.singandroid.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.smule.android.ads.MagicAd;
import com.smule.android.ads.MagicAdSettings;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.singandroid.ads.SingAdSettings;
import java.util.HashMap;

public class SingFullScreenAd
extends MagicAd {
    private static final String j = SingFullScreenAd.class.getSimpleName();
    protected SingAdSettings.FullScreenAdPlacement e;
    protected String f;
    protected String g = "";
    protected HashMap<String, String> h;
    protected int i = MagicAdSettings.e();

    public SingFullScreenAd(@NonNull SingAdSettings.FullScreenAdPlacement fullScreenAdPlacement, @Nullable String string2) {
        this.e = fullScreenAdPlacement;
        this.f = SingAdSettings.d(fullScreenAdPlacement);
        this.g = string2;
        this.h = SingAdSettings.b(null);
        this.i = SingAdSettings.b(fullScreenAdPlacement);
    }

    @Override
    public Analytics a() {
        return this.e;
    }

    @Override
    public void a(MagicAd.AdStatus adStatus) {
        Log.b(j, "FULLSCREEN_AD: status: " + (Object)((Object)this.b) + " -> " + (Object)((Object)adStatus));
        super.a(adStatus);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(MagicAd magicAd) {
        Analytics analyticsType = magicAd.a();
        if (analyticsType == null || analyticsType.a() == null) {
            Log.e(j, "SingAdSettings.FullScreenAdPlacement - attempt to create a value from null string");
            this.e = null;
        } else {
            this.e = com.smule.android.logging.Analytics.a(SingAdSettings.FullScreenAdPlacement.class, analyticsType.a());
        }
        if (this.e == null) {
            Log.e(j, "SingAdSettings.FullScreenAdPlacement does not have enum value: " + analyticsType);
        }
        this.g = magicAd.c();
        this.h = magicAd.e();
        this.i = magicAd.d();
    }

    @Override
    public String b() {
        return this.f;
    }

    @Override
    public String c() {
        return this.g;
    }

    @Override
    public int d() {
        return this.i;
    }

    @Override
    public HashMap<String, String> e() {
        return this.h;
    }
}

