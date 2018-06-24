/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.annotation.NonNull
 *  android.support.design.widget.TabLayout
 *  android.support.design.widget.TabLayout$OnTabSelectedListener
 *  android.support.design.widget.TabLayout$Tab
 *  android.support.v4.content.ContextCompat
 *  android.support.v4.view.PagerAdapter
 *  android.support.v4.view.ViewPager
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  com.h6ah4i.android.tablayouthelper.TabLayoutHelper
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.h6ah4i.android.tablayouthelper.TabLayoutHelper;
import java.util.HashMap;
import java.util.Map;

public class SingTabLayoutHelper
extends TabLayoutHelper {
    protected Map<Integer, TextView> k;
    private int l;
    private int m;

    public SingTabLayoutHelper(@NonNull TabLayout tabLayout, @NonNull ViewPager viewPager) {
        super(tabLayout, viewPager);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected int a(@NonNull TabLayout tabLayout) {
        LinearLayout linearLayout = (LinearLayout)tabLayout.getChildAt(0);
        int n = linearLayout.getChildCount();
        int n2 = tabLayout.getMeasuredWidth() - tabLayout.getPaddingLeft() - tabLayout.getPaddingRight();
        int n3 = tabLayout.getMeasuredHeight();
        int n4 = tabLayout.getPaddingTop();
        int n5 = tabLayout.getPaddingBottom();
        if (n == 0) {
            return 1;
        }
        int n6 = View.MeasureSpec.makeMeasureSpec((int)(n3 - n4 - n5), (int)1073741824);
        n4 = 0;
        n5 = 0;
        for (n3 = 0; n3 < n; ++n3) {
            tabLayout = linearLayout.getChildAt(n3);
            tabLayout.setMinimumWidth(n2 / n);
            tabLayout.measure(0, n6);
            int n7 = tabLayout.getMeasuredWidth();
            n5 += n7;
            n4 = Math.max(n4, n7);
        }
        if (n5 >= n2) return 0;
        if (n4 >= n2 / n) return 0;
        return 1;
    }

    public void a(int n, int n2) {
        this.l = n;
        this.m = n2;
        n2 = this.a.getTabCount();
        for (n = 0; n < n2; ++n) {
            TabLayout.Tab tab = this.a.getTabAt(n);
            this.a(tab.isSelected(), tab);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(TabLayout.Tab tab) {
        if (tab.getCustomView() == null) {
            tab.setCustomView(2130903446);
            TextView textView = (TextView)tab.getCustomView().findViewById(2131756754);
            textView.setText(this.a().getAdapter().getPageTitle(tab.getPosition()));
            if (this.l == 0) {
                this.l = ContextCompat.getColor((Context)this.a().getContext(), (int)2131689579);
            }
            if (this.m == 0) {
                this.m = ContextCompat.getColor((Context)this.a().getContext(), (int)2131689807);
            }
            int n = tab.isSelected() ? this.l : this.m;
            textView.setTextColor(n);
            if (this.k == null) {
                this.k = new HashMap<Integer, TextView>();
            }
            this.k.put(tab.getPosition(), textView);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(boolean bl, TabLayout.Tab tab) {
        if ((tab = this.k.get(tab.getPosition())) != null) {
            int n = bl ? this.l : this.m;
            tab.setTextColor(n);
        }
    }

    protected void b(TabLayout.Tab tab) {
        this.b.setCurrentItem(tab.getPosition(), false);
        this.e();
        if (this.c != null) {
            this.c.onTabSelected(tab);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void c(@NonNull TabLayout tabLayout, PagerAdapter pagerAdapter, int n) {
        int n2 = tabLayout.getSelectedTabPosition();
        int n3 = tabLayout.getScrollX();
        tabLayout.removeAllTabs();
        int n4 = pagerAdapter.getCount();
        for (int i = 0; i < n4; ++i) {
            TabLayout.Tab tab = this.b(tabLayout, pagerAdapter, i);
            tabLayout.addTab(tab, false);
            this.e(tab);
        }
        if ((n = Math.min(n, n4 - 1)) >= 0) {
            if (n2 == n) {
                tabLayout.setOnTabSelectedListener(null);
            }
            if (n2 == n) {
                tabLayout.setOnTabSelectedListener(this.d);
            }
        }
        if (this.j) {
            this.a(n3);
            return;
        } else {
            if (tabLayout.getTabMode() != 0) return;
            {
                tabLayout.scrollTo(n3, 0);
                return;
            }
        }
    }
}

