package com.smule.android.network.managers;

import com.smule.android.network.api.PerformancesAPI$FillStatus;
import com.smule.android.network.core.CoreUtil;

class PerformanceManager$1 implements Runnable {
    final /* synthetic */ PerformanceManager$PerformancesResponseCallback f16736a;
    final /* synthetic */ PerformancesAPI$FillStatus f16737b;
    final /* synthetic */ int f16738c;
    final /* synthetic */ int f16739d;
    final /* synthetic */ PerformanceManager f16740e;

    public void run() {
        CoreUtil.m18079a(this.f16736a, this.f16740e.a(this.f16737b, this.f16738c, this.f16739d));
    }
}
