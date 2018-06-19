package com.smule.android.ads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdsSettingsManager$AdSettings {
    @JsonProperty("frequency")
    int mFrequency;
    @JsonProperty("timeout")
    int mTimeout = 5;

    public AdsSettingsManager$AdSettings(int i, int i2) {
        this.mFrequency = i;
        this.mTimeout = i2;
    }

    public int m7652a() {
        return this.mTimeout * 1000;
    }
}
