/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  com.jirbo.adcolony.AdColony
 *  com.jirbo.adcolony.AdColonyAd
 *  com.jirbo.adcolony.AdColonyAdAvailabilityListener
 *  com.jirbo.adcolony.AdColonyAdListener
 *  com.jirbo.adcolony.AdColonyV4VCAd
 */
package com.smule.android.ads.networks;

import android.app.Activity;
import android.content.Context;
import com.jirbo.adcolony.AdColony;
import com.jirbo.adcolony.AdColonyAd;
import com.jirbo.adcolony.AdColonyAdAvailabilityListener;
import com.jirbo.adcolony.AdColonyAdListener;
import com.jirbo.adcolony.AdColonyV4VCAd;
import com.smule.android.ads.networks.AdVendor;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.MagicDevice;

public class AdColonyAdVendor
extends AdVendor {
    private static final String e = AdColonyAdVendor.class.getName();
    String a;
    String b;
    String c;
    String[] d;
    private volatile boolean f = false;
    private AdColonyAdAvailabilityListener g;

    public AdColonyAdVendor(String string2, String string3, String string4, String[] arrstring) {
        this.g = new AdColonyAdAvailabilityListener(){

            public void a(boolean bl, String string2) {
                Log.b(e, "onAdColonyAdAvailabilityChange zone: " + string2 + " available: " + bl);
                if (AdColonyAdVendor.this.c != null && AdColonyAdVendor.this.c.equalsIgnoreCase(string2)) {
                    Log.b(e, "onAdColonyAdAvailabilityChange reward video available: " + bl);
                    if (bl) {
                        AdColonyAdVendor.this.f = false;
                    }
                    AdColonyAdVendor.this.d();
                }
            }
        };
        this.a = string2;
        this.b = string3;
        this.c = string4;
        this.d = arrstring;
    }

    @Override
    public Analytics a() {
        return Analytics.a;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void a(Activity activity) {
        Log.b(e, "Initializing AdColony");
        AdColony.a((Activity)activity, (String)this.b, (String)this.a, (String[])this.d);
        long l = UserManager.a().g();
        if (l != 0) {
            AdColony.a((String)String.valueOf(l));
        } else {
            Log.e(e, "Player id is null when configuring AdColony");
        }
        AdColony.b((String)MagicDevice.a((Context)activity));
        AdColony.a((AdColonyAdAvailabilityListener)this.g);
    }

    @Override
    protected void a(Activity activity, Object object, final AdVendor.ShowAdCallbackInterface showAdCallbackInterface) {
        Log.a(e, "showRewardVideo");
        new AdColonyV4VCAd(this.c).a(new AdColonyAdListener(){

            public void a(AdColonyAd adColonyAd) {
                Log.b(e, "AdColony video finished");
                if (adColonyAd.a()) {
                    showAdCallbackInterface.b(AdColonyAdVendor.this);
                    return;
                }
                if (adColonyAd.d()) {
                    AdColonyAdVendor.this.f = true;
                    showAdCallbackInterface.c(AdColonyAdVendor.this);
                    return;
                }
                if (adColonyAd.c() || adColonyAd.e()) {
                    showAdCallbackInterface.e(AdColonyAdVendor.this);
                    return;
                }
                showAdCallbackInterface.d(AdColonyAdVendor.this);
            }

            public void b(AdColonyAd adColonyAd) {
                Log.b(e, "AdColony video started");
                showAdCallbackInterface.a(AdColonyAdVendor.this);
            }
        }).n();
    }

    @Override
    public boolean a(AdVendor.AdType adType) {
        switch (.a[adType.ordinal()]) {
            default: {
                return false;
            }
            case 2: 
        }
        return true;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void b(Activity var1_1) {
        block17 : {
            Log.a(AdColonyAdVendor.e, "actuallyPreCacheRewardVideo");
            var1_1 = AdColony.e((String)this.c);
            var2_2 = -1;
            switch (var1_1.hashCode()) {
                case -1422950650: {
                    if (var1_1.equals("active")) {
                        var2_2 = 0;
                        ** break;
                    }
                    ** GOTO lbl23
                }
                case 336650556: {
                    if (var1_1.equals("loading")) {
                        var2_2 = 1;
                        ** break;
                    }
                    ** GOTO lbl23
                }
                case -284840886: {
                    if (var1_1.equals("unknown")) {
                        var2_2 = 2;
                        ** break;
                    }
                    ** GOTO lbl23
                }
                case 1959784951: {
                    if (var1_1.equals("invalid")) {
                        var2_2 = 3;
                    }
                }
lbl23: // 10 sources:
                default: {
                    break block17;
                }
                case 109935: 
            }
            if (var1_1.equals("off")) {
                var2_2 = 4;
            }
        }
        switch (var2_2) {
            default: {
                this.e();
            }
            case 2: {
                return;
            }
            case 0: {
                this.d();
                return;
            }
            case 1: 
        }
        if (this.f == false) return;
        this.d();
    }

}

