package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PromotionAPI$GetBannersRequest extends SnpRequest {
    public String screenType;

    public PromotionAPI$GetBannersRequest setScreenType(String str) {
        this.screenType = str;
        return this;
    }
}
