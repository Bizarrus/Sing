package com.smule.android.magicui.lists;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.smule.android.C3482R;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.DataSourceObserver;
import com.smule.android.magicui.lists.adapters.MagicDataSource.DataSourceObserverObject;

public class MagicListView extends ListView implements MagicDataView {
    MagicAdapter f16380a;
    MagicListViewAdapterWrapper f16381b;
    private SwipeRefreshLayout f16382c;
    private View f16383d;
    private int f16384e;
    private int f16385f;
    private int f16386g;
    private int f16387h;
    private int f16388i;
    private View f16389j;
    private boolean f16390k;
    private int f16391l;
    private int f16392m;
    private DataSourceObserver f16393n;

    class C35321 extends DataSourceObserverObject {
        final /* synthetic */ MagicListView f16375a;

        C35321(MagicListView magicListView) {
            this.f16375a = magicListView;
        }

        public void mo6251a(MagicDataSource magicDataSource) {
            if (this.f16375a.f16382c != null) {
                this.f16375a.f16382c.setRefreshing(true);
            }
        }

        public void mo6253b(MagicDataSource magicDataSource) {
            if (this.f16375a.f16382c != null) {
                this.f16375a.f16382c.setRefreshing(false);
            }
        }
    }

    class C35332 implements OnRefreshListener {
        final /* synthetic */ MagicListView f16376a;

        C35332(MagicListView magicListView) {
            this.f16376a = magicListView;
        }

        public void onRefresh() {
            if (this.f16376a.f16380a != null) {
                this.f16376a.f16380a.m18054f();
            }
        }
    }

    public class NativeAdSetup {
        final /* synthetic */ MagicListView f16377a;
        private MagicAdapter f16378b;
        private MagicListViewAdapterWrapper f16379c;

        public NativeAdSetup(MagicListView magicListView, MagicAdapter magicAdapter) {
            this.f16377a = magicListView;
            this.f16378b = magicAdapter;
            if (magicAdapter == null) {
                this.f16379c = null;
            } else {
                this.f16379c = new MagicListViewAdapterWrapper(magicAdapter);
            }
        }

        public ListAdapter m18005a() {
            return this.f16379c;
        }

        public void m18006a(ListAdapter listAdapter) {
            this.f16377a.m18012a(this.f16378b, listAdapter);
        }
    }

    public MagicListView(Context context) {
        this(context, null);
    }

