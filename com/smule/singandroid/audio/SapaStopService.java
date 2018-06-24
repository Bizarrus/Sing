/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Service
 *  android.content.Intent
 *  android.os.IBinder
 *  android.util.AndroidRuntimeException
 *  com.samsung.android.sdk.professionalaudio.SapaService
 */
package com.smule.singandroid.audio;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.AndroidRuntimeException;
import com.samsung.android.sdk.professionalaudio.SapaService;

public class SapaStopService
extends Service {
    public IBinder onBind(Intent intent) {
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void onTaskRemoved(Intent intent) {
        try {
            new SapaService().stop(true);
        }
        catch (AndroidRuntimeException androidRuntimeException) {
        }
        catch (InstantiationException instantiationException) {}
        this.stopSelf();
    }
}

