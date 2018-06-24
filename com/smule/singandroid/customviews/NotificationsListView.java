/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Fragment
 *  android.content.Context
 *  android.os.Parcelable
 *  android.support.v4.widget.SwipeRefreshLayout
 *  android.support.v4.widget.SwipeRefreshLayout$OnRefreshListener
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  com.mopub.nativeads.MagicMoPubGhostStreamAdPlacer
 *  com.mopub.nativeads.MoPubStreamAdPlacer
 *  com.mopub.nativeads.ViewBinder
 *  com.mopub.nativeads.ViewBinder$Builder
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity$EntryPoint
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity$StartupMode
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_$IntentBuilder_
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer$ActionBarHighlightMode
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$NotificationScreenType
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 *  org.androidannotations.api.builder.PostActivityStarter
 */
package com.smule.singandroid.customviews;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mopub.nativeads.MagicMoPubGhostStreamAdPlacer;
import com.mopub.nativeads.MoPubStreamAdPlacer;
import com.mopub.nativeads.ViewBinder;
import com.smule.android.ads.MagicAdAdapterFactory;
import com.smule.android.ads.MagicAdSettings;
import com.smule.android.ads.nativeAds.MagicNativeAdMediatorAdapter;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.api.PerformancesAPI;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.InviteManager;
import com.smule.android.network.managers.NotificationBadgeManager;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.SocialManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.CursorModel;
import com.smule.android.network.models.Invite;
import com.smule.android.network.models.Notification;
import com.smule.android.network.models.NotificationListItem;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.ReferenceMonitor;
import com.smule.android.utils.UiHandler;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.NotificationsFragment;
import com.smule.singandroid.ads.SingAdSettings;
import com.smule.singandroid.bookmark.BookmarkDialogCallback;
import com.smule.singandroid.bookmark.MediaPlayingMenuManager;
import com.smule.singandroid.common.JoinSectionType;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.customviews.NotificationsListView_;
import com.smule.singandroid.dialogs.NativeAdsMoreDialog;
import com.smule.singandroid.list_items.ActivityNotificationListViewItem;
import com.smule.singandroid.list_items.AggregatedNotificationListViewItem;
import com.smule.singandroid.list_items.InviteNotificationListViewItem;
import com.smule.singandroid.list_items.OpenCallListItem;
import com.smule.singandroid.list_items.PerformanceListEmptyListItem;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity_;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.builder.PostActivityStarter;

