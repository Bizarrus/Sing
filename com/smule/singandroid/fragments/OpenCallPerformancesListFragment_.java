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
 *  android.widget.TextView
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.builder.FragmentBuilder
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.fragments.OpenCallPerformancesListFragment;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class OpenCallPerformancesListFragment_
extends OpenCallPerformancesListFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier o = new OnViewChangedNotifier();
    private View p;

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.g = (PerformanceV2)bundle.getParcelable("mOpenCall");
        this.h = bundle.getBoolean("mAPerformanceIsPinned");
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        OpenCallPerformancesListFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.j = hasViews.findViewById(2131755305);
        this.k = (TextView)hasViews.findViewById(2131755306);
        this.l = hasViews.findViewById(2131755926);
        this.m = (MediaListView)hasViews.findViewById(2131755903);
        if ((hasViews = hasViews.findViewById(2131755307)) != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    OpenCallPerformancesListFragment_.this.F();
                }
            });
        }
        this.a();
    }

    public View findViewById(int n) {
        if (this.p == null) {
            return null;
        }
        return this.p.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.o);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.p = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.p == null) {
            this.p = layoutInflater.inflate(2130903343, viewGroup, false);
        }
        return this.p;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.p = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mOpenCall", (Parcelable)this.g);
        bundle.putBoolean("mAPerformanceIsPinned", this.h);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.o.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, OpenCallPerformancesListFragment> {
    }

}

