package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class PerformanceV2$1 implements Creator<PerformanceV2> {
    PerformanceV2$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18574a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18575a(i);
    }

    public PerformanceV2 m18574a(Parcel parcel) {
        return new PerformanceV2(parcel);
    }

    public PerformanceV2[] m18575a(int i) {
        return new PerformanceV2[i];
    }
}
