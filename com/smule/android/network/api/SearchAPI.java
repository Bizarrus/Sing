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

public interface SearchAPI {
    @POST(value="/v2/user/search/autocomplete")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getAccounts(@Body  var1);

    @POST(value="/v2/user/search/byHandle")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getUsersForTerm(@Body  var1);

    @POST(value="/v2/search")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> search(@Body  var1);

    @POST(value="/v2/search/autocomplete")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> searchAutoComplete(@Body  var1);

    @POST(value="/v2/search/global")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> searchGlobal(@Body  var1);

    @POST(value="/v2/search/songbook")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> searchSongbook(@Body  var1);

    @POST(value="/v2/search/songbook/autocomplete")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> searchSongbookAutoComplete(@Body  var1);

}

