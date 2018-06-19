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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.smule.android.logging.Analytics.SearchClkContext;
import com.smule.android.logging.Analytics.SocialChannel;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.customviews.SquareSNPImageView;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ShareActivity_ extends ShareActivity implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f19956U = new OnViewChangedNotifier();

    class C40981 implements OnClickListener {
        final /* synthetic */ ShareActivity_ f19945a;

        C40981(ShareActivity_ shareActivity_) {
            this.f19945a = shareActivity_;
        }

        public void onClick(View view) {
            this.f19945a.m21453a();
        }
    }

    class C40992 implements OnClickListener {
        final /* synthetic */ ShareActivity_ f19946a;

        C40992(ShareActivity_ shareActivity_) {
            this.f19946a = shareActivity_;
        }

        public void onClick(View view) {
            this.f19946a.m21456b();
        }
    }

    class C41003 implements OnClickListener {
        final /* synthetic */ ShareActivity_ f19947a;

        C41003(ShareActivity_ shareActivity_) {
            this.f19947a = shareActivity_;
        }

        public void onClick(View view) {
            this.f19947a.m21465q();
        }
    }

    class C41014 implements OnClickListener {
        final /* synthetic */ ShareActivity_ f19948a;

        C41014(ShareActivity_ shareActivity_) {
            this.f19948a = shareActivity_;
        }

        public void onClick(View view) {
            this.f19948a.m21466r();
        }
    }

    class C41025 implements OnClickListener {
        final /* synthetic */ ShareActivity_ f19949a;

        C41025(ShareActivity_ shareActivity_) {
            this.f19949a = shareActivity_;
        }

        public void onClick(View view) {
            this.f19949a.m21454a(view);
        }
    }

    class C41036 implements OnClickListener {
        final /* synthetic */ ShareActivity_ f19950a;

        C41036(ShareActivity_ shareActivity_) {
            this.f19950a = shareActivity_;
        }

        public void onClick(View view) {
            this.f19950a.m21457b(view);
        }
    }

    class C41047 implements OnClickListener {
        final /* synthetic */ ShareActivity_ f19951a;

        C41047(ShareActivity_ shareActivity_) {
            this.f19951a = shareActivity_;
        }

        public void onClick(View view) {
            this.f19951a.m21458c(view);
        }
    }

    class C41058 implements OnClickListener {
        final /* synthetic */ ShareActivity_ f19952a;

        C41058(ShareActivity_ shareActivity_) {
            this.f19952a = shareActivity_;
        }

        public void onClick(View view) {
            this.f19952a.m21460d(view);
        }
    }

    class C41069 implements OnClickListener {
        final /* synthetic */ ShareActivity_ f19953a;

        C41069(ShareActivity_ shareActivity_) {
            this.f19953a = shareActivity_;
        }

        public void onClick(View view) {
            this.f19953a.m21461e(view);
        }
    }

    public static class IntentBuilder_ extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment f19954d;
        private android.support.v4.app.Fragment f19955e;

        public PostActivityStarter m21470a(int i) {
            if (this.f19955e != null) {
                this.f19955e.startActivityForResult(this.c, i);
            } else if (this.f19954d != null) {
                this.f19954d.startActivityForResult(this.c, i, this.a);
            } else if (this.b instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) this.b, this.c, i, this.a);
            } else {
                this.b.startActivity(this.c, this.a);
            }
            return new PostActivityStarter(this.b);
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f19956U);
        m21471a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
        setContentView((int) C1947R.layout.new_share_layout);
    }

    private void m21471a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m21475v();
        m21474b(bundle);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        this.f19956U.a(this);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.f19956U.a(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.f19956U.a(this);
    }

    public void m21477a(HasViews hasViews) {
        this.h = (TextView) hasViews.findViewById(C1947R.id.share_title);
        this.i = (SquareSNPImageView) hasViews.findViewById(C1947R.id.album_art);
        this.j = (ImageView) hasViews.findViewById(C1947R.id.visualizer);
        this.k = (TextView) hasViews.findViewById(C1947R.id.share_song_title);
        this.l = (TextView) hasViews.findViewById(C1947R.id.share_song_user);
        this.m = (ViewGroup) hasViews.findViewById(C1947R.id.share_icons_container);
        this.n = hasViews.findViewById(C1947R.id.share_chat);
        this.o = hasViews.findViewById(C1947R.id.share_line);
        this.p = hasViews.findViewById(C1947R.id.share_messenger);
        this.q = hasViews.findViewById(C1947R.id.share_sms);
        this.r = hasViews.findViewById(C1947R.id.share_email);
        this.s = hasViews.findViewById(C1947R.id.share_video);
        this.t = hasViews.findViewById(C1947R.id.share_copy);
        this.u = hasViews.findViewById(C1947R.id.share_more);
        this.v = (ToggleButton) hasViews.findViewById(C1947R.id.facebook_toggle);
        this.w = (ToggleButton) hasViews.findViewById(C1947R.id.twitter_toggle);
        this.x = (RelativeLayout) hasViews.findViewById(C1947R.id.video_download_progress_layout);
        this.y = (TextView) hasViews.findViewById(C1947R.id.video_download_progress_text);
        this.z = (ProgressBar) hasViews.findViewById(C1947R.id.video_download_progress_spinner);
        this.A = (TextView) hasViews.findViewById(C1947R.id.video_download_desc);
        View findViewById = hasViews.findViewById(C1947R.id.facebook_toggle_touch);
        View findViewById2 = hasViews.findViewById(C1947R.id.twitter_toggle_touch);
        View findViewById3 = hasViews.findViewById(C1947R.id.done_button);
        if (this.v != null) {
            this.v.setOnClickListener(new C40981(this));
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new C40992(this));
        }
        if (this.w != null) {
            this.w.setOnClickListener(new C41003(this));
        }
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(new C41014(this));
        }
        if (this.n != null) {
            this.n.setOnClickListener(new C41025(this));
        }
        if (this.s != null) {
            this.s.setOnClickListener(new C41036(this));
        }
        if (this.o != null) {
            this.o.setOnClickListener(new C41047(this));
        }
        if (this.p != null) {
            this.p.setOnClickListener(new C41058(this));
        }
        if (this.q != null) {
            this.q.setOnClickListener(new C41069(this));
        }
        if (this.r != null) {
            this.r.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ShareActivity_ f19940a;

                {
                    this.f19940a = r1;
                }

                public void onClick(View view) {
                    this.f19940a.m21462f(view);
                }
            });
        }
        if (this.t != null) {
            this.t.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ShareActivity_ f19941a;

                {
                    this.f19941a = r1;
                }

                public void onClick(View view) {
                    this.f19941a.m21463g(view);
                }
            });
        }
        if (this.u != null) {
            this.u.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ShareActivity_ f19942a;

                {
                    this.f19942a = r1;
                }

                public void onClick(View view) {
                    this.f19942a.m21464h(view);
                }
            });
        }
        if (findViewById3 != null) {
            findViewById3.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ShareActivity_ f19943a;

                {
                    this.f19943a = r1;
                }

                public void onClick(View view) {
                    this.f19943a.m21467s();
                }
            });
        }
        c();
    }

    private void m21475v() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("PERFORMANCE_KEY")) {
                this.K = (PerformanceV2) extras.getParcelable("PERFORMANCE_KEY");
            }
            if (extras.containsKey("OPENCALL_KEY")) {
                this.L = extras.getString("OPENCALL_KEY");
            }
            if (extras.containsKey("ARRANGEMENT_KEY")) {
                this.M = (ArrangementVersionLite) extras.getParcelable("ARRANGEMENT_KEY");
            }
            if (extras.containsKey("SONG_KEY")) {
                this.N = (SongV2) extras.getParcelable("SONG_KEY");
            }
            if (extras.containsKey("PROMO_ID_KEY")) {
                this.O = extras.getString("PROMO_ID_KEY");
            }
            if (extras.containsKey("SEARCHCLK_CONTEXT_KEY")) {
                this.P = (SearchClkContext) extras.getSerializable("SEARCHCLK_CONTEXT_KEY");
            }
            if (extras.containsKey("mPostSingBundle")) {
                this.Q = (PostSingBundle) extras.getParcelable("mPostSingBundle");
            }
        }
    }

    public void setIntent(Intent intent) {
        super.setIntent(intent);
        m21475v();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("mConnectAndRequestPublish", this.B);
        bundle.putParcelable("mChatIntent", this.C);
        bundle.putParcelable("mMessengerIntent", this.D);
        bundle.putParcelable("mLineIntent", this.E);
        bundle.putParcelable("mSmsShareIntent", this.F);
        bundle.putParcelable("mEmailShareIntent", this.G);
        bundle.putParcelable("mYoutubeShareIntent", this.H);
        bundle.putParcelable("mPerformance", this.K);
        bundle.putString("mOpenCallKey", this.L);
        bundle.putParcelable("mArrVesionLite", this.M);
        bundle.putParcelable("mSong", this.N);
        bundle.putString("mPromoId", this.O);
        bundle.putSerializable("mSearchClkContext", this.P);
        bundle.putParcelable("mPostSingBundle", this.Q);
        bundle.putBoolean("mHasAnimated", this.R);
        bundle.putSerializable("mSocialChannelClicked", this.S);
        bundle.putBoolean("mVideoDownloaded", this.T);
    }

    private void m21474b(Bundle bundle) {
        if (bundle != null) {
            this.B = bundle.getBoolean("mConnectAndRequestPublish");
            this.C = (Intent) bundle.getParcelable("mChatIntent");
            this.D = (Intent) bundle.getParcelable("mMessengerIntent");
            this.E = (Intent) bundle.getParcelable("mLineIntent");
            this.F = (Intent) bundle.getParcelable("mSmsShareIntent");
            this.G = (Intent) bundle.getParcelable("mEmailShareIntent");
            this.H = (Intent) bundle.getParcelable("mYoutubeShareIntent");
            this.K = (PerformanceV2) bundle.getParcelable("mPerformance");
            this.L = bundle.getString("mOpenCallKey");
            this.M = (ArrangementVersionLite) bundle.getParcelable("mArrVesionLite");
            this.N = (SongV2) bundle.getParcelable("mSong");
            this.O = bundle.getString("mPromoId");
            this.P = (SearchClkContext) bundle.getSerializable("mSearchClkContext");
            this.Q = (PostSingBundle) bundle.getParcelable("mPostSingBundle");
            this.R = bundle.getBoolean("mHasAnimated");
            this.S = (SocialChannel) bundle.getSerializable("mSocialChannelClicked");
            this.T = bundle.getBoolean("mVideoDownloaded");
        }
    }

    protected void mo6611u() {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ ShareActivity_ f19944a;

            {
                this.f19944a = r1;
            }

            public void run() {
                super.mo6611u();
            }
        }, 0);
    }

    public void m21476a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.a(runTimePermissionsRequest, resultCallback);
    }
}
