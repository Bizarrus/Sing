package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class UserAPI$UserPersonalUpdateRequest extends SnpRequest {
    public String countryCode;
    public String firstName;
    public String lastName;
    public String location;

    public UserAPI$UserPersonalUpdateRequest setFirstName(String str) {
        this.firstName = str;
        return this;
    }

    public UserAPI$UserPersonalUpdateRequest setLastName(String str) {
        this.lastName = str;
        return this;
    }

    public UserAPI$UserPersonalUpdateRequest setLocation(String str) {
        this.location = str;
        return this;
    }

    public UserAPI$UserPersonalUpdateRequest setCountryCode(String str) {
        this.countryCode = str;
        return this;
    }
}
