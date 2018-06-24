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
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.chat.BlockedUsersFragment;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class BlockedUsersFragment_
extends BlockedUsersFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier k = new OnViewChangedNotifier();
    private View l;

    public static FragmentBuilder_ F() {
        return new FragmentBuilder_();
    }

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        BlockedUsersFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.h = (MagicListView)hasViews.findViewById(2131755303);
        this.i = hasViews.findViewById(2131755304);
        this.t();
    }

    public View findViewById(int n) {
        if (this.l == null) {
            return null;
        }
        return this.l.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.k);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.l = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.l == null) {
            this.l = layoutInflater.inflate(2130903088, viewGroup, false);
        }
        return this.l;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.l = null;
        this.h = null;
        this.i = null;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.k.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, BlockedUsersFragment> {
        public BlockedUsersFragment a() {
            BlockedUsersFragment_ blockedUsersFragment_ = new BlockedUsersFragment_();
            blockedUsersFragment_.setArguments(this.a);
            return blockedUsersFragment_;
        }
    }

}

