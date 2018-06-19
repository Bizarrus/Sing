package com.smule.singandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FindFriendsSearchFragment_ extends FindFriendsSearchFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f18744k = new OnViewChangedNotifier();
    private View f18745l;

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, FindFriendsSearchFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f18744k);
        m20209a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f18745l == null) {
            return null;
        }
        return this.f18745l.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f18745l = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f18745l == null) {
            this.f18745l = layoutInflater.inflate(C1947R.layout.find_friends_search_fragment, viewGroup, false);
        }
        return this.f18745l;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f18745l = null;
        this.f = null;
        this.g = null;
    }

    private void m20209a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m20211b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f18744k.a(this);
    }

    public void m20213a(HasViews hasViews) {
        this.f = (MagicListView) hasViews.findViewById(C1947R.id.search_listview);
        this.g = (LinearLayout) hasViews.findViewById(C1947R.id.find_friends_search_initial_view);
        m20205a();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("mSearchText", this.h);
    }

    private void m20211b(Bundle bundle) {
        if (bundle != null) {
            this.h = bundle.getString("mSearchText");
        }
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
