package com.smule.android.network.managers;

import com.smule.android.network.api.ArrangementAPI$ListSortOrder;
import com.smule.android.network.core.CoreUtil;

class ArrangementManager$4 implements Runnable {
    final /* synthetic */ ArrangementManager$ArrangementVersionLiteListCallback f16514a;
    final /* synthetic */ Integer f16515b;
    final /* synthetic */ Integer f16516c;
    final /* synthetic */ ArrangementAPI$ListSortOrder f16517d;
    final /* synthetic */ ArrangementManager f16518e;

    ArrangementManager$4(ArrangementManager arrangementManager, ArrangementManager$ArrangementVersionLiteListCallback arrangementManager$ArrangementVersionLiteListCallback, Integer num, Integer num2, ArrangementAPI$ListSortOrder arrangementAPI$ListSortOrder) {
        this.f16518e = arrangementManager;
        this.f16514a = arrangementManager$ArrangementVersionLiteListCallback;
        this.f16515b = num;
        this.f16516c = num2;
        this.f16517d = arrangementAPI$ListSortOrder;
    }

    public void run() {
        CoreUtil.m18079a(this.f16514a, this.f16518e.a(this.f16515b, this.f16516c, this.f16517d));
    }
}
