package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreV2 {
    public static final Creator<StoreV2> f6895a = new 1();
    @JsonProperty("listings")
    public List<ListingV2> listings = new ArrayList();
    @JsonProperty("songs")
    public List<SongV2> songs = new ArrayList();
    @JsonProperty("soundfonts")
    public List<SoundfontV2> soundfonts = new ArrayList();
    @JsonProperty("storeId")
    public String storeId;
    @JsonIgnore
    public List<StoreSectionV2> storeSections = new ArrayList();
    @JsonProperty("ts")
    public long ts;

    public StoreV2(Parcel parcel) {
        this.storeId = parcel.readString();
        this.ts = parcel.readLong();
        parcel.readTypedList(this.storeSections, StoreSectionV2.CREATOR);
        parcel.readTypedList(this.listings, ListingV2.CREATOR);
        parcel.readTypedList(this.songs, SongV2.CREATOR);
        parcel.readTypedList(this.soundfonts, SoundfontV2.CREATOR);
    }
}
