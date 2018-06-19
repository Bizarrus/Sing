package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface PlaylistAPI {
    @POST("/v2/playlist")
    @Deprecated
    @SNP
    Call<NetworkResponse> getPlaylistPerformances(@Body GetPlaylistPerformancesRequest getPlaylistPerformancesRequest);

    @POST("/v2/playlist/list")
    @SNP
    Call<NetworkResponse> getPlaylists(@Body GetPlaylistsRequest getPlaylistsRequest);

    @POST("/v2/playlist/get")
    @SNP
    Call<NetworkResponse> playlistPerformancesGet(@Body PlaylistPerformancesGetRequest playlistPerformancesGetRequest);
}
