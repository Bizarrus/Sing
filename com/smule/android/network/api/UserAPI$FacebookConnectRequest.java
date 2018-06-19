package com.smule.android.network.api;

import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.SnpRequest;

public class UserAPI$FacebookConnectRequest extends SnpRequest {
    public String accessToken;
    public String afbId;
    public String birthday;
    public String fbAppId = MagicNetwork.d().getFacebookAppId();
    public String fbEmail;
    public String firstName;
    public String gender;
    public String lastName;
    public Integer maxAge;
    public Integer minAge;
    public String tfb;

    public UserAPI$FacebookConnectRequest setAfbId(String str) {
        this.afbId = str;
        return this;
    }

    public UserAPI$FacebookConnectRequest setFbAppId(String str) {
        this.fbAppId = str;
        return this;
    }

    public UserAPI$FacebookConnectRequest setTfb(String str) {
        this.tfb = str;
        return this;
    }

    public UserAPI$FacebookConnectRequest setAccessToken(String str) {
        this.accessToken = str;
        return this;
    }

    public UserAPI$FacebookConnectRequest setFbEmail(String str) {
        this.fbEmail = str;
        return this;
    }

    public UserAPI$FacebookConnectRequest setFirstName(String str) {
        this.firstName = str;
        return this;
    }

    public UserAPI$FacebookConnectRequest setLastName(String str) {
        this.lastName = str;
        return this;
    }

    public UserAPI$FacebookConnectRequest setGender(String str) {
        this.gender = str;
        return this;
    }

    public UserAPI$FacebookConnectRequest setBirthday(String str) {
        this.birthday = str;
        return this;
    }

    public UserAPI$FacebookConnectRequest setMinAge(Integer num) {
        this.minAge = num;
        return this;
    }

    public UserAPI$FacebookConnectRequest setMaxAge(Integer num) {
        this.maxAge = num;
        return this;
    }
}
