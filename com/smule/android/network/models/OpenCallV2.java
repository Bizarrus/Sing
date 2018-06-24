/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  com.fasterxml.jackson.annotation.JsonIgnore
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.models.OpenCallV2;
import com.smule.android.network.models.PerformanceV2;
import java.io.File;

@JsonIgnoreProperties(ignoreUnknown=true)
public class OpenCallV2
implements Parcelable {
    public static final Parcelable.Creator<OpenCallV2> CREATOR;
    private static final String a;
    @JsonIgnore
    public File backgroundTrackFileAbsolutePath;
    @JsonProperty(value="expireAt")
    public long expireAt;
    @JsonProperty(value="isClosed")
    public boolean isClosed;
    @JsonProperty(value="isPrivate")
    public boolean isPrivate;
    @JsonProperty(value="numJoins")
    public int numJoins;
    @JsonProperty(value="opencallKey")
    public String opencallKey;
    @JsonProperty(value="performance")
    private PerformanceV2 performance;
    @JsonProperty(value="performanceIcon")
    private PerformanceV2 performanceIcon;
    @JsonProperty(value="type")
    public String type;

    static {
        a = OpenCallV2.class.getName();
        CREATOR = new Parcelable.Creator<OpenCallV2>(){

            public OpenCallV2 a(Parcel parcel) {
                return new OpenCallV2(parcel);
            }

            public OpenCallV2[] a(int n) {
                return new OpenCallV2[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
    }

    public OpenCallV2() {
    }

    /*
     * Enabled aggressive block sorting
     */
    public OpenCallV2(Parcel parcel) {
        boolean bl = true;
        this.opencallKey = parcel.readString();
        this.type = parcel.readString();
        boolean bl2 = parcel.readInt() != 0;
        this.isPrivate = bl2;
        bl2 = parcel.readInt() != 0 ? bl : false;
        this.isClosed = bl2;
        this.expireAt = parcel.readLong();
        this.numJoins = parcel.readInt();
        this.performance = (PerformanceV2)parcel.readParcelable(PerformanceV2.class.getClassLoader());
        this.performanceIcon = (PerformanceV2)parcel.readParcelable(PerformanceV2.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        int n2 = 1;
        String string2 = this.opencallKey == null ? "" : this.opencallKey;
        parcel.writeString(string2);
        string2 = this.type == null ? "" : this.type;
        parcel.writeString(string2);
        n = this.isPrivate ? 1 : 0;
        parcel.writeInt(n);
        n = this.isClosed ? n2 : 0;
        parcel.writeInt(n);
        parcel.writeLong(this.expireAt);
        parcel.writeInt(this.numJoins);
        parcel.writeParcelable((Parcelable)this.performance, 0);
        parcel.writeParcelable((Parcelable)this.performanceIcon, 0);
    }
}

