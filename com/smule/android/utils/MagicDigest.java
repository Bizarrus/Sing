package com.smule.android.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MagicDigest {
    private static final MessageDigest f17804a;

    static {
        MessageDigest instance;
        try {
            instance = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            instance = null;
        }
        f17804a = instance;
    }

    private static String m19010a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            int i = (b >>> 4) & 15;
            int i2 = 0;
            while (true) {
                if (i < 0 || i > 9) {
                    stringBuilder.append((char) ((i - 10) + 97));
                } else {
                    stringBuilder.append((char) (i + 48));
                }
                int i3 = b & 15;
                i = i2 + 1;
                if (i2 >= 1) {
                    break;
                }
                i2 = i;
                i = i3;
            }
        }
        return stringBuilder.toString();
    }

    public static String m19009a(String str) {
        if (f17804a == null) {
            return null;
        }
        f17804a.update(str.getBytes());
        byte[] digest = f17804a.digest();
        f17804a.reset();
        return m19010a(digest);
    }
}
