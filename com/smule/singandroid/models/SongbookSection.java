package com.smule.singandroid.models;

import com.smule.android.network.models.ListingV2;
import com.smule.android.network.models.StoreSectionV2;
import com.smule.android.songbook.SongbookEntry;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SongbookSection {
    public List<SongbookEntry> f23512a = new ArrayList();
    public Set<String> f23513b = new HashSet();
    public String f23514c;
    public String f23515d;
    public int f23516e;
    public int f23517f;
    public List<SongbookSection> f23518g = new ArrayList();

    public static SongbookSection m24756a(StoreSectionV2 storeSectionV2) {
        SongbookSection songbookSection = new SongbookSection();
        for (ListingV2 a : storeSectionV2.listings) {
            songbookSection.f23512a.add(SongbookEntry.m18746a(a));
        }
        songbookSection.f23514c = storeSectionV2.sectionId;
        songbookSection.f23515d = storeSectionV2.displayName;
        songbookSection.f23516e = storeSectionV2.order;
        if (storeSectionV2.subSections != null) {
            for (StoreSectionV2 a2 : storeSectionV2.subSections) {
                songbookSection.f23518g.add(m24756a(a2));
            }
        }
        return songbookSection;
    }

    public void m24757a() {
        for (SongbookEntry c : this.f23512a) {
            this.f23513b.add(c.mo6289c());
        }
    }
}
