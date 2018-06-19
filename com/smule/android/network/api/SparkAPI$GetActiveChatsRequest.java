package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class SparkAPI$GetActiveChatsRequest extends SnpRequest {
    public Integer limit;
    public String type;

    public SparkAPI$GetActiveChatsRequest setType(String str) {
        this.type = str;
        return this;
    }

    public SparkAPI$GetActiveChatsRequest setLimit(Integer num) {
        this.limit = num;
        return this;
    }
}
