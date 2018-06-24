/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Resources
 *  android.net.Uri
 *  android.os.Bundle
 *  com.google.android.gms.ads.doubleclick.AppEventListener
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.smule.singandroid.ads;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.smule.SmuleApplication;
import com.smule.android.AdvertisingID;
import com.smule.android.ads.MagicAdSettings;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.RewardsManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.PackageInfoUtils;
import com.smule.singandroid.MasterActivity_;
import com.smule.singandroid.SingApplication;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class SingAdSettings {
    private static final String a = SingAdSettings.class.getSimpleName();

    /*
     * Enabled aggressive block sorting
     */
    public static Bundle a(Map<String, String> iterator) {
        void var5_7;
        int n = 1;
        int n2 = AppSettingsManager.a().b("dfp", "testId", 0);
        int n3 = AppSettingsManager.a().b("dfp", "groupId", 0);
        Bundle bundle = new Bundle();
        bundle.putString("version", MagicNetwork.d().getAppVersion());
        bundle.putInt("sessions", (int)EventLogger2.b(true));
        int n4 = SubscriptionManager.a().b() ? 1 : 0;
        bundle.putInt("is_subs", n4);
        n4 = SubscriptionManager.a().b() ? 1 : 0;
        bundle.putInt("is_reg", n4);
        bundle.putInt("test_id", n2);
        bundle.putInt("group_id", n3);
        if (AdvertisingID.b != null) {
            bundle.putString("aifa", AdvertisingID.b);
        }
        bundle.putString("used", SingAdSettings.a());
        bundle.putInt("inst_days", UserManager.a().r());
        if (RewardsManager.a().d(RewardsManager.Reward.b)) {
            String string2 = "claimed";
        } else {
            String string3 = "unclaimed";
        }
        bundle.putString("fb_reward", (String)var5_7);
        n4 = MagicFacebook.a().d() ? 1 : 0;
        bundle.putInt("is_fb", n4);
        if (iterator != null) {
            for (Map.Entry entry : iterator.entrySet()) {
                bundle.putString((String)entry.getKey(), (String)entry.getValue());
            }
        }
        n4 = SubscriptionManager.a().d() ? n : 0;
        bundle.putInt("is_trial", n4);
        n4 = SingAdSettings.e(SingApplication.g());
        if (n4 > 2) {
            n4 = n = (n4 - 2) % 10;
            if (n == 0) {
                n4 = 10;
            }
            bundle.putInt("ad_count", n4);
        }
        Log.c(a, "Custom parameters : " + (Object)bundle);
        return bundle;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected static String a() {
        void var1_5;
        String string2 = "sing_google";
        if (PackageInfoUtils.c(SingApplication.g())) {
            StringBuilder stringBuilder = new StringBuilder().append("sing_google");
            string2 = "sing_google".isEmpty() ? "" : " ";
            string2 = stringBuilder.append(string2).append("minipiano_android").toString();
        }
        String string3 = string2;
        if (PackageInfoUtils.d(SingApplication.g())) {
            StringBuilder stringBuilder = new StringBuilder().append(string2);
            string2 = string2.isEmpty() ? "" : " ";
            String string4 = stringBuilder.append(string2).append("autorap_goog").toString();
        }
        return var1_5;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static HashMap<String, String> a(HashMap<String, String> hashMap) {
        int n = AppSettingsManager.a().b("mopub", "testId", 0);
        int n2 = AppSettingsManager.a().b("mopub", "groupId", 0);
        HashMap<String, String> hashMap2 = new HashMap<String, String>();
        hashMap2.put("test_id", String.valueOf(n));
        hashMap2.put("group_id", String.valueOf(n2));
        hashMap2.put("version", MagicNetwork.d().getAppVersion());
        hashMap2.put("sessions", String.valueOf(EventLogger2.b(true)));
        String string2 = SubscriptionManager.a().b() ? "1" : "0";
        hashMap2.put("is_subs", string2);
        string2 = SubscriptionManager.a().b() ? "1" : "0";
        hashMap2.put("is_reg", string2);
        hashMap2.put("inst_days", String.valueOf(UserManager.a().r()));
        hashMap2.put("used", SingAdSettings.a());
        hashMap2.put("promo_id", String.valueOf(-1));
        if (hashMap != null) {
            hashMap2.putAll(hashMap);
        }
        return hashMap2;
    }

    public static void a(Context context) {
        SingAdSettings.a(context, 1);
    }

    protected static void a(Context context, int n) {
        SharedPreferences.Editor editor = context.getApplicationContext().getSharedPreferences("sing_prefs", 0).edit();
        n = SingAdSettings.c(context) + n;
        Log.b(a, "alterSongJoinsCount -- setting to " + n);
        editor.putInt("song_joins_count", n);
        editor.apply();
    }

    public static void a(Context context, boolean bl) {
        context = context.getApplicationContext().getSharedPreferences("sing_prefs", 0).edit();
        context.putBoolean("launch_ad_shown", bl);
        context.apply();
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean a(FullScreenAdPlacement fullScreenAdPlacement) {
        Log.b(a, "checking if full screen placement is enabled for: " + fullScreenAdPlacement.a());
        if (!MagicAdSettings.b() || SingAdSettings.c(fullScreenAdPlacement) == null) {
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean a(FullScreenAdPlacement fullScreenAdPlacement, int n) {
        boolean bl = true;
        boolean bl2 = true;
        HashMap<String, Object> hashMap = SingAdSettings.c(fullScreenAdPlacement);
        if (hashMap == null) return bl;
        Integer n2 = (Integer)hashMap.get("frequency");
        if (n2 == null) {
            return false;
        }
        if (n2 <= 0) {
            Log.b(a, "FULLSCREEN_AD: '" + fullScreenAdPlacement.a() + "' has frequency of 0, so always throttling ad");
            return true;
        }
        bl = n % n2 > 0 ? bl2 : false;
        Log.b(a, "FULLSCREEN_AD: '" + fullScreenAdPlacement.a() + "' count: " + n + " against frequency of " + n2 + ", throttling? " + bl);
        return bl;
    }

    public static int b(FullScreenAdPlacement object) {
        if ((object = SingAdSettings.c((FullScreenAdPlacement)object)) != null && (object = (Integer)object.get("timeout")) != null && object.intValue() > 0) {
            return object.intValue();
        }
        return MagicAdSettings.e();
    }

    /*
     * Enabled aggressive block sorting
     */
    public static HashMap<String, String> b(HashMap<String, String> hashMap) {
        int n = AppSettingsManager.a().b("mopub", "testId", 0);
        int n2 = AppSettingsManager.a().b("mopub", "groupId", 0);
        HashMap<String, String> hashMap2 = new HashMap<String, String>();
        hashMap2.put("test_id", String.valueOf(n));
        hashMap2.put("group_id", String.valueOf(n2));
        hashMap2.put("version", MagicNetwork.d().getAppVersion());
        hashMap2.put("sessions", String.valueOf(EventLogger2.b(true)));
        String string2 = SubscriptionManager.a().b() ? "1" : "0";
        hashMap2.put("is_subs", string2);
        string2 = SubscriptionManager.a().b() ? "1" : "0";
        hashMap2.put("is_reg", string2);
        hashMap2.put("inst_days", String.valueOf(UserManager.a().r()));
        hashMap2.put("used", SingAdSettings.a());
        hashMap2.put("promo_id", String.valueOf(-1));
        if (hashMap != null) {
            hashMap2.putAll(hashMap);
        }
        return hashMap2;
    }

    public static void b(Context context) {
        SingAdSettings.a(context, -1);
    }

    public static int c(Context context) {
        context = context.getApplicationContext().getSharedPreferences("sing_prefs", 0);
        int n = context.getInt("song_joins_count", 0);
        Log.b(a, "songJoinCount: " + n);
        return context.getInt("song_joins_count", 0);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static HashMap<String, Object> c(FullScreenAdPlacement fullScreenAdPlacement) {
        Object object;
        JSONObject jSONObject;
        HashMap<String, Integer> hashMap;
        Object var3_1;
        block3 : {
            block2 : {
                var3_1 = null;
                hashMap = new HashMap<String, Integer>();
                try {
                    object = AppSettingsManager.a().a("sing_google.ads", "zones", (String)null);
                    if (object == null) break block2;
                    jSONObject = new JSONObject((String)object);
                    break block3;
                }
                catch (JSONException jSONException) {
                    MagicCrittercism.a((Throwable)jSONException);
                }
            }
            jSONObject = null;
        }
        object = var3_1;
        if (jSONObject == null) return object;
        fullScreenAdPlacement = jSONObject.optJSONObject(fullScreenAdPlacement.a());
        object = var3_1;
        if (fullScreenAdPlacement == null) return object;
        hashMap.put("frequency", fullScreenAdPlacement.optInt("frequency", 1));
        hashMap.put("timeout", fullScreenAdPlacement.optInt("timeout", MagicAdSettings.e()));
        return hashMap;
    }

    public static String d(FullScreenAdPlacement object) {
        Context context = SingApplication.g();
        object = object.b();
        String string2 = MagicAdSettings.d();
        if (string2 == null) {
            return null;
        }
        object = "ad_unit.fullscreen_ads.rewarded.phone." + (String)object + "." + string2;
        int n = context.getResources().getIdentifier((String)object, "string", context.getPackageName());
        if (n == 0) {
            Log.e(a, "no resource ID found for: " + (String)object);
            return null;
        }
        return context.getResources().getString(n);
    }

    public static void d(Context context) {
        SharedPreferences.Editor editor = context.getApplicationContext().getSharedPreferences("sing_prefs", 0).edit();
        int n = SingAdSettings.e(context) + 1;
        Log.b(a, "incrementRecStartCount -- setting to " + n);
        editor.putInt("songs_performed_count", n);
        editor.apply();
    }

    public static int e(Context context) {
        return context.getApplicationContext().getSharedPreferences("sing_prefs", 0).getInt("songs_performed_count", 0);
    }

    public static boolean f(Context context) {
        return context.getApplicationContext().getSharedPreferences("sing_prefs", 0).getBoolean("launch_ad_shown", false);
    }

    public static boolean g(Context context) {
        if (SingAdSettings.e(context) >= 3) {
            return true;
        }
        return false;
    }

    public static boolean h(Context context) {
        if (SingAdSettings.e(context) >= 2 && !SubscriptionManager.a().b()) {
            return true;
        }
        return false;
    }

    public static class DFPAdEventListener
    implements AppEventListener {
        private boolean a = false;
        private boolean b;
        private String c;

        public String a() {
            return this.c;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void onAppEvent(String string2, String string3) {
            Log.b(a, "DFPAdEventListener.onAppEvent -- s = " + string2 + "; s2 = " + string3);
            this.a = false;
            this.b = false;
            Context context = SmuleApplication.a();
            if ("smule-url".equals(string2)) {
                try {
                    Uri uri = Uri.parse((String)string3);
                    if (uri.getScheme().equals("http") || uri.getScheme().equals("https")) {
                        string2 = new Intent("android.intent.action.VIEW");
                        this.b = true;
                    } else {
                        string2 = new Intent(context, MasterActivity_.class);
                        this.a = true;
                    }
                    string2.setData(uri);
                    string2.addFlags(268435456);
                    context.startActivity((Intent)string2);
                    return;
                }
                catch (NullPointerException nullPointerException) {
                    Log.d(a, "onAppEvent -- error parsing URI from string " + string3);
                    return;
                }
            }
            if ("ad-type".equals(string2)) {
                this.c = string3;
            }
        }
    }

    public static enum FullScreenAdPlacement implements Analytics
    {
        a("preload.post_app_launch"),
        b("preload.sing_flow_exit"),
        c("singing.solo"),
        d("singing.seed"),
        e("singing.join");
        
        private String f;

        private FullScreenAdPlacement(String string3) {
            this.f = string3;
        }

        @Override
        public String a() {
            return this.f;
        }

        public String b() {
            switch (.a[this.ordinal()]) {
                default: {
                    return this.a();
                }
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
            }
            return "singing";
        }
    }

}

