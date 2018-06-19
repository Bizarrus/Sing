package com.smule.android.notifications;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.smule.android.AppDelegate$ExternalID;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;

public class GCMIntentService extends IntentService {
    public static final String f17484a = GCMIntentService.class.getSimpleName();

    public GCMIntentService() {
        super("GCMIntentService");
    }

    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Bundle extras = intent.getExtras();
            String messageType = GoogleCloudMessaging.getInstance(this).getMessageType(intent);
            if (!(extras.isEmpty() || GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType) || GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType) || !GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType))) {
                m18611a(extras);
                Log.c(f17484a, "Received: " + extras.toString());
            }
            GCMReceiver.completeWakefulIntent(intent);
        }
    }

    private void m18611a(Bundle bundle) {
        MagicNotifications.a().a(this, bundle);
    }

    public static String m18610a() {
        String externalID = MagicNetwork.d().getExternalID(AppDelegate$ExternalID.GCM_SENDER);
        if (externalID != null) {
            return externalID;
        }
        throw new RuntimeException("getSenderId - unable to find valid GCM_SENDER ID");
    }
}
