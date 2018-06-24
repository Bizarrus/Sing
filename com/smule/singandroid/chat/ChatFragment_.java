package com.smule.singandroid.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.foound.widget.AmazingListView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.chat.activator.ChatActivator;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ChatFragment_ extends ChatFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f20936L = new OnViewChangedNotifier();
    private View f20937M;

    class C42821 implements OnClickListener {
        final /* synthetic */ ChatFragment_ f20934a;

        C42821(ChatFragment_ chatFragment_) {
            this.f20934a = chatFragment_;
        }

        public void onClick(View view) {
            this.f20934a.m22595M();
        }
    }

    class C42832 implements OnClickListener {
        final /* synthetic */ ChatFragment_ f20935a;

        C42832(ChatFragment_ chatFragment_) {
            this.f20935a = chatFragment_;
        }

        public void onClick(View view) {
            this.f20935a.m22597O();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, ChatFragment> {
        public ChatFragment m22609a() {
            ChatFragment chatFragment_ = new ChatFragment_();
            chatFragment_.setArguments(this.a);
            return chatFragment_;
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f20936L);
        m22611a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f20937M == null) {
            return null;
        }
        return this.f20937M.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f20937M = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f20937M == null) {
            this.f20937M = layoutInflater.inflate(C1947R.layout.chat_fragment, viewGroup, false);
        }
        return this.f20937M;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f20937M = null;
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
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
    }

    private void m22611a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m22614b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f20936L.a(this);
    }

    public static FragmentBuilder_ m22610P() {
        return new FragmentBuilder_();
    }

    public void m22617a(HasViews hasViews) {
        this.f = (RelativeLayout) hasViews.findViewById(C1947R.id.root);
        this.g = hasViews.findViewById(C1947R.id.bookmark_banner);
        this.h = (TextView) hasViews.findViewById(C1947R.id.bookmark_banner_title);
        this.i = hasViews.findViewById(C1947R.id.inputBoxContainer);
        this.j = hasViews.findViewById(C1947R.id.inputBoxTopDividerLine);
        this.k = (EditText) hasViews.findViewById(C1947R.id.inputBox);
        this.l = (AmazingListView) hasViews.findViewById(C1947R.id.history);
        this.m = (RelativeLayout) hasViews.findViewById(C1947R.id.chat_follow_banner);
        this.n = (RelativeLayout) hasViews.findViewById(C1947R.id.follow_banner_follow_layout);
        this.o = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.follow_profile);
        this.p = (TextView) hasViews.findViewById(C1947R.id.follow_follow_text);
        this.q = (ImageView) hasViews.findViewById(C1947R.id.follow_add);
        this.r = (ProgressBar) hasViews.findViewById(C1947R.id.follow_progress);
        this.s = (TextView) hasViews.findViewById(C1947R.id.follow_banner_inbox_layout);
        this.t = (LinearLayout) hasViews.findViewById(C1947R.id.chat_empty);
        this.u = (ImageView) hasViews.findViewById(C1947R.id.send_button);
        this.v = hasViews.findViewById(C1947R.id.loading_view);
        this.w = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.chat_empty_profile);
        View findViewById = hasViews.findViewById(C1947R.id.bookmark_banner_ok_button);
        if (this.u != null) {
            this.u.setOnClickListener(new C42821(this));
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new C42832(this));
        }
        m22608z();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mChatActivator", this.J);
    }

    private void m22614b(Bundle bundle) {
        if (bundle != null) {
            this.J = (ChatActivator) bundle.getParcelable("mChatActivator");
        }
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }

    protected void mo6749E() {
        BackgroundExecutor.a();
        super.mo6749E();
    }
}
