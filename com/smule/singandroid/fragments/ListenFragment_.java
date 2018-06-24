package com.smule.singandroid.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import com.smule.android.network.models.RecPerformanceIcon;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.C1947R;
import java.util.ArrayList;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ListenFragment_ extends ListenFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f22411q = new OnViewChangedNotifier();
    private View f22412r;

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, ListenFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f22411q);
        m23820a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f22412r == null) {
            return null;
        }
        return this.f22412r.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f22412r = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f22412r == null) {
            this.f22412r = layoutInflater.inflate(C1947R.layout.listen_fragment, viewGroup, false);
        }
        return this.f22412r;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f22412r = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
    }

    private void m23820a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f22411q.a(this);
    }

    public void m23829a(HasViews hasViews) {
        this.f = hasViews.findViewById(C1947R.id.performances_list_container);
        this.g = hasViews.findViewById(C1947R.id.loading_view);
        this.h = (TextView) hasViews.findViewById(C1947R.id.loading_performances_text);
        this.i = hasViews.findViewById(C1947R.id.no_performances_view);
        this.j = (TextView) hasViews.findViewById(C1947R.id.no_performances_text);
        this.k = (GridView) hasViews.findViewById(C1947R.id.performance_grid_view);
        m23819z();
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }

    protected void mo6823B() {
        BackgroundExecutor.a();
        super.mo6823B();
    }

    protected void mo6826d(int i) {
        BackgroundExecutor.a();
        super.mo6826d(i);
    }

    public void mo6824a(ArrayList<RecPerformanceIcon> arrayList) {
        BackgroundExecutor.a();
        super.mo6824a((ArrayList) arrayList);
    }

    public void mo6825b(ArrayList<RecPerformanceIcon> arrayList) {
        BackgroundExecutor.a();
        super.mo6825b((ArrayList) arrayList);
    }
}
