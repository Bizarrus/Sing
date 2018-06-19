package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Turn implements Parcelable {
    public static final Creator<Turn> CREATOR = new 1();
    @JsonProperty("accountIcon")
    public AccountIcon accountIcon;
    @JsonProperty("trackApp")
    public String trackApp;
    @JsonProperty("trackInstrumentId")
    public String trackInstrumentId;
    @JsonProperty("trackOptions")
    public String trackOptions;
    @JsonProperty("trackPartId")
    public int trackPartId;
    @JsonProperty("trackUrl")
    public String trackUrl;
    @JsonProperty("turn")
    public int turn;

    public Turn(Parcel parcel) {
        this.turn = parcel.readInt();
        this.accountIcon = (AccountIcon) parcel.readParcelable(AccountIcon.class.getClassLoader());
        this.trackUrl = parcel.readString();
        this.trackOptions = parcel.readString();
        this.trackPartId = parcel.readInt();
        this.trackInstrumentId = parcel.readString();
        this.trackApp = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.turn);
        parcel.writeParcelable(this.accountIcon, 0);
        parcel.writeString(this.trackUrl);
        parcel.writeString(this.trackOptions);
        parcel.writeInt(this.trackPartId);
        parcel.writeString(this.trackInstrumentId);
        parcel.writeString(this.trackApp);
    }
}
