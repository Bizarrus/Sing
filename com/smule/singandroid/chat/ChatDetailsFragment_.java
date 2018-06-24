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
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.FrameLayout
 *  android.widget.LinearLayout
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  android.widget.ToggleButton
 *  com.viewpagerindicator.CirclePageIndicator
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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.chat.ChatDetailsFragment;
import com.smule.singandroid.chat.activator.ChatActivator;
import com.smule.singandroid.customviews.ExpandableHeightViewPager;
import com.viewpagerindicator.CirclePageIndicator;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ChatDetailsFragment_
extends ChatDetailsFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier s = new OnViewChangedNotifier();
    private View t;

    public static FragmentBuilder_ J() {
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
        ChatDetailsFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.h = (TextView)hasViews.findViewById(2131755363);
        this.i = (ToggleButton)hasViews.findViewById(2131755364);
        this.j = (FrameLayout)hasViews.findViewById(2131755366);
        this.k = (LinearLayout)hasViews.findViewById(2131755365);
        this.l = (LinearLayout)hasViews.findViewById(2131755367);
        this.m = (TextView)hasViews.findViewById(2131755369);
        this.n = (RelativeLayout)hasViews.findViewById(2131755358);
        this.o = (TextView)hasViews.findViewById(2131755360);
        this.p = (ExpandableHeightViewPager)hasViews.findViewById(2131755356);
        this.q = (CirclePageIndicator)hasViews.findViewById(2131755357);
        this.r = hasViews.findViewById(2131755370);
        hasViews = hasViews.findViewById(2131755368);
        if (this.l != null) {
            this.l.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ChatDetailsFragment_.this.F();
                }
            });
        }
        if (hasViews != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ChatDetailsFragment_.this.G();
                }
            });
        }
    }

    public View findViewById(int n) {
        if (this.t == null) {
            return null;
        }
        return this.t.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.s);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.t = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.t == null) {
            this.t = layoutInflater.inflate(2130903102, viewGroup, false);
        }
        return this.t;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.t = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mChatActivator", (Parcelable)this.L);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.s.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, ChatDetailsFragment> {
        public ChatDetailsFragment a() {
            ChatDetailsFragment_ chatDetailsFragment_ = new ChatDetailsFragment_();
            chatDetailsFragment_.setArguments(this.a);
            return chatDetailsFragment_;
        }
    }

}

