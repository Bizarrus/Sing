package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class AccountApps$1 implements Creator {
    AccountApps$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18525a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18526a(i);
    }

    public AccountApps m18525a(Parcel parcel) {
        return new AccountApps(parcel);
    }

    public AccountApps[] m18526a(int i) {
        return new AccountApps[i];
    }
}
