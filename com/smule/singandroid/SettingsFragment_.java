/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.support.design.widget.TextInputLayout
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.LinearLayout
 *  android.widget.ProgressBar
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  android.widget.ToggleButton
 *  com.smule.singandroid.task.UserUpdateTask
 *  com.smule.singandroid.task.UserUpdateTask$ErrorType
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
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.SettingsFragment;
import com.smule.singandroid.customviews.IconFontView;
import com.smule.singandroid.task.UserUpdateTask;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SettingsFragment_
extends SettingsFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier an = new OnViewChangedNotifier();
    private View ao;

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
    }

    @Override
    protected void R() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                SettingsFragment_.super.R();
            }
        }, (long)0);
    }

    @Override
    protected void a(int n, int n2, int n3, Integer n4) {
        BackgroundExecutor.a();
        SettingsFragment_.super.a(n, n2, n3, n4);
    }

    @Override
    protected void a(final NetworkResponse networkResponse, final Boolean bl, final int n) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                SettingsFragment_.super.a(networkResponse, bl, n);
            }
        }, (long)0);
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        SettingsFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.h = (LinearLayout)hasViews.findViewById(2131755221);
        this.i = (ToggleButton)hasViews.findViewById(2131756521);
        this.j = (ToggleButton)hasViews.findViewById(2131756575);
        this.k = hasViews.findViewById(2131756573);
        this.l = (TextInputLayout)hasViews.findViewById(2131756522);
        this.m = (EditText)hasViews.findViewById(2131756523);
        this.n = (TextInputLayout)hasViews.findViewById(2131756531);
        this.o = (EditText)hasViews.findViewById(2131756532);
        this.p = (TextInputLayout)hasViews.findViewById(2131756528);
        this.q = (EditText)hasViews.findViewById(2131756529);
        this.r = (TextInputLayout)hasViews.findViewById(2131756536);
        this.s = (EditText)hasViews.findViewById(2131756537);
        this.t = (TextInputLayout)hasViews.findViewById(2131756540);
        this.u = (EditText)hasViews.findViewById(2131756541);
        this.v = (TextInputLayout)hasViews.findViewById(2131756542);
        this.w = (EditText)hasViews.findViewById(2131756543);
        this.x = (TextInputLayout)hasViews.findViewById(2131756545);
        this.y = (EditText)hasViews.findViewById(2131756546);
        this.z = (Button)hasViews.findViewById(2131756549);
        this.A = (EditText)hasViews.findViewById(2131756525);
        this.B = (TextInputLayout)hasViews.findViewById(2131756524);
        this.C = (EditText)hasViews.findViewById(2131756534);
        this.D = (EditText)hasViews.findViewById(2131756538);
        this.E = hasViews.findViewById(2131756533);
        this.F = hasViews.findViewById(2131756530);
        this.G = hasViews.findViewById(2131756535);
        this.H = hasViews.findViewById(2131756539);
        this.I = hasViews.findViewById(2131756544);
        this.J = hasViews.findViewById(2131756547);
        this.K = (LinearLayout)hasViews.findViewById(2131756550);
        this.L = (TextView)hasViews.findViewById(2131756551);
        this.M = (ToggleButton)hasViews.findViewById(2131756558);
        this.N = (TextView)hasViews.findViewById(2131756559);
        this.O = (ToggleButton)hasViews.findViewById(2131756560);
        this.P = (ProgressBar)hasViews.findViewById(2131756561);
        this.Q = (ViewGroup)hasViews.findViewById(2131756562);
        this.R = (TextView)hasViews.findViewById(2131756563);
        this.S = (ToggleButton)hasViews.findViewById(2131756564);
        this.T = hasViews.findViewById(2131756565);
        this.U = (ToggleButton)hasViews.findViewById(2131756554);
        this.V = (ToggleButton)hasViews.findViewById(2131756556);
        this.W = hasViews.findViewById(2131756570);
        this.X = (ToggleButton)hasViews.findViewById(2131756571);
        this.Y = (TextView)hasViews.findViewById(2131756576);
        this.Z = (TextView)hasViews.findViewById(2131756579);
        this.aa = (TextView)hasViews.findViewById(2131756580);
        this.ab = (LinearLayout)hasViews.findViewById(2131756581);
        this.ac = (TextView)hasViews.findViewById(2131756582);
        this.ad = (IconFontView)hasViews.findViewById(2131756583);
        this.ae = hasViews.findViewById(2131756548);
        this.af = hasViews.findViewById(2131756584);
        this.ag = (TextView)hasViews.findViewById(2131756527);
        this.ah = (RelativeLayout)hasViews.findViewById(2131756526);
        View view = hasViews.findViewById(2131756520);
        View view2 = hasViews.findViewById(2131756567);
        View view3 = hasViews.findViewById(2131756568);
        View view4 = hasViews.findViewById(2131756578);
        View view5 = hasViews.findViewById(2131756577);
        View view6 = hasViews.findViewById(2131756572);
        hasViews = hasViews.findViewById(2131756552);
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SettingsFragment_.this.G();
                }
            });
        }
        if (this.O != null) {
            this.O.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SettingsFragment_.this.J();
                }
            });
        }
        if (this.S != null) {
            this.S.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SettingsFragment_.this.K();
                }
            });
        }
        if (view2 != null) {
            view2.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SettingsFragment_.this.L();
                }
            });
        }
        if (view3 != null) {
            view3.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SettingsFragment_.this.M();
                }
            });
        }
        if (view4 != null) {
            view4.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SettingsFragment_.this.N();
                }
            });
        }
        if (view5 != null) {
            view5.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SettingsFragment_.this.O();
                }
            });
        }
        if (this.z != null) {
            this.z.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SettingsFragment_.this.P();
                }
            });
        }
        if (view6 != null) {
            view6.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SettingsFragment_.this.Q();
                }
            });
        }
        if (hasViews != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SettingsFragment_.this.U();
                }
            });
        }
        this.F();
    }

    @Override
    protected void a(final boolean bl, final boolean bl2, final NetworkResponse networkResponse, final boolean bl3, final int n, final NetworkResponse networkResponse2, final boolean bl4, final int n2, final UserUpdateTask.ErrorType errorType) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                SettingsFragment_.super.a(bl, bl2, networkResponse, bl3, n, networkResponse2, bl4, n2, errorType);
            }
        }, (long)0);
    }

    @Override
    protected void c(final NetworkResponse networkResponse) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                SettingsFragment_.super.c(networkResponse);
            }
        }, (long)0);
    }

    @Override
    protected void d(final NetworkResponse networkResponse) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                SettingsFragment_.super.d(networkResponse);
            }
        }, (long)0);
    }

    public View findViewById(int n) {
        if (this.ao == null) {
            return null;
        }
        return this.ao.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.an);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.ao = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.ao == null) {
            this.ao = layoutInflater.inflate(2130903405, viewGroup, false);
        }
        return this.ao;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.ao = null;
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
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.E = null;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = null;
        this.R = null;
        this.S = null;
        this.T = null;
        this.U = null;
        this.V = null;
        this.W = null;
        this.X = null;
        this.Y = null;
        this.Z = null;
        this.aa = null;
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.ae = null;
        this.af = null;
        this.ag = null;
        this.ah = null;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.an.a((HasViews)this);
    }

    @Override
    protected void t() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                SettingsFragment_.super.t();
            }
        }, (long)0);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, SettingsFragment> {
    }

}

