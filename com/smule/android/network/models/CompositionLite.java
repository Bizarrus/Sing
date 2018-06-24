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
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.CompositionLite;
import com.smule.android.network.models.SongV2;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CompositionLite
implements Parcelable {
    public static final Parcelable.Creator<CompositionLite> CREATOR = new Parcelable.Creator<CompositionLite>(){

        public CompositionLite a(Parcel parcel) {
            return new CompositionLite(parcel);
        }

        public CompositionLite[] a(int n) {
            return new CompositionLite[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="arrangementVersionLite")
    public ArrangementVersionLite mArrangementVersionLite;
    @JsonProperty(value="songLite")
    @Deprecated
    public SongV2 mSongLite;
    @JsonProperty(value="type")
    public Type mType;

    public CompositionLite() {
    }

    /*
     * Enabled aggressive block sorting
     */
    public CompositionLite(Parcel parcel) {
        int n = parcel.readInt();
        Type type = n == -1 ? null : Type.values()[n];
        this.mType = type;
        this.mSongLite = (SongV2)parcel.readParcelable(SongV2.class.getClassLoader());
        this.mArrangementVersionLite = (ArrangementVersionLite)parcel.readParcelable(ArrangementVersionLite.class.getClassLoader());
    }

    public boolean a() {
        if (this.mType == Type.b) {
            return true;
        }
        return false;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "SearchSongbookResult{mType=" + (Object)((Object)this.mType) + ", mSongLite=" + this.mSongLite + ", mArrangementVersionLite=" + this.mArrangementVersionLite + '}';
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        n = this.mType == null ? -1 : this.mType.ordinal();
        parcel.writeInt(n);
        parcel.writeParcelable((Parcelable)this.mSongLite, 0);
        parcel.writeParcelable((Parcelable)this.mArrangementVersionLite, 0);
    }

    public static enum Type {
        a,
        b;
        

        private Type() {
        }
    }

}

