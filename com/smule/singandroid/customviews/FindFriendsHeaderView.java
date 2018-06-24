package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.smule.android.facebook.MagicFacebook;
import com.smule.singandroid.FacebookFriendsFragment;
import com.smule.singandroid.InviteFriendsFragment;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.utils.SingAnalytics;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class FindFriendsHeaderView extends LinearLayout {
    public static final String f21544a = FindFriendsHeaderView.class.getName();
    @ViewById
    protected ViewGroup f21545b;
    @ViewById
    protected View f21546c;
    @ViewById
    protected View f21547d;
    @ViewById
    protected View f21548e;
    @ViewById
    protected View f21549f;
    private boolean f21550g = false;

    public static FindFriendsHeaderView m23176a(Context context) {
        return FindFriendsHeaderView_.m23181a(context, null);
    }

    public FindFriendsHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void m23177a() {
        if (!this.f21550g) {
            int i = (MagicFacebook.a().c() && MagicFacebook.a().h()) ? 1 : 0;
            if (i != 0) {
                this.f21546c.setVisibility(0);
                this.f21547d.setVisibility(8);
                return;
            }
            this.f21546c.setVisibility(8);
            this.f21547d.setVisibility(0);
        }
    }

    @Click
    protected void m23178b() {
        SingAnalytics.m26158v();
        MasterActivity b = MasterActivity.b(getContext());
        if (b != null) {
            b.a(FacebookFriendsFragment.m20024a(), FacebookFriendsFragment.m20029z());
        }
    }

    @Click
    protected void m23179c() {
        MasterActivity b = MasterActivity.b(getContext());
        if (b != null) {
            b.a(InviteFriendsFragment.m20242a(), InviteFriendsFragment.m20244z());
        }
    }

    public void m23180d() {
        this.f21550g = true;
        this.f21545b.setVisibility(8);
    }
}
