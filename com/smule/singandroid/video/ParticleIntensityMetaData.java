/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.singandroid.video;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.utils.JsonUtils;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ParticleIntensityMetaData
implements Serializable {
    @JsonProperty(value="user_intensity_factor")
    public float intensityFactor;

    public String toString() {
        return JsonUtils.a(this);
    }
}

