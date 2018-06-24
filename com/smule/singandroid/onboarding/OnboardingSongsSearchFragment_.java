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
 *  android.widget.EditText
 *  android.widget.TextView
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
import android.widget.EditText;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.onboarding.OnboardingSongsSearchFragment;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class OnboardingSongsSearchFragment_
extends OnboardingSongsSearchFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier u = new OnViewChangedNotifier();
    private View z;

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
    }

    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        OnboardingSongsSearchFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.i = (EditText)hasViews.findViewById(2131756157);
        this.j = hasViews.findViewById(2131756158);
        this.k = hasViews.findViewById(2131756186);
        this.l = (TextView)hasViews.findViewById(2131756188);
        this.m = (TextView)hasViews.findViewById(2131756184);
        this.n = (MediaListView)hasViews.findViewById(2131756185);
        if ((hasViews = hasViews.findViewById(2131756156)) != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    OnboardingSongsSearchFragment_.this.a();
                }
            });
        }
        if (this.j != null) {
            this.j.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    OnboardingSongsSearchFragment_.this.F();
                }
            });
        }
        this.G();
    }

    public View findViewById(int n) {
        if (this.z == null) {
            return null;
        }
        return this.z.findViewById(n);
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.u);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.z = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.z == null) {
            this.z = layoutInflater.inflate(2130903338, viewGroup, false);
        }
        return this.z;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.z = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.u.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, OnboardingSongsSearchFragment> {
    }

}

