package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Locale;

public class ReasonCount implements Parcelable {
    public static final Creator<ReasonCount> CREATOR = new 1();
    @JsonProperty("count")
    public int count;
    @JsonProperty("reason")
    public int reason;

    protected ReasonCount(Parcel parcel) {
        this.reason = parcel.readInt();
        this.count = parcel.readInt();
    }

    public String toString() {
        return String.format(Locale.US, "ReasonCount{reason='%d', count='%d'}", new Object[]{Integer.valueOf(this.reason), Integer.valueOf(this.count)});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReasonCount)) {
            return false;
        }
        ReasonCount reasonCount = (ReasonCount) obj;
        if (this.reason == reasonCount.reason && this.count == reasonCount.count) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.reason * 31) + this.count;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.reason);
        parcel.writeInt(this.count);
    }
}
