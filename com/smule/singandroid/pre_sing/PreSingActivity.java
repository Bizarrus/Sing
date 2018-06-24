package com.smule.singandroid.pre_sing;

import android.app.Activity;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.smule.android.ads.MagicAdAdapterFactory;
import com.smule.android.ads.MagicAdSettings;
import com.smule.android.ads.fullScreenAds.MagicFullScreenAdMediatorAdapter;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.FullScreenAdPlacementType;
import com.smule.android.logging.Analytics.UserPath;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager$PerformanceResponseCallback;
import com.smule.android.network.managers.PerformanceManager.PerformanceResponse;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.managers.StoreManager.SongResponseCallback;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.models.ListingV2;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.songbook.ListingEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.BaseFragment$BaseFragmentResponder$MenuToggleAction;
import com.smule.singandroid.BaseFragment.BaseFragmentResponder;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.OpenCallFragment;
import com.smule.singandroid.SingBundle;
import com.smule.singandroid.SingBundle.Builder;
import com.smule.singandroid.SingBundle.PerformanceType;
import com.smule.singandroid.SongbookFragment;
import com.smule.singandroid.customviews.ActionBarCustomView;
import com.smule.singandroid.customviews.MasterToolbar;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.BusyDialog.BusyDialogListener;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.fragments.OpenCallPerformancesListFragment;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.pre_sing.PreSingActivity_.IntentBuilder_;
import com.smule.singandroid.task.SongDownloadTask;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer.ActionBarHighlightMode;
import com.smule.singandroid.utils.SongbookEntryUtils;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EActivity
public class PreSingActivity extends MediaPlayingActivity implements BaseFragmentResponder {
    @Extra
    protected StartupMode f23553g;
    @Extra
    protected SongbookEntry f23554h;
    @Extra
    protected String f23555i;
    @Extra
    protected long f23556j = -1;
    @Extra
    protected SingBundle f23557k;
    @Extra
    protected PerformanceV2 f23558l;
    @Extra
    protected boolean f23559m = false;
    @ViewById
    protected RelativeLayout f23560n;
    @ViewById
    protected MasterToolbar f23561o;
    @ViewById
    protected View f23562p;
    @ViewById
    protected RelativeLayout f23563q;
    @InstanceState
    protected boolean f23564r = false;
    @ViewById
    protected ProgressBar f23565s;
    OnClickListener f23566t = new C47322(this);
    private MagicFullScreenAdMediatorAdapter f23567v;
    private SongDownloadTask f23568w;
    private boolean f23569x = false;

    class C47311 implements OnBackStackChangedListener {
        final /* synthetic */ PreSingActivity f23522a;

        C47311(PreSingActivity preSingActivity) {
            this.f23522a = preSingActivity;
        }

        public void onBackStackChanged() {
            if (this.f23522a.m24773F() != null) {
                this.f23522a.m24771w();
            }
        }
    }

    class C47322 implements OnClickListener {
        final /* synthetic */ PreSingActivity f23523a;

        C47322(PreSingActivity preSingActivity) {
            this.f23523a = preSingActivity;
        }

        public void onClick(View view) {
            if (!(this.f23523a.m24773F() instanceof SongbookFragment)) {
                this.f23523a.onBackPressed();
            }
        }
    }

    class C47333 implements OnDismissListener {
        final /* synthetic */ PreSingActivity f23524a;

        C47333(PreSingActivity preSingActivity) {
            this.f23524a = preSingActivity;
        }

        public void onDismiss(DialogInterface dialogInterface) {
            this.f23524a.finish();
        }
    }

    class C47365 implements OnDismissListener {
        final /* synthetic */ PreSingActivity f23534a;

        C47365(PreSingActivity preSingActivity) {
            this.f23534a = preSingActivity;
        }

        public void onDismiss(DialogInterface dialogInterface) {
            this.f23534a.finish();
        }
    }

    public enum StartupMode {
        DEFAULT,
        OPENCALL,
        ONBOARDING,
        BADVIDEO,
        DEEPLINK_SONG,
        DEEPLINK_SONG_OPENCALL,
        DEEPLINK_SEED,
        DEEPLINK_SEED_OPENCALL,
        DEEPLINK_ARR
    }

    public MasterToolbar m24772B() {
        return this.f23561o;
    }

