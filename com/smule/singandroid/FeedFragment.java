package com.smule.singandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.TextView;
import com.mopub.nativeads.MagicMoPubGhostStreamAdPlacer;
import com.mopub.nativeads.ViewBinder.Builder;
import com.smule.android.ads.MagicAdAdapterFactory;
import com.smule.android.ads.MagicAdSettings;
import com.smule.android.ads.nativeAds.MagicNativeAdMediatorAdapter;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.ItemClickType;
import com.smule.android.logging.Analytics.NativeAdPlacementType;
import com.smule.android.logging.Analytics.RecSysContext;
import com.smule.android.logging.Analytics.RecommendationType;
import com.smule.android.logging.Analytics.SearchClkContext;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.DataSourceObserver;
import com.smule.android.magicui.lists.adapters.MagicDataSource.DataSourceObserverObject;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.FeedListItem;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.UiHandler;
import com.smule.singandroid.BaseFragment.BaseFragmentResponder;
import com.smule.singandroid.ads.AdUtils;
import com.smule.singandroid.bookmark.BookmarkDialogCallback;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.dialogs.NativeAdsMoreDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.fragments.JoinersListFragment;
import com.smule.singandroid.list_items.FeedListFriendsNotificationView;
import com.smule.singandroid.list_items.FeedListFriendsNotificationView.ActionListener;
import com.smule.singandroid.list_items.FeedListViewItem;
import com.smule.singandroid.list_items.FeedListViewItem.FeedListItemFeedback;
import com.smule.singandroid.list_items.FeedListViewItem.FeedListViewItemClickCallback;
import com.smule.singandroid.list_items.MediaPlayingListItem;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;
import com.smule.singandroid.sections.feed.FeedDataSource;
import com.smule.singandroid.songbook_search.SearchFragment;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer.ActionBarHighlightMode;
import com.smule.singandroid.utils.ShareUtils;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.FilterType;
import com.smule.singandroid.utils.SingAnalytics.SectionType;
import java.text.MessageFormat;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment
public class FeedFragment extends BaseFragment {
    public static final String f18666e = FeedFragment.class.getName();
    private boolean f18667A = false;
    private BookmarkDialogCallback f18668B = new C38289(this);
    @ViewById
    protected MediaListView f18669f;
    @ViewById
    protected SwipeRefreshLayout f18670g;
    @ViewById
    protected View f18671h;
    @ViewById
    protected TextView f18672i;
    protected FeedAdapter f18673j;
    @InstanceState
    protected int f18674k = -1;
    FeedListFriendsNotificationView f18675l;
    int f18676m;
    int f18677n;
    int f18678o;
    int f18679p;
    int f18680q = -1;
    DataSourceObserver f18681r = new C38192(this);
    private MagicNativeAdMediatorAdapter f18682s;
    private Observer f18683t = new C38181(this);
    private ConcurrentHashMap<String, Boolean> f18684u = new ConcurrentHashMap();
    private FeedRecsysCallback f18685v;
    private String f18686w = "";
    private String f18687x = "";
    private String f18688y = "";
    private String f18689z = "";

    class C38181 implements Observer {
        final /* synthetic */ FeedFragment f18637a;

        C38181(FeedFragment feedFragment) {
            this.f18637a = feedFragment;
        }

        public void update(Observable observable, Object obj) {
            String str = (String) obj;
            Log.b(FeedFragment.f18666e, "mLoveGivenObserver - update - notification received for performance with key: " + str);
            Pair b = this.f18637a.f18673j.m20096b(str);
            if (b != null) {
                Analytics.m17860a((String) b.second, ItemClickType.LOVE, ((Integer) b.first).intValue(), RecSysContext.FEED, null);
            }
        }
    }

    class C38192 extends DataSourceObserverObject {
        final /* synthetic */ FeedFragment f18638a;

        C38192(FeedFragment feedFragment) {
            this.f18638a = feedFragment;
        }

        public void mo6254c(MagicDataSource magicDataSource) {
        }

        public void mo6251a(MagicDataSource magicDataSource) {
            this.f18638a.f18688y = this.f18638a.f18686w;
            this.f18638a.f18689z = this.f18638a.f18687x;
        }

