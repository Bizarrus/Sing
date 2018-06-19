package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;

class PerformanceManager$11 implements Runnable {
    final /* synthetic */ PerformanceManager$PerformanceResponseCallback f16700a;
    final /* synthetic */ String f16701b;
    final /* synthetic */ PerformanceManager f16702c;

    PerformanceManager$11(PerformanceManager performanceManager, PerformanceManager$PerformanceResponseCallback performanceManager$PerformanceResponseCallback, String str) {
        this.f16702c = performanceManager;
        this.f16700a = performanceManager$PerformanceResponseCallback;
        this.f16701b = str;
    }

    public void run() {
        CoreUtil.m18079a(this.f16700a, this.f16702c.c(this.f16701b));
    }
}
