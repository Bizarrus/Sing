package com.smule.singandroid.customviews;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.h6ah4i.android.tablayouthelper.TabLayoutHelper;
import com.smule.singandroid.C1947R;
import java.util.HashMap;
import java.util.Map;

public class SingTabLayoutHelper extends TabLayoutHelper {
    protected Map<Integer, TextView> f21957k;

    public SingTabLayoutHelper(@NonNull TabLayout tabLayout, @NonNull ViewPager viewPager) {
        super(tabLayout, viewPager);
    }

    protected void mo6805a(Tab tab) {
        if (tab.getCustomView() == null) {
            tab.setCustomView(C1947R.layout.tab_layout);
            TextView textView = (TextView) tab.getCustomView().findViewById(C1947R.id.tab_title);
            textView.setText(m11738a().getAdapter().getPageTitle(tab.getPosition()));
            textView.setTextColor(m11738a().getContext().getResources().getColor(tab.isSelected() ? C1947R.color.button_text_inverse : C1947R.color.menu_title_grey));
            if (this.f21957k == null) {
                this.f21957k = new HashMap();
            }
            this.f21957k.put(Integer.valueOf(tab.getPosition()), textView);
        }
    }

    protected void mo6806c(@NonNull TabLayout tabLayout, PagerAdapter pagerAdapter, int i) {
        int i2;
        int selectedTabPosition = tabLayout.getSelectedTabPosition();
        int scrollX = tabLayout.getScrollX();
        tabLayout.removeAllTabs();
        int count = pagerAdapter.getCount();
        for (i2 = 0; i2 < count; i2++) {
            Tab b = m11745b(tabLayout, pagerAdapter, i2);
            tabLayout.addTab(b, false);
            m11754e(b);
        }
        i2 = Math.min(i, count - 1);
        if (i2 >= 0) {
            if (selectedTabPosition == i2) {
                tabLayout.setOnTabSelectedListener(null);
            }
            if (selectedTabPosition == i2) {
                tabLayout.setOnTabSelectedListener(this.d);
            }
        }
        if (this.j) {
            m11739a(scrollX);
        } else if (tabLayout.getTabMode() == 0) {
            tabLayout.scrollTo(scrollX, 0);
        }
    }

    protected int mo6804a(@NonNull TabLayout tabLayout) {
        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        int childCount = linearLayout.getChildCount();
        int measuredWidth = (tabLayout.getMeasuredWidth() - tabLayout.getPaddingLeft()) - tabLayout.getPaddingRight();
        int measuredHeight = (tabLayout.getMeasuredHeight() - tabLayout.getPaddingTop()) - tabLayout.getPaddingBottom();
        if (childCount == 0) {
            return 1;
        }
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        int i = 0;
        int i2 = 0;
        for (measuredHeight = 0; measuredHeight < childCount; measuredHeight++) {
            View childAt = linearLayout.getChildAt(measuredHeight);
            childAt.setMinimumWidth(measuredWidth / childCount);
            childAt.measure(0, makeMeasureSpec);
            int measuredWidth2 = childAt.getMeasuredWidth();
            i2 += measuredWidth2;
            i = Math.max(i, measuredWidth2);
        }
        int i3 = (i2 >= measuredWidth || i >= measuredWidth / childCount) ? 0 : 1;
        return i3;
    }

    public void m23479a(boolean z, Tab tab) {
        TextView textView = (TextView) this.f21957k.get(Integer.valueOf(tab.getPosition()));
        if (textView != null) {
            int color;
            if (z) {
                color = textView.getResources().getColor(C1947R.color.button_text_inverse);
            } else {
                color = textView.getResources().getColor(C1947R.color.menu_title_grey);
            }
            textView.setTextColor(color);
        }
    }
}
