/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.TextView
 *  com.smule.singandroid.textviews.EllipsizingEndMarginTextView
 *  org.androidannotations.api.UiThreadExecutor
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.profile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.customviews.CoverPhotoImageView;
import com.smule.singandroid.customviews.ProfileImageListView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.profile.ProfileUserInfo;
import com.smule.singandroid.profile.ProfileUserInfoView;
import com.smule.singandroid.textviews.EllipsizingEndMarginTextView;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ProfileUserInfoView_
extends ProfileUserInfoView
implements HasViews,
OnViewChangedListener {
    private boolean x = false;
    private final OnViewChangedNotifier y = new OnViewChangedNotifier();

    public ProfileUserInfoView_(Context context) {
        super(context);
        this.i();
    }

    public static ProfileUserInfoView b(Context object) {
        object = new ProfileUserInfoView_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void i() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.y);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (ViewGroup)hasViews.findViewById(2131756391);
        this.b = hasViews.findViewById(2131756390);
        this.c = hasViews.findViewById(2131756392);
        this.d = (TextView)hasViews.findViewById(2131756402);
        this.e = (TextView)hasViews.findViewById(2131756403);
        this.f = (ProfileImageWithVIPBadge)hasViews.findViewById(2131756167);
        this.g = (ImageView)hasViews.findViewById(2131756394);
        this.h = (TextView)hasViews.findViewById(2131756407);
        this.i = (TextView)hasViews.findViewById(2131756406);
        this.j = (EllipsizingEndMarginTextView)hasViews.findViewById(2131756408);
        this.k = (FrameLayout)hasViews.findViewById(2131756395);
        this.l = (ImageView)hasViews.findViewById(2131756397);
        this.m = (ImageView)hasViews.findViewById(2131756398);
        this.n = (FrameLayout)hasViews.findViewById(2131756396);
        this.o = (ImageView)hasViews.findViewById(2131756399);
        this.p = hasViews.findViewById(2131756400);
        this.q = (TextView)hasViews.findViewById(2131756404);
        this.r = (ViewGroup)hasViews.findViewById(2131756388);
        this.s = (CoverPhotoImageView)hasViews.findViewById(2131756389);
        this.t = (ImageView)hasViews.findViewById(2131756401);
        this.u = (ViewGroup)hasViews.findViewById(2131756409);
        this.v = (TextView)hasViews.findViewById(2131756410);
        this.w = (ProfileImageListView)hasViews.findViewById(2131756411);
        if (this.i != null) {
            this.i.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ProfileUserInfoView_.this.d();
                }
            });
        }
        if (this.h != null) {
            this.h.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ProfileUserInfoView_.this.e();
                }
            });
        }
        if (this.k != null) {
            this.k.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ProfileUserInfoView_.this.f();
                }
            });
        }
        if (this.n != null) {
            this.n.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ProfileUserInfoView_.this.g();
                }
            });
        }
        this.a();
    }

    @Override
    protected void b(final ProfileUserInfo profileUserInfo) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                ProfileUserInfoView_.super.b(profileUserInfo);
            }
        }, (long)0);
    }

    public void onFinishInflate() {
        if (!this.x) {
            this.x = true;
            ProfileUserInfoView_.inflate((Context)this.getContext(), (int)2130903374, (ViewGroup)this);
            this.y.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

