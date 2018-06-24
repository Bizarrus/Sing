package com.smule.singandroid.songbook_search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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

public final class SearchHistoryFragment_ extends SearchHistoryFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f24141g = new OnViewChangedNotifier();
    private View f24142h;

    class C48931 implements OnClickListener {
        final /* synthetic */ SearchHistoryFragment_ f24140a;

        C48931(SearchHistoryFragment_ searchHistoryFragment_) {
            this.f24140a = searchHistoryFragment_;
        }

        public void onClick(View view) {
            this.f24140a.m25343z();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, SearchHistoryFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f24141g);
        m25344a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f24142h == null) {
            return null;
        }
        return this.f24142h.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f24142h = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f24142h == null) {
            this.f24142h = layoutInflater.inflate(C1947R.layout.songbook_search_history_fragment_layout, viewGroup, false);
        }
        return this.f24142h;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f24142h = null;
        this.e = null;
    }

    private void m25344a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m25346b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f24141g.a(this);
    }

    public void m25348a(HasViews hasViews) {
        this.e = (MagicListView) hasViews.findViewById(C1947R.id.history_listview);
        View findViewById = hasViews.findViewById(C1947R.id.search_app_bar);
        if (findViewById != null) {
            findViewById.setOnClickListener(new C48931(this));
        }
        m25340a();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("mIsRecent", this.f);
    }

    private void m25346b(Bundle bundle) {
        if (bundle != null) {
            this.f = bundle.getBoolean("mIsRecent");
        }
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
