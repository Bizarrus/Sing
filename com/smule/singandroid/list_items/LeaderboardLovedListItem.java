package com.smule.singandroid.list_items;

import android.content.Context;
import android.widget.TextView;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.Track;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import java.lang.ref.WeakReference;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class LeaderboardLovedListItem extends PerformanceListItem {
    private static final String f23062o = LeaderboardLovedListItem.class.getName();
    @ViewById
    protected ProfileImageWithVIPBadge f23063a;
    @ViewById
    protected TextView f23064b;
    private WeakReference<BaseFragment> f23065p;

    public LeaderboardLovedListItem(Context context) {
        super(context);
    }

    public static LeaderboardLovedListItem m24368a(Context context, BaseFragment baseFragment) {
        LeaderboardLovedListItem a = LeaderboardLovedListItem_.m24370a(context);
        a.setFragment(baseFragment);
        return a;
    }

    public void setRank(int i) {
        this.f23064b.setText("" + i);
    }

    public void setFragment(BaseFragment baseFragment) {
        this.f23065p = new WeakReference(baseFragment);
    }

    protected void mo6877c() {
        super.mo6877c();
        this.f23063a.setVIP(this.D.accountIcon.a());
        this.f23063a.m23388a(4);
        if (this.D.totalPerformers <= 1) {
            this.f23063a.setProfilePicUrl(this.D.accountIcon.picUrl);
            this.f23063a.m23396a((BaseFragment) this.f23065p.get(), this.D.accountIcon);
        } else if (this.D.e()) {
            this.f23063a.setVIP(false);
            this.f23063a.setPerformanceCount(this.D.totalPerformers - 1);
            this.f23063a.m23397a((BaseFragment) this.f23065p.get(), this.D);
        } else {
            AccountIcon accountIcon = ((Track) this.D.recentTracks.get(0)).accountIcon;
            this.f23063a.setProfilePicUrl(accountIcon.picUrl);
            this.f23063a.m23396a((BaseFragment) this.f23065p.get(), accountIcon);
        }
    }
}
