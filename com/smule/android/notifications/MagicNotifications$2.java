package com.smule.android.notifications;

import android.content.Context;
import android.os.AsyncTask;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.PushTokenManager;

class MagicNotifications$2 extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ GoogleCloudMessaging f17487a;
    final /* synthetic */ Context f17488b;
    final /* synthetic */ MagicNotifications f17489c;

    MagicNotifications$2(MagicNotifications magicNotifications, GoogleCloudMessaging googleCloudMessaging, Context context) {
        this.f17489c = magicNotifications;
        this.f17487a = googleCloudMessaging;
        this.f17488b = context;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m18612a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m18613a((Void) obj);
    }

    protected Void m18612a(Void... voidArr) {
        try {
            String advertisingId = MagicNetwork.d().getAdvertisingId(true);
            if (advertisingId == null) {
                advertisingId = MagicNetwork.d().getDeviceId();
            }
            String register = this.f17487a.register(new String[]{GCMIntentService.m18610a()});
            Log.c(MagicNotifications.d(), "registerInBackground - device registered; registration ID = " + register);
            if (PushTokenManager.m18277a().m18278a(register, advertisingId).c()) {
                Log.c(MagicNotifications.d(), "registerInBackground - network call successful; registration ID = " + register + ", device ID = " + advertisingId);
                MagicNotifications.a(this.f17489c, this.f17488b, MagicNotifications.a(this.f17489c), register, advertisingId);
            }
        } catch (Throwable e) {
            Log.c(MagicNotifications.d(), "registerInBackground - IOException thrown while registering", e);
        } catch (Throwable e2) {
            Log.d(MagicNotifications.d(), "registerInBackground - failed to perform registration", e2);
        }
        return null;
    }

    protected void m18613a(Void voidR) {
        super.onPostExecute(voidR);
        MagicNotifications.b(this.f17489c).set(false);
    }
}
