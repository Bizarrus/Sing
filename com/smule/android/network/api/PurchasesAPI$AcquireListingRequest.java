package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PurchasesAPI$AcquireListingRequest extends SnpRequest {
    public String listingId;
    public String productId;

    public PurchasesAPI$AcquireListingRequest setProductId(String str) {
        this.productId = str;
        return this;
    }

    public PurchasesAPI$AcquireListingRequest setListingId(String str) {
        this.listingId = str;
        return this;
    }
}
