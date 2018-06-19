package com.smule.android.network.managers;

import com.smule.android.network.core.MagicNetwork;
import java.util.Observable;
import java.util.Observer;

class SubscriptionManager$3 implements Observer {
    final /* synthetic */ SubscriptionManager f17163a;

    SubscriptionManager$3(SubscriptionManager subscriptionManager) {
        this.f17163a = subscriptionManager;
    }

    public void update(Observable observable, Object obj) {
        Object obj2 = ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) ? 1 : null;
        if ((MagicNetwork.d().supportsGuestSubscriptions() || UserManager.a().z()) && obj2 == null) {
            SubscriptionManager.a().f();
        }
    }
}
