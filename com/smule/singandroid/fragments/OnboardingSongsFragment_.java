package com.smule.singandroid.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class OnboardingSongsFragment_ extends OnboardingSongsFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f22584n = new OnViewChangedNotifier();
    private View f22585o;

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, OnboardingSongsFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f22584n);
        m24030a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f22585o == null) {
            return null;
        }
        return this.f22585o.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f22585o = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f22585o == null) {
            this.f22585o = layoutInflater.inflate(C1947R.layout.onboarding_songs_fragment, viewGroup, false);
        }
        return this.f22585o;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f22585o = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
    }

    private void m24030a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f22584n.a(this);
    }

    public void m24037a(HasViews hasViews) {
        this.e = (MediaListView) hasViews.findViewById(C1947R.id.songs_list);
        this.f = hasViews.findViewById(C1947R.id.songs_container);
        this.g = (LinearLayout) hasViews.findViewById(C1947R.id.loading_view);
        this.h = (TextView) hasViews.findViewById(C1947R.id.greeting_title);
        this.i = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.user_image);
        this.j = hasViews.findViewById(C1947R.id.onboarding_top_section);
        this.k = hasViews.findViewById(C1947R.id.top_section);
        this.l = hasViews.findViewById(C1947R.id.onboarding_relative_layout);
        this.m = hasViews.findViewById(C1947R.id.onboarding_background);
        m24017a();
    }

    protected void mo6848a(final CustomAlertDialogListener customAlertDialogListener) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ OnboardingSongsFragment_ f22579b;

            public void run() {
                super.mo6848a(customAlertDialogListener);
            }
        }, 0);
    }

    protected void mo6849b(final CustomAlertDialogListener customAlertDialogListener) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ OnboardingSongsFragment_ f22581b;

            public void run() {
                super.mo6849b(customAlertDialogListener);
            }
        }, 0);
    }

    protected void mo6850c(final CustomAlertDialogListener customAlertDialogListener) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ OnboardingSongsFragment_ f22583b;

            public void run() {
                super.mo6850c(customAlertDialogListener);
            }
        }, 0);
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
