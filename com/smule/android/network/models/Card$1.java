package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class Card$1 implements Creator<Card> {
    Card$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18547a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18548a(i);
    }

    public Card m18547a(Parcel parcel) {
        return new Card(parcel);
    }

    public Card[] m18548a(int i) {
        return new Card[i];
    }
}
