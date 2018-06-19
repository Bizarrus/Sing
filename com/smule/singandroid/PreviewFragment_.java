package com.smule.singandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import com.smule.android.logging.Analytics.SearchTarget;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.customviews.CustomToolbar;
import com.smule.singandroid.customviews.RippleBackground;
import com.smule.singandroid.list_items.UserFollowItem;
import com.smule.singandroid.utils.FractionalRelativeLayout;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PreviewFragment_ extends PreviewFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f19412T = new OnViewChangedNotifier();
    private View f19413U;

    class C39951 implements OnClickListener {
        final /* synthetic */ PreviewFragment_ f19407a;

        C39951(PreviewFragment_ previewFragment_) {
            this.f19407a = previewFragment_;
        }

        public void onClick(View view) {
            this.f19407a.m20950Q();
        }
    }

    class C39962 implements Runnable {
        final /* synthetic */ PreviewFragment_ f19408a;

        C39962(PreviewFragment_ previewFragment_) {
            this.f19408a = previewFragment_;
        }

        public void run() {
            super.mo6546B();
        }
    }

    class C39984 implements Runnable {
        final /* synthetic */ PreviewFragment_ f19411a;

        C39984(PreviewFragment_ previewFragment_) {
            this.f19411a = previewFragment_;
        }

        public void run() {
            super.mo6548O();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, PreviewFragment> {
        public PreviewFragment m20955a() {
            PreviewFragment previewFragment_ = new PreviewFragment_();
            previewFragment_.setArguments(this.a);
            return previewFragment_;
        }

        public FragmentBuilder_ m20958a(boolean z) {
            this.a.putBoolean("mNavigationMenuShowing", z);
            return this;
        }

        public FragmentBuilder_ m20957a(SongbookEntry songbookEntry) {
            this.a.putParcelable("mEntry", songbookEntry);
            return this;
        }

        public FragmentBuilder_ m20959b(boolean z) {
            this.a.putBoolean("mHasOriginSource", z);
            return this;
        }

        public FragmentBuilder_ m20956a(SearchTarget searchTarget) {
            this.a.putSerializable("mSearchTarget", searchTarget);
            return this;
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f19412T);
        m20962a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f19413U == null) {
            return null;
        }
        return this.f19413U.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f19413U = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f19413U == null) {
            this.f19413U = layoutInflater.inflate(C1947R.layout.preview_fragment, viewGroup, false);
        }
        return this.f19413U;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f19413U = null;
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
    }

    private void m20962a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m20961T();
        m20966b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f19412T.a(this);
    }

    public static FragmentBuilder_ m20960S() {
        return new FragmentBuilder_();
    }

    public void m20973a(HasViews hasViews) {
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
        this.J = (ImageView) hasViews.findViewById(C1947R.id.mBlurredAlbumArt);
        this.K = (ImageView) hasViews.findViewById(C1947R.id.album_art_image);
        this.L = (ImageView) hasViews.findViewById(C1947R.id.google_play_store_image_view);
        this.M = (TextView) hasViews.findViewById(C1947R.id.lyrics);
        this.N = (ProgressBar) hasViews.findViewById(C1947R.id.lyric_spinner);
        this.O = (TextView) hasViews.findViewById(C1947R.id.lyrics_message);
        this.P = (UserFollowItem) hasViews.findViewById(C1947R.id.arranger_follow_item);
        this.Q = hasViews.findViewById(C1947R.id.arranger_divider);
        this.R = (ImageView) hasViews.findViewById(C1947R.id.error_icon);
        this.S = (Button) hasViews.findViewById(C1947R.id.report_button);
        if (this.S != null) {
            this.S.setOnClickListener(new C39951(this));
        }
        m20377J();
        m20947N();
    }

    private void m20961T() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.containsKey("mAutoPlay")) {
                this.u = arguments.getBoolean("mAutoPlay");
            }
            if (arguments.containsKey("mNavigationMenuShowing")) {
                this.v = arguments.getBoolean("mNavigationMenuShowing");
            }
            if (arguments.containsKey("mEntry")) {
                this.G = (SongbookEntry) arguments.getParcelable("mEntry");
            }
            if (arguments.containsKey("mHasOriginSource")) {
                this.H = arguments.getBoolean("mHasOriginSource");
            }
            if (arguments.containsKey("mSearchTarget")) {
                this.I = (SearchTarget) arguments.getSerializable("mSearchTarget");
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("mAutoPlay", this.u);
        bundle.putBoolean("mNavigationMenuShowing", this.v);
    }

    private void m20966b(Bundle bundle) {
        if (bundle != null) {
            this.u = bundle.getBoolean("mAutoPlay");
            this.v = bundle.getBoolean("mNavigationMenuShowing");
        }
    }

    protected void mo6546B() {
        UiThreadExecutor.a("", new C39962(this), 0);
    }

    protected void mo6549b(final boolean z) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ PreviewFragment_ f19410b;

            public void run() {
                super.mo6549b(z);
            }
        }, 0);
    }

    public void mo6548O() {
        UiThreadExecutor.a("", new C39984(this), 0);
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
