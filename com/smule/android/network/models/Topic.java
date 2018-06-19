package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Topic implements Parcelable {
    public static final Creator<Topic> CREATOR = new 1();
    @JsonProperty("coverUrls")
    public ArrayList<String> coverUrls;
    @JsonProperty("displayName")
    public String displayName;
    @JsonProperty("id")
    public int id;

    public String toString() {
        return "Topic [id=" + this.id + ", displayName=" + this.displayName + ", coverUrls=" + this.coverUrls + "]";
    }

    public Topic(Parcel parcel) {
        this.id = parcel.readInt();
        this.displayName = parcel.readString();
        parcel.readStringList(this.coverUrls);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.displayName);
        parcel.writeStringList(this.coverUrls);
    }
}
