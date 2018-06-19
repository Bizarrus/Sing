package com.smule.android.network.api;

import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.utils.TimeUtils;
import com.smule.android.utils.VorgomUtils;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.SNP;

public interface UserAPI {

    public static class FacebookLoginRequest extends SnpRequest {
        public String accessToken;
        public String afbId;
        public String app = MagicNetwork.m7798b();
        public boolean automaticLogin;
        public String birthday;
        public String enteredEmail;
        public String fbAppId = MagicNetwork.m7804d().getFacebookAppId();
        public String fbEmail;
        @JsonInclude(Include.ALWAYS)
        public String firstName;
        public String gender;
        @JsonInclude(Include.ALWAYS)
        public String lastName;
        public Integer maxAge;
        public Integer minAge;
        public Long playerId;
        public int pulp = VorgomUtils.m8442b();
        public String requestedPassword;
        public String tfb;
        public int tzOffset = TimeUtils.a();
        public boolean vorgom = VorgomUtils.m8443c();

        public FacebookLoginRequest setAfbId(String str) {
            this.afbId = str;
            return this;
        }

        public FacebookLoginRequest setFbAppId(String str) {
            this.fbAppId = str;
            return this;
        }

        public FacebookLoginRequest setTfb(String str) {
            this.tfb = str;
            return this;
        }

        public FacebookLoginRequest setAccessToken(String str) {
            this.accessToken = str;
            return this;
        }

        public FacebookLoginRequest setEmail(String str, String str2) {
            if (!TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
                this.fbEmail = str;
            }
            if (TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                this.enteredEmail = str2;
            }
            return this;
        }

        public FacebookLoginRequest setRequestedPassword(String str) {
            this.requestedPassword = str;
            return this;
        }

        public FacebookLoginRequest setAutomaticLogin(boolean z) {
            this.automaticLogin = z;
            return this;
        }

        public FacebookLoginRequest setApp(String str) {
            this.app = str;
            return this;
        }

        public FacebookLoginRequest setFirstName(String str) {
            this.firstName = str;
            return this;
        }

        public FacebookLoginRequest setLastName(String str) {
            this.lastName = str;
            return this;
        }

        public FacebookLoginRequest setGender(String str) {
            this.gender = str;
            return this;
        }

        public FacebookLoginRequest setBirthday(String str) {
            this.birthday = str;
            return this;
        }

        public FacebookLoginRequest setMinAge(Integer num) {
            this.minAge = num;
            return this;
        }

        public FacebookLoginRequest setMaxAge(Integer num) {
            this.maxAge = num;
            return this;
        }

        public FacebookLoginRequest setPlayerId(Long l) {
            this.playerId = l;
            return this;
        }
    }

    @Deprecated
    public static class GooglePlusLoginRequest extends SnpRequest {
        public String accessToken;
        public boolean automaticLogin;
        public String birthday;
        public String email;
        public String firstName;
        public String gender;
        public String id;
        public String lastName;
        public Long playerId;
        public int pulp = VorgomUtils.m8442b();
        public String requestedPassword;
        public int tzOffset = TimeUtils.a();
        public boolean vorgom = VorgomUtils.m8443c();

        public GooglePlusLoginRequest setId(String str) {
            this.id = str;
            return this;
        }

        public GooglePlusLoginRequest setAccessToken(String str) {
            this.accessToken = str;
            return this;
        }

        public GooglePlusLoginRequest setEmail(String str) {
            this.email = str;
            return this;
        }

        public GooglePlusLoginRequest setFirstName(String str) {
            this.firstName = str;
            return this;
        }

        public GooglePlusLoginRequest setLastName(String str) {
            this.lastName = str;
            return this;
        }

        public GooglePlusLoginRequest setGender(String str) {
            this.gender = str;
            return this;
        }

        public GooglePlusLoginRequest setBirthday(String str) {
            this.birthday = str;
            return this;
        }

        public GooglePlusLoginRequest setRequestedPassword(String str) {
            this.requestedPassword = str;
            return this;
        }

        public GooglePlusLoginRequest setAutomaticLogin(boolean z) {
            this.automaticLogin = z;
            return this;
        }

        public GooglePlusLoginRequest setPlayerId(Long l) {
            this.playerId = l;
            return this;
        }
    }

    @POST("/v2/social/block/update")
    @SNP(maxRetries = 2)
    Call<NetworkResponse> blockUnblockUsers(@Body BlockUnblockRequest blockUnblockRequest);

    @POST("/v2/login/guest")
    @SNP(advId = true, deviceInfo = true, maxRetries = 2, needsSession = false, useCommon = true)
    Call<NetworkResponse> consolidatedGuestLogin(@Body ConsolidatedGuestLoginRequest consolidatedGuestLoginRequest);

    @POST("/v2/user/device/login")
    @SNP(advId = true, deviceInfo = true, maxRetries = 2, needsSession = false)
    Call<NetworkResponse> deviceLogin(@Body DeviceLoginRequest deviceLoginRequest);

    @POST("/v2/user/login")
    @SNP(advId = true, deviceInfo = true, maxRetries = 2, needsSession = false, secure = true)
    Call<NetworkResponse> emailLogin(@Body EmailLoginRequest emailLoginRequest);

