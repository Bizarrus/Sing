package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import com.smule.android.utils.TimeUtils;

public class UserAPI$EmailRegisterRequest extends SnpRequest {
    public String email;
    public String password;
    public int tzOffset = TimeUtils.m19067a();

    public UserAPI$EmailRegisterRequest setEmail(String str) {
        this.email = str;
        return this;
    }

    public UserAPI$EmailRegisterRequest setPassword(String str) {
        this.password = str;
        return this;
    }
}
