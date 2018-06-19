package com.smule.singandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.facebook.accountkit.internal.InternalLogger;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.mopub.volley.DefaultRetryPolicy;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.smule.android.audio.AudioDefs.HeadphonesType;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.UserPath;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponse$Status;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.FollowManager.ToggleFollowStateListener;
import com.smule.android.network.managers.PerformanceManager.PerformanceResourceInfo;
import com.smule.android.network.managers.PerformanceManager.PreuploadResponse;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.utils.JsonUtils;
import com.smule.singandroid.SingBundle.PerformanceType;
import com.smule.singandroid.audio.GlitchType;
import com.smule.singandroid.audio.Metadata;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.DeleteRecordingConfirmationDialog;
import com.smule.singandroid.dialogs.ProgressBarDialog;
import com.smule.singandroid.dialogs.ProgressBarDialog.ProgressBarDialogInterface;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.utils.CustomTypefaceSpan;
import com.smule.singandroid.utils.ImageToDiskUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceCreateUtil;
import com.smule.singandroid.utils.PerformanceCreateUtil.PerformanceCreateListener;
import com.smule.singandroid.utils.PerformanceCreateUtil.ResourceCompressionListener;
import com.smule.singandroid.utils.PerformanceCreateUtil.ResourceUploadListener;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.AudioCompletionContext;
import com.smule.singandroid.utils.SingAnalytics.ReviewStepsType;
import com.smule.singandroid.utils.TypefaceUtils;
import com.smule.singandroid.utils.UIHelper;
import com.smule.singandroid.video.ExoPlayerWrapper;
import com.smule.singandroid.video.ExoPlayerWrapper.ExoPlayerStateChangeListener;
import com.smule.singandroid.video.GetAudioTimeCallback;
import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import twitter4j.HttpResponseCode;

@EFragment
public class DuetJoinSaveFragment extends BaseFragment {
    private static final String f18547Y = DuetJoinSaveFragment.class.getName();
    public static final String f18548e = f18547Y;
    @InstanceState
    protected boolean f18549A = false;
    @InstanceState
    protected boolean f18550B = false;
    @InstanceState
    protected PerformanceV2 f18551C;
    @InstanceState
    protected String f18552D;
    @InstanceState
    protected String f18553E;
    @InstanceState
    protected String f18554F;
    @InstanceState
    protected String f18555G;
    @InstanceState
    protected String f18556H;
    @InstanceState
    protected String f18557I;
    @InstanceState
    protected Float f18558J;
    @InstanceState
    protected Float f18559K;
    @InstanceState
    protected boolean f18560L;
    @InstanceState
    protected String f18561M;
    @InstanceState
    protected int f18562N;
    @InstanceState
    protected float f18563O;
    @InstanceState
    protected SongV2 f18564P;
    @InstanceState
    protected boolean f18565Q = false;
    @InstanceState
    protected boolean f18566R = false;
    @InstanceState
    protected String f18567S;
    @InstanceState
    protected Bundle f18568T;
    @InstanceState
    protected boolean f18569U;
    @InstanceState
    protected float f18570V;
    @InstanceState
    protected String f18571W = null;
    boolean f18572X;
    private TextAlertDialog f18573Z;
    private final ResourceUploadListener aA = new ResourceUploadListener(this) {
        final /* synthetic */ DuetJoinSaveFragment f18526a;

        class C37851 implements Runnable {
            final /* synthetic */ AnonymousClass21 f18525a;

            C37851(AnonymousClass21 anonymousClass21) {
                this.f18525a = anonymousClass21;
            }

            public void run() {
                if (this.f18525a.f18526a.isAdded()) {
                    this.f18525a.f18526a.m19977N();
                }
            }
        }

        {
            this.f18526a = r1;
        }

        public void mo6390a() {
            this.f18526a.m19838a(new C37851(this));
        }

        public void mo6391a(long j) {
            this.f18526a.f18593y = Long.valueOf(j);
            this.f18526a.m19919V();
        }

        public void mo6392b() {
            this.f18526a.mo6405L();
        }
    };
    private TextAlertDialog aa;
    private boolean ab = false;
    private boolean ac = false;
    private long ad;
    private boolean ae;
    private Handler af = new Handler();
    private boolean ag = false;
    private boolean ah = false;
    private boolean ai = false;
    private Runnable aj = new C37841(this);
    private PerformanceCreateUtil ak;
    private ExoPlayerWrapper al;
    private Runnable am;
    private long an;
    private boolean ao;
    private boolean ap;
    private Bitmap aq;
    private Bitmap ar;
    private Bitmap as;
    private Bitmap at;
    private boolean au;
    private Future<PreuploadResponse> av;
    private boolean aw;
    private ProgressBarDialog ax;
    private final Runnable ay = new Runnable(this) {
        final /* synthetic */ DuetJoinSaveFragment f18516a;

        {
            this.f18516a = r1;
        }

        public void run() {
            Boolean bool;
            this.f18516a.ai = true;
            int round = Math.round(((float) (System.currentTimeMillis() - this.f18516a.ad)) / 1000.0f);
            String a = PerformanceV2Util.m25938a(this.f18516a.f18551C, this.f18516a.f18564P);
            UserPath userPath = this.f18516a.f18589u.f20149n ? UserPath.ONBOARDING : UserPath.OTHER;
            long a2 = this.f18516a.ak.m25929a();
            ReviewStepsType reviewStepsType = ReviewStepsType.UPLOAD;
            String d = this.f18516a.m19916S();
            if (this.f18516a.f18589u == null || this.f18516a.f18589u.f20141f == null) {
                bool = null;
            } else {
                bool = Boolean.valueOf(this.f18516a.f18589u.f20141f.video);
            }
            SingAnalytics.m26102a(a, userPath, round, a2, reviewStepsType, d, bool, this.f18516a.aa());
            SingAnalytics.m26060a(this.f18516a.f18589u.f20154s, AudioCompletionContext.REVIEW_EXIT, Float.valueOf(this.f18516a.f18589u.f20155t), null, this.f18516a.m19921X(), null, this.f18516a.f18555G, this.f18516a.f18556H, this.f18516a.f18557I, DeviceSettings.n(), DeviceSettings.h());
            if (this.f18516a.f18589u.f20158w != GlitchType.NONE) {
                SingAnalytics.m26109a(null, this.f18516a.f18589u.f20158w, HeadphonesType.m17502a(this.f18516a.f18549A, this.f18516a.f18550B));
            }
            ((PerformanceSaveActivity) this.f18516a.getActivity()).m20662a(this.f18516a.f18588t, null, false);
        }
    };
    private final ResourceCompressionListener az = new ResourceCompressionListener(this) {
        final /* synthetic */ DuetJoinSaveFragment f18524a;

        {
            this.f18524a = r1;
        }

        public void mo6387a() {
        }

        public void mo6388a(String str) {
            this.f18524a.f18571W = str;
            this.f18524a.m19919V();
        }

        public void mo6389b() {
            this.f18524a.mo6405L();
        }
    };
    @ViewById
    protected TextView f18574f;
    @ViewById
    protected ProgressBar f18575g;
    @ViewById
    protected TextView f18576h;
    @ViewById
    protected ProfileImageWithVIPBadge f18577i;
    @ViewById
    protected ProfileImageWithVIPBadge f18578j;
    @ViewById
    protected ProfileImageWithVIPBadge f18579k;
    @ViewById
    protected View f18580l;
    @ViewById
    protected TextView f18581m;
    @ViewById
    protected View f18582n;
    @ViewById
    protected Button f18583o;
    @ViewById
    protected Button f18584p;
    @ViewById
    protected TextureView f18585q;
    @ViewById
    protected Button f18586r;
    @ViewById
    protected View f18587s;
    @InstanceState
    protected PostSingBundle f18588t;
    protected SingBundle f18589u;
    @InstanceState
    protected String f18590v = null;
    @InstanceState
    protected boolean f18591w = false;
    @InstanceState
    protected boolean f18592x = false;
    @InstanceState
    protected Long f18593y = null;
    @InstanceState
    protected boolean f18594z = false;

