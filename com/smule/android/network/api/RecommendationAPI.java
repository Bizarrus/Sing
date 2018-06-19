package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface RecommendationAPI {
    @POST("/v2/rec/comp")
    @SNP
    Call<NetworkResponse> getRecommendedComps(@Body SnpRequest snpRequest);

    @POST("/v2/rec/comp/locale")
    @SNP(allowGuest = true)
    Call<NetworkResponse> getRecommendedCompsByLocale(@Body GetRecommendedCompsByLocaleRequest getRecommendedCompsByLocaleRequest);

    @POST("/v2/rec/comp/similar")
    @SNP(allowGuest = true)
    Call<NetworkResponse> getRecommendedCompsBySong(@Body GetRecommendedCompsBySongRequest getRecommendedCompsBySongRequest);

    @POST("/v2/rec/songs")
    @SNP(allowGuest = true)
    Call<NetworkResponse> getRecommendedDemographicSongs(@Body SnpRequest snpRequest);

    @POST("/v2/rec/account")
    @SNP
    Call<NetworkResponse> getRecommendedSingers(@Body GetRecommentedSingersRequest getRecommentedSingersRequest);

    @POST("/v2/rec/song/similar")
    @SNP(allowGuest = true)
    Call<NetworkResponse> getRecommendedSongsBySong(@Body GetRecommentdedSongsBySongRequest getRecommentdedSongsBySongRequest);

    @POST("/v2/rec/tsrch")
    @SNP
    Call<NetworkResponse> getRecommendedTrendings(@Body SnpRequest snpRequest);

    @POST("/v2/rec/select")
    @SNP(allowGuest = true, maxRetries = 2)
    Call<NetworkResponse> selectRec(@Body SelectRecRequest selectRecRequest);
}
