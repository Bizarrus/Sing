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
 *  android.widget.TextView
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.builder.FragmentBuilder
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.fragments.SearchByTagFragment;
import java.io.Serializable;
import java.util.ArrayList;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SearchByTagFragment_
extends SearchByTagFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier r = new OnViewChangedNotifier();
    private View s;

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.n = (ArrayList)bundle.getSerializable("mResultList");
        this.o = bundle.getString("mTag");
        this.p = bundle.getBoolean("mEmpty");
    }

    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        SearchByTagFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.h = (LinearLayout)hasViews.findViewById(2131756475);
        this.i = (TextView)hasViews.findViewById(2131756472);
        this.j = (MediaListView)hasViews.findViewById(2131756473);
        this.k = hasViews.findViewById(2131755305);
        this.l = (TextView)hasViews.findViewById(2131755306);
        this.m = (TextView)hasViews.findViewById(2131755228);
        View view = hasViews.findViewById(2131756474);
        hasViews = hasViews.findViewById(2131755307);
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SearchByTagFragment_.this.G();
                }
            });
        }
        if (hasViews != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SearchByTagFragment_.this.H();
                }
            });
        }
        this.F();
    }

    public View findViewById(int n) {
        if (this.s == null) {
            return null;
        }
        return this.s.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.r);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.s = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.s == null) {
            this.s = layoutInflater.inflate(2130903394, viewGroup, false);
        }
        return this.s;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.s = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("mResultList", (Serializable)this.n);
        bundle.putString("mTag", this.o);
        bundle.putBoolean("mEmpty", this.p);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.r.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, SearchByTagFragment> {
    }

}

