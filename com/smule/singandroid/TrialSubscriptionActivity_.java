package com.smule.singandroid;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class TrialSubscriptionActivity_ extends TrialSubscriptionActivity implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f20432k = new OnViewChangedNotifier();

    class C42031 implements OnClickListener {
        final /* synthetic */ TrialSubscriptionActivity_ f20425a;

        C42031(TrialSubscriptionActivity_ trialSubscriptionActivity_) {
            this.f20425a = trialSubscriptionActivity_;
        }

        public void onClick(View view) {
            this.f20425a.m21951a();
        }
    }

    class C42042 implements OnClickListener {
        final /* synthetic */ TrialSubscriptionActivity_ f20426a;

        C42042(TrialSubscriptionActivity_ trialSubscriptionActivity_) {
            this.f20426a = trialSubscriptionActivity_;
        }

        public void onClick(View view) {
            this.f20426a.m21952b();
        }
    }

    class C42053 implements Runnable {
        final /* synthetic */ TrialSubscriptionActivity_ f20427a;

        C42053(TrialSubscriptionActivity_ trialSubscriptionActivity_) {
            this.f20427a = trialSubscriptionActivity_;
        }

        public void run() {
            super.mo6675q();
        }
    }

    public static class IntentBuilder_ extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment f20430d;
        private android.support.v4.app.Fragment f20431e;

        public PostActivityStarter m21958a(int i) {
            if (this.f20431e != null) {
                this.f20431e.startActivityForResult(this.c, i);
            } else if (this.f20430d != null) {
                this.f20430d.startActivityForResult(this.c, i, this.a);
            } else if (this.b instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) this.b, this.c, i, this.a);
            } else {
                this.b.startActivity(this.c, this.a);
            }
            return new PostActivityStarter(this.b);
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f20432k);
        m21959a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
        setContentView((int) C1947R.layout.trial_subscription_activity);
    }

    private void m21959a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        this.f20432k.a(this);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.f20432k.a(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.f20432k.a(this);
    }

    public void m21964a(HasViews hasViews) {
        this.g = (Button) hasViews.findViewById(C1947R.id.start_button);
        this.h = (Button) hasViews.findViewById(C1947R.id.cancel_button);
        this.i = (TextView) hasViews.findViewById(C1947R.id.only_xxx_after);
        if (this.g != null) {
            this.g.setOnClickListener(new C42031(this));
        }
        if (this.h != null) {
            this.h.setOnClickListener(new C42042(this));
        }
        c();
    }

    protected void mo6675q() {
        UiThreadExecutor.a("", new C42053(this), 0);
    }

    protected void mo6674c(final String str) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ TrialSubscriptionActivity_ f20429b;

            public void run() {
                super.mo6674c(str);
            }
        }, 0);
    }

    public void m21963a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.a(runTimePermissionsRequest, resultCallback);
    }
}
