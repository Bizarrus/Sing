package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface TopicsAPI {
    @POST("/v2/topic/option")
    @SNP
    Call<NetworkResponse> getTopicOptions(@Body GetTopicOptionsRequest getTopicOptionsRequest);

    @POST("/v2/topic/select")
    @SNP
    Call<NetworkResponse> selectTopics(@Body SelectTopicsRequest selectTopicsRequest);

    @POST("/v2/topic/choose")
    @SNP
    Call<NetworkResponse> submitChosenTopics(@Body SubmitChosenTopicsRequest submitChosenTopicsRequest);
}
