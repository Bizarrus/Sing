package com.smule.singandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PhotoTakingFragment_ extends PhotoTakingFragment implements HasViews {
    private final OnViewChangedNotifier f19313e = new OnViewChangedNotifier();
    private View f19314f;

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, PhotoTakingFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f19313e);
        m20863a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f19314f == null) {
            return null;
        }
        return this.f19314f.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f19314f = super.onCreateView(layoutInflater, viewGroup, bundle);
        return this.f19314f;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f19314f = null;
    }

    private void m20863a(Bundle bundle) {
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f19313e.a(this);
    }

    protected void mo6522c(final NetworkResponse networkResponse) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ PhotoTakingFragment_ f19312b;

            public void run() {
                super.mo6522c(networkResponse);
            }
        }, 0);
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
