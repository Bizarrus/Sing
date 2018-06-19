package com.smule.android.network.models;

import java.util.Comparator;

public class SongV2$SongComparatorByDisplayName implements Comparator<SongV2> {
    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m18588a((SongV2) obj, (SongV2) obj2);
    }

    public int m18588a(SongV2 songV2, SongV2 songV22) {
        return songV2.title.replaceAll("\\p{Punct}", "").toLowerCase().compareTo(songV22.title.replaceAll("\\p{Punct}", "").toLowerCase());
    }
}
