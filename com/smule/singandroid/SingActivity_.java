package com.smule.singandroid;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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

public final class SingActivity_ extends SingActivity implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier aI = new OnViewChangedNotifier();

    class C41311 implements OnClickListener {
        final /* synthetic */ SingActivity_ f20080a;

        C41311(SingActivity_ singActivity_) {
            this.f20080a = singActivity_;
        }

        public void onClick(View view) {
            this.f20080a.m21530B();
        }
    }

    class C41322 implements Runnable {
        final /* synthetic */ SingActivity_ f20081a;

        C41322(SingActivity_ singActivity_) {
            this.f20081a = singActivity_;
        }

        public void run() {
            super.mo6619w();
        }
    }

    class C41333 implements Runnable {
        final /* synthetic */ SingActivity_ f20082a;

        C41333(SingActivity_ singActivity_) {
            this.f20082a = singActivity_;
        }

        public void run() {
            super.mo6620x();
        }
    }

    class C41344 implements Runnable {
        final /* synthetic */ SingActivity_ f20083a;

        C41344(SingActivity_ singActivity_) {
            this.f20083a = singActivity_;
        }

        public void run() {
            super.mo6621y();
        }
    }

    class C41388 implements Runnable {
        final /* synthetic */ SingActivity_ f20094a;

        C41388(SingActivity_ singActivity_) {
            this.f20094a = singActivity_;
        }

        public void run() {
            super.mo6614W();
        }
    }

    public static class IntentBuilder_ extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment f20095d;
        private android.support.v4.app.Fragment f20096e;

        public PostActivityStarter m21588a(int i) {
            if (this.f20096e != null) {
                this.f20096e.startActivityForResult(this.c, i);
            } else if (this.f20095d != null) {
                this.f20095d.startActivityForResult(this.c, i, this.a);
            } else if (this.b instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) this.b, this.c, i, this.a);
            } else {
                this.b.startActivity(this.c, this.a);
            }
            return new PostActivityStarter(this.b);
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.aI);
        m21589a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
        setContentView((int) C1947R.layout.sing_activity);
    }

    private void m21589a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        ab();
        m21595b(bundle);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        this.aI.a(this);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.aI.a(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.aI.a(this);
    }

    public void m21602a(HasViews hasViews) {
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
        this.E = (VisualizerView) hasViews.findViewById(C1947R.id.visualizer);
        this.F = hasViews.findViewById(C1947R.id.duet_info_container);
        this.G = hasViews.findViewById(C1947R.id.pause_menu_container);
        this.H = hasViews.findViewById(C1947R.id.report_song_menu_container);
        this.I = (ProgressBar) hasViews.findViewById(C1947R.id.song_progress);
        this.J = (ViewGroup) hasViews.findViewById(C1947R.id.lyric_container);
        this.K = hasViews.findViewById(C1947R.id.lyric_overlay);
        this.L = (LyricsView) hasViews.findViewById(C1947R.id.song_lyrics);
        this.M = (ViewGroup) hasViews.findViewById(C1947R.id.profile_container);
        this.N = (SNPImageView) hasViews.findViewById(C1947R.id.first_profile_portrait);
        this.O = (SNPImageView) hasViews.findViewById(C1947R.id.second_profile_portrait);
        this.P = (SNPImageView) hasViews.findViewById(C1947R.id.no_lyrics_profile);
        this.Q = (TextView) hasViews.findViewById(C1947R.id.headphones_recommended_text);
        this.R = (ImageView) hasViews.findViewById(C1947R.id.left_button);
        this.S = (TextView) hasViews.findViewById(C1947R.id.right_timer);
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
        View findViewById = hasViews.findViewById(C1947R.id.song_lyrics_placeholder);
        if (findViewById != null) {
            findViewById.setOnClickListener(new C41311(this));
        }
        c();
    }

    private void ab() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("RESTARTED_KEY")) {
            this.al = extras.getBoolean("RESTARTED_KEY");
        }
    }

    public void setIntent(Intent intent) {
        super.setIntent(intent);
        ab();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("mCanSaveEarly", this.k);
        bundle.putBoolean("mHeadphonesEverUnplugged", this.l);
        bundle.putBoolean("mHeadphonesAlertDialogShowingOnPause", this.m);
    }

    private void m21595b(Bundle bundle) {
        if (bundle != null) {
            this.k = bundle.getBoolean("mCanSaveEarly");
            this.l = bundle.getBoolean("mHeadphonesEverUnplugged");
            this.m = bundle.getBoolean("mHeadphonesAlertDialogShowingOnPause");
        }
    }

    protected void mo6619w() {
        UiThreadExecutor.a("", new C41322(this), 0);
    }

    void mo6620x() {
        UiThreadExecutor.a("", new C41333(this), 0);
    }

    public void mo6621y() {
        UiThreadExecutor.a("", new C41344(this), 0);
    }

    public void mo6617b(final Runnable runnable) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ SingActivity_ f20085b;

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
            final /* synthetic */ SingActivity_ f20091f;

            public void run() {
                super.mo6615a(d3, d4, z2, f3, f4);
            }
        }, 0);
    }

    public void mo6616b(final int i) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ SingActivity_ f20093b;

            public void run() {
                super.mo6616b(i);
            }
        }, 0);
    }

    protected void mo6614W() {
        UiThreadExecutor.a("", new C41388(this), 0);
    }

    public void m21601a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.a(runTimePermissionsRequest, resultCallback);
    }
}
