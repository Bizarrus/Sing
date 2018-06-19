package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationItemObject implements Parcelable {
    public static final Creator<NotificationItemObject> CREATOR = new 1();
    @JsonProperty("performance")
    public PerformanceV2 performanceIcon;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.performanceIcon, 0);
    }

    private NotificationItemObject(Parcel parcel) {
        this.performanceIcon = (PerformanceV2) parcel.readParcelable(PerformanceV2.class.getClassLoader());
    }

    public String toString() {
        return "NotificationItemObject{performanceIcon=" + this.performanceIcon + '}';
    }
}
