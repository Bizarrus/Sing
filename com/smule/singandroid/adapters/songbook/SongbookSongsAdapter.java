package com.smule.singandroid.adapters.songbook;

import android.database.MatrixCursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AlphabetIndexer;
import com.smule.android.logging.Log;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.ListingEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.songbook.SongbookEntry.SortByArtistAlphaComparator;
import com.smule.android.songbook.SongbookEntry.SortByReleaseDateComparator;
import com.smule.android.songbook.SongbookEntry.SortByTitleAlphaComparator;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.adapters.songbook.SongbookAmazingAdapter.AdapterInterface;
import com.smule.singandroid.adapters.songbook.SongbookAmazingAdapter.DataRefreshListener;
import com.smule.singandroid.customviews.SongbookSortSelector;
import com.smule.singandroid.customviews.SongbookSortSelector.SortMenuType;
import com.smule.singandroid.customviews.SongbookSortSelector.SortType;
import com.smule.singandroid.list_items.ListingListItem;
import com.smule.singandroid.models.SongbookListViewState;
import com.smule.singandroid.models.SongbookSection;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

public class SongbookSongsAdapter extends SongbookAmazingAdapter {
    private List<View> f20587f;
    protected boolean f20588i = false;
    protected SongbookSection f20589j;
    protected List<SongbookSection> f20590k;
    protected List<SongbookEntry> f20591l;
    protected SortType f20592m;
    protected WeakReference<DataRefreshListener> f20593n;
    protected AdapterInterface f20594o;
    protected LayoutInflater f20595p;
    protected SongbookListViewState f20596q;

    public enum AlphabetCursorColumn {
        ID("_id"),
        TEXT("text");
        
        private final String f20610c;

        private AlphabetCursorColumn(String str) {
            this.f20610c = str;
        }

        public String m22211a() {
            return this.f20610c;
        }

        public static String[] m22210b() {
            return new String[]{ID.m22211a(), TEXT.m22211a()};
        }
    }

    protected class AlphabetIndexingCursorInfo {
        public MatrixCursor f20611a;
        public String f20612b;
        final /* synthetic */ SongbookSongsAdapter f20613c;

        protected AlphabetIndexingCursorInfo(SongbookSongsAdapter songbookSongsAdapter) {
            this.f20613c = songbookSongsAdapter;
        }
    }

    public SongbookSongsAdapter(AdapterInterface adapterInterface) {
        this.f20594o = adapterInterface;
        this.f20595p = (LayoutInflater) adapterInterface.mo6659a().getSystemService("layout_inflater");
        b(0);
        m22172a();
        if (mo6707s()) {
            f();
            this.f20588i = false;
            return;
        }
        e();
    }

    public AdapterInterface mo6700l() {
        return this.f20594o;
    }

    public SongbookSection mo6701m() {
        return this.f20589j;
    }

    public void m22172a() {
        super.a();
        this.f20588i = false;
    }

    public boolean mo6707s() {
        return false;
    }

    public boolean mo6712w() {
        return mo6707s();
    }

    public boolean mo6699k() {
        return true;
    }

    public SortMenuType mo6704p() {
        return SongbookSortSelector.m23493a(this.f20589j);
    }

    public SongbookListViewState mo6703o() {
        return this.f20596q;
    }

    public void mo6694a(SortType sortType) {
        if (this.f20592m != sortType) {
            SortMenuType p = mo6704p();
            Log.b(a, "setSortType - Section: " + this.f20589j.f23514c + " SortMenuType: " + p + " SortType: " + sortType);
            if (p.m23488a(sortType)) {
                this.f20592m = sortType;
                this.f20596q.f23509a = sortType;
                this.e = null;
                if (mo6712w()) {
                    if (this.f20591l != null) {
                        mo6710r();
                        return;
                    }
                    return;
                } else if (p.m23489b() != sortType || ((this.f20589j == null || (this.f20589j.f23518g.size() <= 0 && p != SortMenuType.RECOMMENDED)) && p != SortMenuType.TRENDING)) {
                    mo6702n();
                    return;
                } else if (this.f20591l != null) {
                    mo6710r();
                    return;
                } else {
                    return;
                }
            }
            Log.b(a, p + " does not support sort type: " + sortType);
        }
    }

