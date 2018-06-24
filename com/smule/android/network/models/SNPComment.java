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
import com.smule.android.network.models.AccountIcon;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SNPComment {
    private static final String a = SNPComment.class.getName();
    @JsonProperty(value="accountIcon")
    public AccountIcon accountIcon;
    @JsonProperty(value="app")
    public String app;
    @JsonProperty(value="latitude")
    public float latitude;
    @JsonProperty(value="longitude")
    public float longitude;
    @JsonProperty(value="message")
    public String message;
    @JsonProperty(value="postKey")
    public String postKey;
    @JsonProperty(value="time")
    public long time;
}

