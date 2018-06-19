package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonObject
@JsonIgnoreProperties(ignoreUnknown = true)
public class VerifiedUrl implements Parcelable {
    public static final Creator<VerifiedUrl> CREATOR = new 1();
    @JsonProperty("desc")
    @JsonField
    public String desc;
    @JsonProperty("type")
    @JsonField
    public String type;
    @JsonProperty("url")
    @JsonField
    public String url;

    public VerifiedUrl(Parcel parcel) {
        this.type = parcel.readString();
        this.desc = parcel.readString();
        this.url = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.type == null ? "" : this.type);
        parcel.writeString(this.desc == null ? "" : this.desc);
        parcel.writeString(this.url == null ? "" : this.url);
    }
}
