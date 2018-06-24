/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Build
 */
package com.smule.android.utils;

import android.content.Context;
import android.os.Build;
import java.lang.reflect.Method;

public class HardwareSpecificUtils {
    public static void a(Context context) {
        try {
            if ("samsung".equalsIgnoreCase(Build.MANUFACTURER)) {
                Method method = Class.forName("android.sec.clipboard.ClipboardUIManager").getDeclaredMethod("getInstance", Context.class);
                method.setAccessible(true);
                method.invoke(null, new Object[]{context.getApplicationContext()});
            }
            return;
        }
        catch (Exception exception) {
            return;
        }
    }
}

