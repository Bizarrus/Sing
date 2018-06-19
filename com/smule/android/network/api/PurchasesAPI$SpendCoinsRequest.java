package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PurchasesAPI$SpendCoinsRequest extends SnpRequest {
    public Integer amount;
    public String notes;
    public String productId;
    public String productSku;
    public String productType;

    public PurchasesAPI$SpendCoinsRequest setAmount(Integer num) {
        this.amount = num;
        return this;
    }

    public PurchasesAPI$SpendCoinsRequest setProductType(PurchasesAPI$PurchaseProductType purchasesAPI$PurchaseProductType) {
        this.productType = purchasesAPI$PurchaseProductType.toString();
        return this;
    }

    public PurchasesAPI$SpendCoinsRequest setProductId(String str) {
        this.productId = str;
        return this;
    }

    public PurchasesAPI$SpendCoinsRequest setProductSku(String str) {
        this.productSku = str;
        return this;
    }

    public PurchasesAPI$SpendCoinsRequest setNotes(String str) {
        this.notes = str;
        return this;
    }
}
