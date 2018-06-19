package com.smule.android.magicui.lists;

import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;

class MagicListViewAdapterWrapper implements Filterable, ListAdapter {
    private static final String f16395a = MagicListViewAdapterWrapper.class.getName();
    private final MagicAdapter f16396b;

    public MagicListViewAdapterWrapper(@NonNull MagicAdapter magicAdapter) {
        this.f16396b = magicAdapter;
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.f16396b.m18028a(dataSetObserver);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.f16396b.m18040b(dataSetObserver);
    }

    public int getCount() {
        int i = 1;
        MagicDataSource a = this.f16396b.m18026a();
        switch (a.m17659i()) {
            case HAS_DATA:
                int d = this.f16396b.m18048d();
                if (!a.m17662l()) {
                    i = 0;
                }
                return i + d;
            case LOADING_FIRST_PAGE:
            case LOADING_FIRST_PAGE_FAILED:
            case FIRST_PAGE_EMPTY:
                return 1;
            default:
                return 0;
        }
    }

    public Object getItem(int i) {
        switch (this.f16396b.m18026a().m17659i()) {
            case LOADING_FIRST_PAGE:
            case LOADING_FIRST_PAGE_FAILED:
            case FIRST_PAGE_EMPTY:
            case NONE:
                return null;
            default:
                if (m18013a(i)) {
                    return null;
                }
                return this.f16396b.m18027a(i);
        }
    }

    public boolean hasStableIds() {
        return true;
    }

    public long getItemId(int i) {
        switch (this.f16396b.m18026a().m17659i()) {
            case LOADING_FIRST_PAGE:
            case LOADING_FIRST_PAGE_FAILED:
            case FIRST_PAGE_EMPTY:
            case NONE:
                return -1;
            default:
                if (m18013a(i)) {
                    return -1;
                }
                return this.f16396b.mo6785b(i);
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = null;
        switch (this.f16396b.m18026a().m17659i()) {
            case LOADING_FIRST_PAGE:
                view2 = this.f16396b.mo6464b(viewGroup);
                break;
            case LOADING_FIRST_PAGE_FAILED:
                view2 = this.f16396b.mo6459c(viewGroup);
                break;
            case FIRST_PAGE_EMPTY:
                view2 = this.f16396b.mo6460d(viewGroup);
                break;
            case NONE:
                throw new RuntimeException("Shouldn't be trying to get a view for DataState.NONE");
        }
        if (view2 != null) {
            if (!this.f16396b.mo6799a(view2)) {
                int measuredHeight = viewGroup.getMeasuredHeight();
                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                    measuredHeight -= viewGroup.getChildAt(i2).getMeasuredHeight();
                }
                view2.setLayoutParams(new LayoutParams(-1, measuredHeight));
            }
            return view2;
        } else if (m18013a(i)) {
            return this.f16396b.mo6857a(viewGroup);
        } else {
            return this.f16396b.m18025a(viewGroup, view, i);
        }
    }

    public int getViewTypeCount() {
        return this.f16396b.mo6442e() + 1;
    }

    public int getItemViewType(int i) {
        switch (this.f16396b.m18026a().m17659i()) {
            case LOADING_FIRST_PAGE:
            case LOADING_FIRST_PAGE_FAILED:
            case FIRST_PAGE_EMPTY:
            case NONE:
                return -1;
            default:
                if (m18013a(i)) {
                    return -2;
                }
                return this.f16396b.mo6441c(i);
        }
    }

    protected boolean m18013a(int i) {
        return i == this.f16396b.m18048d();
    }

    public boolean isEmpty() {
        return false;
    }

    public Filter getFilter() {
        if (this.f16396b instanceof Filterable) {
            return ((Filterable) this.f16396b).getFilter();
        }
        return null;
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    public boolean isEnabled(int i) {
        return false;
    }
}
