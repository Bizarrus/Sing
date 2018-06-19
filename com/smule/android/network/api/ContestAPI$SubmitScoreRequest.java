package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class ContestAPI$SubmitScoreRequest extends SnpRequest {
    public Long contestId;
    public Integer score;
    public String songId;

    public ContestAPI$SubmitScoreRequest setContestId(Long l) {
        this.contestId = l;
        return this;
    }

    public ContestAPI$SubmitScoreRequest setSongId(String str) {
        this.songId = str;
        return this;
    }

    public ContestAPI$SubmitScoreRequest setScore(Integer num) {
        this.score = num;
        return this;
    }
}
