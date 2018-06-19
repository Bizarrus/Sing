package com.smule.android.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.smule.android.logging.Log;
import java.lang.ref.WeakReference;

public class Toaster {
    static WeakReference<Toast> f6974a;
    static Duration f6975b = Duration.b;
    private static String f6976c = Toaster.class.getName();
    private static boolean f6977d = true;

    public static void m8430a(Context context, String str) {
        m8431a(context, str, f6975b);
    }

    public static void m8427a(Context context, int i) {
        m8428a(context, i, f6975b);
    }

    public static void m8428a(Context context, int i, Duration duration) {
        m8429a(context, i, duration, 0, 0, 0);
    }

    public static void m8431a(Context context, String str, Duration duration) {
        m8432a(context, str, duration, 0, 0, 0);
    }

    public static void m8429a(Context context, int i, Duration duration, int i2, int i3, int i4) {
        if (context == null) {
            Log.m7776e(f6976c, "showToast - Context was null");
            return;
        }
        m8432a(context, context.getResources().getString(i), duration, i2, i3, i4);
    }

    public static void m8432a(Context context, String str, Duration duration, int i, int i2, int i3) {
        if (context == null) {
            Log.m7776e(f6976c, "showToast - Context was null");
        } else if (str == null) {
            Log.m7776e(f6976c, "showToast - text was null");
        } else if (f6977d) {
            new Handler(Looper.getMainLooper()).post(new 1(context, str, duration, i, i2, i3));
        } else {
            Log.m7772c(f6976c, "showToast - app was not in the foreground: " + str);
        }
    }

    public static void m8426a() {
        if (f6974a != null) {
            Toast toast = (Toast) f6974a.get();
            if (toast != null) {
                toast.cancel();
            }
        }
    }

    public static void m8433b() {
        m8426a();
        f6977d = false;
    }

    public static void m8434c() {
        f6977d = true;
    }
}
