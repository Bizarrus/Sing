package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;

class ArrangementManager$1 implements Runnable {
    final /* synthetic */ ArrangementManager$ArrangementVersionCallback f16503a;
    final /* synthetic */ String f16504b;
    final /* synthetic */ Integer f16505c;
    final /* synthetic */ ArrangementManager f16506d;

    ArrangementManager$1(ArrangementManager arrangementManager, ArrangementManager$ArrangementVersionCallback arrangementManager$ArrangementVersionCallback, String str, Integer num) {
        this.f16506d = arrangementManager;
        this.f16503a = arrangementManager$ArrangementVersionCallback;
        this.f16504b = str;
        this.f16505c = num;
    }

    public void run() {
        CoreUtil.m18079a(this.f16503a, this.f16506d.a(this.f16504b, this.f16505c));
    }
}
