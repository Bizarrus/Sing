package com.smule.singandroid.pre_sing;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;
import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.ScaleModifier;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.android.utils.UiHandler;
import com.smule.android.utils.WeakListener.OnGlobalLayoutListener;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.DeviceSettings;
import com.smule.singandroid.MagicPreferences;
import com.smule.singandroid.SingBundle.PerformanceType;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.customviews.VideoFXTabIndicator;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.runtimepermissions.SingPermissionRequests;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.FxVipStatusType;
import com.smule.singandroid.utils.TypefaceUtils;
import com.smule.singandroid.video.CameraUtils;
import com.smule.singandroid.video.GLVideoRecorder;
import com.smule.singandroid.video.GLVideoRecorder.RecordDelegate;
import com.smule.singandroid.video.PageSwiper;
import com.smule.singandroid.video.PageSwiper.ChangeListener;
import com.smule.singandroid.video.VideoFilterDatabase;
import com.smule.singandroid.video.VideoFilterDatabase.FilterType;
import com.smule.singandroid.video.VideoFilterManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;
import twitter4j.HttpResponseCode;

@EFragment
@TargetApi(19)
public class PreSingVideoSelectionFragment extends PreSingDownloadFragment {
    private static final String ag = PreSingVideoSelectionFragment.class.getSimpleName();
    @ViewById
    protected TextView f23779H;
    @ViewById
    protected TextView f23780I;
    @ViewById
    protected Button f23781J;
    @ViewById
    protected SwitchCompat f23782K;
    @ViewById
    protected LinearLayout f23783L;
    @ViewById
    protected FrameLayout f23784M;
    @ViewById
    protected FrameLayout f23785N;
    @ViewById
    protected ImageView f23786O;
    @ViewById
    protected RelativeLayout f23787P;
    @ViewById
    protected LinearLayout f23788Q;
    @ViewById
    protected View f23789R;
    @ViewById
    protected View f23790S;
    @ViewById
    protected LinearLayout f23791T;
    @ViewById
    protected FrameLayout f23792U;
    protected GLSurfaceView f23793V = null;
    @ViewById
    protected View f23794W;
    @ViewById
    protected VideoFXTabIndicator f23795X;
    @ViewById
    protected FrameLayout f23796Y;
    @ViewById
    protected TextView f23797Z;
    private long aA = 1000;
    private TextAlertDialog aB;
    @ViewById
    protected TextView aa;
    protected int ab = -1;
    protected OnCheckedChangeListener ac = new C48003(this);
    protected OrientationEventListener ad;
    final RecordDelegate ae = new C48025(this);
    @InstanceState
    protected boolean af = true;
    private GLVideoRecorder ah;
    private PageSwiper ai;
    private OnGlobalLayoutListener aj;
    private boolean ak;
    private ParticleSystem al;
    private ValueAnimator am;
    private int an;
    private List<String> ao = new ArrayList();
    private OnClickListener ap = new C47971(this);
    private ChangeListener aq = new C47982(this);
    private boolean ar;
    private boolean as = true;
    private int at;
    private boolean au;
    private boolean av;
    private boolean aw = false;
    private boolean ax;
    private OnClickListener ay = new OnClickListener(this) {
        final /* synthetic */ PreSingVideoSelectionFragment f23754a;

        class C47941 implements ResultCallback {
            final /* synthetic */ AnonymousClass10 f23753a;

            C47941(AnonymousClass10 anonymousClass10) {
                this.f23753a = anonymousClass10;
            }

            public void mo6372a(boolean z, @NonNull Set<String> set) {
                if (z) {
                    this.f23753a.f23754a.mo6913Y();
                    return;
                }
                this.f23753a.f23754a.f23781J.setOnClickListener(this.f23753a.f23754a.ay);
                this.f23753a.f23754a.f23784M.setEnabled(true);
                this.f23753a.f23754a.f23782K.setEnabled(true);
            }
        }

        {
            this.f23754a = r1;
        }

        public void onClick(View view) {
            this.f23754a.f23781J.setOnClickListener(null);
            this.f23754a.f23784M.setEnabled(false);
            this.f23754a.f23782K.setEnabled(false);
            this.f23754a.mo6406a(SingPermissionRequests.f23950d, new C47941(this));
        }
    };
    private boolean az;

