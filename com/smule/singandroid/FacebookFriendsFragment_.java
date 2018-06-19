package com.smule.singandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FacebookFriendsFragment_ extends FacebookFriendsFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f18635i = new OnViewChangedNotifier();
    private View f18636j;

    class C38141 implements OnClickListener {
        final /* synthetic */ FacebookFriendsFragment_ f18630a;

        C38141(FacebookFriendsFragment_ facebookFriendsFragment_) {
            this.f18630a = facebookFriendsFragment_;
        }

        public void onClick(View view) {
            this.f18630a.m20034E();
        }
    }

    class C38152 implements Runnable {
        final /* synthetic */ FacebookFriendsFragment_ f18631a;

        C38152(FacebookFriendsFragment_ facebookFriendsFragment_) {
            this.f18631a = facebookFriendsFragment_;
        }

        public void run() {
            super.mo6421C();
        }
    }

    class C38163 implements Runnable {
        final /* synthetic */ FacebookFriendsFragment_ f18632a;

        C38163(FacebookFriendsFragment_ facebookFriendsFragment_) {
            this.f18632a = facebookFriendsFragment_;
        }

        public void run() {
            super.mo6422D();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, FacebookFriendsFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f18635i);
        m20039a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f18636j == null) {
            return null;
        }
        return this.f18636j.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f18636j = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f18636j == null) {
            this.f18636j = layoutInflater.inflate(C1947R.layout.facebook_friends_fragment, viewGroup, false);
        }
        return this.f18636j;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f18636j = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
    }

    private void m20039a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f18635i.a(this);
    }

    public void m20047a(HasViews hasViews) {
        this.e = hasViews.findViewById(C1947R.id.no_friends_view);
        this.f = hasViews.findViewById(C1947R.id.loading_view);
        this.g = (MagicListView) hasViews.findViewById(C1947R.id.friends_list_view);
        this.h = (TextView) hasViews.findViewById(C1947R.id.error_finding_friends_text_view);
        View findViewById = hasViews.findViewById(C1947R.id.find_recommended_singers_button);
        if (findViewById != null) {
            findViewById.setOnClickListener(new C38141(this));
        }
        m20030A();
    }

    protected void mo6421C() {
        UiThreadExecutor.a("", new C38152(this), 0);
    }

    protected void mo6422D() {
        UiThreadExecutor.a("", new C38163(this), 0);
    }

    public void mo6423b(final boolean z) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ FacebookFriendsFragment_ f18634b;

            public void run() {
                super.mo6423b(z);
            }
        }, 0);
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