    public void m24782a(CharSequence charSequence, CharSequence charSequence2, int i) {
        ActionBarCustomView toolbarView = this.f23561o.getToolbarView();
        if (toolbarView != null) {
            toolbarView.m23104a(charSequence, charSequence2);
            toolbarView.setTitleCompoundDrawable(i);
        }
    }

    public void m24778a(SongbookEntry songbookEntry, PerformanceV2 performanceV2) {
        ActionBarCustomView toolbarView = this.f23561o.getToolbarView();
        if (toolbarView != null) {
            toolbarView.mo6782a(songbookEntry, performanceV2);
        }
    }

    public ProgressBar m24786q() {
        return this.f23565s;
    }

    public static IntentBuilder_ m24763a(Context context) {
        if (context instanceof MediaPlayingActivity) {
            ((MediaPlayingActivity) context).ag();
        }
        return new IntentBuilder_(context);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        m24766a((StartupMode) intent.getSerializableExtra("mStartupMode"), (SongbookEntry) intent.getParcelableExtra("mEntry"), intent.getStringExtra("mSectionIdReferrer"), intent.getLongExtra("mPromoId", -1), (SingBundle) intent.getParcelableExtra("mSingBundle"), (PerformanceV2) intent.getParcelableExtra("mOpenCallPerformance"), intent.getBooleanExtra("mUseUserGivenParams", false));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (MagicAdSettings.m17436a(Arrays.asList(FullScreenAdPlacementType.values()))) {
            this.f23567v = MagicAdAdapterFactory.m17426a().m17427a(this, FullScreenAdPlacementType.SINGING_SOLO, null, null);
            if (this.f23567v != null) {
                this.f23567v.onActivityCreate(this);
            }
        }
        getFragmentManager().addOnBackStackChangedListener(new C47311(this));
        if (bundle == null) {
            m24766a(this.f23553g, this.f23554h, this.f23555i, this.f23556j, this.f23557k, this.f23558l, this.f23559m);
        }
    }

    protected void onStart() {
        super.onStart();
        if (this.f23567v != null) {
            this.f23567v.onActivityStart(this);
        }
    }

    protected void onRestart() {
        super.onRestart();
        if (this.f23567v != null) {
            this.f23567v.onActivityRestart(this);
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.f23567v != null) {
            this.f23567v.onActivityResume(this);
        }
        this.f23561o.m23235d();
    }

    protected void onPause() {
        super.onPause();
        if (this.f23567v != null) {
            this.f23567v.onActivityPause(this);
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.f23567v != null) {
            this.f23567v.onActivityStop(this);
        }
        if (this.f23569x) {
            finish();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (!(this.f23568w == null || this.f23568w.getStatus() == Status.FINISHED)) {
            this.f23568w.m25690d();
        }
        this.f23568w = null;
        if (this.f23567v != null) {
            this.f23567v.onActivityDestroy(this);
            this.f23567v.destroy();
        }
        this.f23567v = null;
    }

    protected void m24785d() {
        super.d();
        setSupportActionBar(this.f23561o);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            if (this.f23564r) {
                supportActionBar.hide();
            } else {
                supportActionBar.setDisplayShowTitleEnabled(false);
                supportActionBar.setDisplayShowCustomEnabled(true);
                View a = ActionBarCustomView.m23105a((Context) this);
                a.setOnClickListener(this.f23566t);
                this.f23561o.setToolbarView(a);
                supportActionBar.setCustomView(a);
            }
        }
        a(this.f23562p, this.f23563q);
    }

    public void m24781a(BaseFragment$BaseFragmentResponder$MenuToggleAction baseFragment$BaseFragmentResponder$MenuToggleAction) {
    }

    public MagicFullScreenAdMediatorAdapter m24787r() {
        return this.f23567v;
    }

    @Click
    public void m24790u() {
        super.u();
    }

    public SongbookEntry m24788s() {
        return this.f23554h;
    }

