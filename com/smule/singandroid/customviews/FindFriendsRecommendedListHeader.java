/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.widget.LinearLayout
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.InviteFriendsFragment;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.chat.ChatShareInviteActivity;
import com.smule.singandroid.customviews.FindFriendsRecommendedListHeader_;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class FindFriendsRecommendedListHeader
extends LinearLayout {
    public static final String a = FindFriendsRecommendedListHeader.class.getName();
    @ViewById
    protected View b;
    private boolean c = false;

    public FindFriendsRecommendedListHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static FindFriendsRecommendedListHeader a(Context context) {
        return FindFriendsRecommendedListHeader_.a(context, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Click
    protected void a() {
        MasterActivity masterActivity = MasterActivity.b(this.getContext());
        if (this.getContext() instanceof ChatShareInviteActivity) {
            ((ChatShareInviteActivity)this.getContext()).A();
            return;
        } else {
            if (masterActivity == null) return;
            {
                masterActivity.a(InviteFriendsFragment.a(), InviteFriendsFragment.t());
                return;
            }
        }
    }

    public void b() {
        this.c = true;
    }
}

