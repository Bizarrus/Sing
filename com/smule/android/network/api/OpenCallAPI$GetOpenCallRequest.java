package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class OpenCallAPI$GetOpenCallRequest extends SnpRequest {
    public String opencallKey;

    public OpenCallAPI$GetOpenCallRequest setOpenCallKey(String str) {
        this.opencallKey = str;
        return this;
    }
}
