package com.smule.android.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.smule.android.AppDelegate;
import com.smule.android.network.core.MagicNetwork;

public class NotificationDismissedReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(AppDelegate.NOTIFICATION_TAG_KEY);
        int intExtra = intent.getIntExtra(AppDelegate.NOTIFICATION_ID_KEY, 0);
        if (stringExtra != null) {
            MagicNetwork.d().onNotificationDismissed(context, stringExtra, intExtra);
        }
    }
}
