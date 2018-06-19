package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class Round$1 implements Creator<Round> {
    Round$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18580a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18581a(i);
    }

    public Round m18580a(Parcel parcel) {
        return new Round(parcel);
    }

    public Round[] m18581a(int i) {
        return new Round[i];
    }
}
