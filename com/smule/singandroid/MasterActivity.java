/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.Animator$AnimatorListener
 *  android.animation.AnimatorSet
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  android.annotation.SuppressLint
 *  android.app.Activity
 *  android.app.Fragment
 *  android.app.FragmentManager
 *  android.app.FragmentManager$BackStackEntry
 *  android.app.FragmentManager$OnBackStackChangedListener
 *  android.app.FragmentTransaction
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Resources
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.support.annotation.NonNull
 *  android.support.v7.app.ActionBar
 *  android.support.v7.widget.Toolbar
 *  android.view.KeyEvent
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.RelativeLayout
 *  com.facebook.CallbackManager
 *  com.nostra13.universalimageloader.core.ImageLoader
 *  com.nostra13.universalimageloader.core.listener.PauseOnScrollListener
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity$StartupMode
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_$IntentBuilder_
 *  com.smule.singandroid.songbook.CategoriesListFragment
 *  com.smule.singandroid.songbook.LongPressIndicatorView
 *  com.smule.singandroid.upsell.UpsellManager
 *  com.smule.singandroid.upsell.UpsellType
 *  com.smule.singandroid.utils.AnimatorEndListener
 *  com.smule.singandroid.utils.DeepLink
 *  com.smule.singandroid.utils.DeepLink$Hosts
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer$ActionBarHighlightMode
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EActivity
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 *  org.androidannotations.api.builder.PostActivityStarter
 */
package com.smule.singandroid;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import com.facebook.CallbackManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.smule.android.ads.AdVendorManager;
import com.smule.android.ads.MagicAd;
import com.smule.android.ads.MagicAdAdapterFactory;
import com.smule.android.ads.MagicAdSettings;
import com.smule.android.ads.fullScreenAds.MagicFullScreenAdMediatorAdapter;
import com.smule.android.ads.networks.AdVendor;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.ArrangementManager;
import com.smule.android.network.managers.OAuth2Manager;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PromotionManager;
import com.smule.android.network.managers.SongbookManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.twitter.MagicTwitter;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.OperationLoader;
import com.smule.android.utils.ThreadUtils;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.WeakListener;
import com.smule.chat.AccountIconCache;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatListenerAdapter;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatManagerListener;
import com.smule.chat.ChatManagerListenerAdapter;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.FindFriendsFragment;
import com.smule.singandroid.LeaderboardFragment;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.MasterActivity_;
import com.smule.singandroid.NotificationsFragment;
import com.smule.singandroid.SettingsFragment;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingBundle;
import com.smule.singandroid.SongbookFragment;
import com.smule.singandroid.WebViewFragment;
import com.smule.singandroid.ads.AdVendorManagerConfig;
import com.smule.singandroid.ads.SingAdSettings;
import com.smule.singandroid.ads.SingFullScreenAd;
import com.smule.singandroid.chat.ChatFragment;
import com.smule.singandroid.chat.MessageCenterFragment;
import com.smule.singandroid.common.JoinSectionType;
import com.smule.singandroid.customviews.ActionBarCustomView;
import com.smule.singandroid.customviews.BottomNavView;
import com.smule.singandroid.customviews.MasterToolbar;
import com.smule.singandroid.customviews.OverlayWithRectangularHoleImageView;
import com.smule.singandroid.dialogs.AutoLoginAuthorizationDialog;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.ChatTooltipDialog;
import com.smule.singandroid.dialogs.UpdatedTermsDialog;
import com.smule.singandroid.fragments.SearchByTagFragment;
import com.smule.singandroid.hashtag.Hashtag;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.mediaplaying.MediaPlayingFragment;
import com.smule.singandroid.mediaplaying.NowPlayingFragment;
import com.smule.singandroid.mediaplaying.PreviewFragment;
import com.smule.singandroid.models.SongbookListViewState;
import com.smule.singandroid.models.SongbookSection;
import com.smule.singandroid.preference.MagicPreferences;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.purchases.V3BillingHelper;
import com.smule.singandroid.runtimepermissions.SingPermissionRequests;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity_;
import com.smule.singandroid.songbook.CategoriesListFragment;
import com.smule.singandroid.songbook.LongPressIndicatorView;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;
import com.smule.singandroid.utils.AnimatorEndListener;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.DeepLink;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Observer;
import java.util.concurrent.Future;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.builder.PostActivityStarter;

