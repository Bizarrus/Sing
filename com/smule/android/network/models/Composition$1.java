package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class Composition$1 implements Creator<Composition> {
    Composition$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18551a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18552a(i);
    }

    public Composition m18551a(Parcel parcel) {
        return new Composition(parcel, null);
    }

    public Composition[] m18552a(int i) {
        return new Composition[i];
    }
}
