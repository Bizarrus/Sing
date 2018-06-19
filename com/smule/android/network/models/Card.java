package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Card implements Parcelable {
    public static final Creator<Card> CREATOR = new 1();
    @JsonProperty("accountIcon")
    public AccountIcon accountIcon;
    @JsonProperty("cardKey")
    public String cardKey;
    @JsonProperty("expireAt")
    public long expireAt;
    @JsonProperty("frameId")
    public int frameId;
    @JsonProperty("message")
    public String message;
    @JsonProperty("performanceIcon")
    public PerformanceV2 performanceIcon;
    @JsonProperty("url")
    public String url;

    public String toString() {
        return "Card [cardKey=" + this.cardKey + ", url=" + this.url + ", message=" + this.message + ", frameId=" + this.frameId + ", expireAt=" + this.expireAt + "]";
    }

    public Card(Parcel parcel) {
        this.cardKey = parcel.readString();
        this.accountIcon = (AccountIcon) parcel.readParcelable(AccountIcon.class.getClassLoader());
        this.performanceIcon = (PerformanceV2) parcel.readParcelable(PerformanceV2.class.getClassLoader());
        this.url = parcel.readString();
        this.message = parcel.readString();
        this.frameId = parcel.readInt();
        this.expireAt = parcel.readLong();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.cardKey == null ? "" : this.cardKey);
        parcel.writeParcelable(this.accountIcon, 0);
        parcel.writeParcelable(this.performanceIcon, 0);
        parcel.writeString(this.url == null ? "" : this.url);
        parcel.writeString(this.message == null ? "" : this.message);
        parcel.writeInt(this.frameId);
        parcel.writeLong(this.expireAt);
    }
}
