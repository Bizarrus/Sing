/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.os.Handler
 *  android.view.View
 *  android.widget.ImageView
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.PerformanceCreateUtil
 *  com.smule.singandroid.utils.PerformanceCreateUtil$Creator
 *  com.smule.singandroid.utils.PerformanceCreateUtil$PerformanceCreateListener
 *  com.smule.singandroid.utils.PerformanceCreateUtil$ResourceCompressionListener
 *  com.smule.singandroid.utils.PerformanceCreateUtil$ResourceUploadListener
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$AudioCompletionContext
 *  com.smule.singandroid.utils.SingAnalytics$ReviewStepsType
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.onboarding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.audio.AudioDefs;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.ImageUtils;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.DeviceSettings;
import com.smule.singandroid.MasterActivity_;
import com.smule.singandroid.PerformanceSaveActivity;
import com.smule.singandroid.PostSingBundle;
import com.smule.singandroid.SingBundle;
import com.smule.singandroid.TrialSubscriptionActivity;
import com.smule.singandroid.TrialSubscriptionActivity_;
import com.smule.singandroid.audio.Metadata;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.onboarding.OnboardingUploadFragment_;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceCreateUtil;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.ArrayList;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment
public class OnboardingUploadFragment
extends BaseFragment {
    public static final String g;
    public static final String h;
    @InstanceState
    protected PerformanceV2 A;
    @InstanceState
    protected String B;
    @InstanceState
    protected String C;
    @InstanceState
    protected String D;
    @InstanceState
    protected int E;
    @InstanceState
    protected String F;
    @InstanceState
    protected String G;
    @InstanceState
    protected String H;
    @InstanceState
    protected Float I;
    @InstanceState
    protected Float J;
    @InstanceState
    protected boolean K;
    @InstanceState
    protected String L;
    @InstanceState
    protected boolean M;
    @InstanceState
    protected int N;
    @InstanceState
    protected float O;
    @InstanceState
    protected Bundle P;
    @InstanceState
    protected SongbookEntry Q;
    @InstanceState
    protected String R;
    @InstanceState
    protected Integer S;
    protected SingBundle T;
    private TextAlertDialog U;
    private boolean V = false;
    private long W;
    private String X;
    private Handler Y = new Handler();
    private int Z = 150;
    private int aa = 1;
    private boolean ab = false;
    private boolean ac = false;
    private boolean ad = false;
    private Runnable ae;
    private PerformanceCreateUtil af;
    private Future<PerformanceManager.PreuploadResponse> ag;
    private boolean ah;
    private final PerformanceCreateUtil.ResourceCompressionListener ai;
    private final PerformanceCreateUtil.ResourceUploadListener aj;
    private Runnable ak;
    @ViewById
    protected View i;
    @ViewById
    protected TextView j;
    @ViewById
    protected ProgressBar k;
    @ViewById
    protected TextView l;
    @ViewById
    protected ImageView m;
    @ViewById
    protected TextView n;
    @ViewById
    protected TextView o;
    @ViewById
    protected TextView p;
    @ViewById
    protected View q;
    @ViewById
    protected View r;
    @InstanceState
    protected PostSingBundle s;
    @InstanceState
    protected String t;
    @InstanceState
    protected boolean u;
    @InstanceState
    protected Long v;
    @InstanceState
    protected boolean w;
    @InstanceState
    protected boolean x;
    @InstanceState
    protected boolean y;
    @InstanceState
    protected boolean z;

    static {
        h = OnboardingUploadFragment.g = OnboardingUploadFragment.class.getName();
    }

    public OnboardingUploadFragment() {
        this.ae = new Runnable(){

            @Override
            public void run() {
                OnboardingUploadFragment.this.Q();
            }
        };
        this.t = null;
        this.u = false;
        this.v = null;
        this.w = false;
        this.x = false;
        this.y = false;
        this.z = false;
        this.R = null;
        this.S = null;
        this.ai = new PerformanceCreateUtil.ResourceCompressionListener(){

            public void a() {
            }

            public void a(String string2) {
                OnboardingUploadFragment.this.R = string2;
                OnboardingUploadFragment.this.T();
            }

            public void b() {
                OnboardingUploadFragment.this.I();
            }

            public void c() {
                SingAnalytics.c((String)OnboardingUploadFragment.this.t, (String)OnboardingUploadFragment.this.W(), (String)"survey", (boolean)true);
                OnboardingUploadFragment.this.F();
            }
        };
        this.aj = new PerformanceCreateUtil.ResourceUploadListener(){

            public void a() {
                OnboardingUploadFragment.this.a(new Runnable(){

                    @Override
                    public void run() {
                        if (OnboardingUploadFragment.this.isAdded()) {
                            OnboardingUploadFragment.this.M();
                        }
                    }
                });
            }

            public void a(long l) {
                OnboardingUploadFragment.this.v = l;
                OnboardingUploadFragment.this.T();
            }

            public void b() {
                OnboardingUploadFragment.this.I();
            }

        };
        this.ak = new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                OnboardingUploadFragment.this.ad = true;
                int n = Math.round((float)(System.currentTimeMillis() - OnboardingUploadFragment.this.W) / 1000.0f);
                String string2 = PerformanceV2Util.e((PerformanceV2)OnboardingUploadFragment.this.A);
                Object object = OnboardingUploadFragment.this.T.o ? Analytics.c : Analytics.d;
                SingAnalytics.a((String)string2, object, (int)n, (long)OnboardingUploadFragment.this.af.a(), (String)OnboardingUploadFragment.this.W(), (Boolean)null, (boolean)false, (boolean)false);
                object = new DeviceSettings();
                SingAnalytics.a((int)OnboardingUploadFragment.this.T.t, (SingAnalytics.AudioCompletionContext)SingAnalytics.AudioCompletionContext.b, (Float)Float.valueOf(OnboardingUploadFragment.this.T.u), (String)null, (String)OnboardingUploadFragment.this.V(), (String)null, (String)OnboardingUploadFragment.this.F, (String)OnboardingUploadFragment.this.G, (String)OnboardingUploadFragment.this.H, (AudioDefs.MonitoringMode)object.o(), (int)object.i(), (Integer)OnboardingUploadFragment.this.S);
                OnboardingUploadFragment.this.Q();
            }
        };
    }

    private void Q() {
        this.L();
        this.Y.removeCallbacks(this.ae);
    }

    private String R() {
        if (this.Q != null) {
            return this.Q.e();
        }
        return "";
    }

    private void S() {
        if (this.w) {
            return;
        }
        this.V = true;
        this.W = System.currentTimeMillis();
        this.af.a(this.getActivity(), this.L, this.P, this.ai);
    }

    private void T() {
        Log.b(g, "prepareResourceDone");
        this.w = true;
        this.V = true;
        if (!(this.ad || this.U != null && this.U.isShowing())) {
            this.U();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void U() {
        PerformanceCreateUtil.PerformanceCreateListener performanceCreateListener = null;
        boolean bl = false;
        if (this.u) {
            return;
        }
        String string2 = this.R();
        String string3 = PerformanceV2Util.e((PerformanceV2)this.A);
        Object object = this.T.o ? Analytics.c : Analytics.d;
        AudioDefs.HeadphonesType headphonesType = AudioDefs.HeadphonesType.a(this.y, this.z);
        String string4 = this.B;
        Analytics ensemble = this.T.b.a();
        String string5 = this.W();
        Object object2 = this.T.f != null ? Boolean.valueOf(this.T.f.video) : null;
        SingAnalytics.a((String)string3, object, (AudioDefs.HeadphonesType)headphonesType, (String)string4, (boolean)false, ensemble, (String)string5, (Boolean)object2, (boolean)false);
        Log.b(g, "createPerformance - performance title is: " + string2);
        object = this.Q.t() ? this.Q.c() : null;
        if (this.T.x != null) {
            object2 = this.T.x;
        } else {
            MagicCrittercism.a(new Exception("noMetaDataFoundException2"));
            object2 = performanceCreateListener;
        }
        int n = this.Q.t() ? ((ArrangementVersionLiteEntry)this.Q).a.version : 0;
        performanceCreateListener = new PerformanceCreateUtil.PerformanceCreateListener(){

            public void a(NetworkResponse networkResponse) {
                OnboardingUploadFragment.this.K();
            }

            public void a(PerformanceV2 object, String string2, String string3) {
                OnboardingUploadFragment.this.V = false;
                OnboardingUploadFragment.this.A = object;
                OnboardingUploadFragment.this.X = string2;
                OnboardingUploadFragment.this.t = string3;
                if (OnboardingUploadFragment.this.X == null) {
                    Log.c(OnboardingUploadFragment.g, "performanceCreationDone - mSongUrl was null; setting to mPerformance.webUrl");
                    OnboardingUploadFragment.this.X = OnboardingUploadFragment.this.A.webUrl;
                }
                Log.b(OnboardingUploadFragment.g, "Performance creation completed!");
                object = new DeviceSettings();
                SingAnalytics.a((int)OnboardingUploadFragment.this.T.t, (SingAnalytics.AudioCompletionContext)SingAnalytics.AudioCompletionContext.c, (Float)Float.valueOf(OnboardingUploadFragment.this.T.u), (String)string3, (String)OnboardingUploadFragment.this.V(), (String)null, (String)OnboardingUploadFragment.this.F, (String)OnboardingUploadFragment.this.G, (String)OnboardingUploadFragment.this.H, (AudioDefs.MonitoringMode)object.o(), (int)object.i(), (Integer)OnboardingUploadFragment.this.S);
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        OnboardingUploadFragment.this.F();
                    }
                }, 200);
            }

            public void a(ArrayList<PerformanceManager.PerformanceResourceInfo> arrayList) {
                OnboardingUploadFragment.this.s.i = arrayList;
                OnboardingUploadFragment.this.V = true;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void b(NetworkResponse networkResponse) {
                if (networkResponse.e()) {
                    ((BaseActivity)OnboardingUploadFragment.this.getActivity()).a(networkResponse.f, false, OnboardingUploadFragment.this.ak);
                    OnboardingUploadFragment.this.O();
                } else {
                    OnboardingUploadFragment.this.J();
                }
                OnboardingUploadFragment.this.V = false;
            }

        };
        string3 = new PerformanceCreateUtil.Creator();
        object = string3.a(this.ag).a(this.getActivity()).a(this.T.e()).b(this.T.c()).c(this.T.d()).a(this.T.g).a((String)object).b(n).b(this.T.j).c(string2).c(this.N).a(this.B, this.E).a(this.I).b(this.J).a(this.O).d(this.x).e(this.y).d("").a((Metadata)object2).f(true).j(this.K).h(this.T.l()).k(this.z);
        boolean bl2 = bl;
        if (this.T.f != null) {
            bl2 = bl;
            if (this.T.f.boost) {
                bl2 = true;
            }
        }
        object.l(bl2).a(performanceCreateListener);
        string3.a(this.af);
    }

    private String V() {
        return "" + null + "," + this.C + "," + this.B;
    }

    private String W() {
        return SingAnalytics.d((PerformanceV2)this.A);
    }

    public static OnboardingUploadFragment a(PostSingBundle postSingBundle, Bundle bundle) {
        OnboardingUploadFragment_ onboardingUploadFragment_ = new OnboardingUploadFragment_();
        onboardingUploadFragment_.s = postSingBundle;
        onboardingUploadFragment_.setArguments(bundle);
        return onboardingUploadFragment_;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void c(String string2) {
        Log.b(g, "savePerformance - called from source: " + string2);
        if (this.w) {
            this.U();
            return;
        } else {
            if (this.V) return;
            {
                this.S();
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @UiThread
    protected void F() {
        block3 : {
            block2 : {
                if (!this.isAdded()) break block2;
                this.u = true;
                if (this.ah) break block3;
            }
            return;
        }
        this.G();
    }

    @UiThread
    protected void G() {
        this.N();
        this.Y.post(this.ae);
    }

    @UiThread
    protected void H() {
        if (!this.isAdded()) {
            return;
        }
        SingAnalytics.a((String)PerformanceV2Util.e((PerformanceV2)this.A), Analytics.c, (AudioDefs.HeadphonesType)AudioDefs.HeadphonesType.a(this.y, this.z), (String)this.B, (boolean)false, this.T.b.a(), (SingAnalytics.ReviewStepsType)SingAnalytics.ReviewStepsType.c, (String)this.W(), (Boolean)null, (boolean)false);
        this.U = new TextAlertDialog((Context)this.getActivity(), this.getString(2131296667), this.getString(2131297073));
        this.U.a(this.getString(2131296733), this.getString(2131296701));
        this.U.a(new CustomAlertDialog.CustomAlertDialogListener(){

            @Override
            public void a(CustomAlertDialog customAlertDialog) {
                OnboardingUploadFragment.this.ak.run();
            }

            @Override
            public void b(CustomAlertDialog customAlertDialog) {
                OnboardingUploadFragment.this.c("showProgressBarDialog - onBackOrCancelButton");
            }
        });
        this.U.show();
    }

    @UiThread
    protected void I() {
        if (!this.isAdded()) {
            Log.d(g, "showFailUploadDialog - not added to fragment; aborting");
            return;
        }
        TextAlertDialog textAlertDialog = new TextAlertDialog((Context)this.getActivity(), this.getString(2131297110));
        textAlertDialog.a(new Runnable(){

            @Override
            public void run() {
                OnboardingUploadFragment.this.S();
            }
        });
        textAlertDialog.b(new Runnable(){

            @Override
            public void run() {
                OnboardingUploadFragment.this.H();
            }
        });
        this.O();
        textAlertDialog.show();
    }

    @UiThread
    protected void J() {
        if (!this.isAdded()) {
            Log.d(g, "showFailCreateDialog - not added to fragment; aborting");
            return;
        }
        this.V = false;
        TextAlertDialog textAlertDialog = new TextAlertDialog((Context)this.getActivity(), this.getString(2131297078));
        textAlertDialog.a(new Runnable(){

            @Override
            public void run() {
                if (OnboardingUploadFragment.this.ac) {
                    OnboardingUploadFragment.this.M();
                }
                OnboardingUploadFragment.this.U();
            }
        });
        textAlertDialog.b(new Runnable(){

            @Override
            public void run() {
                OnboardingUploadFragment.this.H();
            }
        });
        this.O();
        textAlertDialog.show();
    }

    @UiThread
    protected void K() {
        if (!this.isAdded()) {
            Log.d(g, "showFailPreuploadDialog - not added to fragment; aborting");
            return;
        }
        this.V = false;
        TextAlertDialog textAlertDialog = new TextAlertDialog((Context)this.getActivity(), this.getString(2131297078));
        textAlertDialog.a(new Runnable(){

            @Override
            public void run() {
                if (OnboardingUploadFragment.this.ac) {
                    OnboardingUploadFragment.this.M();
                }
                ((PerformanceSaveActivity)OnboardingUploadFragment.this.getActivity()).a();
                OnboardingUploadFragment.this.U();
            }
        });
        textAlertDialog.b(new Runnable(){

            @Override
            public void run() {
                OnboardingUploadFragment.this.ak.run();
            }
        });
        this.O();
        textAlertDialog.show();
    }

    /*
     * Enabled aggressive block sorting
     */
    @UiThread
    protected void L() {
        Log.b(g, "finishFragmentAndActivity");
        this.b(this);
        Intent intent = TrialSubscriptionActivity.a((Context)this.getActivity()) ? new Intent((Context)this.getActivity(), TrialSubscriptionActivity_.class) : new Intent((Context)this.getActivity(), MasterActivity_.class);
        this.startActivity(intent);
        this.getActivity().finish();
    }

    public void M() {
        if (this.k.getVisibility() != 0) {
            return;
        }
        this.P();
        Runnable runnable = new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                int n = 0;
                if (!OnboardingUploadFragment.this.isAdded()) return;
                if (!OnboardingUploadFragment.this.ac) {
                    if (OnboardingUploadFragment.this.k.getMax() <= OnboardingUploadFragment.this.k.getProgress()) {
                        OnboardingUploadFragment.this.ab = false;
                        return;
                    }
                    int n2 = OnboardingUploadFragment.this.k.getMax();
                    int n3 = OnboardingUploadFragment.this.k.getProgress();
                    if (OnboardingUploadFragment.this.ab) {
                        n2 = OnboardingUploadFragment.this.aa;
                        n = 5;
                    } else if (n2 - n3 <= 20) {
                        n2 = OnboardingUploadFragment.this.Z;
                    } else {
                        n2 = OnboardingUploadFragment.this.Z;
                        n = 1;
                    }
                    OnboardingUploadFragment.this.k.setProgress(n + OnboardingUploadFragment.this.k.getProgress());
                    OnboardingUploadFragment.this.Y.postDelayed((Runnable)this, (long)n2);
                }
            }
        };
        this.Y.post(runnable);
    }

    public void N() {
        this.ab = true;
    }

    public void O() {
        this.ac = true;
    }

    public void P() {
        this.k.setProgress(0);
        this.ab = false;
        this.ac = false;
    }

    /*
     * Enabled aggressive block sorting
     */
    @AfterViews
    protected void a() {
        block5 : {
            block4 : {
                if (this.getActivity().isFinishing()) break block4;
                int n = this.T.b("BACKGROUND_RESOURCE_KEY", 2130837623);
                this.r.setBackground(this.getResources().getDrawable(n));
                this.i.setVisibility(0);
                this.k.setVisibility(8);
                this.j.setText((CharSequence)this.Q.e());
                ImageUtils.a(this.Q.j(), this.m);
                if (!this.w) {
                    this.S();
                }
                if (this.u) break block5;
            }
            return;
        }
        this.G();
    }

    public void a(Future<PerformanceManager.PreuploadResponse> future) {
        this.ag = future;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean d() {
        if (this.u) {
            this.Q();
            do {
                return true;
                break;
            } while (true);
        }
        this.H();
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.T = this.s.b;
        this.af = new PerformanceCreateUtil(this.v, this.R);
        if (bundle == null) {
            Log.b(g, "onCreate - no saved instance state");
            bundle = this.getArguments();
            this.L = bundle.getString("RECORDING_FILE_EXTRA_KEY");
            this.M = bundle.getBoolean("PITCH_CORRECT_EXTRA_KEY", false);
            this.N = bundle.getInt("SCORE_EXTRA_KEY", 0);
            this.O = bundle.getFloat("USER_GAIN_DB", 1.0f);
            this.y = bundle.getBoolean("HEADTHING_ONLY", false);
            this.z = bundle.getBoolean("HEADPHONE_HAD_MIC", false);
            this.P = bundle;
        } else {
            Log.b(g, "onCreate - restoring from saved instance state");
        }
        Log.b(g, this.T.toString());
        this.Q = this.T.d;
        this.B = this.getArguments().getString("EFFECT_PRESET");
        this.C = this.getArguments().getString("FX_INITIAL");
        this.D = this.getArguments().getString("FX_SELECTED");
        this.E = this.getArguments().getInt("FX_SELECTED_VERSION", 0);
        this.F = this.getArguments().getString("FXS_UNIQUE_REVIEW");
        this.G = this.getArguments().getString("ADJUSTED_SLIDER");
        this.H = this.getArguments().getString("PLAY_PAUSE_COUNT");
        if (this.B == null || this.B.isEmpty()) {
            this.B = "****";
        }
        this.I = Float.valueOf(this.getArguments().getFloat("META_PARAM_1", -1.0f));
        this.J = Float.valueOf(this.getArguments().getFloat("META_PARAM_2", -1.0f));
        if (this.I.floatValue() == -1.0f) {
            this.I = null;
        }
        if (this.J.floatValue() == -1.0f) {
            this.J = null;
        }
        try {
            this.S = (Integer)this.getArguments().get("ESTIMATED_LATENCY_MS");
        }
        catch (ClassCastException classCastException) {
            Log.d(g, "I tried to get ESTIMATED_LATENCY_MS from arguments, but it wasn't an Integer", classCastException);
            this.S = null;
        }
        this.K = this.getArguments().getBoolean("PRESET_VIP_EXTRA_KEY");
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void t() {
        if (this.ah) {
            return;
        }
        MiscUtils.a((View)this.m, (float)0.5f, (boolean)true, (boolean)true, (boolean)false, (boolean)false, (long)0, (long)500, (float)1.2f, (Runnable)null);
        MiscUtils.a((View)this.q, (float)0.0f, (boolean)false, (boolean)false, (boolean)true, (boolean)false, (long)100, (long)1500, (float)1.2f, (Runnable)null);
        MiscUtils.a((View)this.n, (float)1.0f, (boolean)true, (boolean)true, (boolean)true, (boolean)true, (long)100, (long)1000, (float)1.2f, (Runnable)null);
        MiscUtils.a((View)this.o, (float)1.0f, (boolean)true, (boolean)true, (boolean)true, (boolean)true, (long)200, (long)1000, (float)1.2f, (Runnable)null);
        MiscUtils.a((View)this.p, (float)0.0f, (boolean)false, (boolean)false, (boolean)true, (boolean)true, (long)1250, (long)600, (float)1.2f, (Runnable)new Runnable(){

            @Override
            public void run() {
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        OnboardingUploadFragment.this.ah = true;
                        if (OnboardingUploadFragment.this.isAdded() && OnboardingUploadFragment.this.u) {
                            OnboardingUploadFragment.this.G();
                        }
                    }
                }, 3000);
            }

        });
    }

    @Override
    public String x() {
        return g;
    }

}

