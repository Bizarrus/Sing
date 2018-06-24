/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  okhttp3.MultipartBody
 *  okhttp3.MultipartBody$Part
 *  retrofit2.Call
 *  retrofit2.http.Body
 *  retrofit2.http.Multipart
 *  retrofit2.http.POST
 *  retrofit2.http.Part
 *  retrofit2.http.SNP
 */
package com.smule.android.network.api;

import com.smule.android.network.core.NetworkConstants;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.SNP;

public interface DebugAPI {
    @POST(value="/v2/debug/preupload")
    @SNP
    public Call<NetworkResponse> preupload(@Body  var1);

    @Multipart
    @POST(value="/v2/debug/upload")
    @SNP(timeout=NetworkConstants.Timeout.VERY_LONG)
    public Call<NetworkResponse> upload(@Part MultipartBody.Part var1, @Part(value="jsonData")  var2);

}

