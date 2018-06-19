package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;

class PlaylistManager$3 implements Runnable {
    final /* synthetic */ PlaylistManager$PlaylistPerformancesGetCallback f16877a;
    final /* synthetic */ long f16878b;
    final /* synthetic */ int f16879c;
    final /* synthetic */ int f16880d;
    final /* synthetic */ PlaylistManager f16881e;

    PlaylistManager$3(PlaylistManager playlistManager, PlaylistManager$PlaylistPerformancesGetCallback playlistManager$PlaylistPerformancesGetCallback, long j, int i, int i2) {
        this.f16881e = playlistManager;
        this.f16877a = playlistManager$PlaylistPerformancesGetCallback;
        this.f16878b = j;
        this.f16879c = i;
        this.f16880d = i2;
    }

    public void run() {
        CoreUtil.m18079a(this.f16877a, this.f16881e.b(this.f16878b, this.f16879c, this.f16880d));
    }
}
