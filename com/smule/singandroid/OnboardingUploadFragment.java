package com.smule.singandroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.audio.AudioDefs.HeadphonesType;
import com.smule.android.logging.Analytics.UserPath;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.PerformanceManager.PerformanceResourceInfo;
import com.smule.android.network.managers.PerformanceManager.PreuploadResponse;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.JsonUtils;
import com.smule.singandroid.audio.GlitchType;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceCreateUtil;
import com.smule.singandroid.utils.PerformanceCreateUtil.PerformanceCreateListener;
import com.smule.singandroid.utils.PerformanceCreateUtil.ResourceCompressionListener;
import com.smule.singandroid.utils.PerformanceCreateUtil.ResourceUploadListener;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.AudioCompletionContext;
import com.smule.singandroid.utils.SingAnalytics.ReviewStepsType;
import java.util.ArrayList;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment
public class OnboardingUploadFragment extends BaseFragment {
    public static final String f19029e = OnboardingUploadFragment.class.getName();
    public static final String f19030f = f19029e;
    @InstanceState
    protected String f19031A;
    @InstanceState
    protected String f19032B;
    @InstanceState
    protected String f19033C;
    @InstanceState
    protected String f19034D;
    @InstanceState
    protected String f19035E;
    @InstanceState
    protected Float f19036F;
    @InstanceState
    protected Float f19037G;
    @InstanceState
    protected boolean f19038H;
    @InstanceState
    protected String f19039I;
    @InstanceState
    protected boolean f19040J;
    @InstanceState
    protected int f19041K;
    @InstanceState
    protected float f19042L;
    @InstanceState
    protected SongV2 f19043M;
    @InstanceState
    protected Bundle f19044N;
    @InstanceState
    protected SongbookEntry f19045O;
    @InstanceState
    protected String f19046P = null;
    protected SingBundle f19047Q;
    private TextAlertDialog f19048R;
    private boolean f19049S = false;
    private long f19050T;
    private String f19051U;
    private Handler f19052V = new Handler();
    private int f19053W = CtaButton.WIDTH_DIPS;
    private int f19054X = 1;
    private boolean f19055Y = false;
    private boolean f19056Z = false;
    private boolean aa = false;
    private Runnable ab = new C39021(this);
    private PerformanceCreateUtil ac;
    private Future<PreuploadResponse> ad;
    private boolean ae;
    private final ResourceCompressionListener af = new ResourceCompressionListener(this) {
        final /* synthetic */ OnboardingUploadFragment f19012a;

        {
            this.f19012a = r1;
        }

        public void mo6387a() {
        }

        public void mo6388a(String str) {
            this.f19012a.f19046P = str;
            this.f19012a.m20541O();
        }

        public void mo6389b() {
            this.f19012a.mo6495D();
        }
    };
    private final ResourceUploadListener ag = new ResourceUploadListener(this) {
        final /* synthetic */ OnboardingUploadFragment f19014a;

        class C39001 implements Runnable {
            final /* synthetic */ AnonymousClass11 f19013a;

            C39001(AnonymousClass11 anonymousClass11) {
                this.f19013a = anonymousClass11;
            }

            public void run() {
                if (this.f19013a.f19014a.isAdded()) {
                    this.f19013a.f19014a.m20575H();
                }
            }
        }

        {
            this.f19014a = r1;
        }

        public void mo6390a() {
            this.f19014a.m19838a(new C39001(this));
        }

        public void mo6391a(long j) {
            this.f19014a.f19070t = Long.valueOf(j);
            this.f19014a.m20541O();
        }

        public void mo6392b() {
            this.f19014a.mo6495D();
        }
    };
    private Runnable ah = new Runnable(this) {
        final /* synthetic */ OnboardingUploadFragment f19018a;

        {
            this.f19018a = r1;
        }

        public void run() {
            this.f19018a.aa = true;
            SingAnalytics.m26102a(PerformanceV2Util.m25938a(this.f19018a.f19075y, this.f19018a.f19043M), this.f19018a.f19047Q.f20149n ? UserPath.ONBOARDING : UserPath.OTHER, Math.round(((float) (System.currentTimeMillis() - this.f19018a.f19050T)) / 1000.0f), this.f19018a.ac.m25929a(), ReviewStepsType.UPLOAD, this.f19018a.m20544R(), null, false);
            SingAnalytics.m26060a(this.f19018a.f19047Q.f20154s, AudioCompletionContext.REVIEW_EXIT, Float.valueOf(this.f19018a.f19047Q.f20155t), null, this.f19018a.m20543Q(), null, this.f19018a.f19033C, this.f19018a.f19034D, this.f19018a.f19035E, DeviceSettings.n(), DeviceSettings.h());
            if (this.f19018a.f19047Q.f20158w != GlitchType.NONE) {
                SingAnalytics.m26109a(null, this.f19018a.f19047Q.f20158w, HeadphonesType.m17502a(this.f19018a.f19073w, this.f19018a.f19074x));
            }
            this.f19018a.m20538L();
        }
    };
    @ViewById
    protected View f19057g;
    @ViewById
    protected TextView f19058h;
    @ViewById
    protected ProgressBar f19059i;
    @ViewById
    protected TextView f19060j;
    @ViewById
    protected ImageView f19061k;
    @ViewById
    protected TextView f19062l;
    @ViewById
    protected TextView f19063m;
    @ViewById
    protected TextView f19064n;
    @ViewById
    protected View f19065o;
    @ViewById
    protected View f19066p;
    @InstanceState
    protected PostSingBundle f19067q;
    @InstanceState
    protected String f19068r = null;
    @InstanceState
    protected boolean f19069s = false;
    @InstanceState
    protected Long f19070t = null;
    @InstanceState
    protected boolean f19071u = false;
    @InstanceState
    protected boolean f19072v = false;
    @InstanceState
    protected boolean f19073w = false;
    @InstanceState
    protected boolean f19074x = false;
    @InstanceState
    protected PerformanceV2 f19075y;
    @InstanceState
    protected String f19076z;

