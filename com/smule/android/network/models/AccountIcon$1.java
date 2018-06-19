package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class AccountIcon$1 implements Creator<AccountIcon> {
    AccountIcon$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18530a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18531a(i);
    }

    public AccountIcon m18530a(Parcel parcel) {
        return new AccountIcon(parcel);
    }

    public AccountIcon[] m18531a(int i) {
        return new AccountIcon[i];
    }
}
