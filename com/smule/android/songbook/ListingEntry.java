package com.smule.android.songbook;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.smule.android.network.models.ListingV2;
import com.smule.android.songbook.SongbookEntry.EntryType;
import com.smule.android.songbook.SongbookEntry.PrimaryCompType;
import java.util.Map;

public class ListingEntry extends SongbookEntry {
    public static final Creator<ListingEntry> CREATOR = new C36491();
    public ListingV2 f17626a;

    static class C36491 implements Creator<ListingEntry> {
        C36491() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m18789a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m18790a(i);
        }

        public ListingEntry m18789a(Parcel parcel) {
            return new ListingEntry(parcel);
        }

        public ListingEntry[] m18790a(int i) {
            return new ListingEntry[i];
        }
    }

    public ListingEntry(ListingV2 listingV2) {
        this.f17626a = listingV2;
        if (this.f17626a != null && TextUtils.isEmpty(mo6295i()) && this.f17626a.song != null && this.f17626a.song.a("cover_art_google") != null) {
            this.f17626a.song.googleCoverArtUrl = this.f17626a.song.a("cover_art_google").url;
        }
    }

    public ListingEntry(Parcel parcel) {
        this.f17626a = (ListingV2) parcel.readParcelable(ListingV2.class.getClassLoader());
    }

    public String mo6289c() {
        return this.f17626a.productId;
    }

    public String mo6290d() {
        return this.f17626a.productId;
    }

    public String mo6291e() {
        return this.f17626a.song != null ? this.f17626a.song.title : null;
    }

    public String mo6292f() {
        return this.f17626a.song != null ? this.f17626a.song.artist : null;
    }

    public boolean mo6293g() {
        return this.f17626a.a();
    }

    public int mo6294h() {
        return this.f17626a.price;
    }

    public String mo6295i() {
        return this.f17626a.song != null ? this.f17626a.song.googleCoverArtUrl : null;
    }

    public boolean mo6296j() {
        return !PerformanceV2Util.m18806a(mo6289c());
    }

    public Map<String, String> mo6297k() {
        return this.f17626a.song != null ? this.f17626a.song.resourceFilePaths : null;
    }

    public void mo6287a(String str, String str2) {
        if (this.f17626a.song != null) {
            this.f17626a.song.resourceFilePaths.put(str, str2);
        }
    }

    public boolean mo6288a(String str) {
        if (this.f17626a.song != null) {
            return this.f17626a.song.resourceFilePaths.containsKey(str);
        }
        return false;
    }

    public EntryType mo6298l() {
        return EntryType.LISTING;
    }

    public PrimaryCompType mo6299m() {
        return PrimaryCompType.f17651a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f17626a, 0);
    }

    public String toString() {
        return "Uid: " + mo6289c() + " Artist: " + mo6292f() + " Title: " + mo6291e();
    }
}
