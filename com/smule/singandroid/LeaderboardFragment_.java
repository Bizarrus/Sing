/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.support.design.widget.TabLayout
 *  android.support.v4.view.ViewPager
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.builder.FragmentBuilder
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.LeaderboardFragment;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class LeaderboardFragment_
extends LeaderboardFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier m = new OnViewChangedNotifier();
    private View n;

    public static FragmentBuilder_ F() {
        return new FragmentBuilder_();
    }

    private void G() {
        Bundle bundle = this.getArguments();
        if (bundle != null && bundle.containsKey("mPromoId")) {
            this.j = bundle.getLong("mPromoId");
        }
    }

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.G();
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.j = bundle.getLong("mPromoId");
        this.k = bundle.getString("mHashtag");
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        LeaderboardFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.h = (ViewPager)hasViews.findViewById(2131755892);
        this.i = (TabLayout)hasViews.findViewById(2131755891);
        this.a();
    }

    public View findViewById(int n) {
        if (this.n == null) {
            return null;
        }
        return this.n.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.m);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.n = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.n == null) {
            this.n = layoutInflater.inflate(2130903262, viewGroup, false);
        }
        return this.n;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.n = null;
        this.h = null;
        this.i = null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putLong("mPromoId", this.j);
        bundle.putString("mHashtag", this.k);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.m.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, LeaderboardFragment> {
        public LeaderboardFragment a() {
            LeaderboardFragment_ leaderboardFragment_ = new LeaderboardFragment_();
            leaderboardFragment_.setArguments(this.a);
            return leaderboardFragment_;
        }

        public FragmentBuilder_ a(long l) {
            this.a.putLong("mPromoId", l);
            return this;
        }
    }

}

