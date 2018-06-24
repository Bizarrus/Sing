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

public interface RecommendationAPI {
    @POST(value="/v2/rec/acct")
    @SNP
    public Call<NetworkResponse> getMergedRecommendedSingers(@Body  var1);

    @Deprecated
    @POST(value="/v2/rec/comp")
    @SNP
    public Call<NetworkResponse> getRecommendedComps(@Body SnpRequest var1);

    @POST(value="/v2/rec/comp/locale")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getRecommendedCompsByLocale(@Body  var1);

    @POST(value="/v2/rec/comp/similar")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getRecommendedCompsBySong(@Body  var1);

    @Deprecated
    @POST(value="/v2/rec/songs")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getRecommendedDemographicSongs(@Body SnpRequest var1);

    @Deprecated
    @POST(value="/v2/rec/account")
    @SNP
    public Call<NetworkResponse> getRecommendedSingers(@Body  var1);

    @Deprecated
    @POST(value="/v2/rec/song/similar")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getRecommendedSongsBySong(@Body  var1);

    @POST(value="/v2/rec/tsrch")
    @SNP
    public Call<NetworkResponse> getRecommendedTrendings(@Body SnpRequest var1);

    @POST(value="/v2/rec/select")
    @SNP(allowGuest=true, maxRetries=2)
    public Call<NetworkResponse> selectRec(@Body  var1);

}

