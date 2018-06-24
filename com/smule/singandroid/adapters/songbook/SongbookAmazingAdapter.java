package com.smule.singandroid.adapters.songbook;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;
import com.foound.widget.AmazingAdapter;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.customviews.SongbookSortSelector.SortMenuType;
import com.smule.singandroid.customviews.SongbookSortSelector.SortType;
import com.smule.singandroid.list_items.ListingListItem;
import com.smule.singandroid.models.SongbookListViewState;
import com.smule.singandroid.models.SongbookSection;

public abstract class SongbookAmazingAdapter extends AmazingAdapter {
    protected SectionIndexer f20559e;

    public interface AdapterInterface {
        Context mo6659a();

        void mo6660a(ListingListItem listingListItem, SongbookEntry songbookEntry, int i);
    }

    public interface DataRefreshListener {
        void mo6882a();

        void mo6883b();

        void mo6884c();
    }

    public abstract View mo6691a(int i, View view, ViewGroup viewGroup, boolean z);

    protected abstract void mo6692a(View view, int i, int i2, boolean z);

    public abstract void mo6693a(DataRefreshListener dataRefreshListener);

    public abstract void mo6694a(SortType sortType);

    public abstract int getPositionForSection(int i);

    public abstract int getSectionForPosition(int i);

    public abstract boolean mo6697i();

    public abstract void mo6698j();

    public abstract boolean mo6699k();

    public abstract AdapterInterface mo6700l();

    public abstract SongbookSection mo6701m();

    public abstract void mo6702n();

    public abstract SongbookListViewState mo6703o();

    public abstract SortMenuType mo6704p();

    public boolean mo6705q() {
        return false;
    }

    public View m22125a(int i, View view, ViewGroup viewGroup) {
        return mo6691a(i, view, viewGroup, false);
    }
}
