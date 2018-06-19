package com.smule.android.network.managers;

import com.smule.android.network.api.SongAPI;
import com.smule.android.network.api.SongAPI$SendSongPlayedRequest;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkUtils;
import java.util.concurrent.Future;

public class SongManager {
    private SongAPI f17084a;

    private static class ThreadSafeSingletonWrapper {
        static final SongManager f17083a = new SongManager();

        private ThreadSafeSingletonWrapper() {
        }
    }

    public static SongManager m18356a() {
        return ThreadSafeSingletonWrapper.f17083a;
    }

    private SongManager() {
        this.f17084a = (SongAPI) MagicNetwork.a().a(SongAPI.class);
    }

    public Future<?> m18357a(final String str) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ SongManager f17082b;

            public void run() {
                NetworkUtils.m18104a(this.f17082b.f17084a.sendSongPlayed(new SongAPI$SendSongPlayedRequest().setSongId(str)));
            }
        });
    }
}
