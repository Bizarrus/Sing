/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Looper
 */
package com.smule.android.utils;

import android.os.Handler;
import android.os.Looper;

public class ThreadUtils {
    public static void a() {
    }

    public static void a(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
            return;
        }
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}

