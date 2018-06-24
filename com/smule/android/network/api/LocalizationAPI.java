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

public interface LocalizationAPI {
    @POST(value="/v2/loc/resource")
    @SNP(allowGuest=true, deviceInfo=true)
    public Call<NetworkResponse> getPackage(@Body  var1);

    @POST(value="/v2/loc/lang/set")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> setPreferredLanguage(@Body  var1);

}

