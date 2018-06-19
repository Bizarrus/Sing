package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;
import java.util.List;

class PlaylistManager$1 implements Runnable {
    final /* synthetic */ PlaylistManager$GetPlaylistsCallback f16869a;
    final /* synthetic */ List f16870b;
    final /* synthetic */ PlaylistManager f16871c;

    PlaylistManager$1(PlaylistManager playlistManager, PlaylistManager$GetPlaylistsCallback playlistManager$GetPlaylistsCallback, List list) {
        this.f16871c = playlistManager;
        this.f16869a = playlistManager$GetPlaylistsCallback;
        this.f16870b = list;
    }

    public void run() {
        CoreUtil.m18079a(this.f16869a, this.f16871c.a(this.f16870b));
    }
}
