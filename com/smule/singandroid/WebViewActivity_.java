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
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.builder.ActivityIntentBuilder
 *  org.androidannotations.api.builder.PostActivityStarter
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid;

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
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.WebViewActivity;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

@SuppressLint(value={"Registered"})
public final class WebViewActivity_
extends WebViewActivity
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier k = new OnViewChangedNotifier();

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.h = bundle.getString("mUrl");
        this.i = bundle.getBoolean("mShowCloseView");
        this.j = bundle.getBoolean("mUseApplicationContext");
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        WebViewActivity_.super.a(runTimePermissionsRequest, resultCallback);
    }

    @Override
    public void a(HasViews hasViews) {
        this.g = hasViews.findViewById(2131756818);
        this.e();
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.k);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
        this.setContentView(2130903465);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("mUrl", this.h);
        bundle.putBoolean("mShowCloseView", this.i);
        bundle.putBoolean("mUseApplicationContext", this.j);
    }

    public void setContentView(int n) {
        super.setContentView(n);
        this.k.a((HasViews)this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.k.a((HasViews)this);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.k.a((HasViews)this);
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

