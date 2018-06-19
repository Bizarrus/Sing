package com.smule.android.utils;

import android.os.Build;
import java.util.HashSet;
import java.util.Set;

public class VorgomUtils {
    private static boolean f6983a = false;
    private static boolean f6984b = false;
    private static final Set<String> f6985c = new HashSet();
    private static final byte[] f6986d = new byte[]{(byte) 6, (byte) 0, (byte) 15, (byte) 0, (byte) 18, (byte) 0, (byte) 27, (byte) 0, (byte) 30, (byte) 0, (byte) 39};

    static {
        f6985c.add("79492b0c5a376601ecd5760d7ba2590adaafa15b");
        f6985c.add("29bc39f061bcbf692c86f56a1d9fd9649ccffb0b");
        f6985c.add("30a2bbe3ae62874ee2b59f544e8ad65320107621");
        f6985c.add("bad24a8e7e9f542766ac4f102c3befd2bc7c838f");
        f6985c.add("1f4c5ff5bca12f1db7d51b6cb252177789ad0eef");
        f6985c.add("82b72cad466a7d1147e52834296327cd8d4551ea");
        f6985c.add("2de4b5ca4e6b0fdc4e99a80da8656236831ed2cb");
        f6985c.add("29fdc038bf6e473460af10c0c7f9edceca73c1ea");
        f6985c.add("dc8a7da9bf6a05e0dbb5ee782a3e0938d12e75b5");
        f6985c.add("933636578e1be0dd55a76b85211340eebeee5e54");
        f6985c.add("9fe2c58911666d25b0af1813d124bf4aa8cb52d0");
        f6985c.add("b9a545e2b9b0301ae1e3738ed95d368f1d8cc80e");
    }

    public static int m8439a(int i) {
        for (byte b : f6986d) {
            i = Integer.rotateLeft(b ^ i, 3);
        }
        return i;
    }

    public static boolean m8440a() {
        return false;
    }

    public static int m8442b() {
        return (m8440a() ? 8 : 17) | ((m8439a(3372816) ^ 2049659668) >> 20);
    }

    public static boolean m8443c() {
        if (f6983a) {
            return f6984b;
        }
        String str = Build.TAGS;
        if (str != null && str.contains("test-keys")) {
            return true;
        }
        boolean z = m8441a("/system/bin/which su") || m8441a("/system/xbin/which su") || m8441a("which su");
        f6984b = z;
        f6983a = true;
        return f6984b;
    }

    private static boolean m8441a(String str) {
        try {
            Runtime.getRuntime().exec(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
