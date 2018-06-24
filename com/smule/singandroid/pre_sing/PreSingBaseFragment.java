package com.smule.singandroid.pre_sing;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentManager.BackStackEntry;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.ads.MagicAdSettings;
import com.smule.android.datasources.OpenSeedsDataSource;
import com.smule.android.logging.Analytics.Ensemble;
import com.smule.android.logging.Analytics.FullScreenAdPlacementType;
import com.smule.android.logging.Analytics.PageType;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.ArrangementManager.ArrangementVersionResponse;
import com.smule.android.network.managers.EntitlementsManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.models.ArrangementVersion;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.ListingEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.DeviceSettings;
import com.smule.singandroid.MasterActivity_;
import com.smule.singandroid.OpenCallFragment;
import com.smule.singandroid.SingActivity_;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingBundle;
import com.smule.singandroid.SingBundle.Builder;
import com.smule.singandroid.SingBundle.GateType;
import com.smule.singandroid.SingBundle.PerformanceType;
import com.smule.singandroid.SingVideoActivity_;
import com.smule.singandroid.SongSuggestionManager;
import com.smule.singandroid.TrialSubscriptionActivity;
import com.smule.singandroid.TrialSubscriptionActivity_;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.runtimepermissions.SingPermissionRequests;
import com.smule.singandroid.task.PreDownloadArrangementTask;
import com.smule.singandroid.task.PreDownloadArrangementTask.DownloadListener;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.RecClkType;
import com.smule.singandroid.utils.SingAnalytics.RecEnsembleType;
import com.smule.singandroid.utils.SingAnalytics.RecType;
import com.smule.singandroid.utils.SongbookEntryUtils;
import com.smule.singandroid.utils.SongbookSectionUtil;
import java.text.MessageFormat;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.ViewById;
import twitter4j.HttpResponseCode;

@EFragment
public abstract class PreSingBaseFragment extends BaseFragment {
    private static final String f23596t = PreSingBaseFragment.class.getName();
    private static int f23597u = -1;
    @ViewById
    protected Button f23598e;
    @ViewById
    protected RelativeLayout f23599f;
    @ViewById
    protected TextView f23600g;
    @InstanceState
    protected String f23601h;
    @InstanceState
    protected SingBundle f23602i;
    @InstanceState
    protected PerformanceV2 f23603j;
    @InstanceState
    protected SongbookEntry f23604k;
    @InstanceState
    protected boolean f23605l = true;
    protected BusyDialog f23606m;
    protected ArrangementVersion f23607n;
    protected OpenSeedsDataSource f23608o;
    protected AtomicBoolean f23609p = new AtomicBoolean(false);
    protected final int f23610q = Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
    protected final int f23611r = HttpResponseCode.INTERNAL_SERVER_ERROR;
    protected int f23612s;
    private PreDownloadArrangementTask f23613v;
    private Runnable f23614w;

    class C47432 implements Runnable {
        final /* synthetic */ PreSingBaseFragment f23578a;

        C47432(PreSingBaseFragment preSingBaseFragment) {
            this.f23578a = preSingBaseFragment;
        }

        public void run() {
            this.f23578a.mo6899J();
        }
    }

    class C47443 implements Runnable {
        final /* synthetic */ PreSingBaseFragment f23579a;

        C47443(PreSingBaseFragment preSingBaseFragment) {
            this.f23579a = preSingBaseFragment;
        }

        public void run() {
            this.f23579a.mo6901L();
        }
    }

    class C47465 implements ResultCallback {
        final /* synthetic */ PreSingBaseFragment f23583a;

        C47465(PreSingBaseFragment preSingBaseFragment) {
            this.f23583a = preSingBaseFragment;
        }

        public void mo6372a(boolean z, @NonNull Set<String> set) {
            if (z) {
                this.f23583a.mo6897a();
                return;
            }
            Intent intent;
            if (this.f23583a.f23602i.f20149n && TrialSubscriptionActivity.m21947a(this.f23583a.getActivity())) {
                intent = new Intent(this.f23583a.getActivity(), TrialSubscriptionActivity_.class);
            } else {
                intent = new Intent(this.f23583a.getActivity(), MasterActivity_.class);
            }
            this.f23583a.startActivity(intent);
            this.f23583a.getActivity().finish();
        }
    }

