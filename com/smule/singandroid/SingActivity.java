package com.smule.singandroid;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Point;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
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
import com.smule.android.audio.AudioDefs;
import com.smule.android.audio.AudioDefs.HeadphonesType;
import com.smule.android.audio.AudioDefs.MonitoringMode;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.CameraStatusType;
import com.smule.android.logging.Analytics.Ensemble;
import com.smule.android.logging.Analytics.PageType;
import com.smule.android.logging.Analytics.UserPath;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.logging.TrackedActivity;
import com.smule.android.network.api.StoreAPI$ProductType;
import com.smule.android.network.api.StoreAPI$StreamType;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.managers.ArrangementManager;
import com.smule.android.network.managers.EntitlementsManager;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.RecommendationManager;
import com.smule.android.network.managers.RecommendationManager.CacheDuration;
import com.smule.android.network.managers.SongManager;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.ArrangementVersion;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.ui.SNPImageView;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.ResourceUtils;
import com.smule.android.utils.SimpleBarrier;
import com.smule.singandroid.SingBundle.Builder;
import com.smule.singandroid.ads.AdUtils;
import com.smule.singandroid.audio.AudioInterface;
import com.smule.singandroid.audio.AudioInterface.FailRunnable;
import com.smule.singandroid.audio.AudioUtils;
import com.smule.singandroid.audio.Metadata;
import com.smule.singandroid.audio.OpenSLStreamVersion;
import com.smule.singandroid.audio.exception.UninitializedException;
import com.smule.singandroid.customviews.LyricsView;
import com.smule.singandroid.customviews.PitchView;
import com.smule.singandroid.customviews.RadianceView;
import com.smule.singandroid.customviews.VUMeterView;
import com.smule.singandroid.customviews.VerticalSeekBar;
import com.smule.singandroid.customviews.VisualizerView;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.BusyDialog.BusyDialogListener;
import com.smule.singandroid.dialogs.BusyScreenDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.SongDownloadDialog;
import com.smule.singandroid.dialogs.SongDownloadDialog.SongDownloadDialogListener;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.models.Lyric;
import com.smule.singandroid.models.Lyric.Version;
import com.smule.singandroid.models.LyricLine;
import com.smule.singandroid.models.SongLyrics;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;
import com.smule.singandroid.utils.AppIndexer;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.CustomTypefaceSpan;
import com.smule.singandroid.utils.MathUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.AudioCompletionContext;
import com.smule.singandroid.utils.SingAnalytics.SingFlowPhase;
import com.smule.singandroid.utils.SongbookEntryUtils;
import com.smule.singandroid.utils.StyleReplacer;
import com.smule.singandroid.utils.TypefaceUtils;
import com.smule.singandroid.video.ExoPlayerPlaybackWrapper;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import twitter4j.HttpResponseCode;

@EActivity
public class SingActivity extends BaseActivity implements TrackedActivity {
    private static final String aI = SingActivity.class.getName();
    @ViewById
    protected TextView f20034A;
    @ViewById
    protected RelativeLayout f20035B;
    @ViewById
    protected PitchView f20036C;
    @ViewById
    protected VUMeterView f20037D;
    @ViewById
    protected VisualizerView f20038E;
    @ViewById
    protected View f20039F;
    @ViewById
    protected View f20040G;
    @ViewById
    protected View f20041H;
    @ViewById
    protected ProgressBar f20042I;
    @ViewById
    protected ViewGroup f20043J;
    @ViewById
    protected View f20044K;
    @ViewById
    protected LyricsView f20045L;
    @ViewById
    protected ViewGroup f20046M;
    @ViewById
    protected SNPImageView f20047N;
    @ViewById
    protected SNPImageView f20048O;
    @ViewById
    protected SNPImageView f20049P;
    @ViewById
    protected TextView f20050Q;
    @ViewById
    protected ImageView f20051R;
    @ViewById
    protected TextView f20052S;
    @ViewById
    protected LinearLayout f20053T;
    @ViewById
    protected FrameLayout f20054U;
    @ViewById
    protected FrameLayout f20055V;
    @ViewById
    protected SNPImageView f20056W;
    @ViewById
    protected SNPImageView f20057X;
    @ViewById
    protected ImageView f20058Y;
    @ViewById
    protected ImageView f20059Z;
    protected float aA;
    protected float aB;
    protected AudioInterface aC;
    Metadata aD = new Metadata();
    protected UiAudioLoop aE;
    protected String aF;
    protected volatile boolean aG = false;
    protected TextAlertDialog aH;
    private AppIndexer aJ = new AppIndexer();
    private SongbookEntry aK;
    private int aL;
    private boolean aM;
    private String aN;
    private String aO;
    private String aP;
    private boolean aQ;
    private Ensemble aR;
    private AtomicBoolean aS = new AtomicBoolean();
    private AtomicBoolean aT = new AtomicBoolean();
    private HeadSetBroadCastReceiver aU;
    private Animation aV;
    private Animation aW;
    private Animation aX;
    private Animation aY;
    private Animation aZ;
    @ViewById
    protected RadianceView aa;
    @ViewById
    protected RadianceView ab;
    @ViewById
    protected RelativeLayout ac;
    @ViewById
    protected View ad;
    @ViewById
    protected ImageView ae;
    @ViewById
    protected RelativeLayout af;
    @ViewById
    protected VerticalSeekBar ag;
    @ViewById
    protected FrameLayout ah;
    @ViewById
    protected RelativeLayout ai;
    @ViewById
    protected RelativeLayout aj;
    @ViewById
    protected TextView ak;
    @Extra
    protected boolean al;
    protected SongLyrics am;
    protected SingBundle an;
    protected PerformanceV2 ao;
    protected int ap;
    protected boolean aq = false;
    protected boolean ar;
    protected TextAlertDialog as;
    protected Handler at;
    protected int au;
    protected boolean av;
    protected SingCountdown aw;
    protected SongDownloadDialog ax;
    protected BusyDialog ay;
    protected TextAlertDialog az;
    private Animation ba;
    private Animation bb;
    private Animation bc;
    private boolean bd = true;
    private boolean be = true;
    private boolean bf;
    private boolean bg;
    private HeadphonesType bh;
    private int bi;
    private boolean bj = true;
    private float bk;
    private boolean bl = false;
    private boolean bm = true;
    private PortraitAnimations bn;
    private PortraitAnimations bo;
    private PortraitAnimations bp;
    private PortraitAnimations bq;
    private PortraitAnimations br;
    private SimpleBarrier bs;
    private OnClickListener bt = new OnClickListener(this) {
        final /* synthetic */ SingActivity f19983a;

        class C41131 implements Runnable {
            final /* synthetic */ AnonymousClass16 f19982a;

            C41131(AnonymousClass16 anonymousClass16) {
                this.f19982a = anonymousClass16;
            }

            public void run() {
                if (this.f19982a.f19983a.ap == C1947R.id.mPauseMenuResumeButtonLayout) {
                    if (this.f19982a.f19983a.aw != null) {
                        this.f19982a.f19983a.aw.m21502a();
                    } else if (this.f19982a.f19983a.f20079z.getVisibility() == 0) {
                        this.f19982a.f19983a.mo6643g(false);
                    } else {
                        this.f19982a.f19983a.mo6640c(false);
                    }
                } else if (this.f19982a.f19983a.ap == C1947R.id.mPauseMenuRestartButtonLayout) {
                    try {
                        Log.a(AudioInterface.f20656a, "calling setSongPosition_seconds, prepareForRealTime from dismissPauseMenu");
                        this.f19982a.f19983a.aC.rewindRecording();
                        this.f19982a.f19983a.aC.prepareForRealTime();
                        this.f19982a.f19983a.mo6630U();
                        this.f19982a.f19983a.mo6643g(true);
                    } catch (Throwable e) {
                        Log.d(SingActivity.aI, "Failed to restart audio stuff because of an exception in native code", e);
                        this.f19982a.f19983a.mo6639c("Failed to restart audio");
                    }
                } else if (this.f19982a.f19983a.ap == C1947R.id.mPauseMenuNewSongButtonLayout) {
                    this.f19982a.f19983a.mo6632a(this.f19982a.f19983a.aC.getSongPosition_seconds());
                } else if (this.f19982a.f19983a.ap == C1947R.id.mPauseMenuSaveButtonLayout) {
                    this.f19982a.f19983a.mo6631Z();
                }
            }
        }

        {
            this.f19983a = r1;
        }

        public void onClick(View view) {
            if (this.f19983a.bf) {
                this.f19983a.ap = view.getId();
                this.f19983a.mo6633a(8);
                this.f19983a.m21568c(new C41131(this));
            }
        }
    };
    private final float bu = 1.6f;
    private final float bv = 0.73f;
    private final float bw = 0.75f;
    private final float bx = 0.52f;
    private final float by = 0.56f;
    private final int bz = HttpResponseCode.INTERNAL_SERVER_ERROR;
    public volatile boolean f20060g = false;
    public volatile boolean f20061h = false;
    public double f20062i = 0.0d;
    public boolean f20063j = false;
    @InstanceState
    protected boolean f20064k = false;
    @InstanceState
    protected boolean f20065l = false;
    @InstanceState
    protected boolean f20066m = false;
    @ViewById
    protected FrameLayout f20067n;
    @ViewById
    protected RelativeLayout f20068o;
    @ViewById
    protected TextView f20069p;
    @ViewById
    protected TextView f20070q;
    @ViewById
    protected TextView f20071r;
    @ViewById
    protected TextView f20072s;
    @ViewById
    protected TextView f20073t;
    @ViewById
    protected TextView f20074u;
    @ViewById
    protected TextView f20075v;
    @ViewById
    protected TextView f20076w;
    @ViewById
    protected TextView f20077x;
    @ViewById
    protected TextView f20078y;
    @ViewById
    protected View f20079z;

    class C41151 implements Runnable {
        final /* synthetic */ SingActivity f19988a;

        C41151(SingActivity singActivity) {
            this.f19988a = singActivity;
        }

        public void run() {
            this.f19988a.mo6621y();
        }
    }

    class C41212 implements OnClickListener {
        final /* synthetic */ SingActivity f20003a;

        C41212(SingActivity singActivity) {
            this.f20003a = singActivity;
        }

        public void onClick(View view) {
            this.f20003a.m21535G();
        }
    }

    class C41223 implements OnSeekBarChangeListener {
        final /* synthetic */ SingActivity f20004a;

