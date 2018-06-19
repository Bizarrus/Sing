package com.smule.android.notifications;

import com.smule.android.network.core.MagicNetwork;

class MagicNotifications$3 implements Runnable {
    final /* synthetic */ MagicNotifications f17490a;

    MagicNotifications$3(MagicNotifications magicNotifications) {
        this.f17490a = magicNotifications;
    }

    public void run() {
        this.f17490a.b(MagicNetwork.d().getApplicationContext());
    }
}
