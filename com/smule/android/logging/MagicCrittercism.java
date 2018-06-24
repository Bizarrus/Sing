/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.support.annotation.NonNull
 *  com.crashlytics.android.Crashlytics
 *  com.crashlytics.android.Crashlytics$Builder
 *  com.crashlytics.android.core.CrashlyticsCore
 *  com.crashlytics.android.core.CrashlyticsCore$Builder
 *  com.crashlytics.android.ndk.CrashlyticsNdk
 *  com.crittercism.app.Crittercism
 *  com.crittercism.app.CrittercismConfig
 *  io.fabric.sdk.android.Fabric
 *  io.fabric.sdk.android.Fabric$Builder
 *  io.fabric.sdk.android.Kit
 */
package com.smule.android.logging;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.crittercism.app.Crittercism;
import com.crittercism.app.CrittercismConfig;
import com.smule.SmuleApplication;
import com.smule.android.AppDelegate;
import com.smule.android.logging.BaseEventLog2Listener;
import com.smule.android.logging.EventLogger2;
import com.smule.android.network.core.MagicNetwork;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import java.util.StringTokenizer;

public class MagicCrittercism
extends BaseEventLog2Listener {
    public static final String a = MagicCrittercism.class.getName();
    private static boolean c = false;
    private static boolean d = false;
    private boolean b = true;

    /*
     * Enabled aggressive block sorting
     */
    public MagicCrittercism(Context context, int n, boolean bl) {
        boolean bl2 = !c && MagicCrittercism.b(context, MagicNetwork.d(), n);
        this.b = bl2;
        if (this.c()) {
            CrittercismConfig crittercismConfig = new CrittercismConfig();
            crittercismConfig.setDelaySendingAppLoad(bl);
            crittercismConfig.setLogcatReportingEnabled(true);
            crittercismConfig.setNdkCrashReportingEnabled(true);
            crittercismConfig.setServiceMonitoringEnabled(false);
            Crittercism.initialize((Context)context.getApplicationContext(), (String)MagicNetwork.d().getExternalID(AppDelegate.a), (CrittercismConfig)crittercismConfig);
        }
    }

    public static void a(@NonNull Context context, int n) {
        if (n >= 0 && n <= 100) {
            context = context.getSharedPreferences("magicCrittercism", 0).edit();
            context.putInt("samplePercentage", n);
            context.apply();
        }
    }

    public static void a(Context context, AppDelegate appDelegate, int n) {
        c = true;
        d = MagicCrittercism.b(context, appDelegate, n);
        if (d) {
            appDelegate = new Crashlytics.Builder().core(new CrashlyticsCore.Builder().build()).build();
            Fabric.a((Fabric)new Fabric.Builder(context).a(new Kit[]{appDelegate, new CrashlyticsNdk()}).a());
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(String arrstring) {
        if (arrstring == null || !MagicCrittercism.a() && !d) {
            return;
        }
        if (arrstring.length() <= 140) {
            if (d) {
                Crashlytics.log((String)arrstring);
                return;
            }
            Crittercism.leaveBreadcrumb((String)arrstring);
            return;
        }
        arrstring = MagicCrittercism.a((String)arrstring, 140);
        int n = arrstring.length;
        int n2 = 0;
        while (n2 < n) {
            String string2 = arrstring[n2];
            if (d) {
                Crashlytics.log((String)string2);
            } else {
                Crittercism.leaveBreadcrumb((String)string2);
            }
            ++n2;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(Throwable throwable) {
        if (d) {
            Crashlytics.logException((Throwable)throwable);
            return;
        } else {
            if (!MagicCrittercism.a()) return;
            {
                Crittercism.logHandledException((Throwable)throwable);
                return;
            }
        }
    }

    public static boolean a() {
        if (SmuleApplication.b() != null && SmuleApplication.b().c()) {
            return true;
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static String[] a(String string2, int n) {
        StringTokenizer stringTokenizer = new StringTokenizer(string2, " ");
        StringBuilder stringBuilder = new StringBuilder(string2.length());
        int n2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            int n4;
            int n3;
            block10 : {
                string2 = stringTokenizer.nextToken();
                do {
                    n3 = n2;
                    if (string2.length() + n2 < n) break;
                    n3 = n2;
                    stringBuilder.append(string2.substring(0, n - n2)).append("\n");
                    n3 = n2;
                    string2 = string2.substring(n - n2);
                    n2 = 0;
                    continue;
                    break;
                } while (true);
                n4 = n2;
                n3 = n2;
                try {
                    if (string2.length() + n2 < n) break block10;
                    n3 = n2;
                }
                catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
                    n2 = n3;
                    continue;
                }
                stringBuilder.append("\n");
                n4 = 0;
            }
            n3 = n4;
            stringBuilder.append(string2).append(" ");
            n3 = n4;
            n2 = string2.length();
            n2 = n4 + (n2 + 1);
        }
        return stringBuilder.toString().split("\n");
    }

    public static boolean b() {
        return d;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static boolean b(@NonNull Context context, AppDelegate appDelegate, int n) {
        block3 : {
            block2 : {
                if (appDelegate == null) break block2;
                n = context.getSharedPreferences("magicCrittercism", 0).getInt("samplePercentage", n);
                if (Math.abs(appDelegate.getDeviceId().hashCode() % 100) < n) break block3;
            }
            return false;
        }
        return true;
    }

    private boolean c() {
        return this.b;
    }

    @Override
    public void a(Activity activity) {
        MagicCrittercism.a("Activity start: " + this.c(activity));
    }

    @Override
    public void a(EventLogger2 event) {
        MagicCrittercism.a(event.b + ": " + MagicCrittercism.b(event));
    }

    @Override
    public void b(Activity activity) {
        MagicCrittercism.a("Activity stop: " + this.c(activity));
    }
}

