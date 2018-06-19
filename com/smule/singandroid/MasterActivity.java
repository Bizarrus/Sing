package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentManager.BackStackEntry;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.facebook.CallbackManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.smule.android.ads.AdVendorManager;
import com.smule.android.ads.networks.AdVendor.AdType;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.ArrangementManager;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PromotionManager;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.ListingV2;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.ListingEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.twitter.MagicTwitter;
import com.smule.android.utils.ThreadUtils;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.UiHandler;
import com.smule.android.utils.WeakListener;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatManagerListener;
import com.smule.singandroid.BaseFragment$BaseFragmentResponder.MenuToggleAction;
import com.smule.singandroid.NotificationsFragment.Tabs;
import com.smule.singandroid.SingBundle.Builder;
import com.smule.singandroid.SingBundle.PerformanceType;
import com.smule.singandroid.ads.AdVendorManagerConfig;
import com.smule.singandroid.chat.ChatFragment;
import com.smule.singandroid.chat.MessageCenterFragment;
import com.smule.singandroid.customviews.ActionBarCustomView;
import com.smule.singandroid.customviews.BottomNavView;
import com.smule.singandroid.customviews.BottomNavView$OnTabChangedListener;
import com.smule.singandroid.customviews.BottomNavView.Tab;
import com.smule.singandroid.customviews.MasterToolbar;
import com.smule.singandroid.dialogs.ChatTooltipDialog;
import com.smule.singandroid.fragments.NowPlayingFragment;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.models.SongbookListViewState;
import com.smule.singandroid.models.SongbookSection;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;
import com.smule.singandroid.runtimepermissions.SingPermissionRequests;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.DeepLink;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer.ActionBarHighlightMode;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity
public class MasterActivity extends MediaPlayingActivity implements MediaPlayingFragment$MediaPlayingFragmentResponder, BottomNavView$OnTabChangedListener {
    public static final String f7052g = MasterActivity.class.getName();
    private final Map<String, BaseFragment> f7053A = new HashMap();
    private int f7054B;
    private int f7055C;
    private OnClickListener f7056D;
    private OnClickListener f7057E;
    private ChatTooltipDialog f7058F;
    @ViewById
    protected ViewGroup f7059h;
    @ViewById
    protected ProgressBar f7060i;
    @ViewById
    protected MasterToolbar f7061j;
    @ViewById
    protected BottomNavView f7062k;
    @ViewById
    protected View f7063l;
    @ViewById
    protected RelativeLayout f7064m;
    @InstanceState
    protected Integer f7065n;
    @ViewById
    protected RelativeLayout f7066o;
    @InstanceState
    protected HashMap<String, SongbookListViewState> f7067p = new HashMap();
    @InstanceState
    protected String f7068q;
    @InstanceState
    protected boolean f7069r;
    @InstanceState
    protected long f7070s;
    OnClickListener f7071t = new 5(this);
    private MediaPlayerServiceController f7072v;
    private Intent f7073w;
    private boolean f7074x;
    private ChatListener f7075y = new 1(this);
    private ChatManagerListener f7076z = new 2(this);

    public void onCreate(Bundle bundle) {
        Intent intent;
        super.onCreate(bundle);
        Log.m7770b(f7052g, "onCreate - savedInstanceState is " + (bundle == null ? "null" : "not null"));
        if (bundle == null) {
            intent = getIntent();
        } else {
            intent = null;
        }
        this.f7073w = intent;
        getFragmentManager().addOnBackStackChangedListener(new 3(this));
        this.f7074x = SingApplication.m8798f().getSharedPreferences(MasterActivity.class.getName(), 0).getBoolean("SHOW_CHAT_TOOL_TIP", true);
        if (!(bundle != null)) {
            SingPermissionRequests.a(this, null);
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (m8688U() != null) {
            getFragmentManager().putFragment(bundle, "NOW_PLAYING_FRAGMENT", m8688U());
        }
    }

    protected void mo4100d() {
        super.mo4100d();
        setSupportActionBar(this.f7061j);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowTitleEnabled(false);
            supportActionBar.setDisplayShowCustomEnabled(true);
            View a = ActionBarCustomView.a(this);
            a.setOnClickListener(this.f7071t);
            this.f7061j.setToolbarView(a);
            supportActionBar.setCustomView(a);
        }
        this.f7062k.setOnTabChangedListener(this);
        m8753O();
        m8739A();
        m8697a(this.f7063l, this.f7064m);
    }

