package com.smule.singandroid.utils;

import android.content.Context;
import android.graphics.Typeface;
import com.smule.singandroid.SingApplication;

public class TypefaceUtils {
    private static Typeface f25104a;
    private static Typeface f25105b;
    private static Typeface f25106c;

    public static Typeface m26188a() {
        return Typeface.createFromAsset(SingApplication.f().getAssets(), "fonts/ProximaNova-Regular.ttf");
    }

    public static Typeface m26191b() {
        return Typeface.createFromAsset(SingApplication.f().getAssets(), "fonts/ProximaNova-Semibold.ttf");
    }

    public static Typeface m26194c() {
        return Typeface.createFromAsset(SingApplication.f().getAssets(), "fonts/ProximaNova-Bold.ttf");
    }

    public static Typeface m26189a(Context context) {
        return m26190a(context, false);
    }

    public static Typeface m26190a(Context context, boolean z) {
        if (f25104a == null) {
            if (z) {
                f25104a = Typeface.SANS_SERIF;
            } else {
                f25104a = Typeface.createFromAsset(SingApplication.f().getAssets(), "fonts/ProximaNova-Regular.ttf");
            }
        }
        return f25104a;
    }

    public static Typeface m26192b(Context context) {
        return m26193b(context, false);
    }

    public static Typeface m26193b(Context context, boolean z) {
        if (f25105b == null) {
            if (z) {
                f25105b = Typeface.SANS_SERIF;
            } else {
                f25105b = Typeface.createFromAsset(SingApplication.f().getAssets(), "fonts/ProximaNova-Bold.ttf");
            }
        }
        return f25105b;
    }

    public static Typeface m26195c(Context context) {
        return m26196c(context, false);
    }

    public static Typeface m26196c(Context context, boolean z) {
        if (f25106c == null) {
            if (z) {
                f25106c = Typeface.SANS_SERIF;
            } else {
                f25106c = Typeface.createFromAsset(SingApplication.f().getAssets(), "fonts/ProximaNova-Semibold.ttf");
            }
        }
        return f25106c;
    }
}
