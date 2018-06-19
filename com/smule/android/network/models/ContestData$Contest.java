package com.smule.android.network.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

@JsonObject
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContestData$Contest {
    @JsonProperty("end")
    @JsonField
    public Long end;
    @JsonProperty("id")
    @JsonField
    public Long id;
    @JsonProperty("numWinners")
    @JsonField
    public Integer numWinners;
    @JsonProperty("rewards")
    @JsonField
    public ArrayList<ContestData$Reward> rewards;
    @JsonProperty("songId")
    @JsonField
    public String songId;
    @JsonProperty("start")
    @JsonField
    public Long start;
}
