package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.ProfileFragment.CheckVideoStatusCallback;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

@SuppressLint({"InflateParams"})
public final class ProfileFragment_ extends ProfileFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f19530O = new OnViewChangedNotifier();
    private View f19531P;

    class C40221 implements OnClickListener {
        final /* synthetic */ ProfileFragment_ f19525a;

        C40221(ProfileFragment_ profileFragment_) {
            this.f19525a = profileFragment_;
        }

        public void onClick(View view) {
            this.f19525a.m21087X();
        }
    }

    class C40243 implements Runnable {
        final /* synthetic */ ProfileFragment_ f19528a;

        C40243(ProfileFragment_ profileFragment_) {
            this.f19528a = profileFragment_;
        }

        public void run() {
            super.mo6571C();
        }
    }

    class C40254 implements Runnable {
        final /* synthetic */ ProfileFragment_ f19529a;

        C40254(ProfileFragment_ profileFragment_) {
            this.f19529a = profileFragment_;
        }

        public void run() {
            super.mo6573U();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, ProfileFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f19530O);
        m21122a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f19531P == null) {
            return null;
        }
        return this.f19531P.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f19531P = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f19531P == null) {
            this.f19531P = layoutInflater.inflate(C1947R.layout.profile_fragment, viewGroup, false);
        }
        return this.f19531P;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f19531P = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
    }

    private void m21122a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m21128b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f19530O.a(this);
    }

    public void m21138a(HasViews hasViews) {
        this.f = (RelativeLayout) hasViews.findViewById(C1947R.id.profile_root);
        this.g = hasViews.findViewById(C1947R.id.bookmark_banner);
        this.h = (TextView) hasViews.findViewById(C1947R.id.bookmark_banner_title);
        this.i = (TabLayout) hasViews.findViewById(C1947R.id.performance_type_tab_layout);
        this.j = hasViews.findViewById(C1947R.id.progress_view);
        this.k = (CustomViewPager) hasViews.findViewById(C1947R.id.profile_viewpager);
        this.l = hasViews.findViewById(C1947R.id.profile_header_tab_layout);
        this.m = (LinearLayout) hasViews.findViewById(C1947R.id.profile_header_layout);
        View findViewById = hasViews.findViewById(C1947R.id.bookmark_banner_ok_button);
        if (findViewById != null) {
            findViewById.setOnClickListener(new C40221(this));
        }
        m21067D();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mAccountIcon", this.p);
        bundle.putBoolean("mIsCurrentUsersProfile", this.q);
        bundle.putString("mAppId", this.r);
        bundle.putBoolean("mUsesApp", this.s);
        bundle.putString("mBlurb", this.t);
        bundle.putBoolean("mInTakingPhotoFlow", this.u);
        bundle.putInt("mFirstVisibleRowPosition", this.v);
        bundle.putInt("mFirstVisibleTop", this.w);
        bundle.putBoolean("mShowFutureWarning", this.x);
        bundle.putInt("mCurrentPerformanceType", this.E);
        bundle.putParcelable("mEmailIntent", this.G);
        bundle.putBoolean("mIsMention", this.H);
    }

    private void m21128b(Bundle bundle) {
        if (bundle != null) {
            this.p = (AccountIcon) bundle.getParcelable("mAccountIcon");
            this.q = bundle.getBoolean("mIsCurrentUsersProfile");
            this.r = bundle.getString("mAppId");
            this.s = bundle.getBoolean("mUsesApp");
            this.t = bundle.getString("mBlurb");
            this.u = bundle.getBoolean("mInTakingPhotoFlow");
            this.v = bundle.getInt("mFirstVisibleRowPosition");
            this.w = bundle.getInt("mFirstVisibleTop");
            this.x = bundle.getBoolean("mShowFutureWarning");
            this.E = bundle.getInt("mCurrentPerformanceType");
            this.G = (Intent) bundle.getParcelable("mEmailIntent");
            this.H = bundle.getBoolean("mIsMention");
        }
    }

    protected void mo6522c(final NetworkResponse networkResponse) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ ProfileFragment_ f19527b;

            public void run() {
                super.mo6522c(networkResponse);
            }
        }, 0);
    }

    protected void mo6571C() {
        UiThreadExecutor.a("", new C40243(this), 0);
    }

    protected void mo6573U() {
        UiThreadExecutor.a("", new C40254(this), 0);
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }

    protected void mo6572S() {
        BackgroundExecutor.a();
        super.mo6572S();
    }

    protected void mo6574a(@NonNull MediaPlayingViewInterface mediaPlayingViewInterface, @NonNull PerformanceV2 performanceV2, @NonNull CheckVideoStatusCallback checkVideoStatusCallback) {
        BackgroundExecutor.a();
        super.mo6574a(mediaPlayingViewInterface, performanceV2, checkVideoStatusCallback);
    }

    protected void mo6576c(PerformanceV2 performanceV2) {
        BackgroundExecutor.a();
        super.mo6576c(performanceV2);
    }

    protected void mo6577d(PerformanceV2 performanceV2) {
        BackgroundExecutor.a();
        super.mo6577d(performanceV2);
    }

    public void aa() {
        BackgroundExecutor.a();
        super.aa();
    }
}
