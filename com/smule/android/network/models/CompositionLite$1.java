package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class CompositionLite$1 implements Creator<CompositionLite> {
    CompositionLite$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18553a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18554a(i);
    }

    public CompositionLite m18553a(Parcel parcel) {
        return new CompositionLite(parcel);
    }

    public CompositionLite[] m18554a(int i) {
        return new CompositionLite[i];
    }
}