    public void mo6702n() {
        Comparator sortByTitleAlphaComparator;
        Object sortByTitleAlphaComparator2;
        switch (this.f20592m) {
            case SONGS_ALPHA:
                sortByTitleAlphaComparator2 = new SortByTitleAlphaComparator();
                break;
            case ARTISTS_ALPHA:
                sortByTitleAlphaComparator2 = new SortByArtistAlphaComparator();
                break;
            case MOST_RECENT:
                sortByTitleAlphaComparator2 = new SortByReleaseDateComparator();
                break;
            default:
                sortByTitleAlphaComparator = new SortByTitleAlphaComparator();
                break;
        }
        if (this.f20589j != null) {
            this.f20591l = new ArrayList();
            this.f20591l.addAll(this.f20589j.f23512a);
            if (this.f20591l.size() == 0) {
                Collection hashSet = new HashSet();
                for (SongbookSection songbookSection : this.f20589j.f23518g) {
                    hashSet.addAll(songbookSection.f23512a);
                }
                this.f20591l.addAll(hashSet);
            }
        }
        Collections.sort(this.f20591l, sortByTitleAlphaComparator);
        m22192y();
        notifyDataSetChanged();
    }

    protected AlphabetIndexingCursorInfo m22191x() {
        MatrixCursor matrixCursor = new MatrixCursor(AlphabetCursorColumn.m22210b());
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        char c = '}';
        while (i < this.f20591l.size()) {
            char c2;
            SongbookEntry songbookEntry = (SongbookEntry) this.f20591l.get(i);
            String f = this.f20592m == SortType.ARTISTS_ALPHA ? songbookEntry.mo6292f() : songbookEntry.mo6291e();
            if (f == null) {
                f = "";
            }
            String b = SongbookEntry.m18753b(f);
            if (b.equals("")) {
                c2 = '#';
            } else {
                c2 = b.charAt(0);
            }
            if (Character.isLetter(c2)) {
                c2 = Character.toUpperCase(c2);
            } else {
                c2 = '#';
            }
            if (c2 != c || i == 0) {
                stringBuilder.append(c2);
                c = c2;
            }
            matrixCursor.addRow(new Object[]{Integer.valueOf(i), b});
            i++;
        }
        AlphabetIndexingCursorInfo alphabetIndexingCursorInfo = new AlphabetIndexingCursorInfo(this);
        alphabetIndexingCursorInfo.f20611a = matrixCursor;
        alphabetIndexingCursorInfo.f20612b = stringBuilder.toString();
        return alphabetIndexingCursorInfo;
    }

    protected void m22192y() {
        if (this.f20592m == SortType.SONGS_ALPHA || this.f20592m == SortType.ARTISTS_ALPHA) {
            AlphabetIndexingCursorInfo x = m22191x();
            this.e = new AlphabetIndexer(x.f20611a, 1, x.f20612b);
            return;
        }
        this.e = null;
    }

    public void m22177a(SongbookSection songbookSection, SongbookListViewState songbookListViewState) {
        this.f20589j = songbookSection;
        this.f20590k = new ArrayList();
        this.f20590k.addAll(songbookSection.f23518g);
        this.f20590k.add(this.f20589j);
        this.f20591l = null;
        this.f20596q = songbookListViewState;
    }

    public void mo6693a(DataRefreshListener dataRefreshListener) {
        this.f20593n = new WeakReference(dataRefreshListener);
    }

    protected DataRefreshListener m22193z() {
        if (this.f20593n != null) {
            return (DataRefreshListener) this.f20593n.get();
        }
        return null;
    }

    protected void mo6710r() {
        this.f20591l = null;
        if (mo6707s()) {
            m22172a();
            mo6706c(1);
        }
        notifyDataSetChanged();
    }

    public void mo6706c(int i) {
        if (!mo6707s()) {
            throw new IllegalStateException("Do not know how to request more data for song adapter");
        }
    }

    public boolean mo6697i() {
        return mo6707s();
    }

    public void mo6698j() {
    }

