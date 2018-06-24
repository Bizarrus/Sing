/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.models.Round;
import com.smule.android.network.models.Turn;
import java.util.ArrayList;
import java.util.List;

public class Round
implements Parcelable {
    public static final Parcelable.Creator<Round> CREATOR = new Parcelable.Creator<Round>(){

        public Round a(Parcel parcel) {
            return new Round(parcel);
        }

        public Round[] a(int n) {
            return new Round[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="round")
    public int round;
    @JsonProperty(value="turns")
    public List<Turn> turns = new ArrayList<Turn>();

    public Round() {
    }

    public Round(Parcel parcel) {
        this.round = parcel.readInt();
        this.turns = parcel.readArrayList(Turn.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeInt(this.round);
        parcel.writeList(this.turns);
    }
}

