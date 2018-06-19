package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;

public class PromotionManager$PromotionResponse extends ParsedResponse {
    @JsonProperty("hashtag")
    public String hashtag;
    @JsonProperty("promoUrl")
    public String promoUrl;

    static PromotionManager$PromotionResponse m8045a(NetworkResponse networkResponse) {
        return (PromotionManager$PromotionResponse) ParsedResponse.m7676a(networkResponse, PromotionManager$PromotionResponse.class);
    }
}
