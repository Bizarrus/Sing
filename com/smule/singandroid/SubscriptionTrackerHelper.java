package com.smule.singandroid;

import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.SubscriptionManager;
import java.util.concurrent.TimeUnit;

public class SubscriptionTrackerHelper {
    public static final String f20402a = SubscriptionTrackerHelper.class.getName();
    protected static SubscriptionTrackerHelper f20403b;

    class C41991 implements Runnable {
        final /* synthetic */ SubscriptionTrackerHelper f20401a;

        C41991(SubscriptionTrackerHelper subscriptionTrackerHelper) {
            this.f20401a = subscriptionTrackerHelper;
        }

        public void run() {
            this.f20401a.m21935c();
        }
    }

    public static synchronized SubscriptionTrackerHelper m21933a() {
        SubscriptionTrackerHelper subscriptionTrackerHelper;
        synchronized (SubscriptionTrackerHelper.class) {
            if (f20403b == null) {
                f20403b = new SubscriptionTrackerHelper();
            }
            subscriptionTrackerHelper = f20403b;
        }
        return subscriptionTrackerHelper;
    }

    private SubscriptionTrackerHelper() {
        MagicNetwork.a(new C41991(this), 1170, 1170, TimeUnit.SECONDS);
    }

    public void m21934b() {
    }

    public void m21935c() {
        SubscriptionManager.a().f();
    }
}
