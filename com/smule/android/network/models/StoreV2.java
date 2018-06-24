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
import com.smule.android.network.models.ListingV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.network.models.SoundfontV2;
import com.smule.android.network.models.StoreSectionV2;
import com.smule.android.network.models.StoreV2;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
@Deprecated
public class StoreV2 {
    public static final Parcelable.Creator<StoreV2> a = new Parcelable.Creator<StoreV2>(){

        public StoreV2 a(Parcel parcel) {
            return new StoreV2(parcel);
        }

        public StoreV2[] a(int n) {
            return new StoreV2[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="listings")
    public List<ListingV2> listings = new ArrayList<ListingV2>();
    @JsonProperty(value="songs")
    public List<SongV2> songs = new ArrayList<SongV2>();
    @JsonProperty(value="soundfonts")
    public List<SoundfontV2> soundfonts = new ArrayList<SoundfontV2>();
    @JsonProperty(value="storeId")
    public String storeId;
    @JsonIgnore
    public List<StoreSectionV2> storeSections = new ArrayList<StoreSectionV2>();
    @JsonProperty(value="ts")
    public long ts;

    public StoreV2() {
    }

    public StoreV2(Parcel parcel) {
        this.storeId = parcel.readString();
        this.ts = parcel.readLong();
        parcel.readTypedList(this.storeSections, StoreSectionV2.CREATOR);
        parcel.readTypedList(this.listings, ListingV2.CREATOR);
        parcel.readTypedList(this.songs, SongV2.CREATOR);
        parcel.readTypedList(this.soundfonts, SoundfontV2.CREATOR);
    }
}

