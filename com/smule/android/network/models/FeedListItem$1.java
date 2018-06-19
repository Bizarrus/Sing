package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class FeedListItem$1 implements Creator<FeedListItem> {
    FeedListItem$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18555a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18556a(i);
    }

    public FeedListItem m18555a(Parcel parcel) {
        return new FeedListItem(parcel, null);
    }

    public FeedListItem[] m18556a(int i) {
        return new FeedListItem[i];
    }
}
