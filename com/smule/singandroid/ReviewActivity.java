package com.smule.singandroid;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Display;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.facebook.appevents.AppEventsConstants;
import com.mopub.volley.DefaultRetryPolicy;
import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.ScaleModifier;
import com.smule.android.audio.AudioDefs;
import com.smule.android.audio.AudioDefs.HeadphonesType;
import com.smule.android.logging.Analytics.Ensemble;
import com.smule.android.logging.Analytics.UserPath;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.ResourceUtils;
import com.smule.android.utils.SimpleBarrier;
import com.smule.android.utils.UiHandler;
import com.smule.android.utils.WeakListener.OnGlobalLayoutListener;
import com.smule.singandroid.SingBundle.Builder;
import com.smule.singandroid.SingBundle.PerformanceType;
import com.smule.singandroid.audio.AudioInterface;
import com.smule.singandroid.audio.AudioInterface.FailRunnable;
import com.smule.singandroid.audio.AudioPowerEvent;
import com.smule.singandroid.audio.AudioUtils;
import com.smule.singandroid.audio.GlitchType;
import com.smule.singandroid.audio.Metadata;
import com.smule.singandroid.audio.OpenSLStreamVersion;
import com.smule.singandroid.audio.PartScoreFinder;
import com.smule.singandroid.audio.PartScoreFinder.PartScore;
import com.smule.singandroid.audio.ScorePartEventManager;
import com.smule.singandroid.audio.WaveformData;
import com.smule.singandroid.customviews.CustomToolbar;
import com.smule.singandroid.customviews.VideoFXTabIndicator;
import com.smule.singandroid.customviews.VocalEffectList;
import com.smule.singandroid.customviews.VocalEffectList.OnItemClickListener;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.BusyDialog.BusyDialogListener;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.DeleteRecordingConfirmationDialog;
import com.smule.singandroid.dialogs.SongDownloadDialog;
import com.smule.singandroid.dialogs.SongDownloadDialog.SongDownloadDialogListener;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;
import com.smule.singandroid.purchases.V3BillingHelper;
import com.smule.singandroid.purchases.V3BillingHelper$V3BillingListener;
import com.smule.singandroid.upsell.SubscriptionPurchaseDialog;
import com.smule.singandroid.utils.AnimatorEndListener;
import com.smule.singandroid.utils.BuildUtils.Flavor;
import com.smule.singandroid.utils.ImageToDiskUtils;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MathUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.AudioCompletionContext;
import com.smule.singandroid.utils.SingAnalytics.AudioSyncContext;
import com.smule.singandroid.utils.SingAnalytics.FxVipStatusType;
import com.smule.singandroid.utils.SingAnalytics.ReviewStepsType;
import com.smule.singandroid.utils.SingAnalytics.SingFlowPhase;
import com.smule.singandroid.utils.UIHelper;
import com.smule.singandroid.video.ExoPlayerWrapper;
import com.smule.singandroid.video.ExoPlayerWrapper.ExoPlayerInternalErrorListener;
import com.smule.singandroid.video.ExoPlayerWrapper.ExoPlayerStateChangeListener;
import com.smule.singandroid.video.GetAudioTimeCallback;
import com.smule.singandroid.video.PageSwiper;
import com.smule.singandroid.video.PageSwiper.ChangeListener;
import com.smule.singandroid.video.VideoFilterManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import twitter4j.HttpResponseCode;

@EActivity
public class ReviewActivity extends BaseActivity implements ExoPlayerInternalErrorListener, Observer {
    private static final String ag = ReviewActivity.class.getName();
    private static final String ah = (ag + ":VIDEO");
    @ViewById
    protected TextView f19691A;
    @ViewById
    protected TextView f19692B;
    @ViewById
    protected LinearLayout f19693C;
    @ViewById
    protected TextView f19694D;
    @ViewById
    protected ImageView f19695E;
    @ViewById
    protected View f19696F;
    @ViewById
    protected View f19697G;
    @ViewById
    protected LinearLayout f19698H;
    @ViewById
    protected VocalEffectList f19699I;
    @ViewById
    protected TextView f19700J;
    @ViewById
    protected FrameLayout f19701K;
    @ViewById
    protected VideoFXTabIndicator f19702L;
    @ViewById
    protected FrameLayout f19703M;
    @ViewById
    protected FrameLayout f19704N;
    @ViewById
    protected ImageView f19705O;
    @ViewById
    protected LinearLayout f19706P;
    @ViewById
    protected View f19707Q;
    @ViewById
    protected ImageView f19708R;
    @ViewById
    protected View f19709S;
    @ViewById
    protected View f19710T;
    @ViewById
    protected TextView f19711U;
    @Extra
    protected boolean f19712V;
    protected GlitchType f19713W = GlitchType.NONE;
    @InstanceState
    protected String f19714X;
    @InstanceState
    int f19715Y = -1;
    @InstanceState
    protected String f19716Z;
    private int aA;
    private int aB = 0;
    private V3BillingHelper aC;
    private SubscriptionPurchaseDialog aD;
    private float aE = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    private AudioInterface aF;
    private String aG;
    private String aH;
    private String aI;
    private int aJ;
    private boolean aK;
    private boolean aL;
    private String aM;
    private boolean aN;
    private int aO;
    private float aP;
    private boolean aQ;
    private float aR;
    private Metadata aS;
    private float aT;
    private Set<String> aU;
    private boolean aV = false;
    private SongDownloadDialog aW;
    private BusyDialog aX;
    private boolean aY = false;
    private int aZ;
    @InstanceState
    protected float aa;
    @InstanceState
    protected float ab;
    protected OnGlobalLayoutListener ac;
    protected OnGlobalLayoutListener ad;
    protected TextAlertDialog ae;
    int af = 0;
    private boolean ai = false;
    private Set<String> aj = new HashSet();
    private double ak = 200.0d;
    private double al = 0.0d;
    private ScheduledExecutorService am = Executors.newSingleThreadScheduledExecutor();
    private Future<?> an = null;
    private float ao;
    private SingBundle ap;
    private PostSingBundle aq;
    private SongbookEntry ar;
    private PerformanceV2 as;
    private Observer at;
    private Observer au;
    private Observer av;
    private Observer aw;
    private int ax;
    private int ay;
    private Integer az;
    private boolean bA;
    private boolean bB;
    private int bC;
    private float bD;
    private float bE;
    private float bF;
    private float bG;
    private int bH;
    private int bI;
    private ExoPlayerWrapper bJ;
    private boolean bK;
    private ExoPlayerWrapper bL;
    private ExoPlayerStateChangeListener bM = new ExoPlayerStateChangeListener(this) {
        final /* synthetic */ ReviewActivity f19650a;

        {
            this.f19650a = r1;
        }

        public void mo6398a(int i) {
            if (i == 5) {
                if (this.f19650a.ap.f20142g == 1) {
                    this.f19650a.m21298a(1);
                } else {
                    this.f19650a.m21298a(2);
                }
                this.f19650a.bK = false;
            }
        }
    };
    private View bN;
    private View bO;
    private View bP;
    private View bQ;
    private int bR;
    private int bS;
    private int bT;
    private boolean bU = true;
    private boolean bV = true;
    private int bW;
    private boolean bX;
    private boolean bY;
    private boolean bZ;
    private String ba;
    private boolean bb;
    private ScorePartEventManager bc;
    private float bd;
    private SimpleBarrier be;
    private boolean bf;
    private boolean bg = true;
    private OnGlobalLayoutListener bh;
    private boolean bi;
    private boolean bj;
    private boolean bk;
    private int bl;
    private int bm;
    private int bn;
    private int bo;
    private float bp;
    private AnimatorSet bq;
    private AnimatorSet br;
    private AnimatorSet bs;
    private AnimatorSet bt;
    private Handler bu = new Handler(Looper.getMainLooper());
    private boolean bv;
    private Runnable bw = new C40281(this);
    private PageSwiper bx;
    private ChangeListener by = new C40352(this);
    private OnClickListener bz = new C40423(this);
    @ViewById
    protected CustomToolbar f19717g;
    @ViewById
    protected Button f19718h;
    @ViewById
    protected Button f19719i;
    @ViewById
    protected View f19720j;
    @ViewById
    protected ImageView f19721k;
    @ViewById
    protected SeekBar f19722l;
    @ViewById
    protected SeekBar f19723m;
    @ViewById
    protected TextView f19724n;
    @ViewById
    protected ProgressBar f19725o;
    @ViewById
    protected View f19726p;
    @ViewById
    protected View f19727q;
    @ViewById
    protected WaveformView f19728r;
    @ViewById
    protected TextView f19729s;
    @ViewById
    protected View f19730t;
    @ViewById
    protected LinearLayout f19731u;
    @ViewById
    protected View f19732v;
    @ViewById
    protected LinearLayout f19733w;
    @ViewById
    protected LinearLayout f19734x;
    @ViewById
    protected TextView f19735y;
    @ViewById
    protected TextView f19736z;

    class C40281 implements Runnable {
        final /* synthetic */ ReviewActivity f19587a;

        class C40261 implements Runnable {
            final /* synthetic */ C40281 f19569a;

            C40261(C40281 c40281) {
                this.f19569a = c40281;
            }

            public void run() {
                this.f19569a.f19587a.mo6596z();
            }
        }

        class C40272 implements Runnable {
            final /* synthetic */ C40281 f19570a;

            C40272(C40281 c40281) {
                this.f19570a = c40281;
            }

            public void run() {
                Log.e(ReviewActivity.ag, "Failed to finalize performance because of an error in native code");
                this.f19570a.f19587a.mo6596z();
            }
        }

        C40281(ReviewActivity reviewActivity) {
            this.f19587a = reviewActivity;
        }

        public void run() {
            if (!this.f19587a.h()) {
                Bitmap bitmap;
                if (this.f19587a.ap.f20142g != 1) {
                    bitmap = ((TextureView) this.f19587a.bQ).getBitmap(HttpResponseCode.BAD_REQUEST, HttpResponseCode.BAD_REQUEST);
                } else {
                    bitmap = ((TextureView) this.f19587a.bP).getBitmap(HttpResponseCode.BAD_REQUEST, HttpResponseCode.BAD_REQUEST);
                }
                if (bitmap != null) {
                    ImageToDiskUtils.m25838b(this.f19587a, "duetjoinerthumb");
                    ImageToDiskUtils.m25836a(this.f19587a, "duetjoinerthumb", bitmap);
                }
                this.f19587a.m21301a(false, new C40261(this), new C40272(this));
            }
        }
    }

    class C40352 implements ChangeListener {
        final /* synthetic */ ReviewActivity f19613a;

        C40352(ReviewActivity reviewActivity) {
            this.f19613a = reviewActivity;
        }

        public void mo6581a(float f) {
            if (this.f19613a.bL != null) {
                this.f19613a.bL.m26373f().m26471a(f);
                this.f19613a.bL.m26364a();
            }
        }

        public void mo6580a() {
            if (this.f19613a.bL != null && this.f19613a.f19702L != null) {
                this.f19613a.bL.m26373f().mo7000f();
                this.f19613a.bL.m26364a();
                this.f19613a.f19702L.m23165c();
            }
        }

        public void mo6582b() {
            if (this.f19613a.bL != null && this.f19613a.f19702L != null) {
                this.f19613a.bL.m26373f().mo7001g();
                this.f19613a.bL.m26364a();
                this.f19613a.f19702L.m23166d();
            }
        }
    }

    class C40423 implements OnClickListener {
        final /* synthetic */ ReviewActivity f19631a;

        C40423(ReviewActivity reviewActivity) {
            this.f19631a = reviewActivity;
        }

        public void onClick(View view) {
            this.f19631a.bi = !this.f19631a.bi;
            this.f19631a.f19705O.setBackgroundResource(this.f19631a.bi ? C1947R.drawable.airbrush_switch_on : C1947R.drawable.airbrush_switch_off);
            if (this.f19631a.bi) {
                this.f19631a.av();
            } else {
                this.f19631a.bL.m26373f().m26476a(false);
            }
            MagicPreferences.m20304a(this.f19631a, "AIRBRUSH_PREFERENCE_KEY", this.f19631a.bi);
        }
    }

    class C40494 implements Runnable {
        final /* synthetic */ ReviewActivity f19649a;

        C40494(ReviewActivity reviewActivity) {
            this.f19649a = reviewActivity;
        }

        public void run() {
            this.f19649a.mo6594q();
        }
    }

    class C40515 implements Runnable {
        final /* synthetic */ ReviewActivity f19662a;

        C40515(ReviewActivity reviewActivity) {
            this.f19662a = reviewActivity;
        }

        public void run() {
            this.f19662a.aV = FollowManager.m18204a().m18222a(this.f19662a.as.accountIcon.accountId);
        }
    }

    class C40526 implements Observer {
        final /* synthetic */ ReviewActivity f19663a;

        C40526(ReviewActivity reviewActivity) {
            this.f19663a = reviewActivity;
        }

        public void update(Observable observable, Object obj) {
            Log.b(ReviewActivity.ag, "APP_SETTINGS_LOADED_EVENT notification received; refreshing FX buttons list view");
            this.f19663a.aj = SingServerValues.m21686g();
            this.f19663a.mo6595y();
        }
    }

    class C40537 implements Observer {
        final /* synthetic */ ReviewActivity f19664a;

        C40537(ReviewActivity reviewActivity) {
            this.f19664a = reviewActivity;
        }

        public void update(Observable observable, Object obj) {
            new RebufferAudioTask(this.f19664a, true, true, false, false).execute(new Void[0]);
        }
    }

    class C40548 implements Observer {
        final /* synthetic */ ReviewActivity f19665a;

        C40548(ReviewActivity reviewActivity) {
            this.f19665a = reviewActivity;
        }

        public void update(Observable observable, Object obj) {
            this.f19665a.m21309d(false);
        }
    }

    class C40559 implements Observer {
        final /* synthetic */ ReviewActivity f19666a;

        C40559(ReviewActivity reviewActivity) {
            this.f19666a = reviewActivity;
        }

        public void update(Observable observable, Object obj) {
            this.f19666a.m21309d(true);
        }
    }

    private class AudioSystemSetupTask extends AsyncTask<Context, Void, Boolean> {
        final /* synthetic */ ReviewActivity f19667a;
        private Runnable f19668b = null;
        private Runnable f19669c = null;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m21172a((Context[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m21173a((Boolean) obj);
        }

        AudioSystemSetupTask(ReviewActivity reviewActivity, Runnable runnable, Runnable runnable2) {
            this.f19667a = reviewActivity;
            this.f19668b = runnable;
            this.f19669c = runnable2;
        }

        protected Boolean m21172a(Context... contextArr) {
            try {
                Log.a(AudioInterface.f20656a, "instantiating AudioInterface from AudioSystemSetupTask");
                this.f19667a.aF = new AudioInterface(contextArr[0], "");
                return Boolean.TRUE;
            } catch (Throwable e) {
                Log.d(ReviewActivity.ag, "Failed to initialize audio engine becuase of an exception in native code", e);
                return Boolean.FALSE;
            }
        }

        protected void m21173a(Boolean bool) {
            if (bool.booleanValue()) {
                this.f19668b.run();
            } else {
                this.f19669c.run();
            }
        }
    }

    class RebufferAudioTask extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ ReviewActivity f19670a;
        private float f19671b;
        private float f19672c;
        private String f19673d;
        private float f19674e;
        private float f19675f;
        private boolean f19676g;
        private boolean f19677h;
        private boolean f19678i;
        private boolean f19679j;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m21174a((Void[]) objArr);
        }

        protected /* synthetic */ void onCancelled(Object obj) {
            m21176b((Void) obj);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m21175a((Void) obj);
        }

        public RebufferAudioTask(ReviewActivity reviewActivity, boolean z, boolean z2, boolean z3, boolean z4) {
            this.f19670a = reviewActivity;
            this.f19676g = z;
            this.f19677h = z2;
            this.f19678i = z3;
            this.f19679j = z4;
        }

