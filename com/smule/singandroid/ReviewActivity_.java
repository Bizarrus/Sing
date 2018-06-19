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
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.customviews.CustomToolbar;
import com.smule.singandroid.customviews.VideoFXTabIndicator;
import com.smule.singandroid.customviews.VocalEffectList;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ReviewActivity_ extends ReviewActivity implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier ag = new OnViewChangedNotifier();

    class C40561 implements OnClickListener {
        final /* synthetic */ ReviewActivity_ f19743a;

        C40561(ReviewActivity_ reviewActivity_) {
            this.f19743a = reviewActivity_;
        }

        public void onClick(View view) {
            this.f19743a.m21316s();
        }
    }

    class C40572 implements OnClickListener {
        final /* synthetic */ ReviewActivity_ f19744a;

        C40572(ReviewActivity_ reviewActivity_) {
            this.f19744a = reviewActivity_;
        }

        public void onClick(View view) {
            this.f19744a.m21318u();
        }
    }

    class C40583 implements OnClickListener {
        final /* synthetic */ ReviewActivity_ f19745a;

        C40583(ReviewActivity_ reviewActivity_) {
            this.f19745a = reviewActivity_;
        }

        public void onClick(View view) {
            this.f19745a.m21320w();
        }
    }

    class C40594 implements OnClickListener {
        final /* synthetic */ ReviewActivity_ f19746a;

        C40594(ReviewActivity_ reviewActivity_) {
            this.f19746a = reviewActivity_;
        }

        public void onClick(View view) {
            this.f19746a.m21288G();
        }
    }

    class C40605 implements OnClickListener {
        final /* synthetic */ ReviewActivity_ f19747a;

        C40605(ReviewActivity_ reviewActivity_) {
            this.f19747a = reviewActivity_;
        }

        public void onClick(View view) {
            this.f19747a.m21290I();
        }
    }

    class C40616 implements Runnable {
        final /* synthetic */ ReviewActivity_ f19748a;

        C40616(ReviewActivity_ reviewActivity_) {
            this.f19748a = reviewActivity_;
        }

        public void run() {
            super.mo6594q();
        }
    }

    class C40638 implements Runnable {
        final /* synthetic */ ReviewActivity_ f19751a;

        C40638(ReviewActivity_ reviewActivity_) {
            this.f19751a = reviewActivity_;
        }

        public void run() {
            super.mo6595y();
        }
    }

    class C40649 implements Runnable {
        final /* synthetic */ ReviewActivity_ f19752a;

        C40649(ReviewActivity_ reviewActivity_) {
            this.f19752a = reviewActivity_;
        }

        public void run() {
            super.mo6596z();
        }
    }

    public static class IntentBuilder_ extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment f19753d;
        private android.support.v4.app.Fragment f19754e;

        public PostActivityStarter m21324a(int i) {
            if (this.f19754e != null) {
                this.f19754e.startActivityForResult(this.c, i);
            } else if (this.f19753d != null) {
                this.f19753d.startActivityForResult(this.c, i, this.a);
            } else if (this.b instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) this.b, this.c, i, this.a);
            } else {
                this.b.startActivity(this.c, this.a);
            }
            return new PostActivityStarter(this.b);
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.ag);
        m21326a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
        setContentView((int) C1947R.layout.review_activity);
    }

    private void m21326a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m21325M();
        m21332b(bundle);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        this.ag.a(this);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.ag.a(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.ag.a(this);
    }

    public void m21341a(HasViews hasViews) {
        this.g = (CustomToolbar) hasViews.findViewById(C1947R.id.top_toolbar);
        this.h = (Button) hasViews.findViewById(C1947R.id.review_save_button);
        this.i = (Button) hasViews.findViewById(C1947R.id.review_sing_again_button);
        this.j = hasViews.findViewById(C1947R.id.review_play_button_layout);
        this.k = (ImageView) hasViews.findViewById(C1947R.id.review_review_play_button);
        this.l = (SeekBar) hasViews.findViewById(C1947R.id.review_foreground_level_seek_bar);
        this.m = (SeekBar) hasViews.findViewById(C1947R.id.review_delay_calib_seek_bar);
        this.n = (TextView) hasViews.findViewById(C1947R.id.review_score_label);
        this.o = (ProgressBar) hasViews.findViewById(C1947R.id.review_render_progress);
        this.p = hasViews.findViewById(C1947R.id.main_background);
        this.q = hasViews.findViewById(C1947R.id.review_headphones_required_blocking_view);
        this.r = (WaveformView) hasViews.findViewById(C1947R.id.review_waveform_view);
        this.s = (TextView) hasViews.findViewById(C1947R.id.review_playback_time_text);
        this.t = hasViews.findViewById(C1947R.id.review_score_save_view);
        this.u = (LinearLayout) hasViews.findViewById(C1947R.id.review_bottom_scroll_pane);
        this.v = hasViews.findViewById(C1947R.id.review_delay_sync_layout_header);
        this.w = (LinearLayout) hasViews.findViewById(C1947R.id.review_match_vocal_text_layout);
        this.x = (LinearLayout) hasViews.findViewById(C1947R.id.review_match_vocal_contracted_text_layout);
        this.y = (TextView) hasViews.findViewById(C1947R.id.review_match_vocal_contracted_text_play_view);
        this.z = (TextView) hasViews.findViewById(C1947R.id.review_match_vocal_contracted_text_ms_view);
        this.A = (TextView) hasViews.findViewById(C1947R.id.review_match_vocal_text_play_view);
        this.B = (TextView) hasViews.findViewById(C1947R.id.review_match_vocal_text_ms_view);
        this.C = (LinearLayout) hasViews.findViewById(C1947R.id.review_delay_sync_layout_content);
        this.D = (TextView) hasViews.findViewById(C1947R.id.review_match_vocal_text_reset_view);
        this.E = (ImageView) hasViews.findViewById(C1947R.id.review_match_vocal_expand_arrow);
        this.F = hasViews.findViewById(C1947R.id.review_delay_calib_backward_button);
        this.G = hasViews.findViewById(C1947R.id.review_delay_calib_forward_button);
        this.H = (LinearLayout) hasViews.findViewById(C1947R.id.vocal_effects_list);
        this.I = (VocalEffectList) hasViews.findViewById(C1947R.id.review_effects_scroll_view);
        this.J = (TextView) hasViews.findViewById(C1947R.id.review_message);
        this.K = (FrameLayout) hasViews.findViewById(C1947R.id.video_container);
        this.L = (VideoFXTabIndicator) hasViews.findViewById(C1947R.id.video_fx_indicator);
        this.M = (FrameLayout) hasViews.findViewById(C1947R.id.curtain_animation_view);
        this.N = (FrameLayout) hasViews.findViewById(C1947R.id.airbrush_switch_touchable_area);
        this.O = (ImageView) hasViews.findViewById(C1947R.id.airbrush_switch);
        this.P = (LinearLayout) hasViews.findViewById(C1947R.id.waveform_linear_layout);
        this.Q = hasViews.findViewById(C1947R.id.review_mic_volume_container_touch);
        this.R = (ImageView) hasViews.findViewById(C1947R.id.review_mic_icon_large);
        this.S = hasViews.findViewById(C1947R.id.video_container_alpha);
        this.T = hasViews.findViewById(C1947R.id.review_bottom_anchor);
        this.U = (TextView) hasViews.findViewById(C1947R.id.group_video_message);
        View findViewById = hasViews.findViewById(C1947R.id.review_delay_sync__layout_footer);
        if (this.v != null) {
            this.v.setOnClickListener(new C40561(this));
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new C40572(this));
        }
        if (this.D != null) {
            this.D.setOnClickListener(new C40583(this));
        }
        if (this.K != null) {
            this.K.setOnClickListener(new C40594(this));
        }
        if (this.Q != null) {
            this.Q.setOnClickListener(new C40605(this));
        }
        c();
    }

    private void m21325M() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("RESTARTED_KEY")) {
            this.V = extras.getBoolean("RESTARTED_KEY");
        }
    }

    public void setIntent(Intent intent) {
        super.setIntent(intent);
        m21325M();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("mSelectedVocalEffectSNPId", this.X);
        bundle.putInt("mSelectedPosition", this.Y);
        bundle.putString("mInitialVocalEffectSNPId", this.Z);
        bundle.putFloat("mMetaParam1", this.aa);
        bundle.putFloat("mMetaParam2", this.ab);
    }

    private void m21332b(Bundle bundle) {
        if (bundle != null) {
            this.X = bundle.getString("mSelectedVocalEffectSNPId");
            this.Y = bundle.getInt("mSelectedPosition");
            this.Z = bundle.getString("mInitialVocalEffectSNPId");
            this.aa = bundle.getFloat("mMetaParam1");
            this.ab = bundle.getFloat("mMetaParam2");
        }
    }

    protected void mo6594q() {
        UiThreadExecutor.a("", new C40616(this), 0);
    }

    protected void mo6591c(final boolean z) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ ReviewActivity_ f19750b;

            public void run() {
                super.mo6591c(z);
            }
        }, 0);
    }

    protected void mo6595y() {
        UiThreadExecutor.a("", new C40638(this), 0);
    }

    protected void mo6596z() {
        UiThreadExecutor.a("", new C40649(this), 0);
    }

    protected void mo6589a(final float f) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ ReviewActivity_ f19738b;

            public void run() {
                super.mo6589a(f);
            }
        }, 0);
    }

    protected void mo6590b(final Runnable runnable) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ ReviewActivity_ f19740b;

            public void run() {
                super.mo6590b(runnable);
            }
        }, 0);
    }

    protected void mo6587A() {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ ReviewActivity_ f19741a;

            {
                this.f19741a = r1;
            }

            public void run() {
                super.mo6587A();
            }
        }, 0);
    }

    protected void mo6588C() {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ ReviewActivity_ f19742a;

            {
                this.f19742a = r1;
            }

            public void run() {
                super.mo6588C();
            }
        }, 0);
    }

    public void m21340a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.a(runTimePermissionsRequest, resultCallback);
    }
}
