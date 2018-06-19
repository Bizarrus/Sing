package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.List;

public class PlaylistAPI$GetPlaylistsRequest extends SnpRequest {
    public List<Long> playlistIds;

    public PlaylistAPI$GetPlaylistsRequest setPlaylistIds(List<Long> list) {
        this.playlistIds = list;
        return this;
    }
}
