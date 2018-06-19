package com.smule.singandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.network.models.NotificationListItem$DetailedType;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import java.util.ArrayList;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class NotificationsFragment_ extends NotificationsFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f18976v = new OnViewChangedNotifier();
    private View f18977w;

    class C38901 implements Runnable {
        final /* synthetic */ NotificationsFragment_ f18975a;

        C38901(NotificationsFragment_ notificationsFragment_) {
            this.f18975a = notificationsFragment_;
        }

        public void run() {
            super.mo6489B();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, NotificationsFragment> {
        public NotificationsFragment m20447a() {
            NotificationsFragment notificationsFragment_ = new NotificationsFragment_();
            notificationsFragment_.setArguments(this.a);
            return notificationsFragment_;
        }

        public FragmentBuilder_ m20449a(ArrayList<String> arrayList) {
            this.a.putStringArrayList("mNotificationKeys", arrayList);
            return this;
        }

        public FragmentBuilder_ m20450a(boolean z) {
            this.a.putBoolean("mSuppressPerformances", z);
            return this;
        }

        public FragmentBuilder_ m20448a(NotificationListItem$DetailedType notificationListItem$DetailedType) {
            this.a.putSerializable("mDetailedType", notificationListItem$DetailedType);
            return this;
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f18976v);
        m20453a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f18977w == null) {
            return null;
        }
        return this.f18977w.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f18977w = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f18977w == null) {
            this.f18977w = layoutInflater.inflate(C1947R.layout.notifications_fragment, viewGroup, false);
        }
        return this.f18977w;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f18977w = null;
        this.h = null;
        this.i = null;
    }

    private void m20453a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m20452J();
        m20456b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f18976v.a(this);
    }

    public static FragmentBuilder_ m20451I() {
        return new FragmentBuilder_();
    }

    public void m20459a(HasViews hasViews) {
        this.h = (TabLayout) hasViews.findViewById(C1947R.id.mNotificationTypeTabs);
        this.i = (CustomViewPager) hasViews.findViewById(C1947R.id.mNotificationsViewPager);
        m20446z();
    }

    private void m20452J() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.containsKey("mNotificationKeys")) {
                this.p = arguments.getStringArrayList("mNotificationKeys");
            }
            if (arguments.containsKey("mSuppressPerformances")) {
                this.q = arguments.getBoolean("mSuppressPerformances");
            }
            if (arguments.containsKey("mDetailedType")) {
                this.r = (NotificationListItem$DetailedType) arguments.getSerializable("mDetailedType");
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("mIsShowingActivity", this.f);
        bundle.putBoolean("mSuppressChatTooltipOnActivityTab", this.g);
        bundle.putParcelable("mListViewStateActivity", this.t);
        bundle.putParcelable("mListViewStateInvite", this.u);
    }

    private void m20456b(Bundle bundle) {
        if (bundle != null) {
            this.f = bundle.getBoolean("mIsShowingActivity");
            this.g = bundle.getBoolean("mSuppressChatTooltipOnActivityTab");
            this.t = bundle.getParcelable("mListViewStateActivity");
            this.u = bundle.getParcelable("mListViewStateInvite");
        }
    }

    public void mo6489B() {
        UiThreadExecutor.a("", new C38901(this), 0);
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
