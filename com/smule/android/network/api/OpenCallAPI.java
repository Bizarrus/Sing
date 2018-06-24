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
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface OpenCallAPI {
    @POST(value="/v2/sing/opencall/get")
    @SNP
    public Call<NetworkResponse> getOpenCall(@Body  var1);

    @POST(value="/v2/sing/opencall/getByPlayer")
    @SNP
    public Call<NetworkResponse> getOpenCallByPlayer(@Body  var1);

    @POST(value="/v2/sing/opencall/getBySong")
    @SNP
    public Call<NetworkResponse> getOpenCallBySongUid(@Body  var1);

    @POST(value="/v2/sing/perfOpencall/byPlayer")
    @SNP
    public Call<NetworkResponse> getPerformancesAndOpenCallsByPlayer(@Body  var1);

    @POST(value="/v2/sing/opencall/getSuggested")
    @SNP
    public Call<NetworkResponse> getSuggestedOpenCalls(@Body SnpRequest var1);

    @POST(value="/v2/sing/opencall/setPrivacy")
    @SNP
    public Call<NetworkResponse> setOpenCallPrivacy(@Body  var1);

}