    class C37841 implements Runnable {
        final /* synthetic */ DuetJoinSaveFragment f18523a;

        C37841(DuetJoinSaveFragment duetJoinSaveFragment) {
            this.f18523a = duetJoinSaveFragment;
        }

        public void run() {
            this.f18523a.m19939c(true);
        }
    }

    class C37872 implements Runnable {
        final /* synthetic */ DuetJoinSaveFragment f18534a;

        C37872(DuetJoinSaveFragment duetJoinSaveFragment) {
            this.f18534a = duetJoinSaveFragment;
        }

        public void run() {
            if (this.f18534a.isAdded()) {
                Log.b(DuetJoinSaveFragment.f18547Y, "retry savePerformance");
                this.f18534a.m19938c(InternalLogger.EVENT_PARAM_EXTRAS_RETRY);
            }
        }
    }

    class C37883 implements ToggleFollowStateListener {
        final /* synthetic */ DuetJoinSaveFragment f18535a;

        C37883(DuetJoinSaveFragment duetJoinSaveFragment) {
            this.f18535a = duetJoinSaveFragment;
        }

        public void mo6399a(boolean z, boolean z2, boolean z3) {
            this.f18535a.au = false;
            this.f18535a.mo6407b(z3);
            if (z && !z3 && z2) {
                this.f18535a.mo6401E();
            }
        }
    }

    class C37904 implements Runnable {
        final /* synthetic */ DuetJoinSaveFragment f18537a;

        class C37891 implements Runnable {
            final /* synthetic */ C37904 f18536a;

            C37891(C37904 c37904) {
                this.f18536a = c37904;
            }

            public void run() {
                this.f18536a.f18537a.aw = true;
                this.f18536a.f18537a.m19939c(true);
            }
        }

        C37904(DuetJoinSaveFragment duetJoinSaveFragment) {
            this.f18537a = duetJoinSaveFragment;
        }

        public void run() {
            if (!this.f18537a.f18572X) {
                new Handler().postDelayed(new C37891(this), 2000);
            }
        }
    }

    class C37925 implements Runnable {
        final /* synthetic */ DuetJoinSaveFragment f18539a;

        class C37911 implements Runnable {
            final /* synthetic */ C37925 f18538a;

            C37911(C37925 c37925) {
                this.f18538a = c37925;
            }

            public void run() {
                this.f18538a.f18539a.aw = true;
            }
        }

        C37925(DuetJoinSaveFragment duetJoinSaveFragment) {
            this.f18539a = duetJoinSaveFragment;
        }

        public void run() {
            if (!this.f18539a.f18572X) {
                new Handler().postDelayed(new C37911(this), 2000);
            }
        }
    }

    class C37936 implements AnimationListener {
        final /* synthetic */ DuetJoinSaveFragment f18540a;

        C37936(DuetJoinSaveFragment duetJoinSaveFragment) {
            this.f18540a = duetJoinSaveFragment;
        }

