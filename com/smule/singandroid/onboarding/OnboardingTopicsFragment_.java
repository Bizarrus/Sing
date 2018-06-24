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
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  in.srain.cube.views.GridViewWithHeaderAndFooter
 *  org.androidannotations.api.BackgroundExecutor
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
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.onboarding.OnboardingTopicsFragment;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class OnboardingTopicsFragment_
extends OnboardingTopicsFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier n = new OnViewChangedNotifier();
    private View o;

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        OnboardingTopicsFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.h = (GridViewWithHeaderAndFooter)hasViews.findViewById(2131756193);
        this.i = (TextView)hasViews.findViewById(2131755681);
        this.j = (TextView)hasViews.findViewById(2131756190);
        this.k = (TextView)hasViews.findViewById(2131755687);
        this.l = (ProgressBar)hasViews.findViewById(2131756194);
        if (this.j != null) {
            this.j.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    OnboardingTopicsFragment_.this.t();
                }
            });
        }
        if (this.i != null) {
            this.i.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    OnboardingTopicsFragment_.this.F();
                }
            });
        }
        if (this.k != null) {
            this.k.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    OnboardingTopicsFragment_.this.G();
                }
            });
        }
        this.a();
    }

    public View findViewById(int n) {
        if (this.o == null) {
            return null;
        }
        return this.o.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.n);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.o = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.o == null) {
            this.o = layoutInflater.inflate(2130903339, viewGroup, false);
        }
        return this.o;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.o = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.n.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, OnboardingTopicsFragment> {
    }

}

