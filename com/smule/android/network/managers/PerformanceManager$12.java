package com.smule.android.network.managers;

import com.smule.android.network.api.PerformancesAPI$RenderType;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkResponseCallback;

class PerformanceManager$12 implements Runnable {
    final /* synthetic */ NetworkResponseCallback f16703a;
    final /* synthetic */ String f16704b;
    final /* synthetic */ PerformancesAPI$RenderType f16705c;
    final /* synthetic */ PerformanceManager f16706d;

    PerformanceManager$12(PerformanceManager performanceManager, NetworkResponseCallback networkResponseCallback, String str, PerformancesAPI$RenderType performancesAPI$RenderType) {
        this.f16706d = performanceManager;
        this.f16703a = networkResponseCallback;
        this.f16704b = str;
        this.f16705c = performancesAPI$RenderType;
    }

    public void run() {
        CoreUtil.m18079a(this.f16703a, this.f16706d.a(this.f16704b, this.f16705c));
    }
}
