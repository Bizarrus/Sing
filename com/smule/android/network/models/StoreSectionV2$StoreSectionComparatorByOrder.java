package com.smule.android.network.models;

import java.util.Comparator;

public class StoreSectionV2$StoreSectionComparatorByOrder implements Comparator<StoreSectionV2> {
    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m18594a((StoreSectionV2) obj, (StoreSectionV2) obj2);
    }

    public int m18594a(StoreSectionV2 storeSectionV2, StoreSectionV2 storeSectionV22) {
        if (storeSectionV2.order != storeSectionV22.order) {
            return storeSectionV2.order - storeSectionV22.order;
        }
        if (storeSectionV2.sectionId.equals("my_songs")) {
            return -1;
        }
        return 1;
    }
}