    private void m24766a(StartupMode startupMode, SongbookEntry songbookEntry, String str, long j, SingBundle singBundle, PerformanceV2 performanceV2, boolean z) {
        switch (startupMode) {
            case OPENCALL:
                if (performanceV2 == null || !performanceV2.n()) {
                    this.f23564r = true;
                    TextAlertDialog textAlertDialog = new TextAlertDialog((Context) this, null, getResources().getString(C1947R.string.pre_singing_join_expired), true, false);
                    textAlertDialog.m19806a(getString(C1947R.string.core_ok), "");
                    textAlertDialog.setOnDismissListener(new C47333(this));
                    textAlertDialog.show();
                    return;
                }
                m24779a(songbookEntry, performanceV2, Long.valueOf(j));
                return;
            case ONBOARDING:
                PreSingBaseFragment.m24814a(this, singBundle, null, str);
                return;
            case BADVIDEO:
                PreSingBaseFragment.m24814a(this, singBundle, performanceV2, null);
                return;
            case DEEPLINK_SONG:
                if (z) {
                    m24765a(singBundle.f20139d, null, true, singBundle.f20137b, singBundle.f20142g, str, singBundle.f20153r, singBundle.f20156u);
                    return;
                }
                m24764a(singBundle.f20139d, null, str, singBundle.f20153r);
                return;
            case DEEPLINK_SONG_OPENCALL:
                a(OpenCallFragment.m20621a(singBundle), OpenCallFragment.class.getName());
                return;
            case DEEPLINK_SEED:
                m24779a(null, performanceV2, Long.valueOf(j));
                return;
            case DEEPLINK_SEED_OPENCALL:
                a(OpenCallPerformancesListFragment.m24071a(performanceV2), OpenCallPerformancesListFragment.class.getName());
                return;
            case DEEPLINK_ARR:
                m24765a(singBundle.f20139d, null, z, singBundle.f20137b, singBundle.f20142g, str, singBundle.f20153r, singBundle.f20156u);
                return;
            default:
                m24780a(songbookEntry, str, Long.valueOf(j));
                return;
        }
    }

    public BaseFragment m24773F() {
        return (BaseFragment) getFragmentManager().findFragmentById(C1947R.id.fragment_content_view);
    }

    public void m24775H() {
        BaseFragment F = m24773F();
        if (F != null) {
            F.mo6449u();
        }
    }

    public void m24774G() {
        BaseFragment F = m24773F();
        if (F != null) {
            F.mo6930t();
        }
    }

