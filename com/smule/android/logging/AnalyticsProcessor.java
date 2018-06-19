package com.smule.android.logging;

import java.util.HashMap;

public final class AnalyticsProcessor {
    private static final HashMap<String, AnalyticsTimer> f6700a = new HashMap();

    public static AnalyticsTimer m7715a(String str) {
        AnalyticsTimer analyticsTimer;
        synchronized (f6700a) {
            if (f6700a.containsKey(str)) {
                analyticsTimer = (AnalyticsTimer) f6700a.get(str);
            } else {
                analyticsTimer = new AnalyticsTimer();
                f6700a.put(str, analyticsTimer);
            }
        }
        return analyticsTimer;
    }

    public static void m7716b(String str) {
        m7715a(str).a();
    }

    public static void m7717c(String str) {
        m7715a(str).b();
    }

    public static double m7718d(String str) {
        return m7715a(str).d();
    }

    private AnalyticsProcessor() {
    }
}
