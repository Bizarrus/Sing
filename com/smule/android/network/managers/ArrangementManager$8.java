package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkResponseCallback;

class ArrangementManager$8 implements Runnable {
    final /* synthetic */ NetworkResponseCallback f16529a;
    final /* synthetic */ String f16530b;
    final /* synthetic */ int f16531c;
    final /* synthetic */ Integer f16532d;
    final /* synthetic */ String f16533e;
    final /* synthetic */ ArrangementManager f16534f;

    ArrangementManager$8(ArrangementManager arrangementManager, NetworkResponseCallback networkResponseCallback, String str, int i, Integer num, String str2) {
        this.f16534f = arrangementManager;
        this.f16529a = networkResponseCallback;
        this.f16530b = str;
        this.f16531c = i;
        this.f16532d = num;
        this.f16533e = str2;
    }

    public void run() {
        CoreUtil.m18079a(this.f16529a, this.f16534f.a(this.f16530b, this.f16531c, this.f16532d, this.f16533e));
    }
}
