package com.smule.singandroid.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class LeaveChatsFragment_ extends LeaveChatsFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f21081k = new OnViewChangedNotifier();
    private View f21082l;

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, LeaveChatsFragment> {
        public LeaveChatsFragment m22762a() {
            LeaveChatsFragment leaveChatsFragment_ = new LeaveChatsFragment_();
            leaveChatsFragment_.setArguments(this.a);
            return leaveChatsFragment_;
        }

        public FragmentBuilder_ m22763a(String str) {
            this.a.putString("mPendingJoinJID", str);
            return this;
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21081k);
        m22766a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f21082l == null) {
            return null;
        }
        return this.f21082l.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f21082l = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f21082l == null) {
            this.f21082l = layoutInflater.inflate(C1947R.layout.leave_chats_fragment, viewGroup, false);
        }
        return this.f21082l;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f21082l = null;
        this.e = null;
        this.f = null;
    }

    private void m22766a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m22765C();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f21081k.a(this);
    }

    public static FragmentBuilder_ m22764B() {
        return new FragmentBuilder_();
    }

    public void m22769a(HasViews hasViews) {
        this.e = (ChatListView) hasViews.findViewById(C1947R.id.chats_list);
        this.f = hasViews.findViewById(C1947R.id.network_issue);
        m22758a();
    }

    private void m22765C() {
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("mPendingJoinJID")) {
            this.g = arguments.getString("mPendingJoinJID");
        }
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
