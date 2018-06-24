package com.smule.singandroid.video;

import android.os.Build;
import com.samsung.android.sdk.professionalaudio.SapaService.Parameters;

public class VideoUtils {
    public static int m26579a() {
        return 30;
    }

    public static int m26580b() {
        return 1000000;
    }

    public static int m26581c() {
        if (m26582d() || m26583e() || m26584f()) {
            return 360;
        }
        return Parameters.BUFFER_SIZE_480;
    }

    public static boolean m26582d() {
        return Build.MODEL != null && Build.MODEL.equals("Nexus 7");
    }

    public static boolean m26583e() {
        if (Build.BRAND == null || Build.MODEL == null || !Build.BRAND.equalsIgnoreCase("motorola") || (!Build.MODEL.equalsIgnoreCase("xt1052") && !Build.MODEL.equalsIgnoreCase("xt1053") && !Build.MODEL.equalsIgnoreCase("xt1055") && !Build.MODEL.equalsIgnoreCase("xt1056") && !Build.MODEL.equalsIgnoreCase("xt1058") && !Build.MODEL.equalsIgnoreCase("xt1060") && !Build.MODEL.equalsIgnoreCase("xt1030") && !Build.MODEL.equalsIgnoreCase("xt1080"))) {
            return false;
        }
        return true;
    }

    public static boolean m26584f() {
        if (Build.BRAND == null || Build.MODEL == null || !Build.BRAND.equalsIgnoreCase("lge") || (!Build.MODEL.equalsIgnoreCase("LG-E975") && !Build.MODEL.equalsIgnoreCase("LG-E980") && !Build.MODEL.equalsIgnoreCase("LG-E988") && !Build.MODEL.equalsIgnoreCase("LG-F240L") && !Build.MODEL.equalsIgnoreCase("LG-F240K") && !Build.MODEL.equalsIgnoreCase("LG-F240S") && !Build.MODEL.equalsIgnoreCase("LG-F180K") && !Build.MODEL.equalsIgnoreCase("LG-F180L") && !Build.MODEL.equalsIgnoreCase("LG-F180S"))) {
            return false;
        }
        return true;
    }
}
