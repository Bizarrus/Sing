package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SAOption implements Parcelable {
    public static final Creator<SAOption> CREATOR = new 1();
    @JsonProperty("text")
    public String text;

    public String toString() {
        return "SAOption{text='" + this.text + '\'' + '}';
    }

    public SAOption(Parcel parcel) {
        this.text = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.text.equals(((SAOption) obj).text);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.text);
    }
}
