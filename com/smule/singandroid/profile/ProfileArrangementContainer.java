/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 */
package com.smule.singandroid.profile;

import android.os.Parcel;
import android.os.Parcelable;
import com.smule.android.songbook.ArrangementVersionLiteEntry;

public class ProfileArrangementContainer
implements Parcelable {
    public static final Parcelable.Creator<ProfileArrangementContainer> CREATOR = new Parcelable.Creator<ProfileArrangementContainer>(){

        public ProfileArrangementContainer a(Parcel parcel) {
            return new ProfileArrangementContainer(parcel);
        }

        public ProfileArrangementContainer[] a(int n) {
            return new ProfileArrangementContainer[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    private ArrangementVersionLiteEntry a;
    private Type b;

    protected ProfileArrangementContainer(Parcel parcel) {
        this.a = (ArrangementVersionLiteEntry)parcel.readParcelable(ArrangementVersionLiteEntry.class.getClassLoader());
        this.b = Type.values()[parcel.readInt()];
    }

    public ProfileArrangementContainer(ArrangementVersionLiteEntry arrangementVersionLiteEntry, Type type) {
        this.a = arrangementVersionLiteEntry;
        this.b = type;
    }

    public boolean a() {
        if (this.b == Type.a) {
            return true;
        }
        return false;
    }

    public boolean b() {
        if (this.b == Type.b) {
            return true;
        }
        return false;
    }

    public ArrangementVersionLiteEntry c() {
        return this.a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeParcelable((Parcelable)this.a, n);
        parcel.writeInt(this.b.ordinal());
    }

    public static enum Type {
        a,
        b;
        

        private Type() {
        }
    }

}

