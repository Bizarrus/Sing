package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class OpenCallAPI$SetOpenCallPrivacyRequest extends SnpRequest {
    public Boolean isPrivate;
    public String openCallKey;

    public OpenCallAPI$SetOpenCallPrivacyRequest setOpenCallKey(String str) {
        this.openCallKey = str;
        return this;
    }
}
