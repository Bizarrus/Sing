package com.smule.singandroid;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.opengl.GLSurfaceView;
import android.text.TextUtils;
import android.view.Display;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.extractor.ExtractorSampleSource;
import com.mopub.volley.DefaultRetryPolicy;
import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.ScaleModifier;
import com.smule.android.logging.Log;
import com.smule.android.utils.UiHandler;
import com.smule.android.utils.WeakListener.OnGlobalLayoutListener;
import com.smule.singandroid.audio.Metadata;
import com.smule.singandroid.customviews.LyricsView;
import com.smule.singandroid.customviews.VisualizerView;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.models.LyricLine;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.VideoExitType;
import com.smule.singandroid.video.CameraUtils;
import com.smule.singandroid.video.CameraUtils.Config;
import com.smule.singandroid.video.ExoPlayerWrapper;
import com.smule.singandroid.video.ExoPlayerWrapper.ExoPlayerStateChangeListener;
import com.smule.singandroid.video.ExoPlayerWrapper.ScalingForAspectRatio;
import com.smule.singandroid.video.GLVideoRecorder;
import com.smule.singandroid.video.GLVideoRecorder.RecordDelegate;
import com.smule.singandroid.video.GLVideoRecorder.Stats;
import com.smule.singandroid.video.GetAudioTimeCallback;
import com.smule.singandroid.video.TextureMovieEncoder;
import com.smule.singandroid.video.VideoFilterDatabase.FilterType;
import com.smule.singandroid.video.VideoFilterManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import twitter4j.HttpResponseCode;

@TargetApi(19)
@EActivity
public class SingVideoActivity extends SingActivity implements RecordDelegate {
    private static final String bd = SingVideoActivity.class.getSimpleName();
    @ViewById
    protected TextView aI;
    @ViewById
    protected View aJ;
    @ViewById
    GLSurfaceView aK;
    @ViewById
    protected RelativeLayout aL;
    @ViewById
    protected VisualizerView aM;
    @ViewById
    protected ProgressBar aN;
    @ViewById
    protected ViewGroup aO;
    @ViewById
    protected TextView aP;
    @ViewById
    protected View aQ;
    @ViewById
    protected View aR;
    @ViewById
    protected View aS;
    @ViewById
    protected RelativeLayout aT;
    @ViewById
    protected View aU;
    @ViewById
    protected View aV;
    @ViewById
    protected View aW;
    @ViewById
    protected TextureView aX;
    @ViewById
    protected TextView aY;
    @ViewById
    protected RelativeLayout aZ;
    @ViewById
    protected TextView ba;
    @ViewById
    protected FrameLayout bb;
    protected OnGlobalLayoutListener bc;
    private GLVideoRecorder be;
    private String bf;
    private int bg;
    private int bh;
    private int bi;
    private int bj;
    private float bk;
    private int bl = 0;
    private TextAlertDialog bm;
    private boolean bn;
    private OnClickListener bo = new C41431(this);
    private SeedState bp = SeedState.NONE;
    private ExoPlayerWrapper bq;
    private float br;
    private float bs;
    private float bt;
    private float bu;
    private float bv;
    private float bw;
    private ExoPlayerStateChangeListener bx = new C41475(this);

    class C41431 implements OnClickListener {
        final /* synthetic */ SingVideoActivity f20185a;

        C41431(SingVideoActivity singVideoActivity) {
            this.f20185a = singVideoActivity;
        }

        public void onClick(View view) {
            this.f20185a.bn = !this.f20185a.bn;
            this.f20185a.aS.setBackgroundResource(this.f20185a.bn ? C1947R.drawable.airbrush_switch_on : C1947R.drawable.airbrush_switch_off);
            if (this.f20185a.bn) {
                this.f20185a.ar();
            } else {
                this.f20185a.be.m26424a(false);
            }
            MagicPreferences.m20304a(this.f20185a, "AIRBRUSH_PREFERENCE_KEY", this.f20185a.bn);
        }
    }

    class C41453 implements GetAudioTimeCallback {
        final /* synthetic */ SingVideoActivity f20188a;

        C41453(SingVideoActivity singVideoActivity) {
            this.f20188a = singVideoActivity;
        }

        public float mo6397a() {
            if (this.f20188a.aC != null) {
                return this.f20188a.aC.getSongPosition_seconds();
            }
            return 0.0f;
        }
    }

