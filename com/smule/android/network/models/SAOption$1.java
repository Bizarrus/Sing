package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class SAOption$1 implements Creator<SAOption> {
    SAOption$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18582a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18583a(i);
    }

    public SAOption m18582a(Parcel parcel) {
        return new SAOption(parcel);
    }

    public SAOption[] m18583a(int i) {
        return new SAOption[i];
    }
}
