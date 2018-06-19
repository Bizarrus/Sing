package com.smule.singandroid;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class OnboardingActivity_ extends OnboardingActivity implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f18984i = new OnViewChangedNotifier();

    public static class IntentBuilder_ extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment f18982d;
        private android.support.v4.app.Fragment f18983e;

        public PostActivityStarter m20477a(int i) {
            if (this.f18983e != null) {
                this.f18983e.startActivityForResult(this.c, i);
            } else if (this.f18982d != null) {
                this.f18982d.startActivityForResult(this.c, i, this.a);
            } else if (this.b instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) this.b, this.c, i, this.a);
            } else {
                this.b.startActivity(this.c, this.a);
            }
            return new PostActivityStarter(this.b);
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f18984i);
        m20478a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
        setContentView((int) C1947R.layout.onboarding_activity);
    }

    private void m20478a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        this.f18984i.a(this);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.f18984i.a(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.f18984i.a(this);
    }

    public void m20481a(HasViews hasViews) {
        this.g = (RelativeLayout) hasViews.findViewById(C1947R.id.fragment_content_view);
        c();
    }

    public void m20480a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.a(runTimePermissionsRequest, resultCallback);
    }
}
