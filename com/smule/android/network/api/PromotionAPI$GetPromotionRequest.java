package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PromotionAPI$GetPromotionRequest extends SnpRequest {
    public Long promoId;

    public PromotionAPI$GetPromotionRequest setPromoId(Long l) {
        this.promoId = l;
        return this;
    }
}
