/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.support.constraint.ConstraintLayout
 *  android.support.v4.widget.SwipeRefreshLayout
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.TextView
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.UiThreadExecutor
 *  org.androidannotations.api.builder.FragmentBuilder
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.FeedFragment;
import com.smule.singandroid.customviews.MediaListView;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FeedFragment_
extends FeedFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier w = new OnViewChangedNotifier();
    private View x;

    public static FragmentBuilder_ L() {
        return new FragmentBuilder_();
    }

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.o = bundle.getInt("mExpandedOriginalIdx");
    }

    @Override
    protected void F() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                FeedFragment_.super.F();
            }
        }, (long)100);
    }

    @Override
    protected void H() {
        BackgroundExecutor.a();
        FeedFragment_.super.H();
    }

    @Override
    protected void I() {
        BackgroundExecutor.a();
        FeedFragment_.super.I();
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        FeedFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.i = (MediaListView)hasViews.findViewById(2131755751);
        this.j = (SwipeRefreshLayout)hasViews.findViewById(2131755750);
        this.k = (ConstraintLayout)hasViews.findViewById(2131755749);
        this.l = hasViews.findViewById(2131755305);
        this.m = (TextView)hasViews.findViewById(2131755306);
        View view = hasViews.findViewById(2131755307);
        hasViews = hasViews.findViewById(2131755794);
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    FeedFragment_.this.J();
                }
            });
        }
        if (hasViews != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    FeedFragment_.this.K();
                }
            });
        }
        this.t();
    }

    public View findViewById(int n) {
        if (this.x == null) {
            return null;
        }
        return this.x.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.w);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.x = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.x == null) {
            this.x = layoutInflater.inflate(2130903228, viewGroup, false);
        }
        return this.x;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.x = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("mExpandedOriginalIdx", this.o);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.w.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, FeedFragment> {
        public FeedFragment a() {
            FeedFragment_ feedFragment_ = new FeedFragment_();
            feedFragment_.setArguments(this.a);
            return feedFragment_;
        }
    }

}

