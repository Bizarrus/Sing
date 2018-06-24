/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.support.annotation.NonNull
 *  com.fasterxml.jackson.annotation.JsonIgnore
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.models.ColorTheme;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ColorTheme
implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        public ColorTheme a(Parcel parcel) {
            return new ColorTheme(parcel);
        }

        public ColorTheme[] a(int n) {
            return new ColorTheme[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty
    private Integer background;
    @JsonProperty
    private Integer foreground;
    @JsonProperty
    private Boolean lightText;

    public ColorTheme() {
    }

    private ColorTheme(Parcel parcel) {
        this.background = (Integer)parcel.readValue(Integer.class.getClassLoader());
        this.foreground = (Integer)parcel.readValue(Integer.class.getClassLoader());
        this.lightText = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
    }

    public ColorTheme(@NonNull Integer n, @NonNull Integer n2, @NonNull Boolean bl) {
        this.background = n;
        this.foreground = n2;
        this.lightText = bl;
    }

    public int describeContents() {
        return 0;
    }

    @JsonIgnore
    public boolean getLightText() {
        if (this.lightText != null) {
            return this.lightText;
        }
        return false;
    }

    @JsonIgnore
    public int getSnpBackgroundColor() {
        if (this.background != null) {
            return this.background;
        }
        return 0;
    }

    @JsonIgnore
    public int getSnpForegroundColor() {
        if (this.foreground != null) {
            return this.foreground;
        }
        return 0;
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeValue((Object)this.background);
        parcel.writeValue((Object)this.foreground);
        parcel.writeValue((Object)this.lightText);
    }
}

