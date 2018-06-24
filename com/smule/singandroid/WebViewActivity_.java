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

public final class WebViewActivity_ extends WebViewActivity implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f20484k = new OnViewChangedNotifier();

    public static class IntentBuilder_ extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment f20482d;
        private android.support.v4.app.Fragment f20483e;

        public PostActivityStarter m22003a(int i) {
            if (this.f20483e != null) {
                this.f20483e.startActivityForResult(this.c, i);
            } else if (this.f20482d != null) {
                this.f20482d.startActivityForResult(this.c, i, this.a);
            } else if (this.b instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) this.b, this.c, i, this.a);
            } else {
                this.b.startActivity(this.c, this.a);
            }
            return new PostActivityStarter(this.b);
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f20484k);
        m22004a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
        setContentView((int) C1947R.layout.web_view_activity);
    }

    private void m22004a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m22006b(bundle);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        this.f20484k.a(this);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.f20484k.a(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.f20484k.a(this);
    }

    public void m22008a(HasViews hasViews) {
        this.g = hasViews.findViewById(C1947R.id.close_button_view);
        c();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("mUrl", this.h);
        bundle.putBoolean("mShowCloseView", this.i);
        bundle.putBoolean("mUseApplicationContext", this.j);
    }

    private void m22006b(Bundle bundle) {
        if (bundle != null) {
            this.h = bundle.getString("mUrl");
            this.i = bundle.getBoolean("mShowCloseView");
            this.j = bundle.getBoolean("mUseApplicationContext");
        }
    }

    public void m22007a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.a(runTimePermissionsRequest, resultCallback);
    }
}
