/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Application
 *  android.app.Application$ActivityLifecycleCallbacks
 *  android.os.Bundle
 */
package com.smule.android.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.utils.SessionRefreshActivityLifecycleCallbacks;
import java.util.concurrent.TimeUnit;

public class SessionRefreshActivityLifecycleCallbacks
implements Application.ActivityLifecycleCallbacks {
    private static String a = SessionRefreshActivityLifecycleCallbacks.class.getSimpleName();
    private static long b = TimeUnit.MINUTES.toSeconds(10);
    private int c = 0;
    private long d = 0;
    private long e = b;

    static /* synthetic */ String a() {
        return a;
    }

    private void b() {
        MagicNetwork.a(new Runnable(this){
            final /* synthetic */ SessionRefreshActivityLifecycleCallbacks a;
            {
                this.a = sessionRefreshActivityLifecycleCallbacks;
            }

            public void run() {
                Log.c(SessionRefreshActivityLifecycleCallbacks.a(), "Starting a new session.");
                MagicNetwork.a().a(true);
            }
        });
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        ++this.c;
        long l = System.currentTimeMillis() / 1000;
        if (this.c == 1 && this.d > 0) {
            Log.c(a, "Application came back from background at " + l + ".");
            if (l > this.d + this.e) {
                this.b();
            }
        }
    }

    public void onActivityStopped(Activity activity) {
        --this.c;
        if (this.c == 0) {
            this.d = System.currentTimeMillis() / 1000;
            Log.c(a, "Application went to background at " + this.d + ".");
        }
    }
}

