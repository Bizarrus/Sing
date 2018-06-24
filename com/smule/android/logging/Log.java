/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  com.crashlytics.android.Crashlytics
 */
package com.smule.android.logging;

import com.crashlytics.android.Crashlytics;
import com.smule.android.logging.MagicCrittercism;

public class Log {
    public static int a(String string2, String string3) {
        Log.a(2, string2, string3);
        return android.util.Log.v((String)string2, (String)string3);
    }

    public static int a(String string2, String string3, Throwable throwable) {
        Log.a(3, string2, string3, throwable);
        return android.util.Log.d((String)string2, (String)string3, (Throwable)throwable);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static void a(int var0, String var1_1, String var2_2) {
        block8 : {
            if (!MagicCrittercism.b()) {
                return;
            }
            var3_3 = "";
            switch (var0) {
                case 2: {
                    var3_3 = "V";
                    ** break;
                }
                case 3: {
                    var3_3 = "D";
                    ** break;
                }
                case 4: {
                    var3_3 = "I";
                    ** break;
                }
                case 5: {
                    var3_3 = "W";
                }
lbl16: // 5 sources:
                default: {
                    break block8;
                }
                case 6: 
            }
            var3_3 = "E";
        }
        Crashlytics.log((String)(var3_3 + "/" + var1_1 + " " + var2_2));
    }

    protected static void a(int n, String string2, String string3, Throwable throwable) {
        String string4 = string3;
        if (throwable != null) {
            string4 = string3 + " exc: " + throwable.getMessage();
        }
        Log.a(n, string2, string4);
    }

    public static int b(String string2, String string3) {
        Log.a(3, string2, string3);
        return android.util.Log.d((String)string2, (String)string3);
    }

    public static int b(String string2, String string3, Throwable throwable) {
        Log.a(4, string2, string3, throwable);
        MagicCrittercism.a(string2 + ": " + string3);
        return android.util.Log.i((String)string2, (String)string3, (Throwable)throwable);
    }

    public static int c(String string2, String string3) {
        Log.a(4, string2, string3);
        MagicCrittercism.a(string2 + ": " + string3);
        return android.util.Log.i((String)string2, (String)string3);
    }

    public static int c(String string2, String string3, Throwable throwable) {
        Log.a(5, string2, string3, throwable);
        MagicCrittercism.a(string2 + ": " + string3);
        return android.util.Log.w((String)string2, (String)string3, (Throwable)throwable);
    }

    public static int d(String string2, String string3) {
        Log.a(5, string2, string3);
        MagicCrittercism.a(string2 + ": " + string3);
        return android.util.Log.w((String)string2, (String)string3);
    }

    public static int d(String string2, String string3, Throwable throwable) {
        Log.a(6, string2, string3, throwable);
        MagicCrittercism.a(string2 + ": " + string3);
        MagicCrittercism.a(throwable);
        return android.util.Log.e((String)string2, (String)string3, (Throwable)throwable);
    }

    public static int e(String string2, String string3) {
        Log.a(6, string2, string3);
        MagicCrittercism.a(string2 + ": " + string3);
        return android.util.Log.e((String)string2, (String)string3);
    }
}

