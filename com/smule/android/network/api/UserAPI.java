/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  com.fasterxml.jackson.annotation.JsonInclude
 *  com.fasterxml.jackson.annotation.JsonInclude$Include
 *  okhttp3.MultipartBody
 *  okhttp3.MultipartBody$Part
 *  retrofit2.Call
 *  retrofit2.http.Body
 *  retrofit2.http.Multipart
 *  retrofit2.http.POST
 *  retrofit2.http.Part
 *  retrofit2.http.SNP
 */
package com.smule.android.network.api;

import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.smule.android.l10n.LocaleSettings;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.models.AccountPreference;
import com.smule.android.network.models.ColorTheme;
import com.smule.android.utils.EmailOptIn;
import com.smule.android.utils.TimeUtils;
import com.smule.android.utils.VorgomUtils;
import java.util.Collection;
import java.util.List;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.SNP;

public interface UserAPI {
    @POST(value="/v2/social/block/update")
    @SNP(maxRetries=2)
    public Call<NetworkResponse> blockUnblockUsers(@Body  var1);

    @POST(value="/v2/login/guest")
    @SNP(advId=true, deviceInfo=true, maxRetries=2, needsSession=false, useCommon=true)
    public Call<NetworkResponse> consolidatedGuestLogin(@Body  var1);

    @POST(value="/v2/user/device/login")
    @SNP(advId=true, deviceInfo=true, maxRetries=2, needsSession=false)
    public Call<NetworkResponse> deviceLogin(@Body  var1);

    @POST(value="/v2/user/login")
    @SNP(advId=true, deviceInfo=true, maxRetries=2, needsSession=false, secure=true)
    public Call<NetworkResponse> emailLogin(@Body  var1);

    @POST(value="/v2/user/emailRegister")
    @SNP(advId=true, deviceInfo=true, maxRetries=2, needsSession=false, secure=true)
    public Call<NetworkResponse> emailRegister(@Body  var1);

    @POST(value="/v2/fb/connect")
    @SNP(maxRetries=2, secure=true)
    public Call<NetworkResponse> facebookConnect(@Body  var1);

    @Deprecated
    @POST(value="/v2/fb/disconnect")
    @SNP(maxRetries=2)
    public Call<NetworkResponse> facebookDisconnect(@Body SnpRequest var1);

    @POST(value="/v2/fb/login")
    @SNP(advId=true, deviceInfo=true, maxRetries=2, needsSession=false, secure=true)
    public Call<NetworkResponse> facebookLogin(@Body FacebookLoginRequest var1);

    @POST(value="/v2/user/device/lookup")
    @SNP(deviceInfo=true, maxRetries=2, needsSession=false)
    public Call<NetworkResponse> findAccountByDevice(@Body SnpRequest var1);

    @POST(value="/v2/social/block/list")
    @SNP(maxRetries=2)
    public Call<NetworkResponse> getBlockedUsers(@Body SnpRequest var1);

    @POST(value="/v2/account/info")
    @SNP
    public Call<NetworkResponse> getEmailStatus(@Body  var1);

    @POST(value="/v2/pref")
    @SNP(maxRetries=2)
    public Call<NetworkResponse> getPreferences(@Body  var1);

    @Deprecated
    @POST(value="/v2/user/gplus/connect")
    @SNP(maxRetries=2, secure=true)
    public Call<NetworkResponse> googlePlusConnect(@Body  var1);

    @Deprecated
    @POST(value="/v2/user/gplus/disconnect")
    @SNP(maxRetries=2)
    public Call<NetworkResponse> googlePlusDisconnect(@Body SnpRequest var1);

    @Deprecated
    @POST(value="/v2/user/gplus/login")
    @SNP(advId=true, deviceInfo=true, maxRetries=2, needsSession=false, secure=true)
    public Call<NetworkResponse> googlePlusLogin(@Body GooglePlusLoginRequest var1);

    @POST(value="/v2/user/google/login")
    @SNP(advId=true, deviceInfo=true, maxRetries=2, needsSession=false, secure=true)
    public Call<NetworkResponse> googleSignInLogin(@Body  var1);

