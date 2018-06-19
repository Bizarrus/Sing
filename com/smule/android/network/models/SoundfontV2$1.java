package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class SoundfontV2$1 implements Creator<SoundfontV2> {
    SoundfontV2$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18589a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18590a(i);
    }

    public SoundfontV2 m18589a(Parcel parcel) {
        return new SoundfontV2(parcel);
    }

    public SoundfontV2[] m18590a(int i) {
        return new SoundfontV2[i];
    }
}
