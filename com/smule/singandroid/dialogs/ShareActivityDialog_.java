package com.smule.singandroid.dialogs;

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
import com.smule.singandroid.C1947R;
import com.smule.singandroid.PostSingBundle;
import com.smule.singandroid.customviews.SquareSNPImageView;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ShareActivityDialog_ extends ShareActivityDialog implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f22317U = new OnViewChangedNotifier();

    class C45091 implements OnClickListener {
        final /* synthetic */ ShareActivityDialog_ f22306a;

        C45091(ShareActivityDialog_ shareActivityDialog_) {
            this.f22306a = shareActivityDialog_;
        }

        public void onClick(View view) {
            this.f22306a.m21453a();
        }
    }

    class C45102 implements OnClickListener {
        final /* synthetic */ ShareActivityDialog_ f22307a;

        C45102(ShareActivityDialog_ shareActivityDialog_) {
            this.f22307a = shareActivityDialog_;
        }

        public void onClick(View view) {
            this.f22307a.m21456b();
        }
    }

    class C45113 implements OnClickListener {
        final /* synthetic */ ShareActivityDialog_ f22308a;

        C45113(ShareActivityDialog_ shareActivityDialog_) {
            this.f22308a = shareActivityDialog_;
        }

        public void onClick(View view) {
            this.f22308a.m21465q();
        }
    }

    class C45124 implements OnClickListener {
        final /* synthetic */ ShareActivityDialog_ f22309a;

        C45124(ShareActivityDialog_ shareActivityDialog_) {
            this.f22309a = shareActivityDialog_;
        }

        public void onClick(View view) {
            this.f22309a.m21466r();
        }
    }

    class C45135 implements OnClickListener {
        final /* synthetic */ ShareActivityDialog_ f22310a;

        C45135(ShareActivityDialog_ shareActivityDialog_) {
            this.f22310a = shareActivityDialog_;
        }

        public void onClick(View view) {
            this.f22310a.m21454a(view);
        }
    }

    class C45146 implements OnClickListener {
        final /* synthetic */ ShareActivityDialog_ f22311a;

        C45146(ShareActivityDialog_ shareActivityDialog_) {
            this.f22311a = shareActivityDialog_;
        }

        public void onClick(View view) {
            this.f22311a.m21457b(view);
        }
    }

    class C45157 implements OnClickListener {
        final /* synthetic */ ShareActivityDialog_ f22312a;

        C45157(ShareActivityDialog_ shareActivityDialog_) {
            this.f22312a = shareActivityDialog_;
        }

        public void onClick(View view) {
            this.f22312a.m21458c(view);
        }
    }

    class C45168 implements OnClickListener {
        final /* synthetic */ ShareActivityDialog_ f22313a;

        C45168(ShareActivityDialog_ shareActivityDialog_) {
            this.f22313a = shareActivityDialog_;
        }

        public void onClick(View view) {
            this.f22313a.m21460d(view);
        }
    }

    class C45179 implements OnClickListener {
        final /* synthetic */ ShareActivityDialog_ f22314a;

        C45179(ShareActivityDialog_ shareActivityDialog_) {
            this.f22314a = shareActivityDialog_;
        }

        public void onClick(View view) {
            this.f22314a.m21461e(view);
        }
    }

    public static class IntentBuilder_ extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment f22315d;
        private android.support.v4.app.Fragment f22316e;

        public PostActivityStarter m23690a(int i) {
            if (this.f22316e != null) {
                this.f22316e.startActivityForResult(this.c, i);
            } else if (this.f22315d != null) {
                this.f22315d.startActivityForResult(this.c, i, this.a);
            } else if (this.b instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) this.b, this.c, i, this.a);
            } else {
                this.b.startActivity(this.c, this.a);
            }
            return new PostActivityStarter(this.b);
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f22317U);
        m23691a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
        setContentView((int) C1947R.layout.share_dialog);
    }

    private void m23691a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m23708v();
        m23695b(bundle);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        this.f22317U.a(this);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.f22317U.a(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.f22317U.a(this);
    }

    public void m23710a(HasViews hasViews) {
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
            this.v.setOnClickListener(new C45091(this));
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new C45102(this));
        }
        if (this.w != null) {
            this.w.setOnClickListener(new C45113(this));
        }
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(new C45124(this));
        }
        if (this.n != null) {
            this.n.setOnClickListener(new C45135(this));
        }
        if (this.s != null) {
            this.s.setOnClickListener(new C45146(this));
        }
        if (this.o != null) {
            this.o.setOnClickListener(new C45157(this));
        }
        if (this.p != null) {
            this.p.setOnClickListener(new C45168(this));
        }
        if (this.q != null) {
            this.q.setOnClickListener(new C45179(this));
        }
        if (this.r != null) {
            this.r.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ShareActivityDialog_ f22301a;

                {
                    this.f22301a = r1;
                }

                public void onClick(View view) {
                    this.f22301a.m21462f(view);
                }
            });
        }
        if (this.t != null) {
            this.t.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ShareActivityDialog_ f22302a;

                {
                    this.f22302a = r1;
                }

                public void onClick(View view) {
                    this.f22302a.m21463g(view);
                }
            });
        }
        if (this.u != null) {
            this.u.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ShareActivityDialog_ f22303a;

                {
                    this.f22303a = r1;
                }

                public void onClick(View view) {
                    this.f22303a.m21464h(view);
                }
            });
        }
        if (findViewById3 != null) {
            findViewById3.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ShareActivityDialog_ f22304a;

                {
                    this.f22304a = r1;
                }

                public void onClick(View view) {
                    this.f22304a.m21467s();
                }
            });
        }
        c();
    }

    private void m23708v() {
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
        m23708v();
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

    private void m23695b(Bundle bundle) {
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
            final /* synthetic */ ShareActivityDialog_ f22305a;

            {
                this.f22305a = r1;
            }

            public void run() {
                super.mo6611u();
            }
        }, 0);
    }

    public void m23709a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.a(runTimePermissionsRequest, resultCallback);
    }
}
