package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class UserAPI$PasswordResetRequest extends SnpRequest {
    public String email;

    public UserAPI$PasswordResetRequest setEmail(String str) {
        this.email = str;
        return this;
    }
}
