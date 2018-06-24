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
 *  android.widget.EditText
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.ProgressBar
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  com.foound.widget.AmazingListView
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.foound.widget.AmazingListView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.chat.ChatFragment;
import com.smule.singandroid.chat.activator.ChatActivator;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ChatFragment_
extends ChatFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier N = new OnViewChangedNotifier();
    private View O;

    public static FragmentBuilder_ U() {
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
    protected void J() {
        BackgroundExecutor.a();
        ChatFragment_.super.J();
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        ChatFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.h = (RelativeLayout)hasViews.findViewById(2131755221);
        this.i = hasViews.findViewById(2131755305);
        this.j = (TextView)hasViews.findViewById(2131755306);
        this.k = hasViews.findViewById(2131755379);
        this.l = hasViews.findViewById(2131755384);
        this.m = (EditText)hasViews.findViewById(2131755385);
        this.n = (AmazingListView)hasViews.findViewById(2131755378);
        this.o = (RelativeLayout)hasViews.findViewById(2131755381);
        this.p = (RelativeLayout)hasViews.findViewById(2131755387);
        this.q = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755388);
        this.r = (TextView)hasViews.findViewById(2131755389);
        this.s = (ImageView)hasViews.findViewById(2131755391);
        this.t = (ProgressBar)hasViews.findViewById(2131755246);
        this.u = (TextView)hasViews.findViewById(2131755392);
        this.v = (LinearLayout)hasViews.findViewById(2131755380);
        this.w = (ImageView)hasViews.findViewById(2131755386);
        this.x = hasViews.findViewById(2131755370);
        this.y = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755382);
        hasViews = hasViews.findViewById(2131755307);
        if (this.w != null) {
            this.w.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ChatFragment_.this.R();
                }
            });
        }
        if (hasViews != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ChatFragment_.this.T();
                }
            });
        }
        this.t();
    }

    public View findViewById(int n) {
        if (this.O == null) {
            return null;
        }
        return this.O.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.N);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.O = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.O == null) {
            this.O = layoutInflater.inflate(2130903106, viewGroup, false);
        }
        return this.O;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.O = null;
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
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mChatActivator", (Parcelable)this.L);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.N.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, ChatFragment> {
        public ChatFragment a() {
            ChatFragment_ chatFragment_ = new ChatFragment_();
            chatFragment_.setArguments(this.a);
            return chatFragment_;
        }
    }

}

