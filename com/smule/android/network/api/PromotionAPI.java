package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface PromotionAPI {
    @POST("/v2/banner")
    @SNP
    Call<NetworkResponse> getBanners(@Body GetBannersRequest getBannersRequest);

    @POST("/v2/promotion/leaderboard")
    @SNP
    Call<NetworkResponse> getLeaderboard(@Body GetLeaderboardRequest getLeaderboardRequest);

    @POST("/v2/promotion")
    @SNP
    Call<NetworkResponse> getPromotion(@Body GetPromotionRequest getPromotionRequest);
}
