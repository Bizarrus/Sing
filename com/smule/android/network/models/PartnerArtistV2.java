package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PartnerArtistV2 implements Parcelable {
    public static final Creator<PartnerArtistV2> CREATOR = new 1();
    @JsonProperty("accountId")
    public long accountId;
    @JsonProperty("facebookUrl")
    public String facebookUrl;
    @JsonProperty("imageUrl")
    public String imageUrl;
    @JsonProperty("messageBody")
    public String messageBody;
    @JsonProperty("messageTitle")
    public String messageTitle;
    @JsonProperty("name")
    public String name;
    @JsonProperty("partnerArtistId")
    public String partnerArtistId;
    @JsonProperty("ts")
    public long ts;
    @JsonProperty("twitterUrl")
    public String twitterUrl;
    @JsonProperty("videoUrl")
    public String videoUrl;

    public PartnerArtistV2(Parcel parcel) {
        this.partnerArtistId = parcel.readString();
        this.ts = parcel.readLong();
        this.name = parcel.readString();
        this.imageUrl = parcel.readString();
        this.videoUrl = parcel.readString();
        this.messageTitle = parcel.readString();
        this.messageBody = parcel.readString();
        this.facebookUrl = parcel.readString();
        this.twitterUrl = parcel.readString();
        this.accountId = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.partnerArtistId);
        parcel.writeLong(this.ts);
        parcel.writeString(this.name == null ? "" : this.name);
        parcel.writeString(this.imageUrl == null ? "" : this.imageUrl);
        parcel.writeString(this.videoUrl == null ? "" : this.videoUrl);
        parcel.writeString(this.messageTitle == null ? "" : this.messageTitle);
        parcel.writeString(this.messageBody == null ? "" : this.messageBody);
        parcel.writeString(this.facebookUrl == null ? "" : this.facebookUrl);
        parcel.writeString(this.twitterUrl == null ? "" : this.twitterUrl);
        parcel.writeLong(this.accountId);
    }

    public int describeContents() {
        return 0;
    }
}
