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
 *  android.widget.RelativeLayout
 *  com.smule.singandroid.songbook.LongPressIndicatorView
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.UiThreadExecutor
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
import android.widget.RelativeLayout;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.customviews.BottomNavView;
import com.smule.singandroid.customviews.MasterToolbar;
import com.smule.singandroid.customviews.OverlayWithRectangularHoleImageView;
import com.smule.singandroid.songbook.LongPressIndicatorView;
import java.io.Serializable;
import java.util.HashMap;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

@SuppressLint(value={"Registered"})
public final class MasterActivity_
extends MasterActivity
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier z = new OnViewChangedNotifier();

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.j = (Integer)bundle.getSerializable("mSelectedMenuItem");
        this.o = (HashMap)bundle.getSerializable("mSongbookSectionListViewStates");
        this.p = bundle.getString("mLastSongbookSectionId");
        this.q = bundle.getBoolean("mVisitedFeed");
        this.r = bundle.getLong("mLastMessageAlert");
    }

    @Override
    public void C() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                MasterActivity_.super.C();
            }
        }, (long)0);
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        MasterActivity_.super.a(runTimePermissionsRequest, resultCallback);
    }

    @Override
    protected void a(final String string2, final String string3, final String string4, final String string5, final String string6, final String string7, final String string8) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                MasterActivity_.super.a(string2, string3, string4, string5, string6, string7, string8);
            }
        }, (long)0);
    }

    @Override
    public void a(HasViews hasViews) {
        this.t = (MasterToolbar)hasViews.findViewById(2131755449);
        this.u = hasViews.findViewById(2131755223);
        this.v = (RelativeLayout)hasViews.findViewById(2131755458);
        this.w = (RelativeLayout)hasViews.findViewById(2131756149);
        this.x = hasViews.findViewById(2131755945);
        this.h = (ViewGroup)hasViews.findViewById(2131755221);
        this.i = (BottomNavView)hasViews.findViewById(2131755946);
        this.k = (OverlayWithRectangularHoleImageView)hasViews.findViewById(2131755258);
        this.l = hasViews.findViewById(2131755947);
        this.m = (LongPressIndicatorView)hasViews.findViewById(2131755949);
        if (this.w != null) {
            this.w.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    MasterActivity_.this.w();
                }
            });
        }
        this.e();
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.z);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
        this.setContentView(2130903283);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("mSelectedMenuItem", (Serializable)this.j);
        bundle.putSerializable("mSongbookSectionListViewStates", (Serializable)this.o);
        bundle.putString("mLastSongbookSectionId", this.p);
        bundle.putBoolean("mVisitedFeed", this.q);
        bundle.putLong("mLastMessageAlert", this.r);
    }

    public void setContentView(int n) {
        super.setContentView(n);
        this.z.a((HasViews)this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.z.a((HasViews)this);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.z.a((HasViews)this);
    }

    @Override
    public void v() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                MasterActivity_.super.v();
            }
        }, (long)0);
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

