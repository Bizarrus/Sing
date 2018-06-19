package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface CardAPI {
    @POST("/v2/card/create")
    @SNP
    Call<NetworkResponse> createCard(@Body CreateCardRequest createCardRequest);

    @POST("/v2/card/get")
    @SNP
    Call<NetworkResponse> getCard(@Body GetCardRequest getCardRequest);
}
