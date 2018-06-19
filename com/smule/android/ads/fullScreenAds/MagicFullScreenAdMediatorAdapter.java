package com.smule.android.ads.fullScreenAds;

import android.app.Activity;
import com.smule.android.logging.Analytics.FullScreenAdPlacementType;
import java.util.HashMap;

public abstract class MagicFullScreenAdMediatorAdapter {
    protected MagicFullScreenAdObservable mEventObservable = new MagicFullScreenAdObservable(this);

    public MagicFullScreenAdMediatorAdapter(Activity activity, FullScreenAdPlacementType fullScreenAdPlacementType, HashMap<String, String> hashMap, Runnable runnable) {
    }

    public void preloadInitialAd(HashMap<String, String> hashMap) {
    }

    public void loadAd(HashMap<String, String> hashMap) {
    }

    public boolean showAd() {
        return false;
    }

    public void loadAndShowAd(HashMap<String, String> hashMap) {
    }

    public void cancelAd() {
    }

    public void destroy() {
    }

    public boolean isAdBeingDisplayed() {
        return false;
    }

    public void registerListener(EventListener eventListener) {
        this.mEventObservable.registerObserver(eventListener);
    }

    public void unregisterListener(EventListener eventListener) {
        this.mEventObservable.unregisterObserver(eventListener);
    }

    public void onActivityCreate(Activity activity) {
    }

    public void onActivityPause(Activity activity) {
    }

    public void onActivityResume(Activity activity) {
    }

    public void onActivityStart(Activity activity) {
    }

    public void onActivityRestart(Activity activity) {
    }

    public void onActivityStop(Activity activity) {
    }

    public void onActivityDestroy(Activity activity) {
    }

    public void onBackPressed(Activity activity) {
    }

    public void logAdRewardClick() {
    }
}
