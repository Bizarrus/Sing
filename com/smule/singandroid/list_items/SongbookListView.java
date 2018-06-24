/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.database.DataSetObserver
 *  android.support.v4.widget.SwipeRefreshLayout
 *  android.support.v4.widget.SwipeRefreshLayout$OnRefreshListener
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.widget.AbsListView
 *  android.widget.AbsListView$LayoutParams
 *  android.widget.LinearLayout
 *  android.widget.ListAdapter
 *  com.foound.widget.AmazingListView
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import com.foound.widget.AmazingListView;
import com.smule.singandroid.adapters.songbook.SongbookAmazingAdapter;
import com.smule.singandroid.customviews.SongbookSortSelector;
import com.smule.singandroid.list_items.SongbookListView_;
import com.smule.singandroid.models.SongbookListViewState;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class SongbookListView
extends LinearLayout
implements SongbookAmazingAdapter.DataRefreshListener {
    private static final String h = SongbookListView.class.getName();
    @ViewById
    public AmazingListView a;
    @ViewById
    SwipeRefreshLayout b;
    SongbookAmazingAdapter c;
    SongbookSortSelector d;
    protected volatile int e;
    protected final Runnable f;
    protected final Runnable g;
    private ViewGroup i;
    private View j = null;
    private DataSetObserver k;
    private int l;
    private View m;
    private int n;

    public SongbookListView(Context context) {
        super(context);
        this.k = new DataSetObserver(){

            public void onChanged() {
                SongbookListView.this.e();
            }
        };
        this.e = 0;
        this.f = new Runnable(){

            @Override
            public void run() {
                SongbookListView songbookListView = SongbookListView.this;
                ++songbookListView.e;
                if (SongbookListView.this.e > 0) {
                    SongbookListView.this.b.setRefreshing(true);
                }
            }
        };
        this.g = new Runnable(){

            @Override
            public void run() {
                SongbookListView songbookListView = SongbookListView.this;
                --songbookListView.e;
                SongbookListView.this.b.setRefreshing(false);
            }
        };
    }

    public static SongbookListView a(Context context) {
        return SongbookListView_.b(context);
    }

    private void f() {
        if (this.m != null) {
            this.a.removeFooterView(this.m);
        }
        this.m = null;
        this.a.setHorizontalScrollBarEnabled(true);
    }

    @Override
    public void a() {
        this.b.post(this.f);
        this.a.removeHeaderView((View)this.i);
        this.i = null;
    }

    public void a(int n) {
        SongbookListViewState songbookListViewState = this.getSongsAdapter().p();
        if (songbookListViewState.b == 0 && n != 0) {
            songbookListViewState.c = n;
        }
        this.a.setSelectionFromTop(songbookListViewState.b, songbookListViewState.c);
    }

    public void a(int n, int n2) {
        this.l = n;
        this.b.setProgressViewOffset(false, this.getResources().getDimensionPixelOffset(2131428206) + n, this.getResources().getDimensionPixelOffset(2131428205) + n);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)this.b.getLayoutParams();
        marginLayoutParams.topMargin = n2;
        this.b.setLayoutParams((ViewGroup.LayoutParams)marginLayoutParams);
        if (n > 0) {
            marginLayoutParams = this.getNewPlaceholderView();
            marginLayoutParams.setLayoutParams((ViewGroup.LayoutParams)new AbsListView.LayoutParams(-1, n));
            this.a.addHeaderView((View)marginLayoutParams);
            this.n = n2;
            this.e();
            return;
        }
        this.a.setHeaderView(null);
        this.a.setHorizontalScrollBarEnabled(true);
        this.n = 0;
    }

    @Override
    public void b() {
        this.b.post(this.g);
        this.b.setEnabled(this.c.l());
    }

    /*
     * Enabled aggressive block sorting
     */
    public void b(int n) {
        if (this.a.getChildCount() == 0 || n == - this.l && this.a.getFirstVisiblePosition() > 0 || this.a.getFirstVisiblePosition() == 0 && this.a.getChildAt(0).getTop() == n) {
            return;
        }
        this.getSongsAdapter().p().a(0, n);
        this.a.setSelectionFromTop(0, n);
    }

    @Override
    public void c() {
        this.b.post(this.g);
        if (this.c.getCount() == 0 && this.i == null) {
            this.i = (ViewGroup)SongbookListView.inflate((Context)this.getContext(), (int)2130903423, (ViewGroup)null);
            this.a.addHeaderView((View)this.i);
        }
    }

    public void c(int n) {
        this.getSongsAdapter().p().a(0, n);
        this.a.setSelectionFromTop(0, n);
    }

    @AfterViews
    void d() {
        this.a.setLoadingView(SongbookListView.inflate((Context)this.getContext(), (int)2130903273, (ViewGroup)null));
        this.d = (SongbookSortSelector)((LayoutInflater)this.getContext().getSystemService("layout_inflater")).inflate(2130903431, (ViewGroup)this.a, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void e() {
        int n;
        block11 : {
            block10 : {
                int n2 = 0;
                if (this.c != null && this.c.n() != null && this.c.n().a() == null) break block10;
                if (this.n == 0 || this.c != null && this.c.getCount() > 18) {
                    this.f();
                    return;
                }
                n = n2;
                if (this.c != null) {
                    n = n2;
                    if (this.c.getCount() > 0) {
                        int n3 = this.getMeasuredHeight();
                        n = 0;
                        for (n2 = 0; n2 < this.c.getCount(); ++n2) {
                            View view = this.c.a(n2, null, (ViewGroup)this.a, true);
                            view.measure(View.MeasureSpec.makeMeasureSpec((int)0, (int)0), View.MeasureSpec.makeMeasureSpec((int)0, (int)0));
                            if ((n += view.getMeasuredHeight()) < n3) continue;
                            this.f();
                            return;
                        }
                    }
                }
                if ((n = this.getMeasuredHeight() - n - this.n) <= 0 && this.m != null) {
                    this.f();
                    return;
                }
                if (n > 0 && (this.m == null || n != this.m.getLayoutParams().height)) break block11;
            }
            return;
        }
        if (this.m == null) {
            this.m = this.getNewPlaceholderView();
        } else {
            this.a.removeFooterView(this.m);
        }
        this.m.setLayoutParams((ViewGroup.LayoutParams)new AbsListView.LayoutParams(-1, n));
        this.a.addFooterView(this.m);
        this.a.setHorizontalScrollBarEnabled(true);
    }

    protected View getNewPlaceholderView() {
        return ((LayoutInflater)this.getContext().getSystemService("layout_inflater")).inflate(2130903272, (ViewGroup)this.a, false);
    }

    public SongbookSortSelector getSongbookSortSelector() {
        return this.d;
    }

    public SongbookAmazingAdapter getSongsAdapter() {
        return this.c;
    }

    protected void onSizeChanged(int n, int n2, int n3, int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        this.e();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setAdapter(SongbookAmazingAdapter songbookAmazingAdapter) {
        if (this.c != null) {
            this.c.unregisterDataSetObserver(this.k);
        }
        this.c = songbookAmazingAdapter;
        if (songbookAmazingAdapter != null) {
            this.c.registerDataSetObserver(this.k);
            this.c.a(this);
            if (this.c.l()) {
                this.b.setColorSchemeResources(new int[]{2131689905});
                this.b.setEnabled(true);
                this.b.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

                    public void onRefresh() {
                        SongbookListView.this.c.m();
                    }
                });
            } else {
                this.b.setEnabled(false);
            }
        }
        this.a.setAdapter((ListAdapter)this.c);
        this.k.onChanged();
    }

    public void setCustomFooter(View view) {
        if (this.j != null) {
            this.a.removeFooterView(this.j);
        }
        this.j = view;
        this.a.addFooterView(view);
    }

}