    class C39021 implements Runnable {
        final /* synthetic */ OnboardingUploadFragment f19019a;

        C39021(OnboardingUploadFragment onboardingUploadFragment) {
            this.f19019a = onboardingUploadFragment;
        }

        public void run() {
            this.f19019a.m20538L();
        }
    }

    class C39042 implements Runnable {
        final /* synthetic */ OnboardingUploadFragment f19021a;

        class C39031 implements Runnable {
            final /* synthetic */ C39042 f19020a;

            C39031(C39042 c39042) {
                this.f19020a = c39042;
            }

            public void run() {
                this.f19020a.f19021a.ae = true;
                if (this.f19020a.f19021a.isAdded() && this.f19020a.f19021a.f19069s) {
                    this.f19020a.f19021a.mo6493B();
                }
            }
        }

        C39042(OnboardingUploadFragment onboardingUploadFragment) {
            this.f19021a = onboardingUploadFragment;
        }

        public void run() {
            new Handler().postDelayed(new C39031(this), 3000);
        }
    }

    class C39053 implements CustomAlertDialogListener {
        final /* synthetic */ OnboardingUploadFragment f19022a;

        C39053(OnboardingUploadFragment onboardingUploadFragment) {
            this.f19022a = onboardingUploadFragment;
        }

        public void mo6385a(CustomAlertDialog customAlertDialog) {
            this.f19022a.ah.run();
        }

        public void mo6386b(CustomAlertDialog customAlertDialog) {
            this.f19022a.m20553c("showProgressBarDialog - onBackOrCancelButton");
        }
    }

    class C39064 implements Runnable {
        final /* synthetic */ OnboardingUploadFragment f19023a;

        C39064(OnboardingUploadFragment onboardingUploadFragment) {
            this.f19023a = onboardingUploadFragment;
        }

        public void run() {
            this.f19023a.m20540N();
        }
    }

