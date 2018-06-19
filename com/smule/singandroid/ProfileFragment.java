package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.ItemClickType;
import com.smule.android.logging.Analytics.SearchClkContext;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.api.PerformancesAPI$FillStatus;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.FollowManager.ToggleFollowStateListener;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager$PerformanceResponseCallback;
import com.smule.android.network.managers.PerformanceManager.PerformanceResponse;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.UserManager$GetUserBlurbResponseCallback;
import com.smule.android.network.managers.UserManager$UserProfileResponseCallback;
import com.smule.android.network.managers.UserManager.UserBlurbResponse;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.UserProfile;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.uploader.FileUploaderService;
import com.smule.android.uploader.FileUploaderService.FileUploaderBinder;
import com.smule.android.uploader.FileUploaderService.VideoUploadStatus;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.OperationLoader;
import com.smule.android.utils.OperationLoader.Operation;
import com.smule.android.utils.UiHandler;
import com.smule.chat.Chat;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.PeerChat;
import com.smule.singandroid.adapters.profile.ProfileArrangementDataSource;
import com.smule.singandroid.adapters.profile.ProfileOpenCallDataSource;
import com.smule.singandroid.adapters.profile.ProfilePerformanceDataSource;
import com.smule.singandroid.bookmark.BookmarkDialogCallback;
import com.smule.singandroid.chat.ChatAnalytics;
import com.smule.singandroid.chat.ChatFragment;
import com.smule.singandroid.chat.MessageCenterFragment;
import com.smule.singandroid.chat.activator.ChatActivator.ChatActivatorListener;
import com.smule.singandroid.chat.activator.ChatActivatorDialog;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.ProfileListView;
import com.smule.singandroid.customviews.ProfileListView.FavoriteAdapter;
import com.smule.singandroid.customviews.ProfileListView.InviteAdapter;
import com.smule.singandroid.customviews.ProfileListView.OwnedArrangementsAdapter;
import com.smule.singandroid.customviews.ProfileListView.PerfAdapter;
import com.smule.singandroid.customviews.SingTabLayoutHelper;
import com.smule.singandroid.customviews.StorageWarningView;
import com.smule.singandroid.datasource.BaseProfileDataSource;
import com.smule.singandroid.dialogs.BusyScreenDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.fragments.FlagUserFragment;
import com.smule.singandroid.fragments.OpenCallPerformancesListFragment;
import com.smule.singandroid.list_items.ListingListItem;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.list_items.OpenCallListItem;
import com.smule.singandroid.list_items.OpenCallListItem.ExpandedPerformanceItemListener;
import com.smule.singandroid.list_items.PerformanceItemInterface.PerformanceItemListener;
import com.smule.singandroid.list_items.ProfileUserInfo;
import com.smule.singandroid.list_items.ProfileUserInfoView;
import com.smule.singandroid.list_items.ProfileUserInfoView.ProfileUserInfoViewListener;
import com.smule.singandroid.list_items.VideoUploadingView;
import com.smule.singandroid.media_player_service.MediaPlayerService;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController.MediaPlayerObserver;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;
import com.smule.singandroid.runtimepermissions.SingPermissionRequests;
import com.smule.singandroid.songbook_search.SearchFragment;
import com.smule.singandroid.survey.SurveyContext;
import com.smule.singandroid.survey.SurveyContext.EntryPoint;
import com.smule.singandroid.survey.SurveyPresenter;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.NavigationUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer.ActionBarHighlightMode;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.FilterType;
import com.smule.singandroid.utils.SingAnalytics.ProfilePagevwType;
import com.smule.singandroid.utils.SingAnalytics.SectionType;
import com.smule.singandroid.utils.SingAnalytics.UserRelationType;
import com.smule.singandroid.utils.UIHelper;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@SuppressLint({"InflateParams"})
public class ProfileFragment extends PhotoTakingFragment implements ProfileUserInfoViewListener {
    public static final String f19477e = ProfileFragment.class.getName();
    protected InviteAdapter f19478A;
    protected OwnedArrangementsAdapter f19479B;
    protected FavoriteAdapter f19480C;
    protected ConcurrentHashMap<Integer, Boolean> f19481D;
    @InstanceState
    protected int f19482E = -1;
    protected boolean f19483F;
    @InstanceState
    protected Intent f19484G;
    @InstanceState
    protected boolean f19485H;
    int[] f19486I;
    int f19487J;
    final Observer f19488K;
    Observer f19489L;
    protected PerformanceItemListener f19490M;
    protected ExpandedPerformanceItemListener f19491N;
    private UserProfile f19492O;
    private boolean f19493P = false;
    private PerformanceV2 f19494Q;
    private ConcurrentHashMap<String, Boolean> f19495R = new ConcurrentHashMap();
    private FileUploaderService f19496S;
    private boolean f19497T = false;
    private boolean f19498U = false;
    private Menu f19499V;
    private LocalizedShortNumberFormatter f19500W;
    private boolean f19501X;
    private String f19502Y;
    private MediaPlayerObserver f19503Z;
    private ServiceConnection aa;
    private ProfilePagerAdapter ab;
    private Observer ac;
    private Observer ad;
    private Point ae;
    private boolean af;
    @ViewById
    protected RelativeLayout f19504f;
    @ViewById
    protected View f19505g;
    @ViewById
    protected TextView f19506h;
    @ViewById
    TabLayout f19507i;
    @ViewById
    protected View f19508j;
    @ViewById
    protected CustomViewPager f19509k;
    @ViewById
    protected View f19510l;
    @ViewById
    protected LinearLayout f19511m;
    protected ProfileUserInfoView f19512n;
    protected SingTabLayoutHelper f19513o;
    @InstanceState
    protected AccountIcon f19514p;
    @InstanceState
    protected boolean f19515q;
    @InstanceState
    protected String f19516r;
    @InstanceState
    protected boolean f19517s = true;
    @InstanceState
    protected String f19518t;
    @InstanceState
    protected boolean f19519u = false;
    @InstanceState
    protected int f19520v;
    @InstanceState
    protected int f19521w;
    @InstanceState
    protected boolean f19522x;
    StorageWarningView f19523y;
    protected PerfAdapter f19524z;

    class C40011 implements Observer {
        final /* synthetic */ ProfileFragment f19437a;

        C40011(ProfileFragment profileFragment) {
            this.f19437a = profileFragment;
        }

        public void update(Observable observable, Object obj) {
            this.f19437a.m21102a(this.f19437a.f19502Y, "onMediaPaused");
        }
    }

    public interface CheckVideoStatusCallback {
        void mo6558a(@NonNull PerformanceV2 performanceV2);
    }

    class C40102 extends MediaPlayerObserver {
        final /* synthetic */ ProfileFragment f19454a;

        C40102(ProfileFragment profileFragment) {
            this.f19454a = profileFragment;
        }

        public void mo6476a(MediaPlayerServiceController mediaPlayerServiceController, String str) {
            this.f19454a.f19502Y = str;
        }

        public void mo6477b(MediaPlayerServiceController mediaPlayerServiceController, String str) {
            if (MediaPlayerService.m24585a() != null && MediaPlayerService.m24585a().m24625e()) {
                this.f19454a.m21102a(str, "onMediaPaused");
            }
        }

        public void mo6478c(MediaPlayerServiceController mediaPlayerServiceController, String str) {
            this.f19454a.f19502Y = str;
        }

        public void mo6479d(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        }

        public void mo6480e(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        }

        public void mo6481f(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        }

        public void mo6482g(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        }
    }

    class C40123 extends Operation {
        final /* synthetic */ ProfileFragment f19457a;

        C40123(ProfileFragment profileFragment) {
            this.f19457a = profileFragment;
        }

        public void m21017a(final OperationLoader operationLoader) {
            if (UserManager.a().z()) {
                this.f19457a.mo6571C();
                operationLoader.c(this.g);
                return;
            }
            Observer c40111 = new Observer(this) {
                final /* synthetic */ C40123 f19456b;

                public void update(Observable observable, Object obj) {
                    if (UserManager.a().z()) {
                        NotificationCenter.m19011a().m19016b("USER_LOGGED_IN_EVENT", (Observer) this);
                        NotificationCenter.m19011a().m19016b("USER_RE_LOGGED_IN_EVENT", (Observer) this);
                        this.f19456b.f19457a.mo6571C();
                        operationLoader.c(this.f19456b.g);
                    }
                }
            };
            NotificationCenter.m19011a().m19014a("USER_LOGGED_IN_EVENT", c40111);
            NotificationCenter.m19011a().m19014a("USER_RE_LOGGED_IN_EVENT", c40111);
        }
    }

