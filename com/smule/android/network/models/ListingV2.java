package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListingV2 implements Parcelable {
    public static final Creator<ListingV2> CREATOR = new 1();
    private static final String f6887a = ListingV2.class.getName();
    @JsonProperty("currencyId")
    public int currencyId;
    @JsonIgnore
    public boolean isFree;
    @JsonProperty("isNew")
    public boolean isNew;
    @JsonProperty("listingId")
    public String listingId;
    @JsonProperty("liveTs")
    public long liveTs;
    @JsonProperty("modes")
    public List<String> modes = new ArrayList();
    @JsonProperty("price")
    public int price;
    @JsonProperty("productId")
    public String productId;
    @JsonProperty("productType")
    public String productType;
    @JsonProperty("sale")
    public boolean sale;
    @JsonIgnore
    public SongV2 song;
    @JsonIgnore
    public SoundfontV2 soundfont;
    @JsonProperty("storePrice")
    public float storePrice;
    @JsonProperty("subscriberOnly")
    public boolean subscriberOnly;
    @JsonProperty("ts")
    public long ts;

    public boolean m8259a() {
        return this.subscriberOnly;
    }

    public ListingV2(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.listingId = parcel.readString();
        this.ts = parcel.readLong();
        this.productType = parcel.readString();
        this.productId = parcel.readString();
        this.price = parcel.readInt();
        this.currencyId = parcel.readInt();
        this.isNew = parcel.readByte() == (byte) 1;
        if (parcel.readByte() == (byte) 1) {
            z = true;
        } else {
            z = false;
        }
        this.sale = z;
        if (parcel.readByte() == (byte) 1) {
            z = true;
        } else {
            z = false;
        }
        this.subscriberOnly = z;
        if (parcel.readByte() != (byte) 1) {
            z2 = false;
        }
        this.isFree = z2;
        this.song = (SongV2) parcel.readParcelable(SongV2.class.getClassLoader());
        this.soundfont = (SoundfontV2) parcel.readParcelable(SoundfontV2.class.getClassLoader());
        this.storePrice = parcel.readFloat();
        parcel.readStringList(this.modes);
    }

    public ListingV2(SongV2 songV2) {
        this.listingId = "__owned_song__" + songV2.songId;
        this.ts = 0;
        this.productType = ContestData$Reward.TYPE_SONG;
        this.productId = songV2.songId;
        this.price = 0;
        this.currencyId = 0;
        this.isNew = false;
        this.sale = false;
        this.subscriberOnly = false;
        this.isFree = false;
        this.song = songV2;
        this.soundfont = null;
        this.storePrice = 0.0f;
        this.modes = new ArrayList();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeString(this.listingId);
        parcel.writeLong(this.ts);
        parcel.writeString(this.productType == null ? "" : this.productType);
        parcel.writeString(this.productId == null ? "" : this.productId);
        parcel.writeInt(this.price);
        parcel.writeInt(this.currencyId);
        if (this.isNew) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.sale) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.subscriberOnly) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (!this.isFree) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
        parcel.writeParcelable(this.song, 0);
        parcel.writeParcelable(this.soundfont, 0);
        parcel.writeFloat(this.storePrice);
        parcel.writeStringList(this.modes);
    }

    public static List<String> m8257a(List<ListingV2> list) {
        List<String> arrayList = new ArrayList();
        for (ListingV2 listingV2 : list) {
            arrayList.add(listingV2.listingId);
        }
        return arrayList;
    }

    public int describeContents() {
        return 0;
    }
}
