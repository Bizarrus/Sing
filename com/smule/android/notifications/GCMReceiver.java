package com.smule.android.notifications;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class GCMReceiver extends WakefulBroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        startWakefulService(context, intent.setComponent(new ComponentName(context.getPackageName(), GCMIntentService.class.getName())));
        setResultCode(-1);
    }
}
