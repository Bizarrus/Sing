package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class DebugAPI$UploadRequest extends SnpRequest {
    public long resourceId;
    public String resourceType;

    public DebugAPI$UploadRequest setResourceType(String str) {
        this.resourceType = str;
        return this;
    }

    public DebugAPI$UploadRequest setResourceId(long j) {
        this.resourceId = j;
        return this;
    }
}
