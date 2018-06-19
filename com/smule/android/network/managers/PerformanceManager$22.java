package com.smule.android.network.managers;

import com.smule.android.network.api.PerformancesAPI$FillStatus;
import com.smule.android.network.core.CoreUtil;

class PerformanceManager$22 implements Runnable {
    final /* synthetic */ PerformanceManager$PerformancesByPerformerResponseCallback f16749a;
    final /* synthetic */ long f16750b;
    final /* synthetic */ String f16751c;
    final /* synthetic */ PerformancesAPI$FillStatus f16752d;
    final /* synthetic */ Boolean f16753e;
    final /* synthetic */ Integer f16754f;
    final /* synthetic */ Integer f16755g;
    final /* synthetic */ PerformanceManager f16756h;

    PerformanceManager$22(PerformanceManager performanceManager, PerformanceManager$PerformancesByPerformerResponseCallback performanceManager$PerformancesByPerformerResponseCallback, long j, String str, PerformancesAPI$FillStatus performancesAPI$FillStatus, Boolean bool, Integer num, Integer num2) {
        this.f16756h = performanceManager;
        this.f16749a = performanceManager$PerformancesByPerformerResponseCallback;
        this.f16750b = j;
        this.f16751c = str;
        this.f16752d = performancesAPI$FillStatus;
        this.f16753e = bool;
        this.f16754f = num;
        this.f16755g = num2;
    }

    public void run() {
        CoreUtil.m18079a(this.f16749a, this.f16756h.a(this.f16750b, this.f16751c, this.f16752d, this.f16753e, this.f16754f, this.f16755g));
    }
}
