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
import com.smule.android.network.models.Arrangement;
import com.smule.android.network.models.ArrangementInfo;
import com.smule.android.network.models.ArrangementVersion;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ArrangementInfo
implements Parcelable {
    public static final Parcelable.Creator<ArrangementInfo> CREATOR = new Parcelable.Creator<ArrangementInfo>(){

        public ArrangementInfo a(Parcel parcel) {
            return new ArrangementInfo(parcel);
        }

        public ArrangementInfo[] a(int n) {
            return new ArrangementInfo[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="arr")
    public Arrangement arr;
    @JsonProperty(value="lastPubVersion")
    public ArrangementVersion lastPubVersion;
    @JsonProperty(value="lastVersion")
    public ArrangementVersion lastVersion;

    public ArrangementInfo() {
    }

    private ArrangementInfo(Parcel parcel) {
        this.arr = (Arrangement)parcel.readParcelable(Arrangement.class.getClassLoader());
        this.lastVersion = (ArrangementVersion)parcel.readParcelable(Arrangement.class.getClassLoader());
        this.lastPubVersion = (ArrangementVersion)parcel.readParcelable(Arrangement.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ArrangementInfo)) {
            return false;
        }
        object = (ArrangementInfo)object;
        if (this.arr != null) {
            if (!this.arr.equals(object.arr)) {
                return false;
            }
        } else if (object.arr != null) return false;
        if (this.lastVersion != null) {
            if (!this.lastVersion.equals(object.lastVersion)) {
                return false;
            }
        } else if (object.lastVersion != null) return false;
        if (this.lastPubVersion != null) {
            if (this.lastPubVersion.equals(object.lastPubVersion)) return true;
            return false;
        }
        if (object.lastPubVersion == null) return true;
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public int hashCode() {
        int n = 0;
        int n2 = this.arr != null ? this.arr.hashCode() : 0;
        int n3 = this.lastVersion != null ? this.lastVersion.hashCode() : 0;
        if (this.lastPubVersion != null) {
            n = this.lastPubVersion.hashCode();
        }
        return (n3 + n2 * 31) * 31 + n;
    }

    public String toString() {
        return String.format("ArrangementInfo{arr='%s', lastVersion='%s', lastPubVersion='%s'", this.arr.toString(), this.lastVersion.toString(), this.lastPubVersion.toString());
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeParcelable((Parcelable)this.arr, 0);
        parcel.writeParcelable((Parcelable)this.lastVersion, 0);
        parcel.writeParcelable((Parcelable)this.lastPubVersion, 0);
    }
}

