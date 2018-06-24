/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 */
package com.smule.android.ads;

import android.content.Context;
import android.content.res.Resources;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.SubscriptionManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MagicAdSettings {
    private static final String a = MagicAdSettings.class.getName();

    public static String a() {
        return AppSettingsManager.a().a("nativeAds", "network", (String)null);
    }

    public static String a(Context context, Analytics object) {
        object = object.a();
        String string2 = MagicAdSettings.a();
        if (string2 == null) {
            return null;
        }
        object = "ad_unit.native_ads.phone." + (String)object + "." + string2;
        int n = context.getResources().getIdentifier((String)object, "string", context.getPackageName());
        if (n == 0) {
            Log.e(a, "no resource ID found for: " + (String)object);
            return null;
        }
        return context.getResources().getString(n);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean a(Analytics nativeAdPlacementType) {
        if (SubscriptionManager.a().b() || !MagicAdSettings.f() || !MagicAdSettings.g().contains(nativeAdPlacementType.a())) {
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean b() {
        if (SubscriptionManager.a().b() || !MagicAdSettings.h()) {
            return false;
        }
        return true;
    }

    public static boolean c() {
        boolean bl = false;
        boolean bl2 = AppSettingsManager.a().a("fullScreenAds", "precache", false);
        boolean bl3 = bl;
        if (MagicAdSettings.b()) {
            bl3 = bl;
            if (bl2) {
                bl3 = true;
            }
        }
        return bl3;
    }

    public static String d() {
        return AppSettingsManager.a().a("fullScreenAds", "network", (String)null);
    }

    public static int e() {
        return AppSettingsManager.a().b("fullScreenAds", "timeoutSec", 10);
    }

    private static boolean f() {
        return AppSettingsManager.a().a("nativeAds", "enabled", false);
    }

    private static ArrayList<String> g() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.addAll(AppSettingsManager.a().a("nativeAds", "placements", new ArrayList()));
        return arrayList;
    }

    private static boolean h() {
        return AppSettingsManager.a().a("fullScreenAds", "enabled", false);
    }
}

