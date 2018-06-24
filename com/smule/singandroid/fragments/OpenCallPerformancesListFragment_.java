package com.smule.singandroid.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.MediaListView;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class OpenCallPerformancesListFragment_ extends OpenCallPerformancesListFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f22618l = new OnViewChangedNotifier();
    private View f22619m;

    class C45861 implements OnClickListener {
        final /* synthetic */ OpenCallPerformancesListFragment_ f22617a;

        C45861(OpenCallPerformancesListFragment_ openCallPerformancesListFragment_) {
            this.f22617a = openCallPerformancesListFragment_;
        }

        public void onClick(View view) {
            this.f22617a.m24078A();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, OpenCallPerformancesListFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f22618l);
        m24086a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f22619m == null) {
            return null;
        }
        return this.f22619m.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f22619m = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f22619m == null) {
            this.f22619m = layoutInflater.inflate(C1947R.layout.open_call_performances_fragment, viewGroup, false);
        }
        return this.f22619m;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f22619m = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
    }

    private void m24086a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m24088b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f22618l.a(this);
    }

    public void m24090a(HasViews hasViews) {
        this.g = hasViews.findViewById(C1947R.id.bookmark_banner);
        this.h = (TextView) hasViews.findViewById(C1947R.id.bookmark_banner_title);
        this.i = hasViews.findViewById(C1947R.id.performances_list_container);
        this.j = (MediaListView) hasViews.findViewById(C1947R.id.performance_list_view);
        View findViewById = hasViews.findViewById(C1947R.id.bookmark_banner_ok_button);
        if (findViewById != null) {
            findViewById.setOnClickListener(new C45861(this));
        }
        m24079a();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mOpenCall", this.e);
    }

    private void m24088b(Bundle bundle) {
        if (bundle != null) {
            this.e = (PerformanceV2) bundle.getParcelable("mOpenCall");
        }
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
