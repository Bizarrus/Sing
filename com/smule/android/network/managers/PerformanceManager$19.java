package com.smule.android.network.managers;

import com.smule.android.network.api.PerformancesAPI$UpdateBookmarkRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.core.NetworkUtils;
import java.util.ArrayList;

class PerformanceManager$19 implements Runnable {
    final /* synthetic */ NetworkResponseCallback f16732a;
    final /* synthetic */ ArrayList f16733b;
    final /* synthetic */ ArrayList f16734c;
    final /* synthetic */ PerformanceManager f16735d;

    PerformanceManager$19(PerformanceManager performanceManager, NetworkResponseCallback networkResponseCallback, ArrayList arrayList, ArrayList arrayList2) {
        this.f16735d = performanceManager;
        this.f16732a = networkResponseCallback;
        this.f16733b = arrayList;
        this.f16734c = arrayList2;
    }

    public void run() {
        CoreUtil.m18079a(this.f16732a, NetworkUtils.m18104a(PerformanceManager.a(this.f16735d).updateBookmark(new PerformancesAPI$UpdateBookmarkRequest().setAdd(this.f16733b).setRemove(this.f16734c))));
    }
}
