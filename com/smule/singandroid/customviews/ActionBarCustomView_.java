package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ActionBarCustomView_ extends ActionBarCustomView implements HasViews, OnViewChangedListener {
    private boolean f21444o = false;
    private final OnViewChangedNotifier f21445p = new OnViewChangedNotifier();

    class C43721 implements OnClickListener {
        final /* synthetic */ ActionBarCustomView_ f21442a;

        C43721(ActionBarCustomView_ actionBarCustomView_) {
            this.f21442a = actionBarCustomView_;
        }

        public void onClick(View view) {
            this.f21442a.m23110b();
        }
    }

    class C43732 implements OnClickListener {
        final /* synthetic */ ActionBarCustomView_ f21443a;

        C43732(ActionBarCustomView_ actionBarCustomView_) {
            this.f21443a = actionBarCustomView_;
        }

        public void onClick(View view) {
            this.f21443a.m23110b();
        }
    }

    public ActionBarCustomView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23112c();
    }

    public ActionBarCustomView_(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23112c();
    }

    public static ActionBarCustomView m23111a(Context context, AttributeSet attributeSet) {
        ActionBarCustomView actionBarCustomView_ = new ActionBarCustomView_(context, attributeSet);
        actionBarCustomView_.onFinishInflate();
        return actionBarCustomView_;
    }

    public void onFinishInflate() {
        if (!this.f21444o) {
            this.f21444o = true;
            inflate(getContext(), C1947R.layout.action_bar_custom_view, this);
            this.f21445p.a(this);
        }
        super.onFinishInflate();
    }

    private void m23112c() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21445p);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23113a(HasViews hasViews) {
        this.g = hasViews.findViewById(C1947R.id.root_view);
        this.h = (ImageView) hasViews.findViewById(C1947R.id.left_button);
        this.i = (TextView) hasViews.findViewById(C1947R.id.title_text_view);
        this.j = (TextView) hasViews.findViewById(C1947R.id.sub_title_text_view);
        this.k = (TextView) hasViews.findViewById(C1947R.id.title_centered_text_view);
        this.l = (ImageView) hasViews.findViewById(C1947R.id.pre_search_left_button);
        this.m = (TextView) hasViews.findViewById(C1947R.id.pre_search_title_text_view);
        this.b = hasViews.findViewById(C1947R.id.done_button);
        this.c = hasViews.findViewById(C1947R.id.start_new_chat);
        this.d = (ViewGroup) hasViews.findViewById(C1947R.id.custom_views);
        this.e = hasViews.findViewById(C1947R.id.custom_menu_padding);
        if (this.i != null) {
            this.i.setOnClickListener(new C43721(this));
        }
        if (this.h != null) {
            this.h.setOnClickListener(new C43732(this));
        }
    }
}
