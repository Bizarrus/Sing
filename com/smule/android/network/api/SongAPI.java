package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface SongAPI {
    @POST("/v2/song/play")
    @SNP(allowGuest = true)
    Call<NetworkResponse> sendSongPlayed(@Body SendSongPlayedRequest sendSongPlayedRequest);
}
