/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Fragment
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Parcelable
 *  android.support.annotation.NonNull
 *  android.support.design.widget.TabLayout
 *  android.support.design.widget.TabLayout$OnTabSelectedListener
 *  android.support.design.widget.TabLayout$Tab
 *  android.support.v4.view.PagerAdapter
 *  android.support.v4.view.ViewPager
 *  android.support.v4.view.ViewPager$OnPageChangeListener
 *  android.support.v4.widget.SwipeRefreshLayout
 *  android.text.TextUtils
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.TextView
 *  com.smule.singandroid.songbook_search.SearchFragment
 *  com.smule.singandroid.utils.LayoutUtils
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer$ActionBarHighlightMode
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$NotificationFilterType
 *  com.smule.singandroid.utils.SingAnalytics$NotificationScreenType
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.FragmentArg
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.network.models.NotificationItemObject;
import com.smule.android.network.models.NotificationListItem;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.WeakListener;
import com.smule.chat.Chat;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatListenerAdapter;
import com.smule.chat.ChatManager;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.CustomViewPager;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.NotificationsFragment_;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.MessageCenterFragment;
import com.smule.singandroid.customviews.NotificationsListView;
import com.smule.singandroid.customviews.SingTabLayoutHelper;
import com.smule.singandroid.mediaplaying.MediaPlayingPlayable;
import com.smule.singandroid.songbook_search.SearchFragment;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment
public class NotificationsFragment
extends BaseFragment {
    public static final String g = NotificationsFragment.class.getName();
    private int[] A = new int[2];
    private View.OnClickListener B;
    private View.OnClickListener C;
    @InstanceState
    protected boolean h;
    @InstanceState
    protected boolean i;
    @ViewById
    protected TabLayout j;
    @ViewById
    protected CustomViewPager k;
    protected SingTabLayoutHelper l;
    protected ChatManager m;
    protected MenuItem n;
    protected TextView o;
    protected ImageView p;
    protected ChatListener q;
    @FragmentArg
    protected ArrayList<String> r;
    @FragmentArg
    protected boolean s;
    @FragmentArg
    protected NotificationListItem t;
    ViewPager.OnPageChangeListener u;
    @InstanceState
    protected Parcelable v;
    @InstanceState
    protected Parcelable w;
    private WeakListener.OnGlobalLayoutListener x;
    private NotificationsListView.NotificationsBaseAdapter y;
    private NotificationsListView.NotificationsBaseAdapter z;

    public NotificationsFragment() {
        this.B = new View.OnClickListener(){

            public void onClick(View view) {
                if (!NotificationsFragment.this.isAdded()) {
                    return;
                }
                NotificationsFragment.this.a(MessageCenterFragment.a());
            }
        };
        this.C = new View.OnClickListener(){

            public void onClick(View view) {
                if (!NotificationsFragment.this.isAdded()) {
                    return;
                }
                ((MasterActivity)NotificationsFragment.this.getActivity()).A();
            }
        };
        this.u = new ViewPager.OnPageChangeListener(){

            public void onPageScrollStateChanged(int n) {
            }

            public void onPageScrolled(int n, float f, int n2) {
            }

            /*
             * Enabled aggressive block sorting
             */
            public void onPageSelected(int n) {
                final NotificationsListView.NotificationsBaseAdapter notificationsBaseAdapter = n == 0 ? NotificationsFragment.this.y : NotificationsFragment.this.z;
                if (notificationsBaseAdapter == null) {
                    return;
                }
                QuickReturnListViewMenuSyncer.ActionBarHighlightMode actionBarHighlightMode = NotificationsFragment.this.F() == null ? QuickReturnListViewMenuSyncer.ActionBarHighlightMode.b : QuickReturnListViewMenuSyncer.ActionBarHighlightMode.a;
                NotificationsFragment.this.q();
                NotificationsFragment.this.a((AbsListView)notificationsBaseAdapter.k(), actionBarHighlightMode, new AbsListView.OnScrollListener(){

                    public void onScroll(AbsListView absListView, int n, int n2, int n3) {
                        boolean bl = false;
                        if (notificationsBaseAdapter.k() != null && notificationsBaseAdapter.k().getChildCount() > 0 && notificationsBaseAdapter.k().getFirstVisiblePosition() == 0) {
                            n = notificationsBaseAdapter.k().getChildAt(0).getTop();
                            absListView = notificationsBaseAdapter.l();
                            if (n >= 0) {
                                bl = true;
                            }
                            absListView.setEnabled(bl);
                            return;
                        }
                        notificationsBaseAdapter.l().setEnabled(false);
                    }

                    public void onScrollStateChanged(AbsListView absListView, int n) {
                    }
                });
            }

        };
    }

    public static void J() {
        SharedPreferences.Editor editor = SingApplication.g().getSharedPreferences(MasterActivity.class.getName(), 0).edit();
        editor.putBoolean("INITIAL_TAB", true);
        editor.putBoolean("SUPPRESS_CHAT_TOOLTIP_ON_ACTIVITY_TAB", true);
        editor.apply();
    }

    private void N() {
        this.k.addOnPageChangeListener(this.u);
        this.k.setAdapter(new PagerAdapter(){

            public void destroyItem(ViewGroup viewGroup, int n, Object object) {
                viewGroup.removeView((View)object);
            }

            public int getCount() {
                if (NotificationsFragment.this.r == null) {
                    return 2;
                }
                return 1;
            }

            public CharSequence getPageTitle(int n) {
                if (n == 0) {
                    return NotificationsFragment.this.getResources().getString(2131296664);
                }
                return NotificationsFragment.this.getResources().getString(2131296694);
            }

            /*
             * Enabled aggressive block sorting
             */
            public Object instantiateItem(ViewGroup viewGroup, int n) {
                Object object = NotificationsFragment.this.getActivity();
                NotificationsFragment notificationsFragment = NotificationsFragment.this;
                boolean bl = NotificationsFragment.this.k.getCurrentItem() == n;
                object = NotificationsListView.a((Context)object, notificationsFragment, n, bl);
                object.setOrientation(1);
                object.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -1));
                viewGroup.addView((View)object);
                return object;
            }

            public boolean isViewFromObject(View view, Object object) {
                if (view == object) {
                    return true;
                }
                return false;
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    private void O() {
        if (this.h && this.y != null) {
            this.y.n();
            return;
        } else {
            if (this.h || this.z == null) return;
            {
                this.z.n();
                return;
            }
        }
    }

    private void P() {
        ((MasterActivity)this.getActivity()).y();
    }

    private void Q() {
        if (this.y != null && this.y.k() != null) {
            this.a(0, this.y.k().onSaveInstanceState());
        }
        if (this.z != null && this.z.k() != null) {
            this.a(1, this.z.k().onSaveInstanceState());
        }
    }

    private void R() {
        new Handler().post(new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                if (!NotificationsFragment.this.isAdded()) {
                    return;
                }
                View view = NotificationsFragment.this.getActivity().findViewById(2131756850);
                view.getLocationOnScreen(NotificationsFragment.this.A);
                int n = NotificationsFragment.this.A[0];
                int n2 = view.getWidth() / 2;
                int n3 = NotificationsFragment.this.A[1] > 0 ? NotificationsFragment.this.A[1] : NotificationsFragment.this.getResources().getDimensionPixelSize(2131427385) / 2;
                ((MasterActivity)NotificationsFragment.this.getActivity()).a(NotificationsFragment.this, n + n2, n3, NotificationsFragment.this.B, NotificationsFragment.this.C);
            }
        });
    }

    private boolean S() {
        if (this.t != null) {
            return true;
        }
        return false;
    }

    public static NotificationsFragment a() {
        return NotificationsFragment.a(null, null);
    }

    public static NotificationsFragment a(List<String> list, NotificationListItem detailedType) {
        return NotificationsFragment.a(list, false, detailedType);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static NotificationsFragment a(List<String> list, boolean bl, NotificationListItem detailedType) {
        NotificationsFragment_.FragmentBuilder_ fragmentBuilder_ = NotificationsFragment_.N();
        if (list != null) {
            list = new ArrayList<String>(list);
            do {
                return fragmentBuilder_.a((ArrayList<String>)list).a(bl).a(detailedType).a();
                break;
            } while (true);
        }
        list = null;
        return fragmentBuilder_.a((ArrayList<String>)list).a(bl).a(detailedType).a();
    }

    private void a(int n, boolean bl) {
        block5 : {
            block4 : {
                if (this.o == null) break block4;
                if (n <= 0) break block5;
                this.o.setText((CharSequence)MiscUtils.a((int)n));
                this.o.setVisibility(0);
                this.p.setVisibility(8);
            }
            return;
        }
        if (bl) {
            this.o.setVisibility(8);
            this.p.setVisibility(0);
            return;
        }
        this.o.setVisibility(8);
        this.p.setVisibility(8);
    }

    private void a(TabLayout.Tab tab) {
        this.l.a(true, tab);
    }

    public static void a(Tabs tabs) {
        boolean bl = false;
        SharedPreferences.Editor editor = SingApplication.g().getSharedPreferences(MasterActivity.class.getName(), 0).edit();
        if (tabs == Tabs.a) {
            bl = true;
        }
        editor.putBoolean("INITIAL_TAB", bl);
        editor.apply();
    }

    private void a(String string2, int n) {
        TextView textView = (TextView)this.j.getTabAt(n).getCustomView().findViewById(2131756755);
        ImageView imageView = (ImageView)this.j.getTabAt(n).getCustomView().findViewById(2131756756);
        if (string2 == null) {
            textView.setVisibility(8);
            imageView.setVisibility(8);
            return;
        }
        if (string2.isEmpty()) {
            textView.setVisibility(8);
            imageView.setVisibility(0);
            return;
        }
        textView.setText((CharSequence)string2);
        textView.setVisibility(0);
        imageView.setVisibility(8);
    }

    private void b(TabLayout.Tab tab) {
        this.l.a(false, tab);
    }

    @Override
    protected void A() {
        SingAnalytics.y();
    }

    @Override
    protected boolean D() {
        return this.S();
    }

    public ArrayList<String> F() {
        return this.r;
    }

    /*
     * Enabled aggressive block sorting
     */
    @UiThread
    public void G() {
        String string2 = null;
        if (!this.isAdded()) return;
        if (this.j.getVisibility() != 0 || this.y == null || this.z == null) {
            return;
        }
        String string3 = this.y.m() > 0 ? LayoutUtils.a((int)this.y.m()) : null;
        if (this.z.m() > 0) {
            string2 = LayoutUtils.a((int)this.z.m());
        }
        this.a(string3, 0);
        this.a(string2, 1);
        ((MasterActivity)this.getActivity()).C();
    }

    public int H() {
        return this.e;
    }

    public QuickReturnListViewMenuSyncer I() {
        return this.b;
    }

    public SingAnalytics.NotificationScreenType K() {
        if (this.r == null) {
            return SingAnalytics.NotificationScreenType.b;
        }
        return SingAnalytics.NotificationScreenType.a;
    }

    public boolean L() {
        return this.s;
    }

    public int M() {
        return this.j.getSelectedTabPosition();
    }

    public void a(int n, Parcelable parcelable) {
        if (n == 0) {
            this.v = parcelable;
            return;
        }
        this.w = parcelable;
    }

    @Override
    public void a(AbsListView absListView, QuickReturnListViewMenuSyncer.ActionBarHighlightMode actionBarHighlightMode, AbsListView.OnScrollListener onScrollListener) {
        super.a(absListView, actionBarHighlightMode, onScrollListener);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(@NonNull PerformanceV2 performanceV2) {
        int n;
        NotificationsListView.NotificationsBaseAdapter notificationsBaseAdapter = this.z;
        if (notificationsBaseAdapter == null) {
            Log.e(g, "cannot start ContinuousPlay with a null adapter");
            return;
        }
        int n2 = notificationsBaseAdapter.d();
        ArrayList<MediaPlayingPlayable> arrayList = new ArrayList<MediaPlayingPlayable>(n2);
        int n3 = -1;
        for (n = 0; n < n2; ++n) {
            com.smule.android.network.models.NotificationListItem notificationListItem = (com.smule.android.network.models.NotificationListItem)notificationsBaseAdapter.a(n);
            if (notificationListItem == null || notificationListItem.object == null || notificationListItem.object.performanceIcon == null) {
                Log.e(g, "cannot do ContinuousPlay with null performances");
                continue;
            }
            PerformanceV2 performanceV22 = notificationListItem.object.performanceIcon;
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

    @Override
    public void a(BaseFragment baseFragment) {
        if (baseFragment instanceof NotificationsFragment) {
            this.a(baseFragment, g + "_" + baseFragment.hashCode());
            return;
        }
        super.a(baseFragment);
    }

    public void a(NotificationsListView.NotificationsBaseAdapter notificationsBaseAdapter) {
        this.y = notificationsBaseAdapter;
    }

    public void b(NotificationsListView.NotificationsBaseAdapter notificationsBaseAdapter) {
        this.z = notificationsBaseAdapter;
    }

    public Parcelable d(int n) {
        if (n == 0) {
            return this.v;
        }
        return this.w;
    }

    @Override
    public boolean e() {
        return false;
    }

    @Override
    public boolean f() {
        return true;
    }

    @Override
    public boolean l() {
        return this.S();
    }

    @Override
    protected boolean m() {
        return true;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        bundle = SingApplication.g().getSharedPreferences(MasterActivity.class.getName(), 0);
        this.h = bundle.getBoolean("INITIAL_TAB", false);
        this.i = bundle.getBoolean("SUPPRESS_CHAT_TOOLTIP_ON_ACTIVITY_TAB", false);
        this.a(BaseFragment.ActionBarHighlightMode.b);
        this.setHasOptionsMenu(true);
        if (ChatUtils.a()) {
            this.m = SingApplication.k();
            this.q = new ChatListenerAdapter(){

                /*
                 * Enabled aggressive block sorting
                 */
                @Override
                public void d(Chat chat) {
                    int n = NotificationsFragment.this.m.c(Chat.Bucket.a);
                    boolean bl = NotificationsFragment.this.m.c(Chat.Bucket.b) > 0;
                    NotificationsFragment.this.a(n, bl);
                }
            };
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onCreateOptionsMenu(Menu menu2, MenuInflater menuInflater) {
        boolean bl = false;
        super.onCreateOptionsMenu(menu2, menuInflater);
        if (this.m == null || this.r != null || !ChatUtils.a()) {
            return;
        }
        Log.b(g, "onCreateOptionsMenu");
        if (menu2.findItem(2131756850) != null) {
            this.R();
            return;
        }
        menuInflater.inflate(2131820555, menu2);
        this.n = menu2.findItem(2131756850);
        menu2 = this.n.getActionView();
        this.o = (TextView)menu2.findViewById(2131755236);
        this.p = (ImageView)menu2.findViewById(2131755237);
        menu2.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                NotificationsFragment.this.a(MessageCenterFragment.a());
            }
        });
        if (this.x != null) {
            LayoutUtils.b((View)menu2, (WeakListener.OnGlobalLayoutListener)this.x);
        }
        this.x = new WeakListener.OnGlobalLayoutListener((Object)this, new ViewTreeObserver.OnGlobalLayoutListener((View)menu2){
            final /* synthetic */ View a;
            {
                this.a = view;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void onGlobalLayout() {
                LayoutUtils.b((View)this.a, (WeakListener.OnGlobalLayoutListener)NotificationsFragment.this.x);
                NotificationsFragment.this.x = null;
                if (!NotificationsFragment.this.isAdded() || this.a.getHeight() >= NotificationsFragment.this.getResources().getDimensionPixelSize(2131427385) * 9 / 10) {
                    return;
                }
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams)NotificationsFragment.this.p.getLayoutParams();
                layoutParams.setMargins(0, 0, 0, 0);
                layoutParams.gravity = 8388613;
                NotificationsFragment.this.p.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
                layoutParams = (FrameLayout.LayoutParams)NotificationsFragment.this.o.getLayoutParams();
                layoutParams.setMargins(0, 0, 0, 0);
                NotificationsFragment.this.o.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
            }
        });
        LayoutUtils.a((View)menu2, (WeakListener.OnGlobalLayoutListener)this.x);
        this.R();
        int n = this.m != null ? this.m.c(Chat.Bucket.a) : 0;
        boolean bl2 = bl;
        if (this.m != null) {
            bl2 = bl;
            if (this.m.c(Chat.Bucket.b) > 0) {
                bl2 = true;
            }
        }
        this.a(n, bl2);
    }

    public void onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu();
        this.n = null;
        this.o = null;
        this.p = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.k.removeOnPageChangeListener(this.u);
        this.y = null;
        this.z = null;
        this.l = null;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                return super.onOptionsItemSelected(menuItem);
            }
            case 2131756839: 
        }
        SingAnalytics.a(Analytics.d);
        this.a((BaseFragment)SearchFragment.F());
        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = SingApplication.g().getSharedPreferences(MasterActivity.class.getName(), 0).edit();
        editor.putBoolean("INITIAL_TAB", this.h);
        editor.putBoolean("SUPPRESS_CHAT_TOOLTIP_ON_ACTIVITY_TAB", false);
        editor.apply();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onResume() {
        super.onResume();
        this.u();
        int n = this.h || this.r != null ? 0 : 1;
        if (this.j.getTabCount() <= n) return;
        this.j.getTabAt(n).select();
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.Q();
        super.onSaveInstanceState(bundle);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onStart() {
        String string2;
        int n = 2131296704;
        super.onStart();
        if (this.m != null) {
            this.m.a(this.q);
        }
        if (this.t == null) {
            if (this.r != null) {
                n = 2131296703;
            }
            this.c(n);
            return;
        }
        switch (.a[this.t.ordinal()]) {
            default: {
                this.t = null;
                string2 = "";
                break;
            }
            case 1: {
                string2 = this.getString(2131296397);
                break;
            }
            case 2: {
                string2 = this.getString(2131296398);
                break;
            }
            case 3: {
                string2 = this.getString(2131296390);
                break;
            }
            case 4: {
                string2 = this.getString(2131296391);
                break;
            }
            case 5: {
                string2 = this.getString(2131296387);
                break;
            }
            case 6: {
                string2 = this.getString(2131296388);
                break;
            }
            case 7: {
                string2 = this.getString(2131296399);
                break;
            }
            case 8: {
                string2 = this.getString(2131296400);
                break;
            }
            case 9: {
                string2 = this.getString(2131296392);
                break;
            }
            case 10: {
                string2 = this.getString(2131296394);
                break;
            }
            case 11: {
                string2 = this.getString(2131296396);
                break;
            }
            case 12: {
                string2 = this.getString(2131296395);
                break;
            }
            case 13: {
                string2 = this.getString(2131296393);
                break;
            }
            case 14: {
                string2 = this.getString(2131296389);
                break;
            }
            case 15: {
                string2 = this.getString(2131296401);
            }
        }
        String string3 = string2;
        if (TextUtils.isEmpty((CharSequence)string2)) {
            string3 = this.getString(2131296704);
        }
        this.a((CharSequence)string3);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (this.m != null) {
            this.m.b(this.q);
        }
        this.Q();
    }

    @Override
    public void q() {
        super.q();
    }

    /*
     * Enabled aggressive block sorting
     */
    @AfterViews
    protected void t() {
        this.N();
        if (this.r == null) {
            this.j.setSelectedTabIndicatorColor(this.getResources().getColor(2131689579));
            this.j.setSelectedTabIndicatorHeight(this.getResources().getDimensionPixelOffset(2131428171));
            this.l = new SingTabLayoutHelper(this.j, this.k);
            this.l.a(false);
            this.l.a(new TabLayout.OnTabSelectedListener(){

                public void onTabReselected(TabLayout.Tab tab) {
                    NotificationsFragment.this.a(tab);
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public void onTabSelected(TabLayout.Tab tab) {
                    NotificationsFragment.this.a(tab);
                    NotificationsFragment notificationsFragment = NotificationsFragment.this;
                    boolean bl = tab.getPosition() == 0;
                    notificationsFragment.h = bl;
                    NotificationsFragment.this.k.setCurrentItem(tab.getPosition());
                    tab = NotificationsFragment.this.h ? SingAnalytics.NotificationFilterType.a : SingAnalytics.NotificationFilterType.b;
                    SingAnalytics.a((SingAnalytics.NotificationFilterType)tab);
                    NotificationsFragment.this.O();
                    NotificationsFragment.this.G();
                }

                public void onTabUnselected(TabLayout.Tab tab) {
                    NotificationsFragment.this.O();
                    NotificationsFragment.this.G();
                    NotificationsFragment.this.b(tab);
                }
            });
        } else {
            this.j.setVisibility(8);
        }
        if (!this.h) {
            this.P();
            return;
        }
        if (!this.i) {
            this.P();
        }
    }

    @Override
    public String x() {
        return g;
    }

    @Override
    public void z() {
        super.z();
    }

    public static interface NotificationItemInterface {
        public void a(NotificationsFragment var1, BaseFragment var2, com.smule.android.network.models.NotificationListItem var3, SingAnalytics.NotificationScreenType var4, Runnable var5, boolean var6, boolean var7);

        public void z_();
    }

    public static enum Tabs {
        a,
        b;
        

        private Tabs() {
        }
    }

}