        public void mo6253b(MagicDataSource magicDataSource) {
        }
    }

    public interface FeedRecsysCallback {
        void mo6424a(String str, String str2, boolean z);
    }

    class C38203 implements FeedRecsysCallback {
        boolean f18639a = false;
        final /* synthetic */ FeedFragment f18640b;

        C38203(FeedFragment feedFragment) {
            this.f18640b = feedFragment;
        }

        public synchronized void mo6424a(String str, String str2, boolean z) {
            if (this.f18640b.isAdded()) {
                if (z) {
                    this.f18640b.f18686w = str;
                    this.f18640b.f18687x = str2;
                }
                if (this.f18640b.f18688y.isEmpty()) {
                    this.f18640b.f18688y = str;
                    this.f18640b.f18689z = str2;
                } else if (z) {
                    this.f18640b.f18688y = str + "," + this.f18640b.f18688y;
                    this.f18640b.f18689z = str2 + "," + this.f18640b.f18689z;
                } else {
                    this.f18640b.f18688y = this.f18640b.f18688y + "," + str;
                    this.f18640b.f18689z = this.f18640b.f18689z + "," + str2;
                }
                if (this.f18639a || MagicPreferences.m20307a(this.f18640b.getActivity())) {
                    Analytics.m17870a(this.f18640b.f18688y, this.f18640b.f18689z, RecommendationType.FEED, RecSysContext.FEED, null);
                    this.f18640b.f18667A = true;
                } else {
                    this.f18639a = true;
                }
            }
        }
    }

    class C38214 implements OnClickListener {
        final /* synthetic */ FeedFragment f18641a;

        C38214(FeedFragment feedFragment) {
            this.f18641a = feedFragment;
        }

        public void onClick(View view) {
            this.f18641a.m19874y();
            new NativeAdsMoreDialog(this.f18641a).show();
        }
    }

    class C38225 implements Runnable {
        final /* synthetic */ FeedFragment f18642a;

        C38225(FeedFragment feedFragment) {
            this.f18642a = feedFragment;
        }

        public void run() {
            this.f18642a.m19874y();
        }
    }

    class C38236 implements OnScrollListener {
        final /* synthetic */ FeedFragment f18643a;

        C38236(FeedFragment feedFragment) {
            this.f18643a = feedFragment;
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            this.f18643a.f18678o = i;
            this.f18643a.f18679p = i2;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            this.f18643a.f18680q = i;
            m20054a();
        }

        private void m20054a() {
            if (this.f18643a.f18679p > 0 && this.f18643a.f18680q == 0) {
                if (this.f18643a.f18678o != this.f18643a.f18676m || this.f18643a.f18679p != this.f18643a.f18677n) {
                    this.f18643a.m20102G();
                }
            }
        }
    }

    class C38258 implements OnMenuItemClickListener {
        final /* synthetic */ FeedFragment f18646a;

        C38258(FeedFragment feedFragment) {
            this.f18646a = feedFragment;
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            MagicPreferences.m20315b(this.f18646a.getActivity(), true);
            this.f18646a.mo6452C();
            return false;
        }
    }

    class C38289 implements BookmarkDialogCallback {
        final /* synthetic */ FeedFragment f18651a;

        C38289(FeedFragment feedFragment) {
            this.f18651a = feedFragment;
        }

        public void mo6428a(PerformanceV2 performanceV2) {
            final Activity activity = (MasterActivity) this.f18651a.getActivity();
            new UiHandler(activity).m19081a(new Runnable(this) {
                final /* synthetic */ C38289 f18648b;

                public void run() {
                    activity.ad().m22306a(this.f18648b.f18651a, this.f18648b.f18651a.f18671h, this.f18648b.f18651a.f18672i, true);
                }
            });
            NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "BOOKMARKED_PERFORMANCE", performanceV2);
        }

