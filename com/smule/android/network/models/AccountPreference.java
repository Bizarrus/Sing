package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountPreference implements Parcelable {
    public static final Creator<AccountPreference> CREATOR = new 1();
    @JsonProperty("name")
    public String name;
    @JsonProperty("value")
    public String value;

    public AccountPreference(Parcel parcel) {
        this.name = parcel.readString();
        this.value = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.value);
    }
}
