package com.smule.android.songbook;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.smule.android.network.models.ListingV2;

public class RecListingEntry extends ListingEntry implements RecommendedEntry {
    public static final Creator<RecListingEntry> CREATOR = new C36511();
    private String f17643b;
    private boolean f17644c;

    static class C36511 implements Creator<RecListingEntry> {
        C36511() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m18814a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m18815a(i);
        }

        public RecListingEntry m18814a(Parcel parcel) {
            return new RecListingEntry(parcel);
        }

        public RecListingEntry[] m18815a(int i) {
            return new RecListingEntry[i];
        }
    }

    public RecListingEntry(ListingV2 listingV2, String str, boolean z) {
        super(listingV2);
        this.f17643b = str;
        this.f17644c = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f17643b);
        parcel.writeByte(this.f17644c ? (byte) 1 : (byte) 0);
    }

    public RecListingEntry(Parcel parcel) {
        boolean z = true;
        super(parcel);
        this.f17643b = parcel.readString();
        if (parcel.readByte() != (byte) 1) {
            z = false;
        }
        this.f17644c = z;
    }

    public String mo6300n() {
        return this.f17643b;
    }

    public int mo6294h() {
        if (this.f17644c) {
            return 0;
        }
        return super.mo6294h();
    }

    public boolean mo6293g() {
        return !this.f17644c && super.mo6293g();
    }
}
