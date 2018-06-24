/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Fragment
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.support.annotation.NonNull
 *  android.text.TextUtils
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.TextView
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity$StartupMode
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_$IntentBuilder_
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer$ActionBarHighlightMode
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.ViewById
 *  org.androidannotations.api.builder.PostActivityStarter
 */
package com.smule.singandroid.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.UiHandler;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.bookmark.BookmarkDialogCallback;
import com.smule.singandroid.bookmark.MediaPlayingMenuManager;
import com.smule.singandroid.common.JoinSectionType;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.fragments.OpenCallPerformancesListFragment_;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.list_items.OpenCallListItem;
import com.smule.singandroid.list_items.PerfCountHeader;
import com.smule.singandroid.list_items.PerformanceItemInterface;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.mediaplaying.MediaPlayingPlayable;
import com.smule.singandroid.profile.PerformanceListItem;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity_;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.builder.PostActivityStarter;

@EFragment
public class OpenCallPerformancesListFragment
extends BaseFragment {
    private static final String o = OpenCallPerformancesListFragment.class.getName();
    @InstanceState
    protected PerformanceV2 g;
    @InstanceState
    protected boolean h;
    OpenCallListItem i;
    @ViewById
    protected View j;
    @ViewById
    protected TextView k;
    @ViewById
    protected View l;
    @ViewById
    protected MediaListView m;
    final Observer n;
    private OpenCallPerformancesListAdapter p;
    private LocalizedShortNumberFormatter q;
    private PerformanceItemInterface.PerformanceItemListener r;

    public OpenCallPerformancesListFragment() {
        this.r = new PerformanceItemInterface.PerformanceItemListener(){

            @Override
            public void a(MediaPlayingViewInterface mediaPlayingViewInterface, AccountIcon accountIcon) {
                OpenCallPerformancesListFragment.this.a(accountIcon);
            }

            @Override
            public void a(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
                OpenCallPerformancesListFragment.this.b(performanceV2);
            }

            @Override
            public void b(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
                OpenCallPerformancesListFragment.this.b(performanceV2);
            }
        };
        this.n = new Observer(){

            /*
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            @Override
            public void update(Observable object, Object object2) {
                if (!(object2 instanceof HashMap)) return;
                object = (HashMap)object2;
                if (object.containsKey("DELETED_PERFORMANCE")) {
                    object2 = (PerformanceV2)object.get("DELETED_PERFORMANCE");
                    if (OpenCallPerformancesListFragment.this.g != null && OpenCallPerformancesListFragment.this.g.performanceKey != null && object2 != null && OpenCallPerformancesListFragment.this.g.performanceKey.equals(object2.performanceKey) && OpenCallPerformancesListFragment.this.p() != null) {
                        OpenCallPerformancesListFragment.this.p().b(OpenCallPerformancesListFragment.this);
                        return;
                    }
                }
                if (!object.containsKey("UPDATED_PERFORMANCE")) return;
                if (OpenCallPerformancesListFragment.this.i == null) return;
                if ((object = (PerformanceV2)object.get("UPDATED_PERFORMANCE")) == null) return;
                if (!OpenCallPerformancesListFragment.this.g.performanceKey.equals(object.performanceKey)) return;
                OpenCallPerformancesListFragment.this.g = object;
                OpenCallPerformancesListFragment.this.i.a(OpenCallPerformancesListFragment.this.g, true);
            }
        };
    }

    private LocalizedShortNumberFormatter G() {
        if (this.q == null) {
            this.q = new LocalizedShortNumberFormatter((Context)this.getActivity());
        }
        return this.q;
    }

    private void H() {
        this.m.setVisibility(0);
        this.m.setBackgroundColor(0);
        this.m.setMagicAdapter(this.p);
        this.m.setDivider(null);
        this.m.setDividerHeight(0);
        this.a((AbsListView)this.m, QuickReturnListViewMenuSyncer.ActionBarHighlightMode.a, null);
    }

    public static OpenCallPerformancesListFragment a(PerformanceV2 performanceV2) {
        return OpenCallPerformancesListFragment.a(performanceV2, false);
    }

    public static OpenCallPerformancesListFragment a(PerformanceV2 performanceV2, boolean bl) {
        OpenCallPerformancesListFragment_ openCallPerformancesListFragment_ = new OpenCallPerformancesListFragment_();
        openCallPerformancesListFragment_.g = performanceV2;
        openCallPerformancesListFragment_.h = bl;
        return openCallPerformancesListFragment_;
    }

    private void a(AccountIcon accountIcon) {
        this.a(ProfileFragment.a(accountIcon));
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(@NonNull PerformanceV2 performanceV2) {
        int n;
        OpenCallPerformancesListAdapter openCallPerformancesListAdapter = this.p;
        int n2 = openCallPerformancesListAdapter.d();
        ArrayList<MediaPlayingPlayable> arrayList = new ArrayList<MediaPlayingPlayable>(n2);
        int n3 = -1;
        for (n = 0; n < n2; ++n) {
            PerformanceV2 performanceV22 = (PerformanceV2)openCallPerformancesListAdapter.a(n);
            if (performanceV22 == null) {
                Log.e(o, "cannot do ContinuousPlay with null performances");
                continue;
            }
            arrayList.add(new MediaPlayingPlayable(performanceV22));
            if (TextUtils.isEmpty((CharSequence)performanceV2.performanceKey) || !performanceV2.performanceKey.equals(performanceV22.performanceKey)) continue;
            n3 = arrayList.size() - 1;
        }
        n = n3;
        if (n3 == -1) {
            Log.e(o, "playable not found in adapter, adding it to top of playlist");
            arrayList.add(0, new MediaPlayingPlayable(performanceV2));
            n = 0;
        }
        this.a(arrayList, n);
    }

    private void c(PerformanceV2 performanceV2) {
        PreSingActivity.a((Context)this.getActivity()).a(PreSingActivity.StartupMode.b).a(performanceV2).a();
        SingAnalytics.a((String)PerformanceV2Util.i((PerformanceV2)performanceV2), (JoinSectionType)JoinSectionType.e, (String)PerformanceV2Util.h((PerformanceV2)performanceV2));
    }

    @Click
    protected void F() {
        ((MediaPlayingActivity)this.getActivity()).am().a(this.j);
    }

    @AfterViews
    protected void a() {
        this.t();
        this.H();
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.p = new OpenCallPerformancesListAdapter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.i = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.c(2131296707);
    }

    @Override
    public void onStart() {
        super.onStart();
        NotificationCenter.a().a("PERFORMANCE_UPDATED_NOTIFICATION", this.n);
    }

    @Override
    public void onStop() {
        super.onStop();
        NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", this.n);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void t() {
        this.i = OpenCallListItem.c((Context)this.getActivity());
        this.i.a(this.g, true);
        this.i.c(true);
        this.i.setExpandedPerformanceListener(new OpenCallListItem.ExpandedPerformanceItemListener(){

            @Override
            public void a(PerformanceV2 performanceV2, boolean bl) {
                final MediaPlayingActivity mediaPlayingActivity = (MediaPlayingActivity)OpenCallPerformancesListFragment.this.getActivity();
                mediaPlayingActivity.am().a(performanceV2, new BookmarkDialogCallback(){

                    @Override
                    public void a(PerformanceV2 performanceV2) {
                        new UiHandler((Activity)mediaPlayingActivity).a(new Runnable(){

                            @Override
                            public void run() {
                                mediaPlayingActivity.am().a(OpenCallPerformancesListFragment.this, OpenCallPerformancesListFragment.this.j, OpenCallPerformancesListFragment.this.k, true);
                            }
                        });
                        NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "BOOKMARKED_PERFORMANCE", performanceV2);
                    }

                    @Override
                    public void b(PerformanceV2 performanceV2) {
                        new UiHandler((Activity)mediaPlayingActivity).a(new Runnable(){

                            @Override
                            public void run() {
                                mediaPlayingActivity.am().a(OpenCallPerformancesListFragment.this, OpenCallPerformancesListFragment.this.j, OpenCallPerformancesListFragment.this.k, false);
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

            @Override
            public void a(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
                OpenCallPerformancesListFragment.this.c(performanceV2);
            }

            @Override
            public void b(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
                OpenCallPerformancesListFragment.this.p().a(performanceV2, true, true);
            }

            @Override
            public void c(OpenCallListItem object, PerformanceV2 performanceV2) {
                object = ProfileFragment.a(performanceV2.accountIcon);
                OpenCallPerformancesListFragment.this.p().a((BaseFragment)((Object)object), object.t());
            }

            @Override
            public void d(OpenCallListItem openCallListItem, PerformanceV2 performanceV2) {
                OpenCallPerformancesListFragment.this.p().a(performanceV2, true, true);
            }

        });
        this.m.addHeaderView((View)this.i);
        PerfCountHeader perfCountHeader = PerfCountHeader.a((Context)this.getActivity());
        if (this.g.childCount == 0) {
            perfCountHeader.setText(this.getResources().getString(2131296875));
        } else {
            perfCountHeader.setText(this.getResources().getQuantityString(2131361806, this.g.childCount, new Object[]{this.G().a(this.g.childCount, this.getResources().getInteger(2131623962))}));
        }
        this.m.addHeaderView((View)perfCountHeader);
    }

    @Override
    public String x() {
        return o;
    }

    protected class OpenCallPerformancesDataSource
    extends MagicDataSource<PerformanceV2, MagicDataSource.OffsetPaginationTracker> {
        public OpenCallPerformancesDataSource(Context context) {
            super(new MagicDataSource.OffsetPaginationTracker());
        }

        @Override
        public int a() {
            return 15;
        }

        @Override
        public Future<?> a(MagicDataSource.OffsetPaginationTracker offsetPaginationTracker, int n, final MagicDataSource.FetchDataCallback<PerformanceV2, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
            return com.smule.android.network.managers.PerformanceManager.a().a(OpenCallPerformancesListFragment.this.g.performanceKey, offsetPaginationTracker.a(), (Integer)15, new PerformanceManager(){

                @Override
                public void handleResponse(PerformanceManager.PerformancesResponse performancesResponse) {
                    if (performancesResponse.a()) {
                        fetchDataCallback.a(performancesResponse.mPerformances, new MagicDataSource.OffsetPaginationTracker(performancesResponse.mNext));
                        return;
                    }
                    fetchDataCallback.a();
                    OpenCallPerformancesDataSource.this.a(performancesResponse.a);
                }
            });
        }

        protected void a(NetworkResponse networkResponse) {
            if (networkResponse.e()) {
                ((BaseActivity)OpenCallPerformancesListFragment.this.getActivity()).a(networkResponse.f, true, new Runnable(){

                    @Override
                    public void run() {
                        OpenCallPerformancesListFragment.this.b(OpenCallPerformancesListFragment.this);
                    }
                });
            }
        }

    }

    private class OpenCallPerformancesListAdapter
    extends MagicAdapter {
        public final String c;

        public OpenCallPerformancesListAdapter() {
            super(new OpenCallPerformancesDataSource((Context)OpenCallPerformancesListFragment.this.getActivity()));
            this.c = OpenCallPerformancesListAdapter.class.getSimpleName();
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public View a(ViewGroup viewGroup, int n) {
            boolean bl;
            if (OpenCallPerformancesListFragment.this.g.accountIcon.accountId == UserManager.a().f()) {
                bl = true;
                do {
                    return PerformanceListItem.a((Context)OpenCallPerformancesListFragment.this.getActivity(), bl, OpenCallPerformancesListFragment.this.h);
                    break;
                } while (true);
            }
            bl = false;
            return PerformanceListItem.a((Context)OpenCallPerformancesListFragment.this.getActivity(), bl, OpenCallPerformancesListFragment.this.h);
        }

        @Override
        public void a(View object, int n, int n2) {
            Log.b(this.c, "getView - PERFORMANCE_TYPE_OPEN_CALL - playingSongUID is: " + MediaPlayerServiceController.a().i() + ", position is: " + n);
            object = (PerformanceListItem)object;
            PerformanceV2 performanceV2 = (PerformanceV2)this.a(n);
            object.a(true, OpenCallPerformancesListFragment.this);
            object.setPerformance(performanceV2);
            object.setListener(OpenCallPerformancesListFragment.this.r);
        }
    }

}

