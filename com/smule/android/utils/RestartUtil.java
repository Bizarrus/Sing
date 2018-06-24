/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlarmManager
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.PackageManager
 */
package com.smule.android.utils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.Log;
import com.smule.android.utils.NotificationCenter;

public class RestartUtil {
    private static final String a = RestartUtil.class.getName();

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(Context context) {
        EventLogger2.a().c();
        if (context != null) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null) {
                    if ((packageManager = packageManager.getLaunchIntentForPackage(context.getPackageName())) == null) {
                        Log.e(a, "Was not able to restart application, mStartActivity null");
                        return;
                    }
                    packageManager.addFlags(67108864);
                    packageManager = PendingIntent.getActivity((Context)context, (int)223344, (Intent)packageManager, (int)268435456);
                    ((AlarmManager)context.getSystemService("alarm")).set(1, System.currentTimeMillis() + 100, (PendingIntent)packageManager);
                    if (context instanceof Activity) {
                        ((Activity)context).finish();
                    }
                    NotificationCenter.a().b("APP_RESTART_NOTIFICATION", new Object[0]);
                    System.exit(0);
                    return;
                }
                Log.e(a, "Was not able to restart application, PM null");
                return;
            }
            catch (Exception exception) {
                Log.e(a, "Was not able to restart application");
                return;
            }
        }
        Log.e(a, "Was not able to restart application, Context null");
    }
}

