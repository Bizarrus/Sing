package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class ArrangementVersion$1 implements Creator<ArrangementVersion> {
    ArrangementVersion$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18539a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18540a(i);
    }

    public ArrangementVersion m18539a(Parcel parcel) {
        return new ArrangementVersion(parcel);
    }

    public ArrangementVersion[] m18540a(int i) {
        return new ArrangementVersion[i];
    }
}
