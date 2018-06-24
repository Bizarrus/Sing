/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.utils;

import com.smule.android.logging.Log;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.models.SongV2;
import com.smule.android.songbook.SongbookEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class SongbookSectionUtils {
    private static final String a = SongbookSectionUtils.class.getSimpleName();
    private volatile boolean b;
    private final List<OnFetchedNonListingOwnedSongs> c = new ArrayList<OnFetchedNonListingOwnedSongs>();

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(List<SongbookEntry> list) {
        List<OnFetchedNonListingOwnedSongs> list2 = this.c;
        synchronized (list2) {
            try {
                Iterator<OnFetchedNonListingOwnedSongs> iterator = this.c.iterator();
                while (iterator.hasNext()) {
                    iterator.next().a(list);
                }
            }
            finally {
                this.c.clear();
            }
            return;
        }
    }

    public static interface OnFetchedNonListingOwnedSongs {
        public void a(List<SongbookEntry> var1);
    }

    public class SongbookEntryComparatorByDisplayName
    implements Comparator<SongbookEntry> {
        public int a(SongbookEntry songbookEntry, SongbookEntry songbookEntry2) {
            if (songbookEntry == null || songbookEntry2 == null || songbookEntry.e() == null || songbookEntry2.e() == null) {
                Log.d(a, "ListingComparatorByDisplayName - null elements passed in. Returning 0.");
                return 0;
            }
            return songbookEntry.e().replaceAll("\\p{Punct}", "").toLowerCase().compareTo(songbookEntry2.e().replaceAll("\\p{Punct}", "").toLowerCase());
        }

        @Override
        public /* synthetic */ int compare(Object object, Object object2) {
            return this.a((SongbookEntry)object, (SongbookEntry)object2);
        }
    }

}

