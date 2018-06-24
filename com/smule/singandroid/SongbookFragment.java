/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.Animator$AnimatorListener
 *  android.animation.ObjectAnimator
 *  android.app.Activity
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.ServiceConnection
 *  android.content.res.Resources
 *  android.graphics.Point
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.IBinder
 *  android.os.Looper
 *  android.support.annotation.NonNull
 *  android.support.v4.view.PagerAdapter
 *  android.support.v4.view.ViewPager
 *  android.support.v4.view.ViewPager$OnPageChangeListener
 *  android.text.Html
 *  android.text.Spannable
 *  android.text.TextPaint
 *  android.text.TextUtils
 *  android.text.method.LinkMovementMethod
 *  android.text.method.MovementMethod
 *  android.text.style.ClickableSpan
 *  android.text.style.URLSpan
 *  android.text.style.UnderlineSpan
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnLongClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewPropertyAnimator
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.view.Window
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.TextView
 *  com.foound.widget.AmazingListView
 *  com.mopub.nativeads.GooglePlayServicesAdRenderer
 *  com.mopub.nativeads.MagicGhostRenderer
 *  com.mopub.nativeads.MagicMoPubGhostStreamAdPlacer
 *  com.mopub.nativeads.MagicMoPubStaticNativeAdRenderer
 *  com.mopub.nativeads.MoPubAdRenderer
 *  com.mopub.nativeads.MoPubNative
 *  com.mopub.nativeads.MoPubNative$SmuleNativeAdEventListenerInterface
 *  com.mopub.nativeads.MoPubNativeAdLoadedListener
 *  com.mopub.nativeads.MoPubStaticNativeAdRenderer
 *  com.mopub.nativeads.MoPubStreamAdPlacer
 *  com.mopub.nativeads.RequestParameters
 *  com.mopub.nativeads.SmuleNativeAdEventListener
 *  com.mopub.nativeads.ViewBinder
 *  com.mopub.nativeads.ViewBinder$Builder
 *  com.mopub.nativeads.VisibilityTracker
 *  com.nostra13.universalimageloader.core.ImageLoader
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity$StartupMode
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_$IntentBuilder_
 *  com.smule.singandroid.songbook.BookmarkTutorialCounter
 *  com.smule.singandroid.songbook.CategoriesListFragment
 *  com.smule.singandroid.songbook.CustomizablePagerSlidingTabStrip
 *  com.smule.singandroid.songbook.CustomizablePagerSlidingTabStrip$CustomizeTabsListener
 *  com.smule.singandroid.songbook_search.SearchFragment
 *  com.smule.singandroid.upsell.UpsellManager
 *  com.smule.singandroid.upsell.UpsellType
 *  com.smule.singandroid.utils.DeepLink
 *  com.smule.singandroid.utils.LayoutUtils
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer$ActionBarFadeCallback
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer$ActionBarHighlightMode
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$SingModulePlacement
 *  com.smule.singandroid.utils.SingAnalytics$SongbookSortType
 *  com.smule.singandroid.utils.SongbookEntryUtils
 *  com.smule.singandroid.utils.SongbookSectionUtil
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.SupposeUiThread
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 *  org.androidannotations.api.builder.PostActivityStarter
 */
package com.smule.singandroid;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.foound.widget.AmazingListView;
import com.mopub.nativeads.GooglePlayServicesAdRenderer;
import com.mopub.nativeads.MagicGhostRenderer;
import com.mopub.nativeads.MagicMoPubGhostStreamAdPlacer;
import com.mopub.nativeads.MagicMoPubStaticNativeAdRenderer;
import com.mopub.nativeads.MoPubAdRenderer;
import com.mopub.nativeads.MoPubNative;
import com.mopub.nativeads.MoPubNativeAdLoadedListener;
import com.mopub.nativeads.MoPubStaticNativeAdRenderer;
import com.mopub.nativeads.MoPubStreamAdPlacer;
import com.mopub.nativeads.RequestParameters;
import com.mopub.nativeads.SmuleNativeAdEventListener;
import com.mopub.nativeads.ViewBinder;
import com.mopub.nativeads.VisibilityTracker;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.smule.SmuleApplication;
import com.smule.android.ads.MagicAdSettings;
import com.smule.android.ads.vendors.mopub.MagicMoPubNativeAdMediatorAdapter;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.AnalyticsProcessor;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.EntitlementsManager;
import com.smule.android.network.managers.PromotionManager;
import com.smule.android.network.managers.SongbookManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.Banner;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.uploader.FileUploaderService;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.OperationLoader;
import com.smule.android.utils.WeakListener;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.CustomViewPager;
import com.smule.singandroid.FindFriendsModule;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.SettingsFragment;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.SongbookFragment_;
import com.smule.singandroid.WebViewActivity;
import com.smule.singandroid.adapters.songbook.SongbookAmazingAdapter;
import com.smule.singandroid.adapters.songbook.SongbookAmazingMoPubAdAdapter;
import com.smule.singandroid.adapters.songbook.SongbookCategoryAdapter;
import com.smule.singandroid.adapters.songbook.SongbookFindFriendsAdapter;
import com.smule.singandroid.adapters.songbook.SongbookSongsAdapter;
import com.smule.singandroid.ads.OnLaunchAd;
import com.smule.singandroid.ads.SingAdSettings;
import com.smule.singandroid.bookmark.SongBookmarkMenuBuilder;
import com.smule.singandroid.common.OptionsMenu.OptionsMenu;
import com.smule.singandroid.crm.SingAppboy;
import com.smule.singandroid.customviews.MasterToolbar;
import com.smule.singandroid.customviews.SongbookSortSelector;
import com.smule.singandroid.dialogs.NativeAdsMoreDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.dialogs.TextAndImageAlertDialog;
import com.smule.singandroid.list_items.ListingListItem;
import com.smule.singandroid.list_items.SongbookListView;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.models.SongbookListViewState;
import com.smule.singandroid.models.SongbookSection;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity_;
import com.smule.singandroid.songbook.BookmarkTutorialCounter;
import com.smule.singandroid.songbook.CategoriesListFragment;
import com.smule.singandroid.songbook.CustomizablePagerSlidingTabStrip;
import com.smule.singandroid.songbook_search.SearchFragment;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;
import com.smule.singandroid.utils.DeepLink;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SongbookEntryUtils;
import com.smule.singandroid.utils.SongbookSectionUtil;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.builder.PostActivityStarter;

