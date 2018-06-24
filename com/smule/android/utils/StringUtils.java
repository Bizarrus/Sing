/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.text.TextUtils
 */
package com.smule.android.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

public class StringUtils {
    public static String a(String charSequence) {
        charSequence = new StringBuilder((String)charSequence);
        charSequence.setCharAt(0, Character.toUpperCase(charSequence.charAt(0)));
        return charSequence.toString();
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String a(String string2, int n) {
        if (TextUtils.isEmpty((CharSequence)string2) || string2.length() <= n) {
            return string2;
        }
        if (Character.isHighSurrogate(string2.charAt(n - 1))) {
            return string2.substring(0, n - 1);
        }
        return string2.substring(0, n);
    }

    public static String a(String string2, int n, int n2) {
        if (string2 == null || string2.length() < n + n2) {
            return string2;
        }
        return string2.substring(0, n).trim() + "\u2026";
    }

    @SuppressLint(value={"ValueOfNotAllowedForNumberSubClasses"})
    public static int b(String string2, int n) {
        if (string2 == null) {
            return n;
        }
        try {
            int n2 = Integer.parseInt(string2);
            return n2;
        }
        catch (Exception exception) {
            return n;
        }
    }
}