    protected enum ViewPhase {
        NON_OWNED_MODE_SELECT,
        PRE_SING_MODE_SELECT,
        DUET_PART_SELECT,
        SOFT_PAYWALL,
        HARD_PAYWALL,
        DOWNLOAD,
        VIDEO_SELECT,
        HEADSET_REMINDER
    }

    static String m24824z() {
        return "PreSingFragment";
    }

    public String m24825A() {
        return m24824z() + ": " + getClass().getName();
    }

    public static void m24814a(PreSingActivity preSingActivity, SingBundle singBundle, PerformanceV2 performanceV2, String str) {
        boolean z;
        ViewPhase viewPhase;
        boolean z2 = true;
        boolean a = m24822a(singBundle, str);
        Log.b(f23596t, "processSingBundle - for entry with UID, " + singBundle.f20139d.mo6289c() + ", is playable now: " + a);
        if (a) {
            if (singBundle.f20149n) {
                m24813a(preSingActivity, singBundle.f20139d);
                if (PreSingHeadsetReminderFragment.m24922a()) {
                    ViewPhase viewPhase2 = ViewPhase.HEADSET_REMINDER;
                    SingAnalytics.m26122a(SongbookEntryUtils.m26167b(singBundle.f20139d), false, Ensemble.SOLO, SingAnalytics.m26141d(singBundle.f20139d), true, PageType.SCREEN);
                    z = true;
                    viewPhase = viewPhase2;
                } else {
                    viewPhase = ViewPhase.DOWNLOAD;
                    z = true;
                }
            } else if (singBundle.f20152q) {
                Log.b(f23596t, "processSingBundle - singing parameters already selected; calling playProduct");
                if (m24821a(singBundle)) {
                    if (singBundle.f20156u) {
                        z2 = singBundle.m21650b("VIDEO_RECORD_ENABLED_KEY", true);
                    }
                    viewPhase = ViewPhase.VIDEO_SELECT;
                    z = z2;
                } else {
                    viewPhase = ViewPhase.DOWNLOAD;
                    z = true;
                }
            } else if (singBundle.f20137b == PerformanceType.UNDEFINED) {
                viewPhase = ViewPhase.PRE_SING_MODE_SELECT;
                z = true;
            } else {
                viewPhase = ViewPhase.DUET_PART_SELECT;
                z = true;
            }
        } else if (!SongbookEntryUtils.m26166a(singBundle.f20139d, str)) {
            viewPhase = ViewPhase.HARD_PAYWALL;
            z = true;
        } else if (singBundle.f20152q || singBundle.f20137b != PerformanceType.UNDEFINED) {
            viewPhase = ViewPhase.SOFT_PAYWALL;
            z = true;
        } else {
            viewPhase = ViewPhase.NON_OWNED_MODE_SELECT;
            z = true;
        }
        m24816a(preSingActivity, viewPhase, singBundle, performanceV2, str, z);
    }

    private static boolean m24822a(SingBundle singBundle, String str) {
        if (singBundle.f20139d.m18769o() || singBundle.f20147l || EntitlementsManager.m18163a().m18184c(singBundle.f20139d.mo6289c()) || singBundle.f20149n || singBundle.f20141f != null || SongbookSectionUtil.m26170a(str)) {
            return true;
        }
        return false;
    }

    private static void m24818a(PreSingActivity preSingActivity, PreSingBaseFragment preSingBaseFragment, SingBundle singBundle, PerformanceV2 performanceV2, String str, int i, int i2) {
        Bundle bundle = new Bundle();
        bundle.putString("SECTION_ID_KEY", str);
        bundle.putParcelable("SING_BUNDLE", singBundle);
        bundle.putParcelable("OPEN_CALL_KEY", performanceV2);
        preSingBaseFragment.setArguments(bundle);
        if (preSingActivity != null) {
            preSingActivity.a(preSingBaseFragment, preSingBaseFragment.m24825A(), i, i2);
        }
    }

