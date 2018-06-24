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
 *  android.os.Parcelable
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.support.v4.app.ActivityCompat
 *  android.support.v4.app.Fragment
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  com.smule.singandroid.textviews.AutoResizeTextView
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.builder.ActivityIntentBuilder
 *  org.androidannotations.api.builder.IntentBuilder
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
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.PostSingBundle;
import com.smule.singandroid.PostSongUpsellActivity;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.PurchaseButton;
import com.smule.singandroid.textviews.AutoResizeTextView;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.IntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

@SuppressLint(value={"Registered"})
public final class PostSongUpsellActivity_
extends PostSongUpsellActivity
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier v = new OnViewChangedNotifier();

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.b();
    }

    private void b() {
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null && bundle.containsKey("mPostSingBundle")) {
            this.u = (PostSingBundle)bundle.getParcelable("mPostSingBundle");
        }
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        PostSongUpsellActivity_.super.a(runTimePermissionsRequest, resultCallback);
    }

    @Override
    public void a(HasViews hasViews) {
        this.g = (AutoResizeTextView)hasViews.findViewById(2131756281);
        this.h = (TextView)hasViews.findViewById(2131756285);
        this.i = (TextView)hasViews.findViewById(2131756287);
        this.j = (TextView)hasViews.findViewById(2131756289);
        this.k = (ProfileImageWithVIPBadge)hasViews.findViewById(2131756167);
        this.l = (PurchaseButton)hasViews.findViewById(2131756283);
        this.m = (PurchaseButton)hasViews.findViewById(2131756290);
        this.n = (Button)hasViews.findViewById(2131755376);
        this.o = (ProgressBar)hasViews.findViewById(2131756280);
        this.p = (ImageView)hasViews.findViewById(2131756284);
        this.q = (ImageView)hasViews.findViewById(2131756286);
        this.r = (ImageView)hasViews.findViewById(2131756288);
        this.e();
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.v);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
        this.setContentView(2130903358);
    }

    public void setContentView(int n) {
        super.setContentView(n);
        this.v.a((HasViews)this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.v.a((HasViews)this);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.v.a((HasViews)this);
    }

    public void setIntent(Intent intent) {
        super.setIntent(intent);
        this.b();
    }

    public static class IntentBuilder_
    extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment d;
        private android.support.v4.app.Fragment e;

        public IntentBuilder_(Context context) {
            super(context, PostSongUpsellActivity_.class);
        }

        public IntentBuilder_ a(PostSingBundle postSingBundle) {
            return (IntentBuilder_)super.a("mPostSingBundle", (Parcelable)postSingBundle);
        }

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

