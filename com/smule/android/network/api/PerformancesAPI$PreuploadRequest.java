package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.models.PerformanceV2$CompositionType;

public class PerformancesAPI$PreuploadRequest extends SnpRequest {
    public String arrKey;
    public PerformanceV2$CompositionType compType;
    public PerformancesAPI$EnsembleType ensembleType;
    public String performanceKey;
    public String seedKey;
    public String songId;
    public boolean trackVideo;
    public PerformancesAPI$UploadType uploadType;

    public PerformancesAPI$PreuploadRequest setUploadType(PerformancesAPI$UploadType performancesAPI$UploadType) {
        this.uploadType = performancesAPI$UploadType;
        return this;
    }

    public PerformancesAPI$PreuploadRequest setEnsembleType(PerformancesAPI$EnsembleType performancesAPI$EnsembleType) {
        this.ensembleType = performancesAPI$EnsembleType;
        return this;
    }

    public PerformancesAPI$PreuploadRequest setCompType(PerformanceV2$CompositionType performanceV2$CompositionType) {
        this.compType = performanceV2$CompositionType;
        return this;
    }

    public PerformancesAPI$PreuploadRequest setSongId(String str) {
        this.songId = str;
        return this;
    }

    public PerformancesAPI$PreuploadRequest setArrKey(String str) {
        this.arrKey = str;
        return this;
    }

    public PerformancesAPI$PreuploadRequest setSeedKey(String str) {
        this.seedKey = str;
        return this;
    }

    public PerformancesAPI$PreuploadRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }

    public PerformancesAPI$PreuploadRequest setTrackVideo(boolean z) {
        this.trackVideo = z;
        return this;
    }
}
