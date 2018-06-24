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
import com.smule.android.network.models.SNPChat;
import java.util.Collection;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface SparkAPI {
    @POST(value="/v2/spark/chat/list")
    @SNP
    public Call<NetworkResponse> getActiveChats(@Body  var1);

    @POST(value="/v2/spark/push/mutestate")
    @SNP
    public Call<NetworkResponse> getMuteState(@Body  var1);

    @POST(value="/v2/spark/chat/offline")
    @SNP
    public Call<NetworkResponse> getOfflineMessageCount(@Body SnpRequest var1);

    @POST(value="/v2/spark/chat/update")
    @SNP
    public Call<NetworkResponse> setActiveChats(@Body  var1);

    @POST(value="/v2/spark/push/mutestate/update")
    @SNP
    public Call<NetworkResponse> setMuteState(@Body  var1);

}