    class C40134 implements ServiceConnection {
        final /* synthetic */ ProfileFragment f19458a;

        C40134(ProfileFragment profileFragment) {
            this.f19458a = profileFragment;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.f19458a.f19496S = ((FileUploaderBinder) iBinder).m18899a();
            this.f19458a.f19497T = true;
            this.f19458a.f19496S.m18940b();
            this.f19458a.ad();
            Log.b(ProfileFragment.f19477e, "Binding service end - connected");
        }

        public void onServiceDisconnected(ComponentName componentName) {
            this.f19458a.f19497T = false;
            this.f19458a.ad();
            Log.b(ProfileFragment.f19477e, "Binding service end - disconnected");
        }
    }

    class C40145 implements OnPageChangeListener {
        final /* synthetic */ ProfileFragment f19459a;

        C40145(ProfileFragment profileFragment) {
            this.f19459a = profileFragment;
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
        }

        public void onPageScrollStateChanged(int i) {
        }
    }

    class C40156 implements OnTabSelectedListener {
        final /* synthetic */ ProfileFragment f19460a;

        C40156(ProfileFragment profileFragment) {
            this.f19460a = profileFragment;
        }

        public void onTabSelected(Tab tab) {
            this.f19460a.m21040a(tab);
            NotificationCenter.m19011a().m19012a("PERFORMANCE_TYPE_TABS_CLICKED", Integer.valueOf(tab.getPosition()));
        }

        public void onTabUnselected(Tab tab) {
            this.f19460a.m21047b(tab);
        }

        public void onTabReselected(Tab tab) {
            this.f19460a.m21040a(tab);
        }
    }

    public static class DroidSing10042Exception extends Throwable {
        public DroidSing10042Exception(String str) {
            super(str);
        }
    }

    private class ProfileListViewOnScrollListener implements OnScrollListener {
        final /* synthetic */ ProfileFragment f19469a;

        private ProfileListViewOnScrollListener(ProfileFragment profileFragment) {
            this.f19469a = profileFragment;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            int i2 = 0;
            if (i == 0) {
                View childAt = absListView.getChildAt(0);
                if (childAt != null) {
                    i2 = childAt.getTop();
                }
                this.f19469a.f19520v = absListView.getFirstVisiblePosition();
                this.f19469a.f19521w = i2;
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            float max;
            int i4 = 0;
            int T = this.f19469a.m21083T();
            if (i == 0) {
                View childAt = absListView.getChildAt(0);
                T = -T;
                if (childAt != null) {
                    i4 = childAt.getTop();
                }
                max = (float) Math.max(T, i4);
            } else if (i > 0) {
                max = (float) (-T);
            } else {
                max = 0.0f;
            }
            if (max > ((float) (-this.f19469a.f19511m.getHeight()))) {
                this.f19469a.f19510l.setTranslationY(max);
            } else {
                this.f19469a.f19510l.setTranslationY((float) (-this.f19469a.f19511m.getHeight()));
            }
            this.f19469a.as();
        }
    }

    private class ProfilePagerAdapter extends PagerAdapter {
        protected Map<Integer, ProfileListView> f19470a;
        final /* synthetic */ ProfileFragment f19471b;

        private ProfilePagerAdapter(ProfileFragment profileFragment) {
            this.f19471b = profileFragment;
            this.f19470a = new HashMap();
        }

        public int getCount() {
            return 4;
        }

        public CharSequence getPageTitle(int i) {
            switch (i) {
                case 0:
                    return this.f19471b.getResources().getString(C1947R.string.profile_recordings);
                case 1:
                    return this.f19471b.getResources().getString(C1947R.string.core_open_calls);
                case 2:
                    return this.f19471b.getResources().getString(C1947R.string.core_arrangements);
                default:
                    return this.f19471b.getResources().getString(C1947R.string.core_favorites);
            }
        }

        public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
            if (this.f19471b.isAdded()) {
                ProfileListView profileListView = (ProfileListView) this.f19471b.f19509k.findViewWithTag("sb_item#" + i);
                if (profileListView != null) {
                    for (ProfileListView profileListView2 : m21024a()) {
                        if (profileListView2 != profileListView) {
                            profileListView2.f21887b.setOnScrollListener(null);
                        }
                    }
                }
            }
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public Parcelable saveState() {
            for (ProfileListView profileListView : m21024a()) {
                profileListView.f21887b.setMagicAdapter(null);
            }
            this.f19470a.clear();
            return super.saveState();
        }

        public Collection<ProfileListView> m21024a() {
            return this.f19470a.values();
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            ProfileListView profileListView = (ProfileListView) obj;
            viewGroup.removeView(profileListView);
            this.f19471b.mo6488n();
            profileListView.f21887b.setMagicAdapter(null);
            this.f19470a.remove(Integer.valueOf(i));
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View a = ProfileListView.m23446a(this.f19471b.getActivity(), this.f19471b, i);
            a.setOrientation(1);
            a.setLayoutParams(new LayoutParams(-1, -1));
            viewGroup.addView(a);
            this.f19470a.put(Integer.valueOf(i), a);
            return a;
        }
    }

    private class VideoUploadCancelHandler implements Runnable {
        final /* synthetic */ ProfileFragment f19474a;
        private final PerformanceV2 f19475b;
        private final boolean f19476c;

        class C40201 implements Runnable {
            final /* synthetic */ VideoUploadCancelHandler f19472a;

            C40201(VideoUploadCancelHandler videoUploadCancelHandler) {
                this.f19472a = videoUploadCancelHandler;
            }

            public void run() {
                this.f19472a.f19474a.f19496S.m18938a(this.f19472a.f19475b.performanceKey, this.f19472a.f19475b.songUid, this.f19472a.f19475b.arrKey);
            }
        }

        class C40212 implements Runnable {
            final /* synthetic */ VideoUploadCancelHandler f19473a;

            C40212(VideoUploadCancelHandler videoUploadCancelHandler) {
                this.f19473a = videoUploadCancelHandler;
            }

            public void run() {
                this.f19473a.f19474a.f19524z.m18026a().mo6688a(new PerformanceListItemContainer(this.f19473a.f19475b));
                this.f19473a.f19474a.f19524z.m18026a().m17666p();
            }
        }

        public VideoUploadCancelHandler(ProfileFragment profileFragment, PerformanceV2 performanceV2, boolean z) {
            this.f19474a = profileFragment;
            this.f19475b = performanceV2;
            this.f19476c = z;
        }

        public void run() {
            NavigationUtils.m25907a(this.f19474a.getActivity(), this.f19475b, new C40201(this), new C40212(this), null, this.f19476c);
        }
    }

