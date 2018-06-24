package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import java.util.ArrayList;
import java.util.List;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class BottomNavView_ extends BottomNavView implements HasViews, OnViewChangedListener {
    private boolean f21479o = false;
    private final OnViewChangedNotifier f21480p = new OnViewChangedNotifier();

    class C43821 implements Runnable {
        final /* synthetic */ BottomNavView_ f21477a;

        C43821(BottomNavView_ bottomNavView_) {
            this.f21477a = bottomNavView_;
        }

        public void run() {
            super.mo6780b();
        }
    }

    class C43832 implements Runnable {
        final /* synthetic */ BottomNavView_ f21478a;

        C43832(BottomNavView_ bottomNavView_) {
            this.f21478a = bottomNavView_;
        }

        public void run() {
            super.mo6781c();
        }
    }

    public BottomNavView_(Context context) {
        super(context);
        m23136f();
    }

    public BottomNavView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23136f();
    }

    public BottomNavView_(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23136f();
    }

    public void onFinishInflate() {
        if (!this.f21479o) {
            this.f21479o = true;
            inflate(getContext(), C1947R.layout.bottom_nav_view, this);
            this.f21480p.a(this);
        }
        super.onFinishInflate();
    }

    private void m23136f() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21480p);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23137a(HasViews hasViews) {
        this.b = hasViews.findViewById(C1947R.id.bottom_nav_feed_badge);
        this.c = (TextView) hasViews.findViewById(C1947R.id.new_notification_count);
        this.d = hasViews.findViewById(C1947R.id.new_chat_badge);
        this.e = hasViews.findViewById(C1947R.id.new_activity_badge);
        this.f = hasViews.findViewById(C1947R.id.new_invites_badge);
        this.g = hasViews.findViewById(C1947R.id.new_other_badge);
        this.h = (ImageView) hasViews.findViewById(C1947R.id.new_notification_badge_alone);
        List arrayList = new ArrayList();
        ImageButton imageButton = (ImageButton) hasViews.findViewById(C1947R.id.bottom_nav_feed);
        ImageButton imageButton2 = (ImageButton) hasViews.findViewById(C1947R.id.bottom_nav_featured);
        ImageButton imageButton3 = (ImageButton) hasViews.findViewById(C1947R.id.bottom_nav_sing);
        ImageButton imageButton4 = (ImageButton) hasViews.findViewById(C1947R.id.bottom_nav_notifications);
        ImageButton imageButton5 = (ImageButton) hasViews.findViewById(C1947R.id.bottom_nav_profile);
        if (imageButton != null) {
            arrayList.add(imageButton);
        }
        if (imageButton2 != null) {
            arrayList.add(imageButton2);
        }
        if (imageButton3 != null) {
            arrayList.add(imageButton3);
        }
        if (imageButton4 != null) {
            arrayList.add(imageButton4);
        }
        if (imageButton5 != null) {
            arrayList.add(imageButton5);
        }
        this.a = arrayList;
        m23127a();
    }

    public void mo6780b() {
        UiThreadExecutor.a("", new C43821(this), 0);
    }

    public void mo6781c() {
        UiThreadExecutor.a("", new C43832(this), 0);
    }
}
