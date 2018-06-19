package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface LocalizationAPI {
    @POST("/v2/loc/resource")
    @SNP(allowGuest = true, deviceInfo = true)
    Call<NetworkResponse> getPackage(@Body GetPackageRequest getPackageRequest);
}
