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

import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface SocialAPI {
    public static final String FOLLOWEES_UPDATE_CONTEXT_APP = "APP";
    public static final String FOLLOWEES_UPDATE_CONTEXT_CONTACTS = "CO";
    public static final String FOLLOWEES_UPDATE_CONTEXT_CONTACTS_ONBOARDING = "CO_ONBRD";
    public static final String FOLLOWEES_UPDATE_CONTEXT_FACEBOOK = "FB";
    public static final String FOLLOWEES_UPDATE_CONTEXT_FACEBOOK_ONBOARDING = "FB_ONBRD";
    public static final String FOLLOWEES_UPDATE_CONTEXT_FIND_FRIENDS_MODULE = "FFMD";

    @POST(value="/v2/social/activityFeed")
    @SNP
    public Call<NetworkResponse> getActivityFeed(@Body  var1);

    @POST(value="/v2/fb/friend/find")
    @SNP
    public Call<NetworkResponse> getFacebookUsers(@Body  var1);

    @POST(value="/v2/social/followee")
    @SNP
    public Call<NetworkResponse> getFollowees(@Body  var1);

    @POST(value="/v2/social/follower")
    @SNP
    public Call<NetworkResponse> getFollowers(@Body  var1);

    @POST(value="/v2/social/myFeed")
    @SNP
    public Call<NetworkResponse> getMyFeed(@Body  var1);

    @POST(value="/v2/social/isFollowing")
    @SNP
    public Call<NetworkResponse> isFollowingAccountIds(@Body  var1);

    @POST(value="/v2/social/notification/activity")
    @SNP
    public Call<NetworkResponse> listActivityNotifications(@Body  var1);

    @POST(value="/v2/social/feed/list")
    @SNP
    public Call<NetworkResponse> listFeed(@Body  var1);

    @POST(value="/v2/social/feed")
    @SNP
    public Call<NetworkResponse> listFeedSocialOnly(@Body  var1);

    @POST(value="/v2/social/notification/lookup")
    @SNP
    public Call<NetworkResponse> lookupNotifications(@Body  var1);

    @POST(value="/v2/social/followee/update")
    @SNP
    public Call<NetworkResponse> updateFollowees(@Body  var1);

}

