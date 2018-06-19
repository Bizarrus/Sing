package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class Track$1 implements Creator<Track> {
    Track$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18599a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18600a(i);
    }

    public Track m18599a(Parcel parcel) {
        return new Track(parcel);
    }

    public Track[] m18600a(int i) {
        return new Track[i];
    }
}
