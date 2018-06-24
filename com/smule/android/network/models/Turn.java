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
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.Turn;

public class Turn
implements Parcelable {
    public static final Parcelable.Creator<Turn> CREATOR = new Parcelable.Creator<Turn>(){

        public Turn a(Parcel parcel) {
            return new Turn(parcel);
        }

        public Turn[] a(int n) {
            return new Turn[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="accountIcon")
    public AccountIcon accountIcon;
    @JsonProperty(value="trackApp")
    public String trackApp;
    @JsonProperty(value="trackInstrumentId")
    public String trackInstrumentId;
    @JsonProperty(value="trackOptions")
    public String trackOptions;
    @JsonProperty(value="trackPartId")
    public int trackPartId;
    @JsonProperty(value="trackUrl")
    public String trackUrl;
    @JsonProperty(value="turn")
    public int turn;

    public Turn() {
    }

    public Turn(Parcel parcel) {
        this.turn = parcel.readInt();
        this.accountIcon = (AccountIcon)parcel.readParcelable(AccountIcon.class.getClassLoader());
        this.trackUrl = parcel.readString();
        this.trackOptions = parcel.readString();
        this.trackPartId = parcel.readInt();
        this.trackInstrumentId = parcel.readString();
        this.trackApp = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeInt(this.turn);
        parcel.writeParcelable((Parcelable)this.accountIcon, 0);
        parcel.writeString(this.trackUrl);
        parcel.writeString(this.trackOptions);
        parcel.writeInt(this.trackPartId);
        parcel.writeString(this.trackInstrumentId);
        parcel.writeString(this.trackApp);
    }
}

