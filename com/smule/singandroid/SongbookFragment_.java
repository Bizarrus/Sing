/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.support.v4.view.ViewPager
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.FrameLayout
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  com.smule.singandroid.songbook.CustomizablePagerSlidingTabStrip
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.UiThreadExecutor
 *  org.androidannotations.api.builder.FragmentBuilder
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.CustomViewPager;
import com.smule.singandroid.SongbookFragment;
import com.smule.singandroid.songbook.CustomizablePagerSlidingTabStrip;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SongbookFragment_
extends SongbookFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier E = new OnViewChangedNotifier();
    private View F;

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.v = bundle.getInt("mOwnedSongsCount");
        this.w = bundle.getBoolean("mIsShowingPreSearchBar");
    }

    @Override
    void O() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                SongbookFragment_.super.O();
            }
        }, (long)0);
    }

    @Override
    protected void a() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                SongbookFragment_.super.a();
            }
        }, (long)0);
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        SongbookFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.j = hasViews.findViewById(2131756703);
        this.k = (LinearLayout)hasViews.findViewById(2131756698);
        this.l = (TextView)hasViews.findViewById(2131756699);
        this.m = (FrameLayout)hasViews.findViewById(2131756696);
        this.n = hasViews.findViewById(2131756695);
        this.o = (CustomizablePagerSlidingTabStrip)hasViews.findViewById(2131756702);
        this.p = (CustomViewPager)hasViews.findViewById(2131756697);
        this.q = hasViews.findViewById(2131756700);
        this.r = (FrameLayout)hasViews.findViewById(2131756701);
        this.s = (ViewPager)hasViews.findViewById(2131756693);
        this.t = (LinearLayout)hasViews.findViewById(2131756694);
        if (this.t != null) {
            this.t.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SongbookFragment_.this.H();
                }
            });
        }
        if (this.r != null) {
            this.r.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SongbookFragment_.this.I();
                }
            });
        }
        this.J();
    }

    @Override
    protected void d(String string2) {
        BackgroundExecutor.a();
        SongbookFragment_.super.d(string2);
    }

    @Override
    protected void e(int n) {
        BackgroundExecutor.a();
        SongbookFragment_.super.e(n);
    }

    public View findViewById(int n) {
        if (this.F == null) {
            return null;
        }
        return this.F.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.E);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.F = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.F == null) {
            this.F = layoutInflater.inflate(2130903421, viewGroup, false);
        }
        return this.F;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.F = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("mOwnedSongsCount", this.v);
        bundle.putBoolean("mIsShowingPreSearchBar", this.w);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.E.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, SongbookFragment> {
    }

}