@EFragment
public class SongbookFragment
extends BaseFragment
implements MoPubNativeAdLoadedListener,
QuickReturnListViewMenuSyncer.ActionBarFadeCallback {
    public static final String g = SongbookFragment.class.getName();
    final int A;
    Runnable B;
    int C;
    List<View> D;
    private SectionsPagerAdapter E;
    private List<SongbookSection> F = new ArrayList<SongbookSection>();
    private String G;
    private String H;
    private String I;
    private Observer J;
    private Observer K;
    private boolean L = false;
    private Context M;
    private ConcurrentHashMap<String, Boolean> N = new ConcurrentHashMap();
    private int O;
    private int P;
    private int Q = -1;
    private int R = -1;
    private Menu S;
    private PagerAdapter T;
    private ArrayList<Banner> U = new ArrayList();
    private AtomicBoolean V = new AtomicBoolean(false);
    private volatile boolean W = true;
    private WeakListener.OnGlobalLayoutListener X;
    private FileUploaderService Y;
    private boolean Z;
    private boolean aa;
    private ServiceConnection ab;
    private View.OnClickListener ac;
    private View.OnClickListener ad;
    private Animator.AnimatorListener ae;
    private Animator.AnimatorListener af;
    private boolean ag;
    private SongbookAdapterInterface ah;
    private InitialLoadRecommendedCallback ai;
    protected final SingServerValues h = new SingServerValues();
    protected int i = 0;
    @ViewById
    protected View j;
    @ViewById
    protected LinearLayout k;
    @ViewById
    protected TextView l;
    @ViewById
    protected FrameLayout m;
    @ViewById
    protected View n;
    @ViewById
    protected CustomizablePagerSlidingTabStrip o;
    @ViewById
    CustomViewPager p;
    @ViewById
    View q;
    @ViewById
    FrameLayout r;
    @ViewById
    ViewPager s;
    @ViewById
    LinearLayout t;
    protected Integer u = null;
    @InstanceState
    protected int v = -1;
    @InstanceState
    protected boolean w = false;
    OnLaunchAd x;
    final Handler y = new Handler(Looper.getMainLooper());
    final Runnable z;

    public SongbookFragment() {
        this.z = new Runnable(){

            @Override
            public void run() {
                block3 : {
                    block2 : {
                        if (!SongbookFragment.this.isAdded()) break block2;
                        if (SongbookFragment.this.s.getCurrentItem() != SongbookFragment.this.T.getCount() - 1) break block3;
                        SongbookFragment.this.s.setCurrentItem(0, true);
                    }
                    return;
                }
                SongbookFragment.this.s.setCurrentItem(SongbookFragment.this.s.getCurrentItem() + 1, true);
            }
        };
        this.A = 5000;
        this.Z = false;
        this.aa = false;
        this.ab = new ServiceConnection(){

            public void onServiceConnected(ComponentName object, IBinder iBinder) {
                object = (FileUploaderService.FileUploaderBinder)iBinder;
                SongbookFragment.this.Y = object.a();
                SongbookFragment.this.Z = true;
                if (SongbookFragment.this.Y.b()) {
                    object = new TextAndImageAlertDialog((Context)SongbookFragment.this.getActivity(), 2131296662, 2131296663, 2131296718, 2131296702, 2130838085, SongbookFragment.this.getResources().getDimensionPixelSize(2131428173));
                    object.a(new Runnable(){

                        @Override
                        public void run() {
                            SongbookFragment.this.a(SettingsFragment.a(SettingsFragment.FocusField.b));
                        }
                    });
                    object.show();
                }
                Log.b(SongbookFragment.g, "Binding service end - connected");
            }

            public void onServiceDisconnected(ComponentName componentName) {
                SongbookFragment.this.Z = false;
                Log.b(SongbookFragment.g, "Binding service end - disconnected");
            }

        };
        this.ac = new View.OnClickListener(){

            public void onClick(View view) {
                SingAnalytics.a(Analytics.a);
                SongbookFragment.this.a((BaseFragment)SearchFragment.F());
            }
        };
        this.ad = new View.OnClickListener(){

            public void onClick(View view) {
                SingAnalytics.a(Analytics.m);
                SongbookFragment.this.a((BaseFragment)SearchFragment.F());
            }
        };
        this.ae = new Animator.AnimatorListener(){

            public void onAnimationCancel(Animator animator2) {
            }

            public void onAnimationEnd(Animator animator2) {
                if (!SongbookFragment.this.isAdded()) {
                    return;
                }
                Log.b(SongbookFragment.g, "FadeWhiteAnimator End");
                MasterToolbar masterToolbar = ((MasterActivity)SongbookFragment.this.getActivity()).U();
                animator2 = masterToolbar.getPreSearchTitleView();
                ImageView imageView = masterToolbar.getPreSearchToolbarNavigationIconView();
                masterToolbar = masterToolbar.getTitleView();
                masterToolbar.setVisibility(0);
                masterToolbar.setAlpha(1.0f);
                animator2.setVisibility(8);
                imageView.setVisibility(8);
                imageView.setOnClickListener(null);
                animator2.setOnClickListener(null);
                animator2.animate().setListener(null);
                SongbookFragment.this.V();
            }

            public void onAnimationRepeat(Animator animator2) {
            }

            public void onAnimationStart(Animator animator2) {
                if (!SongbookFragment.this.isAdded()) {
                    return;
                }
                Log.b(SongbookFragment.g, "FadeWhiteAnimator Start");
                animator2 = ((MasterActivity)SongbookFragment.this.getActivity()).U().getTitleView();
                animator2.setVisibility(0);
                animator2.setAlpha(0.0f);
            }
        };
        this.af = new Animator.AnimatorListener(){

            public void onAnimationCancel(Animator animator2) {
            }

            public void onAnimationEnd(Animator animator2) {
                if (!SongbookFragment.this.isAdded()) {
                    return;
                }
                Log.b(SongbookFragment.g, "FadeBlueAnimator End");
                MasterToolbar masterToolbar = ((MasterActivity)SongbookFragment.this.getActivity()).U();
                animator2 = masterToolbar.getTitleView();
                TextView textView = masterToolbar.getPreSearchTitleView();
                masterToolbar = masterToolbar.getPreSearchToolbarNavigationIconView();
                textView.setVisibility(0);
                masterToolbar.setVisibility(0);
                textView.setAlpha(1.0f);
                masterToolbar.setAlpha(1.0f);
                animator2.setVisibility(8);
                animator2.animate().setListener(null);
            }

            public void onAnimationRepeat(Animator animator2) {
            }

            /*
             * Enabled aggressive block sorting
             */
            public void onAnimationStart(Animator animator2) {
                if (!SongbookFragment.this.isAdded()) {
                    return;
                }
                Log.b(SongbookFragment.g, "FadeBlueAnimator Start");
                MasterToolbar masterToolbar = ((MasterActivity)SongbookFragment.this.getActivity()).U();
                animator2 = masterToolbar.getPreSearchTitleView();
                masterToolbar = masterToolbar.getPreSearchToolbarNavigationIconView();
                animator2.setVisibility(0);
                masterToolbar.setVisibility(0);
                animator2.setAlpha(0.0f);
                masterToolbar.setAlpha(0.0f);
                if (SongbookFragment.this.S != null) {
                    SongbookFragment.this.S.clear();
                } else {
                    Log.e(SongbookFragment.g, "DROIDSING-11192: mOptionsMenu is null");
                }
                masterToolbar.setOnClickListener(SongbookFragment.this.ac);
                animator2.setOnClickListener(SongbookFragment.this.ac);
            }
        };
        this.B = new Runnable(){

            @Override
            public void run() {
                SongbookFragment.this.E();
            }
        };
        this.ag = false;
        this.C = 0;
        this.ah = new SongbookAdapterInterface();
        this.D = new ArrayList<View>();
        this.ai = new InitialLoadRecommendedCallback(){

            @Override
            public void a() {
                SongbookFragment.this.Q();
            }
        };
    }

    private void Q() {
        this.c(false);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void R() {
        int n = 0;
        int n2 = !SubscriptionManager.a().b() && this.U.isEmpty() ? 1 : 0;
        LinearLayout linearLayout = this.t;
        n2 = n2 != 0 ? n : 8;
        linearLayout.setVisibility(n2);
        this.y.removeCallbacks(this.z);
        if (!this.U.isEmpty()) {
            this.y.postDelayed(this.z, 5000);
        }
    }

    private void S() {
        Observer observer;
        final int n = this.e;
        NotificationCenter notificationCenter = NotificationCenter.a();
        this.J = observer = new Observer(){

            @Override
            public void update(Observable observable, Object object) {
                SongbookFragment.this.b(new Runnable(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    @Override
                    public void run() {
                        block3 : {
                            block2 : {
                                if (!SongbookFragment.this.a(n)) break block2;
                                Log.b(SongbookFragment.g, "App settings updated");
                                if (SongbookFragment.i((SongbookFragment)SongbookFragment.this).a != null) break block3;
                            }
                            return;
                        }
                        SongbookFragment.i((SongbookFragment)SongbookFragment.this).a.a.invalidateViews();
                    }
                });
            }

        };
        notificationCenter.a("APP_SETTINGS_LOADED_EVENT", observer);
        notificationCenter = NotificationCenter.a();
        this.K = observer = new Observer(){

            @Override
            public void update(Observable observable, Object object) {
                if ("CATEGORY_UPDATED_ACTION".equals((String)((Map)object).get("ACTION"))) {
                    SongbookFragment.this.b(new Runnable(){

                        @Override
                        public void run() {
                            if (!SongbookFragment.this.a(n)) {
                                return;
                            }
                            Log.b(SongbookFragment.g, "Songbook Categories updated; refreshing list data sources");
                            SongbookFragment.this.d("CATEGORY_UPDATED_ACTION");
                        }
                    });
                }
            }

        };
        notificationCenter.a("SONGBOOK_FOR_USER_UPDATED_EVENT", observer);
        n = this.e;
        SingApplication.d().a("SongbookFragment.OP_UPDATE_SONGBOOK_UI", Collections.singleton("InitAppTask.OP_RELOAD_SONGBOOK"), new Runnable(){

            @Override
            public void run() {
                SongbookFragment.this.a(new Runnable(){

                    @Override
                    public void run() {
                        if (SongbookFragment.this.a(n)) {
                            Log.b(SongbookFragment.g, "Songbook sync completed - refreshing list views");
                            SongbookFragment.this.d("SONGBOOK_FOR_USER_UPDATED_EVENT");
                        }
                    }
                });
            }

        }).a();
    }

    private void T() {
        NotificationCenter.a().b("APP_SETTINGS_LOADED_EVENT", this.J);
        NotificationCenter.a().b("SONGBOOK_FOR_USER_UPDATED_EVENT", this.K);
        SingApplication.d().a("SongbookFragment.OP_UPDATE_SONGBOOK_UI");
    }

    /*
     * Enabled aggressive block sorting
     */
    private void U() {
        if (this.g(this.I)) {
            this.I = null;
            return;
        }
        if (this.g(this.K())) return;
        {
            if (this.F.size() > 0) {
                Log.a(g, "restoreLastSelectedSongbookSection : Setting current section to 0");
                this.p.setCurrentItem(0, false);
                return;
            }
        }
        Log.e(g, "onReceive unable to select which StoreSection to show");
    }

    private void V() {
        if (this.getActivity() != null) {
            this.getActivity().invalidateOptionsMenu();
        }
    }

    private void W() {
        if (this.aa) {
            return;
        }
        SingApplication.d().a("INIT_FILE_UPLOADER_SERVICE_KEY", Collections.singletonList("InitAppTask.OP_USER_LOGGED_IN"), new OperationLoader.Operation(){

            @Override
            public void a(OperationLoader object) {
                if (UserManager.a().y()) {
                    SongbookFragment.this.a();
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
                        if (UserManager.a().y()) {
                            NotificationCenter.a().b("USER_LOGGED_IN_EVENT", this);
                            NotificationCenter.a().b("USER_RE_LOGGED_IN_EVENT", this);
                            SongbookFragment.this.a();
                            this.a.c(19.this.f);
                        }
                    }
                };
                NotificationCenter.a().a("USER_LOGGED_IN_EVENT", (Observer)object);
                NotificationCenter.a().a("USER_RE_LOGGED_IN_EVENT", (Observer)object);
            }

        }).a();
    }

    private void X() {
        this.j.setVisibility(0);
    }

    private void Y() {
        int n = this.o.getTop();
        int n2 = (int)this.q.getTranslationY();
        int n3 = this.getResources().getDimensionPixelSize(2131427817);
        n = this.getResources().getDimensionPixelSize(2131427385) + (n + n2 + n3);
        n2 = this.getResources().getDimensionPixelSize(2131427918);
        ((MasterActivity)this.getActivity()).a(n, n + n2);
    }

    private boolean Z() {
        if (this.E != null && this.E.a != null && this.E.a.getSongsAdapter().r()) {
            return true;
        }
        return false;
    }

    private void a(int n, int n2) {
        this.a(n, n2, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(int n, int n2, boolean bl) {
        Object object;
        ArrayList<String> arrayList;
        Object object2;
        block9 : {
            block8 : {
                int n3 = 0;
                if (this.O == this.Q && this.P == this.R && !bl || this.E == null || this.E.a == null || !this.Z()) break block8;
                int n4 = Math.min(0, n - this.E.a.a.getHeaderViewsCount()) * -1;
                int n5 = this.E.a.a.getHeaderViewsCount();
                if (n4 > 0) {
                    n2 -= n4;
                    n = n3;
                } else {
                    n -= n5;
                }
                n3 = this.E.a.getSongsAdapter().getCount();
                object2 = new ArrayList<String>();
                arrayList = new ArrayList<String>();
                n2 = Math.min(n + n2, n3);
                while (n < n2) {
                    String string2;
                    object = this.E.a.getSongsAdapter().getItem(n);
                    if (object instanceof SongbookEntry && (string2 = SongbookEntryUtils.b((SongbookEntry)((SongbookEntry)object))) != null) {
                        object2.add((String)String.valueOf(n));
                        arrayList.add(string2);
                    }
                    if (object instanceof FindFriendsModule) {
                        ((FindFriendsModule)((Object)object)).b();
                    }
                    ++n;
                }
                this.Q = this.O;
                this.R = this.P;
                if (object2.size() > 0) break block9;
            }
            return;
        }
        object = this.M();
        object2 = TextUtils.join((CharSequence)",", object2);
        com.smule.android.logging.Analytics.a(TextUtils.join((CharSequence)",", arrayList), (String)object2, Analytics.c, Analytics.b, (String)object);
    }

    private void a(SongbookSection songbookSection) {
        if (songbookSection == null) {
            Log.d(g, "Calling setSelectedStoreSection with a NULL item");
            return;
        }
        SingAnalytics.d((String)songbookSection.j, (String)null);
        this.H = songbookSection.j;
        this.u = songbookSection.n;
    }

    private void a(List<SongbookSection> list) {
        com.smule.android.network.managers.SongbookManager songbookManager = com.smule.android.network.managers.SongbookManager.b();
        Map<Long, String> map = songbookManager.e();
        for (Long l : map.keySet()) {
            Object object = map.get(l);
            Log.b(g, "CatId: " + l + " | Name: " + (String)object);
            object = SongbookSectionUtil.a((Long)l, (String)object, (Context)this.M);
            list.add((SongbookSection)object);
            if (!songbookManager.a(l) || songbookManager.g() == null) continue;
            object.a(songbookManager.g(), songbookManager.f());
        }
        int n = 0;
        while (n < list.size()) {
            list.get((int)n).n = n++;
        }
        Log.b(g, "refreshListDataSources - there are " + list.size() + " store sections");
        this.F = list;
    }

    private void aa() {
        MasterToolbar masterToolbar = ((MasterActivity)this.getActivity()).U();
        TextView textView = masterToolbar.getTitleView();
        TextView textView2 = masterToolbar.getPreSearchTitleView();
        masterToolbar = masterToolbar.getPreSearchToolbarNavigationIconView();
        if (this.w) {
            textView2.setVisibility(0);
            masterToolbar.setVisibility(0);
            masterToolbar.setOnClickListener(this.ac);
            textView2.setOnClickListener(this.ac);
            textView.setVisibility(8);
            textView.setAlpha(0.0f);
            return;
        }
        textView2.setVisibility(8);
        masterToolbar.setVisibility(8);
        masterToolbar.setOnClickListener(null);
        textView2.setOnClickListener(null);
        textView.setVisibility(0);
        textView.setAlpha(1.0f);
    }

    private void ab() {
        View view = ((LayoutInflater)this.getActivity().getSystemService("layout_inflater")).inflate(2130903395, null, false);
        view.setOnClickListener(this.ad);
        this.E.a.setCustomFooter(view);
    }

    private String b(SongbookEntry songbookEntry) {
        if (songbookEntry.t()) {
            if ((songbookEntry = (ArrangementVersionLiteEntry)songbookEntry).b()) {
                return "-";
            }
            return songbookEntry.a.songId;
        }
        return songbookEntry.d();
    }

    private void c(boolean bl) {
        if (this.E == null || this.E.a == null || this.E.a.a == null) {
            return;
        }
        this.a(0, this.E.a.a.getHeaderViewsCount() + 3, bl);
    }

    private int f(String string2) {
        for (int i = 0; i < this.F.size(); ++i) {
            if (!this.F.get((int)i).j.equals(string2)) continue;
            return i;
        }
        return -1;
    }

    private boolean g(String string2) {
        boolean bl;
        boolean bl2 = bl = false;
        if (string2 != null) {
            int n = this.f(string2);
            bl2 = bl;
            if (n != -1) {
                Log.a(g, "setCurrentTabById : There is a valid section. Setting to " + string2);
                this.p.setCurrentItem(n, false);
                bl2 = true;
            }
        }
        return bl2;
    }

    public static SongbookFragment t() {
        return new SongbookFragment_();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void A() {
        String string2 = this.H != null ? this.H : com.smule.android.network.managers.SongbookManager.b().d();
        SingAnalytics.d((String)string2);
    }

    @Override
    protected boolean D() {
        return false;
    }

    public void F() {
        if (!this.w) {
            return;
        }
        MasterToolbar masterToolbar = ((MasterActivity)this.getActivity()).U();
        TextView textView = masterToolbar.getTitleView();
        TextView textView2 = masterToolbar.getPreSearchTitleView();
        masterToolbar = masterToolbar.getPreSearchToolbarNavigationIconView();
        textView2.animate().setDuration(250).alpha(0.0f).setListener(this.ae).start();
        masterToolbar.animate().setDuration(250).alpha(0.0f).start();
        textView.animate().setDuration(250).alpha(1.0f).start();
        this.w = false;
    }

    public void G() {
        if (this.w) {
            return;
        }
        MasterToolbar masterToolbar = ((MasterActivity)this.getActivity()).U();
        TextView textView = masterToolbar.getTitleView();
        TextView textView2 = masterToolbar.getPreSearchTitleView();
        masterToolbar = masterToolbar.getPreSearchToolbarNavigationIconView();
        textView.animate().setDuration(250).alpha(0.0f).setListener(this.af).start();
        masterToolbar.animate().setDuration(250).alpha(1.0f).start();
        textView2.animate().setDuration(250).alpha(1.0f).start();
        this.w = true;
    }

    @Click
    protected void H() {
        if (!SubscriptionManager.a().b()) {
            this.a(UpsellManager.a((boolean)false, (SongbookEntry)null, (String)this.H, (String)null, (UpsellType)UpsellType.c));
        }
    }

    @Click
    protected void I() {
    }

    /*
     * Enabled aggressive block sorting
     */
    @AfterViews
    void J() {
        Log.a(g, "updateFollowingViewBinding : After views created");
        this.i = LayoutUtils.a((Activity)this.getActivity()).x;
        this.E = new SectionsPagerAdapter(this);
        this.E.b();
        this.o.setVisibility(0);
        this.u = null;
        this.c(2131297542);
        Object object = (Spannable)Html.fromHtml((String)this.getString(2131297526, new Object[]{this.getString(2131297745)}));
        for (URLSpan uRLSpan : (MoPubStreamAdPlacer)object.getSpans(0, object.length(), URLSpan.class)) {
            object.setSpan((Object)new ClickableSpan(uRLSpan.getURL()){
                final /* synthetic */ String a;
                {
                    this.a = string2;
                }

                public void onClick(View view) {
                    view = SongbookFragment.this.getActivity();
                    if (view != null) {
                        view.startActivity(WebViewActivity.a((Context)view, this.a, true, true));
                    }
                }
            }, object.getSpanStart((Object)uRLSpan), object.getSpanEnd((Object)uRLSpan), 0);
            object.setSpan((Object)new UnderlineSpan(){

                public void updateDrawState(TextPaint textPaint) {
                    textPaint.setUnderlineText(false);
                }
            }, object.getSpanStart((Object)uRLSpan), object.getSpanEnd((Object)uRLSpan), 0);
            object.removeSpan((Object)uRLSpan);
        }
        this.l.setText((CharSequence)object);
        this.l.setLinkTextColor(this.getResources().getColor(2131689955));
        this.l.setMovementMethod(LinkMovementMethod.getInstance());
        this.l.setHighlightColor(0);
        this.p.setAdapter((PagerAdapter)this.E);
        this.o.setViewPager((ViewPager)this.p);
        this.o.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            public void onPageScrollStateChanged(int n) {
                switch (n) {
                    default: {
                        return;
                    }
                    case 0: {
                        ImageLoader.a().d();
                        return;
                    }
                    case 1: 
                }
                ImageLoader.a().c();
                SongbookFragment.this.E.a(SongbookFragment.i((SongbookFragment)SongbookFragment.this).d);
            }

            public void onPageScrolled(int n, float f, int n2) {
            }

            public void onPageSelected(int n) {
                if (!SongbookFragment.this.isAdded()) {
                    return;
                }
                SongbookSection songbookSection = (SongbookSection)SongbookFragment.this.F.get(n);
                SongbookFragment.this.c(songbookSection.j);
            }
        });
        this.o.setCustomizeTabsListener(new CustomizablePagerSlidingTabStrip.CustomizeTabsListener(){

            public void a() {
                SingAnalytics.K();
                SingAppboy.a().d();
                SongbookFragment.this.a((BaseFragment)CategoriesListFragment.a());
            }
        });
        object = MagicAdSettings.a((Context)this.getActivity(), Analytics.b);
        int n = object != null && MagicAdSettings.a(Analytics.b) ? 1 : 0;
        if (n != 0) {
            MoPubStreamAdPlacer moPubStreamAdPlacer = new MoPubStreamAdPlacer(this.getActivity());
            moPubStreamAdPlacer.setAdLoadedListener((MoPubNativeAdLoadedListener)this);
            this.T = new BannerPagerMoPubAdAdapter((String)object, moPubStreamAdPlacer);
            this.s.setAdapter(this.T);
            MagicMoPubNativeAdMediatorAdapter.attachInHouseAdListeners((BannerPagerMoPubAdAdapter)this.T, Analytics.b, this.B);
        } else {
            this.T = new BannerPagerAdapter((Context)this.getActivity());
            this.s.setAdapter(this.T);
        }
        this.s.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            public void onPageScrollStateChanged(int n) {
                if (n == 0) {
                    SongbookFragment.this.y.postDelayed(SongbookFragment.this.z, 5000);
                    return;
                }
                SongbookFragment.this.y.removeCallbacks(SongbookFragment.this.z);
            }

            public void onPageScrolled(int n, float f, int n2) {
            }

            public void onPageSelected(int n) {
            }
        });
        n = ((MasterActivity)this.getActivity()).ao();
        this.s.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, n));
        this.r.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, n));
        this.R();
        if (!SingApplication.d().c("InitAppTask.OP_RELOAD_SONGBOOK")) {
            this.X();
            SingApplication.d().b("InitAppTask.OP_RELOAD_SONGBOOK");
        } else {
            this.d("Load");
        }
        BookmarkTutorialCounter.a((Context)this.getActivity());
        BookmarkTutorialCounter.c((Context)this.getActivity());
        if (BookmarkTutorialCounter.b((Context)this.getActivity())) {
            this.X = new WeakListener.OnGlobalLayoutListener((Object)this.n, new ViewTreeObserver.OnGlobalLayoutListener(){

                public void onGlobalLayout() {
                    if (SongbookFragment.this.isAdded()) {
                        SongbookFragment.this.Y();
                        LayoutUtils.b((View)SongbookFragment.this.o, (WeakListener.OnGlobalLayoutListener)SongbookFragment.this.X);
                    }
                }
            });
            LayoutUtils.a((View)this.o, (WeakListener.OnGlobalLayoutListener)this.X);
        }
    }

    String K() {
        if (this.getActivity() instanceof MasterActivity) {
            return ((MasterActivity)this.getActivity()).x();
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    void L() {
        if (!this.isAdded() || com.smule.android.network.managers.PromotionManager.a().b()) {
            return;
        }
        com.smule.android.network.managers.PromotionManager.a().a(this.getResources().getString(2131297640), new PromotionManager.BannersCallback(){

            @Override
            public void handleResponse(PromotionManager bannersResponse) {
                if (SongbookFragment.this.isAdded()) {
                    if (bannersResponse.a() && bannersResponse.banners.size() > 0 && (!bannersResponse.b || SongbookFragment.this.W)) {
                        SongbookFragment.this.U = bannersResponse.banners;
                        SongbookFragment.this.T.notifyDataSetChanged();
                        SongbookFragment.this.W = false;
                    }
                    SongbookFragment.this.R();
                }
            }
        });
    }

    protected String M() {
        return this.H;
    }

    protected void N() {
        if (!this.isAdded()) {
            return;
        }
        Log.b(g, "retriggerSongbookLoad - Re-triggering songbook load");
        if (this.C >= 3) {
            TextAlertDialog textAlertDialog = new TextAlertDialog((Context)this.getActivity(), this.getString(2131297545), this.getActivity().getString(2131297544), true, false);
            textAlertDialog.a(this.getActivity().getString(2131296705), "");
            textAlertDialog.a(new Runnable(){

                @Override
                public void run() {
                    SingApplication.d().b("InitAppTask.OP_RELOAD_SONGBOOK");
                }
            });
            textAlertDialog.show();
            this.C = 0;
            return;
        }
        this.a(new Runnable(){

            @Override
            public void run() {
                if (!com.smule.android.network.managers.SongbookManager.b().h()) {
                    SingApplication.d().b("InitAppTask.OP_RELOAD_SONGBOOK");
                }
            }
        }, 1000);
        ++this.C;
    }

    @UiThread
    void O() {
        if (this.getActivity() != null && this.isAdded()) {
            boolean bl = SingAdSettings.f((Context)this.getActivity());
            Log.b(g, "showOnLaunchAdIfFirstSongbookLoad: hasShownLaunchAd = " + bl);
            if (!bl) {
                SingAdSettings.a((Context)this.getActivity(), true);
                this.x = new OnLaunchAd();
                this.x.a(this.getActivity(), null, new HashMap<String, String>());
                this.x.a(this.getActivity());
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void P() {
        this.j.setVisibility(8);
        String string2 = this.H != null ? this.H : com.smule.android.network.managers.SongbookManager.b().d();
        SingAnalytics.a((String)string2);
    }

    @UiThread
    protected void a() {
        if (!this.isAdded()) {
            return;
        }
        Intent intent = new Intent(SingApplication.g(), FileUploaderService.class);
        Log.b(g, "Binding service");
        this.aa = SingApplication.g().bindService(intent, this.ab, 1);
    }

    @Override
    protected void a(AbsListView absListView, QuickReturnListViewMenuSyncer.ActionBarHighlightMode actionBarHighlightMode, AbsListView.OnScrollListener onScrollListener) {
        super.a(absListView, actionBarHighlightMode, onScrollListener);
        this.b.a((QuickReturnListViewMenuSyncer.ActionBarFadeCallback)this);
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

    void c(String string2) {
        if (this.getActivity() instanceof MasterActivity) {
            ((MasterActivity)this.getActivity()).c(string2);
        }
    }

    public void d(int n) {
    }

    /*
     * Enabled aggressive block sorting
     */
    @SupposeUiThread
    protected void d(String string2) {
        Log.b(g, "refreshListDataSources - called from caller: " + string2);
        Log.b(g, "refreshListDataSources - songbook init complete: " + com.smule.android.network.managers.SongbookManager.b().h());
        if (!com.smule.android.network.managers.SongbookManager.b().h()) {
            Log.b(g, "refreshListDataSources - SongbookManager not ready");
            this.N();
            return;
        }
        if (com.smule.android.network.managers.SongbookManager.b().i() != null) {
            this.E.a(com.smule.android.network.managers.SongbookManager.b().i());
        }
        String string3 = EntitlementsManager.a().d();
        if (!string2.equals("ENTITLEMENTS_UPDATED_ACTION") && !string2.equals("CATEGORIES_UPDATED_ACTION")) {
            boolean bl = this.G != null && this.G.equals(string3);
            if (bl) {
                Log.b(g, "refreshListDataSources - signatures have not changed; aborting update of list views");
                this.P();
                return;
            }
            Log.b(g, "refreshListDataSources - signatures do not match; refreshing list views");
            if (this.L) {
                Log.b(g, "refreshListDataSources - list view refresh already in progress; returning");
                return;
            }
        }
        this.L = true;
        this.G = string3;
        this.a(new ArrayList<SongbookSection>());
        this.E.e = true;
        this.E.notifyDataSetChanged();
        this.E.e = false;
        this.U();
        if (this.F.toArray().length > 2) {
            this.P();
        }
        this.L();
        this.O();
        this.L = false;
        this.V.set(true);
        Log.b(g, "refreshListDataSources - done");
    }

    /*
     * Enabled aggressive block sorting
     */
    @SupposeUiThread
    protected void e(int n) {
        block7 : {
            block6 : {
                SongbookSection songbookSection;
                if (this.u != null && this.u == n) break block6;
                this.E.a = (SongbookListView)this.p.findViewWithTag((Object)("sb_item#" + n));
                if (this.E.a == null) break block6;
                Log.a(g, "setCurrentSectionIndex : Setting to " + n);
                int n2 = this.O;
                int n3 = this.P;
                this.E.b = songbookSection = this.F.get(n);
                this.a(songbookSection);
                this.E.a();
                this.E.a.b(this.E.d);
                if (this.Z()) break block7;
            }
            return;
        }
        if (SongbookSection.d == (long)songbookSection.k.intValue()) {
            this.ab();
        }
        n = this.O == 0 && this.P == 0 ? 1 : 0;
        boolean bl = this.Q == -1 && this.R == -1;
        if (n == 0) {
            this.a(this.O, this.P, true);
        } else {
            this.Q();
        }
        this.ag = false;
    }

    public void e(String string2) {
        this.I = string2;
        this.c(this.I);
        if (this.isAdded() && this.g(this.I)) {
            this.I = null;
        }
    }

    @Override
    public boolean e() {
        return false;
    }

    @Override
    public boolean f() {
        return true;
    }

    @Override
    public boolean l() {
        return false;
    }

    @Override
    protected boolean m() {
        return true;
    }

    public void onAdChanged() {
    }

    public void onAdLoaded(int n) {
        if (this.isAdded()) {
            this.s.invalidate();
            this.T.notifyDataSetChanged();
        }
    }

    public void onAdRemoved(int n) {
        if (this.isAdded()) {
            this.s.invalidate();
            this.T.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreate(Bundle object) {
        super.onCreate((Bundle)object);
        this.setHasOptionsMenu(true);
        this.M = this.getActivity().getApplicationContext();
        object = SingApplication.h();
        if (object != null) {
            new AsyncTask<Void, Void, Void>((SingApplication)((Object)object)){
                final /* synthetic */ SingApplication a;
                {
                    this.a = singApplication;
                }

                protected /* varargs */ Void a(Void ... arrvoid) {
                    this.a.l();
                    return null;
                }

                protected /* synthetic */ Object doInBackground(Object[] arrobject) {
                    return this.a((Void[])arrobject);
                }
            }.execute((Object[])new Void[0]);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu2, MenuInflater menuInflater) {
        Log.b(g, "onCreateOptionsMenu - begin");
        this.S = menu2;
        if (menu2.findItem(2131756839) == null && !this.w) {
            menuInflater.inflate(2131820558, menu2);
            menu2.findItem(2131756839).getIcon().setAlpha(0);
        }
    }

    public void onDestroyOptionsMenu() {
        Log.b(g, "onDestroyOptionsMenu - begin");
        super.onDestroyOptionsMenu();
        this.S = null;
    }

    @Override
    public void onDestroyView() {
        this.p.setAdapter(null);
        this.s.setAdapter(null);
        this.E.c();
        this.E = null;
        if (this.T instanceof BannerPagerMoPubAdAdapter) {
            ((BannerPagerMoPubAdAdapter)this.T).a();
        }
        this.T = null;
        if (this.x != null) {
            this.x.c();
        }
        super.onDestroyView();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                return super.onOptionsItemSelected(menuItem);
            }
            case 2131756839: 
        }
        SingAnalytics.a(Analytics.a);
        SingAppboy.a().d();
        this.a((BaseFragment)SearchFragment.F());
        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
        this.y.removeCallbacks(this.z);
        this.T();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu2) {
        super.onPrepareOptionsMenu(menu2);
        if (menu2.findItem(2131756839) != null) {
            menu2 = ObjectAnimator.ofInt((Object)menu2.findItem(2131756839).getIcon(), (String)"alpha", (int[])new int[]{0, 255});
            menu2.setDuration(10);
            menu2.start();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.getActivity().getWindow().setSoftInputMode(32);
        if (this.V.get()) {
            this.L();
        }
        this.S();
        this.V();
        this.ag = true;
        SingAppboy.a().e();
    }

    @Override
    public void onStart() {
        super.onStart();
        AnalyticsProcessor.b(g);
        if (this.g(this.I)) {
            this.I = null;
        }
        this.u();
        this.aa();
        this.W();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onStop() {
        super.onStop();
        boolean bl = this.b != null && this.b.a();
        this.w = bl;
        if (this.w) {
            MasterToolbar masterToolbar = ((MasterActivity)this.getActivity()).U();
            TextView textView = masterToolbar.getTitleView();
            TextView textView2 = masterToolbar.getPreSearchTitleView();
            masterToolbar = masterToolbar.getPreSearchToolbarNavigationIconView();
            textView2.setVisibility(8);
            masterToolbar.setVisibility(8);
            masterToolbar.setOnClickListener(null);
            textView2.setOnClickListener(null);
            textView.setVisibility(0);
            textView.setAlpha(1.0f);
        }
        if (this.aa) {
            SingApplication.g().unbindService(this.ab);
            this.Z = false;
            this.aa = false;
        }
        SingApplication.d().a("INIT_FILE_UPLOADER_SERVICE_KEY");
    }

    @Override
    public String x() {
        return g;
    }

    private class BannerPagerAdapter
    extends PagerAdapter {
        Context a;
        LayoutInflater b;

        public BannerPagerAdapter(Context context) {
            this.a = context;
            this.b = (LayoutInflater)this.a.getSystemService("layout_inflater");
        }

        public void destroyItem(ViewGroup viewGroup, int n, Object object) {
            viewGroup.removeView((View)((LinearLayout)object));
        }

        public int getCount() {
            if (SongbookFragment.this.U.size() > 0) {
                return Integer.MAX_VALUE;
            }
            return 0;
        }

        public int getItemPosition(Object object) {
            return -2;
        }

        public Object instantiateItem(ViewGroup viewGroup, final int n) {
            View view = this.b.inflate(2130903085, viewGroup, false);
            ImageView imageView = (ImageView)view.findViewById(2131755295);
            ImageUtils.a(((Banner)SongbookFragment.f((SongbookFragment)SongbookFragment.this).get((int)(n %= SongbookFragment.f((SongbookFragment)SongbookFragment.this).size()))).imgUrl, imageView, 2130837629);
            imageView.setOnClickListener((View.OnClickListener)new WeakListener.OnClickListener((Object)this, new View.OnClickListener(){

                /*
                 * Enabled aggressive block sorting
                 * Enabled unnecessary exception pruning
                 * Enabled aggressive exception aggregation
                 */
                public void onClick(View view) {
                    view = Uri.parse((String)((Banner)SongbookFragment.f((SongbookFragment)SongbookFragment.this).get((int)n)).destUrl);
                    String string2 = view.getScheme();
                    SingAnalytics.f((String)((Banner)SongbookFragment.f((SongbookFragment)SongbookFragment.this).get((int)n)).name);
                    if (string2.equals("http") || string2.equals("https")) {
                        SongbookFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String)((Banner)SongbookFragment.f((SongbookFragment)SongbookFragment.this).get((int)n)).destUrl)));
                        return;
                    }
                    if (!string2.equals("smulesing")) return;
                    try {
                        string2 = new DeepLink((Uri)view);
                        ((MasterActivity)SongbookFragment.this.getActivity()).a((DeepLink)string2, true);
                        return;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        Log.e(SongbookFragment.g, "No match for URI: " + (Object)view);
                        return;
                    }
                }
            }));
            viewGroup.addView(view);
            return view;
        }

        public boolean isViewFromObject(View view, Object object) {
            if (view == object) {
                return true;
            }
            return false;
        }

    }

    private class BannerPagerMoPubAdAdapter
    extends PagerAdapter
    implements MoPubNative.SmuleNativeAdEventListenerInterface {
        LayoutInflater a;
        private String c;
        private MoPubStreamAdPlacer d;
        private RequestParameters e;

        public BannerPagerMoPubAdAdapter(@NonNull String object, MoPubStreamAdPlacer moPubStreamAdPlacer) {
            this.c = object;
            this.d = moPubStreamAdPlacer;
            moPubStreamAdPlacer.setItemCount(715827882);
            this.a = (LayoutInflater)SmuleApplication.a().getSystemService("layout_inflater");
            object = SingAdSettings.a(null);
            this.e = MagicMoPubNativeAdMediatorAdapter.getRequestParams(Analytics.a, object);
            object = new ViewBinder.Builder(2130903304).titleId(2131756010).textId(2131756011).iconImageId(2131756007).callToActionId(2131756009).privacyInformationIconImageId(2131756012).build();
            moPubStreamAdPlacer = new GooglePlayServicesAdRenderer((ViewBinder)object, 0, null, SongbookFragment.this.B);
            this.d.registerAdRenderer((MoPubAdRenderer)moPubStreamAdPlacer);
            SongbookFragment.this = new MoPubStaticNativeAdRenderer((ViewBinder)object, SongbookFragment.this.B);
            this.d.registerAdRenderer((MoPubAdRenderer)SongbookFragment.this);
            if (this.d != null) {
                this.d.loadAds(this.c, this.e);
            }
        }

        public void a() {
            if (this.d != null) {
                this.d.destroy();
            }
        }

        public void destroyItem(ViewGroup viewGroup, int n, Object object) {
            viewGroup.removeView((View)object);
        }

        public int getCount() {
            if (SongbookFragment.this.U.size() > 0) {
                return this.d.getAdjustedCount(715827882);
            }
            return 0;
        }

        public int getItemPosition(Object object) {
            return -2;
        }

        /*
         * Enabled aggressive block sorting
         */
        public Object instantiateItem(ViewGroup viewGroup, final int n) {
            View view;
            this.d.placeAdsInRange(n, n);
            if (this.d.isAd(n)) {
                view = this.d.getAdView(n, null, viewGroup);
            } else {
                n = this.d.getOriginalPosition(n) % SongbookFragment.this.U.size();
                view = this.a.inflate(2130903085, viewGroup, false);
                ImageView imageView = (ImageView)view.findViewById(2131755295);
                ImageUtils.a(((Banner)SongbookFragment.f((SongbookFragment)SongbookFragment.this).get((int)n)).imgUrl, imageView, 2130837629);
                imageView.setOnClickListener((View.OnClickListener)new WeakListener.OnClickListener((Object)this, new View.OnClickListener(){

                    /*
                     * Enabled aggressive block sorting
                     * Enabled unnecessary exception pruning
                     * Enabled aggressive exception aggregation
                     */
                    public void onClick(View view) {
                        view = Uri.parse((String)((Banner)SongbookFragment.f((SongbookFragment)SongbookFragment.this).get((int)n)).destUrl);
                        String string2 = view.getScheme();
                        SingAnalytics.f((String)((Banner)SongbookFragment.f((SongbookFragment)SongbookFragment.this).get((int)n)).name);
                        if (string2.equals("http") || string2.equals("https")) {
                            SongbookFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String)((Banner)SongbookFragment.f((SongbookFragment)SongbookFragment.this).get((int)n)).destUrl)));
                            return;
                        }
                        if (!string2.equals("smulesing")) return;
                        try {
                            string2 = new DeepLink((Uri)view);
                            ((MasterActivity)SongbookFragment.this.getActivity()).a((DeepLink)string2, true);
                            return;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            Log.e(SongbookFragment.g, "No match for URI: " + (Object)view);
                            return;
                        }
                    }
                }));
            }
            viewGroup.addView(view);
            return view;
        }

        public boolean isViewFromObject(View view, Object object) {
            if (view == object) {
                return true;
            }
            return false;
        }

        public void setSmuleNativeAdEventListener(@NonNull SmuleNativeAdEventListener smuleNativeAdEventListener) {
            this.d.setSmuleNativeAdEventListener(smuleNativeAdEventListener);
        }

    }

    private class CustomScrollListener
    implements AbsListView.OnScrollListener {
        private final boolean b;

        public CustomScrollListener(boolean bl) {
            this.b = bl;
        }

        /*
         * Enabled aggressive block sorting
         */
        public void onScroll(AbsListView absListView, int n, int n2, int n3) {
            float f;
            if (this.b) {
                SongbookFragment.this.O = n;
                SongbookFragment.this.P = n2;
            }
            n2 = ((MasterActivity)SongbookFragment.this.getActivity()).ao();
            if (SongbookFragment.this.r.getVisibility() == 0) {
                if (n == 0) {
                    View view = absListView.getChildAt(0);
                    n3 = - n2;
                    n2 = view != null ? view.getTop() : 0;
                    f = Math.max(n3, n2);
                } else {
                    f = n > 0 ? (float)(- n2) : 0.0f;
                }
                SongbookFragment.this.q.setTranslationY(f);
            } else {
                f = 0.0f;
            }
            SongbookFragment.this.E.a((int)f);
            if (absListView.getChildCount() > 0 && SongbookFragment.i((SongbookFragment)SongbookFragment.this).a != null) {
                SongbookFragment.i((SongbookFragment)SongbookFragment.this).a.getSongsAdapter().p().a(n, absListView.getChildAt(0).getTop());
            }
        }

        public void onScrollStateChanged(AbsListView absListView, int n) {
            if (n == 0 && SongbookFragment.this.P > 0 && this.b) {
                SongbookFragment.this.a(SongbookFragment.this.O, SongbookFragment.this.P);
            }
        }
    }

    public static interface InitialLoadRecommendedCallback {
        public void a();
    }

    class SectionsPagerAdapter
    extends PagerAdapter {
        SongbookListView a;
        SongbookSection b;
        Set<SongbookListView> c;
        int d;
        boolean e;
        int f;
        BaseFragment g;

        public SectionsPagerAdapter(BaseFragment baseFragment) {
            this.c = new HashSet<SongbookListView>();
            this.f = -1;
            this.g = baseFragment;
        }

        protected SongbookSongsAdapter a(SongbookSection songbookSection) {
            SongbookCategoryAdapter songbookCategoryAdapter = new SongbookCategoryAdapter(songbookSection.k, SongbookFragment.this.ah, SongbookFragment.this.ai);
            SongbookListViewState songbookListViewState = ((MasterActivity)SongbookFragment.this.getActivity()).a(songbookSection);
            songbookCategoryAdapter.a(SongbookFragment.this.D);
            songbookCategoryAdapter.a(songbookSection, songbookListViewState);
            songbookCategoryAdapter.a(songbookListViewState.a);
            return songbookCategoryAdapter;
        }

        public void a() {
            for (SongbookListView songbookListView : this.c) {
                if (songbookListView == this.a) continue;
                songbookListView.a.setOnScrollListener(null);
            }
            SongbookFragment.this.a((AbsListView)SongbookFragment.i((SongbookFragment)SongbookFragment.this).a.a, QuickReturnListViewMenuSyncer.ActionBarHighlightMode.c, new CustomScrollListener(SongbookFragment.this.Z()));
        }

        public void a(int n) {
            this.d = n;
            for (SongbookListView songbookListView : this.c) {
                if (songbookListView == this.a) continue;
                songbookListView.b(this.d);
            }
        }

        protected void a(SongbookListView songbookListView) {
            songbookListView.a(((MasterActivity)SongbookFragment.this.getActivity()).ao(), SongbookFragment.this.getResources().getDimensionPixelSize(2131427817));
        }

        public void a(Long l) {
            Iterator<SongbookListView> iterator = this.c.iterator();
            while (iterator.hasNext()) {
                SongbookAmazingAdapter songbookAmazingAdapter = iterator.next().getSongsAdapter();
                if (!songbookAmazingAdapter.s().equals(l)) continue;
                songbookAmazingAdapter.m();
            }
        }

        public void b() {
            SongbookFragment.this.b(new Runnable(){

                /*
                 * Enabled aggressive block sorting
                 */
                @Override
                public void run() {
                    if (SongbookFragment.this.isAdded()) {
                        for (SongbookListView songbookListView : SectionsPagerAdapter.this.c) {
                            SectionsPagerAdapter.this.a(songbookListView);
                        }
                    }
                }
            });
        }

        public void b(SongbookListView object) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                throw new RuntimeException("Not on main thread!");
            }
            View view = new View();
            object.a.reclaimViews(view);
            object = view.iterator();
            do {
                block6 : {
                    block5 : {
                        if (!object.hasNext()) break block5;
                        view = (View)object.next();
                        if (SongbookFragment.this.D.size() <= 30) break block6;
                        Log.d(SongbookFragment.g, "mListItemsRecycleList.size too big, size: " + SongbookFragment.this.D.size());
                    }
                    return;
                }
                if (!(view instanceof ListingListItem)) continue;
                ((ListingListItem)view).e();
                SongbookFragment.this.D.add(view);
            } while (true);
        }

        public void c() {
            Iterator<SongbookListView> iterator = this.c.iterator();
            while (iterator.hasNext()) {
                this.b(iterator.next());
            }
            this.c.clear();
        }

        public void destroyItem(ViewGroup viewGroup, int n, Object object) {
            SongbookAmazingAdapter songbookAmazingAdapter;
            if ((object = (SongbookListView)object) != null && (songbookAmazingAdapter = object.getSongsAdapter()) instanceof SongbookAmazingMoPubAdAdapter) {
                ((SongbookAmazingMoPubAdAdapter)songbookAmazingAdapter).t();
            }
            this.b((SongbookListView)object);
            viewGroup.removeView((View)object);
            this.c.remove(object);
        }

        public int getCount() {
            return SongbookFragment.this.F.size();
        }

        public CharSequence getPageTitle(int n) {
            return ((SongbookSection)SongbookFragment.j((SongbookFragment)SongbookFragment.this).get((int)n)).l;
        }

        /*
         * Enabled aggressive block sorting
         */
        public Object instantiateItem(ViewGroup viewGroup, int n) {
            Object object;
            String string2;
            Object object2;
            Object object3;
            Object object4;
            Object object5 = (SongbookSection)SongbookFragment.this.F.get(n);
            if (object5.k == SongbookSection.d) {
                object = this.a((SongbookSection)object5);
                object3 = SongbookFragment.this.h.aj();
                if (FindFriendsModule.FindFriendsModulePlacer.a((String)object3, SingAnalytics.SingModulePlacement.b)) {
                    object3 = new FindFriendsModule.FindFriendsModulePlacer((String)object3, SingAnalytics.SingModulePlacement.b, -1, null);
                    object = new SongbookFindFriendsAdapter(object5.k, (Context)SongbookFragment.this.getActivity(), (FindFriendsModule.FindFriendsModulePlacer)object3, (SongbookSongsAdapter)((Object)object), this.g, SingAnalytics.SingModulePlacement.b, SongbookFragment.this.i);
                } else {
                    object = this.a((SongbookSection)object5);
                }
            } else {
                object = this.a((SongbookSection)object5);
            }
            boolean bl = (string2 = MagicAdSettings.a((Context)SongbookFragment.this.getActivity(), Analytics.a)) != null && MagicAdSettings.a(Analytics.a);
            if (bl) {
                object3 = new SongbookAmazingMoPubAdAdapter((MoPubStreamAdPlacer)new MagicMoPubGhostStreamAdPlacer(SongbookFragment.this.getActivity()), (SongbookAmazingAdapter)((Object)object), new VisibilityTracker((Context)SongbookFragment.this.getActivity()));
                object4 = new HashMap();
                object4.put("songbook_id", object5.j);
                object5 = SingAdSettings.a(object4);
                object5 = MagicMoPubNativeAdMediatorAdapter.getRequestParams(Analytics.a, object5);
                object4 = new ViewBinder.Builder(2130903305).titleId(2131756010).textId(2131756011).iconImageId(2131756007).callToActionId(2131756009).privacyInformationIconImageId(2131756012).build();
                object2 = new View.OnClickListener(){

                    public void onClick(View view) {
                        SongbookFragment.this.E();
                        new NativeAdsMoreDialog(SongbookFragment.this).show();
                    }
                };
                object3.a((MoPubAdRenderer)new MagicGhostRenderer(new ViewBinder.Builder(2130903300).build()));
                object3.a((MoPubAdRenderer)new GooglePlayServicesAdRenderer((ViewBinder)object4, 2131755976, (View.OnClickListener)object2, SongbookFragment.this.B));
                object3.a((MoPubAdRenderer)new MagicMoPubStaticNativeAdRenderer((ViewBinder)object4, 2131755976, (View.OnClickListener)object2, SongbookFragment.this.B));
                MagicMoPubNativeAdMediatorAdapter.attachInHouseAdListeners((MoPubNative.SmuleNativeAdEventListenerInterface)object3, Analytics.a, SongbookFragment.this.B);
            } else {
                object5 = null;
                object3 = null;
            }
            if (object3 != null) {
                object = object3;
            }
            object4 = SongbookListView.a((Context)SongbookFragment.this.getActivity());
            object4.setAdapter((SongbookAmazingAdapter)((Object)object));
            object4.getSongbookSortSelector().setSortTypeSelectedListener(new SongbookSortSelector.SortTypeSelectedListener((SongbookListView)object4){
                final /* synthetic */ SongbookListView a;
                {
                    this.a = songbookListView;
                }

                @Override
                public void a(SongbookSortSelector.SortType sortType) {
                    SongbookAmazingAdapter songbookAmazingAdapter = this.a.getSongsAdapter();
                    songbookAmazingAdapter.a(sortType);
                    this.a.c(SectionsPagerAdapter.this.d);
                    SingAnalytics.a((SingAnalytics.SongbookSortType)sortType.a(), (String)songbookAmazingAdapter.o().j);
                }
            });
            object2 = object4.getSongsAdapter().q();
            object4.getSongbookSortSelector().setSortMenuType((SongbookSortSelector.SortMenuType)((Object)object2));
            object4.getSongbookSortSelector().setSortType(object4.getSongsAdapter().p().a);
            this.a((SongbookListView)object4);
            object.m();
            if (object3 != null) {
                object3.a(string2, (RequestParameters)object5);
            }
            viewGroup.addView((View)object4);
            this.c.add((SongbookListView)object4);
            object4.a(this.d);
            object4.setTag((Object)("sb_item#" + n));
            return object4;
        }

        public boolean isViewFromObject(View view, Object object) {
            if (view == object) {
                return true;
            }
            return false;
        }

        /*
         * Enabled aggressive block sorting
         */
        public void setPrimaryItem(ViewGroup viewGroup, int n, Object object) {
            if (!SongbookFragment.this.isAdded() || this.e) {
                return;
            }
            SongbookFragment.this.e(n);
        }

    }

    private class SongbookAdapterInterface
    implements SongbookAmazingAdapter.AdapterInterface {
        private SongbookAdapterInterface() {
        }

        private void a(boolean bl, ArrangementVersionLiteEntry arrangementVersionLiteEntry, ListingListItem listingListItem) {
            SongbookFragment.this.N.put(arrangementVersionLiteEntry.c(), bl);
            if (bl) {
                MediaPlayerServiceController.a().b(arrangementVersionLiteEntry.c());
                SongbookFragment.this.a(listingListItem);
                return;
            }
            com.smule.android.logging.Analytics.a("songbook", SongbookFragment.this.b(arrangementVersionLiteEntry), arrangementVersionLiteEntry.i(), arrangementVersionLiteEntry.r(), SongbookFragment.this.M(), SongbookEntry.a(arrangementVersionLiteEntry));
            SongbookFragment.this.a(arrangementVersionLiteEntry);
        }

        @Override
        public Context a() {
            return SongbookFragment.this.getActivity();
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void a(final ListingListItem listingListItem, final ArrangementVersionLiteEntry arrangementVersionLiteEntry, final int n) {
            HashMap hashMap;
            if (arrangementVersionLiteEntry == null) {
                Log.d(SongbookFragment.g, "setupListItem entry was null");
                return;
            }
            int n2 = listingListItem.getTag() != null && listingListItem.getTag() instanceof HashMap && (hashMap = (HashMap)listingListItem.getTag()).containsKey("position_adjusted_for_native_ads") ? (Integer)hashMap.get("position_adjusted_for_native_ads") : -1;
            if (n2 != -1) {
                n = n2;
            }
            listingListItem.setOnClickListener(new View.OnClickListener(){

                public void onClick(View object) {
                    if (!SongbookFragment.this.isAdded()) {
                        return;
                    }
                    SingAnalytics.a((SongbookEntry)arrangementVersionLiteEntry, (String)SongbookFragment.this.M(), (Number)n);
                    if (!SongbookEntryUtils.c((SongbookEntry)arrangementVersionLiteEntry)) {
                        SongbookFragment.this.a(UpsellManager.a((boolean)true, (SongbookEntry)arrangementVersionLiteEntry, (String)SongbookFragment.this.M(), (String)null, (UpsellType)UpsellType.a));
                        return;
                    }
                    object = SongbookEntryUtils.b((SongbookEntry)arrangementVersionLiteEntry);
                    if (SongbookFragment.this.Z() && object != null) {
                        com.smule.android.logging.Analytics.a((String)object, Analytics.e, n, Analytics.b, SongbookFragment.this.M());
                    }
                    PreSingActivity.a((Context)SongbookFragment.this.getActivity()).a(PreSingActivity.StartupMode.a).a((SongbookEntry)arrangementVersionLiteEntry).a(SongbookFragment.this.M()).a();
                }
            });
            listingListItem.setAlbumArtClickListener(new View.OnClickListener(){

                /*
                 * Enabled aggressive block sorting
                 */
                public void onClick(View object) {
                    boolean bl = true;
                    object = SongbookEntryUtils.b((SongbookEntry)arrangementVersionLiteEntry);
                    if (SongbookFragment.this.Z() && object != null) {
                        com.smule.android.logging.Analytics.a((String)object, Analytics.b, n, Analytics.b, SongbookFragment.this.M());
                    }
                    if (listingListItem.t()) {
                        return;
                    }
                    listingListItem.setAlbumArtClickedState(true);
                    if (SongbookFragment.this.N == null) {
                        SongbookFragment.this.N = new ConcurrentHashMap();
                    }
                    if (SongbookFragment.this.N.containsKey(arrangementVersionLiteEntry.c())) {
                        SongbookAdapterInterface.this.a((Boolean)SongbookFragment.this.N.get(arrangementVersionLiteEntry.c()), arrangementVersionLiteEntry, listingListItem);
                        return;
                    }
                    int n2 = arrangementVersionLiteEntry.a().removalCode;
                    if (n2 < 40 || n2 > 49) {
                        bl = false;
                    }
                    SongbookFragment.this.N.put(arrangementVersionLiteEntry.c(), bl);
                    SongbookAdapterInterface.this.a(bl, arrangementVersionLiteEntry, listingListItem);
                }
            });
            listingListItem.setOnLongClickListener(new View.OnLongClickListener(){

                public boolean onLongClick(View view) {
                    new SongBookmarkMenuBuilder().a(arrangementVersionLiteEntry).a((Context)SongbookFragment.this.getActivity()).a();
                    return true;
                }
            });
        }

    }

}

