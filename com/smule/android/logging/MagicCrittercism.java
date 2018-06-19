package com.smule.android.logging;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.Crashlytics.Builder;
import com.crashlytics.android.core.CrashlyticsCore;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.crittercism.app.Crittercism;
import com.crittercism.app.CrittercismConfig;
import com.smule.SmuleApplication;
import com.smule.android.AppDelegate;
import com.smule.android.AppDelegate.ExternalID;
import com.smule.android.logging.EventLogger2.Event;
import com.smule.android.network.core.MagicNetwork;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import java.util.StringTokenizer;

public class MagicCrittercism extends BaseEventLog2Listener {
    public static final String f6717a = MagicCrittercism.class.getName();
    private static boolean f6718c = false;
    private static boolean f6719d = false;
    private boolean f6720b = true;

    public static boolean m7781a() {
        return SmuleApplication.m7647b() != null && SmuleApplication.m7647b().m7785c();
    }

    public static boolean m7783b() {
        return f6719d;
    }

    public static void m7780a(Throwable th) {
        if (f6719d) {
            Crashlytics.logException(th);
        } else if (m7781a()) {
            Crittercism.logHandledException(th);
        }
    }

    public MagicCrittercism(Context context, int i, boolean z) {
        boolean z2 = !f6718c && m7784b(context, MagicNetwork.m7804d(), i);
        this.f6720b = z2;
        if (m7785c()) {
            CrittercismConfig crittercismConfig = new CrittercismConfig();
            crittercismConfig.setDelaySendingAppLoad(z);
            crittercismConfig.setLogcatReportingEnabled(true);
            crittercismConfig.setNdkCrashReportingEnabled(true);
            crittercismConfig.setServiceMonitoringEnabled(false);
            Crittercism.initialize(context.getApplicationContext(), MagicNetwork.m7804d().getExternalID(ExternalID.a), crittercismConfig);
        }
    }

    public static void m7778a(Context context, AppDelegate appDelegate, int i) {
        f6718c = true;
        f6719d = m7784b(context, appDelegate, i);
        if (f6719d) {
            Crashlytics build = new Builder().core(new CrashlyticsCore.Builder().build()).build();
            Fabric.a(new Fabric.Builder(context).a(new Kit[]{build, new CrashlyticsNdk()}).a());
        }
    }

    public static void m7779a(String str) {
        if (str != null) {
            if (!m7781a() && !f6719d) {
                return;
            }
            if (str.length() > 140) {
                for (String str2 : m7782a(str, 140)) {
                    if (f6719d) {
                        Crashlytics.log(str2);
                    } else {
                        Crittercism.leaveBreadcrumb(str2);
                    }
                }
            } else if (f6719d) {
                Crashlytics.log(str);
            } else {
                Crittercism.leaveBreadcrumb(str);
            }
        }
    }

    public void mo4038a(Event event) {
        m7779a(event.b + ": " + BaseEventLog2Listener.m7723b(event));
    }

    public void mo4037a(Activity activity) {
        m7779a("Activity start: " + m7724c(activity));
    }

    public void mo4039b(Activity activity) {
        m7779a("Activity stop: " + m7724c(activity));
    }

    private boolean m7785c() {
        return this.f6720b;
    }

    private static String[] m7782a(String str, int i) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, " ");
        StringBuilder stringBuilder = new StringBuilder(str.length());
        int i2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            int i3 = i2;
            String nextToken = stringTokenizer.nextToken();
            while (nextToken.length() > i) {
                stringBuilder.append(nextToken.substring(0, i - i3)).append("\n");
                nextToken = nextToken.substring(i - i3);
                i3 = 0;
            }
            if (nextToken.length() + i3 > i) {
                stringBuilder.append("\n");
                i3 = 0;
            }
            stringBuilder.append(nextToken).append(" ");
            i2 = (nextToken.length() + 1) + i3;
        }
        return stringBuilder.toString().split("\n");
    }

    private static boolean m7784b(@NonNull Context context, AppDelegate appDelegate, int i) {
        if (appDelegate != null && Math.abs(appDelegate.getDeviceId().hashCode() % 100) < context.getSharedPreferences("magicCrittercism", 0).getInt("samplePercentage", i)) {
            return true;
        }
        return false;
    }

    public static void m7777a(@NonNull Context context, int i) {
        if (i >= 0 && i <= 100) {
            Editor edit = context.getSharedPreferences("magicCrittercism", 0).edit();
            edit.putInt("samplePercentage", i);
            edit.apply();
        }
    }
}
