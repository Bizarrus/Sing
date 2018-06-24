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
 *  android.widget.CheckBox
 *  android.widget.EditText
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.UiThreadExecutor
 *  org.androidannotations.api.builder.FragmentBuilder
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.registration;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.registration.NewHandleDualScreenFragment;
import java.io.Serializable;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class NewHandleDualScreenFragment_
extends NewHandleDualScreenFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier G = new OnViewChangedNotifier();
    private View H;

    public static FragmentBuilder_ K() {
        return new FragmentBuilder_();
    }

    private void P() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            if (bundle.containsKey("handle")) {
                this.A = bundle.getString("handle");
            }
            if (bundle.containsKey("handlePrefill")) {
                this.B = bundle.getBoolean("handlePrefill");
            }
            if (bundle.containsKey("showEmailOptIn")) {
                this.C = (Boolean)bundle.getSerializable("showEmailOptIn");
            }
        }
    }

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.P();
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.D = (Object)bundle.getSerializable("mHandleUpdateType");
        this.E = (Object)bundle.getSerializable("mProfilePicType");
        this.F = bundle.getBoolean("mShowEmailOpt");
        this.r = (NewHandleDualScreenFragment.SplitScreenState)((Object)bundle.getSerializable("mSplitScreenState"));
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        NewHandleDualScreenFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.t = (ViewGroup)hasViews.findViewById(2131755221);
        this.u = (EditText)hasViews.findViewById(2131756022);
        this.v = (TextView)hasViews.findViewById(2131756035);
        this.w = (TextView)hasViews.findViewById(2131756034);
        this.x = hasViews.findViewById(2131755264);
        this.y = (CheckBox)hasViews.findViewById(2131756024);
        this.z = (ImageView)hasViews.findViewById(2131755407);
        this.g = (LinearLayout)hasViews.findViewById(2131756021);
        this.h = (FrameLayout)hasViews.findViewById(2131756026);
        this.i = (FrameLayout)hasViews.findViewById(2131756029);
        this.j = (ImageView)hasViews.findViewById(2131756027);
        this.k = (ImageView)hasViews.findViewById(2131756028);
        this.l = (ImageView)hasViews.findViewById(2131756030);
        this.m = (LinearLayout)hasViews.findViewById(2131756025);
        this.n = (TextView)hasViews.findViewById(2131756023);
        this.o = (TextView)hasViews.findViewById(2131756031);
        this.p = (TextView)hasViews.findViewById(2131756032);
        this.q = (TextView)hasViews.findViewById(2131756033);
        if (this.v != null) {
            this.v.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    NewHandleDualScreenFragment_.this.L();
                }
            });
        }
        if (this.w != null) {
            this.w.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    NewHandleDualScreenFragment_.this.L();
                }
            });
        }
        this.a();
        this.a();
    }

    @Override
    protected void c(final NetworkResponse networkResponse) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                NewHandleDualScreenFragment_.super.c(networkResponse);
            }
        }, (long)0);
    }

    @Override
    protected void d(final NetworkResponse networkResponse) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                NewHandleDualScreenFragment_.super.d(networkResponse);
            }
        }, (long)0);
    }

    public View findViewById(int n) {
        if (this.H == null) {
            return null;
        }
        return this.H.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.G);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.H = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.H == null) {
            this.H = layoutInflater.inflate(2130903308, viewGroup, false);
        }
        return this.H;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.H = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.g = null;
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
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("mHandleUpdateType", (Serializable)((Object)this.D));
        bundle.putSerializable("mProfilePicType", (Serializable)((Object)this.E));
        bundle.putBoolean("mShowEmailOpt", this.F);
        bundle.putSerializable("mSplitScreenState", (Serializable)((Object)this.r));
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.G.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, NewHandleDualScreenFragment> {
        public NewHandleDualScreenFragment a() {
            NewHandleDualScreenFragment_ newHandleDualScreenFragment_ = new NewHandleDualScreenFragment_();
            newHandleDualScreenFragment_.setArguments(this.a);
            return newHandleDualScreenFragment_;
        }

        public FragmentBuilder_ a(Boolean bl) {
            this.a.putSerializable("showEmailOptIn", (Serializable)bl);
            return this;
        }

        public FragmentBuilder_ a(String string2) {
            this.a.putString("handle", string2);
            return this;
        }

        public FragmentBuilder_ a(boolean bl) {
            this.a.putBoolean("handlePrefill", bl);
            return this;
        }
    }

}

