package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface SubscriptionAPI {
    @POST("/v2/store/subscription/status")
    @Headers({"Cache-control: no-cache", "Pragma: no-cache"})
    @SNP(allowGuest = true)
    Call<NetworkResponse> fetchSubscriptionStatus(@Body SnpRequest snpRequest);

    @POST("/v2/store/subscription/update")
    @SNP(allowGuest = true, secure = true)
    Call<NetworkResponse> reportSubscription(@Body ReportSubscriptionRequest reportSubscriptionRequest);
}