    protected void onStart() {
        super.onStart();
        Log.m7770b(f7052g, "onStart - begin");
        SingAnalytics.p();
        MediaPlayerServiceController.a().a(this);
        if (ChatUtils.m8906a()) {
            ChatManager j = SingApplication.m8802j();
            j.m8564a(this.f7076z);
            j.m8562a(this.f7075y);
        }
        if (!m8770q() && m8684Q() == null && this.f7065n == null) {
            Log.m7770b(f7052g, "onStart - mSelectedMainTab is null so going to Songbook");
            this.f7062k.a(Tab.c, true);
        }
        Log.m7770b(f7052g, "onStart - end");
    }

    protected void onResume() {
        super.onResume();
        mo4092I();
        mo4078a(MenuToggleAction.c);
        this.f7061j.d();
        AdVendorManagerConfig.a(this);
    }

    protected void onStop() {
        super.onStop();
        MediaPlayerServiceController.a().b(this);
        if (ChatUtils.m8906a()) {
            ChatManager j = SingApplication.m8802j();
            j.m8578b(this.f7076z);
            j.m8577b(this.f7075y);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        MediaPlayerServiceController.a().e();
        this.f7053A.clear();
        this.f7072v = null;
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        this.f7053A.clear();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (!intent.hasExtra("com.smule.INTENT_EXTRA_FROM_MEDIA_NOTIFICATION")) {
            this.f7073w = intent;
            if (m8635g()) {
                m8770q();
            }
        } else if (ae() != null) {
            ae().G();
        }
    }

    protected boolean m8770q() {
        boolean z = true;
        Log.m7770b(f7052g, "processIntent: " + this.f7073w);
        if (this.f7073w == null) {
            return false;
        }
        if (this.f7073w.getData() != null) {
            Uri data = this.f7073w.getData();
            Log.m7772c(f7052g, "Starting with URI: " + data);
            try {
                try {
                    m8762a(new DeepLink(data), true);
                } catch (IllegalArgumentException e) {
                    Log.m7770b(f7052g, "No match for URI: " + data);
                    this.f7073w = null;
                    setIntent(new Intent());
                    return z;
                }
            } catch (IllegalArgumentException e2) {
                z = false;
                Log.m7770b(f7052g, "No match for URI: " + data);
                this.f7073w = null;
                setIntent(new Intent());
                return z;
            }
        }
        z = false;
        this.f7073w = null;
        setIntent(new Intent());
        return z;
    }

    public void m8771r() {
        m8736f(false);
    }

    public void m8772s() {
        m8736f(true);
    }

    private void m8736f(boolean z) {
        ThreadUtils.a(new 4(this, z));
    }

    public static Intent m8723a(Context context) {
        return new Intent(context, MasterActivity_.class);
    }

    @UiThread
    public void m8773t() {
        this.f7062k.a(Tab.a, true);
    }

    public SongbookListViewState m8756a(SongbookSection songbookSection) {
        SongbookListViewState songbookListViewState = (SongbookListViewState) this.f7067p.get(songbookSection.c);
        if (songbookListViewState != null) {
            return songbookListViewState;
        }
        songbookListViewState = new SongbookListViewState(songbookSection);
        this.f7067p.put(songbookSection.c, songbookListViewState);
        return songbookListViewState;
    }

    @Click
    public void mo4103u() {
        super.mo4103u();
    }

    public void mo4099c(String str) {
        this.f7068q = str;
    }

    public String m8775v() {
        return this.f7068q;
    }

    private void ai() {
        BaseFragment F = mo4089F();
        boolean h = F.h();
        boolean K = m8749K();
        mo4078a(K ? MenuToggleAction.a : MenuToggleAction.b);
        if (F.g()) {
            MediaPlayerServiceController.a().o();
        } else {
            mo4075a();
            MediaPlayerServiceController.a().p();
        }
        F.w();
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            if (h) {
                if (!supportActionBar.isShowing()) {
                    F.r();
                }
                supportActionBar.show();
                ActionBarCustomView toolbarView = this.f7061j.getToolbarView();
                boolean z = !K && F.i();
                toolbarView.setDisplayUpButton(z);
                this.f7061j.getToolbarView().setEnableUpButton(F.i());
                invalidateOptionsMenu();
                return;
            }
            supportActionBar.hide();
        }
    }

