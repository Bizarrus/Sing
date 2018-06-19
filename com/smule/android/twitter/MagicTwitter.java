package com.smule.android.twitter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import twitter4j.api.HelpResources.Language;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuth2Token;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class MagicTwitter implements TwitterListener {
    public static final String f17664a = MagicTwitter.class.getName();
    private static MagicTwitter f17665i;
    public TwitterOAuthCallback f17666b;
    private final Context f17667c;
    private final String f17668d;
    private final String f17669e;
    private final AsyncTwitter f17670f;
    private RequestToken f17671g;
    private AccountSettings f17672h;
    private TwitterOnPostCallback f17673j;
    private TwitterAccountCallback f17674k;

    public interface TwitterAccountCallback {
        void mo6600a(AccountSettings accountSettings);
    }

    public interface TwitterOAuthCallback {
        void m18835a(Uri uri);
    }

    public interface TwitterOnPostCallback {
        void mo6468a();

        void mo6469a(Exception exception);
    }

    public static void m18839a(Context context, String str, String str2) {
        if (f17665i == null) {
            f17665i = new MagicTwitter(context, str, str2);
        }
    }

    public static MagicTwitter m18838a() {
        return f17665i;
    }

    private MagicTwitter(Context context, String str, String str2) {
        this.f17667c = context.getApplicationContext();
        this.f17668d = str;
        this.f17669e = str2;
        ConfigurationBuilder oAuthConsumerSecret = new ConfigurationBuilder().setOAuthConsumerKey(str).setOAuthConsumerSecret(str2);
        if (m18845c()) {
            SharedPreferences sharedPreferences = this.f17667c.getSharedPreferences(MagicTwitter.class.getName(), 0);
            String string = sharedPreferences.getString("PREF_KEY_TOKEN", null);
            String string2 = sharedPreferences.getString("PREF_KEY_SECRET", null);
            if (!(string == null || string2 == null)) {
                oAuthConsumerSecret.setOAuthAccessToken(string).setOAuthAccessTokenSecret(string2);
            }
        }
        this.f17670f = new AsyncTwitterFactory(oAuthConsumerSecret.build()).getInstance();
        this.f17670f.addListener(this);
        if (m18845c()) {
            this.f17670f.getAccountSettings();
        }
    }

    public boolean m18842a(Uri uri) {
        if (uri == null || this.f17671g == null) {
            return false;
        }
        String queryParameter = uri.getQueryParameter("oauth_verifier");
        if (queryParameter == null) {
            return false;
        }
        try {
            this.f17670f.getOAuthAccessTokenAsync(this.f17671g, queryParameter);
            return true;
        } catch (Throwable e) {
            Log.d(f17664a, "Failed to connect to Twitter", e);
            return false;
        }
    }

    public boolean m18843a(AccessToken accessToken) {
        if (accessToken == null) {
            return false;
        }
        Editor edit = this.f17667c.getSharedPreferences(MagicTwitter.class.getName(), 0).edit();
        edit.putString("PREF_KEY_TOKEN", accessToken.getToken());
        edit.putString("PREF_KEY_SECRET", accessToken.getTokenSecret());
        edit.apply();
        f17665i = new MagicTwitter(this.f17667c, this.f17668d, this.f17669e);
        return true;
    }

    public void m18844b() {
        Editor edit = this.f17667c.getSharedPreferences(MagicTwitter.class.getName(), 0).edit();
        edit.remove("PREF_KEY_TOKEN");
        edit.remove("PREF_KEY_SECRET");
        edit.apply();
        f17665i = new MagicTwitter(this.f17667c, this.f17668d, this.f17669e);
    }

    public boolean m18845c() {
        if (this.f17667c.getSharedPreferences(MagicTwitter.class.getName(), 0).getString("PREF_KEY_TOKEN", null) != null) {
            return true;
        }
        return false;
    }

    public void m18840a(TwitterAccountCallback twitterAccountCallback) {
        if (this.f17672h != null) {
            twitterAccountCallback.mo6600a(this.f17672h);
        } else if (m18845c()) {
            this.f17670f.getAccountSettings();
            this.f17674k = twitterAccountCallback;
        } else {
            twitterAccountCallback.mo6600a(null);
        }
    }

    public void m18841a(StatusUpdate statusUpdate, TwitterOnPostCallback twitterOnPostCallback) {
        this.f17670f.updateStatus(statusUpdate);
        this.f17673j = twitterOnPostCallback;
    }

    public void gotMentions(ResponseList<Status> responseList) {
    }

    public void gotHomeTimeline(ResponseList<Status> responseList) {
    }

    public void gotUserTimeline(ResponseList<Status> responseList) {
    }

    public void gotRetweetsOfMe(ResponseList<Status> responseList) {
    }

    public void gotRetweets(ResponseList<Status> responseList) {
    }

    public void gotShowStatus(Status status) {
    }

    public void destroyedStatus(Status status) {
    }

    public void updatedStatus(Status status) {
        TwitterOnPostCallback twitterOnPostCallback = this.f17673j;
        this.f17673j = null;
        if (twitterOnPostCallback != null) {
            twitterOnPostCallback.mo6468a();
        }
    }

    public void retweetedStatus(Status status) {
    }

    public void gotOEmbed(OEmbed oEmbed) {
    }

    public void lookedup(ResponseList<Status> responseList) {
    }

    public void searched(QueryResult queryResult) {
    }

    public void gotDirectMessages(ResponseList<DirectMessage> responseList) {
    }

    public void gotSentDirectMessages(ResponseList<DirectMessage> responseList) {
    }

    public void gotDirectMessage(DirectMessage directMessage) {
    }

    public void destroyedDirectMessage(DirectMessage directMessage) {
    }

    public void sentDirectMessage(DirectMessage directMessage) {
    }

    public void gotFriendsIDs(IDs iDs) {
    }

    public void gotFollowersIDs(IDs iDs) {
    }

    public void lookedUpFriendships(ResponseList<Friendship> responseList) {
    }

    public void gotIncomingFriendships(IDs iDs) {
    }

    public void gotOutgoingFriendships(IDs iDs) {
    }

    public void createdFriendship(User user) {
    }

    public void destroyedFriendship(User user) {
    }

    public void updatedFriendship(Relationship relationship) {
    }

    public void gotShowFriendship(Relationship relationship) {
    }

    public void gotFriendsList(PagableResponseList<User> pagableResponseList) {
    }

    public void gotFollowersList(PagableResponseList<User> pagableResponseList) {
    }

    public void gotAccountSettings(AccountSettings accountSettings) {
        this.f17672h = accountSettings;
        TwitterAccountCallback twitterAccountCallback = this.f17674k;
        this.f17674k = null;
        if (twitterAccountCallback != null) {
            twitterAccountCallback.mo6600a(accountSettings);
        }
    }

    public void verifiedCredentials(User user) {
    }

    public void updatedAccountSettings(AccountSettings accountSettings) {
    }

    public void updatedProfile(User user) {
    }

    public void updatedProfileBackgroundImage(User user) {
    }

    public void updatedProfileColors(User user) {
    }

    public void updatedProfileImage(User user) {
    }

    public void gotBlocksList(ResponseList<User> responseList) {
    }

    public void gotBlockIDs(IDs iDs) {
    }

    public void createdBlock(User user) {
    }

    public void destroyedBlock(User user) {
    }

    public void lookedupUsers(ResponseList<User> responseList) {
    }

    public void gotUserDetail(User user) {
    }

    public void searchedUser(ResponseList<User> responseList) {
    }

    public void gotContributees(ResponseList<User> responseList) {
    }

    public void gotContributors(ResponseList<User> responseList) {
    }

    public void removedProfileBanner() {
    }

    public void updatedProfileBanner() {
    }

    public void gotMutesList(ResponseList<User> responseList) {
    }

    public void gotMuteIDs(IDs iDs) {
    }

    public void createdMute(User user) {
    }

    public void destroyedMute(User user) {
    }

    public void gotUserSuggestions(ResponseList<User> responseList) {
    }

    public void gotSuggestedUserCategories(ResponseList<Category> responseList) {
    }

    public void gotMemberSuggestions(ResponseList<User> responseList) {
    }

    public void gotFavorites(ResponseList<Status> responseList) {
    }

    public void createdFavorite(Status status) {
    }

    public void destroyedFavorite(Status status) {
    }

    public void gotUserLists(ResponseList<UserList> responseList) {
    }

    public void gotUserListStatuses(ResponseList<Status> responseList) {
    }

    public void destroyedUserListMember(UserList userList) {
    }

    public void gotUserListMemberships(PagableResponseList<UserList> pagableResponseList) {
    }

    public void gotUserListSubscribers(PagableResponseList<User> pagableResponseList) {
    }

    public void subscribedUserList(UserList userList) {
    }

    public void checkedUserListSubscription(User user) {
    }

    public void unsubscribedUserList(UserList userList) {
    }

    public void createdUserListMembers(UserList userList) {
    }

    public void checkedUserListMembership(User user) {
    }

    public void createdUserListMember(UserList userList) {
    }

    public void destroyedUserList(UserList userList) {
    }

    public void updatedUserList(UserList userList) {
    }

    public void createdUserList(UserList userList) {
    }

    public void gotShowUserList(UserList userList) {
    }

    public void gotUserListSubscriptions(PagableResponseList<UserList> pagableResponseList) {
    }

    public void gotUserListMembers(PagableResponseList<User> pagableResponseList) {
    }

    public void gotSavedSearches(ResponseList<SavedSearch> responseList) {
    }

    public void gotSavedSearch(SavedSearch savedSearch) {
    }

    public void createdSavedSearch(SavedSearch savedSearch) {
    }

    public void destroyedSavedSearch(SavedSearch savedSearch) {
    }

    public void gotGeoDetails(Place place) {
    }

    public void gotReverseGeoCode(ResponseList<Place> responseList) {
    }

    public void searchedPlaces(ResponseList<Place> responseList) {
    }

    public void gotSimilarPlaces(ResponseList<Place> responseList) {
    }

    public void gotPlaceTrends(Trends trends) {
    }

    public void gotAvailableTrends(ResponseList<Location> responseList) {
    }

    public void gotClosestTrends(ResponseList<Location> responseList) {
    }

    public void reportedSpam(User user) {
    }

    public void gotOAuthRequestToken(RequestToken requestToken) {
        Log.b(f17664a, "Got OAuth request token: " + requestToken.getAuthenticationURL());
        this.f17671g = requestToken;
        if (this.f17666b != null) {
            this.f17666b.m18835a(Uri.parse(requestToken.getAuthenticationURL()));
        }
    }

    public void gotOAuthAccessToken(AccessToken accessToken) {
        Editor edit = this.f17667c.getSharedPreferences(MagicTwitter.class.getName(), 0).edit();
        edit.putString("PREF_KEY_TOKEN", accessToken.getToken());
        edit.putString("PREF_KEY_SECRET", accessToken.getTokenSecret());
        edit.apply();
        f17665i = new MagicTwitter(this.f17667c, this.f17668d, this.f17669e);
    }

    public void gotOAuth2Token(OAuth2Token oAuth2Token) {
    }

    public void gotAPIConfiguration(TwitterAPIConfiguration twitterAPIConfiguration) {
    }

    public void gotLanguages(ResponseList<Language> responseList) {
    }

    public void gotPrivacyPolicy(String str) {
    }

    public void gotTermsOfService(String str) {
    }

    public void gotRateLimitStatus(Map<String, RateLimitStatus> map) {
    }

    public void onException(TwitterException twitterException, TwitterMethod twitterMethod) {
        if (twitterMethod == TwitterMethod.UPDATE_STATUS && twitterException.getErrorCode() != 187) {
            TwitterOnPostCallback twitterOnPostCallback = this.f17673j;
            this.f17673j = null;
            MagicCrittercism.a(twitterException);
            if (twitterOnPostCallback != null) {
                twitterOnPostCallback.mo6469a(twitterException);
            }
        }
    }
}
