/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.location.Location
 *  android.text.TextUtils
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Adapter
 *  android.widget.ListAdapter
 *  com.mopub.nativeads.GooglePlayServicesAdRenderer
 *  com.mopub.nativeads.MagicGhostRenderer
 *  com.mopub.nativeads.MagicMoPubStaticNativeAdRenderer
 *  com.mopub.nativeads.MoPubAdAdapter
 *  com.mopub.nativeads.MoPubAdRenderer
 *  com.mopub.nativeads.MoPubNative
 *  com.mopub.nativeads.MoPubNative$SmuleNativeAdEventListenerInterface
 *  com.mopub.nativeads.MoPubStreamAdPlacer
 *  com.mopub.nativeads.RequestParameters
 *  com.mopub.nativeads.RequestParameters$Builder
 *  com.mopub.nativeads.RequestParameters$NativeAdAsset
 *  com.mopub.nativeads.SmuleNativeAdEventListener
 *  com.mopub.nativeads.ViewBinder
 *  com.mopub.nativeads.VisibilityTracker
 */
package com.smule.android.ads.vendors.mopub;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListAdapter;
import com.mopub.nativeads.GooglePlayServicesAdRenderer;
import com.mopub.nativeads.MagicGhostRenderer;
import com.mopub.nativeads.MagicMoPubStaticNativeAdRenderer;
import com.mopub.nativeads.MoPubAdAdapter;
import com.mopub.nativeads.MoPubAdRenderer;
import com.mopub.nativeads.MoPubNative;
import com.mopub.nativeads.MoPubStreamAdPlacer;
import com.mopub.nativeads.RequestParameters;
import com.mopub.nativeads.SmuleNativeAdEventListener;
import com.mopub.nativeads.ViewBinder;
import com.mopub.nativeads.VisibilityTracker;
import com.smule.android.R;
import com.smule.android.ads.MagicAdAdapterFactory;
import com.smule.android.ads.MagicAdSettings;
import com.smule.android.ads.nativeAds.MagicNativeAdMediatorAdapter;
import com.smule.android.ads.vendors.mopub.MagicMoPubNativeAdMediatorAdapter;
import com.smule.android.logging.Analytics;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.utils.LocationUtils;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Set;

