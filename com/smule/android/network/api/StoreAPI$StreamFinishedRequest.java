package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.HashMap;
import java.util.Map;

public class StoreAPI$StreamFinishedRequest extends SnpRequest {
    public Map<String, Object> currencyPrice;
    public String productId;
    public String productType;
    public String seedPerformanceKey;
    public String songId;
    public String type;

    public StoreAPI$StreamFinishedRequest setProductId(String str) {
        this.productId = str;
        return this;
    }

    public StoreAPI$StreamFinishedRequest setSongId(String str) {
        this.songId = str;
        return this;
    }

    private StoreAPI$StreamFinishedRequest setCurrency(String str, Integer num) {
        Map hashMap = new HashMap();
        hashMap.put("currencyUid", str);
        hashMap.put("price", num);
        this.currencyPrice = hashMap;
        return this;
    }

    public StoreAPI$StreamFinishedRequest setStreamTypeAndCurrency(StoreAPI$StreamType storeAPI$StreamType, String str, Integer num) {
        if (storeAPI$StreamType != null) {
            this.type = storeAPI$StreamType.getValue();
            if (storeAPI$StreamType == StoreAPI$StreamType.PAID) {
                setCurrency(str, num);
            }
        }
        return this;
    }

    public StoreAPI$StreamFinishedRequest setProductType(StoreAPI$ProductType storeAPI$ProductType) {
        this.productType = storeAPI$ProductType.toString();
        return this;
    }

    public StoreAPI$StreamFinishedRequest setSeedPerformanceKey(String str) {
        this.seedPerformanceKey = str;
        return this;
    }
}
