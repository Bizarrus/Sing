package com.smule.singandroid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SimpleTypeTabs_ extends SimpleTypeTabs implements HasViews, OnViewChangedListener {
    private boolean f19968e = false;
    private final OnViewChangedNotifier f19969f = new OnViewChangedNotifier();

    public SimpleTypeTabs_(Context context) {
        super(context);
        m21487d();
    }

    public SimpleTypeTabs_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m21487d();
    }

    public void onFinishInflate() {
        if (!this.f19968e) {
            this.f19968e = true;
            inflate(getContext(), C1947R.layout.simple_type_tabs, this);
            this.f19969f.a(this);
        }
        super.onFinishInflate();
    }

    private void m21487d() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f19969f);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m21488a(HasViews hasViews) {
        List arrayList = new ArrayList();
        View findViewById = hasViews.findViewById(C1947R.id.tab_1);
        View findViewById2 = hasViews.findViewById(C1947R.id.tab_2);
        View findViewById3 = hasViews.findViewById(C1947R.id.tab_3);
        View findViewById4 = hasViews.findViewById(C1947R.id.tab_4);
        ArrayList arrayList2 = new ArrayList();
        TextView textView = (TextView) hasViews.findViewById(C1947R.id.tab_1_title);
        TextView textView2 = (TextView) hasViews.findViewById(C1947R.id.tab_2_title);
        TextView textView3 = (TextView) hasViews.findViewById(C1947R.id.tab_3_title);
        TextView textView4 = (TextView) hasViews.findViewById(C1947R.id.tab_4_title);
        ArrayList arrayList3 = new ArrayList();
        TextView textView5 = (TextView) hasViews.findViewById(C1947R.id.tab_1_badge);
        TextView textView6 = (TextView) hasViews.findViewById(C1947R.id.tab_2_badge);
        TextView textView7 = (TextView) hasViews.findViewById(C1947R.id.tab_3_badge);
        TextView textView8 = (TextView) hasViews.findViewById(C1947R.id.tab_4_badge);
        ArrayList arrayList4 = new ArrayList();
        ImageView imageView = (ImageView) hasViews.findViewById(C1947R.id.tab_1_badge_empty);
        ImageView imageView2 = (ImageView) hasViews.findViewById(C1947R.id.tab_2_badge_empty);
        ImageView imageView3 = (ImageView) hasViews.findViewById(C1947R.id.tab_3_badge_empty);
        ImageView imageView4 = (ImageView) hasViews.findViewById(C1947R.id.tab_4_badge_empty);
        if (findViewById != null) {
            arrayList.add(findViewById);
        }
        if (findViewById2 != null) {
            arrayList.add(findViewById2);
        }
        if (findViewById3 != null) {
            arrayList.add(findViewById3);
        }
        if (findViewById4 != null) {
            arrayList.add(findViewById4);
        }
        this.a = arrayList;
        if (textView != null) {
            arrayList2.add(textView);
        }
        if (textView2 != null) {
            arrayList2.add(textView2);
        }
        if (textView3 != null) {
            arrayList2.add(textView3);
        }
        if (textView4 != null) {
            arrayList2.add(textView4);
        }
        this.b = arrayList2;
        if (textView5 != null) {
            arrayList3.add(textView5);
        }
        if (textView6 != null) {
            arrayList3.add(textView6);
        }
        if (textView7 != null) {
            arrayList3.add(textView7);
        }
        if (textView8 != null) {
            arrayList3.add(textView8);
        }
        this.c = arrayList3;
        if (imageView != null) {
            arrayList4.add(imageView);
        }
        if (imageView2 != null) {
            arrayList4.add(imageView2);
        }
        if (imageView3 != null) {
            arrayList4.add(imageView3);
        }
        if (imageView4 != null) {
            arrayList4.add(imageView4);
        }
        this.d = arrayList4;
        m21482a();
        m21486b();
    }
}
