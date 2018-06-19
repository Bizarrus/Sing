package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import com.smule.android.utils.LocationUtils;

public class PerformancesAPI$JoinPerfRequest extends SnpRequest {
    public Long audioResourceId;
    public Long coverResourceId;
    public Float latitude;
    public Float longitude;
    public Long metadataResourceId;
    public String performanceKey;
    public String trackOptions;
    public Integer trackPartId;
    public Boolean trackVideo;
    public Boolean usedHeadphone;
    public Float vfactor;
    public Long videoResourceId;
    public Float vscore;

    public PerformancesAPI$JoinPerfRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }

    public PerformancesAPI$JoinPerfRequest setLatAndLong(Float f, Float f2) {
        if (LocationUtils.m19001a(f.floatValue(), f2.floatValue())) {
            this.latitude = f;
            this.longitude = f2;
        }
        return this;
    }

    public PerformancesAPI$JoinPerfRequest setTrackOptions(String str) {
        this.trackOptions = str;
        return this;
    }

    public PerformancesAPI$JoinPerfRequest setAudioResourceId(Long l) {
        this.audioResourceId = l;
        return this;
    }

    public PerformancesAPI$JoinPerfRequest setCoverResourceId(Long l) {
        this.coverResourceId = l;
        return this;
    }

    public PerformancesAPI$JoinPerfRequest setMetadataResourceId(Long l) {
        this.metadataResourceId = l;
        return this;
    }

    public PerformancesAPI$JoinPerfRequest setVideoResourceId(Long l) {
        this.videoResourceId = l;
        return this;
    }

    public PerformancesAPI$JoinPerfRequest setTrackPartId(Integer num) {
        this.trackPartId = num;
        return this;
    }

    public PerformancesAPI$JoinPerfRequest setTrackVideo(Boolean bool) {
        this.trackVideo = bool;
        return this;
    }

    public PerformancesAPI$JoinPerfRequest setUsedHeadphone(Boolean bool) {
        this.usedHeadphone = bool;
        return this;
    }

    public PerformancesAPI$JoinPerfRequest setVscore(Float f) {
        this.vscore = f;
        return this;
    }

    public PerformancesAPI$JoinPerfRequest setVfactor(Float f) {
        this.vfactor = f;
        return this;
    }
}
