package com.smule.android.network.managers;

import android.os.SystemClock;
import java.util.Observable;
import java.util.Observer;

class SubscriptionManager$2 implements Observer {
    final /* synthetic */ SubscriptionManager f17162a;

    SubscriptionManager$2(SubscriptionManager subscriptionManager) {
        this.f17162a = subscriptionManager;
    }

    public void update(Observable observable, Object obj) {
        SubscriptionManager.a(this.f17162a, SystemClock.elapsedRealtime());
    }
}
