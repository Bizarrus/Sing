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
import com.smule.android.network.models.NotificationItemAggObject;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class NotificationItemAggObject
implements Parcelable {
    public static final Parcelable.Creator<NotificationItemAggObject> CREATOR = new Parcelable.Creator<NotificationItemAggObject>(){

        public NotificationItemAggObject a(Parcel parcel) {
            return new NotificationItemAggObject(parcel);
        }

        public NotificationItemAggObject[] a(int n) {
            return new NotificationItemAggObject[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="notificationKeys")
    public List<String> notificationKeys = new ArrayList<String>();

    public NotificationItemAggObject() {
    }

    private NotificationItemAggObject(Parcel parcel) {
        this.notificationKeys = new ArrayList<String>();
        parcel.readList(this.notificationKeys, List.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "NotificationItemAggObject{notificationKeys=" + this.notificationKeys + '}';
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeList(this.notificationKeys);
    }
}

