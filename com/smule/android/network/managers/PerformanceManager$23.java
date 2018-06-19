package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;

class PerformanceManager$23 implements Runnable {
    final /* synthetic */ PerformanceManager$ConnectedPerformancesResponseCallback f16757a;
    final /* synthetic */ PerformanceManager f16758b;

    PerformanceManager$23(PerformanceManager performanceManager, PerformanceManager$ConnectedPerformancesResponseCallback performanceManager$ConnectedPerformancesResponseCallback) {
        this.f16758b = performanceManager;
        this.f16757a = performanceManager$ConnectedPerformancesResponseCallback;
    }

    public void run() {
        CoreUtil.m18079a(this.f16757a, this.f16758b.f());
    }
}
