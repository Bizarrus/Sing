/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.ProfileCustomizations;
import com.smule.android.network.models.UserProfile;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SingUserProfile
extends ParsedResponse {
    @JsonProperty
    public UserProfile profile;
    @JsonProperty
    public ProfileCustomizations singProfile;

    public static SingUserProfile a(NetworkResponse networkResponse) {
        return SingUserProfile.a(networkResponse, SingUserProfile.class);
    }
}

