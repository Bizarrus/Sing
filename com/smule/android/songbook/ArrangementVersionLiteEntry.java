package com.smule.android.songbook;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.models.ArrangementVersion;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.songbook.SongbookEntry.EntryType;
import com.smule.android.songbook.SongbookEntry.PrimaryCompType;
import java.util.Map;

public class ArrangementVersionLiteEntry extends SongbookEntry {
    public static final Creator<ArrangementVersionLiteEntry> CREATOR = new C36481();
    public ArrangementVersionLite f17623a;
    public boolean f17624b;
    protected boolean f17625c;

    static class C36481 implements Creator<ArrangementVersionLiteEntry> {
        C36481() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m18741a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m18742a(i);
        }

        public ArrangementVersionLiteEntry m18741a(Parcel parcel) {
            return new ArrangementVersionLiteEntry(parcel);
        }

        public ArrangementVersionLiteEntry[] m18742a(int i) {
            return new ArrangementVersionLiteEntry[i];
        }
    }

    public ArrangementVersionLiteEntry(ArrangementVersionLite arrangementVersionLite) {
        this.f17624b = false;
        this.f17625c = false;
        this.f17623a = arrangementVersionLite;
    }

    public ArrangementVersionLiteEntry(ArrangementVersion arrangementVersion) {
        this.f17624b = false;
        this.f17625c = false;
        this.f17623a = new ArrangementVersionLite();
        this.f17623a.a(arrangementVersion);
    }

    public ArrangementVersionLiteEntry(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.f17624b = false;
        this.f17625c = false;
        this.f17623a = (ArrangementVersionLite) parcel.readParcelable(ArrangementVersionLite.class.getClassLoader());
        if (parcel.readByte() == (byte) 1) {
            z = true;
        } else {
            z = false;
        }
        this.f17624b = z;
        if (parcel.readByte() != (byte) 1) {
            z2 = false;
        }
        this.f17625c = z2;
    }

    public ArrangementVersionLite m18774a() {
        return this.f17623a;
    }

    public boolean m18777b() {
        return this.f17623a != null && this.f17623a.a();
    }

    public String mo6289c() {
        return this.f17623a.key;
    }

    public String mo6290d() {
        if (this.f17623a != null) {
            return this.f17623a.songId;
        }
        return null;
    }

    public String mo6291e() {
        if (this.f17623a.a() || this.f17623a.compTitle == null) {
            return this.f17623a.name;
        }
        if (TextUtils.isEmpty(this.f17623a.name)) {
            return this.f17623a.compTitle;
        }
        return this.f17623a.compTitle + " - " + this.f17623a.name;
    }

    public String mo6292f() {
        return this.f17623a.artist;
    }

    public boolean mo6293g() {
        return false;
    }

    public int mo6294h() {
        return MagicNetwork.d().getArrangementPrice();
    }

    public String mo6295i() {
        return this.f17623a.coverUrl;
    }

    public boolean mo6296j() {
        return this.f17623a.lyrics;
    }

    public Map<String, String> mo6297k() {
        return this.f17623a.arrangementVersion != null ? this.f17623a.arrangementVersion.resourceFilePaths : null;
    }

    public void mo6287a(String str, String str2) {
        if (this.f17623a.arrangementVersion != null) {
            this.f17623a.arrangementVersion.resourceFilePaths.put(str, str2);
        }
    }

    public boolean mo6288a(String str) {
        if (this.f17623a.arrangementVersion != null) {
            return this.f17623a.arrangementVersion.resourceFilePaths.containsKey(str);
        }
        return false;
    }

    public EntryType mo6298l() {
        return EntryType.ARRANGEMENT;
    }

    public PrimaryCompType mo6299m() {
        return PrimaryCompType.ARR;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        byte b;
        byte b2 = (byte) 1;
        parcel.writeParcelable(this.f17623a, 0);
        if (this.f17624b) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        parcel.writeByte(b);
        if (!this.f17625c) {
            b2 = (byte) 0;
        }
        parcel.writeByte(b2);
    }
}
