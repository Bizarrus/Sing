package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class NotificationItemObject$1 implements Creator<NotificationItemObject> {
    NotificationItemObject$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18566a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18567a(i);
    }

    public NotificationItemObject m18566a(Parcel parcel) {
        return new NotificationItemObject(parcel, null);
    }

    public NotificationItemObject[] m18567a(int i) {
        return new NotificationItemObject[i];
    }
}