    class C39075 implements Runnable {
        final /* synthetic */ OnboardingUploadFragment f19024a;

        C39075(OnboardingUploadFragment onboardingUploadFragment) {
            this.f19024a = onboardingUploadFragment;
        }

        public void run() {
            this.f19024a.mo6494C();
        }
    }

    class C39086 implements Runnable {
        final /* synthetic */ OnboardingUploadFragment f19025a;

        C39086(OnboardingUploadFragment onboardingUploadFragment) {
            this.f19025a = onboardingUploadFragment;
        }

        public void run() {
            if (this.f19025a.f19056Z) {
                this.f19025a.m20575H();
            }
            this.f19025a.m20542P();
        }
    }

    class C39097 implements Runnable {
        final /* synthetic */ OnboardingUploadFragment f19026a;

        C39097(OnboardingUploadFragment onboardingUploadFragment) {
            this.f19026a = onboardingUploadFragment;
        }

        public void run() {
            this.f19026a.mo6494C();
        }
    }

    class C39108 implements Runnable {
        final /* synthetic */ OnboardingUploadFragment f19027a;

        C39108(OnboardingUploadFragment onboardingUploadFragment) {
            this.f19027a = onboardingUploadFragment;
        }

        public void run() {
            if (this.f19027a.f19056Z) {
                this.f19027a.m20575H();
            }
            ((PerformanceSaveActivity) this.f19027a.getActivity()).m20666q();
            this.f19027a.m20542P();
        }
    }

    class C39119 implements Runnable {
        final /* synthetic */ OnboardingUploadFragment f19028a;

        C39119(OnboardingUploadFragment onboardingUploadFragment) {
            this.f19028a = onboardingUploadFragment;
        }

        public void run() {
            this.f19028a.ah.run();
        }
    }

