package com.smule.android.ads;

import android.app.Activity;
import android.view.View.OnClickListener;
import com.mopub.nativeads.MoPubStreamAdPlacer;
import com.mopub.nativeads.ViewBinder;
import com.smule.android.ads.fullScreenAds.MagicFullScreenAdMediatorAdapter;
import com.smule.android.ads.nativeAds.MagicNativeAdMediatorAdapter;
import com.smule.android.logging.Analytics.FullScreenAdPlacementType;
import com.smule.android.logging.Analytics.NativeAdPlacementType;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class MagicAdAdapterFactory {
    private static final String f15605c = MagicAdAdapterFactory.class.getName();
    private static MagicAdAdapterFactory f15606d = null;
    protected HashMap<String, Class> f15607a = new HashMap();
    protected HashMap<String, Class> f15608b = new HashMap();

    public static MagicAdAdapterFactory m17426a() {
        if (f15606d == null) {
            f15606d = new MagicAdAdapterFactory();
        }
        return f15606d;
    }

    public void m17429a(Class<? extends MagicNativeAdMediatorAdapter> cls, String str) {
        if (str == null) {
            Log.e(f15605c, "mediatorId is a required argument for registerNativeMediationAdapter");
        }
        this.f15607a.containsKey(str);
        if (this.f15607a.containsKey(str)) {
            Log.b(f15605c, "re-using native ad mediator: " + str);
            return;
        }
        Log.b(f15605c, "creating NEW native ad mediator: " + str);
        this.f15607a.put(str, cls);
    }

    public void m17430b(Class<?> cls, String str) {
        if (str == null) {
            Log.e(f15605c, "mediatorId is a required argument for registerFullScreenMediationAdapter");
        }
        this.f15608b.containsKey(str);
        if (!this.f15608b.containsKey(str)) {
            this.f15608b.put(str, cls);
        }
    }

    public MagicNativeAdMediatorAdapter m17428a(Activity activity, NativeAdPlacementType nativeAdPlacementType, ViewBinder viewBinder, ViewBinder viewBinder2, MoPubStreamAdPlacer moPubStreamAdPlacer, HashMap<String, String> hashMap, MagicListView magicListView, MagicAdapter magicAdapter, int i, OnClickListener onClickListener, Runnable runnable) {
        if (activity == null) {
            Log.e(f15605c, "createNativeMediatorAdapter was passed a null Activity");
            return null;
        } else if (magicListView == null) {
            Log.e(f15605c, "createNativeMediatorAdapter was passed a null MagicListView");
            return null;
        } else if (magicAdapter == null) {
            Log.e(f15605c, "createNativeMediatorAdapter was passed a null MagicAdapter");
            return null;
        } else if (!MagicAdSettings.m17435a(nativeAdPlacementType)) {
            Log.b(f15605c, "native ad placement: '" + nativeAdPlacementType.mo6235a() + "' not enabled, returning null native ad mediator adapter");
            return null;
        } else if (MagicAdSettings.m17433a(activity.getApplicationContext(), nativeAdPlacementType) == null) {
            Log.b(f15605c, "createNativeMediatorAdapter could not find valid ad unit ID for placement: " + nativeAdPlacementType.mo6235a());
            return null;
        } else {
            Class cls = (Class) this.f15607a.get(MagicAdSettings.m17431a());
            if (cls == null) {
            }
            try {
                return (MagicNativeAdMediatorAdapter) cls.getConstructor(new Class[]{Activity.class, NativeAdPlacementType.class, ViewBinder.class, ViewBinder.class, MoPubStreamAdPlacer.class, HashMap.class, MagicListView.class, MagicAdapter.class, Integer.TYPE, OnClickListener.class, Runnable.class}).newInstance(new Object[]{activity, nativeAdPlacementType, viewBinder, viewBinder2, moPubStreamAdPlacer, hashMap, magicListView, magicAdapter, Integer.valueOf(i), onClickListener, runnable});
            } catch (NoSuchMethodException e) {
                Log.d(f15605c, "No such constructor for MagicNativeAdMediatorAdapter", new RuntimeException("createNativeMediatorAdapter"));
                return null;
            } catch (InvocationTargetException e2) {
                Log.d(f15605c, "InvocationTargetException while constructing MagicNativeAdMediatorAdapter", new RuntimeException("createNativeMediatorAdapter"));
                return null;
            } catch (IllegalAccessException e3) {
                Log.d(f15605c, "IllegalAccessException while constructing MagicNativeAdMediatorAdapter", new RuntimeException("createNativeMediatorAdapter"));
                return null;
            } catch (InstantiationException e4) {
                Log.d(f15605c, "InstantiationException while constructing MagicNativeAdMediatorAdapter", new RuntimeException("createNativeMediatorAdapter"));
                return null;
            }
        }
    }

    public MagicFullScreenAdMediatorAdapter m17427a(Activity activity, FullScreenAdPlacementType fullScreenAdPlacementType, HashMap<String, String> hashMap, Runnable runnable) {
        if (activity == null) {
            Log.e(f15605c, "createFullScreenMediatorAdapter was passed a null Activity");
            return null;
        } else if (!MagicAdSettings.m17434a(fullScreenAdPlacementType)) {
            Log.b(f15605c, "fullscreen ad placement: '" + fullScreenAdPlacementType.mo6235a() + "' not enabled, returning null fullscreen ad mediator adapter");
            return null;
        } else if (MagicAdSettings.m17432a(activity.getApplicationContext(), fullScreenAdPlacementType) == null) {
            Log.b(f15605c, "createFullScreenMediatorAdapter could not find valid ad unit ID for placement: " + fullScreenAdPlacementType.mo6235a());
            return null;
        } else {
            Class cls = (Class) this.f15608b.get(MagicAdSettings.m17437b());
            if (cls == null) {
            }
            try {
                return (MagicFullScreenAdMediatorAdapter) cls.getConstructor(new Class[]{Activity.class, FullScreenAdPlacementType.class, HashMap.class, Runnable.class}).newInstance(new Object[]{activity, fullScreenAdPlacementType, hashMap, runnable});
            } catch (NoSuchMethodException e) {
                Log.d(f15605c, "No such constructor for MagicFullScreenAdMediatorAdapter", new RuntimeException("createFullScreenMediatorAdapter"));
                return null;
            } catch (InvocationTargetException e2) {
                Log.d(f15605c, "InvocationTargetException while constructing MagicFullScreenAdMediatorAdapter", new RuntimeException("createFullScreenMediatorAdapter"));
                return null;
            } catch (IllegalAccessException e3) {
                Log.d(f15605c, "IllegalAccessException while constructing MagicFullScreenAdMediatorAdapter", new RuntimeException("createFullScreenMediatorAdapter"));
                return null;
            } catch (InstantiationException e4) {
                Log.d(f15605c, "InstantiationException while constructing MagicFullScreenAdMediatorAdapter", new RuntimeException("createFullScreenMediatorAdapter"));
                return null;
            }
        }
    }
}
