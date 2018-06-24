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
package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.models.CardInfo;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CardInfo
implements Parcelable {
    public static final Parcelable.Creator<CardInfo> CREATOR = new Parcelable.Creator<CardInfo>(){

        public CardInfo a(Parcel parcel) {
            return new CardInfo(parcel);
        }

        public CardInfo[] a(int n) {
            return new CardInfo[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="backgroundUrl16x9")
    public String backgroundUrl16x9;
    @JsonProperty(value="backgroundUrl4x3")
    public String backgroundUrl4x3;
    @JsonProperty(value="backgroundUrlIphone")
    public String backgroundUrlIphone;
    @JsonProperty(value="backgroundUrlSquare")
    public String backgroundUrlSquare;
    @JsonProperty(value="enabled")
    public boolean enabled;
    @JsonProperty(value="frameId")
    public int frameId;
    @JsonProperty(value="name")
    public String name;

    public CardInfo() {
    }

    /*
     * Enabled aggressive block sorting
     */
    public CardInfo(Parcel parcel) {
        boolean bl = true;
        if (parcel.readByte() != 1) {
            bl = false;
        }
        this.enabled = bl;
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

    public String toString() {
        return "CardInfo [enabled=" + this.enabled + ", frameId=" + this.frameId + ", name=" + this.name + ", frameId=" + this.frameId + ", backgroundUrlIphone=" + this.backgroundUrlIphone + ", backgroundUrlSquare=" + this.backgroundUrlSquare + ", backgroundUrl16x9=" + this.backgroundUrl16x9 + ", backgroundUrl4x3=" + this.backgroundUrl4x3 + "]";
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        n = this.enabled ? 1 : 0;
        parcel.writeByte((byte)n);
        parcel.writeInt(this.frameId);
        parcel.writeString(this.name);
        parcel.writeString(this.backgroundUrlIphone);
        parcel.writeString(this.backgroundUrlSquare);
        parcel.writeString(this.backgroundUrl16x9);
        parcel.writeString(this.backgroundUrl4x3);
    }
}

