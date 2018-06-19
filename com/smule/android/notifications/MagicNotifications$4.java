package com.smule.android.notifications;

import android.os.Handler;
import android.os.Looper;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

class MagicNotifications$4 implements Observer {
    final /* synthetic */ MagicNotifications f17491a;
    private final Handler f17492b = new Handler(Looper.getMainLooper());

    MagicNotifications$4(MagicNotifications magicNotifications) {
        this.f17491a = magicNotifications;
    }

    public void update(Observable observable, Object obj) {
        this.f17492b.removeCallbacks(MagicNotifications.c(this.f17491a));
        this.f17492b.postDelayed(MagicNotifications.c(this.f17491a), TimeUnit.SECONDS.toMillis(5));
    }
}
