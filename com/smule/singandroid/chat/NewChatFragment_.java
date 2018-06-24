package com.smule.singandroid.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.chat.activator.ChatActivator;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class NewChatFragment_ extends NewChatFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f21164e = new OnViewChangedNotifier();
    private View f21165p;

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, NewChatFragment> {
        public NewChatFragment m22848a() {
            NewChatFragment newChatFragment_ = new NewChatFragment_();
            newChatFragment_.setArguments(this.a);
            return newChatFragment_;
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21164e);
        m22850a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f21165p == null) {
            return null;
        }
        return this.f21165p.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f21165p = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f21165p == null) {
            this.f21165p = layoutInflater.inflate(C1947R.layout.new_chat_fragment, viewGroup, false);
        }
        return this.f21165p;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f21165p = null;
        this.g = null;
        this.h = null;
    }

    private void m22850a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m22852b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f21164e.a(this);
    }

    public static FragmentBuilder_ m22849F() {
        return new FragmentBuilder_();
    }

    public void m22854a(HasViews hasViews) {
        this.g = (SelectUsersAndChatsView) hasViews.findViewById(C1947R.id.selected_users_and_chats_view);
        this.h = (LinearLayout) hasViews.findViewById(C1947R.id.root);
        m22341D();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mChatActivator", this.J);
    }

    private void m22852b(Bundle bundle) {
        if (bundle != null) {
            this.J = (ChatActivator) bundle.getParcelable("mChatActivator");
        }
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
