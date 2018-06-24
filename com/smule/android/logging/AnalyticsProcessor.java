/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.logging;

import com.smule.android.logging.AnalyticsTimer;
import java.util.HashMap;

public final class AnalyticsProcessor {
    private static final HashMap<String, AnalyticsTimer> a = new HashMap();

    private AnalyticsProcessor() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static AnalyticsTimer a(String object) {
        HashMap<String, AnalyticsTimer> hashMap = a;
        synchronized (hashMap) {
            if (a.containsKey(object)) {
                return a.get(object);
            }
            AnalyticsTimer analyticsTimer = new AnalyticsTimer();
            a.put((String)object, analyticsTimer);
            return analyticsTimer;
        }
    }

    public static void b(String string2) {
        AnalyticsProcessor.a(string2).a();
    }

    public static void c(String string2) {
        AnalyticsProcessor.a(string2).b();
    }

    public static double d(String string2) {
        return AnalyticsProcessor.a(string2).d();
    }
}

