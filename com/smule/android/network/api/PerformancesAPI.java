/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  retrofit2.Call
 *  retrofit2.http.Body
 *  retrofit2.http.POST
 *  retrofit2.http.SNP
 */
package com.smule.android.network.api;

import android.text.TextUtils;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.LocationUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface PerformancesAPI {
    @POST(value="/v2/performance/comment")
    @SNP
    public Call<NetworkResponse> comment(@Body  var1);

    @POST(value="/v2/perf/create")
    @SNP
    public Call<NetworkResponse> createPerformance(@Body  var1);

    @POST(value="/v2/performance/deleteComment")
    @SNP
    public Call<NetworkResponse> deleteComments(@Body  var1);

    @POST(value="/v2/performance/delete")
    @SNP
    public Call<NetworkResponse> deletePerformance(@Body  var1);

    @POST(value="/v2/performance/bookmarkSeed")
    @SNP
    public Call<NetworkResponse> getBookmarkSeed(@Body  var1);

    @POST(value="/v2/performance/getComments")
    @SNP
    public Call<NetworkResponse> getComments(@Body  var1);

    @POST(value="/v2/performance/connected")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getConnectedPerformances(@Body SnpRequest var1);

    @POST(value="/v2/performance/favorite")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getFavorites(@Body  var1);

    @POST(value="/v2/performance/getLoves")
    @SNP
    public Call<NetworkResponse> getLoves(@Body  var1);

    @POST(value="/v2/performance/children")
    @SNP
    public Call<NetworkResponse> getPerformanceChildren(@Body  var1);

    @POST(value="/v2/performance/show")
    @SNP
    public Call<NetworkResponse> getPerformanceDetails(@Body  var1);

    @POST(value="/v2/performance/byKeys")
    @SNP
    public Call<NetworkResponse> getPerformanceDetailsByKeys(@Body  var1);

    @POST(value="/v2/performance")
    @SNP
    public Call<NetworkResponse> getPerformanceDetailsNoRender(@Body  var1);

    @POST(value="/v2/performance/list")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getPerformanceList(@Body  var1);

    @POST(value="/v2/performance/getByPlayer")
    @SNP
    public Call<NetworkResponse> getPerformances(@Body  var1);

    @Deprecated
    @POST(value="/v2/performance/byFeed")
    @SNP
    public Call<NetworkResponse> getPerformancesByFeed(@Body  var1);

    @POST(value="/v2/performance/byPerformer")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getPerformancesByPerformer(@Body  var1);

    @POST(value="/v2/performance/search/byTag")
    @SNP
    public Call<NetworkResponse> getPerformancesByTag(@Body  var1);

    @POST(value="/v2/performance/network")
    @SNP
    public Call<NetworkResponse> getPerformancesFromMyNetwork(@Body  var1);

    @POST(value="/v2/performance/isBookmarkSeed")
    @SNP
    public Call<NetworkResponse> isBookmarkSeed(@Body  var1);

    @POST(value="/v2/perf/join")
    @SNP
    public Call<NetworkResponse> joinPerformance(@Body  var1);

    @POST(value="/v2/performance/listen")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> listen(@Body  var1);

    @POST(value="/v2/performance/listenStart")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> listenStart(@Body  var1);

    @POST(value="/v2/performance/logRecCompletedArr")
    @SNP
    public Call<NetworkResponse> logRecCompletedArr(@Body  var1);

    @POST(value="/v2/performance/love")
    @SNP
    public Call<NetworkResponse> love(@Body  var1);

    @POST(value="/v2/performance/play")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> playPerformance(@Body  var1);

    @POST(value="/v2/perf/preupload")
    @SNP
    public Call<NetworkResponse> preupload(@Body  var1);

    @POST(value="/v2/performance/render")
    @SNP
    public Call<NetworkResponse> renderPerformance(@Body  var1);

    @POST(value="/v2/performance/commentReportAbuse")
    @SNP
    public Call<NetworkResponse> reportAbuses(@Body  var1);

    @POST(value="/v2/performance/reportAbuse")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> reportAbusivePerformance(@Body  var1);

    @POST(value="/v2/performance/bookmarkSeed/update")
    @SNP
    public Call<NetworkResponse> updateBookmark(@Body  var1);

    @POST(value="/v2/performance/favorite/update")
    @SNP
    public Call<NetworkResponse> updateFavorite(@Body  var1);

    @POST(value="/v2/perf/update")
    @SNP
    public Call<NetworkResponse> updatePerformance(@Body  var1);

}

