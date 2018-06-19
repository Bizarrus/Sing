package com.smule.android.network.managers;

import com.smule.android.network.api.PerformancesAPI$GetBookmarkSeedRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.managers.PerformanceManager.PerformancesByPerformerResponse;

class PerformanceManager$20 implements Runnable {
    final /* synthetic */ PerformanceManager$PerformancesByPerformerResponseCallback f16741a;
    final /* synthetic */ long f16742b;
    final /* synthetic */ Integer f16743c;
    final /* synthetic */ Integer f16744d;
    final /* synthetic */ PerformanceManager f16745e;

    PerformanceManager$20(PerformanceManager performanceManager, PerformanceManager$PerformancesByPerformerResponseCallback performanceManager$PerformancesByPerformerResponseCallback, long j, Integer num, Integer num2) {
        this.f16745e = performanceManager;
        this.f16741a = performanceManager$PerformancesByPerformerResponseCallback;
        this.f16742b = j;
        this.f16743c = num;
        this.f16744d = num2;
    }

    public void run() {
        CoreUtil.m18079a(this.f16741a, PerformancesByPerformerResponse.a(NetworkUtils.m18104a(PerformanceManager.a(this.f16745e).getBookmarkSeed(new PerformancesAPI$GetBookmarkSeedRequest().setAccountId(Long.valueOf(this.f16742b)).setOffset(this.f16743c).setLimit(this.f16744d)))));
    }
}
