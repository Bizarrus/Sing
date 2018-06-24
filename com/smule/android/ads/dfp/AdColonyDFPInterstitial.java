/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.os.Bundle
 *  com.jirbo.adcolony.AdColonyAd
 *  com.jirbo.adcolony.AdColonyAdListener
 *  com.jirbo.adcolony.AdColonyVideoAd
 */
package com.smule.android.ads.dfp;

import android.app.Activity;
import android.os.Bundle;
import com.jirbo.adcolony.AdColonyAd;
import com.jirbo.adcolony.AdColonyAdListener;
import com.jirbo.adcolony.AdColonyVideoAd;
import com.smule.android.ads.AdVendorManager;
import com.smule.android.ads.dfp.DFPCustomEventInterstitial;
import com.smule.android.ads.networks.AdColonyAdVendor;
import com.smule.android.ads.networks.AdVendor;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;

public class AdColonyDFPInterstitial
extends DFPCustomEventInterstitial {
    private static final String TAG = AdColonyDFPInterstitial.class.getName();
    private AdColonyVideoAd mAd;

    @Override
    public Analytics getAnalyticsDFPVendor() {
        return Analytics.a;
    }

    @Override
    public boolean isAdReady() {
        return this.mAd.f();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public boolean requestInterstitialAd(String[] object, Bundle bundle) {
        if (object.length == 0) {
            Log.e(TAG, "Wrong parameters received ");
            return false;
        }
        bundle = this.getActivity();
        if (bundle == null) {
            Log.e(TAG, "Activity was null");
            return false;
        }
        if ((AdColonyAdVendor)AdVendorManager.a().a((Activity)bundle, AdColonyAdVendor.class) == null) {
            Log.e(TAG, "AdColony not initialized before trying to show DFP interstitial");
            return false;
        }
        object = object.length >= 2 ? object[1] : null;
        this.mAd = new AdColonyVideoAd((String)object).a(new AdColonyAdListener(){

            public void a(AdColonyAd adColonyAd) {
                Log.b(TAG, "Attempt finished");
                Log.b(TAG, "canceled  " + adColonyAd.c());
                Log.b(TAG, "shown     " + adColonyAd.a());
                Log.b(TAG, "no fill   " + adColonyAd.d());
                Log.b(TAG, "not shown " + adColonyAd.b());
                Log.b(TAG, "skipped   " + adColonyAd.e());
                AdColonyDFPInterstitial.this.onAdClosed();
            }

            public void b(AdColonyAd adColonyAd) {
                AdColonyDFPInterstitial.this.onAdOpened();
            }
        });
        return true;
    }

    @Override
    public void showAd() {
        this.mAd.j();
    }

}

