package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.managers.l10n.LocalizationObject;
import com.smule.android.network.managers.l10n.LocalizationObjectId;
import java.util.ArrayList;
import java.util.List;

@LocalizationObject(a = "section")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreSectionV2 implements Parcelable {
    public static final Creator<StoreSectionV2> CREATOR = new 1();
    @JsonProperty("displayName")
    public String displayName;
    @JsonProperty("imageUrl")
    public String imageUrl;
    @JsonProperty("listingIds")
    public List<String> listingIds = new ArrayList();
    @JsonIgnore
    public List<ListingV2> listings = new ArrayList();
    @JsonIgnore
    public int order;
    @JsonProperty("sectionId")
    @LocalizationObjectId
    public String sectionId;
    @JsonIgnore
    public List<StoreSectionV2> subSections = new ArrayList();
    @JsonProperty("ts")
    public long ts;

    public StoreSectionV2(Parcel parcel) {
        this.sectionId = parcel.readString();
        this.ts = parcel.readLong();
        this.displayName = parcel.readString();
        this.imageUrl = parcel.readString();
        parcel.readStringList(this.listingIds);
        parcel.readTypedList(this.listings, ListingV2.CREATOR);
        this.order = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.sectionId == null ? "" : this.sectionId);
        parcel.writeLong(this.ts);
        parcel.writeString(this.displayName == null ? "" : this.displayName);
        parcel.writeString(this.imageUrl == null ? "" : this.imageUrl);
        parcel.writeStringList(this.listingIds);
        parcel.writeTypedList(this.listings);
        parcel.writeInt(this.order);
    }

    public int describeContents() {
        return 0;
    }
}
