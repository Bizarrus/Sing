package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class OpenCallV2$1 implements Creator<OpenCallV2> {
    OpenCallV2$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18570a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18571a(i);
    }

    public OpenCallV2 m18570a(Parcel parcel) {
        return new OpenCallV2(parcel);
    }

    public OpenCallV2[] m18571a(int i) {
        return new OpenCallV2[i];
    }
}
