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
import com.smule.android.network.models.Banner;

public class Banner
implements Parcelable {
    public static final Parcelable.Creator<Banner> CREATOR = new Parcelable.Creator<Banner>(){

        public Banner a(Parcel parcel) {
            return new Banner(parcel);
        }

        public Banner[] a(int n) {
            return new Banner[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="destUrl")
    public String destUrl;
    @JsonProperty(value="imgUrl")
    public String imgUrl;
    @JsonProperty(value="name")
    public String name;

    public Banner() {
    }

    private Banner(Parcel parcel) {
        this.name = parcel.readString();
        this.destUrl = parcel.readString();
        this.imgUrl = parcel.readString();
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
        if (!(object instanceof Banner)) {
            return false;
        }
        object = (Banner)object;
        if (this.name != null) {
            if (!this.name.equals(object.name)) {
                return false;
            }
        } else if (object.name != null) return false;
        if (this.destUrl != null) {
            if (!this.destUrl.equals(object.destUrl)) {
                return false;
            }
        } else if (object.destUrl != null) return false;
        if (this.imgUrl != this.imgUrl) {
            if (this.imgUrl.equals(object.imgUrl)) return true;
            return false;
        }
        if (object.imgUrl == null) return true;
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public int hashCode() {
        int n = 0;
        int n2 = this.name != null ? this.name.hashCode() : 0;
        int n3 = this.destUrl != null ? this.destUrl.hashCode() : 0;
        if (this.imgUrl != null) {
            n = this.imgUrl.hashCode();
        }
        return (n3 + n2 * 31) * 31 + n;
    }

    public String toString() {
        return "Banner{name='" + this.name + ", destUrl=" + this.destUrl + ", imgUrl=" + this.imgUrl + '}';
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeString(this.name);
        parcel.writeString(this.destUrl);
        parcel.writeString(this.imgUrl);
    }
}

