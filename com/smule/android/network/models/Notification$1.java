package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class Notification$1 implements Creator<Notification> {
    Notification$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18562a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18563a(i);
    }

    public Notification m18562a(Parcel parcel) {
        return new Notification(parcel, null);
    }

    public Notification[] m18563a(int i) {
        return new Notification[i];
    }
}
