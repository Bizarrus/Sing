package com.smule.android.network.api;

public enum StoreAPI$StreamType {
    SUBSCRIPTION("sub"),
    PAID("paid"),
    OWNED("own");
    
    private String mType;

    private StoreAPI$StreamType(String str) {
        this.mType = str;
    }

    String getValue() {
        return this.mType;
    }
}
