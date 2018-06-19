package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface SparkAPI {
    @POST("/v2/spark/chat/list")
    @SNP
    Call<NetworkResponse> getActiveChats(@Body GetActiveChatsRequest getActiveChatsRequest);

    @POST("/v2/spark/push/mutestate")
    @SNP
    Call<NetworkResponse> getMuteState(@Body GetMuteStateRequest getMuteStateRequest);

    @POST("/v2/spark/chat/offline")
    @SNP
    Call<NetworkResponse> getOfflineMessageCount(@Body SnpRequest snpRequest);

    @POST("/v2/spark/chat/update")
    @SNP
    Call<NetworkResponse> setActiveChats(@Body SetActiveChatsRequest setActiveChatsRequest);

    @POST("/v2/spark/push/mutestate/update")
    @SNP
    Call<NetworkResponse> setMuteState(@Body SetMuteStateRequest setMuteStateRequest);
}
