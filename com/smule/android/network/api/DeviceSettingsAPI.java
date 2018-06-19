package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface DeviceSettingsAPI {
    @POST("/v2/device/setting")
    @SNP(deviceInfo = true, needsSession = false)
    Call<NetworkResponse> getDeviceSettings(@Body SnpRequest snpRequest);
}
