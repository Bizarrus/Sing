package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkResponseCallback;

class PerformanceManager$30 implements Runnable {
    final /* synthetic */ NetworkResponseCallback f16800a;
    final /* synthetic */ String f16801b;
    final /* synthetic */ String f16802c;
    final /* synthetic */ PerformanceManager f16803d;

    public void run() {
        CoreUtil.m18079a(this.f16800a, this.f16803d.a(this.f16801b, this.f16802c));
    }
}
