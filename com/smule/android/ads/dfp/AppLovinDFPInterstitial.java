/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.os.Bundle
 *  com.applovin.adview.AppLovinInterstitialAd
 *  com.applovin.adview.AppLovinInterstitialAdDialog
 *  com.applovin.sdk.AppLovinAd
 *  com.applovin.sdk.AppLovinAdClickListener
 *  com.applovin.sdk.AppLovinAdDisplayListener
 *  com.applovin.sdk.AppLovinSdk
 */
package com.smule.android.ads.dfp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinSdk;
import com.smule.android.ads.AdVendorManager;
import com.smule.android.ads.dfp.DFPCustomEventInterstitial;
import com.smule.android.ads.networks.AdVendor;
import com.smule.android.ads.networks.AppLovinAdVendor;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;

public class AppLovinDFPInterstitial
extends DFPCustomEventInterstitial {
    private static final String TAG = AppLovinDFPInterstitial.class.getName();
    AppLovinInterstitialAdDialog mAd;
    String mZoneId;

    @Override
    public Analytics getAnalyticsDFPVendor() {
        return Analytics.b;
    }

    @Override
    public boolean isAdReady() {
        return this.mAd.b();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public boolean requestInterstitialAd(String[] activity, Bundle bundle) {
        activity = this.getActivity();
        if (activity == null) {
            Log.e(TAG, "Activity was null");
            return false;
        }
        AppLovinAdVendor appLovinAdVendor = (AppLovinAdVendor)AdVendorManager.a().a(activity, AppLovinAdVendor.class);
        if (appLovinAdVendor == null) {
            Log.e(TAG, "AppLovin not initialized before trying to show DFP interstitial");
            return false;
        }
        if (bundle != null) {
            this.mZoneId = bundle.getString("ZONE_ID_BUNDLE_KEY");
            Log.b(TAG, "AppLovin ad placecment: " + this.mZoneId);
        } else {
            Log.e(TAG, "Bundle was null. Make sure to call addCustomEventExtrasBundle for my sub classes");
        }
        this.mAd = AppLovinInterstitialAd.a((AppLovinSdk)appLovinAdVendor.a((Context)activity), (Activity)activity);
        this.mAd.a(new AppLovinAdDisplayListener(){

            public void adDisplayed(AppLovinAd appLovinAd) {
                AppLovinDFPInterstitial.this.onAdOpened();
            }

            public void adHidden(AppLovinAd appLovinAd) {
                AppLovinDFPInterstitial.this.onAdClosed();
            }
        });
        this.mAd.a(new AppLovinAdClickListener(){

            public void adClicked(AppLovinAd appLovinAd) {
                AppLovinDFPInterstitial.this.mAd.c();
            }
        });
        return true;
    }

    @Override
    public void showAd() {
        this.mAd.a(this.mZoneId);
    }

}

