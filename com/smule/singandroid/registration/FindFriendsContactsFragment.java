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
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.FindFriendsContactsPageView;
import com.smule.singandroid.FindFriendsExternalPageView;
import com.smule.singandroid.registration.FindFriendsFacebookFragment;
import com.smule.singandroid.utils.SingAnalytics;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class FindFriendsContactsFragment
extends BaseFragment {
    private static final String h = FindFriendsContactsFragment.class.getName();
    @ViewById
    protected FindFriendsContactsPageView g;

    @Override
    protected void A() {
        SingAnalytics.R();
    }

    @AfterViews
    protected void a() {
        this.g.a((Context)this.getActivity(), this, FindFriendsExternalPageView.Mode.b, 2131361803, 2131296811);
        this.g.a();
        try {
            ((FindFriendsFacebookFragment.AttachListener)this.getActivity()).A_();
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
}

