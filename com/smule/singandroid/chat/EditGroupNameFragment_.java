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
 *  android.widget.EditText
 *  android.widget.TextView
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
import android.widget.EditText;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.chat.EditGroupNameFragment;
import com.smule.singandroid.chat.activator.ChatActivator;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class EditGroupNameFragment_
extends EditGroupNameFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier m = new OnViewChangedNotifier();
    private View n;

    public static FragmentBuilder_ I() {
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
        EditGroupNameFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.h = (EditText)hasViews.findViewById(2131755375);
        this.i = (TextView)hasViews.findViewById(2131755377);
        this.j = hasViews.findViewById(2131755376);
        this.t();
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
            this.n = layoutInflater.inflate(2130903105, viewGroup, false);
        }
        return this.n;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.n = null;
        this.h = null;
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
        this.m.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, EditGroupNameFragment> {
        public EditGroupNameFragment a() {
            EditGroupNameFragment_ editGroupNameFragment_ = new EditGroupNameFragment_();
            editGroupNameFragment_.setArguments(this.a);
            return editGroupNameFragment_;
        }
    }

}

