package com.smule.android.network.models;

import com.smule.android.logging.Log;
import java.util.Comparator;

public class ListingV2$ListingComparatorByDisplayName implements Comparator<ListingV2> {
    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m18561a((ListingV2) obj, (ListingV2) obj2);
    }

    public int m18561a(ListingV2 listingV2, ListingV2 listingV22) {
        if (listingV2 != null && listingV22 != null && listingV2.song != null && listingV22.song != null) {
            return listingV2.song.title.replaceAll("\\p{Punct}", "").toLowerCase().compareTo(listingV22.song.title.replaceAll("\\p{Punct}", "").toLowerCase());
        }
        Log.d(ListingV2.b(), "ListingComparatorByDisplayName - null elements passed in. Returning 0.");
        return 0;
    }
}