    private void m24771w() {
        BaseFragment F = m24773F();
        boolean h = F.mo6511h();
        if (F.mo6445g()) {
            MediaPlayerServiceController.m24641a().m24684o();
        } else {
            a();
            MediaPlayerServiceController.m24641a().m24685p();
        }
        F.mo6904w();
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            if (h) {
                if (!supportActionBar.isShowing()) {
                    F.mo6895r();
                }
                supportActionBar.show();
                this.f23561o.getToolbarView().setDisplayUpButton(F.mo6446i());
                this.f23561o.getToolbarView().setEnableUpButton(F.mo6446i());
                invalidateOptionsMenu();
                return;
            }
            supportActionBar.hide();
        }
    }

    public void m24780a(SongbookEntry songbookEntry, String str, Long l) {
        Log.b(a, "startSongFlow called");
        if (songbookEntry == null || !songbookEntry.m18773s() || SongbookEntryUtils.m26166a(songbookEntry, str)) {
            m24764a(songbookEntry, null, str, l);
            return;
        }
        BaseFragment a = UpsellManager.m25791a(true, songbookEntry, str, null, UpsellType.VIP_SONG);
        a(a, a.mo6383s());
    }

    private void m24764a(SongbookEntry songbookEntry, PerformanceV2 performanceV2, String str, Long l) {
        m24765a(songbookEntry, performanceV2, false, null, 0, str, l, true);
    }

    private void m24765a(SongbookEntry songbookEntry, PerformanceV2 performanceV2, boolean z, PerformanceType performanceType, int i, String str, Long l, boolean z2) {
        boolean b;
        if (songbookEntry != null) {
            int i2;
            b = SubscriptionManager.a().b();
            Object obj = performanceV2 != null ? 1 : null;
            if (z && obj == null) {
                i2 = i;
            } else {
                Object obj2 = (obj == null || !performanceV2.d()) ? null : 1;
                performanceType = obj == null ? PerformanceType.UNDEFINED : obj2 != null ? PerformanceType.DUET : PerformanceType.GROUP;
                i2 = obj != null ? performanceV2.origTrackPartId == 0 ? 0 : performanceV2.origTrackPartId == 1 ? 2 : 1 : obj2 != null ? -1 : 0;
            }
            boolean z3 = (performanceType == PerformanceType.UNDEFINED || i2 == -1) ? false : true;
            Builder e = new Builder().m21613a(songbookEntry).m21612a(performanceV2).m21620a(b).m21615a(performanceType).m21622b(i2).m21618a(l).m21626e(z3);
            if (z) {
                e.m21627f(true);
            }
            if (obj != null && performanceV2.video) {
                e.m21619a(performanceV2.g());
            }
            SingBundle a = e.m21621a();
            if (z) {
                a.m21641a("VIDEO_RECORD_ENABLED_KEY", z2);
            }
            PreSingBaseFragment.m24814a(this, a, performanceV2, str);
        } else if (performanceV2 == null || performanceV2.songUid == null) {
            TextAlertDialog textAlertDialog = new TextAlertDialog((Context) this, getString(C1947R.string.performances_not_available_title), getString(C1947R.string.performances_not_available_text), true, false);
            textAlertDialog.m19806a(getResources().getString(C1947R.string.core_ok), "");
            textAlertDialog.setOnDismissListener(new C47365(this));
            textAlertDialog.show();
        } else {
            final PerformanceV2 performanceV22 = performanceV2;
            b = z;
            final PerformanceType performanceType2 = performanceType;
            final int i3 = i;
            final String str2 = str;
            final Long l2 = l;
            final boolean z4 = z2;
            StoreManager.m18378a().m18417a(performanceV2.songUid, new SongResponseCallback(this) {
                final /* synthetic */ PreSingActivity f23533h;

                class C47341 implements OnDismissListener {
                    final /* synthetic */ C47354 f23525a;

                    C47341(C47354 c47354) {
                        this.f23525a = c47354;
                    }

                    public void onDismiss(DialogInterface dialogInterface) {
                        this.f23525a.f23533h.finish();
                    }
                }

                public void mo6605a(SongV2 songV2) {
                    if (songV2 != null) {
                        this.f23533h.m24765a(SongbookEntry.m18746a(new ListingV2(songV2)), performanceV22, b, performanceType2, i3, str2, l2, z4);
                        return;
                    }
                    TextAlertDialog textAlertDialog = new TextAlertDialog(this.f23533h, this.f23533h.getString(C1947R.string.performances_not_available_title), this.f23533h.getString(C1947R.string.performances_not_available_text), true, false);
                    textAlertDialog.m19806a(this.f23533h.getResources().getString(C1947R.string.core_ok), "");
                    textAlertDialog.setOnDismissListener(new C47341(this));
                    textAlertDialog.show();
                }
            });
        }
    }

    public void m24779a(SongbookEntry songbookEntry, PerformanceV2 performanceV2, Long l) {
        Log.b(a, "startOpenCallJoin called");
        if (performanceV2 == null) {
            Log.e(a, "Attempt to start sing flow without an open call");
            return;
        }
        boolean z = (performanceV2.r() && performanceV2.arrangementVersion == null) || performanceV2.video;
        if (z) {
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            final BusyDialog busyDialog = new BusyDialog((Activity) this, getString(C1947R.string.core_loading));
            busyDialog.m23579a(new BusyDialogListener(this) {
                final /* synthetic */ PreSingActivity f23536b;

                public void mo6373a() {
                    atomicBoolean.set(true);
                }
            });
            busyDialog.show();
            final SongbookEntry songbookEntry2 = songbookEntry;
            final Long l2 = l;
            PerformanceManager.a().a(performanceV2.performanceKey, new PerformanceManager$PerformanceResponseCallback(this) {
                final /* synthetic */ PreSingActivity f23541e;

                public void handleResponse(PerformanceResponse performanceResponse) {
                    if (atomicBoolean.get() || !this.f23541e.g() || !busyDialog.isShowing()) {
                        return;
                    }
                    if (performanceResponse.a()) {
                        busyDialog.dismiss();
                        this.f23541e.m24770b(songbookEntry2, performanceResponse.mPerformance, l2);
                    } else if (performanceResponse.a.e()) {
                        busyDialog.dismiss();
                        this.f23541e.a(performanceResponse.a.f, true, null);
                    } else {
                        busyDialog.m23576a(2, this.f23541e.getResources().getString(C1947R.string.songbook_download_failed_message), true);
                    }
                }
            });
            return;
        }
        m24770b(songbookEntry, performanceV2, l);
    }

    private void m24770b(SongbookEntry songbookEntry, PerformanceV2 performanceV2, Long l) {
        SongbookEntry songbookEntry2;
        if (performanceV2 != null) {
            TextAlertDialog textAlertDialog;
            if (performanceV2.t()) {
                textAlertDialog = new TextAlertDialog((Context) this, getString(C1947R.string.performance_copyright_violation), MessageFormat.format(getString(C1947R.string.performance_copyright_violation_detail), new Object[]{getString(C1947R.string.performance_copyright_violation_email)}));
                textAlertDialog.m19806a(getString(C1947R.string.core_ok), null);
                textAlertDialog.show();
                return;
            } else if (!performanceV2.a()) {
                textAlertDialog = new TextAlertDialog((Context) this, getString(C1947R.string.recording_not_ready), getString(C1947R.string.recording_not_ready_detail), true, false);
                textAlertDialog.m19806a(getString(C1947R.string.core_ok), null);
                textAlertDialog.show();
                return;
            }
        }
        if (songbookEntry != null) {
            songbookEntry2 = songbookEntry;
        } else if (performanceV2 != null) {
            songbookEntry2 = SongbookEntry.m18747a(performanceV2);
        } else {
            songbookEntry2 = null;
        }
        if (!(songbookEntry2 instanceof ListingEntry) || ((ListingEntry) songbookEntry2).f17626a == null || SongbookEntryUtils.m26166a(songbookEntry2, null)) {
            if (performanceV2 != null) {
                Analytics.m17873a(performanceV2.performanceKey, performanceV2.songUid, PerformanceV2Util.m25949g(performanceV2), PerformanceType.m21630a(performanceV2.ensembleType).m21631a());
            }
            m24764a(songbookEntry2, performanceV2, null, l);
            return;
        }
        BaseFragment a = UpsellManager.m25790a(true, songbookEntry2, performanceV2, l, UpsellType.VIP_SONG);
        a(a, a.mo6383s());
    }

    public SongDownloadTask m24789t() {
        return this.f23568w;
    }

    public void m24777a(SongbookEntry songbookEntry) {
        if (this.f23568w != null) {
            if (!this.f23568w.m25687a(songbookEntry) || (!this.f23568w.m25691e() && this.f23568w.getStatus() == Status.FINISHED)) {
                this.f23568w.m25690d();
            } else {
                return;
            }
        }
        this.f23568w = new SongDownloadTask(this, songbookEntry, UserPath.ONBOARDING);
        this.f23568w.execute(new Void[0]);
    }

    public void m24783b(SongbookEntry songbookEntry, PerformanceV2 performanceV2) {
        if (this.f23568w != null) {
            if (!this.f23568w.m25691e() && this.f23568w.getStatus() == Status.FINISHED) {
                this.f23568w.m25690d();
            } else {
                return;
            }
        }
        this.f23568w = new SongDownloadTask(this, songbookEntry, performanceV2, null, null);
        this.f23568w.m25688b();
        this.f23568w.execute(new Void[0]);
    }

    public boolean m24784b(SongbookEntry songbookEntry) {
        return this.f23557k != null && this.f23557k.f20149n && this.f23568w != null && this.f23568w.m25687a(songbookEntry) && this.f23568w.m25691e();
    }

    public void m24791v() {
        this.f23569x = true;
    }

    public QuickReturnListViewMenuSyncer m24776a(AbsListView absListView, ActionBarHighlightMode actionBarHighlightMode, OnScrollListener onScrollListener) {
        OnScrollListener pauseOnScrollListener;
        Log.b(a, "syncMenuWithListView called");
        if (onScrollListener instanceof PauseOnScrollListener) {
            pauseOnScrollListener = new PauseOnScrollListener(ImageLoader.a(), true, true, onScrollListener);
        } else {
            pauseOnScrollListener = onScrollListener;
        }
        return new QuickReturnListViewMenuSyncer(this, absListView, actionBarHighlightMode, pauseOnScrollListener, null, T(), this.f23561o);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        Log.b(a, "onActivityResult : " + i + " / " + i2 + ": " + intent);
        boolean z = false;
        if (Z() != null) {
            z = Z().a(i, i2, intent);
        }
        if (!z) {
            super.onActivityResult(i, i2, intent);
        }
    }
}
