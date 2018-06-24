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
import com.smule.android.network.models.Topic;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Topic
implements Parcelable {
    public static final Parcelable.Creator<Topic> CREATOR = new Parcelable.Creator<Topic>(){

        public Topic a(Parcel parcel) {
            return new Topic(parcel);
        }

        public Topic[] a(int n) {
            return new Topic[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="coverUrls")
    public ArrayList<String> coverUrls;
    @JsonProperty(value="displayName")
    public String displayName;
    @JsonProperty(value="id")
    public int id;

    public Topic() {
    }

    public Topic(Parcel parcel) {
        this.id = parcel.readInt();
        this.displayName = parcel.readString();
        parcel.readStringList(this.coverUrls);
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "Topic [id=" + this.id + ", displayName=" + this.displayName + ", coverUrls=" + this.coverUrls + "]";
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeInt(this.id);
        parcel.writeString(this.displayName);
        parcel.writeStringList(this.coverUrls);
    }
}

