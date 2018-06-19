package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface PushTokenAPI {
    @POST("/v2/pushToken/register")
    @SNP(allowGuest = true)
    Call<NetworkResponse> register(@Body RegisterRequest registerRequest);
}
