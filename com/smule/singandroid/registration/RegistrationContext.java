/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  com.facebook.appevents.AppEventsLogger
 *  com.smule.singandroid.utils.NavigationUtils
 *  com.smule.singandroid.utils.SingAnalytics
 */
package com.smule.singandroid.registration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.facebook.appevents.AppEventsLogger;
import com.smule.android.ads.attribution.AdjustAttributionSettings;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.StartupActivity_;
import com.smule.singandroid.preference.PreferenceKeys;
import com.smule.singandroid.registration.RegistrationCallbacks;
import com.smule.singandroid.registration.WelcomeActivity;
import com.smule.singandroid.utils.NavigationUtils;
import com.smule.singandroid.utils.SingAnalytics;

public class RegistrationContext {
    private static String a = RegistrationContext.class.getName();
    private static boolean b = false;
    private static boolean c = false;
    private static boolean d = false;
    private static boolean e = false;
    private static RegistrationCallbacks.LoggedInCallback f = null;

    public static void a() {
        b = false;
        f = null;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(Activity activity, boolean bl, Analytics registrationFlow) {
        AppEventsLogger appEventsLogger = AppEventsLogger.newLogger((Context)activity);
        Analytics registrationAccountType = bl ? Analytics.b : Analytics.a;
        SingAnalytics.a((AppEventsLogger)appEventsLogger, registrationAccountType, registrationFlow);
        if (!bl) {
            AdjustAttributionSettings.g();
        }
        if (f != null) {
            activity.finish();
            f.a();
            f = null;
            return;
        }
        if (activity instanceof WelcomeActivity) {
            Log.b(a, "force starting StartupActivity");
            registrationFlow = new Intent((Context)activity, StartupActivity_.class);
            registrationFlow.addFlags(268468224);
            activity.startActivity((Intent)registrationFlow);
            activity.finish();
            return;
        }
        NavigationUtils.a((Activity)activity);
        activity.finish();
    }

    public static void a(RegistrationCallbacks.LoggedInCallback loggedInCallback) {
        f = loggedInCallback;
    }

    public static void a(String string2) {
        SingApplication.g().getSharedPreferences("sing_prefs", 0).edit().putString("WELCOME_SPLIT_SCREEN_STATE_KEY", string2).commit();
    }

    public static void a(String string2, String string3, boolean bl, String string4, boolean bl2, boolean bl3) {
        SingApplication.g().getSharedPreferences("sing_prefs", 0).edit().putString("WELCOME_STATE_KEY", string2).putString("WELCOME_HANDLE_KEY", string3).putBoolean("WELCOME_HANDLE_PREFILL_KEY", bl).putString("WELCOME_LOGIN_METHOD_KEY", string4).putBoolean("WELCOME_SHOW_OPT_IN_KEY", bl2).putBoolean("WELCOME_FACEBOOK_PHOTO_AUTO_UPLOADED_KEY", bl3).commit();
    }

    public static void a(boolean bl) {
        c = bl;
    }

    public static void b() {
    }

    public static void b(String string2) {
        SingApplication.g().getSharedPreferences("sing_prefs", 0).edit().putString("WELCOME_PROFILE_PIC_TYPE", string2).commit();
    }

    public static void b(boolean bl) {
        d = bl;
    }

    public static void c() {
    }

    public static void c(boolean bl) {
        e = bl;
    }

    public static void d() {
        b = true;
        RegistrationContext.d(true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void d(boolean bl) {
        SharedPreferences.Editor editor = SingApplication.g().getSharedPreferences("sing_prefs", 0).edit();
        int n = bl ? PreferenceKeys.OnboardStatus.b.ordinal() : PreferenceKeys.OnboardStatus.a.ordinal();
        editor.putInt("ONBOARD_STATUS_KEY", n).apply();
    }

    @Deprecated
    public static boolean e() {
        if (d || c || b || e) {
            return true;
        }
        return false;
    }

    public static String f() {
        return SingApplication.g().getSharedPreferences("sing_prefs", 0).getString("WELCOME_STATE_KEY", null);
    }

    public static String g() {
        return SingApplication.g().getSharedPreferences("sing_prefs", 0).getString("WELCOME_SPLIT_SCREEN_STATE_KEY", null);
    }

    public static String h() {
        return SingApplication.g().getSharedPreferences("sing_prefs", 0).getString("WELCOME_PROFILE_PIC_TYPE", null);
    }

    public static String i() {
        return SingApplication.g().getSharedPreferences("sing_prefs", 0).getString("WELCOME_HANDLE_KEY", null);
    }

    public static boolean j() {
        return SingApplication.g().getSharedPreferences("sing_prefs", 0).getBoolean("WELCOME_HANDLE_PREFILL_KEY", true);
    }

    public static String k() {
        return SingApplication.g().getSharedPreferences("sing_prefs", 0).getString("WELCOME_LOGIN_METHOD_KEY", null);
    }

    public static boolean l() {
        return SingApplication.g().getSharedPreferences("sing_prefs", 0).getBoolean("WELCOME_SHOW_OPT_IN_KEY", false);
    }

    public static boolean m() {
        return SingApplication.g().getSharedPreferences("sing_prefs", 0).getBoolean("WELCOME_FACEBOOK_PHOTO_AUTO_UPLOADED_KEY", false);
    }

    public static boolean n() {
        boolean bl = false;
        int n = SingApplication.g().getSharedPreferences("sing_prefs", 0).getInt("ONBOARD_STATUS_KEY", PreferenceKeys.OnboardStatus.a.ordinal());
        try {
            PreferenceKeys.OnboardStatus onboardStatus = PreferenceKeys.OnboardStatus.values()[n];
            PreferenceKeys.OnboardStatus onboardStatus2 = PreferenceKeys.OnboardStatus.b;
            if (onboardStatus == onboardStatus2) {
                bl = true;
            }
            return bl;
        }
        catch (Exception exception) {
            Log.e(a, "Failed to convert onboarding enum: " + n);
            return false;
        }
    }

    public static String o() {
        return SingApplication.g().getSharedPreferences("sing_prefs", 0).getString("ONBOARD_TOPICS_KEY", null);
    }
}

