/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.os.Handler
 *  android.os.Looper
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.BaseAdapter
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.FragmentArg
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.Track;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.fragments.JoinersListFragment_;
import com.smule.singandroid.list_items.UserFollowListItem;
import com.smule.singandroid.profile.ProfileFragment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

@EFragment
public class JoinersListFragment
extends BaseFragment {
    public static final String g = JoinersListFragment.class.getName();
    @ViewById
    protected ViewGroup h;
    @ViewById
    protected ViewGroup i;
    @ViewById
    protected TextView j;
    @ViewById
    protected ListView k;
    @ViewById
    protected ProgressBar l;
    @FragmentArg
    protected PerformanceV2 m;
    private LocalizedShortNumberFormatter n;

    private LocalizedShortNumberFormatter a() {
        if (this.n == null) {
            this.n = new LocalizedShortNumberFormatter((Context)this.getActivity());
        }
        return this.n;
    }

    public static JoinersListFragment a(PerformanceV2 performanceV2) {
        return JoinersListFragment_.a().a(performanceV2).a();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void a(long l, List<Track> list) {
        if (list.size() == 0) {
            return;
        }
        int n = 0;
        while (n < list.size()) {
            if (l == list.get((int)n).accountIcon.accountId) {
                list.remove(n);
                return;
            }
            ++n;
        }
    }

    private List<Track> t() {
        ArrayList<Track> arrayList = new ArrayList<Track>();
        Iterator<Track> iterator = this.m.recentTracks.iterator();
        while (iterator.hasNext()) {
            arrayList.add(iterator.next());
        }
        return arrayList;
    }

    protected void b(PerformanceV2 object) {
        if (!this.isAdded()) {
            return;
        }
        this.m = object;
        if (this.m != null) {
            object = this.t();
            this.a(this.m.accountIcon.accountId, (List<Track>)object);
            if (object.size() > 0) {
                String string2 = this.getResources().getQuantityString(2131361837, this.m.totalPerformers - 1, new Object[]{this.a().a(this.m.totalPerformers - 1)});
                this.j.setText((CharSequence)string2);
            }
            this.k.setAdapter((ListAdapter)new JoinersAdapter((List<Track>)object));
            this.l.setVisibility(8);
            return;
        }
        this.h.setVisibility(8);
        this.i.setVisibility(0);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (this.m == null || !this.isAdded()) {
            Activity activity = this.getActivity();
            if (activity != null) {
                activity.onBackPressed();
            }
            return;
        }
        if (this.m.recentTracks == null || this.m.recentTracks.size() < 2) {
            com.smule.android.network.managers.PerformanceManager.a().a(this.m.performanceKey, false, new PerformanceManager(){

                @Override
                public void handleResponse(final PerformanceManager.PerformanceResponse performanceResponse) {
                    new Handler(Looper.getMainLooper()).post(new Runnable(){

                        /*
                         * Enabled aggressive block sorting
                         */
                        @Override
                        public void run() {
                            if (performanceResponse.a.e()) {
                                ((BaseActivity)JoinersListFragment.this.getActivity()).a(performanceResponse.a.f, true, new Runnable(){

                                    @Override
                                    public void run() {
                                        JoinersListFragment.this.b(null);
                                    }
                                });
                                return;
                            }
                            JoinersListFragment joinersListFragment = JoinersListFragment.this;
                            PerformanceV2 performanceV2 = performanceResponse.a() ? performanceResponse.mPerformance : null;
                            joinersListFragment.b(performanceV2);
                        }

                    });
                }

            });
            return;
        }
        this.b(this.m);
    }

    @Override
    public String x() {
        return JoinersListFragment.class.getName();
    }

    private final class JoinersAdapter
    extends BaseAdapter
    implements UserFollowListItem.UserFollowListItemListener {
        final List<Track> a;

        public JoinersAdapter(List<Track> list) {
            this.a = list;
        }

        @Override
        public void a(Analytics searchResultClkContext) {
        }

        @Override
        public void a(ProfileFragment profileFragment) {
            JoinersListFragment.this.a(profileFragment);
        }

        @Override
        public void a(boolean bl, boolean bl2) {
            this.notifyDataSetChanged();
        }

        public int getCount() {
            if (this.a != null) {
                return this.a.size();
            }
            return 0;
        }

        public Object getItem(int n) {
            if (this.a != null && n < this.a.size()) {
                return this.a.get(n);
            }
            return null;
        }

        public long getItemId(int n) {
            return n;
        }

        /*
         * Enabled aggressive block sorting
         */
        public View getView(int n, View object, ViewGroup object2) {
            object2 = this.a.get(n);
            object = object == null || !(object instanceof UserFollowListItem) ? UserFollowListItem.c((Context)JoinersListFragment.this.getActivity()) : (UserFollowListItem)((Object)object);
            object.a(object2.accountIcon, JoinersListFragment.this.getActivity(), false, this);
            object.setTime(object2.createdAt);
            object.setJoinersStyle(JoinersListFragment.this.getResources());
            return object;
        }
    }

}

