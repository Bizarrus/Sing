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
 *  android.widget.TextView
 *  android.widget.ToggleButton
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.builder.FragmentBuilder
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.InviteFriendsFragment;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class InviteFriendsFragment_
extends InviteFriendsFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier x = new OnViewChangedNotifier();
    private View y;

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.r = (Intent)bundle.getParcelable("mMessengerIntent");
        this.s = (Intent)bundle.getParcelable("mLineIntent");
        this.t = (Intent)bundle.getParcelable("mWhatsAppShareIntent");
        this.u = (Intent)bundle.getParcelable("mSmsShareIntent");
        this.v = (Intent)bundle.getParcelable("mEmailShareIntent");
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        InviteFriendsFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.h = (TextView)hasViews.findViewById(2131755857);
        this.i = (TextView)hasViews.findViewById(2131755859);
        this.j = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755856);
        this.k = (ViewGroup)hasViews.findViewById(2131755863);
        this.l = hasViews.findViewById(2131755864);
        this.m = hasViews.findViewById(2131755865);
        this.n = hasViews.findViewById(2131755866);
        this.o = hasViews.findViewById(2131755867);
        this.p = hasViews.findViewById(2131755868);
        this.q = (ToggleButton)hasViews.findViewById(2131755862);
        View view = hasViews.findViewById(2131755861);
        View view2 = hasViews.findViewById(2131755869);
        hasViews = hasViews.findViewById(2131755870);
        if (this.q != null) {
            this.q.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    InviteFriendsFragment_.this.H();
                }
            });
        }
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    InviteFriendsFragment_.this.I();
                }
            });
        }
        if (this.l != null) {
            this.l.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    InviteFriendsFragment_.this.a(view);
                }
            });
        }
        if (this.m != null) {
            this.m.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    InviteFriendsFragment_.this.b(view);
                }
            });
        }
        if (this.n != null) {
            this.n.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    InviteFriendsFragment_.this.c(view);
                }
            });
        }
        if (this.o != null) {
            this.o.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    InviteFriendsFragment_.this.d(view);
                }
            });
        }
        if (this.p != null) {
            this.p.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    InviteFriendsFragment_.this.e(view);
                }
            });
        }
        if (view2 != null) {
            view2.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    InviteFriendsFragment_.this.f(view);
                }
            });
        }
        if (hasViews != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    InviteFriendsFragment_.this.g(view);
                }
            });
        }
        this.F();
    }

    public View findViewById(int n) {
        if (this.y == null) {
            return null;
        }
        return this.y.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.x);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.y = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.y == null) {
            this.y = layoutInflater.inflate(2130903255, viewGroup, false);
        }
        return this.y;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.y = null;
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

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mMessengerIntent", (Parcelable)this.r);
        bundle.putParcelable("mLineIntent", (Parcelable)this.s);
        bundle.putParcelable("mWhatsAppShareIntent", (Parcelable)this.t);
        bundle.putParcelable("mSmsShareIntent", (Parcelable)this.u);
        bundle.putParcelable("mEmailShareIntent", (Parcelable)this.v);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.x.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, InviteFriendsFragment> {
    }

}

