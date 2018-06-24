/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.IntentService
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  com.google.android.gms.gcm.GoogleCloudMessaging
 */
package com.smule.android.notifications;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.smule.android.AppDelegate;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.notifications.GCMReceiver;
import com.smule.android.notifications.MagicNotifications;

public class GCMIntentService
extends IntentService {
    public static final String a = GCMIntentService.class.getSimpleName();

    public GCMIntentService() {
        super("GCMIntentService");
    }

    public static String a() {
        String string2 = MagicNetwork.d().getExternalID(AppDelegate.b);
        if (string2 == null) {
            throw new RuntimeException("getSenderId - unable to find valid GCM_SENDER ID");
        }
        return string2;
    }

    private void a(Bundle bundle) {
        MagicNotifications.a().a((Context)this, bundle);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        Bundle bundle = intent.getExtras();
        String string2 = GoogleCloudMessaging.getInstance((Context)this).getMessageType(intent);
        if (!bundle.isEmpty() && !"send_error".equals(string2) && !"deleted_messages".equals(string2) && "gcm".equals(string2)) {
            this.a(bundle);
            Log.c(a, "Received: " + bundle.toString());
        }
        GCMReceiver.completeWakefulIntent((Intent)intent);
    }
}