    @POST("/v2/user/emailRegister")
    @SNP(advId = true, deviceInfo = true, maxRetries = 2, needsSession = false, secure = true)
    Call<NetworkResponse> emailRegister(@Body EmailRegisterRequest emailRegisterRequest);

    @POST("/v2/fb/connect")
    @SNP(maxRetries = 2, secure = true)
    Call<NetworkResponse> facebookConnect(@Body FacebookConnectRequest facebookConnectRequest);

    @POST("/v2/fb/disconnect")
    @Deprecated
    @SNP(maxRetries = 2)
    Call<NetworkResponse> facebookDisconnect(@Body SnpRequest snpRequest);

    @POST("/v2/fb/login")
    @SNP(advId = true, deviceInfo = true, maxRetries = 2, needsSession = false, secure = true)
    Call<NetworkResponse> facebookLogin(@Body FacebookLoginRequest facebookLoginRequest);

    @POST("/v2/user/device/lookup")
    @SNP(deviceInfo = true, maxRetries = 2, needsSession = false)
    Call<NetworkResponse> findAccountByDevice(@Body SnpRequest snpRequest);

    @POST("/v2/social/block/list")
    @SNP(maxRetries = 2)
    Call<NetworkResponse> getBlockedUsers(@Body SnpRequest snpRequest);

    @POST("/v2/pref")
    @SNP(maxRetries = 2)
    Call<NetworkResponse> getPreferences(@Body GetPreferencesRequest getPreferencesRequest);

    @POST("/v2/user/gplus/connect")
    @Deprecated
    @SNP(maxRetries = 2, secure = true)
    Call<NetworkResponse> googlePlusConnect(@Body GooglePlusConnectRequest googlePlusConnectRequest);

    @POST("/v2/user/gplus/disconnect")
    @Deprecated
    @SNP(maxRetries = 2)
    Call<NetworkResponse> googlePlusDisconnect(@Body SnpRequest snpRequest);

    @POST("/v2/user/gplus/login")
    @Deprecated
    @SNP(advId = true, deviceInfo = true, maxRetries = 2, needsSession = false, secure = true)
    Call<NetworkResponse> googlePlusLogin(@Body GooglePlusLoginRequest googlePlusLoginRequest);

    @POST("/v2/user/google/login")
    @SNP(advId = true, deviceInfo = true, maxRetries = 2, needsSession = false, secure = true)
    Call<NetworkResponse> googleSignInLogin(@Body GoogleLoginRequest googleLoginRequest);

    @POST("/v2/user/guestLogin")
    @SNP(advId = true, deviceInfo = true, maxRetries = 2, needsSession = false)
    Call<NetworkResponse> guestLogin(@Body GuestLoginRequest guestLoginRequest);

    @POST("/v2/account/lookup")
    @SNP(maxRetries = 2)
    Call<NetworkResponse> lookupAccounts(@Body AccountsLookupRequest accountsLookupRequest);

    @POST("/v2/user/lookup")
    @SNP(maxRetries = 2, needsSession = false)
    Call<NetworkResponse> lookupUser(@Body UserLookupRequest userLookupRequest);

    @POST("/v2/user/password/reset")
    @SNP(maxRetries = 2, needsSession = false)
    Call<NetworkResponse> passwordReset(@Body PasswordResetRequest passwordResetRequest);

    @POST("/v2/login/phone")
    @SNP(advId = true, deviceInfo = true, maxRetries = 2, needsSession = false, secure = true, useCommon = true)
    Call<NetworkResponse> phoneLogin(@Body LoginPhoneRequest loginPhoneRequest);

    @POST("/v2/login/refresh")
    @SNP(advId = true, deviceInfo = true, maxRetries = 2, needsSession = false, secure = true, useCommon = true)
    Call<NetworkResponse> refreshTokenLogin(@Body LoginTokenRequest loginTokenRequest);

    @POST("/v2/pref/update")
    @SNP(maxRetries = 2)
    Call<NetworkResponse> updatePreferences(@Body UpdatePreferencesRequest updatePreferencesRequest);

    @POST("/v2/user/blurb/update")
    @SNP(maxRetries = 2)
    Call<NetworkResponse> updateUserBlurb(@Body UpdateUserBlurbRequest updateUserBlurbRequest);

    @Multipart
    @POST("/v2/user/uploadPicture")
    @SNP(maxRetries = 2)
    Call<NetworkResponse> uploadPicture(@Part MultipartBody.Part part);

    @POST("/v2/user/blurb")
    @SNP(maxRetries = 2)
    Call<NetworkResponse> userBlurb(@Body UserBlurbRequest userBlurbRequest);

    @POST("/v2/user/init")
    @SNP(deviceInfo = true, maxRetries = 2, needsSession = false)
    Call<NetworkResponse> userInit(@Body UserInitRequest userInitRequest);

    @POST("/v2/user/personal/update")
    @SNP(maxRetries = 2, secure = true)
    Call<NetworkResponse> userPersonalUpdate(@Body UserPersonalUpdateRequest userPersonalUpdateRequest);

    @POST("/v2/user/profile")
    @SNP(maxRetries = 2)
    Call<NetworkResponse> userProfile(@Body UserProfileRequest userProfileRequest);

    @POST("/v2/user/update")
    @SNP(maxRetries = 2, secure = true)
    Call<NetworkResponse> userUpdate(@Body UserUpdateRequest userUpdateRequest);
}
