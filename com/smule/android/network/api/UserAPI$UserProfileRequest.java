package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class UserAPI$UserProfileRequest extends SnpRequest {
    public Long accountId;
    public String email;
    public String fbId;
    public String handle;

    public UserAPI$UserProfileRequest setAccountId(Long l) {
        this.accountId = l;
        return this;
    }

    public UserAPI$UserProfileRequest setEmail(String str) {
        this.email = str;
        return this;
    }

    public UserAPI$UserProfileRequest setHandle(String str) {
        this.handle = str;
        return this;
    }

    public UserAPI$UserProfileRequest setFbId(String str) {
        this.fbId = str;
        return this;
    }
}
