/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.models.AccountApps;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountApps
implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        public AccountApps a(Parcel parcel) {
            return new AccountApps(parcel);
        }

        public AccountApps[] a(int n) {
            return new AccountApps[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="accountId")
    public long accountId;
    @JsonProperty(value="apps")
    public List<String> apps = new ArrayList<String>();

    public AccountApps() {
    }

    public AccountApps(Parcel parcel) {
        this.accountId = parcel.readLong();
        parcel.readStringList(this.apps);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeLong(this.accountId);
        parcel.writeStringList(this.apps);
    }
}