        public void onAnimationStart(Animation animation) {
            this.f18540a.f18580l.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        }

        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    class C37968 implements Runnable {
        final /* synthetic */ DuetJoinSaveFragment f18545a;

        class C37951 implements Runnable {
            final /* synthetic */ C37968 f18544a;

            C37951(C37968 c37968) {
                this.f18544a = c37968;
            }

            public void run() {
                this.f18544a.f18545a.aw = true;
                this.f18544a.f18545a.m19939c(true);
            }
        }

        C37968(DuetJoinSaveFragment duetJoinSaveFragment) {
            this.f18545a = duetJoinSaveFragment;
        }

        public void run() {
            if (!this.f18545a.f18572X) {
                new Handler().postDelayed(new C37951(this), 2000);
            }
        }
    }

    class C37979 implements AnimationListener {
        final /* synthetic */ DuetJoinSaveFragment f18546a;

        C37979(DuetJoinSaveFragment duetJoinSaveFragment) {
            this.f18546a = duetJoinSaveFragment;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.f18546a.f18576h.setText(this.f18546a.getResources().getString(C1947R.string.duet_join_just_followed_title) + "\n");
            MiscUtils.m25892a(this.f18546a.f18576h, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, true, true, true, true, 100, 500, 1.2f, null);
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    public static DuetJoinSaveFragment m19926a(PostSingBundle postSingBundle, Bundle bundle) {
        DuetJoinSaveFragment duetJoinSaveFragment_ = new DuetJoinSaveFragment_();
        duetJoinSaveFragment_.f18588t = postSingBundle;
        duetJoinSaveFragment_.setArguments(bundle);
        return duetJoinSaveFragment_;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.f18588t != null) {
            this.f18589u = this.f18588t.f19316b;
        }
        this.ak = new PerformanceCreateUtil(this.f18593y, this.f18571W);
        Bundle arguments = getArguments();
        if (bundle == null) {
            Log.b(f18547Y, "onCreate - no saved instance state");
            this.f18561M = arguments.getString("RECORDING_FILE_EXTRA_KEY");
            this.f18562N = arguments.getInt("SCORE_EXTRA_KEY", 0);
            this.f18563O = arguments.getFloat("USER_GAIN_DB", DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            this.f18549A = arguments.getBoolean("USED_HEADPHONE", false);
            this.f18550B = arguments.getBoolean("HEADPHONE_HAD_MIC", false);
            this.f18565Q = arguments.getBoolean("IS_FOLLOWING_PARTNER_KEY", false);
            this.f18567S = arguments.getString("VIDEO_FILE");
            this.f18570V = arguments.getFloat("JOIN_MAX_POWER_POSITION_SECONDS", 0.0f);
            this.f18568T = arguments;
            this.as = ((PerformanceSaveActivity) getActivity()).m20668s();
            this.f18569U = !TextUtils.isEmpty(this.f18567S);
        } else {
            Log.b(f18547Y, "onCreate - restoring from saved instance state");
        }
        this.f18564P = this.f18589u.m21659h();
        this.f18551C = this.f18589u.f20141f;
        this.f18552D = arguments.getString("EFFECT_PRESET");
        this.f18553E = arguments.getString("FX_INITIAL");
        this.f18554F = arguments.getString("FX_SELECTED");
        this.f18555G = arguments.getString("FXS_UNIQUE_REVIEW");
        this.f18556H = arguments.getString("ADJUSTED_SLIDER");
        this.f18557I = arguments.getString("PLAY_PAUSE_COUNT");
        if (this.f18552D == null || this.f18552D.isEmpty()) {
            this.f18552D = "****";
        }
        this.f18558J = Float.valueOf(arguments.getFloat("META_PARAM_1", -1.0f));
        this.f18559K = Float.valueOf(arguments.getFloat("META_PARAM_2", -1.0f));
        if (this.f18558J.floatValue() == -1.0f) {
            this.f18558J = null;
        }
        if (this.f18559K.floatValue() == -1.0f) {
            this.f18559K = null;
        }
        this.f18560L = arguments.getBoolean("PRESET_VIP_EXTRA_KEY");
        if (this.f18564P == null) {
            Log.d(f18547Y, "onCreate - at the end, mSong was null!");
            if (!(this.f18551C == null || this.f18551C.songUid == null)) {
                this.f18564P = StoreManager.m18378a().m18416a(this.f18551C.songUid);
            }
            if (this.f18564P == null) {
                Log.e(f18547Y, "onCreate - mSong was not able to be set to non-null!");
            }
        }
        this.am = new C37872(this);
    }

    public void onResume() {
        super.onResume();
        MiscUtils.m25891a(getActivity(), true);
    }

    public void m19981a() {
        if (!this.aw) {
            if (this.f18589u.f20141f.l()) {
                m19964A();
            } else if (this.f18565Q) {
                m19966C();
            } else {
                m19965B();
            }
        }
    }

    public void onStart() {
        super.onStart();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f18573Z = null;
        this.aa = null;
        m19851c(this.am);
        if (this.al != null) {
            this.al.m26368b();
            this.al = null;
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Click
    protected void m19988z() {
        this.f18566R = true;
        if (!this.ae) {
            this.ae = true;
            if (!(FollowManager.m18204a().m18222a(this.f18589u.f20141f.accountIcon.accountId) || this.au)) {
                this.au = true;
                FollowManager.m18204a().m18215a(Long.valueOf(this.f18589u.f20141f.accountIcon.accountId), new C37883(this));
            }
            mo6407b(false);
        }
    }

    protected void m19964A() {
        MiscUtils.m25892a(this.f18580l, 0.5f, true, true, false, false, 0, 500, 1.2f, null);
        MiscUtils.m25892a(this.f18576h, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, true, true, true, true, 350, 1000, 1.2f, null);
        MiscUtils.m25892a(this.f18584p, 0.0f, false, false, true, false, 1950, 600, 1.2f, null);
        MiscUtils.m25893a(this.f18581m, 0.0f, false, false, true, true, 1950, 600, 1.2f, null, true, 0);
        MiscUtils.m25893a(this.f18586r, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, false, false, true, true, 3550, 600, 1.2f, new C37904(this), true, 10);
    }

    protected void m19965B() {
        MiscUtils.m25892a(this.f18580l, 0.5f, true, true, false, false, 0, 500, 1.2f, null);
        MiscUtils.m25892a(this.f18576h, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, true, true, true, true, 350, 1000, 1.2f, null);
        MiscUtils.m25892a(this.f18584p, 0.0f, false, false, true, false, 1950, 600, 1.2f, null);
        MiscUtils.m25893a(this.f18581m, 0.0f, false, false, true, true, 1950, 600, 1.2f, null, true, 0);
        MiscUtils.m25893a(this.f18586r, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, false, false, true, true, 3550, 600, 1.2f, new C37925(this), true, 10);
    }

    protected void m19966C() {
        final TranslateAnimation translateAnimation = new TranslateAnimation(2, -1.0f, 0, 0.0f, 1, 0.0f, 1, 0.0f);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(new AnticipateOvershootInterpolator(0.7f));
        translateAnimation.setAnimationListener(new C37936(this));
        final TranslateAnimation translateAnimation2 = new TranslateAnimation(2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0, 0.0f, 1, 0.0f, 1, 0.0f);
        translateAnimation2.setDuration(1000);
        translateAnimation2.setFillAfter(true);
        translateAnimation2.setInterpolator(new AnticipateOvershootInterpolator(0.7f));
        new Handler().post(new Runnable(this) {
            final /* synthetic */ DuetJoinSaveFragment f18543c;

            public void run() {
                this.f18543c.f18577i.startAnimation(translateAnimation);
                this.f18543c.f18578j.startAnimation(translateAnimation2);
            }
        });
        MiscUtils.m25892a(this.f18576h, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, true, true, true, true, 500, 600, 1.2f, null);
        MiscUtils.m25893a(this.f18581m, 0.0f, false, false, true, true, 1200, 1000, 1.2f, new C37968(this), true, 0);
    }

    @UiThread
    protected void mo6407b(boolean z) {
        if (isAdded() && getActivity() != null) {
            if (z) {
                m19849b(getActivity().getString(C1947R.string.profile_follow_limit_reached));
            }
            UIHelper.m26198a(getActivity(), this.f18589u.f20141f.accountIcon.accountId, this.f18584p);
            this.ae = false;
        }
    }

    @Click
    protected void m19967D() {
        m19939c(true);
    }

    private void m19939c(boolean z) {
        if (isAdded() && getActivity() != null) {
            if (this.f18591w && this.aw) {
                this.af.removeCallbacks(this.aj);
                ((PerformanceSaveActivity) getActivity()).m20662a(this.f18588t, this.f18551C, z);
                return;
            }
            m19971H();
        }
    }

    @UiThread
    protected void mo6401E() {
        if (isAdded() && getActivity() != null) {
            this.f18572X = true;
            this.f18586r.clearAnimation();
            this.f18586r.setVisibility(8);
            this.f18576h.clearAnimation();
            this.f18584p.clearAnimation();
            this.f18581m.clearAnimation();
            this.f18582n.clearAnimation();
            Animation alphaAnimation = new AlphaAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f);
            alphaAnimation.setDuration(100);
            alphaAnimation.setFillAfter(true);
            alphaAnimation.setAnimationListener(new C37979(this));
            this.f18576h.startAnimation(alphaAnimation);
            this.f18581m.setText(MiscUtils.m25878a(getString(C1947R.string.duet_join_just_followed_detail), "{0}", this.f18589u.f20141f.accountIcon.handle, new CustomTypefaceSpan(getActivity(), this.f18581m.getTextSize(), C1947R.color.body_text_white, TypefaceUtils.m26191b())));
            MiscUtils.m25893a(this.f18581m, 0.0f, false, false, true, true, 1200, 1000, 1.2f, new Runnable(this) {
                final /* synthetic */ DuetJoinSaveFragment f18512a;

                class C37831 implements Runnable {
                    final /* synthetic */ AnonymousClass10 f18511a;

                    C37831(AnonymousClass10 anonymousClass10) {
                        this.f18511a = anonymousClass10;
                    }

                    public void run() {
                        this.f18511a.f18512a.aw = true;
                        this.f18511a.f18512a.m19939c(true);
                    }
                }

                {
                    this.f18512a = r1;
                }

                public void run() {
                    new Handler().postDelayed(new C37831(this), 2000);
                }
            }, true, 0);
            this.f18584p.setVisibility(8);
            this.f18577i.setVisibility(0);
            alphaAnimation = new TranslateAnimation(2, -1.0f, 0, 0.0f, 1, 0.0f, 1, 0.0f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setFillAfter(true);
            alphaAnimation.setInterpolator(new OvershootInterpolator(1.2f));
            alphaAnimation.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ DuetJoinSaveFragment f18513a;

                {
                    this.f18513a = r1;
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }
            });
            this.f18577i.startAnimation(alphaAnimation);
            alphaAnimation = new TranslateAnimation(0, 0.0f, 0, (float) ((getResources().getDimensionPixelSize(C1947R.dimen.upload_portrait_center_margin) / 2) + (this.f18579k.getMeasuredWidth() / 2)), 1, 0.0f, 1, 0.0f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setFillBefore(true);
            alphaAnimation.setFillAfter(true);
            alphaAnimation.setInterpolator(new OvershootInterpolator(3.0f));
            alphaAnimation.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ DuetJoinSaveFragment f18514a;

                {
                    this.f18514a = r1;
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }
            });
            this.f18579k.startAnimation(alphaAnimation);
        }
    }

