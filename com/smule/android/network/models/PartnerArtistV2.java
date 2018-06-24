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
import com.smule.android.network.models.PartnerArtistV2;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PartnerArtistV2
implements Parcelable {
    public static final Parcelable.Creator<PartnerArtistV2> CREATOR = new Parcelable.Creator<PartnerArtistV2>(){

        public PartnerArtistV2 a(Parcel parcel) {
            return new PartnerArtistV2(parcel);
        }

        public PartnerArtistV2[] a(int n) {
            return new PartnerArtistV2[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="accountId")
    public long accountId;
    @JsonProperty(value="facebookUrl")
    public String facebookUrl;
    @JsonProperty(value="imageUrl")
    public String imageUrl;
    @JsonProperty(value="messageBody")
    public String messageBody;
    @JsonProperty(value="messageTitle")
    public String messageTitle;
    @JsonProperty(value="name")
    public String name;
    @JsonProperty(value="partnerArtistId")
    public String partnerArtistId;
    @JsonProperty(value="ts")
    public long ts;
    @JsonProperty(value="twitterUrl")
    public String twitterUrl;
    @JsonProperty(value="videoUrl")
    public String videoUrl;

    public PartnerArtistV2() {
    }

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

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeString(this.partnerArtistId);
        parcel.writeLong(this.ts);
        String string2 = this.name == null ? "" : this.name;
        parcel.writeString(string2);
        string2 = this.imageUrl == null ? "" : this.imageUrl;
        parcel.writeString(string2);
        string2 = this.videoUrl == null ? "" : this.videoUrl;
        parcel.writeString(string2);
        string2 = this.messageTitle == null ? "" : this.messageTitle;
        parcel.writeString(string2);
        string2 = this.messageBody == null ? "" : this.messageBody;
        parcel.writeString(string2);
        string2 = this.facebookUrl == null ? "" : this.facebookUrl;
        parcel.writeString(string2);
        string2 = this.twitterUrl == null ? "" : this.twitterUrl;
        parcel.writeString(string2);
        parcel.writeLong(this.accountId);
    }
}

