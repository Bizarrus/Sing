/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 */
package com.smule.android.songbook;

import android.os.Parcel;
import android.os.Parcelable;
import com.smule.android.songbook.ListingEntry;
import com.smule.android.songbook.RecListingEntry;
import com.smule.android.songbook.RecommendedEntry;

@Deprecated
public class RecListingEntry
extends ListingEntry
implements RecommendedEntry {
    public static final Parcelable.Creator<RecListingEntry> CREATOR = new Parcelable.Creator<RecListingEntry>(){

        public RecListingEntry a(Parcel parcel) {
            return new RecListingEntry(parcel);
        }

        public RecListingEntry[] a(int n) {
            return new RecListingEntry[n];
        }

        public /* synthetic */ java.lang.Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ java.lang.Object[] newArray(int n) {
            return this.a(n);
        }
    };
    private String b;
    private boolean c;

    /*
     * Enabled aggressive block sorting
     */
    public RecListingEntry(Parcel parcel) {
        boolean bl = true;
        super(parcel);
        this.b = parcel.readString();
        if (parcel.readByte() != 1) {
            bl = false;
        }
        this.c = bl;
    }

    @Override
    public boolean g() {
        if (!this.c && super.g()) {
            return true;
        }
        return false;
    }

    @Override
    public int i() {
        if (this.c) {
            return 0;
        }
        return super.i();
    }

    @Override
    public String p() {
        return this.b;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void writeToParcel(Parcel parcel, int n) {
        super.writeToParcel(parcel, n);
        parcel.writeString(this.b);
        byte by = this.c ? 1 : 0;
        parcel.writeByte(by);
    }
}

