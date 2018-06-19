package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface PurchasesAPI {
    @POST("/v2/store/product/acquire_free")
    @SNP(allowGuest = true)
    Call<NetworkResponse> acquireFreeProduct(@Body AcquireListingRequest acquireListingRequest);

    @POST("/v2/store/product/acquire")
    @SNP(allowGuest = true)
    Call<NetworkResponse> acquireProduct(@Body AcquireListingRequest acquireListingRequest);

    @POST("/v2/store/product/purchase")
    @SNP(allowGuest = true)
    Call<NetworkResponse> purchase(@Body PurchaseRequest purchaseRequest);

    @POST("/v2/store/coinpack/list")
    @SNP
    Call<NetworkResponse> retrieveCoinPacks(@Body SnpRequest snpRequest);

    @POST("/v2/store/product/reward")
    @SNP(allowGuest = true)
    Call<NetworkResponse> rewardProduct(@Body RewardProductRequest rewardProductRequest);

    @POST("/v2/store/coin/spend")
    @SNP(allowGuest = true)
    Call<NetworkResponse> spendCoins(@Body SpendCoinsRequest spendCoinsRequest);
}
