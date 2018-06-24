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
package com.smule.singandroid.registration;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.FindFriendsFacebookPageView;
import com.smule.singandroid.registration.FindFriendsFacebookFragment;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FindFriendsFacebookFragment_
extends FindFriendsFacebookFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier h = new OnViewChangedNotifier();
    private View i;

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
    }

    public static FragmentBuilder_ t() {
        return new FragmentBuilder_();
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        FindFriendsFacebookFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.g = (FindFriendsFacebookPageView)hasViews.findViewById(2131755807);
        this.a();
    }

    public View findViewById(int n) {
        if (this.i == null) {
            return null;
        }
        return this.i.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.h);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.i = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.i == null) {
            this.i = layoutInflater.inflate(2130903238, viewGroup, false);
        }
        return this.i;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.i = null;
        this.g = null;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.h.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, FindFriendsFacebookFragment> {
        public FindFriendsFacebookFragment a() {
            FindFriendsFacebookFragment_ findFriendsFacebookFragment_ = new FindFriendsFacebookFragment_();
            findFriendsFacebookFragment_.setArguments(this.a);
            return findFriendsFacebookFragment_;
        }
    }

}

