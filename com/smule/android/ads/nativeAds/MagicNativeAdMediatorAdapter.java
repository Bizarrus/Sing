/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.view.View
 *  android.view.View$OnClickListener
 *  com.mopub.nativeads.ViewBinder
 */
package com.smule.android.ads.nativeAds;

import android.app.Activity;
import android.view.View;
import com.mopub.nativeads.ViewBinder;
import com.smule.android.logging.Analytics;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import java.util.HashMap;

public abstract class MagicNativeAdMediatorAdapter {
    public MagicNativeAdMediatorAdapter(Activity activity, Analytics nativeAdPlacementType, ViewBinder viewBinder, ViewBinder viewBinder2, HashMap<String, String> hashMap, MagicListView magicListView, MagicAdapter magicAdapter, int n, View.OnClickListener onClickListener, Runnable runnable) {
    }

    public void destroy() {
    }

    public abstract int getAdjustedPosition(int var1);

    public abstract int getCountIncludingAds();

    public abstract int getOriginalPosition(int var1);

    public abstract boolean isAd(int var1);

    public void loadAds() {
    }

    public void notifyDataSetChanged() {
    }

    public void setAdLoadedListener( magicNativeAdLoadedListener) {
    }

}

