package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkResponseCallback;

class PerformanceManager$36 implements Runnable {
    final /* synthetic */ NetworkResponseCallback f16826a;
    final /* synthetic */ String f16827b;
    final /* synthetic */ PerformanceManager f16828c;

    PerformanceManager$36(PerformanceManager performanceManager, NetworkResponseCallback networkResponseCallback, String str) {
        this.f16828c = performanceManager;
        this.f16826a = networkResponseCallback;
        this.f16827b = str;
    }

    public void run() {
        CoreUtil.m18079a(this.f16826a, this.f16828c.g(this.f16827b));
    }
}
