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
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface SongbookAPI {
    @POST(value="/v2/arr/fromrsong")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getArrangementFromRavenSongRequest(@Body  var1);

    @POST(value="/v2/category/list")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getCategoryList(@Body  var1);

    @POST(value="/v2/category")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getCategorySongs(@Body  var1);

    @POST(value="/v2/songbook")
    @SNP(allowGuest=false)
    public Call<NetworkResponse> getSongbook(@Body  var1);

    @POST(value="/v2/songbook/update")
    @SNP(allowGuest=false)
    public Call<NetworkResponse> submitSongbookUpdate(@Body  var1);

}

