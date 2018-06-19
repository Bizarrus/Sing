package com.smule.android.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class PackageInfoUtils {
    private static final String f17820a = PackageInfoUtils.class.getName();

    public static class AppPackageInfo {
        private String f17816a = "";
        private String f17817b = "";
        private String f17818c = "";
        private int f17819d = 0;

        public String toString() {
            return this.f17816a + ", " + this.f17817b + ", " + this.f17818c + ", " + this.f17819d;
        }
    }

    private static PackageInfo m19022c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    public static int m19020a(Context context) {
        return m19022c(context).versionCode;
    }

    public static String m19021b(Context context) {
        return m19022c(context).versionName;
    }
}
