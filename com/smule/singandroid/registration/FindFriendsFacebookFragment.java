/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.registration;

import android.app.Activity;
import android.content.Context;
import com.smule.android.datasources.FacebookFriendsDataSource;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.FindFriendsExternalPageView;
import com.smule.singandroid.FindFriendsFacebookPageView;
import com.smule.singandroid.utils.SingAnalytics;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class FindFriendsFacebookFragment
extends BaseFragment {
    private static final String h = FindFriendsFacebookFragment.class.getName();
    @ViewById
    protected FindFriendsFacebookPageView g;
    private FacebookFriendsDataSource i = new FacebookFriendsDataSource();

    public FindFriendsFacebookFragment() {
        this.i.u();
        this.i.o();
    }

    @Override
    protected void A() {
        SingAnalytics.P();
    }

    @AfterViews
    protected void a() {
        this.g.a((Context)this.getActivity(), this, FindFriendsExternalPageView.Mode.b, 2131361801, 2131296785);
        this.g.a();
        try {
            ((AttachListener)this.getActivity()).A_();
            return;
        }
        catch (ClassCastException classCastException) {
            throw new ClassCastException(this.getActivity().toString() + " must implement AttachListener");
        }
    }

    @Override
    public String x() {
        return h;
    }

    public static interface AttachListener {
        public void A_();
    }

}

