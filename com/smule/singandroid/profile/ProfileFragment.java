/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.Animator$AnimatorListener
 *  android.annotation.SuppressLint
 *  android.app.Activity
 *  android.app.Fragment
 *  android.app.FragmentManager
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.ServiceConnection
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Point
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.IBinder
 *  android.os.Parcelable
 *  android.support.annotation.NonNull
 *  android.support.design.widget.TabLayout
 *  android.support.design.widget.TabLayout$OnTabSelectedListener
 *  android.support.design.widget.TabLayout$Tab
 *  android.support.v4.content.ContextCompat
 *  android.support.v4.view.PagerAdapter
 *  android.support.v4.view.ViewPager
 *  android.support.v4.view.ViewPager$OnPageChangeListener
 *  android.text.Html
 *  android.text.TextUtils
 *  android.view.Display
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnLayoutChangeListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.view.WindowManager
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.ListView
 *  android.widget.RelativeLayout
 *  android.widget.ScrollView
 *  android.widget.TextView
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity$EntryPoint
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity$StartupMode
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_$IntentBuilder_
 *  com.smule.singandroid.songbook_search.SearchFragment
 *  com.smule.singandroid.survey.SurveyContext
 *  com.smule.singandroid.survey.SurveyContext$EntryPoint
 *  com.smule.singandroid.survey.SurveyPresenter
 *  com.smule.singandroid.upsell.UpsellManager
 *  com.smule.singandroid.upsell.UpsellType
 *  com.smule.singandroid.utils.AnimatorEndListener
 *  com.smule.singandroid.utils.ImageToDiskUtils
 *  com.smule.singandroid.utils.LayoutUtils
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.NavigationUtils
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer$ActionBarHighlightMode
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$ProfileCustomizationFeature
 *  com.smule.singandroid.utils.SingAnalytics$ProfilePagevwType
 *  com.smule.singandroid.utils.SingAnalytics$UserRelationType
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.SupposeUiThread
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 *  org.androidannotations.api.builder.PostActivityStarter
 */
package com.smule.singandroid.profile;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.TextUtils;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.api.PerformancesAPI;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ColorTheme;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.ProfileCustomizations;
import com.smule.android.network.models.SingUserProfile;
import com.smule.android.network.models.UserProfile;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.ui.TouchInterceptingFrameLayout;
import com.smule.android.ui.dialogs.SmuleDialog;
import com.smule.android.uploader.FileUploaderService;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.OperationLoader;
import com.smule.android.utils.SimpleBarrier;
import com.smule.android.utils.UiHandler;
import com.smule.android.utils.WeakListener;
import com.smule.chat.Chat;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.PeerChat;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.CustomTabLayout;
import com.smule.singandroid.CustomViewPager;
import com.smule.singandroid.FollowListFragment;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.PerformanceListItemContainer;
import com.smule.singandroid.PhotoTakingFragment;
import com.smule.singandroid.SettingsFragment;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.WebViewFragment;
import com.smule.singandroid.adapters.profile.ProfileArrangementDataSource;
import com.smule.singandroid.adapters.profile.ProfileOpenCallDataSource;
import com.smule.singandroid.adapters.profile.ProfilePerformanceDataSource;
import com.smule.singandroid.bookmark.BookmarkDialogCallback;
import com.smule.singandroid.bookmark.MediaPlayingMenuManager;
import com.smule.singandroid.boost.BoostStateMachine;
import com.smule.singandroid.chat.ChatAnalytics;
import com.smule.singandroid.chat.ChatFragment;
import com.smule.singandroid.chat.MessageCenterFragment;
import com.smule.singandroid.chat.activator.ChatActivator;
import com.smule.singandroid.chat.activator.ChatActivatorDialog;
import com.smule.singandroid.common.JoinSectionType;
import com.smule.singandroid.crm.SingAppboy;
import com.smule.singandroid.customviews.ColorThemeSelector;
import com.smule.singandroid.customviews.MasterToolbar;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.SingTabLayoutHelper;
import com.smule.singandroid.customviews.StorageWarningView;
import com.smule.singandroid.datasource.BaseProfileDataSource;
import com.smule.singandroid.dialogs.BusyScreenDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.HandleOkOnCancelDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.fragments.FlagUserFragment;
import com.smule.singandroid.fragments.OpenCallPerformancesListFragment;
import com.smule.singandroid.list_items.ListingListItem;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.list_items.OpenCallListItem;
import com.smule.singandroid.list_items.PerformanceItemInterface;
import com.smule.singandroid.list_items.VideoUploadingView;
import com.smule.singandroid.media_player_service.MediaPlayerService;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.mediaplaying.MediaPlayingPlayable;
import com.smule.singandroid.profile.FavoritesAdapter;
import com.smule.singandroid.profile.InvitesAdapter;
import com.smule.singandroid.profile.OwnedArrangementsAdapter;
import com.smule.singandroid.profile.PerformancesAdapter;
import com.smule.singandroid.profile.ProfileFragment_;
import com.smule.singandroid.profile.ProfileListView;
import com.smule.singandroid.profile.ProfilePreview;
import com.smule.singandroid.profile.ProfileUserInfo;
import com.smule.singandroid.profile.ProfileUserInfoView;
import com.smule.singandroid.profile.Theme;
import com.smule.singandroid.runtimepermissions.SingPermissionRequests;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity_;
import com.smule.singandroid.songbook_search.SearchFragment;
import com.smule.singandroid.survey.AVQualityPerformanceInfo;
import com.smule.singandroid.survey.SurveyContext;
import com.smule.singandroid.survey.SurveyPresenter;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;
import com.smule.singandroid.utils.AnimatorEndListener;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.ImageToDiskUtils;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.NavigationUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import com.smule.singandroid.utils.SingAnalytics;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.builder.PostActivityStarter;

