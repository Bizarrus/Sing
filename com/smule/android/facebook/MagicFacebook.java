/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Looper
 *  android.text.TextUtils
 *  com.facebook.AccessToken
 *  com.facebook.AccessTokenSource
 *  com.facebook.FacebookRequestError
 *  com.facebook.FacebookRequestError$Category
 *  com.facebook.GraphRequest
 *  com.facebook.GraphRequest$Callback
 *  com.facebook.GraphRequest$GraphJSONObjectCallback
 *  com.facebook.GraphRequestAsyncTask
 *  com.facebook.GraphResponse
 *  com.facebook.login.LoginManager
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.smule.android.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.facebook.FacebookFriend;
import com.smule.android.facebook.FriendFinder;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.logging.Log;
import com.smule.android.network.api.ResourceDownloader;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class MagicFacebook {
    private static MagicFacebook a;
    private static final String b;
    private Handler c = new Handler(Looper.getMainLooper());
    private  d;
    private  e;
    private boolean f = false;

    static {
        b = MagicFacebook.class.getName();
    }

    private MagicFacebook() {
    }

    static /* synthetic */ Context a(MagicFacebook magicFacebook) {
        return magicFacebook.k();
    }

    static /* synthetic */  a(MagicFacebook magicFacebook,  facebookOnPostCallback) {
        magicFacebook.e = facebookOnPostCallback;
        return facebookOnPostCallback;
    }

    static /* synthetic */  a(MagicFacebook magicFacebook,  facebookUserInfo) {
        magicFacebook.d = facebookUserInfo;
        return facebookUserInfo;
    }

    public static MagicFacebook a() {
        if (a == null) {
            a = new MagicFacebook();
        }
        return a;
    }

    public static NetworkResponse a(FacebookRequestError object) {
        if (object == null) {
            return NetworkResponse.a();
        }
        Log.b(b, "requestError:" + object);
        if (object.getCategory() == FacebookRequestError.Category.LOGIN_RECOVERABLE) {
            object = NetworkResponse.a();
            object.b = 69;
            return object;
        }
        return NetworkResponse.b();
    }

    public static String a(String string2) {
        return "https://graph.facebook.com/" + string2 + "/picture?type=large";
    }

    private void a(AccessToken accessToken) {
        if (this.b() != null) {
            Log.b(b, "token still active");
            return;
        }
        if (accessToken == null) {
            Log.b(b, "no previous token");
            return;
        }
        Date date = new Date();
        AccessToken.setCurrentAccessToken((AccessToken)new AccessToken("invalid_access_token", accessToken.getApplicationId(), accessToken.getUserId(), (Collection)accessToken.getPermissions(), (Collection)accessToken.getDeclinedPermissions(), accessToken.getSource(), date, date));
        Log.b(b, "restoring previous token");
    }

    static /* synthetic */ boolean a(MagicFacebook magicFacebook, boolean bl) {
        magicFacebook.f = bl;
        return bl;
    }

    static /* synthetic */  b(MagicFacebook magicFacebook) {
        return magicFacebook.e;
    }

    static /* synthetic */ String j() {
        return b;
    }

    private Context k() {
        return MagicNetwork.d().getApplicationContext();
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public  a(String object, boolean bl) {
        Object object2;
        Log.b(b, "getUserInfo");
        object2 = this.b();
        Object object3 = this.e();
        if (object3 == null) {
            return null;
        }
        if (object3.getError() != null) {
            Log.e(b, "Failed to get user info from facebook " + (Object)object3.getError());
            this.a((AccessToken)object2);
            return new Object(object3.getError()){
                public AccessToken a;
                public String b;
                public String c;
                public String d;
                public String e;
                public String f;
                public String g;
                public String h;
                public String i;
                public Integer j;
                public Integer k;
                public final FacebookRequestError l;
                {
                    this.l = null;
                }
                {
                    this.l = facebookRequestError;
                }

                public boolean a() {
                    if (this.l == null) {
                        return true;
                    }
                    return false;
                }
            };
        }
        object2 = new ;
        JSONObject jSONObject = object3.getJSONObject();
        object2.b = jSONObject.getString("id");
        object2.f = jSONObject.getString("token_for_business");
        object2.a = this.b();
        object2.g = jSONObject.getString("name");
        object2.h = jSONObject.getString("first_name");
        object2.i = jSONObject.getString("last_name");
        if (bl) {
            object3 = jSONObject.optString("email", null);
            if (object3 != null) {
                object = object3;
            }
            object2.c = object;
        } else {
            object = TextUtils.isEmpty((CharSequence)object) ? jSONObject.optString("email", null) : null;
            object2.c = object;
        }
        object2.d = jSONObject.optString("gender", null);
        object2.e = jSONObject.optString("birthday", null);
        {
            catch (JSONException jSONException) {
                Log.d(b, "Failed to parse JSON response. JsonObject: " + (Object)jSONObject, (Throwable)jSONException);
                return null;
            }
        }
        try {
            object = jSONObject.getJSONObject("age_range");
            object2.j = object.optInt("min", -1);
            if (object2.j == -1) {
                object2.j = null;
            }
            object2.k = object.optInt("max", -1);
            if (object2.k == -1) {
                object2.k = null;
            }
        }
        catch (JSONException jSONException) {
            Log.d(b, "Didn't get age_range");
        }
        Log.a(b, "Facebook user data cached for '" + object2.g + "'");
        this.d = object2;
        return object2;
    }

    public void a(Activity activity) {
        if (!this.d()) {
            return;
        }
        LoginManager.getInstance().logInWithPublishPermissions(activity, MagicNetwork.d().getFacebookPublishPermissions());
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a( facebookUserInfoListener) {
        Handler handler = new Handler();
        if (this.d == null) {
            MagicNetwork.a(new Runnable(this, facebookUserInfoListener, handler){
                final /* synthetic */  a;
                final /* synthetic */ Handler b;
                final /* synthetic */ MagicFacebook c;
                {
                    this.c = magicFacebook;
                    this.a = facebookUserInfoListener;
                    this.b = handler;
                }

                public void run() {
                     facebookUserInfo = this.c.a(null, true);
                    if (this.a != null) {
                        this.b.post(new Runnable(this, facebookUserInfo){
                            final /* synthetic */  a;
                            final /* synthetic */  b;
                            {
                                this.b = var1_1;
                                this.a = facebookUserInfo;
                            }

                            public void run() {
                                if (this.a != null && this.a.a()) {
                                    this.b.a.a(this.a);
                                    return;
                                }
                                Log.e(MagicFacebook.j(), "user info not valid");
                                this.b.a.b(this.a);
                            }
                        });
                    }
                }
            });
            return;
        } else {
            if (facebookUserInfoListener == null) return;
            {
                facebookUserInfoListener.a(this.d);
                return;
            }
        }
    }

    public void a( findFacebookFriendsListener, boolean bl, int n) {
        if (findFacebookFriendsListener == null) {
            Log.e(b, "findFacebookFriends - findFacebookFriendsListener is null; aborting request!");
            return;
        }
        new FriendFinder(findFacebookFriendsListener, bl, n).a();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(String string2,  facebookOnPostCallback) {
        JSONObject jSONObject = new JSONObject();
        this.e = facebookOnPostCallback;
        try {
            jSONObject.put("link", (Object)string2);
        }
        catch (JSONException jSONException) {
            Log.d(b, "Failed to post to Facebook wall.", (Throwable)jSONException);
            if (this.e == null) return;
            this.e.b();
            this.e = null;
            return;
        }
        GraphRequest.newPostRequest((AccessToken)AccessToken.getCurrentAccessToken(), (String)"me/feed", (JSONObject)jSONObject, (GraphRequest.Callback)new GraphRequest.Callback(this){
            final /* synthetic */ MagicFacebook a;
            {
                this.a = magicFacebook;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void onCompleted(GraphResponse graphResponse) {
                if (graphResponse.getError() != null) {
                    Log.d(MagicFacebook.j(), "Error posting to Facebook feed: " + graphResponse.getError().getErrorMessage());
                    if (MagicFacebook.b(this.a) == null) return;
                    {
                        MagicFacebook.b(this.a).b();
                        MagicFacebook.a(this.a, null);
                        return;
                    }
                } else {
                    if (MagicFacebook.b(this.a) == null) return;
                    {
                        MagicFacebook.b(this.a).a();
                        MagicFacebook.a(this.a, null);
                        return;
                    }
                }
            }
        }).executeAsync();
    }

    public void a(boolean bl) {
        this.k().getSharedPreferences("MagicFacebook", 0).edit().putBoolean("HAS_ENABLED_FACEBOOK", bl).apply();
    }

    public AccessToken b() {
        return AccessToken.getCurrentAccessToken();
    }

    /*
     * Enabled aggressive block sorting
     */
    public UserManager b(String object, boolean bl) {
         facebookUserInfo = this.a((String)object, bl);
        if (facebookUserInfo == null) return null;
        if (!facebookUserInfo.a()) {
            return null;
        }
        Object object2 = bl ? com.smule.android.network.managers.UserManager.a().m() : com.smule.android.network.managers.UserManager.H();
        object2 = com.smule.android.network.managers.UserManager.a().a(facebookUserInfo, (String)object, (String)object2, bl);
        if (object2 != null) {
            object = object2;
            if (object2.a.c()) return object;
        }
        Log.e(b, "Failed to login to SNP after Facebook authentication");
        return object2;
    }

    public boolean b(String string2) {
        if (!this.c()) {
            return false;
        }
        return AccessToken.getCurrentAccessToken().getPermissions().contains(string2);
    }

    public boolean c() {
        if (AccessToken.getCurrentAccessToken() != null && !AccessToken.getCurrentAccessToken().isExpired()) {
            return true;
        }
        return false;
    }

    @Deprecated
    public boolean d() {
        return this.c();
    }

    public GraphResponse e() {
        GraphRequest graphRequest = GraphRequest.newMeRequest((AccessToken)this.b(), (GraphRequest.GraphJSONObjectCallback)new GraphRequest.GraphJSONObjectCallback(this){
            final /* synthetic */ MagicFacebook a;
            {
                this.a = magicFacebook;
            }

            public void onCompleted(JSONObject jSONObject, GraphResponse graphResponse) {
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("fields", "first_name,birthday,gender,email,id,last_name,age_range,link,locale,middle_name,name,timezone,updated_time,verified,token_for_business");
        graphRequest.setParameters(bundle);
        return graphRequest.executeAndWait();
    }

    /*
     * Enabled aggressive block sorting
     */
    public NetworkResponse f() {
        NetworkResponse networkResponse = NetworkResponse.b();
        Object object = com.smule.android.network.managers.UserManager.a().n();
        NetworkResponse networkResponse2 = networkResponse;
        if (object == null) return networkResponse2;
        networkResponse2 = MagicNetwork.d().getApplicationContext();
        if ((object = com.smule.android.network.api.ResourceDownloader.downloadFileFromURL(MagicFacebook.a((String)object), (String)object + ".jpg", (Context)networkResponse2)).isSuccess()) {
            networkResponse = com.smule.android.network.managers.UserManager.a().a(object.mFile);
        } else {
            Log.e(b, "Failed to download FB profile image " + (Object)((Object)object.mStatus));
        }
        networkResponse2 = networkResponse;
        if (object.mFile == null) return networkResponse2;
        object.mFile.delete();
        return networkResponse;
    }

    public void g() {
        if (this.f) {
            Log.d(b, "on connectFacebookToSmuleAccountSync - already in the process of connecting Facebook to Smule");
            return;
        }
        this.f = true;
        SharedPreferences sharedPreferences = this.k().getSharedPreferences("MagicFacebook", 0);
        if (sharedPreferences.getBoolean("DID_CONNECT_FACEBOOK", false)) {
            Log.b(b, "on connectFacebookToSmuleAccountSync - account has already been connected; ignoring request");
            this.f = false;
            return;
        }
        MagicNetwork.a(new Runnable(this, sharedPreferences){
            final /* synthetic */ SharedPreferences a;
            final /* synthetic */ MagicFacebook b;
            {
                this.b = magicFacebook;
                this.a = sharedPreferences;
            }

            public void run() {
                NetworkResponse networkResponse = null;
                 facebookUserInfo = this.b.a(null, false);
                NetworkResponse networkResponse2 = networkResponse;
                if (facebookUserInfo != null) {
                    networkResponse2 = networkResponse;
                    if (facebookUserInfo.a()) {
                        networkResponse2 = com.smule.android.network.managers.UserManager.a().a(facebookUserInfo);
                    }
                }
                if (networkResponse2 != null && networkResponse2.c()) {
                    Log.b(MagicFacebook.j(), "on connectFacebookToSmuleAccountSync - Facebook account successfully connected");
                    this.a.edit().putBoolean("DID_CONNECT_FACEBOOK", true).apply();
                }
                MagicFacebook.a(this.b, false);
            }
        });
    }

    public boolean h() {
        return this.b("publish_actions");
    }

    public boolean i() {
        return this.b("user_friends");
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class FacebookFindUserResponse
    extends ParsedResponse {
        @JsonProperty(value="following")
        public List<FacebookUserItem> mFollowing = new ArrayList<FacebookUserItem>();
        @JsonProperty(value="notFollowing")
        public List<FacebookUserItem> mNotFollowing = new ArrayList<FacebookUserItem>();

        public static FacebookFindUserResponse a(NetworkResponse networkResponse) {
            return FacebookFindUserResponse.a(networkResponse, FacebookFindUserResponse.class);
        }

        public String toString() {
            return "FacebookFindUserResponse [mFollowing=" + this.mFollowing + ", mNotFollowing=" + this.mNotFollowing + "]";
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    protected static class FacebookUserItem {
        @JsonProperty(value="accountIcon")
        public AccountIcon mAccountIcon;
        @JsonProperty(value="accountId")
        public long mAccountId;
        @JsonProperty(value="afbId")
        public String mFbId;
    }

}

