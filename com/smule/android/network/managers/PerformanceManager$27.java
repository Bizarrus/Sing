package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;

class PerformanceManager$27 implements Runnable {
    final /* synthetic */ PerformanceManager$PerformancePostsResponseCallback f16775a;
    final /* synthetic */ String f16776b;
    final /* synthetic */ Integer f16777c;
    final /* synthetic */ Integer f16778d;
    final /* synthetic */ PerformanceManager f16779e;

    PerformanceManager$27(PerformanceManager performanceManager, PerformanceManager$PerformancePostsResponseCallback performanceManager$PerformancePostsResponseCallback, String str, Integer num, Integer num2) {
        this.f16779e = performanceManager;
        this.f16775a = performanceManager$PerformancePostsResponseCallback;
        this.f16776b = str;
        this.f16777c = num;
        this.f16778d = num2;
    }

    public void run() {
        CoreUtil.m18079a(this.f16775a, this.f16779e.c(this.f16776b, this.f16777c, this.f16778d));
    }
}
