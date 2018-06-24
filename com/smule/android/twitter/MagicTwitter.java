/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.net.Uri
 *  twitter4j.AccountSettings
 *  twitter4j.AsyncTwitter
 *  twitter4j.AsyncTwitterFactory
 *  twitter4j.Category
 *  twitter4j.DirectMessage
 *  twitter4j.Friendship
 *  twitter4j.IDs
 *  twitter4j.Location
 *  twitter4j.OEmbed
 *  twitter4j.PagableResponseList
 *  twitter4j.Place
 *  twitter4j.QueryResult
 *  twitter4j.RateLimitStatus
 *  twitter4j.Relationship
 *  twitter4j.ResponseList
 *  twitter4j.SavedSearch
 *  twitter4j.Status
 *  twitter4j.StatusUpdate
 *  twitter4j.Trends
 *  twitter4j.TwitterAPIConfiguration
 *  twitter4j.TwitterException
 *  twitter4j.TwitterListener
 *  twitter4j.TwitterMethod
 *  twitter4j.User
 *  twitter4j.UserList
 *  twitter4j.api.HelpResources
 *  twitter4j.api.HelpResources$Language
 *  twitter4j.auth.AccessToken
 *  twitter4j.auth.OAuth2Token
 *  twitter4j.auth.RequestToken
 *  twitter4j.conf.Configuration
 *  twitter4j.conf.ConfigurationBuilder
 */