    @AfterViews
    protected void m19969F() {
        if (!getActivity().isFinishing()) {
            this.f18587s.setBackground(getResources().getDrawable(this.f18589u.m21645b("BACKGROUND_RESOURCE_KEY", (int) C1947R.drawable.bg_sing_gradient_teal_purple)));
            if (this.f18589u.f20141f.l()) {
                this.f18576h.setText(getResources().getString(C1947R.string.duet_join_self_title));
                this.f18584p.setVisibility(8);
                this.f18581m.setText(getResources().getString(C1947R.string.duet_join_self_detail));
                this.f18577i.setProfilePicUrl(UserManager.a().h());
                this.f18577i.setVIP(SubscriptionManager.a().b());
                this.f18578j.setProfilePicUrl(this.f18589u.f20141f.accountIcon.picUrl);
                this.f18578j.setVIP(this.f18589u.f20141f.accountIcon.a());
                this.f18579k.setVisibility(8);
            } else if (this.f18565Q) {
                this.f18576h.setText(getResources().getString(C1947R.string.duet_join_already_following_title));
                this.f18584p.setVisibility(8);
                this.f18581m.setText(MessageFormat.format(getString(C1947R.string.duet_join_already_following_detail), new Object[]{this.f18589u.f20141f.accountIcon.handle}));
                this.f18577i.setProfilePicUrl(UserManager.a().h());
                this.f18577i.setVIP(SubscriptionManager.a().b());
                this.f18578j.setProfilePicUrl(this.f18589u.f20141f.accountIcon.picUrl);
                this.f18578j.setVIP(this.f18589u.f20141f.accountIcon.a());
                this.f18579k.setVisibility(8);
            } else {
                this.f18576h.setText(MessageFormat.format(getString(C1947R.string.duet_join_not_following_title), new Object[]{this.f18589u.f20141f.accountIcon.handle}));
                this.f18577i.setVisibility(8);
                this.f18581m.setText(getResources().getString(C1947R.string.duet_join_not_following_detail));
                this.f18577i.setProfilePicUrl(UserManager.a().h());
                this.f18577i.setVIP(SubscriptionManager.a().b());
                this.f18579k.setProfilePicUrl(this.f18589u.f20141f.accountIcon.picUrl);
                this.f18579k.setVIP(this.f18589u.f20141f.accountIcon.a());
                this.f18578j.setVisibility(4);
                this.f18578j.setImageDrawable(17170445);
                this.f18586r.setVisibility(0);
            }
            this.f18574f.setText(m19917T());
            this.f18575g.setVisibility(8);
            this.f18583o.setVisibility(8);
            m19938c("updateAfterViewBinding - auto-upload for joins");
            if (this.f18591w) {
                mo6403J();
            }
            if (this.f18569U && this.f18589u.f20137b == PerformanceType.DUET && this.f18551C != null) {
                m19922Y();
            }
        }
    }

