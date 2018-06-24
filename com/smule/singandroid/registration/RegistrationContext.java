package com.smule.singandroid.registration;

import android.app.Activity;
import android.content.Intent;
import com.smule.android.ads.attribution.AdjustAttributionSettings;
import com.smule.android.logging.Analytics.RegistrationAccountType;
import com.smule.android.logging.Analytics.RegistrationFlow;
import com.smule.android.logging.Log;
import com.smule.singandroid.MagicPreferences.OnboardStatus;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.StartupActivity_;
import com.smule.singandroid.utils.NavigationUtils;
import com.smule.singandroid.utils.SingAnalytics;

public class RegistrationContext {
    private static String f7165a = RegistrationContext.class.getName();
    private static boolean f7166b = false;
    private static boolean f7167c = false;
    private static boolean f7168d = false;
    private static boolean f7169e = false;
    private static Runnable f7170f = null;

    public static void m8878a() {
        f7166b = false;
        f7170f = null;
    }

    public static void m8882b() {
    }

    public static void m8884c() {
    }

    public static void m8886d() {
        f7166b = true;
        m8887d(true);
    }

    public static void m8881a(boolean z) {
        f7167c = z;
    }

    public static void m8883b(boolean z) {
        f7168d = z;
    }

    public static void m8885c(boolean z) {
        f7169e = z;
    }

    @Deprecated
    public static boolean m8888e() {
        return f7168d || f7167c || f7166b || f7169e;
    }

    public static boolean m8889f() {
        int i = SingApplication.m8798f().getSharedPreferences("sing_prefs", 0).getInt("ONBOARD_STATUS_KEY", OnboardStatus.a.ordinal());
        try {
            if (OnboardStatus.values()[i] == OnboardStatus.b) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Log.m7776e(f7165a, "Failed to convert onboarding enum: " + i);
            return false;
        }
    }

    public static String m8890g() {
        return SingApplication.m8798f().getSharedPreferences("sing_prefs", 0).getString("ONBOARD_TOPICS_KEY", null);
    }

    public static void m8887d(boolean z) {
        SingApplication.m8798f().getSharedPreferences("sing_prefs", 0).edit().putInt("ONBOARD_STATUS_KEY", z ? OnboardStatus.b.ordinal() : OnboardStatus.a.ordinal()).apply();
    }

    public static void m8880a(Runnable runnable) {
        f7170f = runnable;
    }

    public static void m8879a(Activity activity, boolean z, RegistrationFlow registrationFlow) {
        SingAnalytics.a(z ? RegistrationAccountType.b : RegistrationAccountType.a, registrationFlow);
        if (!z) {
            AdjustAttributionSettings.m7654e();
        }
        if (f7170f != null) {
            activity.finish();
            f7170f.run();
            f7170f = null;
        } else if (activity instanceof NewHandleActivity) {
            Log.m7770b(f7165a, "force starting StartupActivity");
            Intent intent = new Intent(activity, StartupActivity_.class);
            intent.addFlags(268468224);
            activity.startActivity(intent);
            activity.finish();
        } else {
            NavigationUtils.a(activity);
            activity.finish();
        }
    }
}
