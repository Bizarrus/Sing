package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class ResourceV2$1 implements Creator<ResourceV2> {
    ResourceV2$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18578a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18579a(i);
    }

    public ResourceV2 m18578a(Parcel parcel) {
        return new ResourceV2(parcel);
    }

    public ResourceV2[] m18579a(int i) {
        return new ResourceV2[i];
    }
}
