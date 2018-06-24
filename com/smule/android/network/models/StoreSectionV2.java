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
import com.smule.android.network.managers.l10n.LocalizationObject;
import com.smule.android.network.managers.l10n.LocalizationObjectId;
import com.smule.android.network.models.ListingV2;
import com.smule.android.network.models.StoreSectionV2;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
@LocalizationObject(a="section")
@Deprecated
public class StoreSectionV2
implements Parcelable {
    public static final Parcelable.Creator<StoreSectionV2> CREATOR = new Parcelable.Creator<StoreSectionV2>(){

        public StoreSectionV2 a(Parcel parcel) {
            return new StoreSectionV2(parcel);
        }

        public StoreSectionV2[] a(int n) {
            return new StoreSectionV2[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="displayName")
    public String displayName;
    @JsonProperty(value="imageUrl")
    public String imageUrl;
    @JsonProperty(value="listingIds")
    public List<String> listingIds = new ArrayList<String>();
    @JsonIgnore
    public List<ListingV2> listings = new ArrayList<ListingV2>();
    @JsonIgnore
    public int order;
    @JsonProperty(value="sectionId")
    @LocalizationObjectId
    public String sectionId;
    @JsonIgnore
    public List<StoreSectionV2> subSections = new ArrayList<StoreSectionV2>();
    @JsonProperty(value="ts")
    public long ts;

    public StoreSectionV2() {
    }

    public StoreSectionV2(Parcel parcel) {
        this.sectionId = parcel.readString();
        this.ts = parcel.readLong();
        this.displayName = parcel.readString();
        this.imageUrl = parcel.readString();
        parcel.readStringList(this.listingIds);
        parcel.readTypedList(this.listings, ListingV2.CREATOR);
        this.order = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        String string2 = this.sectionId == null ? "" : this.sectionId;
        parcel.writeString(string2);
        parcel.writeLong(this.ts);
        string2 = this.displayName == null ? "" : this.displayName;
        parcel.writeString(string2);
        string2 = this.imageUrl == null ? "" : this.imageUrl;
        parcel.writeString(string2);
        parcel.writeStringList(this.listingIds);
        parcel.writeTypedList(this.listings);
        parcel.writeInt(this.order);
    }

}