    public ProfileFragment() {
        this.f19486I = this.f19515q ? new int[]{1, 0, 3, 2} : new int[]{1, 0, 3, 2};
        this.f19487J = 0;
        this.f19488K = new C40011(this);
        this.f19502Y = null;
        this.f19503Z = new C40102(this);
        this.aa = new C40134(this);
        this.f19489L = new Observer(this) {
            final /* synthetic */ ProfileFragment f19430a;

            {
                this.f19430a = r1;
            }

            public void update(Observable observable, Object obj) {
                this.f19430a.f19518t = UserManager.a().u();
                if ((this.f19430a.m21071H() && this.f19430a.f19518t == null) || this.f19430a.f19518t.trim().isEmpty()) {
                    this.f19430a.f19518t = this.f19430a.getResources().getString(C1947R.string.profile_blurb_cta);
                }
                this.f19430a.mo6573U();
            }
        };
        this.f19490M = new PerformanceItemListener(this) {
            final /* synthetic */ ProfileFragment f19440a;

            class C40021 implements CheckVideoStatusCallback {
                final /* synthetic */ AnonymousClass20 f19438a;

                C40021(AnonymousClass20 anonymousClass20) {
                    this.f19438a = anonymousClass20;
                }

                public void mo6558a(PerformanceV2 performanceV2) {
                    boolean z = false;
                    boolean z2 = performanceV2.d() && performanceV2.seed && performanceV2.childCount > 0;
                    if (MiscUtils.m25895a(performanceV2) && !z2) {
                        z = true;
                    }
                    this.f19438a.f19440a.m19862m().a(performanceV2, z, true);
                    if (z2) {
                        this.f19438a.f19440a.mo6487a(OpenCallPerformancesListFragment.m24071a(performanceV2));
                    }
                }
            }

            class C40032 implements CheckVideoStatusCallback {
                final /* synthetic */ AnonymousClass20 f19439a;

                C40032(AnonymousClass20 anonymousClass20) {
                    this.f19439a = anonymousClass20;
                }

                public void mo6558a(PerformanceV2 performanceV2) {
                    MediaPlayerServiceController.m24641a().m24654a(performanceV2.performanceKey);
                    this.f19439a.f19440a.m19862m().a(performanceV2, MiscUtils.m25895a(performanceV2), true);
                }
            }

            {
                this.f19440a = r1;
            }

            public void mo6472a(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
                if (mediaPlayingViewInterface != null && performanceV2 != null) {
                    Log.b(ProfileFragment.f19477e, "mPerformanceItemListener - onPerformanceItemClick called");
                    this.f19440a.mo6574a(mediaPlayingViewInterface, performanceV2, new C40021(this));
                }
            }

            public void mo6471a(MediaPlayingViewInterface mediaPlayingViewInterface, AccountIcon accountIcon) {
                Log.b(ProfileFragment.f19477e, "mPerformanceItemListener - onAccountIconClicked called");
            }

            public void mo6473b(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
                if (mediaPlayingViewInterface != null && performanceV2 != null) {
                    Log.b(ProfileFragment.f19477e, "mPerformanceItemListener - onAlbumArtClicked called");
                    this.f19440a.mo6574a(mediaPlayingViewInterface, performanceV2, new C40032(this));
                }
            }
        };
        this.f19491N = new ExpandedPerformanceItemListener(this) {
            final /* synthetic */ ProfileFragment f19448a;

            class C40041 implements CheckVideoStatusCallback {
                final /* synthetic */ AnonymousClass21 f19441a;

                C40041(AnonymousClass21 anonymousClass21) {
                    this.f19441a = anonymousClass21;
                }

                public void mo6558a(PerformanceV2 performanceV2) {
                    PreSingActivity.m24763a(this.f19441a.f19448a.getActivity()).m24796a(StartupMode.OPENCALL).m24793a(performanceV2).a();
                    SingAnalytics.m26081a(performanceV2.video ? FilterType.f24970b : FilterType.NONE, SectionType.NETWORK);
                }
            }

            class C40052 implements CheckVideoStatusCallback {
                final /* synthetic */ AnonymousClass21 f19442a;

                C40052(AnonymousClass21 anonymousClass21) {
                    this.f19442a = anonymousClass21;
                }

                public void mo6558a(PerformanceV2 performanceV2) {
                    MediaPlayerServiceController.m24641a().m24654a(performanceV2.performanceKey);
                    this.f19442a.f19448a.m19862m().a(performanceV2, MiscUtils.m25895a(performanceV2), true);
                }
            }

            class C40063 implements CheckVideoStatusCallback {
                final /* synthetic */ AnonymousClass21 f19443a;

                C40063(AnonymousClass21 anonymousClass21) {
                    this.f19443a = anonymousClass21;
                }

                public void mo6558a(PerformanceV2 performanceV2) {
                    if (performanceV2.e()) {
                        this.f19443a.f19448a.m19862m().a(performanceV2, MiscUtils.m25895a(performanceV2), true);
                        return;
                    }
                    this.f19443a.f19448a.m19862m().a(performanceV2, false, true);
                    this.f19443a.f19448a.mo6487a(OpenCallPerformancesListFragment.m24071a(performanceV2));
                }
            }

            {
                this.f19448a = r1;
            }

            public void mo6560a(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
                if (openCallListItem != null && performanceV2 != null) {
                    SingAnalytics.m26121a(openCallListItem.getPerformance().performanceKey, PerformanceV2Util.m25947e(openCallListItem.getPerformance()), PerformanceV2Util.m25950h(openCallListItem.getPerformance()), openCallListItem.getPerformance().video, ItemClickType.JOIN);
                    this.f19448a.mo6574a((MediaPlayingViewInterface) openCallListItem, performanceV2, new C40041(this));
                }
            }

            public void mo6561b(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
                if (openCallListItem != null && performanceV2 != null) {
                    SingAnalytics.m26121a(openCallListItem.getPerformance().performanceKey, PerformanceV2Util.m25947e(openCallListItem.getPerformance()), PerformanceV2Util.m25950h(openCallListItem.getPerformance()), openCallListItem.getPerformance().video, ItemClickType.LISTEN);
                    this.f19448a.mo6574a((MediaPlayingViewInterface) openCallListItem, performanceV2, new C40052(this));
                }
            }

            public void mo6562c(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
                BaseFragment a = ProfileFragment.m21036a(performanceV2.accountIcon);
                ((MediaPlayingActivity) this.f19448a.getActivity()).a(a, a.mo6514z());
            }

            public void mo6563d(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
                if (openCallListItem != null && performanceV2 != null) {
                    Log.b(ProfileFragment.f19477e, "onExpandedPerformanceItemMetaDataClick - opencall performers: " + performanceV2.totalPerformers);
                    this.f19448a.mo6574a((MediaPlayingViewInterface) openCallListItem, performanceV2, new C40063(this));
                }
            }

            public void mo6559a(PerformanceV2 performanceV2, boolean z) {
                final MediaPlayingActivity mediaPlayingActivity = (MediaPlayingActivity) this.f19448a.getActivity();
                mediaPlayingActivity.ad().m22308a(performanceV2, new BookmarkDialogCallback(this) {
                    final /* synthetic */ AnonymousClass21 f19447b;

                    class C40071 implements Runnable {
                        final /* synthetic */ C40094 f19444a;

                        C40071(C40094 c40094) {
                            this.f19444a = c40094;
                        }

                        public void run() {
                            mediaPlayingActivity.ad().m22306a(this.f19444a.f19447b.f19448a, this.f19444a.f19447b.f19448a.f19505g, this.f19444a.f19447b.f19448a.f19506h, true);
                        }
                    }

                    class C40082 implements Runnable {
                        final /* synthetic */ C40094 f19445a;

                        C40082(C40094 c40094) {
                            this.f19445a = c40094;
                        }

                        public void run() {
                            mediaPlayingActivity.ad().m22306a(this.f19445a.f19447b.f19448a, this.f19445a.f19447b.f19448a.f19505g, this.f19445a.f19447b.f19448a.f19506h, false);
                        }
                    }

                    public void mo6428a(PerformanceV2 performanceV2) {
                        new UiHandler(mediaPlayingActivity).m19081a(new C40071(this));
                        NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "BOOKMARKED_PERFORMANCE", performanceV2);
                    }

                    public void mo6429b(PerformanceV2 performanceV2) {
                        new UiHandler(mediaPlayingActivity).m19081a(new C40082(this));
                        NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "FAVORITED_PERFORMANCE", performanceV2);
                    }

                    public void mo6430c(PerformanceV2 performanceV2) {
                        NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "UNBOOKMARKED_PERFORMANCE", performanceV2);
                        ListView h = this.f19447b.f19448a.aq();
                        if (h != null) {
                            this.f19447b.f19448a.m21092a(h);
                        }
                    }

                    public void mo6431d(PerformanceV2 performanceV2) {
                        NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "UNFAVORITED_PERFORMANCE", performanceV2);
                    }
                }, z);
            }
        };
        this.ac = new Observer(this) {
            final /* synthetic */ ProfileFragment f19449a;

            {
                this.f19449a = r1;
            }

            public void update(Observable observable, Object obj) {
                this.f19449a.mo6573U();
            }
        };
        this.ad = new Observer(this) {
            final /* synthetic */ ProfileFragment f19450a;

            {
                this.f19450a = r1;
            }

            public void update(Observable observable, Object obj) {
                int i = 0;
                Log.b(ProfileFragment.f19477e, "mPerformanceUpdatedObserver - called");
                if (obj instanceof HashMap) {
                    PerformanceV2 performanceV2;
                    HashMap hashMap = (HashMap) obj;
                    if (hashMap.containsKey("DELETED_PERFORMANCE")) {
                        this.f19450a.mo6576c((PerformanceV2) hashMap.get("DELETED_PERFORMANCE"));
                    }
                    if (hashMap.containsKey("UPDATED_PERFORMANCE")) {
                        performanceV2 = (PerformanceV2) hashMap.get("UPDATED_PERFORMANCE");
                        if (performanceV2 != null) {
                            this.f19450a.mo6577d(performanceV2);
                        }
                    }
                    if (hashMap.containsKey("FAVORITED_PERFORMANCE") && this.f19450a.f19515q) {
                        this.f19450a.f19480C.m18026a().mo6687a(0, new PerformanceListItemContainer((PerformanceV2) hashMap.get("FAVORITED_PERFORMANCE")));
                        this.f19450a.f19480C.m18026a().m17666p();
                    }
                    if (hashMap.containsKey("BOOKMARKED_PERFORMANCE")) {
                        performanceV2 = (PerformanceV2) hashMap.get("BOOKMARKED_PERFORMANCE");
                        PerformanceListItemContainer performanceListItemContainer = new PerformanceListItemContainer(performanceV2);
                        performanceListItemContainer.f19115d = true;
                        performanceListItemContainer.f19116e = true;
                        ProfileOpenCallDataSource profileOpenCallDataSource = (ProfileOpenCallDataSource) this.f19450a.f19478A.m18026a();
                        if (this.f19450a.f19478A.m18048d() == 0) {
                            profileOpenCallDataSource.mo6689a(0, performanceListItemContainer);
                            profileOpenCallDataSource.m17666p();
                        } else {
                            for (int i2 = 0; i2 < this.f19450a.f19478A.m18048d(); i2++) {
                                PerformanceListItemContainer performanceListItemContainer2 = (PerformanceListItemContainer) profileOpenCallDataSource.m17641a(i2);
                                if (performanceListItemContainer2.f19115d) {
                                    performanceListItemContainer2.f19115d = false;
                                    profileOpenCallDataSource.mo6689a(i2, performanceListItemContainer);
                                    profileOpenCallDataSource.m17666p();
                                    break;
                                }
                            }
                        }
                        if (this.f19450a.f19515q) {
                            ProfileFragment.m21049b(this.f19450a.f19478A, new PerformanceListItemContainer(performanceV2));
                        }
                    }
                    if (hashMap.containsKey("UNFAVORITED_PERFORMANCE")) {
                        ProfileFragment.m21049b(this.f19450a.f19480C, new PerformanceListItemContainer((PerformanceV2) hashMap.get("UNFAVORITED_PERFORMANCE")));
                    }
                    if (hashMap.containsKey("UNBOOKMARKED_PERFORMANCE")) {
                        PerformanceListItemContainer performanceListItemContainer3;
                        Object performanceListItemContainer4 = new PerformanceListItemContainer((PerformanceV2) hashMap.get("UNBOOKMARKED_PERFORMANCE"));
                        performanceListItemContainer4.f19116e = true;
                        while (i < this.f19450a.f19478A.m18048d()) {
                            performanceListItemContainer3 = (PerformanceListItemContainer) this.f19450a.f19478A.m18026a().m17641a(i);
                            if (performanceListItemContainer3.equals(performanceListItemContainer4) && performanceListItemContainer3.f19115d) {
                                break;
                            }
                            i++;
                        }
                        i = -1;
                        if (this.f19450a.f19478A.m18026a().mo6688a(performanceListItemContainer4) && i != -1 && this.f19450a.f19478A.m18048d() > i) {
                            performanceListItemContainer3 = (PerformanceListItemContainer) this.f19450a.f19478A.m18026a().m17641a(i);
                            if (performanceListItemContainer3 != null && performanceListItemContainer3.f19116e) {
                                performanceListItemContainer3.f19115d = true;
                            }
                        }
                        this.f19450a.f19478A.m18026a().m17666p();
                    }
                }
                this.f19450a.am();
            }
        };
        this.ae = new Point();
    }

    public static ProfileFragment m21035a() {
        return m21036a(UserManager.a().O());
    }

    public static ProfileFragment m21037a(AccountIcon accountIcon, boolean z) {
        ProfileFragment a = m21036a(accountIcon);
        a.f19485H = z;
        return a;
    }

    public static ProfileFragment m21036a(AccountIcon accountIcon) {
        ProfileFragment profileFragment_ = new ProfileFragment_();
        Bundle bundle = new Bundle();
        bundle.putParcelable("PROFILE_ACCOUNT_ICON_KEY", accountIcon);
        profileFragment_.setArguments(bundle);
        return profileFragment_;
    }

    public String mo6383s() {
        return f19477e;
    }

    public String mo6514z() {
        return f19477e + "-" + m21065B();
    }

    public AccountIcon m21064A() {
        return (AccountIcon) getArguments().getParcelable("PROFILE_ACCOUNT_ICON_KEY");
    }

    public long m21065B() {
        return m21064A().accountId;
    }

    protected void m21102a(String str, String str2) {
        Log.b(f19477e, "Entering startAVQualitySurveyIfEligible; audioId = " + str + "; triggeringContext = " + str2);
        if (!isAdded() || !isResumed()) {
            return;
        }
        if (str == null) {
            Log.e(f19477e, "Not starting survey because audioId == null");
            return;
        }
        PerformanceV2 a = SurveyPresenter.m25508a().m25541a(getActivity());
        if (a != null && str.equals(a.performanceKey)) {
            SurveyPresenter.m25508a().m25543a(getActivity(), null);
            if (this.f19524z != null && this.f19524z.m23441b(str)) {
                SurveyContext surveyContext = new SurveyContext();
                surveyContext.f24288b = a.video ? EntryPoint.VIDEO_POST_LISTEN : EntryPoint.AUDIO_POST_LISTEN;
                surveyContext.f24292f = a;
                if (SurveyPresenter.m25508a().m25549b(getActivity(), surveyContext)) {
                    SurveyPresenter.m25508a().m25551c(getActivity(), surveyContext);
                }
            }
        }
    }

    @UiThread
    protected void mo6571C() {
        if (isAdded()) {
            Intent intent = new Intent(SingApplication.f(), FileUploaderService.class);
            Log.b(f19477e, "Binding service");
            this.f19498U = SingApplication.f().bindService(intent, this.aa, 1);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.f19514p = (AccountIcon) getArguments().getParcelable("PROFILE_ACCOUNT_ICON_KEY");
            if (this.f19514p == null) {
                throw new RuntimeException("AccountIcon was null");
            }
            if (this.f19514p.accountId == UserManager.a().f()) {
                this.f19515q = true;
                this.f19514p.handle = UserManager.a().i();
                this.f19514p.picUrl = UserManager.a().h();
                setHasOptionsMenu(true);
            } else {
                this.f19515q = false;
                setHasOptionsMenu(true);
            }
            this.f19486I = this.f19515q ? new int[]{1, 0, 3, 2} : new int[]{1, 0, 3, 2};
            this.f19487J = 0;
            this.f19501X = false;
        }
    }

    private void ac() {
        if (!this.f19498U && this.f19515q) {
            SingApplication.d().a("INIT_FILE_UPLOADER_SERVICE_KEY", Collections.singletonList("InitAppTask.OP_USER_LOGGED_IN"), new C40123(this)).a();
        }
    }

    public void onStart() {
        super.onStart();
        if (this.f19515q) {
            ac();
        } else {
            setHasOptionsMenu(true);
        }
        m19866q();
    }

    public void onResume() {
        super.onResume();
        ao();
        if (this.f19494Q != null) {
            mo6576c(this.f19494Q);
            this.f19494Q = null;
        }
        if (m19862m() != null) {
            if (this.f19515q) {
                m19862m().a(BaseFragment$BaseFragmentResponder$MenuToggleAction.SHOW_IF_ALLOWED);
            } else {
                m19862m().a(BaseFragment$BaseFragmentResponder$MenuToggleAction.HIDE);
            }
        }
        ai();
        if (this.ab != null && this.ab.f19470a.size() == 0) {
            ad();
        }
        NotificationCenter.m19011a().m19014a(MediaPlayerServiceController.f23430a, this.f19488K);
        MediaPlayerServiceController.m24641a().m24652a(this.f19503Z);
        ((MediaPlayingActivity) getActivity()).S().setVisibility(0);
        if (!this.af) {
            getActivity().getWindowManager().getDefaultDisplay().getSize(this.ae);
            this.af = true;
        }
    }

    public void onPause() {
        super.onPause();
        ap();
        NotificationCenter.m19011a().m19016b(MediaPlayerServiceController.f23430a, this.f19488K);
        MediaPlayerServiceController.m24641a().m24659b(this.f19503Z);
    }

    public void onStop() {
        super.onStop();
        if (this.f19498U) {
            if (this.f19496S != null) {
                this.f19496S.m18942c();
            }
            SingApplication.f().unbindService(this.aa);
            this.f19497T = false;
            this.f19498U = false;
        }
        SingApplication.d().a("INIT_FILE_UPLOADER_SERVICE_KEY");
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f19512n = null;
        this.f19495R = null;
        this.f19481D = null;
        this.f19524z = null;
        this.f19478A = null;
        this.f19479B = null;
        this.f19480C = null;
        this.ab = null;
        this.f19509k = null;
        this.f19513o = null;
    }

    protected void mo6420v() {
        SingAnalytics.m26066a(this.f19514p.accountId, this.f19485H ? ProfilePagevwType.MENTION : null);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (!ChatUtils.a()) {
            super.onCreateOptionsMenu(menu, menuInflater);
        } else if (af()) {
            menuInflater.inflate(C1947R.menu.action_bar_search_menu, menu);
        } else if (!this.f19515q) {
            menuInflater.inflate(C1947R.menu.profile_fragment_menu, menu);
            this.f19499V = menu;
            ai();
        }
    }

    public void onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu();
        this.f19499V = null;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1947R.id.action_search:
                Analytics.m17848a(SearchClkContext.PROFILE);
                mo6487a(SearchFragment.m25251A());
                break;
            case C1947R.id.action_flag:
                ChatAnalytics.m22377a(this.f19514p.accountId);
                mo6487a(FlagUserFragment.m23737a(this.f19514p));
                break;
            case C1947R.id.action_block:
                ag();
                break;
            case C1947R.id.action_unblock:
                ah();
                break;
        }
        return false;
    }

    private void ad() {
        if (!this.f19515q || this.f19497T) {
            this.ab = new ProfilePagerAdapter();
            this.f19509k.setAdapter(this.ab);
            this.f19509k.addOnPageChangeListener(new C40145(this));
            ae();
            this.f19509k.setOffscreenPageLimit(3);
            if (this.f19481D == null) {
                this.f19481D = new ConcurrentHashMap();
            }
            if (this.f19481D.size() == 4 || this.f19482E != -1) {
                m21111d(this.f19482E);
                if (aq() != null && this.f19521w != 0) {
                    aq().setSelectionFromTop(this.f19520v, this.f19521w);
                }
            }
        }
    }

    private void ae() {
        if (this.f19513o == null) {
            this.f19513o = new SingTabLayoutHelper(this.f19507i, this.f19509k);
            this.f19513o.m11744a(true);
            this.f19513o.m11740a(new C40156(this));
        }
    }

    private void m21040a(Tab tab) {
        this.f19513o.m23479a(true, tab);
    }

    private void m21047b(Tab tab) {
        this.f19513o.m23479a(false, tab);
    }

    @AfterViews
    protected void m21067D() {
        Log.b(f19477e, "updateFollowingViewBinding - begin");
        am();
        al();
        mo6572S();
        this.f19507i.setSelectedTabIndicatorColor(getResources().getColor(C1947R.color.button_text_inverse));
        this.f19507i.setSelectedTabIndicatorHeight(getResources().getDimensionPixelOffset(C1947R.dimen.margin_extra_tiny));
        ad();
        Log.b(f19477e, "updateFollowingViewBinding - end");
    }

    private boolean af() {
        return this.f19515q && getFragmentManager().getBackStackEntryCount() <= 1;
    }

    public boolean mo6446i() {
        return !af();
    }

    protected boolean mo6450x() {
        return !af();
    }

    public boolean mo6444d() {
        return this.f19515q;
    }

    public boolean mo6445g() {
        return true;
    }

    public FileUploaderService m21068E() {
        return this.f19496S;
    }

    public boolean m21069F() {
        return this.f19497T;
    }

    public int m21070G() {
        return this.f19482E;
    }

    public void m21098a(PerfAdapter perfAdapter) {
        this.f19524z = perfAdapter;
    }

    public void m21096a(InviteAdapter inviteAdapter) {
        this.f19478A = inviteAdapter;
    }

    public void m21097a(OwnedArrangementsAdapter ownedArrangementsAdapter) {
        this.f19479B = ownedArrangementsAdapter;
    }

    public void m21095a(FavoriteAdapter favoriteAdapter) {
        this.f19480C = favoriteAdapter;
    }

    public boolean m21071H() {
        return this.f19515q;
    }

    public int m21072I() {
        return this.d;
    }

    public int m21073J() {
        return ((BaseProfileDataSource) this.f19480C.m18026a()).m22056w();
    }

    public int m21074K() {
        return ((BaseProfileDataSource) this.f19524z.m18026a()).m22056w();
    }

    public int m21075L() {
        return ((ProfileOpenCallDataSource) this.f19478A.m18026a()).mo6264d();
    }

    public int m21076M() {
        return ((ProfileOpenCallDataSource) this.f19478A.m18026a()).m22095v();
    }

    public int m21077N() {
        return ((ProfileArrangementDataSource) this.f19479B.m18026a()).mo6264d();
    }

    public ConcurrentHashMap<String, Boolean> m21078O() {
        return this.f19495R;
    }

    public void m21103a(ConcurrentHashMap<String, Boolean> concurrentHashMap) {
        this.f19495R = concurrentHashMap;
    }

    private void ag() {
        final TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getResources().getString(C1947R.string.chat_are_you_sure), getResources().getString(C1947R.string.chat_block_subtitle));
        textAlertDialog.m19800a((int) C1947R.string.chat_block, (int) C1947R.string.core_cancel);
        textAlertDialog.m19803a(new CustomAlertDialogListener(this) {
            final /* synthetic */ ProfileFragment f19464b;

            public void mo6385a(CustomAlertDialog customAlertDialog) {
                if (!this.f19464b.f19483F) {
                    this.f19464b.f19483F = true;
                    final BusyScreenDialog busyScreenDialog = new BusyScreenDialog(this.f19464b.getActivity(), this.f19464b.getResources().getString(C1947R.string.chat_blocking_busy_message));
                    busyScreenDialog.show();
                    SingApplication.j().a(this.f19464b.m21065B(), true, new Completion<ChatStatus>(this) {
                        final /* synthetic */ C40177 f19462b;

                        public void m21018a(ChatStatus chatStatus) {
                            busyScreenDialog.dismiss();
                            this.f19462b.f19464b.f19483F = false;
                            if (chatStatus == ChatStatus.OK) {
                                List<Chat> arrayList = new ArrayList();
                                arrayList.addAll(SingApplication.j().a(Bucket.INBOX));
                                arrayList.addAll(SingApplication.j().a(Bucket.OTHER));
                                for (Chat chat : arrayList) {
                                    if ((chat instanceof PeerChat) && chat.mo6341f() == this.f19462b.f19464b.m21065B()) {
                                        SingApplication.j().a(chat, null);
                                    }
                                }
                                this.f19462b.f19464b.m19846b((int) C1947R.string.chat_blocked_user);
                                this.f19462b.f19464b.ai();
                                return;
                            }
                            ChatUtils.a(this.f19462b.f19464b.getActivity(), C1947R.string.chat_error_block, chatStatus);
                        }
                    });
                }
            }

            public void mo6386b(CustomAlertDialog customAlertDialog) {
                textAlertDialog.dismiss();
            }
        });
        textAlertDialog.show();
    }

    private void ah() {
        if (!this.f19483F) {
            this.f19483F = true;
            final BusyScreenDialog busyScreenDialog = new BusyScreenDialog(getActivity(), getResources().getString(C1947R.string.chat_unblocking_busy_message));
            busyScreenDialog.show();
            SingApplication.j().a(m21065B(), false, new Completion<ChatStatus>(this) {
                final /* synthetic */ ProfileFragment f19466b;

                public void m21022a(ChatStatus chatStatus) {
                    busyScreenDialog.dismiss();
                    this.f19466b.f19483F = false;
                    if (chatStatus == ChatStatus.OK) {
                        this.f19466b.m19846b((int) C1947R.string.chat_unblocked_user);
                        this.f19466b.ai();
                        return;
                    }
                    ChatUtils.a(this.f19466b.getActivity(), C1947R.string.chat_error_block, chatStatus);
                }
            });
        }
    }

    private void ai() {
        if (this.f19499V != null) {
            MenuItem findItem = this.f19499V.findItem(C1947R.id.action_block);
            MenuItem findItem2 = this.f19499V.findItem(C1947R.id.action_unblock);
            if (!ChatUtils.a()) {
                findItem.setVisible(false);
                findItem2.setVisible(false);
            } else if (SingApplication.j().a(this.f19514p.accountId)) {
                findItem.setVisible(false);
                findItem2.setVisible(true);
            } else {
                findItem.setVisible(true);
                findItem2.setVisible(false);
            }
        }
        mo6573U();
    }

    private ProfileUserInfo aj() {
        ProfileUserInfo profileUserInfo = new ProfileUserInfo();
        profileUserInfo.m24405a(this.f19514p.accountId);
        profileUserInfo.m24407a(this.f19515q);
        profileUserInfo.m24406a(this.f19514p.picUrl);
        profileUserInfo.m24410b(this.f19514p.a());
        profileUserInfo.m24409b(this.f19518t);
        if (this.f19492O != null) {
            profileUserInfo.m24404a(this.f19492O.b);
            profileUserInfo.m24408b(this.f19492O.c);
        }
        profileUserInfo.m24413c(this.f19514p.jid);
        profileUserInfo.m24414c(this.f19514p.c());
        return profileUserInfo;
    }

    public boolean m21079P() {
        return this.f19514p.a();
    }

    private void m21057e(int i) {
        for (ProfileListView listView : this.ab.f19470a.values()) {
            listView.getListView().setSelectionFromTop(0, i);
        }
    }

    public void mo6566a(TextView textView) {
        if (this.f19515q) {
            mo6487a(SettingsFragment.m21385b(true));
            return;
        }
        final int dimensionPixelOffset = (-LayoutUtils.m25848a((View) textView, this.f19512n).y) + getResources().getDimensionPixelOffset(C1947R.dimen.margin_medium);
        if (this.f19512n.getTop() < dimensionPixelOffset) {
            m19838a(new Runnable(this) {
                final /* synthetic */ ProfileFragment f19468b;

                public void run() {
                    this.f19468b.m21057e(dimensionPixelOffset);
                }
            });
        }
    }

    public void mo6567a(ProfileImageWithVIPBadge profileImageWithVIPBadge) {
        super.m20708a(profileImageWithVIPBadge, profileImageWithVIPBadge.f21825a, true, 200, 200, SingPermissionRequests.f23948b);
    }

    public void mo6568b(final TextView textView) {
        if (!this.f19493P && this.f19514p.accountId != UserManager.a().f()) {
            this.f19493P = true;
            final Context f = SingApplication.f();
            FollowManager.m18204a().m18215a(Long.valueOf(this.f19514p.accountId), new ToggleFollowStateListener(this) {
                final /* synthetic */ ProfileFragment f19420c;

                public void mo6399a(final boolean z, final boolean z2, final boolean z3) {
                    this.f19420c.m19838a(new Runnable(this) {
                        final /* synthetic */ AnonymousClass10 f19417d;

                        public void run() {
                            int i = 1;
                            if (z3) {
                                this.f19417d.f19420c.m19849b(f.getString(C1947R.string.profile_follow_limit_reached));
                            } else if (!z) {
                                this.f19417d.f19420c.m19849b(f.getString(C1947R.string.profile_update_error));
                            } else if (z2) {
                                this.f19417d.f19420c.m19849b(MessageFormat.format(f.getString(C1947R.string.profile_follow_format), new Object[]{this.f19417d.f19420c.f19514p.handle}));
                            } else {
                                this.f19417d.f19420c.m19849b(MessageFormat.format(f.getString(C1947R.string.profile_unfollow_format), new Object[]{this.f19417d.f19420c.f19514p.handle}));
                            }
                            if (!(this.f19417d.f19420c.f19492O == null || z3 || !z)) {
                                UserProfile e = this.f19417d.f19420c.f19492O;
                                if (!z2) {
                                    i = -1;
                                }
                                e.a(i);
                            }
                            UIHelper.m26198a(f, this.f19417d.f19420c.f19514p.accountId, textView);
                            this.f19417d.f19420c.f19493P = false;
                            this.f19417d.f19420c.mo6573U();
                        }
                    });
                }
            });
        }
    }

    public void mo6569c(TextView textView) {
        ChatActivatorDialog chatActivatorDialog = new ChatActivatorDialog(getActivity(), (int) C1947R.string.chat_user_wait_new_peer);
        chatActivatorDialog.m22943a(this.f19514p, new ChatActivatorListener(this) {
            final /* synthetic */ ProfileFragment f19423a;

            {
                this.f19423a = r1;
            }

            public void a_(final Chat chat) {
                new Handler().post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass11 f19422b;

                    public void run() {
                        if (this.f19422b.f19423a.isResumed()) {
                            FragmentManager fragmentManager = this.f19422b.f19423a.getFragmentManager();
                            fragmentManager.popBackStack(MessageCenterFragment.f21133e, 0);
                            String str = ChatFragment.f20901e + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + this.f19422b.f19423a.f19514p.handle;
                            if (fragmentManager.findFragmentByTag(str) != null) {
                                fragmentManager.popBackStack(str, 0);
                            } else {
                                this.f19422b.f19423a.mo6929a(ChatFragment.m22554b(chat), str);
                            }
                        }
                    }
                });
            }
        });
        chatActivatorDialog.show();
    }

    public void mo6570d(TextView textView) {
        ah();
    }

    public void mo6564Q() {
        if (this.f19492O != null && this.f19492O.getNumberFollowers() > 0) {
            mo6487a(FollowListFragment.m20222a(this.f19514p.accountId, this.f19514p.picUrl, 0, this.f19492O.getNumberFollowers()));
        }
    }

    public void mo6565R() {
        if (this.f19492O != null && this.f19492O.getNumberFollowees() > 0) {
            mo6487a(FollowListFragment.m20222a(this.f19514p.accountId, this.f19514p.picUrl, 1, this.f19492O.getNumberFollowees()));
        }
    }

    @SupposeUiThread
    protected void mo6572S() {
        this.f19512n = ProfileUserInfoView.m24422a(getActivity());
        this.f19511m.addView(this.f19512n, 0);
        this.f19512n.addOnLayoutChangeListener(new OnLayoutChangeListener(this) {
            final /* synthetic */ ProfileFragment f19424a;

            {
                this.f19424a = r1;
            }

            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (i4 - i2 != i8 - i6 && this.f19424a.ab != null) {
                    for (ProfileListView a : this.f19424a.ab.m21024a()) {
                        a.mo6803a();
                    }
                }
            }
        });
        this.f19512n.setListener(this);
    }

    public int m21083T() {
        return this.f19512n.getHeight() + this.f19507i.getHeight();
    }

    public VideoUploadStatus m21108c(String str) {
        VideoUploadStatus videoUploadStatus = VideoUploadStatus.UNKNOWN;
        if (!this.f19497T || str == null) {
            return videoUploadStatus;
        }
        return this.f19496S.m18936a(str);
    }

    @UiThread
    protected void mo6573U() {
        if (isAdded()) {
            m21055d(this.f19514p.handle);
            this.f19512n.m24424a(aj());
        }
    }

    private void m21055d(String str) {
        if (str == null) {
            Log.e(f19477e, "configureActionBar - userHandle is null; using blank string");
            str = "";
        }
        if (getActivity() != null) {
            mo6861a((CharSequence) str);
        }
    }

    public LocalizedShortNumberFormatter m21085V() {
        if (this.f19500W == null) {
            this.f19500W = new LocalizedShortNumberFormatter(getActivity());
        }
        return this.f19500W;
    }

    private void ak() {
        final int i = this.d;
        UserManager.a().a(UserManager.a().f(), new UserManager$GetUserBlurbResponseCallback(this) {
            final /* synthetic */ ProfileFragment f19426b;

            public void handleResponse(UserBlurbResponse userBlurbResponse) {
                if (this.f19426b.m19843a(i)) {
                    if (userBlurbResponse.a()) {
                        if (userBlurbResponse.mBlurb == null) {
                            userBlurbResponse.mBlurb = "";
                        }
                        UserManager.a().a(userBlurbResponse.mBlurb);
                        this.f19426b.f19518t = userBlurbResponse.mBlurb;
                    } else {
                        Log.e(ProfileFragment.f19477e, "getPersonalBlurb - errorGettingBlurb - called for personal profile");
                    }
                    if (this.f19426b.f19518t == null || this.f19426b.f19518t.trim().isEmpty()) {
                        this.f19426b.f19518t = this.f19426b.getResources().getString(C1947R.string.profile_blurb_cta);
                    }
                    this.f19426b.mo6573U();
                }
            }
        });
    }

    private void al() {
        if (this.f19515q) {
            UserManager a = UserManager.a();
            if (a.u() == null) {
                ak();
                return;
            }
            if (a.u().trim().isEmpty()) {
                this.f19518t = getResources().getString(C1947R.string.profile_blurb_cta);
            } else {
                this.f19518t = a.u();
            }
            mo6573U();
            return;
        }
        UserManager.a().a(this.f19514p.accountId, new UserManager$GetUserBlurbResponseCallback(this) {
            final /* synthetic */ ProfileFragment f19427a;

            {
                this.f19427a = r1;
            }

            public void handleResponse(UserBlurbResponse userBlurbResponse) {
                if (userBlurbResponse.a()) {
                    this.f19427a.f19518t = userBlurbResponse.mBlurb;
                } else {
                    this.f19427a.f19518t = null;
                }
                this.f19427a.mo6573U();
            }
        });
    }

    public void m21101a(String str, int i) {
        if (isAdded()) {
            TextView textView = (TextView) this.f19507i.getTabAt(i).getCustomView().findViewById(C1947R.id.tab_badge);
            ImageView imageView = (ImageView) this.f19507i.getTabAt(i).getCustomView().findViewById(C1947R.id.tab_badge_empty);
            if (str == null) {
                textView.setVisibility(8);
                imageView.setVisibility(8);
            } else if (str.isEmpty()) {
                textView.setVisibility(8);
                imageView.setVisibility(0);
            } else {
                textView.setText(str);
                textView.setVisibility(0);
                imageView.setVisibility(8);
            }
        }
    }

    public void m21091a(int i, boolean z) {
        boolean z2 = true;
        if (this.f19481D == null) {
            this.f19481D = new ConcurrentHashMap();
        }
        this.f19481D.put(Integer.valueOf(i), Boolean.valueOf(z));
        if (!this.f19501X && this.f19482E == -1) {
            int i2;
            if (this.f19481D.size() == 4) {
                this.f19501X = true;
                for (i2 = 0; i2 < 4; i2++) {
                    if (((Boolean) this.f19481D.get(Integer.valueOf(this.f19486I[i2]))).booleanValue()) {
                        m21111d(this.f19486I[i2]);
                        break;
                    }
                }
                z2 = false;
                if (!z2) {
                    m21111d(this.f19487J);
                    return;
                }
                return;
            }
            for (int i3 = 0; i3 < 4; i3++) {
                if (i == this.f19486I[0] && z) {
                    int i4;
                    for (i2 = i3 - 1; i2 >= 0; i2--) {
                        if (((Boolean) this.f19481D.get(Integer.valueOf(this.f19486I[i2]))).booleanValue()) {
                            i4 = 0;
                            break;
                        }
                    }
                    boolean z3 = true;
                    if (i4 != 0) {
                        this.f19501X = true;
                        m21111d(i);
                        return;
                    }
                }
            }
        }
    }

    public Runnable m21090a(PerformanceV2 performanceV2) {
        return new VideoUploadCancelHandler(this, performanceV2, true);
    }

    public void m21104a(boolean z, ArrangementVersionLiteEntry arrangementVersionLiteEntry, ListingListItem listingListItem) {
        this.f19495R.put(arrangementVersionLiteEntry.mo6289c(), Boolean.valueOf(z));
        if (z) {
            MediaPlayerServiceController.m24641a().m24660b(arrangementVersionLiteEntry.mo6289c());
            m21099a(listingListItem);
            return;
        }
        mo6443a((SongbookEntry) arrangementVersionLiteEntry);
    }

    protected void m21099a(ListingListItem listingListItem) {
        if (isAdded()) {
            listingListItem.setAlbumArtClickedState(false);
            TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(C1947R.string.arrangement_copyright_violation_title), MessageFormat.format(getString(C1947R.string.arrangement_copyright_violation_detail), new Object[]{getString(C1947R.string.performance_copyright_violation_email)}), true, false);
            textAlertDialog.m19806a(getString(C1947R.string.core_ok), null);
            textAlertDialog.show();
        }
    }

    private void am() {
        Log.b(f19477e, "fetchProfileForUser - beginning");
        final int i = this.d;
        UserManager.a().a(this.f19514p.accountId, new UserManager$UserProfileResponseCallback(this) {
            final /* synthetic */ ProfileFragment f19429b;

            public void handleResponse(UserProfile userProfile) {
                if (!this.f19429b.m19843a(i)) {
                    return;
                }
                if (!userProfile.a() || userProfile.accountIcon == null) {
                    Log.e(ProfileFragment.f19477e, "fetchProfileForUser - User account not retrieved");
                    return;
                }
                this.f19429b.f19492O = userProfile;
                this.f19429b.f19514p = userProfile.accountIcon;
                Log.b(ProfileFragment.f19477e, "fetchProfileForUser '" + userProfile.getAccountId() + "' - parsed user profile; going to set follow numbers, subscription badge");
                this.f19429b.mo6573U();
                ProfileFragment profileFragment = this.f19429b;
                boolean z = userProfile.apps.contains("sing_google") || userProfile.apps.contains("sing");
                profileFragment.f19517s = z;
                this.f19429b.mo6573U();
            }
        });
    }

    private boolean m21058e(PerformanceV2 performanceV2) {
        if (!performanceV2.n()) {
            return false;
        }
        if (performanceV2.r() || StoreManager.m18378a().m18416a(performanceV2.songUid) != null) {
            return true;
        }
        return false;
    }

    public boolean m21105a(PerformanceV2 performanceV2, PerformancesAPI$FillStatus performancesAPI$FillStatus) {
        if (performancesAPI$FillStatus == PerformancesAPI$FillStatus.FILLED) {
            if (performanceV2.d() && performanceV2.accountIcon.accountId == this.f19514p.accountId && performanceV2.childCount == 0) {
                return false;
            }
            if (performanceV2.seed && m21058e(performanceV2) && performanceV2.accountIcon.accountId == this.f19514p.accountId && (!performanceV2.d() || performanceV2.childCount == 0)) {
                return false;
            }
            return true;
        } else if (performancesAPI$FillStatus == PerformancesAPI$FillStatus.ACTIVESEED && performanceV2.seed && !performanceV2.closed && performanceV2.accountIcon.accountId == this.f19514p.accountId) {
            return true;
        } else {
            return false;
        }
    }

    @SupposeUiThread
    protected void mo6574a(@NonNull MediaPlayingViewInterface mediaPlayingViewInterface, @NonNull final PerformanceV2 performanceV2, @NonNull final CheckVideoStatusCallback checkVideoStatusCallback) {
        if (mediaPlayingViewInterface instanceof VideoUploadingView) {
            switch (((VideoUploadingView) mediaPlayingViewInterface).getUploadStatus()) {
                case UPLOADING:
                    m21051c((int) C1947R.string.profile_uploading_recording, (int) C1947R.string.profile_uploading_recording_desc);
                    return;
                case RENDERING:
                    PerformanceManager.a().a(performanceV2.performanceKey, new PerformanceManager$PerformanceResponseCallback(this) {
                        final /* synthetic */ ProfileFragment f19433c;

                        public void handleResponse(PerformanceResponse performanceResponse) {
                            if (!this.f19433c.isAdded()) {
                                return;
                            }
                            if (performanceResponse.a() && performanceResponse.mPerformance.a()) {
                                this.f19433c.mo6577d(performanceResponse.mPerformance);
                                if (this.f19433c.f19497T) {
                                    this.f19433c.f19496S.m18941b(performanceV2.performanceKey);
                                }
                                checkVideoStatusCallback.mo6558a(performanceResponse.mPerformance);
                                return;
                            }
                            this.f19433c.m21051c((int) C1947R.string.profile_processing_recording, (int) C1947R.string.profile_processing_recording_desc);
                        }
                    });
                    return;
                case ERROR_INVALID_MEDIA:
                    m21107b(performanceV2);
                    return;
                default:
                    checkVideoStatusCallback.mo6558a(performanceV2);
                    return;
            }
        }
        checkVideoStatusCallback.mo6558a(performanceV2);
    }

    public void m21107b(final PerformanceV2 performanceV2) {
        Object[] objArr = new Object[1];
        String str = (performanceV2 == null || performanceV2.title == null) ? "" : performanceV2.title;
        objArr[0] = str;
        TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(C1947R.string.performance_upload_invalid_media_title), Html.fromHtml(getString(C1947R.string.performance_upload_invalid_media_desc, objArr)), true, false);
        textAlertDialog.m19806a(getString(C1947R.string.core_ok), "");
        textAlertDialog.m19803a(new CustomAlertDialogListener(this) {
            final /* synthetic */ ProfileFragment f19435b;

            private void m20982c(CustomAlertDialog customAlertDialog) {
                new VideoUploadCancelHandler(this.f19435b, performanceV2, false).run();
                customAlertDialog.dismiss();
            }

            public void mo6385a(CustomAlertDialog customAlertDialog) {
                m20982c(customAlertDialog);
            }

            public void mo6386b(CustomAlertDialog customAlertDialog) {
                m20982c(customAlertDialog);
            }
        });
        textAlertDialog.show();
    }

    private void m21051c(int i, int i2) {
        TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(i), getString(i2), true, false);
        textAlertDialog.m19806a(getString(C1947R.string.core_ok), "");
        textAlertDialog.m19803a(new CustomAlertDialogListener(this) {
            final /* synthetic */ ProfileFragment f19436a;

            {
                this.f19436a = r1;
            }

            public void mo6385a(CustomAlertDialog customAlertDialog) {
                customAlertDialog.dismiss();
            }

            public void mo6386b(CustomAlertDialog customAlertDialog) {
                customAlertDialog.dismiss();
            }
        });
        textAlertDialog.show();
    }

    public PerformanceItemListener m21086W() {
        return this.f19490M;
    }

    @Click
    protected void m21087X() {
        ((MediaPlayingActivity) getActivity()).ad().m22307a(this.f19505g);
    }

    public ExpandedPerformanceItemListener m21088Y() {
        return this.f19491N;
    }

    private static boolean m21049b(MagicAdapter magicAdapter, PerformanceListItemContainer performanceListItemContainer) {
        boolean a = magicAdapter.m18026a().mo6688a((Object) performanceListItemContainer);
        if (a) {
            magicAdapter.m18026a().m17666p();
        }
        return a;
    }

    @SupposeUiThread
    protected void mo6576c(PerformanceV2 performanceV2) {
        PerformanceListItemContainer performanceListItemContainer = new PerformanceListItemContainer(performanceV2);
        if (m21049b(this.f19524z, performanceListItemContainer)) {
            an();
        }
        m21049b(this.f19480C, performanceListItemContainer);
        m21049b(this.f19478A, performanceListItemContainer);
        performanceListItemContainer.f19116e = true;
        m21049b(this.f19478A, performanceListItemContainer);
    }

    private static void m21052c(MagicAdapter magicAdapter, PerformanceListItemContainer performanceListItemContainer) {
        magicAdapter.m18026a().m17652b((Object) performanceListItemContainer);
        magicAdapter.m18026a().m17666p();
    }

    @SupposeUiThread
    protected void mo6577d(PerformanceV2 performanceV2) {
        PerformanceListItemContainer performanceListItemContainer = new PerformanceListItemContainer(performanceV2);
        m21052c(this.f19524z, performanceListItemContainer);
        m21052c(this.f19478A, performanceListItemContainer);
        m21052c(this.f19480C, performanceListItemContainer);
    }

    private void an() {
        ProfilePerformanceDataSource profilePerformanceDataSource = (ProfilePerformanceDataSource) this.f19524z.m18026a();
        List d = profilePerformanceDataSource.mo6264d();
        if (!(d == null || d.isEmpty())) {
            profilePerformanceDataSource.mo6689a(profilePerformanceDataSource.m17661k(), (PerformanceListItemContainer) d.get(0));
            profilePerformanceDataSource.m22121v();
            profilePerformanceDataSource.m17666p();
            ar().m23513c();
        }
        aa();
    }

    private void ao() {
        if (this.f19515q) {
            NotificationCenter.m19011a().m19014a("PERSONAL_FOLLOW_INFO_UPDATED_EVENT", this.ac);
            NotificationCenter.m19011a().m19014a("PROFILE_UPDATED_EVENT", this.ac);
        }
        NotificationCenter.m19011a().m19014a("PERFORMANCE_UPDATED_NOTIFICATION", this.ad);
        NotificationCenter.m19011a().m19014a("PROFILE_UPDATED_NOTIFICATION", this.f19489L);
    }

    private void ap() {
        if (this.f19515q) {
            NotificationCenter.m19011a().m19016b("PERSONAL_FOLLOW_INFO_UPDATED_EVENT", this.ac);
            NotificationCenter.m19011a().m19016b("PROFILE_UPDATED_EVENT", this.ac);
        }
        NotificationCenter.m19011a().m19016b("PERFORMANCE_UPDATED_NOTIFICATION", this.ad);
        NotificationCenter.m19011a().m19016b("PROFILE_UPDATED_NOTIFICATION", this.f19489L);
    }

    private ListView aq() {
        ProfileListView profileListView = (ProfileListView) this.ab.f19470a.get(Integer.valueOf(this.f19482E));
        if (profileListView == null) {
            return null;
        }
        return profileListView.getListView();
    }

    public void m21111d(int i) {
        this.f19482E = i;
        UserRelationType userRelationType = this.f19515q ? UserRelationType.MINE : UserRelationType.OTHER;
        if (this.f19508j != null) {
            this.f19508j.setVisibility(8);
        }
        if (this.f19507i != null) {
            if (this.f19507i.getTabCount() == 0) {
                ae();
            }
            this.f19507i.getTabAt(i).select();
        }
        this.f19509k.setCurrentItem(i);
        if (i == 0 && this.f19524z != null) {
            aa();
            SingAnalytics.m26142d(userRelationType, m21074K());
        } else if (i == 1 && this.f19478A != null) {
            SingAnalytics.m26087a(userRelationType, m21075L());
        } else if (i == 2 && this.f19479B != null) {
            SingAnalytics.m26147e(userRelationType, this.f19479B.m18048d() - 1);
        } else if (i == 3 && this.f19480C != null) {
            SingAnalytics.m26150f(userRelationType, m21073J());
        }
        mo6488n();
        ListView aq = aq();
        if (aq != null) {
            m21092a(aq);
        }
    }

    public void m21089Z() {
        this.f19520v = 0;
        this.f19521w = 0;
    }

    @SupposeUiThread
    public void aa() {
        if (this.f19515q && !SubscriptionManager.a().b() && this.f19482E == 0 && this.f19524z != null) {
            ListView aq = aq();
            if (aq == null) {
                return;
            }
            if (this.f19524z.m18048d() != 0 || this.f19524z.m18026a().m17671u() || this.f19524z.m18026a().m17669s()) {
                MagicDataSource a = this.f19524z.m18026a();
                if (a instanceof ProfilePerformanceDataSource) {
                    this.f19522x = ((ProfilePerformanceDataSource) a).mo6264d().isEmpty();
                    View ar = ar();
                    ar.setShowFutureWarning(this.f19522x);
                    ar.m23509a();
                    ar.m23511a(((ProfilePerformanceDataSource) a).mo6264d());
                    if (aq.getFooterViewsCount() == 0) {
                        aq.addFooterView(ar);
                        return;
                    }
                    return;
                }
                return;
            }
            aq.removeFooterView(ar());
        }
    }

    public void ab() {
        if (this.f19515q && !SubscriptionManager.a().b() && this.f19482E == 0 && this.f19524z != null) {
            ListView aq = aq();
            if (aq != null) {
                aq.removeFooterView(ar());
            }
        }
    }

    private StorageWarningView ar() {
        if (this.f19523y == null) {
            this.f19523y = StorageWarningView.m23506a(getActivity(), this.f19522x, new OnClickListener(this) {
                final /* synthetic */ ProfileFragment f19451a;

                {
                    this.f19451a = r1;
                }

                public void onClick(View view) {
                    this.f19451a.mo6487a(UpsellManager.m25791a(false, null, "", null, UpsellType.STORAGE));
                }
            });
            this.f19523y.setOnClickListener(null);
        }
        return this.f19523y;
    }

    public void m21092a(ListView listView) {
        mo6485a((AbsListView) listView, ActionBarHighlightMode.AFTER_SCROLL, new ProfileListViewOnScrollListener());
    }

    private void as() {
        StorageWarningView ar = ar();
        if (ar.m23514d()) {
            int initialTopPos = ar.getInitialTopPos() + ar.getWarningLayoutHeight();
            int dimensionPixelSize = getActivity().getResources().getDimensionPixelSize(C1947R.dimen.vip_storage_divider_height);
            if (ar.getInitialTopPos() != 0 && initialTopPos + dimensionPixelSize < this.ae.y) {
                ar.m23510a(this.ae.y - (initialTopPos + dimensionPixelSize));
            } else if (ar.getInitialTopPos() == 0) {
                ar.m23510a(0);
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            Log.e(f19477e, "Bad result code, " + i2 + ", returned for request code: " + i);
            this.f19519u = false;
            return;
        }
        switch (i) {
            case 2201:
                this.f19519u = true;
                return;
            case 2202:
                if (intent != null) {
                    this.f19519u = true;
                    if (intent.getExtras() != null) {
                        Bitmap b = m20711b(intent);
                        if (b != null) {
                            m20707a(b, new Runnable(this) {
                                final /* synthetic */ ProfileFragment f19452a;

                                {
                                    this.f19452a = r1;
                                }

                                public void run() {
                                    this.f19452a.f19519u = false;
                                    this.f19452a.f19514p.picUrl = UserManager.a().h();
                                    this.f19452a.mo6573U();
                                }
                            });
                            return;
                        } else {
                            Log.e(f19477e, "Null bitmap returned from CROP_PHOTO_INTENT_CODE");
                            return;
                        }
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    protected boolean mo6447j() {
        return true;
    }
}
