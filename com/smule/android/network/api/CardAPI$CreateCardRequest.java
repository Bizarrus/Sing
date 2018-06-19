package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class CardAPI$CreateCardRequest extends SnpRequest {
    public Integer frameId;
    public String message;
    public String performanceKey;

    public CardAPI$CreateCardRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }

    public CardAPI$CreateCardRequest setMessage(String str) {
        this.message = str;
        return this;
    }

    public CardAPI$CreateCardRequest setFrameId(Integer num) {
        this.frameId = num;
        return this;
    }
}
