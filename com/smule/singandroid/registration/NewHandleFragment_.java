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
 *  android.widget.ImageView
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
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.registration.NewHandleFragment;
import java.io.Serializable;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class NewHandleFragment_
extends NewHandleFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier g = new OnViewChangedNotifier();
    private View h;

    private void H() {
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
        this.H();
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.D = (Object)bundle.getSerializable("mHandleUpdateType");
        this.E = (Object)bundle.getSerializable("mProfilePicType");
        this.F = bundle.getBoolean("mShowEmailOpt");
    }

    public static FragmentBuilder_ t() {
        return new FragmentBuilder_();
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        NewHandleFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.t = (ViewGroup)hasViews.findViewById(2131755221);
        this.u = (EditText)hasViews.findViewById(2131756022);
        this.v = (TextView)hasViews.findViewById(2131756035);
        this.w = (TextView)hasViews.findViewById(2131756034);
        this.x = hasViews.findViewById(2131755264);
        this.y = (CheckBox)hasViews.findViewById(2131756024);
        this.z = (ImageView)hasViews.findViewById(2131755407);
        if (this.v != null) {
            this.v.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    NewHandleFragment_.this.L();
                }
            });
        }
        if (this.w != null) {
            this.w.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    NewHandleFragment_.this.L();
                }
            });
        }
        this.a();
    }

    @Override
    protected void c(final NetworkResponse networkResponse) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                NewHandleFragment_.super.c(networkResponse);
            }
        }, (long)0);
    }

    @Override
    protected void d(final NetworkResponse networkResponse) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                NewHandleFragment_.super.d(networkResponse);
            }
        }, (long)0);
    }

    public View findViewById(int n) {
        if (this.h == null) {
            return null;
        }
        return this.h.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.g);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.h = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.h == null) {
            this.h = layoutInflater.inflate(2130903309, viewGroup, false);
        }
        return this.h;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.h = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("mHandleUpdateType", (Serializable)((Object)this.D));
        bundle.putSerializable("mProfilePicType", (Serializable)((Object)this.E));
        bundle.putBoolean("mShowEmailOpt", this.F);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.g.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, NewHandleFragment> {
        public NewHandleFragment a() {
            NewHandleFragment_ newHandleFragment_ = new NewHandleFragment_();
            newHandleFragment_.setArguments(this.a);
            return newHandleFragment_;
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

