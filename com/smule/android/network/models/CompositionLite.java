package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompositionLite implements Parcelable {
    public static final Creator<CompositionLite> CREATOR = new 1();
    @JsonProperty("arrangementVersionLite")
    public ArrangementVersionLite mArrangementVersionLite;
    @JsonProperty("songLite")
    public SongV2 mSongLite;
    @JsonProperty("type")
    public Type mType;

    public String toString() {
        return "SearchSongbookResult{mType=" + this.mType + ", mSongLite=" + this.mSongLite + ", mArrangementVersionLite=" + this.mArrangementVersionLite + '}';
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mType == null ? -1 : this.mType.ordinal());
        parcel.writeParcelable(this.mSongLite, 0);
        parcel.writeParcelable(this.mArrangementVersionLite, 0);
    }

    public boolean m8250a() {
        return this.mType == Type.b;
    }

    public CompositionLite(Parcel parcel) {
        int readInt = parcel.readInt();
        this.mType = readInt == -1 ? null : Type.values()[readInt];
        this.mSongLite = (SongV2) parcel.readParcelable(SongV2.class.getClassLoader());
        this.mArrangementVersionLite = (ArrangementVersionLite) parcel.readParcelable(ArrangementVersionLite.class.getClassLoader());
    }
}
