package com.smule.singandroid.utils;

import com.smule.android.logging.Log;
import com.smule.android.network.managers.EntitlementsManager;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.models.CompositionLite;
import com.smule.android.network.models.CompositionLite$Type;
import com.smule.android.network.models.ListingV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.RecommendedEntry;
import com.smule.android.songbook.SongbookEntry;
import java.util.ArrayList;
import java.util.List;

public class SongbookEntryUtils {
    private static final String f25071a = SongbookEntryUtils.class.getName();

    public static String m26164a(SongbookEntry songbookEntry, SongV2 songV2) {
        if (songbookEntry == null) {
            return "";
        }
        if (songbookEntry.m18772r()) {
            SongV2 a;
            if (songV2 == null) {
                String str = ((ArrangementVersionLiteEntry) songbookEntry).f17623a.songId;
                if (str != null) {
                    a = StoreManager.m18378a().m18416a(str);
                } else {
                    a = null;
                }
            } else {
                a = songV2;
            }
            if (songbookEntry.mo6295i() != null) {
                return songbookEntry.mo6295i();
            }
            if (a != null) {
                return a.googleCoverArtUrl;
            }
            return "";
        } else if (songbookEntry.mo6295i() != null) {
            return songbookEntry.mo6295i();
        } else {
            return null;
        }
    }

    public static String m26163a(SongbookEntry songbookEntry) {
        if (songbookEntry instanceof RecommendedEntry) {
            return ((RecommendedEntry) songbookEntry).mo6300n();
        }
        return null;
    }

    public static boolean m26166a(SongbookEntry songbookEntry, String str) {
        return SongbookSectionUtil.m26170a(str) || songbookEntry.m18772r() || !songbookEntry.mo6293g() || SubscriptionManager.a().b() || EntitlementsManager.m18163a().m18180a(songbookEntry.mo6289c());
    }

    public static String m26167b(SongbookEntry songbookEntry) {
        String str = null;
        if (songbookEntry != null && songbookEntry.m18773s()) {
            str = songbookEntry.mo6289c();
        } else if (songbookEntry != null && songbookEntry.m18772r()) {
            str = songbookEntry.mo6290d();
        }
        if (str == null || str.isEmpty()) {
            return "-";
        }
        return str;
    }

    public static String m26168c(SongbookEntry songbookEntry) {
        if (songbookEntry == null || !songbookEntry.m18772r()) {
            return "-";
        }
        if (songbookEntry.mo6289c() == null || songbookEntry.mo6289c().isEmpty()) {
            return "-";
        }
        return songbookEntry.mo6289c();
    }

    public static List<SongbookEntry> m26165a(List<CompositionLite> list) {
        List<SongbookEntry> arrayList = new ArrayList();
        for (CompositionLite compositionLite : list) {
            if (compositionLite.mType == CompositionLite$Type.ARR && compositionLite.mArrangementVersionLite != null) {
                arrayList.add(SongbookEntry.m18744a(compositionLite.mArrangementVersionLite));
            } else if (compositionLite.mType == CompositionLite$Type.f17391a && compositionLite.mSongLite != null) {
                ListingV2 e = StoreManager.m18378a().m18428e(compositionLite.mSongLite.songId);
                if (e == null) {
                    Log.d(f25071a, "Could not find listing for: " + compositionLite.mSongLite.songId);
                } else {
                    arrayList.add(SongbookEntry.m18746a(e));
                }
            }
        }
        return arrayList;
    }
}
