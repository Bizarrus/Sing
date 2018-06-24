/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.LinearLayout
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.UiThreadExecutor
 *  org.androidannotations.api.builder.FragmentBuilder
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.onboarding;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.onboarding.OnboardingSongsFragment;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class OnboardingSongsFragment_
extends OnboardingSongsFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier s = new OnViewChangedNotifier();
    private View t;

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        OnboardingSongsFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    @Override
    protected void a(final CustomAlertDialog.CustomAlertDialogListener customAlertDialogListener) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                OnboardingSongsFragment_.super.a(customAlertDialogListener);
            }
        }, (long)0);
    }

    public void a(HasViews hasViews) {
        this.h = (MediaListView)hasViews.findViewById(2131756175);
        this.i = hasViews.findViewById(2131756174);
        this.j = (LinearLayout)hasViews.findViewById(2131755370);
        this.k = (ProfileImageWithVIPBadge)hasViews.findViewById(2131756167);
        this.l = hasViews.findViewById(2131756170);
        this.m = hasViews.findViewById(2131756166);
        this.n = hasViews.findViewById(2131756163);
        this.o = hasViews.findViewById(2131756164);
        this.p = hasViews.findViewById(2131756173);
        this.q = (LinearLayout)hasViews.findViewById(2131756182);
        this.r = hasViews.findViewById(2131756179);
        if ((hasViews = hasViews.findViewById(2131755687)) != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    OnboardingSongsFragment_.this.t();
                }
            });
        }
        if (this.q != null) {
            this.q.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    OnboardingSongsFragment_.this.F();
                }
            });
        }
        this.a();
    }

    @Override
    protected void b(final CustomAlertDialog.CustomAlertDialogListener customAlertDialogListener) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                OnboardingSongsFragment_.super.b(customAlertDialogListener);
            }
        }, (long)0);
    }

    @Override
    protected void c(final CustomAlertDialog.CustomAlertDialogListener customAlertDialogListener) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                OnboardingSongsFragment_.super.c(customAlertDialogListener);
            }
        }, (long)0);
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
            this.t = layoutInflater.inflate(2130903335, viewGroup, false);
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

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.s.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, OnboardingSongsFragment> {
    }

}

