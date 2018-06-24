/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  retrofit2.Call
 *  retrofit2.http.Body
 *  retrofit2.http.Headers
 *  retrofit2.http.POST
 *  retrofit2.http.SNP
 */
package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface SubscriptionAPI {
    @Headers(value={"Cache-control: no-cache", "Pragma: no-cache"})
    @POST(value="/v2/store/subscription/status")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> fetchSubscriptionStatus(@Body SnpRequest var1);

    @POST(value="/v2/store/subscription/update")
    @SNP(allowGuest=true, secure=true)
    public Call<NetworkResponse> reportSubscription(@Body  var1);

}

