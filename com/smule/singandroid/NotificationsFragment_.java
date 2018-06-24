/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.support.design.widget.TabLayout
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.UiThreadExecutor
 *  org.androidannotations.api.builder.FragmentBuilder
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.network.models.NotificationListItem;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.CustomViewPager;
import com.smule.singandroid.NotificationsFragment;
import java.io.Serializable;
import java.util.ArrayList;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class NotificationsFragment_
extends NotificationsFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier x = new OnViewChangedNotifier();
    private View y;

    public static FragmentBuilder_ N() {
        return new FragmentBuilder_();
    }

    private void O() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            if (bundle.containsKey("mNotificationKeys")) {
                this.r = bundle.getStringArrayList("mNotificationKeys");
            }
            if (bundle.containsKey("mSuppressPerformances")) {
                this.s = bundle.getBoolean("mSuppressPerformances");
            }
            if (bundle.containsKey("mDetailedType")) {
                this.t = (Object)bundle.getSerializable("mDetailedType");
            }
        }
    }

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.O();
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.h = bundle.getBoolean("mIsShowingActivity");
        this.i = bundle.getBoolean("mSuppressChatTooltipOnActivityTab");
        this.v = bundle.getParcelable("mListViewStateActivity");
        this.w = bundle.getParcelable("mListViewStateInvite");
    }

    @Override
    public void G() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                NotificationsFragment_.super.G();
            }
        }, (long)0);
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        NotificationsFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.j = (TabLayout)hasViews.findViewById(2131756077);
        this.k = (CustomViewPager)hasViews.findViewById(2131756078);
        this.t();
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
            this.y = layoutInflater.inflate(2130903327, viewGroup, false);
        }
        return this.y;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.y = null;
        this.j = null;
        this.k = null;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("mIsShowingActivity", this.h);
        bundle.putBoolean("mSuppressChatTooltipOnActivityTab", this.i);
        bundle.putParcelable("mListViewStateActivity", this.v);
        bundle.putParcelable("mListViewStateInvite", this.w);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.x.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, NotificationsFragment> {
        public NotificationsFragment a() {
            NotificationsFragment_ notificationsFragment_ = new NotificationsFragment_();
            notificationsFragment_.setArguments(this.a);
            return notificationsFragment_;
        }

        public FragmentBuilder_ a(NotificationListItem detailedType) {
            this.a.putSerializable("mDetailedType", (Serializable)((Object)detailedType));
            return this;
        }

        public FragmentBuilder_ a(ArrayList<String> arrayList) {
            this.a.putStringArrayList("mNotificationKeys", arrayList);
            return this;
        }

        public FragmentBuilder_ a(boolean bl) {
            this.a.putBoolean("mSuppressPerformances", bl);
            return this;
        }
    }

}

