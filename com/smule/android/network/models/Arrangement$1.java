package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class Arrangement$1 implements Creator<Arrangement> {
    Arrangement$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18535a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18536a(i);
    }

    public Arrangement m18535a(Parcel parcel) {
        return new Arrangement(parcel, null);
    }

    public Arrangement[] m18536a(int i) {
        return new Arrangement[i];
    }
}
