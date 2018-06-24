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
import com.smule.android.network.models.SAOption;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SAOption
implements Parcelable {
    public static final Parcelable.Creator<SAOption> CREATOR = new Parcelable.Creator<SAOption>(){

        public SAOption a(Parcel parcel) {
            return new SAOption(parcel);
        }

        public SAOption[] a(int n) {
            return new SAOption[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="text")
    public String text;

    public SAOption() {
    }

    public SAOption(Parcel parcel) {
        this.text = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        object = (SAOption)object;
        return this.text.equals(object.text);
    }

    public String toString() {
        return "SAOption{text='" + this.text + '\'' + '}';
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeString(this.text);
    }
}

