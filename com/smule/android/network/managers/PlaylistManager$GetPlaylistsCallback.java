package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.PlaylistManager.PlaylistsResponse;

public interface PlaylistManager$GetPlaylistsCallback extends ResponseInterface<PlaylistsResponse> {
    void handleResponse(PlaylistsResponse playlistsResponse);
}
