/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  retrofit2.Call
 *  retrofit2.http.Body
 *  retrofit2.http.Header
 *  retrofit2.http.POST
 *  retrofit2.http.SNP
 */
package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface StoreAPI {
    @Deprecated
    @POST(value="/v2/store/song")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getSong(@Header(value="If-None-Match") String var1, @Body  var2);

    @Deprecated
    @POST(value="/v2/store/soundfont")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getSoundfont(@Header(value="If-None-Match") String var1, @Body  var2);

    @Deprecated
    @POST(value="/v2/store/store")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getStore(@Header(value="If-None-Match") String var1, @Body  var2);

    @POST(value="/v2/store/stream/log")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> streamFinished(@Body  var1);

    public static enum ProductType {
        KSONG,
        SONG,
        ARR;
        

        private ProductType() {
        }
    }

}

