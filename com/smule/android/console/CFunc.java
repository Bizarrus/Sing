package com.smule.android.console;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

public class CFunc {
    private static Application f15691a;

    private CFunc() {
    }

    public static void m17514a(Application application) {
        f15691a = application;
    }

    public static Application m17511a() {
        return f15691a;
    }

    public static String m17512a(int i) {
        return f15691a.getString(i);
    }

    public static int m17510a(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return 0;
        }
    }

    public static boolean m17517b(String str) {
        return str == null || str.length() == 0;
    }

    public static String m17515b() {
        try {
            Context a = m17511a();
            return a.getPackageManager().getPackageInfo(a.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static String m17518c() {
        return "setosoft@gmail.com";
    }

    public static String m17516b(int i) {
        return String.valueOf(i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    public static String m17513a(byte[] bArr) {
        return m17516b((((bArr[3] << 24) | (bArr[2] << 16)) | (bArr[1] << 8)) | bArr[0]);
    }
}
