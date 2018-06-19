package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceV2 implements Parcelable {
    public static final Creator<ResourceV2> CREATOR = new 1();
    @JsonProperty("contentType")
    public String contentType;
    @JsonProperty("id")
    public long id;
    @JsonProperty("instrument")
    public String instrument;
    @JsonProperty("role")
    public String role;
    @JsonProperty("size")
    public long size;
    @JsonProperty("uid")
    public String uid;
    @JsonProperty("url")
    public String url;

    public ResourceV2(Parcel parcel) {
        this.instrument = parcel.readString();
        this.role = parcel.readString();
        this.url = parcel.readString();
        this.uid = parcel.readString();
        this.id = parcel.readLong();
        this.contentType = parcel.readString();
        this.size = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.instrument == null ? "" : this.instrument);
        parcel.writeString(this.role == null ? "" : this.role);
        parcel.writeString(this.url == null ? "" : this.url);
        parcel.writeString(this.uid == null ? "" : this.uid);
        parcel.writeLong(this.id);
        parcel.writeString(this.contentType == null ? "" : this.contentType);
        parcel.writeLong(this.size);
    }

    public int describeContents() {
        return 0;
    }
}
