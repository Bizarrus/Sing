package com.smule.android.ads.nativeAds;

import android.app.Activity;
import android.view.View.OnClickListener;
import com.mopub.nativeads.ViewBinder;
import com.smule.android.logging.Analytics.NativeAdPlacementType;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import java.util.HashMap;

public abstract class MagicNativeAdMediatorAdapter {
    public abstract int getAdjustedPosition(int i);

    public abstract int getOriginalPosition(int i);

    public MagicNativeAdMediatorAdapter(Activity activity, NativeAdPlacementType nativeAdPlacementType, ViewBinder viewBinder, ViewBinder viewBinder2, HashMap<String, String> hashMap, MagicListView magicListView, MagicAdapter magicAdapter, int i, OnClickListener onClickListener, Runnable runnable) {
    }

    public void loadAds() {
    }

    public void setAdLoadedListener(MagicNativeAdLoadedListener magicNativeAdLoadedListener) {
    }

    public void destroy() {
    }

    public void notifyDataSetChanged() {
    }
}
