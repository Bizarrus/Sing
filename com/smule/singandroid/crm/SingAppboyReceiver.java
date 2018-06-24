/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.ActivityNotFoundException
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.os.Bundle
 *  android.support.v4.app.TaskStackBuilder
 *  android.util.Log
 *  com.appboy.push.AppboyNotificationUtils
 *  com.appboy.support.StringUtils
 *  com.smule.singandroid.utils.SingAnalytics
 */
package com.smule.singandroid.crm;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import com.appboy.push.AppboyNotificationUtils;
import com.appboy.support.StringUtils;
import com.smule.android.logging.Log;
import com.smule.singandroid.utils.SingAnalytics;

public class SingAppboyReceiver
extends BroadcastReceiver {
    public static final String a = SingAppboyReceiver.class.getSimpleName();

    private void a(Context context, Intent object) {
        Bundle bundle;
        Bundle bundle2 = bundle = object.getBundleExtra("extra");
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        bundle2.putString("cid", object.getStringExtra("cid"));
        bundle2.putString("source", "Appboy");
        object = object.getStringExtra("uri");
        if (!StringUtils.c((String)object)) {
            android.util.Log.d((String)a, (String)String.format("Found a deep link %s.", object));
            bundle = new Intent("android.intent.action.VIEW", Uri.parse((String)object)).putExtras(bundle2);
            context = TaskStackBuilder.create((Context)context);
            context.addNextIntent((Intent)bundle);
            try {
                context.startActivities(bundle2);
                return;
            }
            catch (ActivityNotFoundException activityNotFoundException) {
                Log.d(a, String.format("Could not find appropriate activity to open for deep link %s.", object));
                return;
            }
        }
        Log.b(a, "Push notification had no deep link.");
    }

    private void a(String string2, Intent object) {
        if (string2 != null) {
            Object object2 = object.getBundleExtra("extra");
            object = object2.getString("context");
            object2 = object2.getString("value");
            if (!(object == null || object.isEmpty() || object2 == null || object2.isEmpty() || string2.toString().isEmpty())) {
                SingAnalytics.a((String)object, (String)object2, (String)string2);
            }
        }
    }

    private boolean a(String string2) {
        boolean bl;
        boolean bl2 = bl = false;
        if (!StringUtils.c((String)string2)) {
            bl2 = bl;
            if (string2.startsWith("http")) {
                bl2 = true;
            }
        }
        return bl2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onReceive(Context context, Intent intent) {
        String string2 = context.getPackageName();
        String string3 = string2 + ".intent.APPBOY_PUSH_RECEIVED";
        string2 = string2 + ".intent.APPBOY_NOTIFICATION_OPENED";
        String string4 = intent.getAction();
        Log.b(a, String.format("Received intent with action %s", string4));
        if (string3.equals(string4)) {
            Log.b(a, "Received push notification: " + (Object)intent);
            if (!AppboyNotificationUtils.d((Bundle)intent.getExtras())) return;
            {
                Log.b(a, "Got uninstall tracking push: " + (Object)intent);
            }
            return;
        }
        if (!string2.equals(string4)) {
            Log.b(a, String.format("Ignoring intent with unsupported action %s", string4));
            return;
        }
        string3 = intent.getStringExtra("uri");
        if (StringUtils.c((String)string3)) return;
        {
            this.a(string3, intent);
            if (this.a(string3)) {
                Log.b(a, "Handling Weblink from push: " + string3);
                this.a(context, intent);
                return;
            }
        }
        Log.b(a, "Handling Deeplink from push: " + string3);
        AppboyNotificationUtils.b((Context)context, (Intent)intent);
    }
}

