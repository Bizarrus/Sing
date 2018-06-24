package com.smule.singandroid.registration;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import com.smule.android.logging.Analytics.HandleUpdateType;
import com.smule.android.logging.Analytics.ProfilePicType;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class NewHandleActivity_ extends NewHandleActivity implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f23879k = new OnViewChangedNotifier();

    public static class IntentBuilder_ extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment f23877d;
        private android.support.v4.app.Fragment f23878e;

        public PostActivityStarter m25148a(int i) {
            if (this.f23878e != null) {
                this.f23878e.startActivityForResult(this.c, i);
            } else if (this.f23877d != null) {
                this.f23877d.startActivityForResult(this.c, i, this.a);
            } else if (this.b instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) this.b, this.c, i, this.a);
            } else {
                this.b.startActivity(this.c, this.a);
            }
            return new PostActivityStarter(this.b);
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23879k);
        m25149a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    private void m25149a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m25151b(bundle);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        this.f23879k.a(this);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.f23879k.a(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.f23879k.a(this);
    }

    public void m25153a(HasViews hasViews) {
        this.j = (CheckBox) hasViews.findViewById(C1947R.id.email_opt_in);
        c();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("mHandleUpdateType", this.g);
        bundle.putSerializable("mProfilePicType", this.h);
        bundle.putBoolean("mShowEmailOpt", this.i);
    }

    private void m25151b(Bundle bundle) {
        if (bundle != null) {
            this.g = (HandleUpdateType) bundle.getSerializable("mHandleUpdateType");
            this.h = (ProfilePicType) bundle.getSerializable("mProfilePicType");
            this.i = bundle.getBoolean("mShowEmailOpt");
        }
    }

    public void m25152a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.a(runTimePermissionsRequest, resultCallback);
    }
}
