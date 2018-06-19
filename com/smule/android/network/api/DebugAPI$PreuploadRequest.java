package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class DebugAPI$PreuploadRequest extends SnpRequest {
    public String resourceType;

    public DebugAPI$PreuploadRequest setResourceType(String str) {
        this.resourceType = str;
        return this;
    }
}
