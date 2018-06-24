/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.support.annotation.NonNull
 *  android.view.View
 *  android.view.View$OnClickListener
 *  com.mopub.nativeads.MoPubStreamAdPlacer
 *  com.mopub.nativeads.ViewBinder
 */
package com.smule.android.ads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import com.mopub.nativeads.MoPubStreamAdPlacer;
import com.mopub.nativeads.ViewBinder;
import com.smule.android.ads.MagicAdSettings;
import com.smule.android.ads.fullScreenAds.MagicFullScreenAdMediatorAdapter;
import com.smule.android.ads.nativeAds.MagicNativeAdMediatorAdapter;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class MagicAdAdapterFactory {
    private static final String c = MagicAdAdapterFactory.class.getName();
    private static MagicAdAdapterFactory d = null;
    protected HashMap<String, Class> a = new HashMap();
    protected HashMap<String, Class> b = new HashMap();

    public static MagicAdAdapterFactory a() {
        if (d == null) {
            d = new MagicAdAdapterFactory();
        }
        return d;
    }

    public MagicFullScreenAdMediatorAdapter a(@NonNull Activity object) {
        if (object == null) {
            Log.e(c, "createFullScreenMediatorAdapter was passed a null Activity");
            return null;
        }
        if (!MagicAdSettings.b()) {
            Log.b(c, "fullscreen ad placements not enabled in general, returning null fullscreen ad mediator adapter");
            return null;
        }
        Class class_ = this.b.get(MagicAdSettings.d());
        if (class_ == null) {
            // empty if block
        }
        try {
            object = (MagicFullScreenAdMediatorAdapter)class_.getConstructor(Activity.class).newInstance(object);
            return object;
        }
        catch (NoSuchMethodException noSuchMethodException) {
            Log.d(c, "No such constructor for MagicFullScreenAdMediatorAdapter", new RuntimeException("createFullScreenMediatorAdapter"));
            return null;
        }
        catch (InvocationTargetException invocationTargetException) {
            Log.d(c, "InvocationTargetException while constructing MagicFullScreenAdMediatorAdapter", new RuntimeException("createFullScreenMediatorAdapter"));
            return null;
        }
        catch (IllegalAccessException illegalAccessException) {
            Log.d(c, "IllegalAccessException while constructing MagicFullScreenAdMediatorAdapter", new RuntimeException("createFullScreenMediatorAdapter"));
            return null;
        }
        catch (InstantiationException instantiationException) {
            Log.d(c, "InstantiationException while constructing MagicFullScreenAdMediatorAdapter", new RuntimeException("createFullScreenMediatorAdapter"));
            return null;
        }
    }

    public MagicNativeAdMediatorAdapter a(Activity object, Analytics nativeAdPlacementType, ViewBinder viewBinder, ViewBinder viewBinder2, MoPubStreamAdPlacer moPubStreamAdPlacer, HashMap<String, String> hashMap, MagicListView magicListView, MagicAdapter magicAdapter, int n, View.OnClickListener onClickListener, Runnable runnable) {
        if (object == null) {
            Log.e(c, "createNativeMediatorAdapter was passed a null Activity");
            return null;
        }
        if (magicListView == null) {
            Log.e(c, "createNativeMediatorAdapter was passed a null MagicListView");
            return null;
        }
        if (magicAdapter == null) {
            Log.e(c, "createNativeMediatorAdapter was passed a null MagicAdapter");
            return null;
        }
        if (!MagicAdSettings.a(nativeAdPlacementType)) {
            Log.b(c, "native ad placement: '" + nativeAdPlacementType.a() + "' not enabled, returning null native ad mediator adapter");
            return null;
        }
        if (MagicAdSettings.a(object.getApplicationContext(), nativeAdPlacementType) == null) {
            Log.b(c, "createNativeMediatorAdapter could not find valid ad unit ID for placement: " + nativeAdPlacementType.a());
            return null;
        }
        Class class_ = this.a.get(MagicAdSettings.a());
        if (class_ == null) {
            // empty if block
        }
        try {
            object = (MagicNativeAdMediatorAdapter)class_.getConstructor(Activity.class, Analytics.class, ViewBinder.class, ViewBinder.class, MoPubStreamAdPlacer.class, HashMap.class, MagicListView.class, MagicAdapter.class, Integer.TYPE, View.OnClickListener.class, Runnable.class).newInstance(new Object[]{object, nativeAdPlacementType, viewBinder, viewBinder2, moPubStreamAdPlacer, hashMap, magicListView, magicAdapter, n, onClickListener, runnable});
            return object;
        }
        catch (NoSuchMethodException noSuchMethodException) {
            Log.d(c, "No such constructor for MagicNativeAdMediatorAdapter", new RuntimeException("createNativeMediatorAdapter"));
            return null;
        }
        catch (InvocationTargetException invocationTargetException) {
            Log.d(c, "InvocationTargetException while constructing MagicNativeAdMediatorAdapter", new RuntimeException("createNativeMediatorAdapter"));
            return null;
        }
        catch (IllegalAccessException illegalAccessException) {
            Log.d(c, "IllegalAccessException while constructing MagicNativeAdMediatorAdapter", new RuntimeException("createNativeMediatorAdapter"));
            return null;
        }
        catch (InstantiationException instantiationException) {
            Log.d(c, "InstantiationException while constructing MagicNativeAdMediatorAdapter", new RuntimeException("createNativeMediatorAdapter"));
            return null;
        }
    }

    public void a(Class<? extends MagicNativeAdMediatorAdapter> class_, String string2) {
        if (string2 == null) {
            Log.e(c, "mediatorId is a required argument for registerNativeMediationAdapter");
        }
        this.a.containsKey(string2);
        if (!this.a.containsKey(string2)) {
            Log.b(c, "creating NEW native ad mediator: " + string2);
            this.a.put(string2, class_);
            return;
        }
        Log.b(c, "re-using native ad mediator: " + string2);
    }

    public void b(Class<?> class_, String string2) {
        if (string2 == null) {
            Log.e(c, "mediatorId is a required argument for registerFullScreenMediationAdapter");
        }
        this.b.containsKey(string2);
        if (!this.b.containsKey(string2)) {
            this.b.put(string2, class_);
        }
    }
}

