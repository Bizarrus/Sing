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
 *  android.widget.SeekBar
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
import android.widget.SeekBar;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.customviews.IconFontView;
import com.smule.singandroid.fragments.IconFontDebugFragment;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class IconFontDebugFragment_
extends IconFontDebugFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier l = new OnViewChangedNotifier();
    private View m;

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        IconFontDebugFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.g = (SeekBar)hasViews.findViewById(2131755850);
        this.h = (TextView)hasViews.findViewById(2131755849);
        this.i = (TextView)hasViews.findViewById(2131755851);
        this.j = (SeekBar)hasViews.findViewById(2131755852);
        this.k = (IconFontView)hasViews.findViewById(2131755853);
        this.a();
    }

    public View findViewById(int n) {
        if (this.m == null) {
            return null;
        }
        return this.m.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.l);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.m = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.m == null) {
            this.m = layoutInflater.inflate(2130903254, viewGroup, false);
        }
        return this.m;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.m = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.l.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, IconFontDebugFragment> {
    }

}

