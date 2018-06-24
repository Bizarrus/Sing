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
import com.smule.android.network.models.NotificationItemObject;
import com.smule.android.network.models.PerformanceV2;

@JsonIgnoreProperties(ignoreUnknown=true)
public class NotificationItemObject
implements Parcelable {
    public static final Parcelable.Creator<NotificationItemObject> CREATOR = new Parcelable.Creator<NotificationItemObject>(){

        public NotificationItemObject a(Parcel parcel) {
            return new NotificationItemObject(parcel);
        }

        public NotificationItemObject[] a(int n) {
            return new NotificationItemObject[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="performance")
    public PerformanceV2 performanceIcon;

    public NotificationItemObject() {
    }

    private NotificationItemObject(Parcel parcel) {
        this.performanceIcon = (PerformanceV2)parcel.readParcelable(PerformanceV2.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "NotificationItemObject{performanceIcon=" + this.performanceIcon + '}';
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeParcelable((Parcelable)this.performanceIcon, 0);
    }
}

