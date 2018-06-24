/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ListView
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.builder.FragmentBuilder
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.fragments.JoinersListFragment;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class JoinersListFragment_
extends JoinersListFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier n = new OnViewChangedNotifier();
    private View o;

    public static FragmentBuilder_ a() {
        return new FragmentBuilder_();
    }

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.t();
    }

    private void t() {
        Bundle bundle = this.getArguments();
        if (bundle != null && bundle.containsKey("mPerformance")) {
            this.m = (PerformanceV2)bundle.getParcelable("mPerformance");
        }
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        JoinersListFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.h = (ViewGroup)hasViews.findViewById(2131755881);
        this.i = (ViewGroup)hasViews.findViewById(2131755304);
        this.j = (TextView)hasViews.findViewById(2131755882);
        this.k = (ListView)hasViews.findViewById(2131755883);
        this.l = (ProgressBar)hasViews.findViewById(2131755300);
    }

    public View findViewById(int n) {
        if (this.o == null) {
            return null;
        }
        return this.o.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.n);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.o = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.o == null) {
            this.o = layoutInflater.inflate(2130903258, viewGroup, false);
        }
        return this.o;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.o = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.n.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, JoinersListFragment> {
        public JoinersListFragment a() {
            JoinersListFragment_ joinersListFragment_ = new JoinersListFragment_();
            joinersListFragment_.setArguments(this.a);
            return joinersListFragment_;
        }

        public FragmentBuilder_ a(PerformanceV2 performanceV2) {
            this.a.putParcelable("mPerformance", (Parcelable)performanceV2);
            return this;
        }
    }

}

