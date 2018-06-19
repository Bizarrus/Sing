package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class UserAPI$LoginTokenRequest extends SnpRequest {
    public String refreshToken;

    public UserAPI$LoginTokenRequest setRefreshToken(String str) {
        this.refreshToken = str;
        return this;
    }

    public UserAPI$LoginTokenRequest setCommonRequest(UserAPI$LoginRequestCommonRequest userAPI$LoginRequestCommonRequest) {
        this.common = userAPI$LoginRequestCommonRequest;
        return this;
    }
}
