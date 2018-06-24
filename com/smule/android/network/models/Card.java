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
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.Card;
import com.smule.android.network.models.PerformanceV2;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Card
implements Parcelable {
    public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>(){

        public Card a(Parcel parcel) {
            return new Card(parcel);
        }

        public Card[] a(int n) {
            return new Card[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="accountIcon")
    public AccountIcon accountIcon;
    @JsonProperty(value="cardKey")
    public String cardKey;
    @JsonProperty(value="expireAt")
    public long expireAt;
    @JsonProperty(value="frameId")
    public int frameId;
    @JsonProperty(value="message")
    public String message;
    @JsonProperty(value="performanceIcon")
    public PerformanceV2 performanceIcon;
    @JsonProperty(value="url")
    public String url;

    public Card() {
    }

    public Card(Parcel parcel) {
        this.cardKey = parcel.readString();
        this.accountIcon = (AccountIcon)parcel.readParcelable(AccountIcon.class.getClassLoader());
        this.performanceIcon = (PerformanceV2)parcel.readParcelable(PerformanceV2.class.getClassLoader());
        this.url = parcel.readString();
        this.message = parcel.readString();
        this.frameId = parcel.readInt();
        this.expireAt = parcel.readLong();
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "Card [cardKey=" + this.cardKey + ", url=" + this.url + ", message=" + this.message + ", frameId=" + this.frameId + ", expireAt=" + this.expireAt + "]";
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        String string2 = this.cardKey == null ? "" : this.cardKey;
        parcel.writeString(string2);
        parcel.writeParcelable((Parcelable)this.accountIcon, 0);
        parcel.writeParcelable((Parcelable)this.performanceIcon, 0);
        string2 = this.url == null ? "" : this.url;
        parcel.writeString(string2);
        string2 = this.message == null ? "" : this.message;
        parcel.writeString(string2);
        parcel.writeInt(this.frameId);
        parcel.writeLong(this.expireAt);
    }
}

