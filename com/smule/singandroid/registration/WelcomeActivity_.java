/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.Activity
 *  android.app.Fragment
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.support.v4.app.ActivityCompat
 *  android.support.v4.app.Fragment
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.builder.ActivityIntentBuilder
 *  org.androidannotations.api.builder.PostActivityStarter
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.registration;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.customviews.MasterToolbar;
import com.smule.singandroid.registration.WelcomeActivity;
import java.io.Serializable;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

@SuppressLint(value={"Registered"})
public final class WelcomeActivity_
extends WelcomeActivity
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier q = new OnViewChangedNotifier();

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.j = (WelcomeActivity.State)((Object)bundle.getSerializable("mState"));
        this.k = (WelcomeActivity.State)((Object)bundle.getSerializable("mNextState"));
        this.l = bundle.getString("mParamHandle");
        this.m = bundle.getBoolean("mParamHandlePrefill");
        this.n = (Object)bundle.getSerializable("mParamLoginMethod");
        this.o = bundle.getBoolean("mParamShowOptIn");
        this.p = bundle.getBoolean("mParamFacebookPhotoAutoUploaded");
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        WelcomeActivity_.super.a(runTimePermissionsRequest, resultCallback);
    }

    @Override
    public void a(HasViews hasViews) {
        this.t = (MasterToolbar)hasViews.findViewById(2131755449);
        this.u = hasViews.findViewById(2131755223);
        this.v = (RelativeLayout)hasViews.findViewById(2131755458);
        this.w = (RelativeLayout)hasViews.findViewById(2131756149);
        this.x = hasViews.findViewById(2131755945);
        this.g = (Button)hasViews.findViewById(2131756820);
        this.h = (TextView)hasViews.findViewById(2131755176);
        this.i = (ImageView)hasViews.findViewById(2131755226);
        if (this.g != null) {
            this.g.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    WelcomeActivity_.this.t();
                }
            });
        }
        this.e();
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.q);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
        this.setContentView(2130903467);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("mState", (Serializable)((Object)this.j));
        bundle.putSerializable("mNextState", (Serializable)((Object)this.k));
        bundle.putString("mParamHandle", this.l);
        bundle.putBoolean("mParamHandlePrefill", this.m);
        bundle.putSerializable("mParamLoginMethod", (Serializable)((Object)this.n));
        bundle.putBoolean("mParamShowOptIn", this.o);
        bundle.putBoolean("mParamFacebookPhotoAutoUploaded", this.p);
    }

    public void setContentView(int n) {
        super.setContentView(n);
        this.q.a((HasViews)this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.q.a((HasViews)this);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.q.a((HasViews)this);
    }

    public static class IntentBuilder_
    extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment d;
        private android.support.v4.app.Fragment e;

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public PostActivityStarter a(int n) {
            if (this.e != null) {
                this.e.startActivityForResult(this.c, n);
                do {
                    return new PostActivityStarter(this.b);
                    break;
                } while (true);
            }
            if (this.d != null) {
                this.d.startActivityForResult(this.c, n, this.a);
                return new PostActivityStarter(this.b);
            }
            if (this.b instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity)((Activity)this.b), (Intent)this.c, (int)n, (Bundle)this.a);
                return new PostActivityStarter(this.b);
            }
            this.b.startActivity(this.c, this.a);
            return new PostActivityStarter(this.b);
        }
    }

}

