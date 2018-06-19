package com.smule.android.network.managers;

import com.smule.android.network.api.PerformancesAPI$GetFavoritesRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.managers.PerformanceManager.PerformancesByPerformerResponse;

class PerformanceManager$17 implements Runnable {
    final /* synthetic */ PerformanceManager$PerformancesByPerformerResponseCallback f16724a;
    final /* synthetic */ long f16725b;
    final /* synthetic */ Integer f16726c;
    final /* synthetic */ Integer f16727d;
    final /* synthetic */ PerformanceManager f16728e;

    PerformanceManager$17(PerformanceManager performanceManager, PerformanceManager$PerformancesByPerformerResponseCallback performanceManager$PerformancesByPerformerResponseCallback, long j, Integer num, Integer num2) {
        this.f16728e = performanceManager;
        this.f16724a = performanceManager$PerformancesByPerformerResponseCallback;
        this.f16725b = j;
        this.f16726c = num;
        this.f16727d = num2;
    }

    public void run() {
        CoreUtil.m18079a(this.f16724a, PerformancesByPerformerResponse.a(NetworkUtils.m18104a(PerformanceManager.a(this.f16728e).getFavorites(new PerformancesAPI$GetFavoritesRequest().setAccountId(Long.valueOf(this.f16725b)).setOffset(this.f16726c).setLimit(this.f16727d)))));
    }
}
