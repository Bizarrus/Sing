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
import com.smule.android.network.models.ReasonCount;
import java.util.Locale;

public class ReasonCount
implements Parcelable {
    public static final Parcelable.Creator<ReasonCount> CREATOR = new Parcelable.Creator<ReasonCount>(){

        public ReasonCount a(Parcel parcel) {
            return new ReasonCount(parcel);
        }

        public ReasonCount[] a(int n) {
            return new ReasonCount[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="count")
    public int count;
    @JsonProperty(value="reason")
    public int reason;

    public ReasonCount() {
    }

    protected ReasonCount(Parcel parcel) {
        this.reason = parcel.readInt();
        this.count = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        block5 : {
            block4 : {
                if (this == object) break block4;
                if (!(object instanceof ReasonCount)) {
                    return false;
                }
                object = (ReasonCount)object;
                if (this.reason != object.reason || this.count != object.count) break block5;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.reason * 31 + this.count;
    }

    public String toString() {
        return String.format(Locale.US, "ReasonCount{reason='%d', count='%d'}", this.reason, this.count);
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeInt(this.reason);
        parcel.writeInt(this.count);
    }
}

