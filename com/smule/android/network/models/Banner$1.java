package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class Banner$1 implements Creator<Banner> {
    Banner$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18545a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18546a(i);
    }

    public Banner m18545a(Parcel parcel) {
        return new Banner(parcel, null);
    }

    public Banner[] m18546a(int i) {
        return new Banner[i];
    }
}
