/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  com.bluelinelabs.logansquare.annotation.JsonField
 *  com.bluelinelabs.logansquare.annotation.JsonObject
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.models.VerifiedUrl;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonObject
public class VerifiedUrl
implements Parcelable {
    public static final Parcelable.Creator<VerifiedUrl> CREATOR = new Parcelable.Creator<VerifiedUrl>(){

        public VerifiedUrl a(Parcel parcel) {
            return new VerifiedUrl(parcel);
        }

        public VerifiedUrl[] a(int n) {
            return new VerifiedUrl[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="desc")
    @JsonField
    public String desc;
    @JsonProperty(value="type")
    @JsonField
    public String type;
    @JsonProperty(value="url")
    @JsonField
    public String url;

    public VerifiedUrl() {
    }

    public VerifiedUrl(Parcel parcel) {
        this.type = parcel.readString();
        this.desc = parcel.readString();
        this.url = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        String string2 = this.type == null ? "" : this.type;
        parcel.writeString(string2);
        string2 = this.desc == null ? "" : this.desc;
        parcel.writeString(string2);
        string2 = this.url == null ? "" : this.url;
        parcel.writeString(string2);
    }
}

