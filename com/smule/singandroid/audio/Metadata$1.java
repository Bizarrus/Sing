package com.smule.singandroid.audio;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class Metadata$1 implements Creator<Metadata> {
    Metadata$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m22281a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m22282a(i);
    }

    public Metadata m22281a(Parcel parcel) {
        return new Metadata(parcel);
    }

    public Metadata[] m22282a(int i) {
        return new Metadata[i];
    }
}
