package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class LocalizationAPI$GetPackageRequest extends SnpRequest {
    public String locPackageId;

    public LocalizationAPI$GetPackageRequest setLocPackageId(String str) {
        this.locPackageId = str;
        return this;
    }
}
