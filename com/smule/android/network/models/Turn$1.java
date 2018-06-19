package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class Turn$1 implements Creator<Turn> {
    Turn$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18601a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18602a(i);
    }

    public Turn m18601a(Parcel parcel) {
        return new Turn(parcel);
    }

    public Turn[] m18602a(int i) {
        return new Turn[i];
    }
}
