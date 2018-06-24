/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  com.smule.singandroid.video.VideoEffects
 *  com.smule.singandroid.video.VideoEffects$Intensity
 */
package com.smule.singandroid.survey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.logging.Analytics;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.PostSingBundle;
import com.smule.singandroid.SingBundle;
import com.smule.singandroid.video.VideoEffects;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AVQualityPerformanceInfo {
    @JsonProperty
    public String arrangementKey;
    @JsonProperty
    public String colorFilterId;
    @JsonProperty
    public Analytics ensembleType;
    @JsonProperty
    public boolean headphonesHadMic;
    @JsonProperty
    public String intensityId;
    @JsonProperty
    public boolean isAirbrushOn;
    @JsonProperty
    public boolean isAutoTuneOn;
    @JsonProperty
    public boolean isJoin;
    @JsonProperty
    public String performanceKey;
    @JsonProperty
    public boolean usedHeadphones;
    @JsonProperty
    public boolean video;
    @JsonProperty
    public String videoStyleId;

    public AVQualityPerformanceInfo() {
    }

    /*
     * Enabled aggressive block sorting
     */
    public AVQualityPerformanceInfo(PostSingBundle postSingBundle) {
        SingBundle singBundle = postSingBundle.b;
        if (singBundle != null) {
            this.video = singBundle.f();
            if (postSingBundle.c != null) {
                this.performanceKey = postSingBundle.c.performanceKey;
            }
            this.usedHeadphones = singBundle.b("HEADTHING_ONLY", false);
            this.headphonesHadMic = singBundle.b("HEADPHONE_HAD_MIC", false);
            this.isJoin = singBundle.k;
            this.ensembleType = singBundle.d() ? Analytics.c : (singBundle.c() ? Analytics.b : Analytics.a);
            if (singBundle.d != null && singBundle.d.t()) {
                this.arrangementKey = ((ArrangementVersionLiteEntry)singBundle.d).a().key;
            }
            this.videoStyleId = singBundle.h();
            this.colorFilterId = singBundle.i();
            this.intensityId = singBundle.j().b();
            this.isAirbrushOn = singBundle.k();
            this.isAutoTuneOn = singBundle.l();
        }
    }
}

