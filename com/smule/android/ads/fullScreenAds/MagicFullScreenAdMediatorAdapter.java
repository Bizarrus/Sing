/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.database.Observable
 *  android.support.annotation.NonNull
 *  com.mopub.mobileads.MoPubErrorCode
 */
package com.smule.android.ads.fullScreenAds;

import android.app.Activity;
import android.database.Observable;
import android.support.annotation.NonNull;
import com.mopub.mobileads.MoPubErrorCode;
import com.smule.android.ads.MagicAd;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class MagicFullScreenAdMediatorAdapter {
    protected  mEventObservable;

    public MagicFullScreenAdMediatorAdapter(@NonNull Activity activity) {
        this.mEventObservable = new Observable<>(){

            public void a() {
                Iterator iterator = this.mObservers.iterator();
                while (iterator.hasNext()) {
                    (iterator.next()).a();
                }
            }

            public void a( adImpressionResult, MoPubErrorCode moPubErrorCode) {
                Iterator iterator = this.mObservers.iterator();
                while (iterator.hasNext()) {
                    (iterator.next()).a(adImpressionResult, moPubErrorCode);
                }
            }

            public void a( rewardResult) {
                Iterator iterator = this.mObservers.iterator();
                while (iterator.hasNext()) {
                    (iterator.next()).a(rewardResult);
                }
            }
        };
    }

    protected boolean actuallyShowAd() {
        return false;
    }

    public boolean cancelUserWaitForAd(@NonNull MagicAd magicAd) {
        return false;
    }

    public void destroy() {
    }

    public boolean isAdBeingDisplayed() {
        return false;
    }

    public boolean isAdLoaded(@NonNull MagicAd magicAd) {
        return false;
    }

    public boolean isAdLoading(@NonNull MagicAd magicAd) {
        return false;
    }

    public void loadAndShowAdWhileUserWaits(@NonNull MagicAd magicAd) {
    }

    public void logAdRewardClick() {
    }

    public void onActivityCreate(Activity activity) {
    }

    public void onActivityDestroy(Activity activity) {
    }

    public void onActivityPause(Activity activity) {
    }

    public void onActivityRestart(Activity activity) {
    }

    public void onActivityResume(Activity activity) {
    }

    public void onActivityStart(Activity activity) {
    }

    public void onActivityStop(Activity activity) {
    }

    public void onBackPressed(Activity activity) {
    }

    public void preloadAd(@NonNull MagicAd magicAd) {
    }

    public void registerListener( eventListener) {
        this.mEventObservable.registerObserver((Object)eventListener);
    }

    public void unregisterListener( eventListener) {
        this.mEventObservable.unregisterObserver((Object)eventListener);
    }

}

