package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;

class PerformanceManager$29 implements Runnable {
    final /* synthetic */ PerformanceManager$PerformanceLovesResponseCallback f16785a;
    final /* synthetic */ String f16786b;
    final /* synthetic */ PerformanceManager f16787c;

    PerformanceManager$29(PerformanceManager performanceManager, PerformanceManager$PerformanceLovesResponseCallback performanceManager$PerformanceLovesResponseCallback, String str) {
        this.f16787c = performanceManager;
        this.f16785a = performanceManager$PerformanceLovesResponseCallback;
        this.f16786b = str;
    }

    public void run() {
        CoreUtil.m18079a(this.f16785a, this.f16787c.e(this.f16786b));
    }
}
