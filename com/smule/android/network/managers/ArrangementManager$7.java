package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;

class ArrangementManager$7 implements Runnable {
    final /* synthetic */ ArrangementManager$ArrangementVersionLiteListCallback f16524a;
    final /* synthetic */ long f16525b;
    final /* synthetic */ int f16526c;
    final /* synthetic */ int f16527d;
    final /* synthetic */ ArrangementManager f16528e;

    ArrangementManager$7(ArrangementManager arrangementManager, ArrangementManager$ArrangementVersionLiteListCallback arrangementManager$ArrangementVersionLiteListCallback, long j, int i, int i2) {
        this.f16528e = arrangementManager;
        this.f16524a = arrangementManager$ArrangementVersionLiteListCallback;
        this.f16525b = j;
        this.f16526c = i;
        this.f16527d = i2;
    }

    public void run() {
        CoreUtil.m18079a(this.f16524a, this.f16528e.a(this.f16525b, this.f16526c, this.f16527d));
    }
}
