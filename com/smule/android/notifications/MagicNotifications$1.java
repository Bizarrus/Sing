package com.smule.android.notifications;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.smule.android.logging.Log;

class MagicNotifications$1 implements Runnable {
    final /* synthetic */ Context f17485a;
    final /* synthetic */ MagicNotifications f17486b;

    MagicNotifications$1(MagicNotifications magicNotifications, Context context) {
        this.f17486b = magicNotifications;
        this.f17485a = context;
    }

    public void run() {
        if (MagicNotifications.a(this.f17486b, this.f17485a)) {
            GoogleCloudMessaging instance = GoogleCloudMessaging.getInstance(this.f17485a);
            if (MagicNotifications.b(this.f17486b, this.f17485a).isEmpty()) {
                Log.c(MagicNotifications.d(), "register - registering device for push notifications.");
                MagicNotifications.a(this.f17486b, instance, this.f17485a);
                return;
            }
            Log.c(MagicNotifications.d(), "register - registrationId is not empty, so not registering again");
            return;
        }
        String str;
        int isGooglePlayServicesAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this.f17485a);
        switch (isGooglePlayServicesAvailable) {
            case 1:
                str = "Play services are missing";
                break;
            case 2:
                str = "Play services require an update";
                break;
            case 3:
                str = "Play services are disabled";
                break;
            case 9:
                str = "Play services returned 'invalid'";
                break;
            default:
                str = "Unknown response code: " + isGooglePlayServicesAvailable;
                break;
        }
        Log.d(MagicNotifications.d(), "register failed: " + str);
    }
}
