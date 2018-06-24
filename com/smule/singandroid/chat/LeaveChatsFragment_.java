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
package com.smule.singandroid.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.chat.ChatListView;
import com.smule.singandroid.chat.LeaveChatsFragment;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class LeaveChatsFragment_
extends LeaveChatsFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier m = new OnViewChangedNotifier();
    private View n;

    public static FragmentBuilder_ G() {
        return new FragmentBuilder_();
    }

    private void H() {
        Bundle bundle = this.getArguments();
        if (bundle != null && bundle.containsKey("mPendingJoinJID")) {
            this.i = bundle.getString("mPendingJoinJID");
        }
    }

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.H();
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        LeaveChatsFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.g = (ChatListView)hasViews.findViewById(2131755924);
        this.h = hasViews.findViewById(2131755304);
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
            this.n = layoutInflater.inflate(2130903270, viewGroup, false);
        }
        return this.n;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.n = null;
        this.g = null;
        this.h = null;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.m.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, LeaveChatsFragment> {
        public LeaveChatsFragment a() {
            LeaveChatsFragment_ leaveChatsFragment_ = new LeaveChatsFragment_();
            leaveChatsFragment_.setArguments(this.a);
            return leaveChatsFragment_;
        }

        public FragmentBuilder_ a(String string2) {
            this.a.putString("mPendingJoinJID", string2);
            return this;
        }
    }

}

