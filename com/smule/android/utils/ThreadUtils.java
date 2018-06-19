package com.smule.android.utils;

import android.os.Handler;
import android.os.Looper;

public class ThreadUtils {
    public static void m19058a(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    public static void m19057a() {
    }
}
