package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class TopicsAPI$GetTopicOptionsRequest extends SnpRequest {
    public Integer limit = Integer.valueOf(6);
    public Integer offset = Integer.valueOf(0);

    public TopicsAPI$GetTopicOptionsRequest setOffset(Integer num) {
        if (num != null) {
            this.offset = num;
        }
        return this;
    }

    public TopicsAPI$GetTopicOptionsRequest setLimit(Integer num) {
        if (num != null) {
            this.limit = num;
        }
        return this;
    }
}
