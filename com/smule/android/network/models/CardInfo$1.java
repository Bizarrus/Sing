package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class CardInfo$1 implements Creator<CardInfo> {
    CardInfo$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18549a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18550a(i);
    }

    public CardInfo m18549a(Parcel parcel) {
        return new CardInfo(parcel);
    }

    public CardInfo[] m18550a(int i) {
        return new CardInfo[i];
    }
}