    public int getPositionForSection(int i) {
        if (this.f20591l == null) {
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                i2 += ((SongbookSection) this.f20590k.get(i3)).f23512a.size();
            }
            return i2;
        } else if (this.e != null) {
            return this.e.getPositionForSection(i);
        } else {
            return 0;
        }
    }

    public int getSectionForPosition(int i) {
        int i2 = 0;
        if (this.f20591l == null) {
            int i3 = i2;
            int i4 = i2;
            while (i4 < this.f20590k.size()) {
                i2 = ((SongbookSection) this.f20590k.get(i4)).f23512a.size() + i3;
                if (i2 > i) {
                    return i4;
                }
                i4++;
                i3 = i2;
            }
            Log.e(a, "getSectionForPosition - Didn't find section");
            return -1;
        } else if (this.e == null) {
            return i2;
        } else {
            try {
                return this.e.getSectionForPosition(i);
            } catch (Exception e) {
                return i2;
            }
        }
    }

    public Object[] getSections() {
        if (this.e == null || !(this.e instanceof AlphabetIndexer)) {
            return null;
        }
        return this.e.getSections();
    }

    public boolean isEmpty() {
        if (this.f20591l != null) {
            if (this.f20591l.size() == 0) {
                return true;
            }
            return false;
        } else if (this.f20589j.f23512a.size() != 0) {
            return false;
        } else {
            return true;
        }
    }

    public int getCount() {
        if (this.f20591l == null && this.f20589j == null) {
            return 0;
        }
        int size;
        if (this.f20591l != null) {
            size = this.f20591l.size() + 0;
        } else if (this.f20590k != null) {
            size = 0;
            for (SongbookSection songbookSection : this.f20590k) {
                size = songbookSection.f23512a.size() + size;
            }
        } else {
            size = 0;
        }
        return size;
    }

    public Object getItem(int i) {
        if (this.f20591l != null) {
            return this.f20591l.get(i);
        }
        int sectionForPosition = getSectionForPosition(i);
        return ((SongbookSection) this.f20590k.get(sectionForPosition)).f23512a.get(i - getPositionForSection(sectionForPosition));
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        Log.b(a, "SongbookSongsAdapter - notifyDataSetChanged");
    }

    public long getItemId(int i) {
        return 0;
    }

    public void m22178b(List<View> list) {
        this.f20587f = list;
    }

    public View mo6691a(int i, View view, ViewGroup viewGroup, boolean z) {
        View view2;
        if (view != null && view.getTag(C1947R.id.listing_list_item_tag) != null) {
            view2 = view;
        } else if (this.f20587f == null || this.f20587f.size() <= 0) {
            view2 = ListingListItem.m24377a(this.f20594o.mo6659a());
        } else {
            view2 = (View) this.f20587f.remove(0);
        }
        ListingListItem listingListItem = (ListingListItem) view2;
        SongbookEntry songbookEntry = (SongbookEntry) getItem(i);
        if (songbookEntry == null) {
            listingListItem.setVisibility(4);
        } else {
            listingListItem.setVisibility(0);
            boolean z2 = i - getPositionForSection(getSectionForPosition(i)) == 0;
            listingListItem.findViewById(C1947R.id.album_art_progress_spinner).setVisibility(8);
            if (!z) {
                if (songbookEntry.m18772r()) {
                    listingListItem.m24378a((ArrangementVersionLiteEntry) songbookEntry, z2);
                } else {
                    listingListItem.m24379a((ListingEntry) songbookEntry, z2);
                }
            }
            listingListItem.setFastScrollEnabled(mo6699k());
            this.f20594o.mo6660a(listingListItem, songbookEntry, i);
            if (z) {
                this.f20587f.add(view2);
            }
        }
        return view2;
    }

    protected void mo6692a(View view, int i, int i2, boolean z) {
        ListingListItem listingListItem = (ListingListItem) view;
        if (z && this.e != null) {
            String[] strArr = (String[]) this.e.getSections();
            if (strArr == null || strArr.length <= i) {
                listingListItem.setShowListHeader(false);
                return;
            }
            listingListItem.setListHeaderText(strArr[i]);
            listingListItem.setShowListHeader(true);
        } else if (!z || this.f20591l != null || this.f20590k == null || this.f20590k.size() < 2) {
            listingListItem.setShowListHeader(false);
        } else {
            if (i == 0) {
                CharSequence charSequence = ((SongbookSection) this.f20590k.get(0)).f23515d;
                if (charSequence.equals(this.f20589j.f23515d) || Pattern.compile("^[^\\w]*Top ").matcher(charSequence).find()) {
                    listingListItem.setShowListHeader(false);
                    return;
                }
            }
            SongbookSection songbookSection = (SongbookSection) this.f20590k.get(i);
            listingListItem.setShowListHeader(true);
            if (i == this.f20590k.size() - 1) {
                listingListItem.setListHeaderText(this.f20594o.mo6659a().getString(C1947R.string.core_all));
            } else {
                listingListItem.setListHeaderText(songbookSection.f23515d);
            }
        }
    }

    public void m22173a(View view, int i, int i2, int i3) {
    }
}
