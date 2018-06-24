/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 */
package com.smule.android.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.smule.android.logging.Log;
import com.smule.android.notifications.MagicNotifications;

public class AlarmReceiver
extends BroadcastReceiver {
    private static final String a = AlarmReceiver.class.getName();

    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.b(a, "Received " + (Object)intent + " extra " + (Object)bundle);
        if (bundle != null) {
            MagicNotifications.a().a(context, bundle);
        }
    }
}

