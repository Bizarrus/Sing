/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Fragment
 *  android.content.Context
 *  android.database.DataSetObserver
 *  android.os.Bundle
 *  android.support.annotation.NonNull
 *  android.text.TextUtils
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.AbsListView
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.TextView
 *  com.smule.singandroid.utils.ListViewMediaPlayerObserver
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.UiHandler;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.adapters.OpenCallListAdapter;
import com.smule.singandroid.bookmark.BookmarkDialogCallback;
import com.smule.singandroid.bookmark.MediaPlayingMenuManager;
import com.smule.singandroid.boost.BoostOpenCallListItemView;
import com.smule.singandroid.common.JoinSectionType;
import com.smule.singandroid.list_items.OpenCallListItem;
import com.smule.singandroid.list_items.PerformanceListEmptyListItem;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.mediaplaying.MediaPlayingPlayable;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.utils.ListViewMediaPlayerObserver;
import java.util.ArrayList;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public abstract class OpenCallListFragment
extends BaseFragment
implements OpenCallListAdapter.OpenCallListAdapterUIDelegate {
    private static final String g = OpenCallListFragment.class.getName();
    private OpenCallListListener h;
    private OpenCallListAdapter.HasMorePagesListener i;
    private DataSetObserver j;
    protected OpenCallListAdapter k;
    protected OpenCallListAdapter l;
    protected OpenCallListAdapter m;
    protected OpenCallListAdapter n;
    @ViewById
    protected View o;
    @ViewById
    protected TextView p;
    @ViewById
    protected View q;
    @ViewById
    protected View r;
    @ViewById
    protected ListView s;
    @ViewById
    protected View t;
    @ViewById
    protected PerformanceListEmptyListItem u;
    @InstanceState
    protected ArrayList<PerformanceV2> v = new ArrayList();
    @InstanceState
    protected ArrayList<PerformanceV2> w = new ArrayList();
    @InstanceState
    protected ArrayList<PerformanceV2> x = new ArrayList();
    @InstanceState
    protected int y;
    @InstanceState
    protected boolean z;

    public OpenCallListFragment() {
        this.i = new OpenCallListAdapter.HasMorePagesListener(){

            @Override
            public void a() {
                if (OpenCallListFragment.this.k.getCount() <= 0) {
                    OpenCallListFragment.this.e(0);
                }
            }

            @Override
            public void b() {
                OpenCallListFragment.this.e(1);
            }
        };
        this.j = new DataSetObserver(){

            public void onChanged() {
                if (OpenCallListFragment.this.isAdded()) {
                    OpenCallListFragment.this.t.setVisibility(4);
                }
            }
        };
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(@NonNull PerformanceV2 performanceV2) {
        int n;
        OpenCallListAdapter openCallListAdapter = this.k;
        int n2 = openCallListAdapter.getCount();
        ArrayList<MediaPlayingPlayable> arrayList = new ArrayList<MediaPlayingPlayable>(n2);
        int n3 = -1;
        for (n = 0; n < n2; ++n) {
            PerformanceV2 performanceV22 = (PerformanceV2)openCallListAdapter.getItem(n);
            if (performanceV22 == null) {
                Log.e(g, "cannot do ContinuousPlay with null performances");
                continue;
            }
            arrayList.add(new MediaPlayingPlayable(performanceV22));
            if (TextUtils.isEmpty((CharSequence)performanceV2.performanceKey) || !performanceV2.performanceKey.equals(performanceV22.performanceKey)) continue;
            n3 = arrayList.size() - 1;
        }
        n = n3;
        if (n3 == -1) {
            Log.e(g, "playable not found in adapter, adding it to top of playlist");
            arrayList.add(0, new MediaPlayingPlayable(performanceV2));
            n = 0;
        }
        this.a(arrayList, n);
    }

    protected abstract JoinSectionType F();

    protected boolean G() {
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    @AfterViews
    protected void I() {
        block6 : {
            boolean bl = new SingServerValues().ap();
            this.l = new OpenCallListAdapter(this, this.x);
            this.l.b(bl);
            this.m = new OpenCallListAdapter(this, this.v);
            this.n = new OpenCallListAdapter(this, this.w);
            this.n.a(true);
            if (bl) {
                this.k = this.l;
            }
            Object object = this.t();
            switch (.a[object.ordinal()]) {
                default: {
                    object = bl ? this.l : this.m;
                }
                case 1: {
                    object = bl ? this.l : this.m;
                    this.k = object;
                    break block6;
                }
                case 2: {
                    this.k = this.m;
                    break block6;
                }
                case 3: {
                    this.k = this.n;
                    break block6;
                }
            }
            this.k = object;
        }
        this.s.setAdapter((ListAdapter)this.k);
        this.m.a(this.i);
        this.n.a(this.i);
        this.l.a(this.i);
        this.k.e();
        this.m.registerDataSetObserver(this.j);
        this.n.registerDataSetObserver(this.j);
        this.l.registerDataSetObserver(this.j);
    }

    @Click
    public void J() {
        ((MediaPlayingActivity)this.getActivity()).am().a(this.o);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public View a(View object, ViewGroup viewGroup, PerformanceV2 performanceV2, OpenCallListAdapter.ViewType viewType, int n) {
        boolean bl = n == 0;
        if (object == null) {
            object = viewType == OpenCallListAdapter.ViewType.a ? OpenCallListItem.c((Context)this.getActivity()) : BoostOpenCallListItemView.a((Context)this.getActivity());
            object.setIsSectionFree(this.G());
        } else {
            object = (OpenCallListItem)object;
        }
        object.a(performanceV2, bl);
        object.a(false);
        object.setExpandedPerformanceListener(new OpenCallListItem.ExpandedPerformanceItemListener(){

            @Override
            public void a(PerformanceV2 performanceV2, boolean bl) {
                final MediaPlayingActivity mediaPlayingActivity = (MediaPlayingActivity)OpenCallListFragment.this.getActivity();
                if (mediaPlayingActivity != null) {
                    mediaPlayingActivity.am().a(performanceV2, new BookmarkDialogCallback(){

                        @Override
                        public void a(PerformanceV2 performanceV2) {
                            new UiHandler((Activity)mediaPlayingActivity).a(new Runnable(){

                                @Override
                                public void run() {
                                    mediaPlayingActivity.am().a(OpenCallListFragment.this, OpenCallListFragment.this.o, OpenCallListFragment.this.p, true);
                                }
                            });
                            NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "BOOKMARKED_PERFORMANCE", performanceV2);
                        }

                        @Override
                        public void b(PerformanceV2 performanceV2) {
                            new UiHandler((Activity)mediaPlayingActivity).a(new Runnable(){

                                @Override
                                public void run() {
                                    mediaPlayingActivity.am().a(OpenCallListFragment.this, OpenCallListFragment.this.o, OpenCallListFragment.this.p, false);
                                }
                            });
                            NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "FAVORITED_PERFORMANCE", performanceV2);
                        }

                        @Override
                        public void c(PerformanceV2 performanceV2) {
                            NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "UNBOOKMARKED_PERFORMANCE", performanceV2);
                        }

                        @Override
                        public void d(PerformanceV2 performanceV2) {
                            NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "UNFAVORITED_PERFORMANCE", performanceV2);
                        }

                    }, bl);
                }
            }

            @Override
            public void a(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
                if (OpenCallListFragment.this.h != null) {
                    OpenCallListFragment.this.h.a(performanceV2);
                }
            }

            @Override
            public void b(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
                OpenCallListFragment.this.a(performanceV2);
            }

            @Override
            public void c(OpenCallListItem object, PerformanceV2 performanceV2) {
                object = ProfileFragment.a(performanceV2.accountIcon);
                if (OpenCallListFragment.this.p() != null) {
                    OpenCallListFragment.this.p().a((BaseFragment)((Object)object), object.t());
                }
            }

            @Override
            public void d(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
                OpenCallListFragment.this.a(performanceV2);
            }

        });
        return object;
    }

    public void a(OpenCallListListener openCallListListener) {
        this.h = openCallListListener;
    }

    public void c(boolean bl) {
        this.z = bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void e(int n) {
        int n2 = 4;
        if (!this.isAdded()) {
            Log.d(g, "Fragment not added in updateViewVisibility.");
            return;
        } else {
            View view = this.r;
            int n3 = n == 0 ? 0 : 4;
            view.setVisibility(n3);
            view = this.q;
            n3 = n == 0 ? n2 : 0;
            view.setVisibility(n3);
            if (n == 0) {
                this.u.setModeEmptyOpenCallListFragment(this.F());
            }
            if (this.h == null) return;
            {
                this.h.d(n);
                return;
            }
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.s.setSelection(this.y);
        ListViewMediaPlayerObserver.a((AbsListView)this.s);
    }

    @Override
    public void onStop() {
        super.onStop();
        this.y = this.s.getSelectedItemPosition();
        ListViewMediaPlayerObserver.b((AbsListView)this.s);
    }

    protected abstract JoinSectionType t();

    @Override
    public String x() {
        return g;
    }

    public static interface OpenCallListListener {
        public void a(PerformanceV2 var1);

        public void d(int var1);
    }

}

