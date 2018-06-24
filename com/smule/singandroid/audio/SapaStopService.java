package com.smule.singandroid.audio;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.AndroidRuntimeException;
import com.samsung.android.sdk.professionalaudio.SapaService;

public class SapaStopService extends Service {
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onTaskRemoved(Intent intent) {
        try {
            new SapaService().stop(true);
        } catch (InstantiationException e) {
        } catch (AndroidRuntimeException e2) {
        }
        stopSelf();
    }
}
