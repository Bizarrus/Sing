package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CardInfo implements Parcelable {
    public static final Creator<CardInfo> CREATOR = new 1();
    @JsonProperty("backgroundUrl16x9")
    public String backgroundUrl16x9;
    @JsonProperty("backgroundUrl4x3")
    public String backgroundUrl4x3;
    @JsonProperty("backgroundUrlIphone")
    public String backgroundUrlIphone;
    @JsonProperty("backgroundUrlSquare")
    public String backgroundUrlSquare;
    @JsonProperty("enabled")
    public boolean enabled;
    @JsonProperty("frameId")
    public int frameId;
    @JsonProperty("name")
    public String name;

    public String toString() {
        return "CardInfo [enabled=" + this.enabled + ", frameId=" + this.frameId + ", name=" + this.name + ", frameId=" + this.frameId + ", backgroundUrlIphone=" + this.backgroundUrlIphone + ", backgroundUrlSquare=" + this.backgroundUrlSquare + ", backgroundUrl16x9=" + this.backgroundUrl16x9 + ", backgroundUrl4x3=" + this.backgroundUrl4x3 + "]";
    }

    public CardInfo(Parcel parcel) {
        boolean z = true;
        if (parcel.readByte() != (byte) 1) {
            z = false;
        }
        this.enabled = z;
        this.frameId = parcel.readInt();
        this.name = parcel.readString();
        this.backgroundUrlIphone = parcel.readString();
        this.backgroundUrlSquare = parcel.readString();
        this.backgroundUrl16x9 = parcel.readString();
        this.backgroundUrl4x3 = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (this.enabled ? 1 : 0));
        parcel.writeInt(this.frameId);
        parcel.writeString(this.name);
        parcel.writeString(this.backgroundUrlIphone);
        parcel.writeString(this.backgroundUrlSquare);
        parcel.writeString(this.backgroundUrl16x9);
        parcel.writeString(this.backgroundUrl4x3);
    }
}
