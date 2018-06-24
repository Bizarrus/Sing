/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.support.design.widget.TabLayout
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.TextView
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
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.CustomViewPager;
import com.smule.singandroid.chat.MessageCenterFragment;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class MessageCenterFragment_
extends MessageCenterFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier v = new OnViewChangedNotifier();
    private View w;

    public static FragmentBuilder_ J() {
        return new FragmentBuilder_();
    }

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        MessageCenterFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.h = hasViews.findViewById(2131755370);
        this.i = hasViews.findViewById(2131755961);
        this.j = (ImageView)hasViews.findViewById(2131755962);
        this.k = (TextView)hasViews.findViewById(2131755963);
        this.l = (Button)hasViews.findViewById(2131755964);
        this.m = (CustomViewPager)hasViews.findViewById(2131755960);
        this.n = (TabLayout)hasViews.findViewById(2131755959);
        hasViews = hasViews.findViewById(2131755967);
        if (this.l != null) {
            this.l.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    MessageCenterFragment_.this.G();
                }
            });
        }
        if (hasViews != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    MessageCenterFragment_.this.H();
                }
            });
        }
        this.t();
    }

    public View findViewById(int n) {
        if (this.w == null) {
            return null;
        }
        return this.w.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.v);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.w = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.w == null) {
            this.w = layoutInflater.inflate(2130903285, viewGroup, false);
        }
        return this.w;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.w = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.v.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, MessageCenterFragment> {
        public MessageCenterFragment a() {
            MessageCenterFragment_ messageCenterFragment_ = new MessageCenterFragment_();
            messageCenterFragment_.setArguments(this.a);
            return messageCenterFragment_;
        }
    }

}

