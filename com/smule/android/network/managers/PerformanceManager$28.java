package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkResponseCallback;

class PerformanceManager$28 implements Runnable {
    final /* synthetic */ NetworkResponseCallback f16780a;
    final /* synthetic */ String f16781b;
    final /* synthetic */ float f16782c;
    final /* synthetic */ float f16783d;
    final /* synthetic */ PerformanceManager f16784e;

    PerformanceManager$28(PerformanceManager performanceManager, NetworkResponseCallback networkResponseCallback, String str, float f, float f2) {
        this.f16784e = performanceManager;
        this.f16780a = networkResponseCallback;
        this.f16781b = str;
        this.f16782c = f;
        this.f16783d = f2;
    }

    public void run() {
        CoreUtil.m18079a(this.f16780a, this.f16784e.b(this.f16781b, this.f16782c, this.f16783d));
    }
}
