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
import com.smule.android.network.models.ResourceV2;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ResourceV2
implements Parcelable {
    public static final Parcelable.Creator<ResourceV2> CREATOR = new Parcelable.Creator<ResourceV2>(){

        public ResourceV2 a(Parcel parcel) {
            return new ResourceV2(parcel);
        }

        public ResourceV2[] a(int n) {
            return new ResourceV2[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="contentType")
    public String contentType;
    @JsonProperty(value="id")
    public long id;
    @JsonProperty(value="instrument")
    public String instrument;
    @JsonProperty(value="role")
    public String role;
    @JsonProperty(value="size")
    public long size;
    @JsonProperty(value="uid")
    public String uid;
    @JsonProperty(value="url")
    public String url;

    public ResourceV2() {
    }

    public ResourceV2(Parcel parcel) {
        this.instrument = parcel.readString();
        this.role = parcel.readString();
        this.url = parcel.readString();
        this.uid = parcel.readString();
        this.id = parcel.readLong();
        this.contentType = parcel.readString();
        this.size = parcel.readLong();
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        String string2 = this.instrument == null ? "" : this.instrument;
        parcel.writeString(string2);
        string2 = this.role == null ? "" : this.role;
        parcel.writeString(string2);
        string2 = this.url == null ? "" : this.url;
        parcel.writeString(string2);
        string2 = this.uid == null ? "" : this.uid;
        parcel.writeString(string2);
        parcel.writeLong(this.id);
        string2 = this.contentType == null ? "" : this.contentType;
        parcel.writeString(string2);
        parcel.writeLong(this.size);
    }
}

