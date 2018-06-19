package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class AccountPreference$1 implements Creator<AccountPreference> {
    AccountPreference$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18533a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18534a(i);
    }

    public AccountPreference m18533a(Parcel parcel) {
        return new AccountPreference(parcel);
    }

    public AccountPreference[] m18534a(int i) {
        return new AccountPreference[i];
    }
}
