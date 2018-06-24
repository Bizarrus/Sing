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
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ColorTheme;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.ProfileCustomizations;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ProfileCustomizations
extends ParsedResponse
implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        public ProfileCustomizations a(Parcel parcel) {
            return new ProfileCustomizations(parcel);
        }

        public ProfileCustomizations[] a(int n) {
            return new ProfileCustomizations[n];
        }

        public /* synthetic */ java.lang.Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ java.lang.Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty
    public String coverUrl;
    @JsonProperty
    public boolean displayMentions;
    @JsonProperty
    public String displayName;
    @JsonProperty
    public List<AccountIcon> mentionAccountIcons;
    @JsonProperty
    public PerformanceV2 pinPerformanceIcon;
    @JsonProperty
    public ColorTheme theme;

    public ProfileCustomizations() {
    }

    private ProfileCustomizations(Parcel parcel) {
        this.theme = (ColorTheme)parcel.readParcelable(ColorTheme.class.getClassLoader());
        this.coverUrl = parcel.readString();
        this.displayName = parcel.readString();
        this.pinPerformanceIcon = (PerformanceV2)parcel.readParcelable(PerformanceV2.class.getClassLoader());
        this.mentionAccountIcons = parcel.readArrayList(AccountIcon.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeParcelable((Parcelable)this.theme, n);
        parcel.writeString(this.coverUrl);
        parcel.writeString(this.displayName);
        parcel.writeParcelable((Parcelable)this.pinPerformanceIcon, n);
        parcel.writeList(this.mentionAccountIcons);
    }
}

