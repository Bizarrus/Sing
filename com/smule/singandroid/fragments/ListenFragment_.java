/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.GridView
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
import android.widget.GridView;
import android.widget.TextView;
import com.smule.android.network.models.RecPerformanceIcon;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.fragments.ListenFragment;
import java.util.ArrayList;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ListenFragment_
extends ListenFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier s = new OnViewChangedNotifier();
    private View t;

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
    }

    @Override
    protected void G() {
        BackgroundExecutor.a();
        ListenFragment_.super.G();
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        ListenFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    @Override
    public void a(ArrayList<RecPerformanceIcon> arrayList) {
        BackgroundExecutor.a();
        ListenFragment_.super.a(arrayList);
    }

    public void a(HasViews hasViews) {
        this.h = hasViews.findViewById(2131755926);
        this.i = hasViews.findViewById(2131755370);
        this.j = (TextView)hasViews.findViewById(2131755904);
        this.k = hasViews.findViewById(2131755906);
        this.l = (TextView)hasViews.findViewById(2131755907);
        this.m = (GridView)hasViews.findViewById(2131755927);
        this.t();
    }

    @Override
    public void b(ArrayList<RecPerformanceIcon> arrayList) {
        BackgroundExecutor.a();
        ListenFragment_.super.b(arrayList);
    }

    @Override
    protected void d(int n) {
        BackgroundExecutor.a();
        ListenFragment_.super.d(n);
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
            this.t = layoutInflater.inflate(2130903275, viewGroup, false);
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
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.s.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, ListenFragment> {
    }

}

