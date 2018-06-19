package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface InviteAPI {
    @POST("/v2/invite/list")
    @SNP
    Call<NetworkResponse> listInvites(@Body ListInvitesRequest listInvitesRequest);

    @POST("/v2/invite/get")
    @SNP
    Call<NetworkResponse> listInvitesFollower(@Body ListInvitesRequest listInvitesRequest);

    @POST("/v2/invite/chat")
    @SNP
    Call<NetworkResponse> sendChatInvites(@Body SendInvitesRequest sendInvitesRequest);

    @POST("/v2/invite/send")
    @SNP
    Call<NetworkResponse> sendInvites(@Body SendInvitesRequest sendInvitesRequest);

    @POST("/v2/invite/follower")
    @SNP
    Call<NetworkResponse> sendInvitesToAllFollowers(@Body SendInvitesToAllFollowersRequest sendInvitesToAllFollowersRequest);
}
