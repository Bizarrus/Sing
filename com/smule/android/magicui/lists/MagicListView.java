/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.graphics.Canvas
 *  android.os.Parcelable
 *  android.support.v4.view.ViewCompat
 *  android.support.v4.widget.SwipeRefreshLayout
 *  android.support.v4.widget.SwipeRefreshLayout$OnRefreshListener
 *  android.util.AttributeSet
 *  android.view.View
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.Adapter
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  com.nostra13.universalimageloader.core.ImageLoader
 *  com.nostra13.universalimageloader.core.listener.PauseOnScrollListener
 */
package com.smule.android.magicui.lists;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.smule.android.R;
import com.smule.android.magicui.lists.MagicDataView;
import com.smule.android.magicui.lists.MagicListViewAdapterWrapper;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;

public class MagicListView
extends ListView
implements MagicDataView {
    MagicAdapter a;
    MagicListViewAdapterWrapper b;
    private SwipeRefreshLayout c;
    private View d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private View j;
    private boolean k;
    private int l;
    private int m;
    private AbsListView.OnScrollListener n;
    private MagicDataSource.DataSourceObserver o;

    public MagicListView(Context context) {
        this(context, null);
    }

    public MagicListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.magicListViewStyle);
    }

    public MagicListView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.o = new MagicDataSource.DataSourceObserverObject(){

            @Override
            public void a(MagicDataSource magicDataSource) {
                if (MagicListView.this.c != null) {
                    MagicListView.this.c.setRefreshing(true);
                }
            }

            @Override
            public void b(MagicDataSource magicDataSource) {
                if (MagicListView.this.c != null) {
                    MagicListView.this.c.setRefreshing(false);
                }
            }
        };
        context = context.obtainStyledAttributes(attributeSet, R.styleable.MagicListView, n, 0);
        this.e = context.getResourceId(R.styleable.MagicListView_loadingFooter, 0);
        this.f = context.getResourceId(R.styleable.MagicListView_loadingView, 0);
        this.g = context.getResourceId(R.styleable.MagicListView_networkView, 0);
        this.h = context.getResourceId(R.styleable.MagicListView_emptyView, 0);
        this.i = context.getResourceId(R.styleable.MagicListView_emptyText, 0);
        context.recycle();
        this.a();
    }

    private void a() {
        this.setOnScrollListener((AbsListView.OnScrollListener)new PauseOnScrollListener(ImageLoader.a(), true, true));
    }

    public WrapperFactory a(MagicAdapter magicAdapter) {
        return new WrapperFactory(magicAdapter);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(int n) {
        View view;
        int n2;
        int n3;
        block13 : {
            block12 : {
                n2 = 255;
                if (this.j == null) break block12;
                switch (this.a.i(n)) {
                    default: {
                        return;
                    }
                    case 0: {
                        this.k = false;
                        return;
                    }
                    case 1: {
                        this.a.b(this.j, n, 255);
                        if (this.j.getTop() != 0) {
                            this.j.layout(0, 0, this.l, this.m);
                        }
                        this.k = true;
                        return;
                    }
                    case 2: 
                }
                view = this.getChildAt(0);
                if (view != null) break block13;
            }
            return;
        }
        int n4 = view.getBottom();
        if (n4 < (n3 = this.j.getHeight())) {
            n2 = (n3 + (n4 -= n3)) * 255 / n3;
        } else {
            n4 = 0;
        }
        this.a.b(this.j, n, n2);
        if (this.j.getTop() != n4) {
            this.j.layout(0, n4, this.l, this.m + n4);
        }
        this.k = true;
    }

    public void a(SwipeRefreshLayout swipeRefreshLayout, SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        block5 : {
            block4 : {
                if (this.c != null) {
                    this.c.setOnRefreshListener(null);
                }
                this.c = swipeRefreshLayout;
                if (this.c == null) break block4;
                if (onRefreshListener != null) break block5;
                this.c.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

                    public void onRefresh() {
                        if (MagicListView.this.a != null) {
                            MagicListView.this.a.f();
                        }
                    }
                });
            }
            return;
        }
        this.c.setOnRefreshListener(onRefreshListener);
    }

    protected void a(MagicAdapter magicAdapter, ListAdapter listAdapter) {
        super.setAdapter(listAdapter);
        if (this.a != null && ViewCompat.isAttachedToWindow((View)this)) {
            this.a.b(this.o);
        }
        this.a = magicAdapter;
        this.setOnScrollListener(magicAdapter);
        if (this.a != null) {
            this.a.d(this.e);
            this.a.e(this.f);
            this.a.f(this.g);
            this.a.g(this.h);
            this.a.h(this.i);
            magicAdapter = this.a.g();
            if (magicAdapter != null) {
                this.onRestoreInstanceState((Parcelable)magicAdapter);
                this.a.a((Parcelable)null);
            }
            if (ViewCompat.isAttachedToWindow((View)this)) {
                this.a.a(this.o);
            }
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.k && this.j.getVisibility() == 0) {
            this.drawChild(canvas, this.j, this.getDrawingTime());
        }
    }

    public int getSectionCount() {
        return this.a.getSections().length;
    }

    public MagicAdapter getWrappedAdapter() {
        return this.a;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.a != null) {
            this.a.a(this.o);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null) {
            this.a.b(this.o);
        }
    }

    protected void onLayout(boolean bl, int n, int n2, int n3, int n4) {
        super.onLayout(bl, n, n2, n3, n4);
        if (this.j != null) {
            this.j.layout(0, 0, this.l, this.m);
            this.a(this.getFirstVisiblePosition());
        }
    }

    protected void onMeasure(int n, int n2) {
        super.onMeasure(n, n2);
        if (this.j != null) {
            this.measureChild(this.j, n, n2);
            this.l = this.j.getMeasuredWidth();
            this.m = this.j.getMeasuredHeight();
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable parcelable = super.onSaveInstanceState();
        if (this.a != null) {
            this.a.a(parcelable);
        }
        return parcelable;
    }

    public void setAdapter(ListAdapter listAdapter) {
        throw new RuntimeException("Invalid use case. Please use setMagicAdapter(MagicAdapter) instead.");
    }

    public void setHeaderView(View view) {
        if (this.d != null) {
            this.removeHeaderView(this.d);
        }
        this.d = view;
        if (this.d != null) {
            this.addHeaderView(this.d);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setMagicAdapter(MagicAdapter magicAdapter) {
        this.b = magicAdapter == null ? null : new MagicListViewAdapterWrapper(magicAdapter);
        this.a(magicAdapter, this.b);
    }

    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.n = onScrollListener;
        super.setOnScrollListener(onScrollListener);
    }

    public void setPinnedHeaderView(View view) {
        this.j = view;
        if (this.j != null) {
            this.setFadingEdgeLength(0);
        }
        this.requestLayout();
    }

    public void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.a(swipeRefreshLayout, null);
    }

    public class WrapperFactory {
        private MagicAdapter b;
        private MagicListViewAdapterWrapper c;

        public WrapperFactory(MagicAdapter magicAdapter) {
            this.b = magicAdapter;
            if (magicAdapter == null) {
                this.c = null;
                return;
            }
            this.c = new MagicListViewAdapterWrapper(magicAdapter);
        }

        public ListAdapter a() {
            return this.c;
        }

        public void a(ListAdapter listAdapter) {
            MagicListView.this.a(this.b, listAdapter);
        }
    }

}

