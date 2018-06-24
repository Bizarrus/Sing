package com.smule.singandroid.list_items;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.models.PerformanceV2;

public class SearchMediaExpandableListItem implements Parcelable {
    public static final Creator<SearchMediaExpandableListItem> CREATOR = new 1();
    private static final String f7127a = SearchMediaExpandableListItem.class.getSimpleName();
    @JsonProperty("createdAt")
    public long createdAt;
    @JsonProperty("expireAt")
    public long expireAt;
    @JsonIgnore
    public ItemType itemType;
    @JsonProperty("performance")
    public PerformanceV2 performanceIcon;

    public ItemType m8849a() {
        return this.performanceIcon.m8280n() ? ItemType.a : ItemType.b;
    }

    public boolean m8850b() {
        return this.performanceIcon.m8280n();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.performanceIcon, 0);
        parcel.writeLong(this.expireAt);
        parcel.writeLong(this.createdAt);
    }

    private SearchMediaExpandableListItem(Parcel parcel) {
        this.performanceIcon = (PerformanceV2) parcel.readParcelable(PerformanceV2.class.getClassLoader());
        this.expireAt = parcel.readLong();
        this.createdAt = parcel.readLong();
    }

    public String toString() {
        return "SearchMediaExpandableListItem{performanceIcon=" + this.performanceIcon + ", expireAt='" + this.expireAt + '\'' + ", createdAt='" + this.createdAt + '\'' + ", itemType=" + this.itemType + '}';
    }

    public static SearchMediaExpandableListItem m8848a(PerformanceV2 performanceV2) {
        SearchMediaExpandableListItem searchMediaExpandableListItem = new SearchMediaExpandableListItem();
        searchMediaExpandableListItem.performanceIcon = performanceV2;
        searchMediaExpandableListItem.itemType = performanceV2.m8280n() ? ItemType.a : ItemType.b;
        searchMediaExpandableListItem.expireAt = performanceV2.expireAt;
        searchMediaExpandableListItem.createdAt = (long) performanceV2.createdAt;
        return searchMediaExpandableListItem;
    }
}