    @Click
    protected void m19970G() {
        if (isAdded()) {
            this.f18586r.setVisibility(8);
            this.aw = true;
            if (this.f18591w) {
                m19939c(true);
            } else {
                m19971H();
            }
        }
    }

    protected void m19971H() {
        if (isAdded() && getActivity() != null && this.aw && this.f18586r.getVisibility() != 0) {
            if ((this.aa == null || !this.aa.isShowing()) && this.ax == null) {
                this.ax = new ProgressBarDialog(getActivity(), getString(C1947R.string.core_saving), new ProgressBarDialogInterface(this) {
                    final /* synthetic */ DuetJoinSaveFragment f18515a;

                    {
                        this.f18515a = r1;
                    }

                    public void mo6384a() {
                        if (this.f18515a.ax != null) {
                            this.f18515a.ax.dismiss();
                            this.f18515a.ax = null;
                            this.f18515a.mo6404K();
                        }
                    }
                });
                this.ax.m23685b(5);
                this.ax.show();
            }
        }
    }

    private String m19916S() {
        return SingAnalytics.m26140d(this.f18551C);
    }

    private String m19917T() {
        if (this.f18564P != null) {
            return this.f18564P.title;
        }
        if (this.f18551C != null) {
            return this.f18551C.arrangementVersion.arrangement.name;
        }
        return "";
    }

    private void m19938c(String str) {
        Log.b(f18547Y, "savePerformance - called from source: " + str);
        if (this.f18592x) {
            m19920W();
        } else if (!this.ac) {
            m19918U();
        }
        if (this.aw) {
            m19971H();
        }
    }

    @UiThread
    protected void mo6408d(int i) {
        Intent intent = new Intent();
        intent.putExtra("CHANGE_MADE_CODE", i);
        super.m19824a(intent);
    }

    @UiThread
    protected void mo6402I() {
        if (isAdded()) {
            this.f18591w = true;
            mo6403J();
        }
    }

    @UiThread
    protected void mo6403J() {
        m19978O();
        if (this.f18586r.getVisibility() != 0) {
            m19939c(true);
        }
    }

    @UiThread
    protected void mo6404K() {
        if (isAdded()) {
            SingAnalytics.m26106a(PerformanceV2Util.m25938a(this.f18551C, this.f18564P), this.f18589u.f20149n ? UserPath.ONBOARDING : UserPath.OTHER, HeadphonesType.m17502a(this.f18549A, this.f18550B), this.f18552D, true, Analytics.m17828a(this.f18551C), ReviewStepsType.UPLOAD, m19916S(), Boolean.valueOf(this.f18551C.video), this.f18569U);
            CustomAlertDialogListener anonymousClass15 = new CustomAlertDialogListener(this) {
                final /* synthetic */ DuetJoinSaveFragment f18517a;

                {
                    this.f18517a = r1;
                }

                public void mo6385a(CustomAlertDialog customAlertDialog) {
                    this.f18517a.m19938c("showCancelDialog - onBackOrCancelButton");
                }

                public void mo6386b(CustomAlertDialog customAlertDialog) {
                    this.f18517a.ay.run();
                }
            };
            this.f18573Z = new DeleteRecordingConfirmationDialog(getActivity());
            this.f18573Z.m19803a(anonymousClass15);
            this.f18573Z.show();
        }
    }

