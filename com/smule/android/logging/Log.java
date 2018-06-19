package com.smule.android.logging;

import com.crashlytics.android.Crashlytics;

public class Log {
    private static void m7769a(int i, String str, String str2) {
        if (MagicCrittercism.m7783b()) {
            String str3 = "";
            switch (i) {
                case 2:
                    str3 = "V";
                    break;
                case 3:
                    str3 = "D";
                    break;
                case 4:
                    str3 = "I";
                    break;
                case 5:
                    str3 = "W";
                    break;
                case 6:
                    str3 = "E";
                    break;
            }
            Crashlytics.log(str3 + "/" + str + " " + str2);
        }
    }

    public static int m7767a(String str, String str2) {
        m7769a(2, str, str2);
        return android.util.Log.v(str, str2);
    }

    public static int m7770b(String str, String str2) {
        m7769a(3, str, str2);
        return android.util.Log.d(str, str2);
    }

    public static int m7768a(String str, String str2, Throwable th) {
        m7769a(3, str, str2);
        return android.util.Log.d(str, str2, th);
    }

    public static int m7772c(String str, String str2) {
        m7769a(4, str, str2);
        MagicCrittercism.m7779a(str + ": " + str2);
        return android.util.Log.i(str, str2);
    }

    public static int m7771b(String str, String str2, Throwable th) {
        m7769a(4, str, str2);
        MagicCrittercism.m7779a(str + ": " + str2);
        return android.util.Log.i(str, str2, th);
    }

    public static int m7774d(String str, String str2) {
        m7769a(5, str, str2);
        MagicCrittercism.m7779a(str + ": " + str2);
        return android.util.Log.w(str, str2);
    }

    public static int m7773c(String str, String str2, Throwable th) {
        m7769a(5, str, str2);
        MagicCrittercism.m7779a(str + ": " + str2);
        return android.util.Log.w(str, str2, th);
    }

    public static int m7776e(String str, String str2) {
        m7769a(6, str, str2);
        MagicCrittercism.m7779a(str + ": " + str2);
        return android.util.Log.e(str, str2);
    }

    public static int m7775d(String str, String str2, Throwable th) {
        m7769a(6, str, str2);
        MagicCrittercism.m7779a(str + ": " + str2);
        MagicCrittercism.m7780a(th);
        return android.util.Log.e(str, str2, th);
    }
}
