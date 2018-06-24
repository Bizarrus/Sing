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
import com.smule.singandroid.chat.ChatAddMembersFragment;
import com.smule.singandroid.chat.SelectUsersAndChatsView;
import com.smule.singandroid.chat.activator.ChatActivator;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ChatAddMembersFragment_
extends ChatAddMembersFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier r = new OnViewChangedNotifier();
    private View s;

    public static FragmentBuilder_ H() {
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
        ChatAddMembersFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.i = (SelectUsersAndChatsView)hasViews.findViewById(2131755456);
        this.j = (LinearLayout)hasViews.findViewById(2131755221);
        this.I();
    }

    public View findViewById(int n) {
        if (this.s == null) {
            return null;
        }
        return this.s.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.r);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.s = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.s == null) {
            this.s = layoutInflater.inflate(2130903307, viewGroup, false);
        }
        return this.s;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.s = null;
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
        this.r.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, ChatAddMembersFragment> {
        public ChatAddMembersFragment a() {
            ChatAddMembersFragment_ chatAddMembersFragment_ = new ChatAddMembersFragment_();
            chatAddMembersFragment_.setArguments(this.a);
            return chatAddMembersFragment_;
        }
    }

}