    @UiThread
    protected void mo6405L() {
        if (isAdded()) {
            this.ac = false;
            TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(C1947R.string.performance_upload_error));
            textAlertDialog.m19804a(new Runnable(this) {
                final /* synthetic */ DuetJoinSaveFragment f18518a;

                {
                    this.f18518a = r1;
                }

                public void run() {
                    this.f18518a.m19918U();
                }
            });
            if (this.f18573Z != null) {
                this.f18573Z.dismiss();
                this.f18573Z = null;
            }
            m19979P();
            textAlertDialog.show();
            return;
        }
        Log.d(f18547Y, "showFailUploadDialog - not added to fragment; aborting");
    }

    protected void m19982a(NetworkResponse networkResponse) {
        this.ac = false;
        this.ab = false;
        if (isAdded()) {
            if (this.f18573Z != null) {
                this.f18573Z.dismiss();
                this.f18573Z = null;
            }
            m19979P();
            if (networkResponse.e()) {
                ((BaseActivity) getActivity()).a(networkResponse.f, false, this.ay);
                return;
            }
            Runnable anonymousClass17 = new Runnable(this) {
                final /* synthetic */ DuetJoinSaveFragment f18519a;

                {
                    this.f18519a = r1;
                }

                public void run() {
                    this.f18519a.mo6404K();
                }
            };
            if (networkResponse.a == NetworkResponse$Status.CONNECTION_TIMEOUT) {
                ((BaseActivity) getActivity()).a(this.am, anonymousClass17);
                return;
            }
            if (networkResponse.b == PointerIconCompat.TYPE_GRAB || networkResponse.b == PointerIconCompat.TYPE_GRABBING) {
                this.aa = new TextAlertDialog(getActivity(), null, getString(C1947R.string.performance_create_error_question_blocked), true, false);
                this.aa.m19804a(this.ay);
                this.aa.m19809b(this.ay);
                this.aa.m19806a(getString(C1947R.string.core_ok), "");
            } else {
                this.aa = new TextAlertDialog(getActivity(), getString(C1947R.string.performance_create_error_question));
                this.aa.m19804a(new Runnable(this) {
                    final /* synthetic */ DuetJoinSaveFragment f18520a;

                    {
                        this.f18520a = r1;
                    }

                    public void run() {
                        if (this.f18520a.ah) {
                            this.f18520a.m19977N();
                        }
                        this.f18520a.m19920W();
                    }
                });
                this.aa.m19809b(anonymousClass17);
            }
            this.aa.show();
            return;
        }
        Log.d(f18547Y, "showFailCreateDialog - not added to fragment; aborting");
    }

    protected void m19976M() {
        this.ac = false;
        this.ab = false;
        if (isAdded()) {
            m19979P();
            final PerformanceSaveActivity performanceSaveActivity = (PerformanceSaveActivity) getActivity();
            performanceSaveActivity.a(new Runnable(this) {
                final /* synthetic */ DuetJoinSaveFragment f18522b;

                public void run() {
                    performanceSaveActivity.m20666q();
                    if (this.f18522b.ah) {
                        this.f18522b.m19977N();
                    }
                    this.f18522b.am.run();
                }
            }, this.ay);
            return;
        }
        Log.d(f18547Y, "showFailCreateDialog - not added to fragment; aborting");
    }

    private void m19918U() {
        if (!this.f18592x) {
            this.ac = true;
            this.ad = System.currentTimeMillis();
            this.ak.m25930a(getActivity(), this.f18561M, this.f18568T, this.az);
        }
    }

    private void m19919V() {
        Log.b(f18547Y, "prepareResourceDone");
        this.f18592x = true;
        this.ac = true;
        if (!this.ai) {
            if ((this.f18573Z == null || !this.f18573Z.isShowing()) && getActivity() != null) {
                m19920W();
            }
        }
    }

