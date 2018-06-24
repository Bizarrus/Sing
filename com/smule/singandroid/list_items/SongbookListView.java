package com.smule.singandroid.list_items;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView.LayoutParams;
import android.widget.LinearLayout;
import com.foound.widget.AmazingListView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.adapters.songbook.SongbookAmazingAdapter;
import com.smule.singandroid.adapters.songbook.SongbookAmazingAdapter.DataRefreshListener;
import com.smule.singandroid.customviews.SongbookSortSelector;
import com.smule.singandroid.models.SongbookListViewState;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class SongbookListView extends LinearLayout implements DataRefreshListener {
    private static final String f23251h = SongbookListView.class.getName();
    @ViewById
    public AmazingListView f23252a;
    @ViewById
    SwipeRefreshLayout f23253b;
    SongbookAmazingAdapter f23254c;
    SongbookSortSelector f23255d;
    protected volatile int f23256e = 0;
    protected final Runnable f23257f = new C46972(this);
    protected final Runnable f23258g = new C46983(this);
    private ViewGroup f23259i;
    private DataSetObserver f23260j = new C46961(this);
    private int f23261k;
    private View f23262l;
    private int f23263m;

    class C46961 extends DataSetObserver {
        final /* synthetic */ SongbookListView f23247a;

        C46961(SongbookListView songbookListView) {
            this.f23247a = songbookListView;
        }

        public void onChanged() {
            this.f23247a.m24491e();
            if (this.f23247a.f23254c != null) {
                this.f23247a.f23252a.setFastScrollEnabled(this.f23247a.f23254c.mo6699k());
            }
        }
    }

    class C46972 implements Runnable {
        final /* synthetic */ SongbookListView f23248a;

        C46972(SongbookListView songbookListView) {
            this.f23248a = songbookListView;
        }

        public void run() {
            SongbookListView songbookListView = this.f23248a;
            songbookListView.f23256e++;
            if (this.f23248a.f23256e > 0) {
                this.f23248a.f23253b.setRefreshing(true);
            }
        }
    }

    class C46983 implements Runnable {
        final /* synthetic */ SongbookListView f23249a;

        C46983(SongbookListView songbookListView) {
            this.f23249a = songbookListView;
        }

        public void run() {
            SongbookListView songbookListView = this.f23249a;
            songbookListView.f23256e--;
            this.f23249a.f23253b.setRefreshing(false);
        }
    }

    class C46994 implements OnRefreshListener {
        final /* synthetic */ SongbookListView f23250a;

        C46994(SongbookListView songbookListView) {
            this.f23250a = songbookListView;
        }

        public void onRefresh() {
            this.f23250a.f23254c.mo6698j();
        }
    }

    public static SongbookListView m24481a(Context context) {
        return SongbookListView_.m24492b(context);
    }

    public SongbookListView(Context context) {
        super(context);
    }

    @AfterViews
    void m24490d() {
        this.f23252a.setLoadingView(inflate(getContext(), C1947R.layout.list_loading_view, null));
        this.f23255d = (SongbookSortSelector) ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(C1947R.layout.songbook_sort_bar, this.f23252a, false);
    }

    public void setAdapter(SongbookAmazingAdapter songbookAmazingAdapter) {
        if (this.f23254c != null) {
            this.f23254c.unregisterDataSetObserver(this.f23260j);
        }
        this.f23254c = songbookAmazingAdapter;
        if (songbookAmazingAdapter != null) {
            this.f23254c.registerDataSetObserver(this.f23260j);
            this.f23254c.mo6693a((DataRefreshListener) this);
            if (this.f23254c.mo6697i()) {
                this.f23253b.setColorSchemeResources(new int[]{C1947R.color.refresh_icon});
                this.f23253b.setEnabled(true);
                this.f23253b.setOnRefreshListener(new C46994(this));
            } else {
                this.f23253b.setEnabled(false);
            }
        }
        this.f23252a.setAdapter(this.f23254c);
        this.f23260j.onChanged();
    }

    public SongbookAmazingAdapter getSongsAdapter() {
        return this.f23254c;
    }

    public void m24485a(int i, int i2) {
        this.f23261k = i;
        this.f23253b.setProgressViewOffset(false, getResources().getDimensionPixelOffset(C1947R.dimen.songbook_swipe_to_refresh_spinner_offset_top) + i, getResources().getDimensionPixelOffset(C1947R.dimen.songbook_swipe_to_refresh_spinner_offset_bottom) + i);
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f23253b.getLayoutParams();
        marginLayoutParams.topMargin = i2;
        this.f23253b.setLayoutParams(marginLayoutParams);
        if (i > 0) {
            View newPlaceholderView = getNewPlaceholderView();
            newPlaceholderView.setLayoutParams(new LayoutParams(-1, i));
            this.f23252a.addHeaderView(newPlaceholderView);
            this.f23252a.addHeaderView(this.f23255d);
            this.f23263m = i2;
            m24491e();
            return;
        }
        this.f23252a.setHeaderView(null);
        this.f23252a.setHorizontalScrollBarEnabled(true);
        this.f23263m = 0;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        m24491e();
    }

    protected void m24491e() {
        int i = 0;
        if (this.f23254c != null && this.f23254c.mo6700l() != null && this.f23254c.mo6700l().mo6659a() == null) {
            return;
        }
        if (this.f23263m == 0 || (this.f23254c != null && this.f23254c.getCount() > 18)) {
            m24482f();
            return;
        }
        int i2;
        if (this.f23254c != null && this.f23254c.getCount() > 0) {
            int measuredHeight = getMeasuredHeight();
            int i3 = 0;
            for (i2 = 0; i2 < this.f23254c.getCount(); i2++) {
                View a = this.f23254c.mo6691a(i2, null, this.f23252a, true);
                a.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
                i3 += a.getMeasuredHeight();
                if (i3 >= measuredHeight) {
                    m24482f();
                    return;
                }
            }
            i = i3;
        }
        i2 = (getMeasuredHeight() - i) - this.f23263m;
        if (i2 <= 0 && this.f23262l != null) {
            m24482f();
        } else if (i2 <= 0) {
        } else {
            if (this.f23262l == null || i2 != this.f23262l.getLayoutParams().height) {
                if (this.f23262l == null) {
                    this.f23262l = getNewPlaceholderView();
                } else {
                    this.f23252a.removeFooterView(this.f23262l);
                }
                this.f23262l.setLayoutParams(new LayoutParams(-1, i2));
                this.f23252a.addFooterView(this.f23262l);
                this.f23252a.setHorizontalScrollBarEnabled(true);
            }
        }
    }

    private void m24482f() {
        if (this.f23262l != null) {
            this.f23252a.removeFooterView(this.f23262l);
        }
        this.f23262l = null;
        this.f23252a.setHorizontalScrollBarEnabled(true);
    }

    protected View getNewPlaceholderView() {
        return ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(C1947R.layout.songbook_list_placeholder, this.f23252a, false);
    }

    public void m24484a(int i) {
        SongbookListViewState o = getSongsAdapter().mo6703o();
        if (o.f23510b == 0 && i != 0) {
            o.f23511c = i;
        }
        this.f23252a.setSelectionFromTop(o.f23510b, o.f23511c);
    }

    public void m24487b(int i) {
        if (this.f23252a.getChildCount() != 0) {
            if (i == (-this.f23261k) && this.f23252a.getFirstVisiblePosition() > 0) {
                return;
            }
            if (this.f23252a.getFirstVisiblePosition() != 0 || this.f23252a.getChildAt(0).getTop() != i) {
                getSongsAdapter().mo6703o().m24755a(0, i);
                this.f23252a.setSelectionFromTop(0, i);
            }
        }
    }

    public void m24489c(int i) {
        getSongsAdapter().mo6703o().m24755a(0, i);
        this.f23252a.setSelectionFromTop(0, i);
    }

    public void mo6882a() {
        this.f23253b.post(this.f23257f);
        this.f23252a.removeHeaderView(this.f23259i);
        this.f23259i = null;
    }

    public void mo6883b() {
        this.f23253b.post(this.f23258g);
        this.f23253b.setEnabled(this.f23254c.mo6697i());
    }

    public void mo6884c() {
        this.f23253b.post(this.f23258g);
        if (this.f23254c.getCount() == 0 && this.f23259i == null) {
            this.f23259i = (ViewGroup) inflate(getContext(), C1947R.layout.songbook_network_issue_list_item, null);
            this.f23252a.addHeaderView(this.f23259i);
        }
    }

    public SongbookSortSelector getSongbookSortSelector() {
        return this.f23255d;
    }
}