        C41223(SingActivity singActivity) {
            this.f20004a = singActivity;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            Log.a(AudioInterface.f20656a, "calling setMonitoringLevel_amp from onProgressChanged");
            if (this.f20004a.aC != null) {
                this.f20004a.aC.setMonitoringLevel_amp(this.f20004a.m21533E());
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    class C41234 implements OnClickListener {
        final /* synthetic */ SingActivity f20005a;

        C41234(SingActivity singActivity) {
            this.f20005a = singActivity;
        }

        public void onClick(View view) {
            if (this.f20005a.af.getVisibility() == 0) {
                this.f20005a.af.setVisibility(8);
                this.f20005a.ae.setImageDrawable(this.f20005a.getResources().getDrawable(C1947R.drawable.icn_vocal_vol_white));
            } else if (view instanceof ImageView) {
                this.f20005a.af.setVisibility(0);
                this.f20005a.ae.setImageDrawable(this.f20005a.getResources().getDrawable(C1947R.drawable.icn_vocal_vol_gray));
            }
        }
    }

    class C41265 implements Runnable {
        Runnable f20008a = new C41241(this);
        FailRunnable f20009b = new C41252(this);
        final /* synthetic */ SingActivity f20010c;

        class C41241 implements Runnable {
            final /* synthetic */ C41265 f20006a;

            C41241(C41265 c41265) {
                this.f20006a = c41265;
            }

            public void run() {
                this.f20006a.f20010c.m21514a(SingApplication.g().e(), false);
                this.f20006a.f20010c.bs.m19034a();
                AudioInterface audioInterface = this.f20006a.f20010c.aC;
                Log.a(AudioInterface.f20656a, "calling setBackgroundLevel_amp from onViewsCreated");
                this.f20006a.f20010c.aC.setBackgroundLevel_amp(0.5f);
                if (!(DeviceSettings.n() == MonitoringMode.NONE || this.f20006a.f20010c.ag == null)) {
                    this.f20006a.f20010c.ag.setProgress((int) (MagicPreferences.m20296a(this.f20006a.f20010c, 0.5f) * ((float) this.f20006a.f20010c.ag.getMax())));
                    Log.a(AudioInterface.f20656a, "calling setMonitoringLevel_amp from onViewsCreated");
                    this.f20006a.f20010c.aC.setMonitoringLevel_amp(this.f20006a.f20010c.m21533E());
                }
                if (this.f20006a.f20010c.an.m21643a() && this.f20006a.f20010c.an.f20146k) {
                    audioInterface = this.f20006a.f20010c.aC;
                    Log.a(AudioInterface.f20656a, "calling setMonitoringPan from onViewsCreated");
                    this.f20006a.f20010c.aC.setMonitoringPan(0.25f);
                }
                try {
                    audioInterface = this.f20006a.f20010c.aC;
                    Log.a(AudioInterface.f20656a, "calling start from onViewsCreated");
                    this.f20006a.f20010c.aC.start();
                    if (this.f20006a.f20010c.aE == null) {
                        this.f20006a.f20010c.mo6639c("mUiAudioLoop null");
                        return;
                    }
                    this.f20006a.f20010c.aE.mo6625b();
                    this.f20006a.f20010c.registerReceiver(this.f20006a.f20010c.aU, new IntentFilter("android.intent.action.HEADSET_PLUG"));
                    this.f20006a.f20010c.mo6628S();
                } catch (Throwable th) {
                    this.f20006a.f20010c.m21562a(th);
                }
            }
        }

        class C41252 extends FailRunnable {
            final /* synthetic */ C41265 f20007a;

            C41252(C41265 c41265) {
                this.f20007a = c41265;
            }

            public void run() {
                this.f20007a.f20010c.aC.stopAndShutdown();
                this.f20007a.f20010c.aC = null;
                this.f20007a.f20010c.bs.m19034a();
                if (m21146a() instanceof UninitializedException) {
                    this.f20007a.f20010c.m21562a(m21146a());
                } else {
                    this.f20007a.f20010c.mo6614W();
                }
            }
        }

        C41265(SingActivity singActivity) {
            this.f20010c = singActivity;
        }

        public void run() {
            this.f20010c.bs.m19037d();
            AudioInterface audioInterface = this.f20010c.aC;
            Log.a(AudioInterface.f20656a, "calling setupPerformance from onViewsCreated in SingActivity");
            this.f20010c.aC.m22270a(this.f20010c.aN, this.f20010c.aO, this.f20010c.aP, null, null, this.f20008a, this.f20009b);
        }
    }

    class C41276 implements Runnable {
        final /* synthetic */ SingActivity f20011a;

        C41276(SingActivity singActivity) {
            this.f20011a = singActivity;
        }

        public void run() {
            this.f20011a.mo6639c("Failed to setup audio system. Cannot continue");
        }
    }

    class C41287 implements Runnable {
        final /* synthetic */ SingActivity f20012a;

        C41287(SingActivity singActivity) {
            this.f20012a = singActivity;
        }

        public void run() {
            this.f20012a.f20070q.setClickable(true);
            this.f20012a.f20069p.setClickable(true);
            this.f20012a.f20071r.setClickable(true);
            this.f20012a.f20073t.setClickable(true);
            this.f20012a.av = false;
        }
    }

    class C41309 implements Runnable {
        final /* synthetic */ SingActivity f20015a;

        C41309(SingActivity singActivity) {
            this.f20015a = singActivity;
        }

        public void run() {
            if (this.f20015a.aw != null) {
                this.f20015a.mo6643g(this.f20015a.bg);
            } else {
                this.f20015a.mo6640c(false);
            }
        }
    }

    private class AudioSystemSetupTask extends AsyncTask<Context, Void, Boolean> {
        final /* synthetic */ SingActivity f20016a;
        private Runnable f20017b = null;
        private Runnable f20018c = null;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m21498a((Context[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m21499a((Boolean) obj);
        }

        AudioSystemSetupTask(SingActivity singActivity, Runnable runnable, Runnable runnable2) {
            this.f20016a = singActivity;
            this.f20017b = runnable;
            this.f20018c = runnable2;
        }

        protected Boolean m21498a(Context... contextArr) {
            try {
                Log.a(AudioInterface.f20656a, "instantiating AudioInterface from AudioSystemSetupTask");
                this.f20016a.aC = new AudioInterface(contextArr[0], this.f20016a.aP);
                return Boolean.TRUE;
            } catch (Throwable e) {
                Log.d(SingActivity.aI, "Failed to initialize audio engine becuase of an exception in native code", e);
                return Boolean.FALSE;
            }
        }

        protected void m21499a(Boolean bool) {
            if (bool.booleanValue()) {
                this.f20017b.run();
            } else {
                this.f20018c.run();
            }
        }
    }

    public class HeadSetBroadCastReceiver extends BroadcastReceiver {
        final /* synthetic */ SingActivity f20019a;

        public HeadSetBroadCastReceiver(SingActivity singActivity) {
            this.f20019a = singActivity;
        }

        public void onReceive(Context context, Intent intent) {
            boolean z = true;
            String action = intent.getAction();
            if (action != null && action.compareTo("android.intent.action.HEADSET_PLUG") == 0) {
                Log.b(SingActivity.aI, "onReceive - ACTION_HEADSET_PLUG");
                int intExtra = intent.getIntExtra("state", 0);
                int intExtra2 = intent.getIntExtra("microphone", -1);
                SingActivity singActivity = this.f20019a;
                boolean z2 = intExtra == 1;
                if (intExtra2 != 1) {
                    z = false;
                }
                singActivity.m21514a(z2, z);
                if (this.f20019a.ar) {
                    Log.b(SingActivity.aI, "onReceive - showing pause menu");
                    this.f20019a.ar = false;
                    this.f20019a.m21535G();
                }
                this.f20019a.ac();
            }
        }
    }

    private static class PortraitAnimations {
        public final AnimationSet f20020a;
        public final AnimationSet f20021b;

        private PortraitAnimations(AnimationSet animationSet, AnimationSet animationSet2) {
            this.f20020a = animationSet;
            this.f20021b = animationSet2;
        }
    }

    private class SaveRecordingTask extends AsyncTask<Void, Void, Boolean> {
        BusyScreenDialog f20022a;
        final /* synthetic */ SingActivity f20023b;

        private SaveRecordingTask(SingActivity singActivity) {
            this.f20023b = singActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m21500a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m21501a((Boolean) obj);
        }

        protected Boolean m21500a(Void... voidArr) {
            Log.a(AudioInterface.f20656a, "getting tons of data from finalizePerformance");
            try {
                this.f20023b.bk = this.f20023b.aC.getMaxVULevel_amp();
                this.f20023b.aA = this.f20023b.aC.getSongPosition_seconds();
                this.f20023b.aB = this.f20023b.aC.getBackgroundDuration_seconds();
                this.f20023b.aD.voicedRMS = Float.valueOf(this.f20023b.aC.getVoicedRMS());
                this.f20023b.aD.minRMS = Float.valueOf(this.f20023b.aC.getMinRMS());
                this.f20023b.aD.maxRMS = Float.valueOf(this.f20023b.aC.getMaxRMS());
                this.f20023b.aD.sibilanceFrequencyHz = Float.valueOf(this.f20023b.aC.getSibilanceFreq_Hz());
                this.f20023b.aD.noiseProfile = this.f20023b.aC.getEncodedNoiseProfile();
                this.f20023b.aD.maxNoiseRMS = Float.valueOf(this.f20023b.aC.getMaxNoiseRMS());
                this.f20023b.aD.usePreGain = Boolean.valueOf(SingServerValues.m21688i());
                this.f20023b.aD.audioPowerEvents = this.f20023b.aC.getAudioPowerEvents();
                String metadata = this.f20023b.aD.toString();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(new File(this.f20023b.aP + ".json")));
                outputStreamWriter.write(metadata);
                outputStreamWriter.close();
                this.f20023b.aC.finalizePerformanceNative();
                return Boolean.TRUE;
            } catch (IOException e) {
                Log.e(SingActivity.aI, "Metadata write failed: " + e.toString());
                return Boolean.FALSE;
            } catch (Throwable e2) {
                Log.d(AudioInterface.f20656a, "Failed to get data from performance because of an exception in native code", e2);
                return Boolean.FALSE;
            }
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.f20022a = new BusyScreenDialog(this.f20023b, "");
        }

        protected void m21501a(Boolean bool) {
            if (this.f20022a.isShowing()) {
                this.f20022a.dismiss();
            }
            if (!this.f20023b.h() && bool.booleanValue()) {
                this.f20023b.mo6629T();
            }
        }
    }

    protected class SingCountdown implements Runnable {
        final /* synthetic */ SingActivity f20024a;
        private final Handler f20025b;
        private final ObjectAnimator f20026c;
        private boolean f20027d;
        private int f20028e;
        private boolean f20029f;

        public SingCountdown(SingActivity singActivity, boolean z) {
            this.f20024a = singActivity;
            this.f20026c = ObjectAnimator.ofFloat(singActivity.f20034A, "alpha", new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f});
            this.f20026c.setDuration(1000);
            this.f20027d = z;
            this.f20025b = new Handler(singActivity.getMainLooper());
            singActivity.i();
        }

        public void m21502a() {
            this.f20029f = true;
            this.f20024a.aq = false;
            this.f20028e = 3;
            mo6623b();
            this.f20025b.post(this);
        }

        public void mo6623b() {
            FrameLayout frameLayout;
            this.f20024a.f20079z.setVisibility(0);
            this.f20024a.f20034A.setAlpha(0.0f);
            this.f20024a.f20034A.setVisibility(0);
            this.f20024a.f20045L.setLyrics(this.f20024a.am);
            this.f20024a.f20036C.m23372c();
            if (this.f20024a.bm) {
                frameLayout = this.f20024a.f20054U;
            } else {
                frameLayout = this.f20024a.f20055V;
            }
            this.f20024a.aa.m23468a(this.f20024a.f20049P.getX() + ((float) (this.f20024a.f20049P.getWidth() / 2)), this.f20024a.f20049P.getY() + ((float) (this.f20024a.f20049P.getHeight() / 2)));
            this.f20024a.ab.m23468a((float) (frameLayout.getLeft() + (frameLayout.getWidth() / 2)), (float) frameLayout.getBottom());
        }

        public void m21504c() {
            this.f20029f = false;
        }

        public void run() {
            if (this.f20029f && !this.f20024a.isFinishing()) {
                if (this.f20028e > 0) {
                    this.f20024a.f20034A.setText(Integer.toString(this.f20028e));
                    this.f20025b.postDelayed(this, 1000);
                    this.f20026c.start();
                    this.f20028e--;
                    return;
                }
                this.f20024a.f20079z.setVisibility(8);
                this.f20024a.aw = null;
                this.f20024a.m21573d(this.f20027d);
            }
        }
    }

    protected class UiAudioLoop extends Thread {
        protected volatile boolean f20030a = true;
        protected volatile boolean f20031b;
        protected long f20032c;
        final /* synthetic */ SingActivity f20033d;

        protected UiAudioLoop(SingActivity singActivity) {
            this.f20033d = singActivity;
        }

        public void run() {
            super.run();
            if (!(this.f20033d.ab == null || this.f20033d.aa == null)) {
                this.f20033d.ab.setDrawStar(true);
                this.f20033d.aa.setDrawStar(true);
            }
            while (!this.f20031b) {
                this.f20032c = System.currentTimeMillis() + 33;
                if (!(this.f20030a || this.f20033d.aG || this.f20033d.aC == null)) {
                    try {
                        double songPosition_seconds = (double) this.f20033d.aC.getSongPosition_seconds();
                        float backgroundDuration_seconds = this.f20033d.aC.getBackgroundDuration_seconds();
                        boolean endOfPerformanceReached = this.f20033d.aC.endOfPerformanceReached();
                        float vULevel_amp = this.f20033d.aC.getVULevel_amp();
                        float maxVULevel_amp = this.f20033d.aC.getMaxVULevel_amp();
                        if (this.f20033d.f20036C.getVisibility() == 0) {
                            this.f20033d.f20036C.m23369a(songPosition_seconds, this.f20033d.aC.getDetectedPitch_MIDI(), Math.min(Math.max(this.f20033d.aC.getVULevel_amp(), 0.0f), DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        }
                        if (this.f20033d.aa != null && this.f20033d.aa.getVisibility() == 0) {
                            this.f20033d.aa.m23467a(this.f20033d.f20037D.getVolumeFraction());
                        }
                        if (this.f20033d.ab != null && this.f20033d.ab.getVisibility() == 0) {
                            this.f20033d.ab.m23467a(this.f20033d.f20037D.getVolumeFraction());
                        }
                        this.f20033d.mo6615a(songPosition_seconds, (double) backgroundDuration_seconds, endOfPerformanceReached, vULevel_amp, maxVULevel_amp);
                    } catch (Throwable e) {
                        Log.d(SingActivity.aI, "Failed to render audio UI loop because of an exception in native code. Did the performance engine get deleted?", e);
                    }
                }
                long currentTimeMillis = this.f20032c - System.currentTimeMillis();
                if (currentTimeMillis > 5) {
                    try {
                        Thread.sleep(currentTimeMillis);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            Log.b(UiAudioLoop.class.getSimpleName(), "audio UI loop done.");
        }

        public void mo6624a() {
            if (this.f20031b) {
                throw new RuntimeException("UiAudioLoop.pause should not be called after quit");
            }
            this.f20030a = true;
            this.f20033d.ab.setDrawStar(false);
            this.f20033d.aa.setDrawStar(false);
            this.f20033d.ab.m23466a();
            this.f20033d.aa.m23466a();
            this.f20033d.f20036C.m23368a();
        }

        public void mo6625b() {
            if (this.f20031b) {
                throw new RuntimeException("UiAudioLoop.cont should not be called after quit");
            }
            this.f20030a = false;
            this.f20033d.ab.setDrawStar(true);
            this.f20033d.aa.setDrawStar(true);
        }

        public void m21507c() {
            mo6624a();
            this.f20031b = true;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ExoPlayerPlaybackWrapper.m26262o();
        this.aJ.m25803a((Context) this);
        this.aU = new HeadSetBroadCastReceiver(this);
        SingCoreBridge.makeCacheDirWriteable(this);
        this.bs = new SimpleBarrier(1, new C41151(this));
    }

    protected void mo6641d() {
        super.d();
        this.an = SingBundle.m21632a(getIntent());
        this.aL = this.an.f20140e;
        this.aM = this.an.f20148m;
        this.aK = this.an.f20139d;
        this.ao = this.an.f20141f;
        this.aQ = this.an.f20146k;
        this.aR = this.an.f20137b.m21631a();
        this.at = new Handler(Looper.getMainLooper());
        if (m21541M() || m21539K() || this.an.m21652c()) {
            boolean z = (this.an.m21643a() && this.an.f20142g != 2) || ((this.an.m21648b() && !this.an.m21652c()) || !(this.an.m21643a() || this.an.m21648b()));
            this.bm = z;
        }
        this.f20051R.setOnClickListener(new C41212(this));
        this.av = true;
        if (m21541M()) {
            this.f20074u.setVisibility(0);
        } else {
            this.f20072s.setBackgroundColor(getResources().getColor(17170445));
            this.f20073t.setBackgroundColor(getResources().getColor(17170445));
            this.f20074u.setVisibility(8);
        }
        ab();
        if (DeviceSettings.n() != MonitoringMode.NONE) {
            this.ag.setOnSeekBarChangeListener(new C41223(this));
            OnClickListener c41234 = new C41234(this);
            this.ac.setOnClickListener(c41234);
            this.ae.setOnClickListener(c41234);
        }
        m21532D();
        try {
            AudioInterface.destroyPerformance();
        } catch (Throwable e) {
            Log.d(aI, "Exception when forcing destroyPerformance", e);
        }
        if (m21546R()) {
            mo6648q();
            al();
            getWindow().addFlags(128);
            new AudioSystemSetupTask(this, new C41265(this), new C41276(this)).execute(new Context[]{this});
            return;
        }
        mo6614W();
    }

    protected void mo6648q() {
        String str;
        int i = 0;
        int b = this.an.m21645b("BACKGROUND_RESOURCE_KEY", (int) C1947R.drawable.bg_sing_gradient_teal_purple);
        this.f20067n.setBackground(getResources().getDrawable(b));
        this.f20079z.setBackground(getResources().getDrawable(b));
        if (m21539K() || !this.an.m21643a() || this.an.f20142g == 0) {
            this.f20039F.setVisibility(8);
        } else {
            this.f20039F.setVisibility(0);
        }
        this.f20079z.setVisibility(0);
        if (!this.f20036C.m23371b()) {
            this.f20047N.setVisibility(8);
            this.f20048O.setVisibility(8);
            if (m21542N() || m21539K()) {
                this.f20035B.setVisibility(0);
                this.aj.setVisibility(4);
                m21513a(UserManager.a().h(), this.f20049P);
                this.f20043J.setVisibility(8);
                this.f20044K.setVisibility(8);
                this.ak.setText(getResources().getString(C1947R.string.sing_pause_overlay_no_lyric_title));
            } else {
                this.f20053T.setVisibility(0);
                if (this.an.m21643a() || this.an.m21652c()) {
                    str = this.ao != null ? this.ao.accountIcon.picUrl : null;
                    if (this.bm) {
                        m21513a(UserManager.a().h(), this.f20056W);
                        m21513a(str, this.f20057X);
                    } else {
                        m21513a(str, this.f20056W);
                        m21513a(UserManager.a().h(), this.f20057X);
                    }
                    this.ab.setVisibility(0);
                } else {
                    m21513a(UserManager.a().h(), this.f20056W);
                    if (!this.an.m21648b()) {
                        this.f20055V.setVisibility(8);
                    }
                    this.ab.setVisibility(0);
                }
            }
        }
        if (!(m21539K() || m21542N())) {
            if (this.an.m21643a()) {
                this.f20045L.setSingPart(this.an.f20142g == 1 ? 1 : 2);
            } else if (this.an.m21648b()) {
                this.f20045L.setSingPart(3);
            } else {
                this.f20045L.setSingPart(0);
            }
        }
        VisualizerView visualizerView = this.f20038E;
        if (this.f20036C.m23371b()) {
            b = 8;
        } else {
            b = 0;
        }
        visualizerView.setVisibility(b);
        PitchView pitchView = this.f20036C;
        if (this.f20038E.getVisibility() == 0) {
            b = 8;
        } else {
            b = 0;
        }
        pitchView.setVisibility(b);
        if (!m21539K() && (this.an.m21643a() || this.an.m21648b())) {
            if (this.an.m21643a() || this.an.m21648b()) {
                if (!m21541M() || this.f20036C.m23371b()) {
                    this.bn = ap();
                    this.bo = aq();
                    this.bp = ar();
                    this.bq = as();
                    this.br = at();
                } else {
                    this.aW = AnimationUtils.loadAnimation(this, C1947R.anim.grow);
                    this.aV = AnimationUtils.loadAnimation(this, C1947R.anim.shrink);
                    this.aX = AnimationUtils.loadAnimation(this, C1947R.anim.spot);
                    this.aY = AnimationUtils.loadAnimation(this, C1947R.anim.fade);
                    this.ba = AnimationUtils.loadAnimation(this, C1947R.anim.grow);
                    this.aZ = AnimationUtils.loadAnimation(this, C1947R.anim.shrink);
                    this.bb = AnimationUtils.loadAnimation(this, C1947R.anim.spot);
                    this.bc = AnimationUtils.loadAnimation(this, C1947R.anim.fade);
                }
                this.bd = true;
                this.be = true;
            }
            if (!m21541M() || this.f20036C.m23371b()) {
                str = this.ao != null ? this.ao.accountIcon.picUrl : null;
                if ((this.an.m21643a() && this.an.f20142g != 2) || this.an.m21648b()) {
                    i = 1;
                }
                if (i != 0) {
                    m21513a(UserManager.a().h(), this.f20047N);
                    m21513a(str, this.f20048O);
                    return;
                }
                m21513a(str, this.f20047N);
                m21513a(UserManager.a().h(), this.f20048O);
            }
        } else if (this.f20036C.m23371b()) {
            m21513a(UserManager.a().h(), this.f20047N);
            this.f20048O.setVisibility(8);
        }
    }

    protected void onStart() {
        super.onStart();
        if (ChatUtils.a()) {
            SingApplication.j().b(true);
        }
    }

    protected UiAudioLoop mo6649r() {
        return new UiAudioLoop(this);
    }

    protected synchronized void m21580s() {
        m21581t();
        Log.a(aI, "Audio UI Loop being allocated");
        this.aE = mo6649r();
    }

    protected synchronized void m21581t() {
        if (this.aE != null) {
            Log.a(aI, "Audio UI Loop quitting");
            this.aE.m21507c();
            this.aE = null;
        }
    }

    protected void m21582u() throws Exception {
        this.bj = false;
    }

    protected void m21583v() throws Exception {
        this.aC.m22269a(this.aP);
        this.aC.start();
        if (!(this.aw == null || mo6627Q())) {
            this.aw.m21502a();
        }
        registerReceiver(this.aU, new IntentFilter("android.intent.action.HEADSET_PLUG"));
    }

    protected void onResume() {
        super.onResume();
        if (this.aH != null) {
            Log.d(aI, "recording error dialog showing");
            return;
        }
        try {
            if (this.bj) {
                m21582u();
            } else {
                m21583v();
            }
        } catch (Throwable e) {
            String str = "Failed to configure or start audio system in onResume because: " + e.getMessage();
            Log.d(AudioInterface.f20656a, str, e);
            mo6639c(str);
        }
        if (!this.bl) {
            m21580s();
        }
        i();
        this.bs.m19034a();
        if (this.f20066m) {
            this.f20066m = false;
            ad();
        }
    }

    protected void onPause() {
        super.onPause();
        Thread thread = this.aE;
        try {
            unregisterReceiver(this.aU);
        } catch (IllegalArgumentException e) {
            Log.d(aI, "Couldn't unregister the headset broadcast receiver, probably due to a race condition. Ignoring and moving on...");
        }
        if (this.aw != null) {
            this.aw.m21504c();
        }
        j();
        if (this.ax == null) {
            this.bs.m19037d();
            if (this.as == null || !this.as.isShowing()) {
                mo6644k();
            } else {
                this.f20066m = true;
            }
            m21581t();
            if (this.aC != null) {
                Log.a(AudioInterface.f20656a, "calling pause from onPause");
                mo6640c(true);
                Log.a(AudioInterface.f20656a, "calling stopAndShutdown from onPause");
                if (thread != null) {
                    try {
                        thread.join(200);
                    } catch (Throwable e2) {
                        Log.d(aI, "Failed to join mUiAudioLoop", e2);
                    }
                }
                Log.a(aI, "Audio UI loop joined");
                try {
                    this.aC.stopAndShutdown();
                } catch (Throwable e22) {
                    Log.d(aI, "Failed to stop audio system because of an exception in native code", e22);
                }
            }
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.ax != null && this.ax.isShowing()) {
            this.ax.dismiss();
        }
        this.ax = null;
        if (this.ay != null && this.ay.isShowing()) {
            this.ay.dismiss();
        }
        this.ay = null;
        if (this.as != null && this.as.isShowing()) {
            this.as.dismiss();
        }
        this.as = null;
        if (this.ag != null) {
            MagicPreferences.m20313b((Context) this, m21533E());
        }
        if (ChatUtils.a()) {
            SingApplication.j().b(false);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f20079z != null) {
            this.f20079z.setBackgroundResource(0);
        }
        this.aJ.m25802a();
        this.am = null;
    }

    protected void m21563a(boolean z) {
        Log.b(aI, "audioFocusGain called; fromCanDuckState = " + z);
        if (!z) {
            if (this.bl) {
                m21581t();
                return;
            }
            m21580s();
            this.aE.start();
        }
    }

    protected void mo6644k() {
        m21535G();
    }

    @UiThread
    protected void mo6619w() {
        if (!isFinishing()) {
            startActivity(new Intent(this, TrialSubscriptionActivity_.class));
            finish();
        }
    }

    @UiThread
    void mo6620x() {
        if (!isFinishing()) {
            if (this.ax == null || !this.ax.isShowing()) {
                if (this.aA > 0.0f) {
                    this.an.m21640a("RECORDING_FILE_EXTRA_KEY", this.aP);
                    this.an.m21641a("USED_HEADPHONE", !this.f20065l);
                    this.an.m21641a("HEADPHONE_HAD_MIC", this.f20061h);
                    PostSingFlowActivity.m20878a((Activity) this, this.an, an());
                }
                finish();
            }
        }
    }

    @UiThread
    public void mo6621y() {
        if (f()) {
            this.bs.m19036c();
            if (this.ax != null) {
                this.ax.m23722a(this.aK, this.ao);
            }
        }
    }

    public boolean m21587z() {
        return this.f20040G.getVisibility() == 0;
    }

    public boolean m21529A() {
        return this.f20041H.getVisibility() == 0;
    }

    @Click
    protected void m21530B() {
        mo6626C();
    }

    protected void mo6626C() {
        if (!this.av && this.aw == null) {
            if (this.ah.getVisibility() == 0 || this.aC.isPlaying()) {
                this.av = true;
                if (this.ah.getVisibility() != 0) {
                    mo6633a(0);
                    mo6640c(true);
                } else {
                    mo6633a(8);
                    mo6640c(false);
                }
                this.av = false;
                return;
            }
            Log.b(aI, "skipping pause");
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 24 || i == 25) {
            m21532D();
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void m21532D() {
        if (this.aC != null) {
            Log.a(AudioInterface.f20656a, "calling updateMasterVolumeFromHardware");
            AudioManager audioManager = (AudioManager) getSystemService("audio");
            float streamMaxVolume = (float) audioManager.getStreamMaxVolume(3);
            int streamVolume = audioManager.getStreamVolume(3);
            if (streamVolume <= 1) {
                this.aC.m22268a(0.0f);
            } else if (streamMaxVolume > 0.0f) {
                this.aC.m22268a(MathUtils.m25871b(((((float) streamVolume) / streamMaxVolume) * 48.0f) - 0.10546875f));
            }
        }
    }

    public float m21533E() {
        return ((float) this.ag.getProgress()) / ((float) this.ag.getMax());
    }

    public void onBackPressed() {
        m21534F();
    }

    public void m21534F() {
        if (this.az != null) {
            this.az.dismiss();
            this.az = null;
        } else if (m21529A()) {
            m21538J();
        } else if (m21587z()) {
            m21536H();
        } else {
            m21535G();
        }
    }

    public void mo6640c(boolean z) {
        if (this.aE != null) {
            if (z) {
                this.aE.mo6624a();
            } else {
                this.aE.mo6625b();
            }
        }
        Log.a(AudioInterface.f20656a, "calling pause from setPauseMedia: " + Boolean.valueOf(z).toString());
        if (this.aC != null) {
            this.aC.setPause(z);
        }
    }

    public void m21535G() {
        if (!h() && !m21587z() && !m21529A() && !this.aq && this.aw == null && !this.av) {
            if (!SingApplication.g.booleanValue() || this.aC == null || this.aC.getSongPosition_seconds() >= 2.0f) {
                this.av = true;
                this.bf = true;
                mo6640c(true);
                mo6617b(new C41287(this));
            }
        }
    }

    @UiThread
    public void mo6617b(final Runnable runnable) {
        if (!h()) {
            this.f20070q.setClickable(false);
            this.f20069p.setClickable(false);
            this.f20071r.setClickable(false);
            this.f20073t.setClickable(false);
            this.ar = false;
            this.f20070q.setVisibility(this.f20079z.getVisibility() == 8 ? 0 : 8);
            if (SingApplication.g.booleanValue() && new Random().nextInt(Integer.MAX_VALUE) % 2 == 0) {
                this.f20070q.setClickable(false);
                this.f20071r.setClickable(false);
            }
            if (this.f20064k) {
                this.f20073t.setVisibility(0);
                this.f20072s.setVisibility(8);
            } else {
                this.f20073t.setVisibility(8);
                this.f20072s.setVisibility(0);
            }
            this.f20040G.setAlpha(0.0f);
            this.f20040G.setVisibility(0);
            ViewPropertyAnimator animate = this.f20040G.animate();
            if (animate != null) {
                animate.alpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).setDuration(500).setListener(new AnimatorListenerAdapter(this) {
                    final /* synthetic */ SingActivity f20014b;

                    public void onAnimationEnd(Animator animator) {
                        if (runnable != null) {
                            this.f20014b.runOnUiThread(runnable);
                        }
                    }
                });
            }
        }
    }

    public void m21536H() {
        m21568c(new C41309(this));
    }

    public void m21568c(@NonNull final Runnable runnable) {
        if (!h() && m21587z() && !this.av) {
            this.av = true;
            this.f20070q.setClickable(false);
            this.f20069p.setClickable(false);
            this.f20071r.setClickable(false);
            this.f20073t.setClickable(false);
            this.ar = true;
            i();
            this.f20040G.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            this.f20040G.setVisibility(0);
            ViewPropertyAnimator animate = this.f20040G.animate();
            if (animate != null) {
                animate.alpha(0.0f).setDuration(100).setListener(new AnimatorListenerAdapter(this) {
                    final /* synthetic */ SingActivity f19971b;

                    public void onAnimationEnd(Animator animator) {
                        if (!this.f19971b.h()) {
                            this.f19971b.f20040G.setVisibility(8);
                            if (this.f19971b.f() && this.f19971b.ah.getVisibility() != 0) {
                                runnable.run();
                            }
                            this.f19971b.av = false;
                            this.f19971b.bf = false;
                        }
                    }
                });
            }
        }
    }

    public void m21537I() {
        mo6640c(true);
        this.f20041H.setAlpha(0.0f);
        this.f20041H.setVisibility(0);
        this.f20041H.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SingActivity f19972a;

            {
                this.f19972a = r1;
            }

            public void onClick(View view) {
                this.f19972a.m21538J();
            }
        });
        this.f20078y.setVisibility(this.f20036C.m23371b() ? 0 : 8);
        this.f20075v.setTag(Integer.valueOf(0));
        this.f20076w.setTag(Integer.valueOf(1));
        this.f20077x.setTag(Integer.valueOf(2));
        this.f20078y.setTag(Integer.valueOf(3));
        CustomTypefaceSpan customTypefaceSpan = new CustomTypefaceSpan(this, this.f20076w.getTextSize(), C1947R.color.menu_item, TypefaceUtils.m26188a());
        CustomTypefaceSpan customTypefaceSpan2 = new CustomTypefaceSpan(this, this.f20076w.getTextSize() * 0.8f, C1947R.color.contextual_text, TypefaceUtils.m26188a());
        StyleReplacer styleReplacer = new StyleReplacer(getString(C1947R.string.sing_report_inappropriate_content), this.f20076w, customTypefaceSpan);
        styleReplacer.m26180a("\n", customTypefaceSpan2);
        styleReplacer.m26174a();
        styleReplacer = new StyleReplacer(getString(C1947R.string.sing_report_mislabeled_content), this.f20077x, customTypefaceSpan);
        styleReplacer.m26180a("\n", customTypefaceSpan2);
        styleReplacer.m26174a();
        OnClickListener anonymousClass12 = new OnClickListener(this) {
            final /* synthetic */ SingActivity f19977a;

            class C41101 implements Runnable {
                final /* synthetic */ AnonymousClass12 f19974a;

                class C41091 implements Runnable {
                    final /* synthetic */ C41101 f19973a;

                    C41091(C41101 c41101) {
                        this.f19973a = c41101;
                    }

                    public void run() {
                        this.f19973a.f19974a.f19977a.mo6632a(this.f19973a.f19974a.f19977a.aC.getSongPosition_seconds());
                    }
                }

                C41101(AnonymousClass12 anonymousClass12) {
                    this.f19974a = anonymousClass12;
                }

                public void run() {
                    this.f19974a.f19977a.m21572d(new C41091(this));
                }
            }

            class C41112 implements Runnable {
                final /* synthetic */ AnonymousClass12 f19975a;

                C41112(AnonymousClass12 anonymousClass12) {
                    this.f19975a = anonymousClass12;
                }

                public void run() {
                    this.f19975a.f19977a.m21538J();
                }
            }

            class C41123 implements OnDismissListener {
                final /* synthetic */ AnonymousClass12 f19976a;

                C41123(AnonymousClass12 anonymousClass12) {
                    this.f19976a = anonymousClass12;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                    this.f19976a.f19977a.az = null;
                }
            }

            {
                this.f19977a = r1;
            }

            public void onClick(View view) {
                int i;
                Integer num = (Integer) view.getTag();
                int i2 = C1947R.string.sing_report_copyright_infringement;
                switch (num.intValue()) {
                    case 1:
                        i = C1947R.string.sing_report_inappropriate_content_msg;
                        i2 = C1947R.string.sing_report_inappropriate_content_title;
                        break;
                    case 2:
                        i = C1947R.string.sing_report_mislabeled_content_msg;
                        i2 = C1947R.string.sing_report_mislabeled_content_title;
                        break;
                    case 3:
                        i = C1947R.string.sing_report_incorrect_pitch_lines_msg;
                        i2 = C1947R.string.sing_report_incorrect_pitch_lines_title;
                        break;
                    default:
                        i = C1947R.string.songbook_report_song_copyright;
                        break;
                }
                this.f19977a.az = new TextAlertDialog(this.f19977a, this.f19977a.getString(i2), this.f19977a.getString(i), true, true);
                this.f19977a.az.setCancelable(true);
                this.f19977a.az.m19800a((int) C1947R.string.sing_new_song, (int) C1947R.string.sing_resume);
                this.f19977a.az.m19804a(new C41101(this));
                this.f19977a.az.m19809b(new C41112(this));
                this.f19977a.az.setOnDismissListener(new C41123(this));
                this.f19977a.az.show();
            }
        };
        this.f20075v.setOnClickListener(anonymousClass12);
        this.f20076w.setOnClickListener(anonymousClass12);
        this.f20077x.setOnClickListener(anonymousClass12);
        this.f20078y.setOnClickListener(anonymousClass12);
        ViewPropertyAnimator animate = this.f20041H.animate();
        if (animate != null) {
            animate.alpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).setDuration(500).setListener(new AnimatorListenerAdapter(this) {
                final /* synthetic */ SingActivity f19978a;

                {
                    this.f19978a = r1;
                }

                public void onAnimationEnd(Animator animator) {
                }
            });
        }
    }

    public void m21538J() {
        m21572d(new Runnable(this) {
            final /* synthetic */ SingActivity f19979a;

            {
                this.f19979a = r1;
            }

            public void run() {
                if (!this.f19979a.isFinishing() && this.f19979a.f() && this.f19979a.ah.getVisibility() != 0) {
                    if (this.f19979a.aw != null) {
                        this.f19979a.mo6643g(this.f19979a.bg);
                    } else {
                        this.f19979a.mo6640c(false);
                    }
                }
            }
        });
    }

    public void m21572d(final Runnable runnable) {
        this.f20041H.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        this.f20041H.setVisibility(0);
        ViewPropertyAnimator animate = this.f20041H.animate();
        if (animate != null) {
            animate.alpha(0.0f).setDuration(100).setListener(new AnimatorListenerAdapter(this) {
                final /* synthetic */ SingActivity f19981b;

                public void onAnimationEnd(Animator animator) {
                    this.f19981b.f20041H.setVisibility(8);
                    if (runnable != null) {
                        this.f19981b.runOnUiThread(runnable);
                    }
                }
            });
        }
    }

    private void m21514a(boolean z, boolean z2) {
        int i;
        boolean z3 = true;
        int i2 = 0;
        this.f20060g = z;
        boolean z4 = z && z2;
        this.f20061h = z4;
        if (this.ar && !z) {
            Log.b(aI, "setHeadphonesPluggedIn - setting mHeadphonesEverUnplugged to true");
            this.f20065l = true;
        }
        if (this.f20060g) {
            z4 = false;
        } else {
            z4 = true;
        }
        TextView textView = this.f20050Q;
        if (z4) {
            i = 0;
        } else {
            i = 8;
        }
        textView.setVisibility(i);
        if (this.an.m21648b()) {
            this.f20050Q.setText(getString(C1947R.string.sing_headphones_required));
        }
        if (!this.f20060g || DeviceSettings.n() == MonitoringMode.NONE) {
            z3 = false;
        }
        View view = this.ad;
        if (!z3) {
            i2 = 8;
        }
        view.setVisibility(i2);
        Log.a(AudioInterface.f20656a, "calling setMonitoring from setHeadphonesPluggedIn");
        if (this.aC != null) {
            this.aC.setMonitoring(z3);
        }
    }

    protected void mo6633a(int i) {
        this.ai.setVisibility(i);
        this.ah.setVisibility(i);
    }

    private void ab() {
        this.f20069p.setOnClickListener(this.bt);
        this.f20070q.setOnClickListener(this.bt);
        this.f20071r.setOnClickListener(this.bt);
        this.f20073t.setOnClickListener(this.bt);
        this.f20074u.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SingActivity f19985a;

            class C41141 implements Runnable {
                final /* synthetic */ AnonymousClass17 f19984a;

                C41141(AnonymousClass17 anonymousClass17) {
                    this.f19984a = anonymousClass17;
                }

                public void run() {
                    this.f19984a.f19985a.m21537I();
                }
            }

            {
                this.f19985a = r1;
            }

            public void onClick(View view) {
                this.f19985a.m21568c(new C41141(this));
            }
        });
        this.f20040G.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SingActivity f19986a;

            {
                this.f19986a = r1;
            }

            public void onClick(View view) {
                this.f19986a.m21536H();
            }
        });
    }

    private void ac() {
        Log.b(aI, "mHeadphonesPluggedIn = " + this.f20060g + "; mHeadphonesHaveMic = " + this.f20061h + "; mHeadphonesEverUnplugged = " + this.f20065l + "; mShouldBeTrackingHeadphoneStatus = " + this.ar);
    }

    private void ad() {
        if (this.an.m21648b()) {
            this.as = new TextAlertDialog((Context) this, getString(C1947R.string.sing_headphones_required), getString(C1947R.string.sing_headphones_required_msg), true, false);
            this.as.m19806a(getString(C1947R.string.core_ok), "");
        } else {
            this.as = new TextAlertDialog((Context) this, getString(C1947R.string.sing_headphones), getString(C1947R.string.sing_headphones_msg), true, false);
            this.as.m19806a(getString(C1947R.string.core_ok), "");
        }
        this.as.m19799a((int) C1947R.layout.soft_permission_request_header);
        ((ImageView) this.as.findViewById(C1947R.id.header_image)).setImageResource(C1947R.drawable.icn_headphone_permission);
        this.as.m19807a(true);
        this.as.m19803a(new CustomAlertDialogListener(this) {
            final /* synthetic */ SingActivity f19987a;

            {
                this.f19987a = r1;
            }

            public void mo6385a(CustomAlertDialog customAlertDialog) {
                if (this.f19987a.as != null) {
                    this.f19987a.as.dismiss();
                }
                SingAnalytics.m26067a(HeadphonesType.m17502a(this.f19987a.f20060g, this.f19987a.f20061h), this.f19987a.an.f20149n, PageType.MODAL);
                this.f19987a.m21575f(false);
            }

            public void mo6386b(CustomAlertDialog customAlertDialog) {
                mo6385a(customAlertDialog);
            }
        });
        SingAnalytics.m26122a(SongbookEntryUtils.m26167b(this.aK), this.aQ, this.aR, aj(), this.an.f20149n, PageType.MODAL);
        this.as.show();
    }

    public boolean m21539K() {
        return this.aK != null && PerformanceV2Util.m25941a(this.aK.mo6289c());
    }

    public void m21540L() {
        Log.b(aI, "reportStream - begin");
        if (!this.aM) {
            Log.b(aI, "SingIntent specified that this stream should not be reported");
            this.aT.set(true);
        } else if (this.aK == null) {
            Log.e(aI, "Tried to report stream but mEntry is null, so we won't be able to attain useful info");
        } else {
            Log.b(aI, "SingIntent specified we should report this stream");
            if (this.aS.weakCompareAndSet(false, true) && !this.aT.get()) {
                String str;
                String str2;
                boolean z = this.aK.m18769o() || this.an.f20146k;
                EntitlementsManager.m18163a().m18184c(this.aK.mo6289c());
                StoreAPI$ProductType storeAPI$ProductType = this.aK.m18773s() ? StoreAPI$ProductType.SONG : StoreAPI$ProductType.ARR;
                String c = this.aK.mo6289c();
                if (!this.aK.m18772r() || ((ArrangementVersionLiteEntry) this.aK).f17623a.songId == null) {
                    str = null;
                } else {
                    str = ((ArrangementVersionLiteEntry) this.aK).f17623a.songId;
                }
                if (this.an.f20146k) {
                    str2 = this.an.f20145j;
                } else {
                    str2 = null;
                }
                if (this.an.f20146k && this.an.f20145j == null) {
                    Log.e(aI, "Tried to report stream but openCallKey is null for a join, so we are aborting");
                    return;
                }
                Log.b(aI, "Logging stream - song uid: " + this.aK.mo6289c() + "; is a join: " + this.an.f20146k + "; is free: " + z + "; stream cost: " + this.aL);
                StoreAPI$StreamType storeAPI$StreamType = SubscriptionManager.a().b() ? StoreAPI$StreamType.SUBSCRIPTION : StoreAPI$StreamType.OWNED;
                Log.b(aI, "reportStream - begin call to reportStream");
                StoreManager.m18378a().m18422a(c, str, SingApplication.i(), Integer.valueOf(0), storeAPI$StreamType, storeAPI$ProductType, new NetworkResponseCallback(this) {
                    final /* synthetic */ SingActivity f19989a;

                    {
                        this.f19989a = r1;
                    }

                    public void handleResponse(NetworkResponse networkResponse) {
                        Log.b(SingActivity.aI, "reportStream - completion block called for reportStream");
                        this.f19989a.aS.set(false);
                        this.f19989a.aT.set(true);
                        RecommendationManager.m18285a().m18295a(CacheDuration.SHORT, null);
                    }
                }, str2);
            } else if (this.aT.get()) {
                Log.d(aI, "reportStream - stream already reported");
            } else {
                Log.d(aI, "reportStream - stream reporting in progress");
            }
            Log.b(aI, "reportStream - end");
        }
    }

    private SongLyrics ae() {
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        SongLyrics songLyrics = new SongLyrics(TypefaceUtils.m26194c(), getResources().getDimension(C1947R.dimen.sing_lyric_size), point.x, 0.0f);
        if (m21539K() || m21542N()) {
            return songLyrics;
        }
        ArrayList lyrics = SingCoreBridge.getLyrics(this.an.f20142g);
        songLyrics.m24746a(Version.m24739a(SingCoreBridge.getLyricVersion()));
        if (lyrics != null) {
            Iterator it = lyrics.iterator();
            while (it.hasNext()) {
                songLyrics.m24747a((Lyric) it.next());
            }
        }
        songLyrics.m24745a();
        return songLyrics;
    }

    protected boolean m21541M() {
        return (this.ao != null && this.ao.r()) || (this.aK != null && this.aK.m18772r());
    }

    protected boolean m21542N() {
        return (this.aK == null || !this.aK.m18772r() || this.aK.mo6296j()) ? false : true;
    }

    private boolean af() {
        ArrangementVersion O = m21543O();
        return O != null && O.multipart;
    }

    public ArrangementVersion m21543O() {
        if (m21541M()) {
            return this.ao != null ? this.ao.arrangementVersion : ((ArrangementVersionLiteEntry) this.aK).f17623a.arrangementVersion;
        } else {
            return null;
        }
    }

    protected Boolean m21544P() {
        return (this.an == null || !this.an.f20146k || this.ao == null) ? null : Boolean.valueOf(this.ao.video);
    }

    protected boolean mo6627Q() {
        return false;
    }

    protected void m21573d(boolean z) {
        if (f()) {
            int intValue;
            Integer num;
            if (!z) {
                Builder builder = new Builder(this.an);
                builder.m21611a((int) Math.round(((double) System.currentTimeMillis()) / 1000.0d)).m21610a(-1.0f);
                this.an = builder.m21621a();
                this.ar = true;
                if (!this.f20060g) {
                    this.f20065l = true;
                }
                SingApplication g = SingApplication.g();
                g.j++;
                if (this.an.f20146k) {
                    PerformanceManager.a().b(this.an.f20145j, null);
                }
                if (this.an.f20149n) {
                    Analytics.m17857a(ai(), HeadphonesType.m17502a(this.f20060g, this.f20061h), SongbookEntryUtils.m26167b(this.aK), aj(), CameraStatusType.CAMERA_OFF);
                }
            }
            this.f20064k = false;
            SingAnalytics.m26104a(ai(), this.an.f20149n ? UserPath.ONBOARDING : UserPath.OTHER, HeadphonesType.m17502a(this.f20060g, this.f20061h), SongbookEntryUtils.m26167b(this.aK), this.aR, this.an.f20142g, aj(), m21544P(), mo6627Q());
            AdUtils.m22220a((Context) this);
            if (this.aK.m18772r()) {
                ArrangementManager.a().e(this.aK.mo6289c());
            } else {
                SongManager.m18356a().m18357a(this.aK.mo6289c());
            }
            mo6642e(z);
            this.bi = (int) (100.0d * ((double) AudioDefs.m17508a((Activity) this)));
            this.bh = HeadphonesType.m17502a(this.f20060g, this.f20061h);
            if (this.aC == null) {
                intValue = MagicPreferences.m20297a().intValue();
                num = null;
            } else {
                intValue = MagicPreferences.m20309b(this.bh, this.aC.m22267a(), (int) this.aC.m22272b());
                num = MagicPreferences.m20298a(this.bh, this.aC.m22267a(), (int) this.aC.m22272b());
            }
            this.f20036C.setAudioLatency(intValue);
            SingAnalytics.m26064a(this.an.f20154s, z, this.bi, this.bh, SongbookEntryUtils.m26167b(this.aK), null, Float.valueOf(m21533E()), num, intValue, DeviceSettings.n(), DeviceSettings.f());
            mo6640c(false);
        }
    }

    private void ag() {
        Animation animationSet = new AnimationSet(true);
        Animation scaleAnimation = new ScaleAnimation(1.6f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1.6f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0, 0.0f, 1, 0.5f);
        scaleAnimation.setDuration(0);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation(scaleAnimation);
        scaleAnimation = new TranslateAnimation(1, 1.27f, 1, 0.0f, 1, -0.56f, 1, 0.0f);
        scaleAnimation.setDuration(0);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation(scaleAnimation);
        scaleAnimation = new AlphaAnimation(0.6f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        scaleAnimation.setDuration(0);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setFillAfter(true);
        this.f20047N.bringToFront();
        this.f20047N.startAnimation(animationSet);
        animationSet = new AnimationSet(true);
        scaleAnimation = new ScaleAnimation(0.73f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.73f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0, 0.0f, 1, 0.5f);
        scaleAnimation.setDuration(0);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation(scaleAnimation);
        scaleAnimation = new TranslateAnimation(1, 1.27f, 1, 0.75f, 1, -0.56f, 1, 0.0f);
        scaleAnimation.setDuration(0);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation(scaleAnimation);
        scaleAnimation = new AlphaAnimation(0.6f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        scaleAnimation.setDuration(0);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setFillAfter(true);
        this.f20048O.startAnimation(animationSet);
        this.bd = true;
        this.be = true;
    }

    private void ah() {
        Animation animationSet = new AnimationSet(true);
        Animation scaleAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1.6f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1.6f, 0, 0.0f, 1, 0.5f);
        scaleAnimation.setDuration(0);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setFillAfter(true);
        this.f20047N.startAnimation(animationSet);
        this.bd = true;
        this.be = false;
    }

    protected void mo6642e(boolean z) {
        if (z) {
            this.f20045L.m23219a();
            this.f20045L.m23220a(0.0d);
            this.f20036C.m23369a(0.0d, 0.0f, 0.0f);
            this.aa.m23467a(0.0f);
            this.ab.m23467a(0.0f);
            this.f20037D.m23527a(0.0d);
        }
        if (!this.an.m21643a() && !this.an.m21648b()) {
            ah();
        } else if (m21541M()) {
            if (!this.an.m21643a() || af()) {
                if (this.an.m21648b()) {
                    ag();
                }
            } else if (this.an.f20142g != 2) {
                mo6616b(1);
            } else {
                mo6616b(2);
            }
        } else if (this.an.m21648b()) {
            ag();
        }
    }

    private String ai() {
        return this.ao != null ? this.ao.performanceKey : null;
    }

    private String aj() {
        if (this.aK == null || !this.aK.m18772r()) {
            return "-";
        }
        return this.aK.mo6289c();
    }

    private void ak() {
        int a = (int) (100.0d * ((double) AudioDefs.m17508a((Activity) this)));
        HeadphonesType a2 = HeadphonesType.m17502a(this.f20060g, this.f20061h);
        if (a != this.bi || a2 != this.bh) {
            OpenSLStreamVersion a3 = this.aC.m22267a();
            int b = (int) this.aC.m22272b();
            SingAnalytics.m26061a(this.an.f20154s, SingFlowPhase.RECORD, a, a2, null, null, null, null, MagicPreferences.m20309b(HeadphonesType.m17502a(this.f20060g, this.f20061h), a3, b), null);
        }
    }

    protected boolean m21546R() {
        Map k;
        String str;
        if (this.ao == null || this.ao.arrangementVersion == null) {
            k = this.aK.mo6297k();
        } else {
            k = this.ao.arrangementVersion.resourceFilePaths;
        }
        if (this.an.f20146k) {
            str = this.an.f20143h;
        } else {
            str = (String) k.get("background");
        }
        this.aN = str;
        if (this.aN == null || this.aN.isEmpty()) {
            Log.d(aI, "resourcePath was empty: " + this.aN);
            MagicCrittercism.a(new Exception("resourcePath was empty: " + this.aN));
            return false;
        }
        File file = new File(this.aN);
        if (!file.exists()) {
            MagicCrittercism.a(new IllegalStateException("Backing track was missing in SingActivity"));
            return false;
        } else if (!file.canRead()) {
            return false;
        } else {
            this.aO = (String) k.get("main");
            if (TextUtils.isEmpty(this.aO)) {
                Log.d(aI, "ResourceV2.ROLE_MAIN path empty");
                return false;
            } else if (!new File(this.aO).canRead() || !SingCoreBridge.setMidiFile(this.aO)) {
                return false;
            } else {
                List pitchEvents;
                if (AudioUtils.m22280a()) {
                    this.aF = ResourceUtils.m19028a() + "/" + UUID.randomUUID() + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss", Locale.US).format(new Date());
                } else {
                    this.aF = ResourceUtils.m19032b(this) + "/" + UUID.randomUUID();
                }
                this.aP = this.aF + ".wav";
                if (this.am == null) {
                    this.am = ae();
                }
                int i = this.an.m21643a() ? this.an.f20142g : 0;
                if (!m21541M() || SingServerValues.m21695p()) {
                    pitchEvents = SingCoreBridge.getPitchEvents(i);
                } else {
                    pitchEvents = new ArrayList();
                }
                if (!(m21539K() || m21542N())) {
                    boolean z;
                    boolean z2;
                    if (this.am.m24751c()) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (!pitchEvents.isEmpty() || m21541M()) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (!(z && z2)) {
                        MagicCrittercism.a(new IllegalStateException("Corrupt MIDI File: lyricsPass=" + z + " pitchesPass=" + z2));
                        return false;
                    }
                }
                this.f20045L.setLyrics(this.am);
                this.f20036C.m23370a(i, pitchEvents);
                Log.c(aI, "prepareSongResources - set MIDI file path to " + this.aO);
                Log.c(aI, "prepareSongResources - set background track to " + this.aN);
                Log.c(aI, "prepareSongResources - set recording file to " + this.aP);
                return true;
            }
        }
    }

    private void al() {
        if (this.aK != null && this.aK.mo6290d() != null) {
            this.aJ.m25806d(this.aK);
        }
    }

    private void am() {
        Log.c(aI, "App Indexing appIndexingStop: " + this.aK + " / " + this.aK.mo6290d());
        if (this.aK != null && this.aK.mo6290d() != null) {
            this.aJ.m25807e(this.aK);
        }
    }

    private void m21513a(String str, ImageView imageView) {
        ImageUtils.a(str, imageView, C1947R.drawable.icn_default_profile_large, true, getResources().getColor(C1947R.color.profile_border));
    }

    protected void mo6628S() {
        final Handler handler = new Handler();
        handler.post(new Runnable(this) {
            final /* synthetic */ SingActivity f19992b;

            class C41161 implements Runnable {
                final /* synthetic */ AnonymousClass21 f19990a;

                C41161(AnonymousClass21 anonymousClass21) {
                    this.f19990a = anonymousClass21;
                }

                public void run() {
                    if (!this.f19990a.f19992b.f()) {
                        Log.d(SingActivity.aI, "Activity not resumed.");
                    } else if (this.f19990a.f19992b.f20060g || this.f19990a.f19992b.an.f20149n) {
                        this.f19990a.f19992b.m21575f(false);
                    } else {
                        this.f19990a.f19992b.ad();
                    }
                }
            }

            public void run() {
                handler.postDelayed(new C41161(this), 300);
            }
        });
    }

    public void m21575f(boolean z) {
        if (this.as == null || !this.as.isShowing()) {
            mo6643g(z);
        }
    }

    public void mo6643g(boolean z) {
        if (this.aw != null) {
            this.aw.m21504c();
        }
        this.aw = new SingCountdown(this, z);
        this.aw.m21502a();
        this.av = false;
    }

    public void mo6629T() {
        m21560a(this.an.m21660i());
    }

    protected void m21560a(SingBundle singBundle) {
        boolean z = true;
        Log.b(aI, "launchReview - begin");
        this.aq = true;
        singBundle.m21640a("RECORDING_FILE_EXTRA_KEY", this.aP);
        singBundle.m21640a("BACKGROUND_FILE_EXTRA_KEY", this.aN);
        singBundle.m21640a("MIDI_FILE_EXTRA_KEY", this.aO);
        singBundle.m21639a("SCORE_EXTRA_KEY", this.f20036C.getScore());
        String str = "USED_HEADPHONE";
        if (this.f20065l) {
            z = false;
        }
        singBundle.m21641a(str, z);
        singBundle.m21641a("HEADPHONE_HAD_MIC", this.f20061h);
        singBundle.m21638a("MAX_RMS_LEVEL", this.bk);
        singBundle.m21641a("MIDI_HAS_CHORDS_TRACK", SingCoreBridge.midiHasChordsTrack());
        Log.a(AudioInterface.f20656a, "calling getRunningSampleRate from launchReview");
        singBundle.m21638a("SAMPLE_RATE_EXTRA_KEY", (float) this.aC.mo6718e());
        singBundle.m21640a("ANALYTICS_PROGRESS_KEY", an());
        singBundle.m21640a("AUDIO_SYSTEM_NAME", this.aC.getAudioSystemName());
        if (this.aC.getAudioSystemName().startsWith("OpenSL")) {
            singBundle.m21639a("OPENSL_STREAM_VERSION", this.aC.getOpenSLStreamVersionInt());
        }
        singBundle.m21640a("INTERNAL_BUFFERING_LATENCY_IN_FRAMES", this.aC.m22274d());
        singBundle.m21640a("OPENSL_STREAM_V1_BUFFERING_LATENCY_IN_FRAMES", this.aC.m22273c());
        SingBundle a = new Builder(singBundle).m21617a(this.aD).m21621a();
        Intent intent = new Intent(this, ReviewActivity_.class);
        PostSingBundle postSingBundle = new PostSingBundle(a);
        postSingBundle.f19318d = false;
        postSingBundle.f19320f = an();
        postSingBundle.m20871b(intent);
        startActivity(intent);
        Log.b(aI, "ReviewActivity launched");
        Log.b(aI, "Calling finish on SingActivity after starting ReviewActivity");
        finish();
        am();
    }

    private String an() {
        float f;
        if (!this.aq || m21552X()) {
            f = this.aA / this.aB;
        } else {
            f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        }
        return Boolean.toString(m21552X()) + "," + String.format(Locale.US, "%.2f", new Object[]{Float.valueOf(f)});
    }

    protected void mo6630U() {
        this.bg = true;
        SingAnalytics.m26095a(SongbookEntryUtils.m26167b(this.aK), HeadphonesType.m17502a(this.f20060g, this.f20061h), this.aQ, this.aR, aj(), m21544P(), mo6627Q());
    }

    public void m21550V() {
        if (this.an.f20149n && TrialSubscriptionActivity.m21947a((Context) this)) {
            mo6619w();
        } else {
            mo6620x();
        }
    }

    public boolean mo6612a() {
        return false;
    }

    public String mo6613b() {
        return null;
    }

    public void finish() {
        super.finish();
        Log.b(aI, "finish called for " + getClass().getName());
    }

    protected String m21555a(double d) {
        int i = (int) d;
        int i2 = i / 60;
        int i3 = i % 60;
        String str = "";
        if (i3 < 10) {
            str = AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
        return i2 + ":" + str + i3;
    }

    protected void m21556a(double d, double d2) {
        boolean z = true;
        if (m21539K()) {
            if (d <= ((double) SingServerValues.m21696q())) {
                z = false;
            }
            this.f20064k = z;
            return;
        }
        if (d <= 0.10000000149011612d * d2) {
            z = false;
        }
        this.f20064k = z;
    }

    @UiThread
    public void mo6615a(double d, double d2, boolean z, float f, float f2) {
        int i = 1;
        if (!isFinishing() && this.aC != null) {
            if (!this.f20063j && z && !isFinishing()) {
                this.f20063j = true;
                m21540L();
                mo6640c(true);
                mo6631Z();
            } else if (d != this.f20062i) {
                this.aG = true;
                this.f20062i = d;
                this.aA = (float) d;
                this.aB = (float) d2;
                m21556a(d, d2);
                if (this.f20045L != null) {
                    this.f20045L.m23220a(d);
                }
                if (this.f20052S != null) {
                    this.f20052S.setText(m21555a(Math.max(0.0d, d2 - d)));
                }
                if (this.f20036C != null && this.f20036C.getVisibility() == 0) {
                    this.f20036C.invalidate();
                }
                if (this.aa != null && this.aa.getVisibility() == 0) {
                    this.aa.invalidate();
                }
                if (this.ab != null && this.ab.getVisibility() == 0) {
                    this.ab.invalidate();
                }
                if (this.f20042I != null) {
                    this.f20042I.setProgress((int) ((d / d2) * 10000.0d));
                }
                if (this.f20037D != null && this.f20037D.getVisibility() == 0) {
                    this.f20037D.m23527a((double) f);
                }
                if (!(this.an == null || !this.an.m21643a() || this.am == null)) {
                    LyricLine a = this.am.m24743a(d);
                    if (a != null) {
                        if (m21541M() && !this.f20036C.m23371b()) {
                            a = this.am.m24743a((((double) this.aW.getDuration()) / 1000.0d) + d);
                        }
                        if (a != null) {
                            int i2 = a.f23496f;
                            if (this.an.m21643a() && this.an.f20142g == 0) {
                                i = 3;
                            } else if (i2 != 0) {
                                i = i2;
                            } else if (this.an.f20142g != 1) {
                                i = 2;
                            }
                            if (i != this.au) {
                                mo6616b(i);
                            }
                        }
                    }
                }
                if (m21539K() || m21541M()) {
                    float min = (float) Math.min(Math.max(f > 0.0f ? 10.0d * Math.log10((double) f) : -30.0d, -30.0d), -6.0d);
                    this.f20038E.m23541a(d, min);
                    if (m21542N() || m21539K()) {
                        float a2 = m21508a(min, -30.0f, -6.0f, 0.75f, (float) DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                        this.f20049P.setScaleX(a2);
                        this.f20049P.setScaleY(a2);
                    }
                }
                this.aG = false;
            }
        }
    }

    private float m21508a(float f, float f2, float f3, float f4, float f5) {
        return (((f - f2) * (f5 - f4)) / (f3 - f2)) + f4;
    }

    @UiThread
    public void mo6616b(int i) {
        Log.b(aI, "scorepart: " + i);
        switch (i) {
            case 1:
                if (m21541M() && !this.f20036C.m23371b()) {
                    if (!this.bd) {
                        this.f20056W.startAnimation(this.aW);
                        this.f20058Y.startAnimation(this.aX);
                    }
                    if (this.be) {
                        this.f20057X.startAnimation(this.aZ);
                        this.f20059Z.startAnimation(this.bc);
                    }
                } else if (!this.bd || this.be) {
                    if (this.bd && this.be) {
                        Log.b(aI, "duel -> one");
                        this.f20047N.bringToFront();
                        this.f20047N.startAnimation(this.bn.f20020a);
                        this.f20048O.startAnimation(this.bn.f20021b);
                    } else if (!this.bd) {
                        Log.b(aI, "two -> one");
                        this.f20047N.bringToFront();
                        this.f20048O.startAnimation(this.bp.f20021b);
                        this.f20047N.startAnimation(this.bp.f20020a);
                    }
                }
                this.bd = true;
                this.be = false;
                break;
            case 2:
                if (m21541M() && !this.f20036C.m23371b()) {
                    if (this.bd) {
                        this.f20056W.startAnimation(this.aV);
                        this.f20058Y.startAnimation(this.aY);
                    }
                    if (!this.be) {
                        this.f20057X.startAnimation(this.ba);
                        this.f20059Z.startAnimation(this.bb);
                    }
                } else if (!this.be || this.bd) {
                    if (this.bd && this.be) {
                        Log.b(aI, "duel -> two");
                        this.f20048O.bringToFront();
                        this.f20048O.startAnimation(this.bo.f20020a);
                        this.f20047N.startAnimation(this.bo.f20021b);
                    } else if (!this.be) {
                        Log.b(aI, "one -> two");
                        this.f20048O.bringToFront();
                        this.f20048O.startAnimation(this.bp.f20020a);
                        this.f20047N.startAnimation(this.bp.f20021b);
                    }
                }
                this.bd = false;
                this.be = true;
                break;
            case 3:
                if (m21541M() && !this.f20036C.m23371b()) {
                    if (!this.bd) {
                        this.f20056W.startAnimation(this.aW);
                        this.f20058Y.startAnimation(this.aX);
                    }
                    if (!this.be) {
                        this.f20057X.startAnimation(this.ba);
                        this.f20059Z.startAnimation(this.bb);
                    }
                } else if (!(this.bd && this.be)) {
                    if (this.bd && !this.be) {
                        this.f20047N.bringToFront();
                        this.f20047N.startAnimation(this.bq.f20020a);
                        this.f20048O.startAnimation(this.bq.f20021b);
                    } else if (!this.bd && this.be) {
                        this.f20048O.bringToFront();
                        this.f20048O.startAnimation(this.br.f20020a);
                        this.f20047N.startAnimation(this.br.f20021b);
                    }
                }
                this.bd = true;
                this.be = true;
                break;
        }
        this.au = i;
    }

    @UiThread
    protected void mo6614W() {
        this.bl = true;
        if (this.al) {
            MagicCrittercism.a(new IllegalStateException("Looping in SingActivity - giving up"));
            String string = getString(C1947R.string.songbook_download_failed_message);
            this.ay = new BusyDialog((Activity) this, string);
            this.ay.m23577a(2, string, true, getString(C1947R.string.core_ok));
            this.ay.m23579a(new BusyDialogListener(this) {
                final /* synthetic */ SingActivity f19993a;

                {
                    this.f19993a = r1;
                }

                public void mo6373a() {
                    this.f19993a.ay.dismiss();
                    try {
                        AudioInterface.destroyPerformance();
                    } catch (Throwable e) {
                        Log.d(SingActivity.aI, "Exception when forcing destroyPerformance", e);
                    }
                    this.f19993a.startActivity(new Intent(this.f19993a, MasterActivity_.class));
                    this.f19993a.finish();
                }
            });
            this.ay.m23580a(true);
            return;
        }
        m21520d(this.aN);
        m21520d(this.aO);
        if (this.as != null && this.as.isShowing()) {
            this.as.dismiss();
            this.as = null;
        }
        this.ax = ao();
        this.ax.m23722a(this.aK, this.ao);
    }

    private void m21520d(String str) {
        if (str != null && !new File(str).delete()) {
            Log.d(aI, "Failed to delete potentially corrupted file: '" + str + "'");
        }
    }

    private SongDownloadDialog ao() {
        String i;
        if (!this.aK.m18772r()) {
            i = this.aK.mo6295i();
        } else if (this.aK.mo6295i() != null) {
            i = this.aK.mo6295i();
        } else if (this.an.m21659h() != null) {
            i = this.an.m21659h().googleCoverArtUrl;
        } else {
            i = "";
        }
        return new SongDownloadDialog(this, getString(C1947R.string.redownload_song_sing), i, new SongDownloadDialogListener(this) {
            final /* synthetic */ SingActivity f19998a;

            class C41181 implements Runnable {
                final /* synthetic */ AnonymousClass23 f19996a;

                C41181(AnonymousClass23 anonymousClass23) {
                    this.f19996a = anonymousClass23;
                }

                public void run() {
                    if (this.f19996a.f19998a.ax != null) {
                        this.f19996a.f19998a.ax.dismiss();
                        this.f19996a.f19998a.ax = null;
                    }
                    Log.a(SingActivity.aI, "Download complete - restarting");
                    final Intent intent = new Intent(this.f19996a.f19998a.getIntent());
                    intent.putExtra("RESTARTED_KEY", true);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable(this) {
                        final /* synthetic */ C41181 f19995b;

                        public void run() {
                            this.f19995b.f19996a.f19998a.startActivity(intent);
                        }
                    }, 100);
                    this.f19996a.f19998a.finish();
                }
            }

            class C41192 implements BusyDialogListener {
                final /* synthetic */ AnonymousClass23 f19997a;

                C41192(AnonymousClass23 anonymousClass23) {
                    this.f19997a = anonymousClass23;
                }

                public void mo6373a() {
                    this.f19997a.f19998a.ay.dismiss();
                    this.f19997a.f19998a.finish();
                }
            }

            {
                this.f19998a = r1;
            }

            public void mo6584a(SongbookEntry songbookEntry) {
                Log.a(SingActivity.aI, "Download success - Preparing to restart");
                this.f19998a.aK = songbookEntry;
                this.f19998a.am = null;
                new Handler(this.f19998a.getMainLooper()).postDelayed(new C41181(this), 200);
            }

            public void mo6583a() {
                MagicCrittercism.a(new IllegalStateException("Backing track re-download failed in SingActivity"));
                String string = this.f19998a.getString(C1947R.string.songbook_download_failed_message);
                this.f19998a.ay = new BusyDialog(this.f19998a, string);
                this.f19998a.ay.m23577a(2, string, true, this.f19998a.getString(C1947R.string.core_ok));
                this.f19998a.ay.m23579a(new C41192(this));
                this.f19998a.ay.m23580a(true);
            }

            public void mo6585b() {
                if (this.f19998a.ax != null) {
                    this.f19998a.ax.dismiss();
                    this.f19998a.ax = null;
                }
                this.f19998a.m21553Y();
            }
        });
    }

    public boolean m21552X() {
        return this.ap == C1947R.id.mPauseMenuSaveButtonLayout;
    }

    private PortraitAnimations ap() {
        AnimationSet animationSet = new AnimationSet(true);
        Animation scaleAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1.6f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1.6f, 0, 0.0f, 1, 0.5f);
        scaleAnimation.setDuration(500);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setFillAfter(true);
        AnimationSet animationSet2 = new AnimationSet(true);
        scaleAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.73f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.73f, 0, 0.0f, 1, 0.5f);
        scaleAnimation.setDuration(500);
        animationSet2.addAnimation(scaleAnimation);
        scaleAnimation = new TranslateAnimation(1, 0.75f, 1, 1.27f, 1, 0.0f, 1, -0.56f);
        scaleAnimation.setDuration(500);
        animationSet2.addAnimation(scaleAnimation);
        scaleAnimation = new AlphaAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.6f);
        scaleAnimation.setDuration(500);
        animationSet2.addAnimation(scaleAnimation);
        animationSet2.setFillAfter(true);
        return new PortraitAnimations(animationSet, animationSet2);
    }

    private PortraitAnimations aq() {
        AnimationSet animationSet = new AnimationSet(true);
        Animation scaleAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1.6f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1.6f, 0, 0.0f, 1, 0.5f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation(scaleAnimation);
        scaleAnimation = new TranslateAnimation(1, 0.75f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setFillAfter(true);
        AnimationSet animationSet2 = new AnimationSet(true);
        scaleAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.73f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.73f, 0, 0.0f, 1, 0.5f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        animationSet2.addAnimation(scaleAnimation);
        scaleAnimation = new TranslateAnimation(1, 0.0f, 1, 1.27f, 1, 0.0f, 1, -0.56f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        animationSet2.addAnimation(scaleAnimation);
        scaleAnimation = new AlphaAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.6f);
        scaleAnimation.setDuration(500);
        animationSet2.addAnimation(scaleAnimation);
        animationSet2.setFillAfter(true);
        return new PortraitAnimations(animationSet, animationSet2);
    }

    private PortraitAnimations ar() {
        AnimationSet animationSet = new AnimationSet(true);
        Animation scaleAnimation = new ScaleAnimation(0.73f, 1.6f, 0.73f, 1.6f, 0, 0.0f, 1, 0.5f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation(scaleAnimation);
        scaleAnimation = new TranslateAnimation(1, 1.27f, 1, 0.0f, 1, -0.56f, 1, 0.0f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation(scaleAnimation);
        scaleAnimation = new AlphaAnimation(0.6f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        scaleAnimation.setDuration(500);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setFillAfter(true);
        AnimationSet animationSet2 = new AnimationSet(true);
        scaleAnimation = new ScaleAnimation(1.6f, 0.73f, 1.6f, 0.73f, 0, 0.0f, 1, 0.5f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        animationSet2.addAnimation(scaleAnimation);
        scaleAnimation = new TranslateAnimation(1, 0.0f, 1, 1.27f, 1, 0.0f, 1, -0.56f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(500);
        animationSet2.addAnimation(scaleAnimation);
        scaleAnimation = new AlphaAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.6f);
        scaleAnimation.setDuration(500);
        animationSet2.addAnimation(scaleAnimation);
        animationSet2.setFillAfter(true);
        return new PortraitAnimations(animationSet, animationSet2);
    }

    private PortraitAnimations as() {
        AnimationSet animationSet = new AnimationSet(true);
        Animation scaleAnimation = new ScaleAnimation(1.6f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1.6f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0, 0.0f, 1, 0.5f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setFillAfter(true);
        AnimationSet animationSet2 = new AnimationSet(true);
        scaleAnimation = new ScaleAnimation(0.73f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.73f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0, 0.0f, 1, 0.5f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        animationSet2.addAnimation(scaleAnimation);
        scaleAnimation = new TranslateAnimation(1, 1.27f, 1, 0.75f, 1, -0.56f, 1, 0.0f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        animationSet2.addAnimation(scaleAnimation);
        scaleAnimation = new AlphaAnimation(0.6f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        scaleAnimation.setDuration(500);
        animationSet2.addAnimation(scaleAnimation);
        animationSet2.setFillAfter(true);
        return new PortraitAnimations(animationSet, animationSet2);
    }

    private PortraitAnimations at() {
        AnimationSet animationSet = new AnimationSet(true);
        Animation scaleAnimation = new ScaleAnimation(0.73f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.73f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0, 0.0f, 1, 0.5f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation(scaleAnimation);
        scaleAnimation = new TranslateAnimation(1, 1.27f, 1, 0.0f, 1, -0.56f, 1, 0.0f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation(scaleAnimation);
        scaleAnimation = new AlphaAnimation(0.6f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        scaleAnimation.setDuration(500);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setFillAfter(true);
        AnimationSet animationSet2 = new AnimationSet(true);
        scaleAnimation = new ScaleAnimation(1.6f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1.6f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0, 0.0f, 1, 0.5f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        animationSet2.addAnimation(scaleAnimation);
        scaleAnimation = new TranslateAnimation(1, 0.0f, 1, 0.75f, 1, 0.0f, 1, 0.0f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        animationSet2.addAnimation(scaleAnimation);
        animationSet2.setFillAfter(true);
        return new PortraitAnimations(animationSet2, animationSet);
    }

    protected void m21553Y() {
        m21517b(0.0f);
        m21550V();
    }

    protected void mo6632a(float f) {
        ak();
        m21517b(f);
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ SingActivity f20001a;

            {
                this.f20001a = r1;
            }

            public void run() {
                final BusyScreenDialog busyScreenDialog = new BusyScreenDialog(this.f20001a, "");
                busyScreenDialog.show();
                Runnable c41201 = new Runnable(this) {
                    final /* synthetic */ AnonymousClass24 f20000b;

                    public void run() {
                        if (busyScreenDialog.isShowing()) {
                            busyScreenDialog.dismiss();
                        }
                        if (!this.f20000b.f20001a.h()) {
                            this.f20000b.f20001a.m21550V();
                        }
                    }
                };
                this.f20001a.aC.m22271a(false, c41201, c41201);
            }
        });
    }

    protected void mo6631Z() {
        ak();
        av();
        new SaveRecordingTask().execute(new Void[0]);
    }

    private String au() {
        return null;
    }

    protected void mo6639c(String str) {
        m21561a(str, (int) C1947R.string.sing_audio_recording_error_header, (int) C1947R.string.sing_audio_recording_error_body);
    }

    protected void m21562a(Throwable th) {
        mo6639c(th.getMessage());
    }

    @android.support.annotation.UiThread
    protected void m21561a(String str, int i, int i2) {
        if (!isFinishing()) {
            if (this.aH != null) {
                Log.d(aI, "recording error dialog showing");
                Log.e(aI, str);
                return;
            }
            this.aH = new TextAlertDialog((Context) this, i, i2, true, false);
            this.aH.m19806a(getString(C1947R.string.core_ok), "");
            this.aH.m19803a(new CustomAlertDialogListener(this) {
                final /* synthetic */ SingActivity f20002a;

                {
                    this.f20002a = r1;
                }

                public void mo6385a(CustomAlertDialog customAlertDialog) {
                    if (this.f20002a.aH != null) {
                        this.f20002a.aH.dismiss();
                        this.f20002a.aH = null;
                    }
                    if (this.f20002a.an != null) {
                        PreSingActivity.m24763a(this.f20002a).m24796a(StartupMode.BADVIDEO).m24795a(this.f20002a.an).m24793a(this.f20002a.an.f20141f).a();
                    }
                    this.f20002a.finish();
                }

                public void mo6386b(CustomAlertDialog customAlertDialog) {
                    mo6385a(customAlertDialog);
                }
            });
            Log.e(aI, str);
            this.aH.show();
        }
    }

    private void av() {
        SingAnalytics.m26105a(ai(), this.an.f20149n ? UserPath.ONBOARDING : UserPath.OTHER, HeadphonesType.m17502a(this.f20060g, this.f20061h), SongbookEntryUtils.m26167b(this.aK), m21552X(), this.aR, this.an.f20142g, aj(), m21544P(), mo6627Q());
        if (this.an.f20149n) {
            Analytics.m17859a(ai(), HeadphonesType.m17502a(this.f20060g, this.f20061h), SongbookEntryUtils.m26167b(this.aK), aj(), CameraStatusType.CAMERA_OFF, m21552X());
        }
    }

    private void m21517b(float f) {
        SingAnalytics.m26060a(this.an.f20154s, AudioCompletionContext.RECORD_EXIT, null, null, au(), null, null, null, null, DeviceSettings.n(), DeviceSettings.h());
        SingAnalytics.m26103a(ai(), this.an.f20149n ? UserPath.ONBOARDING : UserPath.OTHER, HeadphonesType.m17502a(this.f20060g, this.f20061h), SongbookEntryUtils.m26167b(this.aK), (int) f, this.aR, aj(), m21544P(), mo6627Q());
        if (this.an.f20149n) {
            Analytics.m17858a(ai(), HeadphonesType.m17502a(this.f20060g, this.f20061h), SongbookEntryUtils.m26167b(this.aK), aj(), CameraStatusType.CAMERA_OFF, (long) ((int) f));
        }
    }
}
