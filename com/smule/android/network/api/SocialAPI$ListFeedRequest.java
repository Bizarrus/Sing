package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class SocialAPI$ListFeedRequest extends SnpRequest {
    public Integer limit = Integer.valueOf(10);
    public Integer offset = Integer.valueOf(0);

    public SocialAPI$ListFeedRequest setOffset(Integer num) {
        if (num != null) {
            this.offset = num;
        }
        return this;
    }

    public SocialAPI$ListFeedRequest setLimit(Integer num) {
        if (num != null) {
            this.limit = num;
        }
        return this;
    }
}
