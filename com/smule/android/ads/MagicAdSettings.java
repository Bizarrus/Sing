package com.smule.android.ads;

import android.content.Context;
import com.smule.android.logging.Analytics.FullScreenAdPlacementType;
import com.smule.android.logging.Analytics.NativeAdPlacementType;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.SubscriptionManager;
import java.util.ArrayList;
import java.util.List;

public class MagicAdSettings {
    private static final String f15610a = MagicAdSettings.class.getName();

    public static boolean m17435a(NativeAdPlacementType nativeAdPlacementType) {
        if (!SubscriptionManager.a().b() && m17439d() && m17440e().contains(nativeAdPlacementType.mo6235a())) {
            return true;
        }
        return false;
    }

    public static String m17431a() {
        return AppSettingsManager.a().a("nativeAds", "network", null);
    }

    public static String m17433a(Context context, NativeAdPlacementType nativeAdPlacementType) {
        String a = nativeAdPlacementType.mo6235a();
        String a2 = m17431a();
        if (a2 == null) {
            return null;
        }
        a = "ad_unit.native_ads.phone." + a + "." + a2;
        int identifier = context.getResources().getIdentifier(a, "string", context.getPackageName());
        if (identifier != 0) {
            return context.getResources().getString(identifier);
        }
        Log.e(f15610a, "no resource ID found for: " + a);
        return null;
    }

    public static boolean m17436a(List<FullScreenAdPlacementType> list) {
        if (SubscriptionManager.a().b()) {
            return false;
        }
        if (!m17441f()) {
            return false;
        }
        for (FullScreenAdPlacementType fullScreenAdPlacementType : list) {
            if (!m17442g().contains(fullScreenAdPlacementType.mo6235a())) {
                if (m17443h().contains(fullScreenAdPlacementType.mo6235a())) {
                }
            }
            return true;
        }
        return false;
    }

    public static boolean m17434a(FullScreenAdPlacementType fullScreenAdPlacementType) {
        if (SubscriptionManager.a().b() || !m17441f()) {
            return false;
        }
        if (m17442g().contains(fullScreenAdPlacementType.mo6235a()) || m17443h().contains(fullScreenAdPlacementType.mo6235a())) {
            return true;
        }
        return false;
    }

    public static String m17437b() {
        return AppSettingsManager.a().a("fullScreenAds", "network", null);
    }

    public static String m17432a(Context context, FullScreenAdPlacementType fullScreenAdPlacementType) {
        String str;
        switch (fullScreenAdPlacementType) {
            case SINGING_JOIN:
            case SINGING_SOLO:
            case SINGING_SEED:
                str = "singing";
                break;
            default:
                str = fullScreenAdPlacementType.mo6235a();
                break;
        }
        String b = m17437b();
        if (b == null) {
            return null;
        }
        str = "ad_unit.fullscreen_ads.rewarded.phone." + str + "." + b;
        int identifier = context.getResources().getIdentifier(str, "string", context.getPackageName());
        if (identifier != 0) {
            return context.getResources().getString(identifier);
        }
        Log.e(f15610a, "no resource ID found for: " + str);
        return null;
    }

    public static int m17438c() {
        return AppSettingsManager.a().c("fullScreenAds", "timeoutSec", 10);
    }

    private static boolean m17439d() {
        return AppSettingsManager.a().a("nativeAds", "enabled", false);
    }

    private static ArrayList<String> m17440e() {
        ArrayList<String> arrayList = new ArrayList();
        arrayList.addAll(AppSettingsManager.a().a("nativeAds", "placements", new ArrayList()));
        return arrayList;
    }

    private static boolean m17441f() {
        return AppSettingsManager.a().a("fullScreenAds", "enabled", false);
    }

    private static ArrayList<String> m17442g() {
        ArrayList<String> arrayList = new ArrayList();
        arrayList.addAll(AppSettingsManager.a().a("fullScreenAds", "rewardedPlacements", new ArrayList()));
        return arrayList;
    }

    private static ArrayList<String> m17443h() {
        ArrayList<String> arrayList = new ArrayList();
        arrayList.addAll(AppSettingsManager.a().a("fullScreenAds", "interstitialPlacements", new ArrayList()));
        return arrayList;
    }
}
