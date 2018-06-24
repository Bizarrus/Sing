/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Intent
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.EditText
 *  android.widget.LinearLayout
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.builder.FragmentBuilder
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.chat.ChatAnalytics;
import com.smule.singandroid.fragments.FlagUserFragment;
import java.io.Serializable;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FlagUserFragment_
extends FlagUserFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier n = new OnViewChangedNotifier();
    private View o;

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.k = (AccountIcon)bundle.getParcelable("mAccountIcon");
        this.l = (ChatAnalytics.FlagUserType)((Object)bundle.getSerializable("mFlagUserType"));
        this.m = (Intent)bundle.getParcelable("mEmailIntent");
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        FlagUserFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.h = (LinearLayout)hasViews.findViewById(2131755834);
        this.i = (LinearLayout)hasViews.findViewById(2131755838);
        this.j = (EditText)hasViews.findViewById(2131755839);
        View view = hasViews.findViewById(2131755835);
        View view2 = hasViews.findViewById(2131755836);
        hasViews = hasViews.findViewById(2131755837);
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    FlagUserFragment_.this.t();
                }
            });
        }
        if (view2 != null) {
            view2.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    FlagUserFragment_.this.F();
                }
            });
        }
        if (hasViews != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    FlagUserFragment_.this.G();
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
            this.o = layoutInflater.inflate(2130903249, viewGroup, false);
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
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mAccountIcon", (Parcelable)this.k);
        bundle.putSerializable("mFlagUserType", (Serializable)((Object)this.l));
        bundle.putParcelable("mEmailIntent", (Parcelable)this.m);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.n.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, FlagUserFragment> {
    }

}

