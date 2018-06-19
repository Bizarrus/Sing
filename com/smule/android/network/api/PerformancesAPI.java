package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface PerformancesAPI {
    @POST("/v2/performance/comment")
    @SNP
    Call<NetworkResponse> comment(@Body CommentPerformanceRequest commentPerformanceRequest);

    @POST("/v2/perf/create")
    @SNP
    Call<NetworkResponse> createPerformance(@Body CreatePerfRequest createPerfRequest);

    @POST("/v2/performance/deleteComment")
    @SNP
    Call<NetworkResponse> deleteComments(@Body DeleteCommentsRequest deleteCommentsRequest);

    @POST("/v2/performance/delete")
    @SNP
    Call<NetworkResponse> deletePerformance(@Body DeletePerformanceRequest deletePerformanceRequest);

    @POST("/v2/performance/bookmarkSeed")
    @SNP
    Call<NetworkResponse> getBookmarkSeed(@Body GetBookmarkSeedRequest getBookmarkSeedRequest);

    @POST("/v2/performance/getComments")
    @SNP
    Call<NetworkResponse> getComments(@Body GetCommentsRequest getCommentsRequest);

    @POST("/v2/performance/connected")
    @SNP(allowGuest = true)
    Call<NetworkResponse> getConnectedPerformances(@Body SnpRequest snpRequest);

    @POST("/v2/performance/favorite")
    @SNP(allowGuest = true)
    Call<NetworkResponse> getFavorites(@Body GetFavoritesRequest getFavoritesRequest);

    @POST("/v2/performance/getLoves")
    @SNP
    Call<NetworkResponse> getLoves(@Body GetLovesRequest getLovesRequest);

    @POST("/v2/performance/children")
    @SNP
    Call<NetworkResponse> getPerformanceChildren(@Body GetPerformanceChildrenRequest getPerformanceChildrenRequest);

    @POST("/v2/performance/show")
    @SNP
    Call<NetworkResponse> getPerformanceDetails(@Body GetPerformanceDetailsRequest getPerformanceDetailsRequest);

    @POST("/v2/performance/byKeys")
    @SNP
    Call<NetworkResponse> getPerformanceDetailsByKeys(@Body GetPerformanceDetailsByKeysRequest getPerformanceDetailsByKeysRequest);

    @POST("/v2/performance")
    @SNP
    Call<NetworkResponse> getPerformanceDetailsNoRender(@Body GetPerformanceDetailsRequest getPerformanceDetailsRequest);

    @POST("/v2/performance/list")
    @SNP(allowGuest = true)
    Call<NetworkResponse> getPerformanceList(@Body GetPerformanceListRequest getPerformanceListRequest);

    @POST("/v2/performance/getByPlayer")
    @SNP
    Call<NetworkResponse> getPerformances(@Body GetPerformancesRequest getPerformancesRequest);

    @POST("/v2/performance/byFeed")
    @Deprecated
    @SNP
    Call<NetworkResponse> getPerformancesByFeed(@Body GetPerformancesByFeedRequest getPerformancesByFeedRequest);

    @POST("/v2/performance/byPerformer")
    @SNP(allowGuest = true)
    Call<NetworkResponse> getPerformancesByPerformer(@Body GetPerformancesByPerformerRequest getPerformancesByPerformerRequest);

    @POST("/v2/performance/search/byTag")
    @SNP
    Call<NetworkResponse> getPerformancesByTag(@Body GetPerformancesByTagRequest getPerformancesByTagRequest);

    @POST("/v2/performance/network")
    @SNP
    Call<NetworkResponse> getPerformancesFromMyNetwork(@Body GetPerformancesFromMyNetworkRequest getPerformancesFromMyNetworkRequest);

    @POST("/v2/performance/isBookmarkSeed")
    @SNP
    Call<NetworkResponse> isBookmarkSeed(@Body IsBookmarkSeedRequest isBookmarkSeedRequest);

    @POST("/v2/perf/join")
    @SNP
    Call<NetworkResponse> joinPerformance(@Body JoinPerfRequest joinPerfRequest);

    @POST("/v2/performance/listen")
    @SNP(allowGuest = true)
    Call<NetworkResponse> listen(@Body ListenRequest listenRequest);

    @POST("/v2/performance/listenStart")
    @SNP(allowGuest = true)
    Call<NetworkResponse> listenStart(@Body ListenStartRequest listenStartRequest);

    @POST("/v2/performance/love")
    @SNP
    Call<NetworkResponse> love(@Body LoveRequest loveRequest);

    @POST("/v2/performance/play")
    @SNP(allowGuest = true)
    Call<NetworkResponse> playPerformance(@Body PlayPerformanceRequest playPerformanceRequest);

    @POST("/v2/perf/preupload")
    @SNP
    Call<NetworkResponse> preupload(@Body PreuploadRequest preuploadRequest);

    @POST("/v2/performance/render")
    @SNP
    Call<NetworkResponse> renderPerformance(@Body RenderPerformanceRequest renderPerformanceRequest);

    @POST("/v2/performance/commentReportAbuse")
    @SNP
    Call<NetworkResponse> reportAbuses(@Body ReportAbusesRequest reportAbusesRequest);

    @POST("/v2/performance/reportAbuse")
    @SNP(allowGuest = true)
    Call<NetworkResponse> reportAbusivePerformance(@Body ReportAbusePerformanceRequest reportAbusePerformanceRequest);

    @POST("/v2/performance/bookmarkSeed/update")
    @SNP
    Call<NetworkResponse> updateBookmark(@Body UpdateBookmarkRequest updateBookmarkRequest);

    @POST("/v2/performance/favorite/update")
    @SNP
    Call<NetworkResponse> updateFavorite(@Body UpdateFavoritesRequest updateFavoritesRequest);

    @POST("/v2/perf/update")
    @SNP
    Call<NetworkResponse> updatePerformance(@Body UpdatePerformanceRequest updatePerformanceRequest);
}
