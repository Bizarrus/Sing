package com.smule.singandroid.fragments;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.UiHandler;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.adapters.OpenCallListAdapter;
import com.smule.singandroid.adapters.OpenCallListAdapter.HasMorePagesListener;
import com.smule.singandroid.adapters.OpenCallListAdapter.OpenCallListAdapterUIDelegate;
import com.smule.singandroid.bookmark.BookmarkDialogCallback;
import com.smule.singandroid.list_items.OpenCallListItem;
import com.smule.singandroid.list_items.OpenCallListItem.ExpandedPerformanceItemListener;
import com.smule.singandroid.list_items.PerformanceListEmptyListItem;
import com.smule.singandroid.utils.ListViewMediaPlayerObserver;
import com.smule.singandroid.utils.MiscUtils;
import java.util.ArrayList;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public abstract class OpenCallListFragment extends BaseFragment implements OpenCallListAdapterUIDelegate {
    private static final String f19087e = OpenCallListFragment.class.getName();
    private OpenCallListListener f19088f;
    private HasMorePagesListener f19089g = new C45721(this);
    protected OpenCallListAdapter f19090h;
    protected OpenCallListAdapter f19091i;
    protected OpenCallListAdapter f19092j;
    @ViewById
    protected View f19093k;
    @ViewById
    protected TextView f19094l;
    @ViewById
    protected View f19095m;
    @ViewById
    protected View f19096n;
    @ViewById
    protected ListView f19097o;
    @ViewById
    protected View f19098p;
    @ViewById
    protected PerformanceListEmptyListItem f19099q;
    @InstanceState
    protected ArrayList<PerformanceV2> f19100r = new ArrayList();
    @InstanceState
    protected ArrayList<PerformanceV2> f19101s = new ArrayList();
    @InstanceState
    protected int f19102t;
    @InstanceState
    protected boolean f19103u;
    private DataSetObserver f19104v = new C45732(this);

    public interface OpenCallListListener {
        void mo6502a(PerformanceV2 performanceV2);

        void mo6503d(int i);
    }

    class C45721 implements HasMorePagesListener {
        final /* synthetic */ OpenCallListFragment f22586a;

        C45721(OpenCallListFragment openCallListFragment) {
            this.f22586a = openCallListFragment;
        }

        public void mo6851a() {
            if (this.f22586a.f19090h.getCount() <= 0) {
                this.f22586a.m20616e(0);
            }
        }

        public void mo6852b() {
            this.f22586a.m20616e(1);
        }
    }

    class C45732 extends DataSetObserver {
        final /* synthetic */ OpenCallListFragment f22587a;

        C45732(OpenCallListFragment openCallListFragment) {
            this.f22587a = openCallListFragment;
        }

        public void onChanged() {
            if (this.f22587a.isAdded()) {
                this.f22587a.f19098p.setVisibility(4);
            }
        }
    }

    class C45773 implements ExpandedPerformanceItemListener {
        final /* synthetic */ OpenCallListFragment f22592a;

        C45773(OpenCallListFragment openCallListFragment) {
            this.f22592a = openCallListFragment;
        }

        public void mo6560a(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
            if (this.f22592a.f19088f != null) {
                this.f22592a.f19088f.mo6502a(performanceV2);
            }
        }

        public void mo6561b(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
            if (this.f22592a.m19862m() != null) {
                this.f22592a.m19862m().a(performanceV2, MiscUtils.m25895a(performanceV2), true);
            }
        }

        public void mo6562c(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
            BaseFragment a = ProfileFragment.m21036a(performanceV2.accountIcon);
            if (this.f22592a.m19862m() != null) {
                this.f22592a.m19862m().a(a, a.mo6514z());
            }
        }

        public void mo6563d(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
            if (this.f22592a.m19862m() != null) {
                this.f22592a.m19862m().a(performanceV2, MiscUtils.m25895a(performanceV2), true);
            }
        }

        public void mo6559a(PerformanceV2 performanceV2, boolean z) {
            final MediaPlayingActivity mediaPlayingActivity = (MediaPlayingActivity) this.f22592a.getActivity();
            if (mediaPlayingActivity != null) {
                mediaPlayingActivity.ad().m22308a(performanceV2, new BookmarkDialogCallback(this) {
                    final /* synthetic */ C45773 f22591b;

                    class C45741 implements Runnable {
                        final /* synthetic */ C45761 f22588a;

                        C45741(C45761 c45761) {
                            this.f22588a = c45761;
                        }

                        public void run() {
                            mediaPlayingActivity.ad().m22306a(this.f22588a.f22591b.f22592a, this.f22588a.f22591b.f22592a.f19093k, this.f22588a.f22591b.f22592a.f19094l, true);
                        }
                    }

                    class C45752 implements Runnable {
                        final /* synthetic */ C45761 f22589a;

                        C45752(C45761 c45761) {
                            this.f22589a = c45761;
                        }

                        public void run() {
                            mediaPlayingActivity.ad().m22306a(this.f22589a.f22591b.f22592a, this.f22589a.f22591b.f22592a.f19093k, this.f22589a.f22591b.f22592a.f19094l, false);
                        }
                    }

                    public void mo6428a(PerformanceV2 performanceV2) {
                        new UiHandler(mediaPlayingActivity).m19081a(new C45741(this));
                        NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "BOOKMARKED_PERFORMANCE", performanceV2);
                    }

                    public void mo6429b(PerformanceV2 performanceV2) {
                        new UiHandler(mediaPlayingActivity).m19081a(new C45752(this));
                        NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "FAVORITED_PERFORMANCE", performanceV2);
                    }

                    public void mo6430c(PerformanceV2 performanceV2) {
                        NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "UNBOOKMARKED_PERFORMANCE", performanceV2);
                    }

                    public void mo6431d(PerformanceV2 performanceV2) {
                        NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "UNFAVORITED_PERFORMANCE", performanceV2);
                    }
                }, z);
            }
        }
    }

    public void m20615b(boolean z) {
        this.f19103u = z;
    }

    public void m20614a(OpenCallListListener openCallListListener) {
        this.f19088f = openCallListListener;
    }

    @AfterViews
    protected void m20611A() {
        this.f19091i = new OpenCallListAdapter(this, this.f19100r);
        this.f19092j = new OpenCallListAdapter(this, this.f19101s);
        this.f19092j.m22042a(true);
        this.f19090h = this.f19091i;
        this.f19097o.setAdapter(this.f19090h);
        this.f19091i.m22041a(this.f19089g);
        this.f19092j.m22041a(this.f19089g);
        this.f19090h.m22046e();
        this.f19091i.registerDataSetObserver(this.f19104v);
        this.f19092j.registerDataSetObserver(this.f19104v);
    }

    public String mo6383s() {
        return f19087e;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onStart() {
        super.onStart();
        this.f19097o.setSelection(this.f19102t);
        ListViewMediaPlayerObserver.m25861a(this.f19097o);
    }

    public void onStop() {
        super.onStop();
        this.f19102t = this.f19097o.getSelectedItemPosition();
        ListViewMediaPlayerObserver.m25862b(this.f19097o);
    }

    protected void m20616e(int i) {
        int i2 = 4;
        if (isAdded()) {
            this.f19096n.setVisibility(i == 0 ? 0 : 4);
            View view = this.f19095m;
            if (i != 0) {
                i2 = 0;
            }
            view.setVisibility(i2);
            if (i == 0) {
                this.f19099q.m24395a();
            }
            if (this.f19088f != null) {
                this.f19088f.mo6503d(i);
                return;
            }
            return;
        }
        Log.d(f19087e, "Fragment not added in updateViewVisibility.");
    }

    public View mo6501a(View view, ViewGroup viewGroup, PerformanceV2 performanceV2, boolean z) {
        View c;
        if (view == null) {
            c = OpenCallListItem.m24333c(getActivity());
            c.setIsSectionFree(mo6504z());
        } else {
            c = view;
        }
        OpenCallListItem openCallListItem = (OpenCallListItem) c;
        openCallListItem.m24336a(performanceV2, z);
        openCallListItem.m24340c(false);
        openCallListItem.setExpandedPerformanceListener(new C45773(this));
        return c;
    }

    @Click
    public void m20612B() {
        ((MediaPlayingActivity) getActivity()).ad().m22307a(this.f19093k);
    }

    protected boolean mo6504z() {
        return false;
    }
}
