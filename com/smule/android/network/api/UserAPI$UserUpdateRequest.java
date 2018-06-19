package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import com.smule.android.utils.EmailOptIn;

public class UserAPI$UserUpdateRequest extends SnpRequest {
    public String email;
    public String handle;
    public int newsletter;
    public String password;

    public UserAPI$UserUpdateRequest setHandle(String str) {
        this.handle = str;
        return this;
    }

    public UserAPI$UserUpdateRequest setPassword(String str) {
        this.password = str;
        return this;
    }

    public UserAPI$UserUpdateRequest setEmail(String str) {
        this.email = str;
        return this;
    }

    public UserAPI$UserUpdateRequest setNewsletter(EmailOptIn emailOptIn) {
        if (emailOptIn != null) {
            this.newsletter = emailOptIn.m18964a();
        }
        return this;
    }
}
