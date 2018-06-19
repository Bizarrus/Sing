package com.smule.android.network.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonObject
public class ContestData$AccountScore {
    @JsonProperty("accountIcon")
    @JsonField
    public AccountIcon accountIcon;
    @JsonProperty("score")
    @JsonField
    public Integer score;
}
