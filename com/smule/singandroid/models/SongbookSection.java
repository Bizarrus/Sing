/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.models;

import com.smule.android.logging.Log;
import com.smule.android.network.managers.SongbookManager;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.songbook.RecArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SongbookSection {
    public static int a;
    public static int b;
    public static int c;
    public static long d;
    private static final String p;
    public List<SongbookEntry> e = new ArrayList<SongbookEntry>();
    public String f;
    public boolean g;
    public long h;
    public Set<String> i = new HashSet<String>();
    public String j;
    public Long k;
    public String l;
    public int m;
    public int n;
    public List<SongbookSection> o = new ArrayList<SongbookSection>();

    static {
        p = SongbookSection.class.getSimpleName();
        a = 9996;
        b = 9997;
        c = 9998;
        d = 9999;
    }

    public SongbookSection() {
        this.a();
    }

    public void a() {
        if (this.f != null) {
            Log.c(p, "resetting cached data");
        }
        this.e.clear();
        this.f = null;
        this.h = 0;
        this.g = false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(SongbookManager songbookCursor, List<SongbookManager> iterator) {
        if (songbookCursor == null || iterator == null) {
            Log.e(p, "prefetch data is null");
            return;
        }
        if (this.f != null || this.e.size() > 0) {
            Log.d(p, "replacing cached data with prefetched data ");
            this.e.clear();
        }
        iterator = iterator.iterator();
        while (iterator.hasNext()) {
            SongbookManager recArrangementVersionLite = iterator.next();
            this.e.add(new RecArrangementVersionLiteEntry(recArrangementVersionLite.mArrVersionLite, recArrangementVersionLite.mRecId, false));
        }
        if (!songbookCursor.mHasNext.booleanValue()) {
            this.g = true;
        }
        this.f = songbookCursor.mNext == null ? "end" : songbookCursor.mNext;
        this.h = System.currentTimeMillis();
    }
}

