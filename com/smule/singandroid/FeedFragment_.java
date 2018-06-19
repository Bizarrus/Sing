package com.smule.singandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.customviews.MediaListView;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FeedFragment_ extends FeedFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f18692s = new OnViewChangedNotifier();
    private View f18693t;

    class C38331 implements OnClickListener {
        final /* synthetic */ FeedFragment_ f18690a;

        C38331(FeedFragment_ feedFragment_) {
            this.f18690a = feedFragment_;
        }

        public void onClick(View view) {
            this.f18690a.m20126E();
        }
    }

    class C38342 implements Runnable {
        final /* synthetic */ FeedFragment_ f18691a;

        C38342(FeedFragment_ feedFragment_) {
            this.f18691a = feedFragment_;
        }

        public void run() {
            super.mo6451A();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, FeedFragment> {
        public FeedFragment m20139a() {
            FeedFragment feedFragment_ = new FeedFragment_();
            feedFragment_.setArguments(this.a);
            return feedFragment_;
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f18692s);
        m20141a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f18693t == null) {
            return null;
        }
        return this.f18693t.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f18693t = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f18693t == null) {
            this.f18693t = layoutInflater.inflate(C1947R.layout.feed_list_layout, viewGroup, false);
        }
        return this.f18693t;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f18693t = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
    }

    private void m20141a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m20144b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f18692s.a(this);
    }

    public static FragmentBuilder_ m20140F() {
        return new FragmentBuilder_();
    }

    public void m20151a(HasViews hasViews) {
        this.f = (MediaListView) hasViews.findViewById(C1947R.id.feed_listview);
        this.g = (SwipeRefreshLayout) hasViews.findViewById(C1947R.id.mSwipeLayout);
        this.h = hasViews.findViewById(C1947R.id.bookmark_banner);
        this.i = (TextView) hasViews.findViewById(C1947R.id.bookmark_banner_title);
        View findViewById = hasViews.findViewById(C1947R.id.bookmark_banner_ok_button);
        if (findViewById != null) {
            findViewById.setOnClickListener(new C38331(this));
        }
        m20138z();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("mExpandedOriginalIdx", this.k);
    }

    private void m20144b(Bundle bundle) {
        if (bundle != null) {
            this.k = bundle.getInt("mExpandedOriginalIdx");
        }
    }

    protected void mo6451A() {
        UiThreadExecutor.a("", new C38342(this), 100);
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }

    protected void mo6452C() {
        BackgroundExecutor.a();
        super.mo6452C();
    }

    protected void mo6453D() {
        BackgroundExecutor.a();
        super.mo6453D();
    }
}
