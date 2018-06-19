package com.smule.android.utils;

import android.text.TextUtils;

public class StringUtils {
    public static String m19053a(String str, int i, int i2) {
        return (str == null || str.length() < i + i2) ? str : str.substring(0, i).trim() + "…";
    }

    public static String m19052a(String str, int i) {
        if (TextUtils.isEmpty(str) || str.length() <= i) {
            return str;
        }
        if (Character.isHighSurrogate(str.charAt(i - 1))) {
            return str.substring(0, i - 1);
        }
        return str.substring(0, i);
    }
}
