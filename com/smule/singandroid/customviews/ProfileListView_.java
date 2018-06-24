package com.smule.singandroid.customviews;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ProfileListView_ extends ProfileListView implements HasViews, OnViewChangedListener {
    private boolean f21898i = false;
    private final OnViewChangedNotifier f21899j = new OnViewChangedNotifier();

    class C44381 implements Runnable {
        final /* synthetic */ ProfileListView_ f21897a;

        C44381(ProfileListView_ profileListView_) {
            this.f21897a = profileListView_;
        }

        public void run() {
            super.mo6803a();
        }
    }

    public ProfileListView_(Context context) {
        super(context);
        m23457d();
    }

    public static ProfileListView m23455a(Context context) {
        ProfileListView profileListView_ = new ProfileListView_(context);
        profileListView_.onFinishInflate();
        return profileListView_;
    }

    public void onFinishInflate() {
        if (!this.f21898i) {
            this.f21898i = true;
            inflate(getContext(), C1947R.layout.profile_list_view, this);
            this.f21899j.a(this);
        }
        super.onFinishInflate();
    }

    private void m23457d() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21899j);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23459a(HasViews hasViews) {
        this.b = (MediaListView) hasViews.findViewById(C1947R.id.profile_fragment_list_view);
        this.c = (SwipeRefreshLayout) hasViews.findViewById(C1947R.id.mSwipeLayout);
        m23452c();
    }

    public void mo6803a() {
        UiThreadExecutor.a("", new C44381(this), 0);
    }
}
