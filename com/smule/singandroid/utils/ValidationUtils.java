package com.smule.singandroid.utils;

import java.util.regex.Pattern;

public class ValidationUtils {
    private static final Pattern f25107a = Pattern.compile("[A-Z0-9_\\.%\\+\\-]+@(?:[A-Z0-9\\.\\-]+\\.)(?:[A-Z]{2,4}|museum|travel)", 2);

    public static boolean m26200a(String str) {
        return str != null && f25107a.matcher(str).matches();
    }
}
