package com.smule.android.utils;

import android.content.Context;
import android.os.Build;
import java.lang.reflect.Method;

public class HardwareSpecificUtils {
    public static void m8350a(Context context) {
        try {
            if ("samsung".equalsIgnoreCase(Build.MANUFACTURER)) {
                Method declaredMethod = Class.forName("android.sec.clipboard.ClipboardUIManager").getDeclaredMethod("getInstance", new Class[]{Context.class});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(null, new Object[]{context.getApplicationContext()});
            }
        } catch (Exception e) {
        }
    }
}
