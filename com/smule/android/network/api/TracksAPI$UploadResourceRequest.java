package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.PerformanceManager.PerformanceResourceInfo;

public class TracksAPI$UploadResourceRequest extends SnpRequest {
    public PerformanceResourceInfo file1ResourceInfo;
    public PerformanceResourceInfo file2ResourceInfo;
    public PerformanceResourceInfo file3ResourceInfo;
    public String performanceKey;
    public String trackKey;
    public PerformancesAPI$UploadType uploadType;

    public TracksAPI$UploadResourceRequest setFile1ResourceInfo(PerformanceResourceInfo performanceResourceInfo) {
        this.file1ResourceInfo = performanceResourceInfo;
        return this;
    }

    public TracksAPI$UploadResourceRequest setFile2ResourceInfo(PerformanceResourceInfo performanceResourceInfo) {
        this.file2ResourceInfo = performanceResourceInfo;
        return this;
    }

    public TracksAPI$UploadResourceRequest setFile3ResourceInfo(PerformanceResourceInfo performanceResourceInfo) {
        this.file3ResourceInfo = performanceResourceInfo;
        return this;
    }

    public TracksAPI$UploadResourceRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }

    public TracksAPI$UploadResourceRequest setTrackKey(String str) {
        this.trackKey = str;
        return this;
    }

    public TracksAPI$UploadResourceRequest setUploadType(PerformancesAPI$UploadType performancesAPI$UploadType) {
        this.uploadType = performancesAPI$UploadType;
        return this;
    }
}
