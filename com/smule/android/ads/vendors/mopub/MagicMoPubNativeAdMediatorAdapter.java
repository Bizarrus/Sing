package com.smule.android.ads.vendors.mopub;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import com.mopub.nativeads.GooglePlayServicesAdRenderer;
import com.mopub.nativeads.MagicGhostRenderer;
import com.mopub.nativeads.MagicMoPubStaticNativeAdRenderer;
import com.mopub.nativeads.MoPubAdAdapter;
import com.mopub.nativeads.MoPubNative.SmuleNativeAdEventListenerInterface;
import com.mopub.nativeads.MoPubStreamAdPlacer;
import com.mopub.nativeads.RequestParameters;
import com.mopub.nativeads.RequestParameters.Builder;
import com.mopub.nativeads.RequestParameters.NativeAdAsset;
import com.mopub.nativeads.ViewBinder;
import com.mopub.nativeads.VisibilityTracker;
import com.smule.android.R;
import com.smule.android.ads.MagicAdAdapterFactory;
import com.smule.android.ads.MagicAdSettings;
import com.smule.android.ads.nativeAds.MagicNativeAdMediatorAdapter;
import com.smule.android.logging.Analytics.NativeAdPlacementType;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.MagicListView.NativeAdSetup;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.utils.LocationUtils;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;

public class MagicMoPubNativeAdMediatorAdapter extends MagicNativeAdMediatorAdapter {
    private String mAdUnit;
    private MoPubAdAdapter mNativeAdAdapter;
    private NativeAdPlacementType mPlacementType;
    private HashMap<String, String> mTargetingParams;

    public static void registerWithFactory(Context context) {
        MagicAdAdapterFactory.a().a(MagicMoPubNativeAdMediatorAdapter.class, context.getResources().getString(R.string.MagicMoPubMediatorSettingsKey));
    }

    public MagicMoPubNativeAdMediatorAdapter(Activity activity, NativeAdPlacementType nativeAdPlacementType, ViewBinder viewBinder, ViewBinder viewBinder2, MoPubStreamAdPlacer moPubStreamAdPlacer, HashMap<String, String> hashMap, MagicListView magicListView, MagicAdapter magicAdapter, int i, OnClickListener onClickListener, Runnable runnable) {
        super(activity, nativeAdPlacementType, viewBinder, viewBinder2, hashMap, magicListView, magicAdapter, i, onClickListener, runnable);
        this.mPlacementType = nativeAdPlacementType;
        NativeAdSetup a = magicListView.a(magicAdapter);
        Adapter a2 = a.a();
        if (moPubStreamAdPlacer == null) {
            this.mNativeAdAdapter = new MoPubAdAdapter(activity, a2);
        } else {
            this.mNativeAdAdapter = new MoPubAdAdapter(moPubStreamAdPlacer, a2, new VisibilityTracker(activity));
        }
        this.mNativeAdAdapter.registerAdRenderer(new MagicGhostRenderer(viewBinder));
        this.mNativeAdAdapter.registerAdRenderer(new GooglePlayServicesAdRenderer(viewBinder2, i, onClickListener, runnable));
        this.mNativeAdAdapter.registerAdRenderer(new MagicMoPubStaticNativeAdRenderer(viewBinder2, i, onClickListener, runnable));
        a.a(this.mNativeAdAdapter);
        attachInHouseAdListeners(this.mNativeAdAdapter, this.mPlacementType, runnable);
        this.mTargetingParams = hashMap;
        this.mAdUnit = MagicAdSettings.a(activity, this.mPlacementType);
    }

    public void loadAds() {
        this.mNativeAdAdapter.loadAds(this.mAdUnit, getRequestParams(this.mPlacementType, this.mTargetingParams));
    }

    public void destroy() {
        this.mNativeAdAdapter.destroy();
    }

    public void notifyDataSetChanged() {
        if (this.mNativeAdAdapter != null) {
            this.mNativeAdAdapter.notifyDataSetChanged();
        }
    }

    public static RequestParameters getRequestParams(NativeAdPlacementType nativeAdPlacementType, HashMap<String, String> hashMap) {
        EnumSet desiredAssets = getDesiredAssets(nativeAdPlacementType);
        String formatKeywords = formatKeywords(hashMap);
        Builder builder = new Builder();
        builder.desiredAssets(desiredAssets);
        builder.keywords(formatKeywords);
        builder.location(LocationUtils.a());
        return builder.build();
    }

    private static String formatKeywords(HashMap<String, String> hashMap) {
        Iterable arrayList = new ArrayList();
        for (String str : hashMap.keySet()) {
            arrayList.add(str + ":" + ((String) hashMap.get(str)));
        }
        return TextUtils.join(",", arrayList);
    }

    public static EnumSet getDesiredAssets(NativeAdPlacementType nativeAdPlacementType) {
        switch (2.a[nativeAdPlacementType.ordinal()]) {
            case 1:
            case 2:
                return EnumSet.of(NativeAdAsset.TITLE, NativeAdAsset.TEXT, NativeAdAsset.ICON_IMAGE, NativeAdAsset.CALL_TO_ACTION_TEXT);
            default:
                return EnumSet.of(NativeAdAsset.TITLE, NativeAdAsset.TEXT, NativeAdAsset.ICON_IMAGE, NativeAdAsset.MAIN_IMAGE, NativeAdAsset.CALL_TO_ACTION_TEXT);
        }
    }

    public static void attachInHouseAdListeners(SmuleNativeAdEventListenerInterface smuleNativeAdEventListenerInterface, NativeAdPlacementType nativeAdPlacementType, Runnable runnable) {
        smuleNativeAdEventListenerInterface.setSmuleNativeAdEventListener(new 1(nativeAdPlacementType, runnable));
    }

    public int getOriginalPosition(int i) {
        return this.mNativeAdAdapter.getOriginalPosition(i);
    }

    public int getAdjustedPosition(int i) {
        return this.mNativeAdAdapter.getAdjustedPosition(i);
    }
}
