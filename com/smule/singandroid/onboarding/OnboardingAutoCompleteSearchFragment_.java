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
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.onboarding.OnboardingAutoCompleteSearchFragment;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class OnboardingAutoCompleteSearchFragment_
extends OnboardingAutoCompleteSearchFragment
implements HasViews,
OnViewChangedListener {
    private View A;
    private final OnViewChangedNotifier z = new OnViewChangedNotifier();

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.j = bundle.getInt("mAutoCompleteSearchDelay");
    }

    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        OnboardingAutoCompleteSearchFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.i = (EditText)hasViews.findViewById(2131756157);
        this.k = hasViews.findViewById(2131756158);
        this.l = (MagicListView)hasViews.findViewById(2131756159);
        this.s = hasViews.findViewById(2131756160);
        hasViews = hasViews.findViewById(2131756156);
        if (this.k != null) {
            this.k.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    OnboardingAutoCompleteSearchFragment_.this.a();
                }
            });
        }
        if (hasViews != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    OnboardingAutoCompleteSearchFragment_.this.F();
                }
            });
        }
        this.G();
    }

    public View findViewById(int n) {
        if (this.A == null) {
            return null;
        }
        return this.A.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.z);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.A = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.A == null) {
            this.A = layoutInflater.inflate(2130903334, viewGroup, false);
        }
        return this.A;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.A = null;
        this.i = null;
        this.k = null;
        this.l = null;
        this.s = null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("mAutoCompleteSearchDelay", this.j);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.z.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, OnboardingAutoCompleteSearchFragment> {
    }

}

