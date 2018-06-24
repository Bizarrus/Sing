/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.utils;

import java.util.TimeZone;

public class TimeUtils {
    public static int a() {
        return TimeZone.getDefault().getRawOffset() / 1000;
    }
}

