/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Build
 */
package com.smule.android.utils;

import android.os.Build;
import java.util.HashSet;
import java.util.Set;

public class VorgomUtils {
    private static boolean a = false;
    private static boolean b = false;
    private static final Set<String> c = new HashSet<String>();
    private static final byte[] d = new byte[]{6, 0, 15, 0, 18, 0, 27, 0, 30, 0, 39};

    static {
        c.add("79492b0c5a376601ecd5760d7ba2590adaafa15b");
        c.add("29bc39f061bcbf692c86f56a1d9fd9649ccffb0b");
        c.add("30a2bbe3ae62874ee2b59f544e8ad65320107621");
        c.add("bad24a8e7e9f542766ac4f102c3befd2bc7c838f");
        c.add("1f4c5ff5bca12f1db7d51b6cb252177789ad0eef");
        c.add("82b72cad466a7d1147e52834296327cd8d4551ea");
        c.add("2de4b5ca4e6b0fdc4e99a80da8656236831ed2cb");
        c.add("29fdc038bf6e473460af10c0c7f9edceca73c1ea");
        c.add("dc8a7da9bf6a05e0dbb5ee782a3e0938d12e75b5");
        c.add("933636578e1be0dd55a76b85211340eebeee5e54");
        c.add("9fe2c58911666d25b0af1813d124bf4aa8cb52d0");
        c.add("b9a545e2b9b0301ae1e3738ed95d368f1d8cc80e");
    }

    public static int a(int n) {
        byte[] arrby = d;
        int n2 = arrby.length;
        int n3 = 0;
        int n4 = n;
        for (n = n3; n < n2; ++n) {
            n4 = Integer.rotateLeft(arrby[n] ^ n4, 3);
        }
        return n4;
    }

    public static boolean a() {
        return false;
    }

    private static boolean a(String string2) {
        try {
            Runtime.getRuntime().exec(string2);
            return true;
        }
        catch (Exception exception) {
            return false;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int b() {
        int n;
        int n2 = VorgomUtils.a(3372816);
        if (VorgomUtils.a()) {
            n = 8;
            do {
                return n | (n2 ^ 2049659668) >> 20;
                break;
            } while (true);
        }
        n = 17;
        return n | (n2 ^ 2049659668) >> 20;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean c() {
        boolean bl = true;
        if (a) {
            return b;
        }
        String string2 = Build.TAGS;
        if (string2 != null) {
            if (string2.contains("test-keys")) return bl;
        }
        bl = VorgomUtils.a("/system/bin/which su") || VorgomUtils.a("/system/xbin/which su") || VorgomUtils.a("which su");
        b = bl;
        a = true;
        return b;
    }
}

