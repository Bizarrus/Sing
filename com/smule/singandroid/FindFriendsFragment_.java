/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.CustomTabLayout;
import com.smule.singandroid.CustomViewPager;
import com.smule.singandroid.FindFriendsFragment;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FindFriendsFragment_
extends FindFriendsFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier p = new OnViewChangedNotifier();
    private View q;

    public static FragmentBuilder_ H() {
        return new FragmentBuilder_();
    }

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        FindFriendsFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.i = (CustomTabLayout)hasViews.findViewById(2131755812);
        this.j = (CustomViewPager)hasViews.findViewById(2131755813);
        this.t();
    }

    public View findViewById(int n) {
        if (this.q == null) {
            return null;
        }
        return this.q.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.p);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.q = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.q == null) {
            this.q = layoutInflater.inflate(2130903241, viewGroup, false);
        }
        return this.q;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.q = null;
        this.i = null;
        this.j = null;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.p.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, FindFriendsFragment> {
        public FindFriendsFragment a() {
            FindFriendsFragment_ findFriendsFragment_ = new FindFriendsFragment_();
            findFriendsFragment_.setArguments(this.a);
            return findFriendsFragment_;
        }
    }

}

