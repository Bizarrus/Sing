package com.smule.singandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class LeaderboardFragment_ extends LeaderboardFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f18818k = new OnViewChangedNotifier();
    private View f18819l;

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, LeaderboardFragment> {
        public LeaderboardFragment m20282a() {
            LeaderboardFragment leaderboardFragment_ = new LeaderboardFragment_();
            leaderboardFragment_.setArguments(this.a);
            return leaderboardFragment_;
        }

        public FragmentBuilder_ m20283a(long j) {
            this.a.putLong("mPromoId", j);
            return this;
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f18818k);
        m20286a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f18819l == null) {
            return null;
        }
        return this.f18819l.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f18819l = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f18819l == null) {
            this.f18819l = layoutInflater.inflate(C1947R.layout.leaderboard_fragment, viewGroup, false);
        }
        return this.f18819l;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f18819l = null;
        this.f = null;
        this.g = null;
    }

    private void m20286a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m20285B();
        m20288b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f18818k.a(this);
    }

    public static FragmentBuilder_ m20284A() {
        return new FragmentBuilder_();
    }

    public void m20290a(HasViews hasViews) {
        this.f = (ViewPager) hasViews.findViewById(C1947R.id.view_pager);
        this.g = (TabLayout) hasViews.findViewById(C1947R.id.tab_layout);
        m20279a();
    }

    private void m20285B() {
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("mPromoId")) {
            this.h = arguments.getLong("mPromoId");
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putLong("mPromoId", this.h);
        bundle.putString("mHashtag", this.i);
    }

    private void m20288b(Bundle bundle) {
        if (bundle != null) {
            this.h = bundle.getLong("mPromoId");
            this.i = bundle.getString("mHashtag");
        }
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
