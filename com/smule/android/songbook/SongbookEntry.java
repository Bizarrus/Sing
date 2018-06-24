/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcelable
 *  com.bluelinelabs.logansquare.typeconverters.IntBasedTypeConverter
 */
package com.smule.android.songbook;

import android.os.Parcelable;
import com.bluelinelabs.logansquare.typeconverters.IntBasedTypeConverter;
import com.smule.android.logging.Analytics;
import com.smule.android.network.managers.EntitlementsManager;
import com.smule.android.network.models.ArrangementVersion;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.ListingV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.ListingEntry;
import java.util.Comparator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class SongbookEntry
implements Parcelable {
    private static final String a = SongbookEntry.class.getName();
    private static Pattern b = Pattern.compile("[#('\"]");
    private String c;
    private boolean d;

    public static SongbookEntry a(ArrangementVersion arrangementVersion) {
        return new ArrangementVersionLiteEntry(arrangementVersion);
    }

    public static SongbookEntry a(ArrangementVersionLite arrangementVersionLite) {
        return new ArrangementVersionLiteEntry(arrangementVersionLite);
    }

    public static SongbookEntry a(ArrangementVersionLite object, boolean bl) {
        object = new ArrangementVersionLiteEntry((ArrangementVersionLite)object);
        object.b = bl;
        return object;
    }

    @Deprecated
    public static SongbookEntry a(ListingV2 listingV2) {
        return new ListingEntry(listingV2);
    }

    @Deprecated
    public static SongbookEntry a(SongV2 songV2) {
        return SongbookEntry.a(new ListingV2(songV2));
    }

    public static String a(SongbookEntry songbookEntry) {
        return Analytics.b(songbookEntry);
    }

    public static String a(String string2) {
        return b.matcher(string2).replaceAll("");
    }

    public static String b(SongbookEntry songbookEntry) {
        return Analytics.a(songbookEntry);
    }

    public static ArrangementVersionLiteEntry c(SongbookEntry songbookEntry) {
        if (songbookEntry instanceof ArrangementVersionLiteEntry) {
            return (ArrangementVersionLiteEntry)songbookEntry;
        }
        return null;
    }

    public abstract String c();

    public abstract String d();

    public abstract String e();

    public abstract String f();

    public abstract boolean g();

    public abstract boolean h();

    public abstract int i();

    public abstract String j();

    public abstract boolean k();

    public abstract Map<String, String> l();

    public abstract EntryType m();

    public abstract  n();

    public boolean o() {
        return false;
    }

    public boolean q() {
        if (this.i() == 0) {
            return true;
        }
        return false;
    }

    public String r() {
        if (this.s()) {
            return "owned";
        }
        if (!this.q() && !this.g()) {
            return "credits";
        }
        if (this.q()) {
            return "free";
        }
        return "vip";
    }

    public boolean s() {
        return EntitlementsManager.a().c(this.c());
    }

    public boolean t() {
        if (this.m() == EntryType.b) {
            return true;
        }
        return false;
    }

    @Deprecated
    public boolean u() {
        if (this.m() == EntryType.a) {
            return true;
        }
        return false;
    }

    public String v() {
        return Analytics.a(this);
    }

    public String w() {
        return Analytics.b(this);
    }

    public static enum EntryType {
        a,
        b,
        c,
        d,
        e;
        

        private EntryType() {
        }
    }

}

