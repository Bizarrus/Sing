package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedListItemObject implements Parcelable {
    public static final Creator<FeedListItemObject> CREATOR = new 1();
    @JsonProperty("arrVersionLite")
    public ArrangementVersionLite arrVersionLite;
    @JsonProperty("performance")
    public PerformanceV2 performanceIcon;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.performanceIcon, 0);
        parcel.writeParcelable(this.arrVersionLite, 0);
    }

    private FeedListItemObject(Parcel parcel) {
        this.performanceIcon = (PerformanceV2) parcel.readParcelable(PerformanceV2.class.getClassLoader());
        this.arrVersionLite = (ArrangementVersionLite) parcel.readParcelable(ArrangementVersionLite.class.getClassLoader());
    }

    public String toString() {
        return "FeedListItemObject{performanceIcon=" + this.performanceIcon + "arrVersionLite=" + this.arrVersionLite + '}';
    }

    public boolean m8256a() {
        if (this.performanceIcon == null && this.arrVersionLite == null) {
            return false;
        }
        return true;
    }
}
