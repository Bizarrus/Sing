package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class ArrangementInfo$1 implements Creator<ArrangementInfo> {
    ArrangementInfo$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18537a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18538a(i);
    }

    public ArrangementInfo m18537a(Parcel parcel) {
        return new ArrangementInfo(parcel, null);
    }

    public ArrangementInfo[] m18538a(int i) {
        return new ArrangementInfo[i];
    }
}