        protected void onPreExecute() {
            this.f19670a.getWindow().setFlags(16, 16);
            this.f19670a.f19721k.setVisibility(8);
            this.f19670a.f19725o.setVisibility(0);
            if (this.f19679j) {
                this.f19670a.f19699I.m23549a(true);
            }
            this.f19670a.m21217X();
            this.f19671b = (float) this.f19670a.aA;
            this.f19672c = this.f19670a.f19728r.getCurrentPositionSec();
            this.f19673d = this.f19670a.f19714X;
            this.f19674e = this.f19670a.aa;
            this.f19675f = this.f19670a.ab;
            Log.c(ReviewActivity.ag, "Rebuffering Audio: song position sec: " + this.f19672c + " fore delay: " + this.f19671b);
        }

        protected Void m21174a(Void... voidArr) {
            if (!this.f19670a.h()) {
                try {
                    if (this.f19678i) {
                        this.f19670a.aF.setForegroundDelay_ms(this.f19671b);
                    }
                    if (this.f19679j) {
                        this.f19670a.aF.setForegroundFX(this.f19673d);
                        if (VocalEffect.m21979b(this.f19673d).m21984b()) {
                            this.f19670a.aF.setMetaParameters(this.f19674e, this.f19675f);
                        }
                    }
                    if (this.f19677h) {
                        this.f19670a.aF.setSongPosition_seconds(this.f19672c);
                    }
                } catch (Throwable e) {
                    Log.d(AudioInterface.f20656a, "Failed to rebuffer audio because one of the set methods threw an exception", e);
                }
            }
            return null;
        }

        protected void m21175a(Void voidR) {
            if (!this.f19670a.h()) {
                try {
                    this.f19670a.m21309d(true);
                    this.f19670a.aF.prepareForRealTime();
                } catch (Throwable e) {
                    Log.d(ReviewActivity.ag, "Error accessing audio interface in RebufferAudioTask.onPostExecute", e);
                }
                Window window = this.f19670a.getWindow();
                if (window != null) {
                    window.clearFlags(16);
                }
                this.f19670a.f19721k.setVisibility(0);
                this.f19670a.f19725o.setVisibility(8);
                if (this.f19679j) {
                    this.f19670a.f19699I.m23549a(false);
                }
                if (this.f19676g && this.f19670a.f()) {
                    this.f19670a.m21220Y();
                }
            }
        }

        protected void m21176b(Void voidR) {
        }
    }

    class RenderToDiskAudioTask extends AsyncTask<String, Void, Boolean> {
        float f19680a;
        final /* synthetic */ ReviewActivity f19681b;

        RenderToDiskAudioTask(ReviewActivity reviewActivity) {
            this.f19681b = reviewActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m21177a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m21178a((Boolean) obj);
        }

        protected void onPreExecute() {
            this.f19681b.f19721k.setVisibility(8);
            this.f19681b.f19725o.setVisibility(0);
            this.f19681b.m21217X();
            this.f19680a = this.f19681b.aF.getSongPosition_seconds();
        }

        protected Boolean m21177a(String... strArr) {
            try {
                for (String str : strArr) {
                    Log.c(AudioInterface.f20656a, "Rendering performance to file: " + str);
                    this.f19681b.aF.renderPerformanceToFile(str);
                }
                return Boolean.valueOf(true);
            } catch (Throwable e) {
                Log.d(AudioInterface.f20656a, "Failed to render performance to file", e);
                return Boolean.valueOf(false);
            }
        }

        protected void m21178a(Boolean bool) {
            if (!this.f19681b.isFinishing()) {
                if (bool.booleanValue()) {
                    this.f19681b.a_("Rendered to file");
                } else {
                    this.f19681b.a_("Failed to render locally");
                }
                this.f19681b.aF.setSongPosition_seconds(this.f19680a);
                this.f19681b.f19721k.setVisibility(0);
                this.f19681b.f19725o.setVisibility(8);
            }
        }
    }

    class WaveformAndAudioPowerTask extends AsyncTask<Void, Void, WaveformData> {
        final /* synthetic */ ReviewActivity f19682a;
        private boolean f19683b;
        private boolean f19684c;
        private String f19685d;
        private String f19686e;
        private ArrayList<AudioPowerEvent> f19687f;
        private float f19688g;
        private int f19689h;
        private int f19690i;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m21179a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m21180a((WaveformData) obj);
        }

        public WaveformAndAudioPowerTask(ReviewActivity reviewActivity, boolean z, boolean z2, String str, String str2, float f, int i, int i2) {
            this.f19682a = reviewActivity;
            this.f19683b = z;
            this.f19684c = z2;
            this.f19685d = str;
            this.f19686e = str2;
            this.f19688g = f;
            this.f19689h = i;
            this.f19690i = i2;
        }

        protected void onPreExecute() {
            if (this.f19686e != null) {
                Metadata a = Metadata.a(new File(this.f19686e));
                if (a != null) {
                    this.f19687f = a.audioPowerEvents;
                }
            }
        }

        protected WaveformData m21179a(Void... voidArr) {
            if (!isCancelled() || new File(this.f19685d).exists()) {
                return SingCoreBridge.getWaveformAndAudioEvents(this.f19683b, this.f19684c, this.f19685d, this.f19688g, this.f19689h, this.f19690i, this.f19687f, this.f19682a.aS.audioPowerEvents);
            }
            return null;
        }

