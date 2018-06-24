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
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.Track;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Track
implements Parcelable {
    public static final Parcelable.Creator<Track> CREATOR = new Parcelable.Creator<Track>(){

        public Track a(Parcel parcel) {
            return new Track(parcel);
        }

        public Track[] a(int n) {
            return new Track[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="accountIcon")
    public AccountIcon accountIcon;
    @JsonProperty(value="createdAt")
    public long createdAt;

    public Track() {
    }

    public Track(Parcel parcel) {
        this.accountIcon = (AccountIcon)parcel.readParcelable(AccountIcon.class.getClassLoader());
        this.createdAt = parcel.readLong();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeParcelable((Parcelable)this.accountIcon, 0);
        parcel.writeLong(this.createdAt);
    }
}