    @POST(value="/v2/user/guestLogin")
    @SNP(advId=true, deviceInfo=true, maxRetries=2, needsSession=false)
    public Call<NetworkResponse> guestLogin(@Body  var1);

    @POST(value="/v2/account/lookup")
    @SNP(maxRetries=2)
    public Call<NetworkResponse> lookupAccounts(@Body  var1);

    @POST(value="/v2/user/lookup")
    @SNP(maxRetries=2, needsSession=false)
    public Call<NetworkResponse> lookupUser(@Body  var1);

    @POST(value="/v2/user/password/reset")
    @SNP(maxRetries=2, needsSession=false)
    public Call<NetworkResponse> passwordReset(@Body  var1);

    @POST(value="/v2/login/phone")
    @SNP(advId=true, deviceInfo=true, maxRetries=2, needsSession=false, secure=true, useCommon=true)
    public Call<NetworkResponse> phoneLogin(@Body  var1);

    @POST(value="/v2/user/picture/delete")
    @SNP(maxRetries=2)
    public Call<NetworkResponse> pictureDelete(@Body SnpRequest var1);

    @POST(value="/v2/login/refresh")
    @SNP(advId=true, deviceInfo=true, maxRetries=2, needsSession=false, secure=true, useCommon=true)
    public Call<NetworkResponse> refreshTokenLogin(@Body  var1);

    @POST(value="/v2/user/activation/send")
    @SNP
    public Call<NetworkResponse> resendVerificationEmail(@Body  var1);

    @POST(value="/v2/sing/coverPhoto/delete")
    @SNP(maxRetries=2)
    public Call<NetworkResponse> singCoverPhotoDelete(@Body SnpRequest var1);

    @Multipart
    @POST(value="/v2/sing/coverPhoto/upload")
    @SNP(maxRetries=2)
    public Call<NetworkResponse> singCoverPhotoUpload(@Part MultipartBody.Part var1);

    @POST(value="/v2/sing/profile/update")
    @SNP(maxRetries=2)
    public Call<NetworkResponse> singProfileUpdate(@Body  var1);

    @POST(value="/v2/sing/user/profile")
    @SNP(maxRetries=2)
    public Call<NetworkResponse> singUserProfile(@Body  var1);

    @POST(value="/v2/pref/update")
    @SNP(maxRetries=2)
    public Call<NetworkResponse> updatePreferences(@Body  var1);

    @POST(value="/v2/user/blurb/update")
    @SNP(maxRetries=2)
    public Call<NetworkResponse> updateUserBlurb(@Body  var1);

    @Multipart
    @POST(value="/v2/user/uploadPicture")
    @SNP(maxRetries=2)
    public Call<NetworkResponse> uploadPicture(@Part MultipartBody.Part var1);

    @POST(value="/v2/user/blurb")
    @SNP(maxRetries=2)
    public Call<NetworkResponse> userBlurb(@Body  var1);

    @POST(value="/v2/user/init")
    @SNP(deviceInfo=true, maxRetries=2, needsSession=false)
    public Call<NetworkResponse> userInit(@Body  var1);

    @POST(value="/v2/user/personal/update")
    @SNP(maxRetries=2, secure=true)
    public Call<NetworkResponse> userPersonalUpdate(@Body  var1);

    @POST(value="/v2/user/profile")
    @SNP(maxRetries=2)
    public Call<NetworkResponse> userProfile(@Body  var1);

    @POST(value="/v2/user/update")
    @SNP(maxRetries=2, secure=true)
    public Call<NetworkResponse> userUpdate(@Body  var1);

