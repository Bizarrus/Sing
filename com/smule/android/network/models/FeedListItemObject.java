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
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.FeedListItemObject;
import com.smule.android.network.models.PerformanceV2;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FeedListItemObject
implements Parcelable {
    public static final Parcelable.Creator<FeedListItemObject> CREATOR = new Parcelable.Creator<FeedListItemObject>(){

        public FeedListItemObject a(Parcel parcel) {
            return new FeedListItemObject(parcel);
        }

        public FeedListItemObject[] a(int n) {
            return new FeedListItemObject[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="arrVersionLite")
    public ArrangementVersionLite arrVersionLite;
    @JsonProperty(value="performance")
    public PerformanceV2 performanceIcon;

    public FeedListItemObject() {
    }

    private FeedListItemObject(Parcel parcel) {
        this.performanceIcon = (PerformanceV2)parcel.readParcelable(PerformanceV2.class.getClassLoader());
        this.arrVersionLite = (ArrangementVersionLite)parcel.readParcelable(ArrangementVersionLite.class.getClassLoader());
    }

    public boolean a() {
        if (this.performanceIcon == null && this.arrVersionLite == null) {
            return false;
        }
        return true;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "FeedListItemObject{performanceIcon=" + this.performanceIcon + "arrVersionLite=" + this.arrVersionLite + '}';
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeParcelable((Parcelable)this.performanceIcon, 0);
        parcel.writeParcelable((Parcelable)this.arrVersionLite, 0);
    }
}

