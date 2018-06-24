/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.v4.widget.SwipeRefreshLayout
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.ViewGroup
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.profile;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.profile.ProfileListView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ProfileListView_
extends ProfileListView
implements HasViews,
OnViewChangedListener {
    private boolean k = false;
    private final OnViewChangedNotifier l = new OnViewChangedNotifier();

    public ProfileListView_(Context context) {
        super(context);
        this.f();
    }

    public static ProfileListView a(Context object) {
        object = new ProfileListView_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void f() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.l);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.b = (MediaListView)hasViews.findViewById(2131756387);
        this.c = (SwipeRefreshLayout)hasViews.findViewById(2131756386);
        this.e();
    }

    public void onFinishInflate() {
        if (!this.k) {
            this.k = true;
            ProfileListView_.inflate((Context)this.getContext(), (int)2130903373, (ViewGroup)this);
            this.l.a((HasViews)this);
        }
        super.onFinishInflate();
    }

    public static final class HeaderAnchor_
    extends ProfileListView.HeaderAnchor
    implements HasViews {
        private boolean a = false;
        private final OnViewChangedNotifier b = new OnViewChangedNotifier();

        public HeaderAnchor_(Context context) {
            super(context);
            this.a();
        }

        public static ProfileListView.HeaderAnchor a(Context object) {
            object = new HeaderAnchor_((Context)object);
            object.onFinishInflate();
            return object;
        }

        private void a() {
            OnViewChangedNotifier.a((OnViewChangedNotifier)OnViewChangedNotifier.a((OnViewChangedNotifier)this.b));
        }

        public void onFinishInflate() {
            if (!this.a) {
                this.a = true;
                HeaderAnchor_.inflate((Context)this.getContext(), (int)2130903272, (ViewGroup)this);
                this.b.a((HasViews)this);
            }
            super.onFinishInflate();
        }
    }

}

