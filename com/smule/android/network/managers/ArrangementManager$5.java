package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;
import java.util.List;

class ArrangementManager$5 implements Runnable {
    final /* synthetic */ ArrangementManager$ArrangementVersionLiteListCallback f16519a;
    final /* synthetic */ List f16520b;
    final /* synthetic */ ArrangementManager f16521c;

    ArrangementManager$5(ArrangementManager arrangementManager, ArrangementManager$ArrangementVersionLiteListCallback arrangementManager$ArrangementVersionLiteListCallback, List list) {
        this.f16521c = arrangementManager;
        this.f16519a = arrangementManager$ArrangementVersionLiteListCallback;
        this.f16520b = list;
    }

    public void run() {
        CoreUtil.m18079a(this.f16519a, this.f16521c.a(this.f16520b));
    }
}
