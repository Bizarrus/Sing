package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.PlaylistManager.PlaylistPerformancesResponse;

public interface PlaylistManager$GetPlaylistPerformancesCallback extends ResponseInterface<PlaylistPerformancesResponse> {
    void handleResponse(PlaylistPerformancesResponse playlistPerformancesResponse);
}
