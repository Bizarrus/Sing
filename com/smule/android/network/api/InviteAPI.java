/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  retrofit2.Call
 *  retrofit2.http.Body
 *  retrofit2.http.POST
 *  retrofit2.http.SNP
 */
package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import java.util.Collection;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface InviteAPI {
    @POST(value="/v2/invite/list")
    @SNP
    public Call<NetworkResponse> listInvites(@Body  var1);

    @POST(value="/v2/invite/me")
    @SNP
    public Call<NetworkResponse> listInvitesFollower(@Body  var1);

    @POST(value="/v2/invite/chat")
    @SNP
    public Call<NetworkResponse> sendChatInvites(@Body  var1);

    @POST(value="/v2/invite/send")
    @SNP
    public Call<NetworkResponse> sendInvites(@Body  var1);

    @POST(value="/v2/invite/follower")
    @SNP
    public Call<NetworkResponse> sendInvitesToAllFollowers(@Body  var1);

}

