/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.os.RemoteException
 *  com.android.installreferrer.api.InstallReferrerClient
 *  com.android.installreferrer.api.InstallReferrerStateListener
 *  com.android.installreferrer.api.ReferrerDetails
 */
package com.smule.android.ads.attribution;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.RemoteException;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.smule.android.ads.attribution.AdjustAttributionSettings;
import com.smule.android.logging.EventLog2Listener;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.MagicCrittercism;

public class AdjustInstallReferrerLogger
implements InstallReferrerStateListener,
EventLog2Listener {
    private String a;
    private InstallReferrerClient b;
    private Context c;

    AdjustInstallReferrerLogger(String string2, Context context) {
        this.a = string2;
        this.c = context.getApplicationContext();
        if (!this.d()) {
            com.smule.android.logging.EventLogger2.a(this);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private void a() {
        if (this.b == null) {
            this.b = InstallReferrerClient.newBuilder((Context)this.c).build();
            this.b.startConnection((InstallReferrerStateListener)this);
        }
        // MONITOREXIT : this
    }

    private void b() {
        try {
            ReferrerDetails referrerDetails = this.b.getInstallReferrer();
            String string2 = referrerDetails.getInstallReferrer();
            long l = referrerDetails.getReferrerClickTimestampSeconds();
            long l2 = referrerDetails.getInstallBeginTimestampSeconds();
            AdjustAttributionSettings.a(this.a, "sreferrer", string2, "refclkts", String.valueOf(l), "startinstts", String.valueOf(l2));
            this.e();
            return;
        }
        catch (RemoteException remoteException) {
            MagicCrittercism.a((Throwable)remoteException);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void c() {
        synchronized (this) {
            if (this.b != null) {
                this.b.endConnection();
                this.b = null;
            }
            return;
        }
    }

    private boolean d() {
        return this.c.getSharedPreferences("smule-adjust", 0).getBoolean("logged-referrer", false);
    }

    private void e() {
        this.c.getSharedPreferences("smule-adjust", 0).edit().putBoolean("logged-referrer", true).apply();
    }

    @Override
    public void a(Activity activity) {
    }

    @Override
    public void a(EventLogger2 event) {
        if (event.b.equals("app_launch_complete")) {
            this.a();
            com.smule.android.logging.EventLogger2.b(this);
        }
    }

    @Override
    public void b(Activity activity) {
    }

    public void onInstallReferrerServiceDisconnected() {
        this.c();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onInstallReferrerSetupFinished(int n) {
        switch (n) {
            case 0: {
                this.b();
            }
            default: {
                break;
            }
            case 2: 
            case 3: {
                this.e();
            }
        }
        this.c();
    }
}

