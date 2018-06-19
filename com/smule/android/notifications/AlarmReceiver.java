package com.smule.android.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.smule.android.logging.Log;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String f17483a = AlarmReceiver.class.getName();

    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        Log.b(f17483a, "Received " + intent + " extra " + extras);
        if (extras != null) {
            MagicNotifications.a().a(context, extras);
        }
    }
}
