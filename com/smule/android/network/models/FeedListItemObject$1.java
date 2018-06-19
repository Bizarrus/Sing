package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class FeedListItemObject$1 implements Creator<FeedListItemObject> {
    FeedListItemObject$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18557a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18558a(i);
    }

    public FeedListItemObject m18557a(Parcel parcel) {
        return new FeedListItemObject(parcel, null);
    }

    public FeedListItemObject[] m18558a(int i) {
        return new FeedListItemObject[i];
    }
}
