package com.smule.android.utils;

import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;

class SessionRefreshActivityLifecycleCallbacks$1 implements Runnable {
    final /* synthetic */ SessionRefreshActivityLifecycleCallbacks f17836a;

    SessionRefreshActivityLifecycleCallbacks$1(SessionRefreshActivityLifecycleCallbacks sessionRefreshActivityLifecycleCallbacks) {
        this.f17836a = sessionRefreshActivityLifecycleCallbacks;
    }

    public void run() {
        Log.c(SessionRefreshActivityLifecycleCallbacks.a(), "Starting a new session.");
        MagicNetwork.a().a(true);
    }
}
