package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.smule.android.network.models.ArrangementVersion.Resource;

class ArrangementVersion$Resource$1 implements Creator<Resource> {
    ArrangementVersion$Resource$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18541a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18542a(i);
    }

    public Resource m18541a(Parcel parcel) {
        return new Resource(parcel);
    }

    public Resource[] m18542a(int i) {
        return new Resource[i];
    }
}
