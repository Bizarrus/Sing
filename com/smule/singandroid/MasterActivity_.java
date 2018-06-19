package com.smule.singandroid;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.customviews.BottomNavView;
import com.smule.singandroid.customviews.MasterToolbar;
import java.util.HashMap;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class MasterActivity_ extends MasterActivity implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f18882v = new OnViewChangedNotifier();

    class C38711 implements OnClickListener {
        final /* synthetic */ MasterActivity_ f18877a;

        C38711(MasterActivity_ masterActivity_) {
            this.f18877a = masterActivity_;
        }

        public void onClick(View view) {
            this.f18877a.u();
        }
    }

    class C38722 implements Runnable {
        final /* synthetic */ MasterActivity_ f18878a;

        C38722(MasterActivity_ masterActivity_) {
            this.f18878a = masterActivity_;
        }

        public void run() {
            super.t();
        }
    }

    class C38733 implements Runnable {
        final /* synthetic */ MasterActivity_ f18879a;

        C38733(MasterActivity_ masterActivity_) {
            this.f18879a = masterActivity_;
        }

        public void run() {
            super.A();
        }
    }

    public static class IntentBuilder_ extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment f18880d;
        private android.support.v4.app.Fragment f18881e;

        public PostActivityStarter m20327a(int i) {
            if (this.f18881e != null) {
                this.f18881e.startActivityForResult(this.c, i);
            } else if (this.f18880d != null) {
                this.f18880d.startActivityForResult(this.c, i, this.a);
            } else if (this.b instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) this.b, this.c, i, this.a);
            } else {
                this.b.startActivity(this.c, this.a);
            }
            return new PostActivityStarter(this.b);
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f18882v);
        m20328a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
        setContentView((int) C1947R.layout.master_activity);
    }

    private void m20328a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m20331b(bundle);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        this.f18882v.a(this);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.f18882v.a(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.f18882v.a(this);
    }

    public void m20335a(HasViews hasViews) {
        this.h = (ViewGroup) hasViews.findViewById(C1947R.id.root);
        this.i = (ProgressBar) hasViews.findViewById(C1947R.id.top_progress_bar);
        this.j = (MasterToolbar) hasViews.findViewById(C1947R.id.top_toolbar);
        this.k = (BottomNavView) hasViews.findViewById(C1947R.id.bottom_menu_layout);
        this.l = hasViews.findViewById(C1947R.id.now_playing_bar_layout);
        this.m = (RelativeLayout) hasViews.findViewById(C1947R.id.now_playing_overlay_view);
        this.o = (RelativeLayout) hasViews.findViewById(C1947R.id.fragment_content_view);
        if (this.m != null) {
            this.m.setOnClickListener(new C38711(this));
        }
        c();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("mSelectedMenuItem", this.n);
        bundle.putSerializable("mSongbookSectionListViewStates", this.p);
        bundle.putString("mLastSongbookSectionId", this.q);
        bundle.putBoolean("mVisitedFeed", this.r);
        bundle.putLong("mLastMessageAlert", this.s);
    }

    private void m20331b(Bundle bundle) {
        if (bundle != null) {
            this.n = (Integer) bundle.getSerializable("mSelectedMenuItem");
            this.p = (HashMap) bundle.getSerializable("mSongbookSectionListViewStates");
            this.q = bundle.getString("mLastSongbookSectionId");
            this.r = bundle.getBoolean("mVisitedFeed");
            this.s = bundle.getLong("mLastMessageAlert");
        }
    }

    public void m20336t() {
        UiThreadExecutor.a("", new C38722(this), 0);
    }

    public void m20333A() {
        UiThreadExecutor.a("", new C38733(this), 0);
    }

    public void m20334a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.a(runTimePermissionsRequest, resultCallback);
    }
}
