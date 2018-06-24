package com.smule.singandroid.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FindFriendsWithoutHeaderFragment_ extends FindFriendsWithoutHeaderFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f21058s = new OnViewChangedNotifier();
    private View f21059t;

    class C43091 implements Runnable {
        final /* synthetic */ FindFriendsWithoutHeaderFragment_ f21057a;

        C43091(FindFriendsWithoutHeaderFragment_ findFriendsWithoutHeaderFragment_) {
            this.f21057a = findFriendsWithoutHeaderFragment_;
        }

        public void run() {
            super.mo6458A();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, FindFriendsWithoutHeaderFragment> {
        public FindFriendsWithoutHeaderFragment m22732a() {
            FindFriendsWithoutHeaderFragment findFriendsWithoutHeaderFragment_ = new FindFriendsWithoutHeaderFragment_();
            findFriendsWithoutHeaderFragment_.setArguments(this.a);
            return findFriendsWithoutHeaderFragment_;
        }

        public FragmentBuilder_ m22733a(boolean z) {
            this.a.putBoolean("mIsFromShare", z);
            return this;
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21058s);
        m22736a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f21059t == null) {
            return null;
        }
        return this.f21059t.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f21059t = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f21059t == null) {
            this.f21059t = layoutInflater.inflate(C1947R.layout.find_friends_fragment, viewGroup, false);
        }
        return this.f21059t;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f21059t = null;
        this.f = null;
        this.g = null;
        this.i = null;
        this.j = null;
    }

    private void m22736a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m22735D();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f21058s.a(this);
    }

    public static FragmentBuilder_ m22734C() {
        return new FragmentBuilder_();
    }

    public void m22741a(HasViews hasViews) {
        this.f = hasViews.findViewById(C1947R.id.mFindFriendsContainerView);
        this.g = (ListView) hasViews.findViewById(C1947R.id.mRecommendedListView);
        this.i = hasViews.findViewById(C1947R.id.mLoadingView);
        this.j = hasViews.findViewById(C1947R.id.mNoRecommendedUsersView);
        m20174z();
    }

    private void m22735D() {
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("mIsFromShare")) {
            this.l = arguments.getBoolean("mIsFromShare");
        }
    }

    protected void mo6458A() {
        UiThreadExecutor.a("", new C43091(this), 0);
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
