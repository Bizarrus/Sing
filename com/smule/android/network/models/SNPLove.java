package com.smule.android.network.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SNPLove {
    private static final String f6894a = SNPLove.class.getName();
    @JsonProperty("accountIcon")
    public AccountIcon accountIcon;
    @JsonProperty("app")
    public String app;
    @JsonProperty("latitude")
    public float latitude;
    @JsonProperty("longitude")
    public float longitude;
    @JsonProperty("postKey")
    public String postKey;
    @JsonProperty("time")
    public long time;
}
