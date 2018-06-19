package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;

class PerformanceManager$37 implements Runnable {
    final /* synthetic */ PerformanceManager$PerformanceResponseCallback f16829a;
    final /* synthetic */ String f16830b;
    final /* synthetic */ String f16831c;
    final /* synthetic */ String f16832d;
    final /* synthetic */ Boolean f16833e;
    final /* synthetic */ Boolean f16834f;
    final /* synthetic */ PerformanceManager f16835g;

    PerformanceManager$37(PerformanceManager performanceManager, PerformanceManager$PerformanceResponseCallback performanceManager$PerformanceResponseCallback, String str, String str2, String str3, Boolean bool, Boolean bool2) {
        this.f16835g = performanceManager;
        this.f16829a = performanceManager$PerformanceResponseCallback;
        this.f16830b = str;
        this.f16831c = str2;
        this.f16832d = str3;
        this.f16833e = bool;
        this.f16834f = bool2;
    }

    public void run() {
        CoreUtil.m18079a(this.f16829a, this.f16835g.a(this.f16830b, this.f16831c, this.f16832d, this.f16833e, this.f16834f));
    }
}
