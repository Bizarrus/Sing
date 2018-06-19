package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.File;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenCallV2 implements Parcelable {
    public static final Creator<OpenCallV2> CREATOR = new 1();
    private static final String f6890a = OpenCallV2.class.getName();
    @JsonIgnore
    public File backgroundTrackFileAbsolutePath;
    @JsonProperty("expireAt")
    public long expireAt;
    @JsonProperty("isClosed")
    public boolean isClosed;
    @JsonProperty("isPrivate")
    public boolean isPrivate;
    @JsonProperty("numJoins")
    public int numJoins;
    @JsonProperty("opencallKey")
    public String opencallKey;
    @JsonProperty("performance")
    private PerformanceV2 performance;
    @JsonProperty("performanceIcon")
    private PerformanceV2 performanceIcon;
    @JsonProperty("type")
    public String type;

    public int describeContents() {
        return 0;
    }

    public OpenCallV2(Parcel parcel) {
        boolean z = true;
        this.opencallKey = parcel.readString();
        this.type = parcel.readString();
        this.isPrivate = parcel.readInt() != 0;
        if (parcel.readInt() == 0) {
            z = false;
        }
        this.isClosed = z;
        this.expireAt = parcel.readLong();
        this.numJoins = parcel.readInt();
        this.performance = (PerformanceV2) parcel.readParcelable(PerformanceV2.class.getClassLoader());
        this.performanceIcon = (PerformanceV2) parcel.readParcelable(PerformanceV2.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeString(this.opencallKey == null ? "" : this.opencallKey);
        parcel.writeString(this.type == null ? "" : this.type);
        if (this.isPrivate) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (!this.isClosed) {
            i3 = 0;
        }
        parcel.writeInt(i3);
        parcel.writeLong(this.expireAt);
        parcel.writeInt(this.numJoins);
        parcel.writeParcelable(this.performance, 0);
        parcel.writeParcelable(this.performanceIcon, 0);
    }
}
