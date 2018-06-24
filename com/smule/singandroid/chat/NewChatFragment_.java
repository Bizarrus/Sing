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
 *  android.widget.LinearLayout
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.builder.FragmentBuilder
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.chat;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.chat.NewChatFragment;
import com.smule.singandroid.chat.SelectUsersAndChatsView;
import com.smule.singandroid.chat.activator.ChatActivator;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class NewChatFragment_
extends NewChatFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier g = new OnViewChangedNotifier();
    private View r;

    public static FragmentBuilder_ K() {
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
        this.L = (ChatActivator)bundle.getParcelable("mChatActivator");
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        NewChatFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.i = (SelectUsersAndChatsView)hasViews.findViewById(2131755456);
        this.j = (LinearLayout)hasViews.findViewById(2131755221);
        this.I();
    }

    public View findViewById(int n) {
        if (this.r == null) {
            return null;
        }
        return this.r.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.g);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.r = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.r == null) {
            this.r = layoutInflater.inflate(2130903307, viewGroup, false);
        }
        return this.r;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.r = null;
        this.i = null;
        this.j = null;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mChatActivator", (Parcelable)this.L);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.g.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, NewChatFragment> {
        public NewChatFragment a() {
            NewChatFragment_ newChatFragment_ = new NewChatFragment_();
            newChatFragment_.setArguments(this.a);
            return newChatFragment_;
        }
    }

}

