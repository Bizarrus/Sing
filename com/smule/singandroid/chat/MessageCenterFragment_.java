package com.smule.singandroid.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.CustomViewPager;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class MessageCenterFragment_ extends MessageCenterFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f21160t = new OnViewChangedNotifier();
    private View f21161u;

    class C43241 implements OnClickListener {
        final /* synthetic */ MessageCenterFragment_ f21158a;

        C43241(MessageCenterFragment_ messageCenterFragment_) {
            this.f21158a = messageCenterFragment_;
        }

        public void onClick(View view) {
            this.f21158a.m22827B();
        }
    }

    class C43252 implements OnClickListener {
        final /* synthetic */ MessageCenterFragment_ f21159a;

        C43252(MessageCenterFragment_ messageCenterFragment_) {
            this.f21159a = messageCenterFragment_;
        }

        public void onClick(View view) {
            this.f21159a.m22828C();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, MessageCenterFragment> {
        public MessageCenterFragment m22841a() {
            MessageCenterFragment messageCenterFragment_ = new MessageCenterFragment_();
            messageCenterFragment_.setArguments(this.a);
            return messageCenterFragment_;
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21160t);
        m22843a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f21161u == null) {
            return null;
        }
        return this.f21161u.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f21161u = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f21161u == null) {
            this.f21161u = layoutInflater.inflate(C1947R.layout.message_center_fragment, viewGroup, false);
        }
        return this.f21161u;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f21161u = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
    }

    private void m22843a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f21160t.a(this);
    }

    public static FragmentBuilder_ m22842E() {
        return new FragmentBuilder_();
    }

    public void m22846a(HasViews hasViews) {
        this.f = hasViews.findViewById(C1947R.id.loading_view);
        this.g = hasViews.findViewById(C1947R.id.no_chats);
        this.h = (ImageView) hasViews.findViewById(C1947R.id.no_chats_image);
        this.i = (TextView) hasViews.findViewById(C1947R.id.no_chats_message);
        this.j = (Button) hasViews.findViewById(C1947R.id.start_new_chat);
        this.k = (CustomViewPager) hasViews.findViewById(C1947R.id.message_center_viewpager);
        this.l = (TabLayout) hasViews.findViewById(C1947R.id.message_center_tab_layout);
        View findViewById = hasViews.findViewById(C1947R.id.tooltip_header_cancel);
        if (this.j != null) {
            this.j.setOnClickListener(new C43241(this));
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new C43252(this));
        }
        m22840z();
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
