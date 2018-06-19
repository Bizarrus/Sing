package com.smule.singandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FindFriendsFragment_ extends FindFriendsFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f18718r = new OnViewChangedNotifier();
    private View f18719s;

    class C38401 implements Runnable {
        final /* synthetic */ FindFriendsFragment_ f18717a;

        C38401(FindFriendsFragment_ findFriendsFragment_) {
            this.f18717a = findFriendsFragment_;
        }

        public void run() {
            super.mo6458A();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, FindFriendsFragment> {
        public FindFriendsFragment m20175a() {
            FindFriendsFragment findFriendsFragment_ = new FindFriendsFragment_();
            findFriendsFragment_.setArguments(this.a);
            return findFriendsFragment_;
        }

        public FragmentBuilder_ m20176a(boolean z) {
            this.a.putBoolean("mIsFromShare", z);
            return this;
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f18718r);
        m20179a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f18719s == null) {
            return null;
        }
        return this.f18719s.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f18719s = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f18719s == null) {
            this.f18719s = layoutInflater.inflate(C1947R.layout.find_friends_fragment, viewGroup, false);
        }
        return this.f18719s;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f18719s = null;
        this.f = null;
        this.g = null;
        this.i = null;
        this.j = null;
    }

    private void m20179a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m20178C();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f18718r.a(this);
    }

    public static FragmentBuilder_ m20177B() {
        return new FragmentBuilder_();
    }

    public void m20184a(HasViews hasViews) {
        this.f = hasViews.findViewById(C1947R.id.mFindFriendsContainerView);
        this.g = (ListView) hasViews.findViewById(C1947R.id.mRecommendedListView);
        this.i = hasViews.findViewById(C1947R.id.mLoadingView);
        this.j = hasViews.findViewById(C1947R.id.mNoRecommendedUsersView);
        m20174z();
    }

    private void m20178C() {
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("mIsFromShare")) {
            this.l = arguments.getBoolean("mIsFromShare");
        }
    }

    protected void mo6458A() {
        UiThreadExecutor.a("", new C38401(this), 0);
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
