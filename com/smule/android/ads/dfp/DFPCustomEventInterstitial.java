/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Looper
 *  com.google.android.gms.ads.mediation.MediationAdRequest
 *  com.google.android.gms.ads.mediation.customevent.CustomEventInterstitial
 *  com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener
 */
package com.smule.android.ads.dfp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitial;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import java.lang.ref.WeakReference;

public abstract class DFPCustomEventInterstitial
implements CustomEventInterstitial {
    public static final String TAG = DFPCustomEventInterstitial.class.getName();
    private static long TIMEOUT = 0;
    public static final String ZONE_ID_BUNDLE_KEY = "ZONE_ID_BUNDLE_KEY";
    private static Analytics sLastDFPInterstitialVendor;
    private WeakReference<Activity> mActivityRef;
    private CustomEventInterstitialListener mInterstitialListener;
    private Thread mIsReadyThread;

    static {
        TIMEOUT = 60000;
        sLastDFPInterstitialVendor = null;
    }

    static /* synthetic */ long access$100() {
        return TIMEOUT;
    }

    public static Analytics getLastDFPInterstitialVendor() {
        return sLastDFPInterstitialVendor;
    }

    private void onFailedToReceiveAd() {
        new Handler(Looper.getMainLooper()).post(new Runnable(){

            @Override
            public void run() {
                DFPCustomEventInterstitial.this.mInterstitialListener.onAdFailedToLoad(0);
            }
        });
    }

    private void onReceivedAd() {
        new Handler(Looper.getMainLooper()).post(new Runnable(){

            @Override
            public void run() {
                DFPCustomEventInterstitial.this.mInterstitialListener.onAdLoaded();
            }
        });
    }

    public static void setLastDFPInterstitialVendor(Analytics dFPVendor) {
        sLastDFPInterstitialVendor = dFPVendor;
    }

    protected Activity getActivity() {
        return this.mActivityRef.get();
    }

    public abstract Analytics getAnalyticsDFPVendor();

    public abstract boolean isAdReady();

    protected void onAdClosed() {
        this.mInterstitialListener.onAdClosed();
    }

    protected void onAdOpened() {
        DFPCustomEventInterstitial.setLastDFPInterstitialVendor(this.getAnalyticsDFPVendor());
        this.mInterstitialListener.onAdOpened();
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

    public final void requestInterstitialAd(Context arrstring, CustomEventInterstitialListener customEventInterstitialListener, String string2, MediationAdRequest mediationAdRequest, Bundle bundle) {
        Log.b(TAG, "Ad request received with parameters " + string2);
        this.mActivityRef = new WeakReference<Activity>((Activity)arrstring);
        arrstring = string2.trim().split("\\s*,\\s*");
        this.mInterstitialListener = customEventInterstitialListener;
        if (!this.requestInterstitialAd(arrstring, bundle)) {
            this.onFailedToReceiveAd();
            return;
        }
        this.mIsReadyThread = new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    for (long i = DFPCustomEventInterstitial.access$100(); i > 0; i -= 100) {
                        if (DFPCustomEventInterstitial.this.isAdReady()) {
                            DFPCustomEventInterstitial.this.onReceivedAd();
                            return;
                        }
                        Thread.sleep(100);
                    }
                    DFPCustomEventInterstitial.this.onFailedToReceiveAd();
                    return;
                }
                catch (InterruptedException interruptedException) {
                    Log.b(DFPCustomEventInterstitial.TAG, "isReady() thread interrupted");
                    return;
                }
                finally {
                    DFPCustomEventInterstitial.this.mIsReadyThread = null;
                }
            }
        });
        this.mIsReadyThread.start();
    }

    public abstract boolean requestInterstitialAd(String[] var1, Bundle var2);

    public abstract void showAd();

    public void showInterstitial() {
        if (this.isAdReady()) {
            this.showAd();
        }
    }

}