    private void m19920W() {
        m19851c(this.am);
        if (!this.f18591w && !this.ab) {
            m19971H();
            if (this.f18569U && this.f18589u.f20137b == PerformanceType.DUET && this.at == null) {
                if (System.currentTimeMillis() - this.an < 3000) {
                    Log.b(f18547Y, "wait for composite to complete");
                    m19839a(this.am, 3000);
                    return;
                }
                this.at = m19933b(this.as, this.ar, this.f18589u.f20142g != 1);
            }
            Log.b(f18547Y, "mCompsiteBitmap:" + this.at);
            this.ab = true;
            String T = m19917T();
            SingAnalytics.m26107a(PerformanceV2Util.m25938a(this.f18551C, this.f18564P), this.f18589u.f20149n ? UserPath.ONBOARDING : UserPath.OTHER, HeadphonesType.m17502a(this.f18549A, this.f18550B), this.f18552D, true, this.f18589u.f20137b.m21631a(), m19916S(), this.f18589u.f20141f != null ? Boolean.valueOf(this.f18589u.f20141f.video) : null, aa());
            Log.b(f18547Y, "createPerformance - performance title is: " + T);
            String str = this.f18551C.songUid;
            String str2 = this.f18551C.arrangementVersion != null ? this.f18551C.arrangementVersion.arrangement.key : null;
            int i = this.f18551C.arrangementVersion != null ? this.f18551C.arrangementVersion.version : 0;
            String str3 = null;
            if (this.f18589u.f20157v != null) {
                if (!this.f18569U) {
                    this.f18589u.f20157v.audioPower = null;
                }
                str3 = JsonUtils.m18987a(this.f18589u.f20157v);
            } else {
                MagicCrittercism.a(new Exception("noMetaDataFoundException"));
            }
            String str4 = null;
            if (this.f18589u.f20141f.video) {
                str4 = this.f18589u.m21657f();
            }
            this.ak.m25931a(this.av, getActivity(), this.f18589u.m21652c(), this.f18589u.m21643a(), this.f18589u.m21648b(), this.f18589u.f20142g, str, str2, i, this.f18589u.f20145j, T, this.f18562N, this.f18552D, this.f18558J, this.f18559K, this.f18563O, this.f18594z, this.f18549A, "", this.at, this.f18567S, str3, this.f18589u.f20149n, str4, this.f18589u.m21658g(), this.f18560L, this.f18550B, new PerformanceCreateListener(this) {
                final /* synthetic */ DuetJoinSaveFragment f18528a;

                class C37861 implements Runnable {
                    final /* synthetic */ AnonymousClass22 f18527a;

                    C37861(AnonymousClass22 anonymousClass22) {
                        this.f18527a = anonymousClass22;
                    }

                    public void run() {
                        this.f18527a.f18528a.mo6402I();
                    }
                }

                {
                    this.f18528a = r1;
                }

                public void mo6395a(ArrayList<PerformanceResourceInfo> arrayList) {
                    this.f18528a.f18588t.f19323i = arrayList;
                    this.f18528a.ac = true;
                }

                public void mo6394a(PerformanceV2 performanceV2, String str, String str2) {
                    this.f18528a.ac = false;
                    this.f18528a.ab = false;
                    this.f18528a.f18590v = str2;
                    this.f18528a.f18551C = performanceV2;
                    this.f18528a.f18591w = true;
                    Log.b(DuetJoinSaveFragment.f18547Y, "Performance creation completed!");
                    SingAnalytics.m26060a(this.f18528a.f18589u.f20154s, AudioCompletionContext.UPLOAD, Float.valueOf(this.f18528a.f18589u.f20155t), str2, this.f18528a.m19921X(), null, this.f18528a.f18555G, this.f18528a.f18556H, this.f18528a.f18557I, DeviceSettings.n(), DeviceSettings.h());
                    if (this.f18528a.f18589u.f20158w != GlitchType.NONE) {
                        SingAnalytics.m26109a(str2, this.f18528a.f18589u.f20158w, HeadphonesType.m17502a(this.f18528a.f18549A, this.f18528a.f18550B));
                    }
                    if (this.f18528a.f18569U) {
                        SingAnalytics.m26090a(this.f18528a.f18590v, this.f18528a.f18589u.m21644b("VIDEO_STATS_CAMERA_FPS", 0.0f), this.f18528a.f18589u.m21644b("VIDEO_STATS_ENCODER_FPS", 0.0f), this.f18528a.f18589u.m21645b("VIDEO_STATS_CAMERA_TO_ENCODER_DROPS", 0), this.f18528a.f18589u.m21644b("VIDEO_STATS_MUXER_FPS", 0.0f), this.f18528a.f18589u.m21645b("VIDEO_STATS_ENCODER_TO_MUXER_DROPS", 0));
                    }
                    new Handler().postDelayed(new C37861(this), 200);
                }

                public void mo6393a(NetworkResponse networkResponse) {
                    this.f18528a.m19976M();
                }

                public void mo6396b(NetworkResponse networkResponse) {
                    this.f18528a.m19982a(networkResponse);
                }
            });
        }
    }

    private String m19921X() {
        return null + "," + this.f18553E + "," + this.f18552D;
    }

    public void m19977N() {
        if (this.f18575g.getVisibility() == 0) {
            m19980Q();
            this.af.post(new Runnable(this) {
                final /* synthetic */ DuetJoinSaveFragment f18529a;

                {
                    this.f18529a = r1;
                }

                public void run() {
                    int i = 1;
                    if (!this.f18529a.isAdded() || this.f18529a.ah) {
                        return;
                    }
                    if (this.f18529a.f18575g.getMax() > this.f18529a.f18575g.getProgress()) {
                        int i2;
                        int max = this.f18529a.f18575g.getMax() - this.f18529a.f18575g.getProgress();
                        if (this.f18529a.ag) {
                            i2 = 5;
                        } else if (max <= 20) {
                            i = CtaButton.WIDTH_DIPS;
                            i2 = 0;
                        } else {
                            i2 = 1;
                            i = 150;
                        }
                        this.f18529a.f18575g.setProgress(i2 + this.f18529a.f18575g.getProgress());
                        this.f18529a.af.postDelayed(this, (long) i);
                        return;
                    }
                    this.f18529a.ag = false;
                }
            });
        }
    }

    public void m19978O() {
        this.ag = true;
    }

    public void m19979P() {
        if (this.ax != null) {
            this.ax.dismiss();
            this.ax = null;
        }
        this.ah = true;
    }

    public void m19980Q() {
        this.f18575g.setProgress(0);
        this.ag = false;
        this.ah = false;
    }

    public String mo6383s() {
        return f18547Y;
    }

    public boolean mo6400c() {
        if (this.f18591w) {
            m19939c(true);
        } else {
            mo6404K();
        }
        return true;
    }

