/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.api.PlaylistAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.PlaylistManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.Playlist;
import com.smule.android.network.models.RecPerformanceIcon;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import retrofit2.Call;

public class PlaylistManager {
    protected static PlaylistManager a;
    private static final String b;
    private com.smule.android.network.api.PlaylistAPI c = MagicNetwork.a().a(com.smule.android.network.api.PlaylistAPI.class);

    static {
        b = PlaylistManager.class.getName();
    }

    private PlaylistManager() {
    }

    public static PlaylistManager a() {
        if (a == null) {
            a = new PlaylistManager();
        }
        return a;
    }

    @Deprecated
    public PlaylistPerformancesResponse a(long l, int n, int n2) {
        return PlaylistPerformancesResponse.a(NetworkUtils.a(this.c.getPlaylistPerformances(new SnpRequest(){
            public Integer limit;
            public Integer offset;
            public Long playlistId;

            public PlaylistAPI setLimit(Integer n) {
                this.limit = n;
                return this;
            }

            public PlaylistAPI setOffset(Integer n) {
                this.offset = n;
                return this;
            }

            public PlaylistAPI setPlaylistId(Long l) {
                this.playlistId = l;
                return this;
            }
        }.setPlaylistId(l).setOffset(n).setLimit(n2))));
    }

    public PlaylistsResponse a(List<Long> list) {
        return PlaylistsResponse.a(NetworkUtils.a(this.c.getPlaylists(new SnpRequest(){
            public List<Long> playlistIds;

            public PlaylistAPI setPlaylistIds(List<Long> list) {
                this.playlistIds = list;
                return this;
            }
        }.setPlaylistIds(list))));
    }

    public Future<?> a(long l, int n, int n2,  playlistPerformancesGetCallback) {
        return MagicNetwork.a(new Runnable(this, playlistPerformancesGetCallback, l, n, n2){
            final /* synthetic */  a;
            final /* synthetic */ long b;
            final /* synthetic */ int c;
            final /* synthetic */ int d;
            final /* synthetic */ PlaylistManager e;
            {
                this.e = playlistManager;
                this.a = playlistPerformancesGetCallback;
                this.b = l;
                this.c = n;
                this.d = n2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.e.b(this.b, this.c, this.d));
            }
        });
    }

    public Future<?> a(List<Long> list,  getPlaylistsCallback) {
        return MagicNetwork.a(new Runnable(this, getPlaylistsCallback, list){
            final /* synthetic */  a;
            final /* synthetic */ List b;
            final /* synthetic */ PlaylistManager c;
            {
                this.c = playlistManager;
                this.a = getPlaylistsCallback;
                this.b = list;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.c.a(this.b));
            }
        });
    }

    public PlaylistPerformancesGetResponse b(long l, int n, int n2) {
        return PlaylistPerformancesGetResponse.a(NetworkUtils.a(this.c.playlistPerformancesGet(new SnpRequest(){
            public Integer limit;
            public Integer offset;
            public Long playlistId;

            public PlaylistAPI setLimit(Integer n) {
                this.limit = n;
                return this;
            }

            public PlaylistAPI setOffset(Integer n) {
                this.offset = n;
                return this;
            }

            public PlaylistAPI setPlaylistId(Long l) {
                this.playlistId = l;
                return this;
            }
        }.setPlaylistId(l).setOffset(n).setLimit(n2))));
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class PlaylistPerformancesGetResponse
    extends ParsedResponse {
        @JsonProperty(value="next")
        public Integer next;
        @JsonProperty(value="recPerformanceIcons")
        public ArrayList<RecPerformanceIcon> recPerformanceIcons;

        static PlaylistPerformancesGetResponse a(NetworkResponse networkResponse) {
            return PlaylistPerformancesGetResponse.a(networkResponse, PlaylistPerformancesGetResponse.class);
        }

        public String toString() {
            return "PlaylistPerformancesGetResponse [mResponse=" + (Object)((Object)this.a) + ", next=" + this.next + ", recPerformanceIcons=" + this.recPerformanceIcons + "]";
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class PlaylistPerformancesResponse
    extends ParsedResponse {
        @JsonProperty(value="performanceIcons")
        public List<PerformanceV2> performances;

        static PlaylistPerformancesResponse a(NetworkResponse networkResponse) {
            return PlaylistPerformancesResponse.a(networkResponse, PlaylistPerformancesResponse.class);
        }

        public String toString() {
            return "PlaylistPerformancesResponse [mResponse=" + (Object)((Object)this.a) + ", performances=" + this.performances + "]";
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class PlaylistsResponse
    extends ParsedResponse {
        @JsonProperty(value="playlists")
        public List<Playlist> playlists;

        static PlaylistsResponse a(NetworkResponse networkResponse) {
            return PlaylistsResponse.a(networkResponse, PlaylistsResponse.class);
        }

        public String toString() {
            return "PlaylistsResponse [mResponse=" + (Object)((Object)this.a) + ", playlists=" + this.playlists + "]";
        }
    }

}

