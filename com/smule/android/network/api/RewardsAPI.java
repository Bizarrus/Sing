package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface RewardsAPI {
    @POST("/v2/store/rewardCoins")
    @SNP(allowGuest = true, maxRetries = 2)
    Call<NetworkResponse> rewardCoins(@Body RewardCoinsRequest rewardCoinsRequest);
}
