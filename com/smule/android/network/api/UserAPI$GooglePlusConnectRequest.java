package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class UserAPI$GooglePlusConnectRequest extends SnpRequest {
    public String accessToken;
    public String birthday;
    public String email;
    public String gender;
    public String id;

    public UserAPI$GooglePlusConnectRequest setId(String str) {
        this.id = str;
        return this;
    }

    public UserAPI$GooglePlusConnectRequest setAccessToken(String str) {
        this.accessToken = str;
        return this;
    }

    public UserAPI$GooglePlusConnectRequest setEmail(String str) {
        this.email = str;
        return this;
    }

    public UserAPI$GooglePlusConnectRequest setGender(String str) {
        this.gender = str;
        return this;
    }

    public UserAPI$GooglePlusConnectRequest setBirthday(String str) {
        this.birthday = str;
        return this;
    }
}
