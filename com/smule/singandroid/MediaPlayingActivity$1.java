package com.smule.singandroid;

import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.utils.NavigationUtils;

class MediaPlayingActivity$1 implements Runnable {
    final /* synthetic */ PerformanceV2 f18883a;
    final /* synthetic */ MediaPlayingActivity f18884b;

    MediaPlayingActivity$1(MediaPlayingActivity mediaPlayingActivity, PerformanceV2 performanceV2) {
        this.f18884b = mediaPlayingActivity;
        this.f18883a = performanceV2;
    }

    public void run() {
        NavigationUtils.m25907a(this.f18884b, this.f18883a, null, null, null, true);
    }
}
