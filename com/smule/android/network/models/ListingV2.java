/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  com.fasterxml.jackson.annotation.JsonIgnore
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.logging.Log;
import com.smule.android.network.models.ListingV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.network.models.SoundfontV2;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
@Deprecated
public class ListingV2
implements Parcelable {
    public static final Parcelable.Creator<ListingV2> CREATOR;
    private static final String a;
    @JsonProperty(value="currencyId")
    public int currencyId;
    @JsonIgnore
    public boolean isFree;
    @JsonProperty(value="isNew")
    public boolean isNew;
    @JsonProperty(value="listingId")
    public String listingId;
    @JsonProperty(value="liveTs")
    public long liveTs;
    @JsonProperty(value="modes")
    public List<String> modes;
    @JsonProperty(value="price")
    public int price;
    @JsonProperty(value="productId")
    public String productId;
    @JsonProperty(value="productType")
    public String productType;
    @JsonProperty(value="sale")
    public boolean sale;
    @JsonIgnore
    public SongV2 song;
    @JsonIgnore
    public SoundfontV2 soundfont;
    @JsonProperty(value="storePrice")
    public float storePrice;
    @JsonProperty(value="subscriberOnly")
    public boolean subscriberOnly;
    @JsonProperty(value="ts")
    public long ts;

    static {
        a = ListingV2.class.getName();
        CREATOR = new Parcelable.Creator<ListingV2>(){

            public ListingV2 a(Parcel parcel) {
                return new ListingV2(parcel);
            }

            public ListingV2[] a(int n) {
                return new ListingV2[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
    }

    public ListingV2() {
        this.modes = new ArrayList<String>();
    }

    /*
     * Enabled aggressive block sorting
     */
    public ListingV2(Parcel parcel) {
        boolean bl = true;
        this.modes = new ArrayList<String>();
        this.listingId = parcel.readString();
        this.ts = parcel.readLong();
        this.productType = parcel.readString();
        this.productId = parcel.readString();
        this.price = parcel.readInt();
        this.currencyId = parcel.readInt();
        boolean bl2 = parcel.readByte() == 1;
        this.isNew = bl2;
        bl2 = parcel.readByte() == 1;
        this.sale = bl2;
        bl2 = parcel.readByte() == 1;
        this.subscriberOnly = bl2;
        bl2 = parcel.readByte() == 1 ? bl : false;
        this.isFree = bl2;
        this.song = (SongV2)parcel.readParcelable(SongV2.class.getClassLoader());
        this.soundfont = (SoundfontV2)parcel.readParcelable(SoundfontV2.class.getClassLoader());
        this.storePrice = parcel.readFloat();
        parcel.readStringList(this.modes);
    }

    public ListingV2(SongV2 songV2) {
        this.modes = new ArrayList<String>();
        this.listingId = "__owned_song__" + songV2.songId;
        this.ts = 0;
        this.productType = "SONG";
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
        this.modes = new ArrayList<String>();
    }

    public static List<String> a(List<ListingV2> object) {
        ArrayList<String> arrayList = new ArrayList<String>();
        object = object.iterator();
        while (object.hasNext()) {
            arrayList.add(((ListingV2)object.next()).listingId);
        }
        return arrayList;
    }

    public boolean a() {
        return this.subscriberOnly;
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        int n2 = 1;
        parcel.writeString(this.listingId);
        parcel.writeLong(this.ts);
        String string2 = this.productType == null ? "" : this.productType;
        parcel.writeString(string2);
        string2 = this.productId == null ? "" : this.productId;
        parcel.writeString(string2);
        parcel.writeInt(this.price);
        parcel.writeInt(this.currencyId);
        n = this.isNew ? 1 : 0;
        parcel.writeByte((byte)n);
        n = this.sale ? 1 : 0;
        parcel.writeByte((byte)n);
        n = this.subscriberOnly ? 1 : 0;
        parcel.writeByte((byte)n);
        n = this.isFree ? n2 : 0;
        parcel.writeByte((byte)n);
        parcel.writeParcelable((Parcelable)this.song, 0);
        parcel.writeParcelable((Parcelable)this.soundfont, 0);
        parcel.writeFloat(this.storePrice);
        parcel.writeStringList(this.modes);
    }

}

