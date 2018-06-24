/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnShowListener
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$Config
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Rect
 *  android.graphics.Typeface
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Looper
 *  android.text.TextUtils
 *  android.view.TextureView
 *  android.view.View
 *  android.view.animation.AlphaAnimation
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  android.view.animation.AnticipateOvershootInterpolator
 *  android.view.animation.Interpolator
 *  android.view.animation.OvershootInterpolator
 *  android.view.animation.TranslateAnimation
 *  android.widget.Button
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  com.nostra13.universalimageloader.core.DisplayImageOptions
 *  com.nostra13.universalimageloader.core.DisplayImageOptions$Builder
 *  com.nostra13.universalimageloader.core.ImageLoader
 *  com.nostra13.universalimageloader.core.assist.FailReason
 *  com.nostra13.universalimageloader.core.listener.ImageLoadingListener
 *  com.smule.singandroid.utils.CustomTypefaceSpan
 *  com.smule.singandroid.utils.ImageToDiskUtils
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
 *  com.smule.singandroid.utils.TypefaceUtils
 *  com.smule.singandroid.utils.UIHelper
 *  com.smule.singandroid.video.ExoPlayerWrapper
 *  com.smule.singandroid.video.ExoPlayerWrapper$ExoPlayerInternalErrorListener
 *  com.smule.singandroid.video.ExoPlayerWrapper$ExoPlayerStateChangeListener
 *  com.smule.singandroid.video.ExoPlayerWrapperBuilder
 *  com.smule.singandroid.video.GetAudioTimeCallback
 *  com.smule.singandroid.video.VideoEffects
 *  com.smule.singandroid.video.VideoEffects$Intensity
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.smule.android.audio.AudioDefs;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.Arrangement;
import com.smule.android.network.models.ArrangementVersion;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.DeviceSettings;
import com.smule.singandroid.DuetJoinSaveFragment_;
import com.smule.singandroid.PerformanceSaveActivity;
import com.smule.singandroid.PostSingBundle;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingBundle;
import com.smule.singandroid.audio.AudioPower;
import com.smule.singandroid.audio.Metadata;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.DeleteRecordingConfirmationDialog;
import com.smule.singandroid.dialogs.ProgressBarDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.utils.CustomTypefaceSpan;
import com.smule.singandroid.utils.ImageToDiskUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceCreateUtil;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.TypefaceUtils;
import com.smule.singandroid.utils.UIHelper;
import com.smule.singandroid.video.ExoPlayerWrapper;
import com.smule.singandroid.video.ExoPlayerWrapperBuilder;
import com.smule.singandroid.video.GetAudioTimeCallback;
import com.smule.singandroid.video.VideoEffects;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment
public class DuetJoinSaveFragment
extends BaseFragment {
    private static final String ab;
    public static final String g;
    @InstanceState
    protected Long A;
    @InstanceState
    protected boolean B;
    @InstanceState
    protected boolean C;
    @InstanceState
    protected boolean D;
    @InstanceState
    protected PerformanceV2 E;
    @InstanceState
    protected String F;
    @InstanceState
    protected String G;
    @InstanceState
    protected String H;
    @InstanceState
    protected int I;
    @InstanceState
    protected String J;
    @InstanceState
    protected String K;
    @InstanceState
    protected String L;
    @InstanceState
    protected Float M;
    @InstanceState
    protected Float N;
    @InstanceState
    protected boolean O;
    @InstanceState
    protected String P;
    @InstanceState
    protected int Q;
    @InstanceState
    protected float R;
    @InstanceState
    protected boolean S;
    @InstanceState
    protected boolean T;
    @InstanceState
    protected String U;
    @InstanceState
    protected Bundle V;
    @InstanceState
    protected boolean W;
    @InstanceState
    protected float X;
    @InstanceState
    protected String Y;
    @InstanceState
    protected Integer Z;
    private ProgressBarDialog aA;
    private final Runnable aB;
    private final PerformanceCreateUtil.ResourceCompressionListener aC;
    private final PerformanceCreateUtil.ResourceUploadListener aD;
    boolean aa;
    private TextAlertDialog ac;
    private TextAlertDialog ad;
    private boolean ae = false;
    private boolean af = false;
    private long ag;
    private boolean ah;
    private Handler ai = new Handler();
    private boolean aj = false;
    private boolean ak = false;
    private boolean al = false;
    private Runnable am;
    private PerformanceCreateUtil an;
    private ExoPlayerWrapper ao;
    private Runnable ap;
    private long aq;
    private boolean ar;
    private boolean as;
    private Bitmap at;
    private Bitmap au;
    private Bitmap av;
    private Bitmap aw;
    private boolean ax;
    private Future<PerformanceManager.PreuploadResponse> ay;
    private boolean az;
    @ViewById
    protected TextView h;
    @ViewById
    protected ProgressBar i;
    @ViewById
    protected TextView j;
    @ViewById
    protected ProfileImageWithVIPBadge k;
    @ViewById
    protected ProfileImageWithVIPBadge l;
    @ViewById
    protected ProfileImageWithVIPBadge m;
    @ViewById
    protected View n;
    @ViewById
    protected TextView o;
    @ViewById
    protected View p;
    @ViewById
    protected Button q;
    @ViewById
    protected Button r;
    @ViewById
    protected TextureView s;
    @ViewById
    protected Button t;
    @ViewById
    protected View u;
    @InstanceState
    protected PostSingBundle v;
    protected SingBundle w;
    @InstanceState
    protected String x;
    @InstanceState
    protected boolean y;
    @InstanceState
    protected boolean z;

    static {
        g = DuetJoinSaveFragment.ab = DuetJoinSaveFragment.class.getName();
    }

    public DuetJoinSaveFragment() {
        this.am = new Runnable(){

            @Override
            public void run() {
                DuetJoinSaveFragment.this.d(true);
            }
        };
        this.x = null;
        this.y = false;
        this.z = false;
        this.A = null;
        this.B = false;
        this.C = false;
        this.D = false;
        this.S = false;
        this.T = false;
        this.Y = null;
        this.Z = null;
        this.aB = new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                DuetJoinSaveFragment.this.al = true;
                int n = Math.round((float)(System.currentTimeMillis() - DuetJoinSaveFragment.this.ag) / 1000.0f);
                String string2 = PerformanceV2Util.e((PerformanceV2)DuetJoinSaveFragment.this.E);
                Object object = DuetJoinSaveFragment.this.w.o ? Analytics.c : Analytics.d;
                long l = DuetJoinSaveFragment.this.an.a();
                String string3 = DuetJoinSaveFragment.this.Y();
                Boolean bl = DuetJoinSaveFragment.this.w != null && DuetJoinSaveFragment.this.w.f != null ? Boolean.valueOf(DuetJoinSaveFragment.this.w.f.video) : null;
                SingAnalytics.a((String)string2, object, (int)n, (long)l, (String)string3, (Boolean)bl, (boolean)DuetJoinSaveFragment.this.ag(), (boolean)false);
                object = new DeviceSettings();
                SingAnalytics.a((int)DuetJoinSaveFragment.this.w.t, (SingAnalytics.AudioCompletionContext)SingAnalytics.AudioCompletionContext.b, (Float)Float.valueOf(DuetJoinSaveFragment.this.w.u), (String)null, (String)DuetJoinSaveFragment.this.ad(), (String)null, (String)DuetJoinSaveFragment.this.J, (String)DuetJoinSaveFragment.this.K, (String)DuetJoinSaveFragment.this.L, (AudioDefs.MonitoringMode)object.o(), (int)object.i(), (Integer)DuetJoinSaveFragment.this.Z);
                ((PerformanceSaveActivity)DuetJoinSaveFragment.this.getActivity()).a(DuetJoinSaveFragment.this.v, null, false);
            }
        };
        this.aC = new PerformanceCreateUtil.ResourceCompressionListener(){

            public void a() {
            }

            public void a(String string2) {
                DuetJoinSaveFragment.this.Y = string2;
                DuetJoinSaveFragment.this.ab();
            }

            public void b() {
                DuetJoinSaveFragment.this.R();
            }

            public void c() {
                SingAnalytics.c((String)DuetJoinSaveFragment.this.x, (String)DuetJoinSaveFragment.this.Y(), (String)"performance", (boolean)false);
                DuetJoinSaveFragment.this.aB.run();
            }
        };
        this.aD = new PerformanceCreateUtil.ResourceUploadListener(){

            public void a() {
                DuetJoinSaveFragment.this.a(new Runnable(){

                    @Override
                    public void run() {
                        if (DuetJoinSaveFragment.this.isAdded()) {
                            DuetJoinSaveFragment.this.T();
                        }
                    }
                });
            }

            public void a(long l) {
                DuetJoinSaveFragment.this.A = l;
                DuetJoinSaveFragment.this.ab();
            }

            public void b() {
                DuetJoinSaveFragment.this.R();
            }

        };
    }

    private String Y() {
        return SingAnalytics.d((PerformanceV2)this.E);
    }

    private String Z() {
        if (this.E != null) {
            return this.E.arrangementVersion.arrangement.name;
        }
        return "";
    }

    public static DuetJoinSaveFragment a(PostSingBundle postSingBundle, Bundle bundle) {
        DuetJoinSaveFragment_ duetJoinSaveFragment_ = new DuetJoinSaveFragment_();
        duetJoinSaveFragment_.v = postSingBundle;
        duetJoinSaveFragment_.setArguments(bundle);
        return duetJoinSaveFragment_;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(final float f) {
        String string2;
        float f2;
        block6 : {
            block5 : {
                int n;
                if (!TextUtils.isEmpty((CharSequence)this.E.joinVideoUrl)) {
                    string2 = this.E.joinVideoUrl;
                } else {
                    if (TextUtils.isEmpty((CharSequence)this.E.origTrackVideoUrl)) {
                        Log.b(ab, "unable to find seed");
                        return;
                    }
                    string2 = this.E.origTrackVideoUrl;
                }
                if (this.w.i == null) break block5;
                try {
                    n = Metadata.c((File)new File((String)this.w.i)).userDelayCalibrationMs;
                }
                catch (IOException iOException) {
                    Log.d(ab, "Failed to read metadata from " + this.w.i, iOException);
                }
                f2 = (float)n / 1000.0f;
                break block6;
            }
            f2 = 0.0f;
        }
        Log.b(ab, "seed frame pos:" + (f += f2));
        this.ar = false;
        this.as = false;
        this.ao = new ExoPlayerWrapper(new ExoPlayerWrapperBuilder((Context)this.getActivity(), this.s, new Handler(Looper.getMainLooper()), string2, new GetAudioTimeCallback(){

            public float a() {
                return 0.0f;
            }
        }, Float.MAX_VALUE, 0.0f, null, new ExoPlayerWrapper.ExoPlayerStateChangeListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void a(int n) {
                boolean bl;
                block6 : {
                    block5 : {
                        bl = true;
                        if (!DuetJoinSaveFragment.this.isAdded() || n != 4) break block5;
                        if (!DuetJoinSaveFragment.this.ar) {
                            DuetJoinSaveFragment.this.ar = true;
                            DuetJoinSaveFragment.this.ao.c();
                            DuetJoinSaveFragment.this.ao.b(f);
                            return;
                        }
                        if (DuetJoinSaveFragment.this.as) break block5;
                        DuetJoinSaveFragment.this.at = DuetJoinSaveFragment.this.s.getBitmap(400, 400);
                        if (DuetJoinSaveFragment.this.av != null) break block6;
                    }
                    return;
                }
                DuetJoinSaveFragment duetJoinSaveFragment = DuetJoinSaveFragment.this;
                Bitmap bitmap = DuetJoinSaveFragment.this.av;
                Bitmap bitmap2 = DuetJoinSaveFragment.this.at;
                boolean bl2 = DuetJoinSaveFragment.this.w.g != 1;
                duetJoinSaveFragment.aw = DuetJoinSaveFragment.b(bitmap, bitmap2, bl2);
                duetJoinSaveFragment = DuetJoinSaveFragment.this;
                bl2 = DuetJoinSaveFragment.this.aw != null ? bl : false;
                duetJoinSaveFragment.as = bl2;
                if (DuetJoinSaveFragment.this.as) {
                    ImageToDiskUtils.b((Context)DuetJoinSaveFragment.this.getActivity(), (String)"duetjoinerthumb");
                    ImageToDiskUtils.a((Context)DuetJoinSaveFragment.this.getActivity(), (String)"duetjoincompositebitmap", (Bitmap)DuetJoinSaveFragment.this.aw);
                }
                DuetJoinSaveFragment.this.c(DuetJoinSaveFragment.this.ap);
                DuetJoinSaveFragment.this.ai.post(DuetJoinSaveFragment.this.ap);
            }
        }));
    }

    private void aa() {
        if (this.z) {
            return;
        }
        this.af = true;
        this.ag = System.currentTimeMillis();
        this.an.a(this.getActivity(), this.P, this.V, this.aC);
    }

    private void ab() {
        Log.b(ab, "prepareResourceDone");
        this.z = true;
        this.af = true;
        if (!(this.al || this.ac != null && this.ac.isShowing() || this.getActivity() == null)) {
            this.ac();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void ac() {
        Object object;
        Object object2;
        int n = 0;
        String string2 = null;
        this.c(this.ap);
        if (this.y || this.ae) {
            return;
        }
        this.M();
        if (this.W && this.w.b == SingBundle.PerformanceType.c && this.aw == null) {
            if (System.currentTimeMillis() - this.aq < 3000) {
                Log.b(ab, "wait for composite to complete");
                this.a(this.ap, 3000);
                return;
            }
            object2 = this.av;
            object = this.au;
            boolean bl = this.w.g != 1;
            this.aw = DuetJoinSaveFragment.b((Bitmap)object2, (Bitmap)object, bl);
        }
        Log.b(ab, "mCompsiteBitmap:" + (Object)this.aw);
        this.ae = true;
        String string3 = this.Z();
        String string4 = PerformanceV2Util.e((PerformanceV2)this.E);
        object2 = this.w.o ? Analytics.c : Analytics.d;
        Object object3 = AudioDefs.HeadphonesType.a(this.C, this.D);
        Object object4 = this.F;
        Analytics ensemble = this.w.b.a();
        String string5 = this.Y();
        object = this.w.f != null ? Boolean.valueOf(this.w.f.video) : null;
        SingAnalytics.a((String)string4, object2, (AudioDefs.HeadphonesType)object3, (String)object4, (boolean)true, ensemble, (String)string5, (Boolean)object, (boolean)this.ag());
        Log.b(ab, "createPerformance - performance title is: " + string3);
        object2 = this.E.arrangementVersion != null ? this.E.arrangementVersion.arrangement.key : null;
        if (this.E.arrangementVersion != null) {
            n = this.E.arrangementVersion.version;
        }
        if (this.w.x != null) {
            if (!this.W) {
                this.w.x.audioPower = null;
            }
            object = this.w.x;
        } else {
            MagicCrittercism.a(new Exception("noMetaDataFoundException"));
            object = null;
        }
        if (this.w.f.video) {
            object3 = this.w.h();
            string4 = this.w.i();
            string2 = this.w.j().b();
        } else {
            string4 = null;
            object3 = null;
        }
        object4 = new PerformanceCreateUtil.PerformanceCreateListener(){

            public void a(com.smule.android.network.core.NetworkResponse networkResponse) {
                DuetJoinSaveFragment.this.S();
            }

            public void a(PerformanceV2 object, String string2, String string3) {
                DuetJoinSaveFragment.this.af = false;
                DuetJoinSaveFragment.this.ae = false;
                DuetJoinSaveFragment.this.x = string3;
                DuetJoinSaveFragment.this.E = object;
                DuetJoinSaveFragment.this.y = true;
                Log.b(ab, "Performance creation completed!");
                object = new DeviceSettings();
                SingAnalytics.a((int)DuetJoinSaveFragment.this.w.t, (SingAnalytics.AudioCompletionContext)SingAnalytics.AudioCompletionContext.c, (Float)Float.valueOf(DuetJoinSaveFragment.this.w.u), (String)string3, (String)DuetJoinSaveFragment.this.ad(), (String)null, (String)DuetJoinSaveFragment.this.J, (String)DuetJoinSaveFragment.this.K, (String)DuetJoinSaveFragment.this.L, (AudioDefs.MonitoringMode)object.o(), (int)object.i(), (Integer)DuetJoinSaveFragment.this.Z);
                if (DuetJoinSaveFragment.this.W) {
                    SingAnalytics.a((String)DuetJoinSaveFragment.this.x, (float)DuetJoinSaveFragment.this.w.b("VIDEO_STATS_CAMERA_FPS", 0.0f), (float)DuetJoinSaveFragment.this.w.b("VIDEO_STATS_ENCODER_FPS", 0.0f), (int)DuetJoinSaveFragment.this.w.b("VIDEO_STATS_CAMERA_TO_ENCODER_DROPS", 0), (float)DuetJoinSaveFragment.this.w.b("VIDEO_STATS_MUXER_FPS", 0.0f), (int)DuetJoinSaveFragment.this.w.b("VIDEO_STATS_ENCODER_TO_MUXER_DROPS", 0));
                }
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        DuetJoinSaveFragment.this.N();
                    }
                }, 200);
            }

            public void a(ArrayList<PerformanceManager.PerformanceResourceInfo> arrayList) {
                DuetJoinSaveFragment.this.v.i = arrayList;
                DuetJoinSaveFragment.this.af = true;
            }

            public void b(com.smule.android.network.core.NetworkResponse networkResponse) {
                DuetJoinSaveFragment.this.a(networkResponse);
            }

        };
        ensemble = new PerformanceCreateUtil.Creator();
        ensemble.a(this.ay).a(this.getActivity()).a(this.w.e()).b(this.w.c()).c(this.w.d()).a(this.w.g).a((String)object2).b(n).b(this.w.j).c(string3).c(this.Q).a(this.F, this.I).a(this.M).b(this.N).a(this.R).d(this.B).e(this.C).d("").a(this.aw).e(this.U).a((Metadata)object).f(this.w.o).f((String)object3).g(string4).h(string2).g(this.w.k()).h(this.w.l()).i(this.w.m()).j(this.O).k(this.D).l(this.w.f.boost).m(this.w.b()).a((PerformanceCreateUtil.PerformanceCreateListener)object4);
        ensemble.a(this.an);
    }

    private String ad() {
        return "" + null + "," + this.G + "," + this.F;
    }

    private void ae() {
        this.aq = System.currentTimeMillis();
        Log.b(ab, "mJoinMaxPowerPositionSeconds:" + this.X);
        this.a(this.X);
        this.af();
    }

    private void af() {
        if (TextUtils.isEmpty((CharSequence)this.E.coverUrl)) {
            Log.b(ab, "seed cover art not found.");
            return;
        }
        ImageLoader.a().a(this.E.coverUrl, new DisplayImageOptions.Builder().a(true).b(true).a(), new ImageLoadingListener(){

            public void a(String string2, View view) {
            }

            public void a(String string2, View view, Bitmap bitmap) {
                Log.b(ab, "seed covert art loaded");
                DuetJoinSaveFragment.this.au = bitmap;
            }

            public void a(String string2, View view, FailReason failReason) {
            }

            public void b(String string2, View view) {
            }
        });
    }

    private boolean ag() {
        if (SingApplication.m() && this.W) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static Bitmap b(Bitmap bitmap, Bitmap bitmap2, boolean bl) {
        Bitmap bitmap3;
        if (bitmap == null || bitmap2 == null) {
            Log.e(ab, "bitmaps not ready");
            return null;
        }
        if (!bl) {
            bitmap3 = bitmap;
            bitmap = bitmap2;
            bitmap2 = bitmap3;
        }
        bitmap3 = Bitmap.createBitmap((int)400, (int)400, (Bitmap.Config)Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap3);
        int n = bitmap2.getWidth();
        int n2 = n / 4;
        int n3 = n / 4;
        Rect rect = new Rect(n2, 0, n / 2 + n3, bitmap2.getHeight());
        Rect rect2 = new Rect(0, 0, 200, 400);
        canvas.drawBitmap(bitmap2, rect, rect2, null);
        n = bitmap.getWidth();
        rect.left = n / 4;
        n2 = n / 4;
        rect.right = n / 2 + n2;
        rect.bottom = bitmap.getHeight();
        rect2.left = 200;
        rect2.right = 400;
        canvas.drawBitmap(bitmap, rect, rect2, null);
        return bitmap3;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void c(String string2) {
        Log.b(ab, "savePerformance - called from source: " + string2);
        if (this.z) {
            this.ac();
        } else if (!this.af) {
            this.aa();
        }
        if (this.az) {
            this.M();
        }
    }

    private void d(boolean bl) {
        if (!this.isAdded() || this.getActivity() == null) {
            return;
        }
        if (!this.y || !this.az) {
            this.M();
            return;
        }
        this.ai.removeCallbacks(this.am);
        ((PerformanceSaveActivity)this.getActivity()).a(this.v, this.E, bl);
    }

    protected void F() {
        MiscUtils.a((View)this.n, (float)0.5f, (boolean)true, (boolean)true, (boolean)false, (boolean)false, (long)0, (long)500, (float)1.2f, (Runnable)null);
        MiscUtils.a((View)this.j, (float)1.0f, (boolean)true, (boolean)true, (boolean)true, (boolean)true, (long)350, (long)1000, (float)1.2f, (Runnable)null);
        MiscUtils.a((View)this.r, (float)0.0f, (boolean)false, (boolean)false, (boolean)true, (boolean)false, (long)1950, (long)600, (float)1.2f, (Runnable)null);
        MiscUtils.a((View)this.o, (float)0.0f, (boolean)false, (boolean)false, (boolean)true, (boolean)true, (long)1950, (long)600, (float)1.2f, (Runnable)null, (boolean)true, (int)0);
        MiscUtils.a((View)this.t, (float)1.0f, (boolean)false, (boolean)false, (boolean)true, (boolean)true, (long)3550, (long)600, (float)1.2f, (Runnable)new Runnable(){

            @Override
            public void run() {
                if (DuetJoinSaveFragment.this.aa) {
                    return;
                }
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        DuetJoinSaveFragment.this.az = true;
                        DuetJoinSaveFragment.this.d(true);
                    }
                }, 2000);
            }

        }, (boolean)true, (int)10);
    }

    protected void G() {
        MiscUtils.a((View)this.n, (float)0.5f, (boolean)true, (boolean)true, (boolean)false, (boolean)false, (long)0, (long)500, (float)1.2f, (Runnable)null);
        MiscUtils.a((View)this.j, (float)1.0f, (boolean)true, (boolean)true, (boolean)true, (boolean)true, (long)350, (long)1000, (float)1.2f, (Runnable)null);
        MiscUtils.a((View)this.r, (float)0.0f, (boolean)false, (boolean)false, (boolean)true, (boolean)false, (long)1950, (long)600, (float)1.2f, (Runnable)null);
        MiscUtils.a((View)this.o, (float)0.0f, (boolean)false, (boolean)false, (boolean)true, (boolean)true, (long)1950, (long)600, (float)1.2f, (Runnable)null, (boolean)true, (int)0);
        MiscUtils.a((View)this.t, (float)1.0f, (boolean)false, (boolean)false, (boolean)true, (boolean)true, (long)3550, (long)600, (float)1.2f, (Runnable)new Runnable(){

            @Override
            public void run() {
                if (DuetJoinSaveFragment.this.aa) {
                    return;
                }
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        DuetJoinSaveFragment.this.az = true;
                    }
                }, 2000);
            }

        }, (boolean)true, (int)10);
    }

    protected void H() {
        final TranslateAnimation translateAnimation = new TranslateAnimation(2, -1.0f, 0, 0.0f, 1, 0.0f, 1, 0.0f);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator((Interpolator)new AnticipateOvershootInterpolator(0.7f));
        translateAnimation.setAnimationListener(new Animation.AnimationListener(){

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                DuetJoinSaveFragment.this.n.setAlpha(1.0f);
            }
        });
        final TranslateAnimation translateAnimation2 = new TranslateAnimation(2, 1.0f, 0, 0.0f, 1, 0.0f, 1, 0.0f);
        translateAnimation2.setDuration(1000);
        translateAnimation2.setFillAfter(true);
        translateAnimation2.setInterpolator((Interpolator)new AnticipateOvershootInterpolator(0.7f));
        new Handler().post(new Runnable(){

            @Override
            public void run() {
                DuetJoinSaveFragment.this.k.startAnimation((Animation)translateAnimation);
                DuetJoinSaveFragment.this.l.startAnimation((Animation)translateAnimation2);
            }
        });
        MiscUtils.a((View)this.j, (float)1.0f, (boolean)true, (boolean)true, (boolean)true, (boolean)true, (long)500, (long)600, (float)1.2f, (Runnable)null);
        MiscUtils.a((View)this.o, (float)0.0f, (boolean)false, (boolean)false, (boolean)true, (boolean)true, (long)1200, (long)1000, (float)1.2f, (Runnable)new Runnable(){

            @Override
            public void run() {
                if (DuetJoinSaveFragment.this.aa) {
                    return;
                }
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        DuetJoinSaveFragment.this.az = true;
                        DuetJoinSaveFragment.this.d(true);
                    }
                }, 2000);
            }

        }, (boolean)true, (int)0);
    }

    @Click
    protected void I() {
        this.d(true);
    }

    @UiThread
    protected void J() {
        if (!this.isAdded() || this.getActivity() == null) {
            return;
        }
        this.aa = true;
        this.t.clearAnimation();
        this.t.setVisibility(8);
        this.j.clearAnimation();
        this.r.clearAnimation();
        this.o.clearAnimation();
        this.p.clearAnimation();
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(100);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener(){

            public void onAnimationEnd(Animation animation) {
                DuetJoinSaveFragment.this.j.setText((CharSequence)(DuetJoinSaveFragment.this.getResources().getString(2131296750) + "\n"));
                MiscUtils.a((View)DuetJoinSaveFragment.this.j, (float)1.0f, (boolean)true, (boolean)true, (boolean)true, (boolean)true, (long)100, (long)500, (float)1.2f, (Runnable)null);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        this.j.startAnimation((Animation)alphaAnimation);
        alphaAnimation = new CustomTypefaceSpan((Context)this.getActivity(), this.o.getTextSize(), 2131689549, TypefaceUtils.b());
        alphaAnimation = MiscUtils.a((String)this.getString(2131296749), (String)"{0}", (String)this.w.f.accountIcon.handle, (Object)alphaAnimation);
        this.o.setText((CharSequence)alphaAnimation);
        MiscUtils.a((View)this.o, (float)0.0f, (boolean)false, (boolean)false, (boolean)true, (boolean)true, (long)1200, (long)1000, (float)1.2f, (Runnable)new Runnable(){

            @Override
            public void run() {
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        DuetJoinSaveFragment.this.az = true;
                        DuetJoinSaveFragment.this.d(true);
                    }
                }, 2000);
            }

        }, (boolean)true, (int)0);
        this.r.setVisibility(8);
        this.k.setVisibility(0);
        alphaAnimation = new TranslateAnimation(2, -1.0f, 0, 0.0f, 1, 0.0f, 1, 0.0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setInterpolator((Interpolator)new OvershootInterpolator(1.2f));
        alphaAnimation.setAnimationListener(new Animation.AnimationListener(){

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        this.k.startAnimation((Animation)alphaAnimation);
        alphaAnimation = new TranslateAnimation(0, 0.0f, 0, (float)(this.getResources().getDimensionPixelSize(2131427942) / 2 + this.m.getMeasuredWidth() / 2), 1, 0.0f, 1, 0.0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillBefore(true);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setInterpolator((Interpolator)new OvershootInterpolator(3.0f));
        alphaAnimation.setAnimationListener(new Animation.AnimationListener(){

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        this.m.startAnimation((Animation)alphaAnimation);
    }

    /*
     * Enabled aggressive block sorting
     */
    @AfterViews
    protected void K() {
        block9 : {
            block8 : {
                String string2;
                if (this.getActivity().isFinishing()) break block8;
                int n = this.w.b("BACKGROUND_RESOURCE_KEY", 2130837623);
                this.u.setBackground(this.getResources().getDrawable(n));
                if (this.w.f.n()) {
                    this.j.setText((CharSequence)this.getResources().getString(2131296755));
                    this.r.setVisibility(8);
                    this.o.setText((CharSequence)this.getResources().getString(2131296754));
                    this.k.setProfilePicUrl(UserManager.a().h());
                    this.k.setVIP(SubscriptionManager.a().b());
                    this.l.setProfilePicUrl(this.w.f.accountIcon.picUrl);
                    this.l.setVIP(this.w.f.accountIcon.a());
                    this.m.setVisibility(8);
                } else if (this.S) {
                    this.j.setText((CharSequence)this.getResources().getString(2131296747));
                    this.r.setVisibility(8);
                    string2 = MessageFormat.format(this.getString(2131296746), this.w.f.accountIcon.handle);
                    this.o.setText((CharSequence)string2);
                    this.k.setProfilePicUrl(UserManager.a().h());
                    this.k.setVIP(SubscriptionManager.a().b());
                    this.l.setProfilePicUrl(this.w.f.accountIcon.picUrl);
                    this.l.setVIP(this.w.f.accountIcon.a());
                    this.m.setVisibility(8);
                } else {
                    this.j.setText((CharSequence)MessageFormat.format(this.getString(2131296753), this.w.f.accountIcon.handle));
                    this.k.setVisibility(8);
                    this.o.setText((CharSequence)this.getResources().getString(2131296752));
                    this.k.setProfilePicUrl(UserManager.a().h());
                    this.k.setVIP(SubscriptionManager.a().b());
                    this.m.setProfilePicUrl(this.w.f.accountIcon.picUrl);
                    this.m.setVIP(this.w.f.accountIcon.a());
                    this.l.setVisibility(4);
                    this.l.setImageDrawable(17170445);
                    this.t.setVisibility(0);
                }
                string2 = this.E != null ? this.E.title : this.Z();
                this.h.setText((CharSequence)string2);
                this.i.setVisibility(8);
                this.q.setVisibility(8);
                this.c("updateAfterViewBinding - auto-upload for joins");
                if (this.y) {
                    this.O();
                }
                if (this.W && this.w.b == SingBundle.PerformanceType.c && !this.as && this.E != null) break block9;
            }
            return;
        }
        this.ae();
    }

    @Click
    protected void L() {
        if (!this.isAdded()) {
            return;
        }
        this.t.setVisibility(8);
        this.az = true;
        if (this.y) {
            this.d(true);
            return;
        }
        this.M();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void M() {
        if (!this.isAdded() || this.getActivity() == null || !this.az || this.t.getVisibility() == 0 || this.ad != null && this.ad.isShowing() || this.aA != null) {
            return;
        }
        this.aA = new ProgressBarDialog(this.getActivity(), this.getString(2131296716), new ProgressBarDialog.ProgressBarDialogInterface(){

            @Override
            public void a() {
                if (DuetJoinSaveFragment.this.aA != null) {
                    DuetJoinSaveFragment.this.aA.dismiss();
                    DuetJoinSaveFragment.this.aA = null;
                    DuetJoinSaveFragment.this.P();
                }
            }
        });
        this.aA.b(5);
        this.aA.show();
    }

    @UiThread
    protected void N() {
        if (!this.isAdded()) {
            return;
        }
        this.y = true;
        this.O();
    }

    @UiThread
    protected void O() {
        this.U();
        if (this.t.getVisibility() != 0) {
            this.d(true);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @UiThread
    protected void P() {
        if (!this.isAdded()) {
            return;
        }
        String string2 = PerformanceV2Util.e((PerformanceV2)this.E);
        Object object = this.w.o ? Analytics.c : Analytics.d;
        SingAnalytics.a((String)string2, object, (AudioDefs.HeadphonesType)AudioDefs.HeadphonesType.a(this.C, this.D), (String)this.F, (boolean)true, SingAnalytics.a((PerformanceV2)this.E), (SingAnalytics.ReviewStepsType)SingAnalytics.ReviewStepsType.c, (String)this.Y(), (Boolean)this.E.video, (boolean)this.W);
        object = new CustomAlertDialog.CustomAlertDialogListener(){

            @Override
            public void a(CustomAlertDialog customAlertDialog) {
                DuetJoinSaveFragment.this.c("showCancelDialog - onBackOrCancelButton");
            }

            @Override
            public void b(CustomAlertDialog customAlertDialog) {
                DuetJoinSaveFragment.this.aB.run();
            }
        };
        this.ac = new DeleteRecordingConfirmationDialog(this.getActivity());
        this.ac.a((CustomAlertDialog.CustomAlertDialogListener)object);
        this.ac.show();
    }

    @UiThread
    protected void Q() {
        if (!this.isAdded()) {
            Log.d(ab, "showCompressionAllocationFailDialog - not added to fragment; aborting");
            return;
        }
        this.af = false;
        if (this.ac != null) {
            this.ac.dismiss();
            this.ac = null;
        }
        TextAlertDialog textAlertDialog = new TextAlertDialog((Context)this.getActivity(), null, this.getString(2131297639), true, false);
        textAlertDialog.a(this.getString(2131296705), "");
        textAlertDialog.setCanceledOnTouchOutside(false);
        textAlertDialog.setOnShowListener(new DialogInterface.OnShowListener(){

            public void onShow(DialogInterface dialogInterface) {
                SingAnalytics.c((String)DuetJoinSaveFragment.this.x, (String)DuetJoinSaveFragment.this.Y(), (String)"performance", (boolean)false);
            }
        });
        textAlertDialog.a(new Runnable(){

            @Override
            public void run() {
                DuetJoinSaveFragment.this.aa();
            }
        });
        this.V();
        textAlertDialog.show();
    }

    @UiThread
    protected void R() {
        if (!this.isAdded()) {
            Log.d(ab, "showFailUploadDialog - not added to fragment; aborting");
            return;
        }
        this.af = false;
        if (this.ac != null) {
            this.ac.dismiss();
            this.ac = null;
        }
        TextAlertDialog textAlertDialog = new TextAlertDialog((Context)this.getActivity(), this.getString(2131297110));
        textAlertDialog.a(new Runnable(){

            @Override
            public void run() {
                DuetJoinSaveFragment.this.aa();
            }
        });
        this.V();
        textAlertDialog.show();
    }

    protected void S() {
        this.af = false;
        this.ae = false;
        if (!this.isAdded()) {
            Log.d(ab, "showFailCreateDialog - not added to fragment; aborting");
            return;
        }
        this.V();
        final PerformanceSaveActivity performanceSaveActivity = (PerformanceSaveActivity)this.getActivity();
        performanceSaveActivity.a(new Runnable(){

            @Override
            public void run() {
                performanceSaveActivity.a();
                if (DuetJoinSaveFragment.this.ak) {
                    DuetJoinSaveFragment.this.T();
                }
                DuetJoinSaveFragment.this.ap.run();
            }
        }, this.aB);
    }

    public void T() {
        if (this.i.getVisibility() != 0) {
            return;
        }
        this.W();
        Runnable runnable = new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                int n = 1;
                if (!DuetJoinSaveFragment.this.isAdded() || DuetJoinSaveFragment.this.ak) {
                    return;
                }
                if (DuetJoinSaveFragment.this.i.getMax() <= DuetJoinSaveFragment.this.i.getProgress()) {
                    DuetJoinSaveFragment.this.aj = false;
                    return;
                }
                int n2 = DuetJoinSaveFragment.this.i.getMax();
                int n3 = DuetJoinSaveFragment.this.i.getProgress();
                if (DuetJoinSaveFragment.this.aj) {
                    n2 = 5;
                } else if (n2 - n3 <= 20) {
                    n = 150;
                    n2 = 0;
                } else {
                    n2 = 1;
                    n = 150;
                }
                DuetJoinSaveFragment.this.i.setProgress(n2 + DuetJoinSaveFragment.this.i.getProgress());
                DuetJoinSaveFragment.this.ai.postDelayed((Runnable)this, (long)n);
            }
        };
        this.ai.post(runnable);
    }

    public void U() {
        this.aj = true;
    }

    public void V() {
        if (this.aA != null) {
            this.aA.dismiss();
            this.aA = null;
        }
        this.ak = true;
    }

    public void W() {
        this.i.setProgress(0);
        this.aj = false;
        this.ak = false;
    }

    public void a() {
        if (this.az) {
            return;
        }
        if (this.w.f.n()) {
            this.F();
            return;
        }
        if (this.S) {
            this.H();
            return;
        }
        this.G();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(com.smule.android.network.core.NetworkResponse networkResponse) {
        this.af = false;
        this.ae = false;
        if (!this.isAdded()) {
            Log.d(ab, "showFailCreateDialog - not added to fragment; aborting");
            return;
        }
        if (this.ac != null) {
            this.ac.dismiss();
            this.ac = null;
        }
        this.V();
        if (networkResponse.e()) {
            ((BaseActivity)this.getActivity()).a(networkResponse.f, false, this.aB);
            return;
        }
        Runnable runnable = new Runnable(){

            @Override
            public void run() {
                DuetJoinSaveFragment.this.P();
            }
        };
        if (networkResponse.a == NetworkResponse.b) {
            ((BaseActivity)this.getActivity()).a(this.ap, runnable);
            return;
        }
        if (networkResponse.b != 1020 && networkResponse.b != 1021) {
            this.ad = new TextAlertDialog((Context)this.getActivity(), this.getString(2131297078));
            this.ad.a(new Runnable(){

                @Override
                public void run() {
                    if (DuetJoinSaveFragment.this.ak) {
                        DuetJoinSaveFragment.this.T();
                    }
                    DuetJoinSaveFragment.this.ac();
                }
            });
            this.ad.b(runnable);
        } else {
            this.ad = new TextAlertDialog((Context)this.getActivity(), null, this.getString(2131297079), true, false);
            this.ad.a(this.aB);
            this.ad.b(this.aB);
            this.ad.a(this.getString(2131296705), "");
        }
        this.ad.show();
    }

    public void a(Future<PerformanceManager.PreuploadResponse> future) {
        this.ay = future;
    }

    @UiThread
    protected void c(boolean bl) {
        if (!this.isAdded() || this.getActivity() == null) {
            return;
        }
        if (bl) {
            this.b(this.getActivity().getString(2131297196));
        }
        UIHelper.a((Context)this.getActivity(), (long)this.w.f.accountIcon.accountId, (TextView)this.r);
        this.ah = false;
    }

    @UiThread
    protected void d(int n) {
        Intent intent = new Intent();
        intent.putExtra("CHANGE_MADE_CODE", n);
        super.a(intent);
    }

    @Override
    public boolean d() {
        if (this.y) {
            this.d(true);
            return true;
        }
        this.P();
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
        if (this.v != null) {
            this.w = this.v.b;
        }
        this.an = new PerformanceCreateUtil(this.A, this.Y);
        Bundle bundle2 = this.getArguments();
        if (bundle == null) {
            Log.b(ab, "onCreate - no saved instance state");
            this.P = bundle2.getString("RECORDING_FILE_EXTRA_KEY");
            this.Q = bundle2.getInt("SCORE_EXTRA_KEY", 0);
            this.R = bundle2.getFloat("USER_GAIN_DB", 1.0f);
            this.C = bundle2.getBoolean("HEADTHING_ONLY", false);
            this.D = bundle2.getBoolean("HEADPHONE_HAD_MIC", false);
            this.S = bundle2.getBoolean("IS_FOLLOWING_PARTNER_KEY", false);
            this.U = bundle2.getString("VIDEO_FILE");
            this.X = bundle2.getFloat("JOIN_MAX_POWER_POSITION_SECONDS", 0.0f);
            this.V = bundle2;
            this.av = ((PerformanceSaveActivity)this.getActivity()).t();
            boolean bl = !TextUtils.isEmpty((CharSequence)this.U);
            this.W = bl;
            if (this.W && this.w.b == SingBundle.PerformanceType.c) {
                VideoEffects.b((String)this.w.h());
                this.aw = this.av;
                ImageToDiskUtils.b((Context)this.getActivity(), (String)"duetjoinerthumb");
                ImageToDiskUtils.a((Context)this.getActivity(), (String)"duetjoincompositebitmap", (Bitmap)this.aw);
                this.as = true;
            }
        } else {
            Log.b(ab, "onCreate - restoring from saved instance state");
        }
        this.E = this.w.f;
        this.F = bundle2.getString("EFFECT_PRESET");
        this.G = bundle2.getString("FX_INITIAL");
        this.H = bundle2.getString("FX_SELECTED");
        this.I = bundle2.getInt("FX_SELECTED_VERSION", 0);
        this.J = bundle2.getString("FXS_UNIQUE_REVIEW");
        this.K = bundle2.getString("ADJUSTED_SLIDER");
        this.L = bundle2.getString("PLAY_PAUSE_COUNT");
        if (this.F == null || this.F.isEmpty()) {
            this.F = "****";
        }
        this.M = Float.valueOf(bundle2.getFloat("META_PARAM_1", -1.0f));
        this.N = Float.valueOf(bundle2.getFloat("META_PARAM_2", -1.0f));
        if (this.M.floatValue() == -1.0f) {
            this.M = null;
        }
        if (this.N.floatValue() == -1.0f) {
            this.N = null;
        }
        this.O = bundle2.getBoolean("PRESET_VIP_EXTRA_KEY");
        try {
            this.Z = (Integer)bundle2.get("ESTIMATED_LATENCY_MS");
        }
        catch (ClassCastException classCastException) {
            Log.d(ab, "There was a value for ESTIMATED_LATENCY_MS but it wasn't an Integer", classCastException);
            this.Z = null;
        }
        this.ap = new Runnable(){

            @Override
            public void run() {
                if (!DuetJoinSaveFragment.this.isAdded()) {
                    return;
                }
                Log.b(ab, "retry savePerformance");
                DuetJoinSaveFragment.this.c("retry");
            }
        };
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.ac = null;
        this.ad = null;
        this.c(this.ap);
        if (this.ao != null) {
            this.ao.d();
            this.ao = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MiscUtils.a((Activity)this.getActivity(), (boolean)true);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Click
    protected void t() {
        this.T = true;
        if (this.ah) {
            return;
        }
        this.ah = true;
        long l = this.w.f.accountIcon.accountId;
        if (!FollowManager.a().a(l) && !this.ax) {
            this.ax = true;
            Analytics followType = FollowManager.a().a(l) ? Analytics.b : Analytics.a;
            com.smule.android.logging.Analytics.a(followType, l);
            FollowManager.a().a((Long)l, new FollowManager.ToggleFollowStateListener(){

                @Override
                public void a(boolean bl, boolean bl2, boolean bl3) {
                    DuetJoinSaveFragment.this.ax = false;
                    DuetJoinSaveFragment.this.c(bl3);
                    if (bl && !bl3 && bl2) {
                        DuetJoinSaveFragment.this.J();
                    }
                }
            });
        }
        this.c(false);
    }

    @Override
    public String x() {
        return ab;
    }

}