    class C47971 implements OnClickListener {
        final /* synthetic */ PreSingVideoSelectionFragment f23768a;

        C47971(PreSingVideoSelectionFragment preSingVideoSelectionFragment) {
            this.f23768a = preSingVideoSelectionFragment;
        }

        public void onClick(View view) {
            this.f23768a.al();
        }
    }

    class C47982 implements ChangeListener {
        final /* synthetic */ PreSingVideoSelectionFragment f23769a;

        C47982(PreSingVideoSelectionFragment preSingVideoSelectionFragment) {
            this.f23769a = preSingVideoSelectionFragment;
        }

        public void mo6581a(float f) {
            if (this.f23769a.ah != null && this.f23769a.ah.m26420a() != null) {
                this.f23769a.ah.m26420a().m26471a(f);
            }
        }

        public void mo6580a() {
            if (this.f23769a.ah != null && this.f23769a.ah.m26420a() != null) {
                this.f23769a.ah.m26420a().mo7000f();
                this.f23769a.f23795X.m23165c();
                Log.b(PreSingVideoSelectionFragment.ag, "next filter is on");
            }
        }

        public void mo6582b() {
            if (this.f23769a.ah != null && this.f23769a.ah.m26420a() != null) {
                this.f23769a.ah.m26420a().mo7001g();
                this.f23769a.f23795X.m23166d();
                Log.b(PreSingVideoSelectionFragment.ag, "previous filter is on");
            }
        }
    }

    class C48003 implements OnCheckedChangeListener {
        final /* synthetic */ PreSingVideoSelectionFragment f23771a;

        class C47991 implements ResultCallback {
            final /* synthetic */ C48003 f23770a;

            C47991(C48003 c48003) {
                this.f23770a = c48003;
            }

            public void mo6372a(boolean z, @NonNull Set<String> set) {
                if (z) {
                    this.f23770a.f23771a.m25052d(true);
                } else {
                    this.f23770a.f23771a.f23782K.setChecked(false);
                }
            }
        }

        C48003(PreSingVideoSelectionFragment preSingVideoSelectionFragment) {
            this.f23771a = preSingVideoSelectionFragment;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (!z) {
                this.f23771a.m25052d(false);
            } else if (ContextCompat.checkSelfPermission(this.f23771a.getActivity(), "android.permission.CAMERA") == 0) {
                this.f23771a.m25052d(true);
            } else {
                m25041a();
            }
        }

        private void m25041a() {
            this.f23771a.mo6406a(SingPermissionRequests.f23949c, new C47991(this));
        }
    }

    class C48014 implements OnClickListener {
        final /* synthetic */ PreSingVideoSelectionFragment f23772a;

        C48014(PreSingVideoSelectionFragment preSingVideoSelectionFragment) {
            this.f23772a = preSingVideoSelectionFragment;
        }

        public void onClick(View view) {
            this.f23772a.ak();
        }
    }

    class C48025 implements RecordDelegate {
        final /* synthetic */ PreSingVideoSelectionFragment f23773a;

        C48025(PreSingVideoSelectionFragment preSingVideoSelectionFragment) {
            this.f23773a = preSingVideoSelectionFragment;
        }

        public void mo6634a(Exception exception) {
        }

        public void ae() {
            if (this.f23773a.au) {
                this.f23773a.ad();
            } else {
                this.f23773a.av = true;
            }
        }
    }

    class C48047 implements OnTouchListener {
        final /* synthetic */ PreSingVideoSelectionFragment f23775a;

