package com.smule.singandroid.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.chat.activator.ChatActivator;
import com.smule.singandroid.customviews.ExpandableHeightViewPager;
import com.viewpagerindicator.CirclePageIndicator;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ChatDetailsFragment_ extends ChatDetailsFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f20849q = new OnViewChangedNotifier();
    private View f20850r;

    class C42621 implements OnClickListener {
        final /* synthetic */ ChatDetailsFragment_ f20847a;

        C42621(ChatDetailsFragment_ chatDetailsFragment_) {
            this.f20847a = chatDetailsFragment_;
        }

        public void onClick(View view) {
            this.f20847a.m22499A();
        }
    }

    class C42632 implements OnClickListener {
        final /* synthetic */ ChatDetailsFragment_ f20848a;

        C42632(ChatDetailsFragment_ chatDetailsFragment_) {
            this.f20848a = chatDetailsFragment_;
        }

        public void onClick(View view) {
            this.f20848a.m22500B();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, ChatDetailsFragment> {
        public ChatDetailsFragment m22507a() {
            ChatDetailsFragment chatDetailsFragment_ = new ChatDetailsFragment_();
            chatDetailsFragment_.setArguments(this.a);
            return chatDetailsFragment_;
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f20849q);
        m22509a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f20850r == null) {
            return null;
        }
        return this.f20850r.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f20850r = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f20850r == null) {
            this.f20850r = layoutInflater.inflate(C1947R.layout.chat_details_fragment, viewGroup, false);
        }
        return this.f20850r;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f20850r = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
    }

    private void m22509a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m22511b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f20849q.a(this);
    }

    public static FragmentBuilder_ m22508E() {
        return new FragmentBuilder_();
    }

    public void m22513a(HasViews hasViews) {
        this.f = (TextView) hasViews.findViewById(C1947R.id.allow_notification_title);
        this.g = (ToggleButton) hasViews.findViewById(C1947R.id.allow_notification_toggle);
        this.h = (FrameLayout) hasViews.findViewById(C1947R.id.allow_notification_following);
        this.i = (LinearLayout) hasViews.findViewById(C1947R.id.allow_notification_not_following);
        this.j = (LinearLayout) hasViews.findViewById(C1947R.id.block_user_layout);
        this.k = (TextView) hasViews.findViewById(C1947R.id.delete_chat_title);
        this.l = (RelativeLayout) hasViews.findViewById(C1947R.id.group_name_layout);
        this.m = (TextView) hasViews.findViewById(C1947R.id.group_name_text);
        this.n = (ExpandableHeightViewPager) hasViews.findViewById(C1947R.id.members_pager);
        this.o = (CirclePageIndicator) hasViews.findViewById(C1947R.id.indicator);
        this.p = hasViews.findViewById(C1947R.id.loading_view);
        View findViewById = hasViews.findViewById(C1947R.id.delete_chat_layout);
        if (this.j != null) {
            this.j.setOnClickListener(new C42621(this));
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new C42632(this));
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mChatActivator", this.J);
    }

    private void m22511b(Bundle bundle) {
        if (bundle != null) {
            this.J = (ChatActivator) bundle.getParcelable("mChatActivator");
        }
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
