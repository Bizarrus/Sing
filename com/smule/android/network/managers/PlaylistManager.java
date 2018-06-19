package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.api.PlaylistAPI;
import com.smule.android.network.api.PlaylistAPI.GetPlaylistPerformancesRequest;
import com.smule.android.network.api.PlaylistAPI.GetPlaylistsRequest;
import com.smule.android.network.api.PlaylistAPI.PlaylistPerformancesGetRequest;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.Playlist;
import com.smule.android.network.models.RecPerformanceIcon;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class PlaylistManager {
    protected static PlaylistManager f6819a;
    private static final String f6820b = PlaylistManager.class.getName();
    private PlaylistAPI f6821c = ((PlaylistAPI) MagicNetwork.m7789a().m7817a(PlaylistAPI.class));

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PlaylistPerformancesGetResponse extends ParsedResponse {
        @JsonProperty("next")
        public Integer next;
        @JsonProperty("recPerformanceIcons")
        public ArrayList<RecPerformanceIcon> recPerformanceIcons;

        static PlaylistPerformancesGetResponse m8035a(NetworkResponse networkResponse) {
            return (PlaylistPerformancesGetResponse) ParsedResponse.m7676a(networkResponse, PlaylistPerformancesGetResponse.class);
        }

        public String toString() {
            return "PlaylistPerformancesGetResponse [mResponse=" + this.a + ", next=" + this.next + ", recPerformanceIcons=" + this.recPerformanceIcons + "]";
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PlaylistPerformancesResponse extends ParsedResponse {
        @JsonProperty("performanceIcons")
        public List<PerformanceV2> performances;

        static PlaylistPerformancesResponse m8036a(NetworkResponse networkResponse) {
            return (PlaylistPerformancesResponse) ParsedResponse.m7676a(networkResponse, PlaylistPerformancesResponse.class);
        }

        public String toString() {
            return "PlaylistPerformancesResponse [mResponse=" + this.a + ", performances=" + this.performances + "]";
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PlaylistsResponse extends ParsedResponse {
        @JsonProperty("playlists")
        public List<Playlist> playlists;

        static PlaylistsResponse m8037a(NetworkResponse networkResponse) {
            return (PlaylistsResponse) ParsedResponse.m7676a(networkResponse, PlaylistsResponse.class);
        }

        public String toString() {
            return "PlaylistsResponse [mResponse=" + this.a + ", playlists=" + this.playlists + "]";
        }
    }

    private PlaylistManager() {
    }

    public static PlaylistManager m8038a() {
        if (f6819a == null) {
            f6819a = new PlaylistManager();
        }
        return f6819a;
    }

    public PlaylistsResponse m8040a(List<Long> list) {
        return PlaylistsResponse.m8037a(NetworkUtils.a(this.f6821c.getPlaylists(new GetPlaylistsRequest().setPlaylistIds(list))));
    }

    public Future<?> m8042a(List<Long> list, GetPlaylistsCallback getPlaylistsCallback) {
        return MagicNetwork.m7790a(new 1(this, getPlaylistsCallback, list));
    }

    @Deprecated
    public PlaylistPerformancesResponse m8039a(long j, int i, int i2) {
        return PlaylistPerformancesResponse.m8036a(NetworkUtils.a(this.f6821c.getPlaylistPerformances(new GetPlaylistPerformancesRequest().setPlaylistId(Long.valueOf(j)).setOffset(Integer.valueOf(i)).setLimit(Integer.valueOf(i2)))));
    }

    public PlaylistPerformancesGetResponse m8043b(long j, int i, int i2) {
        return PlaylistPerformancesGetResponse.m8035a(NetworkUtils.a(this.f6821c.playlistPerformancesGet(new PlaylistPerformancesGetRequest().setPlaylistId(Long.valueOf(j)).setOffset(Integer.valueOf(i)).setLimit(Integer.valueOf(i2)))));
    }

    public Future<?> m8041a(long j, int i, int i2, PlaylistPerformancesGetCallback playlistPerformancesGetCallback) {
        return MagicNetwork.m7790a(new 3(this, playlistPerformancesGetCallback, j, i, i2));
    }
}
