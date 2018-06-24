/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.Cursor
 *  android.database.MatrixCursor
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.AlphabetIndexer
 *  android.widget.SectionIndexer
 */
package com.smule.singandroid.adapters.songbook;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AlphabetIndexer;
import android.widget.SectionIndexer;
import com.smule.android.logging.Log;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.adapters.songbook.SongbookAmazingAdapter;
import com.smule.singandroid.customviews.SongbookSortSelector;
import com.smule.singandroid.list_items.ListingListItem;
import com.smule.singandroid.models.SongbookListViewState;
import com.smule.singandroid.models.SongbookSection;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class SongbookSongsAdapter
extends SongbookAmazingAdapter {
    private List<View> i;
    protected boolean j = false;
    protected SongbookSection k;
    protected List<SongbookSection> l;
    protected List<com.smule.android.songbook.SongbookEntry> m;
    protected SongbookSortSelector.SortType n;
    protected WeakReference<SongbookAmazingAdapter.DataRefreshListener> o;
    protected SongbookAmazingAdapter.AdapterInterface p;
    protected LayoutInflater q;
    protected SongbookListViewState r;

    public SongbookSongsAdapter(SongbookAmazingAdapter.AdapterInterface adapterInterface) {
        this.p = adapterInterface;
        this.q = (LayoutInflater)adapterInterface.a().getSystemService("layout_inflater");
        this.b(0);
        this.a();
        if (this.v()) {
            this.g();
            this.j = false;
            return;
        }
        this.e();
    }

    public boolean A() {
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void B() {
        void var1_2;
        switch (.a[this.n.ordinal()]) {
            default: {
                Comparator<com.smule.android.songbook.SongbookEntry> comparator = new Comparator<com.smule.android.songbook.SongbookEntry>(){

                    public int a(com.smule.android.songbook.SongbookEntry songbookEntry, com.smule.android.songbook.SongbookEntry songbookEntry2) {
                        return com.smule.android.songbook.SongbookEntry.a(songbookEntry.e()).compareToIgnoreCase(com.smule.android.songbook.SongbookEntry.a(songbookEntry2.e()));
                    }

                    @Override
                    public /* synthetic */ int compare(Object object, Object object2) {
                        return this.a((com.smule.android.songbook.SongbookEntry)object, (com.smule.android.songbook.SongbookEntry)object2);
                    }
                };
                break;
            }
            case 1: {
                Comparator<com.smule.android.songbook.SongbookEntry> comparator = new ;
                break;
            }
            case 2: {
                Comparator<com.smule.android.songbook.SongbookEntry> comparator = new Comparator<com.smule.android.songbook.SongbookEntry>(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    private String a(com.smule.android.songbook.SongbookEntry songbookEntry) {
                        String string2 = songbookEntry.f() == null ? "" : songbookEntry.f();
                        if (!songbookEntry.d) {
                            songbookEntry.c = com.smule.android.songbook.SongbookEntry.a(string2);
                            if (!songbookEntry.c.equals(songbookEntry.f())) {
                                string2 = songbookEntry.c;
                            }
                            songbookEntry.d = true;
                            return string2;
                        } else {
                            if (songbookEntry.c == null) return string2;
                            return songbookEntry.c;
                        }
                    }

                    public int a(com.smule.android.songbook.SongbookEntry songbookEntry, com.smule.android.songbook.SongbookEntry songbookEntry2) {
                        return this.a(songbookEntry).compareToIgnoreCase(this.a(songbookEntry2));
                    }

                    @Override
                    public /* synthetic */ int compare(Object object, Object object2) {
                        return this.a((com.smule.android.songbook.SongbookEntry)object, (com.smule.android.songbook.SongbookEntry)object2);
                    }
                };
                break;
            }
            case 3: {
                Comparator<com.smule.android.songbook.SongbookEntry> comparator = new Comparator<com.smule.android.songbook.SongbookEntry>(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    public int a(com.smule.android.songbook.SongbookEntry songbookEntry, com.smule.android.songbook.SongbookEntry songbookEntry2) {
                        long l = 0;
                        long l2 = songbookEntry instanceof ArrangementVersionLiteEntry ? ((ArrangementVersionLiteEntry)songbookEntry).a.arrCreatedAt : (songbookEntry instanceof com.smule.android.songbook.ListingEntry ? ((com.smule.android.songbook.ListingEntry)songbookEntry).a.liveTs : 0);
                        if (songbookEntry2 instanceof ArrangementVersionLiteEntry) {
                            l = ((ArrangementVersionLiteEntry)songbookEntry2).a.arrCreatedAt;
                        } else if (songbookEntry instanceof com.smule.android.songbook.ListingEntry) {
                            l = ((com.smule.android.songbook.ListingEntry)songbookEntry2).a.liveTs;
                        }
                        if (l2 > l) {
                            return -1;
                        }
                        if (l2 == l) {
                            return 0;
                        }
                        return 1;
                    }

                    @Override
                    public /* synthetic */ int compare(Object object, Object object2) {
                        return this.a((com.smule.android.songbook.SongbookEntry)object, (com.smule.android.songbook.SongbookEntry)object2);
                    }
                };
            }
        }
        if (this.k != null) {
            this.m = new ArrayList<com.smule.android.songbook.SongbookEntry>();
            this.m.addAll(this.k.e);
            if (this.m.size() == 0) {
                HashSet<com.smule.android.songbook.SongbookEntry> hashSet = new HashSet<com.smule.android.songbook.SongbookEntry>();
                Iterator<SongbookSection> iterator = this.k.o.iterator();
                while (iterator.hasNext()) {
                    hashSet.addAll(iterator.next().e);
                }
                this.m.addAll(hashSet);
            }
        }
        Collections.sort(this.m, var1_2);
        this.D();
        this.notifyDataSetChanged();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected AlphabetIndexingCursorInfo C() {
        MatrixCursor matrixCursor = new MatrixCursor(AlphabetCursorColumn.b());
        StringBuilder stringBuilder = new StringBuilder();
        int n = 0;
        int n2 = 125;
        do {
            Object object;
            if (n >= this.m.size()) {
                object = new AlphabetIndexingCursorInfo();
                object.a = matrixCursor;
                object.b = stringBuilder.toString();
                return object;
            }
            object = this.m.get(n);
            object = this.n == SongbookSortSelector.SortType.d ? object.f() : object.e();
            Object object2 = object;
            if (object == null) {
                object2 = "";
            }
            int n3 = (object = com.smule.android.songbook.SongbookEntry.a((String)object2)).equals("") ? 35 : object.charAt(0);
            n3 = Character.isLetter((char)n3) ? (int)Character.toUpperCase((char)n3) : 35;
            if (n3 != n2 || n == 0) {
                stringBuilder.append((char)n3);
                n2 = n3;
            }
            matrixCursor.addRow(new Object[]{n, object});
            ++n;
        } while (true);
    }

    protected void D() {
        if (this.n != SongbookSortSelector.SortType.c && this.n != SongbookSortSelector.SortType.d) {
            this.h = null;
            return;
        }
        AlphabetIndexingCursorInfo alphabetIndexingCursorInfo = this.C();
        this.h = new AlphabetIndexer((Cursor)alphabetIndexingCursorInfo.a, 1, (CharSequence)alphabetIndexingCursorInfo.b);
    }

    protected SongbookAmazingAdapter.DataRefreshListener E() {
        if (this.o != null) {
            return this.o.get();
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public View a(int n, View object, ViewGroup object2, boolean bl) {
        if (object == null || object.getTag(2131755015) == null) {
            object = this.i != null && this.i.size() > 0 ? this.i.remove(0) : ListingListItem.a(this.p.a());
        }
        object2 = (ListingListItem)object;
        com.smule.android.songbook.SongbookEntry songbookEntry = (com.smule.android.songbook.SongbookEntry)this.getItem(n);
        if (songbookEntry == null) {
            object2.setVisibility(4);
            return object;
        } else {
            object2.setVisibility(0);
            boolean bl2 = n - this.getPositionForSection(this.getSectionForPosition(n)) == 0;
            object2.findViewById(2131756274).setVisibility(8);
            if (!bl && songbookEntry.t()) {
                object2.a((ArrangementVersionLiteEntry)songbookEntry, bl2);
            }
            object2.setFastScrollEnabled(this.A());
            this.p.a((ListingListItem)object2, (ArrangementVersionLiteEntry)songbookEntry, n);
            if (!bl) return object;
            {
                this.i.add((View)object);
                return object;
            }
        }
    }

    public void a() {
        super.a();
        this.j = false;
    }

    public void a(View view, int n, int n2, int n3) {
    }

    @Override
    protected void a(View object, int n, int n2, boolean bl) {
        Object object2;
        object = (ListingListItem)object;
        if (bl && this.h != null) {
            String[] arrstring = (String[])this.h.getSections();
            if (arrstring != null && arrstring.length > n) {
                object.setListHeaderText(arrstring[n]);
                object.setShowListHeader(true);
                return;
            }
            object.setShowListHeader(false);
            return;
        }
        if (!bl || this.m != null || this.l == null || this.l.size() < 2) {
            object.setShowListHeader(false);
            return;
        }
        if (n == 0 && ((object2 = this.l.get((int)0).l).equals(this.k.l) || Pattern.compile("^[^\\w]*Top ").matcher((CharSequence)object2).find())) {
            object.setShowListHeader(false);
            return;
        }
        object2 = this.l.get(n);
        object.setShowListHeader(true);
        if (n == this.l.size() - 1) {
            object.setListHeaderText(this.p.a().getString(2131296665));
            return;
        }
        object.setListHeaderText(object2.l);
    }

    @Override
    public void a(SongbookAmazingAdapter.DataRefreshListener dataRefreshListener) {
        this.o = new WeakReference<SongbookAmazingAdapter.DataRefreshListener>(dataRefreshListener);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(SongbookSortSelector.SortType sortType) {
        if (this.n == sortType) return;
        SongbookSortSelector.SortMenuType sortMenuType = this.q();
        Log.b(a, "setSortType - Section: " + this.k.j + " SortMenuType: " + (Object)((Object)sortMenuType) + " SortType: " + (Object)((Object)sortType));
        if (!sortMenuType.a(sortType)) {
            Log.b(a, (Object)((Object)sortMenuType) + " does not support sort type: " + (Object)((Object)sortType));
            return;
        }
        this.n = sortType;
        this.r.a = sortType;
        this.h = null;
        if (this.t()) {
            if (this.m == null) return;
            {
                this.u();
                return;
            }
        }
        if (sortMenuType.b() == sortType && (this.k != null && (this.k.o.size() > 0 || sortMenuType == SongbookSortSelector.SortMenuType.c) || sortMenuType == SongbookSortSelector.SortMenuType.d)) {
            if (this.m == null) {
                return;
            }
            this.u();
            return;
        }
        this.B();
    }

    public void a(SongbookSection songbookSection, SongbookListViewState songbookListViewState) {
        this.k = songbookSection;
        this.l = new ArrayList<SongbookSection>();
        this.l.addAll(songbookSection.o);
        this.l.add(this.k);
        this.m = null;
        this.r = songbookListViewState;
    }

    public void a(List<View> list) {
        this.i = list;
    }

    public void b(String string2) {
        if (!this.v()) {
            throw new IllegalStateException("Do not know how to request more data for song adapter");
        }
    }

    public void c(int n) {
        if (!this.v()) {
            throw new IllegalStateException("Do not know how to request more data for song adapter");
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int getCount() {
        if (this.m == null && this.k == null) {
            return 0;
        }
        if (this.m != null) {
            return this.m.size() + 0;
        }
        if (this.l == null) return 0;
        Iterator<SongbookSection> iterator = this.l.iterator();
        int n = 0;
        do {
            int n2 = n;
            if (!iterator.hasNext()) return n2;
            n = iterator.next().e.size() + n;
        } while (true);
    }

    public Object getItem(int n) {
        if (this.m != null) {
            return this.m.get(n);
        }
        int n2 = this.getSectionForPosition(n);
        return this.l.get((int)n2).e.get(n - this.getPositionForSection(n2));
    }

    public long getItemId(int n) {
        return 0;
    }

    @Override
    public int getPositionForSection(int n) {
        int n2 = 0;
        if (this.m != null) {
            if (this.h != null) {
                n2 = this.h.getPositionForSection(n);
            }
            return n2;
        }
        int n3 = 0;
        for (n2 = 0; n2 < n; ++n2) {
            n3 += this.l.get((int)n2).e.size();
        }
        return n3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public int getSectionForPosition(int n) {
        int n2 = 0;
        if (this.m != null) {
            if (this.h == null) return n2;
            try {
                return this.h.getSectionForPosition(n);
            }
            catch (Exception exception) {
                return 0;
            }
        }
        int n3 = 0;
        n2 = 0;
        do {
            if (n2 >= this.l.size()) {
                Log.e(a, "getSectionForPosition - Didn't find section");
                return -1;
            }
            n3 = this.l.get((int)n2).e.size() + n3;
            if (n3 > n) {
                return n2;
            }
            ++n2;
        } while (true);
    }

    public Object[] getSections() {
        if (this.h != null && this.h instanceof AlphabetIndexer) {
            return this.h.getSections();
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean isEmpty() {
        if (this.m != null) {
            if (this.m.size() == 0) return true;
            return false;
        }
        if (this.k.e.size() != 0) return false;
        return true;
    }

    @Override
    public boolean l() {
        return this.v();
    }

    @Override
    public void m() {
    }

    @Override
    public SongbookAmazingAdapter.AdapterInterface n() {
        return this.p;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        Log.b(a, "SongbookSongsAdapter - notifyDataSetChanged");
    }

    @Override
    public SongbookSection o() {
        return this.k;
    }

    @Override
    public SongbookListViewState p() {
        return this.r;
    }

    @Override
    public SongbookSortSelector.SortMenuType q() {
        return SongbookSortSelector.a(this.k);
    }

    public boolean t() {
        return this.v();
    }

    protected void u() {
        this.m = null;
        if (this.v()) {
            this.a();
            this.c(1);
        }
        this.notifyDataSetChanged();
    }

    public boolean v() {
        return false;
    }

    public boolean z() {
        return this.j;
    }

    public static enum AlphabetCursorColumn {
        a("_id"),
        b("text");
        
        private final String c;

        private AlphabetCursorColumn(String string3) {
            this.c = string3;
        }

        public static String[] b() {
            return new String[]{a.a(), b.a()};
        }

        public String a() {
            return this.c;
        }
    }

    protected class AlphabetIndexingCursorInfo {
        public MatrixCursor a;
        public String b;

        protected AlphabetIndexingCursorInfo() {
        }
    }

}

