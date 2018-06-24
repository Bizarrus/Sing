/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.text.TextUtils
 */
package com.smule.android.songbook;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.smule.android.network.models.ListingV2;
import com.smule.android.network.models.ResourceV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.songbook.ListingEntry;
import com.smule.android.songbook.PerformanceV2Util;
import com.smule.android.songbook.SongbookEntry;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
public class ListingEntry
extends SongbookEntry {
    public static final Parcelable.Creator<ListingEntry> CREATOR = new Parcelable.Creator<ListingEntry>(){

        public ListingEntry a(Parcel parcel) {
            return new ListingEntry(parcel);
        }

        public ListingEntry[] a(int n) {
            return new ListingEntry[n];
        }

        public /* synthetic */ java.lang.Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ java.lang.Object[] newArray(int n) {
            return this.a(n);
        }
    };
    public ListingV2 a;

    public ListingEntry(Parcel parcel) {
        this.a = (ListingV2)parcel.readParcelable(ListingV2.class.getClassLoader());
    }

    public ListingEntry(ListingV2 listingV2) {
        this.a = listingV2;
        if (this.a != null && TextUtils.isEmpty((CharSequence)this.j()) && this.a.song != null && this.a.song.a("cover_art_google") != null) {
            this.a.song.googleCoverArtUrl = this.a.song.a((String)"cover_art_google").url;
        }
    }

    @Override
    public String c() {
        return this.a.productId;
    }

    @Override
    public String d() {
        return this.a.productId;
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String e() {
        if (this.a.song != null) {
            return this.a.song.title;
        }
        return null;
    }

    @Override
    public String f() {
        if (this.a.song != null) {
            return this.a.song.artist;
        }
        return null;
    }

    @Override
    public boolean g() {
        return this.a.a();
    }

    @Override
    public boolean h() {
        return this.q();
    }

    @Override
    public int i() {
        return this.a.price;
    }

    @Override
    public String j() {
        if (this.a.song != null) {
            return this.a.song.googleCoverArtUrl;
        }
        return null;
    }

    @Override
    public boolean k() {
        if (!PerformanceV2Util.a(this.c())) {
            return true;
        }
        return false;
    }

    @Override
    public Map<String, String> l() {
        if (this.a.song != null) {
            return this.a.song.resourceFilePaths;
        }
        return null;
    }

    @Override
    public SongbookEntry.EntryType m() {
        return SongbookEntry.EntryType.a;
    }

    @Override
    public SongbookEntry n() {
        return SongbookEntry.a;
    }

    public String toString() {
        return "Uid: " + this.c() + " Artist: " + this.f() + " Title: " + this.e();
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeParcelable((Parcelable)this.a, 0);
    }
}

