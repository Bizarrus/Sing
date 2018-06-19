package com.smule.android.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.facebook.AccessToken;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookRequestError.Category;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.share.model.AppInviteContent.Builder;
import com.facebook.share.widget.AppInviteDialog;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.UserManager.LoginResponse;
import com.smule.android.network.models.AccountIcon;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class MagicFacebook {
    private static MagicFacebook f6685a;
    private static final String f6686b = MagicFacebook.class.getName();
    private Handler f6687c = new Handler(Looper.getMainLooper());
    private FacebookUserInfo f6688d;
    private FacebookOnPostCallback f6689e;
    private boolean f6690f = false;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FacebookFindUserResponse extends ParsedResponse {
        @JsonProperty("following")
        public List<FacebookUserItem> mFollowing = new ArrayList();
        @JsonProperty("notFollowing")
        public List<FacebookUserItem> mNotFollowing = new ArrayList();

        public static FacebookFindUserResponse m7678a(NetworkResponse networkResponse) {
            return (FacebookFindUserResponse) ParsedResponse.m7676a(networkResponse, FacebookFindUserResponse.class);
        }

        public String toString() {
            return "FacebookFindUserResponse [mFollowing=" + this.mFollowing + ", mNotFollowing=" + this.mNotFollowing + "]";
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    protected static class FacebookUserItem {
        @JsonProperty("accountIcon")
        public AccountIcon mAccountIcon;
        @JsonProperty("accountId")
        public long mAccountId;
        @JsonProperty("afbId")
        public String mFbId;
    }

    public static MagicFacebook m7682a() {
        if (f6685a == null) {
            f6685a = new MagicFacebook();
        }
        return f6685a;
    }

    public AccessToken m7697b() {
        return AccessToken.getCurrentAccessToken();
    }

    private MagicFacebook() {
    }

    private Context m7688j() {
        return MagicNetwork.m7804d().getApplicationContext();
    }

    public boolean m7699c() {
        return (AccessToken.getCurrentAccessToken() == null || AccessToken.getCurrentAccessToken().isExpired()) ? false : true;
    }

    @Deprecated
    public boolean m7700d() {
        return m7699c();
    }

    public GraphResponse m7701e() {
        GraphRequest newMeRequest = GraphRequest.newMeRequest(m7697b(), new 1(this));
        Bundle bundle = new Bundle();
        bundle.putString(GraphRequest.FIELDS_PARAM, "first_name,birthday,gender,email,id,last_name,age_range,link,locale,middle_name,name,timezone,updated_time,verified,token_for_business");
        newMeRequest.setParameters(bundle);
        return newMeRequest.executeAndWait();
    }

    public void m7702f() {
        if (this.f6690f) {
            Log.m7774d(f6686b, "on connectFacebookToSmuleAccountSync - already in the process of connecting Facebook to Smule");
            return;
        }
        this.f6690f = true;
        SharedPreferences sharedPreferences = m7688j().getSharedPreferences("MagicFacebook", 0);
        if (sharedPreferences.getBoolean("DID_CONNECT_FACEBOOK", false)) {
            Log.m7770b(f6686b, "on connectFacebookToSmuleAccountSync - account has already been connected; ignoring request");
            this.f6690f = false;
            return;
        }
        MagicNetwork.m7790a(new 2(this, sharedPreferences));
    }

    public FacebookUserInfo m7689a(String str, boolean z) {
        Log.m7770b(f6686b, "getUserInfo");
        AccessToken b = m7697b();
        GraphResponse e = m7701e();
        if (e == null) {
            return null;
        }
        if (e.getError() != null) {
            Log.m7776e(f6686b, "Failed to get user info from facebook " + e.getError());
            m7684a(b);
            return new FacebookUserInfo(e.getError());
        }
        FacebookUserInfo facebookUserInfo = new FacebookUserInfo();
        JSONObject jSONObject = e.getJSONObject();
        try {
            facebookUserInfo.b = jSONObject.getString("id");
            facebookUserInfo.f = jSONObject.getString("token_for_business");
            facebookUserInfo.a = m7697b();
            facebookUserInfo.g = jSONObject.getString("name");
            facebookUserInfo.h = jSONObject.getString("first_name");
            facebookUserInfo.i = jSONObject.getString("last_name");
            String optString;
            if (z) {
                optString = jSONObject.optString("email", null);
                if (optString != null) {
                    str = optString;
                }
                facebookUserInfo.c = str;
            } else {
                if (TextUtils.isEmpty(str)) {
                    optString = jSONObject.optString("email", null);
                } else {
                    optString = null;
                }
                facebookUserInfo.c = optString;
            }
            facebookUserInfo.d = jSONObject.optString("gender", null);
            facebookUserInfo.e = jSONObject.optString("birthday", null);
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("age_range");
                facebookUserInfo.j = Integer.valueOf(jSONObject2.optInt("min", -1));
                if (facebookUserInfo.j.intValue() == -1) {
                    facebookUserInfo.j = null;
                }
                facebookUserInfo.k = Integer.valueOf(jSONObject2.optInt("max", -1));
                if (facebookUserInfo.k.intValue() == -1) {
                    facebookUserInfo.k = null;
                }
            } catch (JSONException e2) {
                Log.m7774d(f6686b, "Didn't get age_range");
            }
            Log.m7767a(f6686b, "Facebook user data cached for '" + facebookUserInfo.g + "'");
            this.f6688d = facebookUserInfo;
            return facebookUserInfo;
        } catch (Throwable e3) {
            Log.m7775d(f6686b, "Failed to parse JSON response. JsonObject: " + jSONObject, e3);
            return null;
        }
    }

    public void m7692a(FacebookUserInfoListener facebookUserInfoListener) {
        Handler handler = new Handler();
        if (this.f6688d == null) {
            MagicNetwork.m7790a(new 3(this, facebookUserInfoListener, handler));
        } else if (facebookUserInfoListener != null) {
            facebookUserInfoListener.a(this.f6688d);
        }
    }

    public LoginResponse m7698b(String str, boolean z) {
        FacebookUserInfo a = m7689a(str, z);
        if (a == null || !a.a()) {
            return null;
        }
        LoginResponse a2 = UserManager.m8111a().m8162a(a, str, z ? UserManager.m8111a().m8217m() : UserManager.m8105I(), z);
        if (a2 != null && a2.a.m7850c()) {
            return a2;
        }
        Log.m7776e(f6686b, "Failed to login to SNP after Facebook authentication");
        return a2;
    }

    public void m7693a(FindFacebookFriendsListener findFacebookFriendsListener, boolean z, int i) {
        if (findFacebookFriendsListener == null) {
            Log.m7776e(f6686b, "findFacebookFriends - findFacebookFriendsListener is null; aborting request!");
        } else {
            new FriendFinder(findFacebookFriendsListener, z, i).a();
        }
    }

    public void m7690a(Activity activity) {
        if (m7700d()) {
            LoginManager.getInstance().logInWithPublishPermissions(activity, MagicNetwork.m7804d().getFacebookPublishPermissions());
        }
    }

    public void m7695a(boolean z) {
        m7688j().getSharedPreferences("MagicFacebook", 0).edit().putBoolean("HAS_ENABLED_FACEBOOK", z).apply();
    }

    public boolean m7703g() {
        return m7696a("publish_actions");
    }

    public boolean m7704h() {
        return m7696a("user_friends");
    }

    public boolean m7696a(String str) {
        if (m7699c()) {
            return AccessToken.getCurrentAccessToken().getPermissions().contains(str);
        }
        return false;
    }

    public void m7694a(String str, FacebookOnPostCallback facebookOnPostCallback) {
        JSONObject jSONObject = new JSONObject();
        this.f6689e = facebookOnPostCallback;
        try {
            jSONObject.put("link", str);
            GraphRequest.newPostRequest(AccessToken.getCurrentAccessToken(), "me/feed", jSONObject, new 5(this)).executeAsync();
        } catch (Throwable e) {
            Log.m7775d(f6686b, "Failed to post to Facebook wall.", e);
            if (this.f6689e != null) {
                this.f6689e.b();
                this.f6689e = null;
            }
        }
    }

    public void m7691a(Activity activity, String str, String str2) {
        AppInviteDialog.show(activity, new Builder().setApplinkUrl(str).setPreviewImageUrl(str2).build());
    }

    public static NetworkResponse m7683a(FacebookRequestError facebookRequestError) {
        if (facebookRequestError == null) {
            return NetworkResponse.m7834a();
        }
        Log.m7770b(f6686b, "requestError:" + facebookRequestError);
        if (facebookRequestError.getCategory() != Category.LOGIN_RECOVERABLE) {
            return NetworkResponse.m7840b();
        }
        NetworkResponse a = NetworkResponse.m7834a();
        a.f6756b = 69;
        return a;
    }

    private void m7684a(AccessToken accessToken) {
        if (m7697b() != null) {
            Log.m7770b(f6686b, "token still active");
        } else if (accessToken == null) {
            Log.m7770b(f6686b, "no previous token");
        } else {
            Date date = new Date();
            AccessToken.setCurrentAccessToken(new AccessToken("invalid_access_token", accessToken.getApplicationId(), accessToken.getUserId(), accessToken.getPermissions(), accessToken.getDeclinedPermissions(), accessToken.getSource(), date, date));
            Log.m7770b(f6686b, "restoring previous token");
        }
    }
}
