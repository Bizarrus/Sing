/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Fragment
 *  android.app.FragmentManager
 *  android.app.FragmentManager$BackStackEntry
 *  android.app.FragmentTransaction
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Parcelable
 *  android.support.annotation.NonNull
 *  android.util.DisplayMetrics
 *  android.view.KeyEvent
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  com.facebook.CallbackManager
 *  com.facebook.CallbackManager$Factory
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer$ActionBarHighlightMode
 *  org.androidannotations.annotations.EActivity
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.mediaplaying;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import com.facebook.CallbackManager;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.NotificationCenter;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.bookmark.MediaPlayingMenuManager;
import com.smule.singandroid.customviews.ActionBarCustomView;
import com.smule.singandroid.customviews.MasterToolbar;
import com.smule.singandroid.hashtag.Hashtag;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.mediaplaying.MediaPlayingFragment;
import com.smule.singandroid.mediaplaying.MediaPlayingPlayable;
import com.smule.singandroid.mediaplaying.MediaPlayingPlaybackPresenter;
import com.smule.singandroid.mediaplaying.NowPlayingFragment;
import com.smule.singandroid.mediaplaying.PlaybackPresenter;
import com.smule.singandroid.mediaplaying.PreviewFragment;
import com.smule.singandroid.purchases.V3BillingHelper;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity
public abstract class MediaPlayingActivity
extends BaseActivity
implements BaseFragment {
    private boolean g;
    private volatile NowPlayingFragment h;
    private volatile PreviewFragment i;
    private FragmentTransaction j;
    private List<CallbackManager> k = new ArrayList<CallbackManager>();
    private MediaPlayingMenuManager l;
    private PlaybackPresenter m;
    private V3BillingHelper n = new V3BillingHelper();
    @ViewById
    protected MasterToolbar t;
    @ViewById
    protected View u;
    @ViewById
    protected RelativeLayout v;
    @ViewById
    protected RelativeLayout w;
    @ViewById
    protected View x;
    protected  y;

    static /* synthetic */ NowPlayingFragment a(MediaPlayingActivity mediaPlayingActivity) {
        return mediaPlayingActivity.h;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(boolean bl, Runnable runnable, boolean bl2) {
        FragmentTransaction fragmentTransaction;
        if (this.j == null) {
            fragmentTransaction = this.getFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(2131034128, 2131034117, 2131034128, 2131034117);
        } else {
            fragmentTransaction = this.j;
        }
        if (this.h.isAdded()) {
            fragmentTransaction.show((Fragment)this.h);
        } else {
            fragmentTransaction.replace(2131755945, (Fragment)this.h, "NOW_PLAYING_FRAGMENT");
        }
        if (this.j == null) {
            fragmentTransaction.commitAllowingStateLoss();
        }
        this.J();
        if (bl) {
            new Handler().postDelayed(new Runnable(this, runnable, bl2){
                final /* synthetic */ Runnable a;
                final /* synthetic */ boolean b;
                final /* synthetic */ MediaPlayingActivity c;
                {
                    this.c = mediaPlayingActivity;
                    this.a = runnable;
                    this.b = bl;
                }

                public void run() {
                    if (this.c.p_() && MediaPlayingActivity.a(this.c) != null) {
                        MediaPlayingActivity.a(this.c).a(this.a, this.b);
                    }
                }
            }, 100);
        }
        this.aa();
    }

    private boolean a() {
        if (this.v != null && this.v.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            return true;
        }
        return false;
    }

    private boolean a(MediaPlayingFragment mediaPlayingFragment) {
        if (mediaPlayingFragment != null && mediaPlayingFragment.isAdded() && !mediaPlayingFragment.isDetached() && !mediaPlayingFragment.isRemoving()) {
            return true;
        }
        return false;
    }

    protected boolean F() {
        return false;
    }

    public BaseFragment G() {
        return (BaseFragment)this.getFragmentManager().findFragmentById(2131755458);
    }

    public void H() {
        BaseFragment baseFragment = this.G();
        if (baseFragment != null) {
            baseFragment.y();
        }
    }

    public void I() {
        BaseFragment baseFragment = this.G();
        if (baseFragment != null) {
            baseFragment.z();
        }
    }

    public void J() {
    }

    public boolean M() {
        return false;
    }

    public MasterToolbar U() {
        return this.t;
    }

    public void V() {
        this.t.b(true);
        this.u.setVisibility(8);
    }

    public void W() {
        this.t.b(false);
        this.u.setVisibility(0);
    }

    public void X() {
        if (this.a()) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)this.v.getLayoutParams();
            layoutParams.addRule(3, 0);
            this.v.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
            this.u.setVisibility(8);
            this.t.getBackground().setAlpha(0);
        }
    }

    public void Y() {
        if (this.a()) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)this.v.getLayoutParams();
            layoutParams.addRule(3, this.t.getId());
            this.v.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
            this.u.setVisibility(0);
            this.t.getBackground().setAlpha(255);
        }
    }

    protected BaseFragment Z() {
        FragmentManager fragmentManager = this.getFragmentManager();
        int n = fragmentManager.getBackStackEntryCount();
        if (n > 0 && (fragmentManager = fragmentManager.findFragmentByTag(fragmentManager.getBackStackEntryAt(n - 1).getName())) instanceof BaseFragment) {
            return (BaseFragment)fragmentManager;
        }
        return null;
    }

    @Override
    public QuickReturnListViewMenuSyncer a(AbsListView absListView, QuickReturnListViewMenuSyncer.ActionBarHighlightMode actionBarHighlightMode, AbsListView.OnScrollListener onScrollListener) {
        return null;
    }

    @Override
    public void a(PerformanceV2 performanceV2, boolean bl, boolean bl2) {
        Log.b(a, "showNowPlayingBarForPerformance called");
        this.a(performanceV2, bl, bl2, null);
    }

    public void a(PerformanceV2 performanceV2, boolean bl, boolean bl2, Runnable runnable) {
        this.a(performanceV2, bl, bl2, runnable, -1, null, false, 0, null, -1);
    }

    public void a(PerformanceV2 performanceV2, boolean bl, boolean bl2, Runnable runnable, Long l, Analytics searchTarget, boolean bl3, int n, NowPlayingFragment.NowPlayingFragmentMenuSelectedCallback nowPlayingFragmentMenuSelectedCallback, int n2) {
        this.a(performanceV2, bl, bl2, runnable, l, searchTarget, bl3, n, nowPlayingFragmentMenuSelectedCallback, n2, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(PerformanceV2 performanceV2, boolean bl, boolean bl2, Runnable runnable, Long l, Analytics searchTarget, boolean bl3, int n, NowPlayingFragment.NowPlayingFragmentMenuSelectedCallback nowPlayingFragmentMenuSelectedCallback, int n2, boolean bl4) {
        if (!this.p_() || this.a(performanceV2)) {
            return;
        }
        MediaPlayerServiceController mediaPlayerServiceController = MediaPlayerServiceController.a();
        if (mediaPlayerServiceController.c(performanceV2.performanceKey) && this.h != null && this.h.isAdded()) {
            mediaPlayerServiceController.b();
            return;
        }
        if (bl) {
            MiscUtils.a((Activity)this, (boolean)true);
        }
        this.j = this.getFragmentManager().beginTransaction();
        if (bl && bl4) {
            this.j.setCustomAnimations(0, 0, 0, 0);
        } else {
            this.j.setCustomAnimations(2131034130, 2131034119, 2131034130, 2131034119);
        }
        this.c(bl4);
        this.h = NowPlayingFragment.a(performanceV2, bl2, this.M(), searchTarget, bl3, n, nowPlayingFragmentMenuSelectedCallback, n2);
        this.h.a(l);
        this.a(bl, runnable, bl4);
        this.j.commitAllowingStateLoss();
        this.j = null;
    }

    public void a(SongbookEntry songbookEntry, PerformanceV2 performanceV2) {
        ActionBarCustomView actionBarCustomView = this.t.getToolbarView();
        if (actionBarCustomView != null) {
            actionBarCustomView.a(songbookEntry, performanceV2);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(SongbookEntry songbookEntry, boolean bl, Analytics searchTarget) {
        Log.b(a, "playPreview called");
        MediaPlayerServiceController mediaPlayerServiceController = MediaPlayerServiceController.a();
        if (mediaPlayerServiceController.c(songbookEntry.c()) && this.i != null && this.i.isAdded()) {
            mediaPlayerServiceController.b();
            return;
        }
        this.j = this.getFragmentManager().beginTransaction();
        this.j.setCustomAnimations(2131034130, 2131034119, 2131034130, 2131034119);
        if (!bl) {
            this.af();
        }
        this.i = PreviewFragment.a(songbookEntry, bl, this.M(), searchTarget);
        if (this.i.isAdded()) {
            this.j.show((Fragment)this.i);
        } else {
            this.j.add(2131755945, (Fragment)this.i, "PREVIEW_FRAGMENT");
        }
        this.j.commitAllowingStateLoss();
        this.j = null;
        this.J();
    }

    @Override
    public void a(BaseFragment menuToggleAction) {
        if (this instanceof MasterActivity) {
            ((MasterActivity)this).a(menuToggleAction);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(BaseFragment baseFragment) {
        String string2 = baseFragment.x() != null ? baseFragment.x() : baseFragment.getClass().getName();
        this.a(baseFragment, string2);
    }

    @Override
    public void a(BaseFragment baseFragment, String string2) {
        this.a(baseFragment, string2, 0, 0);
    }

    public void a(BaseFragment baseFragment, String string2, int n, int n2) {
        this.a(baseFragment, string2, n, n2, n, n2, false, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(BaseFragment baseFragment, String string2, int n, int n2, int n3, int n4, boolean bl, boolean bl2) {
        boolean bl3 = false;
        if (this.j()) {
            return;
        }
        Log.b(a, "showFragment - called with fragment with tag: " + string2);
        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (baseFragment.isAdded()) {
            fragmentTransaction.show((Fragment)baseFragment);
        } else {
            boolean bl4 = bl3;
            if (n != 0) {
                bl4 = bl3;
                if (n2 != 0) {
                    if (bl) {
                        fragmentTransaction.setCustomAnimations(n, n2, n3, n4);
                    } else {
                        fragmentTransaction.setCustomAnimations(n, n2, 0, 0);
                    }
                    bl4 = bl3;
                    if (!bl2) {
                        this.H();
                        fragmentTransaction.add(2131755458, (Fragment)baseFragment, string2);
                        bl4 = true;
                    }
                }
            }
            if (!bl4) {
                Fragment fragment = fragmentManager.findFragmentByTag(string2);
                if (fragment != null && fragment.isVisible()) {
                    fragmentManager.popBackStack();
                }
                fragmentTransaction.replace(2131755458, (Fragment)baseFragment, string2);
            }
        }
        fragmentTransaction.addToBackStack(string2);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void a(MasterToolbar.FadeMode fadeMode) {
        this.t.setFadeMode(fadeMode);
        this.t.b(0, true);
    }

    public void a( onPopNowPlayingFragmentListener) {
        this.y = onPopNowPlayingFragmentListener;
    }

    @Override
    public void a(NowPlayingFragment nowPlayingFragment) {
        Log.b(a, "popNowPlayingFragment called");
        this.ag();
    }

    public void a(CharSequence charSequence, CharSequence charSequence2, int n) {
        ActionBarCustomView actionBarCustomView = this.t.getToolbarView();
        if (actionBarCustomView != null) {
            actionBarCustomView.a(charSequence, charSequence2);
            actionBarCustomView.setTitleCompoundDrawable(n);
        }
    }

    @Override
    public void a(String string2) {
        Log.b(a, "popBackStackByFragmentTag - called with fragment with tag: " + string2);
        FragmentManager fragmentManager = this.getFragmentManager();
        int n = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < n; ++i) {
            String string3 = fragmentManager.getBackStackEntryAt(i).getName();
            if (string3 == null || !string3.equals(string2)) continue;
            while (n > i) {
                fragmentManager.popBackStack();
                --n;
            }
            break;
        }
    }

    @Override
    public void a(@NonNull List<MediaPlayingPlayable> list, int n) {
        this.i().a(list, n);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(boolean bl, boolean bl2, Hashtag.HashtagCallback object) {
        FragmentManager fragmentManager = this.getFragmentManager();
        NowPlayingFragment nowPlayingFragment = (NowPlayingFragment)fragmentManager.findFragmentByTag("NOW_PLAYING_FRAGMENT");
        if (nowPlayingFragment != null) {
            nowPlayingFragment.a(bl, bl2, (Hashtag.HashtagCallback)object);
        } else if (object != null) {
            object.a();
        }
        if ((object = (PreviewFragment)fragmentManager.findFragmentByTag("PREVIEW_FRAGMENT")) != null) {
            object.W();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected boolean a(PerformanceV2 object) {
        if (object.removalCode == 0) {
            return false;
        }
        MediaPlayerServiceController.a().b(object.performanceKey);
        int n = object.removalCode;
        object = object.o() ? new Runnable(this, (PerformanceV2)object){
            final /* synthetic */ PerformanceV2 a;
            final /* synthetic */ MediaPlayingActivity b;
            {
                this.b = mediaPlayingActivity;
                this.a = performanceV2;
            }

            public void run() {
                com.smule.singandroid.utils.NavigationUtils.a((Activity)this.b, (PerformanceV2)this.a, (Runnable)null, (Runnable)null, (Runnable)null, (boolean)true);
            }
        } : null;
        this.a(n, true, null, (Runnable)object);
        return true;
    }

    @Override
    public void a_(Intent intent) {
        Log.b(a, "popFragmentAndPassIntentToNowPlayingFragment called");
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        if (intent.hasExtra("CHANGE_MADE_PERFORMANCE")) {
            hashMap.put("UPDATED_PERFORMANCE", (Object)intent.getParcelableExtra("CHANGE_MADE_PERFORMANCE"));
        }
        hashMap.put("INVITED_FOLLOWERS", intent.getBooleanExtra("CHANGE_MADE_INVITED_FOLLOWERS", false));
        NotificationCenter.a().a("PERFORMANCE_UPDATED_NOTIFICATION", (Object)hashMap);
        new Handler().postDelayed(new Runnable(this, intent){
            final /* synthetic */ Intent a;
            final /* synthetic */ MediaPlayingActivity b;
            {
                this.b = mediaPlayingActivity;
                this.a = intent;
            }

            public void run() {
                Object object = this.b.getFragmentManager();
                object.popBackStack();
                object = (NowPlayingFragment)object.findFragmentByTag("NOW_PLAYING_FRAGMENT");
                if (object != null) {
                    object.onActivityResult(6800, -1, this.a);
                }
            }
        }, 100);
    }

    public void aa() {
        if (this.g && this.w.getVisibility() != 0 && (this.h != null || this.i != null) && this.x.getVisibility() == 0 && this.h != null) {
            this.w.setVisibility(0);
            this.g = false;
            this.getSharedPreferences(MediaPlayingActivity.class.getName(), 0).edit().putBoolean("SHOW_NOW_PLAYING_TOOL_TIP", false).apply();
        }
    }

    public View ab() {
        return this.x;
    }

    public RelativeLayout ac() {
        return this.w;
    }

    public NowPlayingFragment ad() {
        return this.h;
    }

    public PreviewFragment ae() {
        return this.i;
    }

    protected void af() {
        this.c(true);
    }

    protected void ag() {
        this.d(true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void ah() {
        if (this.i != null) {
            PreviewFragment previewFragment = this.i;
            this.i = null;
            FragmentManager fragmentManager = this.getFragmentManager();
            if (this.j == null) {
                fragmentManager = fragmentManager.beginTransaction();
                fragmentManager.remove((Fragment)previewFragment);
                fragmentManager.commitAllowingStateLoss();
            } else {
                this.j.remove((Fragment)previewFragment);
            }
            if (MediaPlayerServiceController.a().c(previewFragment.S())) {
                MediaPlayerServiceController.a().e();
            }
        }
        this.w();
        this.J();
    }

    public V3BillingHelper ai() {
        return this.n;
    }

    public boolean aj() {
        return this.a((MediaPlayingFragment)this.ad());
    }

    public boolean ak() {
        return this.a(this.ae());
    }

    public List<CallbackManager> al() {
        return this.k;
    }

    public MediaPlayingMenuManager am() {
        return this.l;
    }

    protected MediaPlayingFragment an() {
        if (this.ad() != null) {
            return this.ad();
        }
        if (this.ae() != null) {
            return this.ae();
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int ao() {
        int n = this.getResources().getDisplayMetrics().widthPixels * 7;
        int n2 = n / 18;
        if (n % 18 == 0) {
            n = 0;
            do {
                return n + n2;
                break;
            } while (true);
        }
        n = 1;
        return n + n2;
    }

    public int ap() {
        if (this.t.getFadeMode() == MasterToolbar.FadeMode.c) {
            return (int)(this.getResources().getDimension(2131427385) + this.getResources().getDimension(2131428169));
        }
        return this.ao();
    }

    public void aq() {
        this.m_();
        MediaPlayerServiceController.a().p();
    }

    public void ar() {
        MediaPlayerServiceController mediaPlayerServiceController = MediaPlayerServiceController.a();
        if (mediaPlayerServiceController.j() || mediaPlayerServiceController.n() || mediaPlayerServiceController.l()) {
            MediaPlayerServiceController.a().c();
        }
    }

    @Override
    public void b(BaseFragment baseFragment) {
        Log.b(a, "popFragment - called for fragment with tag: " + baseFragment.getTag());
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.remove((Fragment)baseFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected int c(int n) {
        if (n <= 0) {
            return 0;
        }
        FragmentManager fragmentManager = this.getFragmentManager();
        if (n > fragmentManager.getBackStackEntryCount()) {
            Log.e(a, "popBackSkippableFragments - count of remaining frags exceeds actual count of back stack entries");
            return n;
        }
        int n2 = n - 1;
        do {
            int n3 = n;
            if (n2 < 0) return n3;
            Fragment fragment = fragmentManager.findFragmentByTag(fragmentManager.getBackStackEntryAt(n2).getName());
            n3 = n;
            if (fragment == null) return n3;
            n3 = n;
            if (!(fragment instanceof BaseFragment)) return n3;
            n3 = n--;
            if (!((BaseFragment)fragment).x_()) return n3;
            ((BaseFragment)fragment).a_(true);
            fragmentManager.popBackStack();
            --n2;
        } while (true);
    }

    public void c(boolean bl) {
        this.d(bl);
        this.ah();
        if (this.j == null) {
            this.getFragmentManager().executePendingTransactions();
        }
        this.J();
    }

    protected boolean c(BaseFragment baseFragment) {
        return false;
    }

    public void d(int n) {
        MediaPlayingFragment mediaPlayingFragment = this.an();
        if (mediaPlayingFragment == null) {
            return;
        }
        mediaPlayingFragment.h(n);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void d(boolean bl) {
        if (this.h != null) {
            NowPlayingFragment nowPlayingFragment = this.h;
            if (this.y != null && nowPlayingFragment.S() != null) {
                this.y.a(nowPlayingFragment.S().performanceKey);
            }
            this.h = null;
            FragmentManager fragmentManager = this.getFragmentManager();
            if (this.j == null) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentManager = fragmentTransaction;
                if (bl) {
                    fragmentTransaction.setCustomAnimations(2131034128, 2131034117, 2131034128, 2131034117);
                    fragmentManager = fragmentTransaction;
                }
            } else {
                fragmentManager = this.j;
            }
            fragmentManager.remove((Fragment)nowPlayingFragment);
            if (this.j == null) {
                fragmentManager.commitAllowingStateLoss();
            }
            if (MediaPlayerServiceController.a().c(nowPlayingFragment.T()) || MediaPlayerServiceController.a().d(nowPlayingFragment.T())) {
                MediaPlayerServiceController.a().e();
            }
            NotificationCenter.a().b("NOW_PLAYING_FRAGMENT_POPPED_NOTIFICATION", new Object[0]);
            this.a(BaseFragment.c);
        }
        this.w();
        this.J();
    }

    public void e(int n) {
        MediaPlayingFragment mediaPlayingFragment = this.an();
        if (mediaPlayingFragment == null) {
            return;
        }
        mediaPlayingFragment.g(n);
    }

    public void e(boolean bl) {
        Log.b(a, "hideNowPlayingAndPreviewFragments called");
        this.d(bl);
        this.ah();
        this.J();
    }

    @Override
    public CallbackManager h() {
        Log.b(a, "getFacebookCallbackManager called");
        CallbackManager callbackManager = CallbackManager.Factory.create();
        this.k.add(callbackManager);
        return callbackManager;
    }

    @Override
    public PlaybackPresenter i() {
        if (this.m == null) {
            this.m = new MediaPlayingPlaybackPresenter(this);
        }
        return this.m;
    }

    @Override
    public void m_() {
        this.e(true);
    }

    @Override
    public V3BillingHelper n_() {
        Log.b(a, "getNewV3BillingHelper called");
        if (this.n != null) {
            Log.e(a, "V3BillingHelper not yet destroyed!!!");
        }
        this.n = new V3BillingHelper();
        return this.n;
    }

    @Override
    public void o_() {
        Log.b(a, "destroyV3BillingHelper called");
        if (this.n != null) {
            this.n.c();
            this.n = null;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onBackPressed() {
        Log.b(a, "onBackPressed - begin");
        this.t.setLightIcons(false);
        if (SingApplication.h.booleanValue() && new Random().nextInt(Integer.MAX_VALUE) % 2 != 0) return;
        FragmentManager fragmentManager = this.getFragmentManager();
        int n = fragmentManager.getBackStackEntryCount();
        Log.b(a, "   onBackPressed - backStackEntryCount is: " + n);
        if (this.F()) return;
        if (this.ac().getVisibility() == 0) {
            this.w();
            return;
        }
        BaseFragment baseFragment = this.Z();
        if (baseFragment == null) {
            Log.e(a, "onBackPressed: backstack was empty");
            Log.b(a, "onBackPressed - nothing else happened so calling our super");
            super.onBackPressed();
            return;
        }
        if (baseFragment.d()) return;
        if (n > 1) {
            fragmentManager.popBackStack();
            if (this.c(n - 1) != 0) return;
            {
                this.finish();
                return;
            }
        }
        if (this.c(baseFragment)) {
            return;
        }
        this.finish();
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.h = (NowPlayingFragment)this.getFragmentManager().getFragment(bundle, "NOW_PLAYING_FRAGMENT");
        }
        this.g = SingApplication.g().getSharedPreferences(MediaPlayingActivity.class.getName(), 0).getBoolean("SHOW_NOW_PLAYING_TOOL_TIP", true);
        this.l = new MediaPlayingMenuManager(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.h = null;
        this.i = null;
        this.n = null;
        this.l = null;
    }

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

    @Override
    protected void onResume() {
        super.onResume();
        this.am().a();
    }

    public void w() {
        if (this.w != null) {
            this.w.setVisibility(8);
        }
    }

}

