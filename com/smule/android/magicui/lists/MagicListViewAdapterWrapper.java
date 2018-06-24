/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.database.DataSetObserver
 *  android.support.annotation.NonNull
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.AbsListView
 *  android.widget.AbsListView$LayoutParams
 *  android.widget.Filter
 *  android.widget.Filterable
 *  android.widget.ListAdapter
 */
package com.smule.android.magicui.lists;

import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;

class MagicListViewAdapterWrapper
implements Filterable,
ListAdapter {
    private static final String a = MagicListViewAdapterWrapper.class.getName();
    private final MagicAdapter b;

    public MagicListViewAdapterWrapper(@NonNull MagicAdapter magicAdapter) {
        this.b = magicAdapter;
    }

    protected boolean a(int n) {
        if (n == this.b.d()) {
            return true;
        }
        return false;
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int getCount() {
        int n = 1;
        int n2 = 1;
        MagicDataSource magicDataSource = this.b.a();
        switch (.a[magicDataSource.i().ordinal()]) {
            default: {
                n2 = 0;
            }
            case 2: 
            case 3: 
            case 4: {
                return n2;
            }
            case 1: 
        }
        int n3 = this.b.d();
        if (magicDataSource.l()) {
            n2 = n;
            do {
                return n2 + n3;
                break;
            } while (true);
        }
        n2 = 0;
        return n2 + n3;
    }

    public Filter getFilter() {
        if (this.b instanceof Filterable) {
            return ((Filterable)this.b).getFilter();
        }
        return null;
    }

    public Object getItem(int n) {
        MagicDataSource magicDataSource = this.b.a();
        switch (.a[magicDataSource.i().ordinal()]) {
            default: {
                if (!this.a(n)) break;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: {
                return null;
            }
        }
        return this.b.a(n);
    }

    public long getItemId(int n) {
        MagicDataSource magicDataSource = this.b.a();
        switch (.a[magicDataSource.i().ordinal()]) {
            default: {
                if (!this.a(n)) break;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: {
                return -1;
            }
        }
        return this.b.b(n);
    }

    public int getItemViewType(int n) {
        MagicDataSource magicDataSource = this.b.a();
        switch (.a[magicDataSource.i().ordinal()]) {
            default: {
                if (!this.a(n)) break;
                return -2;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: {
                return -1;
            }
        }
        return this.b.c(n);
    }

    /*
     * Enabled aggressive block sorting
     */
    public View getView(int n, View view, ViewGroup viewGroup) {
        MagicDataSource magicDataSource = this.b.a();
        View view2 = null;
        switch (.a[magicDataSource.i().ordinal()]) {
            case 5: {
                throw new RuntimeException("Shouldn't be trying to get a view for DataState.NONE");
            }
            case 2: {
                view2 = this.b.b(viewGroup);
                break;
            }
            case 3: {
                view2 = this.b.c(viewGroup);
                break;
            }
            case 4: {
                view2 = this.b.d(viewGroup);
            }
        }
        if (view2 != null) {
            if (!this.b.a(view2)) {
                int n2 = viewGroup.getMeasuredHeight();
                for (n = 0; n < viewGroup.getChildCount(); n2 -= viewGroup.getChildAt((int)n).getMeasuredHeight(), ++n) {
                }
                view2.setLayoutParams((ViewGroup.LayoutParams)new AbsListView.LayoutParams(-1, n2));
            }
            return view2;
        }
        if (this.a(n)) {
            return this.b.a(viewGroup);
        }
        return this.b.a(viewGroup, view, n);
    }

    public int getViewTypeCount() {
        return this.b.e() + 1;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isEnabled(int n) {
        return false;
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.b.a(dataSetObserver);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.b.b(dataSetObserver);
    }

}

