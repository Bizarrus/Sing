package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class StoreAPI$GetStoreRequest extends SnpRequest {
    public String storeId;

    public StoreAPI$GetStoreRequest setStoreId(String str) {
        this.storeId = str;
        return this;
    }
}
