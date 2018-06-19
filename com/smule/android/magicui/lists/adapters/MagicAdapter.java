package com.smule.android.magicui.lists.adapters;

import android.annotation.SuppressLint;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicDataSource.DataSourceObserver;
import com.smule.android.magicui.lists.adapters.MagicDataSource.DataState;
import com.smule.android.utils.ThreadUtils;
import java.util.List;

@SuppressLint({"InflateParams"})
public abstract class MagicAdapter implements OnScrollListener, SectionIndexer, DataSourceObserver {
    private static final String f16412c = MagicAdapter.class.getName();
    public final int f16413a = 1;
    DataSetObservable f16414b = new DataSetObservable();
    private MagicDataSource f16415d;
    private String f16416e = "";
    private int f16417f;
    private int f16418g;
    private int f16419h;
    private int f16420i;
    private int f16421j;
    private boolean f16422k = false;
    private MagicDataSourceObservable f16423l = new MagicDataSourceObservable();
    private Parcelable f16424m;

    public abstract View mo6418a(ViewGroup viewGroup, int i);

    public abstract void mo6419a(View view, int i, int i2);

    public MagicAdapter(MagicDataSource magicDataSource) {
        this.f16415d = magicDataSource;
        this.f16415d.m17646a((DataSourceObserver) this);
        if (this.f16415d.m17659i() != DataState.LOADING_FIRST_PAGE) {
            if (this.f16415d.m17661k() == 0 && this.f16415d.m17662l()) {
                this.f16415d.m17665o();
            } else {
                mo6786b();
            }
        }
        this.f16422k = true;
    }

    public MagicDataSource m18026a() {
        return this.f16415d;
    }

    public void mo6858a(DataSourceObserver dataSourceObserver) {
        this.f16423l.registerObserver(dataSourceObserver);
    }

    public void m18042b(DataSourceObserver dataSourceObserver) {
        this.f16423l.unregisterObserver(dataSourceObserver);
    }

    public void mo6786b() {
        this.f16423l.mo6254c(m18026a());
    }

    public void mo6254c(MagicDataSource magicDataSource) {
        ThreadUtils.m19057a();
        if (this.f16422k) {
            this.f16423l.mo6254c(magicDataSource);
            this.f16414b.notifyChanged();
            return;
        }
        throw new RuntimeException(getClass().getName() + ": DataSource was not setup yet. This should not get called before the constructor is done.");
    }

    public void mo6255d(MagicDataSource magicDataSource) {
        this.f16423l.mo6255d(magicDataSource);
    }

    public void mo6252a(MagicDataSource magicDataSource, List<Object> list) {
        this.f16423l.mo6252a(magicDataSource, list);
    }

    public void mo6251a(MagicDataSource magicDataSource) {
        this.f16423l.mo6251a(magicDataSource);
    }

    public void mo6253b(MagicDataSource magicDataSource) {
        this.f16423l.mo6253b(magicDataSource);
    }

    public void m18028a(DataSetObserver dataSetObserver) {
        this.f16414b.registerObserver(dataSetObserver);
    }

    public void m18040b(DataSetObserver dataSetObserver) {
        this.f16414b.unregisterObserver(dataSetObserver);
    }

    public final boolean m18047c() {
        return this.f16415d.m17662l();
    }

    public final int m18048d() {
        return this.f16415d.m17661k();
    }

    public final Object m18027a(int i) {
        return this.f16415d.m17641a(i);
    }

    public long mo6785b(int i) {
        return (long) i;
    }

    public final View m18025a(ViewGroup viewGroup, View view, int i) {
        this.f16415d.m17650b(i);
        int c = mo6441c(i);
        if (view == null) {
            view = mo6418a(viewGroup, c);
        }
        if (view != null) {
            mo6419a(view, i, c);
            mo6463a(view, i, getPositionForSection(getSectionForPosition(i)) == i);
        }
        return view;
    }

    public View mo6857a(ViewGroup viewGroup) {
        if (this.f16417f != 0) {
            return LayoutInflater.from(viewGroup.getContext()).inflate(this.f16417f, null);
        }
        throw new RuntimeException("Must define theme's magicListViewStyle and MagicListView's loadingFooter attr in styles.xml or overload createLoadingFooterView");
    }

    public View mo6464b(ViewGroup viewGroup) {
        if (this.f16418g != 0) {
            return LayoutInflater.from(viewGroup.getContext()).inflate(this.f16418g, null);
        }
        throw new RuntimeException("Must define theme's magicListViewStyle and MagicListView's loadingView attr in styles.xml or overload createLoadingFullPageView");
    }

    public View mo6459c(ViewGroup viewGroup) {
        if (this.f16419h != 0) {
            return LayoutInflater.from(viewGroup.getContext()).inflate(this.f16419h, null);
        }
        throw new RuntimeException("Must define theme's magicListViewStyle and MagicListView's networkView attr in styles.xml or overload createLoadFailedFullPageView");
    }

    public View mo6460d(ViewGroup viewGroup) {
        if (this.f16420i == 0) {
            throw new RuntimeException("Must define theme's magicListViewStyle and MagicListView's emptyView attr in styles.xml");
        } else if (this.f16421j == 0) {
            throw new RuntimeException("Must define theme's magicListViewStyle and MagicListView's emptyText attr in styles.xml");
        } else {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(this.f16420i, null);
            TextView textView = (TextView) inflate.findViewById(this.f16421j);
            if (textView != null) {
                textView.setText(this.f16416e);
            }
            return inflate;
        }
    }

    public boolean mo6799a(View view) {
        return false;
    }

    public int mo6442e() {
        return 1;
    }

    public int mo6441c(int i) {
        return 1;
    }

    public void m18054f() {
        this.f16415d.m17664n();
    }

    protected void m18035a(String str) {
        this.f16416e = str;
    }

    public void m18029a(Parcelable parcelable) {
        this.f16424m = parcelable;
    }

    public Parcelable m18056g() {
        return this.f16424m;
    }

    public void m18050d(int i) {
        this.f16417f = i;
    }

    public void m18053e(int i) {
        this.f16418g = i;
    }

    public void m18055f(int i) {
        this.f16419h = i;
    }

    public void m18057g(int i) {
        this.f16420i = i;
    }

    public void m18058h(int i) {
        this.f16421j = i;
    }

    public int m18059i(int i) {
        if (i < 0 || m18048d() == 0) {
            return 0;
        }
        int positionForSection = getPositionForSection(getSectionForPosition(i) + 1);
        if (positionForSection == -1 || i != positionForSection - 1) {
            return 1;
        }
        return 2;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (absListView instanceof MagicListView) {
            ((MagicListView) absListView).m18010a(i);
        }
    }

    protected void mo6463a(View view, int i, boolean z) {
    }

    public void mo6681b(View view, int i, int i2) {
    }

    public int getPositionForSection(int i) {
        return 0;
    }

    public int getSectionForPosition(int i) {
        return 0;
    }

    public Object[] getSections() {
        return new Object[0];
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }
}
