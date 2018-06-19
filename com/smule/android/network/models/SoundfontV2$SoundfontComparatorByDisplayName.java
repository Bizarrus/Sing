package com.smule.android.network.models;

import java.util.Comparator;

public class SoundfontV2$SoundfontComparatorByDisplayName implements Comparator<SoundfontV2> {
    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m18591a((SoundfontV2) obj, (SoundfontV2) obj2);
    }

    public int m18591a(SoundfontV2 soundfontV2, SoundfontV2 soundfontV22) {
        return soundfontV2.name.replaceAll("\\p{Punct}", "").toLowerCase().compareTo(soundfontV22.name.replaceAll("\\p{Punct}", "").toLowerCase());
    }
}
