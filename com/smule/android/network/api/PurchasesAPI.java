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
import java.util.Arrays;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface PurchasesAPI {
    @Deprecated
    @POST(value="/v2/store/product/acquire_free")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> acquireFreeProduct(@Body  var1);

    @Deprecated
    @POST(value="/v2/store/product/acquire")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> acquireProduct(@Body  var1);

    @POST(value="/v2/store/product/purchase")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> purchase(@Body  var1);

    @POST(value="/v2/store/coinpack/list")
    @SNP
    public Call<NetworkResponse> retrieveCoinPacks(@Body SnpRequest var1);

    @POST(value="/v2/store/product/reward")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> rewardProduct(@Body  var1);

    @POST(value="/v2/store/coin/spend")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> spendCoins(@Body  var1);

}

