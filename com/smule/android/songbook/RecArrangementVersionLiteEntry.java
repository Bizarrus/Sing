package com.smule.android.songbook;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.smule.android.network.models.ArrangementVersionLite;

public class RecArrangementVersionLiteEntry extends ArrangementVersionLiteEntry implements RecommendedEntry {
    public static final Creator<RecArrangementVersionLiteEntry> CREATOR = new C36501();
    public String f17640d;
    private boolean f17641e;
    private boolean f17642f;

    static class C36501 implements Creator<RecArrangementVersionLiteEntry> {
        C36501() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m18808a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m18809a(i);
        }

        public RecArrangementVersionLiteEntry m18808a(Parcel parcel) {
            return new RecArrangementVersionLiteEntry(parcel);
        }

        public RecArrangementVersionLiteEntry[] m18809a(int i) {
            return new RecArrangementVersionLiteEntry[i];
        }
    }

    public RecArrangementVersionLiteEntry(ArrangementVersionLite arrangementVersionLite, String str, boolean z) {
        this(arrangementVersionLite, str, z, false);
    }

    public RecArrangementVersionLiteEntry(ArrangementVersionLite arrangementVersionLite, String str, boolean z, boolean z2) {
        super(arrangementVersionLite);
        this.f17642f = false;
        this.f17640d = str;
        this.f17641e = z;
        this.f17642f = z2;
    }

    public RecArrangementVersionLiteEntry(Parcel parcel) {
        super(parcel);
        this.f17642f = false;
    }

    public String mo6300n() {
        return this.f17640d;
    }

    public int mo6294h() {
        if (!this.f17641e || this.f17642f) {
            return super.mo6294h();
        }
        return 0;
    }

    public boolean mo6293g() {
        if (this.f17642f) {
            return super.mo6293g();
        }
        return !this.f17641e && super.mo6293g();
    }
}
