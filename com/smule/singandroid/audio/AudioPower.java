/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  com.smule.singandroid.utils.ArrayConverter
 *  com.smule.singandroid.utils.Base64
 */
package com.smule.singandroid.audio;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.singandroid.audio.AudioPower;
import com.smule.singandroid.utils.ArrayConverter;
import com.smule.singandroid.utils.Base64;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AudioPower
implements Parcelable {
    public static final Parcelable.Creator<AudioPower> CREATOR = new Parcelable.Creator<AudioPower>(){

        public AudioPower a(Parcel parcel) {
            return new AudioPower(parcel);
        }

        public AudioPower[] a(int n) {
            return new AudioPower[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="bufferSize")
    public int bufferSize;
    public float[] data;
    @JsonProperty(value="durationInSecs")
    public float durationInSecs;
    @JsonProperty(value="data")
    public String encodedData;
    @JsonProperty(value="sampleRate")
    public int sampleRate;

    public AudioPower() {
    }

    public AudioPower(float f, int n, int n2, float[] arrf) {
        this.durationInSecs = f;
        this.sampleRate = n;
        this.bufferSize = n2;
        this.encodedData = Base64.a((byte[])ArrayConverter.a((float[])arrf));
        this.data = arrf;
    }

    public AudioPower(Parcel parcel) {
        this.durationInSecs = parcel.readFloat();
        this.sampleRate = parcel.readInt();
        this.bufferSize = parcel.readInt();
        this.encodedData = parcel.readString();
        this.data = new float[parcel.readInt()];
        parcel.readFloatArray(this.data);
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "durationInSecs = " + this.durationInSecs + "; sampleRate = " + this.sampleRate + "; bufferSize = " + this.bufferSize + "; encodedData = " + this.encodedData;
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeFloat(this.durationInSecs);
        parcel.writeInt(this.sampleRate);
        parcel.writeInt(this.bufferSize);
        parcel.writeString(this.encodedData);
        parcel.writeInt(this.data.length);
        parcel.writeFloatArray(this.data);
    }
}

