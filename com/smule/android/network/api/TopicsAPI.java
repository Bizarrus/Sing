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
import com.smule.android.network.models.SongbookEntryId;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface TopicsAPI {
    @POST(value="/v2/topic/option")
    @SNP
    public Call<NetworkResponse> getTopicOptions(@Body  var1);

    @POST(value="/v2/topic/select")
    @SNP
    public Call<NetworkResponse> selectTopics(@Body  var1);

    @POST(value="/v2/topic/choose")
    @SNP
    public Call<NetworkResponse> submitChosenTopics(@Body  var1);

}

