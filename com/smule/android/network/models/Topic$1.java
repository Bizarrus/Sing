package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class Topic$1 implements Creator<Topic> {
    Topic$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18597a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18598a(i);
    }

    public Topic m18597a(Parcel parcel) {
        return new Topic(parcel);
    }

    public Topic[] m18598a(int i) {
        return new Topic[i];
    }
}
