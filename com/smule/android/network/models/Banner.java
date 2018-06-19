package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Banner implements Parcelable {
    public static final Creator<Banner> CREATOR = new 1();
    @JsonProperty("destUrl")
    public String destUrl;
    @JsonProperty("imgUrl")
    public String imgUrl;
    @JsonProperty("name")
    public String name;

    public String toString() {
        return "Banner{name='" + this.name + ", destUrl=" + this.destUrl + ", imgUrl=" + this.imgUrl + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Banner)) {
            return false;
        }
        Banner banner = (Banner) obj;
        if (this.name == null ? banner.name != null : !this.name.equals(banner.name)) {
            return false;
        }
        if (this.destUrl == null ? banner.destUrl != null : !this.destUrl.equals(banner.destUrl)) {
            return false;
        }
        if (this.imgUrl != this.imgUrl) {
            if (this.imgUrl.equals(banner.imgUrl)) {
                return true;
            }
        } else if (banner.imgUrl == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.name != null) {
            hashCode = this.name.hashCode();
        } else {
            hashCode = 0;
        }
        int i2 = hashCode * 31;
        if (this.destUrl != null) {
            hashCode = this.destUrl.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (this.imgUrl != null) {
            i = this.imgUrl.hashCode();
        }
        return hashCode + i;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.destUrl);
        parcel.writeString(this.imgUrl);
    }

    private Banner(Parcel parcel) {
        this.name = parcel.readString();
        this.destUrl = parcel.readString();
        this.imgUrl = parcel.readString();
    }
}