        protected void m21180a(WaveformData waveformData) {
            if (!this.f19682a.isFinishing() && waveformData != null) {
                if (waveformData.mGlitchType != null) {
                    this.f19682a.f19713W = waveformData.mGlitchType;
                }
                if (waveformData.mWaveformData != null) {
                    this.f19682a.f19728r.m21997a(waveformData.mWaveformData, this.f19689h, this.f19690i);
                }
                if (this.f19682a.aS.audioPowerEvents != null) {
                    this.f19682a.aS.audioPower = waveformData.mAudioPower;
                    this.f19682a.ap = new Builder(this.f19682a.ap).m21617a(this.f19682a.aS).m21616a(this.f19682a.f19713W).m21621a();
                    if (this.f19682a.ap.f20137b == PerformanceType.DUET && !this.f19682a.ap.f20146k && ((this.f19682a.ap.f20142g == 1 || this.f19682a.ap.f20142g == 2) && this.f19682a.aK)) {
                        PartScore a = PartScoreFinder.m22285a(SingCoreBridge.getLyrics(this.f19682a.ap.f20142g), this.f19682a.aS.audioPowerEvents, this.f19682a.ap.f20142g);
                        if (a.f20664a >= 0) {
                            this.f19682a.aS.myParts = Integer.valueOf(a.f20664a);
                            this.f19682a.aS.myPartsSung = Integer.valueOf(a.f20665b);
                            this.f19682a.aS.otherParts = Integer.valueOf(a.f20666c);
                            this.f19682a.aS.otherPartsSung = Integer.valueOf(a.f20667d);
                        }
                    }
                } else {
                    MagicCrittercism.a(new Exception("noMetaDataFoundException1"));
                }
                if (waveformData.mScorePartEvents != null) {
                    this.f19682a.bc = new ScorePartEventManager(waveformData.mScorePartEvents);
                }
                this.f19682a.aT = waveformData.mJoinMaxPowerPositionSeconds;
                Log.b(ReviewActivity.ag, "mJoinMaxPowerPositionSeconds:" + this.f19682a.aT);
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.b(ag, "Begin of onCreate()");
        getWindow().addFlags(128);
        if (bundle == null) {
            this.aq = PostSingBundle.m20870a(getIntent());
        } else {
            this.aq = (PostSingBundle) bundle.getParcelable("POST_SING_BUNDLE_KEY");
            this.aB = bundle.getInt("m_delayCalibInitialVal", 0);
        }
        this.ap = this.aq.f19316b;
        this.aU = new HashSet();
        this.aG = this.ap.m21656e("RECORDING_FILE_EXTRA_KEY");
        this.aH = this.ap.m21656e("BACKGROUND_FILE_EXTRA_KEY");
        this.aI = this.ap.m21656e("MIDI_FILE_EXTRA_KEY");
        this.aJ = this.ap.m21645b("SCORE_EXTRA_KEY", 9999);
        this.aK = this.ap.m21650b("USED_HEADPHONE", false);
        this.aL = this.ap.m21650b("HEADPHONE_HAD_MIC", false);
        this.aQ = this.ap.m21650b("MIDI_HAS_CHORDS_TRACK", false);
        if (TextUtils.isEmpty(this.f19714X)) {
            this.f19714X = MagicPreferences.m20312b((Context) this, "PREFS_LAST_SELECTED_FX", SingServerValues.m21689j());
            if ((this.ap.m21648b() && this.f19714X.equals(VocalEffect.MAGIC.m21986c())) || (!this.aQ && this.f19714X.equals(VocalEffect.SUPER_HARMONY.m21986c()))) {
                this.f19714X = SingServerValues.m21689j();
            }
            MagicPreferences.m20303a((Context) this, "PREFS_LAST_SELECTED_FX", this.f19714X);
            VocalEffect b = VocalEffect.m21979b(this.f19714X);
            if (b != null && b.m21984b()) {
                this.aa = VocalEffect.m21974a(this, this.f19714X);
                this.ab = VocalEffect.m21978b(this, this.f19714X);
            }
        }
        this.aP = this.ap.m21644b("MAX_RMS_LEVEL", 0.001f);
        this.aS = this.ap.f20157v;
        if (this.aS == null) {
            this.aS = new Metadata();
        }
        this.aR = this.ap.m21644b("SAMPLE_RATE_EXTRA_KEY", DeviceSettings.e());
        this.ba = this.ap.m21646b("VIDEO_FILE", "");
        this.bi = this.ap.m21658g();
        this.bW = 3;
        this.be = new SimpleBarrier(1, new C40494(this));
        this.ar = this.ap.f20139d;
        this.as = this.ap.f20141f;
        boolean z = this.as != null && this.as.d() && this.as.origTrackVideoUrl != null && an();
        this.bf = z;
        if (this.ap.f20146k) {
            FollowManager.m18204a().m18216a(Long.valueOf(this.as.accountIcon.accountId), new C40515(this));
        }
        if (!m21283B()) {
            mo6588C();
        }
        if (AudioUtils.m22280a()) {
            try {
                InputStream open = getAssets().open(this.ar.mo6289c() + ".wav");
                OutputStream fileOutputStream = new FileOutputStream(this.aG);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = open.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                open.close();
                fileOutputStream.close();
                Log.c(ag, "Because audio debug enabled and matching .wav file found, replacing " + this.aG);
            } catch (FileNotFoundException e) {
            } catch (IOException e2) {
                Log.d(ag, e2.getMessage());
            }
        }
        Log.b(ag, "End of onCreate()");
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
        if (this.aX != null && this.aX.isShowing()) {
            this.aX.dismiss();
            this.aX = null;
        }
        if (this.aW != null && this.aW.isShowing()) {
            this.aW.dismiss();
            this.aW = null;
        }
    }

    protected void m21310e() {
        String f;
        boolean z;
        String str;
        String str2;
        Object arrayList = new ArrayList();
        if (an() && DeviceSettings.r()) {
            f = this.f19702L.getCurrentItemId() == null ? this.ap.m21657f() : this.f19702L.getCurrentItemId();
            boolean b = VideoFilterManager.m26577b(f);
            this.ap.m21637a(f);
            arrayList.add(f);
            z = b;
        } else if (!an() || DeviceSettings.r()) {
            z = false;
            f = null;
        } else {
            str = "unsupported";
            this.ap.m21637a("normal");
            arrayList.add(str);
            z = false;
            f = str;
        }
        if (this.bi) {
            arrayList.add("airbrush");
        }
        if (arrayList.isEmpty()) {
            str2 = f;
        } else {
            Collections.sort(arrayList);
            str2 = TextUtils.join(":", arrayList);
        }
        VocalEffect b2 = VocalEffect.m21979b(this.f19714X);
        String c = b2 == null ? null : b2.m21986c();
        f = SongbookEntry.m18752b(this.ar);
        if (this.as != null) {
            str = this.as.performanceKey;
        } else {
            str = null;
        }
        String Q = m21204Q();
        FxVipStatusType fxVipStatusType = (b2 == null || !b2.m21987e()) ? FxVipStatusType.NON_VIP : FxVipStatusType.VIP;
        SingAnalytics.m26118a(f, str, Q, fxVipStatusType, c, z ? FxVipStatusType.VIP : FxVipStatusType.NON_VIP, str2);
    }

    private void m21195M() {
        Observer c40526 = new C40526(this);
        this.at = c40526;
        NotificationCenter.m19011a().m19014a("APP_SETTINGS_LOADED_EVENT", c40526);
        c40526 = new C40537(this);
        this.au = c40526;
        NotificationCenter.m19011a().m19014a("USER_MODIFIED_SEEK_TIME_EVENT", c40526);
        c40526 = new C40548(this);
        this.av = c40526;
        NotificationCenter.m19011a().m19014a("VERTICAL_SEEK_BAR_MOVE_EVENT", c40526);
        c40526 = new C40559(this);
        this.aw = c40526;
        NotificationCenter.m19011a().m19014a("VERTICAL_SEEK_BAR_TOUCH_UP_EVENT", c40526);
    }

    private void m21198N() {
        NotificationCenter.m19011a().m19016b("APP_SETTINGS_LOADED_EVENT", this.at);
        NotificationCenter.m19011a().m19016b("USER_MODIFIED_SEEK_TIME_EVENT", this.au);
        NotificationCenter.m19011a().m19016b("VERTICAL_SEEK_BAR_MOVE_EVENT", this.av);
        NotificationCenter.m19011a().m19016b("VERTICAL_SEEK_BAR_TOUCH_UP_EVENT", this.aw);
    }

    protected void m21296a() throws Exception {
        this.bg = false;
    }

    protected void m21302b() throws Exception {
        this.aF.m22269a(null);
        this.aF.start();
        this.be.m19034a();
        m21313g(true);
    }

    public void onResume() {
        super.onResume();
        Log.c(ag, "onResume");
        if (isFinishing()) {
            Log.c(ag, "Exiting onResume() as the activity is about to be closed");
        } else if (this.ae != null) {
            Log.d(ag, "review error dialog showing");
        } else {
            if (this.aC != null) {
                this.aC.a();
            }
            try {
                if (this.bg) {
                    m21296a();
                } else {
                    m21302b();
                }
                if (this.aW == null) {
                    m21195M();
                    Log.b(ag, "onResume, rendering");
                    LayoutParams layoutParams = this.f19693C.getLayoutParams();
                    layoutParams.height = -2;
                    this.f19693C.setLayoutParams(layoutParams);
                    this.bB = false;
                    this.ac = new OnGlobalLayoutListener(this, new ViewTreeObserver.OnGlobalLayoutListener(this) {
                        final /* synthetic */ ReviewActivity f19571a;

                        {
                            this.f19571a = r1;
                        }

                        public void onGlobalLayout() {
                            LayoutUtils.m25859b(this.f19571a.f19726p, this.f19571a.ac);
                            if (!this.f19571a.bB) {
                                this.f19571a.bB = true;
                                if (this.f19571a.aZ == 0) {
                                    this.f19571a.aZ = this.f19571a.f19693C.getMeasuredHeight();
                                    Log.b(ReviewActivity.ag, "mDelayContentHeight:" + this.f19571a.aZ);
                                }
                                LayoutParams layoutParams = this.f19571a.f19693C.getLayoutParams();
                                layoutParams.height = 0;
                                this.f19571a.f19693C.setLayoutParams(layoutParams);
                                this.f19571a.m21200O();
                                this.f19571a.m21291J();
                                this.f19571a.f19699I.m23546a(this.f19571a.f19715Y, this.f19571a.aa, this.f19571a.ab);
                            }
                        }
                    });
                    LayoutUtils.m25854a(this.f19726p, this.ac);
                    i();
                    if (this.bL != null) {
                        this.bL.m26371d();
                    }
                    if (this.bJ != null) {
                        this.bJ.m26371d();
                        this.bK = true;
                    }
                }
            } catch (Throwable e) {
                String str = "Failed to configure or start audio system in onResume";
                Log.d(AudioInterface.f20656a, "Failed to configure or start audio system in onResume", e);
                m21300a(e);
            }
        }
    }

    private void m21200O() {
        float dimensionPixelSize = (float) getResources().getDimensionPixelSize(C1947R.dimen.review_score_min_height);
        float height = (float) this.f19730t.getHeight();
        if (height < dimensionPixelSize) {
            Log.b(ag, "enforceMinimumScoreHeight:curHeight:" + height + " minHeight:" + dimensionPixelSize);
            this.bA = true;
            int bottom = this.f19732v.getBottom() + (this.f19730t.getTop() + ((int) dimensionPixelSize));
            int top = this.f19710T.getTop();
            if (bottom < top) {
                dimensionPixelSize += (float) (top - bottom);
                Log.b(ag, "new minPx:" + dimensionPixelSize);
            }
            height = dimensionPixelSize;
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f19730t.getLayoutParams();
            layoutParams.height = (int) height;
            layoutParams.addRule(2, 0);
            this.f19730t.setLayoutParams(layoutParams);
            layoutParams = (RelativeLayout.LayoutParams) this.f19731u.getLayoutParams();
            layoutParams.addRule(3, C1947R.id.review_score_save_view);
            this.f19731u.setLayoutParams(layoutParams);
        }
    }

    public void m21305c(String str) {
        Log.b(ag, "finish - called from " + str);
        super.finish();
    }

    public void onPause() {
        super.onPause();
        m21313g(false);
        j();
        if (this.aC != null) {
            this.aC.b();
        }
        this.be.m19037d();
        if (this.aW == null && !this.aY) {
            m21217X();
            m21198N();
            Log.b(AudioInterface.f20656a, "shutting down video");
            ah();
            Log.b(AudioInterface.f20656a, "shutting down audio");
            try {
                this.aF.stopAndShutdown();
            } catch (Throwable e) {
                Log.d(AudioInterface.f20656a, "Failed to shutdown audio system because of an exception in native code", e);
            }
            this.bY = false;
            if (an()) {
                this.bX = false;
                this.bZ = false;
                m21292K();
                as();
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("POST_SING_BUNDLE_KEY", this.aq);
        bundle.putInt("m_delayCalibInitialVal", this.aB);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.am.shutdown();
        this.am = null;
        this.an = null;
        if (this.aC != null) {
            this.aC.c();
            this.aC = null;
        }
        this.aD = null;
        this.aX = null;
        this.aW = null;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        Object obj = (this.aC == null || !this.aC.a(i, i2, intent)) ? null : 1;
        if (obj == null) {
            super.onActivityResult(i, i2, intent);
        }
    }

    @UiThread
    protected void mo6594q() {
        if (f()) {
            this.be.m19036c();
            if (this.aW != null) {
                this.aW.m23722a(this.ar, this.as);
            }
        }
    }

    protected void m21315r() {
        this.f19732v.setVisibility(0);
        UIHelper.m26199a(this.f19723m, getResources().getColor(C1947R.color.review_seek_bar_bg));
        OpenSLStreamVersion a = OpenSLStreamVersion.m22283a(this.ap.m21645b("OPENSL_STREAM_VERSION", OpenSLStreamVersion.UNSPECIFIED.m22284a()));
        float c = (((float) (this.ap.m21651c("INTERNAL_BUFFERING_LATENCY_IN_FRAMES") - Integer.valueOf(this.ap.m21646b("OPENSL_STREAM_V1_BUFFERING_LATENCY_IN_FRAMES", AppEventsConstants.EVENT_PARAM_VALUE_NO)).intValue())) / this.ap.m21653d("SAMPLE_RATE_EXTRA_KEY")) * 1000.0f;
        this.aA = MagicPreferences.m20309b(HeadphonesType.m17502a(this.aK, this.aL), a, (int) c);
        this.az = MagicPreferences.m20298a(HeadphonesType.m17502a(this.aK, this.aL), a, (int) c);
        if (this.az != null) {
            this.ax = this.az.intValue() - 400;
            this.ay = this.az.intValue() + HttpResponseCode.BAD_REQUEST;
        } else {
            this.az = MagicPreferences.m20297a();
            this.ax = 0;
            this.ay = 800;
        }
        if (this.ax < 0) {
            this.ax = 0;
        }
        if (this.aA > this.ay || this.aA < this.ax) {
            this.aA = ((this.ay - this.ax) / 2) + this.ax;
        }
        this.aB = this.aA;
        this.bH = this.aA;
        this.bI = this.aA;
        this.f19723m.setMax(this.ay - this.ax);
        this.f19723m.setProgress(this.aA - this.ax);
        this.f19723m.setRotation(180.0f);
        this.f19723m.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
            final /* synthetic */ ReviewActivity f19572a;

            {
                this.f19572a = r1;
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                this.f19572a.mo6591c(true);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                this.f19572a.mo6591c(true);
                this.f19572a.m21201P();
                SingAnalytics.m26091a(SongbookEntry.m18752b(this.f19572a.ar), this.f19572a.aA, AudioSyncContext.ATTEMPT, HeadphonesType.m17502a(this.f19572a.aK, this.f19572a.aL));
            }
        });
        Log.b(ag, "Delay calibration seek bar configured to range [" + this.ax + ", " + this.ay + "], with current value = " + this.aA);
        this.f19723m.setThumbOffset(getResources().getDimensionPixelSize(C1947R.dimen.review_scrubber_offset_large));
    }

    protected ValueAnimator m21295a(final View view, int i, int i2) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i, i2});
        ofInt.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ ReviewActivity f19574b;

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (!this.f19574b.h()) {
                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    LayoutParams layoutParams = view.getLayoutParams();
                    layoutParams.height = intValue;
                    view.setLayoutParams(layoutParams);
                }
            }
        });
        return ofInt;
    }

    private ValueAnimator m21225a(final View view, float f) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{view.getTranslationY(), view.getTranslationY() + f});
        ofFloat.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ ReviewActivity f19576b;

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (!this.f19576b.h()) {
                    view.setTranslationY(((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
            }
        });
        return ofFloat;
    }

    @Click
    protected void m21316s() {
        if (at()) {
            Rect rect = new Rect();
            this.f19732v.getGlobalVisibleRect(rect);
            if (rect.bottom <= this.f19731u.getBottom()) {
                this.bY = true;
                if (this.f19701K.getVisibility() == 0) {
                    this.bX = false;
                }
                m21291J();
            }
        }
    }

    protected void m21317t() {
        if (this.f19693C.getVisibility() == 4) {
            boolean z;
            int i;
            this.f19693C.setVisibility(0);
            this.bq = new AnimatorSet();
            int dimension = (int) (2.0f * getResources().getDimension(C1947R.dimen.review_thin_divider));
            ValueAnimator a = m21295a(this.f19731u, this.f19731u.getHeight(), ((this.f19707Q.getVisibility() == 8) != this.bX ? dimension : 0) + (this.aZ + this.f19731u.getHeight()));
            int measuredHeight = this.aZ - this.f19732v.getMeasuredHeight();
            if (this.f19707Q.getVisibility() == 8) {
                z = true;
            } else {
                z = false;
            }
            if (z != this.bX) {
                i = (-this.aZ) + dimension;
                if (this.bk) {
                    i -= this.bm;
                }
            } else {
                i = measuredHeight;
            }
            ValueAnimator a2 = m21225a(this.f19731u, (float) (-i));
            ValueAnimator a3 = m21295a(this.f19693C, 0, this.aZ);
            this.bq.addListener(new AnimatorListener(this) {
                final /* synthetic */ ReviewActivity f19577a;

                {
                    this.f19577a = r1;
                }

                public void onAnimationStart(Animator animator) {
                    if (!this.f19577a.h()) {
                        this.f19577a.f19695E.setVisibility(8);
                        this.f19577a.mo6591c(true);
                        Animation loadAnimation = AnimationUtils.loadAnimation(this.f19577a, 17432576);
                        loadAnimation.setFillAfter(true);
                        this.f19577a.f19733w.startAnimation(loadAnimation);
                    }
                }

                public void onAnimationEnd(Animator animator) {
                }

                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }
            });
            this.bq.setDuration(300);
            if (an() && this.f19707Q.getVisibility() == 8) {
                this.bq.playTogether(new Animator[]{a3, a, a2});
            } else {
                this.bq.playTogether(new Animator[]{a3, a});
            }
            this.bq.start();
        }
    }

    @Click
    protected void m21318u() {
        if (at()) {
            this.bY = false;
            if (this.f19701K.getVisibility() == 0) {
                this.bX = false;
            }
            m21291J();
        }
    }

    protected void m21319v() {
        if (this.f19693C.getVisibility() == 0) {
            int dimensionPixelSize;
            int i;
            ValueAnimator a;
            ValueAnimator a2;
            ValueAnimator a3;
            this.bq = new AnimatorSet();
            if (this.bX) {
                if ((this.f19707Q.getVisibility() == 8) != this.bX) {
                    dimensionPixelSize = this.aZ - ((getResources().getDimensionPixelSize(C1947R.dimen.review_waveform_view_height) + au()) + (au() - getResources().getDimensionPixelSize(C1947R.dimen.review_section_heading_height)));
                    if (this.f19731u.getMeasuredHeight() == this.bC) {
                        i = this.aZ;
                        dimensionPixelSize = au() - getResources().getDimensionPixelSize(C1947R.dimen.review_section_heading_height);
                        if (this.bk) {
                            dimensionPixelSize += this.bm + (au() - getResources().getDimensionPixelSize(C1947R.dimen.review_waveform_mini_height));
                        }
                    } else if (this.bk) {
                        dimensionPixelSize = ((-au()) + this.bm) + getResources().getDimensionPixelSize(C1947R.dimen.review_thin_divider);
                        i = 0;
                    } else {
                        i = 0;
                    }
                    a = m21225a(this.f19731u, (float) dimensionPixelSize);
                    a2 = m21295a(this.f19731u, this.f19731u.getHeight(), i + (this.f19731u.getHeight() - this.aZ));
                    a3 = m21295a(this.f19693C, this.aZ, 0);
                    this.bq.addListener(new AnimatorListener(this) {
                        final /* synthetic */ ReviewActivity f19578a;

                        {
                            this.f19578a = r1;
                        }

                        public void onAnimationStart(Animator animator) {
                        }

                        public void onAnimationEnd(Animator animator) {
                            if (!this.f19578a.h()) {
                                this.f19578a.f19693C.setVisibility(4);
                                this.f19578a.f19695E.setVisibility(0);
                                this.f19578a.f19694D.setVisibility(8);
                                this.f19578a.mo6591c(false);
                                Animation loadAnimation = AnimationUtils.loadAnimation(this.f19578a, 17432576);
                                loadAnimation.setFillAfter(true);
                                this.f19578a.f19733w.startAnimation(loadAnimation);
                                if (!this.f19578a.bX) {
                                    this.f19578a.as();
                                }
                            }
                        }

                        public void onAnimationCancel(Animator animator) {
                        }

                        public void onAnimationRepeat(Animator animator) {
                        }
                    });
                    this.bq.setDuration(300);
                    if (an() || this.f19707Q.getVisibility() != 0) {
                        this.bq.playTogether(new Animator[]{a3, a2});
                    } else {
                        this.bq.playTogether(new Animator[]{a3, a2, a});
                    }
                    this.bq.start();
                }
            }
            dimensionPixelSize = 0;
            i = 0;
            a = m21225a(this.f19731u, (float) dimensionPixelSize);
            a2 = m21295a(this.f19731u, this.f19731u.getHeight(), i + (this.f19731u.getHeight() - this.aZ));
            a3 = m21295a(this.f19693C, this.aZ, 0);
            this.bq.addListener(/* anonymous class already generated */);
            this.bq.setDuration(300);
            if (an()) {
            }
            this.bq.playTogether(new Animator[]{a3, a2});
            this.bq.start();
        }
    }

    @Click
    protected void m21320w() {
        this.f19723m.setProgress(this.az.intValue() - this.ax);
        m21201P();
        SingAnalytics.m26091a(SongbookEntry.m18752b(this.ar), this.aA, AudioSyncContext.ATTEMPT, HeadphonesType.m17502a(this.aK, this.aL));
    }

    private synchronized void m21201P() {
        m21258h(false);
    }

    private synchronized void m21258h(boolean z) {
        this.aA = this.f19723m.getProgress() + this.ax;
        this.aA = Math.max(this.aA, this.ax);
        this.aA = Math.min(this.aA, this.ay);
        if (this.aA < this.bH) {
            this.bH = this.aA;
        }
        if (this.aA > this.bI) {
            this.bI = this.aA;
        }
        MagicPreferences.m20306a(HeadphonesType.m17502a(this.aK, this.aL), this.aA);
        new RebufferAudioTask(this, true, true, true, z).execute(new Void[0]);
    }

    private void m21243c(int i) {
        int min = Math.min(Math.max((this.ax + this.f19723m.getProgress()) + i, this.ax), this.ay);
        if (min != this.aA) {
            this.f19723m.setProgress(min - this.ax);
            m21201P();
            SingAnalytics.m26091a(SongbookEntry.m18752b(this.ar), this.aA, AudioSyncContext.ATTEMPT, HeadphonesType.m17502a(this.aK, this.aL));
        }
    }

    @UiThread
    protected void mo6591c(boolean z) {
        if (!isFinishing()) {
            if (z) {
                int progress = (this.f19723m.getProgress() + this.ax) - this.az.intValue();
                this.f19734x.setVisibility(8);
                CharSequence string;
                if (progress == 0) {
                    string = getResources().getString(C1947R.string.vocal_match_unchanged);
                    this.f19691A.setText(string);
                    this.f19692B.setText("");
                    this.f19694D.setTextColor(getResources().getColor(C1947R.color.contextual_text));
                    this.f19735y.setText(string);
                    this.f19736z.setText("");
                } else if (progress > 0) {
                    string = getResources().getString(C1947R.string.vocal_match_diff).replace("{0}", "-" + progress);
                    this.f19694D.setTextColor(getResources().getColor(C1947R.color.contextual_text_accent));
                    this.f19691A.setText(getResources().getString(C1947R.string.vocal_match_earlier) + " | ");
                    this.f19692B.setText(string);
                    this.f19735y.setText(getResources().getString(C1947R.string.vocal_match_adjusted) + " | ");
                    this.f19736z.setText(string);
                } else {
                    string = getResources().getString(C1947R.string.vocal_match_diff).replace("{0}", Integer.toString(-progress));
                    this.f19694D.setTextColor(getResources().getColor(C1947R.color.contextual_text_accent));
                    this.f19691A.setText(getResources().getString(C1947R.string.vocal_match_later) + " | ");
                    this.f19692B.setText(string);
                    this.f19735y.setText(getResources().getString(C1947R.string.vocal_match_adjusted) + " | ");
                    this.f19736z.setText(string);
                }
                this.f19694D.setVisibility(0);
                return;
            }
            this.f19734x.setVisibility(0);
            this.f19691A.setText("");
            this.f19692B.setText("");
        }
    }

    private String m21204Q() {
        if (this.ar == null || !this.ar.m18772r()) {
            return "-";
        }
        return this.ar.mo6289c();
    }

    private void m21206R() {
        if (this.bE != this.bD || this.aA != this.aB) {
            SingAnalytics.m26061a(this.ap.f20154s, SingFlowPhase.REVIEW, (int) (100.0d * ((double) AudioDefs.m17508a((Activity) this))), HeadphonesType.m17502a(this.aK, this.aL), null, null, Float.valueOf(this.bE), Float.valueOf(this.bG - this.bF), this.aA, Integer.valueOf(this.bI - this.bH));
        }
    }

    void m21321x() {
        UIHelper.m26199a(this.f19722l, getResources().getColor(C1947R.color.review_seek_bar_fg));
        this.f19722l.setProgress(m21294a(MathUtils.m25870a(this.ao), this.f19722l.getMax(), -12.0f, 6.0f));
        this.bD = MathUtils.m25870a(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        this.f19722l.setThumbOffset(getResources().getDimensionPixelSize(C1947R.dimen.review_scrubber_offset_small));
    }

    int m21294a(float f, int i, float f2, float f3) {
        if (f3 - f2 > 0.0f) {
            return (int) (((f - f2) / (f3 - f2)) * ((float) i));
        }
        Log.b(ag, "seekBarMaxRange=" + f3 + "; should be strictly greater than seekBarMinRange = " + f2);
        return 0;
    }

    float m21293a(int i, int i2, float f, float f2) {
        float f3 = ((float) i) / ((float) i2);
        Log.b(ag, "seekPos = " + i + "; seekBarMax = " + i2 + "; rangeFraction = " + f3 + "; seekBarMinRange = " + f + "; seekBarMaxRange = " + f2 + "; result = " + (((f2 - f) * f3) + f));
        return (f3 * (f2 - f)) + f;
    }

    protected void m21307d() {
        Log.b(ag, "onViewsCreated - begin; isFinishing?" + isFinishing());
        if (!isFinishing()) {
            View view;
            this.f19726p.setBackground(getResources().getDrawable(this.ap.m21645b("BACKGROUND_RESOURCE_KEY", (int) C1947R.drawable.bg_sing_gradient_teal_purple)));
            this.f19717g.mo6782a(this.ar, null);
            this.aj = SingServerValues.m21686g();
            m21222Z();
            m21212U();
            mo6595y();
            this.f19720j.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ReviewActivity f19579a;

                {
                    this.f19579a = r1;
                }

                public void onClick(View view) {
                    if (this.f19579a.aF == null) {
                        Log.e(ReviewActivity.ag, "onClick:mAudioInterface null for play button");
                        return;
                    }
                    if (this.f19579a.aF.isPlaying()) {
                        this.f19579a.m21217X();
                    } else {
                        this.f19579a.m21220Y();
                    }
                    this.f19579a.aO = this.f19579a.aO + 1;
                }
            });
            if (Flavor.a.a()) {
                this.f19720j.setOnLongClickListener(new OnLongClickListener(this) {
                    final /* synthetic */ ReviewActivity f19580a;

                    {
                        this.f19580a = r1;
                    }

                    public boolean onLongClick(View view) {
                        new RenderToDiskAudioTask(this.f19580a).execute(new String[]{ResourceUtils.m19028a() + "/review_screen.wav"});
                        return true;
                    }
                });
            }
            final boolean z = this.aJ > getResources().getInteger(C1947R.integer.performance_minimum_score);
            Object obj = (!this.ap.m21648b() || this.aK || SingApplication.g.booleanValue()) ? null : 1;
            Object obj2 = (obj == null || an()) ? null : 1;
            if (an()) {
                view = this.f19701K;
                if (obj != null) {
                    this.f19711U.setVisibility(0);
                    this.f19711U.setText(C1947R.string.headphones_required_to_save_your_perf);
                } else if (this.ap.m21648b() && this.ap.f20146k) {
                    this.f19711U.setVisibility(0);
                    this.f19711U.setText(C1947R.string.group_video_message);
                    final ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{getResources().getDimensionPixelSize(C1947R.dimen.review_headphones_required_group_video_height), 0});
                    final View b = this.f19702L.m23163b(C1947R.id.top_gradient);
                    final View b2 = this.f19702L.m23163b(C1947R.id.tab_title);
                    final int dimensionPixelOffset = getResources().getDimensionPixelOffset(C1947R.dimen.smoothing_touchable_area_size) / 2;
                    ofInt.addUpdateListener(new AnimatorUpdateListener(this) {
                        final /* synthetic */ ReviewActivity f19584d;

                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            if (!this.f19584d.h()) {
                                LayoutParams layoutParams = this.f19584d.f19711U.getLayoutParams();
                                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) b.getLayoutParams();
                                MarginLayoutParams marginLayoutParams2 = (MarginLayoutParams) b2.getLayoutParams();
                                MarginLayoutParams marginLayoutParams3 = (MarginLayoutParams) this.f19584d.f19704N.getLayoutParams();
                                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                marginLayoutParams.topMargin = intValue;
                                layoutParams.height = intValue;
                                marginLayoutParams2.topMargin = ((Integer) valueAnimator.getAnimatedValue()).intValue() + this.f19584d.getResources().getDimensionPixelSize(C1947R.dimen.margin_huge);
                                if (SingServerValues.m21670H().size() > 1) {
                                    marginLayoutParams3.topMargin = (marginLayoutParams3.topMargin + (b2.getTop() + (b2.getMeasuredHeight() / 2))) - ((this.f19584d.f19704N.getTop() - this.f19584d.getResources().getDimensionPixelSize(C1947R.dimen.app_bar_height)) + dimensionPixelOffset);
                                } else {
                                    marginLayoutParams3.topMargin = ((Integer) valueAnimator.getAnimatedValue()).intValue() + this.f19584d.getResources().getDimensionPixelOffset(C1947R.dimen.margin_large);
                                }
                                this.f19584d.f19711U.setLayoutParams(layoutParams);
                                b.setLayoutParams(marginLayoutParams);
                                b2.setLayoutParams(marginLayoutParams2);
                                this.f19584d.f19704N.setLayoutParams(marginLayoutParams3);
                            }
                        }
                    });
                    ofInt.addListener(new AnimatorEndListener(this) {
                        final /* synthetic */ ReviewActivity f19586b;

                        public void onAnimationEnd(Animator animator) {
                            if (!this.f19586b.h()) {
                                this.f19586b.f19711U.setVisibility(8);
                                ofInt.removeListener(this);
                            }
                        }
                    });
                    ofInt.setStartDelay((long) getResources().getInteger(C1947R.integer.vfx_fadeout_delay));
                    ofInt.setDuration((long) getResources().getInteger(17694721));
                    ofInt.start();
                }
            } else {
                view = obj2 != null ? this.f19727q : this.f19700J;
            }
            if (obj2 == null) {
                String[] stringArray = getResources().getStringArray(C1947R.array.review_encouragement);
                this.f19700J.setText(stringArray[new Random().nextInt(stringArray.length)]);
            }
            if (this.ap.f20149n) {
                this.f19718h.setText(getResources().getString(C1947R.string.core_continue));
            }
            Animation loadAnimation = AnimationUtils.loadAnimation(this, C1947R.anim.pulse);
            if (obj != null) {
                this.f19719i.setVisibility(0);
                this.f19719i.startAnimation(loadAnimation);
                this.f19718h.setVisibility(8);
            } else {
                this.f19719i.setVisibility(8);
                this.f19718h.setVisibility(0);
                this.f19718h.startAnimation(loadAnimation);
            }
            this.f19700J.setVisibility(8);
            this.f19727q.setVisibility(8);
            if (z) {
                this.f19724n.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ReviewActivity f19588a;

                    {
                        this.f19588a = r1;
                    }

                    public void onClick(View view) {
                        this.f19588a.f19724n.setText(Integer.toString(this.f19588a.aJ));
                        this.f19588a.f19724n.setTag(Boolean.valueOf(true));
                    }
                });
            } else {
                this.f19724n.setVisibility(8);
            }
            final long currentTimeMillis = 2000 + System.currentTimeMillis();
            final AccelerateInterpolator accelerateInterpolator = new AccelerateInterpolator(3.0f);
            final Handler handler = new Handler();
            handler.post(new Runnable(this) {
                final /* synthetic */ ReviewActivity f19596f;

                class C40301 implements Runnable {
                    final /* synthetic */ AnonymousClass21 f19590a;

                    class C40291 extends AnimatorListenerAdapter {
                        final /* synthetic */ C40301 f19589a;

                        C40291(C40301 c40301) {
                            this.f19589a = c40301;
                        }

                        public void onAnimationEnd(Animator animator) {
                            int i = 0;
                            if (!this.f19589a.f19590a.f19596f.h()) {
                                if (this.f19589a.f19590a.f19596f.f19724n != null) {
                                    this.f19589a.f19590a.f19596f.f19724n.setVisibility(8);
                                }
                                if (this.f19589a.f19590a.f19596f.f19702L != null) {
                                    this.f19589a.f19590a.f19596f.f19702L.setVisibility(0);
                                }
                                if (this.f19589a.f19590a.f19596f.f19704N != null) {
                                    FrameLayout frameLayout = this.f19589a.f19590a.f19596f.f19704N;
                                    if (!(this.f19589a.f19590a.f19596f.an() && VideoFilterManager.m26576a())) {
                                        i = 8;
                                    }
                                    frameLayout.setVisibility(i);
                                }
                            }
                        }
                    }

                    C40301(AnonymousClass21 anonymousClass21) {
                        this.f19590a = anonymousClass21;
                    }

                    public void run() {
                        if (!this.f19590a.f19596f.isFinishing()) {
                            view.setAlpha(0.0f);
                            view.setVisibility(0);
                            view.animate().alpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).setDuration(800);
                            this.f19590a.f19596f.f19724n.animate().alpha(0.0f).setDuration(800).setListener(new C40291(this));
                        }
                    }
                }

                public void run() {
                    if (!this.f19596f.isFinishing()) {
                        this.f19596f.f19702L.setVisibility(4);
                        float currentTimeMillis = (float) (currentTimeMillis - System.currentTimeMillis());
                        if (!z || currentTimeMillis <= 0.0f) {
                            if (z) {
                                this.f19596f.f19724n.setText(Integer.toString(this.f19596f.aJ));
                            }
                            handler.postDelayed(new C40301(this), 1000);
                            return;
                        }
                        this.f19596f.f19724n.setText(Integer.toString((int) (accelerateInterpolator.getInterpolation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - Math.max(Math.min(currentTimeMillis / 2000.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT), 0.0f)) * ((float) this.f19596f.aJ))));
                        handler.postDelayed(this, 20);
                    }
                }
            });
            File file = new File(this.aG);
            try {
                MediaPlayer mediaPlayer = new MediaPlayer();
                FileInputStream fileInputStream = new FileInputStream(file);
                mediaPlayer.setDataSource(fileInputStream.getFD());
                mediaPlayer.prepare();
                double duration = (double) (((float) mediaPlayer.getDuration()) * 0.001f);
                this.ak = duration;
                this.al = duration;
                mediaPlayer.release();
                fileInputStream.close();
                this.ao = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
                m21321x();
                this.f19728r.setTotalDurationSec((float) this.ak);
                mo6589a(0.0f);
                SharedPreferences sharedPreferences = getSharedPreferences("RATE_US_POPUP", 0);
                sharedPreferences.edit().putInt("Rate_US_COUNT", sharedPreferences.getInt("Rate_US_COUNT", 0) + 1).apply();
                if (!SubscriptionManager.a().b()) {
                    this.aC = new V3BillingHelper();
                    this.aD = new SubscriptionPurchaseDialog(this, this.aC, this.ar);
                    this.aD.setOnCancelListener(new OnCancelListener(this) {
                        final /* synthetic */ ReviewActivity f19597a;

                        {
                            this.f19597a = r1;
                        }

                        public void onCancel(DialogInterface dialogInterface) {
                            this.f19597a.mo6587A();
                        }
                    });
                    this.aD.setOnDismissListener(new OnDismissListener(this) {
                        final /* synthetic */ ReviewActivity f19598a;

                        {
                            this.f19598a = r1;
                        }

                        public void onDismiss(DialogInterface dialogInterface) {
                            this.f19598a.mo6587A();
                        }
                    });
                    this.aC.a(this, "vipfx", null, new V3BillingHelper$V3BillingListener(this) {
                        final /* synthetic */ ReviewActivity f19601a;

                        class C40311 implements Runnable {
                            final /* synthetic */ AnonymousClass24 f19599a;

                            C40311(AnonymousClass24 anonymousClass24) {
                                this.f19599a = anonymousClass24;
                            }

                            public void run() {
                                this.f19599a.f19601a.mo6596z();
                            }
                        }

                        class C40322 implements Runnable {
                            final /* synthetic */ AnonymousClass24 f19600a;

                            C40322(AnonymousClass24 anonymousClass24) {
                                this.f19600a = anonymousClass24;
                            }

                            public void run() {
                                Log.e(ReviewActivity.ag, "Failed to finalize performance because of an exception in native code");
                                this.f19600a.f19601a.mo6596z();
                            }
                        }

                        {
                            this.f19601a = r1;
                        }

                        public void mo6535a() {
                        }

                        public void mo6536a(boolean z) {
                            Log.b(ReviewActivity.ag, "onReportEnd called; success: " + z + ", selected vocal effect ID is: " + this.f19601a.f19714X);
                            if (z && this.f19601a.ai) {
                                this.f19601a.m21301a(false, new C40311(this), new C40322(this));
                            }
                        }
                    });
                }
                m21210T();
                m21287F();
                new WaveformAndAudioPowerTask(this, an(), this.ap.f20142g != 1, this.aG, this.bb ? this.ap.f20144i : null, (float) this.al, (int) this.aR, 2048).execute(new Void[0]);
                m21315r();
                m21313g(false);
                new AudioSystemSetupTask(this, new Runnable(this) {
                    Runnable f19605a = new C40331(this);
                    FailRunnable f19606b = new C40342(this);
                    final /* synthetic */ ReviewActivity f19607c;

                    class C40331 implements Runnable {
                        final /* synthetic */ AnonymousClass25 f19602a;

                        C40331(AnonymousClass25 anonymousClass25) {
                            this.f19602a = anonymousClass25;
                        }

                        public void run() {
                            if (!this.f19602a.f19607c.isFinishing()) {
                                this.f19602a.f19607c.aF;
                                Log.a(AudioInterface.f20656a, "calling start from onViewsCreated");
                                this.f19602a.f19607c.aF.start();
                                this.f19602a.f19607c.be.m19034a();
                                if (this.f19602a.f19607c.ap.m21643a() && this.f19602a.f19607c.ap.f20146k) {
                                    this.f19602a.f19607c.aF.setForegroundPan(0.25f);
                                    Log.b(ReviewActivity.ag, "setting pan to .25");
                                } else {
                                    Log.b(ReviewActivity.ag, "not setting pan");
                                }
                                this.f19602a.f19607c.m21309d(true);
                                this.f19602a.f19607c.m21258h(true);
                                if (this.f19602a.f19607c.an()) {
                                    this.f19602a.f19607c.ak();
                                    this.f19602a.f19607c.bL.m26371d();
                                    if (this.f19602a.f19607c.bf) {
                                        this.f19602a.f19607c.am();
                                        this.f19602a.f19607c.bJ.m26371d();
                                        this.f19602a.f19607c.bK = true;
                                    }
                                }
                                this.f19602a.f19607c.al();
                                this.f19602a.f19607c.m21313g(true);
                            }
                        }
                    }

                    class C40342 extends FailRunnable {
                        final /* synthetic */ AnonymousClass25 f19604a;

                        C40342(AnonymousClass25 anonymousClass25) {
                            this.f19604a = anonymousClass25;
                        }

                        public void run() {
                            if (!this.f19604a.f19607c.isFinishing()) {
                                this.f19604a.f19607c.aF.stopAndShutdown();
                                this.f19604a.f19607c.aF = null;
                                this.f19604a.f19607c.be.m19034a();
                                this.f19604a.f19607c.m21308d("Failed to setup performance because of an exception in native code");
                            }
                        }
                    }

                    {
                        this.f19607c = r2;
                    }

                    public void run() {
                        this.f19607c.be.m19037d();
                        this.f19607c.aF;
                        Log.a(AudioInterface.f20656a, "calling setupPerformance from onViewsCreated in ReviewActivity");
                        this.f19607c.aF.m22270a(this.f19607c.aH, this.f19607c.aI, null, this.f19607c.aG, this.f19607c.aG + ".json", this.f19605a, this.f19606b);
                    }
                }, new Runnable(this) {
                    final /* synthetic */ ReviewActivity f19608a;

                    {
                        this.f19608a = r1;
                    }

                    public void run() {
                        this.f19608a.m21308d("Failed to initialize audio engine because of an exception in native code");
                    }
                }).execute(new Context[]{this});
                this.bC = ((((getResources().getDimensionPixelSize(C1947R.dimen.review_thin_divider) + getResources().getDimensionPixelSize(C1947R.dimen.review_waveform_mini_height)) + getResources().getDimensionPixelSize(C1947R.dimen.review_thin_divider)) + getResources().getDimensionPixelSize(C1947R.dimen.review_vocal_effect_height)) + getResources().getDimensionPixelSize(C1947R.dimen.review_section_heading_height)) + getResources().getDimensionPixelSize(C1947R.dimen.review_delay_content_total_height);
                Log.b(ag, "onViewsCreated - end");
            } catch (Throwable e) {
                Log.d(ag, "Could not open recording file", e);
                m21308d(this.aG + " could not be opened and read. Is it corrupted?");
            }
        }
    }

    private void m21207S() {
        final View b = this.f19702L.m23163b(C1947R.id.tab_title);
        this.bh = new OnGlobalLayoutListener(this.f19702L, new ViewTreeObserver.OnGlobalLayoutListener(this) {
            final /* synthetic */ ReviewActivity f19610b;

            public void onGlobalLayout() {
                if (!this.f19610b.h()) {
                    LayoutUtils.m25859b(b, this.f19610b.bh);
                    int dimensionPixelOffset = this.f19610b.getResources().getDimensionPixelOffset(C1947R.dimen.smoothing_touchable_area_size) / 2;
                    MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f19610b.f19704N.getLayoutParams();
                    if (SingServerValues.m21670H().size() > 1) {
                        marginLayoutParams.topMargin = (marginLayoutParams.topMargin + (b.getTop() + (b.getMeasuredHeight() / 2))) - (dimensionPixelOffset + (this.f19610b.f19704N.getTop() - this.f19610b.getResources().getDimensionPixelSize(C1947R.dimen.app_bar_height)));
                    } else if (this.f19610b.ap.f20146k && this.f19610b.ap.m21648b()) {
                        marginLayoutParams.topMargin += this.f19610b.getResources().getDimensionPixelOffset(C1947R.dimen.review_headphones_required_group_video_height);
                    }
                    this.f19610b.f19704N.setLayoutParams(marginLayoutParams);
                }
            }
        });
        LayoutUtils.m25854a(b, this.bh);
    }

    private void m21210T() {
        this.f19722l.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ ReviewActivity f19611a;

            {
                this.f19611a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                        view.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case 1:
                    case 3:
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                view.onTouchEvent(motionEvent);
                return true;
            }
        });
    }

    private void m21212U() {
        Collection arrayList = new ArrayList();
        if (!this.aQ) {
            arrayList.add(VocalEffect.SUPER_HARMONY);
        }
        if (this.ap.m21648b()) {
            arrayList.add(VocalEffect.MAGIC);
        }
        String a = this.f19699I.m23545a(this.f19698H, arrayList, this.f19714X);
        if (this.f19716Z == null) {
            this.f19716Z = a;
        }
        this.f19699I.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ ReviewActivity f19612a;

            {
                this.f19612a = r1;
            }

            public void mo6579a(String str, int i, float f, float f2, boolean z) {
                if (this.f19612a.f19714X == null || !this.f19612a.f19714X.equals(str) || Math.abs(f - this.f19612a.aa) >= 0.01f || Math.abs(f2 - this.f19612a.ab) >= 0.01f) {
                    Log.b(ReviewActivity.ag, "FX settingEffectsPreset to " + str);
                    this.f19612a.f19714X = str;
                    this.f19612a.f19715Y = i;
                    this.f19612a.aM = str;
                    this.f19612a.aU.add(str);
                    this.f19612a.aa = f;
                    this.f19612a.ab = f2;
                    MagicPreferences.m20303a(this.f19612a, "PREFS_LAST_SELECTED_FX", str);
                    this.f19612a.mo6595y();
                    if (z) {
                        new RebufferAudioTask(this.f19612a, true, true, false, true).execute(new Void[0]);
                    }
                }
            }

            public void mo6578a(String str, float f, float f2, boolean z) {
                Log.b(ReviewActivity.ag, "FX Setting meta params: " + str + " (" + f + ", " + f2 + ")");
                this.f19612a.aa = f;
                this.f19612a.ab = f2;
                if (this.f19612a.aF != null) {
                    try {
                        this.f19612a.aF.setMetaParameters(f, f2);
                    } catch (Exception e) {
                        Log.e(ReviewActivity.ag, "setMetaParams called when performance is not initialized");
                    }
                }
                if (z) {
                    this.f19612a.aN = true;
                }
            }
        });
    }

    @UiThread
    protected void mo6595y() {
        if (!isFinishing()) {
            VocalEffect b = VocalEffect.m21979b(this.f19714X);
            if (b != null) {
                this.f19728r.setWaveformColor(b.m21985c(this));
                this.f19728r.setSeekColor(b.m21983b((Context) this));
                this.f19728r.m22000b();
                this.f19728r.invalidate();
            }
        }
    }

    private void m21234a(Bundle bundle, String str) {
        if (this.ap.m21649b(str)) {
            bundle.putInt(str, this.ap.m21651c(str));
        }
    }

    private void m21238b(Bundle bundle, String str) {
        if (this.ap.m21649b(str)) {
            bundle.putString(str, this.ap.m21656e(str));
        }
    }

    @UiThread
    protected void mo6596z() {
        if (f()) {
            int progress = this.ax + this.f19723m.getProgress();
            m21206R();
            Bundle bundle = new Bundle();
            bundle.putInt("PERFORMANCE_SAVE_ACTIVITY_MODE_KEY", 0);
            bundle.putString("APP_VERSION", "4.4.9");
            bundle.putString("RECORDING_FILE_EXTRA_KEY", this.aG);
            bundle.putDouble("RECORDING_FILE_DURATION", this.al);
            bundle.putString("EFFECT_PRESET", this.f19714X);
            bundle.putString("FX_INITIAL", this.f19716Z);
            bundle.putString("FX_SELECTED", this.aM);
            bundle.putString("FXS_UNIQUE_REVIEW", Integer.toString(this.aU.size()));
            bundle.putString("ADJUSTED_SLIDER", Boolean.toString(this.aN));
            bundle.putString("PLAY_PAUSE_COUNT", Integer.toString(this.aO));
            VocalEffect b = VocalEffect.m21979b(this.f19714X);
            if (b != null && b.m21984b()) {
                bundle.putFloat("META_PARAM_1", this.aa);
                bundle.putFloat("META_PARAM_2", this.ab);
            }
            String str = "PRESET_VIP_EXTRA_KEY";
            boolean z = b != null && b.m21987e();
            bundle.putBoolean(str, z);
            bundle.putBoolean("USED_HEADPHONE", this.aK);
            bundle.putBoolean("HEADPHONE_HAD_MIC", this.aL);
            bundle.putFloat("NORMALIZATION_SCALE_FACTOR", this.aE);
            bundle.putFloat("USER_GAIN_DB", MathUtils.m25870a(this.ao));
            bundle.putFloat("USER_GAIN_AMP", this.ao);
            bundle.putInt("USER_DELAY_CALIBRATION_MS", progress);
            bundle.putFloat("MAX_RMS_LEVEL", this.aP);
            bundle.putString("DEVICE_MANUFACTURE", Build.MANUFACTURER);
            bundle.putString("DEVICE_MODEL", Build.MODEL);
            bundle.putString("DEVICE_PRODUCT_NAME", Build.PRODUCT);
            bundle.putInt("SCORE_EXTRA_KEY", this.aJ);
            bundle.putFloat("SYSTEM_VOLUME", AudioDefs.m17508a((Activity) this));
            bundle.putInt("ANALYTICS_AUDIO_UUID", this.ap.f20154s);
            bundle.putString("VIDEO_FILE", this.ba);
            bundle.putFloat("JOIN_MAX_POWER_POSITION_SECONDS", this.aT);
            bundle.putBoolean("IS_FOLLOWING_PARTNER_KEY", this.aV);
            Integer a = MagicPreferences.m20298a(HeadphonesType.m17502a(this.aK, this.aL), this.aF.m22267a(), 0);
            if (a != null) {
                bundle.putInt("DEVICE_SETTINGS_DEFAULT_LATENCY_IN_MS", a.intValue());
            }
            bundle.putInt("BUFFER_SIZE_MULTIPLIER", DeviceSettings.d());
            bundle.putInt("FINAL_BUFFER_SIZE", DeviceSettings.g());
            a = DeviceSettings.f();
            if (a != null) {
                bundle.putInt("NATIVE_BUFFER_SIZE", a.intValue());
            }
            if (!this.ap.f20149n) {
                bundle.putString("ANALYTICS_PROGRESS_KEY", this.aq.f19320f);
            }
            bundle.putString("OS_VERSION", VERSION.RELEASE);
            bundle.putInt("ANDROID_API", VERSION.SDK_INT);
            bundle.putString("AUDIO_SYSTEM_NAME", this.ap.m21656e("AUDIO_SYSTEM_NAME"));
            m21234a(bundle, "OPENSL_STREAM_VERSION");
            m21238b(bundle, "INTERNAL_BUFFERING_LATENCY_IN_FRAMES");
            m21238b(bundle, "OPENSL_STREAM_V1_BUFFERING_LATENCY_IN_FRAMES");
            if (this.aS != null) {
                this.aS.userDelayCalibrationMs = progress;
            }
            SingBundle i = this.ap.m21660i();
            String str2 = null;
            if (an() && DeviceSettings.r()) {
                str2 = this.f19702L.getCurrentItemId() == null ? this.ap.m21657f() : this.f19702L.getCurrentItemId();
            }
            i.m21637a(str2);
            i.m21642a(this.bi);
            if (!((this.ap.f20146k && this.ap.m21648b()) || SubscriptionManager.a().b() || !VideoFilterManager.m26577b(i.m21657f()))) {
                i.m21637a("normal");
            }
            Intent a2 = i.m21636a(getApplicationContext(), PerformanceSaveActivity_.class);
            a2.putExtra("OGG_FILE_METADATA_BUNDLE_EXTRA_KEY", bundle);
            this.aq.f19316b = i;
            this.aq.m20871b(a2);
            Log.b(ag, "goToPerformanceSaveActivity - gainDb: " + MathUtils.m25870a(this.ao) + "; mRecordingFilePath: " + this.aG + "; selectedVocalEffectEffectsV2Id: " + this.f19714X + "; mForegroundDuration: " + this.al);
            startActivity(a2);
            m21305c("goToPerformanceSaveActivity");
        }
    }

    @UiThread
    protected void mo6589a(float f) {
        if (!isFinishing()) {
            this.f19728r.setCurrentPositionSec(f);
            this.f19729s.setText(MiscUtils.m25883a(f));
            if (this.bc != null && this.bf) {
                int a = this.bc.m22292a(f);
                if (a != this.bW && this.bK) {
                    m21298a(a);
                }
            }
        }
    }

    @UiThread
    protected void mo6590b(Runnable runnable) {
        if (!isFinishing()) {
            if (this.ai) {
                Log.d(ag, "showSubscriptionPurchaseDialogIfNeeded - already showing subscription purchase dialog");
            } else if (!this.aj.contains(this.f19714X)) {
                Log.b(ag, "Vocal effect with id, " + this.f19714X + ", is not VIP-only");
                if (runnable != null) {
                    runnable.run();
                }
            } else if (SubscriptionManager.a().b() || this.aD == null) {
                Log.b(ag, "showSubscriptionPurchaseDialogIfNeeded - user subscribed");
                if (runnable != null) {
                    runnable.run();
                }
            } else {
                Log.b(ag, "showSubscriptionPurchaseDialogIfNeeded - user not subscribed");
                this.aD.show();
                this.ai = true;
            }
        }
    }

    @UiThread
    protected void mo6587A() {
        if (!isFinishing()) {
            if (this.ai && this.f19718h != null && this.f19718h.getVisibility() == 0 && !this.f19718h.isEnabled()) {
                this.f19718h.setEnabled(true);
            }
            this.ai = false;
        }
    }

    private void m21213V() {
        this.an = this.am.scheduleAtFixedRate(new Runnable(this) {
            final /* synthetic */ ReviewActivity f19615a;
            private volatile boolean f19616b = false;

            class C40361 implements Runnable {
                final /* synthetic */ AnonymousClass30 f19614a;

                C40361(AnonymousClass30 anonymousClass30) {
                    this.f19614a = anonymousClass30;
                }

                public void run() {
                    if (!this.f19614a.f19615a.isFinishing()) {
                        this.f19614a.f19615a.m21217X();
                        this.f19614a.f19615a.mo6589a(0.0f);
                        this.f19614a.f19615a.f19728r.setCurrentPositionSec(0.0f);
                        new RebufferAudioTask(this.f19614a.f19615a, false, true, false, false).execute(new Void[0]);
                    }
                }
            }

            {
                this.f19615a = r2;
            }

            public void run() {
                if (!this.f19616b && !this.f19615a.isFinishing()) {
                    if (this.f19615a.aF.endOfPerformanceReached()) {
                        Log.c(ReviewActivity.ag, "END OF PERFORMANCE REACHED");
                        this.f19616b = true;
                        this.f19615a.runOnUiThread(new C40361(this));
                        return;
                    }
                    this.f19615a.mo6589a(this.f19615a.aF.getSongPosition_seconds());
                }
            }
        }, 0, 50, TimeUnit.MILLISECONDS);
    }

    private void m21215W() {
        if (this.an != null) {
            this.an.cancel(true);
            this.an = null;
        }
    }

    private synchronized void m21217X() {
        if (this.aF == null) {
            Log.e(ag, "stopPlayback:mAudioInterface null");
        } else if (this.aF.isPlaying()) {
            Log.c(ag, "Stop Media Player");
            m21215W();
            this.aF.setPause(true);
            aj();
            runOnUiThread(new Runnable(this) {
                final /* synthetic */ ReviewActivity f19617a;

                {
                    this.f19617a = r1;
                }

                public void run() {
                    if (!this.f19617a.isFinishing()) {
                        this.f19617a.f19721k.setImageResource(C1947R.drawable.icn_play_review);
                    }
                }
            });
        }
    }

    private synchronized void m21220Y() {
        if (this.aF.isPlaying()) {
            Log.b(ag, "start Playback called but already playing");
        } else {
            Log.c(ag, "Start Media Player");
            this.aF.setPause(false);
            m21213V();
            runOnUiThread(new Runnable(this) {
                final /* synthetic */ ReviewActivity f19618a;

                {
                    this.f19618a = r1;
                }

                public void run() {
                    if (!this.f19618a.isFinishing()) {
                        this.f19618a.ai();
                        this.f19618a.f19721k.setImageResource(C1947R.drawable.icn_pause_review);
                    }
                }
            });
        }
    }

    private void m21222Z() {
        this.f19717g.setRightButtonOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ReviewActivity f19619a;

            {
                this.f19619a = r1;
            }

            public void onClick(View view) {
                this.f19619a.ad();
            }
        });
        this.f19718h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ReviewActivity f19623a;

            class C40391 implements Runnable {
                final /* synthetic */ AnonymousClass34 f19622a;

                class C40371 implements Runnable {
                    final /* synthetic */ C40391 f19620a;

                    C40371(C40391 c40391) {
                        this.f19620a = c40391;
                    }

                    public void run() {
                        this.f19620a.f19622a.f19623a.mo6596z();
                    }
                }

                class C40382 implements Runnable {
                    final /* synthetic */ C40391 f19621a;

                    C40382(C40391 c40391) {
                        this.f19621a = c40391;
                    }

                    public void run() {
                        Log.e(ReviewActivity.ag, "Failed to finalize performance because of an error in native code");
                        this.f19621a.f19622a.f19623a.mo6596z();
                    }
                }

                C40391(AnonymousClass34 anonymousClass34) {
                    this.f19622a = anonymousClass34;
                }

                public void run() {
                    if (this.f19622a.f19623a.an() && this.f19622a.f19623a.ap.m21643a() && this.f19622a.f19623a.ap.f20146k) {
                        this.f19622a.f19623a.aa();
                        this.f19622a.f19623a.bu.postDelayed(this.f19622a.f19623a.bw, 1000);
                        return;
                    }
                    this.f19622a.f19623a.m21301a(false, new C40371(this), new C40382(this));
                }
            }

            {
                this.f19623a = r1;
            }

            public void onClick(View view) {
                SingAnalytics.m26091a(SongbookEntry.m18752b(this.f19623a.ar), this.f19623a.aA, AudioSyncContext.SAVE, HeadphonesType.m17502a(this.f19623a.aK, this.f19623a.aL));
                this.f19623a.m21217X();
                String b = SongbookEntry.m18752b(this.f19623a.ar);
                UserPath userPath = this.f19623a.ap.f20149n ? UserPath.ONBOARDING : UserPath.OTHER;
                HeadphonesType a = HeadphonesType.m17502a(this.f19623a.aK, this.f19623a.aL);
                String str = this.f19623a.f19714X;
                boolean z = this.f19623a.ap.f20146k;
                Ensemble a2 = this.f19623a.ap.f20137b.m21631a();
                String L = this.f19623a.m21204Q();
                Boolean valueOf = (!this.f19623a.ap.f20146k || this.f19623a.as == null) ? null : Boolean.valueOf(this.f19623a.as.video);
                SingAnalytics.m26136c(b, userPath, a, str, z, a2, L, valueOf, this.f19623a.an());
                this.f19623a.f19718h.setEnabled(false);
                this.f19623a.mo6590b(new C40391(this));
            }
        });
        this.f19719i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ReviewActivity f19626a;

            class C40401 implements Runnable {
                final /* synthetic */ AnonymousClass35 f19624a;

                C40401(AnonymousClass35 anonymousClass35) {
                    this.f19624a = anonymousClass35;
                }

                public void run() {
                    this.f19624a.f19626a.ac();
                }
            }

            class C40412 implements Runnable {
                final /* synthetic */ AnonymousClass35 f19625a;

                C40412(AnonymousClass35 anonymousClass35) {
                    this.f19625a = anonymousClass35;
                }

                public void run() {
                    Log.e(ReviewActivity.ag, "Failed to finalize performance because of an error in native code");
                    this.f19625a.f19626a.ac();
                }
            }

            {
                this.f19626a = r1;
            }

            public void onClick(View view) {
                Boolean valueOf;
                this.f19626a.m21217X();
                String b = SongbookEntry.m18752b(this.f19626a.ar);
                UserPath userPath = this.f19626a.ap.f20149n ? UserPath.ONBOARDING : UserPath.OTHER;
                HeadphonesType a = HeadphonesType.m17502a(this.f19626a.aK, this.f19626a.aL);
                String str = this.f19626a.f19714X;
                boolean z = this.f19626a.ap.f20146k;
                Ensemble a2 = this.f19626a.ap.f20137b.m21631a();
                String L = this.f19626a.m21204Q();
                if (this.f19626a.as != null) {
                    valueOf = Boolean.valueOf(this.f19626a.as.video);
                } else {
                    valueOf = null;
                }
                SingAnalytics.m26144d(b, userPath, a, str, z, a2, L, valueOf, this.f19626a.an());
                this.f19626a.m21206R();
                SingAnalytics.m26060a(this.f19626a.ap.f20154s, AudioCompletionContext.REVIEW_EXIT, Float.valueOf(this.f19626a.aE), null, this.f19626a.ab(), null, Integer.toString(this.f19626a.aU.size()), Boolean.toString(this.f19626a.aN), Integer.toString(this.f19626a.aO), DeviceSettings.n(), DeviceSettings.h());
                if (this.f19626a.ap.f20158w != GlitchType.NONE) {
                    SingAnalytics.m26109a(null, this.f19626a.ap.f20158w, HeadphonesType.m17502a(this.f19626a.aK, this.f19626a.aL));
                }
                MagicPreferences.m20306a(HeadphonesType.m17502a(this.f19626a.aK, this.f19626a.aL), this.f19626a.aB);
                this.f19626a.m21301a(false, new C40401(this), new C40412(this));
            }
        });
        this.f19717g.setLeftButtonOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ReviewActivity f19627a;

            {
                this.f19627a = r1;
            }

            public void onClick(View view) {
                this.f19627a.ae();
            }
        });
        this.f19725o.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ReviewActivity f19628a;

            {
                this.f19628a = r1;
            }

            public void onClick(View view) {
                this.f19628a.af();
            }
        });
        this.f19696F.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ReviewActivity f19629a;

            {
                this.f19629a = r1;
            }

            public void onClick(View view) {
                this.f19629a.m21243c(-10);
            }
        });
        this.f19697G.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ReviewActivity f19630a;

            {
                this.f19630a = r1;
            }

            public void onClick(View view) {
                this.f19630a.m21243c(10);
            }
        });
    }

    private void aa() {
        float f = this.aT;
        if (this.ap.f20157v != null) {
            f += ((float) this.ap.f20157v.userDelayCalibrationMs) / 1000.0f;
        }
        this.bv = true;
        this.bL.m26369b(f);
    }

    private String ab() {
        return null + "," + this.f19716Z + "," + this.f19714X;
    }

    private void ac() {
        SingBundle a = new Builder(this.ap).m21623b(false).m21621a();
        startActivity(a.m21636a(getApplicationContext(), a.m21654d() ? SingVideoActivity_.class : SingActivity_.class));
        m21305c("returnToSingActivity");
    }

    private void ad() {
        if (!SingApplication.g.booleanValue() || new Random().nextInt(Integer.MAX_VALUE) % 10 == 0) {
            m21217X();
            String b = SongbookEntry.m18752b(this.ar);
            UserPath userPath = this.ap.f20149n ? UserPath.ONBOARDING : UserPath.OTHER;
            HeadphonesType a = HeadphonesType.m17502a(this.aK, this.aL);
            String str = this.f19714X;
            boolean z = this.ap.f20146k;
            Ensemble a2 = this.ap.f20137b.m21631a();
            ReviewStepsType reviewStepsType = ReviewStepsType.REVIEW;
            String Q = m21204Q();
            Boolean valueOf = (!this.ap.f20146k || this.as == null) ? null : Boolean.valueOf(this.as.video);
            SingAnalytics.m26106a(b, userPath, a, str, z, a2, reviewStepsType, Q, valueOf, an());
            DeleteRecordingConfirmationDialog deleteRecordingConfirmationDialog = new DeleteRecordingConfirmationDialog(this);
            deleteRecordingConfirmationDialog.m19809b(new Runnable(this) {
                final /* synthetic */ ReviewActivity f19632a;

                {
                    this.f19632a = r1;
                }

                public void run() {
                    Boolean bool;
                    String b = SongbookEntry.m18752b(this.f19632a.ar);
                    UserPath userPath = this.f19632a.ap.f20149n ? UserPath.ONBOARDING : UserPath.OTHER;
                    ReviewStepsType reviewStepsType = ReviewStepsType.REVIEW;
                    String L = this.f19632a.m21204Q();
                    if (!this.f19632a.ap.f20146k || this.f19632a.as == null) {
                        bool = null;
                    } else {
                        bool = Boolean.valueOf(this.f19632a.as.video);
                    }
                    SingAnalytics.m26102a(b, userPath, 0, 0, reviewStepsType, L, bool, this.f19632a.an());
                    this.f19632a.m21206R();
                    SingAnalytics.m26060a(this.f19632a.ap.f20154s, AudioCompletionContext.REVIEW_EXIT, Float.valueOf(this.f19632a.aE), null, this.f19632a.ab(), null, Integer.toString(this.f19632a.aU.size()), Boolean.toString(this.f19632a.aN), Integer.toString(this.f19632a.aO), DeviceSettings.n(), DeviceSettings.h());
                    if (this.f19632a.ap.f20158w != GlitchType.NONE) {
                        SingAnalytics.m26109a(null, this.f19632a.ap.f20158w, HeadphonesType.m17502a(this.f19632a.aK, this.f19632a.aL));
                    }
                    MagicPreferences.m20306a(HeadphonesType.m17502a(this.f19632a.aK, this.f19632a.aL), this.f19632a.aB);
                    this.f19632a.m21301a(false, null, null);
                    PostSingFlowActivity.m20876a(this.f19632a, this.f19632a.aq);
                    this.f19632a.finish();
                }
            });
            deleteRecordingConfirmationDialog.show();
        }
    }

    private void ae() {
        TextAlertDialog textAlertDialog = new TextAlertDialog((Context) this, getString(C1947R.string.core_are_you_sure), getString(C1947R.string.post_performance_delete_try_again));
        textAlertDialog.m19804a(new Runnable(this) {
            final /* synthetic */ ReviewActivity f19635a;

            class C40431 implements Runnable {
                final /* synthetic */ AnonymousClass41 f19633a;

                C40431(AnonymousClass41 anonymousClass41) {
                    this.f19633a = anonymousClass41;
                }

                public void run() {
                    this.f19633a.f19635a.ac();
                }
            }

            class C40442 implements Runnable {
                final /* synthetic */ AnonymousClass41 f19634a;

                C40442(AnonymousClass41 anonymousClass41) {
                    this.f19634a = anonymousClass41;
                }

                public void run() {
                    Log.e(ReviewActivity.ag, "Failed to finalize performance because of an exception in native code");
                    this.f19634a.f19635a.ac();
                }
            }

            {
                this.f19635a = r1;
            }

            public void run() {
                Boolean bool;
                this.f19635a.m21217X();
                String b = SongbookEntry.m18752b(this.f19635a.ar);
                UserPath userPath = this.f19635a.ap.f20149n ? UserPath.ONBOARDING : UserPath.OTHER;
                HeadphonesType a = HeadphonesType.m17502a(this.f19635a.aK, this.f19635a.aL);
                String str = this.f19635a.f19714X;
                boolean z = this.f19635a.ap.f20146k;
                Ensemble a2 = this.f19635a.ap.f20137b.m21631a();
                String L = this.f19635a.m21204Q();
                if (!this.f19635a.ap.f20146k || this.f19635a.as == null) {
                    bool = null;
                } else {
                    bool = Boolean.valueOf(this.f19635a.as.video);
                }
                SingAnalytics.m26144d(b, userPath, a, str, z, a2, L, bool, this.f19635a.an());
                this.f19635a.m21206R();
                SingAnalytics.m26060a(this.f19635a.ap.f20154s, AudioCompletionContext.REVIEW_EXIT, Float.valueOf(this.f19635a.aE), null, this.f19635a.ab(), null, Integer.toString(this.f19635a.aU.size()), Boolean.toString(this.f19635a.aN), Integer.toString(this.f19635a.aO), DeviceSettings.n(), DeviceSettings.h());
                if (this.f19635a.ap.f20158w != GlitchType.NONE) {
                    SingAnalytics.m26109a(null, this.f19635a.ap.f20158w, HeadphonesType.m17502a(this.f19635a.aK, this.f19635a.aL));
                }
                MagicPreferences.m20306a(HeadphonesType.m17502a(this.f19635a.aK, this.f19635a.aL), this.f19635a.aB);
                this.f19635a.m21301a(false, new C40431(this), new C40442(this));
            }
        });
        textAlertDialog.show();
    }

    private void af() {
        TextAlertDialog textAlertDialog = new TextAlertDialog((Context) this, getString(C1947R.string.client_render_progess_title), getString(C1947R.string.client_render_progress_body), true, false);
        textAlertDialog.m19806a(getString(C1947R.string.core_ok), "");
        textAlertDialog.show();
    }

    public void onBackPressed() {
        if (this.ai) {
            mo6587A();
        } else {
            ad();
        }
    }

    protected boolean m21283B() {
        File file = new File(this.aH);
        if (file.exists()) {
            android.util.Log.v(ag, "Backing track and lyrics are ready!");
        } else {
            MagicCrittercism.a(new IllegalStateException("Backing track was missing in ReviewActivity"));
            android.util.Log.w(ag, "Backing track was lost in the transition");
        }
        return file.exists();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void m21309d(boolean r9) {
        /*
        r8 = this;
        r6 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r1 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r2 = ag;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r3 = "Using pre gain: ";
        r3 = r0.append(r3);
        r0 = com.smule.singandroid.SingServerValues.m21688i();
        if (r0 == 0) goto L_0x00ca;
    L_0x0017:
        r0 = "yes";
    L_0x0019:
        r0 = r3.append(r0);
        r0 = r0.toString();
        com.smule.android.logging.Log.c(r2, r0);
        r0 = com.smule.singandroid.SingServerValues.m21688i();
        if (r0 != 0) goto L_0x00ce;
    L_0x002a:
        r2 = 981668463; // 0x3a83126f float:0.001 double:4.85008663E-315;
        r0 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r4 = java.lang.Math.sqrt(r6);
        r4 = r4 / r6;
        r3 = (float) r4;
        r4 = r8.aP;
        r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x0045;
    L_0x003b:
        r2 = r8.aP;
        r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1));
        if (r2 >= 0) goto L_0x0045;
    L_0x0041:
        r1 = r8.aP;
        r1 = r3 / r1;
    L_0x0045:
        r2 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1));
        if (r2 <= 0) goto L_0x00ce;
    L_0x0049:
        r8.aE = r0;
        r1 = new com.smule.singandroid.SingBundle$Builder;
        r2 = r8.ap;
        r1.<init>(r2);
        r1.m21610a(r0);
        r1 = r1.m21621a();
        r8.ap = r1;
        r1 = r8.f19722l;
        r1 = r1.getProgress();
        r2 = r8.f19722l;
        r2 = r2.getMax();
        r3 = -1052770304; // 0xffffffffc1400000 float:-12.0 double:NaN;
        r4 = 1086324736; // 0x40c00000 float:6.0 double:5.367157323E-315;
        r1 = r8.m21293a(r1, r2, r3, r4);
        r8.bE = r1;
        r1 = r8.bE;
        r2 = r8.bG;
        r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1));
        if (r1 <= 0) goto L_0x007d;
    L_0x0079:
        r1 = r8.bE;
        r8.bG = r1;
    L_0x007d:
        r1 = r8.bE;
        r2 = r8.bF;
        r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1));
        if (r1 >= 0) goto L_0x0089;
    L_0x0085:
        r1 = r8.bE;
        r8.bF = r1;
    L_0x0089:
        r1 = r8.aS;
        r2 = r8.bE;
        r2 = java.lang.Float.valueOf(r2);
        r1.visualGainDb = r2;
        r1 = r8.aS;
        r2 = java.lang.Float.valueOf(r0);
        r1.normalizationScaleFactor = r2;
        r1 = r8.bE;
        r1 = com.smule.singandroid.utils.MathUtils.m25871b(r1);
        r0 = r0 * r1;
        r8.ao = r0;
        r0 = r8.aF;
        if (r0 == 0) goto L_0x00b6;
    L_0x00a8:
        r0 = r8.aF;
        r1 = r8.ao;
        r0.setForegroundLevel_amp(r1);
        r0 = r8.aF;
        r1 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r0.setBackgroundLevel_amp(r1);
    L_0x00b6:
        if (r9 == 0) goto L_0x00c9;
    L_0x00b8:
        r0 = r8.f19728r;
        r1 = r8.ao;
        r0.setForegroundVolume(r1);
        r0 = r8.f19728r;
        r0.m22000b();
        r0 = r8.f19728r;
        r0.invalidate();
    L_0x00c9:
        return;
    L_0x00ca:
        r0 = "no";
        goto L_0x0019;
    L_0x00ce:
        r0 = r1;
        goto L_0x0049;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.smule.singandroid.ReviewActivity.d(boolean):void");
    }

    private void m21249e(String str) {
        if (str != null && !new File(str).delete()) {
            android.util.Log.w(ag, "Failed to delete potentially corrupted file: '" + str + "'");
        }
    }

    @UiThread
    protected void mo6588C() {
        if (!this.f19712V) {
            m21249e(this.aH);
            if (!isFinishing()) {
                this.aW = m21285D();
                this.aW.m23722a(this.ar, this.as);
            }
        } else if (!isFinishing()) {
            MagicCrittercism.a(new IllegalStateException("Looping in Review - giving up"));
            String string = getString(C1947R.string.songbook_download_failed_message);
            this.aX = new BusyDialog((Activity) this, string);
            this.aX.m23577a(2, string, true, getString(C1947R.string.core_ok));
            this.aX.m23579a(new BusyDialogListener(this) {
                final /* synthetic */ ReviewActivity f19636a;

                {
                    this.f19636a = r1;
                }

                public void mo6373a() {
                    this.f19636a.aX.dismiss();
                    this.f19636a.startActivity(new Intent(this.f19636a, MasterActivity_.class));
                    this.f19636a.finish();
                }
            });
            this.aX.m23580a(true);
        }
    }

    private void ag() {
        final Intent intent = new Intent(getIntent());
        intent.putExtra("RESTARTED_KEY", true);
        this.bu.postDelayed(new Runnable(this) {
            final /* synthetic */ ReviewActivity f19638b;

            public void run() {
                this.f19638b.startActivity(intent);
            }
        }, 100);
        finish();
    }

    public SongDownloadDialog m21285D() {
        String i;
        if (!this.ar.m18772r()) {
            i = this.ar.mo6295i();
        } else if (this.ar.mo6295i() != null) {
            i = this.ar.mo6295i();
        } else if (this.ap.m21659h() != null) {
            i = this.ap.m21659h().googleCoverArtUrl;
        } else {
            i = "";
        }
        return new SongDownloadDialog(this, getString(C1947R.string.redownload_song_review), i, new SongDownloadDialogListener(this) {
            final /* synthetic */ ReviewActivity f19643a;

            class C40471 implements Runnable {
                final /* synthetic */ AnonymousClass44 f19641a;

                class C40451 implements Runnable {
                    final /* synthetic */ C40471 f19639a;

                    C40451(C40471 c40471) {
                        this.f19639a = c40471;
                    }

                    public void run() {
                        this.f19639a.f19641a.f19643a.ag();
                    }
                }

                class C40462 implements Runnable {
                    final /* synthetic */ C40471 f19640a;

                    C40462(C40471 c40471) {
                        this.f19640a = c40471;
                    }

                    public void run() {
                        Log.e(ReviewActivity.ag, "Failed to clean up files or something");
                        this.f19640a.f19641a.f19643a.ag();
                    }
                }

                C40471(AnonymousClass44 anonymousClass44) {
                    this.f19641a = anonymousClass44;
                }

                public void run() {
                    this.f19641a.f19643a.aW.dismiss();
                    this.f19641a.f19643a.aW = null;
                    this.f19641a.f19643a.m21301a(false, new C40451(this), new C40462(this));
                }
            }

            class C40482 implements BusyDialogListener {
                final /* synthetic */ AnonymousClass44 f19642a;

                C40482(AnonymousClass44 anonymousClass44) {
                    this.f19642a = anonymousClass44;
                }

                public void mo6373a() {
                    this.f19642a.f19643a.aX.dismiss();
                    this.f19642a.f19643a.finish();
                }
            }

            {
                this.f19643a = r1;
            }

            public void mo6584a(SongbookEntry songbookEntry) {
                android.util.Log.v(ReviewActivity.ag, "Download success! Restarting.");
                this.f19643a.ar = songbookEntry;
                this.f19643a.aY = true;
                this.f19643a.bu.postDelayed(new C40471(this), 500);
            }

            public void mo6583a() {
                MagicCrittercism.a(new IllegalStateException("Backing track re-download failed in SingActivity"));
                this.f19643a.aX.m23577a(2, this.f19643a.getString(C1947R.string.songbook_download_failed_message), true, this.f19643a.getString(C1947R.string.core_ok));
                this.f19643a.aX.m23579a(new C40482(this));
                this.f19643a.aX.m23580a(true);
            }

            public void mo6585b() {
                this.f19643a.finish();
            }
        });
    }

    public void update(Observable observable, Object obj) {
    }

    private void ah() {
        if (this.bL != null) {
            this.bL.m26368b();
        }
        if (this.bJ != null) {
            this.bJ.m26368b();
        }
    }

    private void ai() {
        if (this.bL != null) {
            this.bL.m26370c();
        }
        if (this.bJ != null) {
            this.bJ.m26370c();
            this.bK = true;
        }
    }

    private void aj() {
        if (this.bL != null) {
            this.bL.m26372e();
        }
        if (this.bJ != null) {
            this.bJ.m26372e();
        }
    }

    private void ak() {
        Log.b(ah, "startLocalVideoPlayer:" + this.af);
        String str = "";
        try {
            String uri = new File(this.ba).toURI().toString();
            TextureView textureView = (TextureView) findViewById(C1947R.id.review_local_video_texture_view);
            Handler handler = this.bu;
            GetAudioTimeCallback anonymousClass45 = new GetAudioTimeCallback(this) {
                final /* synthetic */ ReviewActivity f19644a;

                {
                    this.f19644a = r1;
                }

                public float mo6397a() {
                    float f = 0.0f;
                    try {
                        return this.f19644a.aF.getSongPosition_seconds() + (((float) this.f19644a.aA) / 1000.0f);
                    } catch (Throwable e) {
                        Log.d(ReviewActivity.ag, "Exception getting song position from audio interface", e);
                        return f;
                    }
                }
            };
            ExoPlayerStateChangeListener anonymousClass46 = new ExoPlayerStateChangeListener(this) {
                final /* synthetic */ ReviewActivity f19645a;

                {
                    this.f19645a = r1;
                }

                public void mo6398a(int i) {
                    if (!this.f19645a.h() && i == 4 && this.f19645a.bv) {
                        this.f19645a.bv = false;
                        this.f19645a.bu.removeCallbacks(this.f19645a.bw);
                        this.f19645a.bu.post(this.f19645a.bw);
                    }
                }
            };
            String f = (!DeviceSettings.r() || SingServerValues.m21670H().isEmpty()) ? null : this.ap.m21657f();
            this.bL = new ExoPlayerWrapper(this, textureView, handler, uri, anonymousClass45, 0.1f, 0.5f, this, anonymousClass46, f, VideoFilterManager.m26576a(), this.ap.m21658g());
            if (this.af != 0) {
                this.bL.m26371d();
            }
            this.af++;
        } catch (Exception e) {
            Log.e(ah, "invalid file:" + str);
        }
    }

    private void al() {
        if (an() && DeviceSettings.r() && this.bL.m26373f().m26485i()) {
            this.f19705O.setBackgroundResource(this.bi ? C1947R.drawable.airbrush_switch_on : C1947R.drawable.airbrush_switch_off);
            this.f19704N.setOnClickListener(this.bz);
            if (this.ap.f20146k && this.ap.m21648b()) {
                this.f19702L.m23528a(this.ap.m21657f());
                this.f19701K.setOnTouchListener(new OnTouchListener(this) {
                    final /* synthetic */ ReviewActivity f19646a;

                    {
                        this.f19646a = r1;
                    }

                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == 0) {
                            this.f19646a.f19702L.m23167e();
                        }
                        return false;
                    }
                });
            } else {
                if (this.f19711U.getVisibility() == 0) {
                    this.f19702L.m23159a(C1947R.dimen.review_headphones_required_group_video_height);
                }
                this.f19702L.m23529b(this.bL.m26373f().m26484h(), this.bL.m26373f().m26469a(this.ap.m21657f()));
                if (this.bx == null) {
                    this.bx = new PageSwiper();
                    this.bx.m26526a(this.by);
                    this.f19701K.setOnTouchListener(this.bx);
                }
            }
            m21207S();
            return;
        }
        this.f19702L.m23164b();
    }

    private void am() {
        String str;
        if (this.as.joinVideoUrl == null || this.as.joinVideoUrl.isEmpty()) {
            str = this.as.origTrackVideoUrl;
        } else {
            str = this.as.joinVideoUrl;
        }
        this.bJ = new ExoPlayerWrapper(this, (TextureView) findViewById(C1947R.id.review_seed_video_texture_view), this.bu, str, new GetAudioTimeCallback(this) {
            final /* synthetic */ ReviewActivity f19647a;

            {
                this.f19647a = r1;
            }

            public float mo6397a() {
                float f = 0.0f;
                try {
                    return this.f19647a.bd + this.f19647a.aF.getSongPosition_seconds();
                } catch (Throwable e) {
                    Log.d(ReviewActivity.ag, "Exception getting song position from audio interface", e);
                    return f;
                }
            }
        }, 0.1f, 2.0f, null, this.bM, null, false, false);
    }

    public void mo6586E() {
        Log.e(ah, "ExoPlayer internal error");
        if (this.bL != null) {
            this.bL.m26368b();
            this.bL = null;
        }
        a(new Runnable(this) {
            final /* synthetic */ ReviewActivity f19648a;

            {
                this.f19648a = r1;
            }

            public void run() {
                this.f19648a.ak();
            }
        }, 3000);
    }

    public void m21287F() {
        if (an()) {
            this.bX = false;
            this.f19707Q.setVisibility(0);
            this.f19708R.setVisibility(0);
            this.f19701K.setAlpha(0.0f);
            this.f19701K.setVisibility(0);
            LayoutParams layoutParams = this.f19701K.getLayoutParams();
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);
            layoutParams.height = point.x;
            this.f19701K.setLayoutParams(layoutParams);
            layoutParams = this.f19706P.getLayoutParams();
            layoutParams.height = getResources().getDimensionPixelSize(C1947R.dimen.review_waveform_mini_height);
            this.f19706P.setLayoutParams(layoutParams);
            if (this.bf) {
                if (this.ap.f20144i != null) {
                    Metadata a = Metadata.a(new File(this.ap.f20144i));
                    if (a != null) {
                        this.bd = ((float) a.userDelayCalibrationMs) / 1000.0f;
                        Log.b(ag, "Seed video user delay calibration from metadata:" + this.bd);
                    }
                }
                if (this.bd < 0.0f) {
                    this.bd = ((float) this.aF.getUserDelayCalibrationFromBackgroundTrack()) / 1000.0f;
                    Log.b(ag, "Seed video user delay calibration from background track:" + this.bd);
                }
                if (this.bd < 0.0f) {
                    this.bd = 0.0f;
                    Log.b(ag, "Seed video user delay calibration fallback:" + this.bd);
                }
                this.bb = this.as.w();
                Log.b(ag, "mApplyDuetTransitions:" + this.bb);
                final View findViewById = findViewById(C1947R.id.review_local_video_container);
                this.ad = new OnGlobalLayoutListener(this, new ViewTreeObserver.OnGlobalLayoutListener(this) {
                    final /* synthetic */ ReviewActivity f19652b;

                    public void onGlobalLayout() {
                        this.f19652b.m21303b(this.f19652b.ap.f20142g);
                        LayoutUtils.m25859b(findViewById, this.f19652b.ad);
                    }
                });
                LayoutUtils.m25854a(findViewById, this.ad);
                return;
            } else if (this.bJ != null) {
                this.bJ.m26368b();
                this.bJ = null;
                return;
            } else {
                return;
            }
        }
        Log.b(ag, "Video not enabled");
        this.f19701K.setVisibility(8);
        this.bX = true;
    }

    private boolean an() {
        return (!SingApplication.n() || this.ba == null || this.ba.isEmpty()) ? false : true;
    }

    public void m21298a(int i) {
        Log.b(ag, "scorepart: " + i);
        switch (i) {
            case 1:
                if (!this.bU || this.bV) {
                    if (this.bU) {
                        Log.b(ag, "duel -> one");
                        ao();
                    } else {
                        Log.b(ag, "two -> one");
                        m21262i(false);
                    }
                }
                this.bU = true;
                this.bV = false;
                break;
            case 2:
                if (!this.bV || this.bU) {
                    if (this.bV) {
                        Log.b(ag, "duel -> two");
                        ap();
                    } else {
                        Log.b(ag, "one -> two");
                        m21262i(true);
                    }
                }
                this.bU = false;
                this.bV = true;
                break;
            case 3:
                if (!(this.bU && this.bV)) {
                    if (this.bU) {
                        aq();
                    } else if (this.bV) {
                        ar();
                    }
                }
                this.bU = true;
                this.bV = true;
                break;
        }
        this.bW = i;
    }

    void m21303b(int i) {
        if (i == 1) {
            this.bN = findViewById(C1947R.id.review_local_video_container);
            this.bO = findViewById(C1947R.id.review_seed_video_container);
            this.bP = findViewById(C1947R.id.review_local_video_texture_view);
            this.bQ = findViewById(C1947R.id.review_seed_video_texture_view);
        } else {
            this.bO = findViewById(C1947R.id.review_local_video_container);
            this.bN = findViewById(C1947R.id.review_seed_video_container);
            this.bQ = findViewById(C1947R.id.review_local_video_texture_view);
            this.bP = findViewById(C1947R.id.review_seed_video_texture_view);
        }
        Log.b(ah, "anim:" + this.bN.getWidth());
        this.bT = this.bN.getWidth();
        this.bR = this.bN.getWidth() / 4;
        this.bS = this.bN.getWidth() / 2;
        this.bN.setTranslationX((float) (0 - this.bS));
        this.bP.setTranslationX((float) this.bR);
        this.bO.setTranslationX((float) this.bS);
        this.bQ.setTranslationX((float) (0 - this.bR));
    }

    private void ao() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.bN, "translationX", new float[]{(float) (0 - this.bS), 0.0f});
        ofFloat.setDuration(250);
        ofFloat.start();
        ofFloat = ObjectAnimator.ofFloat(this.bP, "translationX", new float[]{(float) this.bR, 0.0f});
        ofFloat.setDuration(250);
        ofFloat.start();
        ofFloat = ObjectAnimator.ofFloat(this.bO, "translationX", new float[]{(float) this.bS, (float) this.bT});
        ofFloat.setDuration(250);
        ofFloat.start();
    }

    private void ap() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.bN, "translationX", new float[]{(float) (0 - this.bS), (float) (0 - this.bT)});
        ofFloat.setDuration(250);
        ofFloat.start();
        ofFloat = ObjectAnimator.ofFloat(this.bO, "translationX", new float[]{(float) this.bS, 0.0f});
        ofFloat.setDuration(250);
        ofFloat.start();
        ofFloat = ObjectAnimator.ofFloat(this.bQ, "translationX", new float[]{(float) (0 - this.bR), 0.0f});
        ofFloat.setDuration(250);
        ofFloat.start();
    }

    private void m21262i(boolean z) {
        int i;
        int i2 = 0;
        if (z) {
            this.bQ.setTranslationX(0.0f);
        } else {
            this.bP.setTranslationX(0.0f);
        }
        View view = this.bN;
        String str = "translationX";
        float[] fArr = new float[2];
        fArr[0] = (float) (z ? 0 : 0 - this.bT);
        fArr[1] = (float) (z ? 0 - this.bT : 0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, str, fArr);
        ofFloat.setDuration(250);
        ofFloat.start();
        view = this.bO;
        str = "translationX";
        fArr = new float[2];
        if (z) {
            i = this.bT;
        } else {
            i = 0;
        }
        fArr[0] = (float) i;
        if (!z) {
            i2 = this.bT;
        }
        fArr[1] = (float) i2;
        ofFloat = ObjectAnimator.ofFloat(view, str, fArr);
        ofFloat.setDuration(250);
        ofFloat.start();
    }

    private void aq() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.bN, "translationX", new float[]{0.0f, (float) (0 - this.bS)});
        ofFloat.setDuration(250);
        ofFloat.start();
        ofFloat = ObjectAnimator.ofFloat(this.bP, "translationX", new float[]{0.0f, (float) this.bR});
        ofFloat.setDuration(250);
        ofFloat.start();
        ofFloat = ObjectAnimator.ofFloat(this.bO, "translationX", new float[]{(float) this.bT, (float) this.bS});
        ofFloat.setDuration(250);
        ofFloat.start();
        this.bQ.setTranslationX((float) (0 - this.bR));
    }

    private void ar() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.bN, "translationX", new float[]{(float) (0 - this.bT), (float) (0 - this.bS)});
        ofFloat.setDuration(250);
        ofFloat.start();
        this.bP.setTranslationX((float) this.bR);
        ofFloat = ObjectAnimator.ofFloat(this.bO, "translationX", new float[]{0.0f, (float) this.bS});
        ofFloat.setDuration(250);
        ofFloat.start();
        ofFloat = ObjectAnimator.ofFloat(this.bQ, "translationX", new float[]{0.0f, (float) (0 - this.bR)});
        ofFloat.setDuration(250);
        ofFloat.start();
    }

    @Click
    protected void m21288G() {
        if (at()) {
            m21289H();
        }
    }

    protected void m21289H() {
        if (this.bX) {
            this.bX = false;
        } else if (this.bY) {
            this.bY = false;
        } else {
            boolean z;
            if (this.bZ) {
                z = false;
            } else {
                z = true;
            }
            this.bZ = z;
            this.bX = false;
            this.bY = false;
        }
        m21291J();
    }

    @Click
    protected void m21290I() {
        if (at()) {
            this.bX = true;
            this.bY = false;
            m21291J();
        }
    }

    protected void m21291J() {
        Log.b(ag, "mVideoSquare:" + this.bZ);
        Log.b(ag, "mVocalMatchExpanded:" + this.bY);
        Log.b(ag, "mPlayerBarExpanded:" + this.bX);
        if (this.bY) {
            m21317t();
        } else {
            m21319v();
        }
        m21311e(this.bX);
        m21312f(this.bZ);
    }

    protected void m21311e(final boolean z) {
        int i = 0;
        if ((this.f19707Q.getVisibility() == 8) != z) {
            int i2;
            int dimensionPixelOffset;
            View view = this.f19707Q;
            if (z) {
                i2 = 8;
            } else {
                i2 = 0;
            }
            view.setVisibility(i2);
            ImageView imageView = this.f19708R;
            if (z) {
                i2 = 8;
            } else {
                i2 = 0;
            }
            imageView.setVisibility(i2);
            this.br = new AnimatorSet();
            i2 = getResources().getDimensionPixelSize(C1947R.dimen.review_waveform_view_height) - au();
            if (!z && this.f19693C.getVisibility() == 0) {
                i2 = 0;
            } else if (!z && this.f19693C.getVisibility() != 0) {
                i2 = -i2;
            } else if (z && this.f19693C.getVisibility() == 0) {
                i2 = 0;
            }
            ValueAnimator a = m21295a(this.f19731u, this.f19731u.getHeight(), this.f19731u.getHeight() + i2);
            if (z) {
                dimensionPixelOffset = getResources().getDimensionPixelOffset(C1947R.dimen.review_waveform_view_height);
            } else {
                dimensionPixelOffset = au();
            }
            ValueAnimator a2 = m21295a(this.f19706P, this.f19706P.getHeight(), dimensionPixelOffset);
            this.br.addListener(new AnimatorListener(this) {
                final /* synthetic */ ReviewActivity f19654b;

                public void onAnimationEnd(Animator animator) {
                    if (!this.f19654b.h()) {
                        this.f19654b.f19728r.m22000b();
                        if (this.f19654b.f19693C.getVisibility() != 0 && !z && !this.f19654b.bZ) {
                            this.f19654b.as();
                        }
                    }
                }

                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }
            });
            this.br.setDuration(300);
            if (i2 != 0) {
                this.br.playTogether(new Animator[]{a2, a});
            } else {
                this.br.playTogether(new Animator[]{a2});
            }
            this.br.start();
            View view2 = this.f19709S;
            if (!z) {
                i = 8;
            }
            view2.setVisibility(i);
        }
    }

    protected void m21312f(boolean z) {
        ValueAnimator a;
        ValueAnimator a2;
        ValueAnimator a3;
        if (z) {
            if (this.bk || this.f19702L.getMeasuredHeight() == this.f19701K.getMeasuredWidth()) {
                Log.b(ag, "video already square");
                return;
            }
            this.bs = new AnimatorSet();
            a = m21225a(this.f19731u, (float) this.bm);
            a2 = m21295a(this.f19702L, this.f19702L.getHeight(), this.f19702L.getHeight() + this.bm);
            a3 = m21295a(this.f19703M, this.f19703M.getHeight(), this.f19703M.getHeight() + this.bm);
            this.bs.setDuration(300);
            this.bs.playTogether(new Animator[]{a, a2, a3});
            this.bs.start();
        } else if (this.bk || this.f19702L.getMeasuredHeight() == this.bn) {
            Log.b(ag, "scroll pane already set");
        } else {
            this.bt = new AnimatorSet();
            a = m21225a(this.f19731u, (float) (-this.bm));
            a2 = m21295a(this.f19702L, this.f19702L.getHeight(), this.f19702L.getHeight() - this.bm);
            a3 = m21295a(this.f19703M, this.f19703M.getHeight(), this.f19703M.getHeight() - this.bm);
            Animator a4 = m21295a(this.f19732v, this.f19732v.getHeight(), getResources().getDimensionPixelSize(C1947R.dimen.review_section_heading_height));
            this.bt.setDuration(300);
            if (this.bo != 0) {
                this.bt.playTogether(new Animator[]{a, a2, a3});
            } else {
                this.bt.play(a4);
            }
            this.bt.addListener(new AnimatorListener(this) {
                final /* synthetic */ ReviewActivity f19655a;

                {
                    this.f19655a = r1;
                }

                public void onAnimationEnd(Animator animator) {
                    if (!this.f19655a.h()) {
                        if (this.f19655a.an() && !this.f19655a.bk && this.f19655a.f19701K.getBottom() < this.f19655a.f19731u.getTop()) {
                            Log.c(ReviewActivity.ag, "full square video already displayed. Adjust view heights");
                            this.f19655a.bk = true;
                            int top = this.f19655a.f19731u.getTop() - this.f19655a.f19701K.getBottom();
                            this.f19655a.bm = -top;
                            AnimatorSet animatorSet = new AnimatorSet();
                            int measuredHeight = this.f19655a.f19731u.getMeasuredHeight() + top;
                            ValueAnimator a = this.f19655a.m21295a(this.f19655a.f19731u, this.f19655a.f19731u.getMeasuredHeight(), measuredHeight);
                            this.f19655a.bo = measuredHeight;
                            this.f19655a.bp = this.f19655a.f19731u.getY() - ((float) top);
                            measuredHeight = this.f19655a.f19706P.getMeasuredHeight() + top;
                            ValueAnimator a2 = this.f19655a.m21295a(this.f19655a.f19706P, this.f19655a.f19706P.getMeasuredHeight(), measuredHeight);
                            this.f19655a.bC = this.f19655a.bC + top;
                            this.f19655a.bl = measuredHeight;
                            measuredHeight = this.f19655a.f19702L.getMeasuredHeight() - top;
                            ValueAnimator a3 = this.f19655a.m21295a(this.f19655a.f19702L, this.f19655a.f19702L.getMeasuredHeight(), measuredHeight);
                            this.f19655a.bn = measuredHeight;
                            ValueAnimator a4 = this.f19655a.m21295a(this.f19655a.f19703M, this.f19655a.f19703M.getMeasuredHeight(), this.f19655a.f19703M.getMeasuredHeight() - top);
                            animatorSet.playTogether(new Animator[]{a, a2, a3, a4});
                            animatorSet.setDuration(300);
                            animatorSet.start();
                        }
                        if (this.f19655a.bn == 0) {
                            this.f19655a.bn = this.f19655a.f19702L.getMeasuredHeight();
                        }
                        if (this.f19655a.bo == 0) {
                            this.f19655a.bo = this.f19655a.f19731u.getMeasuredHeight();
                        }
                        if (this.f19655a.bp == 0.0f) {
                            this.f19655a.bp = this.f19655a.f19731u.getY();
                        }
                        if (this.f19655a.bm == 0) {
                            this.f19655a.bm = this.f19655a.f19701K.getBottom() - this.f19655a.f19731u.getTop();
                        }
                        if (!this.f19655a.bj) {
                            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f19655a.f19702L.getLayoutParams();
                            layoutParams.addRule(2, 0);
                            this.f19655a.f19702L.setLayoutParams(layoutParams);
                            layoutParams = (RelativeLayout.LayoutParams) this.f19655a.f19703M.getLayoutParams();
                            layoutParams.addRule(2, 0);
                            this.f19655a.f19703M.setLayoutParams(layoutParams);
                            layoutParams = (RelativeLayout.LayoutParams) this.f19655a.f19731u.getLayoutParams();
                            layoutParams.addRule(3, 0);
                            this.f19655a.f19731u.setLayoutParams(layoutParams);
                            this.f19655a.bj = true;
                        }
                        this.f19655a.m21292K();
                    }
                }

                public void onAnimationCancel(Animator animator) {
                    onAnimationEnd(animator);
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }
            });
            this.bt.start();
        }
    }

    protected void m21292K() {
        LayoutParams layoutParams = this.f19702L.getLayoutParams();
        layoutParams.height = this.bn;
        this.f19702L.setLayoutParams(layoutParams);
        layoutParams = this.f19703M.getLayoutParams();
        layoutParams.height = this.bn;
        this.f19703M.setLayoutParams(layoutParams);
        layoutParams = this.f19731u.getLayoutParams();
        layoutParams.height = this.bo;
        this.f19731u.setLayoutParams(layoutParams);
    }

    private void as() {
        LayoutParams layoutParams = this.f19702L.getLayoutParams();
        layoutParams.height = this.bn;
        this.f19702L.setLayoutParams(layoutParams);
        layoutParams = this.f19703M.getLayoutParams();
        layoutParams.height = this.bn;
        this.f19703M.setLayoutParams(layoutParams);
        layoutParams = this.f19731u.getLayoutParams();
        layoutParams.height = this.bo;
        this.f19731u.setY(this.bp);
        this.f19731u.setTranslationY(0.0f);
        this.f19731u.setLayoutParams(layoutParams);
    }

    private boolean at() {
        return (this.bs == null || !this.bs.isRunning()) && ((this.bt == null || !this.bt.isRunning()) && ((this.br == null || !this.br.isRunning()) && (this.bq == null || !this.bq.isRunning())));
    }

    private int au() {
        if (this.bk) {
            return this.bl;
        }
        return getResources().getDimensionPixelOffset(C1947R.dimen.review_waveform_mini_height);
    }

    private void av() {
        final ParticleSystem particleSystem = new ParticleSystem((ViewGroup) this.f19726p, 1000, getResources().getDrawable(C1947R.drawable.glow_particle_25_percent), 750);
        particleSystem.m17362a(0.05f, 0.3f);
        particleSystem.m17366a(new ScaleModifier(0.3f, 0.0f, 0, 750));
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, (float) this.f19701K.getMeasuredWidth()});
        ofFloat.setDuration(1600);
        ofFloat.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ ReviewActivity f19657b;

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (!this.f19657b.h()) {
                    Random random = new Random();
                    particleSystem.m17363a(0.0f, 0.03f, 0, 360);
                    particleSystem.m17364a(5.0E-5f, random.nextInt(361));
                    this.f19657b.f19703M.setX(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    particleSystem.m17368a(this.f19657b.f19703M, 5);
                }
            }
        });
        ofFloat.addListener(new AnimatorListener(this) {
            final /* synthetic */ ReviewActivity f19660b;

            class C40501 implements Runnable {
                final /* synthetic */ AnonymousClass55 f19658a;

                C40501(AnonymousClass55 anonymousClass55) {
                    this.f19658a = anonymousClass55;
                }

                public void run() {
                    if (!this.f19658a.f19660b.h() && this.f19658a.f19660b.bL != null && this.f19658a.f19660b.bL.m26373f() != null) {
                        this.f19658a.f19660b.bL.m26373f().m26476a(true);
                    }
                }
            }

            public void onAnimationStart(Animator animator) {
                this.f19660b.f19704N.setOnClickListener(null);
                new UiHandler(this.f19660b).postDelayed(new C40501(this), 1040);
            }

            public void onAnimationEnd(Animator animator) {
                if (!this.f19660b.h()) {
                    particleSystem.m17367a();
                    this.f19660b.f19703M.setLayerType(0, null);
                    this.f19660b.f19704N.setOnClickListener(this.f19660b.bz);
                }
            }

            public void onAnimationCancel(Animator animator) {
                onAnimationEnd(animator);
            }

            public void onAnimationRepeat(Animator animator) {
            }
        });
        this.f19703M.setLayerType(2, null);
        particleSystem.m17369a(this.f19703M, 5, HttpResponseCode.MULTIPLE_CHOICES);
        ofFloat.start();
    }

    protected void m21308d(String str) {
        m21299a(str, (int) C1947R.string.sing_audio_recording_error_header, (int) C1947R.string.sing_audio_recording_error_body);
    }

    protected void m21300a(Throwable th) {
        if (th == null || th.getMessage() == null) {
            m21308d("null");
        } else {
            m21308d(th.getMessage());
        }
    }

    @android.support.annotation.UiThread
    protected void m21299a(String str, int i, int i2) {
        if (!isFinishing()) {
            if (this.ae != null) {
                Log.d(ag, "review error dialog showing");
                Log.e(ag, str);
                return;
            }
            this.ae = new TextAlertDialog((Context) this, i, i2, true, false);
            this.ae.m19806a(getString(C1947R.string.core_ok), "");
            this.ae.m19803a(new CustomAlertDialogListener(this) {
                final /* synthetic */ ReviewActivity f19661a;

                {
                    this.f19661a = r1;
                }

                public void mo6385a(CustomAlertDialog customAlertDialog) {
                    if (this.f19661a.ae != null) {
                        this.f19661a.ae.dismiss();
                        this.f19661a.ae = null;
                    }
                    if (this.f19661a.ap != null) {
                        PreSingActivity.m24763a(this.f19661a).m24796a(StartupMode.BADVIDEO).m24795a(this.f19661a.ap).m24793a(this.f19661a.ap.f20141f).a();
                    }
                    this.f19661a.finish();
                }

                public void mo6386b(CustomAlertDialog customAlertDialog) {
                    mo6385a(customAlertDialog);
                }
            });
            Log.e(ag, str);
            this.ae.show();
        }
    }

    protected void m21313g(boolean z) {
        this.f19728r.setTouchable(z);
        this.f19720j.setEnabled(z);
        this.f19696F.setEnabled(z);
        this.f19697G.setEnabled(z);
        this.f19723m.setEnabled(z);
        this.f19694D.setEnabled(z);
        this.f19722l.setEnabled(z);
        this.f19699I.setClickable(z);
        this.f19718h.setEnabled(z);
    }

    protected void m21301a(boolean z, Runnable runnable, Runnable runnable2) {
        m21313g(false);
        ah();
        if (this.aF != null) {
            this.aF.m22271a(z, runnable, runnable2);
        }
    }
}