    private void m19922Y() {
        this.an = System.currentTimeMillis();
        Log.b(f18547Y, "mJoinMaxPowerPositionSeconds:" + this.f18570V);
        m19929a(this.f18570V);
        m19923Z();
    }

    private void m19929a(float f) {
        String str;
        float f2;
        final float f3;
        if (!TextUtils.isEmpty(this.f18551C.joinVideoUrl)) {
            str = this.f18551C.joinVideoUrl;
        } else if (TextUtils.isEmpty(this.f18551C.origTrackVideoUrl)) {
            Log.b(f18547Y, "unable to find seed");
            return;
        } else {
            str = this.f18551C.origTrackVideoUrl;
        }
        if (this.f18589u.f20144i != null) {
            Metadata a = Metadata.a(new File(this.f18589u.f20144i));
            if (a != null) {
                f2 = ((float) a.userDelayCalibrationMs) / 1000.0f;
                f3 = f + f2;
                Log.b(f18547Y, "seed frame pos:" + f3);
                this.ao = false;
                this.ap = false;
                this.al = new ExoPlayerWrapper(getActivity(), this.f18585q, new Handler(Looper.getMainLooper()), str, new GetAudioTimeCallback(this) {
                    final /* synthetic */ DuetJoinSaveFragment f18530a;

                    {
                        this.f18530a = r1;
                    }

                    public float mo6397a() {
                        return 0.0f;
                    }
                }, AutoScrollHelper.NO_MAX, 0.0f, null, new ExoPlayerStateChangeListener(this) {
                    final /* synthetic */ DuetJoinSaveFragment f18532b;

                    public void mo6398a(int i) {
                        boolean z = true;
                        if (!this.f18532b.isAdded() || i != 4) {
                            return;
                        }
                        if (!this.f18532b.ao) {
                            this.f18532b.ao = true;
                            this.f18532b.al.m26364a();
                            this.f18532b.al.m26369b(f3);
                        } else if (!this.f18532b.ap) {
                            this.f18532b.aq = this.f18532b.f18585q.getBitmap(HttpResponseCode.BAD_REQUEST, HttpResponseCode.BAD_REQUEST);
                            if (this.f18532b.as != null) {
                                this.f18532b.at = DuetJoinSaveFragment.m19933b(this.f18532b.as, this.f18532b.aq, this.f18532b.f18589u.f20142g != 1);
                                DuetJoinSaveFragment duetJoinSaveFragment = this.f18532b;
                                if (this.f18532b.at == null) {
                                    z = false;
                                }
                                duetJoinSaveFragment.ap = z;
                                if (this.f18532b.ap) {
                                    ImageToDiskUtils.m25838b(this.f18532b.getActivity(), "duetjoinerthumb");
                                    ImageToDiskUtils.m25836a(this.f18532b.getActivity(), "duetjoincompositebitmap", this.f18532b.at);
                                }
                                this.f18532b.m19851c(this.f18532b.am);
                                this.f18532b.af.post(this.f18532b.am);
                            }
                        }
                    }
                }, null, false, false);
            }
        }
        f2 = 0.0f;
        f3 = f + f2;
        Log.b(f18547Y, "seed frame pos:" + f3);
        this.ao = false;
        this.ap = false;
        this.al = new ExoPlayerWrapper(getActivity(), this.f18585q, new Handler(Looper.getMainLooper()), str, /* anonymous class already generated */, AutoScrollHelper.NO_MAX, 0.0f, null, /* anonymous class already generated */, null, false, false);
    }

    private void m19923Z() {
        if (TextUtils.isEmpty(this.f18551C.coverUrl)) {
            Log.b(f18547Y, "seed cover art not found.");
        } else {
            ImageLoader.a().a(this.f18551C.coverUrl, new Builder().m17114a(true).m17116b(true).m17115a(), new ImageLoadingListener(this) {
                final /* synthetic */ DuetJoinSaveFragment f18533a;

                {
                    this.f18533a = r1;
                }

                public void mo6154a(String str, View view) {
                }

                public void mo6156a(String str, View view, FailReason failReason) {
                }

                public void mo6155a(String str, View view, Bitmap bitmap) {
                    Log.b(DuetJoinSaveFragment.f18547Y, "seed covert art loaded");
                    this.f18533a.ar = bitmap;
                }

                public void mo6157b(String str, View view) {
                }
            });
        }
    }

    private static Bitmap m19933b(Bitmap bitmap, Bitmap bitmap2, boolean z) {
        if (bitmap == null || bitmap2 == null) {
            Log.e(f18547Y, "bitmaps not ready");
            return null;
        }
        if (!z) {
            Bitmap bitmap3 = bitmap;
            bitmap = bitmap2;
            bitmap2 = bitmap3;
        }
        Bitmap createBitmap = Bitmap.createBitmap(HttpResponseCode.BAD_REQUEST, HttpResponseCode.BAD_REQUEST, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        int width = bitmap2.getWidth();
        Rect rect = new Rect(width / 4, 0, (width / 2) + (width / 4), bitmap2.getHeight());
        Rect rect2 = new Rect(0, 0, 200, HttpResponseCode.BAD_REQUEST);
        canvas.drawBitmap(bitmap2, rect, rect2, null);
        int width2 = bitmap.getWidth();
        rect.left = width2 / 4;
        rect.right = (width2 / 2) + (width2 / 4);
        rect.bottom = bitmap.getHeight();
        rect2.left = 200;
        rect2.right = HttpResponseCode.BAD_REQUEST;
        canvas.drawBitmap(bitmap, rect, rect2, null);
        return createBitmap;
    }

    private boolean aa() {
        return SingApplication.n() && this.f18569U;
    }

    public void m19983a(Future<PreuploadResponse> future) {
        this.av = future;
    }
}
