package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class UserAPI$UserLookupRequest extends SnpRequest {
    public Long accountId;
    public String email;
    public String handle;

    public UserAPI$UserLookupRequest setAccountId(Long l) {
        this.accountId = l;
        return this;
    }

    public UserAPI$UserLookupRequest setEmail(String str) {
        this.email = str;
        return this;
    }

    public UserAPI$UserLookupRequest setHandle(String str) {
        this.handle = str;
        return this;
    }
}
