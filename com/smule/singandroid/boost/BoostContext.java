/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.boost;

import com.smule.android.logging.Analytics;
import com.smule.android.network.managers.BoostManager;
import com.smule.android.network.models.PerformanceV2;

public class BoostContext {
    public boolean a;
    public boolean b;
    public boolean c;
    public PerformanceV2 d;
    public Analytics e;

    public BoostContext(boolean bl, boolean bl2, boolean bl3, PerformanceV2 performanceV2) {
        this.a = bl;
        this.b = bl2;
        this.c = bl3;
        this.d = performanceV2;
        BoostManager.a().a(performanceV2.performanceKey);
    }

    public static class BoostContextBuilder {
        private boolean a = false;
        private boolean b = false;
        private boolean c = false;
        private PerformanceV2 d = null;
    }

}

