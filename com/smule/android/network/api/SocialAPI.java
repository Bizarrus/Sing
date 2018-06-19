package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface SocialAPI {
    public static final String FOLLOWEES_UPDATE_CONTEXT_APP = "APP";
    public static final String FOLLOWEES_UPDATE_CONTEXT_FACEBOOK = "FB";

    @POST("/v2/social/activityFeed")
    @SNP
    Call<NetworkResponse> getActivityFeed(@Body GetActivityFeedRequest getActivityFeedRequest);

    @POST("/v2/fb/friend/find")
    @SNP
    Call<NetworkResponse> getFacebookUsers(@Body GetFacebookUsersRequest getFacebookUsersRequest);

    @POST("/v2/social/followee")
    @SNP
    Call<NetworkResponse> getFollowees(@Body GetFolloweesRequest getFolloweesRequest);

    @POST("/v2/social/follower")
    @SNP
    Call<NetworkResponse> getFollowers(@Body GetFollowersRequest getFollowersRequest);

    @POST("/v2/social/myFeed")
    @SNP
    Call<NetworkResponse> getMyFeed(@Body GetMyFeedRequest getMyFeedRequest);

    @POST("/v2/social/isFollowing")
    @SNP
    Call<NetworkResponse> isFollowingAccountIds(@Body IsFollowingAccountIdsRequest isFollowingAccountIdsRequest);

    @POST("/v2/social/notification/activity")
    @SNP
    Call<NetworkResponse> listActivityNotifications(@Body ListActivityNotificationsRequest listActivityNotificationsRequest);

    @POST("/v2/social/feed/list")
    @SNP
    Call<NetworkResponse> listFeed(@Body ListFeedRequest listFeedRequest);

    @POST("/v2/social/notification/lookup")
    @SNP
    Call<NetworkResponse> lookupNotifications(@Body LookupNotificationsRequest lookupNotificationsRequest);

    @POST("/v2/social/followee/update")
    @SNP
    Call<NetworkResponse> updateFollowees(@Body UpdateFolloweesRequest updateFolloweesRequest);
}