    protected static void m24815a(PreSingActivity preSingActivity, ViewPhase viewPhase, SingBundle singBundle, PerformanceV2 performanceV2, String str, OpenSeedsDataSource openSeedsDataSource) {
        m24817a(preSingActivity, viewPhase, singBundle, performanceV2, str, true, openSeedsDataSource);
    }

    protected static void m24816a(PreSingActivity preSingActivity, ViewPhase viewPhase, SingBundle singBundle, PerformanceV2 performanceV2, String str, boolean z) {
        m24817a(preSingActivity, viewPhase, singBundle, performanceV2, str, z, null);
    }

    protected static void m24817a(PreSingActivity preSingActivity, ViewPhase viewPhase, SingBundle singBundle, PerformanceV2 performanceV2, String str, boolean z, OpenSeedsDataSource openSeedsDataSource) {
        Log.b(f23596t, "pre-sing will show view phase: " + viewPhase);
        if (viewPhase == ViewPhase.HARD_PAYWALL) {
            preSingActivity.c(UpsellManager.m25791a(true, singBundle.f20139d, f23596t, null, UpsellType.VIP_SONG));
            return;
        }
        PreSingBaseFragment preSingNonOwnedModeSelectFragment_;
        switch (viewPhase) {
            case NON_OWNED_MODE_SELECT:
                preSingNonOwnedModeSelectFragment_ = new PreSingNonOwnedModeSelectFragment_();
                break;
            case PRE_SING_MODE_SELECT:
                preSingNonOwnedModeSelectFragment_ = new PreSingModeSelectFragment_();
                break;
            case DUET_PART_SELECT:
                preSingNonOwnedModeSelectFragment_ = new PreSingDuetPartSelectFragment_();
                break;
            case SOFT_PAYWALL:
                preSingNonOwnedModeSelectFragment_ = new PreSingPurchaseFragment_();
                break;
            case VIDEO_SELECT:
                preSingNonOwnedModeSelectFragment_ = new PreSingVideoSelectionFragment_();
                ((PreSingVideoSelectionFragment) preSingNonOwnedModeSelectFragment_).m25075b(z);
                break;
            case DOWNLOAD:
                preSingNonOwnedModeSelectFragment_ = new PreSingDownloadFragment_();
                break;
            case HEADSET_REMINDER:
                preSingNonOwnedModeSelectFragment_ = new PreSingHeadsetReminderFragment_();
                break;
            default:
                throw new RuntimeException("ViewPhase not recognized: " + viewPhase);
        }
        Pair O = preSingNonOwnedModeSelectFragment_.mo6911O();
        m24818a(preSingActivity, preSingNonOwnedModeSelectFragment_, singBundle, performanceV2, str, ((Integer) O.first).intValue(), ((Integer) O.second).intValue());
    }

    protected void m24845a(ViewPhase viewPhase) {
        m24815a((PreSingActivity) getActivity(), viewPhase, this.f23602i, this.f23603j, this.f23601h, null);
    }

    protected void m24826B() {
        switch (this.f23602i.f20138c) {
            case SOFT_PAYWALL:
                m24845a(ViewPhase.SOFT_PAYWALL);
                return;
            case HARD_PAYWALL:
                m24845a(ViewPhase.HARD_PAYWALL);
                return;
            default:
                throw new RuntimeException("GateType not recognized: " + this.f23602i.f20138c);
        }
    }

    protected static void m24813a(PreSingActivity preSingActivity, SongbookEntry songbookEntry) {
        preSingActivity.m24777a(songbookEntry);
    }

    protected boolean m24827C() {
        PreSingActivity preSingActivity = (PreSingActivity) getActivity();
        return preSingActivity != null && preSingActivity.m24784b(this.f23604k);
    }

