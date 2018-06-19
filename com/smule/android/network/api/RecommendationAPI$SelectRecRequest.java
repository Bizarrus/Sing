package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class RecommendationAPI$SelectRecRequest extends SnpRequest {
    public String selection;
    public String selectionType;
    public Boolean topic;

    public RecommendationAPI$SelectRecRequest setSelection(String str) {
        this.selection = str;
        return this;
    }

    public RecommendationAPI$SelectRecRequest setSelectionType(String str) {
        this.selectionType = str;
        return this;
    }

    public RecommendationAPI$SelectRecRequest setTopic(Boolean bool) {
        this.topic = bool;
        return this;
    }
}
