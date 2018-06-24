/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MagicDigest {
    private static final MessageDigest a;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
        }
        catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            messageDigest = null;
        }
        a = messageDigest;
    }

    public static String a(String arrby) {
        if (a == null) {
            return null;
        }
        a.update(arrby.getBytes());
        arrby = a.digest();
        a.reset();
        return MagicDigest.a(arrby);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static String a(byte[] arrby) {
        StringBuilder stringBuilder = new StringBuilder();
        int n = arrby.length;
        int n2 = 0;
        block0 : while (n2 < n) {
            byte by = arrby[n2];
            int n3 = by >>> 4 & 15;
            int n4 = 0;
            do {
                if (n3 >= 0 && n3 <= 9) {
                    stringBuilder.append((char)(n3 + 48));
                } else {
                    stringBuilder.append((char)(n3 - 10 + 97));
                }
                if (n4 >= 1) {
                    ++n2;
                    continue block0;
                }
                ++n4;
                n3 = by & 15;
            } while (true);
            break;
        }
        return stringBuilder.toString();
    }
}

