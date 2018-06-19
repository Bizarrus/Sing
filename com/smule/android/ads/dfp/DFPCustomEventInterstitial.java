package com.smule.android.ads.dfp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitial;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener;
import com.smule.android.logging.Analytics.DFPVendor;
import com.smule.android.logging.Log;
import java.lang.ref.WeakReference;

public abstract class DFPCustomEventInterstitial implements CustomEventInterstitial {
    public static final String TAG = DFPCustomEventInterstitial.class.getName();
    private static long TIMEOUT = 60000;
    public static final String ZONE_ID_BUNDLE_KEY = "ZONE_ID_BUNDLE_KEY";
    private static DFPVendor sLastDFPInterstitialVendor = null;
    private WeakReference<Activity> mActivityRef;
    private CustomEventInterstitialListener mInterstitialListener;
    private Thread mIsReadyThread;

    class C34931 implements Runnable {
        final /* synthetic */ DFPCustomEventInterstitial f15614a;

        C34931(DFPCustomEventInterstitial dFPCustomEventInterstitial) {
            this.f15614a = dFPCustomEventInterstitial;
        }

        public void run() {
            this.f15614a.mInterstitialListener.onAdFailedToLoad(0);
        }
    }

    class C34942 implements Runnable {
        final /* synthetic */ DFPCustomEventInterstitial f15615a;

        C34942(DFPCustomEventInterstitial dFPCustomEventInterstitial) {
            this.f15615a = dFPCustomEventInterstitial;
        }

        public void run() {
            this.f15615a.mInterstitialListener.onAdLoaded();
        }
    }

    class C34953 implements Runnable {
        final /* synthetic */ DFPCustomEventInterstitial f15616a;

        C34953(DFPCustomEventInterstitial dFPCustomEventInterstitial) {
            this.f15616a = dFPCustomEventInterstitial;
        }

        public void run() {
            try {
                long access$100 = DFPCustomEventInterstitial.TIMEOUT;
                while (access$100 > 0) {
                    if (this.f15616a.isAdReady()) {
                        this.f15616a.onReceivedAd();
                        return;
                    } else {
                        Thread.sleep(100);
                        access$100 -= 100;
                    }
                }
                this.f15616a.onFailedToReceiveAd();
                this.f15616a.mIsReadyThread = null;
            } catch (InterruptedException e) {
                Log.b(DFPCustomEventInterstitial.TAG, "isReady() thread interrupted");
            } finally {
                this.f15616a.mIsReadyThread = null;
            }
        }
    }

    public abstract DFPVendor getAnalyticsDFPVendor();

    public abstract boolean isAdReady();

    public abstract boolean requestInterstitialAd(String[] strArr, Bundle bundle);

    public abstract void showAd();

    public static void setLastDFPInterstitialVendor(DFPVendor dFPVendor) {
        sLastDFPInterstitialVendor = dFPVendor;
    }

    public static DFPVendor getLastDFPInterstitialVendor() {
        return sLastDFPInterstitialVendor;
    }

    private void onFailedToReceiveAd() {
        new Handler(Looper.getMainLooper()).post(new C34931(this));
    }

    private void onReceivedAd() {
        new Handler(Looper.getMainLooper()).post(new C34942(this));
    }

    protected void onAdClosed() {
        this.mInterstitialListener.onAdClosed();
    }

    protected void onAdOpened() {
        setLastDFPInterstitialVendor(getAnalyticsDFPVendor());
        this.mInterstitialListener.onAdOpened();
    }

    protected Activity getActivity() {
        return (Activity) this.mActivityRef.get();
    }

    public final void requestInterstitialAd(Context context, CustomEventInterstitialListener customEventInterstitialListener, String str, MediationAdRequest mediationAdRequest, Bundle bundle) {
        Log.b(TAG, "Ad request received with parameters " + str);
        this.mActivityRef = new WeakReference((Activity) context);
        String[] split = str.trim().split("\\s*,\\s*");
        this.mInterstitialListener = customEventInterstitialListener;
        if (requestInterstitialAd(split, bundle)) {
            this.mIsReadyThread = new Thread(new C34953(this));
            this.mIsReadyThread.start();
            return;
        }
        onFailedToReceiveAd();
    }

    public void showInterstitial() {
        if (isAdReady()) {
            showAd();
        }
    }

    public void onDestroy() {
        if (this.mIsReadyThread != null) {
            this.mIsReadyThread.interrupt();
        }
        this.mIsReadyThread = null;
    }

    public void onPause() {
    }

    public void onResume() {
    }
}
