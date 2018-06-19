package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class UserAPI$UpdateUserBlurbRequest extends SnpRequest {
    public String blurb;

    public UserAPI$UpdateUserBlurbRequest setBlurb(String str) {
        this.blurb = str;
        return this;
    }
}
