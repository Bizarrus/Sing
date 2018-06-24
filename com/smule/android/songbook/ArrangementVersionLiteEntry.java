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
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.models.Arrangement;
import com.smule.android.network.models.ArrangementVersion;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.songbook.SongbookEntry;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ArrangementVersionLiteEntry
extends SongbookEntry {
    public static final Parcelable.Creator<ArrangementVersionLiteEntry> CREATOR = new Parcelable.Creator<ArrangementVersionLiteEntry>(){

        public ArrangementVersionLiteEntry a(Parcel parcel) {
            return new ArrangementVersionLiteEntry(parcel);
        }

        public ArrangementVersionLiteEntry[] a(int n) {
            return new ArrangementVersionLiteEntry[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    public ArrangementVersionLite a;
    public boolean b;
    protected boolean c;

    /*
     * Enabled aggressive block sorting
     */
    public ArrangementVersionLiteEntry(Parcel parcel) {
        boolean bl = true;
        this.b = false;
        this.c = false;
        this.a = (ArrangementVersionLite)parcel.readParcelable(ArrangementVersionLite.class.getClassLoader());
        boolean bl2 = parcel.readByte() == 1;
        this.b = bl2;
        bl2 = parcel.readByte() == 1 ? bl : false;
        this.c = bl2;
    }

    public ArrangementVersionLiteEntry(ArrangementVersion arrangementVersion) {
        this.b = false;
        this.c = false;
        this.a = new ArrangementVersionLite();
        this.a.a(arrangementVersion);
    }

    public ArrangementVersionLiteEntry(ArrangementVersionLite arrangementVersionLite) {
        this.b = false;
        this.c = false;
        this.a = arrangementVersionLite;
    }

    public ArrangementVersionLite a() {
        return this.a;
    }

    public boolean b() {
        if (this.a != null && this.a.a()) {
            return true;
        }
        return false;
    }

    @Override
    public String c() {
        return this.a.key;
    }

    @Override
    public String d() {
        if (this.a != null) {
            return this.a.songId;
        }
        return null;
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String e() {
        if (this.a.a() || this.a.compTitle == null) {
            return this.a.name;
        }
        if (!TextUtils.isEmpty((CharSequence)this.a.name)) {
            return this.a.compTitle + " - " + this.a.name;
        }
        return this.a.compTitle;
    }

    @Override
    public String f() {
        return this.a.artist;
    }

    @Override
    public boolean g() {
        return false;
    }

    @Override
    public boolean h() {
        boolean bl;
        boolean bl2 = bl = false;
        if (this.a.arrangementVersion != null) {
            bl2 = bl;
            if (this.a.arrangementVersion.arrangement != null) {
                bl2 = this.a.arrangementVersion.arrangement.noPaywall;
            }
        }
        return bl2;
    }

    @Override
    public int i() {
        return MagicNetwork.d().getArrangementPrice();
    }

    @Override
    public String j() {
        return this.a.coverUrl;
    }

    @Override
    public boolean k() {
        return this.a.lyrics;
    }

    @Override
    public Map<String, String> l() {
        if (this.a.arrangementVersion != null) {
            return this.a.arrangementVersion.resourceFilePaths;
        }
        return null;
    }

    @Override
    public SongbookEntry.EntryType m() {
        return SongbookEntry.EntryType.b;
    }

    @Override
    public SongbookEntry n() {
        return SongbookEntry.b;
    }

    @Override
    public boolean o() {
        boolean bl = false;
        if (this.a != null) {
            bl = "3402003_3402003".equals(this.a.key);
        }
        return bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        byte by = 1;
        parcel.writeParcelable((Parcelable)this.a, 0);
        byte by2 = this.b ? 1 : 0;
        parcel.writeByte(by2);
        by2 = this.c ? by : 0;
        parcel.writeByte(by2);
    }

}

