package com.smule.singandroid.audio;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AudioPower implements Parcelable {
    public static final Creator<AudioPower> CREATOR = new 1();
    @JsonProperty("bufferSize")
    public int bufferSize;
    public float[] data;
    @JsonProperty("durationInSecs")
    public float durationInSecs;
    @JsonProperty("data")
    public String encodedData;
    @JsonProperty("sampleRate")
    public int sampleRate;

    public AudioPower(float f, int i, int i2, float[] fArr, String str) {
        this.durationInSecs = f;
        this.sampleRate = i;
        this.bufferSize = i2;
        this.encodedData = str;
        this.data = fArr;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.durationInSecs);
        parcel.writeInt(this.sampleRate);
        parcel.writeInt(this.bufferSize);
        parcel.writeString(this.encodedData);
        parcel.writeInt(this.data.length);
        parcel.writeFloatArray(this.data);
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
}
