package com.smule.android.network.managers;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.smule.android.network.managers.PerformanceManager.PerformanceResourceInfo;

class PerformanceManager$PerformanceResourceInfo$1 implements Creator<PerformanceResourceInfo> {
    PerformanceManager$PerformanceResourceInfo$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18252a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18253a(i);
    }

    public PerformanceResourceInfo m18252a(Parcel parcel) {
        return new PerformanceResourceInfo(parcel, null);
    }

    public PerformanceResourceInfo[] m18253a(int i) {
        return new PerformanceResourceInfo[i];
    }
}