@SuppressLint(value={"InflateParams"})
@EFragment
public class ProfileFragment
extends PhotoTakingFragment
implements ProfileUserInfoView.ProfileUserInfoViewListener {
    public static final String g = ProfileFragment.class.getName();
    @InstanceState
    protected int A;
    @InstanceState
    protected boolean B;
    @InstanceState
    protected boolean C;
    @InstanceState
    protected boolean D = true;
    @InstanceState
    protected boolean E;
    @InstanceState
    protected boolean F;
    @InstanceState
    protected boolean G;
    @InstanceState
    protected boolean H;
    @InstanceState
    protected boolean I;
    @InstanceState
    protected int J = -1;
    @InstanceState
    protected Intent K;
    @InstanceState
    protected boolean L;
    @InstanceState
    protected int M = -1;
    WeakListener.OnGlobalLayoutListener N;
    StorageWarningView O;
    protected PerformancesAdapter P;
    protected InvitesAdapter Q;
    protected OwnedArrangementsAdapter R;
    protected FavoritesAdapter S;
    protected ConcurrentHashMap<Integer, Boolean> T;
    protected boolean U;
    int[] V;
    int W;
    protected SimpleBarrier X;
    final Observer Y;
    final Animator.AnimatorListener Z;
    private Observer aA;
    private Observer aB;
    private Point aC;
    private boolean aD;
    private Bitmap aE;
    private Bitmap aF;
    private ColorThemeSelector.OnThemeSelectedListener aG;
    private final View.OnClickListener aI;
    private ProfileUserInfoView.ProfileUserInfoViewListener aJ;
    final Animator.AnimatorListener aa;
    protected PerformanceItemInterface.PerformanceItemListener ab;
    protected OpenCallListItem.ExpandedPerformanceItemListener ac;
    private ProfilePreview ad;
    private boolean ae;
    private boolean af;
    private ProfilePagerAdapter ag;
    private boolean ah;
    private boolean ai;
    private boolean aj = false;
    private PerformanceV2 ak;
    private ConcurrentHashMap<String, Boolean> al = new ConcurrentHashMap();
    private FileUploaderService am;
    private boolean an = false;
    private boolean ao = false;
    private Menu ap;
    private LocalizedShortNumberFormatter aq;
    private boolean ar;
    private boolean as;
    private String at;
    private final Observer au;
    private MediaPlayerServiceController.MediaPlayerObserver av;
    private ServiceConnection aw;
    private PerformanceV2 ax;
    private Observer ay;
    private Observer az;
    @ViewById
    protected RelativeLayout h;
    @ViewById
    protected TouchInterceptingFrameLayout i;
    @ViewById
    protected CustomTabLayout j;
    @ViewById
    protected View k;
    @ViewById
    protected CustomViewPager l;
    @ViewById
    protected ProfileImageWithVIPBadge m;
    @ViewById
    protected ScrollView n;
    @ViewById
    protected FrameLayout o;
    @ViewById
    protected FrameLayout p;
    @ViewById
    protected ColorThemeSelector q;
    protected ProfileUserInfoView r;
    protected SingTabLayoutHelper s;
    @InstanceState
    protected ProfileUserInfo t;
    @InstanceState
    protected ProfileUserInfo u;
    @InstanceState
    protected volatile AccountIcon v;
    @InstanceState
    protected boolean w;
    @InstanceState
    protected String x;
    @InstanceState
    protected boolean y = false;
    @InstanceState
    protected int z;

    /*
     * Enabled aggressive block sorting
     */
    public ProfileFragment() {
        int[] arrn;
        if (this.w) {
            int[] arrn2 = arrn = new int[4];
            arrn2[0] = 1;
            arrn2[1] = 0;
            arrn2[2] = 3;
            arrn2[3] = 2;
        } else {
            int[] arrn3 = arrn = new int[4];
            arrn3[0] = 1;
            arrn3[1] = 0;
            arrn3[2] = 3;
            arrn3[3] = 2;
        }
        this.V = arrn;
        this.W = 0;
        this.at = null;
        this.Y = new Observer(){

            @Override
            public void update(Observable observable, Object object) {
                ProfileFragment.this.a(ProfileFragment.this.at, "onMediaPaused");
            }
        };
        this.Z = new Animator.AnimatorListener(){

            public void onAnimationCancel(Animator animator2) {
            }

            public void onAnimationEnd(Animator animator2) {
            }

            public void onAnimationRepeat(Animator animator2) {
            }

            public void onAnimationStart(Animator animator2) {
                ProfileFragment.this.p.setVisibility(0);
                ProfileFragment.this.q.setVisibility(0);
            }
        };
        this.aa = new AnimatorEndListener(){

            public void onAnimationEnd(Animator animator2) {
                if (!ProfileFragment.this.isAdded()) {
                    return;
                }
                if (ProfileFragment.this.p() != null) {
                    ProfileFragment.this.p().a(BaseFragment.c);
                }
                ProfileFragment.this.f(true);
                ProfileFragment.this.p.setVisibility(8);
                ProfileFragment.this.q.setVisibility(8);
            }
        };
        this.au = new Observer(){

            @Override
            public void update(Observable object, Object object2) {
                block5 : {
                    block6 : {
                        block7 : {
                            if (!ProfileFragment.this.isAdded()) {
                                ProfileFragment.this.ar = true;
                                ProfileFragment.this.D = true;
                                return;
                            }
                            if (!ProfileFragment.this.D) break block5;
                            object = object2 = com.smule.android.network.managers.UserManager.a().s();
                            if (!ProfileFragment.this.O()) break block6;
                            if (object2 == null) break block7;
                            object = object2;
                            if (!object2.trim().isEmpty()) break block6;
                        }
                        object = ProfileFragment.this.getResources().getString(2131297192);
                    }
                    if (ProfileFragment.this.X() != null) {
                        ProfileFragment.this.X().b((String)object);
                    }
                }
                ProfileFragment.this.aj();
            }
        };
        this.av = new MediaPlayerServiceController.MediaPlayerObserver(){

            @Override
            public void a(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
            }

            @Override
            public void b(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
            }

            @Override
            public void c(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
                ProfileFragment.this.at = string2;
            }

            @Override
            public void d(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
                if (MediaPlayerService.a() != null && MediaPlayerService.a().e()) {
                    ProfileFragment.this.a(string2, "onMediaPaused");
                }
            }

            @Override
            public void f(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
                ProfileFragment.this.at = string2;
            }

            @Override
            public void g(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
            }

            @Override
            public void h(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
            }
        };
        this.aw = new ServiceConnection(){

            public void onServiceConnected(ComponentName object, IBinder iBinder) {
                object = (FileUploaderService.FileUploaderBinder)iBinder;
                ProfileFragment.this.am = object.a();
                ProfileFragment.this.an = true;
                ProfileFragment.this.am.d();
                ProfileFragment.this.ax();
                Log.b(ProfileFragment.g, "Binding service end - connected");
            }

            public void onServiceDisconnected(ComponentName componentName) {
                ProfileFragment.this.an = false;
                ProfileFragment.this.ax();
                Log.b(ProfileFragment.g, "Binding service end - disconnected");
            }
        };
        this.ab = new PerformanceItemInterface.PerformanceItemListener(){

            @Override
            public void a(MediaPlayingViewInterface mediaPlayingViewInterface, AccountIcon accountIcon) {
                Log.b(ProfileFragment.g, "mPerformanceItemListener - onAccountIconClicked called");
            }

            @Override
            public void a(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
                if (mediaPlayingViewInterface == null || performanceV2 == null) {
                    return;
                }
                Log.b(ProfileFragment.g, "mPerformanceItemListener - onPerformanceItemClick called");
                ProfileFragment.this.a(mediaPlayingViewInterface, performanceV2, new CheckVideoStatusCallback(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    @Override
                    public void a(PerformanceV2 performanceV2) {
                        boolean bl = true;
                        boolean bl2 = performanceV2.e() && performanceV2.seed && performanceV2.childCount > 0;
                        if (!MiscUtils.a((PerformanceV2)performanceV2) || !bl2) {
                            // empty if block
                        }
                        ProfileFragment.this.f(performanceV2);
                        if (bl2) {
                            ProfileCustomizations profileCustomizations = ProfileFragment.this.u.d();
                            ProfileFragment profileFragment = ProfileFragment.this;
                            if (profileCustomizations == null || profileCustomizations.pinPerformanceIcon == null) {
                                bl = false;
                            }
                            profileFragment.a(OpenCallPerformancesListFragment.a(performanceV2, bl));
                        }
                    }
                });
            }

            @Override
            public void b(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
                if (mediaPlayingViewInterface == null || performanceV2 == null) {
                    return;
                }
                Log.b(ProfileFragment.g, "mPerformanceItemListener - onAlbumArtClicked called");
                ProfileFragment.this.a(mediaPlayingViewInterface, performanceV2, new CheckVideoStatusCallback(){

                    @Override
                    public void a(PerformanceV2 performanceV2) {
                        MediaPlayerServiceController.a().a(performanceV2.performanceKey);
                        ProfileFragment.this.f(performanceV2);
                    }
                });
            }

        };
        this.ac = new OpenCallListItem.ExpandedPerformanceItemListener(){

            @Override
            public void a(PerformanceV2 performanceV2, boolean bl) {
                final MediaPlayingActivity mediaPlayingActivity = (MediaPlayingActivity)ProfileFragment.this.getActivity();
                mediaPlayingActivity.am().a(performanceV2, new BookmarkDialogCallback(){

                    @Override
                    public void a(PerformanceV2 performanceV2) {
                        new UiHandler((Activity)mediaPlayingActivity).a(new Runnable(){

                            @Override
                            public void run() {
                                mediaPlayingActivity.am().b();
                            }
                        });
                        NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "BOOKMARKED_PERFORMANCE", performanceV2);
                    }

                    @Override
                    public void b(PerformanceV2 performanceV2) {
                        new UiHandler((Activity)mediaPlayingActivity).a(new Runnable(){

                            @Override
                            public void run() {
                                mediaPlayingActivity.am().c();
                            }
                        });
                        NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "FAVORITED_PERFORMANCE", performanceV2);
                    }

                    @Override
                    public void c(PerformanceV2 performanceV2) {
                        NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "UNBOOKMARKED_PERFORMANCE", performanceV2);
                        performanceV2 = ProfileFragment.this.aG();
                        if (performanceV2 != null) {
                            ProfileFragment.this.a((ListView)performanceV2);
                        }
                    }

                    @Override
                    public void d(PerformanceV2 performanceV2) {
                        NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "UNFAVORITED_PERFORMANCE", performanceV2);
                    }

                }, bl);
            }

            @Override
            public void a(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
                if (openCallListItem == null || performanceV2 == null) {
                    return;
                }
                SingAnalytics.a((String)openCallListItem.getPerformance().performanceKey, (String)PerformanceV2Util.f((PerformanceV2)openCallListItem.getPerformance()), (String)PerformanceV2Util.h((PerformanceV2)openCallListItem.getPerformance()), (boolean)openCallListItem.getPerformance().video, Analytics.f);
                ProfileFragment.this.a(openCallListItem, performanceV2, new CheckVideoStatusCallback(){

                    @Override
                    public void a(PerformanceV2 performanceV2) {
                        PreSingActivity.a((Context)ProfileFragment.this.getActivity()).a(PreSingActivity.StartupMode.b).a(performanceV2).a(PreSingActivity.EntryPoint.b).a();
                        SingAnalytics.a((String)PerformanceV2Util.i((PerformanceV2)performanceV2), (JoinSectionType)JoinSectionType.c, (String)PerformanceV2Util.h((PerformanceV2)performanceV2));
                    }
                });
            }

            @Override
            public void b(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
                if (openCallListItem == null || performanceV2 == null) {
                    return;
                }
                SingAnalytics.a((String)openCallListItem.getPerformance().performanceKey, (String)PerformanceV2Util.f((PerformanceV2)openCallListItem.getPerformance()), (String)PerformanceV2Util.h((PerformanceV2)openCallListItem.getPerformance()), (boolean)openCallListItem.getPerformance().video, Analytics.b);
                ProfileFragment.this.a(openCallListItem, performanceV2, new CheckVideoStatusCallback(){

                    @Override
                    public void a(PerformanceV2 performanceV2) {
                        MediaPlayerServiceController.a().a(performanceV2.performanceKey);
                        ProfileFragment.this.f(performanceV2);
                    }
                });
            }

            @Override
            public void c(OpenCallListItem object, PerformanceV2 performanceV2) {
                object = ProfileFragment.a(performanceV2.accountIcon);
                ((MediaPlayingActivity)ProfileFragment.this.getActivity()).a((BaseFragment)((Object)object), object.t());
            }

            @Override
            public void d(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
                if (openCallListItem == null || performanceV2 == null) {
                    return;
                }
                Log.b(ProfileFragment.g, "onExpandedPerformanceItemMetaDataClick - opencall performers: " + performanceV2.totalPerformers);
                ProfileFragment.this.a(openCallListItem, performanceV2, new CheckVideoStatusCallback(){

                    @Override
                    public void a(PerformanceV2 performanceV2) {
                        if (performanceV2.f()) {
                            ProfileFragment.this.f(performanceV2);
                            return;
                        }
                        ProfileFragment.this.p().a(performanceV2, false, true);
                        ProfileFragment.this.a(OpenCallPerformancesListFragment.a(performanceV2));
                    }
                });
            }

        };
        this.ay = new Observer(){

            @Override
            public void update(Observable observable, Object object) {
                ProfileFragment.this.aj();
            }
        };
        this.az = new Observer(){

            @Override
            public void update(Observable observable, Object object) {
                if (ProfileFragment.this.r != null && ProfileFragment.this.X() != null) {
                    ProfileFragment.this.r.a(ProfileFragment.this.X());
                }
            }
        };
        this.aA = new Observer(){

            @Override
            public void update(Observable object, Object object2) {
                if (!ProfileFragment.this.isAdded()) {
                    return;
                }
                if (!(object2 instanceof HashMap)) {
                    Log.d(ProfileFragment.g, "notification payload error", new RuntimeException());
                    return;
                }
                object = (PerformanceV2)((HashMap)object2).get("BOOST_PERFORMANCE");
                if (object == null) {
                    Log.d(ProfileFragment.g, "notification payload error", new RuntimeException("performance null"));
                    return;
                }
                ProfileFragment.this.d((PerformanceV2)object);
            }
        };
        this.aB = new Observer(){

            /*
             * Unable to fully structure code
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            @Override
            public void update(Observable var1_1, Object var2_2) {
                block14 : {
                    var4_3 = 0;
                    Log.b(ProfileFragment.g, "mPerformanceUpdatedObserver - called");
                    if (!(var2_2 instanceof HashMap)) ** GOTO lbl48
                    var1_1 = (HashMap)var2_2;
                    if (var1_1.containsKey("UPDATED_PERFORMANCE")) {
                        ProfileFragment.b(ProfileFragment.this, (PerformanceV2)var1_1.get("UPDATED_PERFORMANCE"));
                        ProfileFragment.b(ProfileFragment.this, true);
                    }
                    if (var1_1.containsKey("PINNED_PERFORMANCE") && ProfileFragment.this.u != null) {
                        ProfileFragment.this.u.a((PerformanceV2)var1_1.get("PINNED_PERFORMANCE"));
                        ProfileFragment.this.D = true;
                    }
                    if (!ProfileFragment.this.isAdded()) {
                        return;
                    }
                    if (var1_1.containsKey("DELETED_PERFORMANCE")) {
                        var2_2 = (PerformanceV2)var1_1.get("DELETED_PERFORMANCE");
                        ProfileFragment.this.c((PerformanceV2)var2_2);
                    }
                    if (var1_1.containsKey("UPDATED_PERFORMANCE") && (var2_2 = (PerformanceV2)var1_1.get("UPDATED_PERFORMANCE")) != null) {
                        ProfileFragment.this.d((PerformanceV2)var2_2);
                        ProfileFragment.b(ProfileFragment.this, false);
                        ProfileFragment.b(ProfileFragment.this, null);
                    }
                    if (var1_1.containsKey("FAVORITED_PERFORMANCE") && ProfileFragment.this.w) {
                        var2_2 = new PerformanceListItemContainer((PerformanceV2)var1_1.get("FAVORITED_PERFORMANCE"));
                        ProfileFragment.this.S.a().a(0, var2_2);
                        ProfileFragment.this.S.a().p();
                    }
                    if (!var1_1.containsKey("BOOKMARKED_PERFORMANCE") || !ProfileFragment.this.w) ** GOTO lbl36
                    var2_2 = new PerformanceListItemContainer((PerformanceV2)var1_1.get("BOOKMARKED_PERFORMANCE"));
                    var2_2.d = true;
                    var5_4 = (ProfileOpenCallDataSource)ProfileFragment.this.Q.a();
                    if (ProfileFragment.this.Q.d() != 0) break block14;
                    var5_4.a(0, (PerformanceListItemContainer)var2_2);
                    var5_4.p();
                    ** GOTO lbl36
                }
                var3_5 = 0;
                do {
                    block15 : {
                        if (var3_5 < ProfileFragment.this.Q.d()) break block15;
lbl36: // 4 sources:
                        do {
                            if (var1_1.containsKey("UNFAVORITED_PERFORMANCE")) {
                                var2_2 = new PerformanceListItemContainer((PerformanceV2)var1_1.get("UNFAVORITED_PERFORMANCE"));
                                ProfileFragment.a(ProfileFragment.this.S, (PerformanceListItemContainer)var2_2);
                            }
                            if (!var1_1.containsKey("UNBOOKMARKED_PERFORMANCE")) ** GOTO lbl48
                            var1_1 = new PerformanceListItemContainer((PerformanceV2)var1_1.get("UNBOOKMARKED_PERFORMANCE"));
                            var1_1.d = true;
                            var3_5 = var4_3;
                            do {
                                block16 : {
                                    if (var3_5 < ProfileFragment.this.Q.d() && !((PerformanceListItemContainer)ProfileFragment.this.Q.a().a(var3_5)).equals(var1_1)) break block16;
                                    ProfileFragment.this.Q.a().a(var1_1);
                                    ProfileFragment.this.Q.a().p();
lbl48: // 3 sources:
                                    ProfileFragment.this.al();
                                    return;
                                }
                                ++var3_5;
                            } while (true);
                            break;
                        } while (true);
                    }
                    if (!((PerformanceListItemContainer)var5_4.a((int)var3_5)).d) {
                        var5_4.a(var3_5, (PerformanceListItemContainer)var2_2);
                        var5_4.p();
                        ** continue;
                    }
                    ++var3_5;
                } while (true);
            }
        };
        this.aC = new Point();
        this.aG = new ColorThemeSelector.OnThemeSelectedListener(){

            @Override
            public void a(@NonNull Theme theme) {
                ProfileUserInfo.ColorSet colorSet = new ProfileUserInfo.ColorSet();
                colorSet.a = ContextCompat.getColor((Context)ProfileFragment.this.getActivity(), (int)theme.a());
                colorSet.b = ContextCompat.getColor((Context)ProfileFragment.this.getActivity(), (int)theme.b());
                colorSet.c = ContextCompat.getColor((Context)ProfileFragment.this.getActivity(), (int)theme.c());
                colorSet.d = ContextCompat.getColor((Context)ProfileFragment.this.getActivity(), (int)theme.d());
                colorSet.e = ContextCompat.getColor((Context)ProfileFragment.this.getActivity(), (int)theme.e());
                ProfileFragment.this.u.a(theme);
                ProfileFragment.this.u.a(colorSet);
                ProfileFragment.this.H = true;
                ProfileFragment.this.aJ();
            }
        };
        this.aI = new View.OnClickListener(){

            public void onClick(View view) {
                ProfileFragment.this.as();
                ProfileFragment.this.r.getProfileImage().performClick();
            }
        };
        this.aJ = new ProfileUserInfoView.ProfileUserInfoViewListener(){

            @Override
            public void Z() {
                if (!ProfileFragment.this.C) {
                    ProfileFragment.this.Z();
                }
            }

            @Override
            public void a(TextView textView) {
                if (!ProfileFragment.this.C) {
                    ProfileFragment.this.a(textView);
                }
            }

            @Override
            public void a(ProfileImageWithVIPBadge profileImageWithVIPBadge) {
                if (ProfileFragment.this.C) {
                    ProfileFragment.this.aL();
                    return;
                }
                ProfileFragment.this.aT();
            }

            @Override
            public void aa() {
                if (!ProfileFragment.this.C) {
                    ProfileFragment.this.aa();
                }
            }

            @Override
            public boolean ab() {
                return ProfileFragment.this.C;
            }

            @Override
            public void b(View view) {
                if (!ProfileFragment.this.C) {
                    ProfileFragment.this.b(view);
                }
            }

            @Override
            public void b(AccountIcon accountIcon) {
                if (!ProfileFragment.this.C) {
                    ProfileFragment.this.b(accountIcon);
                }
            }

            @Override
            public void c(View view) {
                if (!ProfileFragment.this.C) {
                    ProfileFragment.this.c(view);
                }
            }
        };
    }

    public static ProfileFragment a() {
        return ProfileFragment.a(com.smule.android.network.managers.UserManager.a().O());
    }

    public static ProfileFragment a(AccountIcon accountIcon) {
        ProfileFragment_ profileFragment_ = new ProfileFragment_();
        Bundle bundle = new Bundle();
        bundle.putParcelable("PROFILE_ACCOUNT_ICON_KEY", (Parcelable)accountIcon);
        profileFragment_.setArguments(bundle);
        return profileFragment_;
    }

    public static ProfileFragment a(AccountIcon object, boolean bl) {
        object = ProfileFragment.a((AccountIcon)object);
        object.L = bl;
        return object;
    }

    @NonNull
    private ProfileUserInfo.ColorSet a(ProfileCustomizations profileCustomizations) {
        ProfileUserInfo.ColorSet colorSet = new ProfileUserInfo.ColorSet((Context)this.getActivity());
        if (profileCustomizations != null && profileCustomizations.theme != null) {
            boolean bl = profileCustomizations.theme.getLightText();
            colorSet.a = Theme.a(profileCustomizations.theme.getSnpBackgroundColor());
            colorSet.b = Theme.a(profileCustomizations.theme.getSnpForegroundColor());
            if (bl) {
                colorSet.c = colorSet.b;
                colorSet.d = ContextCompat.getColor((Context)this.getActivity(), (int)2131689874);
                colorSet.e = colorSet.a;
            }
        }
        return colorSet;
    }

    private void a(TabLayout.Tab tab) {
        this.s.a(true, tab);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(SingUserProfile object) {
        Log.e(g, "fetchProfileForUser - User account not retrieved");
        object = object.a != null && object.a.b == 1002 ? new TextAlertDialog((Context)this.getActivity(), this.getString(2131296682), this.getString(2131297197), true, false) : new TextAlertDialog((Context)this.getActivity(), this.getString(2131296682), this.getString(2131297198), true, false);
        Runnable runnable = new Runnable(){

            @Override
            public void run() {
                if (ProfileFragment.this.K()) {
                    ((MasterActivity)ProfileFragment.this.getActivity()).b();
                    return;
                }
                ProfileFragment.this.a(ProfileFragment.this.getTag());
            }
        };
        object.a(this.getString(2131296705), null);
        object.b(runnable);
        object.a(runnable);
        object.show();
    }

    static /* synthetic */ boolean a(MagicAdapter magicAdapter, PerformanceListItemContainer performanceListItemContainer) {
        return ProfileFragment.b(magicAdapter, performanceListItemContainer);
    }

    private void aA() {
        if (this.U) {
            return;
        }
        this.U = true;
        final BusyScreenDialog busyScreenDialog = new BusyScreenDialog((Context)this.getActivity(), this.getResources().getString(2131296617));
        busyScreenDialog.show();
        SingApplication.k().a(this.G(), false, new Completion<ChatStatus>(){

            @Override
            public void a(ChatStatus chatStatus) {
                busyScreenDialog.dismiss();
                ProfileFragment.this.U = false;
                if (chatStatus == ChatStatus.a) {
                    ProfileFragment.this.b(2131296616);
                    ProfileFragment.this.aB();
                    return;
                }
                ChatUtils.a((Context)ProfileFragment.this.getActivity(), 2131296496, chatStatus);
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    private void aB() {
        if (this.ap != null) {
            MenuItem menuItem = this.ap.findItem(2131756852);
            MenuItem menuItem2 = this.ap.findItem(2131756853);
            if (!ChatUtils.a()) {
                menuItem.setVisible(false);
                menuItem2.setVisible(false);
            } else if (SingApplication.k().a(this.v.accountId)) {
                menuItem.setVisible(false);
                menuItem2.setVisible(true);
            } else {
                menuItem.setVisible(true);
                menuItem2.setVisible(false);
            }
        }
        if (this.r != null && this.X() != null) {
            this.r.a(this.X());
        }
    }

    private void aC() {
        this.X.d();
        final int n = this.e;
        com.smule.android.network.managers.UserManager.a().a(com.smule.android.network.managers.UserManager.a().f(), new UserManager(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void handleResponse(UserManager.UserBlurbResponse object) {
                Object object2;
                block7 : {
                    block8 : {
                        if (!ProfileFragment.this.a(n)) {
                            return;
                        }
                        object2 = "";
                        if (object.a()) {
                            if (object.mBlurb == null) {
                                object.mBlurb = "";
                            }
                            com.smule.android.network.managers.UserManager.a().a(object.mBlurb);
                            object = object.mBlurb;
                        } else {
                            Log.e(ProfileFragment.g, "getPersonalBlurb - errorGettingBlurb - called for personal profile");
                            object = object2;
                        }
                        object2 = object;
                        if (!ProfileFragment.this.K()) break block7;
                        if (object == null) break block8;
                        object2 = object;
                        if (!object.trim().isEmpty()) break block7;
                    }
                    object2 = ProfileFragment.this.getResources().getString(2131297192);
                }
                ProfileFragment.this.X().b((String)object2);
                ProfileFragment.this.aj();
                ProfileFragment.this.X.a();
            }
        });
    }

    private void aD() {
        ProfilePerformanceDataSource profilePerformanceDataSource = (ProfilePerformanceDataSource)this.P.a();
        List<PerformanceListItemContainer> list = profilePerformanceDataSource.e();
        if (list != null && !list.isEmpty()) {
            profilePerformanceDataSource.a(profilePerformanceDataSource.k(), list.get(0));
            profilePerformanceDataSource.w();
            profilePerformanceDataSource.p();
            this.aH().c();
        }
        this.ap();
    }

    private void aE() {
        if (this.w) {
            NotificationCenter.a().a("PERSONAL_FOLLOW_INFO_UPDATED_EVENT", this.az);
            NotificationCenter.a().a("PROFILE_UPDATED_EVENT", this.ay);
            NotificationCenter.a().a(BoostStateMachine.State.g.a(), this.aA);
        }
        NotificationCenter.a().a("PERFORMANCE_UPDATED_NOTIFICATION", this.aB);
        NotificationCenter.a().a("PROFILE_UPDATED_NOTIFICATION", this.au);
    }

    private void aF() {
        if (this.w) {
            NotificationCenter.a().b("PERSONAL_FOLLOW_INFO_UPDATED_EVENT", this.az);
            NotificationCenter.a().b("PROFILE_UPDATED_EVENT", this.ay);
            NotificationCenter.a().b(BoostStateMachine.State.g.a(), this.aA);
        }
        NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", this.aB);
        NotificationCenter.a().b("PROFILE_UPDATED_NOTIFICATION", this.au);
    }

    private ListView aG() {
        ProfileListView profileListView = this.ag.a.get(this.J);
        if (profileListView == null) {
            return null;
        }
        return profileListView.getListView();
    }

    private StorageWarningView aH() {
        if (this.O == null) {
            this.O = StorageWarningView.a(this.getActivity(), this.B, new View.OnClickListener(){

                public void onClick(View view) {
                    ProfileFragment.this.a(UpsellManager.a((boolean)false, (SongbookEntry)null, (String)"", (String)null, (UpsellType)UpsellType.i));
                }
            });
            this.O.setOnClickListener(null);
        }
        return this.O;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void aI() {
        StorageWarningView storageWarningView;
        block5 : {
            block4 : {
                storageWarningView = this.aH();
                if (!storageWarningView.d()) break block4;
                int n = storageWarningView.getInitialTopPos() + storageWarningView.getWarningLayoutHeight();
                int n2 = this.getActivity().getResources().getDimensionPixelSize(2131427957);
                if (storageWarningView.getInitialTopPos() != 0 && n + n2 < this.aC.y) {
                    storageWarningView.a(this.aC.y - (n + n2));
                    return;
                }
                if (storageWarningView.getInitialTopPos() == 0) break block5;
            }
            return;
        }
        storageWarningView.a(0);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void aJ() {
        boolean bl = false;
        if (this.s == null) return;
        if (this.j == null) {
            return;
        }
        ProfileUserInfo.ColorSet colorSet = this.u.e();
        this.j.setBackgroundColor(colorSet.a);
        this.j.setSelectedTabIndicatorColor(colorSet.b);
        this.s.a(colorSet.d, colorSet.b);
        this.r.setColorTheme(colorSet);
        this.l.getCurrentItem();
        if (this.P != null) {
            this.P.a().q();
        }
        if (this.R != null) {
            this.R.a().q();
        }
        if (this.Q != null) {
            this.Q.a().q();
        }
        if (this.S != null) {
            this.S.a().q();
        }
        MasterToolbar masterToolbar = ((MediaPlayingActivity)this.getActivity()).U();
        boolean bl2 = colorSet.a == ContextCompat.getColor((Context)this.getActivity(), (int)Theme.a.a());
        bl2 = bl2 && this.ai;
        if (Theme.a((Context)this.getActivity(), colorSet.d) || bl2) {
            bl = true;
        }
        masterToolbar.a(colorSet.e, bl);
        this.aK();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void aK() {
        View view;
        if (this.u == null || this.J != 0 || this.P == null || (view = this.P.l()) == null || (view = (TextView)view.findViewById(2131756262)) == null) {
            return;
        }
        if (this.u.h() == Theme.a) {
            view.setTextColor(ContextCompat.getColor((Context)this.getActivity(), (int)2131689871));
            return;
        }
        view.setTextColor(this.u.e().a);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void aL() {
        boolean bl = true;
        boolean bl2 = true;
        if (!this.K()) {
            return;
        }
        Object object = this.r.getProfileImage();
        ImageView imageView = object.getImageView();
        boolean bl3 = !this.F || this.aH;
        super.a((View)object, imageView, true, bl3, 200, 200, 2131297136, SingPermissionRequests.b);
        super.a(this.r.getProfileCameraButton(), imageView, true, bl3, 200, 200, 2131297136, SingPermissionRequests.b);
        bl3 = this.G ? (this.r.getCoverPhoto() != null ? bl2 : false) : ((object = this.u.d()) != null && !TextUtils.isEmpty((CharSequence)object.coverUrl) ? bl : false);
        super.a((View)this.m, null, false, bl3, 2048, 2048, 2131297134, SingPermissionRequests.b);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void aM() {
        boolean bl;
        boolean bl2;
        boolean bl3;
        block14 : {
            Object object;
            block10 : {
                block12 : {
                    block13 : {
                        block9 : {
                            block11 : {
                                bl = true;
                                if (this.E) {
                                    if (this.aE != null) {
                                        this.a(this.aE, null);
                                        ImageToDiskUtils.b((Context)this.getActivity(), (String)"PROFILE_PICTURE_KEY");
                                        bl2 = true;
                                    } else {
                                        this.aE = ImageToDiskUtils.c((Context)this.getActivity(), (String)"PROFILE_PICTURE_KEY");
                                        if (this.aE == null) {
                                            this.ae();
                                            this.aP();
                                            bl2 = true;
                                        } else {
                                            bl2 = false;
                                        }
                                    }
                                    bl3 = !bl2;
                                    this.E = bl3;
                                } else {
                                    bl2 = false;
                                }
                                if (!SubscriptionManager.a().b()) break block9;
                                if (!this.G) break block10;
                                object = this.r.getCoverPhoto();
                                if (object == null) break block11;
                                this.a((Bitmap)object);
                                ImageToDiskUtils.b((Context)this.getActivity(), (String)"COVER_PICTURE_KEY");
                                bl2 = true;
                                break block12;
                            }
                            this.aF = ImageToDiskUtils.c((Context)this.getActivity(), (String)"COVER_PICTURE_KEY");
                            if (this.aF != null) break block13;
                            this.af();
                            this.aQ();
                            bl2 = true;
                            break block12;
                        }
                        this.aU();
                        break block14;
                    }
                    bl2 = false;
                }
                bl3 = !bl2;
                this.G = bl3;
            }
            if (this.H) {
                if (this.u.h() == Theme.a) {
                    object = new ColorTheme();
                } else {
                    object = this.u.e();
                    object = new ColorTheme(Theme.b(object.a), Theme.b(object.b), Theme.a((Context)this.getActivity(), object.d));
                }
                this.t.a(Theme.a((Context)this.getActivity(), (ColorTheme)object));
                this.t.a(this.u.e());
                com.smule.android.network.managers.UserManager.a().a((ColorTheme)object, null, null, null, null);
                this.H = false;
                bl2 = true;
            }
        }
        bl3 = !bl2 ? bl : false;
        this.I = bl3;
    }

    private void aN() {
        if (!this.isAdded() || this.r == null) {
            return;
        }
        this.r.h();
        if (this.O()) {
            String string2 = com.smule.android.network.managers.UserManager.a().h();
            this.X().a(string2);
            this.v.picUrl = string2;
        }
        if (this.v != null && this.v.picUrl != null && ImageUtils.a(this.v.picUrl)) {
            this.F = true;
            return;
        }
        this.F = false;
    }

    private void aO() {
        if (!this.isAdded() || this.r == null) {
            return;
        }
        this.r.setCoverPhoto(null);
    }

    private void aP() {
        if (this.X() != null) {
            this.X().a().picUrl = null;
            this.aH = false;
        }
    }

    private void aQ() {
        if (this.X() != null && this.X().d() != null) {
            this.X().d().coverUrl = null;
        }
    }

    private void aR() {
        this.u.a(this.t.h());
        this.u.a(this.t.e());
        this.aJ();
    }

    private void aS() {
        this.H = false;
        this.G = false;
        this.E = false;
        this.I = false;
        this.aE = null;
        this.aF = null;
    }

    private void aT() {
        this.r.getProfileImage().setOnClickListener(this.aI);
    }

    private void aU() {
        this.aN();
        this.aO();
        this.aR();
        this.aj();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void ax() {
        if (this.u == null || this.w && !this.an) {
            return;
        }
        this.X.d();
        this.ag = new ProfilePagerAdapter();
        this.l.setAdapter((PagerAdapter)this.ag);
        this.l.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            public void onPageScrollStateChanged(int n) {
            }

            public void onPageScrolled(int n, float f, int n2) {
            }

            public void onPageSelected(int n) {
            }
        });
        this.ay();
        this.l.setOffscreenPageLimit(3);
        if (this.T == null) {
            this.T = new ConcurrentHashMap();
        }
        if (this.T.size() == 4 || this.J != -1 || this.M != -1) {
            if (this.M != -1) {
                this.e(this.M);
                this.M = -1;
            } else {
                this.e(this.J);
            }
            if (this.aG() != null && this.A != 0) {
                this.aG().setSelectionFromTop(this.z, this.A);
            }
        }
        this.X.a();
    }

    private void ay() {
        if (this.s != null) {
            return;
        }
        this.s = new SingTabLayoutHelper(this.j, this.l);
        this.s.a(true);
        this.s.a(new TabLayout.OnTabSelectedListener(){

            public void onTabReselected(TabLayout.Tab tab) {
                ProfileFragment.this.a(tab);
            }

            public void onTabSelected(TabLayout.Tab tab) {
                ProfileFragment.this.a(tab);
                NotificationCenter.a().a("PERFORMANCE_TYPE_TABS_CLICKED", (Object)tab.getPosition());
            }

            public void onTabUnselected(TabLayout.Tab tab) {
                ProfileFragment.this.b(tab);
            }
        });
        this.aJ();
    }

    private void az() {
        Object object = this.getResources().getString(2131296469);
        object = new TextAlertDialog((Context)this.getActivity(), this.getResources().getString(2131296466), (String)object);
        object.a(2131296467, 2131296672);
        object.a(new CustomAlertDialog.CustomAlertDialogListener((TextAlertDialog)((Object)object)){
            final /* synthetic */ TextAlertDialog a;
            {
                this.a = textAlertDialog;
            }

            @Override
            public void a(CustomAlertDialog smuleDialog) {
                if (ProfileFragment.this.U) {
                    return;
                }
                ProfileFragment.this.U = true;
                smuleDialog = new BusyScreenDialog((Context)ProfileFragment.this.getActivity(), ProfileFragment.this.getResources().getString(2131296473));
                smuleDialog.show();
                SingApplication.k().a(ProfileFragment.this.G(), true, new Completion<ChatStatus>((BusyScreenDialog)smuleDialog){
                    final /* synthetic */ BusyScreenDialog a;
                    {
                        this.a = busyScreenDialog;
                    }

                    @Override
                    public void a(ChatStatus iterator) {
                        this.a.dismiss();
                        ProfileFragment.this.U = false;
                        if (iterator == ChatStatus.a) {
                            iterator = new ArrayList();
                            iterator.addAll(SingApplication.k().a(Chat.Bucket.a));
                            iterator.addAll(SingApplication.k().a(Chat.Bucket.b));
                            iterator = iterator.iterator();
                            while (iterator.hasNext()) {
                                Chat chat = (Chat)iterator.next();
                                if (!(chat instanceof PeerChat) || chat.f() != ProfileFragment.this.G()) continue;
                                SingApplication.k().a(chat, (Completion<ChatStatus>)null);
                            }
                            ProfileFragment.this.b(2131296472);
                            ProfileFragment.this.aB();
                            return;
                        }
                        ChatUtils.a((Context)ProfileFragment.this.getActivity(), 2131296496, (ChatStatus)((Object)iterator));
                    }
                });
            }

            @Override
            public void b(CustomAlertDialog customAlertDialog) {
                this.a.dismiss();
            }

        });
        object.show();
    }

    static /* synthetic */ PerformanceV2 b(ProfileFragment profileFragment, PerformanceV2 performanceV2) {
        profileFragment.ax = performanceV2;
        return performanceV2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(Bitmap bitmap) {
        boolean bl = bitmap != null;
        this.ai = bl;
        this.aF = bitmap;
        this.r.setCoverPhoto(this.aF);
        this.G = true;
        this.aL();
    }

    private void b(TabLayout.Tab tab) {
        this.s.a(false, tab);
    }

    private static boolean b(MagicAdapter magicAdapter, PerformanceListItemContainer performanceListItemContainer) {
        boolean bl = magicAdapter.a().a(performanceListItemContainer);
        if (bl) {
            magicAdapter.a().p();
        }
        return bl;
    }

    private void c(int n, int n2) {
        TextAlertDialog textAlertDialog = new TextAlertDialog((Context)this.getActivity(), this.getString(n), this.getString(n2), true, false);
        textAlertDialog.a(this.getString(2131296705), "");
        textAlertDialog.a(new CustomAlertDialog.CustomAlertDialogListener(){

            @Override
            public void a(CustomAlertDialog customAlertDialog) {
                customAlertDialog.dismiss();
            }

            @Override
            public void b(CustomAlertDialog customAlertDialog) {
                customAlertDialog.dismiss();
            }
        });
        textAlertDialog.show();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void c(Bitmap bitmap) {
        boolean bl = true;
        this.aE = bitmap;
        this.r.setProfilePhoto(bitmap);
        this.E = true;
        if (bitmap != null) {
            bl = false;
        }
        this.F = bl;
        this.aL();
    }

    private static void c(MagicAdapter magicAdapter, PerformanceListItemContainer performanceListItemContainer) {
        if (magicAdapter != null) {
            magicAdapter.a().b(performanceListItemContainer);
            magicAdapter.a().p();
        }
    }

    private void d(String string2) {
        if (this.getActivity() == null) {
            return;
        }
        String string3 = string2;
        if (string2 == null) {
            Log.e(g, "configureActionBar - userHandle is null; using blank string");
            string3 = "";
        }
        this.a((CharSequence)string3);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void e(Runnable runnable) {
        ProfileCustomizations profileCustomizations;
        if (!this.w) {
            this.X.d();
        }
        boolean bl = (profileCustomizations = this.u.d()) != null && !TextUtils.isEmpty((CharSequence)profileCustomizations.coverUrl);
        this.ai = bl;
        this.aJ();
        this.aj();
        this.D = true;
        if (runnable != null) {
            runnable.run();
        }
        if (!this.w) {
            this.ax();
            this.X.a();
        }
        this.X.a();
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean e(PerformanceV2 performanceV2) {
        if (!performanceV2.p() || !performanceV2.v()) {
            return false;
        }
        return true;
    }

    private void f(int n) {
        Iterator<ProfileListView> iterator = this.ag.a.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().getListView().setSelectionFromTop(0, n);
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void f(@NonNull PerformanceV2 var1_1) {
        block12 : {
            block14 : {
                block13 : {
                    switch (this.l.getCurrentItem()) {
                        default: {
                            Log.e(ProfileFragment.g, "unknown tab - cannot start continuous play");
                            return;
                        }
                        case 1: {
                            var5_2 = this.Q;
                            var3_3 = this.Q.d();
                            ** GOTO lbl16
                        }
                        case 0: {
                            var5_2 = this.P;
                            var3_3 = this.P.k();
                            ** GOTO lbl16
                        }
                        case 3: {
                            var5_2 = this.S;
                            var3_3 = this.S.d();
lbl16: // 3 sources:
                            var6_4 = new ArrayList<MediaPlayingPlayable>(var3_3);
                            var4_5 = 0;
                            var2_6 = -1;
lbl19: // 2 sources:
                            if (var4_5 >= var3_3) break block12;
                            var7_7 = ((PerformanceListItemContainer)var5_2.a(var4_5)).a();
                            if (var7_7 != null) break block13;
                            Log.e(ProfileFragment.g, "cannot do ContinuousPlay with null performances");
                            break block14;
                        }
                        case 2: 
                    }
                    Log.d(ProfileFragment.g, "Profile Songs tab is out of scope for Continuous Play feature", new IllegalStateException());
                    return;
                }
                var8_8 = this.c(var7_7.performanceKey);
                if (var8_8 != FileUploaderService.VideoUploadStatus.c && var8_8 != FileUploaderService.VideoUploadStatus.e) {
                    Log.b(ProfileFragment.g, "attempting to add non ready playable to the continuous playlist");
                } else {
                    var6_4.add(new MediaPlayingPlayable(var7_7));
                    if (!TextUtils.isEmpty((CharSequence)var1_1.performanceKey) && var1_1.performanceKey.equals(var7_7.performanceKey)) {
                        var2_6 = var6_4.size() - 1;
                    }
                }
            }
            ++var4_5;
            ** GOTO lbl19
        }
        var3_3 = var2_6;
        if (var2_6 == -1) {
            var6_4.add(0, new MediaPlayingPlayable(var1_1));
            var3_3 = 0;
        }
        this.a(var6_4, var3_3);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void f(boolean bl) {
        this.a(BaseFragment.ActionBarHighlightMode.f);
        this.v();
        if (bl) {
            this.u();
        } else {
            this.s();
        }
        if (this.getActivity() instanceof MediaPlayingActivity) {
            ((MediaPlayingActivity)this.getActivity()).X();
        }
    }

    private void g(boolean bl) {
        if (this.getActivity() instanceof MediaPlayingActivity) {
            ((MediaPlayingActivity)this.getActivity()).U().setIsInProfile(bl);
        }
    }

    static /* synthetic */ void r(ProfileFragment profileFragment) {
        profileFragment.aI();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void A() {
        long l = this.v.accountId;
        SingAnalytics.ProfilePagevwType profilePagevwType = this.L ? SingAnalytics.ProfilePagevwType.a : null;
        SingAnalytics.a((long)l, (SingAnalytics.ProfilePagevwType)profilePagevwType);
    }

    @Override
    protected boolean D() {
        if (!this.K()) {
            return true;
        }
        return false;
    }

    public AccountIcon F() {
        return (AccountIcon)this.getArguments().getParcelable("PROFILE_ACCOUNT_ICON_KEY");
    }

    public long G() {
        return this.F().accountId;
    }

    @UiThread
    protected void H() {
        if (!this.isAdded()) {
            return;
        }
        Intent intent = new Intent(SingApplication.g(), FileUploaderService.class);
        Log.b(g, "Binding service");
        this.ao = SingApplication.g().bindService(intent, this.aw, 1);
    }

    protected void I() {
        if (this.ao || !this.w) {
            return;
        }
        SingApplication.d().a("INIT_FILE_UPLOADER_SERVICE_KEY", Collections.singletonList("InitAppTask.OP_USER_LOGGED_IN"), new OperationLoader.Operation(){

            @Override
            public void a(OperationLoader object) {
                if (com.smule.android.network.managers.UserManager.a().y()) {
                    ProfileFragment.this.H();
                    object.c(this.f);
                    return;
                }
                object = new Observer((OperationLoader)object){
                    final /* synthetic */ OperationLoader a;
                    {
                        this.a = operationLoader;
                    }

                    @Override
                    public void update(Observable observable, Object object) {
                        if (com.smule.android.network.managers.UserManager.a().y()) {
                            NotificationCenter.a().b("USER_LOGGED_IN_EVENT", this);
                            NotificationCenter.a().b("USER_RE_LOGGED_IN_EVENT", this);
                            ProfileFragment.this.H();
                            this.a.c(6.this.f);
                        }
                    }
                };
                NotificationCenter.a().a("USER_LOGGED_IN_EVENT", (Observer)object);
                NotificationCenter.a().a("USER_RE_LOGGED_IN_EVENT", (Observer)object);
            }

        }).a();
    }

    @AfterViews
    protected void J() {
        Log.b(g, "updateFollowingViewBinding - begin");
        this.X = new SimpleBarrier(0, new Runnable(){

            @Override
            public void run() {
                if (ProfileFragment.this.k != null) {
                    ProfileFragment.this.k.setVisibility(8);
                }
            }
        });
        this.aE();
        this.l.setPagingEnabled(false);
        this.f(true);
        this.ac();
        if (!(this.X() == null || this.ar && this.w)) {
            this.D = false;
        }
        this.j.setSelectedTabIndicatorColor(this.getResources().getColor(2131689579));
        this.j.setSelectedTabIndicatorHeight(this.getResources().getDimensionPixelOffset(2131428171));
        this.a(true, false);
        this.al();
        if (this.K()) {
            this.ad = new ProfilePreview(this.h, this.i, this.Z, this.aa);
        }
        Log.b(g, "updateFollowingViewBinding - end");
    }

    public boolean K() {
        if (this.w && this.getFragmentManager().getBackStackEntryCount() <= 1) {
            return true;
        }
        return false;
    }

    public FileUploaderService L() {
        return this.am;
    }

    public boolean M() {
        return this.an;
    }

    public int N() {
        return this.J;
    }

    public boolean O() {
        return this.w;
    }

    public int P() {
        return this.e;
    }

    public int Q() {
        return ((BaseProfileDataSource)this.S.a()).x();
    }

    public int R() {
        return ((BaseProfileDataSource)this.P.a()).x();
    }

    public int S() {
        return ((ProfileOpenCallDataSource)this.Q.a()).e();
    }

    public int T() {
        return ((ProfileOpenCallDataSource)this.Q.a()).w();
    }

    public int U() {
        return ((ProfileArrangementDataSource)this.R.a()).e();
    }

    public int V() {
        return ((ProfileArrangementDataSource)this.R.a()).w();
    }

    public ConcurrentHashMap<String, Boolean> W() {
        return this.al;
    }

    ProfileUserInfo X() {
        return this.u;
    }

    public boolean Y() {
        return this.v.a();
    }

    @Override
    public void Z() {
        UserProfile userProfile = this.u.c();
        if (userProfile != null && userProfile.getNumberFollowers() > 0) {
            this.a(FollowListFragment.a(this.v.accountId, this.v.picUrl, 0, userProfile.getNumberFollowers()));
        }
    }

    public Runnable a(PerformanceV2 performanceV2) {
        return new VideoUploadCancelHandler(performanceV2, true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(int n, boolean bl) {
        int n2;
        block13 : {
            block14 : {
                block12 : {
                    block10 : {
                        n2 = 1;
                        if (this.T == null) {
                            this.T = new ConcurrentHashMap();
                        }
                        this.T.put(n, bl);
                        if (this.as || this.J != -1) break block12;
                        if (this.T.size() != 4) break block13;
                        this.as = true;
                        for (n = 0; n < 4; ++n) {
                            if (!this.T.get(this.V[n]).booleanValue()) continue;
                            this.e(this.V[n]);
                            n = n2;
                            break block10;
                        }
                        n = 0;
                    }
                    if (n == 0) break block14;
                }
                return;
            }
            this.e(this.W);
            return;
        }
        n2 = 0;
        while (n2 < 4) {
            if (n == this.V[0] && bl) {
                int n3;
                block11 : {
                    for (n3 = n2 - 1; n3 >= 0; --n3) {
                        if (!this.T.get(this.V[n3]).booleanValue()) continue;
                        n3 = 0;
                        break block11;
                    }
                    n3 = 1;
                }
                if (n3 != 0) {
                    this.as = true;
                    this.e(n);
                    return;
                }
            }
            ++n2;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void a(View view) {
        if (this.m.getId() == this.ag()) {
            this.G = SubscriptionManager.a().b();
            this.r.setCoverPhoto(null);
            this.aF = null;
            ImageToDiskUtils.b((Context)this.getActivity(), (String)"COVER_PICTURE_KEY");
        } else {
            this.E = true;
            this.F = true;
            this.aE = null;
            this.r.getProfileImage().getImageView().setImageResource(2130837899);
            ImageToDiskUtils.b((Context)this.getActivity(), (String)"PROFILE_PICTURE_KEY");
        }
        this.aL();
    }

    public void a(ListView listView) {
        this.a((AbsListView)listView, QuickReturnListViewMenuSyncer.ActionBarHighlightMode.c, new ProfileListViewOnScrollListener(), BaseFragment.ActionBarHighlightMode.f);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(TextView textView) {
        if (this.K()) {
            this.a(SettingsFragment.a(SettingsFragment.FocusField.a));
            return;
        } else {
            final int n = - LayoutUtils.a((View)textView, (View)this.r).y + this.getResources().getDimensionPixelOffset(2131428167);
            if (this.r.getTop() >= n) return;
            {
                this.a(new Runnable(){

                    @Override
                    public void run() {
                        ProfileFragment.this.f(n);
                    }
                });
                return;
            }
        }
    }

    @Override
    public void a(ProfileImageWithVIPBadge profileImageWithVIPBadge) {
        this.aT();
    }

    protected void a(ListingListItem object) {
        if (!this.isAdded()) {
            return;
        }
        object.setAlbumArtClickedState(false);
        object = new TextAlertDialog((Context)this.getActivity(), this.getString(2131296404), MessageFormat.format(this.getString(2131296403), this.getString(2131297940)), true, false);
        object.a(this.getString(2131296705), null);
        object.show();
    }

    @SupposeUiThread
    protected void a(@NonNull MediaPlayingViewInterface object, final @NonNull PerformanceV2 performanceV2, final @NonNull CheckVideoStatusCallback checkVideoStatusCallback) {
        if (object instanceof VideoUploadingView) {
            object = ((VideoUploadingView)object).getUploadStatus();
            switch (.a[object.ordinal()]) {
                default: {
                    checkVideoStatusCallback.a(performanceV2);
                    return;
                }
                case 1: {
                    this.c(2131297213, 2131297214);
                    return;
                }
                case 2: {
                    com.smule.android.network.managers.PerformanceManager.a().a(performanceV2.performanceKey, false, new PerformanceManager(){

                        @Override
                        public void handleResponse(PerformanceManager.PerformanceResponse performanceResponse) {
                            if (!ProfileFragment.this.isAdded()) {
                                return;
                            }
                            if (performanceResponse.a() && performanceResponse.mPerformance.a()) {
                                ProfileFragment.this.d(performanceResponse.mPerformance);
                                if (ProfileFragment.this.an) {
                                    ProfileFragment.this.am.b(performanceV2.performanceKey);
                                }
                                checkVideoStatusCallback.a(performanceResponse.mPerformance);
                                return;
                            }
                            ProfileFragment.this.c(2131297205, 2131297206);
                        }
                    });
                    return;
                }
                case 3: 
            }
            this.b(performanceV2);
            return;
        }
        checkVideoStatusCallback.a(performanceV2);
    }

    public void a(FavoritesAdapter favoritesAdapter) {
        this.S = favoritesAdapter;
    }

    public void a(InvitesAdapter invitesAdapter) {
        this.Q = invitesAdapter;
    }

    public void a(OwnedArrangementsAdapter ownedArrangementsAdapter) {
        this.R = ownedArrangementsAdapter;
    }

    public void a(PerformancesAdapter performancesAdapter) {
        this.P = performancesAdapter;
    }

    public void a(String string2, int n) {
        if (!this.isAdded()) {
            return;
        }
        TextView textView = (TextView)this.j.getTabAt(n).getCustomView().findViewById(2131756755);
        ImageView imageView = (ImageView)this.j.getTabAt(n).getCustomView().findViewById(2131756756);
        if (string2 == null) {
            textView.setVisibility(8);
            imageView.setVisibility(8);
            return;
        }
        if (string2.isEmpty()) {
            textView.setVisibility(8);
            imageView.setVisibility(0);
            return;
        }
        textView.setText((CharSequence)string2);
        textView.setVisibility(0);
        imageView.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(String string2, String object) {
        SurveyContext surveyContext;
        block5 : {
            block4 : {
                Log.b(g, "Entering startAVQualitySurveyIfEligible; audioId = " + string2 + "; triggeringContext = " + (String)object);
                if (!this.isAdded() || !this.isResumed()) break block4;
                if (string2 == null) {
                    Log.e(g, "Not starting survey because audioId == null");
                    return;
                }
                object = SurveyPresenter.a().a((Context)this.getActivity());
                if (object == null || !string2.equals(object.performanceKey)) break block4;
                SurveyPresenter.a().a((Context)this.getActivity(), null);
                if (this.P == null || !this.P.b(string2)) break block4;
                surveyContext = new SurveyContext();
                string2 = object.video ? SurveyContext.EntryPoint.g : SurveyContext.EntryPoint.h;
                surveyContext.b = string2;
                surveyContext.f = object;
                if (SurveyPresenter.a().b(this.getActivity(), surveyContext)) break block5;
            }
            return;
        }
        SurveyPresenter.a().c(this.getActivity(), surveyContext);
    }

    public void a(ConcurrentHashMap<String, Boolean> concurrentHashMap) {
        this.al = concurrentHashMap;
    }

    public void a(boolean bl, ArrangementVersionLiteEntry arrangementVersionLiteEntry, ListingListItem listingListItem, boolean bl2) {
        this.al.put(arrangementVersionLiteEntry.c(), bl);
        if (bl) {
            MediaPlayerServiceController.a().b(arrangementVersionLiteEntry.c());
            this.a(listingListItem);
            return;
        }
        if (bl2) {
            SingAnalytics.a((String)arrangementVersionLiteEntry.w(), (String)arrangementVersionLiteEntry.v(), Analytics.a);
        }
        this.a(arrangementVersionLiteEntry);
    }

    public void a(boolean bl, boolean bl2) {
        this.ae = bl;
        this.af = bl2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean a(PerformanceV2 performanceV2, PerformancesAPI fillStatus) {
        if (fillStatus == PerformancesAPI.FILLED) {
            if (performanceV2.e() && performanceV2.accountIcon.accountId == this.v.accountId && performanceV2.childCount == 0 || performanceV2.seed && this.e(performanceV2) && performanceV2.accountIcon.accountId == this.v.accountId && (!performanceV2.e() || performanceV2.childCount == 0)) return false;
            return true;
        }
        if (fillStatus == PerformancesAPI.ACTIVESEED && performanceV2.seed && !performanceV2.closed && performanceV2.accountIcon.accountId == this.v.accountId) return true;
        return false;
    }

    @Override
    public void aa() {
        UserProfile userProfile = this.u.c();
        if (userProfile != null && userProfile.getNumberFollowees() > 0) {
            this.a(FollowListFragment.a(this.v.accountId, this.v.picUrl, 1, userProfile.getNumberFollowees()));
        }
    }

    @Override
    public boolean ab() {
        return this.C;
    }

    @SupposeUiThread
    protected void ac() {
        this.r = ProfileUserInfoView.a((Context)this.getActivity());
        this.r.findViewById(2131756400).setOnClickListener(new View.OnClickListener(){

            public void onClick(View object) {
                if (ProfileFragment.this.getActivity() instanceof MasterActivity) {
                    object = ProfileFragment.i((ProfileFragment)ProfileFragment.this).a.values().iterator();
                    while (object.hasNext()) {
                        ((ProfileListView)object.next()).getListView().setSelectionFromTop(0, 0);
                    }
                    SingAppboy.a().d();
                    ProfileFragment.this.as();
                }
            }
        });
        this.o.addView((View)this.r, 0);
        this.r.addOnLayoutChangeListener(new View.OnLayoutChangeListener(){

            public void onLayoutChange(View object, int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8) {
                if (n4 - n2 != n8 - n6 && ProfileFragment.this.ag != null) {
                    object = ProfileFragment.this.ag.a().iterator();
                    while (object.hasNext()) {
                        ((ProfileListView)object.next()).a();
                    }
                }
            }
        });
        this.r.setListener(this.aJ);
        this.ah = this.K();
        if (this.ah && this.C) {
            this.N = new WeakListener.OnGlobalLayoutListener(this, new ViewTreeObserver.OnGlobalLayoutListener(){

                public void onGlobalLayout() {
                    LayoutUtils.b((View)ProfileFragment.this.r, (WeakListener.OnGlobalLayoutListener)ProfileFragment.this.N);
                    Runnable runnable = new Runnable(){

                        @Override
                        public void run() {
                            ProfileFragment.this.r.post(new Runnable(){

                                /*
                                 * Enabled aggressive block sorting
                                 */
                                @Override
                                public void run() {
                                    if (ProfileFragment.this.E) {
                                        if (ProfileFragment.this.aE == null) {
                                            ProfileFragment.this.aE = ImageToDiskUtils.c((Context)ProfileFragment.this.getActivity(), (String)"PROFILE_PICTURE_KEY");
                                        }
                                        if (ProfileFragment.this.aE != null) {
                                            ProfileFragment.this.c(ProfileFragment.this.aE);
                                        } else if (ProfileFragment.this.F) {
                                            ProfileFragment.this.r.getProfileImage().getImageView().setImageResource(2130837899);
                                        }
                                    }
                                    if (ProfileFragment.this.G) {
                                        if (ProfileFragment.this.aF == null) {
                                            ProfileFragment.this.aF = ImageToDiskUtils.c((Context)ProfileFragment.this.getActivity(), (String)"COVER_PICTURE_KEY");
                                        }
                                        ProfileFragment.this.b(ProfileFragment.this.aF);
                                    }
                                }
                            });
                        }

                    };
                    ProfileFragment.this.as();
                    ProfileFragment.this.d(runnable);
                }

            });
            LayoutUtils.a((View)this.r, (WeakListener.OnGlobalLayoutListener)this.N);
        }
    }

    public int ad() {
        return this.o.getHeight();
    }

    @UiThread
    protected void aj() {
        if (!this.isAdded() || this.r == null || this.X() == null) {
            return;
        }
        this.d(this.v.handle);
        this.r.a(this.C, this.X());
    }

    public LocalizedShortNumberFormatter ak() {
        if (this.aq == null) {
            this.aq = new LocalizedShortNumberFormatter((Context)this.getActivity());
        }
        return this.aq;
    }

    protected void al() {
        this.d((Runnable)null);
    }

    public PerformanceItemInterface.PerformanceItemListener am() {
        return this.ab;
    }

    public OpenCallListItem.ExpandedPerformanceItemListener an() {
        return this.ac;
    }

    public void ao() {
        this.z = 0;
        this.A = 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    @SupposeUiThread
    public void ap() {
        ListView listView;
        StorageWarningView storageWarningView;
        block5 : {
            block4 : {
                if (!this.w || SubscriptionManager.a().b() || this.J != 0 || this.P == null || (listView = this.aG()) == null) break block4;
                if (this.P.k() == 0 && !this.P.a().v() && !this.P.a().t()) {
                    listView.removeFooterView((View)this.aH());
                    return;
                }
                MagicDataSource magicDataSource = this.P.a();
                if (!(magicDataSource instanceof ProfilePerformanceDataSource)) break block4;
                this.B = ((ProfilePerformanceDataSource)magicDataSource).e().isEmpty();
                storageWarningView = this.aH();
                storageWarningView.setShowFutureWarning(this.B);
                storageWarningView.a();
                storageWarningView.a(((ProfilePerformanceDataSource)magicDataSource).e());
                if (listView.getFooterViewsCount() == 1) break block5;
            }
            return;
        }
        listView.addFooterView((View)storageWarningView);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void aq() {
        ListView listView;
        if (!this.w || SubscriptionManager.a().b() || this.J != 0 || this.P == null || (listView = this.aG()) == null) {
            return;
        }
        listView.removeFooterView((View)this.aH());
    }

    public int ar() {
        if (this.u.h() == Theme.a) {
            return ContextCompat.getColor((Context)this.getActivity(), (int)2131689871);
        }
        return this.u.e().a;
    }

    @UiThread
    public void as() {
        this.f(false);
        if (this.ag != null) {
            Iterator<ProfileListView> iterator = this.ag.a.values().iterator();
            while (iterator.hasNext()) {
                iterator.next().b();
            }
        }
        this.D = false;
        this.C = true;
        this.aL();
        this.j.setTouchEnabled(false);
        this.q.setTheme(this.u.h());
        this.q.setOnThemeSelectedListener(this.aG);
        this.r.b();
        this.r.b(true);
        new Handler().post(new Runnable(){

            @Override
            public void run() {
                ((MediaPlayingActivity)ProfileFragment.this.getActivity()).c(false);
            }
        });
        if (this.p() != null) {
            this.p().a(BaseFragment.b);
        }
        this.ad.a();
    }

    public void at() {
        if (!(this.E || this.G || this.H)) {
            this.aS();
            this.e(false);
            return;
        }
        HandleOkOnCancelDialog handleOkOnCancelDialog = new HandleOkOnCancelDialog(this.getActivity(), 2131297607, 2131297603, 2131296675, 2131297606);
        handleOkOnCancelDialog.a(new CustomAlertDialog.CustomAlertDialogListener(){

            @Override
            public void a(CustomAlertDialog customAlertDialog) {
            }

            @Override
            public void b(CustomAlertDialog customAlertDialog) {
                ProfileFragment.this.aU();
                ProfileFragment.this.aS();
                ProfileFragment.this.e(false);
            }
        });
        handleOkOnCancelDialog.show();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void au() {
        boolean bl;
        block9 : {
            block10 : {
                boolean bl2;
                block8 : {
                    bl2 = false;
                    ArrayList<SingAnalytics.ProfileCustomizationFeature> arrayList = new ArrayList<SingAnalytics.ProfileCustomizationFeature>();
                    if (this.E) {
                        arrayList.add(SingAnalytics.ProfileCustomizationFeature.d);
                    }
                    if (this.G) {
                        arrayList.add(SingAnalytics.ProfileCustomizationFeature.c);
                    }
                    if (this.H) {
                        arrayList.add(SingAnalytics.ProfileCustomizationFeature.a);
                    }
                    SingAnalytics.ProfileCustomizationFeature[] arrprofileCustomizationFeature = new SingAnalytics.ProfileCustomizationFeature[arrayList.size()];
                    arrayList.toArray((T[])arrprofileCustomizationFeature);
                    SingAnalytics.a((String)null, (String)null, (SingAnalytics.ProfileCustomizationFeature[])arrprofileCustomizationFeature);
                    if (!SubscriptionManager.a().b()) break block8;
                    bl = bl2;
                    break block9;
                }
                if (this.G) break block10;
                bl = bl2;
                if (!this.H) break block9;
                bl = bl2;
                if (this.u.h() == Theme.a) break block9;
            }
            bl = true;
        }
        if (bl) {
            this.I = true;
            this.a(UpsellManager.a());
        } else {
            this.aM();
        }
        this.e(bl);
    }

    @Click
    public void av() {
        this.at();
    }

    @Click
    protected void aw() {
        this.au();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void b(View object) {
        long l;
        if (this.aj || (l = this.v.accountId) == com.smule.android.network.managers.UserManager.a().f()) {
            return;
        }
        this.aj = true;
        final Context context = SingApplication.g();
        object = FollowManager.a().a(l) ? Analytics.b : Analytics.a;
        com.smule.android.logging.Analytics.a(object, l);
        FollowManager.a().a((Long)l, new FollowManager.ToggleFollowStateListener(){

            @Override
            public void a(final boolean bl, final boolean bl2, final boolean bl3) {
                ProfileFragment.this.a(new Runnable(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    @Override
                    public void run() {
                        int n = 1;
                        if (bl3) {
                            ProfileFragment.this.b(context.getString(2131297196));
                        } else if (!bl) {
                            ProfileFragment.this.b(context.getString(2131297210));
                        } else if (bl2) {
                            ProfileFragment.this.b(MessageFormat.format(context.getString(2131297195), ProfileFragment.this.v.handle));
                        } else {
                            ProfileFragment.this.b(MessageFormat.format(context.getString(2131297209), ProfileFragment.this.v.handle));
                        }
                        UserProfile userProfile = ProfileFragment.this.u.c();
                        if (!bl3 && bl) {
                            if (!bl2) {
                                n = -1;
                            }
                            userProfile.a(n);
                        }
                        ProfileFragment.this.aj = false;
                        ProfileFragment.this.aj();
                    }
                });
            }

        });
    }

    @Override
    public void b(AccountIcon object) {
        object = ProfileFragment.a((AccountIcon)object);
        this.a((BaseFragment)((Object)object), object.t());
    }

    /*
     * Enabled aggressive block sorting
     */
    public void b(final PerformanceV2 performanceV2) {
        Object object = performanceV2 != null && performanceV2.title != null ? performanceV2.title : "";
        object = this.getString(2131297111, new Object[]{object});
        object = new TextAlertDialog((Context)this.getActivity(), this.getString(2131297112), (CharSequence)Html.fromHtml((String)object), true, false);
        object.a(this.getString(2131296705), "");
        object.a(new CustomAlertDialog.CustomAlertDialogListener(){

            private void c(CustomAlertDialog customAlertDialog) {
                new VideoUploadCancelHandler(performanceV2, false).run();
                customAlertDialog.dismiss();
            }

            @Override
            public void a(CustomAlertDialog customAlertDialog) {
                this.c(customAlertDialog);
            }

            @Override
            public void b(CustomAlertDialog customAlertDialog) {
                this.c(customAlertDialog);
            }
        });
        object.show();
    }

    public FileUploaderService.VideoUploadStatus c(String string2) {
        FileUploaderService.VideoUploadStatus videoUploadStatus;
        FileUploaderService.VideoUploadStatus videoUploadStatus2 = videoUploadStatus = FileUploaderService.VideoUploadStatus.e;
        if (this.an) {
            videoUploadStatus2 = videoUploadStatus;
            if (string2 != null) {
                videoUploadStatus2 = this.am.a(string2);
            }
        }
        return videoUploadStatus2;
    }

    @Override
    public void c(View object) {
        object = new ChatActivatorDialog((Context)this.getActivity(), 2131296622);
        object.a(this.v, (ChatActivator.ChatActivatorInterface)new ChatActivator.ChatActivatorListener(){

            @Override
            public void b(final Chat chat) {
                new Handler().post(new Runnable(){

                    @Override
                    public void run() {
                        if (!ProfileFragment.this.isResumed()) {
                            return;
                        }
                        FragmentManager fragmentManager = ProfileFragment.this.getFragmentManager();
                        fragmentManager.popBackStack(MessageCenterFragment.g, 0);
                        String string2 = ChatFragment.g + "_" + ProfileFragment.this.v.handle;
                        if (fragmentManager.findFragmentByTag(string2) != null) {
                            fragmentManager.popBackStack(string2, 0);
                            return;
                        }
                        ProfileFragment.this.a(ChatFragment.a(chat), string2);
                    }
                });
            }

        });
        object.show();
    }

    @SupposeUiThread
    protected void c(PerformanceV2 object) {
        if (ProfileFragment.b(this.P, (PerformanceListItemContainer)(object = new PerformanceListItemContainer((PerformanceV2)object)))) {
            this.aD();
        }
        ProfileFragment.b(this.S, (PerformanceListItemContainer)object);
        ProfileFragment.b(this.Q, (PerformanceListItemContainer)object);
        object.d = true;
        ProfileFragment.b(this.Q, (PerformanceListItemContainer)object);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void c(boolean bl) {
        if (!this.w) {
            this.X.d();
            final int n = this.e;
            com.smule.android.network.managers.UserManager.a().a(this.v.accountId, new UserManager(){

                /*
                 * Enabled aggressive block sorting
                 */
                @Override
                public void handleResponse(UserManager.UserBlurbResponse userBlurbResponse) {
                    if (!ProfileFragment.this.a(n)) {
                        return;
                    }
                    if (userBlurbResponse.a()) {
                        ProfileFragment.this.X().b(userBlurbResponse.mBlurb);
                    } else {
                        ProfileFragment.this.X().b(null);
                    }
                    ProfileFragment.this.aj();
                    ProfileFragment.this.X.a();
                }
            });
            return;
        }
        com.smule.android.network.managers.UserManager userManager = com.smule.android.network.managers.UserManager.a();
        if (bl || userManager.s() == null) {
            this.aC();
            return;
        }
        if (this.K() && userManager.s().trim().isEmpty()) {
            this.X().b(this.getResources().getString(2131297192));
        } else {
            this.X().b(userManager.s());
        }
        this.aj();
    }

    public void d(int n) {
        this.M = n;
        if (this.isAdded()) {
            this.e(this.M);
            this.M = -1;
        }
    }

    @SupposeUiThread
    protected void d(PerformanceV2 object) {
        object = new PerformanceListItemContainer((PerformanceV2)object);
        ProfileFragment.c(this.P, (PerformanceListItemContainer)object);
        ProfileFragment.c(this.Q, (PerformanceListItemContainer)object);
        ProfileFragment.c(this.S, (PerformanceListItemContainer)object);
    }

    protected void d(final Runnable runnable) {
        Log.b(g, "fetchProfileForUser - beginning");
        this.X.d();
        this.k.setVisibility(0);
        if (!this.D && this.u != null) {
            this.e(runnable);
            return;
        }
        this.X.d();
        final int n = this.e;
        com.smule.android.network.managers.UserManager.a().a(this.v.accountId, new UserManager(){

            /*
             * Enabled aggressive block sorting
             */
            protected void a(SingUserProfile singUserProfile) {
                ProfileCustomizations profileCustomizations = singUserProfile.singProfile;
                ProfileUserInfo.ColorSet colorSet = ProfileFragment.this.a(profileCustomizations);
                if (profileCustomizations != null) {
                    ProfileFragment profileFragment = ProfileFragment.this;
                    boolean bl = !TextUtils.isEmpty((CharSequence)profileCustomizations.coverUrl);
                    profileFragment.ai = bl;
                    ProfileFragment.this.r.a(profileCustomizations.coverUrl);
                }
                ProfileFragment.this.t = new ProfileUserInfo((Context)ProfileFragment.this.getActivity(), ProfileFragment.this.K(), singUserProfile.profile, singUserProfile.singProfile, colorSet, com.smule.android.network.managers.UserManager.a().s());
                ProfileFragment.this.u = ProfileFragment.this.t.a((Context)ProfileFragment.this.getActivity());
                ProfileFragment.this.v = ProfileFragment.this.t.a();
                if (ProfileFragment.this.v.picUrl != null && ImageUtils.a(ProfileFragment.this.v.picUrl)) {
                    ProfileFragment.this.F = true;
                }
            }

            @Override
            public void handleResponse(SingUserProfile singUserProfile) {
                if (!ProfileFragment.this.a(n)) {
                    return;
                }
                if (!singUserProfile.a() || singUserProfile.profile == null || singUserProfile.profile.accountIcon == null) {
                    ProfileFragment.this.a(singUserProfile);
                    return;
                }
                this.a(singUserProfile);
                ProfileFragment.this.aJ();
                ProfileFragment.this.ax();
                ProfileFragment.this.aN();
                if (runnable != null) {
                    runnable.run();
                }
                if (ProfileFragment.this.ae) {
                    ProfileFragment.this.c(ProfileFragment.this.af);
                }
                ProfileFragment.this.a(false, false);
                ProfileFragment.this.X.a();
            }
        });
        this.X.a();
    }

    public void d(boolean bl) {
        this.D = bl;
    }

    @Override
    public boolean d() {
        if (this.C) {
            this.at();
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void e(int n) {
        this.J = n;
        SingAnalytics.UserRelationType userRelationType = this.w ? SingAnalytics.UserRelationType.a : SingAnalytics.UserRelationType.b;
        if (this.j != null) {
            if (this.j.getTabCount() == 0) {
                this.ay();
            }
            this.j.getTabAt(n).select();
        }
        this.l.setCurrentItem(n, false);
        if (n == 0 && this.P != null) {
            this.ap();
            this.aK();
            SingAnalytics.d((SingAnalytics.UserRelationType)userRelationType, (int)this.R());
        } else if (n == 1 && this.Q != null) {
            SingAnalytics.a((SingAnalytics.UserRelationType)userRelationType, (int)this.S());
        } else if (n == 2 && this.R != null) {
            SingAnalytics.e((SingAnalytics.UserRelationType)userRelationType, (int)(this.R.d() - 1));
        } else if (n == 3 && this.S != null) {
            SingAnalytics.f((SingAnalytics.UserRelationType)userRelationType, (int)this.Q());
        }
        if (this.ar && this.ax != null) {
            this.d(this.ax);
            this.ar = false;
            this.ax = null;
        }
        if ((userRelationType = this.aG()) != null) {
            this.a((ListView)userRelationType);
        }
    }

    @UiThread
    public void e(boolean bl) {
        if (!this.isAdded() || this.r == null || this.j == null) {
            return;
        }
        Iterator<ProfileListView> iterator = this.ag.a.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().c();
        }
        this.C = bl;
        if (!bl) {
            this.aT();
            this.j.setTouchEnabled(true);
            this.r.c();
            this.r.b(false);
            this.ad.b();
        }
        SingAppboy.a().e();
    }

    @Override
    public boolean e() {
        return this.w;
    }

    @Override
    public boolean f() {
        return false;
    }

    @Override
    public boolean g() {
        return this.C;
    }

    @Override
    public boolean l() {
        if (!this.K()) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean m() {
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onActivityResult(int n, int n2, Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n2 != -1) {
            Log.e(g, "Bad result code, " + n2 + ", returned for request code: " + n);
            return;
        }
        switch (n) {
            case 2201: {
                return;
            }
            default: {
                return;
            }
            case 2202: 
        }
        if (intent == null) return;
        if (intent.getExtras() == null) return;
        if ((intent = this.b(intent)) == null) {
            Log.e(g, "Null bitmap returned from CROP_PHOTO_INTENT_CODE");
            return;
        }
        if (this.m.getId() == this.ag()) {
            this.b((Bitmap)intent);
            return;
        }
        this.c((Bitmap)intent);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onCreate(Bundle arrn) {
        super.onCreate((Bundle)arrn);
        this.setHasOptionsMenu(true);
        if (arrn != null) {
            return;
        }
        this.v = (AccountIcon)this.getArguments().getParcelable("PROFILE_ACCOUNT_ICON_KEY");
        if (this.v == null) {
            throw new RuntimeException("AccountIcon was null");
        }
        if (this.v.accountId == com.smule.android.network.managers.UserManager.a().f()) {
            this.w = true;
            this.v.handle = com.smule.android.network.managers.UserManager.a().i();
            this.v.picUrl = com.smule.android.network.managers.UserManager.a().h();
        } else {
            this.w = false;
        }
        if (this.w) {
            int[] arrn2 = arrn = new int[4];
            arrn2[0] = 1;
            arrn2[1] = 0;
            arrn2[2] = 3;
            arrn2[3] = 2;
        } else {
            int[] arrn3 = arrn = new int[4];
            arrn3[0] = 1;
            arrn3[1] = 0;
            arrn3[2] = 3;
            arrn3[3] = 2;
        }
        this.V = arrn;
        int n = this.M != -1 ? this.M : 0;
        this.W = n;
        this.as = false;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onCreateOptionsMenu(Menu menu2, MenuInflater menuInflater) {
        if (!ChatUtils.a()) {
            super.onCreateOptionsMenu(menu2, menuInflater);
            return;
        } else {
            if (this.K()) {
                menuInflater.inflate(2131820545, menu2);
                return;
            }
            if (this.w) return;
            {
                menuInflater.inflate(2131820556, menu2);
                this.ap = menu2;
                this.aB();
                return;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.aF();
    }

    public void onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu();
        this.ap = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.r = null;
        this.al = null;
        this.T = null;
        this.P = null;
        this.Q = null;
        this.R = null;
        this.S = null;
        this.ag = null;
        this.l = null;
        this.s = null;
        this.X.c();
        this.X = null;
        this.C = false;
        if (this.aE != null) {
            ImageToDiskUtils.b((Context)this.getActivity(), (String)"PROFILE_PICTURE_KEY", (Bitmap)this.aE);
            this.aE = null;
        }
        if (this.aF != null) {
            ImageToDiskUtils.b((Context)this.getActivity(), (String)"COVER_PICTURE_KEY", (Bitmap)this.aF);
            this.aF = null;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean onOptionsItemSelected(MenuItem var1_1) {
        switch (var1_1.getItemId()) {
            case 2131756851: {
                ChatAnalytics.a(this.v.accountId);
                this.a(FlagUserFragment.a(this.v));
                ** break;
            }
            case 2131756852: {
                this.az();
                ** break;
            }
            case 2131756853: {
                this.aA();
                ** break;
            }
            case 2131756839: {
                SingAnalytics.a(Analytics.e);
                this.a((BaseFragment)SearchFragment.F());
                ** break;
            }
            case 2131756836: {
                this.a(SettingsFragment.a(), SettingsFragment.g);
                ** break;
            }
            case 2131756837: {
                this.a(WebViewFragment.a(this.getResources().getString(2131297950), this.getResources().getString(2131296724)));
            }
lbl21: // 7 sources:
            default: {
                return false;
            }
            case 2131756838: 
        }
        this.a(WebViewFragment.a(this.getResources().getString(2131297948), this.getResources().getString(2131296692)));
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();
        NotificationCenter.a().b(MediaPlayerServiceController.a, this.Y);
        MediaPlayerServiceController.a().b(this.av);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onResume() {
        super.onResume();
        if (this.ak != null) {
            this.c(this.ak);
            this.ak = null;
        }
        if (this.p() != null) {
            if (this.w && !this.C) {
                this.p().a(BaseFragment.c);
            } else {
                this.p().a(BaseFragment.b);
            }
        }
        this.aB();
        if (this.ag != null && this.ag.a.size() == 0) {
            this.ax();
        }
        NotificationCenter.a().a(MediaPlayerServiceController.a, this.Y);
        MediaPlayerServiceController.a().a(this.av);
        ((MediaPlayingActivity)this.getActivity()).ab().setVisibility(0);
        if (!this.aD) {
            this.getActivity().getWindowManager().getDefaultDisplay().getSize(this.aC);
            this.aD = true;
        }
        if (this.I) {
            if (this.v.picUrl == null) {
                this.E = true;
            }
            this.aM();
            return;
        } else {
            if (this.u == null) return;
            {
                if (this.O()) {
                    this.u.c().c = FollowManager.a().b();
                    this.aj();
                }
                boolean bl = this.Y() || this.u.h() == Theme.a;
                if (this.C) return;
                if (bl) return;
                {
                    this.aR();
                    return;
                }
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onStart() {
        boolean bl = true;
        super.onStart();
        this.g(true);
        if (this.w) {
            this.I();
        } else {
            this.setHasOptionsMenu(true);
        }
        if (this.C) {
            bl = false;
        }
        this.f(bl);
    }

    @Override
    public void onStop() {
        super.onStop();
        this.g(false);
        if (this.getActivity() instanceof MediaPlayingActivity) {
            ((MediaPlayingActivity)this.getActivity()).Y();
        }
        if (this.ao) {
            if (this.am != null) {
                this.am.e();
            }
            SingApplication.g().unbindService(this.aw);
            this.an = false;
            this.ao = false;
        }
        SingApplication.d().a("INIT_FILE_UPLOADER_SERVICE_KEY");
    }

    public String t() {
        return g + "-" + this.G();
    }

    @Override
    public String x() {
        return g;
    }

    public static interface CheckVideoStatusCallback {
        public void a(PerformanceV2 var1);
    }

    public static class DroidSing10042Exception
    extends Throwable {
        public DroidSing10042Exception(String string2) {
            super(string2);
        }
    }

    private class ProfileListViewOnScrollListener
    implements AbsListView.OnScrollListener {
        private ProfileListViewOnScrollListener() {
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public void onScroll(AbsListView var1_1, int var2_2, int var3_3, int var4_4) {
            var3_3 = ProfileFragment.this.ad();
            var2_2 = var2_2 == 0 ? 1 : 0;
            if (ProfileFragment.this.j.getVisibility() != 4) ** GOTO lbl-1000
            ProfileFragment.this.j.setVisibility(0);
            if (var2_2 != 0) lbl-1000: // 2 sources:
            {
                var1_1 = var1_1.getChildAt(0);
                var3_3 = - var3_3;
                var2_2 = var1_1 != null ? var1_1.getTop() : 0;
                var5_5 = - Math.max(var3_3, var2_2);
            } else {
                var5_5 = var3_3;
            }
            ProfileFragment.this.n.scrollTo(0, (int)var5_5);
            var6_6 = Math.max((float)ProfileFragment.this.getResources().getDimensionPixelSize(2131427385), (float)(ProfileFragment.this.o.getHeight() - ProfileFragment.this.j.getHeight()) - var5_5);
            ProfileFragment.this.j.setTranslationY(var6_6);
            ProfileFragment.this.r.setCoverPhotoTranslationY(var5_5 * 0.5f);
            ProfileFragment.r(ProfileFragment.this);
        }

        /*
         * Enabled aggressive block sorting
         */
        public void onScrollStateChanged(AbsListView absListView, int n) {
            int n2 = 0;
            if (n != 0) return;
            View view = absListView.getChildAt(0);
            n = view == null ? n2 : view.getTop();
            ProfileFragment.this.z = absListView.getFirstVisiblePosition();
            ProfileFragment.this.A = n;
        }
    }

    private class ProfilePagerAdapter
    extends PagerAdapter {
        protected Map<Integer, ProfileListView> a;

        private ProfilePagerAdapter() {
            this.a = new HashMap<Integer, ProfileListView>();
        }

        public Collection<ProfileListView> a() {
            return this.a.values();
        }

        public void destroyItem(ViewGroup viewGroup, int n, Object object) {
            object = (ProfileListView)object;
            viewGroup.removeView((View)object);
            ProfileFragment.this.q();
            object.b.setMagicAdapter(null);
            this.a.remove(n);
        }

        public int getCount() {
            return 4;
        }

        public CharSequence getPageTitle(int n) {
            switch (n) {
                default: {
                    return ProfileFragment.this.getResources().getString(2131296685);
                }
                case 0: {
                    return ProfileFragment.this.getResources().getString(2131297207);
                }
                case 1: {
                    return ProfileFragment.this.getResources().getString(2131296707);
                }
                case 2: 
            }
            return ProfileFragment.this.getResources().getString(2131296668);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public Object instantiateItem(ViewGroup viewGroup, int n) {
            int n2 = 1;
            ProfileListView profileListView = ProfileListView.a((Context)ProfileFragment.this.getActivity(), ProfileFragment.this, n);
            profileListView.setOrientation(1);
            profileListView.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -1));
            viewGroup.addView((View)profileListView);
            this.a.put(n, profileListView);
            if (this.a.size() != this.getCount() || ProfileFragment.this.aG() == null) return profileListView;
            n = n2;
            if (n == 0) return profileListView;
            ProfileFragment.this.a(ProfileFragment.this.aG());
            return profileListView;
        }

        public boolean isViewFromObject(View view, Object object) {
            if (view == object) {
                return true;
            }
            return false;
        }

        public Parcelable saveState() {
            Iterator<ProfileListView> iterator = this.a().iterator();
            while (iterator.hasNext()) {
                iterator.next().b.setMagicAdapter(null);
            }
            this.a.clear();
            return super.saveState();
        }

        /*
         * Enabled aggressive block sorting
         */
        public void setPrimaryItem(ViewGroup object, int n, Object iterator) {
            if (ProfileFragment.this.isAdded() && (object = (ProfileListView)ProfileFragment.this.l.findViewWithTag((Object)("sb_item#" + n))) != null) {
                for (ProfileListView profileListView : this.a()) {
                    if (profileListView == object) continue;
                    profileListView.b.setOnScrollListener(null);
                }
            }
        }
    }

    private class VideoUploadCancelHandler
    implements Runnable {
        private final PerformanceV2 b;
        private final boolean c;

        public VideoUploadCancelHandler(PerformanceV2 performanceV2, boolean bl) {
            this.b = performanceV2;
            this.c = bl;
        }

        @Override
        public void run() {
            NavigationUtils.a((Activity)ProfileFragment.this.getActivity(), (PerformanceV2)this.b, (Runnable)new Runnable(){

                @Override
                public void run() {
                    ProfileFragment.this.am.a(VideoUploadCancelHandler.a((VideoUploadCancelHandler)VideoUploadCancelHandler.this).performanceKey, null, VideoUploadCancelHandler.a((VideoUploadCancelHandler)VideoUploadCancelHandler.this).arrKey);
                }
            }, (Runnable)new Runnable(){

                @Override
                public void run() {
                    ProfileFragment.this.P.a().a(new PerformanceListItemContainer(VideoUploadCancelHandler.this.b));
                    ProfileFragment.this.P.a().p();
                }
            }, (Runnable)null, (boolean)this.c, (boolean)ProfileFragment.this.am.a());
        }

    }

}

