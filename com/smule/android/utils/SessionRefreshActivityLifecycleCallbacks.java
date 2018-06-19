package com.smule.android.utils;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import java.util.concurrent.TimeUnit;

public class SessionRefreshActivityLifecycleCallbacks implements ActivityLifecycleCallbacks {
    private static String f6963a = SessionRefreshActivityLifecycleCallbacks.class.getSimpleName();
    private static long f6964b = TimeUnit.MINUTES.toSeconds(10);
    private int f6965c = 0;
    private long f6966d = 0;
    private long f6967e = f6964b;

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        this.f6965c++;
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (this.f6965c == 1 && this.f6966d > 0) {
            Log.m7772c(f6963a, "Application came back from background at " + currentTimeMillis + ".");
            if (currentTimeMillis > this.f6966d + this.f6967e) {
                m8412b();
            }
        }
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
        this.f6965c--;
        if (this.f6965c == 0) {
            this.f6966d = System.currentTimeMillis() / 1000;
            Log.m7772c(f6963a, "Application went to background at " + this.f6966d + ".");
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    private void m8412b() {
        MagicNetwork.m7790a(new 1(this));
    }
}
