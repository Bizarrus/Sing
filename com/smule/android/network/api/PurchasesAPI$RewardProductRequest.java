package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PurchasesAPI$RewardProductRequest extends SnpRequest {
    public String notes;
    public String productId;
    public String productType;

    public PurchasesAPI$RewardProductRequest setProductId(String str) {
        this.productId = str;
        return this;
    }

    public PurchasesAPI$RewardProductRequest setProductType(String str) {
        this.productType = str;
        return this;
    }

    public PurchasesAPI$RewardProductRequest setNotes(String str) {
        this.notes = str;
        return this;
    }
}
