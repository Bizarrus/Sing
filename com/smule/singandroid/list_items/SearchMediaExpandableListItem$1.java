package com.smule.singandroid.list_items;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class SearchMediaExpandableListItem$1 implements Creator<SearchMediaExpandableListItem> {
    SearchMediaExpandableListItem$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m24458a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m24459a(i);
    }

    public SearchMediaExpandableListItem m24458a(Parcel parcel) {
        return new SearchMediaExpandableListItem(parcel, null);
    }

    public SearchMediaExpandableListItem[] m24459a(int i) {
        return new SearchMediaExpandableListItem[i];
    }
}
