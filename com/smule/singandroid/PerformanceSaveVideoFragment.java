package com.smule.singandroid;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.AutoScrollHelper;
import android.view.KeyEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.audio.AudioDefs.HeadphonesType;
import com.smule.android.logging.Analytics.UserPath;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.PerformanceManager.PerformanceResourceInfo;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.WeakListener.OnGlobalLayoutListener;
import com.smule.singandroid.PerformanceSaveFragment.Mode;
import com.smule.singandroid.utils.ImageToDiskUtils;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceCreateUtil.PerformanceCreateListener;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.video.ExoPlayerWrapper;
import com.smule.singandroid.video.ExoPlayerWrapper.ExoPlayerStateChangeListener;
import com.smule.singandroid.video.GetAudioTimeCallback;
import com.smule.singandroid.video.VideoFilterManager;
import java.util.ArrayList;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;
import twitter4j.HttpResponseCode;

@EFragment
public class PerformanceSaveVideoFragment extends PerformanceSaveFragment implements OnSeekBarChangeListener {
    private static final String aD = PerformanceSaveVideoFragment.class.getName();
    @InstanceState
    protected String aA;
    protected OnGlobalLayoutListener aB = new OnGlobalLayoutListener(this, new C39461(this));
    protected ExoPlayerWrapper aC;
    private MediaMetadataRetriever aE;
    private float aF;
    private float aG;
    @ViewById
    protected SeekBar as;
    @ViewById
    protected RelativeLayout at;
    @ViewById
    protected View au;
    @ViewById
    protected View av;
    @ViewById
    protected ViewGroup aw;
    @ViewById
    protected ViewGroup ax;
    @ViewById
    protected ViewGroup ay;
    @ViewById
    protected TextureView az;

    class C39461 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ PerformanceSaveVideoFragment f19255a;

        C39461(PerformanceSaveVideoFragment performanceSaveVideoFragment) {
            this.f19255a = performanceSaveVideoFragment;
        }

