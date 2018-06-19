package com.smule.android.ads.dfp;

import android.app.Activity;
import android.os.Bundle;
import com.jirbo.adcolony.AdColonyAd;
import com.jirbo.adcolony.AdColonyAdListener;
import com.jirbo.adcolony.AdColonyVideoAd;
import com.smule.android.ads.AdVendorManager;
import com.smule.android.ads.networks.AdColonyAdVendor;
import com.smule.android.logging.Analytics.DFPVendor;
import com.smule.android.logging.Log;

public class AdColonyDFPInterstitial extends DFPCustomEventInterstitial {
    private static final String TAG = AdColonyDFPInterstitial.class.getName();
    private AdColonyVideoAd mAd;

    class C34901 implements AdColonyAdListener {
        final /* synthetic */ AdColonyDFPInterstitial f15611a;

        C34901(AdColonyDFPInterstitial adColonyDFPInterstitial) {
            this.f15611a = adColonyDFPInterstitial;
        }

        public void mo6212a(AdColonyAd adColonyAd) {
            Log.b(AdColonyDFPInterstitial.TAG, "Attempt finished");
            Log.b(AdColonyDFPInterstitial.TAG, "canceled  " + adColonyAd.m14582c());
            Log.b(AdColonyDFPInterstitial.TAG, "shown     " + adColonyAd.m14578a());
            Log.b(AdColonyDFPInterstitial.TAG, "no fill   " + adColonyAd.m14583d());
            Log.b(AdColonyDFPInterstitial.TAG, "not shown " + adColonyAd.m14580b());
            Log.b(AdColonyDFPInterstitial.TAG, "skipped   " + adColonyAd.m14584e());
            this.f15611a.onAdClosed();
        }

        public void mo6213b(AdColonyAd adColonyAd) {
            this.f15611a.onAdOpened();
        }
    }

    public DFPVendor getAnalyticsDFPVendor() {
        return DFPVendor.ADCOLONY;
    }

    public boolean requestInterstitialAd(String[] strArr, Bundle bundle) {
        if (strArr.length == 0) {
            Log.e(TAG, "Wrong parameters received ");
            return false;
        }
        Activity activity = getActivity();
        if (activity == null) {
            Log.e(TAG, "Activity was null");
            return false;
        } else if (((AdColonyAdVendor) AdVendorManager.m17399a().m17405a(activity, AdColonyAdVendor.class)) == null) {
            Log.e(TAG, "AdColony not initialized before trying to show DFP interstitial");
            return false;
        } else {
            this.mAd = new AdColonyVideoAd(strArr.length >= 2 ? strArr[1] : null).m14630a(new C34901(this));
            return true;
        }
    }

    public boolean isAdReady() {
        return this.mAd.mo5688f();
    }

    public void showAd() {
        this.mAd.m14601j();
    }
}
