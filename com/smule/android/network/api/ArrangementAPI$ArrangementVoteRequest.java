package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class ArrangementAPI$ArrangementVoteRequest extends SnpRequest {
    public String arrKey;
    public Integer reason;
    public Integer ver;
    public String vote;

    public ArrangementAPI$ArrangementVoteRequest setArrKey(String str) {
        this.arrKey = str;
        return this;
    }

    public ArrangementAPI$ArrangementVoteRequest setVer(Integer num) {
        this.ver = num;
        return this;
    }

    public ArrangementAPI$ArrangementVoteRequest setReason(Integer num) {
        this.reason = num;
        return this;
    }

    public ArrangementAPI$ArrangementVoteRequest setVote(String str) {
        this.vote = str;
        return this;
    }
}