        public void onGlobalLayout() {
            float f = 0.0f;
            if (this.f19255a.isAdded()) {
                Rect rect = new Rect();
                this.f19255a.ay.getWindowVisibleDisplayFrame(rect);
                LayoutParams layoutParams;
                if (((float) (this.f19255a.ay.getRootView().getHeight() - rect.height())) > ((float) (this.f19255a.ay.getRootView().getHeight() / 4))) {
                    Log.b(PerformanceSaveVideoFragment.aD, "kbd up");
                    if (this.f19255a.av.getVisibility() != 0) {
                        float height = ((float) (rect.height() - this.f19255a.ax.getHeight())) - ((float) (this.f19255a.aw.getBottom() - this.f19255a.at.getBottom()));
                        if (height >= 0.0f) {
                            f = height;
                        }
                        ViewGroup.LayoutParams layoutParams2 = this.f19255a.av.getLayoutParams();
                        layoutParams2.height = (int) f;
                        this.f19255a.av.setLayoutParams(layoutParams2);
                        this.f19255a.av.setVisibility(0);
                        layoutParams = (LayoutParams) this.f19255a.aw.getLayoutParams();
                        layoutParams.addRule(3, C1947R.id.scrub_alpha_overlay);
                        this.f19255a.aw.setLayoutParams(layoutParams);
                        return;
                    }
                    return;
                }
                Log.b(PerformanceSaveVideoFragment.aD, "kbd down");
                if (this.f19255a.av.getVisibility() != 8) {
                    this.f19255a.av.setVisibility(8);
                    layoutParams = (LayoutParams) this.f19255a.aw.getLayoutParams();
                    layoutParams.addRule(3, C1947R.id.scrub_container);
                    this.f19255a.aw.setLayoutParams(layoutParams);
                }
            }
        }
    }

    class C39472 implements OnEditorActionListener {
        final /* synthetic */ PerformanceSaveVideoFragment f19256a;

        C39472(PerformanceSaveVideoFragment performanceSaveVideoFragment) {
            this.f19256a = performanceSaveVideoFragment;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == 6) {
                this.f19256a.mo6529U();
            }
            return false;
        }
    }

    class C39483 implements GetAudioTimeCallback {
        final /* synthetic */ PerformanceSaveVideoFragment f19257a;

        C39483(PerformanceSaveVideoFragment performanceSaveVideoFragment) {
            this.f19257a = performanceSaveVideoFragment;
        }

        public float mo6397a() {
            return 0.0f;
        }
    }

    class C39504 implements ExoPlayerStateChangeListener {
        final /* synthetic */ PerformanceSaveVideoFragment f19259a;

        class C39491 implements Runnable {
            final /* synthetic */ C39504 f19258a;

            C39491(C39504 c39504) {
                this.f19258a = c39504;
            }

            public void run() {
                if (this.f19258a.f19259a.isAdded()) {
                    this.f19258a.f19259a.am = this.f19258a.f19259a.az.getBitmap(HttpResponseCode.BAD_REQUEST, HttpResponseCode.BAD_REQUEST);
                }
            }
        }

        C39504(PerformanceSaveVideoFragment performanceSaveVideoFragment) {
            this.f19259a = performanceSaveVideoFragment;
        }

        public void mo6398a(int i) {
            if (this.f19259a.isAdded() && i == 4 && this.f19259a.az.getAlpha() == DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
                this.f19259a.aC.m26364a();
                this.f19259a.m19839a(new C39491(this), 300);
            }
        }
    }

    class C39515 implements PerformanceCreateListener {
        final /* synthetic */ PerformanceSaveVideoFragment f19260a;

        C39515(PerformanceSaveVideoFragment performanceSaveVideoFragment) {
            this.f19260a = performanceSaveVideoFragment;
        }

        public void mo6395a(ArrayList<PerformanceResourceInfo> arrayList) {
            this.f19260a.A.f19323i = arrayList;
            this.f19260a.m20760R();
        }

        public void mo6394a(PerformanceV2 performanceV2, String str, String str2) {
            this.f19260a.mo6531a(performanceV2);
        }

        public void mo6393a(NetworkResponse networkResponse) {
            this.f19260a.m20767a(networkResponse);
        }

        public void mo6396b(NetworkResponse networkResponse) {
            this.f19260a.m20772b(networkResponse);
        }
    }

    protected boolean mo6525F() {
        return (this.aA == null || this.aA.isEmpty()) ? false : true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.aA = this.ae.getString("VIDEO_FILE");
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.aE != null) {
            this.aE.release();
            this.aE = null;
        }
        if (this.ay != null) {
            LayoutUtils.m25859b(this.ay, this.aB);
        }
        if (this.aC != null) {
            this.aC.m26368b();
            this.aC = null;
        }
    }

    protected void mo6510a() {
        float f = 0.0f;
        super.mo6510a();
        LayoutUtils.m25854a(this.ay, this.aB);
        if (!this.ad) {
            boolean h;
            String f2;
            this.as.setOnSeekBarChangeListener(this);
            if (this.aa == Mode.Create) {
                this.m.setOnEditorActionListener(new C39472(this));
            }
            String str = this.aA;
            if (this.aa == Mode.Edit) {
                str = this.L.videoRenderedUrl;
                h = this.L.h();
            } else {
                h = this.aj.m21658g();
            }
            if (this.aa == Mode.Create && DeviceSettings.r() && !SingServerValues.m21670H().isEmpty()) {
                f2 = this.aj.m21657f();
            } else {
                f2 = null;
            }
            this.aC = new ExoPlayerWrapper(getActivity(), this.az, new Handler(Looper.getMainLooper()), str, new C39483(this), AutoScrollHelper.NO_MAX, 0.0f, null, new C39504(this), f2, VideoFilterManager.m26576a(), h);
            onProgressChanged(this.as, 0, true);
        }
        if (this.aa == Mode.Create) {
            this.k.setAlpha(0.0f);
            this.az.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            return;
        }
        this.k.setAlpha(mo6530V() ? DefaultRetryPolicy.DEFAULT_BACKOFF_MULT : 0.0f);
        TextureView textureView = this.az;
        if (!mo6530V()) {
            f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        }
        textureView.setAlpha(f);
    }

    public void onPause() {
        super.onPause();
        if (this.aC != null) {
            this.aC.m26365a(this.aG);
        }
    }

    public void onResume() {
        super.onResume();
        if (this.aC != null) {
            this.aC.m26370c();
        }
    }

    protected void mo6526G() {
        if (this.ad || mo6530V()) {
            super.mo6526G();
        }
    }

    protected void mo6528T() {
        this.k.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        this.az.setAlpha(0.0f);
    }

    protected void mo6527Q() {
        if (!this.D) {
            String obj = this.j.getText().toString();
            if (this.al) {
                if (!this.ad && obj.length() == 0) {
                    m19849b(getResources().getString(C1947R.string.performance_save_title_required));
                    return;
                }
            } else if (obj.length() == 0) {
                obj = m20746D();
            }
            SingAnalytics.m26107a(m20747E(), this.aj.f20149n ? UserPath.ONBOARDING : UserPath.OTHER, HeadphonesType.m17502a(this.J, this.K), this.N, this.ad, this.aj.f20137b.m21631a(), m20758P(), this.aj.f20141f != null ? Boolean.valueOf(this.aj.f20141f.video) : null, mo6525F());
            m20757O();
            Bitmap bitmap = null;
            if (this.am == null && this.H != null) {
                bitmap = ImageToDiskUtils.m25830a(getActivity(), this.H);
            } else if (this.am != null) {
                bitmap = this.am;
            }
            Log.b(aD, "createPerformance - performance title is: " + obj);
            String str = null;
            if (this.aj.f20157v != null) {
                str = JsonUtils.m18987a(this.aj.f20157v);
            } else {
                MagicCrittercism.a(new Exception("noMetaDataFoundException4"));
            }
            this.an.m25931a(this.ak, getActivity(), this.aj.m21652c(), this.aj.m21643a(), this.aj.m21648b(), this.aj.f20142g, this.ab.m18773s() ? this.ab.mo6289c() : this.ab.mo6290d(), this.ab.m18772r() ? this.ab.mo6289c() : null, this.ab.m18772r() ? ((ArrangementVersionLiteEntry) this.ab).f17623a.version : 0, this.aj.f20145j, obj, this.Y, this.N, this.T, this.U, this.Z, this.I, this.J, this.m.getText().toString(), bitmap, this.aA, str, this.aj.f20149n, this.aj.m21657f(), this.aj.m21658g(), this.V, this.K, new C39515(this));
        }
    }

    protected void mo6531a(PerformanceV2 performanceV2) {
        super.mo6531a(performanceV2);
        if (this.aA != null && !this.aA.isEmpty()) {
            SingAnalytics.m26090a(this.B, this.aj.m21644b("VIDEO_STATS_CAMERA_FPS", 0.0f), this.aj.m21644b("VIDEO_STATS_ENCODER_FPS", 0.0f), this.aj.m21645b("VIDEO_STATS_CAMERA_TO_ENCODER_DROPS", 0), this.aj.m21644b("VIDEO_STATS_MUXER_FPS", 0.0f), this.aj.m21645b("VIDEO_STATS_ENCODER_TO_MUXER_DROPS", 0));
        }
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        this.k.setAlpha(0.0f);
        this.az.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        this.aF = this.aC.m26374g();
        this.aG = (((float) i) / ((float) this.as.getMax())) * this.aF;
        this.aC.m26369b(this.aG);
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Click
    protected void mo6529U() {
        MiscUtils.m25894a(this.m, true);
    }

    protected boolean mo6530V() {
        return (this.L == null || this.L.coverUrl == null) ? false : true;
    }
}
