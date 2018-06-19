package com.smule.singandroid;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PerformanceSaveActivity_ extends PerformanceSaveActivity implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f19133i = new OnViewChangedNotifier();

    public static class IntentBuilder_ extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment f19131d;
        private android.support.v4.app.Fragment f19132e;

        public PostActivityStarter m20669a(int i) {
            if (this.f19132e != null) {
                this.f19132e.startActivityForResult(this.c, i);
            } else if (this.f19131d != null) {
                this.f19131d.startActivityForResult(this.c, i, this.a);
            } else if (this.b instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) this.b, this.c, i, this.a);
            } else {
                this.b.startActivity(this.c, this.a);
            }
            return new PostActivityStarter(this.b);
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f19133i);
        m20670a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
        setContentView((int) C1947R.layout.performance_save_activity);
    }

    private void m20670a(Bundle bundle) {
        m20672b(bundle);
        OnViewChangedNotifier.a(this);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        this.f19133i.a(this);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.f19133i.a(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.f19133i.a(this);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mPostSingBundle", this.g);
    }

    private void m20672b(Bundle bundle) {
        if (bundle != null) {
            this.g = (PostSingBundle) bundle.getParcelable("mPostSingBundle");
        }
    }

    public void m20674a(HasViews hasViews) {
        c();
    }

    public void m20673a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.a(runTimePermissionsRequest, resultCallback);
    }
}
