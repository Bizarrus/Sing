package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountApps implements Parcelable {
    public static final Creator CREATOR = new 1();
    @JsonProperty("accountId")
    public long accountId;
    @JsonProperty("apps")
    public List<String> apps = new ArrayList();

    public AccountApps(Parcel parcel) {
        this.accountId = parcel.readLong();
        parcel.readStringList(this.apps);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.accountId);
        parcel.writeStringList(this.apps);
    }
}
