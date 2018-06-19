package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class UserAPI$LoginPhoneRequest extends SnpRequest {
    public String accessToken;
    public String fbAppId;

    public UserAPI$LoginPhoneRequest setAccessToken(String str) {
        this.accessToken = str;
        return this;
    }

    public UserAPI$LoginPhoneRequest setFbAppId(String str) {
        this.fbAppId = str;
        return this;
    }

    public UserAPI$LoginPhoneRequest setCommonRequest(UserAPI$LoginRequestCommonRequest userAPI$LoginRequestCommonRequest) {
        this.common = userAPI$LoginRequestCommonRequest;
        return this;
    }
}
