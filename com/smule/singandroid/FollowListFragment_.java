package com.smule.singandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FollowListFragment_ extends FollowListFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f18754j = new OnViewChangedNotifier();
    private View f18755k;

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, FollowListFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f18754j);
        m20227a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f18755k == null) {
            return null;
        }
        return this.f18755k.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f18755k = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f18755k == null) {
            this.f18755k = layoutInflater.inflate(C1947R.layout.follow_list_fragment, viewGroup, false);
        }
        return this.f18755k;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f18755k = null;
        this.i = null;
    }

    private void m20227a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m20229b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f18754j.a(this);
    }

    public void m20231a(HasViews hasViews) {
        this.i = (MagicListView) hasViews.findViewById(C1947R.id.follow_list_view);
        m20224a();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putLong("mAccountId", this.e);
        bundle.putString("mPictureUrl", this.f);
        bundle.putInt("mFollowListDisplayMode", this.g);
        bundle.putInt("mFollowListCreatedCount", this.h);
    }

    private void m20229b(Bundle bundle) {
        if (bundle != null) {
            this.e = bundle.getLong("mAccountId");
            this.f = bundle.getString("mPictureUrl");
            this.g = bundle.getInt("mFollowListDisplayMode");
            this.h = bundle.getInt("mFollowListCreatedCount");
        }
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
