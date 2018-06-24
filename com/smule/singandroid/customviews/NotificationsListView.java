package com.smule.singandroid.customviews;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mopub.nativeads.MagicMoPubGhostStreamAdPlacer;
import com.mopub.nativeads.ViewBinder.Builder;
import com.smule.android.ads.MagicAdAdapterFactory;
import com.smule.android.ads.MagicAdSettings;
import com.smule.android.ads.nativeAds.MagicNativeAdMediatorAdapter;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.NativeAdPlacementType;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import com.smule.android.network.api.PerformancesAPI$FillStatus;
import com.smule.android.network.api.PerformancesAPI$SortOrder;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.InviteManager;
import com.smule.android.network.managers.InviteManager.ListInvitesResponse;
import com.smule.android.network.managers.InviteManager.ListInvitesResponseCallback;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager$PerformancesResponseCallback;
import com.smule.android.network.managers.PerformanceManager.PerformancesResponse;
import com.smule.android.network.managers.SocialManager;
import com.smule.android.network.managers.SocialManager.ListNotificationsResponse;
import com.smule.android.network.managers.SocialManager.ListNotificationsResponseCallback;
import com.smule.android.network.managers.SocialManager.LookupNotificationsResponse;
import com.smule.android.network.managers.SocialManager.LookupNotificationsResponseCallback;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.Invite;
import com.smule.android.network.models.Notification;
import com.smule.android.network.models.Notification$Type;
import com.smule.android.network.models.NotificationListItem;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.ReferenceMonitor;
import com.smule.android.utils.UiHandler;
import com.smule.singandroid.BaseFragment.BaseFragmentResponder;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.NotificationsFragment;
import com.smule.singandroid.NotificationsFragment.NotificationItemInterface;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.ads.AdUtils;
import com.smule.singandroid.bookmark.BookmarkDialogCallback;
import com.smule.singandroid.dialogs.NativeAdsMoreDialog;
import com.smule.singandroid.list_items.ActivityNotificationListViewItem;
import com.smule.singandroid.list_items.AggregatedNotificationListViewItem;
import com.smule.singandroid.list_items.InviteNotificationListViewItem;
import com.smule.singandroid.list_items.OpenCallListItem;
import com.smule.singandroid.list_items.OpenCallListItem.ExpandedPerformanceItemListener;
import com.smule.singandroid.list_items.PerformanceListEmptyListItem;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer.ActionBarHighlightMode;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.FilterType;
import com.smule.singandroid.utils.SingAnalytics.SectionType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class NotificationsListView extends LinearLayout {
    public final String f21663a = NotificationsListView.class.getName();
    @ViewById
    protected View f21664b;
    @ViewById
    protected TextView f21665c;
    @ViewById
    protected SwipeRefreshLayout f21666d;
    @ViewById
    protected MediaListView f21667e;
    @ViewById
    protected View f21668f;
    protected NotificationsFragment f21669g;
    protected NotificationsBaseAdapter f21670h;
    protected int f21671i;
    protected boolean f21672j;
    protected View f21673k;
    private MagicNativeAdMediatorAdapter f21674l;
    private boolean f21675m;

    class C43971 implements OnClickListener {
        final /* synthetic */ NotificationsListView f21619a;

        C43971(NotificationsListView notificationsListView) {
            this.f21619a = notificationsListView;
        }

        public void onClick(View view) {
            this.f21619a.f21669g.m19874y();
            new NativeAdsMoreDialog(this.f21619a.f21669g).show();
        }
    }

    class C43982 implements Runnable {
        final /* synthetic */ NotificationsListView f21620a;

        C43982(NotificationsListView notificationsListView) {
            this.f21620a = notificationsListView;
        }

        public void run() {
            this.f21620a.f21669g.m19874y();
        }
    }

    class C43993 implements OnRefreshListener {
        final /* synthetic */ NotificationsListView f21621a;

        C43993(NotificationsListView notificationsListView) {
            this.f21621a = notificationsListView;
        }

        public void onRefresh() {
            this.f21621a.f21675m = true;
            this.f21621a.f21669g.m20426D().m25995e();
            this.f21621a.f21670h.m18054f();
            this.f21621a.f21669g.mo6489B();
        }
    }

    class C44004 implements OnScrollListener {
        final /* synthetic */ NotificationsListView f21622a;

        C44004(NotificationsListView notificationsListView) {
            this.f21622a = notificationsListView;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            boolean z = false;
            if (this.f21622a.f21667e == null || this.f21622a.f21667e.getChildCount() <= 0 || this.f21622a.f21667e.getFirstVisiblePosition() != 0) {
                this.f21622a.f21666d.setEnabled(false);
                return;
            }
            int top = this.f21622a.f21667e.getChildAt(0).getTop();
            SwipeRefreshLayout swipeRefreshLayout = this.f21622a.f21666d;
            if (top >= 0) {
                z = true;
            }
            swipeRefreshLayout.setEnabled(z);
        }
    }

    public abstract class NotificationsBaseAdapter extends MagicAdapter {
        private HashSet<Integer> f21623c;
        final /* synthetic */ NotificationsListView f21624d;
        private int f21625e = -1;

        class C44093 implements ExpandedPerformanceItemListener {
            final /* synthetic */ NotificationsBaseAdapter f21646a;

            C44093(NotificationsBaseAdapter notificationsBaseAdapter) {
                this.f21646a = notificationsBaseAdapter;
            }

            public void mo6560a(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
            }

            public void mo6561b(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
            }

            public void mo6562c(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
            }

            public void mo6563d(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
            }

            public void mo6559a(PerformanceV2 performanceV2, boolean z) {
                final MasterActivity masterActivity = (MasterActivity) this.f21646a.f21624d.f21669g.getActivity();
                masterActivity.ad().m22308a(performanceV2, new BookmarkDialogCallback(this) {
                    final /* synthetic */ C44093 f21645b;

                    class C44061 implements Runnable {
                        final /* synthetic */ C44081 f21642a;

                        C44061(C44081 c44081) {
                            this.f21642a = c44081;
                        }

                        public void run() {
                            masterActivity.ad().m22306a(this.f21642a.f21645b.f21646a.f21624d.f21669g, this.f21642a.f21645b.f21646a.f21624d.f21664b, this.f21642a.f21645b.f21646a.f21624d.f21665c, true);
                        }
                    }

                    class C44072 implements Runnable {
                        final /* synthetic */ C44081 f21643a;

                        C44072(C44081 c44081) {
                            this.f21643a = c44081;
                        }

                        public void run() {
                            masterActivity.ad().m22306a(this.f21643a.f21645b.f21646a.f21624d.f21669g, this.f21643a.f21645b.f21646a.f21624d.f21664b, this.f21643a.f21645b.f21646a.f21624d.f21665c, false);
                        }
                    }

                    public void mo6428a(PerformanceV2 performanceV2) {
                        new UiHandler(masterActivity).m19081a(new C44061(this));
                        NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "BOOKMARKED_PERFORMANCE", performanceV2);
                    }

                    public void mo6429b(PerformanceV2 performanceV2) {
                        new UiHandler(masterActivity).m19081a(new C44072(this));
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

        class C44126 implements OnClickListener {
            final /* synthetic */ NotificationsBaseAdapter f21652a;

            C44126(NotificationsBaseAdapter notificationsBaseAdapter) {
                this.f21652a = notificationsBaseAdapter;
            }

            public void onClick(View view) {
                ((MasterActivity) this.f21652a.f21624d.f21669g.getActivity()).r();
            }
        }

        protected abstract int mo6787h();

        protected abstract void mo6788i();

        protected abstract int mo6789j();

        public NotificationsBaseAdapter(NotificationsListView notificationsListView, MagicDataSource magicDataSource) {
            this.f21624d = notificationsListView;
            super(magicDataSource);
        }

        public MagicListView m23261k() {
            return this.f21624d.f21667e;
        }

        public SwipeRefreshLayout m23262l() {
            return this.f21624d.f21666d;
        }

        public void mo6786b() {
            super.mo6786b();
            if (this.f21624d.f21669g != null && this.f21624d.f21669g.isAdded()) {
                this.f21624d.m23301d();
                if ((m18048d() > 0 || !m18047c()) && this.f21624d.f21671i == this.f21624d.f21669g.m20429H()) {
                    mo6788i();
                }
                this.f21624d.f21669g.mo6489B();
                this.f21624d.m23302e();
                this.f21624d.f21669g.m20430a(this.f21624d.f21671i, null);
            }
        }

        public int m23263m() {
            m23250o();
            return this.f21625e;
        }

        public void m23264n() {
            m23250o();
            this.f21625e = 0;
            mo6788i();
        }

        private void m23250o() {
            if (this.f21625e == -1) {
                this.f21625e = mo6787h();
                if (this.f21623c == null) {
                    m23249j(this.f21625e);
                }
            }
        }

        public View mo6418a(ViewGroup viewGroup, int i) {
            final View a;
            switch (i) {
                case 0:
                    return AggregatedNotificationListViewItem.m24223a(this.f21624d.getContext());
                case 1:
                    a = InviteNotificationListViewItem.m24350a(this.f21624d.getContext());
                    a.setOnAlbumArtClickListener(new OnClickListener(this) {
                        final /* synthetic */ NotificationsBaseAdapter f21639b;

                        public void onClick(View view) {
                            PerformanceV2 performance = a.getPerformance();
                            Log.b(this.f21639b.f21624d.f21663a, "mPerformanceItemListener - onAlbumArtClicked called");
                            if (performance != null) {
                                this.f21639b.f21624d.f21669g.mo6486a(performance, MiscUtils.m25895a(performance), true);
                            }
                        }
                    });
                    a.setOnJoinListener(new OnClickListener(this) {
                        final /* synthetic */ NotificationsBaseAdapter f21641b;

                        public void onClick(View view) {
                            PerformanceV2 performance = a.getPerformance();
                            SingAnalytics.m26088a(performance.performanceKey, a.getNotificationListItem().a(), Analytics.m17828a(performance), this.f21641b.f21624d.f21669g.m20427F());
                            PreSingActivity.m24763a(this.f21641b.f21624d.f21669g.getActivity()).m24796a(StartupMode.OPENCALL).m24793a(performance).a();
                            SingAnalytics.m26081a(performance.video ? FilterType.f24970b : FilterType.NONE, SectionType.INVITES);
                        }
                    });
                    a.setExpandedPerformanceListener(new C44093(this));
                    return a;
                case 2:
                    a = ActivityNotificationListViewItem.m24203a(this.f21624d.getContext());
                    a.setOnAlbumArtClickListener(new OnClickListener(this) {
                        final /* synthetic */ NotificationsBaseAdapter f21648b;

                        public void onClick(View view) {
                            PerformanceV2 performance = a.getPerformance();
                            Log.b(this.f21648b.f21624d.f21663a, "mPerformanceItemListener - onAlbumArtClicked called");
                            if (performance != null) {
                                this.f21648b.f21624d.f21669g.mo6486a(performance, MiscUtils.m25895a(performance), true);
                            }
                        }
                    });
                    return a;
                case 3:
                    return this.f21624d.f21669g.getActivity().getLayoutInflater().inflate(C1947R.layout.public_invites_header_row, viewGroup, false);
                default:
                    Log.e(this.f21624d.f21663a, "getPaginatedView type was not defined");
                    return null;
            }
        }

        public void mo6419a(View view, final int i, int i2) {
            NotificationListItem notificationListItem = (NotificationListItem) m18027a(i);
            if (i2 != 3) {
                boolean z;
                boolean z2 = this.f21623c != null && this.f21623c.contains(Integer.valueOf(i));
                if (i >= m18048d() - 1 || mo6441c(i + 1) == 3) {
                    z = true;
                } else {
                    z = false;
                }
                final NotificationItemInterface notificationItemInterface = (NotificationItemInterface) view;
                Log.b(this.f21624d.f21663a, "Calling bind for position = " + i);
                notificationItemInterface.mo6865a(this.f21624d.f21669g, (BaseFragmentResponder) this.f21624d.f21669g.getActivity(), notificationListItem, this.f21624d.f21669g.m20427F(), new Runnable(this) {
                    final /* synthetic */ NotificationsBaseAdapter f21651c;

                    public void run() {
                        if (this.f21651c.f21623c != null) {
                            this.f21651c.f21623c.remove(Integer.valueOf(i));
                        }
                        ((View) notificationItemInterface).setBackgroundResource(C1947R.drawable.notifications_item_selector);
                        notificationItemInterface.mo6864a();
                        this.f21651c.f21624d.f21669g.mo6489B();
                    }
                }, z2, z);
                int i3 = C1947R.drawable.notifications_item_selector;
                if (z2) {
                    i3 = C1947R.drawable.notifications_new_item_selector;
                }
                view.setBackgroundResource(i3);
            }
        }

        public int mo6442e() {
            return 4;
        }

        public int mo6441c(int i) {
            NotificationListItem notificationListItem = (NotificationListItem) m18027a(i);
            if (notificationListItem.count == 0 && notificationListItem.type == null) {
                return 3;
            }
            if ((notificationListItem.a() == Notification$Type.FOLLOW || notificationListItem.a() == Notification$Type.FOLLOW_FB || notificationListItem.a() == Notification$Type.CONNECT_FB || notificationListItem.a() == Notification$Type.MENTION || notificationListItem.a() == Notification$Type.RENDER) && notificationListItem.count > 1) {
                return 0;
            }
            return mo6789j();
        }

        public long mo6785b(int i) {
            return (long) i;
        }

        public View mo6460d(ViewGroup viewGroup) {
            View a = PerformanceListEmptyListItem.m24394a(this.f21624d.getContext());
            a.setModeEmptyNotifications(new C44126(this));
            return a;
        }

        private void m23249j(int i) {
            this.f21623c = new HashSet();
            for (int i2 = 0; i2 < i; i2++) {
                this.f21623c.add(Integer.valueOf(i2));
            }
        }
    }

    protected class NotificationActivityAdapter extends NotificationsBaseAdapter {
        final /* synthetic */ NotificationsListView f21626c;

        public NotificationActivityAdapter(NotificationsListView notificationsListView) {
            this.f21626c = notificationsListView;
            super(notificationsListView, new NotificationActivityDataSource(notificationsListView));
        }

        protected int mo6787h() {
            return SingApplication.l();
        }

        protected void mo6788i() {
            SingApplication.a(0);
        }

        protected int mo6789j() {
            return 2;
        }
    }

    protected abstract class NotificationsBaseDataSource extends MagicDataSource<NotificationListItem> {
        final /* synthetic */ NotificationsListView f21629b;

        public NotificationsBaseDataSource(NotificationsListView notificationsListView, String str) {
            this.f21629b = notificationsListView;
            super(str);
        }

        public ArrayList<NotificationListItem> mo6791a(List<NotificationListItem> list) {
            mo6792b(list);
            return super.mo6791a((List) list);
        }

        protected void mo6792b(List<NotificationListItem> list) {
            Collection arrayList = new ArrayList();
            for (NotificationListItem notificationListItem : list) {
                if (notificationListItem.a() == Notification$Type.FOLLOW || notificationListItem.a() == Notification$Type.FOLLOW_FB || notificationListItem.a() == Notification$Type.CONNECT_FB) {
                    arrayList.add(Long.valueOf(((AccountIcon) notificationListItem.subjects.get(0)).accountId));
                }
            }
            final int C = this.f21629b.f21669g.m20425C();
            FollowManager.m18204a().m18219a(arrayList, new Runnable(this) {
                final /* synthetic */ NotificationsBaseDataSource f21655b;

                class C44131 implements Runnable {
                    final /* synthetic */ C44141 f21653a;

                    C44131(C44141 c44141) {
                        this.f21653a = c44141;
                    }

                    public void run() {
                        this.f21653a.f21655b.m17666p();
                    }
                }

                public void run() {
                    if (this.f21655b.f21629b.f21669g.m19843a(C)) {
                        this.f21655b.f21629b.f21669g.getActivity().runOnUiThread(new C44131(this));
                    }
                }
            });
        }
    }

    protected class NotificationActivityDataSource extends NotificationsBaseDataSource {
        final /* synthetic */ NotificationsListView f21630a;

        public NotificationActivityDataSource(NotificationsListView notificationsListView) {
            this.f21630a = notificationsListView;
            super(notificationsListView, NotificationActivityDataSource.class.getSimpleName());
        }

        public int mo6242a() {
            return 10;
        }

        public Future<?> mo6243a(int i, int i2, final FetchDataCallback<NotificationListItem> fetchDataCallback) {
            return SocialManager.m18345a().m18350a(Integer.valueOf(i), Integer.valueOf(i2), new ListNotificationsResponseCallback(this) {
                final /* synthetic */ NotificationActivityDataSource f21628b;

                public void handleResponse(ListNotificationsResponse listNotificationsResponse) {
                    if (listNotificationsResponse.a()) {
                        List arrayList = new ArrayList();
                        for (NotificationListItem notificationListItem : listNotificationsResponse.notificationListItems) {
                            if (!(notificationListItem.a() == null || notificationListItem.c() == null)) {
                                arrayList.add(notificationListItem);
                            }
                        }
                        fetchDataCallback.mo6257a(arrayList, listNotificationsResponse.mNext.intValue());
                        return;
                    }
                    fetchDataCallback.mo6256a();
                }
            });
        }
    }

    protected class NotificationInvitesAdapter extends NotificationsBaseAdapter {
        final /* synthetic */ NotificationsListView f21631c;

        public NotificationInvitesAdapter(NotificationsListView notificationsListView) {
            this.f21631c = notificationsListView;
            super(notificationsListView, new NotificationInvitesDataSource(notificationsListView));
        }

        protected int mo6787h() {
            return SingApplication.m();
        }

        protected void mo6788i() {
            SingApplication.b(0);
        }

        protected int mo6789j() {
            return 1;
        }
    }

    class NotificationInvitesDataSource extends NotificationsBaseDataSource {
        final /* synthetic */ NotificationsListView f21637a;

        public NotificationInvitesDataSource(NotificationsListView notificationsListView) {
            this.f21637a = notificationsListView;
            super(notificationsListView, NotificationInvitesDataSource.class.getSimpleName());
        }

        public Future<?> mo6243a(int i, int i2, final FetchDataCallback<NotificationListItem> fetchDataCallback) {
            return InviteManager.m18240a().m18242a(Integer.valueOf(i), Integer.valueOf(i2), "FOLLOWING", new ListInvitesResponseCallback(this) {
                final /* synthetic */ NotificationInvitesDataSource f21633b;

                public void handleResponse(ListInvitesResponse listInvitesResponse) {
                    if (listInvitesResponse.a()) {
                        List arrayList = new ArrayList();
                        Iterator it = listInvitesResponse.mInvites.iterator();
                        while (it.hasNext()) {
                            Invite invite = (Invite) it.next();
                            if (invite.performance.n()) {
                                arrayList.add(new NotificationListItem(invite));
                            }
                        }
                        int size = arrayList.size();
                        if (!this.f21633b.f21637a.f21675m) {
                            size += this.f21633b.m17661k();
                        }
                        this.f21633b.f21637a.f21675m = false;
                        if (listInvitesResponse.mNext.intValue() != -1 || r0 >= 10) {
                            fetchDataCallback.mo6257a(arrayList, listInvitesResponse.mNext.intValue());
                            return;
                        } else {
                            this.f21633b.m23276a(arrayList, fetchDataCallback);
                            return;
                        }
                    }
                    fetchDataCallback.mo6256a();
                    this.f21633b.f21637a.f21675m = false;
                }
            });
        }

        private void m23276a(final List<NotificationListItem> list, final FetchDataCallback<NotificationListItem> fetchDataCallback) {
            PerformanceManager.a().a(PerformancesAPI$SortOrder.SUGGESTED, Integer.valueOf(0), Integer.valueOf(25), PerformancesAPI$FillStatus.ACTIVESEED, null, null, "sing", null, false, false, new PerformanceManager$PerformancesResponseCallback(this) {
                final /* synthetic */ NotificationInvitesDataSource f21636c;

                public void handleResponse(PerformancesResponse performancesResponse) {
                    if (performancesResponse == null || !performancesResponse.a() || performancesResponse.mPerformances == null) {
                        fetchDataCallback.mo6257a(list, -1);
                        return;
                    }
                    Iterator it = performancesResponse.mPerformances.iterator();
                    int i = 0;
                    while (it.hasNext()) {
                        PerformanceV2 performanceV2 = (PerformanceV2) it.next();
                        if (performanceV2.n()) {
                            if (i == 0) {
                                i = 1;
                                NotificationListItem notificationListItem = new NotificationListItem();
                                notificationListItem.count = 0;
                                notificationListItem.type = null;
                                list.add(notificationListItem);
                            }
                            list.add(new NotificationListItem(performanceV2));
                        }
                    }
                    fetchDataCallback.mo6257a(list, -1);
                }
            });
        }
    }

    protected class NotificationsByIdAdapter extends NotificationsBaseAdapter {
        final /* synthetic */ NotificationsListView f21656c;

        public NotificationsByIdAdapter(NotificationsListView notificationsListView, List<String> list) {
            this.f21656c = notificationsListView;
            super(notificationsListView, new NotificationsByIdDataSource(notificationsListView, list, notificationsListView.m23294a((List) list)));
        }

        protected int mo6787h() {
            return 0;
        }

        protected void mo6788i() {
        }

        public int mo6441c(int i) {
            return 2;
        }

        protected int mo6789j() {
            return 2;
        }
    }

    protected class NotificationsByIdDataSource extends NotificationsBaseDataSource {
        final /* synthetic */ NotificationsListView f21661a;
        private List<String> f21662c = null;

        public NotificationsByIdDataSource(NotificationsListView notificationsListView, List<String> list, int i) {
            this.f21661a = notificationsListView;
            super(notificationsListView, NotificationsByIdDataSource.class.getSimpleName() + ":" + i);
            this.f21662c = list;
        }

        public Future<?> mo6243a(final int i, final int i2, final FetchDataCallback<NotificationListItem> fetchDataCallback) {
            return SocialManager.m18345a().m18351a(this.f21662c.subList(i, Math.min(i + i2, this.f21662c.size())), new LookupNotificationsResponseCallback(this) {
                final /* synthetic */ NotificationsByIdDataSource f21660d;

                public void handleResponse(LookupNotificationsResponse lookupNotificationsResponse) {
                    List arrayList = new ArrayList();
                    if (lookupNotificationsResponse == null || !lookupNotificationsResponse.a() || lookupNotificationsResponse.notifications == null) {
                        fetchDataCallback.mo6257a(arrayList, -1);
                        return;
                    }
                    int i;
                    for (Notification notificationListItem : lookupNotificationsResponse.notifications) {
                        NotificationListItem notificationListItem2 = new NotificationListItem(notificationListItem);
                        if (!(notificationListItem2.a() == null || notificationListItem2.c() == null)) {
                            arrayList.add(notificationListItem2);
                        }
                    }
                    if (i + i2 < this.f21660d.f21662c.size()) {
                        i = i + i2;
                    } else {
                        i = -1;
                    }
                    fetchDataCallback.mo6257a(arrayList, i);
                }
            });
        }
    }

    public static NotificationsListView m23295a(Context context, NotificationsFragment notificationsFragment, int i, boolean z) {
        NotificationsListView a = NotificationsListView_.m23305a(context);
        a.f21669g = notificationsFragment;
        a.f21671i = i;
        a.f21672j = z;
        ReferenceMonitor.a().a(a);
        return a;
    }

    public NotificationsListView(Context context) {
        super(context);
    }

    @AfterViews
    protected void m23303a() {
        this.f21666d.setColorSchemeResources(new int[]{C1947R.color.refresh_icon});
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f21673k = this.f21669g.getActivity().getLayoutInflater().inflate(C1947R.layout.perf_count_header, null, false);
        this.f21667e.addFooterView(this.f21673k);
        m23299c();
    }

    private void m23299c() {
        Object obj = null;
        if (this.f21669g.m20423A() != null) {
            this.f21670h = new NotificationsByIdAdapter(this, this.f21669g.m20423A());
            this.f21669g.m20434a(this.f21670h);
        } else if (this.f21671i == 0) {
            this.f21670h = new NotificationActivityAdapter(this);
            this.f21669g.m20434a(this.f21670h);
        } else {
            this.f21670h = new NotificationInvitesAdapter(this);
            this.f21669g.m20435b(this.f21670h);
            obj = 1;
        }
        if (this.f21672j) {
            this.f21669g.mo6488n();
        }
        if (obj == null || !MagicAdSettings.m17435a(NativeAdPlacementType.NOTIFICATIONS_INVITES)) {
            this.f21667e.setMagicAdapter(this.f21670h);
        } else {
            this.f21674l = MagicAdAdapterFactory.m17426a().m17428a(this.f21669g.getActivity(), NativeAdPlacementType.NOTIFICATIONS_INVITES, new Builder(C1947R.layout.native_ad_ghost_notification_invite_item_view).build(), new Builder(C1947R.layout.native_ad_notification_invite_item_view).iconImageId(C1947R.id.notification_invite_ad_icon).titleId(C1947R.id.notification_invite_ad_title).callToActionId(C1947R.id.notification_invite_cta).textId(C1947R.id.notification_invite_ad_text).mainImageId(C1947R.id.notification_invite_ad_main_image).privacyInformationIconImageId(C1947R.id.notification_invite_privacy_icon).build(), new MagicMoPubGhostStreamAdPlacer(this.f21669g.getActivity()), AdUtils.m22219a(null), this.f21667e, this.f21670h, C1947R.id.icn_more, new C43971(this), new C43982(this));
            if (this.f21674l != null) {
                this.f21674l.loadAds();
            } else {
                Log.e(this.f21663a, "mMagicNativeAdMediatorAdapter null");
                this.f21667e.setMagicAdapter(this.f21670h);
            }
        }
        this.f21667e.m18011a(this.f21666d, new C43993(this));
        if (this.f21672j) {
            this.f21669g.mo6485a(this.f21667e, this.f21669g.m20423A() == null ? ActionBarHighlightMode.ALWAYS : ActionBarHighlightMode.NEVER, new C44004(this));
        }
        m23301d();
        this.f21669g.mo6489B();
    }

    private void m23301d() {
        if (this.f21670h != null && this.f21670h.m18048d() > 0) {
            this.f21668f.setVisibility(this.f21670h.m18048d() > 0 ? 0 : 8);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f21667e.removeFooterView(this.f21673k);
        if (this.f21674l != null) {
            this.f21674l.destroy();
            this.f21674l = null;
        }
    }

    private void m23302e() {
        Parcelable d = this.f21669g.m20436d(this.f21671i);
        if (d != null) {
            this.f21667e.onRestoreInstanceState(d);
        }
    }

    @Click
    protected void m23304b() {
        ((MasterActivity) this.f21669g.getActivity()).ad().m22307a(this.f21664b);
    }

    private int m23294a(List<String> list) {
        if (list == null) {
            return 0;
        }
        int i = 0;
        for (String hashCode : list) {
            i = hashCode.hashCode() + (i * 31);
        }
        return i;
    }
}
