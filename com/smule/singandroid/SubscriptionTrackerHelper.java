/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid;

import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.SubscriptionManager;
import java.util.concurrent.TimeUnit;

public class SubscriptionTrackerHelper {
    public static final String a = SubscriptionTrackerHelper.class.getName();
    protected static SubscriptionTrackerHelper b;

    private SubscriptionTrackerHelper() {
        MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                SubscriptionTrackerHelper.this.c();
            }
        }, 1170, 1170, TimeUnit.SECONDS);
    }

    public static SubscriptionTrackerHelper a() {
        synchronized (SubscriptionTrackerHelper.class) {
            if (b == null) {
                b = new SubscriptionTrackerHelper();
            }
            SubscriptionTrackerHelper subscriptionTrackerHelper = b;
            return subscriptionTrackerHelper;
        }
    }

    public void b() {
    }

    public void c() {
        SubscriptionManager.a().f();
    }

}

