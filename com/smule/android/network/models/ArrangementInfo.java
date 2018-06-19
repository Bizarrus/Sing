package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrangementInfo implements Parcelable {
    public static final Creator<ArrangementInfo> CREATOR = new 1();
    @JsonProperty("arr")
    public Arrangement arr;
    @JsonProperty("lastPubVersion")
    public ArrangementVersion lastPubVersion;
    @JsonProperty("lastVersion")
    public ArrangementVersion lastVersion;

    private ArrangementInfo(Parcel parcel) {
        this.arr = (Arrangement) parcel.readParcelable(Arrangement.class.getClassLoader());
        this.lastVersion = (ArrangementVersion) parcel.readParcelable(Arrangement.class.getClassLoader());
        this.lastPubVersion = (ArrangementVersion) parcel.readParcelable(Arrangement.class.getClassLoader());
    }

    public String toString() {
        return String.format("ArrangementInfo{arr='%s', lastVersion='%s', lastPubVersion='%s'", new Object[]{this.arr.toString(), this.lastVersion.toString(), this.lastPubVersion.toString()});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ArrangementInfo)) {
            return false;
        }
        ArrangementInfo arrangementInfo = (ArrangementInfo) obj;
        if (this.arr == null ? arrangementInfo.arr != null : !this.arr.equals(arrangementInfo.arr)) {
            return false;
        }
        if (this.lastVersion == null ? arrangementInfo.lastVersion != null : !this.lastVersion.equals(arrangementInfo.lastVersion)) {
            return false;
        }
        if (this.lastPubVersion != null) {
            if (this.lastPubVersion.equals(arrangementInfo.lastPubVersion)) {
                return true;
            }
        } else if (arrangementInfo.lastPubVersion == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.arr != null) {
            hashCode = this.arr.hashCode();
        } else {
            hashCode = 0;
        }
        int i2 = hashCode * 31;
        if (this.lastVersion != null) {
            hashCode = this.lastVersion.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (this.lastPubVersion != null) {
            i = this.lastPubVersion.hashCode();
        }
        return hashCode + i;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.arr, 0);
        parcel.writeParcelable(this.lastVersion, 0);
        parcel.writeParcelable(this.lastPubVersion, 0);
    }
}
