/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.support.design.widget.TabLayout
 *  android.support.design.widget.TabLayout$OnTabSelectedListener
 *  android.support.design.widget.TabLayout$Tab
 *  android.support.v4.view.PagerAdapter
 *  android.support.v4.view.ViewPager
 *  android.support.v4.view.ViewPager$OnPageChangeListener
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.MenuItem$OnMenuItemClickListener
 *  android.view.View
 *  android.view.ViewGroup
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.CustomTabLayout;
import com.smule.singandroid.CustomViewPager;
import com.smule.singandroid.FindFriendsContactsPageView;
import com.smule.singandroid.FindFriendsExternalPageView;
import com.smule.singandroid.FindFriendsFacebookPageView;
import com.smule.singandroid.FindFriendsFragment_;
import com.smule.singandroid.FindFriendsPageView;
import com.smule.singandroid.FindFriendsRecommendedPageView;
import com.smule.singandroid.FindFriendsSearchFragment;
import com.smule.singandroid.customviews.SingTabLayoutHelper;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.HashMap;
import java.util.Map;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class FindFriendsFragment
extends BaseFragment {
    public static final String g = FindFriendsFragment.class.getName();
    protected FindFriendsTab h = FindFriendsTab.a;
    @ViewById
    protected CustomTabLayout i;
    @ViewById
    protected CustomViewPager j;
    protected SingTabLayoutHelper k;
    protected boolean l = false;
    protected FindFriendsRecommendedPageView m;
    protected FindFriendsFacebookPageView n;
    protected FindFriendsContactsPageView o;
    private FindFriendsPagerAdapter p;
    private EntryPoint q;
    private MenuItem r;

    private void H() {
        this.r.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){

            public boolean onMenuItemClick(MenuItem menuItem) {
                SingAnalytics.a(Analytics.f);
                FindFriendsFragment.this.a(FindFriendsSearchFragment.c(FindFriendsFragment.this.F()));
                return true;
            }
        });
    }

    private void I() {
        if (this.r != null) {
            this.r.setOnMenuItemClickListener(null);
        }
    }

    private boolean J() {
        if (!MagicFacebook.a().c() && this.p() == null) {
            return true;
        }
        return false;
    }

    private void K() {
        this.p = new FindFriendsPagerAdapter();
        this.j.setOffscreenPageLimit(3);
        this.j.setAdapter((PagerAdapter)this.p);
        this.j.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            public void onPageScrollStateChanged(int n) {
            }

            public void onPageScrolled(int n, float f, int n2) {
            }

            public void onPageSelected(int n) {
            }
        });
        this.L();
    }

    private void L() {
        this.k = new SingTabLayoutHelper(this.i, this.j);
        this.k.a(this.getResources().getColor(2131689579), this.getResources().getColor(2131689807));
        this.k.a(true);
        this.k.a(new TabLayout.OnTabSelectedListener(){

            public void onTabReselected(TabLayout.Tab tab) {
                FindFriendsFragment.this.a(tab);
            }

            public void onTabSelected(TabLayout.Tab tab) {
                FindFriendsFragment.this.h = FindFriendsTab.a(tab.getPosition());
                FindFriendsFragment.this.a(tab);
            }

            public void onTabUnselected(TabLayout.Tab tab) {
                FindFriendsFragment.this.b(tab);
            }
        });
    }

    public static FindFriendsFragment a() {
        return FindFriendsFragment.a(EntryPoint.e, FindFriendsTab.a);
    }

    public static FindFriendsFragment a(EntryPoint entryPoint) {
        return FindFriendsFragment.a(entryPoint, FindFriendsTab.a);
    }

    public static FindFriendsFragment a(EntryPoint entryPoint, FindFriendsTab findFriendsTab) {
        FindFriendsFragment findFriendsFragment = FindFriendsFragment_.H().a();
        findFriendsFragment.q = entryPoint;
        findFriendsFragment.h = findFriendsTab;
        return findFriendsFragment;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(TabLayout.Tab object) {
        this.k.a(true, (TabLayout.Tab)object);
        FindFriendsTab findFriendsTab = FindFriendsTab.a(object.getPosition());
        object = findFriendsTab;
        if (findFriendsTab == null) {
            object = FindFriendsTab.a;
        }
        switch (.a[object.ordinal()]) {
            case 2: {
                if (this.n != null) {
                    this.n.c();
                    return;
                }
                this.l = true;
                return;
            }
            case 3: {
                if (this.o != null) {
                    this.o.c();
                    break;
                }
                this.l = true;
            }
        }
        if (this.m != null) {
            this.m.c();
            return;
        }
        this.l = true;
    }

    private void b(TabLayout.Tab tab) {
        this.k.a(false, tab);
    }

    protected boolean F() {
        if (this.q == EntryPoint.c) {
            return true;
        }
        return false;
    }

    protected boolean G() {
        if (this.q == EntryPoint.b || this.q == EntryPoint.c) {
            return true;
        }
        return false;
    }

    protected void a(FindFriendsPageView findFriendsPageView) {
        if (this.l) {
            findFriendsPageView.c();
            this.l = false;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a(BaseFragment.ActionBarHighlightMode.b);
        boolean bl = !this.G();
        this.setHasOptionsMenu(bl);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu2, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu2, menuInflater);
        Log.b(g, "onCreateOptionsMenu - begin");
        if (menu2.findItem(2131756839) == null) {
            menuInflater.inflate(2131820549, menu2);
        }
        this.r = menu2.findItem(2131756839);
        int n = this.getResources().getColor(2131689486);
        this.r.getIcon().setColorFilter(n, PorterDuff.Mode.SRC_ATOP);
        this.a(new Runnable(){

            @Override
            public void run() {
                FindFriendsFragment.this.H();
            }
        }, 300);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.k = null;
        this.p = null;
        this.i = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.getActivity() != null) {
            this.getActivity().invalidateOptionsMenu();
            this.i.getTabAt(this.h.a()).select();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        this.u();
        this.c(2131296687);
    }

    @Override
    public void onStop() {
        super.onStop();
        this.I();
    }

    @AfterViews
    protected void t() {
        this.K();
        this.j.setPagingEnabled(true);
        this.i.setSelectedTabIndicatorColor(this.getResources().getColor(2131689579));
        this.i.setTabTextColors(this.getResources().getColor(2131689579), this.getResources().getColor(2131690053));
    }

    @Override
    public String x() {
        return null;
    }

    public static enum EntryPoint {
        a,
        b,
        c,
        d,
        e;
        

        private EntryPoint() {
        }
    }

    private class FindFriendsPagerAdapter
    extends PagerAdapter {
        protected Map<Integer, FindFriendsTab> a;

        public FindFriendsPagerAdapter() {
            this.a = new HashMap<Integer, FindFriendsTab>();
            if (FindFriendsFragment.this.J()) {
                this.a.put(0, FindFriendsTab.a);
                this.a.put(1, FindFriendsTab.c);
                return;
            }
            this.a.put(0, FindFriendsTab.a);
            this.a.put(1, FindFriendsTab.b);
            this.a.put(2, FindFriendsTab.c);
        }

        public void destroyItem(ViewGroup viewGroup, int n, Object object) {
        }

        public int getCount() {
            return this.a.size();
        }

        public CharSequence getPageTitle(int n) {
            FindFriendsTab findFriendsTab = this.a.get(n);
            if (findFriendsTab == null) {
                return "";
            }
            switch (.a[findFriendsTab.ordinal()]) {
                default: {
                    return "";
                }
                case 1: {
                    return FindFriendsFragment.this.getResources().getString(2131296845);
                }
                case 2: {
                    return FindFriendsFragment.this.getResources().getString(2131296844);
                }
                case 3: 
            }
            return FindFriendsFragment.this.getResources().getString(2131296843);
        }

        /*
         * Enabled aggressive block sorting
         */
        public Object instantiateItem(ViewGroup viewGroup, int n) {
            Object object;
            block6 : {
                boolean bl;
                FindFriendsTab findFriendsTab = this.a.get(n);
                object = findFriendsTab;
                if (findFriendsTab == null) {
                    object = FindFriendsTab.a;
                }
                switch (.a[object.ordinal()]) {
                    default: {
                        FindFriendsFragment.this.m = FindFriendsRecommendedPageView.a((Context)FindFriendsFragment.this.getActivity(), FindFriendsFragment.this);
                        FindFriendsFragment.this.m.a();
                        if (FindFriendsFragment.this.G()) {
                            FindFriendsFragment.this.m.b();
                        }
                        object = FindFriendsFragment.this.m;
                        bl = !FindFriendsFragment.this.F();
                    }
                    case 2: {
                        FindFriendsFragment.this.n = FindFriendsFacebookPageView.a((Context)FindFriendsFragment.this.getActivity(), FindFriendsFragment.this, FindFriendsExternalPageView.Mode.a);
                        FindFriendsFragment.this.n.a();
                        object = FindFriendsFragment.this.n;
                        FindFriendsFragment.this.a(FindFriendsFragment.this.n);
                        break block6;
                    }
                    case 3: {
                        FindFriendsFragment.this.o = FindFriendsContactsPageView.a((Context)FindFriendsFragment.this.getActivity(), FindFriendsFragment.this, FindFriendsExternalPageView.Mode.a);
                        FindFriendsFragment.this.o.a();
                        object = FindFriendsFragment.this.o;
                        FindFriendsFragment.this.a(FindFriendsFragment.this.o);
                        break block6;
                    }
                }
                object.a(bl);
                object = FindFriendsFragment.this.m;
                FindFriendsFragment.this.a(FindFriendsFragment.this.m);
            }
            viewGroup.addView((View)object);
            return object;
        }

        public boolean isViewFromObject(View view, Object object) {
            if (view == object) {
                return true;
            }
            return false;
        }

        public void setPrimaryItem(ViewGroup viewGroup, int n, Object object) {
        }
    }

    public static enum FindFriendsTab {
        a(0),
        b(1),
        c(2);
        
        private final int d;

        private FindFriendsTab(int n2) {
            this.d = n2;
        }

        static FindFriendsTab a(int n) {
            for (FindFriendsTab findFriendsTab : FindFriendsTab.values()) {
                if (findFriendsTab.d != n) continue;
                return findFriendsTab;
            }
            return null;
        }

        public int a() {
            return this.d;
        }
    }

}

