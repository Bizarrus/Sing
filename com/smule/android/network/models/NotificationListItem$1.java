package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class NotificationListItem$1 implements Creator<NotificationListItem> {
    NotificationListItem$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18568a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18569a(i);
    }

    public NotificationListItem m18568a(Parcel parcel) {
        return new NotificationListItem(parcel, null);
    }

    public NotificationListItem[] m18569a(int i) {
        return new NotificationListItem[i];
    }
}
