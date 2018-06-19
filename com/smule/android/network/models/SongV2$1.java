package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class SongV2$1 implements Creator<SongV2> {
    SongV2$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18586a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18587a(i);
    }

    public SongV2 m18586a(Parcel parcel) {
        return new SongV2(parcel);
    }

    public SongV2[] m18587a(int i) {
        return new SongV2[i];
    }
}
