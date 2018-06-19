package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Composition implements Parcelable {
    public static final Creator<Composition> CREATOR = new 1();
    @JsonProperty("artist")
    public String artist;
    @JsonProperty("id")
    public String id;
    @JsonProperty("title")
    public String title;

    public String toString() {
        return "Composition{id='" + this.id + '\'' + ", title=" + this.title + ", artist=" + this.artist + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Composition)) {
            return false;
        }
        Composition composition = (Composition) obj;
        if (this.id == null ? composition.id != null : !this.id.equals(composition.id)) {
            return false;
        }
        if (this.title == null ? composition.title != null : !this.title.equals(composition.title)) {
            return false;
        }
        if (this.artist != null) {
            if (this.artist.equals(composition.artist)) {
                return true;
            }
        } else if (composition.artist == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.id != null) {
            hashCode = this.id.hashCode();
        } else {
            hashCode = 0;
        }
        int i2 = hashCode * 31;
        if (this.title != null) {
            hashCode = this.title.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (this.artist != null) {
            i = this.artist.hashCode();
        }
        return hashCode + i;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.artist);
    }

    private Composition(Parcel parcel) {
        this.id = parcel.readString();
        this.title = parcel.readString();
        this.artist = parcel.readString();
    }
}
