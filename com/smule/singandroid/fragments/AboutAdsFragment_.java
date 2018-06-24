package com.smule.singandroid.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class AboutAdsFragment_ extends AboutAdsFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f22343g = new OnViewChangedNotifier();
    private View f22344h;

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, AboutAdsFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f22343g);
        m23729a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f22344h == null) {
            return null;
        }
        return this.f22344h.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f22344h = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f22344h == null) {
            this.f22344h = layoutInflater.inflate(C1947R.layout.native_ad_about_ads, viewGroup, false);
        }
        return this.f22344h;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f22344h = null;
        this.f = null;
    }

    private void m23729a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f22343g.a(this);
    }

    public void m23732a(HasViews hasViews) {
        this.f = (TextView) hasViews.findViewById(C1947R.id.about_ad_paywall_link);
        m23728z();
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
