/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  com.fasterxml.jackson.annotation.JsonIgnore
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.singandroid.list_items;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.list_items.SearchMediaExpandableListItem;

public class SearchMediaExpandableListItem
implements Parcelable {
    public static final Parcelable.Creator<SearchMediaExpandableListItem> CREATOR;
    private static final String a;
    @JsonProperty(value="createdAt")
    public long createdAt;
    @JsonProperty(value="expireAt")
    public long expireAt;
    @JsonIgnore
    public  itemType;
    @JsonProperty(value="performance")
    public PerformanceV2 performanceIcon;

    static {
        a = SearchMediaExpandableListItem.class.getSimpleName();
        CREATOR = new Parcelable.Creator<SearchMediaExpandableListItem>(){

            public SearchMediaExpandableListItem a(Parcel parcel) {
                return new SearchMediaExpandableListItem(parcel);
            }

            public SearchMediaExpandableListItem[] a(int n) {
                return new SearchMediaExpandableListItem[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
    }

    public SearchMediaExpandableListItem() {
    }

    private SearchMediaExpandableListItem(Parcel parcel) {
        this.performanceIcon = (PerformanceV2)parcel.readParcelable(PerformanceV2.class.getClassLoader());
        this.expireAt = parcel.readLong();
        this.createdAt = parcel.readLong();
    }

    /*
     * Enabled aggressive block sorting
     */
    public static SearchMediaExpandableListItem a(PerformanceV2 performanceV2) {
        SearchMediaExpandableListItem searchMediaExpandableListItem = new SearchMediaExpandableListItem();
        searchMediaExpandableListItem.performanceIcon = performanceV2;
         itemType = performanceV2.p() ? .a : .b;
        searchMediaExpandableListItem.itemType = itemType;
        searchMediaExpandableListItem.expireAt = performanceV2.expireAt;
        searchMediaExpandableListItem.createdAt = performanceV2.createdAt;
        return searchMediaExpandableListItem;
    }

    public  a() {
        if (this.performanceIcon.p()) {
            return .a;
        }
        return .b;
    }

    public boolean b() {
        return this.performanceIcon.p();
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "SearchMediaExpandableListItem{performanceIcon=" + this.performanceIcon + ", expireAt='" + this.expireAt + '\'' + ", createdAt='" + this.createdAt + '\'' + ", itemType=" + (Object)((Object)this.itemType) + '}';
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeParcelable((Parcelable)this.performanceIcon, 0);
        parcel.writeLong(this.expireAt);
        parcel.writeLong(this.createdAt);
    }

}

