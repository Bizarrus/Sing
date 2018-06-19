package com.smule.android.network.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.models.ContestData.SubmitState;
import java.util.Date;

@JsonObject
public class ContestData$ContestUserState {
    @JsonProperty("rank")
    @JsonField
    public Long rank;
    @JsonProperty("reported")
    @JsonField
    public Boolean reported;
    @JsonProperty("rewardEndDate")
    @JsonField
    public Date rewardEndDate;
    @JsonProperty("score")
    @JsonField
    public Integer score;
    @JsonProperty("started")
    @JsonField
    public Boolean started;
    @JsonProperty("submitState")
    @JsonField
    public SubmitState submitState = SubmitState.NOT_SUBMITTED;
}
