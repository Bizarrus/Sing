package com.smule.singandroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.RelativeLayout;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.smule.android.logging.Analytics.SearchTarget;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.NotificationCenter;
import com.smule.singandroid.BaseFragment$BaseFragmentResponder.MenuToggleAction;
import com.smule.singandroid.bookmark.BookmarkManager;
import com.smule.singandroid.customviews.MasterToolbar;
import com.smule.singandroid.fragments.NowPlayingFragment;
import com.smule.singandroid.fragments.NowPlayingFragment.NowPlayingFragmentMenuSelectedCallback;
import com.smule.singandroid.hashtag.Hashtag.HashtagCallback;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.purchases.V3BillingHelper;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer.ActionBarHighlightMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MediaPlayingActivity extends BaseActivity implements BaseFragment$BaseFragmentResponder {
    private View f7042g;
    private RelativeLayout f7043h;
    private boolean f7044i;
    private volatile NowPlayingFragment f7045j;
    private volatile PreviewFragment f7046k;
    private FragmentTransaction f7047l;
    private List<CallbackManager> f7048m = new ArrayList();
    private BookmarkManager f7049n;
    private V3BillingHelper f7050o = new V3BillingHelper();
    protected OnPopNowPlayingFragmentListener f7051u;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.f7045j = (NowPlayingFragment) getFragmentManager().getFragment(bundle, "NOW_PLAYING_FRAGMENT");
        }
        this.f7044i = SingApplication.m8798f().getSharedPreferences(MediaPlayingActivity.class.getName(), 0).getBoolean("SHOW_NOW_PLAYING_TOOL_TIP", true);
        this.f7049n = new BookmarkManager(this);
    }

    protected void onResume() {
        super.onResume();
        ad().a();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f7045j = null;
        this.f7046k = null;
        this.f7050o = null;
        this.f7049n = null;
    }

    public MasterToolbar mo4087B() {
        return null;
    }

    public void mo4097a(CharSequence charSequence, CharSequence charSequence2, int i) {
    }

    public void mo4095a(SongbookEntry songbookEntry, PerformanceV2 performanceV2) {
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        FragmentManager fragmentManager = getFragmentManager();
        PreviewFragment previewFragment = (PreviewFragment) fragmentManager.findFragmentByTag("PREVIEW_FRAGMENT");
        NowPlayingFragment nowPlayingFragment = (NowPlayingFragment) fragmentManager.findFragmentByTag("NOW_PLAYING_FRAGMENT");
        if (previewFragment != null && nowPlayingFragment != null && i == 4 && previewFragment.a(i, keyEvent)) {
            nowPlayingFragment.N();
            return true;
        } else if (previewFragment != null && previewFragment.a(i, keyEvent)) {
            return true;
        } else {
            if (nowPlayingFragment != null && nowPlayingFragment.a(i, keyEvent)) {
                return true;
            }
            BaseFragment Q = m8684Q();
            if (Q == null || !Q.a(i, keyEvent)) {
                return super.onKeyDown(i, keyEvent);
            }
            return true;
        }
    }

    public void onBackPressed() {
        Log.m7770b(a, "onBackPressed - begin");
        if (!SingApplication.f7092g.booleanValue() || new Random().nextInt(Integer.MAX_VALUE) % 2 == 0) {
            FragmentManager fragmentManager = getFragmentManager();
            int backStackEntryCount = fragmentManager.getBackStackEntryCount();
            Log.m7770b(a, "onBackPressed - backStackEntryCount is: " + backStackEntryCount);
            if (!mo4088E()) {
                if (m8687T().getVisibility() == 0) {
                    mo4103u();
                    return;
                }
                BaseFragment Q = m8684Q();
                if (Q == null) {
                    Log.m7776e(a, "onBackPressed: backstack was empty");
                    Log.m7770b(a, "onBackPressed - nothing else happened so calling our super");
                    super.onBackPressed();
                } else if (!Q.c()) {
                    if (backStackEntryCount > 1) {
                        fragmentManager.popBackStack();
                    } else if (!mo4098b(Q)) {
                        finish();
                    }
                }
            }
        }
    }

    protected boolean mo4088E() {
        return false;
    }

    protected boolean mo4098b(BaseFragment baseFragment) {
        return false;
    }

    protected BaseFragment m8684Q() {
        FragmentManager fragmentManager = getFragmentManager();
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        if (backStackEntryCount > 0) {
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag(fragmentManager.getBackStackEntryAt(backStackEntryCount - 1).getName());
            if (findFragmentByTag instanceof BaseFragment) {
                return (BaseFragment) findFragmentByTag;
            }
        }
        return null;
    }

    protected void m8697a(View view, RelativeLayout relativeLayout) {
        this.f7042g = view;
        this.f7043h = relativeLayout;
    }

    public void m8685R() {
        if (this.f7044i && this.f7043h.getVisibility() != 0) {
            if ((this.f7045j != null || this.f7046k != null) && this.f7042g.getVisibility() == 0 && this.f7045j != null) {
                this.f7043h.setVisibility(0);
                this.f7044i = false;
                getSharedPreferences(MediaPlayingActivity.class.getName(), 0).edit().putBoolean("SHOW_NOW_PLAYING_TOOL_TIP", false).apply();
            }
        }
    }

    public View m8686S() {
        return this.f7042g;
    }

    public RelativeLayout m8687T() {
        return this.f7043h;
    }

    public NowPlayingFragment m8688U() {
        return this.f7045j;
    }

    public PreviewFragment m8689V() {
        return this.f7046k;
    }

    public void mo4103u() {
        if (this.f7043h != null) {
            this.f7043h.setVisibility(8);
        }
    }

    protected void m8690W() {
        m8717c(true);
    }

    public void m8717c(boolean z) {
        m8718d(z);
        m8692Y();
        if (this.f7047l == null) {
            getFragmentManager().executePendingTransactions();
        }
        mo4092I();
    }

    protected void m8691X() {
        m8718d(true);
    }

    public void m8708a(OnPopNowPlayingFragmentListener onPopNowPlayingFragmentListener) {
        this.f7051u = onPopNowPlayingFragmentListener;
    }

    protected void m8718d(boolean z) {
        if (this.f7045j != null) {
            FragmentTransaction beginTransaction;
            Fragment fragment = this.f7045j;
            if (!(this.f7051u == null || fragment.K() == null)) {
                this.f7051u.a(fragment.K().performanceKey);
            }
            this.f7045j = null;
            FragmentManager fragmentManager = getFragmentManager();
            if (this.f7047l == null) {
                beginTransaction = fragmentManager.beginTransaction();
                if (z) {
                    beginTransaction.setCustomAnimations(C1947R.animator.slide_up, C1947R.animator.slide_down, C1947R.animator.slide_up, C1947R.animator.slide_down);
                }
            } else {
                beginTransaction = this.f7047l;
            }
            beginTransaction.remove(fragment);
            if (this.f7047l == null) {
                beginTransaction.commitAllowingStateLoss();
            }
            if (MediaPlayerServiceController.a().c(fragment.L()) || MediaPlayerServiceController.a().d(fragment.L())) {
                MediaPlayerServiceController.a().e();
            }
            NotificationCenter.a().b("NOW_PLAYING_FRAGMENT_POPPED_NOTIFICATION", new Object[0]);
            mo4078a(MenuToggleAction.c);
        }
        mo4103u();
        mo4092I();
    }

    public void m8692Y() {
        if (this.f7046k != null) {
            Fragment fragment = this.f7046k;
            this.f7046k = null;
            FragmentManager fragmentManager = getFragmentManager();
            if (this.f7047l == null) {
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.remove(fragment);
                beginTransaction.commitAllowingStateLoss();
            } else {
                this.f7047l.remove(fragment);
            }
            if (MediaPlayerServiceController.a().c(fragment.K())) {
                MediaPlayerServiceController.a().e();
            }
        }
        mo4103u();
        mo4092I();
    }

    public BaseFragment mo4089F() {
        return (BaseFragment) getFragmentManager().findFragmentById(C1947R.id.fragment_content_view);
    }

    public void mo4091H() {
        BaseFragment F = mo4089F();
        if (F != null) {
            F.u();
        }
    }

    public void mo4090G() {
        BaseFragment F = mo4089F();
        if (F != null) {
            F.t();
        }
    }

    public void m8716c(BaseFragment baseFragment) {
        mo4080a(baseFragment, baseFragment.s() != null ? baseFragment.s() : baseFragment.getClass().getName());
    }

    public void mo4080a(BaseFragment baseFragment, String str) {
        m8706a(baseFragment, str, 0, 0);
    }

    public void m8706a(BaseFragment baseFragment, String str, int i, int i2) {
        m8707a(baseFragment, str, i, i2, i, i2, false, false);
    }

    public void m8707a(BaseFragment baseFragment, String str, int i, int i2, int i3, int i4, boolean z, boolean z2) {
        int i5 = 0;
        if (!isFinishing()) {
            Log.m7770b(a, "showFragment - called with fragment with tag: " + str);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (baseFragment.isAdded()) {
                beginTransaction.show(baseFragment);
            } else {
                if (!(i == 0 || i2 == 0)) {
                    if (z) {
                        beginTransaction.setCustomAnimations(i, i2, i3, i4);
                    } else {
                        beginTransaction.setCustomAnimations(i, i2, 0, 0);
                    }
                    if (!z2) {
                        mo4090G();
                        beginTransaction.add(C1947R.id.fragment_content_view, baseFragment, str);
                        i5 = 1;
                    }
                }
                if (i5 == 0) {
                    Fragment findFragmentByTag = fragmentManager.findFragmentByTag(str);
                    if (findFragmentByTag != null && findFragmentByTag.isVisible()) {
                        fragmentManager.popBackStack();
                    }
                    beginTransaction.replace(C1947R.id.fragment_content_view, baseFragment, str);
                }
            }
            beginTransaction.addToBackStack(str);
            beginTransaction.commitAllowingStateLoss();
        }
    }

    public void mo4079a(BaseFragment baseFragment) {
        Log.m7770b(a, "popFragment - called for fragment with tag: " + baseFragment.getTag());
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.remove(baseFragment);
        beginTransaction.commitAllowingStateLoss();
    }

    public void mo4082a(String str) {
        Log.m7770b(a, "popBackStackByFragmentTag - called with fragment with tag: " + str);
        FragmentManager fragmentManager = getFragmentManager();
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        int i = 0;
        while (i < backStackEntryCount) {
            String name = fragmentManager.getBackStackEntryAt(i).getName();
            if (name == null || !name.equals(str)) {
                i++;
            } else {
                while (backStackEntryCount > i) {
                    fragmentManager.popBackStack();
                    backStackEntryCount--;
                }
                return;
            }
        }
    }

    public void mo4077a(SongbookEntry songbookEntry, boolean z, SearchTarget searchTarget) {
        Log.m7770b(a, "playPreview called");
        MediaPlayerServiceController a = MediaPlayerServiceController.a();
        if (a.c(songbookEntry.c()) && this.f7046k != null && this.f7046k.isAdded()) {
            a.b();
            return;
        }
        this.f7047l = getFragmentManager().beginTransaction();
        this.f7047l.setCustomAnimations(C1947R.animator.slide_up, C1947R.animator.slide_down, C1947R.animator.slide_up, C1947R.animator.slide_down);
        if (!z) {
            m8690W();
        }
        this.f7046k = PreviewFragment.a(songbookEntry, z, mo4093L(), searchTarget);
        if (this.f7046k.isAdded()) {
            this.f7047l.show(this.f7046k);
        } else {
            this.f7047l.add(C1947R.id.now_playing_bar_layout, this.f7046k, "PREVIEW_FRAGMENT");
        }
        this.f7047l.commitAllowingStateLoss();
        this.f7047l = null;
        mo4092I();
    }

    public void mo4076a(PerformanceV2 performanceV2, boolean z, boolean z2) {
        Log.m7770b(a, "showNowPlayingBarForPerformance called");
        m8699a(performanceV2, z, z2, null);
    }

    public void m8699a(PerformanceV2 performanceV2, boolean z, boolean z2, Runnable runnable) {
        m8700a(performanceV2, z, z2, runnable, Long.valueOf(-1), null, false, 0, null);
    }

    public void m8700a(PerformanceV2 performanceV2, boolean z, boolean z2, Runnable runnable, Long l, SearchTarget searchTarget, boolean z3, int i, NowPlayingFragmentMenuSelectedCallback nowPlayingFragmentMenuSelectedCallback) {
        if (!m8634f()) {
            return;
        }
        if (performanceV2.removalCode != 0) {
            MediaPlayerServiceController.a().b(performanceV2.performanceKey);
            m8622a(performanceV2.removalCode, true, null, performanceV2.m8279m() ? new 1(this, performanceV2) : null);
            return;
        }
        MediaPlayerServiceController a = MediaPlayerServiceController.a();
        if (a.c(performanceV2.performanceKey) && this.f7045j != null && this.f7045j.isAdded()) {
            a.b();
            return;
        }
        if (z) {
            MiscUtils.a(this, true);
        }
        this.f7047l = getFragmentManager().beginTransaction();
        this.f7047l.setCustomAnimations(C1947R.animator.slide_up, C1947R.animator.slide_down, C1947R.animator.slide_up, C1947R.animator.slide_down);
        m8690W();
        this.f7045j = NowPlayingFragment.a(performanceV2, z2, mo4093L(), searchTarget, z3, i, nowPlayingFragmentMenuSelectedCallback);
        this.f7045j.a(l);
        m8675a(z, runnable);
        this.f7047l.commitAllowingStateLoss();
        this.f7047l = null;
    }

    private void m8675a(boolean z, Runnable runnable) {
        FragmentTransaction beginTransaction;
        if (this.f7047l == null) {
            beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.setCustomAnimations(C1947R.animator.slide_up, C1947R.animator.slide_down, C1947R.animator.slide_up, C1947R.animator.slide_down);
        } else {
            beginTransaction = this.f7047l;
        }
        if (this.f7045j.isAdded()) {
            beginTransaction.show(this.f7045j);
        } else {
            beginTransaction.replace(C1947R.id.now_playing_bar_layout, this.f7045j, "NOW_PLAYING_FRAGMENT");
        }
        if (this.f7047l == null) {
            beginTransaction.commitAllowingStateLoss();
        }
        mo4092I();
        if (z) {
            new Handler().postDelayed(new 2(this, runnable), 100);
        }
        m8685R();
    }

    public void mo4081a(NowPlayingFragment nowPlayingFragment) {
        Log.m7770b(a, "popNowPlayingFragment called");
        m8691X();
    }

    public void a_(Intent intent) {
        Log.m7770b(a, "popFragmentAndPassIntentToNowPlayingFragment called");
        Map hashMap = new HashMap();
        if (intent.hasExtra("CHANGE_MADE_PERFORMANCE")) {
            hashMap.put("UPDATED_PERFORMANCE", intent.getParcelableExtra("CHANGE_MADE_PERFORMANCE"));
        }
        hashMap.put("INVITED_FOLLOWERS", Boolean.valueOf(intent.getBooleanExtra("CHANGE_MADE_INVITED_FOLLOWERS", false)));
        NotificationCenter.a().a("PERFORMANCE_UPDATED_NOTIFICATION", hashMap);
        new Handler().postDelayed(new 3(this, intent), 100);
    }

    public void mo4075a() {
        m8719e(true);
    }

    public void m8719e(boolean z) {
        Log.m7770b(a, "hideNowPlayingAndPreviewFragments called");
        m8718d(z);
        m8692Y();
        mo4092I();
    }

    public void m8712a(boolean z, boolean z2, HashtagCallback hashtagCallback) {
        FragmentManager fragmentManager = getFragmentManager();
        NowPlayingFragment nowPlayingFragment = (NowPlayingFragment) fragmentManager.findFragmentByTag("NOW_PLAYING_FRAGMENT");
        if (nowPlayingFragment != null) {
            nowPlayingFragment.a(z, z2, hashtagCallback);
        } else if (hashtagCallback != null) {
            hashtagCallback.a();
        }
        PreviewFragment previewFragment = (PreviewFragment) fragmentManager.findFragmentByTag("PREVIEW_FRAGMENT");
        if (previewFragment != null) {
            previewFragment.O();
        }
    }

    public V3BillingHelper m8693Z() {
        return this.f7050o;
    }

    public V3BillingHelper mo4084b() {
        Log.m7770b(a, "getNewV3BillingHelper called");
        if (this.f7050o != null) {
            Log.m7776e(a, "V3BillingHelper not yet destroyed!!!");
        }
        this.f7050o = new V3BillingHelper();
        return this.f7050o;
    }

    public void m_() {
        Log.m7770b(a, "destroyV3BillingHelper called");
        if (this.f7050o != null) {
            this.f7050o.m8874c();
            this.f7050o = null;
        }
    }

    public QuickReturnListViewMenuSyncer mo4074a(AbsListView absListView, ActionBarHighlightMode actionBarHighlightMode, OnScrollListener onScrollListener) {
        return null;
    }

    public void mo4078a(MenuToggleAction menuToggleAction) {
        if (this instanceof MasterActivity) {
            ((MasterActivity) this).mo4078a(menuToggleAction);
        }
    }

    public CallbackManager n_() {
        Log.m7770b(a, "getFacebookCallbackManager called");
        CallbackManager create = Factory.create();
        this.f7048m.add(create);
        return create;
    }

    private boolean m8676a(MediaPlayingFragment mediaPlayingFragment) {
        return (mediaPlayingFragment == null || !mediaPlayingFragment.isAdded() || mediaPlayingFragment.isDetached() || mediaPlayingFragment.isRemoving()) ? false : true;
    }

    public boolean aa() {
        return m8676a(m8688U());
    }

    public boolean ab() {
        return m8676a(m8689V());
    }

    public boolean mo4093L() {
        return false;
    }

    public void mo4092I() {
    }

    public List<CallbackManager> ac() {
        return this.f7048m;
    }

    public BookmarkManager ad() {
        return this.f7049n;
    }

    protected MediaPlayingFragment ae() {
        if (m8688U() != null) {
            return m8688U();
        }
        if (m8689V() != null) {
            return m8689V();
        }
        return null;
    }

    public void m8696a(int i) {
        MediaPlayingFragment ae = ae();
        if (ae != null) {
            ae.h(i);
        }
    }

    public int af() {
        int i = getResources().getDisplayMetrics().widthPixels * 7;
        return (i % 18 == 0 ? 0 : 1) + (i / 18);
    }

    public void m8714b(int i) {
        MediaPlayingFragment ae = ae();
        if (ae != null) {
            ae.g(i);
        }
    }

    public void ag() {
        mo4075a();
        MediaPlayerServiceController.a().p();
    }

    public void ah() {
        MediaPlayerServiceController a = MediaPlayerServiceController.a();
        if (a.j() || a.n() || a.l()) {
            MediaPlayerServiceController.a().c();
        }
    }
}
