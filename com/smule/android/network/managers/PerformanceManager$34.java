package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkResponseCallback;
import java.util.ArrayList;

class PerformanceManager$34 implements Runnable {
    final /* synthetic */ NetworkResponseCallback f16818a;
    final /* synthetic */ String f16819b;
    final /* synthetic */ ArrayList f16820c;
    final /* synthetic */ PerformanceManager f16821d;

    public void run() {
        CoreUtil.m18079a(this.f16818a, this.f16821d.b(this.f16819b, this.f16820c));
    }
}
