package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import com.smule.android.utils.LocationUtils;

public class PerformancesAPI$CommentPerformanceRequest extends SnpRequest {
    public String comment;
    public Float latitude;
    public Float longitude;
    public String performanceKey;
    public Long replyToAccountId;

    public PerformancesAPI$CommentPerformanceRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }

    public PerformancesAPI$CommentPerformanceRequest setComment(String str) {
        this.comment = str;
        return this;
    }

    public PerformancesAPI$CommentPerformanceRequest setLatAndLong(Float f, Float f2) {
        if (LocationUtils.m19001a(f.floatValue(), f2.floatValue())) {
            this.latitude = f;
            this.longitude = f2;
        }
        return this;
    }

    public PerformancesAPI$CommentPerformanceRequest setReplyToAccountId(Long l) {
        this.replyToAccountId = l;
        return this;
    }
}
