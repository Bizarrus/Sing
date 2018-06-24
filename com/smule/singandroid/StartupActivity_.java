package com.smule.singandroid;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class StartupActivity_ extends StartupActivity implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f20400h = new OnViewChangedNotifier();

    public static class IntentBuilder_ extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment f20398d;
        private android.support.v4.app.Fragment f20399e;

        public PostActivityStarter m21922a(int i) {
            if (this.f20399e != null) {
                this.f20399e.startActivityForResult(this.c, i);
            } else if (this.f20398d != null) {
                this.f20398d.startActivityForResult(this.c, i, this.a);
            } else if (this.b instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) this.b, this.c, i, this.a);
            } else {
                this.b.startActivity(this.c, this.a);
            }
            return new PostActivityStarter(this.b);
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f20400h);
        m21923a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
        setContentView((int) C1947R.layout.startup_splash_screen);
    }

    private void m21923a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        this.f20400h.a(this);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.f20400h.a(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.f20400h.a(this);
    }

    public void m21931a(HasViews hasViews) {
        this.g = (ProgressBar) hasViews.findViewById(C1947R.id.spinner);
        c();
    }

    public void m21929a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.a(runTimePermissionsRequest, resultCallback);
    }

    protected void mo6668a() {
        BackgroundExecutor.a();
        super.mo6668a();
    }

    protected void mo6670b() {
        BackgroundExecutor.a();
        super.mo6670b();
    }

    protected void mo6669a(@NonNull ErrorReason errorReason, NetworkResponse networkResponse) {
        BackgroundExecutor.a();
        super.mo6669a(errorReason, networkResponse);
    }
}
