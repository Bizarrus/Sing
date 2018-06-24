package com.smule.singandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class WebViewFragment_ extends WebViewFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f20501h = new OnViewChangedNotifier();
    private View f20502i;

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, WebViewFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f20501h);
        m22024a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f20502i == null) {
            return null;
        }
        return this.f20502i.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f20502i = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f20502i == null) {
            this.f20502i = layoutInflater.inflate(C1947R.layout.web_view_fragment, viewGroup, false);
        }
        return this.f20502i;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f20502i = null;
        this.f = null;
        this.g = null;
    }

    private void m22024a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f20501h.a(this);
    }

    public void m22027a(HasViews hasViews) {
        this.f = (FrameLayout) hasViews.findViewById(C1947R.id.web_view_container);
        this.g = hasViews.findViewById(C1947R.id.loading_view);
        m22022a();
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
