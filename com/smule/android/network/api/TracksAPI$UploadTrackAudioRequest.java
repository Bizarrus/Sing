package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class TracksAPI$UploadTrackAudioRequest extends SnpRequest {
    public String performanceKey;
    public String trackKey;

    public TracksAPI$UploadTrackAudioRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }

    public TracksAPI$UploadTrackAudioRequest setTrackKey(String str) {
        this.trackKey = str;
        return this;
    }
}
