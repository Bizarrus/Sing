package com.smule.android.network.managers;

import com.smule.android.network.api.PerformancesAPI$PlayPerformanceRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.core.NetworkUtils;

class PerformanceManager$39 implements Runnable {
    final /* synthetic */ String f16840a;
    final /* synthetic */ NetworkResponseCallback f16841b;
    final /* synthetic */ PerformanceManager f16842c;

    PerformanceManager$39(PerformanceManager performanceManager, String str, NetworkResponseCallback networkResponseCallback) {
        this.f16842c = performanceManager;
        this.f16840a = str;
        this.f16841b = networkResponseCallback;
    }

    public void run() {
        NetworkResponse a = NetworkUtils.m18104a(PerformanceManager.a(this.f16842c).playPerformance(new PerformancesAPI$PlayPerformanceRequest().setPerformanceKey(this.f16840a)));
        if (this.f16841b != null) {
            CoreUtil.m18079a(this.f16841b, a);
        }
    }
}
