/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.widget.TextView
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.widget.TextView;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.Track;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.LeaderboardLovedListItem_;
import com.smule.singandroid.profile.PerformanceListItem;
import java.lang.ref.WeakReference;
import java.util.List;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class LeaderboardLovedListItem
extends PerformanceListItem {
    private static final String p = LeaderboardLovedListItem.class.getName();
    @ViewById
    protected ProfileImageWithVIPBadge a;
    @ViewById
    protected TextView b;
    private WeakReference<BaseFragment> q;

    public LeaderboardLovedListItem(Context context) {
        super(context);
    }

    public static LeaderboardLovedListItem a(Context object, BaseFragment baseFragment) {
        object = LeaderboardLovedListItem_.a((Context)object);
        object.setFragment(baseFragment);
        return object;
    }

    @Override
    protected void c() {
        super.c();
        this.a.setVIP(this.D.accountIcon.a());
        this.a.a(4);
        if (this.D.totalPerformers > 1) {
            if (this.D.f()) {
                this.a.setVIP(false);
                this.a.setPerformanceCount(this.D.totalPerformers - 1);
                this.a.a(this.q.get(), this.D);
                return;
            }
            AccountIcon accountIcon = this.D.recentTracks.get((int)0).accountIcon;
            this.a.setProfilePicUrl(accountIcon.picUrl);
            this.a.a(this.q.get(), accountIcon);
            return;
        }
        this.a.setProfilePicUrl(this.D.accountIcon.picUrl);
        this.a.a(this.q.get(), this.D.accountIcon);
    }

    public void setFragment(BaseFragment baseFragment) {
        this.q = new WeakReference<BaseFragment>(baseFragment);
    }

    public void setRank(int n) {
        this.b.setText((CharSequence)("" + n));
    }
}

