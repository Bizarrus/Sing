package com.smule.singandroid.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class JoinersListFragment_ extends JoinersListFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f22374l = new OnViewChangedNotifier();
    private View f22375m;

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, JoinersListFragment> {
        public JoinersListFragment m23760a() {
            JoinersListFragment joinersListFragment_ = new JoinersListFragment_();
            joinersListFragment_.setArguments(this.a);
            return joinersListFragment_;
        }

        public FragmentBuilder_ m23761a(PerformanceV2 performanceV2) {
            this.a.putParcelable("mPerformance", performanceV2);
            return this;
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f22374l);
        m23763a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f22375m == null) {
            return null;
        }
        return this.f22375m.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f22375m = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f22375m == null) {
            this.f22375m = layoutInflater.inflate(C1947R.layout.joiners_list_fragment, viewGroup, false);
        }
        return this.f22375m;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f22375m = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
    }

    private void m23763a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m23765z();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f22374l.a(this);
    }

    public static FragmentBuilder_ m23762a() {
        return new FragmentBuilder_();
    }

    public void m23767a(HasViews hasViews) {
        this.f = (ViewGroup) hasViews.findViewById(C1947R.id.joiners_view);
        this.g = (ViewGroup) hasViews.findViewById(C1947R.id.network_issue);
        this.h = (TextView) hasViews.findViewById(C1947R.id.joiners_view_joiners_count_text_view);
        this.i = (ListView) hasViews.findViewById(C1947R.id.joiners_view_list_view);
        this.j = (ProgressBar) hasViews.findViewById(C1947R.id.spinner);
    }

    private void m23765z() {
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("mPerformance")) {
            this.k = (PerformanceV2) arguments.getParcelable("mPerformance");
        }
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
