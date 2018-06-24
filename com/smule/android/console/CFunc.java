/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Application
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 */
package com.smule.android.console;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class CFunc {
    private static Application a;

    private CFunc() {
    }

    public static int a(String string2) {
        try {
            int n = Integer.parseInt(string2);
            return n;
        }
        catch (Exception exception) {
            return 0;
        }
    }

    public static Application a() {
        return a;
    }

    public static String a(int n) {
        return a.getString(n);
    }

    public static String a(byte[] arrby) {
        return CFunc.b(arrby[3] << 24 | arrby[2] << 16 | arrby[1] << 8 | arrby[0]);
    }

    public static void a(Application application) {
        a = application;
    }

    public static String b() {
        try {
            Object object = CFunc.a();
            object = object.getPackageManager().getPackageInfo((String)object.getPackageName(), (int)0).versionName;
            return object;
        }
        catch (PackageManager.NameNotFoundException nameNotFoundException) {
            return null;
        }
    }

    public static String b(int n) {
        return String.valueOf(n & 255) + "." + (n >> 8 & 255) + "." + (n >> 16 & 255) + "." + (n >> 24 & 255);
    }

    public static boolean b(String string2) {
        if (string2 == null || string2.length() == 0) {
            return true;
        }
        return false;
    }

    public static String c() {
        return "setosoft@gmail.com";
    }
}

