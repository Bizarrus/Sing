/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$UserRelationType
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.logging.Analytics;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.NotificationCenter;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.FollowListFragment_;
import com.smule.singandroid.list_items.UserFollowListItem;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.utils.SingAnalytics;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public class FollowListFragment
extends BaseFragment {
    private static final String l = FollowListFragment.class.getName();
    @InstanceState
    protected long g;
    @InstanceState
    protected String h;
    @InstanceState
    protected int i;
    @InstanceState
    protected int j;
    @ViewById
    protected MagicListView k;

    public static FollowListFragment a(long l, String string2, int n, int n2) {
        FollowListFragment_ followListFragment_ = new FollowListFragment_();
        Bundle bundle = new Bundle();
        bundle.putLong("FOLLOW_LIST_ACCOUNT_ID_KEY", l);
        bundle.putString("FOLLOW_LIST_PICTURE_URL", string2);
        bundle.putInt("FOLLOW_LIST_DISPLAY_MODE_KEY", n);
        bundle.putInt("FOLLOW_LIST_CREATED_COUNT_KEY", n2);
        followListFragment_.setArguments(bundle);
        return followListFragment_;
    }

    private SingAnalytics.UserRelationType t() {
        if (this.g == UserManager.a().f()) {
            return SingAnalytics.UserRelationType.a;
        }
        return SingAnalytics.UserRelationType.b;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void A() {
        if (this.i == 1) {
            SingAnalytics.c((SingAnalytics.UserRelationType)this.t(), (int)this.j);
            return;
        } else {
            if (this.i != 0) return;
            {
                SingAnalytics.b((SingAnalytics.UserRelationType)this.t(), (int)this.j);
                return;
            }
        }
    }

    @AfterViews
    public void a() {
        FollowListAdapter followListAdapter = new FollowListAdapter(new FollowManager.FollowListDataSource(this.i, this.g, null, null));
        this.k.setMagicAdapter(followListAdapter);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = this.getArguments();
            this.g = bundle.getLong("FOLLOW_LIST_ACCOUNT_ID_KEY");
            this.h = bundle.getString("FOLLOW_LIST_PICTURE_URL");
            this.i = bundle.getInt("FOLLOW_LIST_DISPLAY_MODE_KEY", -1);
            this.j = bundle.getInt("FOLLOW_LIST_CREATED_COUNT_KEY", 0);
        }
        if (this.i == -1) {
            throw new RuntimeException("Display mode not set properly when creating activity. Finishing now.");
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onStart() {
        super.onStart();
        if (this.i == 0) {
            this.c(2131296689);
            return;
        } else {
            if (this.i != 1) return;
            {
                this.c(2131296690);
                return;
            }
        }
    }

    @Override
    public String x() {
        return l;
    }

    private class FollowListAdapter
    extends MagicAdapter {
        public FollowListAdapter(MagicDataSource magicDataSource) {
            super(magicDataSource);
        }

        @Override
        public View a(ViewGroup viewGroup, int n) {
            return UserFollowListItem.c((Context)FollowListFragment.this.getActivity());
        }

        @Override
        public void a(View view, int n, int n2) {
            ((UserFollowListItem)view).a((AccountIcon)this.a(n), n, (Context)FollowListFragment.this.getActivity(), false, new UserFollowListItem.UserFollowListItemListener(){

                @Override
                public void a(Analytics searchResultClkContext) {
                }

                @Override
                public void a(ProfileFragment profileFragment) {
                    FollowListFragment.this.p().a(profileFragment, profileFragment.t());
                }

                @Override
                public void a(boolean bl, boolean bl2) {
                    NotificationCenter.a().b("PROFILE_UPDATED_NOTIFICATION", new Object[0]);
                }
            }, true);
        }

        @Override
        protected void a(View view, int n, boolean bl) {
            super.a(view, n, bl);
            UserFollowListItem userFollowListItem = (UserFollowListItem)view;
            if (bl) {
                if (this.getSectionForPosition(n) == 0) {
                    ((UserFollowListItem)view).a(0, FollowListFragment.this.getResources().getString(2131296998));
                    return;
                }
                ((UserFollowListItem)view).a(0, FollowListFragment.this.getResources().getString(2131296997));
                return;
            }
            ((UserFollowListItem)view).a(8, "");
        }

        @Override
        public View b(ViewGroup viewGroup) {
            return LayoutInflater.from((Context)FollowListFragment.this.getActivity()).inflate(2130903252, viewGroup, false);
        }

        @Override
        public View d(ViewGroup viewGroup) {
            return LayoutInflater.from((Context)FollowListFragment.this.getActivity()).inflate(2130903250, null, false);
        }

        @Override
        public int getPositionForSection(int n) {
            FollowManager.FollowListDataSource followListDataSource = (FollowManager.FollowListDataSource)this.a();
            if (n == 0) {
                return 0;
            }
            return followListDataSource.e();
        }

        @Override
        public int getSectionForPosition(int n) {
            if (n < ((FollowManager.FollowListDataSource)this.a()).e()) {
                return 0;
            }
            return 1;
        }

        @Override
        public Object[] getSections() {
            FollowManager.FollowListDataSource followListDataSource = (FollowManager.FollowListDataSource)this.a();
            int n = 0;
            if (followListDataSource.w() > 0) {
                n = 1;
            }
            int n2 = n;
            if (followListDataSource.e() > 0) {
                n2 = n + 1;
            }
            return new Object[n2];
        }

    }

}

