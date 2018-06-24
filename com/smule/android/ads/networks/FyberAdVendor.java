/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  com.fyber.Fyber
 *  com.fyber.Fyber$Settings
 *  com.fyber.ads.AdFormat
 *  com.fyber.ads.interstitials.InterstitialAdCloseReason
 *  com.fyber.requesters.OfferWallRequester
 *  com.fyber.requesters.RequestCallback
 *  com.fyber.requesters.RequestError
 *  com.fyber.utils.FyberLogger
 */
package com.smule.android.ads.networks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.fyber.Fyber;
import com.fyber.ads.AdFormat;
import com.fyber.ads.interstitials.InterstitialAdCloseReason;
import com.fyber.requesters.OfferWallRequester;
import com.fyber.requesters.RequestCallback;
import com.fyber.requesters.RequestError;
import com.fyber.utils.FyberLogger;
import com.smule.android.ads.AdVendorManager;
import com.smule.android.ads.networks.AdVendor;
import com.smule.android.logging.Analytics;
import com.smule.android.network.managers.UserManager;
import java.io.Serializable;

public class FyberAdVendor
extends AdVendor {
    private static final String c = FyberAdVendor.class.getName();
    String a;
    String b;

    public FyberAdVendor(String string2, String string3) {
        this.a = string2;
        this.b = string3;
    }

    @Override
    public Analytics a() {
        return Analytics.b;
    }

    @Override
    public void a(Activity activity) {
        Fyber.a((String)this.a, (Activity)activity).a(String.valueOf(UserManager.a().g())).b(this.b).b();
    }

    @Override
    protected void a(final Activity activity, final AdVendor.ShowAdCallbackInterface showAdCallbackInterface) {
        OfferWallRequester.a((RequestCallback)new RequestCallback(){

            public void a(Intent intent) {
                showAdCallbackInterface.a(FyberAdVendor.this);
                activity.startActivityForResult(intent, 2114);
            }

            public void a(AdFormat adFormat) {
                showAdCallbackInterface.c(FyberAdVendor.this);
            }

            public void a(RequestError requestError) {
                showAdCallbackInterface.d(FyberAdVendor.this);
            }
        }).a(true).a(activity.getApplicationContext());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean a(Context object, int n, int n2, Intent intent) {
        block6 : {
            block5 : {
                if (n != 2114) {
                    return false;
                }
                if (n2 != -1 || intent == null) break block5;
                InterstitialAdCloseReason interstitialAdCloseReason = (InterstitialAdCloseReason)intent.getSerializableExtra("AD_STATUS");
                FyberLogger.b((String)c, (String)("SPInterstitial closed with status - " + (Object)interstitialAdCloseReason));
                if (!interstitialAdCloseReason.equals((Object)InterstitialAdCloseReason.d)) break block6;
                object = intent.getStringExtra("ERROR_MESSAGE");
                FyberLogger.b((String)c, (String)("SPInterstitial closed and error - " + (String)object));
            }
            do {
                return true;
                break;
            } while (true);
        }
        AdVendorManager.a().a((Context)object);
        return true;
    }

    @Override
    public boolean a(AdVendor.AdType adType) {
        switch (adType) {
            default: {
                return false;
            }
            case a: 
        }
        return true;
    }

    @Override
    public void b(Activity activity) {
    }

}

