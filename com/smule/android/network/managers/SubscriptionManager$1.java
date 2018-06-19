package com.smule.android.network.managers;

import java.util.Observable;
import java.util.Observer;

class SubscriptionManager$1 implements Observer {
    final /* synthetic */ SubscriptionManager f17161a;

    SubscriptionManager$1(SubscriptionManager subscriptionManager) {
        this.f17161a = subscriptionManager;
    }

    public void update(Observable observable, Object obj) {
        SubscriptionManager.a(this.f17161a);
    }
}
