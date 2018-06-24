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
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.RecommendedEntry;

public class RecArrangementVersionLiteEntry
extends ArrangementVersionLiteEntry
implements RecommendedEntry {
    public static final Parcelable.Creator<RecArrangementVersionLiteEntry> CREATOR = new Parcelable.Creator<RecArrangementVersionLiteEntry>(){

        public RecArrangementVersionLiteEntry a(Parcel parcel) {
            return new RecArrangementVersionLiteEntry(parcel);
        }

        public RecArrangementVersionLiteEntry[] a(int n) {
            return new RecArrangementVersionLiteEntry[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    public String d;
    private boolean e;
    private boolean f = false;

    public RecArrangementVersionLiteEntry(Parcel parcel) {
        super(parcel);
    }

    public RecArrangementVersionLiteEntry(ArrangementVersionLite arrangementVersionLite, String string2, boolean bl) {
        this(arrangementVersionLite, string2, bl, false);
    }

    public RecArrangementVersionLiteEntry(ArrangementVersionLite arrangementVersionLite, String string2, boolean bl, boolean bl2) {
        super(arrangementVersionLite);
        this.d = string2;
        this.e = bl;
        this.f = bl2;
    }

    @Override
    public boolean g() {
        if (this.f) {
            return super.g();
        }
        if (!this.e && super.g()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean h() {
        return super.h();
    }

    @Override
    public int i() {
        if (this.e && !this.f) {
            return 0;
        }
        return super.i();
    }

    @Override
    public String p() {
        return this.d;
    }

}

