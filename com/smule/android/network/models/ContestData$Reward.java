package com.smule.android.network.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonObject
public class ContestData$Reward {
    public static final String TYPE_SONG = "SONG";
    public static final String TYPE_XP_BOOST = "XP";
    @JsonProperty("type")
    @JsonField
    public String type;
    @JsonProperty("value")
    @JsonField
    public String value;
}
