/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.os.Handler
 *  android.os.Looper
 *  android.widget.Toast
 */
package com.smule.android.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.smule.android.logging.Log;
import com.smule.android.utils.Toaster;
import java.lang.ref.WeakReference;

public class Toaster {
    static WeakReference<Toast> a;
    static  b;
    private static String c;
    private static boolean d;

    static {
        c = Toaster.class.getName();
        d = true;
        b = .b;
    }

    public static void a() {
        Toast toast;
        if (a != null && (toast = a.get()) != null) {
            toast.cancel();
        }
    }

    public static void a(Context context, int n) {
        Toaster.a(context, n, b);
    }

    public static void a(Context context, int n,  duration) {
        Toaster.a(context, n, duration, 0, 0, 0);
    }

    public static void a(Context context, int n,  duration, int n2, int n3, int n4) {
        if (context == null) {
            Log.e(c, "showToast - Context was null");
            return;
        }
        Toaster.a(context, context.getResources().getString(n), duration, n2, n3, n4);
    }

    public static void a(Context context, String string2) {
        Toaster.a(context, string2, b);
    }

    public static void a(Context context, String string2,  duration) {
        Toaster.a(context, string2, duration, 0, 0, 0);
    }

    public static void a(Context context, String string2,  duration, int n, int n2, int n3) {
        if (context == null) {
            Log.e(c, "showToast - Context was null");
            return;
        }
        if (string2 == null) {
            Log.e(c, "showToast - text was null");
            return;
        }
        if (!d) {
            Log.c(c, "showToast - app was not in the foreground: " + string2);
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable(context, string2, duration, n, n2, n3){
            final /* synthetic */ Context a;
            final /* synthetic */ String b;
            final /* synthetic */  c;
            final /* synthetic */ int d;
            final /* synthetic */ int e;
            final /* synthetic */ int f;
            {
                this.a = context;
                this.b = string2;
                this.c = duration;
                this.d = n;
                this.e = n2;
                this.f = n3;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void run() {
                Toaster.a();
                Context context = this.a.getApplicationContext();
                String string2 = this.b;
                int n = this.c == .b ? 1 : 0;
                context = Toast.makeText((Context)context, (java.lang.CharSequence)string2, (int)n);
                if (this.d != 0) {
                    context.setGravity(this.d, this.e, this.f);
                }
                Toaster.a(this.b);
                context.show();
                Toaster.a = new WeakReference<Context>(context);
            }
        });
    }

    static /* synthetic */ void a(String string2) {
        Toaster.b(string2);
    }

    public static void b() {
        Toaster.a();
        d = false;
    }

    private static void b(String string2) {
        Log.b(c, "showing Toast:" + string2);
    }

    public static void c() {
        d = true;
    }

}