@SuppressLint(value={"Registered"})
@EActivity
public class MasterActivity
extends MediaPlayingActivity
implements BottomNavView,
MediaPlayingFragment {
    public static final String g = MasterActivity.class.getName();
    private boolean A;
    private boolean B;
    private UpdatedTermsDialog C = null;
    private ChatListener D;
    private ChatManagerListener E;
    private Observer F;
    private final Map<String, BaseFragment> G;
    private boolean H;
    private int I;
    private int J;
    private View.OnClickListener K;
    private View.OnClickListener L;
    private ChatTooltipDialog M;
    @ViewById
    protected ViewGroup h;
    @ViewById
    protected BottomNavView i;
    @InstanceState
    protected Integer j;
    @ViewById
    protected OverlayWithRectangularHoleImageView k;
    @ViewById
    protected View l;
    @ViewById
    protected LongPressIndicatorView m;
    protected BusyDialog n;
    @InstanceState
    protected HashMap<String, SongbookListViewState> o;
    @InstanceState
    protected String p;
    @InstanceState
    protected boolean q;
    @InstanceState
    protected long r;
    View.OnClickListener s;
    private Intent z;

    public MasterActivity() {
        this.D = new ChatListenerAdapter(this){
            final /* synthetic */ MasterActivity a;
            {
                this.a = masterActivity;
            }

            public void a(com.smule.chat.Chat chat, com.smule.chat.ChatMessage chatMessage, boolean bl) {
                long l = new java.util.Date().getTime();
                if (chat.b() != com.smule.chat.Chat$Bucket.a || chatMessage.b() == com.smule.android.network.managers.UserManager.a().f() || chatMessage.c().getTime() < l - 1000 || this.a.r > l - 5000) {
                    // empty if block
                }
            }

            public void d(com.smule.chat.Chat chat) {
                this.a.C();
            }
        };
        this.E = new ChatManagerListenerAdapter(this){
            final /* synthetic */ MasterActivity a;
            {
                this.a = masterActivity;
            }

            public void a() {
                this.a.C();
            }

            public void e(com.smule.chat.Chat chat) {
                this.a.C();
            }

            public void f(com.smule.chat.Chat chat) {
                this.a.C();
            }
        };
        this.F = new Observer(this){
            final /* synthetic */ MasterActivity a;
            {
                this.a = masterActivity;
            }

            public void update(java.util.Observable observable, Object object) {
                com.smule.android.magicui.lists.adapters.MagicDataSource.a(com.smule.singandroid.customviews.NotificationsListView.c);
                com.smule.android.magicui.lists.adapters.MagicDataSource.a(com.smule.singandroid.customviews.NotificationsListView.b);
                this.a.C();
            }
        };
        this.o = new HashMap();
        this.G = new HashMap<String, BaseFragment>();
        this.H = true;
        this.s = new View.OnClickListener(this){
            final /* synthetic */ MasterActivity a;
            {
                this.a = masterActivity;
            }

            public void onClick(View view) {
                if (!(this.a.G() instanceof SongbookFragment)) {
                    this.a.onBackPressed();
                }
            }
        };
    }

    public static Intent a(Context context) {
        return new Intent(context, MasterActivity_.class);
    }

    private void a(long l) {
        com.smule.android.network.managers.UserManager.a().a(l, new UserManager(this, l){
            final /* synthetic */ long a;
            final /* synthetic */ MasterActivity b;
            {
                this.b = masterActivity;
                this.a = l;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void handleResponse(com.smule.android.network.managers.UserManager$AccountIconResponse object) {
                if (object != null && object.a()) {
                    object = object.mAccount;
                    this.b.a(new Runnable(this, (com.smule.android.network.models.AccountIcon)object){
                        final /* synthetic */ com.smule.android.network.models.AccountIcon a;
                        final /* synthetic */  b;
                        {
                            this.b = var1_1;
                            this.a = accountIcon;
                        }

                        public void run() {
                            ProfileFragment profileFragment = ProfileFragment.a(this.a);
                            this.b.b.a(profileFragment, profileFragment.t());
                        }
                    });
                    return;
                }
                object = object == null ? "NULL reply" : "error in call";
                Log.d(MasterActivity.g, "showUserProfile for id " + this.a + " failed; " + (String)object);
            }
        });
    }

    static /* synthetic */ void a(MasterActivity masterActivity) {
        masterActivity.at();
    }

    private void a(DeepLink deepLink) {
        if (!ChatUtils.a() || deepLink.c == null) {
            return;
        }
        switch (.b[deepLink.c.ordinal()]) {
            default: {
                Log.d(g, "Unknown deep link handed to chat: " + (Object)deepLink);
                return;
            }
            case 59: {
                this.a(MessageCenterFragment.a());
                return;
            }
            case 60: {
                this.a(MessageCenterFragment.a());
                this.a(ChatFragment.c(deepLink.d));
                return;
            }
            case 61: {
                this.a(MessageCenterFragment.a());
                this.a(ChatFragment.d(deepLink.d));
                return;
            }
            case 62: 
        }
        this.g(deepLink.d);
    }

    private void a(String string2, Long l) {
        SongbookManager.b().a(string2, new SongbookManager.GetArrangementFromRavenSongCallback(this, string2, l){
            final /* synthetic */ String a;
            final /* synthetic */ Long b;
            final /* synthetic */ MasterActivity c;
            {
                this.c = masterActivity;
                this.a = string2;
                this.b = l;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void handleResponse(com.smule.android.network.managers.SongbookManager$GetArrangementFromRavenSongResponse getArrangementFromRavenSongResponse) {
                if (this.c.j()) {
                    return;
                }
                if (getArrangementFromRavenSongResponse != null && getArrangementFromRavenSongResponse.a() && getArrangementFromRavenSongResponse.mArrVersion != null) {
                    this.c.a(new Runnable(this, getArrangementFromRavenSongResponse){
                        final /* synthetic */ com.smule.android.network.managers.SongbookManager$GetArrangementFromRavenSongResponse a;
                        final /* synthetic */  b;
                        {
                            this.b = var1_1;
                            this.a = getArrangementFromRavenSongResponse;
                        }

                        public void run() {
                            Log.c(MasterActivity.g, "show opencalls for song, songUid: " + this.b.a);
                            SongbookEntry songbookEntry = SongbookEntry.a(this.a.mArrVersion);
                            SingBundle singBundle = new SingBundle.Builder().a(songbookEntry).b(SubscriptionManager.a().b()).a(this.b.b).a();
                            PreSingActivity.StartupMode startupMode = PreSingActivity.StartupMode.f;
                            PreSingActivity.a((Context)this.b.c).a(startupMode).a(songbookEntry).a(this.b.b.longValue()).a(singBundle).a();
                        }
                    });
                    return;
                }
                String string2 = getArrangementFromRavenSongResponse == null ? "NULL reply" : "error in call";
                Log.d(MasterActivity.g, "showOpenCallsForSong for songUid " + this.a + " failed; " + string2);
                int n = getArrangementFromRavenSongResponse != null && getArrangementFromRavenSongResponse.a != null ? getArrangementFromRavenSongResponse.a.f : 0;
                this.c.a(n, false, new Runnable(this){
                    final /* synthetic */  a;
                    {
                        this.a = var1_1;
                    }

                    public void run() {
                        this.a.c.t();
                    }
                });
            }
        });
    }

    private void a(String string2, Long l, JoinSectionType joinSectionType) {
        com.smule.android.network.managers.ArrangementManager.a().a(string2, new ArrangementManager(this, string2, l, joinSectionType){
            final /* synthetic */ String a;
            final /* synthetic */ Long b;
            final /* synthetic */ JoinSectionType c;
            final /* synthetic */ MasterActivity d;
            {
                this.d = masterActivity;
                this.a = string2;
                this.b = l;
                this.c = joinSectionType;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void handleResponse(com.smule.android.network.managers.ArrangementManager$ArrangementVersionResponse arrangementVersionResponse) {
                if (this.d.j()) {
                    return;
                }
                if (arrangementVersionResponse != null && arrangementVersionResponse.a() && arrangementVersionResponse.mArrangementVersion != null) {
                    this.d.a(new Runnable(this, arrangementVersionResponse){
                        final /* synthetic */ com.smule.android.network.managers.ArrangementManager$ArrangementVersionResponse a;
                        final /* synthetic */  b;
                        {
                            this.b = var1_1;
                            this.a = arrangementVersionResponse;
                        }

                        public void run() {
                            Log.c(MasterActivity.g, "show opencalls for arrangement, key: " + this.b.a);
                            SongbookEntry songbookEntry = SongbookEntry.a(this.a.mArrangementVersion);
                            SingBundle singBundle = new SingBundle.Builder().a(songbookEntry).b(SubscriptionManager.a().b()).a(this.b.b).a(this.b.c).a();
                            PreSingActivity.StartupMode startupMode = PreSingActivity.StartupMode.f;
                            PreSingActivity.a((Context)this.b.d).a(startupMode).a(songbookEntry).a(this.b.b.longValue()).a(singBundle).a();
                        }
                    });
                    return;
                }
                String string2 = arrangementVersionResponse == null ? "NULL reply" : "error in call";
                Log.d(MasterActivity.g, "showOpenCallsForArrangement for key " + this.a + " failed; " + string2);
                int n = arrangementVersionResponse != null && arrangementVersionResponse.a != null ? arrangementVersionResponse.a.f : 0;
                this.d.a(n, false, new Runnable(this){
                    final /* synthetic */  a;
                    {
                        this.a = var1_1;
                    }

                    public void run() {
                        this.a.d.t();
                    }
                });
            }
        });
    }

    private void a(String string2, String string3) {
        this.C = new UpdatedTermsDialog(this, string2, string3);
        this.C.setCancelable(false);
        this.C.a(new Runnable(this){
            final /* synthetic */ MasterActivity a;
            {
                this.a = masterActivity;
            }

            public void run() {
                com.smule.android.network.managers.ContactsManager.a().a(com.smule.android.network.managers.UserManager.a().V(), new com.smule.android.network.managers.ContactsManager$UpdateConsentCallback(this){
                    final /* synthetic */  a;
                    {
                        this.a = var1_1;
                    }

                    /*
                     * Enabled aggressive block sorting
                     */
                    public void handleResponse(com.smule.android.network.core.NetworkResponse networkResponse) {
                        if (networkResponse.c()) {
                            if (MasterActivity.e(this.a.a) != null) {
                                MasterActivity.e(this.a.a).dismiss();
                            }
                            com.smule.android.network.managers.UserManager.a().d(true);
                            return;
                        } else {
                            if (this.a.a.n == null) {
                                this.a.a.n = new BusyDialog((Activity)this.a.a, this.a.a.getResources().getString(2131296658));
                            }
                            this.a.a.n.a(2, this.a.a.getResources().getString(2131296659), this.a.a.getResources().getString(2131296658), null, this.a.a.getResources().getString(2131296705));
                            if (this.a.a.isFinishing()) return;
                            {
                                this.a.a.n.show();
                                return;
                            }
                        }
                    }
                });
            }
        });
    }

    private void a(String string2, boolean bl, SingBundle.PerformanceType performanceType, int n, Long l, boolean bl2) {
        this.a(string2, bl, performanceType, n, l, bl2, false);
    }

    private void a(String string2, boolean bl, SingBundle.PerformanceType performanceType, int n, Long l, boolean bl2, boolean bl3) {
        SongbookManager.b().a(string2, new SongbookManager.GetArrangementFromRavenSongCallback(this, string2, bl, performanceType, n, bl2, l, bl3){
            final /* synthetic */ String a;
            final /* synthetic */ boolean b;
            final /* synthetic */ SingBundle.PerformanceType c;
            final /* synthetic */ int d;
            final /* synthetic */ boolean e;
            final /* synthetic */ Long f;
            final /* synthetic */ boolean g;
            final /* synthetic */ MasterActivity h;
            {
                this.h = masterActivity;
                this.a = string2;
                this.b = bl;
                this.c = performanceType;
                this.d = n;
                this.e = bl2;
                this.f = l;
                this.g = bl3;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void handleResponse(com.smule.android.network.managers.SongbookManager$GetArrangementFromRavenSongResponse getArrangementFromRavenSongResponse) {
                if (getArrangementFromRavenSongResponse != null && getArrangementFromRavenSongResponse.a() && getArrangementFromRavenSongResponse.mArrVersion != null) {
                    this.h.a(new Runnable(this, getArrangementFromRavenSongResponse){
                        final /* synthetic */ com.smule.android.network.managers.SongbookManager$GetArrangementFromRavenSongResponse a;
                        final /* synthetic */  b;
                        {
                            this.b = var1_1;
                            this.a = getArrangementFromRavenSongResponse;
                        }

                        /*
                         * Enabled aggressive block sorting
                         */
                        public void run() {
                            Log.c(MasterActivity.g, "Starting song flow for migrated raven songUid " + this.b.a + " which has arr " + this.a.mArrVersion);
                            SongbookEntry songbookEntry = SongbookEntry.a(this.a.mArrVersion);
                            SingAnalytics.c((SongbookEntry)songbookEntry);
                            SingBundle.Builder builder = new SingBundle.Builder().a(songbookEntry);
                            Object object = this.b.b ? this.b.c : SingBundle.PerformanceType.a;
                            object = builder.a((SingBundle.PerformanceType)((Object)object)).b(this.b.d).g(this.b.e).a(this.b.f).a(this.b.g).a();
                            PreSingActivity.a((Context)this.b.h).a(PreSingActivity.StartupMode.i).a(songbookEntry).a("lk_url").a(this.b.f.longValue()).a((SingBundle)object).a(this.b.b).a();
                        }
                    });
                    return;
                }
                String string2 = getArrangementFromRavenSongResponse == null ? "NULL reply" : "error in call";
                Log.d(MasterActivity.g, "launchSingForSong for songUid " + this.a + " failed; " + string2);
                int n = getArrangementFromRavenSongResponse != null && getArrangementFromRavenSongResponse.a != null ? getArrangementFromRavenSongResponse.a.f : 0;
                this.h.a(n, false, new Runnable(this){
                    final /* synthetic */  a;
                    {
                        this.a = var1_1;
                    }

                    public void run() {
                        this.a.h.t();
                    }
                });
            }
        });
    }

    static /* synthetic */ boolean a(MasterActivity masterActivity, boolean bl) {
        masterActivity.B = bl;
        return bl;
    }

    private boolean as() {
        if (this.B || this.k.getVisibility() != 0) {
            return false;
        }
        MagicPreferences.d(this.getApplicationContext(), true);
        this.k.setVisibility(8);
        this.l.setVisibility(8);
        this.m.setVisibility(8);
        this.m.b();
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void at() {
        BaseFragment baseFragment = this.G();
        boolean bl = baseFragment.k();
        boolean bl2 = this.L();
        Object object = bl2 ? BaseFragment.a : BaseFragment.b;
        this.a((Object)object);
        if (!baseFragment.j()) {
            this.m_();
            MediaPlayerServiceController.a().p();
        } else {
            MediaPlayerServiceController.a().o();
        }
        baseFragment.B();
        object = this.getSupportActionBar();
        if (object == null) {
            return;
        }
        if (!bl) {
            object.hide();
            return;
        }
        if (!object.isShowing()) {
            baseFragment.w();
        }
        object.show();
        object = this.t.getToolbarView();
        bl = !bl2 && baseFragment.l();
        object.setDisplayUpButton(bl);
        this.t.getToolbarView().setEnableUpButton(baseFragment.l());
        this.invalidateOptionsMenu();
    }

    private void au() {
        this.a(true, false, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void av() {
        if (!MagicAdSettings.c()) return;
        {
            if (!SingApplication.d().c("InitAppTask.OP_RELOAD_SONGBOOK")) {
                Log.b(g, "FULLSCREEN_AD: songbook not loaded, will try precaching ad again when finished");
                SingApplication.d().a("OP_AD_INITIAL_PRECACHE_WAIT_FOR_SONGBOOK_LOAD", Collections.singletonList("InitAppTask.OP_RELOAD_SONGBOOK"), new Runnable(this){
                    final /* synthetic */ MasterActivity a;
                    {
                        this.a = masterActivity;
                    }

                    public void run() {
                        MasterActivity.c(this.a);
                    }
                });
                return;
            } else {
                MagicFullScreenAdMediatorAdapter magicFullScreenAdMediatorAdapter = MagicAdAdapterFactory.a().a((Activity)this);
                if (magicFullScreenAdMediatorAdapter == null) return;
                {
                    SingFullScreenAd singFullScreenAd = new SingFullScreenAd(SingAdSettings.FullScreenAdPlacement.a, null);
                    singFullScreenAd.a(false);
                    Log.c(g, "FULLSCREEN_AD: precaching initial ad upon songbook load complete");
                    magicFullScreenAdMediatorAdapter.preloadAd(singFullScreenAd);
                    return;
                }
            }
        }
    }

    private void aw() {
        NotificationCenter.a().a("NOTIFICATION_LOGIN_COUNT_RECEIVED_EVENT", this.F);
        NotificationCenter.a().a("NOTIFICATION_PUSH_COUNT_RECEIVED_EVENT", this.F);
    }

    private void ax() {
        NotificationCenter.a().b("NOTIFICATION_LOGIN_COUNT_RECEIVED_EVENT", this.F);
        NotificationCenter.a().b("NOTIFICATION_PUSH_COUNT_RECEIVED_EVENT", this.F);
    }

    public static MasterActivity b(Context context) {
        if (context != null && context instanceof MasterActivity) {
            return (MasterActivity)context;
        }
        return null;
    }

    static /* synthetic */ void b(MasterActivity masterActivity) {
        masterActivity.af();
    }

    private void b(String string2, Long l) {
        if (string2 == null || string2.isEmpty()) {
            Log.d(g, "performanceKey from deep link was null or empty");
            return;
        }
        com.smule.android.network.managers.PerformanceManager.a().a(string2, true, new PerformanceManager(this, l){
            final /* synthetic */ Long a;
            final /* synthetic */ MasterActivity b;
            {
                this.b = masterActivity;
                this.a = l;
            }

            public void handleResponse(com.smule.android.network.managers.PerformanceManager$PerformanceResponse object) {
                if (!object.a()) {
                    if (object.a.e()) {
                        this.b.a(object.a.f, true, new Runnable(this){
                            final /* synthetic */  a;
                            {
                                this.a = var1_1;
                            }

                            public void run() {
                                this.a.b.b();
                            }
                        });
                        return;
                    }
                    this.b.b();
                    return;
                }
                if (object.mRestricted) {
                    this.b.a(object.a.f, true, new Runnable(this){
                        final /* synthetic */  a;
                        {
                            this.a = var1_1;
                        }

                        public void run() {
                            this.a.b.b();
                        }
                    });
                    return;
                }
                object = object.mPerformance;
                if (object == null) {
                    this.b.b();
                    return;
                }
                PreSingActivity.a((Context)this.b).a(PreSingActivity.StartupMode.g).a(this.a.longValue()).a((com.smule.android.network.models.PerformanceV2)object).a();
            }
        });
    }

    private void b(String string2, boolean bl, SingBundle.PerformanceType performanceType, int n, Long l, boolean bl2) {
        com.smule.android.network.managers.ArrangementManager.a().a(string2, new ArrangementManager(this, string2, bl, performanceType, n, bl2, l){
            final /* synthetic */ String a;
            final /* synthetic */ boolean b;
            final /* synthetic */ SingBundle.PerformanceType c;
            final /* synthetic */ int d;
            final /* synthetic */ boolean e;
            final /* synthetic */ Long f;
            final /* synthetic */ MasterActivity g;
            {
                this.g = masterActivity;
                this.a = string2;
                this.b = bl;
                this.c = performanceType;
                this.d = n;
                this.e = bl2;
                this.f = l;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void handleResponse(com.smule.android.network.managers.ArrangementManager$ArrangementVersionResponse arrangementVersionResponse) {
                if (arrangementVersionResponse != null && arrangementVersionResponse.a() && arrangementVersionResponse.mArrangementVersion != null) {
                    this.g.a(new Runnable(this, arrangementVersionResponse){
                        final /* synthetic */ com.smule.android.network.managers.ArrangementManager$ArrangementVersionResponse a;
                        final /* synthetic */  b;
                        {
                            this.b = var1_1;
                            this.a = arrangementVersionResponse;
                        }

                        /*
                         * Enabled aggressive block sorting
                         */
                        public void run() {
                            Log.c(MasterActivity.g, "Starting song flow for key " + this.b.a);
                            SongbookEntry songbookEntry = SongbookEntry.a(this.a.mArrangementVersion);
                            SingAnalytics.c((SongbookEntry)songbookEntry);
                            SingBundle.Builder builder = new SingBundle.Builder().a(songbookEntry);
                            Object object = this.b.b ? this.b.c : SingBundle.PerformanceType.a;
                            object = builder.a((SingBundle.PerformanceType)((Object)object)).b(this.b.d).g(this.b.e).a(this.b.f).a();
                            PreSingActivity.a((Context)this.b.g).a(PreSingActivity.StartupMode.i).a(songbookEntry).a("lk_url").a(this.b.f.longValue()).a((SingBundle)object).a(this.b.b).a();
                        }
                    });
                    return;
                }
                String string2 = arrangementVersionResponse == null ? "NULL reply" : "error in call";
                Log.d(MasterActivity.g, "launchArrangement for key " + this.a + " failed; " + string2);
                int n = arrangementVersionResponse != null && arrangementVersionResponse.a != null ? arrangementVersionResponse.a.f : 0;
                this.g.a(n, false, new Runnable(this){
                    final /* synthetic */  a;
                    {
                        this.a = var1_1;
                    }

                    public void run() {
                        this.a.g.t();
                    }
                });
            }
        });
    }

    static /* synthetic */ void c(MasterActivity masterActivity) {
        masterActivity.av();
    }

    private void c(String string2, Long l) {
        if (this.n == null || !this.n.isShowing()) {
            this.n = new BusyDialog((Activity)this, this.getString(2131296698));
            this.n.a(true);
        }
        com.smule.android.network.managers.PerformanceManager.a().a(string2, false, new PerformanceManager(this, l){
            final /* synthetic */ Long a;
            final /* synthetic */ MasterActivity b;
            {
                this.b = masterActivity;
                this.a = l;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void handleResponse(com.smule.android.network.managers.PerformanceManager$PerformanceResponse performanceResponse) {
                if (!performanceResponse.a()) {
                    if (!performanceResponse.a.e()) return;
                    {
                        this.b.a(performanceResponse.a.f, true, null);
                        return;
                    }
                } else {
                    if (performanceResponse.mPerformance == null) return;
                    {
                        this.b.a(new Runnable(this, performanceResponse){
                            final /* synthetic */ com.smule.android.network.managers.PerformanceManager$PerformanceResponse a;
                            final /* synthetic */  b;
                            {
                                this.b = var1_1;
                                this.a = performanceResponse;
                            }

                            public void run() {
                                this.b.b.T();
                                this.b.b.a(this.a.mPerformance, true, true, null, this.b.a, null, false, 0, null, -1);
                            }
                        });
                        return;
                    }
                }
            }
        });
    }

    static /* synthetic */ boolean d(MasterActivity masterActivity) {
        return masterActivity.as();
    }

    static /* synthetic */ UpdatedTermsDialog e(MasterActivity masterActivity) {
        return masterActivity.C;
    }

    private void f(int n) {
        FragmentManager fragmentManager = this.getFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > n) {
            fragmentManager.popBackStack(fragmentManager.getBackStackEntryAt(n).getId(), 1);
        }
    }

    private void f(String string2) {
        if (string2 == null || string2.isEmpty()) {
            Log.d(g, "performanceKey from deep link was null or empty");
            return;
        }
        com.smule.android.network.managers.PerformanceManager.a().a(string2, true, new PerformanceManager(this){
            final /* synthetic */ MasterActivity a;
            {
                this.a = masterActivity;
            }

            public void handleResponse(com.smule.android.network.managers.PerformanceManager$PerformanceResponse object) {
                if (!object.a()) {
                    Log.d(MasterActivity.g, "OpenCall not found - Server responded !ok");
                    if (object.a.e()) {
                        this.a.a(object.a.f, true, new Runnable(this){
                            final /* synthetic */  a;
                            {
                                this.a = var1_1;
                            }

                            public void run() {
                                this.a.a.b();
                            }
                        });
                        return;
                    }
                    this.a.b();
                    return;
                }
                if (object.mRestricted) {
                    this.a.a(object.a.f, true, new Runnable(this){
                        final /* synthetic */  a;
                        {
                            this.a = var1_1;
                        }

                        public void run() {
                            this.a.a.b();
                        }
                    });
                    return;
                }
                object = object.mPerformance;
                if (object == null) {
                    Log.d(MasterActivity.g, "OpenCall not found - Server response was null");
                    this.a.b();
                    return;
                }
                this.a.a(new Runnable(this, (com.smule.android.network.models.PerformanceV2)object){
                    final /* synthetic */ com.smule.android.network.models.PerformanceV2 a;
                    final /* synthetic */  b;
                    {
                        this.b = var1_1;
                        this.a = performanceV2;
                    }

                    public void run() {
                        PreSingActivity.a((Context)this.b.a).a(PreSingActivity.StartupMode.h).a(this.a).a();
                    }
                });
            }
        });
    }

    private void f(boolean bl) {
        ThreadUtils.a(new Runnable(this, bl){
            final /* synthetic */ boolean a;
            final /* synthetic */ MasterActivity b;
            {
                this.b = masterActivity;
                this.a = bl;
            }

            public void run() {
                if (this.a && this.b.i.getSelectedTab() == BottomNavView.Tab.c) {
                    this.b.a(BottomNavView.Tab.c, true);
                    return;
                }
                this.b.i.a(BottomNavView.Tab.c, true);
            }
        });
    }

    private void g(String string2) {
        try {
            long l = Long.parseLong(string2);
            AccountIconCache.a().a(l, new UserManager(this, l){
                final /* synthetic */ long a;
                final /* synthetic */ MasterActivity b;
                {
                    this.b = masterActivity;
                    this.a = l;
                }

                public void handleResponse(com.smule.android.network.managers.UserManager$AccountIconsResponse object) {
                    if (object.a()) {
                        for (com.smule.android.network.models.AccountIcon accountIcon : object.accountIcons) {
                            if (accountIcon.accountId != this.a) continue;
                            this.b.a(MessageCenterFragment.a());
                            this.b.a(ChatFragment.c(accountIcon.jid));
                            break;
                        }
                    }
                }
            });
            return;
        }
        catch (Exception exception) {
            Log.d(g, "Invalid accountId sent to chat: " + string2);
            return;
        }
    }

    private void g(boolean bl) {
        if (this.ae() != null) {
            this.ae().d(bl);
        }
        if (this.ad() != null) {
            this.ad().d(bl);
        }
    }

    private void h(String string2) {
        AdVendorManagerConfig.b((Activity)this);
        AdVendorManager.a().a((Activity)this, AdVendor.AdType.a, string2, null);
    }

    public void A() {
        if (this.A) {
            this.A = false;
            if (this.M != null) {
                this.M.dismiss();
            }
            SingApplication.g().getSharedPreferences(MasterActivity.class.getName(), 0).edit().putBoolean("SHOW_CHAT_TOOL_TIP", false).apply();
        }
    }

    public void B() {
        if (this.M != null) {
            this.M.dismiss();
        }
    }

    @UiThread
    public void C() {
        if (!this.q_() || this.j()) {
            return;
        }
        this.i.c();
    }

    public void D() {
        this.i.setTranslationY(0.0f);
        this.i.setVisibility(0);
        this.ac().setTranslationY((float)(- this.i.getHeight()));
        this.g(true);
    }

    public void E() {
        this.i.setVisibility(8);
        this.ac().setTranslationY(0.0f);
        this.g(false);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    protected boolean F() {
        if (this.M == null || !this.M.isShowing()) return this.as();
        if (this.G() instanceof MessageCenterFragment) {
            this.B();
            do {
                return true;
                break;
            } while (true);
        }
        this.A();
        return true;
    }

    @Override
    public BaseFragment G() {
        return (BaseFragment)this.getFragmentManager().findFragmentById(2131755458);
    }

    @Override
    public void H() {
        BaseFragment baseFragment = this.G();
        if (baseFragment != null) {
            baseFragment.y();
        }
    }

    @Override
    public void I() {
        BaseFragment baseFragment = this.G();
        if (baseFragment != null) {
            baseFragment.z();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void J() {
        if (this.v == null) {
            Log.d(g, "refreshBottomMarginToFragmentContentView - called, but mFragmentContentView is null. Aborting!");
            return;
        } else {
            int n = (this.ad() != null || this.ae() != null) && this.ab().getVisibility() == 0 ? (int)this.getResources().getDimension(2131427627) : 0;
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)this.v.getLayoutParams();
            if (marginLayoutParams.bottomMargin == n) return;
            {
                marginLayoutParams.setMargins(0, 0, 0, n);
                this.v.setLayoutParams((ViewGroup.LayoutParams)marginLayoutParams);
                return;
            }
        }
    }

    public void K() {
        if (this.ad() != null) {
            this.ad().ab();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected boolean L() {
        boolean bl = false;
        int n = this.getFragmentManager().getBackStackEntryCount();
        if (n == 0) {
            return true;
        }
        boolean bl2 = bl;
        if (n != 1) return bl2;
        BaseFragment baseFragment = this.G();
        if (baseFragment instanceof ProfileFragment) {
            bl2 = bl;
            if (!((ProfileFragment)baseFragment).O()) return bl2;
        }
        if (baseFragment == null) return true;
        if (!baseFragment.m()) return false;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public boolean M() {
        block3 : {
            block2 : {
                if (this.i == null) break block2;
                int n = this.i.getHeight();
                int n2 = (int)this.i.getTranslationY();
                if (this.i.getVisibility() == 0 && n2 < n) break block3;
            }
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public boolean N() {
        boolean bl = this.ad() != null && this.ad().K() || this.ae() != null && this.ae().K();
        if (!this.L()) return false;
        if (!bl) {
            return true;
        }
        return false;
    }

    public void O() {
        this.a("_open_mic_5m", false, null, 0, -1, SingApplication.m(), true);
    }

    public void P() {
        com.smule.android.facebook.MagicFacebook.a().a(new MagicFacebook(this){
            final /* synthetic */ MasterActivity a;
            {
                this.a = masterActivity;
            }

            public void a() {
            }

            public void a(List<com.smule.android.facebook.FacebookFriend> list, List<com.smule.android.facebook.FacebookFriend> list2, boolean bl) {
                if (list != null && !list.isEmpty()) {
                    MagicPreferences.a((Context)this.a, false);
                    this.a.Q();
                }
            }
        }, false, 10);
    }

    protected void Q() {
        if (this.isFinishing()) {
            return;
        }
        this.i.b();
    }

    public void R() {
        this.h.requestFocus();
    }

    public void S() {
        if (!(this.G() instanceof CategoriesListFragment)) {
            Log.e(g, "closeCategoriesAndRefreshSongbook can only be called from CategoriesListFragment");
            return;
        }
        this.getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener(this){
            final /* synthetic */ MasterActivity a;
            {
                this.a = masterActivity;
            }

            public void onBackStackChanged() {
                this.a.getFragmentManager().removeOnBackStackChangedListener((FragmentManager.OnBackStackChangedListener)this);
                if (!(this.a.G() instanceof SongbookFragment)) {
                    Log.e(MasterActivity.g, "expected SongbookFragment to be on top of the back stack after popping CategoriesListFragment");
                    return;
                }
                ((SongbookFragment)this.a.G()).d("CATEGORIES_UPDATED_ACTION");
            }
        });
        this.getFragmentManager().popBackStack();
    }

    protected void T() {
        if (this.n != null) {
            this.n.dismiss();
            this.n = null;
        }
    }

    protected BaseFragment a(BottomNavView.Tab object) {
        String string2 = object.g;
        if (this.G.containsKey(string2)) {
            return this.G.get(string2);
        }
        object = object.h.a();
        this.G.put(string2, (BaseFragment)((Object)object));
        return object;
    }

    public SongbookListViewState a(SongbookSection songbookSection) {
        SongbookListViewState songbookListViewState;
        SongbookListViewState songbookListViewState2 = songbookListViewState = this.o.get(songbookSection.j);
        if (songbookListViewState == null) {
            songbookListViewState2 = new SongbookListViewState(songbookSection);
            this.o.put(songbookSection.j, songbookListViewState2);
        }
        return songbookListViewState2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public QuickReturnListViewMenuSyncer a(AbsListView absListView, QuickReturnListViewMenuSyncer.ActionBarHighlightMode actionBarHighlightMode, AbsListView.OnScrollListener onScrollListener) {
        BottomNavView bottomNavView;
        Log.b(g, "syncMenuWithListView called");
        if (onScrollListener instanceof PauseOnScrollListener) {
            onScrollListener = new PauseOnScrollListener(ImageLoader.a(), true, true, onScrollListener);
        }
        if (this.L()) {
            bottomNavView = this.i;
            do {
                return new QuickReturnListViewMenuSyncer((MediaPlayingActivity)this, absListView, actionBarHighlightMode, onScrollListener, (View)bottomNavView, (View)this.ac(), this.t);
                break;
            } while (true);
        }
        bottomNavView = null;
        return new QuickReturnListViewMenuSyncer((MediaPlayingActivity)this, absListView, actionBarHighlightMode, onScrollListener, (View)bottomNavView, (View)this.ac(), this.t);
    }

    public void a(int n, int n2) {
        if (this.H) {
            this.k.setVisibility(0);
            this.l.setVisibility(0);
            this.m.setVisibility(0);
            this.k.setOnClickListener(new View.OnClickListener(this){
                final /* synthetic */ MasterActivity a;
                {
                    this.a = masterActivity;
                }

                public void onClick(View view) {
                    MasterActivity.d(this.a);
                }
            });
            this.k.a(this.h.getLeft(), this.h.getRight(), n, n2);
            this.l.setY((float)n2);
            int n3 = this.h.getLeft();
            int n4 = (this.h.getRight() - this.h.getLeft()) / 2;
            n2 = (n2 - n) / 2;
            this.m.a(n3 + n4, n2 + n);
            this.m.a();
            ValueAnimator valueAnimator = ValueAnimator.ofFloat((float[])new float[]{0.0f, 1.0f});
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this){
                final /* synthetic */ MasterActivity a;
                {
                    this.a = masterActivity;
                }

                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    if (this.a.k != null) {
                        this.a.k.setAlpha(((java.lang.Float)valueAnimator.getAnimatedValue()).floatValue());
                    }
                }
            });
            ValueAnimator valueAnimator2 = ValueAnimator.ofFloat((float[])new float[]{0.0f, 1.0f});
            valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this){
                final /* synthetic */ MasterActivity a;
                {
                    this.a = masterActivity;
                }

                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    if (this.a.l != null) {
                        this.a.l.setAlpha(((java.lang.Float)valueAnimator.getAnimatedValue()).floatValue());
                    }
                }
            });
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{valueAnimator, valueAnimator2});
            animatorSet.addListener((Animator.AnimatorListener)new AnimatorEndListener(this){
                final /* synthetic */ MasterActivity a;
                {
                    this.a = masterActivity;
                }

                public void onAnimationEnd(Animator animator2) {
                    MasterActivity.a(this.a, false);
                }
            });
            animatorSet.setDuration(2000);
            animatorSet.start();
            this.B = true;
        }
    }

    public void a(Fragment fragment, int n, int n2, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        this.I = n;
        this.J = n2;
        this.K = new WeakListener.OnClickListener((Object)fragment, onClickListener);
        this.L = new WeakListener.OnClickListener((Object)fragment, onClickListener2);
        if (this.M != null) {
            this.M.a(n, n2, onClickListener, onClickListener2);
        }
    }

    @Override
    public void a(BaseFragment menuToggleAction) {
        Log.b(g, "toggleBottomMenu:" + (Object)((Object)menuToggleAction));
        BaseFragment baseFragment = this.G();
        if (baseFragment != null && baseFragment.g()) {
            this.E();
            return;
        }
        switch (.a[menuToggleAction.ordinal()]) {
            default: {
                return;
            }
            case 1: {
                this.D();
                return;
            }
            case 2: {
                this.E();
                return;
            }
            case 3: 
        }
        if (this.N()) {
            this.D();
            return;
        }
        this.E();
    }

    @Override
    public void a(BottomNavView.Tab tab, boolean bl) {
        if (!this.q_()) {
            return;
        }
        FragmentManager fragmentManager = this.getFragmentManager();
        if (fragmentManager.findFragmentByTag(tab.g) != null) {
            fragmentManager.popBackStack(tab.g, 0);
            return;
        }
        if (this.M != null) {
            this.A();
        }
        BaseFragment baseFragment = this.a(tab);
        if (bl) {
            this.f(0);
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(2131755458, (Fragment)baseFragment, tab.g);
        fragmentTransaction.addToBackStack(tab.g);
        fragmentTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
        this.au();
        this.j = tab.f;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @SuppressLint(value={"ValueOfNotAllowedForNumberSubClasses"})
    public void a(@NonNull DeepLink object, boolean bl) {
        Log.a(g, "Processing deep link: " + object);
        if (bl) {
            this.f(1);
        }
        if ("market".equals(object.e)) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(object.b);
            this.startActivity(intent);
            return;
        }
        if (object.c == null) {
            throw new RuntimeException("deep link target was null");
        }
        this.H = false;
        this.as();
        Object object2 = DeepLink.a((Context)this, (Uri)object.b);
        switch (.b[object.c.ordinal()]) {
            default: {
                return;
            }
            case 1: {
                this.H = true;
                this.f(true);
                return;
            }
            case 2: {
                this.H = true;
                object = object.d;
                object2 = this.G();
                if (object2 instanceof SongbookFragment) {
                    ((SongbookFragment)((Object)object2)).e((String)object);
                    return;
                }
                this.e((String)object);
                return;
            }
            case 3: 
            case 4: {
                this.i.a(BottomNavView.Tab.e, bl);
                return;
            }
            case 5: {
                this.b(3);
                return;
            }
            case 6: {
                this.b();
                this.a(Long.parseLong(object.d));
                return;
            }
            case 7: {
                this.f(object.d);
                return;
            }
            case 8: {
                this.i.a(BottomNavView.Tab.b, bl);
                return;
            }
            case 9: {
                this.a(FindFriendsFragment.a(FindFriendsFragment.EntryPoint.d), FindFriendsFragment.g);
                return;
            }
            case 10: 
            case 11: 
            case 12: {
                this.a(object.d, false, null, 0, (Long)object2, DeepLink.b((Context)this, (Uri)object.b));
                return;
            }
            case 13: {
                this.a(object.d, true, SingBundle.PerformanceType.b, 0, (Long)object2, DeepLink.b((Context)this, (Uri)object.b));
                return;
            }
            case 14: {
                this.a(object.d, true, SingBundle.PerformanceType.c, -1, (Long)object2, DeepLink.b((Context)this, (Uri)object.b));
                return;
            }
            case 15: 
            case 16: {
                this.a(object.d, true, SingBundle.PerformanceType.c, 1, (Long)object2, DeepLink.b((Context)this, (Uri)object.b));
                return;
            }
            case 17: {
                this.a(object.d, true, SingBundle.PerformanceType.c, 2, (Long)object2, DeepLink.b((Context)this, (Uri)object.b));
                return;
            }
            case 18: {
                this.a(object.d, true, SingBundle.PerformanceType.c, 0, (Long)object2, DeepLink.b((Context)this, (Uri)object.b));
                return;
            }
            case 19: 
            case 20: 
            case 21: 
            case 22: 
            case 23: 
            case 24: 
            case 25: {
                this.a(object.d, true, SingBundle.PerformanceType.d, 0, (Long)object2, DeepLink.b((Context)this, (Uri)object.b));
                return;
            }
            case 26: 
            case 27: {
                this.b(object.d, (Long)object2);
                return;
            }
            case 28: 
            case 29: {
                this.b(object.d, false, null, 0, (Long)object2, DeepLink.b((Context)this, (Uri)object.b));
                return;
            }
            case 30: {
                this.b(object.d, true, SingBundle.PerformanceType.b, 0, (Long)object2, DeepLink.b((Context)this, (Uri)object.b));
                return;
            }
            case 31: {
                this.b(object.d, true, SingBundle.PerformanceType.c, -1, (Long)object2, DeepLink.b((Context)this, (Uri)object.b));
                return;
            }
            case 32: 
            case 33: {
                this.b(object.d, true, SingBundle.PerformanceType.c, 1, (Long)object2, DeepLink.b((Context)this, (Uri)object.b));
                return;
            }
            case 34: {
                this.b(object.d, true, SingBundle.PerformanceType.c, 2, (Long)object2, DeepLink.b((Context)this, (Uri)object.b));
                return;
            }
            case 35: {
                this.b(object.d, true, SingBundle.PerformanceType.c, 0, (Long)object2, DeepLink.b((Context)this, (Uri)object.b));
                return;
            }
            case 36: 
            case 37: 
            case 38: 
            case 39: 
            case 40: 
            case 41: 
            case 42: {
                this.b(object.d, true, SingBundle.PerformanceType.d, 0, (Long)object2, DeepLink.b((Context)this, (Uri)object.b));
                return;
            }
            case 43: {
                this.a(object.d, (Long)object2);
                return;
            }
            case 44: {
                this.a(object.d, (Long)object2, JoinSectionType.e);
                return;
            }
            case 45: {
                this.a(object.d, (Long)object2, JoinSectionType.g);
                return;
            }
            case 46: {
                this.a(object.d, (Long)object2, JoinSectionType.f);
                return;
            }
            case 47: {
                this.b("3402003_3402003", false, null, 0, (Long)object2, true);
                return;
            }
            case 48: {
                this.a(SettingsFragment.a(), SettingsFragment.g);
                return;
            }
            case 49: {
                this.a(WebViewFragment.a(com.smule.android.network.managers.UserManager.a().W(), this.getResources().getString(2131297361)), WebViewFragment.class.getName());
                return;
            }
            case 50: {
                this.a(WebViewFragment.a(com.smule.android.network.managers.UserManager.a().X(), this.getResources().getString(2131297371)), WebViewFragment.class.getName());
                return;
            }
            case 51: {
                NotificationsFragment.a(NotificationsFragment.Tabs.a);
                this.i.a(BottomNavView.Tab.d, bl);
                return;
            }
            case 52: {
                NotificationsFragment.a(NotificationsFragment.Tabs.b);
                this.i.a(BottomNavView.Tab.d, bl);
                return;
            }
            case 53: {
                this.i.a(BottomNavView.Tab.a, bl);
                return;
            }
            case 54: {
                if (this.G() == null) {
                    NotificationsFragment.J();
                    this.i.a(BottomNavView.Tab.d, bl);
                } else if (this.i.getSelectedTab() == BottomNavView.Tab.c) {
                    this.a(BottomNavView.Tab.c, true);
                }
                this.c(object.d, (Long)object2);
                return;
            }
            case 55: {
                if (!SubscriptionManager.a().b()) {
                    object = UpsellManager.a((boolean)false, (SongbookEntry)null, (String)"lk_url", (String)null, (UpsellType)UpsellType.c);
                    this.a((BaseFragment)((Object)object), object.x());
                    return;
                }
                this.b();
                return;
            }
            case 56: {
                if (!SubscriptionManager.a().b()) {
                    object = UpsellManager.a((boolean)false, (SongbookEntry)null, (String)"lk_url", (String)object.d, (UpsellType)UpsellType.c);
                    this.a((BaseFragment)((Object)object), object.x());
                    return;
                }
                this.b();
                return;
            }
            case 57: {
                this.b();
                return;
            }
            case 58: {
                this.b();
                if (SubscriptionManager.a().b()) return;
                {
                    this.h(object.d);
                    return;
                }
            }
            case 59: 
            case 60: 
            case 61: 
            case 62: {
                this.a((DeepLink)object);
                return;
            }
            case 63: {
                Log.b(g, "Twitter reply: " + (Object)object.b);
                MagicTwitter.a().a(object.b);
                return;
            }
            case 64: {
                object = SingApplication.h().b(object.d);
                SingApplication.h().a((Locale)object);
                this.recreate();
                return;
            }
            case 65: {
                this.b();
                try {
                    PromotionManager.a().a(Long.parseLong(object.d), new PromotionManager.PromotionCallback(this, (DeepLink)object){
                        final /* synthetic */ DeepLink a;
                        final /* synthetic */ MasterActivity b;
                        {
                            this.b = masterActivity;
                            this.a = deepLink;
                        }

                        public void handleResponse(com.smule.android.network.managers.PromotionManager$PromotionResponse promotionResponse) {
                            if (promotionResponse.a()) {
                                String string2 = this.b.getResources().getString(2131297220);
                                Object object = string2;
                                if (promotionResponse.hashtag != null) {
                                    object = string2;
                                    if (!promotionResponse.hashtag.isEmpty()) {
                                        object = "#" + promotionResponse.hashtag;
                                    }
                                }
                                object = WebViewFragment.a(promotionResponse.promoUrl + "?account_id=" + com.smule.android.network.managers.UserManager.a().f() + "&app_id=sing_google&locale=" + this.b.getResources().getConfiguration().locale.toString(), (String)object, false);
                                this.b.a((BaseFragment)((Object)object));
                                MasterActivity.b(this.b);
                                MagicPreferences.a((Context)this.b, "LAST_PROMOTION_HASHTAG_PAIR", this.a.d + "," + promotionResponse.hashtag);
                                return;
                            }
                            if (promotionResponse.a.b == 1027) {
                                Toaster.a((Context)this.b, 2131297218);
                                return;
                            }
                            Toaster.a((Context)this.b, 2131297217);
                        }
                    });
                    return;
                }
                catch (NumberFormatException numberFormatException) {
                    Log.e(g, "Trying to load a promo page for a non-number promoId: " + object.d);
                    Toaster.a((Context)this, 2131297217);
                    return;
                }
            }
            case 66: {
                try {
                    this.a(LeaderboardFragment.a(Long.parseLong(object.d)), LeaderboardFragment.g);
                    return;
                }
                catch (NumberFormatException numberFormatException) {
                    Log.e(g, "Trying to load a leaderboard for a non-number promoId: " + object.d);
                    Toaster.a((Context)this, 2131297217);
                    return;
                }
            }
            case 67: {
                if (this.z == null) {
                    Log.d(g, "onboarding deeplink has null intent", new Exception("onboarding deeplink has null intent"));
                    return;
                }
                object = (SongbookEntry)this.z.getParcelableExtra("ONBOARDING_SONGBOOK_ENTRY");
                if (object != null) {
                    bl = SubscriptionManager.a().b();
                    object = new SingBundle.Builder().a((SongbookEntry)object).b(bl).a(SingBundle.PerformanceType.b).d(true).a();
                    PreSingActivity.a((Context)this).a(PreSingActivity.StartupMode.c).a((SingBundle)object).a();
                    return;
                }
                Log.d(g, "onboarding deeplink does not contain SongbookEntry", new Exception("onboarding deeplink does not contain SongbookEntry"));
                return;
            }
            case 68: {
                this.a((BaseFragment)((Object)SearchByTagFragment.a(object.d, false)));
                return;
            }
            case 69: 
            case 70: {
                object2 = object.b.getQueryParameter("response_type");
                String string2 = object.b.getQueryParameter("client_id");
                String string3 = object.b.getQueryParameter("scope");
                String string4 = object.b.getQueryParameter("state");
                object = object.b.getQueryParameter("redirect_uri");
                OAuth2Manager.a().a((String)object2, string2, string3, string4, (String)object, new OAuth2Manager.InfoResponseCallback(this, string3, (String)object2, string2, string4, (String)object){
                    final /* synthetic */ String a;
                    final /* synthetic */ String b;
                    final /* synthetic */ String c;
                    final /* synthetic */ String d;
                    final /* synthetic */ String e;
                    final /* synthetic */ MasterActivity f;
                    {
                        this.f = masterActivity;
                        this.a = string2;
                        this.b = string3;
                        this.c = string4;
                        this.d = string5;
                        this.e = string6;
                    }

                    public void handleResponse(com.smule.android.network.managers.OAuth2Manager$InfoResponse infoResponse) {
                        if (infoResponse.a() && infoResponse.mScopes.contains(this.a)) {
                            this.f.a(this.b, this.c, this.a, this.d, this.e, infoResponse.mDenyUri, infoResponse.mClientName);
                        }
                    }
                });
                this.b();
                return;
            }
            case 71: {
                object = Intent.makeMainSelectorActivity((String)"android.intent.action.MAIN", (String)"android.intent.category.APP_EMAIL");
                object.addFlags(268435456);
                this.startActivity((Intent)object);
                Log.c(g, "open email inbox");
                return;
            }
            case 72: 
        }
        com.smule.android.network.managers.UserManager.a().a(com.smule.android.network.managers.UserManager.a().f(), new UserManager(this){
            final /* synthetic */ MasterActivity a;
            {
                this.a = masterActivity;
            }

            public void handleResponse(com.smule.android.network.core.NetworkResponse networkResponse) {
                if (networkResponse.c()) {
                    Log.c(MasterActivity.g, networkResponse.j);
                    return;
                }
                Log.c(MasterActivity.g, networkResponse.j);
                Toaster.a(this.a.getApplicationContext(), 2131297278);
            }
        });
    }

    @UiThread
    protected void a(String object, String string2, String string3, String string4, String string5, String string6, String string7) {
        if (!this.isFinishing()) {
            object = new AutoLoginAuthorizationDialog((Activity)this, (String)object, string2, string3, string4, string5, string6, string7);
            object.setCanceledOnTouchOutside(false);
            object.show();
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    protected boolean a() {
        block5 : {
            var2_1 = true;
            var1_2 = true;
            Log.b(MasterActivity.g, "processIntent: " + (Object)this.z);
            if (this.z == null) {
                return false;
            }
            if (this.z.getData() == null) ** GOTO lbl17
            var3_3 = this.z.getData();
            Log.c(MasterActivity.g, "Starting with URI: " + (Object)var3_3);
            var4_4 = new DeepLink(var3_3);
            this.a(var4_4, true);
            break block5;
            catch (IllegalArgumentException var4_5) {
                block6 : {
                    var1_2 = false;
                    break block6;
lbl17: // 1 sources:
                    var1_2 = false;
                    catch (IllegalArgumentException var4_6) {
                        var1_2 = var2_1;
                    }
                }
                Log.b(MasterActivity.g, "No match for URI: " + (Object)var3_3);
            }
        }
        this.z = null;
        this.setIntent(new Intent());
        return var1_2;
    }

    public void b() {
        this.f(false);
    }

    public void b(int n) {
        ((ProfileFragment)this.a(BottomNavView.Tab.e)).d(n);
        this.i.a(BottomNavView.Tab.e, true);
    }

    public void c(String string2) {
        this.p = string2;
    }

    @Override
    protected boolean c(BaseFragment baseFragment) {
        if (!(baseFragment instanceof SongbookFragment)) {
            this.b();
            return true;
        }
        return false;
    }

    public void d(String string2) {
        if (this.ad() != null) {
            this.ad().e(string2);
        }
    }

    public void e(String string2) {
        ((SongbookFragment)this.a(BottomNavView.Tab.c)).e(string2);
        this.i.a(BottomNavView.Tab.c, true);
    }

    @Override
    protected void f() {
        super.f();
        this.setSupportActionBar((Toolbar)this.t);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            ActionBarCustomView actionBarCustomView = ActionBarCustomView.a((Context)this);
            actionBarCustomView.setOnClickListener(this.s);
            this.t.setToolbarView(actionBarCustomView);
            actionBar.setCustomView((View)actionBarCustomView);
        }
        this.i.setOnTabChangedListener(this);
        this.a(com.smule.android.network.managers.UserManager.a().W(), com.smule.android.network.managers.UserManager.a().X());
        this.Q();
        this.C();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void onActivityResult(int n, int n2, Intent intent) {
        Log.b(g, "onActivityResult : " + n + " / " + n2 + ": " + (Object)intent);
        boolean bl = false;
        if (this.ai() != null) {
            bl = this.ai().a(n, n2, intent);
        }
        boolean bl2 = bl;
        if (!bl) {
            bl2 = bl;
            if (this.al() != null) {
                bl2 = bl;
                if (this.al().size() > 0) {
                    bl = this.al().get(this.al().size() - 1).onActivityResult(n, n2, intent);
                    Log.b(g, "onActivityResult Facebook callback managers: " + this.al().size() + " / " + bl);
                    bl2 = bl;
                    if (bl) {
                        this.al().remove(this.al().size() - 1);
                        bl2 = bl;
                    }
                }
            }
        }
        if (!bl2 && n2 == -1 && n == 42405) {
            this.au();
            try {
                this.a(new DeepLink(intent.getData()), true);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                Log.c(g, "ShareActivity returned an invalid deep link", illegalArgumentException);
            }
        }
        if (!bl2) {
            bl2 = AdVendorManager.a().a((Context)this, n, n2, intent);
        }
        if (!bl2) {
            super.onActivityResult(n, n2, intent);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String string2 = g;
        StringBuilder stringBuilder = new StringBuilder().append("onCreate - savedInstanceState is ");
        String string3 = bundle == null ? "null" : "not null";
        Log.b(string2, stringBuilder.append(string3).toString());
        string3 = bundle == null ? this.getIntent() : null;
        this.z = string3;
        this.getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener(this){
            final /* synthetic */ MasterActivity a;
            {
                this.a = masterActivity;
            }

            public void onBackStackChanged() {
                if (this.a.G() == null) {
                    return;
                }
                MasterActivity.a(this.a);
            }
        });
        this.A = SingApplication.g().getSharedPreferences(MasterActivity.class.getName(), 0).getBoolean("SHOW_CHAT_TOOL_TIP", true);
        boolean bl = bundle != null;
        if (!bl) {
            SingPermissionRequests.a(this, null);
        }
        this.av();
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onCreateOptionsMenu(Menu menu2) {
        boolean bl;
        Object object;
        boolean bl2 = true;
        Log.c(g, "onCreateOptionsMenu - begin - " + this.L());
        if (this.j()) {
            return false;
        }
        menu2.clear();
        BaseFragment baseFragment = this.G();
        if (baseFragment == null || baseFragment.e()) {
            this.getMenuInflater().inflate(2131820544, menu2);
            object = this.t;
            bl = baseFragment == null || baseFragment.f();
            object.setHasRightPadding(bl);
        } else {
            this.t.setHasRightPadding(baseFragment.f());
        }
        if (!this.L()) {
            for (int i = 0; i < menu2.size(); ++i) {
                menu2.getItem(i).setVisible(false);
                menu2.getItem(i).setEnabled(false);
            }
            object = this.t.getToolbarView();
            bl = baseFragment != null && baseFragment.l() ? bl2 : false;
            object.setDisplayUpButton(bl);
        }
        return super.onCreateOptionsMenu(menu2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MediaPlayerServiceController.a().e();
        if (this.C != null) {
            this.C.dismiss();
            this.C = null;
        }
        this.G.clear();
    }

    @Override
    public boolean onKeyDown(int n, KeyEvent keyEvent) {
        Object object = this.getFragmentManager();
        BaseFragment baseFragment = (PreviewFragment)object.findFragmentByTag("PREVIEW_FRAGMENT");
        object = (NowPlayingFragment)object.findFragmentByTag("NOW_PLAYING_FRAGMENT");
        if (baseFragment != null && object != null && n == 4 && baseFragment.a(n, keyEvent)) {
            object.U();
            return true;
        }
        if (baseFragment != null && baseFragment.a(n, keyEvent)) {
            return true;
        }
        if (object != null && object.a(n, keyEvent)) {
            return true;
        }
        baseFragment = this.Z();
        if (baseFragment != null && baseFragment.a(n, keyEvent)) {
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.hasExtra("com.smule.INTENT_EXTRA_FROM_MEDIA_NOTIFICATION")) {
            if (this.an() == null) return;
            {
                this.an().L();
                return;
            }
        } else {
            this.z = intent;
            if (!this.q_()) return;
            {
                this.a();
                return;
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                return super.onOptionsItemSelected(menuItem);
            }
            case 2131756839: {
                return false;
            }
            case 2131756836: {
                this.a(SettingsFragment.a(), SettingsFragment.g);
                return true;
            }
            case 2131756837: {
                this.a(WebViewFragment.a(this.getResources().getString(2131297950), this.getResources().getString(2131296724)));
                return true;
            }
            case 2131756838: 
        }
        this.a(WebViewFragment.a(this.getResources().getString(2131297948), this.getResources().getString(2131296692)));
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.ax();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.J();
        this.a(BaseFragment.c);
        this.t.d();
        if (com.smule.android.network.managers.UserManager.a().U() && this.C != null && !this.C.isShowing() && !this.isFinishing()) {
            this.C.show();
        }
        this.aw();
        AdVendorManagerConfig.a((Activity)this);
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.ad() != null) {
            this.getFragmentManager().putFragment(bundle, "NOW_PLAYING_FRAGMENT", (Fragment)this.ad());
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.b(g, "onStart - begin");
        SingAnalytics.u();
        BottomNavView.Tab tab = MagicPreferences.d(this.getApplicationContext());
        Object object = tab;
        if (tab == null) {
            object = BottomNavView.Tab.c;
        }
        if (this.a()) {
            object = null;
        } else if (this.Z() == null && this.j == null) {
            Log.b(g, "onStart - mSelectedMainTab is null so going to landing screen " + object.name());
            this.i.a((BottomNavView.Tab)((Object)object), true);
        } else {
            object = null;
        }
        SingAnalytics.a((BottomNavView.Tab)((Object)object));
        MediaPlayerServiceController.a().a((Activity)this);
        if (ChatUtils.a()) {
            object = SingApplication.k();
            object.a(this.E);
            object.a(this.D);
        }
        Log.b(g, "onStart - end");
    }

    @Override
    protected void onStop() {
        super.onStop();
        MediaPlayerServiceController.a().b((Activity)this);
        if (ChatUtils.a()) {
            ChatManager chatManager = SingApplication.k();
            chatManager.b(this.E);
            chatManager.b(this.D);
        }
    }

    public void onTrimMemory(int n) {
        super.onTrimMemory(n);
        this.G.clear();
    }

    public void t() {
        this.f(true);
    }

    public void u() {
        if (!(this.G() instanceof SettingsFragment)) {
            Log.e(g, "closeSettingsAndCustomizeProfile can only be called from SettingsFragment");
            return;
        }
        this.getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener(this){
            final /* synthetic */ MasterActivity a;
            {
                this.a = masterActivity;
            }

            public void onBackStackChanged() {
                this.a.getFragmentManager().removeOnBackStackChangedListener((FragmentManager.OnBackStackChangedListener)this);
                if (!(this.a.G() instanceof ProfileFragment)) {
                    Log.e(MasterActivity.g, "expected ProfileFragment to be on top of the back stack after popping SettingsFragment");
                    return;
                }
                ((ProfileFragment)this.a.G()).as();
            }
        });
        this.getFragmentManager().popBackStack();
    }

    @UiThread
    public void v() {
        this.i.a(BottomNavView.Tab.a, true);
    }

    @Click
    @Override
    public void w() {
        super.w();
    }

    public String x() {
        return this.p;
    }

    public void y() {
        if (ChatUtils.a() && this.A && this.ac().getVisibility() != 0) {
            this.M = new ChatTooltipDialog(this, this.I, this.J, this.K, this.L);
            this.M.a(2131296606);
            this.M.show();
        }
    }

    public void z() {
        this.M = new ChatTooltipDialog(this, this.I, this.J, this.K, this.L);
        this.M.a(2131296611);
        this.M.show();
    }
}

