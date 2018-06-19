package com.smule.android.ads.dfp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.smule.android.ads.AdVendorManager;
import com.smule.android.ads.networks.AppLovinAdVendor;
import com.smule.android.logging.Analytics.DFPVendor;
import com.smule.android.logging.Log;

public class AppLovinDFPInterstitial extends DFPCustomEventInterstitial {
    private static final String TAG = AppLovinDFPInterstitial.class.getName();
    AppLovinInterstitialAdDialog mAd;
    String mZoneId;

    class C34911 implements AppLovinAdDisplayListener {
        final /* synthetic */ AppLovinDFPInterstitial f15612a;

        C34911(AppLovinDFPInterstitial appLovinDFPInterstitial) {
            this.f15612a = appLovinDFPInterstitial;
        }

        public void adDisplayed(AppLovinAd appLovinAd) {
            this.f15612a.onAdOpened();
        }

        public void adHidden(AppLovinAd appLovinAd) {
            this.f15612a.onAdClosed();
        }
    }

    class C34922 implements AppLovinAdClickListener {
        final /* synthetic */ AppLovinDFPInterstitial f15613a;

        C34922(AppLovinDFPInterstitial appLovinDFPInterstitial) {
            this.f15613a = appLovinDFPInterstitial;
        }

        public void adClicked(AppLovinAd appLovinAd) {
            this.f15613a.mAd.c();
        }
    }

    public DFPVendor getAnalyticsDFPVendor() {
        return DFPVendor.APPLOVIN;
    }

    public boolean requestInterstitialAd(String[] strArr, Bundle bundle) {
        Context activity = getActivity();
        if (activity == null) {
            Log.e(TAG, "Activity was null");
            return false;
        }
        AppLovinAdVendor appLovinAdVendor = (AppLovinAdVendor) AdVendorManager.m17399a().m17405a((Activity) activity, AppLovinAdVendor.class);
        if (appLovinAdVendor == null) {
            Log.e(TAG, "AppLovin not initialized before trying to show DFP interstitial");
            return false;
        }
        if (bundle != null) {
            this.mZoneId = bundle.getString(DFPCustomEventInterstitial.ZONE_ID_BUNDLE_KEY);
            Log.b(TAG, "AppLovin ad placecment: " + this.mZoneId);
        } else {
            Log.e(TAG, "Bundle was null. Make sure to call addCustomEventExtrasBundle for my sub classes");
        }
        this.mAd = AppLovinInterstitialAd.a(appLovinAdVendor.m17486a(activity), activity);
        this.mAd.a(new C34911(this));
        this.mAd.a(new C34922(this));
        return true;
    }

    public boolean isAdReady() {
        return this.mAd.b();
    }

    public void showAd() {
        this.mAd.a(this.mZoneId);
    }
}
