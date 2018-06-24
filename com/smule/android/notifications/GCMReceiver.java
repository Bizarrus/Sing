/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.support.v4.content.WakefulBroadcastReceiver
 */
package com.smule.android.notifications;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.smule.android.notifications.GCMIntentService;

public class GCMReceiver
extends WakefulBroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        GCMReceiver.startWakefulService((Context)context, (Intent)intent.setComponent(new ComponentName(context.getPackageName(), GCMIntentService.class.getName())));
        this.setResultCode(-1);
    }
}

