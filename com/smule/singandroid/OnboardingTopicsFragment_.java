package com.smule.singandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class OnboardingTopicsFragment_ extends OnboardingTopicsFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f19010j = new OnViewChangedNotifier();
    private View f19011k;

    class C38971 implements OnClickListener {
        final /* synthetic */ OnboardingTopicsFragment_ f19007a;

        C38971(OnboardingTopicsFragment_ onboardingTopicsFragment_) {
            this.f19007a = onboardingTopicsFragment_;
        }

        public void onClick(View view) {
            this.f19007a.m20521z();
        }
    }

    class C38982 implements OnClickListener {
        final /* synthetic */ OnboardingTopicsFragment_ f19008a;

        C38982(OnboardingTopicsFragment_ onboardingTopicsFragment_) {
            this.f19008a = onboardingTopicsFragment_;
        }

        public void onClick(View view) {
            this.f19008a.m20515A();
        }
    }

    class C38993 implements OnClickListener {
        final /* synthetic */ OnboardingTopicsFragment_ f19009a;

        C38993(OnboardingTopicsFragment_ onboardingTopicsFragment_) {
            this.f19009a = onboardingTopicsFragment_;
        }

        public void onClick(View view) {
            this.f19009a.m20516B();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, OnboardingTopicsFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f19010j);
        m20522a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f19011k == null) {
            return null;
        }
        return this.f19011k.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f19011k = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f19011k == null) {
            this.f19011k = layoutInflater.inflate(C1947R.layout.onboarding_topics_fragment, viewGroup, false);
        }
        return this.f19011k;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f19011k = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
    }

    private void m20522a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f19010j.a(this);
    }

    public void m20525a(HasViews hasViews) {
        this.e = (GridViewWithHeaderAndFooter) hasViews.findViewById(C1947R.id.topics_grid_view);
        this.f = (TextView) hasViews.findViewById(C1947R.id.next_button);
        this.g = (TextView) hasViews.findViewById(C1947R.id.debug_button);
        this.h = (ProgressBar) hasViews.findViewById(C1947R.id.center_loading_spinner);
        View findViewById = hasViews.findViewById(C1947R.id.skip_button);
        if (this.g != null) {
            this.g.setOnClickListener(new C38971(this));
        }
        if (this.f != null) {
            this.f.setOnClickListener(new C38982(this));
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new C38993(this));
        }
        m20517a();
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