    public static OnboardingUploadFragment m20545a(PostSingBundle postSingBundle, Bundle bundle) {
        OnboardingUploadFragment onboardingUploadFragment_ = new OnboardingUploadFragment_();
        onboardingUploadFragment_.f19067q = postSingBundle;
        onboardingUploadFragment_.setArguments(bundle);
        return onboardingUploadFragment_;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f19047Q = this.f19067q.f19316b;
        this.ac = new PerformanceCreateUtil(this.f19070t, this.f19046P);
        if (bundle == null) {
            Log.b(f19029e, "onCreate - no saved instance state");
            Bundle arguments = getArguments();
            this.f19039I = arguments.getString("RECORDING_FILE_EXTRA_KEY");
            this.f19040J = arguments.getBoolean("PITCH_CORRECT_EXTRA_KEY", false);
            this.f19041K = arguments.getInt("SCORE_EXTRA_KEY", 0);
            this.f19042L = arguments.getFloat("USER_GAIN_DB", DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            this.f19073w = arguments.getBoolean("USED_HEADPHONE", false);
            this.f19074x = arguments.getBoolean("HEADPHONE_HAD_MIC", false);
            this.f19044N = arguments;
        } else {
            Log.b(f19029e, "onCreate - restoring from saved instance state");
        }
        Log.b(f19029e, this.f19047Q.toString());
        this.f19043M = this.f19047Q.m21659h();
        this.f19045O = this.f19047Q.f20139d;
        this.f19076z = getArguments().getString("EFFECT_PRESET");
        this.f19031A = getArguments().getString("FX_INITIAL");
        this.f19032B = getArguments().getString("FX_SELECTED");
        this.f19033C = getArguments().getString("FXS_UNIQUE_REVIEW");
        this.f19034D = getArguments().getString("ADJUSTED_SLIDER");
        this.f19035E = getArguments().getString("PLAY_PAUSE_COUNT");
        if (this.f19076z == null || this.f19076z.isEmpty()) {
            this.f19076z = "****";
        }
        this.f19036F = Float.valueOf(getArguments().getFloat("META_PARAM_1", -1.0f));
        this.f19037G = Float.valueOf(getArguments().getFloat("META_PARAM_2", -1.0f));
        if (this.f19036F.floatValue() == -1.0f) {
            this.f19036F = null;
        }
        if (this.f19037G.floatValue() == -1.0f) {
            this.f19037G = null;
        }
        this.f19038H = getArguments().getBoolean("PRESET_VIP_EXTRA_KEY");
        if (this.f19043M == null) {
            Log.d(f19029e, "onCreate - at the end, mSong was null!");
            if (!(this.f19075y == null || this.f19075y.songUid == null)) {
                this.f19043M = StoreManager.m18378a().m18416a(this.f19075y.songUid);
            }
            if (this.f19043M == null) {
                Log.e(f19029e, "onCreate - mSong was not able to be set to non-null!");
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    private void m20538L() {
        mo6498G();
        this.f19052V.removeCallbacks(this.ab);
    }

    @AfterViews
    protected void m20579a() {
        if (!getActivity().isFinishing()) {
            this.f19066p.setBackground(getResources().getDrawable(this.f19047Q.m21645b("BACKGROUND_RESOURCE_KEY", (int) C1947R.drawable.bg_sing_gradient_teal_purple)));
            this.f19057g.setVisibility(0);
            this.f19059i.setVisibility(8);
            this.f19058h.setText(this.f19045O.mo6291e());
            ImageUtils.a(this.f19045O.mo6295i(), this.f19061k);
            if (!this.f19071u) {
                m20540N();
            }
            if (this.f19069s) {
                mo6493B();
            }
        }
    }

    public void onStart() {
        super.onStart();
    }

    public void m20583z() {
        if (!this.ae) {
            MiscUtils.m25892a(this.f19061k, 0.5f, true, true, false, false, 0, 500, 1.2f, null);
            MiscUtils.m25892a(this.f19065o, 0.0f, false, false, true, false, 100, 1500, 1.2f, null);
            MiscUtils.m25892a(this.f19062l, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, true, true, true, true, 100, 1000, 1.2f, null);
            MiscUtils.m25892a(this.f19063m, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, true, true, true, true, 200, 1000, 1.2f, null);
            MiscUtils.m25892a(this.f19064n, 0.0f, false, false, true, true, 1250, 600, 1.2f, new C39042(this));
        }
    }

    private String m20539M() {
        return this.f19045O != null ? this.f19045O.mo6291e() : "";
    }

    private void m20553c(String str) {
        Log.b(f19029e, "savePerformance - called from source: " + str);
        if (this.f19071u) {
            m20542P();
        } else if (!this.f19049S) {
            m20540N();
        }
    }

    @UiThread
    protected void mo6492A() {
        if (isAdded()) {
            this.f19069s = true;
            if (this.ae) {
                mo6493B();
            }
        }
    }

    @UiThread
    protected void mo6493B() {
        m20576I();
        this.f19052V.post(this.ab);
    }

    @UiThread
    protected void mo6494C() {
        if (isAdded()) {
            SingAnalytics.m26106a(PerformanceV2Util.m25938a(this.f19075y, this.f19043M), UserPath.ONBOARDING, HeadphonesType.m17502a(this.f19073w, this.f19074x), this.f19076z, false, this.f19047Q.f20137b.m21631a(), ReviewStepsType.UPLOAD, m20544R(), null, false);
            this.f19048R = new TextAlertDialog(getActivity(), getString(C1947R.string.core_are_you_sure), getString(C1947R.string.performance_cancel_confirm));
            this.f19048R.m19806a(getString(C1947R.string.core_yes), getString(C1947R.string.core_no));
            this.f19048R.m19803a(new C39053(this));
            this.f19048R.show();
        }
    }

    @UiThread
    protected void mo6495D() {
        if (isAdded()) {
            TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(C1947R.string.performance_upload_error));
            textAlertDialog.m19804a(new C39064(this));
            textAlertDialog.m19809b(new C39075(this));
            m20577J();
            textAlertDialog.show();
            return;
        }
        Log.d(f19029e, "showFailUploadDialog - not added to fragment; aborting");
    }

    @UiThread
    protected void mo6496E() {
        if (isAdded()) {
            this.f19049S = false;
            TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(C1947R.string.performance_create_error_question));
            textAlertDialog.m19804a(new C39086(this));
            textAlertDialog.m19809b(new C39097(this));
            m20577J();
            textAlertDialog.show();
            return;
        }
        Log.d(f19029e, "showFailCreateDialog - not added to fragment; aborting");
    }

    @UiThread
    protected void mo6497F() {
        if (isAdded()) {
            this.f19049S = false;
            TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(C1947R.string.performance_create_error_question));
            textAlertDialog.m19804a(new C39108(this));
            textAlertDialog.m19809b(new C39119(this));
            m20577J();
            textAlertDialog.show();
            return;
        }
        Log.d(f19029e, "showFailPreuploadDialog - not added to fragment; aborting");
    }

    private void m20540N() {
        if (!this.f19071u) {
            this.f19049S = true;
            this.f19050T = System.currentTimeMillis();
            this.ac.m25930a(getActivity(), this.f19039I, this.f19044N, this.af);
        }
    }

    private void m20541O() {
        Log.b(f19029e, "prepareResourceDone");
        this.f19071u = true;
        this.f19049S = true;
        if (!this.aa) {
            if (this.f19048R == null || !this.f19048R.isShowing()) {
                m20542P();
            }
        }
    }

    private void m20542P() {
        if (!this.f19069s) {
            String M = m20539M();
            SingAnalytics.m26107a(PerformanceV2Util.m25938a(this.f19075y, this.f19043M), this.f19047Q.f20149n ? UserPath.ONBOARDING : UserPath.OTHER, HeadphonesType.m17502a(this.f19073w, this.f19074x), this.f19076z, false, this.f19047Q.f20137b.m21631a(), m20544R(), this.f19047Q.f20141f != null ? Boolean.valueOf(this.f19047Q.f20141f.video) : null, false);
            Log.b(f19029e, "createPerformance - performance title is: " + M);
            String c = this.f19045O.m18773s() ? this.f19045O.mo6289c() : this.f19045O.mo6290d();
            String c2 = this.f19045O.m18772r() ? this.f19045O.mo6289c() : null;
            String str = null;
            if (this.f19047Q.f20157v != null) {
                str = JsonUtils.m18987a(this.f19047Q.f20157v);
            } else {
                MagicCrittercism.a(new Exception("noMetaDataFoundException2"));
            }
            this.ac.m25931a(this.ad, getActivity(), this.f19047Q.m21652c(), this.f19047Q.m21643a(), this.f19047Q.m21648b(), this.f19047Q.f20142g, c, c2, this.f19045O.m18772r() ? ((ArrangementVersionLiteEntry) this.f19045O).f17623a.version : 0, this.f19047Q.f20145j, M, this.f19041K, this.f19076z, this.f19036F, this.f19037G, this.f19042L, this.f19072v, this.f19073w, "", null, null, str, true, null, false, this.f19038H, this.f19074x, new PerformanceCreateListener(this) {
                final /* synthetic */ OnboardingUploadFragment f19016a;

                class C39011 implements Runnable {
                    final /* synthetic */ AnonymousClass12 f19015a;

                    C39011(AnonymousClass12 anonymousClass12) {
                        this.f19015a = anonymousClass12;
                    }

                    public void run() {
                        this.f19015a.f19016a.mo6492A();
                    }
                }

                {
                    this.f19016a = r1;
                }

                public void mo6395a(ArrayList<PerformanceResourceInfo> arrayList) {
                    this.f19016a.f19067q.f19323i = arrayList;
                    this.f19016a.f19049S = true;
                }

                public void mo6394a(PerformanceV2 performanceV2, String str, String str2) {
                    this.f19016a.f19049S = false;
                    this.f19016a.f19075y = performanceV2;
                    this.f19016a.f19051U = str;
                    this.f19016a.f19068r = str2;
                    if (this.f19016a.f19051U == null) {
                        Log.c(OnboardingUploadFragment.f19029e, "performanceCreationDone - mSongUrl was null; setting to mPerformance.webUrl");
                        this.f19016a.f19051U = this.f19016a.f19075y.webUrl;
                    }
                    Log.b(OnboardingUploadFragment.f19029e, "Performance creation completed!");
                    SingAnalytics.m26060a(this.f19016a.f19047Q.f20154s, AudioCompletionContext.UPLOAD, Float.valueOf(this.f19016a.f19047Q.f20155t), str2, this.f19016a.m20543Q(), null, this.f19016a.f19033C, this.f19016a.f19034D, this.f19016a.f19035E, DeviceSettings.n(), DeviceSettings.h());
                    if (this.f19016a.f19047Q.f20158w != GlitchType.NONE) {
                        SingAnalytics.m26109a(str2, this.f19016a.f19047Q.f20158w, HeadphonesType.m17502a(this.f19016a.f19073w, this.f19016a.f19074x));
                    }
                    new Handler().postDelayed(new C39011(this), 200);
                }

                public void mo6393a(NetworkResponse networkResponse) {
                    this.f19016a.mo6497F();
                }

                public void mo6396b(NetworkResponse networkResponse) {
                    if (networkResponse.e()) {
                        ((BaseActivity) this.f19016a.getActivity()).a(networkResponse.f, false, this.f19016a.ah);
                        this.f19016a.m20577J();
                    } else {
                        this.f19016a.mo6496E();
                    }
                    this.f19016a.f19049S = false;
                }
            });
        }
    }

    private String m20543Q() {
        return null + "," + this.f19031A + "," + this.f19076z;
    }

    @UiThread
    protected void mo6498G() {
        Intent intent;
        Log.b(f19029e, "finishFragmentAndActivity");
        m19847b((BaseFragment) this);
        if (TrialSubscriptionActivity.m21947a(getActivity())) {
            intent = new Intent(getActivity(), TrialSubscriptionActivity_.class);
        } else {
            intent = new Intent(getActivity(), MasterActivity_.class);
        }
        startActivity(intent);
        getActivity().finish();
    }

    public void m20575H() {
        if (this.f19059i.getVisibility() == 0) {
            m20578K();
            this.f19052V.post(new Runnable(this) {
                final /* synthetic */ OnboardingUploadFragment f19017a;

                {
                    this.f19017a = r1;
                }

                public void run() {
                    int i = 0;
                    if (this.f19017a.isAdded() && !this.f19017a.f19056Z) {
                        if (this.f19017a.f19059i.getMax() > this.f19017a.f19059i.getProgress()) {
                            int max = this.f19017a.f19059i.getMax() - this.f19017a.f19059i.getProgress();
                            if (this.f19017a.f19055Y) {
                                max = this.f19017a.f19054X;
                                i = 5;
                            } else if (max <= 20) {
                                max = this.f19017a.f19053W;
                            } else {
                                max = this.f19017a.f19053W;
                                i = 1;
                            }
                            this.f19017a.f19059i.setProgress(i + this.f19017a.f19059i.getProgress());
                            this.f19017a.f19052V.postDelayed(this, (long) max);
                            return;
                        }
                        this.f19017a.f19055Y = false;
                    }
                }
            });
        }
    }

    public void m20576I() {
        this.f19055Y = true;
    }

    public void m20577J() {
        this.f19056Z = true;
    }

    public void m20578K() {
        this.f19059i.setProgress(0);
        this.f19055Y = false;
        this.f19056Z = false;
    }

    public String mo6383s() {
        return f19029e;
    }

    private String m20544R() {
        return SingAnalytics.m26140d(this.f19075y);
    }

    public boolean mo6400c() {
        if (this.f19069s) {
            m20538L();
        } else {
            mo6494C();
        }
        return true;
    }

    public void m20580a(Future<PreuploadResponse> future) {
        this.ad = future;
    }
}
