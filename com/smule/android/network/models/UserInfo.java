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

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserInfo
extends ParsedResponse {
    @JsonProperty
    public String emailId;
    @JsonProperty
    public String emailStatus;

    public static UserInfo a(NetworkResponse networkResponse) {
        return UserInfo.a(networkResponse, UserInfo.class);
    }
}