public class MagicMoPubNativeAdMediatorAdapter
extends MagicNativeAdMediatorAdapter {
    public static final String TAG = MagicMoPubNativeAdMediatorAdapter.class.getSimpleName();
    private String mAdUnit;
    private MoPubAdAdapter mNativeAdAdapter;
    private Analytics mPlacementType;
    private HashMap<String, String> mTargetingParams;

    /*
     * Enabled aggressive block sorting
     */
    public MagicMoPubNativeAdMediatorAdapter(Activity activity, Analytics object, ViewBinder viewBinder, ViewBinder viewBinder2, MoPubStreamAdPlacer moPubStreamAdPlacer, HashMap<String, String> hashMap, MagicListView magicListView, MagicAdapter magicAdapter, int n, View.OnClickListener onClickListener, Runnable runnable) {
        super(activity, object, viewBinder, viewBinder2, hashMap, magicListView, magicAdapter, n, onClickListener, runnable);
        this.mPlacementType = object;
        object = magicListView.a(magicAdapter);
        magicListView = object.a();
        this.mNativeAdAdapter = moPubStreamAdPlacer == null ? new MoPubAdAdapter(activity, (Adapter)magicListView) : new MoPubAdAdapter(moPubStreamAdPlacer, (Adapter)magicListView, new VisibilityTracker((Context)activity));
        viewBinder = new MagicGhostRenderer(viewBinder);
        this.mNativeAdAdapter.registerAdRenderer((MoPubAdRenderer)viewBinder);
        viewBinder = new GooglePlayServicesAdRenderer(viewBinder2, n, onClickListener, runnable);
        this.mNativeAdAdapter.registerAdRenderer((MoPubAdRenderer)viewBinder);
        viewBinder = new MagicMoPubStaticNativeAdRenderer(viewBinder2, n, onClickListener, runnable);
        this.mNativeAdAdapter.registerAdRenderer((MoPubAdRenderer)viewBinder);
        object.a((ListAdapter)this.mNativeAdAdapter);
        MagicMoPubNativeAdMediatorAdapter.attachInHouseAdListeners((MoPubNative.SmuleNativeAdEventListenerInterface)this.mNativeAdAdapter, this.mPlacementType, runnable);
        this.mTargetingParams = hashMap;
        this.mAdUnit = MagicAdSettings.a((Context)activity, this.mPlacementType);
    }

    public static void attachInHouseAdListeners(MoPubNative.SmuleNativeAdEventListenerInterface smuleNativeAdEventListenerInterface, Analytics nativeAdPlacementType, Runnable runnable) {
        smuleNativeAdEventListenerInterface.setSmuleNativeAdEventListener(new SmuleNativeAdEventListener(nativeAdPlacementType, runnable){
            final /* synthetic */ Analytics a;
            final /* synthetic */ Runnable b;
            {
                this.a = nativeAdPlacementType;
                this.b = runnable;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void onAdClick(com.mopub.nativeads.BaseNativeAd baseNativeAd) {
                block3 : {
                    block2 : {
                        if (baseNativeAd == null) break block2;
                        com.smule.android.logging.Analytics.b(com.smule.android.logging.Analytics$AdType.c, this.a, baseNativeAd.getPosition(), com.smule.android.logging.Analytics$AdMediator.a, baseNativeAd.getAdNetworkName(), com.smule.android.logging.Analytics$AdCategory.a, null, baseNativeAd.getRequestId());
                        if (this.b != null) break block3;
                    }
                    return;
                }
                this.b.run();
            }

            public void onAdImpression(com.mopub.nativeads.BaseNativeAd baseNativeAd) {
                if (baseNativeAd == null) {
                    return;
                }
                com.smule.android.logging.Analytics.a(com.smule.android.logging.Analytics$AdType.c, this.a, (java.lang.Integer)baseNativeAd.getPosition(), com.smule.android.logging.Analytics$AdMediator.a, baseNativeAd.getAdNetworkName(), com.smule.android.logging.Analytics$AdCategory.a, null, baseNativeAd.getRequestId());
            }

            public void onAdRequestFail(com.mopub.nativeads.BaseNativeAd baseNativeAd, com.mopub.nativeads.NativeErrorCode nativeErrorCode) {
                if (baseNativeAd == null) {
                    return;
                }
                com.smule.android.logging.Analytics.a(com.smule.android.logging.Analytics$AdType.c, this.a, false, com.smule.android.logging.Analytics$AdMediator.a, baseNativeAd.getAdNetworkName(), com.smule.android.logging.Analytics$AdCategory.a, null, baseNativeAd.getRequestId(), nativeErrorCode.name(), null);
            }

            public void onAdRequestSuccess(com.mopub.nativeads.BaseNativeAd baseNativeAd) {
                if (baseNativeAd == null) {
                    return;
                }
                com.smule.android.logging.Analytics.a(com.smule.android.logging.Analytics$AdType.c, this.a, true, com.smule.android.logging.Analytics$AdMediator.a, baseNativeAd.getAdNetworkName(), com.smule.android.logging.Analytics$AdCategory.a, null, baseNativeAd.getRequestId(), null, (java.lang.Long)baseNativeAd.getLoadTime());
            }
        });
    }

    private static String formatKeywords(HashMap<String, String> hashMap) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string2 : hashMap.keySet()) {
            String string3 = hashMap.get(string2);
            arrayList.add(string2 + ":" + string3);
        }
        return TextUtils.join((CharSequence)",", arrayList);
    }

    public static EnumSet getDesiredAssets(Analytics nativeAdPlacementType) {
        switch (.a[nativeAdPlacementType.ordinal()]) {
            default: {
                return EnumSet.of(RequestParameters.NativeAdAsset.TITLE, RequestParameters.NativeAdAsset.TEXT, RequestParameters.NativeAdAsset.ICON_IMAGE, RequestParameters.NativeAdAsset.MAIN_IMAGE, RequestParameters.NativeAdAsset.CALL_TO_ACTION_TEXT);
            }
            case 1: 
            case 2: 
        }
        return EnumSet.of(RequestParameters.NativeAdAsset.TITLE, RequestParameters.NativeAdAsset.TEXT, RequestParameters.NativeAdAsset.ICON_IMAGE, RequestParameters.NativeAdAsset.CALL_TO_ACTION_TEXT);
    }

    public static RequestParameters getRequestParams(Analytics object, HashMap<String, String> object2) {
        object = MagicMoPubNativeAdMediatorAdapter.getDesiredAssets(object);
        object2 = MagicMoPubNativeAdMediatorAdapter.formatKeywords(object2);
        RequestParameters.Builder builder = new RequestParameters.Builder();
        builder.desiredAssets((EnumSet)object);
        builder.keywords((String)object2);
        builder.location(LocationUtils.a());
        return builder.build();
    }

    public static void registerWithFactory(Context object) {
        object = object.getResources().getString(R.string.MagicMoPubMediatorSettingsKey);
        MagicAdAdapterFactory.a().a(MagicMoPubNativeAdMediatorAdapter.class, (String)object);
    }

    @Override
    public void destroy() {
        this.mNativeAdAdapter.destroy();
    }

    @Override
    public int getAdjustedPosition(int n) {
        return this.mNativeAdAdapter.getAdjustedPosition(n);
    }

    @Override
    public int getCountIncludingAds() {
        return this.mNativeAdAdapter.getCount();
    }

    @Override
    public int getOriginalPosition(int n) {
        return this.mNativeAdAdapter.getOriginalPosition(n);
    }

    @Override
    public boolean isAd(int n) {
        return this.mNativeAdAdapter.isAd(n);
    }

    @Override
    public void loadAds() {
        RequestParameters requestParameters = MagicMoPubNativeAdMediatorAdapter.getRequestParams(this.mPlacementType, this.mTargetingParams);
        this.mNativeAdAdapter.loadAds(this.mAdUnit, requestParameters);
    }

    @Override
    public void notifyDataSetChanged() {
        if (this.mNativeAdAdapter != null) {
            this.mNativeAdAdapter.notifyDataSetChanged();
        }
    }
}