    public MagicListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C3482R.attr.magicListViewStyle);
    }

    public MagicListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f16393n = new C35321(this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C3482R.styleable.MagicListView, i, 0);
        this.f16384e = obtainStyledAttributes.getResourceId(C3482R.styleable.MagicListView_loadingFooter, 0);
        this.f16385f = obtainStyledAttributes.getResourceId(C3482R.styleable.MagicListView_loadingView, 0);
        this.f16386g = obtainStyledAttributes.getResourceId(C3482R.styleable.MagicListView_networkView, 0);
        this.f16387h = obtainStyledAttributes.getResourceId(C3482R.styleable.MagicListView_emptyView, 0);
        this.f16388i = obtainStyledAttributes.getResourceId(C3482R.styleable.MagicListView_emptyText, 0);
        obtainStyledAttributes.recycle();
        m18008a();
    }

    private void m18008a() {
        setOnScrollListener(new PauseOnScrollListener(ImageLoader.a(), true, true));
    }

    public void setAdapter(ListAdapter listAdapter) {
        throw new RuntimeException("Invalid use case. Please use setMagicAdapter(MagicAdapter) instead.");
    }

    public NativeAdSetup m18009a(MagicAdapter magicAdapter) {
        return new NativeAdSetup(this, magicAdapter);
    }

    public void setMagicAdapter(MagicAdapter magicAdapter) {
        if (magicAdapter == null) {
            this.f16381b = null;
        } else {
            this.f16381b = new MagicListViewAdapterWrapper(magicAdapter);
        }
        m18012a(magicAdapter, this.f16381b);
    }

    protected void m18012a(MagicAdapter magicAdapter, ListAdapter listAdapter) {
        super.setAdapter(listAdapter);
        if (this.f16380a != null && ViewCompat.isAttachedToWindow(this)) {
            this.f16380a.m18042b(this.f16393n);
        }
        this.f16380a = magicAdapter;
        setOnScrollListener(magicAdapter);
        if (this.f16380a != null) {
            this.f16380a.m18050d(this.f16384e);
            this.f16380a.m18053e(this.f16385f);
            this.f16380a.m18055f(this.f16386g);
            this.f16380a.m18057g(this.f16387h);
            this.f16380a.m18058h(this.f16388i);
            Parcelable g = this.f16380a.m18056g();
            if (g != null) {
                onRestoreInstanceState(g);
                this.f16380a.m18029a(null);
            }
            if (ViewCompat.isAttachedToWindow(this)) {
                this.f16380a.mo6858a(this.f16393n);
            }
        }
    }

    public MagicAdapter getWrappedAdapter() {
        return this.f16380a;
    }

    public void setHeaderView(View view) {
        if (this.f16383d != null) {
            removeHeaderView(this.f16383d);
        }
        this.f16383d = view;
        if (this.f16383d != null) {
            addHeaderView(this.f16383d);
        }
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        super.setOnScrollListener(onScrollListener);
    }

    public void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        m18011a(swipeRefreshLayout, null);
    }

    public void m18011a(SwipeRefreshLayout swipeRefreshLayout, OnRefreshListener onRefreshListener) {
        if (this.f16382c != null) {
            this.f16382c.setOnRefreshListener(null);
        }
        this.f16382c = swipeRefreshLayout;
        if (this.f16382c == null) {
            return;
        }
        if (onRefreshListener == null) {
            this.f16382c.setOnRefreshListener(new C35332(this));
        } else {
            this.f16382c.setOnRefreshListener(onRefreshListener);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f16380a != null) {
            this.f16380a.mo6858a(this.f16393n);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f16380a != null) {
            this.f16380a.m18042b(this.f16393n);
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (this.f16380a != null) {
            this.f16380a.m18029a(onSaveInstanceState);
        }
        return onSaveInstanceState;
    }

    public int getSectionCount() {
        return this.f16380a.getSections().length;
    }

    public void setPinnedHeaderView(View view) {
        this.f16389j = view;
        if (this.f16389j != null) {
            setFadingEdgeLength(0);
        }
        requestLayout();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f16389j != null) {
            measureChild(this.f16389j, i, i2);
            this.f16391l = this.f16389j.getMeasuredWidth();
            this.f16392m = this.f16389j.getMeasuredHeight();
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f16389j != null) {
            this.f16389j.layout(0, 0, this.f16391l, this.f16392m);
            m18010a(getFirstVisiblePosition());
        }
    }

    public void m18010a(int i) {
        int i2 = 255;
        if (this.f16389j != null) {
            switch (this.f16380a.m18059i(i)) {
                case 0:
                    this.f16390k = false;
                    return;
                case 1:
                    this.f16380a.mo6681b(this.f16389j, i, 255);
                    if (this.f16389j.getTop() != 0) {
                        this.f16389j.layout(0, 0, this.f16391l, this.f16392m);
                    }
                    this.f16390k = true;
                    return;
                case 2:
                    View childAt = getChildAt(0);
                    if (childAt != null) {
                        int bottom = childAt.getBottom();
                        int height = this.f16389j.getHeight();
                        if (bottom < height) {
                            bottom -= height;
                            i2 = ((height + bottom) * 255) / height;
                        } else {
                            bottom = 0;
                        }
                        this.f16380a.mo6681b(this.f16389j, i, i2);
                        if (this.f16389j.getTop() != bottom) {
                            this.f16389j.layout(0, bottom, this.f16391l, this.f16392m + bottom);
                        }
                        this.f16390k = true;
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.f16390k && this.f16389j.getVisibility() == 0) {
            drawChild(canvas, this.f16389j, getDrawingTime());
        }
    }
}
