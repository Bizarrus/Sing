package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class VerifiedUrl$1 implements Creator<VerifiedUrl> {
    VerifiedUrl$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18606a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18607a(i);
    }

    public VerifiedUrl m18606a(Parcel parcel) {
        return new VerifiedUrl(parcel);
    }

    public VerifiedUrl[] m18607a(int i) {
        return new VerifiedUrl[i];
    }
}
