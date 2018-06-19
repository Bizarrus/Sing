package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PlaylistAPI$PlaylistPerformancesGetRequest extends SnpRequest {
    public Integer limit;
    public Integer offset;
    public Long playlistId;

    public PlaylistAPI$PlaylistPerformancesGetRequest setPlaylistId(Long l) {
        this.playlistId = l;
        return this;
    }

    public PlaylistAPI$PlaylistPerformancesGetRequest setOffset(Integer num) {
        this.offset = num;
        return this;
    }

    public PlaylistAPI$PlaylistPerformancesGetRequest setLimit(Integer num) {
        this.limit = num;
        return this;
    }
}
