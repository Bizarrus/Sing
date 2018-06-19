package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkResponseCallback;

class PerformanceManager$32 implements Runnable {
    final /* synthetic */ NetworkResponseCallback f16808a;
    final /* synthetic */ String f16809b;
    final /* synthetic */ PerformanceManager f16810c;

    public void run() {
        CoreUtil.m18079a(this.f16808a, this.f16810c.f(this.f16809b));
    }
}
