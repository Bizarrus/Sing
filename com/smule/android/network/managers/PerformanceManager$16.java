package com.smule.android.network.managers;

import com.smule.android.network.api.PerformancesAPI$UpdateFavoritesRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.core.NetworkUtils;
import java.util.List;

class PerformanceManager$16 implements Runnable {
    final /* synthetic */ NetworkResponseCallback f16720a;
    final /* synthetic */ List f16721b;
    final /* synthetic */ List f16722c;
    final /* synthetic */ PerformanceManager f16723d;

    PerformanceManager$16(PerformanceManager performanceManager, NetworkResponseCallback networkResponseCallback, List list, List list2) {
        this.f16723d = performanceManager;
        this.f16720a = networkResponseCallback;
        this.f16721b = list;
        this.f16722c = list2;
    }

    public void run() {
        CoreUtil.m18079a(this.f16720a, NetworkUtils.m18104a(PerformanceManager.a(this.f16723d).updateFavorite(new PerformancesAPI$UpdateFavoritesRequest().setAdd(this.f16721b).setRemove(this.f16722c))));
    }
}
