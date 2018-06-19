package com.smule.singandroid;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
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

public final class PostSingFlowActivity_ extends PostSingFlowActivity implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f19349k = new OnViewChangedNotifier();

    public static class IntentBuilder_ extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment f19347d;
        private android.support.v4.app.Fragment f19348e;

        public IntentBuilder_(Context context) {
            super(context, PostSingFlowActivity_.class);
        }

        public PostActivityStarter m20900a(int i) {
            if (this.f19348e != null) {
                this.f19348e.startActivityForResult(this.c, i);
            } else if (this.f19347d != null) {
                this.f19347d.startActivityForResult(this.c, i, this.a);
            } else if (this.b instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) this.b, this.c, i, this.a);
            } else {
                this.b.startActivity(this.c, this.a);
            }
            return new PostActivityStarter(this.b);
        }

        public IntentBuilder_ m20899a(PostSingBundle postSingBundle) {
            return (IntentBuilder_) super.a("mPostSingBundle", postSingBundle);
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f19349k);
        m20901a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    private void m20901a(Bundle bundle) {
        m20903b();
        m20904b(bundle);
        OnViewChangedNotifier.a(this);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        this.f19349k.a(this);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.f19349k.a(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.f19349k.a(this);
    }

    private void m20903b() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("mPostSingBundle")) {
            this.h = (PostSingBundle) extras.getParcelable("mPostSingBundle");
        }
    }

    public void setIntent(Intent intent) {
        super.setIntent(intent);
        m20903b();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putStringArrayList("completedTaskKeys", this.i);
    }

    private void m20904b(Bundle bundle) {
        if (bundle != null) {
            this.i = bundle.getStringArrayList("completedTaskKeys");
        }
    }

    public void m20906a(HasViews hasViews) {
        c();
    }

    public void m20905a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.a(runTimePermissionsRequest, resultCallback);
    }
}
