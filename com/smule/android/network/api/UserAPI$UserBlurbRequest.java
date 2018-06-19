package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class UserAPI$UserBlurbRequest extends SnpRequest {
    public Long accountId;

    public UserAPI$UserBlurbRequest setAccountId(Long l) {
        this.accountId = l;
        return this;
    }
}
