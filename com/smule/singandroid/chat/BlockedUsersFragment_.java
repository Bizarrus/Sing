package com.smule.singandroid.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class BlockedUsersFragment_ extends BlockedUsersFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f20717i = new OnViewChangedNotifier();
    private View f20718j;

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, BlockedUsersFragment> {
        public BlockedUsersFragment m22322a() {
            BlockedUsersFragment blockedUsersFragment_ = new BlockedUsersFragment_();
            blockedUsersFragment_.setArguments(this.a);
            return blockedUsersFragment_;
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f20717i);
        m22324a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f20718j == null) {
            return null;
        }
        return this.f20718j.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f20718j = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f20718j == null) {
            this.f20718j = layoutInflater.inflate(C1947R.layout.blocked_users_fragment, viewGroup, false);
        }
        return this.f20718j;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f20718j = null;
        this.f = null;
        this.g = null;
    }

    private void m22324a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f20717i.a(this);
    }

    public static FragmentBuilder_ m22323A() {
        return new FragmentBuilder_();
    }

    public void m22327a(HasViews hasViews) {
        this.f = (MagicListView) hasViews.findViewById(C1947R.id.user_list);
        this.g = hasViews.findViewById(C1947R.id.network_issue);
        m22321z();
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
