package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class Round implements Parcelable {
    public static final Creator<Round> CREATOR = new 1();
    @JsonProperty("round")
    public int round;
    @JsonProperty("turns")
    public List<Turn> turns = new ArrayList();

    public Round(Parcel parcel) {
        this.round = parcel.readInt();
        this.turns = parcel.readArrayList(Turn.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.round);
        parcel.writeList(this.turns);
    }
}
