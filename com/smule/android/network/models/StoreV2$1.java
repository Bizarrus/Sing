package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class StoreV2$1 implements Creator<StoreV2> {
    StoreV2$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18595a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18596a(i);
    }

    public StoreV2 m18595a(Parcel parcel) {
        return new StoreV2(parcel);
    }

    public StoreV2[] m18596a(int i) {
        return new StoreV2[i];
    }
}
