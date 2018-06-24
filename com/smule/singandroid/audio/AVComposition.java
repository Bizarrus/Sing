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
import com.smule.singandroid.audio.AVComposition;
import java.io.Serializable;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AVComposition
implements Parcelable {
    public static final Parcelable.Creator<AVComposition> CREATOR = new Parcelable.Creator<AVComposition>(){

        public AVComposition a(Parcel parcel) {
            return new AVComposition(parcel);
        }

        public AVComposition[] a(int n) {
            return new AVComposition[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty
    public HashMap<String, Object> content;
    @JsonProperty
    public int end;
    @JsonProperty
    public int start;

    public AVComposition(Parcel parcel) {
        this.start = parcel.readInt();
        this.end = parcel.readInt();
        this.content = (HashMap)parcel.readSerializable();
    }

    public AVComposition( builder) {
        this.start = builder.a;
        this.end = builder.b;
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put(builder.c, builder.d);
        this.content = hashMap;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return JsonUtils.a(this);
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeInt(this.start);
        parcel.writeInt(this.end);
        parcel.writeSerializable(this.content);
    }

}

