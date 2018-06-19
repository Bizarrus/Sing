package com.smule.android.network.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SNPComment {
    private static final String f6893a = SNPComment.class.getName();
    @JsonProperty("accountIcon")
    public AccountIcon accountIcon;
    @JsonProperty("app")
    public String app;
    @JsonProperty("latitude")
    public float latitude;
    @JsonProperty("longitude")
    public float longitude;
    @JsonProperty("message")
    public String message;
    @JsonProperty("postKey")
    public String postKey;
    @JsonProperty("time")
    public long time;
}
