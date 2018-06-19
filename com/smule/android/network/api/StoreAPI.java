package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface StoreAPI {
    @POST("/v2/store/song")
    @SNP(allowGuest = true)
    Call<NetworkResponse> getSong(@Header("If-None-Match") String str, @Body GetSongRequest getSongRequest);

    @POST("/v2/store/soundfont")
    @SNP(allowGuest = true)
    Call<NetworkResponse> getSoundfont(@Header("If-None-Match") String str, @Body GetSoundfontRequest getSoundfontRequest);

    @POST("/v2/store/store")
    @SNP(allowGuest = true)
    Call<NetworkResponse> getStore(@Header("If-None-Match") String str, @Body GetStoreRequest getStoreRequest);

    @POST("/v2/store/stream/log")
    @SNP(allowGuest = true)
    Call<NetworkResponse> streamFinished(@Body StreamFinishedRequest streamFinishedRequest);
}
