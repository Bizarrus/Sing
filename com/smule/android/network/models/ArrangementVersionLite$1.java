package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class ArrangementVersionLite$1 implements Creator<ArrangementVersionLite> {
    ArrangementVersionLite$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18543a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18544a(i);
    }

    public ArrangementVersionLite m18543a(Parcel parcel) {
        return new ArrangementVersionLite(parcel, null);
    }

    public ArrangementVersionLite[] m18544a(int i) {
        return new ArrangementVersionLite[i];
    }
}