        public void mo6429b(PerformanceV2 performanceV2) {
            final Activity activity = (MasterActivity) this.f18651a.getActivity();
            new UiHandler(activity).m19081a(new Runnable(this) {
                final /* synthetic */ C38289 f18650b;

                public void run() {
                    activity.ad().m22306a(this.f18650b.f18651a, this.f18650b.f18651a.f18671h, this.f18650b.f18651a.f18672i, false);
                }
            });
            NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "FAVORITED_PERFORMANCE", performanceV2);
        }

        public void mo6430c(PerformanceV2 performanceV2) {
            NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "UNBOOKMARKED_PERFORMANCE", performanceV2);
        }

        public void mo6431d(PerformanceV2 performanceV2) {
            NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "UNFAVORITED_PERFORMANCE", performanceV2);
        }
    }

    protected class FeedAdapter extends MagicAdapter {
        boolean f18663c = false;
        public final int f18664d = 0;
        final /* synthetic */ FeedFragment f18665e;

        public FeedAdapter(FeedFragment feedFragment, MagicDataSource magicDataSource) {
            this.f18665e = feedFragment;
            super(magicDataSource);
        }

        public int mo6442e() {
            return 1;
        }

        public int mo6441c(int i) {
            return 0;
        }

        public void mo6254c(MagicDataSource magicDataSource) {
            super.mo6254c(magicDataSource);
            if (m18026a().m17661k() > 0 && !this.f18663c) {
                this.f18663c = true;
                this.f18665e.m20101F();
            }
        }

        private boolean m20093a(FeedListItem feedListItem, int i) {
            if (!(feedListItem.object == null || feedListItem.object.performanceIcon == null)) {
                String str = feedListItem.object.performanceIcon.performanceKey;
                if (str != null && MediaPlayerServiceController.m24641a().m24662c(str) && this.f18665e.f18674k == -1) {
                    this.f18665e.f18674k = i;
                }
            }
            return this.f18665e.f18674k == i;
        }

        public View mo6418a(ViewGroup viewGroup, int i) {
            return FeedListViewItem.m24290a(this.f18665e.getActivity());
        }

        public void mo6419a(View view, final int i, int i2) {
            final FeedListItem feedListItem = (FeedListItem) m18027a(i);
            final FeedListViewItem feedListViewItem = (FeedListViewItem) view;
            boolean a = m20093a(feedListItem, i);
            if (!a) {
                feedListViewItem.m24321k();
            }
            feedListViewItem.m24307a(this.f18665e, feedListItem, a, new FeedListItemFeedback(this) {
                final /* synthetic */ FeedAdapter f18654c;

                public void mo6432a() {
                    this.f18654c.m20100h();
                }

                public void mo6434a(AccountIcon accountIcon) {
                    mo6433a(ItemClickType.PROFILE);
                    this.f18654c.f18665e.mo6487a(ProfileFragment.m21036a(accountIcon));
                }

                public void mo6436a(PerformanceV2 performanceV2) {
                    mo6433a(ItemClickType.GROUP);
                    this.f18654c.f18665e.mo6487a(JoinersListFragment.m23755a(performanceV2));
                }

                public void mo6438b(PerformanceV2 performanceV2) {
                    mo6433a(ItemClickType.JOIN);
                    PreSingActivity.m24763a(this.f18654c.f18665e.getActivity()).m24796a(StartupMode.OPENCALL).m24793a(feedListItem.object.performanceIcon).a();
                    SingAnalytics.m26081a(performanceV2.video ? FilterType.f24970b : FilterType.NONE, SectionType.NETWORK);
                }

                public void mo6435a(ArrangementVersionLite arrangementVersionLite) {
                    mo6433a(ItemClickType.SING);
                    PreSingActivity.m24763a(this.f18654c.f18665e.getActivity()).m24796a(StartupMode.DEFAULT).m24794a(SongbookEntry.m18744a(arrangementVersionLite)).m24797a("feed").a();
                }

                public void mo6437b(ArrangementVersionLite arrangementVersionLite) {
                    mo6433a(ItemClickType.SHARE);
                    ShareUtils.m26026f(this.f18654c.f18665e.getActivity(), arrangementVersionLite);
                }

                public void mo6439c(PerformanceV2 performanceV2) {
                    ((MediaPlayingActivity) this.f18654c.f18665e.getActivity()).ad().m22308a(performanceV2, this.f18654c.f18665e.f18668B, false);
                }

                public void mo6433a(ItemClickType itemClickType) {
                    SongbookEntry a;
                    if (itemClickType != ItemClickType.LOVE) {
                        Analytics.m17860a(feedListItem.recId, itemClickType, i, RecSysContext.FEED, null);
                    }
                    if (feedListItem.c()) {
                        a = SongbookEntry.m18744a(feedListItem.object.arrVersionLite);
                    } else {
                        a = m20077d(feedListItem.object.performanceIcon);
                    }
                    if (itemClickType == ItemClickType.SING) {
                        SingAnalytics.m26133c(a);
                    }
                }

                private SongbookEntry m20077d(PerformanceV2 performanceV2) {
                    if (!performanceV2.r()) {
                        return SongbookEntry.m18747a(performanceV2);
                    }
                    ArrangementVersionLite arrangementVersionLite = new ArrangementVersionLite();
                    arrangementVersionLite.key = performanceV2.arrKey;
                    arrangementVersionLite.name = performanceV2.title;
                    arrangementVersionLite.coverUrl = performanceV2.coverUrl;
                    return SongbookEntry.m18744a(arrangementVersionLite);
                }
            });
            feedListViewItem.m24309a(new FeedListViewItemClickCallback(this) {
                final /* synthetic */ FeedAdapter f18657c;

                public void mo6440a() {
                    if (!feedListViewItem.m24314d()) {
                        this.f18657c.m20092a(feedListViewItem, i);
                    }
                }
            }, new FeedListViewItemClickCallback(this) {
                final /* synthetic */ FeedAdapter f18660c;

                public void mo6440a() {
                    this.f18660c.m20092a(feedListViewItem, i);
                }
            });
            feedListViewItem.setTag(Integer.valueOf(i));
        }

        private void m20092a(final FeedListViewItem feedListViewItem, int i) {
            boolean z = true;
            if (this.f18665e.getActivity() != null) {
                MasterActivity masterActivity = (MasterActivity) this.f18665e.getActivity();
                boolean z2 = masterActivity.aa() || masterActivity.ab();
                String str = feedListViewItem.getPerformance() != null ? feedListViewItem.getPerformance().performanceKey : null;
                if (this.f18665e.f18674k != i || !z2 || !MediaPlayerServiceController.m24641a().m24662c(str)) {
                    FeedListViewItem feedListViewItem2 = (FeedListViewItem) this.f18665e.f18669f.findViewWithTag(Integer.valueOf(this.f18665e.f18674k));
                    this.f18665e.f18674k = i;
                    if (feedListViewItem2 != null) {
                        if (feedListViewItem2.getPerformance() == null || !feedListViewItem2.m23037a(feedListViewItem2.getPerformance())) {
                            feedListViewItem2.startAnimation(feedListViewItem2.getCollapseViewAnimation());
                        }
                        feedListViewItem2.m24322l();
                        feedListViewItem2.setIsExpanded(false);
                    }
                    feedListViewItem.setIsExpanded(true);
                    String str2 = FeedFragment.f18666e;
                    StringBuilder append = new StringBuilder().append("View item is video ");
                    if (feedListViewItem.getPerformance() == null || !feedListViewItem.getPerformance().video) {
                        z = false;
                    }
                    Log.b(str2, append.append(z).toString());
                    if (feedListViewItem.getPerformance() == null || !feedListViewItem.m23037a(feedListViewItem.getPerformance())) {
                        Animation expandViewAnimation = feedListViewItem.getExpandViewAnimation();
                        expandViewAnimation.setAnimationListener(new AnimationListener(this) {
                            final /* synthetic */ FeedAdapter f18662b;

                            public void onAnimationStart(Animation animation) {
                            }

                            public void onAnimationEnd(Animation animation) {
                                if (this.f18662b.f18665e.isAdded()) {
                                    this.f18662b.m20091a(feedListViewItem);
                                }
                            }

                            public void onAnimationRepeat(Animation animation) {
                            }
                        });
                        feedListViewItem.startAnimation(expandViewAnimation);
                        return;
                    }
                    m20091a(feedListViewItem);
                } else if (feedListViewItem.m23043q() || !feedListViewItem.m23046t()) {
                    feedListViewItem.mo6881u();
                }
            }
        }

        private void m20091a(FeedListViewItem feedListViewItem) {
            if (!feedListViewItem.m24313c()) {
                feedListViewItem.m24319i();
                feedListViewItem.m24306a(this.f18665e);
            } else if (!feedListViewItem.m23046t()) {
                feedListViewItem.setAlbumArtClickedState(true);
                if (this.f18665e.f18684u == null) {
                    this.f18665e.f18684u = new ConcurrentHashMap();
                }
                SongbookEntry a = SongbookEntry.m18744a(feedListViewItem.getArrVersionLite());
                if (this.f18665e.f18684u.containsKey(a.mo6289c())) {
                    this.f18665e.m20109a(((Boolean) this.f18665e.f18684u.get(a.mo6289c())).booleanValue(), (ArrangementVersionLiteEntry) a, feedListViewItem);
                    return;
                }
                int i = ((ArrangementVersionLiteEntry) a).m18774a().removalCode;
                boolean z = i >= 40 && i <= 49;
                this.f18665e.f18684u.put(a.mo6289c(), Boolean.valueOf(z));
                this.f18665e.m20109a(z, (ArrangementVersionLiteEntry) a, feedListViewItem);
            }
        }

        public void m20100h() {
            int childCount = this.f18665e.f18669f.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = this.f18665e.f18669f.getChildAt(i);
                if (childAt != null && (childAt instanceof FeedListViewItem)) {
                    ((FeedListViewItem) childAt).mo6875a(true, false, false);
                }
            }
        }

        public Pair<Integer, String> m20096b(String str) {
            FeedDataSource feedDataSource = (FeedDataSource) m18026a();
            int i = 0;
            while (i < feedDataSource.m17661k()) {
                FeedListItem feedListItem = (FeedListItem) feedDataSource.m17641a(i);
                if (feedListItem.object == null || feedListItem.object.performanceIcon == null || !feedListItem.object.performanceIcon.performanceKey.equals(str)) {
                    i++;
                } else {
                    PerformanceV2 performanceV2 = feedListItem.object.performanceIcon;
                    performanceV2.totalLoves++;
                    feedDataSource.m17666p();
                    return new Pair(Integer.valueOf(i), feedListItem.recId);
                }
            }
            return null;
        }
    }

    public static FeedFragment m20104a() {
        return FeedFragment_.m20140F().m20139a();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        this.f18673j = new FeedAdapter(this, new FeedDataSource());
        this.f18673j.mo6858a(this.f18681r);
    }

    public boolean mo6446i() {
        return false;
    }

    @AfterViews
    protected void m20138z() {
        this.f18685v = new C38203(this);
        if (!MagicPreferences.m20307a(getActivity())) {
            m20123B();
        }
        if (MagicAdSettings.m17435a(NativeAdPlacementType.FEED)) {
            this.f18682s = MagicAdAdapterFactory.m17426a().m17428a(getActivity(), NativeAdPlacementType.FEED, new Builder(C1947R.layout.native_ad_ghost_feed_item_view).build(), new Builder(C1947R.layout.native_ad_feed_item_view).iconImageId(C1947R.id.feed_ad_icon).titleId(C1947R.id.feed_ad_title).callToActionId(C1947R.id.feed_ad_cta).textId(C1947R.id.feed_ad_text).mainImageId(C1947R.id.feed_ad_main_image).privacyInformationIconImageId(C1947R.id.feed_ad_privacy_icon).build(), new MagicMoPubGhostStreamAdPlacer(getActivity()), AdUtils.m22219a(null), this.f18669f, this.f18673j, C1947R.id.icn_more, new C38214(this), new C38225(this));
            if (this.f18682s != null) {
                this.f18682s.loadAds();
            } else {
                Log.e(f18666e, "mMagicNativeAdMediatorAdapter null");
                this.f18669f.setMagicAdapter(this.f18673j);
            }
        } else {
            this.f18669f.setMagicAdapter(this.f18673j);
        }
        mo6451A();
        this.f18670g.setColorSchemeResources(new int[]{C1947R.color.refresh_icon});
        this.f18669f.setSwipeRefreshLayout(this.f18670g);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f18675l = null;
        if (this.f18682s != null) {
            this.f18682s.destroy();
            this.f18682s = null;
        }
    }

    @UiThread
    protected void mo6451A() {
        if (isAdded()) {
            mo6485a(this.f18669f, ActionBarHighlightMode.NEVER, new C38236(this));
        }
    }

    protected void m20123B() {
        final View a = FeedListFriendsNotificationView.m24271a(getActivity(), this.f18685v);
        a.setActionListener(new ActionListener(this) {
            final /* synthetic */ FeedFragment f18645b;

            public void mo6425a() {
                this.f18645b.mo6452C();
                MagicPreferences.m20305a(this.f18645b.getActivity(), true);
            }

            public void mo6426b() {
                this.f18645b.mo6453D();
                MagicPreferences.m20305a(this.f18645b.getActivity(), true);
            }

            public void mo6427c() {
                this.f18645b.f18669f.removeHeaderView(a);
                this.f18645b.f18675l = null;
                MagicPreferences.m20305a(this.f18645b.getActivity(), true);
                this.f18645b.f18686w = "";
                this.f18645b.f18687x = "";
            }
        });
        this.f18669f.addHeaderView(a);
        this.f18675l = a;
    }

    public void onStart() {
        super.onStart();
        m19850c((int) C1947R.string.core_feed);
        NotificationCenter.m19011a().m19014a("LOVE_GIVEN", this.f18683t);
        FeedListViewItem feedListViewItem = (FeedListViewItem) this.f18669f.findViewWithTag(Integer.valueOf(this.f18674k));
        if (feedListViewItem == null || !MediaPlayerServiceController.m24641a().m24662c(feedListViewItem.getMediaKey())) {
            this.f18674k = -1;
        }
    }

    public void onResume() {
        super.onResume();
        if (this.f18675l != null && this.f18675l.isShown()) {
            this.f18675l.m24279f();
        }
        if (!MediaPlayerServiceController.m24641a().m24682m()) {
            this.f18674k = -1;
        }
        MediaPlayingListItem.m23035b(this.f18669f);
        MediaPlayingListItem.m23034a(this.f18669f);
        if (this.f18667A && !m20102G() && this.f18678o == 0) {
            m20101F();
        }
    }

    public void mo6449u() {
        super.mo6449u();
        MediaPlayingListItem.m23035b(this.f18669f);
        MediaPlayingListItem.m23034a(this.f18669f);
    }

    public void onStop() {
        super.onStop();
        NotificationCenter.m19011a().m19016b("LOVE_GIVEN", this.f18683t);
        MediaPlayerServiceController.m24641a().m24687r();
    }

    protected void mo6420v() {
        SingAnalytics.m26157u();
    }

    protected boolean mo6450x() {
        return false;
    }

    public boolean mo6444d() {
        return false;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        Log.b(f18666e, "onCreateOptionsMenu");
        menuInflater.inflate(C1947R.menu.feed_fragment_menu, menu);
        m20127a(menu);
    }

    protected void m20127a(Menu menu) {
        Drawable drawable;
        MenuItem findItem = menu.findItem(C1947R.id.action_find_friends);
        Drawable drawable2 = getResources().getDrawable(C1947R.drawable.action_bar_icon_find_friends);
        if (MagicPreferences.m20316b(getActivity())) {
            drawable = drawable2;
        } else {
            drawable = new IconWithNotificationDrawable(getActivity(), getResources().getDimensionPixelSize(C1947R.dimen.margin_small), -getResources().getDimensionPixelSize(C1947R.dimen.margin_compound_drawable), getResources().getDimensionPixelSize(C1947R.dimen.action_bar_icon_find_friend_padding), new Drawable[]{drawable2});
        }
        findItem.setIcon(drawable);
        findItem.setOnMenuItemClickListener(new C38258(this));
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1947R.id.action_search:
                Analytics.m17848a(SearchClkContext.FEED);
                mo6487a(SearchFragment.m25251A());
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @SupposeUiThread
    protected void mo6452C() {
        mo6929a(FindFriendsFragment.m20167a(), FindFriendsFragment.f18701e);
    }

    @SupposeUiThread
    protected void mo6453D() {
        mo6487a(FacebookFriendsFragment.m20024a());
    }

    public String mo6383s() {
        return f18666e;
    }

    public boolean mo6445g() {
        return true;
    }

    @Click
    protected void m20126E() {
        ((MediaPlayingActivity) getActivity()).ad().m22307a(this.f18671h);
    }

    private Pair<String, String> m20103a(int i, int i2) {
        int i3 = 0;
        if (this.f18669f == null || this.f18669f.getAdapter() == null) {
            return null;
        }
        int min = Math.min(0, i - this.f18669f.getHeaderViewsCount()) * -1;
        int headerViewsCount = i - this.f18669f.getHeaderViewsCount();
        if (min > 0) {
            i2 -= min;
            headerViewsCount = 0;
        }
        if (i2 <= 0 || this.f18673j.m18048d() < headerViewsCount + i2) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder("");
        StringBuilder stringBuilder2 = new StringBuilder("");
        while (i3 < i2) {
            FeedListItem feedListItem = (FeedListItem) this.f18673j.m18027a(headerViewsCount + i3);
            if (feedListItem.recId != null) {
                stringBuilder.append((headerViewsCount + i3) + this.f18669f.getHeaderViewsCount());
                stringBuilder2.append(feedListItem.recId);
                if (i3 + 1 < i2) {
                    stringBuilder.append(",");
                    stringBuilder2.append(",");
                }
            }
            i3++;
        }
        this.f18676m = this.f18678o;
        this.f18677n = this.f18679p;
        return new Pair(stringBuilder2.toString(), stringBuilder.toString());
    }

    private void m20101F() {
        Context activity = getActivity();
        if (activity != null) {
            Pair a;
            if (LayoutUtils.m25858a(activity)) {
                a = m20103a(0, 3);
            } else {
                a = m20103a(0, 2);
            }
            if (a != null) {
                this.f18685v.mo6424a((String) a.first, (String) a.second, false);
            }
        }
    }

    private boolean m20102G() {
        Pair a = m20103a(this.f18678o, this.f18679p);
        if (a != null) {
            String str = (String) a.first;
            String str2 = (String) a.second;
            if (this.f18678o == 0 && !this.f18686w.isEmpty()) {
                str = this.f18686w + "," + str;
                str2 = this.f18687x + "," + str2;
            }
            Analytics.m17870a(str, str2, RecommendationType.FEED, RecSysContext.FEED, null);
        }
        return a != null;
    }

    public void mo6443a(SongbookEntry songbookEntry) {
        Log.b(mo6383s(), "playPreview - called");
        BaseFragmentResponder m = m19862m();
        if (m != null) {
            m.a(songbookEntry, false, null);
        }
    }

    private void m20109a(boolean z, ArrangementVersionLiteEntry arrangementVersionLiteEntry, FeedListViewItem feedListViewItem) {
        this.f18684u.put(arrangementVersionLiteEntry.mo6289c(), Boolean.valueOf(z));
        if (z) {
            MediaPlayerServiceController.m24641a().m24660b(feedListViewItem.getMediaKey());
            m20129a(feedListViewItem);
            return;
        }
        Analytics.m17867a("feed", m20111b((SongbookEntry) arrangementVersionLiteEntry), arrangementVersionLiteEntry.mo6294h(), arrangementVersionLiteEntry.m18770p(), null, SongbookEntry.m18749a((SongbookEntry) arrangementVersionLiteEntry));
        feedListViewItem.m24320j();
        mo6443a((SongbookEntry) arrangementVersionLiteEntry);
    }

    protected void m20129a(FeedListViewItem feedListViewItem) {
        if (isAdded()) {
            feedListViewItem.setAlbumArtClickedState(false);
            TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(C1947R.string.arrangement_copyright_violation_title), MessageFormat.format(getString(C1947R.string.arrangement_copyright_violation_detail), new Object[]{getString(C1947R.string.performance_copyright_violation_email)}), true, false);
            textAlertDialog.m19806a(getString(C1947R.string.core_ok), null);
            textAlertDialog.show();
        }
    }

    private String m20111b(SongbookEntry songbookEntry) {
        if (!songbookEntry.m18772r()) {
            return songbookEntry.mo6290d();
        }
        ArrangementVersionLiteEntry arrangementVersionLiteEntry = (ArrangementVersionLiteEntry) songbookEntry;
        return arrangementVersionLiteEntry.m18777b() ? "-" : arrangementVersionLiteEntry.f17623a.songId;
    }

    protected boolean mo6447j() {
        return true;
    }
}
