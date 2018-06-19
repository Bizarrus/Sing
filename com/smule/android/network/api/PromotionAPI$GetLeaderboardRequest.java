package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PromotionAPI$GetLeaderboardRequest extends SnpRequest {
    public int limit;
    public int offset;
    public String orderBy;
    public Long promoId;

    public PromotionAPI$GetLeaderboardRequest setPromoId(Long l) {
        this.promoId = l;
        return this;
    }

    public PromotionAPI$GetLeaderboardRequest setOrderBy(String str) {
        this.orderBy = str;
        return this;
    }

    public PromotionAPI$GetLeaderboardRequest setOffset(int i) {
        this.offset = i;
        return this;
    }

    public PromotionAPI$GetLeaderboardRequest setLimit(int i) {
        this.limit = i;
        return this;
    }
}
