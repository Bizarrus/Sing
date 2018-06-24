package com.smule.singandroid.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import com.smule.android.logging.Analytics.SearchTarget;
import com.smule.android.network.models.ListingV2;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.CustomToolbar;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.RippleBackground;
import com.smule.singandroid.customviews.SquareTextureView;
import com.smule.singandroid.hashtag.Hashtag.HashtagCallback;
import com.smule.singandroid.utils.FractionalRelativeLayout;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class NowPlayingFragment_ extends NowPlayingFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier aP = new OnViewChangedNotifier();
    private View aQ;

    class C45541 implements OnClickListener {
        final /* synthetic */ NowPlayingFragment_ f22537a;

        C45541(NowPlayingFragment_ nowPlayingFragment_) {
            this.f22537a = nowPlayingFragment_;
        }

        public void onClick(View view) {
            this.f22537a.m23907M();
        }
    }

    class C45552 implements OnClickListener {
        final /* synthetic */ NowPlayingFragment_ f22538a;

        C45552(NowPlayingFragment_ nowPlayingFragment_) {
            this.f22538a = nowPlayingFragment_;
        }

        public void onClick(View view) {
            this.f22538a.m23907M();
        }
    }

    class C45563 implements OnClickListener {
        final /* synthetic */ NowPlayingFragment_ f22539a;

        C45563(NowPlayingFragment_ nowPlayingFragment_) {
            this.f22539a = nowPlayingFragment_;
        }

        public void onClick(View view) {
            this.f22539a.m23907M();
        }
    }

    class C45574 implements OnClickListener {
        final /* synthetic */ NowPlayingFragment_ f22540a;

        C45574(NowPlayingFragment_ nowPlayingFragment_) {
            this.f22540a = nowPlayingFragment_;
        }

        public void onClick(View view) {
            this.f22540a.m23907M();
        }
    }

    class C45585 implements OnClickListener {
        final /* synthetic */ NowPlayingFragment_ f22541a;

        C45585(NowPlayingFragment_ nowPlayingFragment_) {
            this.f22541a = nowPlayingFragment_;
        }

        public void onClick(View view) {
            this.f22541a.m23916V();
        }
    }

    class C45596 implements Runnable {
        final /* synthetic */ NowPlayingFragment_ f22542a;

        C45596(NowPlayingFragment_ nowPlayingFragment_) {
            this.f22542a = nowPlayingFragment_;
        }

        public void run() {
            if (this.f22542a.getActivity() != null) {
                super.mo6838U();
            }
        }
    }

    class C45607 implements Runnable {
        final /* synthetic */ NowPlayingFragment_ f22543a;

        C45607(NowPlayingFragment_ nowPlayingFragment_) {
            this.f22543a = nowPlayingFragment_;
        }

        public void run() {
            super.mo6546B();
        }
    }

    class C45629 implements Runnable {
        final /* synthetic */ NowPlayingFragment_ f22546a;

        C45629(NowPlayingFragment_ nowPlayingFragment_) {
            this.f22546a = nowPlayingFragment_;
        }

        public void run() {
            super.mo6834O();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, NowPlayingFragment> {
        public NowPlayingFragment m23946a() {
            NowPlayingFragment nowPlayingFragment_ = new NowPlayingFragment_();
            nowPlayingFragment_.setArguments(this.a);
            return nowPlayingFragment_;
        }

        public FragmentBuilder_ m23950a(boolean z) {
            this.a.putBoolean("mAutoPlay", z);
            return this;
        }

        public FragmentBuilder_ m23951b(boolean z) {
            this.a.putBoolean("mNavigationMenuShowing", z);
            return this;
        }

        public FragmentBuilder_ m23949a(PerformanceV2 performanceV2) {
            this.a.putParcelable("mPerformance", performanceV2);
            return this;
        }

        public FragmentBuilder_ m23952c(boolean z) {
            this.a.putBoolean("mHasCustomMenu", z);
            return this;
        }

        public FragmentBuilder_ m23948a(SearchTarget searchTarget) {
            this.a.putSerializable("mSearchTarget", searchTarget);
            return this;
        }

        public FragmentBuilder_ m23947a(int i) {
            this.a.putInt("mMenuRes", i);
            return this;
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.aP);
        m23953a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.aQ == null) {
            return null;
        }
        return this.aQ.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.aQ = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.aQ == null) {
            this.aQ = layoutInflater.inflate(C1947R.layout.now_playing_fragment, viewGroup, false);
        }
        return this.aQ;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.aQ = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = null;
        this.R = null;
        this.S = null;
        this.T = null;
        this.U = null;
        this.V = null;
        this.W = null;
        this.X = null;
        this.Y = null;
        this.Z = null;
        this.aa = null;
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.ae = null;
        this.af = null;
        this.ag = null;
        this.ah = null;
        this.ai = null;
        this.aj = null;
        this.ak = null;
        this.al = null;
        this.am = null;
        this.an = null;
        this.ao = null;
        this.ap = null;
        this.aq = null;
        this.ar = null;
        this.as = null;
        this.at = null;
        this.au = null;
        this.av = null;
        this.aw = null;
        this.aI = null;
        this.aJ = null;
        this.aK = null;
        this.aL = null;
        this.aM = null;
        this.aN = null;
    }

    private void m23953a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        ab();
        m23963b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.aP.a(this);
    }

    public static FragmentBuilder_ aa() {
        return new FragmentBuilder_();
    }

    public void m23981a(HasViews hasViews) {
        this.e = (CustomToolbar) hasViews.findViewById(C1947R.id.top_toolbar);
        this.f = (SeekBar) hasViews.findViewById(C1947R.id.mSeekBar);
        this.g = (ImageView) hasViews.findViewById(C1947R.id.mPlayButton);
        this.h = (TextView) hasViews.findViewById(C1947R.id.mCurrentTimeTextView);
        this.i = hasViews.findViewById(C1947R.id.mLoadingView);
        this.j = (ImageView) hasViews.findViewById(C1947R.id.mini_bar_album_art);
        this.k = hasViews.findViewById(C1947R.id.album_art_overlay_view);
        this.l = (Button) hasViews.findViewById(C1947R.id.album_art_play_pause_button);
        this.m = (TextView) hasViews.findViewById(C1947R.id.mini_bar_title_text_view);
        this.n = (TextView) hasViews.findViewById(C1947R.id.mini_bar_subtitle_text_view);
        this.o = (ProgressBar) hasViews.findViewById(C1947R.id.media_mini_bar_progress_bar);
        this.p = (RippleBackground) hasViews.findViewById(C1947R.id.ripple_background);
        this.r = hasViews.findViewById(C1947R.id.media_mini_bar);
        this.s = hasViews.findViewById(C1947R.id.media_main_content);
        this.t = (FractionalRelativeLayout) hasViews.findViewById(C1947R.id.media_root);
        this.G = hasViews.findViewById(C1947R.id.bookmark_banner);
        this.H = (TextView) hasViews.findViewById(C1947R.id.bookmark_banner_title);
        this.I = hasViews.findViewById(C1947R.id.now_playing_loading_view);
        this.J = hasViews.findViewById(C1947R.id.now_playing_content_view);
        this.K = (TextView) hasViews.findViewById(C1947R.id.loading_performance_textview);
        this.L = (ProgressBar) hasViews.findViewById(C1947R.id.loading_performance_progress_bar);
        this.M = (Button) hasViews.findViewById(C1947R.id.mini_bar_love_button);
        this.N = (ImageView) hasViews.findViewById(C1947R.id.mBlurredAlbumArt);
        this.O = (ImageView) hasViews.findViewById(C1947R.id.album_art_container_view);
        this.P = hasViews.findViewById(C1947R.id.performance_copyright_infringement_note);
        this.Q = (TextView) hasViews.findViewById(C1947R.id.performance_copyright_infringement_title);
        this.R = (TextView) hasViews.findViewById(C1947R.id.performance_copyright_infringement_detail);
        this.S = hasViews.findViewById(C1947R.id.performers_view);
        this.T = hasViews.findViewById(C1947R.id.first_performer_view);
        this.U = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.first_performer_image_view);
        this.V = (TextView) hasViews.findViewById(C1947R.id.first_performer_handle);
        this.W = hasViews.findViewById(C1947R.id.performers_plus_image_view);
        this.X = hasViews.findViewById(C1947R.id.second_performer_view);
        this.Y = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.second_performer_image_view);
        this.Z = (TextView) hasViews.findViewById(C1947R.id.second_performer_handle);
        this.aa = (TextView) hasViews.findViewById(C1947R.id.performance_blurb_text_view);
        this.ab = hasViews.findViewById(C1947R.id.comments_count_view);
        this.ac = (TextView) hasViews.findViewById(C1947R.id.mLovesCountView);
        this.ad = (TextView) hasViews.findViewById(C1947R.id.mCommentsCountView);
        this.ae = (TextView) hasViews.findViewById(C1947R.id.play_count_text_view);
        this.af = (TextView) hasViews.findViewById(C1947R.id.time_since_created_text_view);
        this.ag = (ImageButton) hasViews.findViewById(C1947R.id.share_button);
        this.ah = (ImageButton) hasViews.findViewById(C1947R.id.comment_button);
        this.ai = (ImageButton) hasViews.findViewById(C1947R.id.love_button);
        this.aj = (ImageButton) hasViews.findViewById(C1947R.id.more_options_image_button);
        this.ak = hasViews.findViewById(C1947R.id.loves_view);
        this.al = (CustomToolbar) hasViews.findViewById(C1947R.id.loves_top_toolbar);
        this.am = (ListView) hasViews.findViewById(C1947R.id.loves_view_list_view);
        this.an = (TextView) hasViews.findViewById(C1947R.id.loves_view_love_count_text_view);
        this.ao = (ImageButton) hasViews.findViewById(C1947R.id.loves_view_love_button);
        this.ap = hasViews.findViewById(C1947R.id.joiners_view);
        this.aq = (CustomToolbar) hasViews.findViewById(C1947R.id.joiners_top_toolbar);
        this.ar = (ListView) hasViews.findViewById(C1947R.id.joiners_view_list_view);
        this.as = (TextView) hasViews.findViewById(C1947R.id.joiners_view_joiners_count_text_view);
        this.at = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.loves_view_user_pic_view);
        this.au = hasViews.findViewById(C1947R.id.bottom_bar_view);
        this.av = hasViews.findViewById(C1947R.id.bottom_audio_bar_view);
        this.aw = (ProgressBar) hasViews.findViewById(C1947R.id.mLoveProgressBar);
        this.aI = hasViews.findViewById(C1947R.id.audio_playing_view);
        this.aJ = (SquareTextureView) hasViews.findViewById(C1947R.id.video_playback_view);
        this.aK = hasViews.findViewById(C1947R.id.video_playback_view_mask);
        this.aL = hasViews.findViewById(C1947R.id.video_loading_container);
        this.aM = hasViews.findViewById(C1947R.id.performers_view_background);
        this.aN = (LinearLayout) hasViews.findViewById(C1947R.id.mSlidingControls);
        View findViewById = hasViews.findViewById(C1947R.id.play_count_view);
        View findViewById2 = hasViews.findViewById(C1947R.id.video_playback_view_background);
        View findViewById3 = hasViews.findViewById(C1947R.id.bookmark_banner_ok_button);
        if (this.aJ != null) {
            this.aJ.setOnClickListener(new C45541(this));
        }
        if (this.S != null) {
            this.S.setOnClickListener(new C45552(this));
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new C45563(this));
        }
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(new C45574(this));
        }
        if (findViewById3 != null) {
            findViewById3.setOnClickListener(new C45585(this));
        }
        m20377J();
        m23917W();
    }

    private void ab() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.containsKey("mAutoPlay")) {
                this.u = arguments.getBoolean("mAutoPlay");
            }
            if (arguments.containsKey("mNavigationMenuShowing")) {
                this.v = arguments.getBoolean("mNavigationMenuShowing");
            }
            if (arguments.containsKey("mPerformance")) {
                this.ay = (PerformanceV2) arguments.getParcelable("mPerformance");
            }
            if (arguments.containsKey("mHasCustomMenu")) {
                this.aD = arguments.getBoolean("mHasCustomMenu");
            }
            if (arguments.containsKey("mSearchTarget")) {
                this.aE = (SearchTarget) arguments.getSerializable("mSearchTarget");
            }
            if (arguments.containsKey("mMenuRes")) {
                this.aF = arguments.getInt("mMenuRes");
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("mAutoPlay", this.u);
        bundle.putBoolean("mNavigationMenuShowing", this.v);
        bundle.putBoolean("mShowAllComments", this.az);
        bundle.putBoolean("mLovedPerformance", this.aA);
        bundle.putString("mShareWebURL", this.aB);
        bundle.putParcelable("mListing", this.aC);
        bundle.putBoolean("mSongIsVIPOnly", this.aG);
        bundle.putBoolean("mCloseAllOnBack", this.aH);
    }

    private void m23963b(Bundle bundle) {
        if (bundle != null) {
            this.u = bundle.getBoolean("mAutoPlay");
            this.v = bundle.getBoolean("mNavigationMenuShowing");
            this.az = bundle.getBoolean("mShowAllComments");
            this.aA = bundle.getBoolean("mLovedPerformance");
            this.aB = bundle.getString("mShareWebURL");
            this.aC = (ListingV2) bundle.getParcelable("mListing");
            this.aG = bundle.getBoolean("mSongIsVIPOnly");
            this.aH = bundle.getBoolean("mCloseAllOnBack");
        }
    }

    protected void mo6838U() {
        UiThreadExecutor.a("", new C45596(this), 0);
    }

    protected void mo6546B() {
        UiThreadExecutor.a("", new C45607(this), 0);
    }

    protected void mo6549b(final boolean z) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ NowPlayingFragment_ f22545b;

            public void run() {
                super.mo6549b(z);
            }
        }, 0);
    }

    protected void mo6834O() {
        UiThreadExecutor.a("", new C45629(this), 0);
    }

    public void mo6835Q() {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ NowPlayingFragment_ f22513a;

            {
                this.f22513a = r1;
            }

            public void run() {
                super.mo6835Q();
            }
        }, 0);
    }

    protected void mo6836R() {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ NowPlayingFragment_ f22514a;

            {
                this.f22514a = r1;
            }

            public void run() {
                super.mo6836R();
            }
        }, 0);
    }

    public void mo6837S() {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ NowPlayingFragment_ f22515a;

            {
                this.f22515a = r1;
            }

            public void run() {
                super.mo6837S();
            }
        }, 0);
    }

    protected void mo6844c(final String str) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ NowPlayingFragment_ f22517b;

            public void run() {
                super.mo6844c(str);
            }
        }, 0);
    }

    protected void mo6840a(final View view, final float f, final float f2) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ NowPlayingFragment_ f22521d;

            public void run() {
                super.mo6840a(view, f, f2);
            }
        }, 0);
    }

    protected void mo6839a(final Activity activity, final String str) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ NowPlayingFragment_ f22524c;

            public void run() {
                super.mo6839a(activity, str);
            }
        }, 0);
    }

    protected void mo6841a(final boolean z, final int i) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ NowPlayingFragment_ f22527c;

            public void run() {
                super.mo6841a(z, i);
            }
        }, 0);
    }

    public void mo6843b(final boolean z, final boolean z2) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ NowPlayingFragment_ f22530c;

            public void run() {
                super.mo6843b(z, z2);
            }
        }, 0);
    }

    public void mo6842a(final boolean z, final boolean z2, final HashtagCallback hashtagCallback) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ NowPlayingFragment_ f22534d;

            public void run() {
                super.mo6842a(z, z2, hashtagCallback);
            }
        }, 0);
    }

    public void mo6845f(final boolean z) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ NowPlayingFragment_ f22536b;

            public void run() {
                super.mo6845f(z);
            }
        }, 0);
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }

    protected void mo6547C() {
        BackgroundExecutor.a();
        super.mo6547C();
    }
}