    class C41464 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ SingVideoActivity f20189a;

        C41464(SingVideoActivity singVideoActivity) {
            this.f20189a = singVideoActivity;
        }

        public void onGlobalLayout() {
            LayoutUtils.m25859b(this.f20189a.aT, this.f20189a.bc);
            this.f20189a.bi = this.f20189a.aT.getWidth();
            this.f20189a.bj = this.f20189a.aT.getHeight();
            this.f20189a.bk = this.f20189a.aT.getY();
            Log.b(SingVideoActivity.bd, "overlay:" + this.f20189a.bi + "x" + this.f20189a.bj);
            int[] iArr = new int[2];
            this.f20189a.aT.getLocationOnScreen(iArr);
            if (this.f20189a.bj > this.f20189a.bi) {
                int h = this.f20189a.bj - this.f20189a.bi;
                Log.b(SingVideoActivity.bd, "overlay:shrink overlay by " + h);
                LayoutParams layoutParams = (LayoutParams) this.f20189a.ac.getLayoutParams();
                layoutParams.height += h;
                this.f20189a.ac.setLayoutParams(layoutParams);
                this.f20189a.bj = this.f20189a.bj - h;
            }
            if (this.f20189a.be != null) {
                this.f20189a.be.m26421a(iArr[1] + (this.f20189a.bj / 2), this.f20189a.bh);
            }
            this.f20189a.aq();
        }
    }

    class C41475 implements ExoPlayerStateChangeListener {
        final /* synthetic */ SingVideoActivity f20190a;

        C41475(SingVideoActivity singVideoActivity) {
            this.f20190a = singVideoActivity;
        }

        public void mo6398a(int i) {
            int i2 = 0;
            if (i == 5) {
                Log.b(SingVideoActivity.bd, "seed ended");
                this.f20190a.aX.setVisibility(8);
            } else if (i == 4) {
                this.f20190a.aX.setVisibility(0);
                if (this.f20190a.bp == SeedState.AWAITING_DIMENSIONS) {
                    ExtractorSampleSource h = this.f20190a.bq.m26375h();
                    if (h != null) {
                        int b = h.mo4290b();
                        while (i2 < b) {
                            MediaFormat a = h.mo4285a(i2);
                            if (a.b.contains(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO)) {
                                this.f20190a.m21718a(a.h, a.i);
                            }
                            i2++;
                        }
                    }
                }
            }
        }
    }

    class C41486 implements GetAudioTimeCallback {
        final /* synthetic */ SingVideoActivity f20191a;

        C41486(SingVideoActivity singVideoActivity) {
            this.f20191a = singVideoActivity;
        }

        public float mo6397a() {
            float f = 0.0f;
            try {
                return this.f20191a.br + this.f20191a.aC.getSongPosition_seconds();
            } catch (Throwable e) {
                Log.d(SingVideoActivity.bd, "Exception getting song position from audio interface", e);
                return f;
            }
        }
    }

    enum SeedState {
        NONE,
        SINGLE,
        FILMSTRIP,
        AWAITING_DIMENSIONS
    }

    protected class VideoCountdown extends SingCountdown {
        final /* synthetic */ SingVideoActivity f20202b;

        public VideoCountdown(SingVideoActivity singVideoActivity, boolean z) {
            this.f20202b = singVideoActivity;
            super(singVideoActivity, z);
        }

        public void mo6623b() {
            this.f20202b.z.setVisibility(0);
            this.f20202b.A.setVisibility(0);
            this.f20202b.L.setLyrics(this.f20202b.am);
            this.f20202b.C.m23372c();
        }
    }

    protected class VideoUiAudioLoop extends UiAudioLoop {
        final /* synthetic */ SingVideoActivity f20203e;

        protected VideoUiAudioLoop(SingVideoActivity singVideoActivity) {
            this.f20203e = singVideoActivity;
            super(singVideoActivity);
        }

        public void mo6624a() {
            this.a = true;
            this.f20203e.C.m23368a();
        }

        public void mo6625b() {
            this.a = false;
        }
    }

    public void mo6641d() {
        super.mo6641d();
        ah();
    }

    protected void mo6648q() {
        int i = 1;
        Log.b(bd, "setupViews+");
        this.bp = SeedState.NONE;
        al();
        if (!(m21539K() || m21542N())) {
            if (this.an.m21643a()) {
                LyricsView lyricsView = this.L;
                if (this.an.f20142g != 1) {
                    i = 2;
                }
                lyricsView.setSingPart(i);
            } else if (this.an.m21648b()) {
                this.L.setSingPart(3);
            } else {
                this.L.setSingPart(0);
            }
        }
        Log.b(bd, "setupViews-");
    }

    protected void mo6628S() {
        super.mo6628S();
        ao();
    }

    protected void onResume() {
        super.onResume();
        if (this.bm != null) {
            Log.d(bd, "interrupted dialog showing");
        } else if (this.aH != null) {
            Log.d(bd, "recording error dialog showing");
        }
    }

    protected void onPause() {
        ap();
        super.onPause();
        if (this.be != null) {
            this.be.m26427b();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.be != null) {
            this.be.m26429c();
            this.be = null;
        }
    }

    protected void ab() {
        if (!h()) {
            if (this.bm != null) {
                Log.c(bd, "interrupted dialog already showing");
                return;
            }
            String string;
            String string2;
            final boolean z = this.k;
            if (z) {
                string = getString(C1947R.string.sing_video_interrupted_save_early_title);
                string2 = getString(C1947R.string.sing_video_interrupted_save_early);
            } else {
                string = getString(C1947R.string.sing_video_interrupted_not_enough_title);
                string2 = getString(C1947R.string.sing_video_interrupted_not_now);
            }
            this.bm = new TextAlertDialog((Context) this, string, getString(C1947R.string.sing_video_interrupted_message), true, true);
            this.bm.m19806a(getString(C1947R.string.sing_video_interrupted_sing_again), string2);
            this.bm.setCanceledOnTouchOutside(false);
            Drawable drawable = getResources().getDrawable(C1947R.drawable.bg_cord);
            if (drawable != null) {
                int intrinsicWidth = drawable.getIntrinsicWidth();
                int dimensionPixelSize = intrinsicWidth - (getResources().getDimensionPixelSize(C1947R.dimen.margin_huge) * 2);
                if (dimensionPixelSize > 0) {
                    Log.b(bd, "forcing text width:" + intrinsicWidth);
                    ((TextView) this.bm.findViewById(C1947R.id.title)).setWidth(dimensionPixelSize);
                    ((TextView) this.bm.findViewById(C1947R.id.customTextView)).setWidth(dimensionPixelSize);
                }
            }
            this.bm.m19801a((int) C1947R.drawable.bg_cord, true);
            this.bm.m19803a(new CustomAlertDialogListener(this) {
                final /* synthetic */ SingVideoActivity f20187b;

                public void mo6385a(CustomAlertDialog customAlertDialog) {
                    if (this.f20187b.bm != null) {
                        this.f20187b.bm.dismiss();
                        this.f20187b.bm = null;
                        if (this.f20187b.be != null) {
                            Config c = CameraUtils.m26202a().m26215c();
                            GLVideoRecorder c2 = this.f20187b.be;
                            boolean z = c == null || c.f25111d;
                            c2.m26425a(z, false);
                        }
                        this.f20187b.ao();
                        this.f20187b.ap = C1947R.id.mPauseMenuRestartButtonLayout;
                        this.f20187b.ar = true;
                        this.f20187b.aC.rewindRecording();
                        this.f20187b.aC.prepareForRealTime();
                        this.f20187b.mo6630U();
                        this.f20187b.mo6643g(true);
                    }
                }

                public void mo6386b(CustomAlertDialog customAlertDialog) {
                    if (this.f20187b.bm != null) {
                        this.f20187b.bm.dismiss();
                        this.f20187b.bm = null;
                        if (z) {
                            this.f20187b.ap = C1947R.id.mPauseMenuSaveButtonLayout;
                            this.f20187b.ap();
                            this.f20187b.mo6631Z();
                            return;
                        }
                        this.f20187b.ap = C1947R.id.mPauseMenuNewSongButtonLayout;
                        this.f20187b.ap();
                        this.f20187b.mo6632a(this.f20187b.aC.getSongPosition_seconds());
                    }
                }
            });
            this.bm.show();
        }
    }

    protected void mo6632a(float f) {
        ap();
        super.mo6632a(f);
    }

    protected void mo6631Z() {
        ap();
        super.mo6631Z();
    }

    protected void mo6644k() {
        if (this.aw != null) {
            this.aw.m21504c();
        }
        mo6640c(true);
        if (this.be != null) {
            this.be.m26427b();
            this.be.m26430d();
        }
        ap();
        if (this.aq || isFinishing()) {
            Log.b(bd, "audioFocusLoss called after performance ended; not showing the pause menu");
        } else {
            Log.b(bd, "audioFocusLoss called; showing interrupted menu");
            ab();
            if (this.ax != null && this.ax.isShowing()) {
                this.ax.dismiss();
                this.ax = null;
            }
            if (this.ay != null && this.ay.isShowing()) {
                this.ay.dismiss();
                this.ay = null;
            }
            if (this.as != null && this.as.isShowing()) {
                this.as.dismiss();
                this.as = null;
            }
            if (this.az != null) {
                this.az.dismiss();
                this.az = null;
            }
            this.G.setVisibility(8);
            this.H.setVisibility(8);
            mo6633a(8);
        }
        this.ar = false;
    }

    protected void mo6633a(int i) {
        this.aU.setVisibility(i);
        super.mo6633a(i);
    }

    public void mo6629T() {
        Object obj = null;
        String str = "";
        try {
            File file = new File(this.bf);
            if (!file.exists()) {
                str = "validate:" + this.bf + " does not exist";
            } else if (file.length() <= 0) {
                str = "validate:" + this.bf + " has invalid length";
            } else {
                int i = 1;
            }
        } catch (Exception e) {
            str = "validate:exception checking recording:" + e + " " + this.bf;
        }
        if (obj == null) {
            mo6639c(str);
            return;
        }
        SingBundle i2 = this.an.m21660i();
        i2.m21642a(this.bn);
        i2.m21640a("VIDEO_FILE", this.bf);
        m21745a(this.aA, i2);
        m21560a(i2);
    }

    public void mo6616b(int i) {
        this.au = i;
        m21725c(this.au);
    }

    protected UiAudioLoop mo6649r() {
        return new VideoUiAudioLoop(this);
    }

    public void mo6643g(boolean z) {
        if (this.aw != null) {
            this.aw.m21504c();
        }
        this.aw = new VideoCountdown(this, z);
        this.aw.m21502a();
        this.aK.setVisibility(0);
        this.av = false;
    }

    protected void mo6642e(boolean z) {
        this.aL.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        if (this.bq != null) {
            this.bq.m26377j();
        }
        aq();
        if (z) {
            this.L.m23219a();
            this.L.m23220a(0.0d);
            this.C.m23369a(0.0d, 0.0f, 0.0f);
        }
    }

    public void mo6615a(double d, double d2, boolean z, float f, float f2) {
        if (!isFinishing()) {
            if (this.j || !z || isFinishing()) {
                this.aG = true;
                this.i = d;
                this.aA = (float) d;
                this.aB = (float) d2;
                m21556a(d, d2);
                this.L.m23220a(d);
                this.aP.setText(m21555a(Math.max(0.0d, d2 - d)));
                if (this.C.getVisibility() == 0) {
                    this.C.invalidate();
                }
                if (this.an.m21643a() && this.am != null) {
                    LyricLine a = this.am.m24743a(d);
                    if (a != null) {
                        if (m21541M()) {
                            a = this.am.m24743a(0.5d + d);
                        }
                        if (a != null) {
                            int i = a.f23496f;
                            if (this.an.m21643a() && this.an.f20142g == 0) {
                                i = 3;
                            } else if (i == 0) {
                                i = this.an.f20142g == 1 ? 1 : 2;
                            }
                            if (i != this.au) {
                                mo6616b(i);
                            }
                        }
                    }
                }
                if (d2 > 0.0d) {
                    this.aN.setProgress((int) ((d / d2) * 10000.0d));
                } else {
                    this.aN.setProgress(0);
                }
                if ((m21539K() || m21541M()) && this.aM.getVisibility() == 0) {
                    this.aM.m23541a(d, (float) Math.min(Math.max(f2 > 0.0f ? 10.0d * Math.log10((double) f2) : -30.0d, -30.0d), -6.0d));
                }
                this.aG = false;
                return;
            }
            this.j = true;
            m21540L();
            mo6640c(true);
            this.be.m26430d();
            ap();
            ap();
            mo6631Z();
        }
    }

    private FilterType[] ag() {
        ArrayList arrayList = new ArrayList();
        String f = this.an.m21657f();
        Object obj = -1;
        switch (f.hashCode()) {
            case -906020504:
                if (f.equals("selfie")) {
                    obj = 3;
                    break;
                }
                break;
            case 3157:
                if (f.equals("bw")) {
                    obj = 1;
                    break;
                }
                break;
            case 97429520:
                if (f.equals("fight")) {
                    obj = 4;
                    break;
                }
                break;
            case 109324790:
                if (f.equals("sepia")) {
                    obj = null;
                    break;
                }
                break;
            case 462452390:
                if (f.equals("vintage")) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                arrayList.add(FilterType.SEPIA);
                break;
            case 1:
                arrayList.add(FilterType.BLACK_AND_WHITE);
                break;
            case 2:
                arrayList.add(FilterType.VINTAGE);
                break;
            case 3:
                arrayList.add(FilterType.SELFIE);
                break;
            case 4:
                arrayList.add(FilterType.FIGHTCLUB);
                break;
            default:
                arrayList.add(FilterType.NORMAL);
                break;
        }
        return (FilterType[]) arrayList.toArray(new FilterType[arrayList.size()]);
    }

    private void ah() {
        if (CameraUtils.m26202a().m26215c() == null) {
            mo6639c("CameraUtils config null");
            return;
        }
        FilterType[] ag;
        this.bf = CameraUtils.m26203a(this.aF);
        this.be = new GLVideoRecorder();
        GLSurfaceView gLSurfaceView = (GLSurfaceView) findViewById(C1947R.id.sva_full_screen_camera_preview);
        if (DeviceSettings.r() && DeviceSettings.s()) {
            ag = ag();
            this.ba.setVisibility(8);
        } else {
            ag = new FilterType[0];
            if (!DeviceSettings.r() || DeviceSettings.s()) {
                this.ba.setVisibility(8);
            } else {
                String f = this.an.m21657f();
                if (!(f == null || f.equals("normal"))) {
                    this.ba.setText(getResources().getString(C1947R.string.video_fx_disabled_during_singing, new Object[]{VideoFilterManager.m26575a(f)}));
                }
            }
        }
        this.be.m26422a(this, gLSurfaceView, this.bf, CameraUtils.m26201a((Activity) this), new C41453(this), ag, "", Boolean.valueOf(CameraUtils.m26202a().m26215c().f25111d), VideoFilterManager.m26576a(), this.bn, LayoutUtils.m25846a((Activity) this));
    }

    protected void mo6630U() {
        this.aL.setAlpha(0.0f);
        this.aR.setVisibility(8);
        if (this.be != null) {
            this.be.m26431e();
        }
        this.bl++;
        super.mo6630U();
    }

    @Click
    protected void ac() {
        mo6626C();
    }

    @Click
    protected void ad() {
        mo6626C();
    }

    protected void mo6626C() {
        super.mo6626C();
        if (!DeviceSettings.r()) {
            return;
        }
        if (DeviceSettings.s()) {
            if (SingServerValues.m21672J() && this.ah.getVisibility() == 0) {
                this.aS.setBackgroundResource(this.bn ? C1947R.drawable.airbrush_switch_on : C1947R.drawable.airbrush_switch_off);
                this.aR.setVisibility(0);
                this.aR.setOnClickListener(this.bo);
                return;
            }
            this.aR.setVisibility(8);
            this.aR.setOnClickListener(null);
        } else if (this.ah.getVisibility() != 0) {
            this.ba.setVisibility(0);
        } else {
            this.ba.setVisibility(8);
        }
    }

    private void ai() {
        Log.b(bd, "pauseVideoRecording+");
        if (this.be != null) {
            this.be.m26432f();
        } else {
            Log.e(bd, "pauseVideoRecording: renderer null");
        }
        Log.b(bd, "pauseVideoRecording-");
    }

    private boolean aj() {
        return (m21539K() || m21541M()) ? false : true;
    }

    private boolean ak() {
        return (aj() || m21542N() || m21539K()) ? false : true;
    }

    private void al() {
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        this.bg = point.x;
        this.bh = point.y;
        Log.b(bd, "setupViewsForVideo:" + this.bg + "x" + this.bh);
        int i = (m21539K() || !this.an.m21643a()) ? 0 : 1;
        if (i != 0) {
            this.aJ.setVisibility(0);
            LayoutParams layoutParams = (LayoutParams) this.aI.getLayoutParams();
            layoutParams.setMargins(layoutParams.leftMargin, (int) getResources().getDimension(C1947R.dimen.singing_video_countdown_tip_duet_top_margin), layoutParams.rightMargin, layoutParams.bottomMargin);
            this.aI.setLayoutParams(layoutParams);
            layoutParams = (LayoutParams) this.A.getLayoutParams();
            layoutParams.removeRule(10);
            layoutParams.addRule(3, C1947R.id.sva_countdown_tip_view);
            this.A.setLayoutParams(layoutParams);
            this.aQ.setVisibility(0);
        }
        this.aL.setAlpha(0.0f);
        this.L.setTextViewLayoutId(C1947R.layout.lyric_line_video);
        if (!(aj() || this.C.m23371b())) {
            if (ak()) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.aV.getLayoutParams();
                layoutParams2.height = (int) getResources().getDimension(C1947R.dimen.singing_video_cccp_lyrics_padding);
                this.aV.setLayoutParams(layoutParams2);
                layoutParams2 = (LinearLayout.LayoutParams) this.L.getLayoutParams();
                layoutParams2.height = (int) getResources().getDimension(C1947R.dimen.singing_video_cccp_lyrics_height);
                this.L.setLayoutParams(layoutParams2);
                layoutParams2 = (LinearLayout.LayoutParams) this.aW.getLayoutParams();
                layoutParams2.height = (int) getResources().getDimension(C1947R.dimen.singing_video_cccp_lyrics_padding);
                this.aW.setLayoutParams(layoutParams2);
                layoutParams = (LayoutParams) this.ac.getLayoutParams();
                layoutParams.height = 0;
                this.ac.setLayoutParams(layoutParams);
                this.ac.setBackgroundResource(C1947R.color.black_80_percent);
            } else {
                this.L.setVisibility(8);
                this.aO.setVisibility(8);
                this.aY.setVisibility(0);
                layoutParams = (LayoutParams) this.aZ.getLayoutParams();
                layoutParams.height = (int) getResources().getDimension(C1947R.dimen.singing_video_no_lyrics_container_height);
                this.aZ.setLayoutParams(layoutParams);
            }
        }
        am();
        this.bc = new OnGlobalLayoutListener(this, new C41464(this));
        LayoutUtils.m25854a(this.aT, this.bc);
        this.bn = this.an.m21658g();
    }

    protected void m21745a(float f, SingBundle singBundle) {
        if (this.be != null) {
            Stats g = this.be.m26433g();
            TextureMovieEncoder.Stats h = this.be.m26434h();
            float f2 = ((float) g.f25292b.f24780c) / 1000.0f;
            float f3 = ((float) g.f25291a.f25289b) / f2;
            long j = (long) (h.f25401a.f25397a + h.f25401a.f25400d);
            float f4 = ((float) j) / f2;
            float f5 = ((float) h.f25402b.f24780c) / 1000.0f;
            float f6 = ((float) h.f25401a.f25397a) / f5;
            float f7 = ((float) h.f25401a.f25398b) / f;
            int i = (int) (g.f25291a.f25289b - j);
            int i2 = h.f25401a.f25397a - h.f25401a.f25398b;
            g.m26409a(bd);
            h.m26530a(bd);
            Log.b(bd, "render active:" + f2);
            Log.b(bd, "record active:" + f5);
            Log.b(bd, "recording file duration:" + f);
            Log.b(bd, "camera fps:" + f3);
            Log.b(bd, "encode fps:" + f4);
            Log.b(bd, "encode while record active fps:" + f6);
            Log.b(bd, "drops between camera and encoder:" + i);
            Log.b(bd, "drops between encoder and muxer:" + i2);
            Log.b(bd, "muxer fps:" + f7);
            singBundle.m21638a("VIDEO_STATS_CAMERA_FPS", f3);
            singBundle.m21638a("VIDEO_STATS_ENCODER_FPS", f6);
            singBundle.m21639a("VIDEO_STATS_CAMERA_TO_ENCODER_DROPS", i);
            if (f > 0.0f) {
                singBundle.m21638a("VIDEO_STATS_MUXER_FPS", f7);
            }
            singBundle.m21639a("VIDEO_STATS_ENCODER_TO_MUXER_DROPS", i2);
        }
    }

    public void mo6640c(boolean z) {
        super.mo6640c(z);
        if (z) {
            ai();
            return;
        }
        this.aR.setVisibility(8);
        if (this.be != null) {
            float songPosition_seconds = this.aC.getSongPosition_seconds();
            Log.b(bd, "unpause:curAudioTime:" + songPosition_seconds);
            this.be.m26423a(new Float(songPosition_seconds));
        }
    }

    protected boolean mo6627Q() {
        return true;
    }

    private void am() {
        if (this.bp == SeedState.AWAITING_DIMENSIONS) {
            this.C.setVisibility(8);
            this.aM.setVisibility(8);
        } else if (this.bp != SeedState.NONE && this.bp != SeedState.SINGLE) {
            this.C.setVisibility(8);
            this.aM.setVisibility(8);
        } else if (aj() || this.C.m23371b()) {
            this.C.setVisibility(0);
            this.aM.setVisibility(8);
        } else if (ak()) {
            this.C.setVisibility(8);
            this.aM.setVisibility(8);
        } else {
            this.C.setVisibility(8);
            this.aM.setVisibility(0);
        }
    }

    private void an() {
        am();
        if (this.bp != SeedState.NONE && this.bp != SeedState.AWAITING_DIMENSIONS) {
            if (this.bp == SeedState.SINGLE) {
                this.aX.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                this.aX.setVisibility(0);
                this.bw = (float) (((this.bg / 4) - getResources().getDimensionPixelSize(C1947R.dimen.singing_video_seed_size)) / 2);
                LayoutParams layoutParams = (LayoutParams) this.aX.getLayoutParams();
                layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, (int) this.bw, layoutParams.bottomMargin);
                this.aX.setLayoutParams(layoutParams);
            } else if (this.bp == SeedState.FILMSTRIP) {
                if (this.bq != null) {
                    this.bq.m26366a(ScalingForAspectRatio.FIT_FOR_FILMSTRIP);
                }
                this.aX.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                this.aX.setVisibility(0);
                ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, -1);
                layoutParams2.addRule(6, this.ac.getId());
                layoutParams2.addRule(8, this.ac.getId());
                this.aX.setLayoutParams(layoutParams2);
            }
        }
    }

    private void m21718a(int i, int i2) {
        if (this.bp != SeedState.AWAITING_DIMENSIONS) {
            Log.b(bd, "processFilmstripSize:not waiting on dimensions");
            return;
        }
        Log.b(bd, "processFilmstripSize:" + i + "x" + i2);
        if (i == i2) {
            this.bp = SeedState.SINGLE;
        } else {
            this.bp = SeedState.FILMSTRIP;
        }
        an();
    }

    private void ao() {
        ap();
        if (this.ao == null) {
            this.bp = SeedState.NONE;
            return;
        }
        Object obj;
        if (this.ao.d()) {
            this.bp = SeedState.SINGLE;
            if (TextUtils.isEmpty(this.ao.joinVideoUrl)) {
                obj = this.ao.origTrackVideoUrl;
            } else {
                obj = this.ao.joinVideoUrl;
            }
        } else {
            this.bp = SeedState.AWAITING_DIMENSIONS;
            obj = this.ao.filmstripUrl;
        }
        if (TextUtils.isEmpty(obj)) {
            this.bp = SeedState.NONE;
            return;
        }
        an();
        if (this.ao.d()) {
            this.br = -1.0f;
            if (this.an.f20144i != null) {
                Metadata a = Metadata.a(new File(this.an.f20144i));
                if (a != null) {
                    this.br = ((float) a.userDelayCalibrationMs) / 1000.0f;
                    Log.b(bd, "Seed video user delay calibration from metadata:" + this.br);
                }
            }
            if (this.br < 0.0f) {
                this.br = 0.0f;
                Log.b(bd, "Seed video user delay calibration fallback:" + this.br);
            }
        } else {
            this.br = 0.0f;
            this.aX.setAlpha(0.0f);
            this.aX.setVisibility(0);
        }
        this.bq = new ExoPlayerWrapper(this, this.aX, this.at, obj, new C41486(this), 0.2f, 2.0f, null, this.bx, null, false, false);
    }

    private void ap() {
        if (this.bq != null) {
            SingAnalytics.m26111a(this.ao.performanceKey, (this.ap == C1947R.id.mPauseMenuNewSongButtonLayout ? 1 : null) != null ? VideoExitType.CANCEL : VideoExitType.COMPLETE, this.bq.m26376i(), this.bl);
            this.bq.m26368b();
            this.bq = null;
            this.aX.setVisibility(8);
        }
    }

    private void aq() {
        if (this.bq != null && this.bp == SeedState.SINGLE) {
            float dimensionPixelSize = (float) getResources().getDimensionPixelSize(C1947R.dimen.singing_video_seed_size);
            float f = this.bw;
            this.bs = (((float) this.bi) - dimensionPixelSize) - f;
            this.bt = ((((float) this.bj) - dimensionPixelSize) - f) + this.bk;
            f = 1.5f * dimensionPixelSize;
            this.bu = this.bs - ((f - dimensionPixelSize) / 2.0f);
            this.bv = this.bt - ((f - dimensionPixelSize) / 2.0f);
            Log.b(bd, "videoAnimationReset:start:" + this.bs + "x" + this.bt);
            Log.b(bd, "videoAnimationReset:zoom:" + this.bu + "x" + this.bv);
            this.aX.setX(this.bs);
            this.aX.setY(this.bt);
            this.aX.setScaleX(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            this.aX.setScaleY(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        }
    }

    private void m21725c(int i) {
        Object obj = 1;
        if (this.bq != null) {
            Log.b(bd, "newPart:" + i);
            if (i != 3 && i == this.an.f20142g) {
                obj = null;
            }
            if (obj != null) {
                this.aX.animate().x(this.bu).y(this.bv).scaleX(1.5f).scaleY(1.5f).setDuration(250).start();
            } else {
                this.aX.animate().x(this.bs).y(this.bt).scaleX(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).scaleY(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).setDuration(250).start();
            }
        }
    }

    protected void mo6639c(String str) {
        if (this.bm != null) {
            Log.d(bd, "interrupted dialog showing");
            Log.e(bd, str);
            return;
        }
        m21561a(str, (int) C1947R.string.sing_video_recording_error_header, (int) C1947R.string.sing_video_recording_error_body);
    }

    public void mo6634a(Exception exception) {
        mo6639c("encoder error:" + exception);
    }

    public void ae() {
    }

    private void ar() {
        final ParticleSystem particleSystem = new ParticleSystem(this.aL, 1000, getResources().getDrawable(C1947R.drawable.glow_particle_25_percent), 750);
        particleSystem.m17362a(0.05f, 0.3f);
        particleSystem.m17366a(new ScaleModifier(0.3f, 0.0f, 0, 750));
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, (float) this.aK.getMeasuredWidth()});
        ofFloat.setDuration(1600);
        ofFloat.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ SingVideoActivity f20193b;

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (!this.f20193b.h()) {
                    Random random = new Random();
                    particleSystem.m17363a(0.0f, 0.03f, 0, 360);
                    particleSystem.m17364a(5.0E-5f, random.nextInt(361));
                    this.f20193b.bb.setX(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    particleSystem.m17368a(this.f20193b.bb, 5);
                }
            }
        });
        ofFloat.addListener(new AnimatorListener(this) {
            final /* synthetic */ SingVideoActivity f20196b;

            class C41501 implements Runnable {
                final /* synthetic */ C41518 f20194a;

                C41501(C41518 c41518) {
                    this.f20194a = c41518;
                }

                public void run() {
                    if (!this.f20194a.f20196b.h()) {
                        this.f20194a.f20196b.be.m26424a(true);
                    }
                }
            }

            public void onAnimationStart(Animator animator) {
                this.f20196b.aR.setOnClickListener(null);
                new UiHandler(this.f20196b).postDelayed(new C41501(this), 1040);
            }

            public void onAnimationEnd(Animator animator) {
                if (!this.f20196b.h()) {
                    particleSystem.m17367a();
                    this.f20196b.bb.setLayerType(0, null);
                    this.f20196b.aR.setOnClickListener(this.f20196b.bo);
                }
            }

            public void onAnimationCancel(Animator animator) {
                onAnimationEnd(animator);
            }

            public void onAnimationRepeat(Animator animator) {
            }
        });
        this.bb.setLayerType(2, null);
        particleSystem.m17369a(this.bb, 5, HttpResponseCode.MULTIPLE_CHOICES);
        ofFloat.start();
    }
}
