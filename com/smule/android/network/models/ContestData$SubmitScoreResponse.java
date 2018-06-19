package com.smule.android.network.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public class ContestData$SubmitScoreResponse {
    @JsonProperty("contestId")
    public Long contestId;
    @JsonProperty("leaderboard")
    public ArrayList<ContestData$AccountScore> leaderboard;
    @JsonProperty("rank")
    public Long rank;
    @JsonProperty("score")
    public Integer score;
}
