package com.smule.android.network.managers;

import com.smule.android.network.api.PerformancesAPI$ListenStartRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.core.NetworkUtils;

class PerformanceManager$40 implements Runnable {
    final /* synthetic */ String f16845a;
    final /* synthetic */ NetworkResponseCallback f16846b;
    final /* synthetic */ PerformanceManager f16847c;

    PerformanceManager$40(PerformanceManager performanceManager, String str, NetworkResponseCallback networkResponseCallback) {
        this.f16847c = performanceManager;
        this.f16845a = str;
        this.f16846b = networkResponseCallback;
    }

    public void run() {
        NetworkResponse a = NetworkUtils.m18104a(PerformanceManager.a(this.f16847c).listenStart(new PerformancesAPI$ListenStartRequest().setPerformanceKey(this.f16845a)));
        if (this.f16846b != null) {
            CoreUtil.m18079a(this.f16846b, a);
        }
    }
}
