package com.smule.singandroid;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.android.ui.SNPImageView;
import com.smule.singandroid.customviews.LyricsView;
import com.smule.singandroid.customviews.PitchView;
import com.smule.singandroid.customviews.RadianceView;
import com.smule.singandroid.customviews.VUMeterView;
import com.smule.singandroid.customviews.VerticalSeekBar;
import com.smule.singandroid.customviews.VisualizerView;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

@TargetApi(19)
public final class SingVideoActivity_ extends SingVideoActivity implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier bd = new OnViewChangedNotifier();

    class C41521 implements OnClickListener {
        final /* synthetic */ SingVideoActivity_ f20205a;

        C41521(SingVideoActivity_ singVideoActivity_) {
            this.f20205a = singVideoActivity_;
        }

        public void onClick(View view) {
            this.f20205a.m21530B();
        }
    }

    class C41532 implements OnClickListener {
        final /* synthetic */ SingVideoActivity_ f20206a;

        C41532(SingVideoActivity_ singVideoActivity_) {
            this.f20206a = singVideoActivity_;
        }

        public void onClick(View view) {
            this.f20206a.ac();
        }
    }

    class C41543 implements OnClickListener {
        final /* synthetic */ SingVideoActivity_ f20207a;

        C41543(SingVideoActivity_ singVideoActivity_) {
            this.f20207a = singVideoActivity_;
        }

        public void onClick(View view) {
            this.f20207a.ad();
        }
    }

    class C41554 implements Runnable {
        final /* synthetic */ SingVideoActivity_ f20208a;

        C41554(SingVideoActivity_ singVideoActivity_) {
            this.f20208a = singVideoActivity_;
        }

        public void run() {
            super.mo6619w();
        }
    }

    class C41565 implements Runnable {
        final /* synthetic */ SingVideoActivity_ f20209a;

        C41565(SingVideoActivity_ singVideoActivity_) {
            this.f20209a = singVideoActivity_;
        }

        public void run() {
            super.mo6620x();
        }
    }

    class C41576 implements Runnable {
        final /* synthetic */ SingVideoActivity_ f20210a;

        C41576(SingVideoActivity_ singVideoActivity_) {
            this.f20210a = singVideoActivity_;
        }

        public void run() {
            super.mo6621y();
        }
    }

    public static class IntentBuilder_ extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment f20221d;
        private android.support.v4.app.Fragment f20222e;

        public PostActivityStarter m21757a(int i) {
            if (this.f20222e != null) {
                this.f20222e.startActivityForResult(this.c, i);
            } else if (this.f20221d != null) {
                this.f20221d.startActivityForResult(this.c, i, this.a);
            } else if (this.b instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) this.b, this.c, i, this.a);
            } else {
                this.b.startActivity(this.c, this.a);
            }
            return new PostActivityStarter(this.b);
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.bd);
        m21758a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
        setContentView((int) C1947R.layout.sing_video_activity);
    }

    private void m21758a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        ag();
        m21764b(bundle);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        this.bd.a(this);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.bd.a(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.bd.a(this);
    }

    public void m21771a(HasViews hasViews) {
        this.n = (FrameLayout) hasViews.findViewById(C1947R.id.sing_root);
        this.o = (RelativeLayout) hasViews.findViewById(C1947R.id.top_toolbar);
        this.p = (TextView) hasViews.findViewById(C1947R.id.mPauseMenuResumeButtonLayout);
        this.q = (TextView) hasViews.findViewById(C1947R.id.mPauseMenuRestartButtonLayout);
        this.r = (TextView) hasViews.findViewById(C1947R.id.mPauseMenuNewSongButtonLayout);
        this.s = (TextView) hasViews.findViewById(C1947R.id.mPauseMenuSaveInactiveButtonLayout);
        this.t = (TextView) hasViews.findViewById(C1947R.id.mPauseMenuSaveButtonLayout);
        this.u = (TextView) hasViews.findViewById(C1947R.id.mPauseMenuReportSongButtonLayout);
        this.v = (TextView) hasViews.findViewById(C1947R.id.mReportSongCopyrightInfringementButton);
        this.w = (TextView) hasViews.findViewById(C1947R.id.mReportSongInappropriateContentButton);
        this.x = (TextView) hasViews.findViewById(C1947R.id.mReportSongMislabeledContentButton);
        this.y = (TextView) hasViews.findViewById(C1947R.id.mReportSongIncorrectPitchLinesButton);
        this.z = hasViews.findViewById(C1947R.id.countdown_container);
        this.A = (TextView) hasViews.findViewById(C1947R.id.countdown_text_view);
        this.B = (RelativeLayout) hasViews.findViewById(C1947R.id.no_lyrics_container);
        this.C = (PitchView) hasViews.findViewById(C1947R.id.pitch_layer);
        this.D = (VUMeterView) hasViews.findViewById(C1947R.id.vu_meter);
        this.aM = (VisualizerView) hasViews.findViewById(C1947R.id.visualizer);
        this.aJ = hasViews.findViewById(C1947R.id.duet_info_container);
        this.G = hasViews.findViewById(C1947R.id.pause_menu_container);
        this.H = hasViews.findViewById(C1947R.id.report_song_menu_container);
        this.aN = (ProgressBar) hasViews.findViewById(C1947R.id.song_progress);
        this.aO = (ViewGroup) hasViews.findViewById(C1947R.id.lyric_container);
        this.K = hasViews.findViewById(C1947R.id.lyric_overlay);
        this.L = (LyricsView) hasViews.findViewById(C1947R.id.song_lyrics);
        this.M = (ViewGroup) hasViews.findViewById(C1947R.id.profile_container);
        this.N = (SNPImageView) hasViews.findViewById(C1947R.id.first_profile_portrait);
        this.O = (SNPImageView) hasViews.findViewById(C1947R.id.second_profile_portrait);
        this.P = (SNPImageView) hasViews.findViewById(C1947R.id.no_lyrics_profile);
        this.Q = (TextView) hasViews.findViewById(C1947R.id.headphones_recommended_text);
        this.R = (ImageView) hasViews.findViewById(C1947R.id.left_button);
        this.aP = (TextView) hasViews.findViewById(C1947R.id.right_timer);
        this.T = (LinearLayout) hasViews.findViewById(C1947R.id.profile_container_cccp);
        this.U = (FrameLayout) hasViews.findViewById(C1947R.id.first_profile_cccp);
        this.V = (FrameLayout) hasViews.findViewById(C1947R.id.second_profile_cccp);
        this.W = (SNPImageView) hasViews.findViewById(C1947R.id.first_profile_portrait_cccp);
        this.X = (SNPImageView) hasViews.findViewById(C1947R.id.second_profile_portrait_cccp);
        this.Y = (ImageView) hasViews.findViewById(C1947R.id.first_profile_indicator_cccp);
        this.Z = (ImageView) hasViews.findViewById(C1947R.id.second_profile_indicator_cccp);
        this.aa = (RadianceView) hasViews.findViewById(C1947R.id.no_lyrics_radiance);
        this.ab = (RadianceView) hasViews.findViewById(C1947R.id.cccp_radiance);
        this.ac = (RelativeLayout) hasViews.findViewById(C1947R.id.bottom_layout);
        this.ad = hasViews.findViewById(C1947R.id.monitor_view);
        this.ae = (ImageView) hasViews.findViewById(C1947R.id.monitor_icon);
        this.af = (RelativeLayout) hasViews.findViewById(C1947R.id.monitor_bar_container);
        this.ag = (VerticalSeekBar) hasViews.findViewById(C1947R.id.monitor_bar);
        this.ah = (FrameLayout) hasViews.findViewById(C1947R.id.pause_upper);
        this.ai = (RelativeLayout) hasViews.findViewById(C1947R.id.pause_lower);
        this.aj = (RelativeLayout) hasViews.findViewById(C1947R.id.sing_container);
        this.ak = (TextView) hasViews.findViewById(C1947R.id.pause_text);
        this.aI = (TextView) hasViews.findViewById(C1947R.id.sva_countdown_tip_view);
        this.aJ = hasViews.findViewById(C1947R.id.sva_duet_info_container);
        this.aK = (GLSurfaceView) hasViews.findViewById(C1947R.id.sva_full_screen_camera_preview);
        this.aL = (RelativeLayout) hasViews.findViewById(C1947R.id.sva_singing_container);
        this.aM = (VisualizerView) hasViews.findViewById(C1947R.id.sva_visualizer_view);
        this.aN = (ProgressBar) hasViews.findViewById(C1947R.id.sva_song_progress);
        this.aO = (ViewGroup) hasViews.findViewById(C1947R.id.sva_lyrics_container);
        this.aP = (TextView) hasViews.findViewById(C1947R.id.sva_right_timer);
        this.aQ = hasViews.findViewById(C1947R.id.sva_duet_overlay);
        this.aR = hasViews.findViewById(C1947R.id.airbrush_switch_touchable_area);
        this.aS = hasViews.findViewById(C1947R.id.airbrush_switch);
        this.aT = (RelativeLayout) hasViews.findViewById(C1947R.id.sva_video_overlay);
        this.aU = hasViews.findViewById(C1947R.id.sva_pause_text);
        this.aV = hasViews.findViewById(C1947R.id.sva_lyric_pad_top);
        this.aW = hasViews.findViewById(C1947R.id.sva_lyric_pad_bot);
        this.aX = (TextureView) hasViews.findViewById(C1947R.id.sva_seed_video);
        this.aY = (TextView) hasViews.findViewById(C1947R.id.sva_no_lyrics_title);
        this.aZ = (RelativeLayout) hasViews.findViewById(C1947R.id.sva_top_container);
        this.ba = (TextView) hasViews.findViewById(C1947R.id.sva_no_vfx_text_view);
        this.bb = (FrameLayout) hasViews.findViewById(C1947R.id.sva_curtain_layout);
        View findViewById = hasViews.findViewById(C1947R.id.song_lyrics_placeholder);
        View findViewById2 = hasViews.findViewById(C1947R.id.sva_top_container_clickable_area);
        if (findViewById != null) {
            findViewById.setOnClickListener(new C41521(this));
        }
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(new C41532(this));
        }
        if (this.aT != null) {
            this.aT.setOnClickListener(new C41543(this));
        }
        c();
    }

    private void ag() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("RESTARTED_KEY")) {
            this.al = extras.getBoolean("RESTARTED_KEY");
        }
    }

    public void setIntent(Intent intent) {
        super.setIntent(intent);
        ag();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("mCanSaveEarly", this.k);
        bundle.putBoolean("mHeadphonesEverUnplugged", this.l);
        bundle.putBoolean("mHeadphonesAlertDialogShowingOnPause", this.m);
    }

    private void m21764b(Bundle bundle) {
        if (bundle != null) {
            this.k = bundle.getBoolean("mCanSaveEarly");
            this.l = bundle.getBoolean("mHeadphonesEverUnplugged");
            this.m = bundle.getBoolean("mHeadphonesAlertDialogShowingOnPause");
        }
    }

    protected void mo6619w() {
        UiThreadExecutor.a("", new C41554(this), 0);
    }

    void mo6620x() {
        UiThreadExecutor.a("", new C41565(this), 0);
    }

    public void mo6621y() {
        UiThreadExecutor.a("", new C41576(this), 0);
    }

    public void mo6617b(final Runnable runnable) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ SingVideoActivity_ f20212b;

            public void run() {
                super.mo6617b(runnable);
            }
        }, 0);
    }

    public void mo6615a(double d, double d2, boolean z, float f, float f2) {
        final double d3 = d;
        final double d4 = d2;
        final boolean z2 = z;
        final float f3 = f;
        final float f4 = f2;
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ SingVideoActivity_ f20218f;

            public void run() {
                super.mo6615a(d3, d4, z2, f3, f4);
            }
        }, 0);
    }

    public void mo6616b(final int i) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ SingVideoActivity_ f20220b;

            public void run() {
                super.mo6616b(i);
            }
        }, 0);
    }

    protected void mo6614W() {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ SingVideoActivity_ f20204a;

            {
                this.f20204a = r1;
            }

            public void run() {
                super.mo6614W();
            }
        }, 0);
    }

    public void m21770a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.a(runTimePermissionsRequest, resultCallback);
    }
}
