package com.smule.singandroid.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager$PerformancesResponseCallback;
import com.smule.android.network.managers.PerformanceManager.PerformancesResponse;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.UiHandler;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.bookmark.BookmarkDialogCallback;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.list_items.OpenCallListItem;
import com.smule.singandroid.list_items.OpenCallListItem.ExpandedPerformanceItemListener;
import com.smule.singandroid.list_items.PerfCountHeader;
import com.smule.singandroid.list_items.PerformanceItemInterface.PerformanceItemListener;
import com.smule.singandroid.list_items.PerformanceListItem;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer.ActionBarHighlightMode;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.FilterType;
import com.smule.singandroid.utils.SingAnalytics.SectionType;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public class OpenCallPerformancesListFragment extends BaseFragment {
    private static final String f22606l = OpenCallPerformancesListFragment.class.getName();
    @InstanceState
    protected PerformanceV2 f22607e;
    OpenCallListItem f22608f;
    @ViewById
    protected View f22609g;
    @ViewById
    protected TextView f22610h;
    @ViewById
    protected View f22611i;
    @ViewById
    protected MediaListView f22612j;
    final Observer f22613k = new C45833(this);
    private OpenCallPerformancesListAdapter f22614m;
    private LocalizedShortNumberFormatter f22615n;
    private PerformanceItemListener f22616o = new C45822(this);

    class C45811 implements ExpandedPerformanceItemListener {
        final /* synthetic */ OpenCallPerformancesListFragment f22597a;

        C45811(OpenCallPerformancesListFragment openCallPerformancesListFragment) {
            this.f22597a = openCallPerformancesListFragment;
        }

        public void mo6560a(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
            this.f22597a.m24082b(performanceV2);
        }

        public void mo6561b(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
            this.f22597a.m19862m().a(performanceV2, true, true);
        }

        public void mo6562c(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
            BaseFragment a = ProfileFragment.m21036a(performanceV2.accountIcon);
            this.f22597a.m19862m().a(a, a.mo6514z());
        }

        public void mo6563d(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
            this.f22597a.m19862m().a(performanceV2, true, true);
        }

        public void mo6559a(PerformanceV2 performanceV2, boolean z) {
            final MediaPlayingActivity mediaPlayingActivity = (MediaPlayingActivity) this.f22597a.getActivity();
            mediaPlayingActivity.ad().m22308a(performanceV2, new BookmarkDialogCallback(this) {
                final /* synthetic */ C45811 f22596b;

                class C45781 implements Runnable {
                    final /* synthetic */ C45801 f22593a;

                    C45781(C45801 c45801) {
                        this.f22593a = c45801;
                    }

                    public void run() {
                        mediaPlayingActivity.ad().m22306a(this.f22593a.f22596b.f22597a, this.f22593a.f22596b.f22597a.f22609g, this.f22593a.f22596b.f22597a.f22610h, true);
                    }
                }

                class C45792 implements Runnable {
                    final /* synthetic */ C45801 f22594a;

                    C45792(C45801 c45801) {
                        this.f22594a = c45801;
                    }

                    public void run() {
                        mediaPlayingActivity.ad().m22306a(this.f22594a.f22596b.f22597a, this.f22594a.f22596b.f22597a.f22609g, this.f22594a.f22596b.f22597a.f22610h, false);
                    }
                }

                public void mo6428a(PerformanceV2 performanceV2) {
                    new UiHandler(mediaPlayingActivity).m19081a(new C45781(this));
                    NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "BOOKMARKED_PERFORMANCE", performanceV2);
                }

                public void mo6429b(PerformanceV2 performanceV2) {
                    new UiHandler(mediaPlayingActivity).m19081a(new C45792(this));
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

    class C45822 implements PerformanceItemListener {
        final /* synthetic */ OpenCallPerformancesListFragment f22598a;

        C45822(OpenCallPerformancesListFragment openCallPerformancesListFragment) {
            this.f22598a = openCallPerformancesListFragment;
        }

        public void mo6472a(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
            this.f22598a.m24081a(performanceV2, MiscUtils.m25895a(performanceV2));
        }

        public void mo6471a(MediaPlayingViewInterface mediaPlayingViewInterface, AccountIcon accountIcon) {
            this.f22598a.m24080a(accountIcon);
        }

        public void mo6473b(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
            this.f22598a.m24081a(performanceV2, MiscUtils.m25895a(performanceV2));
        }
    }

    class C45833 implements Observer {
        final /* synthetic */ OpenCallPerformancesListFragment f22599a;

        C45833(OpenCallPerformancesListFragment openCallPerformancesListFragment) {
            this.f22599a = openCallPerformancesListFragment;
        }

        public void update(Observable observable, Object obj) {
            if (obj instanceof HashMap) {
                PerformanceV2 performanceV2;
                HashMap hashMap = (HashMap) obj;
                if (hashMap.containsKey("DELETED_PERFORMANCE")) {
                    performanceV2 = (PerformanceV2) hashMap.get("DELETED_PERFORMANCE");
                    if (!(this.f22599a.f22607e == null || this.f22599a.f22607e.performanceKey == null || performanceV2 == null || !this.f22599a.f22607e.performanceKey.equals(performanceV2.performanceKey) || this.f22599a.m19862m() == null)) {
                        this.f22599a.m19862m().a(this.f22599a);
                        return;
                    }
                }
                if (hashMap.containsKey("UPDATED_PERFORMANCE") && this.f22599a.f22608f != null) {
                    performanceV2 = (PerformanceV2) hashMap.get("UPDATED_PERFORMANCE");
                    if (performanceV2 != null && this.f22599a.f22607e.performanceKey.equals(performanceV2.performanceKey)) {
                        this.f22599a.f22607e = performanceV2;
                        this.f22599a.f22608f.m24336a(this.f22599a.f22607e, true);
                    }
                }
            }
        }
    }

    protected class OpenCallPerformancesDataSource extends MagicDataSource<PerformanceV2> {
        final /* synthetic */ OpenCallPerformancesListFragment f22603a;

        class C45852 implements Runnable {
            final /* synthetic */ OpenCallPerformancesDataSource f22602a;

            C45852(OpenCallPerformancesDataSource openCallPerformancesDataSource) {
                this.f22602a = openCallPerformancesDataSource;
            }

            public void run() {
                this.f22602a.f22603a.m19847b(this.f22602a.f22603a);
            }
        }

        public OpenCallPerformancesDataSource(OpenCallPerformancesListFragment openCallPerformancesListFragment, Context context) {
            this.f22603a = openCallPerformancesListFragment;
        }

        public int mo6242a() {
            return 15;
        }

        public Future<?> mo6243a(int i, int i2, final FetchDataCallback<PerformanceV2> fetchDataCallback) {
            return PerformanceManager.a().a(this.f22603a.f22607e.performanceKey, Integer.valueOf(i), Integer.valueOf(15), new PerformanceManager$PerformancesResponseCallback(this) {
                final /* synthetic */ OpenCallPerformancesDataSource f22601b;

                public void handleResponse(PerformancesResponse performancesResponse) {
                    if (performancesResponse.a()) {
                        fetchDataCallback.mo6257a(performancesResponse.mPerformances, performancesResponse.mNext.intValue());
                        return;
                    }
                    fetchDataCallback.mo6256a();
                    this.f22601b.m24065a(performancesResponse.a);
                }
            });
        }

        protected void m24065a(NetworkResponse networkResponse) {
            if (networkResponse.e()) {
                ((BaseActivity) this.f22603a.getActivity()).a(networkResponse.f, true, new C45852(this));
            }
        }
    }

    private class OpenCallPerformancesListAdapter extends MagicAdapter {
        public final String f22604c = OpenCallPerformancesListAdapter.class.getSimpleName();
        final /* synthetic */ OpenCallPerformancesListFragment f22605d;

        public OpenCallPerformancesListAdapter(OpenCallPerformancesListFragment openCallPerformancesListFragment) {
            this.f22605d = openCallPerformancesListFragment;
            super(new OpenCallPerformancesDataSource(openCallPerformancesListFragment, openCallPerformancesListFragment.getActivity()));
        }

        public View mo6418a(ViewGroup viewGroup, int i) {
            return PerformanceListItem.m24362c(this.f22605d.getActivity());
        }

        public void mo6419a(View view, int i, int i2) {
            Log.b(this.f22604c, "getView - PERFORMANCE_TYPE_OPEN_CALL - playingSongUID is: " + MediaPlayerServiceController.m24641a().m24673i() + ", position is: " + i);
            PerformanceListItem performanceListItem = (PerformanceListItem) view;
            PerformanceV2 performanceV2 = (PerformanceV2) m18027a(i);
            performanceListItem.m24365a(true, this.f22605d);
            performanceListItem.setPerformance(performanceV2);
            performanceListItem.setListener(this.f22605d.f22616o);
        }
    }

    public static OpenCallPerformancesListFragment m24071a(PerformanceV2 performanceV2) {
        OpenCallPerformancesListFragment openCallPerformancesListFragment_ = new OpenCallPerformancesListFragment_();
        openCallPerformancesListFragment_.f22607e = performanceV2;
        return openCallPerformancesListFragment_;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f22614m = new OpenCallPerformancesListAdapter(this);
    }

    public boolean mo6445g() {
        return true;
    }

    @AfterViews
    protected void m24079a() {
        m24085z();
        m24069C();
    }

    public void onStart() {
        super.onStart();
        NotificationCenter.m19011a().m19014a("PERFORMANCE_UPDATED_NOTIFICATION", this.f22613k);
    }

    public void onResume() {
        super.onResume();
        m19850c((int) C1947R.string.core_open_calls);
    }

    public void onStop() {
        super.onStop();
        NotificationCenter.m19011a().m19016b("PERFORMANCE_UPDATED_NOTIFICATION", this.f22613k);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f22608f = null;
    }

    private LocalizedShortNumberFormatter m24068B() {
        if (this.f22615n == null) {
            this.f22615n = new LocalizedShortNumberFormatter(getActivity());
        }
        return this.f22615n;
    }

    protected void m24085z() {
        this.f22608f = OpenCallListItem.m24333c(getActivity());
        this.f22608f.m24336a(this.f22607e, true);
        this.f22608f.m24338b(true);
        this.f22608f.setExpandedPerformanceListener(new C45811(this));
        this.f22612j.addHeaderView(this.f22608f);
        View a = PerfCountHeader.m24390a(getActivity());
        if (this.f22607e.childCount == 0) {
            a.setText(getResources().getString(C1947R.string.joins_count_zero));
        } else {
            a.setText(getResources().getQuantityString(C1947R.plurals.joins_count, this.f22607e.childCount, new Object[]{m24068B().m18999a((long) this.f22607e.childCount, (long) getResources().getInteger(C1947R.integer.long_form_threshold))}));
        }
        this.f22612j.addHeaderView(a);
    }

    private void m24069C() {
        this.f22612j.setVisibility(0);
        this.f22612j.setBackgroundColor(0);
        this.f22612j.setMagicAdapter(this.f22614m);
        this.f22612j.setDivider(null);
        this.f22612j.setDividerHeight(0);
        mo6485a(this.f22612j, ActionBarHighlightMode.NEVER, null);
    }

    public void m24081a(PerformanceV2 performanceV2, boolean z) {
        mo6486a(performanceV2, z, true);
    }

    public void m24080a(AccountIcon accountIcon) {
        mo6487a(ProfileFragment.m21036a(accountIcon));
    }

    public void m24082b(PerformanceV2 performanceV2) {
        PreSingActivity.m24763a(getActivity()).m24796a(StartupMode.OPENCALL).m24793a(performanceV2).a();
        SingAnalytics.m26081a(performanceV2.video ? FilterType.f24970b : FilterType.NONE, SectionType.ALL);
    }

    public String mo6383s() {
        return f22606l;
    }

    @Click
    protected void m24078A() {
        ((MediaPlayingActivity) getActivity()).ad().m22307a(this.f22609g);
    }
}
