package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class StoreSectionV2$1 implements Creator<StoreSectionV2> {
    StoreSectionV2$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18592a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18593a(i);
    }

    public StoreSectionV2 m18592a(Parcel parcel) {
        return new StoreSectionV2(parcel);
    }

    public StoreSectionV2[] m18593a(int i) {
        return new StoreSectionV2[i];
    }
}
