package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class ReasonCount$1 implements Creator<ReasonCount> {
    ReasonCount$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18576a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18577a(i);
    }

    public ReasonCount m18576a(Parcel parcel) {
        return new ReasonCount(parcel);
    }

    public ReasonCount[] m18577a(int i) {
        return new ReasonCount[i];
    }
}
