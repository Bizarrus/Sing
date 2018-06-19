package com.smule.android.network.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

@JsonObject
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContestData$ContestInfo {
    @JsonProperty("contest")
    @JsonField
    public ContestData$Contest contest;
    @JsonProperty("leaderboard")
    @JsonField
    public ArrayList<ContestData$AccountScore> leaderboard;
    @JsonProperty("rank")
    @JsonField
    public Long rank;
    @JsonProperty("score")
    @JsonField
    public Integer score;

    public boolean isEnded() {
        return this.contest.end.longValue() * 1000 < System.currentTimeMillis();
    }
}
