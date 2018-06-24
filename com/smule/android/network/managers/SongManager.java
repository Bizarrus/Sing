/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.network.managers;

import com.smule.android.network.api.SongAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.SongManager;
import java.util.concurrent.Future;

@Deprecated
public class SongManager {
    private SongAPI a = MagicNetwork.a().a(SongAPI.class);

    private SongManager() {
    }

    static /* synthetic */ SongAPI a(SongManager songManager) {
        return songManager.a;
    }

    public static SongManager a() {
        return .a;
    }

    public Future<?> a(String string2) {
        return MagicNetwork.a(new Runnable(this, string2){
            final /* synthetic */ String a;
            final /* synthetic */ SongManager b;
            {
                this.b = songManager;
                this.a = string2;
            }

            public void run() {
                com.smule.android.network.core.NetworkUtils.a(SongManager.a(this.b).sendSongPlayed(new com.smule.android.network.core.SnpRequest(){
                    public String songId;

                    public com.smule.android.network.api.SongAPI$SendSongPlayedRequest setSongId(String string2) {
                        this.songId = string2;
                        return this;
                    }
                }.setSongId(this.a)));
            }
        });
    }

}