    public void m8758a(Fragment fragment, int i, int i2, OnClickListener onClickListener, OnClickListener onClickListener2) {
        this.f7054B = i;
        this.f7055C = i2;
        this.f7056D = new WeakListener.OnClickListener(fragment, onClickListener);
        this.f7057E = new WeakListener.OnClickListener(fragment, onClickListener2);
        if (this.f7058F != null) {
            this.f7058F.a(i, i2, onClickListener, onClickListener2);
        }
    }

    public void m8776w() {
        if (ChatUtils.m8906a() && this.f7074x && m8687T().getVisibility() != 0) {
            this.f7058F = new ChatTooltipDialog(this, this.f7054B, this.f7055C, this.f7056D, this.f7057E);
            this.f7058F.a(C1947R.string.chat_tooltip_message_center);
            this.f7058F.show();
        }
    }

    public void m8777x() {
        this.f7058F = new ChatTooltipDialog(this, this.f7054B, this.f7055C, this.f7056D, this.f7057E);
        this.f7058F.a(C1947R.string.chat_tooltip_new_message);
        this.f7058F.show();
    }

    public void m8778y() {
        if (this.f7074x) {
            this.f7074x = false;
            if (this.f7058F != null) {
                this.f7058F.dismiss();
            }
            SingApplication.m8798f().getSharedPreferences(MasterActivity.class.getName(), 0).edit().putBoolean("SHOW_CHAT_TOOL_TIP", false).apply();
        }
    }

