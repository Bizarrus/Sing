package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;

class PerformanceManager$24 implements Runnable {
    final /* synthetic */ PerformanceManager$PerformancesResponseCallback f16759a;
    final /* synthetic */ String f16760b;
    final /* synthetic */ Integer f16761c;
    final /* synthetic */ Integer f16762d;
    final /* synthetic */ PerformanceManager f16763e;

    PerformanceManager$24(PerformanceManager performanceManager, PerformanceManager$PerformancesResponseCallback performanceManager$PerformancesResponseCallback, String str, Integer num, Integer num2) {
        this.f16763e = performanceManager;
        this.f16759a = performanceManager$PerformancesResponseCallback;
        this.f16760b = str;
        this.f16761c = num;
        this.f16762d = num2;
    }

    public void run() {
        CoreUtil.m18079a(this.f16759a, this.f16763e.b(this.f16760b, this.f16761c, this.f16762d));
    }
}
