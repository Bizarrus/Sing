/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.SectionIndexer
 *  com.foound.widget.AmazingAdapter
 */
package com.smule.singandroid.adapters.songbook;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;
import com.foound.widget.AmazingAdapter;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.singandroid.customviews.SongbookSortSelector;
import com.smule.singandroid.list_items.ListingListItem;
import com.smule.singandroid.models.SongbookListViewState;
import com.smule.singandroid.models.SongbookSection;

public abstract class SongbookAmazingAdapter
extends AmazingAdapter {
    protected SectionIndexer h;

    public View a(int n, View view, ViewGroup viewGroup) {
        return this.a(n, view, viewGroup, false);
    }

    public abstract View a(int var1, View var2, ViewGroup var3, boolean var4);

    protected abstract void a(View var1, int var2, int var3, boolean var4);

    public abstract void a(DataRefreshListener var1);

    public abstract void a(SongbookSortSelector.SortType var1);

    public abstract int getPositionForSection(int var1);

    public abstract int getSectionForPosition(int var1);

    public abstract boolean l();

    public abstract void m();

    public abstract AdapterInterface n();

    public abstract SongbookSection o();

    public abstract SongbookListViewState p();

    public abstract SongbookSortSelector.SortMenuType q();

    public boolean r() {
        return false;
    }

    public abstract Long s();

    public static interface AdapterInterface {
        public Context a();

        public void a(ListingListItem var1, ArrangementVersionLiteEntry var2, int var3);
    }

    public static interface DataRefreshListener {
        public void a();

        public void b();

        public void c();
    }

}

