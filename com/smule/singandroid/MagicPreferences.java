package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.audio.AudioDefs;
import com.smule.android.audio.AudioDefs.HeadphonesType;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.JsonUtils;
import com.smule.singandroid.audio.OpenSLStreamVersion;
import twitter4j.HttpResponseCode;

public class MagicPreferences {
    public static final String f18831a = MagicPreferences.class.getName();

    public enum OnboardStatus {
        EXISTING_USER,
        UNFINISHED,
        FINISHED
    }

    public static void m20304a(Context context, String str, boolean z) {
        Editor edit = context.getApplicationContext().getSharedPreferences("sing_prefs", 0).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public static boolean m20317b(Context context, String str, boolean z) {
        return context.getApplicationContext().getSharedPreferences("sing_prefs", 0).getBoolean(str, z);
    }

    public static void m20301a(Context context, String str, float f) {
        Editor edit = context.getApplicationContext().getSharedPreferences("sing_prefs", 0).edit();
        edit.putFloat(str, f);
        edit.apply();
    }

    public static float m20308b(Context context, String str, float f) {
        return context.getApplicationContext().getSharedPreferences("sing_prefs", 0).getFloat(str, f);
    }

    public static void m20302a(Context context, String str, long j) {
        Editor edit = context.getApplicationContext().getSharedPreferences("sing_prefs", 0).edit();
        edit.putLong(str, j);
        edit.apply();
    }

    public static long m20310b(Context context, String str, long j) {
        return context.getApplicationContext().getSharedPreferences("sing_prefs", 0).getLong(str, j);
    }

    public static void m20303a(Context context, String str, String str2) {
        Editor edit = context.getApplicationContext().getSharedPreferences("sing_prefs", 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static String m20312b(Context context, String str, String str2) {
        return context.getApplicationContext().getSharedPreferences("sing_prefs", 0).getString(str, str2);
    }

    public static Integer m20298a(HeadphonesType headphonesType, OpenSLStreamVersion openSLStreamVersion, int i) {
        int i2 = -1;
        for (HeadphonesType a : AudioDefs.m17509a(headphonesType)) {
            i2 = DeviceSettings.a(a, openSLStreamVersion, i);
            if (i2 != -1) {
                break;
            }
        }
        if (i2 == -1) {
            return null;
        }
        return Integer.valueOf(i2);
    }

    public static Integer m20297a() {
        return Integer.valueOf(HttpResponseCode.MULTIPLE_CHOICES);
    }

    public static int m20309b(HeadphonesType headphonesType, OpenSLStreamVersion openSLStreamVersion, int i) {
        SharedPreferences sharedPreferences = SingApplication.f().getSharedPreferences("sing_prefs", 0);
        for (HeadphonesType headphonesType2 : AudioDefs.m17509a(headphonesType)) {
            int a = DeviceSettings.a(headphonesType2, openSLStreamVersion, i);
            int i2 = sharedPreferences.getInt(headphonesType2.m17505c(), -1);
            if (a != -1 && a != i2) {
                Editor edit = sharedPreferences.edit();
                edit.putInt(headphonesType2.m17505c(), a);
                edit.putInt(headphonesType2.m17504b(), a);
                edit.apply();
                return a;
            } else if (sharedPreferences.contains(headphonesType2.m17504b())) {
                return sharedPreferences.getInt(headphonesType2.m17504b(), -1);
            }
        }
        return HttpResponseCode.MULTIPLE_CHOICES;
    }

    public static void m20306a(HeadphonesType headphonesType, int i) {
        Editor edit = SingApplication.f().getSharedPreferences("sing_prefs", 0).edit();
        edit.putInt(headphonesType.m17504b(), i);
        edit.apply();
    }

    public static boolean m20307a(Context context) {
        return m20317b(context, "FEED_CONNECT_RECOMMENDATION_DISMISSED", false);
    }

    public static void m20305a(Context context, boolean z) {
        m20304a(context, "FEED_CONNECT_RECOMMENDATION_DISMISSED", z);
    }

    public static boolean m20316b(Context context) {
        return m20317b(context, "FIND_FRIENDS_BUTTON_PRESSED", false);
    }

    public static void m20315b(Context context, boolean z) {
        m20304a(context, "FIND_FRIENDS_BUTTON_PRESSED", z);
    }

    public static float m20296a(Context context, float f) {
        return m20308b(context, "SING_ACTIVITY_MONITORING_LEVEL", f);
    }

    public static void m20313b(Context context, float f) {
        m20301a(context, "SING_ACTIVITY_MONITORING_LEVEL", f);
    }

    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    public static String m20299a(Context context, long j) {
        if (j < 0) {
            return null;
        }
        String b = m20312b(context, "LAST_PROMOTION_HASHTAG_PAIR", null);
        if (b == null) {
            return null;
        }
        String[] split = b.split(",");
        if (j == Long.valueOf(Long.parseLong(split[0])).longValue()) {
            return split[1];
        }
        return null;
    }

    public static void m20314b(Context context, long j) {
        m20302a(context, "AV_QUALITY_SURVEY_TIME", j);
    }

    public static long m20318c(Context context, long j) {
        return m20310b(context, "AV_QUALITY_SURVEY_TIME", j);
    }

    public static void m20300a(Context context, PerformanceV2 performanceV2) {
        if (performanceV2 == null) {
            m20303a(context, "AV_SURVEY_LAST_SAVED_PERFORMANCE", null);
        } else {
            m20303a(context, "AV_SURVEY_LAST_SAVED_PERFORMANCE", JsonUtils.m18987a((Object) performanceV2));
        }
    }

    public static PerformanceV2 m20311b(Context context, PerformanceV2 performanceV2) {
        String b = m20312b(context, "AV_SURVEY_LAST_SAVED_PERFORMANCE", null);
        if (b == null) {
            return null;
        }
        try {
            JsonNode jsonNode = (JsonNode) JsonUtils.m18984a().readValue(b, JsonNode.class);
            if (jsonNode != null) {
                return (PerformanceV2) JsonUtils.m18985a(jsonNode, PerformanceV2.class);
            }
            Log.e(f18831a, "Could not parse PerformanceV2 from preferences string: " + b);
            return null;
        } catch (Throwable e) {
            Log.d(f18831a, "Could not parse PerformanceV2 from preferences string", e);
            return null;
        }
    }
}
