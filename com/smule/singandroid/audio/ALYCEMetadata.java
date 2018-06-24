/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.singandroid.audio;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.utils.JsonUtils;
import com.smule.singandroid.audio.ALYCEMetadata;
import com.smule.singandroid.video.ParticleIntensityMetaData;
import java.io.Serializable;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ALYCEMetadata
implements Parcelable {
    public static final Parcelable.Creator<ALYCEMetadata> CREATOR;
    static final String a;
    @JsonProperty
    public String color;
    @JsonProperty
    public HashMap<String, ParticleIntensityMetaData> particles;
    @JsonProperty
    public String style;

    static {
        a = ALYCEMetadata.class.getName();
        CREATOR = new Parcelable.Creator<ALYCEMetadata>(){

            public ALYCEMetadata a(Parcel parcel) {
                return new ALYCEMetadata(parcel);
            }

            public ALYCEMetadata[] a(int n) {
                return new ALYCEMetadata[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
    }

    public ALYCEMetadata() {
    }

    public ALYCEMetadata(Parcel parcel) {
        this.style = parcel.readString();
        this.color = parcel.readString();
        this.particles = (HashMap)parcel.readSerializable();
    }

    public float a(String string2) {
        if (this.particles == null || this.particles.get(string2) == null) {
            return 0.0f;
        }
        return this.particles.get((Object)string2).intensityFactor;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return JsonUtils.a(this);
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeString(this.style);
        parcel.writeString(this.color);
        parcel.writeSerializable(this.particles);
    }
}

