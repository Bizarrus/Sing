package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Track implements Parcelable {
    public static final Creator<Track> CREATOR = new 1();
    @JsonProperty("accountIcon")
    public AccountIcon accountIcon;
    @JsonProperty("createdAt")
    public long createdAt;

    public Track(Parcel parcel) {
        this.accountIcon = (AccountIcon) parcel.readParcelable(AccountIcon.class.getClassLoader());
        this.createdAt = parcel.readLong();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.accountIcon, 0);
        parcel.writeLong(this.createdAt);
    }
}
