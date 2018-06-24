/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 */
package com.smule.android.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.smule.android.network.core.MagicNetwork;

public class NotificationDismissedReceiver
extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String string2 = intent.getStringExtra("com.smule.NOTIFICATION_TAG_KEY");
        int n = intent.getIntExtra("com.smule.NOTIFICATION_ID_KEY", 0);
        if (string2 != null) {
            MagicNetwork.d().onNotificationDismissed(context, string2, n);
        }
    }
}