    public void m8779z() {
        if (this.f7058F != null) {
            this.f7058F.dismiss();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        boolean z = false;
        Log.m7772c(f7052g, "onCreateOptionsMenu - begin - " + m8749K());
        if (m8636h()) {
            return false;
        }
        menu.clear();
        BaseFragment F = mo4089F();
        if (F == null || F.d()) {
            getMenuInflater().inflate(C1947R.menu.action_bar_menu, menu);
            this.f7061j.setPadding(0, 0, 0, 0);
        } else {
            this.f7061j.setPadding(0, 0, (int) getResources().getDimension(C1947R.dimen.margin_medium_small), 0);
        }
        if (F instanceof ProfileFragment) {
            this.f7061j.setPadding(0, 0, 0, 0);
        }
        if (!m8749K()) {
            for (int i = 0; i < menu.size(); i++) {
                menu.getItem(i).setVisible(false);
                menu.getItem(i).setEnabled(false);
            }
            ActionBarCustomView toolbarView = this.f7061j.getToolbarView();
            if (F != null && F.i()) {
                z = true;
            }
            toolbarView.setDisplayUpButton(z);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @UiThread
    public void m8739A() {
        if (m8635g()) {
            this.f7062k.c();
        }
    }

    public void mo4097a(CharSequence charSequence, CharSequence charSequence2, int i) {
        ActionBarCustomView toolbarView = this.f7061j.getToolbarView();
        if (toolbarView != null) {
            toolbarView.a(charSequence, charSequence2);
            toolbarView.setTitleCompoundDrawable(i);
        }
    }

    public void mo4095a(SongbookEntry songbookEntry, PerformanceV2 performanceV2) {
        ActionBarCustomView toolbarView = this.f7061j.getToolbarView();
        if (toolbarView != null) {
            toolbarView.a(songbookEntry, performanceV2);
        }
    }

    public MasterToolbar mo4087B() {
        return this.f7061j;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1947R.id.action_settings:
                mo4080a(SettingsFragment.a(), SettingsFragment.e);
                return true;
            case C1947R.id.action_smule_apps:
                m8716c(WebViewFragment.a(getResources().getString(C1947R.string.smule_apps_url), getResources().getString(C1947R.string.core_smule_apps)));
                return true;
            case C1947R.id.action_help:
                m8716c(WebViewFragment.a(getResources().getString(C1947R.string.sing_android_help_url), getResources().getString(C1947R.string.core_help)));
                return true;
            case C1947R.id.action_search:
                return false;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void m8738g(boolean z) {
        if (m8689V() != null) {
            m8689V().c(z);
        }
        if (m8688U() != null) {
            m8688U().c(z);
        }
    }

    public void m8741C() {
        this.f7062k.setTranslationY(0.0f);
        this.f7062k.setVisibility(0);
        m8687T().setTranslationY((float) (-this.f7062k.getHeight()));
        m8738g(true);
    }

    public void m8742D() {
        this.f7062k.setVisibility(8);
        m8687T().setTranslationY(0.0f);
        m8738g(false);
    }

    private void m8733c(int i) {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > i) {
            fragmentManager.popBackStack(fragmentManager.getBackStackEntryAt(i).getId(), 1);
        }
    }

    public void mo4096a(Tab tab, boolean z) {
        if (m8635g()) {
            FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager.findFragmentByTag(tab.g) != null) {
                fragmentManager.popBackStack(tab.g, 0);
                return;
            }
            if (this.f7058F != null) {
                m8778y();
            }
            Fragment a = m8755a(tab);
            if (z) {
                m8733c(0);
            }
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            beginTransaction.replace(C1947R.id.fragment_content_view, a, tab.g);
            beginTransaction.addToBackStack(tab.g);
            beginTransaction.commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
            aj();
            this.f7065n = Integer.valueOf(tab.f);
        }
    }

    protected BaseFragment m8755a(Tab tab) {
        String str = tab.g;
        if (this.f7053A.containsKey(str)) {
            return (BaseFragment) this.f7053A.get(str);
        }
        BaseFragment a = tab.h.a();
        this.f7053A.put(str, a);
        return a;
    }

    private void aj() {
        m8712a(true, false, null);
    }

    protected boolean mo4088E() {
        if (this.f7058F == null || !this.f7058F.isShowing()) {
            return false;
        }
        if (mo4089F() instanceof MessageCenterFragment) {
            m8779z();
        } else {
            m8778y();
        }
        return true;
    }

    protected boolean mo4098b(BaseFragment baseFragment) {
        if (baseFragment instanceof SongbookFragment) {
            return false;
        }
        m8771r();
        return true;
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

    public void m8764b(BaseFragment baseFragment, String str) {
        FragmentManager fragmentManager = getFragmentManager();
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        int i = 0;
        while (i < backStackEntryCount) {
            BackStackEntry backStackEntryAt = fragmentManager.getBackStackEntryAt(i);
            if (backStackEntryAt.getName() == null || !backStackEntryAt.getName().equals(str)) {
                i++;
            } else {
                while (backStackEntryCount > i + 1) {
                    fragmentManager.popBackStack();
                    backStackEntryCount--;
                }
                return;
            }
        }
        mo4080a(baseFragment, str);
    }

    public BaseFragment mo4089F() {
        return (BaseFragment) getFragmentManager().findFragmentById(C1947R.id.fragment_content_view);
    }

    public void mo4090G() {
        BaseFragment F = mo4089F();
        if (F != null) {
            F.t();
        }
    }

    public void mo4091H() {
        BaseFragment F = mo4089F();
        if (F != null) {
            F.u();
        }
    }

    public void mo4092I() {
        if (this.f7066o == null) {
            Log.m7774d(f7052g, "refreshBottomMarginToFragmentContentView - called, but mFragmentContentView is null. Aborting!");
            return;
        }
        int dimension;
        if (!(m8688U() == null && m8689V() == null) && m8686S().getVisibility() == 0) {
            dimension = (int) getResources().getDimension(C1947R.dimen.row_single_height);
        } else {
            dimension = 0;
        }
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f7066o.getLayoutParams();
        if (marginLayoutParams.bottomMargin != dimension) {
            marginLayoutParams.setMargins(0, 0, 0, dimension);
            this.f7066o.setLayoutParams(marginLayoutParams);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        boolean z;
        Log.m7770b(f7052g, "onActivityResult : " + i + " / " + i2 + ": " + intent);
        boolean z2 = false;
        if (m8693Z() != null) {
            z2 = m8693Z().m8872a(i, i2, intent);
        }
        if (!(z2 || ac() == null || ac().size() <= 0)) {
            z2 = ((CallbackManager) ac().get(ac().size() - 1)).onActivityResult(i, i2, intent);
            Log.m7770b(f7052g, "onActivityResult Facebook callback managers: " + ac().size() + " / " + z2);
            if (z2) {
                ac().remove(ac().size() - 1);
            }
        }
        if (!z2 && i2 == -1 && i == 42405) {
            aj();
            try {
                m8762a(new DeepLink(intent.getData()), true);
            } catch (Throwable e) {
                Log.m7773c(f7052g, "ShareActivity returned an invalid deep link", e);
            }
        }
        if (z2) {
            z = z2;
        } else {
            z = AdVendorManager.a().a(this, i, i2, intent);
        }
        if (!z) {
            super.onActivityResult(i, i2, intent);
        }
    }

    public void m8748J() {
        if (m8688U() != null) {
            m8688U().T();
        }
    }

    public void m8768d(String str) {
        if (m8688U() != null) {
            m8688U().d(str);
        }
    }

    public QuickReturnListViewMenuSyncer mo4074a(AbsListView absListView, ActionBarHighlightMode actionBarHighlightMode, OnScrollListener onScrollListener) {
        OnScrollListener pauseOnScrollListener;
        Log.m7770b(f7052g, "syncMenuWithListView called");
        if (onScrollListener instanceof PauseOnScrollListener) {
            pauseOnScrollListener = new PauseOnScrollListener(ImageLoader.m7591a(), true, true, onScrollListener);
        } else {
            pauseOnScrollListener = onScrollListener;
        }
        return new QuickReturnListViewMenuSyncer(this, absListView, actionBarHighlightMode, pauseOnScrollListener, m8749K() ? this.f7062k : null, m8687T(), this.f7061j);
    }

    protected boolean m8749K() {
        int backStackEntryCount = getFragmentManager().getBackStackEntryCount();
        if (backStackEntryCount == 0) {
            return true;
        }
        if (backStackEntryCount != 1) {
            return false;
        }
        BaseFragment F = mo4089F();
        if ((F instanceof ProfileFragment) && !((ProfileFragment) F).H()) {
            return false;
        }
        boolean z = F == null || F.j();
        return z;
    }

    public void mo4078a(MenuToggleAction menuToggleAction) {
        Log.m7770b(f7052g, "toggleBottomMenu:" + menuToggleAction);
        switch (14.a[menuToggleAction.ordinal()]) {
            case 1:
                m8741C();
                return;
            case 2:
                m8742D();
                return;
            case 3:
                if (mo4094M()) {
                    m8741C();
                    return;
                } else {
                    m8742D();
                    return;
                }
            default:
                return;
        }
    }

    public boolean mo4093L() {
        if (this.f7062k == null) {
            return false;
        }
        int height = this.f7062k.getHeight();
        int translationY = (int) this.f7062k.getTranslationY();
        if (this.f7062k.getVisibility() != 0 || translationY >= height) {
            return false;
        }
        return true;
    }

    public boolean mo4094M() {
        boolean z;
        if ((m8688U() == null || !m8688U().F()) && (m8689V() == null || !m8689V().F())) {
            z = false;
        } else {
            z = true;
        }
        if (!m8749K() || r0) {
            return false;
        }
        return true;
    }

    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    public void m8762a(@NonNull DeepLink deepLink, boolean z) {
        Log.m7767a(f7052g, "Processing deep link: " + deepLink);
        if (z) {
            m8733c(1);
        }
        if ("market".equals(deepLink.e)) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(deepLink.b);
            startActivity(intent);
        } else if (deepLink.c == null) {
            throw new RuntimeException("deep link target was null");
        } else {
            Long a = DeepLink.a(this, deepLink.b);
            BaseFragment F;
            switch (14.b[deepLink.c.ordinal()]) {
                case 1:
                    m8736f(true);
                    return;
                case 2:
                    String str = deepLink.d;
                    F = mo4089F();
                    if (F instanceof SongbookFragment) {
                        ((SongbookFragment) F).e(str);
                        return;
                    } else {
                        m8769e(str);
                        return;
                    }
                case 3:
                case 4:
                    this.f7062k.a(Tab.e, z);
                    return;
                case 5:
                    m8771r();
                    m8724a(Long.parseLong(deepLink.d));
                    return;
                case 6:
                    m8735f(deepLink.d);
                    return;
                case 7:
                    this.f7062k.a(Tab.b, z);
                    return;
                case 8:
                    m8771r();
                    return;
                case 9:
                case 10:
                case 11:
                    m8729a(deepLink.d, false, null, 0, a, DeepLink.b(this, deepLink.b));
                    return;
                case 12:
                    m8729a(deepLink.d, true, PerformanceType.b, 0, a, DeepLink.b(this, deepLink.b));
                    return;
                case 13:
                    m8729a(deepLink.d, true, PerformanceType.c, -1, a, DeepLink.b(this, deepLink.b));
                    return;
                case 14:
                case 15:
                    m8729a(deepLink.d, true, PerformanceType.c, 1, a, DeepLink.b(this, deepLink.b));
                    return;
                case 16:
                    m8729a(deepLink.d, true, PerformanceType.c, 2, a, DeepLink.b(this, deepLink.b));
                    return;
                case 17:
                    m8729a(deepLink.d, true, PerformanceType.c, 0, a, DeepLink.b(this, deepLink.b));
                    return;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                    m8729a(deepLink.d, true, PerformanceType.d, 0, a, DeepLink.b(this, deepLink.b));
                    return;
                case 25:
                case 26:
                    m8731b(deepLink.d, a);
                    return;
                case 27:
                case 28:
                    m8732b(deepLink.d, false, null, 0, a, DeepLink.b(this, deepLink.b));
                    return;
                case 29:
                    m8732b(deepLink.d, true, PerformanceType.b, 0, a, DeepLink.b(this, deepLink.b));
                    return;
                case 30:
                    m8732b(deepLink.d, true, PerformanceType.c, -1, a, DeepLink.b(this, deepLink.b));
                    return;
                case 31:
                case 32:
                    m8732b(deepLink.d, true, PerformanceType.c, 1, a, DeepLink.b(this, deepLink.b));
                    return;
                case 33:
                    m8732b(deepLink.d, true, PerformanceType.c, 2, a, DeepLink.b(this, deepLink.b));
                    return;
                case 34:
                    m8732b(deepLink.d, true, PerformanceType.c, 0, a, DeepLink.b(this, deepLink.b));
                    return;
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                    m8732b(deepLink.d, true, PerformanceType.d, 0, a, DeepLink.b(this, deepLink.b));
                    return;
                case 42:
                    m8728a(deepLink.d, a);
                    return;
                case 43:
                    m8729a("_open_mic_5m", false, null, 0, a, true);
                    return;
                case 44:
                    mo4080a(SettingsFragment.a(), SettingsFragment.e);
                    return;
                case 45:
                    mo4080a(WebViewFragment.a(getResources().getString(C1947R.string.privacy_url), getResources().getString(C1947R.string.settings_privacy_policy)), WebViewFragment.class.getName());
                    return;
                case 46:
                    mo4080a(WebViewFragment.a(getResources().getString(C1947R.string.tos_url), getResources().getString(C1947R.string.settings_terms_of_service)), WebViewFragment.class.getName());
                    return;
                case 47:
                    NotificationsFragment.a(Tabs.a);
                    this.f7062k.a(Tab.d, z);
                    return;
                case 48:
                    NotificationsFragment.a(Tabs.b);
                    this.f7062k.a(Tab.d, z);
                    return;
                case 49:
                    this.f7062k.a(Tab.a, z);
                    return;
                case 50:
                    if (mo4089F() == null) {
                        NotificationsFragment.E();
                        this.f7062k.a(Tab.d, z);
                    }
                    m8734c(deepLink.d, a);
                    return;
                case 51:
                    if (SubscriptionManager.m8066a().m8087b()) {
                        m8771r();
                        return;
                    }
                    F = UpsellManager.a(false, null, "lk_url", null, UpsellType.b);
                    mo4080a(F, F.s());
                    return;
                case 52:
                    if (SubscriptionManager.m8066a().m8087b()) {
                        m8771r();
                        return;
                    }
                    F = UpsellManager.a(false, null, "lk_url", deepLink.d, UpsellType.b);
                    mo4080a(F, F.s());
                    return;
                case 53:
                    m8771r();
                    return;
                case 54:
                    m8771r();
                    if (!SubscriptionManager.m8066a().m8087b()) {
                        m8737g(deepLink.d);
                        return;
                    }
                    return;
                case 55:
                case 56:
                case 57:
                    m8727a(deepLink);
                    return;
                case 58:
                    Log.m7770b(f7052g, "Twitter reply: " + deepLink.b);
                    MagicTwitter.a().a(deepLink.b);
                    return;
                case 59:
                    SingApplication.m8799g().m8816a(SingApplication.m8799g().m8817b(deepLink.d));
                    recreate();
                    return;
                case 60:
                    m8771r();
                    try {
                        PromotionManager.a().a(Long.parseLong(deepLink.d), new 6(this, deepLink));
                        return;
                    } catch (NumberFormatException e) {
                        Log.m7776e(f7052g, "Trying to load a promo page for a non-number promoId: " + deepLink.d);
                        Toaster.m8427a((Context) this, (int) C1947R.string.promo_error);
                        return;
                    }
                case 61:
                    try {
                        mo4080a(LeaderboardFragment.a(Long.parseLong(deepLink.d)), LeaderboardFragment.e);
                        return;
                    } catch (NumberFormatException e2) {
                        Log.m7776e(f7052g, "Trying to load a leaderboard for a non-number promoId: " + deepLink.d);
                        Toaster.m8427a((Context) this, (int) C1947R.string.promo_error);
                        return;
                    }
                case 62:
                    String str2;
                    if (this.f7073w == null) {
                        str2 = "onboarding deeplink has null intent";
                        Log.m7775d(f7052g, "onboarding deeplink has null intent", new Exception("onboarding deeplink has null intent"));
                        return;
                    }
                    SongbookEntry songbookEntry = (SongbookEntry) this.f7073w.getParcelableExtra("ONBOARDING_SONGBOOK_ENTRY");
                    if (songbookEntry != null) {
                        PreSingActivity.a(this).a(StartupMode.c).a(new Builder().a(songbookEntry).a(SubscriptionManager.m8066a().m8087b()).a(PerformanceType.b).c(true).a()).a();
                        return;
                    }
                    str2 = "onboarding deeplink does not contain SongbookEntry";
                    Log.m7775d(f7052g, "onboarding deeplink does not contain SongbookEntry", new Exception("onboarding deeplink does not contain SongbookEntry"));
                    return;
                default:
                    return;
            }
        }
    }

    public void m8769e(String str) {
        ((SongbookFragment) m8755a(Tab.c)).e(str);
        this.f7062k.a(Tab.c, true);
    }

    private void m8729a(String str, boolean z, PerformanceType performanceType, int i, Long l, boolean z2) {
        if (SingApplication.m8797d().m8401c("InitAppTask.OP_RELOAD_SONGBOOK")) {
            ListingV2 e = StoreManager.a().e(str);
            Log.m7770b(f7052g, "song: " + e);
            if (e != null) {
                SongbookEntry listingEntry = new ListingEntry(e);
                SingAnalytics.c(listingEntry);
                Builder a = new Builder().a(listingEntry);
                if (!z) {
                    performanceType = PerformanceType.a;
                }
                PreSingActivity.a(this).a(StartupMode.e).a(listingEntry).a("lk_url").a(l.longValue()).a(a.a(performanceType).a(null).b(i).f(z2).a(l).a()).a(z).a();
                return;
            }
            m8771r();
            return;
        }
        Log.m7770b(f7052g, "Wait for store deep init");
        SingApplication.m8797d().m8393a("OP_DEEP_LINK_WAIT_FOR_STORE_INIT", Collections.singletonList("InitAppTask.OP_RELOAD_SONGBOOK"), new 7(this, new UiHandler(this), str, z, performanceType, i, l, z2));
    }

    private void m8728a(String str, Long l) {
        ListingV2 e = StoreManager.a().e(str);
        if (e != null) {
            SongbookEntry listingEntry = new ListingEntry(e);
            PreSingActivity.a(this).a(StartupMode.f).a(listingEntry).a(l.longValue()).a(new Builder().a(listingEntry).a(SubscriptionManager.m8066a().m8087b()).a(l).a()).a();
            return;
        }
        m8771r();
    }

    private void m8731b(String str, Long l) {
        if (str == null || str.isEmpty()) {
            Log.m7774d(f7052g, "performanceKey from deep link was null or empty");
        } else {
            PerformanceManager.m7975a().m8007a(str, new 8(this, l));
        }
    }

    private void m8735f(String str) {
        if (str == null || str.isEmpty()) {
            Log.m7774d(f7052g, "performanceKey from deep link was null or empty");
        } else {
            PerformanceManager.m7975a().m8007a(str, new 9(this));
        }
    }

    private void m8732b(String str, boolean z, PerformanceType performanceType, int i, Long l, boolean z2) {
        ArrangementManager.m7901a().m7917a(str, new 10(this, str, z, performanceType, i, z2, l));
    }

    private void m8724a(long j) {
        UserManager.m8111a().m8167a(j, new 11(this, j));
    }

    private void m8734c(String str, Long l) {
        PerformanceManager.m7975a().m8007a(str, new 12(this, l));
    }

    private void m8727a(DeepLink deepLink) {
        if (ChatUtils.m8906a() && deepLink.c != null) {
            switch (14.b[deepLink.c.ordinal()]) {
                case 55:
                    m8716c(MessageCenterFragment.a());
                    return;
                case 56:
                    m8716c(MessageCenterFragment.a());
                    m8716c(ChatFragment.c(deepLink.d));
                    return;
                case 57:
                    m8716c(MessageCenterFragment.a());
                    m8716c(ChatFragment.d(deepLink.d));
                    return;
                default:
                    Log.m7774d(f7052g, "Unknown deep link handed to chat: " + deepLink);
                    return;
            }
        }
    }

    private void m8737g(String str) {
        AdVendorManagerConfig.b(this);
        AdVendorManager.a().a(this, AdType.a, str, null);
    }

    public void m8752N() {
        MagicFacebook.m7682a().m7693a(new 13(this), false, 10);
    }

    protected void m8753O() {
        if (!isFinishing()) {
            this.f7062k.b();
        }
    }

    public static MasterActivity m8730b(Context context) {
        if (context == null || !(context instanceof MasterActivity)) {
            return null;
        }
        return (MasterActivity) context;
    }

    public void m8754P() {
        this.f7059h.requestFocus();
    }
}
