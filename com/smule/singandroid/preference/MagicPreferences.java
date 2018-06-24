/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.support.annotation.NonNull
 *  android.text.TextUtils
 *  com.fasterxml.jackson.databind.JsonNode
 *  com.smule.singandroid.video.VideoEffects
 *  com.smule.singandroid.video.VideoEffects$Intensity
 */
package com.smule.singandroid.preference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.audio.AudioDefs;
import com.smule.android.logging.Log;
import com.smule.android.utils.JsonUtils;
import com.smule.singandroid.DeviceSettings;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.audio.OpenSLStreamVersion;
import com.smule.singandroid.customviews.BottomNavView;
import com.smule.singandroid.survey.AVQualityPerformanceInfo;
import com.smule.singandroid.video.VideoEffects;
import java.util.Iterator;

public class MagicPreferences {
    public static final String a = MagicPreferences.class.getName();

    public static float a(Context context, float f) {
        return MagicPreferences.b(context, "LAST_USED_MONITORING_LEVEL", f);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int a() {
        boolean bl;
        SingServerValues singServerValues = new SingServerValues();
        DeviceSettings deviceSettings = new DeviceSettings();
        if (Build.VERSION.SDK_INT < 21) {
            bl = true;
            do {
                return MagicPreferences.a(deviceSettings, singServerValues, bl);
                break;
            } while (true);
        }
        bl = false;
        return MagicPreferences.a(deviceSettings, singServerValues, bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int a(SharedPreferences sharedPreferences, DeviceSettings deviceSettings, SingServerValues singServerValues, boolean bl, AudioDefs.HeadphonesType object, OpenSLStreamVersion openSLStreamVersion, int n) {
        String string2;
        Iterator<AudioDefs.HeadphonesType> iterator = AudioDefs.a((AudioDefs.HeadphonesType)object).iterator();
        do {
            if (!iterator.hasNext()) {
                return MagicPreferences.a(deviceSettings, singServerValues, bl);
            }
            Object object2 = iterator.next();
            object = deviceSettings.e() ? object2.e() : object2.c();
            string2 = deviceSettings.e() ? object2.f() : object2.b();
            object2 = deviceSettings.a((AudioDefs.HeadphonesType)object2, openSLStreamVersion, n);
            int n2 = sharedPreferences.getInt((String)object, -1);
            if (object2 == null || object2.intValue() == n2) continue;
            sharedPreferences = sharedPreferences.edit();
            sharedPreferences.putInt((String)object, object2.intValue());
            sharedPreferences.putInt(string2, object2.intValue());
            sharedPreferences.apply();
            return object2.intValue();
        } while (!sharedPreferences.contains(string2));
        return sharedPreferences.getInt(string2, -1);
    }

    public static int a(AudioDefs.HeadphonesType headphonesType, OpenSLStreamVersion openSLStreamVersion, int n) {
        boolean bl = false;
        SingServerValues singServerValues = new SingServerValues();
        SharedPreferences sharedPreferences = SingApplication.g().getSharedPreferences("sing_prefs", 0);
        DeviceSettings deviceSettings = new DeviceSettings();
        if (Build.VERSION.SDK_INT < 21) {
            bl = true;
        }
        return MagicPreferences.a(sharedPreferences, deviceSettings, singServerValues, bl, headphonesType, openSLStreamVersion, n);
    }

    public static int a(DeviceSettings deviceSettings, SingServerValues singServerValues, boolean bl) {
        if (deviceSettings.e()) {
            if (bl) {
                return 250;
            }
            return singServerValues.C();
        }
        return 300;
    }

    public static Integer a(AudioDefs.HeadphonesType object, OpenSLStreamVersion openSLStreamVersion, int n, DeviceSettings deviceSettings) {
        Integer n2 = null;
        Iterator<AudioDefs.HeadphonesType> iterator = AudioDefs.a((AudioDefs.HeadphonesType)object).iterator();
        object = n2;
        while (iterator.hasNext()) {
            n2 = deviceSettings.a(iterator.next(), openSLStreamVersion, n);
            object = n2;
            if (n2 == null) continue;
            object = n2;
            break;
        }
        return object;
    }

    /*
     * Enabled aggressive block sorting
     */
    @SuppressLint(value={"ValueOfNotAllowedForNumberSubClasses"})
    public static String a(Context arrstring, long l) {
        if (l < 0 || (arrstring = MagicPreferences.b((Context)arrstring, "LAST_PROMOTION_HASHTAG_PAIR", null)) == null || l != Long.valueOf(Long.parseLong((arrstring = arrstring.split(","))[0]))) {
            return null;
        }
        return arrstring[1];
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(Context object, String string2) {
        String string3 = null;
        if (VideoEffects.b((String)string2) == null) {
            Log.e(a, "videoStyleId cannot be found");
            return string3;
        }
        string3 = MagicPreferences.b((Context)object, "VIDEO_STYLE_" + string2, null);
        object = string3;
        if (string3 != null) return object;
        return VideoEffects.b((String)string2).b();
    }

    public static void a(Context context, int n) {
        MagicPreferences.a(context, "SONG_BOOKMARK_TUTORIAL_COUNTDOWN", n);
    }

    public static void a(@NonNull Context context, @NonNull BottomNavView.Tab tab) {
        MagicPreferences.a(context, "pending_landing_screen_on_app_start_key", tab.name());
    }

    public static void a(Context context, AVQualityPerformanceInfo aVQualityPerformanceInfo) {
        if (aVQualityPerformanceInfo == null) {
            MagicPreferences.a(context, "AV_SURVEY_LAST_SAVED_PERFORMANCE_INFO", null);
            return;
        }
        MagicPreferences.a(context, "AV_SURVEY_LAST_SAVED_PERFORMANCE_INFO", JsonUtils.a(aVQualityPerformanceInfo));
    }

    public static void a(Context context, String string2, float f) {
        context = context.getApplicationContext().getSharedPreferences("sing_prefs", 0).edit();
        context.putFloat(string2, f);
        context.apply();
    }

    public static void a(Context context, String string2, int n) {
        context = context.getApplicationContext().getSharedPreferences("sing_prefs", 0).edit();
        context.putInt(string2, n);
        context.apply();
    }

    public static void a(Context context, String string2, long l) {
        context = context.getApplicationContext().getSharedPreferences("sing_prefs", 0).edit();
        context.putLong(string2, l);
        context.apply();
    }

    public static void a(Context context, String string2, VideoEffects.Intensity intensity) {
        MagicPreferences.a(context, "INTENSITY_VIDEO_STYLE_" + string2, intensity.b());
    }

    public static void a(Context context, String string2, String string3) {
        context = context.getApplicationContext().getSharedPreferences("sing_prefs", 0).edit();
        context.putString(string2, string3);
        context.apply();
    }

    public static void a(Context context, String string2, boolean bl) {
        context = context.getApplicationContext().getSharedPreferences("sing_prefs", 0).edit();
        context.putBoolean(string2, bl);
        context.apply();
    }

    public static void a(Context context, boolean bl) {
        MagicPreferences.a(context, "FEED_CONNECT_RECOMMENDATION_DISMISSED", bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(AudioDefs.HeadphonesType object, int n) {
        object = new DeviceSettings().e() ? object.f() : object.b();
        SharedPreferences.Editor editor = SingApplication.g().getSharedPreferences("sing_prefs", 0).edit();
        editor.putInt((String)object, n);
        editor.apply();
    }

    public static boolean a(Context context) {
        return MagicPreferences.b(context, "FEED_CONNECT_RECOMMENDATION_DISMISSED", false);
    }

    public static float b(Context context, String string2, float f) {
        return context.getApplicationContext().getSharedPreferences("sing_prefs", 0).getFloat(string2, f);
    }

    public static int b(Context context, String string2, int n) {
        return context.getApplicationContext().getSharedPreferences("sing_prefs", 0).getInt(string2, n);
    }

    public static long b(Context context, String string2, long l) {
        return context.getApplicationContext().getSharedPreferences("sing_prefs", 0).getLong(string2, l);
    }

    public static AVQualityPerformanceInfo b(Context object, AVQualityPerformanceInfo aVQualityPerformanceInfo) {
        block5 : {
            if ((object = MagicPreferences.b((Context)object, "AV_SURVEY_LAST_SAVED_PERFORMANCE_INFO", null)) == null) {
                return null;
            }
            try {
                aVQualityPerformanceInfo = (JsonNode)JsonUtils.a().readValue((String)object, JsonNode.class);
                if (aVQualityPerformanceInfo != null) break block5;
            }
            catch (Exception exception) {
                Log.d(a, "Could not parse AVQualityPerformanceInfo from preferences string", exception);
                return null;
            }
            Log.e(a, "Could not parse AVQualityPerformanceInfo from preferences string: " + (String)object);
            return null;
        }
        object = JsonUtils.a((JsonNode)aVQualityPerformanceInfo, AVQualityPerformanceInfo.class);
        return object;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String b(Context object, String string2) {
        String string3 = null;
        if (VideoEffects.b((String)string2) == null) {
            Log.e(a, "videoStyleId cannot be found");
            return string3;
        }
        string3 = MagicPreferences.b((Context)object, "UPLOAD_VIDEO_STYLE_" + string2, null);
        object = string3;
        if (string3 != null) return object;
        return VideoEffects.b((String)string2).b();
    }

    public static String b(Context context, String string2, String string3) {
        return context.getApplicationContext().getSharedPreferences("sing_prefs", 0).getString(string2, string3);
    }

    public static void b(Context context, float f) {
        MagicPreferences.a(context, "LAST_USED_MONITORING_LEVEL", f);
    }

    public static void b(Context context, long l) {
        MagicPreferences.a(context, "AV_QUALITY_SURVEY_TIME", l);
    }

    public static void b(Context context, String string2, VideoEffects.Intensity intensity) {
        MagicPreferences.a(context, "UPLOAD_INTENSITY_VIDEO_STYLE_" + string2, intensity.b());
    }

    public static void b(Context context, boolean bl) {
        MagicPreferences.a(context, "FIND_FRIENDS_BUTTON_PRESSED", bl);
    }

    public static boolean b(Context context) {
        return MagicPreferences.b(context, "FIND_FRIENDS_BUTTON_PRESSED", false);
    }

    public static boolean b(Context context, String string2, boolean bl) {
        return context.getApplicationContext().getSharedPreferences("sing_prefs", 0).getBoolean(string2, bl);
    }

    public static long c(Context context, long l) {
        return MagicPreferences.b(context, "AV_QUALITY_SURVEY_TIME", l);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @NonNull
    public static String c(Context object, String string2) {
        SingServerValues singServerValues = new SingServerValues();
        if (!singServerValues.U()) {
            return "off";
        }
        if (VideoEffects.b((String)string2) == null) {
            Log.e(a, "videoStyleId cannot be found");
            return singServerValues.V();
        }
        string2 = MagicPreferences.b((Context)object, "INTENSITY_VIDEO_STYLE_" + string2, null);
        object = string2;
        if (string2 != null) return object;
        return singServerValues.V();
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void c(@NonNull Context context) {
        String string2;
        String string3 = MagicPreferences.i(context);
        if (TextUtils.isEmpty((CharSequence)string3) || string3.equals(string2 = MagicPreferences.j(context))) {
            return;
        }
        MagicPreferences.e(context, string3);
        Log.c(a, "setActiveLandingScreen for next startup - was:" + string2 + ", is:" + string3);
    }

    public static void c(Context context, String string2, String string3) {
        MagicPreferences.a(context, "VIDEO_STYLE_" + string2, string3);
    }

    public static void c(Context context, boolean bl) {
        MagicPreferences.a(context, "SONG_BOOKMARK_TIP_SHOWN", bl);
    }

    public static BottomNavView.Tab d(@NonNull Context object) {
        if (TextUtils.isEmpty((CharSequence)(object = MagicPreferences.j((Context)object)))) {
            return null;
        }
        try {
            BottomNavView.Tab tab = BottomNavView.Tab.valueOf((String)object);
            return tab;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            Log.d(a, "getActiveLandingScreen -- Could not read " + BottomNavView.Tab.class.getSimpleName() + " value from \"" + (String)object + "\", was enum value renamed?", illegalArgumentException);
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @NonNull
    public static String d(Context object, String string2) {
        SingServerValues singServerValues = new SingServerValues();
        if (!singServerValues.U()) {
            return "off";
        }
        if (VideoEffects.b((String)string2) == null) {
            Log.e(a, "videoStyleId cannot be found");
            return singServerValues.V();
        }
        string2 = MagicPreferences.b((Context)object, "UPLOAD_INTENSITY_VIDEO_STYLE_" + string2, null);
        object = string2;
        if (string2 != null) return object;
        return singServerValues.V();
    }

    public static void d(Context context, String string2, String string3) {
        MagicPreferences.a(context, "UPLOAD_VIDEO_STYLE_" + string2, string3);
    }

    public static void d(Context context, boolean bl) {
        MagicPreferences.a(context, "SONG_BOOKMARK_TUTORIAL_ANIM_SHOWN", bl);
    }

    private static void e(@NonNull Context context, @NonNull String string2) {
        MagicPreferences.a(context, "active_landing_screen_on_app_start_key", string2);
    }

    public static void e(Context context, boolean bl) {
        MagicPreferences.a(context, "CONTINUOUS_PLAY_AUTO_PLAY", bl);
    }

    public static boolean e(Context context) {
        return MagicPreferences.b(context, "SONG_BOOKMARK_TIP_SHOWN", false);
    }

    public static boolean f(Context context) {
        return MagicPreferences.b(context, "SONG_BOOKMARK_TUTORIAL_ANIM_SHOWN", false);
    }

    public static int g(Context context) {
        return MagicPreferences.b(context, "SONG_BOOKMARK_TUTORIAL_COUNTDOWN", -1);
    }

    public static boolean h(Context context) {
        return MagicPreferences.b(context, "CONTINUOUS_PLAY_AUTO_PLAY", true);
    }

    private static String i(@NonNull Context context) {
        return MagicPreferences.b(context, "pending_landing_screen_on_app_start_key", null);
    }

    private static String j(@NonNull Context context) {
        return MagicPreferences.b(context, "active_landing_screen_on_app_start_key", null);
    }
}

