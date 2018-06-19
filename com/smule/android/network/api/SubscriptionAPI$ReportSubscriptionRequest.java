package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class SubscriptionAPI$ReportSubscriptionRequest extends SnpRequest {
    public String receipt;
    public String sku;

    public SubscriptionAPI$ReportSubscriptionRequest setSku(String str) {
        this.sku = str;
        return this;
    }

    public SubscriptionAPI$ReportSubscriptionRequest setReceipt(String str) {
        this.receipt = "{\"orders\":[" + str + "]}";
        return this;
    }
}
