package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationItemAggObject implements Parcelable {
    public static final Creator<NotificationItemAggObject> CREATOR = new 1();
    @JsonProperty("notificationKeys")
    public List<String> notificationKeys;

    public NotificationItemAggObject() {
        this.notificationKeys = new ArrayList();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.notificationKeys);
    }

    private NotificationItemAggObject(Parcel parcel) {
        this.notificationKeys = new ArrayList();
        this.notificationKeys = new ArrayList();
        parcel.readList(this.notificationKeys, List.class.getClassLoader());
    }

    public String toString() {
        return "NotificationItemAggObject{notificationKeys=" + this.notificationKeys + '}';
    }
}
