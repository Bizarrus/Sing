package com.smule.android.network.managers;

import com.smule.android.network.api.PerformancesAPI$IsBookmarkSeedRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.managers.PerformanceManager.IsBookmarkSeedResponse;

class PerformanceManager$18 implements Runnable {
    final /* synthetic */ PerformanceManager$IsBookmarkSeedResponseCallback f16729a;
    final /* synthetic */ String f16730b;
    final /* synthetic */ PerformanceManager f16731c;

    PerformanceManager$18(PerformanceManager performanceManager, PerformanceManager$IsBookmarkSeedResponseCallback performanceManager$IsBookmarkSeedResponseCallback, String str) {
        this.f16731c = performanceManager;
        this.f16729a = performanceManager$IsBookmarkSeedResponseCallback;
        this.f16730b = str;
    }

    public void run() {
        CoreUtil.m18079a(this.f16729a, IsBookmarkSeedResponse.a(NetworkUtils.m18104a(PerformanceManager.a(this.f16731c).isBookmarkSeed(new PerformancesAPI$IsBookmarkSeedRequest().setPerfKey(this.f16730b)))));
    }
}