    public static class FacebookLoginRequest
    extends SnpRequest {
        public String accessToken;
        public String afbId;
        public String app = MagicNetwork.b();
        public boolean automaticLogin;
        public String birthday;
        public String enteredEmail;
        public String fbAppId = MagicNetwork.d().getFacebookAppId();
        public String fbEmail;
        @JsonInclude(value=JsonInclude.Include.ALWAYS)
        public String firstName;
        public String gender;
        @JsonInclude(value=JsonInclude.Include.ALWAYS)
        public String lastName;
        public Integer maxAge;
        public Integer minAge;
        public Long playerId;
        public String preferredLang = LocaleSettings.b();
        public int pulp = VorgomUtils.b();
        public String requestedPassword;
        public String tfb;
        public int tzOffset = TimeUtils.a();
        public boolean vorgom = VorgomUtils.c();

        public FacebookLoginRequest setAccessToken(String string2) {
            this.accessToken = string2;
            return this;
        }

        public FacebookLoginRequest setAfbId(String string2) {
            this.afbId = string2;
            return this;
        }

        public FacebookLoginRequest setApp(String string2) {
            this.app = string2;
            return this;
        }

        public FacebookLoginRequest setAutomaticLogin(boolean bl) {
            this.automaticLogin = bl;
            if (bl) {
                this.preferredLang = null;
            }
            return this;
        }

        public FacebookLoginRequest setBirthday(String string2) {
            this.birthday = string2;
            return this;
        }

        public FacebookLoginRequest setEmail(String string2, String string3) {
            if (!TextUtils.isEmpty((CharSequence)string2) && TextUtils.isEmpty((CharSequence)string3)) {
                this.fbEmail = string2;
            }
            if (TextUtils.isEmpty((CharSequence)string2) && !TextUtils.isEmpty((CharSequence)string3)) {
                this.enteredEmail = string3;
            }
            return this;
        }

        public FacebookLoginRequest setFbAppId(String string2) {
            this.fbAppId = string2;
            return this;
        }

        public FacebookLoginRequest setFirstName(String string2) {
            this.firstName = string2;
            return this;
        }

        public FacebookLoginRequest setGender(String string2) {
            this.gender = string2;
            return this;
        }

        public FacebookLoginRequest setLastName(String string2) {
            this.lastName = string2;
            return this;
        }

        public FacebookLoginRequest setMaxAge(Integer n) {
            this.maxAge = n;
            return this;
        }

        public FacebookLoginRequest setMinAge(Integer n) {
            this.minAge = n;
            return this;
        }

        public FacebookLoginRequest setPlayerId(Long l) {
            this.playerId = l;
            return this;
        }

        public FacebookLoginRequest setRequestedPassword(String string2) {
            this.requestedPassword = string2;
            return this;
        }

        public FacebookLoginRequest setTfb(String string2) {
            this.tfb = string2;
            return this;
        }
    }

    @Deprecated
    public static class GooglePlusLoginRequest
    extends SnpRequest {
        public String accessToken;
        public boolean automaticLogin;
        public String birthday;
        public String email;
        public String firstName;
        public String gender;
        public String id;
        public String lastName;
        public Long playerId;
        public String preferredLang = LocaleSettings.b();
        public int pulp = VorgomUtils.b();
        public String requestedPassword;
        public int tzOffset = TimeUtils.a();
        public boolean vorgom = VorgomUtils.c();

        public GooglePlusLoginRequest setAccessToken(String string2) {
            this.accessToken = string2;
            return this;
        }

        public GooglePlusLoginRequest setAutomaticLogin(boolean bl) {
            this.automaticLogin = bl;
            if (bl) {
                this.preferredLang = null;
            }
            return this;
        }

        public GooglePlusLoginRequest setBirthday(String string2) {
            this.birthday = string2;
            return this;
        }

        public GooglePlusLoginRequest setEmail(String string2) {
            this.email = string2;
            return this;
        }

        public GooglePlusLoginRequest setFirstName(String string2) {
            this.firstName = string2;
            return this;
        }

        public GooglePlusLoginRequest setGender(String string2) {
            this.gender = string2;
            return this;
        }

        public GooglePlusLoginRequest setId(String string2) {
            this.id = string2;
            return this;
        }

        public GooglePlusLoginRequest setLastName(String string2) {
            this.lastName = string2;
            return this;
        }

        public GooglePlusLoginRequest setPlayerId(Long l) {
            this.playerId = l;
            return this;
        }

        public GooglePlusLoginRequest setRequestedPassword(String string2) {
            this.requestedPassword = string2;
            return this;
        }
    }

}

