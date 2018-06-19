package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface OpenCallAPI {
    @POST("/v2/sing/opencall/get")
    @SNP
    Call<NetworkResponse> getOpenCall(@Body GetOpenCallRequest getOpenCallRequest);

    @POST("/v2/sing/opencall/getByPlayer")
    @SNP
    Call<NetworkResponse> getOpenCallByPlayer(@Body GetOpenCallByPlayerRequest getOpenCallByPlayerRequest);

    @POST("/v2/sing/opencall/getBySong")
    @SNP
    Call<NetworkResponse> getOpenCallBySongUid(@Body GetOpenCallBySongUidRequest getOpenCallBySongUidRequest);

    @POST("/v2/sing/perfOpencall/byPlayer")
    @SNP
    Call<NetworkResponse> getPerformancesAndOpenCallsByPlayer(@Body GetPerformancesAndOpenCallsRequest getPerformancesAndOpenCallsRequest);

    @POST("/v2/sing/opencall/getSuggested")
    @SNP
    Call<NetworkResponse> getSuggestedOpenCalls(@Body SnpRequest snpRequest);

    @POST("/v2/sing/opencall/setPrivacy")
    @SNP
    Call<NetworkResponse> setOpenCallPrivacy(@Body SetOpenCallPrivacyRequest setOpenCallPrivacyRequest);
}