        C48047(PreSingVideoSelectionFragment preSingVideoSelectionFragment) {
            this.f23775a = preSingVideoSelectionFragment;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                this.f23775a.f23794W.setVisibility(4);
                this.f23775a.f23795X.m23167e();
            }
            return false;
        }
    }

    class C48058 implements Runnable {
        final /* synthetic */ PreSingVideoSelectionFragment f23776a;

        C48058(PreSingVideoSelectionFragment preSingVideoSelectionFragment) {
            this.f23776a = preSingVideoSelectionFragment;
        }

        public void run() {
            if (this.f23776a.isAdded()) {
                this.f23776a.m25056e(true);
            }
        }
    }

    protected void mo6897a() {
        m25076c(true);
    }

    protected void mo6912T() {
        m24872U();
        Pair a = MiscUtils.m25881a(this.k, this.j, true);
        this.f23779H.setText((CharSequence) a.first);
        this.f23780I.setText((CharSequence) a.second);
        if (this.C == null) {
            m24874W();
            m25076c(false);
        } else {
            m25076c(true);
        }
        this.f23784M.setOnClickListener(new C48014(this));
        this.f23781J.setOnClickListener(this.ay);
        this.G = getActivity().getWindow().getDecorView().getSystemUiVisibility();
        m24840P();
    }

    public String mo6383s() {
        return ag;
    }

    public boolean mo6511h() {
        return false;
    }

    protected void mo6910G() {
    }

    public void onStop() {
        super.onStop();
        this.f23792U.setVisibility(8);
        if (this.ad != null) {
            this.ad.disable();
            this.ad = null;
        }
    }

    protected void mo6896E() {
        super.mo6896E();
        this.f23782K.setChecked(false);
        this.f23782K.setOnCheckedChangeListener(this.ac);
        this.f23782K.setChecked(true);
    }

    public void onResume() {
        super.onResume();
        this.av = false;
        this.au = false;
        if (ContextCompat.checkSelfPermission(getActivity(), "android.permission.CAMERA") == 0) {
            this.f23782K.setOnCheckedChangeListener(null);
            this.f23782K.setChecked(false);
            this.f23782K.setOnCheckedChangeListener(this.ac);
            this.f23782K.setChecked(true);
            return;
        }
        aj();
    }

    public void onPause() {
        super.onPause();
        ag();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.ah != null) {
            this.ah.m26429c();
            this.ah = null;
        }
    }

    public void m25075b(boolean z) {
        this.aw = z;
    }

    private boolean ac() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int i = defaultSharedPreferences.getInt("VIDEO_FX_ENGAGEMENT_COUNT", 0);
        if (i >= 2) {
            return false;
        }
        if (this.ax) {
            return true;
        }
        defaultSharedPreferences.edit().putInt("VIDEO_FX_ENGAGEMENT_COUNT", i + 1).apply();
        this.ax = true;
        return true;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.av) {
            ad();
        } else {
            this.au = true;
        }
    }

    private void ad() {
        this.au = false;
        this.av = false;
        if (isAdded()) {
            ag();
            this.ab = -1;
            ae();
        }
    }

    private void ae() {
        if (this.ar) {
            Log.b(ag, "startVideoPreview: camera already on");
            return;
        }
        if (this.f23793V == null) {
            this.f23793V = new GLSurfaceView(getActivity());
            this.f23792U.addView(this.f23793V, -1, -1);
            this.f23793V.setVisibility(0);
        }
        if (ac()) {
            this.f23794W.setVisibility(0);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f23794W, View.ALPHA, new float[]{0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT});
            ofFloat.setDuration((long) getResources().getInteger(17694721));
            ofFloat.start();
        }
        this.at = CameraUtils.m26201a(getActivity());
        if (this.ab == -1) {
            this.ab = this.at;
        }
        Log.b(ag, "rotation:" + this.at + " " + this.ab);
        if (this.ad == null) {
            this.ad = new OrientationEventListener(this, getActivity(), 3) {
                final /* synthetic */ PreSingVideoSelectionFragment f23774a;

                public void onOrientationChanged(int i) {
                    if (this.f23774a.isAdded() && this.f23774a.ah != null) {
                        int a = CameraUtils.m26201a(this.f23774a.getActivity());
                        if (a != this.f23774a.at) {
                            Log.b(PreSingVideoSelectionFragment.ag, "onOrientationChanged:" + this.f23774a.at + " " + this.f23774a.ab + " cur:" + a);
                            this.f23774a.ah.m26428b(this.f23774a.ab != a);
                            this.f23774a.at = a;
                        }
                    }
                }
            };
            if (this.ad.canDetectOrientation()) {
                this.ad.enable();
            }
        }
        Point a = LayoutUtils.m25846a(getActivity());
        if (this.ah == null) {
            Log.b(ag, "creatingVideoPlayer and starting preview");
            String str = "";
            if (this.i.f20146k && this.i.m21648b()) {
                String str2;
                if (this.i.m21657f() == null) {
                    str2 = "normal";
                } else {
                    str2 = this.i.m21657f();
                }
                str = str2;
            }
            this.ah = new GLVideoRecorder();
            try {
                if (!DeviceSettings.r() || SingServerValues.m21670H().isEmpty()) {
                    this.ah.m26422a(this.ae, this.f23793V, null, this.at, null, new FilterType[0], null, Boolean.valueOf(this.as), false, false, a);
                } else {
                    this.ah.m26422a(this.ae, this.f23793V, null, this.at, null, VideoFilterDatabase.m26569a(), str, Boolean.valueOf(this.as), VideoFilterManager.m26576a(), false, a);
                }
            } catch (Exception e) {
                m25047a("start", e);
                return;
            }
        }
        try {
            this.ab = this.at;
            this.ah.m26426a(this.as, false, this.at, a);
        } catch (Exception e2) {
            m25047a("start", e2);
            return;
        }
        this.ar = true;
        if (DeviceSettings.r() && this.ah.m26420a() != null && this.ah.m26420a().m26485i()) {
            this.f23795X.m23158a();
            if (this.ai == null) {
                if (this.i.f20146k && this.i.m21648b()) {
                    this.f23795X.m23528a(this.i.m21657f());
                } else {
                    this.f23795X.setHideIndicatorsWithAnimation(false);
                    this.f23795X.m23529b(this.ah.m26420a().m26484h(), 0);
                    this.ai = new PageSwiper();
                    this.ai.m26526a(this.aq);
                    this.f23793V.setOnTouchListener(this.ai);
                }
                this.f23795X.setOnTouchListener(new C48047(this));
            }
            af();
            if (SingServerValues.m21672J()) {
                int i;
                this.ak = MagicPreferences.m20317b(getActivity(), "AIRBRUSH_PREFERENCE_KEY", true);
                if (this.ak) {
                    if (MagicPreferences.m20317b(getActivity(), "AIRBRUSH_IS_FTUX", true)) {
                        new UiHandler((Fragment) this).postDelayed(new C48058(this), 1000);
                    } else {
                        this.f23785N.setOnClickListener(this.ap);
                        if (!(this.ah == null || this.ah.m26420a() == null)) {
                            this.ah.m26424a(true);
                        }
                    }
                }
                this.f23785N.setOnClickListener(this.ap);
                ImageView imageView = this.f23786O;
                if (this.ak) {
                    i = C1947R.drawable.airbrush_switch_on;
                } else {
                    i = C1947R.drawable.airbrush_switch_off;
                }
                imageView.setBackgroundResource(i);
                return;
            }
            return;
        }
        this.f23785N.setVisibility(8);
        this.f23794W.setVisibility(8);
        this.f23795X.m23164b();
    }

    private void af() {
        final View b = this.f23795X.m23163b(C1947R.id.tab_title);
        this.aj = new OnGlobalLayoutListener(this.f23795X, new ViewTreeObserver.OnGlobalLayoutListener(this) {
            final /* synthetic */ PreSingVideoSelectionFragment f23778b;

            public void onGlobalLayout() {
                if (this.f23778b.isAdded()) {
                    LayoutUtils.m25859b(b, this.f23778b.aj);
                    MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f23778b.f23783L.getLayoutParams();
                    marginLayoutParams.topMargin = (marginLayoutParams.topMargin + (b.getTop() + (b.getMeasuredHeight() / 2))) - ((this.f23778b.getResources().getDimensionPixelOffset(C1947R.dimen.smoothing_touchable_area_size) / 2) + this.f23778b.f23783L.getTop());
                    this.f23778b.f23783L.setLayoutParams(marginLayoutParams);
                    this.f23778b.f23784M.setVisibility(0);
                    this.f23778b.f23785N.setVisibility(VideoFilterManager.m26576a() ? 0 : 8);
                }
            }
        });
        LayoutUtils.m25854a(b, this.aj);
    }

    private void ag() {
        if (this.ar) {
            Log.b(ag, "stopVideoPreview");
            if (this.am != null && this.am.isRunning()) {
                this.am.cancel();
            }
            this.f23794W.setVisibility(8);
            this.f23795X.m23164b();
            if (this.ah != null) {
                this.ah.m26427b();
            }
            this.ar = false;
        }
    }

    void mo6913Y() {
        String str = null;
        boolean isChecked = this.f23782K.isChecked();
        if (this.i.f20137b != PerformanceType.GROUP || !isChecked || this.j == null || SingServerValues.m21699t() > this.j.totalPerformers) {
            String join;
            this.i.m21641a("VIDEO_RECORD_ENABLED_KEY", isChecked);
            boolean z = false;
            if (isChecked) {
                Object obj;
                Iterable arrayList = new ArrayList();
                if (DeviceSettings.r()) {
                    String currentItemId = this.f23795X.getCurrentItemId();
                    if (currentItemId == null) {
                        currentItemId = "normal";
                    }
                    isChecked = VideoFilterManager.m26577b(currentItemId);
                    this.i.m21637a(currentItemId);
                    boolean z2 = isChecked;
                    obj = currentItemId;
                    z = z2;
                } else {
                    obj = "unsupported";
                    this.i.m21637a("normal");
                }
                arrayList.add(obj);
                this.i.m21642a(this.ak);
                if (this.ak) {
                    arrayList.add("airbrush");
                }
                Collections.sort(arrayList);
                join = TextUtils.join(":", arrayList);
            } else {
                join = null;
            }
            ag();
            if (this.j != null) {
                str = this.j.performanceKey;
            }
            SingAnalytics.m26115a(str, this.k.mo6290d(), z ? FxVipStatusType.VIP : FxVipStatusType.NON_VIP, join, ah());
            this.az = true;
            m24838N();
            return;
        }
        m19846b((int) C1947R.string.sing_video_join_limit_reached);
        this.f23781J.setOnClickListener(this.ay);
    }

    private String ah() {
        if (this.j != null) {
            return SingAnalytics.m26140d(this.j);
        }
        return SingAnalytics.m26141d(this.k);
    }

    void m25076c(boolean z) {
        Log.b(ag, "enableStartButton: allow:" + z);
        if (z) {
            this.f23781J.setEnabled(true);
            this.f23781J.startAnimation(AnimationUtils.loadAnimation(getActivity(), C1947R.anim.pulse));
            return;
        }
        this.f23781J.setEnabled(false);
    }

    private void ai() {
        int i = 0;
        this.f23792U.setVisibility(0);
        this.f23789R.setVisibility(0);
        this.f23790S.setVisibility(0);
        this.f23784M.setVisibility(0);
        this.f23785N.setVisibility(VideoFilterManager.m26576a() ? 0 : 8);
        this.f23788Q.setVisibility(8);
        this.f23787P.setBackgroundColor(0);
        this.f23779H.setTextColor(getResources().getColor(C1947R.color.contextual_text));
        this.f23780I.setTextColor(getResources().getColor(C1947R.color.body_text_dark_grey));
        LinearLayout linearLayout = this.f23791T;
        if (!this.i.m21643a()) {
            i = 8;
        }
        linearLayout.setVisibility(i);
        this.f23797Z.setTypeface(TypefaceUtils.m26188a());
        this.aa.setTypeface(TypefaceUtils.m26191b());
    }

    private void aj() {
        this.f23792U.setVisibility(8);
        this.f23789R.setVisibility(8);
        this.f23790S.setVisibility(8);
        this.f23784M.setVisibility(8);
        this.f23785N.setVisibility(8);
        this.f23788Q.setVisibility(0);
        this.f23787P.setBackgroundColor(getResources().getColor(C1947R.color.presing_background));
        this.f23779H.setTextColor(getResources().getColor(C1947R.color.body_text_dark_grey));
        this.f23780I.setTextColor(getResources().getColor(C1947R.color.presing_text));
        this.f23791T.setVisibility(8);
        this.f23797Z.setTypeface(TypefaceUtils.m26191b());
        this.aa.setTypeface(TypefaceUtils.m26188a());
        this.f23795X.m23164b();
    }

    private void ak() {
        boolean z = false;
        Log.c(ag, "switching camera" + this.aA);
        this.f23784M.setEnabled(false);
        this.f23781J.setOnClickListener(null);
        this.f23782K.setEnabled(false);
        m19839a(new Runnable(this) {
            final /* synthetic */ PreSingVideoSelectionFragment f23755a;

            {
                this.f23755a = r1;
            }

            public void run() {
                Log.c(PreSingVideoSelectionFragment.ag, "unlocking switch");
                if (this.f23755a.isAdded()) {
                    this.f23755a.f23784M.setEnabled(true);
                    this.f23755a.f23782K.setEnabled(true);
                    this.f23755a.f23781J.setOnClickListener(this.f23755a.ay);
                }
            }
        }, this.aA);
        ag();
        if (!this.as) {
            z = true;
        }
        this.as = z;
        ae();
    }

    private void al() {
        this.ak = !this.ak;
        this.f23786O.setBackgroundResource(this.ak ? C1947R.drawable.airbrush_switch_on : C1947R.drawable.airbrush_switch_off);
        if (this.ak) {
            m25056e(false);
        } else {
            this.ah.m26420a().m26476a(false);
        }
        MagicPreferences.m20304a(getActivity(), "AIRBRUSH_PREFERENCE_KEY", this.ak);
    }

    private void m25052d(boolean z) {
        Log.b(ag, "videoToggleClicked:" + z);
        if (z) {
            this.f23784M.setEnabled(false);
            this.f23782K.setEnabled(false);
            m19839a(new Runnable(this) {
                final /* synthetic */ PreSingVideoSelectionFragment f23756a;

                {
                    this.f23756a = r1;
                }

                public void run() {
                    if (this.f23756a.isAdded()) {
                        this.f23756a.f23784M.setEnabled(true);
                        this.f23756a.f23782K.setEnabled(true);
                    }
                }
            }, this.aA);
            ae();
            ai();
            return;
        }
        ag();
        aj();
    }

    @Click
    protected void mo6914Z() {
        getActivity().onBackPressed();
    }

    protected Pair<Integer, Integer> mo6911O() {
        return new Pair(Integer.valueOf(C1947R.animator.slide_up_long), Integer.valueOf(C1947R.animator.slide_down_long));
    }

    @Click
    protected void aa() {
    }

    public Animator onCreateAnimator(int i, boolean z, final int i2) {
        if (i2 == 0) {
            return null;
        }
        Animator loadAnimator = AnimatorInflater.loadAnimator(getActivity(), i2);
        if (loadAnimator != null) {
            final PreSingActivity preSingActivity = (PreSingActivity) getActivity();
            final Window window = getActivity().getWindow();
            window.setFlags(16, 16);
            this.af = true;
            loadAnimator.addListener(new AnimatorListener(this) {
                final /* synthetic */ PreSingVideoSelectionFragment f23760d;

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    Log.b(PreSingVideoSelectionFragment.ag, "onCurtainAnimationEnd");
                    window.clearFlags(16);
                    if (i2 == C1947R.animator.slide_down_long) {
                        if (!this.f23760d.az) {
                            preSingActivity.m24775H();
                        }
                        this.f23760d.az = false;
                    } else if (this.f23760d.isAdded() && i2 == C1947R.animator.slide_up_long) {
                        this.f23760d.af = false;
                        if (!this.f23760d.aw) {
                            this.f23760d.aj();
                            this.f23760d.f23782K.setChecked(false);
                        }
                    }
                }

                public void onAnimationCancel(Animator animator) {
                    this.f23760d.getActivity().getWindow().clearFlags(16);
                }

                public void onAnimationRepeat(Animator animator) {
                }
            });
        }
        return loadAnimator;
    }

    public boolean mo6400c() {
        Log.b(ag, "onFragmentBackPressed mFragmentAnimatingIn: " + this.af);
        return this.af || super.mo6400c();
    }

    @UiThread
    private void m25047a(String str, Exception exception) {
        if (isAdded()) {
            Log.b(ag, "showCameraErrorDialog " + str);
            if (this.aB != null) {
                Log.b(ag, "dialog already showing");
                return;
            }
            this.aB = new TextAlertDialog(getActivity(), (int) C1947R.string.pre_sing_camera_issues_title, (int) C1947R.string.pre_sing_camera_issues_body, true, false);
            this.aB.m19806a(getString(C1947R.string.core_ok), "");
            this.aB.m19803a(new CustomAlertDialogListener(this) {
                final /* synthetic */ PreSingVideoSelectionFragment f23761a;

                {
                    this.f23761a = r1;
                }

                public void mo6385a(CustomAlertDialog customAlertDialog) {
                    if (this.f23761a.aB != null) {
                        this.f23761a.aB.dismiss();
                        this.f23761a.aB = null;
                    }
                    if (this.f23761a.isAdded() && this.f23761a.f23782K != null) {
                        this.f23761a.f23782K.setChecked(false);
                    }
                }

                public void mo6386b(CustomAlertDialog customAlertDialog) {
                    mo6385a(customAlertDialog);
                }
            });
            MagicCrittercism.a(exception);
            ag();
            this.aB.show();
        }
    }

    private void m25056e(final boolean z) {
        this.al = new ParticleSystem(this.f23787P, 1000, getResources().getDrawable(C1947R.drawable.glow_particle_25_percent), 750);
        this.al.m17362a(0.05f, 0.3f);
        this.al.m17366a(new ScaleModifier(0.3f, 0.0f, 0, 750));
        this.am = ValueAnimator.ofInt(new int[]{0, this.f23793V.getMeasuredWidth()});
        this.am.setDuration(1600);
        this.am.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ PreSingVideoSelectionFragment f23762a;

            {
                this.f23762a = r1;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (this.f23762a.isAdded()) {
                    Random random = new Random();
                    this.f23762a.al.m17363a(0.001f, 0.01f, 0, 360);
                    this.f23762a.al.m17364a(5.0E-6f, random.nextInt(361));
                    this.f23762a.f23796Y.setX((float) ((Integer) valueAnimator.getAnimatedValue()).intValue());
                    this.f23762a.al.m17368a(this.f23762a.f23796Y, 5);
                }
            }
        });
        this.am.addListener(new AnimatorListener(this) {
            final /* synthetic */ PreSingVideoSelectionFragment f23767b;

            class C47951 implements Runnable {
                final /* synthetic */ AnonymousClass16 f23763a;

                C47951(AnonymousClass16 anonymousClass16) {
                    this.f23763a = anonymousClass16;
                }

                public void run() {
                    if (this.f23763a.f23767b.isAdded() && this.f23763a.f23767b.ah != null && this.f23763a.f23767b.ah.m26420a() != null) {
                        this.f23763a.f23767b.ah.m26424a(true);
                    }
                }
            }

            public void onAnimationStart(Animator animator) {
                this.f23767b.f23785N.setOnClickListener(null);
                if (z) {
                    this.f23767b.f23786O.setBackgroundResource(C1947R.drawable.airbrush_switch_off);
                }
                new UiHandler(this.f23767b.getActivity()).postDelayed(new C47951(this), 1040);
            }

            public void onAnimationEnd(Animator animator) {
                if (this.f23767b.isAdded()) {
                    this.f23767b.al.m17367a();
                    this.f23767b.f23796Y.setLayerType(0, null);
                    if (z) {
                        final Animation loadAnimation = AnimationUtils.loadAnimation(this.f23767b.getActivity(), C1947R.anim.pulse_airbrush);
                        loadAnimation.setAnimationListener(new AnimationListener(this) {
                            final /* synthetic */ AnonymousClass16 f23765b;

                            public void onAnimationStart(Animation animation) {
                            }

                            public void onAnimationEnd(Animation animation) {
                                if (this.f23765b.f23767b.isAdded()) {
                                    if (this.f23765b.f23767b.an == 0) {
                                        this.f23765b.f23767b.f23786O.setBackgroundResource(C1947R.drawable.airbrush_switch_on);
                                        this.f23765b.f23767b.f23786O.startAnimation(loadAnimation);
                                    } else if (this.f23765b.f23767b.an == 2) {
                                        this.f23765b.f23767b.f23785N.setOnClickListener(this.f23765b.f23767b.ap);
                                    }
                                    this.f23765b.f23767b.an = this.f23765b.f23767b.an + 1;
                                }
                            }

                            public void onAnimationRepeat(Animation animation) {
                            }
                        });
                        this.f23767b.f23786O.startAnimation(loadAnimation);
                        MagicPreferences.m20304a(this.f23767b.getActivity(), "AIRBRUSH_IS_FTUX", false);
                        return;
                    }
                    this.f23767b.f23785N.setOnClickListener(this.f23767b.ap);
                }
            }

            public void onAnimationCancel(Animator animator) {
                onAnimationEnd(animator);
            }

            public void onAnimationRepeat(Animator animator) {
            }
        });
        this.f23796Y.setLayerType(2, null);
        this.al.m17369a(this.f23796Y, 5, HttpResponseCode.MULTIPLE_CHOICES);
        this.am.start();
    }
}
