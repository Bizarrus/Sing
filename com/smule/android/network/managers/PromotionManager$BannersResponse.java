package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.Banner;
import java.util.ArrayList;

public class PromotionManager$BannersResponse extends ParsedResponse {
    public transient boolean f6822b = false;
    @JsonProperty("banners")
    public ArrayList<Banner> banners;

    static PromotionManager$BannersResponse m8044a(NetworkResponse networkResponse) {
        return (PromotionManager$BannersResponse) ParsedResponse.m7676a(networkResponse, PromotionManager$BannersResponse.class);
    }
}
