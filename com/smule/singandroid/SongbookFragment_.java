package com.smule.singandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.customviews.OverlayWithRectangularHoleImageView;
import com.smule.singandroid.customviews.PagerSlidingTabStrip;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SongbookFragment_ extends SongbookFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f20357D = new OnViewChangedNotifier();
    private View f20358E;

    class C41841 implements OnClickListener {
        final /* synthetic */ SongbookFragment_ f20354a;

        C41841(SongbookFragment_ songbookFragment_) {
            this.f20354a = songbookFragment_;
        }

        public void onClick(View view) {
            this.f20354a.m21862B();
        }
    }

    class C41852 implements OnClickListener {
        final /* synthetic */ SongbookFragment_ f20355a;

        C41852(SongbookFragment_ songbookFragment_) {
            this.f20355a = songbookFragment_;
        }

        public void onClick(View view) {
            this.f20355a.m21863C();
        }
    }

    class C41863 implements Runnable {
        final /* synthetic */ SongbookFragment_ f20356a;

        C41863(SongbookFragment_ songbookFragment_) {
            this.f20356a = songbookFragment_;
        }

        public void run() {
            super.mo6664J();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, SongbookFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f20357D);
        m21888a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f20358E == null) {
            return null;
        }
        return this.f20358E.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f20358E = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f20358E == null) {
            this.f20358E = layoutInflater.inflate(C1947R.layout.songbook_fragment, viewGroup, false);
        }
        return this.f20358E;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f20358E = null;
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
        this.q = null;
        this.r = null;
        this.s = null;
    }

    private void m21888a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m21893b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f20357D.a(this);
    }

    public void m21896a(HasViews hasViews) {
        this.f = hasViews.findViewById(C1947R.id.mLoadingSongbookView);
        this.g = (LinearLayout) hasViews.findViewById(C1947R.id.mEmptySongListView);
        this.h = (TextView) hasViews.findViewById(C1947R.id.mEmptySongListDescriptionTextView);
        this.i = (FrameLayout) hasViews.findViewById(C1947R.id.mSongListingsContainerView);
        this.j = hasViews.findViewById(C1947R.id.songbook_root_view);
        this.k = (PagerSlidingTabStrip) hasViews.findViewById(C1947R.id.mSectionTabsListView);
        this.l = (CustomViewPager) hasViews.findViewById(C1947R.id.mSectionViewPager);
        this.m = hasViews.findViewById(C1947R.id.mSongbookHeaderView);
        this.n = (FrameLayout) hasViews.findViewById(C1947R.id.mSongbookBannerView);
        this.o = (ViewPager) hasViews.findViewById(C1947R.id.banner_view_pager);
        this.p = (LinearLayout) hasViews.findViewById(C1947R.id.default_banner);
        this.q = (OverlayWithRectangularHoleImageView) hasViews.findViewById(C1947R.id.coachmark_overlay);
        this.r = hasViews.findViewById(C1947R.id.coachmark_tooltip);
        this.s = hasViews.findViewById(C1947R.id.coachmark_triangle);
        if (this.p != null) {
            this.p.setOnClickListener(new C41841(this));
        }
        if (this.n != null) {
            this.n.setOnClickListener(new C41852(this));
        }
        m21864D();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("mOwnedSongsCount", this.u);
        bundle.putBoolean("mIsShowingPreSearchBar", this.v);
    }

    private void m21893b(Bundle bundle) {
        if (bundle != null) {
            this.u = bundle.getInt("mOwnedSongsCount");
            this.v = bundle.getBoolean("mIsShowingPreSearchBar");
        }
    }

    void mo6664J() {
        UiThreadExecutor.a("", new C41863(this), 0);
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }

    protected void mo6666e(int i) {
        BackgroundExecutor.a();
        super.mo6666e(i);
    }

    protected void mo6665d(String str) {
        BackgroundExecutor.a();
        super.mo6665d(str);
    }
}
