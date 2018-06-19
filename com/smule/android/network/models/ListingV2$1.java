package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class ListingV2$1 implements Creator<ListingV2> {
    ListingV2$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18559a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18560a(i);
    }

    public ListingV2 m18559a(Parcel parcel) {
        return new ListingV2(parcel);
    }

    public ListingV2[] m18560a(int i) {
        return new ListingV2[i];
    }
}