    protected void m24848b(SongbookEntry songbookEntry, PerformanceV2 performanceV2) {
        PreSingActivity preSingActivity = (PreSingActivity) getActivity();
        if (preSingActivity != null) {
            if (songbookEntry.m18772r()) {
                Log.b(f23596t, "startMainRoleDownloadTask skipped due to arr");
            } else {
                preSingActivity.m24783b(songbookEntry, performanceV2);
            }
        }
    }

    public static void m24812a(Activity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        int backStackEntryCount = fragmentManager.getBackStackEntryCount() - 1;
        while (backStackEntryCount >= 0) {
            BackStackEntry backStackEntryAt = fragmentManager.getBackStackEntryAt(backStackEntryCount);
            if (backStackEntryAt.getName().startsWith(m24824z())) {
                if (backStackEntryCount == 0) {
                    fragmentManager.popBackStack(backStackEntryAt.getName(), 1);
                }
                backStackEntryCount--;
            } else {
                fragmentManager.popBackStack(backStackEntryAt.getName(), 0);
                return;
            }
        }
    }

    public boolean mo6445g() {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null && getArguments() != null) {
            this.f23601h = getArguments().getString("SECTION_ID_KEY");
            this.f23602i = (SingBundle) getArguments().getParcelable("SING_BUNDLE");
            this.f23603j = (PerformanceV2) getArguments().getParcelable("OPEN_CALL_KEY");
            this.f23604k = this.f23602i.f20139d;
        }
    }

    protected RecType m24828D() {
        if (this instanceof PreSingNonOwnedModeSelectFragment) {
            return RecType.LOCKED;
        }
        if (SubscriptionManager.a().b()) {
            return RecType.VIP;
        }
        return RecType.UNLOCKED;
    }

    @AfterViews
    protected void mo6896E() {
        mo6910G();
    }

    public void onStop() {
        super.onStop();
        mo6898H();
    }

    @Click
    public void m24830F() {
        SingAnalytics.m26083a(m24828D(), this.f23602i.f20139d.mo6290d(), RecClkType.JOIN, null, SongbookEntryUtils.m26168c(this.f23602i.f20139d));
        if (this.f23605l) {
            mo6487a(OpenCallFragment.m20621a(this.f23602i));
        }
    }

    protected void mo6910G() {
        if (isAdded()) {
            m19830a(this.f23604k, this.f23603j);
        } else {
            Log.d(f23596t, "configureTopBar - fragment is not added; aborting configuration");
        }
    }

    public void mo6895r() {
        m19864o();
        m19830a(this.f23604k, this.f23603j);
    }

    public boolean mo6446i() {
        return this.f23602i == null || !this.f23602i.f20149n;
    }

    protected void m24846a(boolean z, Runnable runnable) {
        this.f23614w = runnable;
        if (z && (this.f23606m == null || !this.f23606m.isShowing())) {
            this.f23606m = new BusyDialog(getActivity(), getString(C1947R.string.core_loading));
            this.f23606m.m23580a(true);
        }
        if (this.f23613v != null) {
            Log.b(f23596t, "preFetchArrangementVersion - mPreDownloadArrangementTask was not null");
            return;
        }
        final ArrangementVersionLiteEntry arrangementVersionLiteEntry = (ArrangementVersionLiteEntry) this.f23604k;
        this.f23613v = new PreDownloadArrangementTask(arrangementVersionLiteEntry, new DownloadListener(this) {
            final /* synthetic */ PreSingBaseFragment f23577b;

            public void mo6894a(final ArrangementVersionResponse arrangementVersionResponse) {
                this.f23577b.f23613v = null;
                this.f23577b.f23607n = arrangementVersionResponse.mArrangementVersion;
                this.f23577b.m19838a(new Runnable(this) {
                    final /* synthetic */ C47421 f23575b;

                    public void run() {
                        this.f23575b.f23577b.mo6898H();
                        if (this.f23575b.f23577b.f23607n != null) {
                            arrangementVersionLiteEntry.f17623a.a(this.f23575b.f23577b.f23607n);
                            if (this.f23575b.f23577b.f23614w != null) {
                                this.f23575b.f23577b.f23614w.run();
                            }
                        } else if (arrangementVersionResponse.a.e()) {
                            ((BaseActivity) this.f23575b.f23577b.getActivity()).a(arrangementVersionResponse.a.f, false, null);
                        } else {
                            this.f23575b.f23577b.m19846b((int) C1947R.string.songbook_download_failed_message);
                        }
                    }
                });
            }
        });
        this.f23613v.execute(new Void[0]);
    }

    @SupposeUiThread
    protected void mo6898H() {
        if (this.f23606m != null) {
            this.f23606m.dismiss();
            this.f23606m = null;
        }
    }

    protected void m24833I() {
        if (isAdded()) {
            SingAnalytics.m26083a(m24828D(), this.f23602i.f20139d.mo6290d(), RecClkType.START, RecEnsembleType.LOCKED, SongbookEntryUtils.m26168c(this.f23602i.f20139d));
            if (this.f23604k.m18772r() && this.f23607n == null) {
                m24846a(true, new C47432(this));
            } else {
                mo6899J();
            }
        }
    }

    @SupposeUiThread
    protected void mo6899J() {
        if (!isAdded()) {
            return;
        }
        if (this.f23607n == null || !this.f23607n.arrangement.coprDisable) {
            Log.b(f23596t, "beginPurchaseFlow");
            m24826B();
            return;
        }
        mo6902M();
    }

    protected void m24844a(PerformanceType performanceType) {
        Builder builder = new Builder(this.f23602i);
        GateType gateType = GateType.NONE;
        switch (performanceType) {
            case SOLO:
                if (!MagicAdSettings.m17434a(FullScreenAdPlacementType.SINGING_SOLO)) {
                    gateType = GateType.HARD_PAYWALL;
                    break;
                } else {
                    gateType = GateType.SOFT_PAYWALL;
                    break;
                }
            case DUET:
            case GROUP:
                if (!MagicAdSettings.m17434a(FullScreenAdPlacementType.SINGING_SEED)) {
                    gateType = GateType.HARD_PAYWALL;
                    break;
                } else {
                    gateType = GateType.SOFT_PAYWALL;
                    break;
                }
        }
        Log.b(f23596t, "pre-sing pending gate set to: " + gateType.f20128e);
        builder.m21614a(gateType);
        this.f23602i = builder.m21621a();
    }

    protected void m24849b(PerformanceType performanceType) {
        boolean z = false;
        Builder builder = new Builder(this.f23602i);
        builder.m21615a(performanceType);
        builder.m21622b(0);
        if (performanceType != PerformanceType.DUET) {
            z = true;
        }
        builder.m21626e(z);
        this.f23602i = builder.m21621a();
    }

    protected void m24847a(boolean z, boolean z2, int i) {
        Builder builder = new Builder(this.f23602i);
        builder.m21622b(i);
        if (z) {
            builder.m21615a(PerformanceType.DUET);
        } else if (z2) {
            builder.m21615a(PerformanceType.GROUP);
        } else {
            builder.m21615a(PerformanceType.SOLO);
        }
        builder.m21626e(true);
        this.f23602i = builder.m21621a();
        if (this.f23604k.m18772r() && this.f23607n == null) {
            m24846a(true, new C47443(this));
        } else {
            mo6901L();
        }
    }

    @SupposeUiThread
    protected void mo6900K() {
        if (!isAdded()) {
            return;
        }
        if (m24827C()) {
            m24838N();
        } else if (m24821a(this.f23602i)) {
            m24845a(ViewPhase.VIDEO_SELECT);
        } else {
            m24845a(ViewPhase.DOWNLOAD);
        }
    }

    @SupposeUiThread
    protected void mo6901L() {
        if (!isAdded()) {
            return;
        }
        if (this.f23604k.m18772r() && this.f23607n.arrangement.coprDisable) {
            mo6902M();
        } else {
            mo6900K();
        }
    }

    @SupposeUiThread
    protected void mo6902M() {
        TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(C1947R.string.arrangement_copyright_violation_title), MessageFormat.format(getString(C1947R.string.arrangement_copyright_violation_detail), new Object[]{getString(C1947R.string.performance_copyright_violation_email)}), true, false);
        textAlertDialog.m19806a(getString(C1947R.string.core_ok), null);
        textAlertDialog.show();
    }

    protected Runnable m24842a(final TextView textView) {
        return new Runnable(this) {
            final /* synthetic */ PreSingBaseFragment f23581b;
            private int f23582c = 0;

            public void run() {
                int i = 0;
                if (!this.f23581b.f23609p.get() && this.f23581b.isAdded()) {
                    textView.setVisibility(0);
                    CharSequence string = this.f23581b.getString(C1947R.string.pre_singing_open_loading);
                    while (i < this.f23582c) {
                        string = string + ".";
                        i++;
                    }
                    textView.setText(string);
                    this.f23582c = (this.f23582c + 1) % 4;
                    this.f23581b.m19839a((Runnable) this, 250);
                }
            }
        };
    }

    protected void m24843a(View view, boolean z, boolean z2) {
        int i;
        if (z) {
            i = 17432576;
        } else {
            i = 17432577;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(getActivity(), i);
        loadAnimation.setFillAfter(z2);
        view.startAnimation(loadAnimation);
    }

    protected void m24838N() {
        mo6406a(SingPermissionRequests.f23950d, new C47465(this));
    }

    private void mo6897a() {
        PreSingActivity preSingActivity = (PreSingActivity) getActivity();
        if (preSingActivity != null) {
            if (this.f23604k.m18773s()) {
                SongSuggestionManager.m21778a(getActivity()).m21779a(((ListingEntry) this.f23604k).f17626a.listingId);
            }
            preSingActivity.m24791v();
            Builder builder = new Builder(this.f23602i);
            builder.m21613a(this.f23604k);
            if (this.f23603j != null) {
                builder.m21612a(this.f23603j);
            }
            if (DeviceSettings.r()) {
                builder.m21619a(this.f23602i.m21657f());
            }
            this.f23602i = builder.m21621a();
            MediaPlayerServiceController.m24641a().m24685p();
            preSingActivity.startActivity(this.f23602i.m21636a(getActivity().getApplicationContext(), this.f23602i.m21654d() ? SingVideoActivity_.class : SingActivity_.class));
        } else if (isAdded()) {
            FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager != null) {
                fragmentManager.popBackStack();
            }
        }
    }

    protected static boolean m24821a(SingBundle singBundle) {
        if (!SingApplication.n() || singBundle.f20149n) {
            return false;
        }
        if (!singBundle.f20146k) {
            return true;
        }
        if (singBundle.f20141f != null) {
            return singBundle.f20141f.video;
        }
        return false;
    }

    protected Pair<Integer, Integer> mo6911O() {
        return new Pair(Integer.valueOf(0), Integer.valueOf(0));
    }

    protected void m24840P() {
        if (f23597u == -1) {
            switch (new Random().nextInt(5)) {
                case 0:
                    this.f23612s = C1947R.drawable.bg_sing_gradient_teal_purple;
                    break;
                case 1:
                    this.f23612s = C1947R.drawable.bg_sing_gradient_green_yellow;
                    break;
                case 2:
                    this.f23612s = C1947R.drawable.bg_sing_gradient_green_orange;
                    break;
                case 3:
                    this.f23612s = C1947R.drawable.bg_sing_gradient_blue_red;
                    break;
                case 4:
                    this.f23612s = C1947R.drawable.bg_sing_gradient_purple_peach;
                    break;
            }
            f23597u = this.f23612s;
        } else {
            this.f23612s = f23597u;
        }
        this.f23602i.m21639a("BACKGROUND_RESOURCE_KEY", this.f23612s);
    }

    protected void m24841Q() {
        f23597u = -1;
    }
}
