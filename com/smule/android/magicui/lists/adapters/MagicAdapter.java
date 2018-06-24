/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.content.Context
 *  android.database.DataSetObservable
 *  android.database.DataSetObserver
 *  android.os.Parcelable
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.SectionIndexer
 *  android.widget.TextView
 */
package com.smule.android.magicui.lists.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSourceObservable;
import com.smule.android.utils.ThreadUtils;
import java.util.List;

@SuppressLint(value={"InflateParams"})
public abstract class MagicAdapter
implements AbsListView.OnScrollListener,
SectionIndexer,
MagicDataSource.DataSourceObserver {
    private static final String c = MagicAdapter.class.getName();
    public final int a = 1;
    protected final DataSetObservable b = new DataSetObservable();
    private MagicDataSource d;
    private String e = "";
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private boolean k = false;
    private MagicDataSourceObservable l = new MagicDataSourceObservable();
    private Parcelable m;

    /*
     * Enabled aggressive block sorting
     */
    public MagicAdapter(MagicDataSource magicDataSource) {
        this.d = magicDataSource;
        this.d.a(this);
        if (this.d.i() != MagicDataSource.DataState.c) {
            if (this.d.k() == 0 && this.d.l()) {
                this.d.o();
            } else {
                this.b();
            }
        }
        this.k = true;
    }

    public View a(ViewGroup viewGroup) {
        if (this.f == 0) {
            throw new RuntimeException("Must define theme's magicListViewStyle and MagicListView's loadingFooter attr in styles.xml or overload createLoadingFooterView");
        }
        return LayoutInflater.from((Context)viewGroup.getContext()).inflate(this.f, null);
    }

    public abstract View a(ViewGroup var1, int var2);

    /*
     * Enabled aggressive block sorting
     */
    public final View a(ViewGroup viewGroup, View view, int n) {
        this.d.b(n);
        int n2 = this.c(n);
        View view2 = view;
        if (view == null) {
            view2 = this.a(viewGroup, n2);
        }
        if (view2 != null) {
            this.a(view2, n, n2);
            boolean bl = this.getPositionForSection(this.getSectionForPosition(n)) == n;
            this.a(view2, n, bl);
        }
        return view2;
    }

    public MagicDataSource a() {
        return this.d;
    }

    public Object a(int n) {
        return this.d.a(n);
    }

    public void a(DataSetObserver dataSetObserver) {
        this.b.registerObserver((Object)dataSetObserver);
    }

    public void a(Parcelable parcelable) {
        this.m = parcelable;
    }

    public abstract void a(View var1, int var2, int var3);

    protected void a(View view, int n, boolean bl) {
    }

    public void a(MagicDataSource.DataSourceObserver dataSourceObserver) {
        this.l.registerObserver((Object)dataSourceObserver);
    }

    @Override
    public void a(MagicDataSource magicDataSource) {
        this.l.a(magicDataSource);
    }

    @Override
    public void a(MagicDataSource magicDataSource, List<Object> list) {
        this.l.a(magicDataSource, list);
    }

    protected void a(String string2) {
        this.e = string2;
    }

    public boolean a(View view) {
        return false;
    }

    public long b(int n) {
        return n;
    }

    public View b(ViewGroup viewGroup) {
        if (this.g == 0) {
            throw new RuntimeException("Must define theme's magicListViewStyle and MagicListView's loadingView attr in styles.xml or overload createLoadingFullPageView");
        }
        return LayoutInflater.from((Context)viewGroup.getContext()).inflate(this.g, null);
    }

    public void b() {
        this.l.c(this.a());
    }

    public void b(DataSetObserver dataSetObserver) {
        this.b.unregisterObserver((Object)dataSetObserver);
    }

    public void b(View view, int n, int n2) {
    }

    public void b(MagicDataSource.DataSourceObserver dataSourceObserver) {
        this.l.unregisterObserver((Object)dataSourceObserver);
    }

    @Override
    public void b(MagicDataSource magicDataSource) {
        this.l.b(magicDataSource);
    }

    public int c(int n) {
        return 1;
    }

    public View c(ViewGroup viewGroup) {
        if (this.h == 0) {
            throw new RuntimeException("Must define theme's magicListViewStyle and MagicListView's networkView attr in styles.xml or overload createLoadFailedFullPageView");
        }
        return LayoutInflater.from((Context)viewGroup.getContext()).inflate(this.h, null);
    }

    @Override
    public void c(MagicDataSource magicDataSource) {
        ThreadUtils.a();
        if (!this.k) {
            throw new RuntimeException(this.getClass().getName() + ": DataSource was not setup yet. This should not get called before the constructor is done.");
        }
        this.l.c(magicDataSource);
        this.b.notifyChanged();
    }

    public final boolean c() {
        return this.d.l();
    }

    public int d() {
        return this.d.k();
    }

    public View d(ViewGroup viewGroup) {
        if (this.i == 0) {
            throw new RuntimeException("Must define theme's magicListViewStyle and MagicListView's emptyView attr in styles.xml");
        }
        if (this.j == 0) {
            throw new RuntimeException("Must define theme's magicListViewStyle and MagicListView's emptyText attr in styles.xml");
        }
        TextView textView = (TextView)(viewGroup = LayoutInflater.from((Context)viewGroup.getContext()).inflate(this.i, null)).findViewById(this.j);
        if (textView != null) {
            textView.setText((CharSequence)this.e);
        }
        return viewGroup;
    }

    public void d(int n) {
        this.f = n;
    }

    @Override
    public void d(MagicDataSource magicDataSource) {
        this.l.d(magicDataSource);
    }

    public int e() {
        return 1;
    }

    public void e(int n) {
        this.g = n;
    }

    public void f() {
        this.d.n();
    }

    public void f(int n) {
        this.h = n;
    }

    public Parcelable g() {
        return this.m;
    }

    public void g(int n) {
        this.i = n;
    }

    public int getPositionForSection(int n) {
        return 0;
    }

    public int getSectionForPosition(int n) {
        return 0;
    }

    public Object[] getSections() {
        return new Object[0];
    }

    public void h(int n) {
        this.j = n;
    }

    public int i(int n) {
        if (n < 0 || this.d() == 0) {
            return 0;
        }
        int n2 = this.getPositionForSection(this.getSectionForPosition(n) + 1);
        if (n2 != -1 && n == n2 - 1) {
            return 2;
        }
        return 1;
    }

    public void onScroll(AbsListView absListView, int n, int n2, int n3) {
        if (absListView instanceof MagicListView) {
            ((MagicListView)absListView).a(n);
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int n) {
    }
}

