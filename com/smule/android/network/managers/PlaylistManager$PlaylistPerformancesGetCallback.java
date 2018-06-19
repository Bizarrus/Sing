package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.PlaylistManager.PlaylistPerformancesGetResponse;

public interface PlaylistManager$PlaylistPerformancesGetCallback extends ResponseInterface<PlaylistPerformancesGetResponse> {
    void handleResponse(PlaylistPerformancesGetResponse playlistPerformancesGetResponse);
}
