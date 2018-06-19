package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class SNPChat$1 implements Creator<SNPChat> {
    SNPChat$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18584a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18585a(i);
    }

    public SNPChat m18584a(Parcel parcel) {
        return new SNPChat(parcel);
    }

    public SNPChat[] m18585a(int i) {
        return new SNPChat[i];
    }
}
