package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class NotificationItemAggObject$1 implements Creator<NotificationItemAggObject> {
    NotificationItemAggObject$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18564a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18565a(i);
    }

    public NotificationItemAggObject m18564a(Parcel parcel) {
        return new NotificationItemAggObject(parcel, null);
    }

    public NotificationItemAggObject[] m18565a(int i) {
        return new NotificationItemAggObject[i];
    }
}
