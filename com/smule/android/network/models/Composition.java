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
import com.smule.android.network.models.Composition;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Composition
implements Parcelable {
    public static final Parcelable.Creator<Composition> CREATOR = new Parcelable.Creator<Composition>(){

        public Composition a(Parcel parcel) {
            return new Composition(parcel);
        }

        public Composition[] a(int n) {
            return new Composition[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="artist")
    public String artist;
    @JsonProperty(value="id")
    public String id;
    @JsonProperty(value="title")
    public String title;

    public Composition() {
    }

    private Composition(Parcel parcel) {
        this.id = parcel.readString();
        this.title = parcel.readString();
        this.artist = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Composition)) {
            return false;
        }
        object = (Composition)object;
        if (this.id != null) {
            if (!this.id.equals(object.id)) {
                return false;
            }
        } else if (object.id != null) return false;
        if (this.title != null) {
            if (!this.title.equals(object.title)) {
                return false;
            }
        } else if (object.title != null) return false;
        if (this.artist != null) {
            if (this.artist.equals(object.artist)) return true;
            return false;
        }
        if (object.artist == null) return true;
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public int hashCode() {
        int n = 0;
        int n2 = this.id != null ? this.id.hashCode() : 0;
        int n3 = this.title != null ? this.title.hashCode() : 0;
        if (this.artist != null) {
            n = this.artist.hashCode();
        }
        return (n3 + n2 * 31) * 31 + n;
    }

    public String toString() {
        return "Composition{id='" + this.id + '\'' + ", title=" + this.title + ", artist=" + this.artist + '}';
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeString(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.artist);
    }
}