package com.smule.android.twitter;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import java.util.Map;
import twitter4j.AccountSettings;
import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.Category;
import twitter4j.DirectMessage;
import twitter4j.Friendship;
import twitter4j.IDs;
import twitter4j.Location;
import twitter4j.OEmbed;
import twitter4j.PagableResponseList;
import twitter4j.Place;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.Relationship;
import twitter4j.ResponseList;
import twitter4j.SavedSearch;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Trends;
import twitter4j.TwitterAPIConfiguration;
import twitter4j.TwitterException;
import twitter4j.TwitterListener;
import twitter4j.TwitterMethod;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.api.HelpResources;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuth2Token;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class MagicTwitter
implements TwitterListener {
    public static final String a = MagicTwitter.class.getName();
    private static MagicTwitter i;
    public TwitterOAuthCallback b;
    private final Context c;
    private final String d;
    private final String e;
    private final AsyncTwitter f;
    private RequestToken g;
    private AccountSettings h;
    private TwitterOnPostCallback j;
    private TwitterAccountCallback k;

    private MagicTwitter(Context context, String string2, String string3) {
        this.c = context.getApplicationContext();
        this.d = string2;
        this.e = string3;
        context = new ConfigurationBuilder().setOAuthConsumerKey(string2).setOAuthConsumerSecret(string3);
        if (this.c()) {
            string3 = this.c.getSharedPreferences(MagicTwitter.class.getName(), 0);
            string2 = string3.getString("PREF_KEY_TOKEN", null);
            string3 = string3.getString("PREF_KEY_SECRET", null);
            if (string2 != null && string3 != null) {
                context.setOAuthAccessToken(string2).setOAuthAccessTokenSecret(string3);
            }
        }
        this.f = new AsyncTwitterFactory(context.build()).getInstance();
        this.f.addListener((TwitterListener)this);
        if (this.c()) {
            this.f.getAccountSettings();
        }
    }

    public static MagicTwitter a() {
        return i;
    }

    public static void a(Context context, String string2, String string3) {
        if (i == null) {
            i = new MagicTwitter(context, string2, string3);
        }
    }

    public void a(TwitterAccountCallback twitterAccountCallback) {
        if (this.h != null) {
            twitterAccountCallback.a(this.h);
            return;
        }
        if (this.c()) {
            this.f.getAccountSettings();
            this.k = twitterAccountCallback;
            return;
        }
        twitterAccountCallback.a(null);
    }

    public void a(StatusUpdate statusUpdate, TwitterOnPostCallback twitterOnPostCallback) {
        this.f.updateStatus(statusUpdate);
        this.j = twitterOnPostCallback;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean a(Uri object) {
        if (object == null || this.g == null || (object = object.getQueryParameter("oauth_verifier")) == null) {
            return false;
        }
        try {
            this.f.getOAuthAccessTokenAsync(this.g, (String)object);
            return true;
        }
        catch (Exception exception) {
            Log.d(a, "Failed to connect to Twitter", exception);
            return false;
        }
    }

    public boolean a(AccessToken accessToken) {
        if (accessToken == null) {
            return false;
        }
        SharedPreferences.Editor editor = this.c.getSharedPreferences(MagicTwitter.class.getName(), 0).edit();
        editor.putString("PREF_KEY_TOKEN", accessToken.getToken());
        editor.putString("PREF_KEY_SECRET", accessToken.getTokenSecret());
        editor.apply();
        i = new MagicTwitter(this.c, this.d, this.e);
        return true;
    }

    public void b() {
        SharedPreferences.Editor editor = this.c.getSharedPreferences(MagicTwitter.class.getName(), 0).edit();
        editor.remove("PREF_KEY_TOKEN");
        editor.remove("PREF_KEY_SECRET");
        editor.apply();
        i = new MagicTwitter(this.c, this.d, this.e);
    }

    public boolean c() {
        boolean bl = false;
        if (this.c.getSharedPreferences(MagicTwitter.class.getName(), 0).getString("PREF_KEY_TOKEN", null) != null) {
            bl = true;
        }
        return bl;
    }

    public void checkedUserListMembership(User user) {
    }

    public void checkedUserListSubscription(User user) {
    }

    public void createdBlock(User user) {
    }

    public void createdFavorite(Status status) {
    }

    public void createdFriendship(User user) {
    }

    public void createdMute(User user) {
    }

    public void createdSavedSearch(SavedSearch savedSearch) {
    }

    public void createdUserList(UserList userList) {
    }

    public void createdUserListMember(UserList userList) {
    }

    public void createdUserListMembers(UserList userList) {
    }

    public void destroyedBlock(User user) {
    }

    public void destroyedDirectMessage(DirectMessage directMessage) {
    }

    public void destroyedFavorite(Status status) {
    }

    public void destroyedFriendship(User user) {
    }

    public void destroyedMute(User user) {
    }

    public void destroyedSavedSearch(SavedSearch savedSearch) {
    }

    public void destroyedStatus(Status status) {
    }

    public void destroyedUserList(UserList userList) {
    }

    public void destroyedUserListMember(UserList userList) {
    }

    public void gotAPIConfiguration(TwitterAPIConfiguration twitterAPIConfiguration) {
    }

    public void gotAccountSettings(AccountSettings accountSettings) {
        this.h = accountSettings;
        TwitterAccountCallback twitterAccountCallback = this.k;
        this.k = null;
        if (twitterAccountCallback != null) {
            twitterAccountCallback.a(accountSettings);
        }
    }

    public void gotAvailableTrends(ResponseList<Location> responseList) {
    }

    public void gotBlockIDs(IDs iDs) {
    }

    public void gotBlocksList(ResponseList<User> responseList) {
    }

    public void gotClosestTrends(ResponseList<Location> responseList) {
    }

    public void gotContributees(ResponseList<User> responseList) {
    }

    public void gotContributors(ResponseList<User> responseList) {
    }

    public void gotDirectMessage(DirectMessage directMessage) {
    }

    public void gotDirectMessages(ResponseList<DirectMessage> responseList) {
    }

    public void gotFavorites(ResponseList<Status> responseList) {
    }

    public void gotFollowersIDs(IDs iDs) {
    }

    public void gotFollowersList(PagableResponseList<User> pagableResponseList) {
    }

    public void gotFriendsIDs(IDs iDs) {
    }

    public void gotFriendsList(PagableResponseList<User> pagableResponseList) {
    }

    public void gotGeoDetails(Place place) {
    }

    public void gotHomeTimeline(ResponseList<Status> responseList) {
    }

    public void gotIncomingFriendships(IDs iDs) {
    }

    public void gotLanguages(ResponseList<HelpResources.Language> responseList) {
    }

    public void gotMemberSuggestions(ResponseList<User> responseList) {
    }

    public void gotMentions(ResponseList<Status> responseList) {
    }

    public void gotMuteIDs(IDs iDs) {
    }

    public void gotMutesList(ResponseList<User> responseList) {
    }

    public void gotOAuth2Token(OAuth2Token oAuth2Token) {
    }

    public void gotOAuthAccessToken(AccessToken accessToken) {
        SharedPreferences.Editor editor = this.c.getSharedPreferences(MagicTwitter.class.getName(), 0).edit();
        editor.putString("PREF_KEY_TOKEN", accessToken.getToken());
        editor.putString("PREF_KEY_SECRET", accessToken.getTokenSecret());
        editor.apply();
        i = new MagicTwitter(this.c, this.d, this.e);
    }

    public void gotOAuthRequestToken(RequestToken requestToken) {
        Log.b(a, "Got OAuth request token: " + requestToken.getAuthenticationURL());
        this.g = requestToken;
        if (this.b != null) {
            this.b.a(Uri.parse((String)requestToken.getAuthenticationURL()));
        }
    }

    public void gotOEmbed(OEmbed oEmbed) {
    }

    public void gotOutgoingFriendships(IDs iDs) {
    }

    public void gotPlaceTrends(Trends trends) {
    }

    public void gotPrivacyPolicy(String string2) {
    }

    public void gotRateLimitStatus(Map<String, RateLimitStatus> map) {
    }

    public void gotRetweets(ResponseList<Status> responseList) {
    }

    public void gotRetweetsOfMe(ResponseList<Status> responseList) {
    }

    public void gotReverseGeoCode(ResponseList<Place> responseList) {
    }

    public void gotSavedSearch(SavedSearch savedSearch) {
    }

    public void gotSavedSearches(ResponseList<SavedSearch> responseList) {
    }

    public void gotSentDirectMessages(ResponseList<DirectMessage> responseList) {
    }

    public void gotShowFriendship(Relationship relationship) {
    }

    public void gotShowStatus(Status status) {
    }

    public void gotShowUserList(UserList userList) {
    }

    public void gotSimilarPlaces(ResponseList<Place> responseList) {
    }

    public void gotSuggestedUserCategories(ResponseList<Category> responseList) {
    }

    public void gotTermsOfService(String string2) {
    }

    public void gotUserDetail(User user) {
    }

    public void gotUserListMembers(PagableResponseList<User> pagableResponseList) {
    }

    public void gotUserListMemberships(PagableResponseList<UserList> pagableResponseList) {
    }

    public void gotUserListStatuses(ResponseList<Status> responseList) {
    }

    public void gotUserListSubscribers(PagableResponseList<User> pagableResponseList) {
    }

    public void gotUserListSubscriptions(PagableResponseList<UserList> pagableResponseList) {
    }

    public void gotUserLists(ResponseList<UserList> responseList) {
    }

    public void gotUserSuggestions(ResponseList<User> responseList) {
    }

    public void gotUserTimeline(ResponseList<Status> responseList) {
    }

    public void lookedUpFriendships(ResponseList<Friendship> responseList) {
    }

    public void lookedup(ResponseList<Status> responseList) {
    }

    public void lookedupUsers(ResponseList<User> responseList) {
    }

    public void onException(TwitterException twitterException, TwitterMethod object) {
        if (object == TwitterMethod.UPDATE_STATUS && twitterException.getErrorCode() != 187) {
            object = this.j;
            this.j = null;
            MagicCrittercism.a((Throwable)twitterException);
            if (object != null) {
                object.a((Exception)twitterException);
            }
        }
    }

    public void removedProfileBanner() {
    }

    public void reportedSpam(User user) {
    }

    public void retweetedStatus(Status status) {
    }

    public void searched(QueryResult queryResult) {
    }

    public void searchedPlaces(ResponseList<Place> responseList) {
    }

    public void searchedUser(ResponseList<User> responseList) {
    }

    public void sentDirectMessage(DirectMessage directMessage) {
    }

    public void subscribedUserList(UserList userList) {
    }

    public void unsubscribedUserList(UserList userList) {
    }

    public void updatedAccountSettings(AccountSettings accountSettings) {
    }

    public void updatedFriendship(Relationship relationship) {
    }

    public void updatedProfile(User user) {
    }

    public void updatedProfileBackgroundImage(User user) {
    }

    public void updatedProfileBanner() {
    }

    public void updatedProfileColors(User user) {
    }

    public void updatedProfileImage(User user) {
    }

    public void updatedStatus(Status object) {
        object = this.j;
        this.j = null;
        if (object != null) {
            object.a();
        }
    }

    public void updatedUserList(UserList userList) {
    }

    public void verifiedCredentials(User user) {
    }

    public static interface TwitterAccountCallback {
        public void a(AccountSettings var1);
    }

    public static interface TwitterOAuthCallback {
        public void a(Uri var1);
    }

    public static interface TwitterOnPostCallback {
        public void a();

        public void a(Exception var1);
    }

}

