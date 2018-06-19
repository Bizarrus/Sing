package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PlaylistAPI$GetPlaylistPerformancesRequest extends SnpRequest {
    public Integer limit;
    public Integer offset;
    public Long playlistId;

    public PlaylistAPI$GetPlaylistPerformancesRequest setPlaylistId(Long l) {
        this.playlistId = l;
        return this;
    }

    public PlaylistAPI$GetPlaylistPerformancesRequest setOffset(Integer num) {
        this.offset = num;
        return this;
    }

    public PlaylistAPI$GetPlaylistPerformancesRequest setLimit(Integer num) {
        this.limit = num;
        return this;
    }
}
