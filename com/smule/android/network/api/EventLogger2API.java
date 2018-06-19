package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.SNP;

public interface EventLogger2API {
    public static final String TAG = "EventLogger2API";

    @POST("/v2/clientReport")
    @SNP(needsSession = false)
    Call<NetworkResponse> postClientReport(@Query("reportType") String str, @Body ClientReportRequest clientReportRequest);

    @POST("/v2/analytics/el")
    @SNP(needsSession = false)
    Call<NetworkResponse> postEvents(@Body PostEventsRequest postEventsRequest);
}
