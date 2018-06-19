package com.smule.android.songbook;

import android.os.Parcelable;
import com.bluelinelabs.logansquare.typeconverters.IntBasedTypeConverter;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.EntitlementsManager;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.models.ArrangementVersion;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.ListingV2;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SongV2;
import java.util.Comparator;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class SongbookEntry implements Parcelable {
    private static final String f17619a = SongbookEntry.class.getName();
    private static Pattern f17620b = Pattern.compile("[#('\"]");
    private String f17621c;
    private boolean f17622d;

    public enum EntryType {
        LISTING,
        ARRANGEMENT,
        DRAFT,
        USER,
        SEARCH
    }

    public enum PrimaryCompType {
        f17651a,
        ARR;

        public static class TypeConverter extends IntBasedTypeConverter<PrimaryCompType> {
            public /* synthetic */ int convertToInt(Object obj) {
                return m18819a((PrimaryCompType) obj);
            }

            public /* synthetic */ Object getFromInt(int i) {
                return m18820a(i);
            }

            public PrimaryCompType m18820a(int i) {
                return PrimaryCompType.values()[i];
            }

            public int m18819a(PrimaryCompType primaryCompType) {
                return primaryCompType.ordinal();
            }
        }
    }

    public static class SortByArtistAlphaComparator implements Comparator<SongbookEntry> {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m18822a((SongbookEntry) obj, (SongbookEntry) obj2);
        }

        public int m18822a(SongbookEntry songbookEntry, SongbookEntry songbookEntry2) {
            return m18821a(songbookEntry).compareToIgnoreCase(m18821a(songbookEntry2));
        }

        private String m18821a(SongbookEntry songbookEntry) {
            String f = songbookEntry.mo6292f() == null ? "" : songbookEntry.mo6292f();
            if (!songbookEntry.f17622d) {
                songbookEntry.f17621c = SongbookEntry.m18753b(f);
                if (!songbookEntry.f17621c.equals(songbookEntry.mo6292f())) {
                    f = songbookEntry.f17621c;
                }
                songbookEntry.f17622d = true;
                return f;
            } else if (songbookEntry.f17621c != null) {
                return songbookEntry.f17621c;
            } else {
                return f;
            }
        }
    }

    public static class SortByReleaseDateComparator implements Comparator<SongbookEntry> {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m18823a((SongbookEntry) obj, (SongbookEntry) obj2);
        }

        public int m18823a(SongbookEntry songbookEntry, SongbookEntry songbookEntry2) {
            long j = 0;
            long j2 = songbookEntry instanceof ArrangementVersionLiteEntry ? ((ArrangementVersionLiteEntry) songbookEntry).f17623a.arrCreatedAt : songbookEntry instanceof ListingEntry ? ((ListingEntry) songbookEntry).f17626a.liveTs : 0;
            if (songbookEntry2 instanceof ArrangementVersionLiteEntry) {
                j = ((ArrangementVersionLiteEntry) songbookEntry2).f17623a.arrCreatedAt;
            } else if (songbookEntry instanceof ListingEntry) {
                j = ((ListingEntry) songbookEntry2).f17626a.liveTs;
            }
            if (j2 > j) {
                return -1;
            }
            return j2 == j ? 0 : 1;
        }
    }

    public static class SortByTitleAlphaComparator implements Comparator<SongbookEntry> {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m18824a((SongbookEntry) obj, (SongbookEntry) obj2);
        }

        public int m18824a(SongbookEntry songbookEntry, SongbookEntry songbookEntry2) {
            return SongbookEntry.m18753b(songbookEntry.mo6291e()).compareToIgnoreCase(SongbookEntry.m18753b(songbookEntry2.mo6291e()));
        }
    }

    public abstract void mo6287a(String str, String str2);

    public abstract boolean mo6288a(String str);

    public abstract String mo6289c();

    public abstract String mo6290d();

    public abstract String mo6291e();

    public abstract String mo6292f();

    public abstract boolean mo6293g();

    public abstract int mo6294h();

    public abstract String mo6295i();

    public abstract boolean mo6296j();

    public abstract Map<String, String> mo6297k();

    public abstract EntryType mo6298l();

    public abstract PrimaryCompType mo6299m();

    public static String m18749a(SongbookEntry songbookEntry) {
        return Analytics.m17882b(songbookEntry);
    }

    public static String m18752b(SongbookEntry songbookEntry) {
        return Analytics.m17830a(songbookEntry);
    }

    public static SongbookEntry m18746a(ListingV2 listingV2) {
        return new ListingEntry(listingV2);
    }

    public static SongbookEntry m18748a(SongV2 songV2) {
        return m18746a(new ListingV2(songV2));
    }

    public static SongbookEntry m18744a(ArrangementVersionLite arrangementVersionLite) {
        return new ArrangementVersionLiteEntry(arrangementVersionLite);
    }

    public static SongbookEntry m18745a(ArrangementVersionLite arrangementVersionLite, boolean z) {
        SongbookEntry arrangementVersionLiteEntry = new ArrangementVersionLiteEntry(arrangementVersionLite);
        arrangementVersionLiteEntry.f17624b = z;
        return arrangementVersionLiteEntry;
    }

    public static SongbookEntry m18743a(ArrangementVersion arrangementVersion) {
        return new ArrangementVersionLiteEntry(arrangementVersion);
    }

    public static SongbookEntry m18747a(PerformanceV2 performanceV2) {
        if (performanceV2.r()) {
            return m18743a(performanceV2.arrangementVersion);
        }
        SongV2 b = PerformanceV2Util.m18807b(performanceV2);
        if (b != null) {
            ListingV2 e = StoreManager.m18378a().m18428e(b.songId);
            if (e != null) {
                return m18746a(e);
            }
        }
        Log.e(f17619a, "No song found for performance - " + performanceV2.songUid);
        return null;
    }

    public boolean m18769o() {
        return mo6294h() == 0;
    }

    public String m18770p() {
        if (m18771q()) {
            return "owned";
        }
        if (!m18769o() && !mo6293g()) {
            return "credits";
        }
        if (m18769o()) {
            return "free";
        }
        return "vip";
    }

    public boolean m18771q() {
        return EntitlementsManager.m18163a().m18184c(mo6289c());
    }

    public boolean m18772r() {
        return mo6298l() == EntryType.ARRANGEMENT;
    }

    public boolean m18773s() {
        return mo6298l() == EntryType.LISTING;
    }

    public static String m18753b(String str) {
        return f17620b.matcher(str).replaceAll("");
    }
}
