package com.smule.singandroid.ads;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.mopub.common.Constants;
import com.smule.SmuleApplication;
import com.smule.android.AdvertisingID;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.RewardsManager;
import com.smule.android.network.managers.RewardsManager.Reward;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.singandroid.MasterActivity_;
import com.smule.singandroid.SingApplication;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class AdUtils {
    private static final String f20619a = AdUtils.class.getName();

    public static class DFPAdEventListener implements AppEventListener {
        private boolean f20616a = false;
        private boolean f20617b;
        private String f20618c;

        public String m22217a() {
            return this.f20618c;
        }

        public void onAppEvent(String str, String str2) {
            Log.b(AdUtils.f20619a, "DFPAdEventListener.onAppEvent -- s = " + str + "; s2 = " + str2);
            this.f20616a = false;
            this.f20617b = false;
            Context a = SmuleApplication.a();
            if ("smule-url".equals(str)) {
                try {
                    Intent intent;
                    Uri parse = Uri.parse(str2);
                    if (parse.getScheme().equals(Constants.HTTP) || parse.getScheme().equals(Constants.HTTPS)) {
                        intent = new Intent("android.intent.action.VIEW");
                        this.f20617b = true;
                    } else {
                        intent = new Intent(a, MasterActivity_.class);
                        this.f20616a = true;
                    }
                    intent.setData(parse);
                    intent.addFlags(268435456);
                    a.startActivity(intent);
                } catch (NullPointerException e) {
                    Log.d(AdUtils.f20619a, "onAppEvent -- error parsing URI from string " + str2);
                }
            } else if ("ad-type".equals(str)) {
                this.f20618c = str2;
            }
        }
    }

    public static boolean m22223a(String str) {
        try {
            ApplicationInfo applicationInfo = SingApplication.f().getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                Log.b(f20619a, "App " + str + " installed in " + applicationInfo.dataDir);
                return true;
            }
            Log.b(f20619a, "No info for app " + str);
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static void m22220a(Context context) {
        Editor edit = context.getApplicationContext().getSharedPreferences("sing_prefs", 0).edit();
        int b = m22224b(context) + 1;
        Log.b(f20619a, "incrementRecStartCount -- setting to " + b);
        edit.putInt("songs_performed_count", b);
        edit.apply();
    }

    public static int m22224b(Context context) {
        return context.getApplicationContext().getSharedPreferences("sing_prefs", 0).getInt("songs_performed_count", 0);
    }

    public static void m22221a(Context context, boolean z) {
        Editor edit = context.getApplicationContext().getSharedPreferences("sing_prefs", 0).edit();
        edit.putBoolean("launch_ad_shown", z);
        edit.apply();
    }

    public static boolean m22228c(Context context) {
        return context.getApplicationContext().getSharedPreferences("sing_prefs", 0).getBoolean("launch_ad_shown", false);
    }

    public static boolean m22230d(Context context) {
        return m22224b(context) >= 3;
    }

    public static boolean m22231e(Context context) {
        return m22224b(context) >= 2 && !SubscriptionManager.a().b();
    }

    public static boolean m22222a() {
        return m22223a("com.smule.magicpiano");
    }

    public static boolean m22226b() {
        return m22223a("com.smule.autorap");
    }

    public static HashMap<String, String> m22219a(HashMap<String, String> hashMap) {
        int c = AppSettingsManager.a().c("mopub", "testId", 0);
        int c2 = AppSettingsManager.a().c("mopub", "groupId", 0);
        HashMap<String, String> hashMap2 = new HashMap();
        hashMap2.put("test_id", String.valueOf(c));
        hashMap2.put("group_id", String.valueOf(c2));
        hashMap2.put("version", MagicNetwork.d().getAppVersion());
        hashMap2.put("sessions", String.valueOf(EventLogger2.a(true)));
        hashMap2.put("is_subs", SubscriptionManager.a().b() ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        hashMap2.put("is_reg", SubscriptionManager.a().b() ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        hashMap2.put("inst_days", String.valueOf(UserManager.a().r()));
        hashMap2.put("used", m22227c());
        hashMap2.put("promo_id", String.valueOf(-1));
        if (hashMap != null) {
            hashMap2.putAll(hashMap);
        }
        return hashMap2;
    }

    public static HashMap<String, String> m22225b(HashMap<String, String> hashMap) {
        int c = AppSettingsManager.a().c("mopub", "testId", 0);
        int c2 = AppSettingsManager.a().c("mopub", "groupId", 0);
        HashMap<String, String> hashMap2 = new HashMap();
        hashMap2.put("test_id", String.valueOf(c));
        hashMap2.put("group_id", String.valueOf(c2));
        hashMap2.put("version", MagicNetwork.d().getAppVersion());
        hashMap2.put("sessions", String.valueOf(EventLogger2.a(true)));
        hashMap2.put("is_subs", SubscriptionManager.a().b() ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        hashMap2.put("is_reg", SubscriptionManager.a().b() ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        hashMap2.put("inst_days", String.valueOf(UserManager.a().r()));
        hashMap2.put("used", m22227c());
        hashMap2.put("promo_id", String.valueOf(-1));
        if (hashMap != null) {
            hashMap2.putAll(hashMap);
        }
        return hashMap2;
    }

    public static Bundle m22218a(Map<String, String> map) {
        int i;
        int i2 = 1;
        int c = AppSettingsManager.a().c("dfp", "testId", 0);
        int c2 = AppSettingsManager.a().c("dfp", "groupId", 0);
        Bundle bundle = new Bundle();
        bundle.putString("version", MagicNetwork.d().getAppVersion());
        bundle.putInt("sessions", (int) EventLogger2.a(true));
        bundle.putInt("is_subs", SubscriptionManager.a().b() ? 1 : 0);
        String str = "is_reg";
        if (SubscriptionManager.a().b()) {
            i = 1;
        } else {
            i = 0;
        }
        bundle.putInt(str, i);
        bundle.putInt("test_id", c);
        bundle.putInt("group_id", c2);
        if (AdvertisingID.f15528b != null) {
            bundle.putString("aifa", AdvertisingID.f15528b);
        }
        bundle.putString("used", m22227c());
        bundle.putInt("inst_days", UserManager.a().r());
        bundle.putString("fb_reward", RewardsManager.m18319a().m18328d(Reward.FB_LOGIN) ? "claimed" : "unclaimed");
        String str2 = "is_fb";
        if (MagicFacebook.a().d()) {
            i = 1;
        } else {
            i = 0;
        }
        bundle.putInt(str2, i);
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                bundle.putString((String) entry.getKey(), (String) entry.getValue());
            }
        }
        String str3 = "is_trial";
        if (!SubscriptionManager.a().d()) {
            i2 = 0;
        }
        bundle.putInt(str3, i2);
        i = m22224b(SingApplication.f());
        if (i > 2) {
            i = (i - 2) % 10;
            str2 = "ad_count";
            if (i == 0) {
                i = 10;
            }
            bundle.putInt(str2, i);
        }
        Log.c(f20619a, "Custom parameters : " + bundle);
        return bundle;
    }

    protected static String m22227c() {
        String str = "sing_google";
        if (m22222a()) {
            str = str + (str.isEmpty() ? "" : " ") + "minipiano_android";
        }
        if (!m22226b()) {
            return str;
        }
        return str + (str.isEmpty() ? "" : " ") + "autorap_goog";
    }
}