@EViewGroup
public class NotificationsListView
extends LinearLayout {
    public static final String b = NotificationActivityDataSource.class.getSimpleName();
    public static final String c = NotificationInvitesDataSource.class.getSimpleName();
    public final String a = NotificationsListView.class.getName();
    @ViewById
    protected View d;
    @ViewById
    protected TextView e;
    @ViewById
    protected SwipeRefreshLayout f;
    @ViewById
    protected MediaListView g;
    @ViewById
    protected View h;
    protected NotificationsFragment i;
    protected NotificationsBaseAdapter j;
    protected int k;
    protected boolean l;
    protected View m;
    private MagicNativeAdMediatorAdapter n;
    private boolean o;

    public NotificationsListView(Context context) {
        super(context);
    }

    private int a(List<String> object) {
        if (object == null) {
            return 0;
        }
        object = object.iterator();
        int n = 0;
        while (object.hasNext()) {
            n = ((String)object.next()).hashCode() + n * 31;
        }
        return n;
    }

    public static NotificationsListView a(Context object, NotificationsFragment notificationsFragment, int n, boolean bl) {
        object = NotificationsListView_.a((Context)object);
        object.i = notificationsFragment;
        object.k = n;
        object.l = bl;
        ReferenceMonitor.a().a(object);
        return object;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void c() {
        block9 : {
            var1_1 = false;
            if (this.i.F() != null) {
                this.j = new NotificationsByIdAdapter(this.i.F());
                this.i.a(this.j);
            } else if (this.k == 0) {
                this.j = new NotificationActivityAdapter();
                this.i.a(this.j);
            } else {
                this.j = new NotificationInvitesAdapter();
                this.i.b(this.j);
                var1_1 = true;
            }
            if (!this.l) break block9;
            this.i.q();
            if (!var1_1) ** GOTO lbl-1000
        }
        if (MagicAdSettings.a(Analytics.d)) {
            var2_2 = new ViewBinder.Builder(2130903301).iconImageId(2131755985).titleId(2131755986).callToActionId(2131755991).textId(2131755990).mainImageId(2131755989).privacyInformationIconImageId(2131755992).build();
            var3_3 = SingAdSettings.a(null);
            this.n = MagicAdAdapterFactory.a().a(this.i.getActivity(), Analytics.d, new ViewBinder.Builder(2130903297).build(), var2_2, (MoPubStreamAdPlacer)new MagicMoPubGhostStreamAdPlacer(this.i.getActivity()), var3_3, this.g, this.j, 2131755976, new View.OnClickListener(){

                public void onClick(View view) {
                    NotificationsListView.this.i.E();
                    new NativeAdsMoreDialog(NotificationsListView.this.i).show();
                }
            }, new Runnable(){

                @Override
                public void run() {
                    NotificationsListView.this.i.E();
                }
            });
            if (this.n != null) {
                this.n.loadAds();
            } else {
                Log.e(this.a, "mMagicNativeAdMediatorAdapter null");
                this.g.setMagicAdapter(this.j);
            }
        } else lbl-1000: // 2 sources:
        {
            this.g.setMagicAdapter(this.j);
        }
        this.g.a(this.f, new SwipeRefreshLayout.OnRefreshListener(){

            public void onRefresh() {
                NotificationsListView.this.o = true;
                NotificationsListView.this.i.I().e();
                NotificationsListView.this.j.f();
                NotificationsListView.this.i.G();
            }
        });
        if (this.l) {
            var2_2 = this.i.F() == null ? QuickReturnListViewMenuSyncer.ActionBarHighlightMode.b : QuickReturnListViewMenuSyncer.ActionBarHighlightMode.a;
            this.i.a((AbsListView)this.g, (QuickReturnListViewMenuSyncer.ActionBarHighlightMode)var2_2, new AbsListView.OnScrollListener(){

                public void onScroll(AbsListView absListView, int n, int n2, int n3) {
                    boolean bl = false;
                    if (NotificationsListView.this.g != null && NotificationsListView.this.g.getChildCount() > 0 && NotificationsListView.this.g.getFirstVisiblePosition() == 0) {
                        n = NotificationsListView.this.g.getChildAt(0).getTop();
                        absListView = NotificationsListView.this.f;
                        if (n >= 0) {
                            bl = true;
                        }
                        absListView.setEnabled(bl);
                        return;
                    }
                    NotificationsListView.this.f.setEnabled(false);
                }

                public void onScrollStateChanged(AbsListView absListView, int n) {
                }
            });
        }
        this.d();
        this.i.G();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void d() {
        if (this.j != null && this.j.d() > 0) {
            View view = this.h;
            int n = this.j.d() > 0 ? 0 : 8;
            view.setVisibility(n);
        }
    }

    private void e() {
        Parcelable parcelable = this.i.d(this.k);
        if (parcelable != null) {
            this.g.onRestoreInstanceState(parcelable);
        }
    }

    @AfterViews
    protected void a() {
        this.f.setColorSchemeResources(new int[]{2131689905});
    }

    @Click
    protected void b() {
        ((MasterActivity)this.i.getActivity()).am().a(this.d);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.m = this.i.getActivity().getLayoutInflater().inflate(2130903346, null, false);
        this.g.addFooterView(this.m);
        this.c();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.g.removeFooterView(this.m);
        if (this.n != null) {
            this.n.destroy();
            this.n = null;
        }
    }

    protected class NotificationActivityAdapter
    extends NotificationsBaseAdapter {
        public NotificationActivityAdapter() {
            super(new NotificationActivityDataSource());
        }

        @Override
        protected int h() {
            return NotificationBadgeManager.a().c();
        }

        @Override
        protected void i() {
            NotificationBadgeManager.a().d();
        }

        @Override
        protected int j() {
            return 2;
        }
    }

    protected class NotificationActivityDataSource
    extends NotificationsBaseDataSource<MagicDataSource.OffsetPaginationTracker> {
        public NotificationActivityDataSource() {
            super(NotificationsListView.this, NotificationsListView.b, (MagicDataSource.PaginationTracker)new MagicDataSource.OffsetPaginationTracker());
        }

        @Override
        public int a() {
            return 10;
        }

        @Override
        public Future<?> a(MagicDataSource.OffsetPaginationTracker offsetPaginationTracker, int n, final MagicDataSource.FetchDataCallback<NotificationListItem, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
            return com.smule.android.network.managers.SocialManager.a().a(offsetPaginationTracker.a(), (Integer)n, new SocialManager.ListNotificationsResponseCallback(){

                @Override
                public void handleResponse(SocialManager listNotificationsResponse) {
                    if (!listNotificationsResponse.a()) {
                        fetchDataCallback.a();
                        return;
                    }
                    ArrayList<NotificationListItem> arrayList = new ArrayList<NotificationListItem>();
                    for (NotificationListItem notificationListItem : listNotificationsResponse.notificationListItems) {
                        if (notificationListItem.a() == null || notificationListItem.c() == null) continue;
                        arrayList.add(notificationListItem);
                    }
                    fetchDataCallback.a(arrayList, new MagicDataSource.OffsetPaginationTracker(listNotificationsResponse.mNext));
                }
            });
        }

    }

    protected class NotificationInvitesAdapter
    extends NotificationsBaseAdapter {
        public NotificationInvitesAdapter() {
            super(new NotificationInvitesDataSource());
        }

        @Override
        protected int h() {
            return NotificationBadgeManager.a().b();
        }

        @Override
        protected void i() {
            NotificationBadgeManager.a().e();
        }

        @Override
        protected int j() {
            return 1;
        }
    }

    class NotificationInvitesDataSource
    extends NotificationsBaseDataSource<MagicDataSource.CursorPaginationTracker> {
        public NotificationInvitesDataSource() {
            super(NotificationsListView.this, NotificationsListView.c, (MagicDataSource.PaginationTracker)new MagicDataSource.CursorPaginationTracker(CursorModel.a()));
        }

        private void a(final List<NotificationListItem> list, final MagicDataSource.FetchDataCallback<NotificationListItem, MagicDataSource.CursorPaginationTracker> fetchDataCallback, final MagicDataSource.CursorPaginationTracker cursorPaginationTracker) {
            com.smule.android.network.managers.PerformanceManager.a().a(PerformancesAPI.SUGGESTED, 0, 25, PerformancesAPI.ACTIVESEED, null, null, "sing", null, false, false, new PerformanceManager(){

                @Override
                public void handleResponse(PerformanceManager.PerformancesResponse object) {
                    if (object == null || !object.a() || object.mPerformances == null) {
                        fetchDataCallback.a(list, cursorPaginationTracker);
                        return;
                    }
                    object = object.mPerformances.iterator();
                    boolean bl = false;
                    while (object.hasNext()) {
                        PerformanceV2 performanceV2 = (PerformanceV2)object.next();
                        if (!performanceV2.p()) continue;
                        boolean bl2 = bl;
                        if (!bl) {
                            bl2 = true;
                            NotificationListItem notificationListItem = new NotificationListItem();
                            notificationListItem.count = 0;
                            notificationListItem.type = null;
                            list.add(notificationListItem);
                        }
                        list.add(new NotificationListItem(performanceV2));
                        bl = bl2;
                    }
                    fetchDataCallback.a(list, cursorPaginationTracker);
                }
            });
        }

        @Override
        public Future<?> a(MagicDataSource.CursorPaginationTracker cursorPaginationTracker, int n, final MagicDataSource.FetchDataCallback<NotificationListItem, MagicDataSource.CursorPaginationTracker> fetchDataCallback) {
            return com.smule.android.network.managers.InviteManager.a().a(cursorPaginationTracker.a(), (Integer)n, new InviteManager.ListInvitesFollowerResponseCallback(){

                @Override
                public void handleResponse(InviteManager listInvitesFollowerResponse) {
                    int n;
                    if (!listInvitesFollowerResponse.a()) {
                        fetchDataCallback.a();
                        NotificationsListView.this.o = false;
                        return;
                    }
                    ArrayList<NotificationListItem> arrayList = new ArrayList<NotificationListItem>();
                    for (Invite invite : listInvitesFollowerResponse.mInvites) {
                        if (!invite.performance.p()) continue;
                        arrayList.add(new NotificationListItem(invite));
                    }
                    int n2 = n = arrayList.size();
                    if (!NotificationsListView.this.o) {
                        n2 = n + NotificationInvitesDataSource.this.k();
                    }
                    NotificationsListView.this.o = false;
                    if (!listInvitesFollowerResponse.mCursor.hasNext && n2 < 10) {
                        NotificationInvitesDataSource.this.a(arrayList, fetchDataCallback, new MagicDataSource.CursorPaginationTracker(listInvitesFollowerResponse.mCursor));
                        return;
                    }
                    fetchDataCallback.a(arrayList, new MagicDataSource.CursorPaginationTracker(listInvitesFollowerResponse.mCursor));
                }
            });
        }

    }

    public abstract class NotificationsBaseAdapter
    extends MagicAdapter {
        private HashSet<Integer> c;
        private int e;

        public NotificationsBaseAdapter(MagicDataSource magicDataSource) {
            super(magicDataSource);
            this.e = -1;
        }

        private void j(int n) {
            this.c = new HashSet();
            for (int i = 0; i < n; ++i) {
                this.c.add(i);
            }
        }

        private void o() {
            if (this.e == -1) {
                this.e = this.h();
                if (this.c == null) {
                    this.j(this.e);
                }
            }
        }

        @Override
        public View a(ViewGroup object, int n) {
            switch (n) {
                default: {
                    Log.e(NotificationsListView.this.a, "getPaginatedView type was not defined");
                    return null;
                }
                case 0: {
                    return AggregatedNotificationListViewItem.a(NotificationsListView.this.getContext());
                }
                case 1: {
                    object = InviteNotificationListViewItem.a(NotificationsListView.this.getContext());
                    object.setOnAlbumArtClickListener(new View.OnClickListener((InviteNotificationListViewItem)object){
                        final /* synthetic */ InviteNotificationListViewItem a;
                        {
                            this.a = inviteNotificationListViewItem;
                        }

                        public void onClick(View object) {
                            object = this.a.getPerformance();
                            Log.b(NotificationsListView.this.a, "mPerformanceItemListener - onAlbumArtClicked called");
                            if (object != null) {
                                NotificationsListView.this.i.a((PerformanceV2)object);
                            }
                        }
                    });
                    object.setOnJoinListener(new View.OnClickListener((InviteNotificationListViewItem)object){
                        final /* synthetic */ InviteNotificationListViewItem a;
                        {
                            this.a = inviteNotificationListViewItem;
                        }

                        public void onClick(View object) {
                            object = this.a.getPerformance();
                            Analytics ensemble = SingAnalytics.a((PerformanceV2)object);
                            SingAnalytics.a((Object)object.performanceKey, this.a.getNotificationListItem().a(), ensemble, (SingAnalytics.NotificationScreenType)NotificationsListView.this.i.K());
                            PreSingActivity.a((Context)NotificationsListView.this.i.getActivity()).a(PreSingActivity.StartupMode.b).a((PerformanceV2)object).a(PreSingActivity.EntryPoint.d).a();
                            SingAnalytics.a((String)PerformanceV2Util.i((PerformanceV2)object), (JoinSectionType)JoinSectionType.a, (String)PerformanceV2Util.h((PerformanceV2)object));
                        }
                    });
                    object.setExpandedPerformanceListener(new OpenCallListItem.ExpandedPerformanceItemListener(){

                        @Override
                        public void a(PerformanceV2 performanceV2, boolean bl) {
                            final MasterActivity masterActivity = (MasterActivity)NotificationsListView.this.i.getActivity();
                            masterActivity.am().a(performanceV2, new BookmarkDialogCallback(){

                                @Override
                                public void a(PerformanceV2 performanceV2) {
                                    new UiHandler((Activity)masterActivity).a(new Runnable(){

                                        @Override
                                        public void run() {
                                            masterActivity.am().a(NotificationsListView.this.i, NotificationsListView.this.d, NotificationsListView.this.e, true);
                                        }
                                    });
                                    NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "BOOKMARKED_PERFORMANCE", performanceV2);
                                }

                                @Override
                                public void b(PerformanceV2 performanceV2) {
                                    new UiHandler((Activity)masterActivity).a(new Runnable(){

                                        @Override
                                        public void run() {
                                            masterActivity.am().a(NotificationsListView.this.i, NotificationsListView.this.d, NotificationsListView.this.e, false);
                                        }
                                    });
                                    NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "FAVORITED_PERFORMANCE", performanceV2);
                                }

                                @Override
                                public void c(PerformanceV2 performanceV2) {
                                    NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "UNBOOKMARKED_PERFORMANCE", performanceV2);
                                }

                                @Override
                                public void d(PerformanceV2 performanceV2) {
                                    NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "UNFAVORITED_PERFORMANCE", performanceV2);
                                }

                            }, bl);
                        }

                        @Override
                        public void a(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
                        }

                        @Override
                        public void b(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
                        }

                        @Override
                        public void c(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
                        }

                        @Override
                        public void d(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
                        }

                    });
                    return object;
                }
                case 2: {
                    object = ActivityNotificationListViewItem.a(NotificationsListView.this.getContext());
                    object.setOnAlbumArtClickListener(new View.OnClickListener((ActivityNotificationListViewItem)object){
                        final /* synthetic */ ActivityNotificationListViewItem a;
                        {
                            this.a = activityNotificationListViewItem;
                        }

                        public void onClick(View object) {
                            object = this.a.getPerformance();
                            Log.b(NotificationsListView.this.a, "mPerformanceItemListener - onAlbumArtClicked called");
                            if (object != null) {
                                boolean bl = MiscUtils.a((PerformanceV2)object);
                                NotificationsListView.this.i.a((PerformanceV2)object, bl, true);
                            }
                        }
                    });
                    return object;
                }
                case 3: 
            }
            return NotificationsListView.this.i.getActivity().getLayoutInflater().inflate(2130903379, (ViewGroup)object, false);
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void a(View view, final int n, int n2) {
            NotificationListItem notificationListItem = (NotificationListItem)this.a(n);
            if (n2 == 3) {
                return;
            }
            boolean bl = this.c != null && this.c.contains(n);
            boolean bl2 = n >= this.d() - 1 || this.c(n + 1) == 3;
            final NotificationsFragment.NotificationItemInterface notificationItemInterface = (NotificationsFragment.NotificationItemInterface)view;
            Log.b(NotificationsListView.this.a, "Calling bind for position = " + n);
            notificationItemInterface.a(NotificationsListView.this.i, NotificationsListView.this.i.getActivity(), notificationListItem, NotificationsListView.this.i.K(), new Runnable(){

                @Override
                public void run() {
                    if (NotificationsBaseAdapter.this.c != null) {
                        NotificationsBaseAdapter.this.c.remove(n);
                    }
                    ((View)notificationItemInterface).setBackgroundResource(2130838153);
                    notificationItemInterface.z_();
                    NotificationsListView.this.i.G();
                }
            }, bl, bl2);
            n = 2130838153;
            if (bl) {
                n = 2130838154;
            }
            view.setBackgroundResource(n);
        }

        @Override
        public long b(int n) {
            return n;
        }

        @Override
        public void b() {
            super.b();
            if (NotificationsListView.this.i == null || !NotificationsListView.this.i.isAdded()) {
                return;
            }
            NotificationsListView.this.d();
            if (!(this.d() <= 0 && this.c() || NotificationsListView.this.k != NotificationsListView.this.i.M())) {
                this.i();
            }
            NotificationsListView.this.i.G();
            NotificationsListView.this.e();
            NotificationsListView.this.i.a(NotificationsListView.this.k, null);
        }

        @Override
        public int c(int n) {
            NotificationListItem notificationListItem = (NotificationListItem)this.a(n);
            if (notificationListItem.count == 0 && notificationListItem.type == null) {
                return 3;
            }
            if ((notificationListItem.a() == Notification.e || notificationListItem.a() == Notification.h || notificationListItem.a() == Notification.i || notificationListItem.a() == Notification.d || notificationListItem.a() == Notification.j) && notificationListItem.count > 1) {
                return 0;
            }
            return this.j();
        }

        @Override
        public View d(ViewGroup object) {
            object = PerformanceListEmptyListItem.a(NotificationsListView.this.getContext());
            object.setModeEmptyNotifications(new View.OnClickListener(){

                public void onClick(View view) {
                    ((MasterActivity)NotificationsListView.this.i.getActivity()).b();
                }
            });
            return object;
        }

        @Override
        public int e() {
            return 4;
        }

        protected abstract int h();

        protected abstract void i();

        protected abstract int j();

        public MagicListView k() {
            return NotificationsListView.this.g;
        }

        public SwipeRefreshLayout l() {
            return NotificationsListView.this.f;
        }

        public int m() {
            this.o();
            return this.e;
        }

        public void n() {
            this.o();
            this.e = 0;
            this.i();
        }

    }

    protected abstract class NotificationsBaseDataSource<PT extends MagicDataSource.PaginationTracker>
    extends MagicDataSource<NotificationListItem, PT> {
        public NotificationsBaseDataSource(PT string2) {
            super(string2, PT);
        }

        @Override
        public ArrayList<NotificationListItem> a(List<NotificationListItem> list) {
            this.b(list);
            return super.a(list);
        }

        @Override
        protected void b(List<NotificationListItem> object) {
            ArrayList<Long> arrayList = new ArrayList<Long>();
            object = object.iterator();
            while (object.hasNext()) {
                NotificationListItem notificationListItem = (NotificationListItem)object.next();
                if (notificationListItem.a() != Notification.e && notificationListItem.a() != Notification.h && notificationListItem.a() != Notification.i) continue;
                arrayList.add(notificationListItem.subjects.get((int)0).accountId);
            }
            final int n = b.i.H();
            FollowManager.a().a(arrayList, new Runnable(){

                @Override
                public void run() {
                    if (!b.i.a(n)) {
                        return;
                    }
                    b.i.getActivity().runOnUiThread(new Runnable(){

                        @Override
                        public void run() {
                            NotificationsBaseDataSource.this.p();
                        }
                    });
                }

            });
        }

    }

    protected class NotificationsByIdAdapter
    extends NotificationsBaseAdapter {
        public NotificationsByIdAdapter(List<String> list) {
            super(new NotificationsByIdDataSource(list, NotificationsListView.this.a(list)));
        }

        @Override
        public int c(int n) {
            return 2;
        }

        @Override
        protected int h() {
            return 0;
        }

        @Override
        protected void i() {
        }

        @Override
        protected int j() {
            return 2;
        }
    }

    protected class NotificationsByIdDataSource
    extends NotificationsBaseDataSource<MagicDataSource.OffsetPaginationTracker> {
        private List<String> l;

        public NotificationsByIdDataSource(List<String> list, int n) {
            super(NotificationsListView.this, NotificationsByIdDataSource.class.getSimpleName() + ":" + n, (MagicDataSource.PaginationTracker)new MagicDataSource.OffsetPaginationTracker());
            this.l = null;
            this.l = list;
        }

        @Override
        public Future<?> a(final MagicDataSource.OffsetPaginationTracker offsetPaginationTracker, final int n, final MagicDataSource.FetchDataCallback<NotificationListItem, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
            int n2 = Math.min(offsetPaginationTracker.a() + n, this.l.size());
            List<String> list = this.l.subList(offsetPaginationTracker.a(), n2);
            return com.smule.android.network.managers.SocialManager.a().a(list, new SocialManager.LookupNotificationsResponseCallback(){

                /*
                 * Enabled aggressive block sorting
                 */
                @Override
                public void handleResponse(SocialManager iterator) {
                    ArrayList<NotificationListItem> arrayList = new ArrayList<NotificationListItem>();
                    if (iterator == null || !iterator.a() || iterator.notifications == null) {
                        fetchDataCallback.a(arrayList, new MagicDataSource.OffsetPaginationTracker(-1));
                        return;
                    }
                    iterator = iterator.notifications.iterator();
                    while (iterator.hasNext()) {
                        NotificationListItem notificationListItem = new NotificationListItem(iterator.next());
                        if (notificationListItem.a() == null || notificationListItem.c() == null) continue;
                        arrayList.add(notificationListItem);
                    }
                    int n2 = offsetPaginationTracker.a() + n < NotificationsByIdDataSource.this.l.size() ? offsetPaginationTracker.a() + n : -1;
                    fetchDataCallback.a(arrayList, new MagicDataSource.OffsetPaginationTracker(n2));
                }
            });
        }

    }

}

