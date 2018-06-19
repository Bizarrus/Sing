package com.smule.android.utils;

import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.ArrangementManager;
import com.smule.android.network.managers.EntitlementsManager;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.ListingV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.songbook.SongbookEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.androidannotations.annotations.SupposeUiThread;

public class SongbookSectionUtils {
    private static final String f17852a = SongbookSectionUtils.class.getSimpleName();
    private static SongbookSectionUtils f17853b;
    private volatile boolean f17854c;
    private final List<OnFetchedNonListingOwnedSongs> f17855d = new ArrayList();

    public interface OnFetchedNonListingOwnedSongs {
        void mo6651a(List<SongbookEntry> list);
    }

    public class SongbookEntryComparatorByDisplayName implements Comparator<SongbookEntry> {
        final /* synthetic */ SongbookSectionUtils f17851a;

        public SongbookEntryComparatorByDisplayName(SongbookSectionUtils songbookSectionUtils) {
            this.f17851a = songbookSectionUtils;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m19041a((SongbookEntry) obj, (SongbookEntry) obj2);
        }

        public int m19041a(SongbookEntry songbookEntry, SongbookEntry songbookEntry2) {
            if (songbookEntry != null && songbookEntry2 != null && songbookEntry.mo6291e() != null && songbookEntry2.mo6291e() != null) {
                return songbookEntry.mo6291e().replaceAll("\\p{Punct}", "").toLowerCase().compareTo(songbookEntry2.mo6291e().replaceAll("\\p{Punct}", "").toLowerCase());
            }
            Log.d(SongbookSectionUtils.f17852a, "ListingComparatorByDisplayName - null elements passed in. Returning 0.");
            return 0;
        }
    }

    public static SongbookSectionUtils m19042a() {
        if (f17853b == null) {
            f17853b = new SongbookSectionUtils();
        }
        return f17853b;
    }

    @SupposeUiThread
    public void m19048a(OnFetchedNonListingOwnedSongs onFetchedNonListingOwnedSongs) {
        synchronized (this.f17855d) {
            this.f17855d.add(onFetchedNonListingOwnedSongs);
        }
    }

    private void m19046b(List<SongbookEntry> list) {
        synchronized (this.f17855d) {
            try {
                for (OnFetchedNonListingOwnedSongs a : this.f17855d) {
                    a.mo6651a(list);
                }
                this.f17855d.clear();
            } catch (Throwable th) {
                this.f17855d.clear();
            }
        }
    }

    public List<SongbookEntry> m19050b() {
        Set b = EntitlementsManager.m18163a().m18181b();
        if (b == null || b.size() == 0) {
            Log.b(f17852a, "createOwnedSongsSection - getOwnedProducts returned no UIDs; aborting creation of owned songs section");
        }
        Set c = EntitlementsManager.m18163a().m18183c();
        if (c == null || c.size() == 0) {
            Log.b(f17852a, "createOwnedSongsSection - getOwnedArrangements returned no UIDs; aborting creation of owned songs section");
        }
        return m19043a(b, c);
    }

    @SupposeUiThread
    public void m19049a(final List<String> list) {
        this.f17854c = true;
        MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ SongbookSectionUtils f17850b;

            public void run() {
                List arrayList = new ArrayList();
                for (String str : list) {
                    SongV2 songV2 = new SongV2();
                    songV2.songId = str;
                    arrayList.add(SongbookEntry.m18748a(StoreManager.m18378a().m18415a(songV2)));
                }
                this.f17850b.m19046b(arrayList);
                this.f17850b.f17854c = false;
            }
        });
    }

    public boolean m19051c() {
        return this.f17854c;
    }

    private List<SongbookEntry> m19043a(Set<String> set, Set<String> set2) {
        List<SongbookEntry> arrayList = new ArrayList();
        Set<String> hashSet = new HashSet();
        hashSet.addAll(set);
        Set<String> hashSet2 = new HashSet();
        hashSet2.addAll(set2);
        for (String str : hashSet) {
            SongV2 a = StoreManager.m18378a().m18416a(str);
            if (a != null) {
                ListingV2 e = StoreManager.m18378a().m18428e(a.songId);
                if (e == null) {
                    Log.e(f17852a, "Couldn't find listings for entitlement, creating it: " + str);
                    arrayList.add(SongbookEntry.m18748a(a));
                } else {
                    arrayList.add(SongbookEntry.m18746a(e));
                }
                Log.c(f17852a, "buildOwnedSongsSection - adding " + a.title);
            } else {
                Log.c(f17852a, "Couldn't find song for entitlement " + str + " from store listings!");
            }
        }
        for (String str2 : hashSet2) {
            ArrangementVersionLite d = ArrangementManager.a().d(str2);
            if (d == null) {
                Log.c(f17852a, "Arrangement " + str2 + " is owned, but its details could not be found!");
            } else {
                arrayList.add(SongbookEntry.m18744a(d));
            }
        }
        if (arrayList.size() > 0) {
            Collections.sort(arrayList, new SongbookEntryComparatorByDisplayName(this));
        }
        return arrayList;
    }
}
