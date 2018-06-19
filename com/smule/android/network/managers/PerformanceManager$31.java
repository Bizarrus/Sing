package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkResponseCallback;
import java.util.ArrayList;

class PerformanceManager$31 implements Runnable {
    final /* synthetic */ NetworkResponseCallback f16804a;
    final /* synthetic */ String f16805b;
    final /* synthetic */ ArrayList f16806c;
    final /* synthetic */ PerformanceManager f16807d;

    public void run() {
        CoreUtil.m18079a(this.f16804a, this.f16807d.a(this.f16805b, this.f16806c));
    }
}
