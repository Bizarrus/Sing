package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.models.PerformanceV2$CompositionType;
import com.smule.android.utils.LocationUtils;

public class PerformancesAPI$CreatePerfRequest extends SnpRequest {
    public String arrKey;
    public Integer arrVer;
    public Long audioResourceId;
    public PerformanceV2$CompositionType compType;
    public Long coverResourceId;
    public PerformancesAPI$EnsembleType ensembleType;
    public Boolean isPrivate;
    public Float latitude;
    public Float longitude;
    public String message;
    public Long metadataResourceId;
    public Long seconds;
    public String songId;
    public String title;
    public String trackOptions;
    public Integer trackPartId;
    public Boolean trackVideo;
    public Boolean usedHeadphone;
    public Float vfactor;
    public Long videoResourceId;
    public Float vscore;

    public PerformancesAPI$CreatePerfRequest setCompType(PerformanceV2$CompositionType performanceV2$CompositionType) {
        this.compType = performanceV2$CompositionType;
        return this;
    }

    public PerformancesAPI$CreatePerfRequest setArrangement(String str, int i) {
        if (str != null) {
            this.arrKey = str;
            this.arrVer = Integer.valueOf(i);
        }
        return this;
    }

    public PerformancesAPI$CreatePerfRequest setSongId(String str) {
        this.songId = str;
        return this;
    }

    public PerformancesAPI$CreatePerfRequest setTitle(String str) {
        this.title = str;
        return this;
    }

    public PerformancesAPI$CreatePerfRequest setMessage(String str) {
        this.message = str;
        return this;
    }

    public PerformancesAPI$CreatePerfRequest setSeconds(Long l) {
        this.seconds = l;
        return this;
    }

    public PerformancesAPI$CreatePerfRequest setPrivate(Boolean bool) {
        this.isPrivate = bool;
        return this;
    }

    public PerformancesAPI$CreatePerfRequest setLatAndLong(Float f, Float f2) {
        if (LocationUtils.m19001a(f.floatValue(), f2.floatValue())) {
            this.latitude = f;
            this.longitude = f2;
        }
        return this;
    }

    public PerformancesAPI$CreatePerfRequest setTrackOptions(String str) {
        this.trackOptions = str;
        return this;
    }

    public PerformancesAPI$CreatePerfRequest setAudioResourceId(Long l) {
        this.audioResourceId = l;
        return this;
    }

    public PerformancesAPI$CreatePerfRequest setCoverResourceId(Long l) {
        this.coverResourceId = l;
        return this;
    }

    public PerformancesAPI$CreatePerfRequest setMetadataResourceId(Long l) {
        this.metadataResourceId = l;
        return this;
    }

    public PerformancesAPI$CreatePerfRequest setVideoResourceId(Long l) {
        this.videoResourceId = l;
        return this;
    }

    public PerformancesAPI$CreatePerfRequest setTrackPartId(Integer num) {
        this.trackPartId = num;
        return this;
    }

    public PerformancesAPI$CreatePerfRequest setTrackVideo(Boolean bool) {
        this.trackVideo = bool;
        return this;
    }

    public PerformancesAPI$CreatePerfRequest setUsedHeadphone(Boolean bool) {
        this.usedHeadphone = bool;
        return this;
    }

    public PerformancesAPI$CreatePerfRequest setVscore(Float f) {
        this.vscore = f;
        return this;
    }

    public PerformancesAPI$CreatePerfRequest setVfactor(Float f) {
        this.vfactor = f;
        return this;
    }

    public PerformancesAPI$CreatePerfRequest setEnsembleType(PerformancesAPI$EnsembleType performancesAPI$EnsembleType) {
        this.ensembleType = performancesAPI$EnsembleType;
        return this;
    }
}
