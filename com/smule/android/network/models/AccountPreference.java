/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.models.AccountPreference;

public class AccountPreference
implements Parcelable {
    public static final Parcelable.Creator<AccountPreference> CREATOR = new Parcelable.Creator<AccountPreference>(){

        public AccountPreference a(Parcel parcel) {
            return new AccountPreference(parcel);
        }

        public AccountPreference[] a(int n) {
            return new AccountPreference[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="name")
    public String name;
    @JsonProperty(value="value")
    public String value;

    public AccountPreference() {
    }

    public AccountPreference(Parcel parcel) {
        this.name = parcel.readString();
        this.value = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeString(this.name);
        parcel.writeString(this.value);
    }

}

