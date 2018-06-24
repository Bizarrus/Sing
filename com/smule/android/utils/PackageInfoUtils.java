/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.pm.ApplicationInfo
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 */
package com.smule.android.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.smule.android.logging.Log;

public class PackageInfoUtils {
    private static final String a = PackageInfoUtils.class.getName();

    public static int a(Context context) {
        return PackageInfoUtils.e((Context)context).versionCode;
    }

    public static boolean a(Context context, String string2) {
        block4 : {
            context = context.getPackageManager();
            try {
                context = context.getApplicationInfo(string2, 0);
                if (context == null) break block4;
            }
            catch (PackageManager.NameNotFoundException nameNotFoundException) {
                return false;
            }
            Log.b(a, "App " + string2 + " installed in " + context.dataDir);
            return true;
        }
        Log.b(a, "No info for app " + string2);
        return false;
    }

    public static String b(Context context) {
        return PackageInfoUtils.e((Context)context).versionName;
    }

    public static boolean c(Context context) {
        return PackageInfoUtils.a(context, "com.smule.magicpiano");
    }

    public static boolean d(Context context) {
        return PackageInfoUtils.a(context, "com.smule.autorap");
    }

    private static PackageInfo e(Context context) {
        try {
            context = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return context;
        }
        catch (PackageManager.NameNotFoundException nameNotFoundException) {
            throw new RuntimeException("Could not get package name: " + (Object)nameNotFoundException);
        }
    }

    public static class AppPackageInfo {
        private String a = "";
        private String b = "";
        private String c = "";
        private int d = 0;

        public String toString() {
            return this.a + ", " + this.b + ", " + this.c + ", " + this.d;
        }
    }

}

