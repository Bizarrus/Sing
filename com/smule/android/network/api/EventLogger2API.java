/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  retrofit2.Call
 *  retrofit2.http.Body
 *  retrofit2.http.POST
 *  retrofit2.http.Query
 *  retrofit2.http.SNP
 */
package com.smule.android.network.api;

import android.text.TextUtils;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.UserManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.SNP;

public interface EventLogger2API {
    public static final String TAG = "EventLogger2API";

    @POST(value="/v2/clientReport")
    @SNP(needsSession=false)
    public Call<NetworkResponse> postClientReport(@Query(value="reportType") String var1, @Body  var2);

    @POST(value="/v2/analytics/el")
    @SNP(needsSession=false)
    public Call<NetworkResponse> postEvents(@Body  var1);

}

