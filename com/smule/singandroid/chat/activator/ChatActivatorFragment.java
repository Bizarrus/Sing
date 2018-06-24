package com.smule.singandroid.chat.activator;

import android.os.Bundle;
import com.smule.chat.Chat;
import com.smule.chat.ChatStatus;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.ConnectionStatusIndicator;
import com.smule.singandroid.chat.activator.ChatActivator.ChatActivatorInterface;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;

@EFragment
public abstract class ChatActivatorFragment extends BaseFragment implements ChatActivatorInterface {
    @InstanceState
    protected ChatActivator f20721J = new ChatActivator();
    protected Chat f20722K;
    private ConnectionStatusIndicator f20723e;

    public void m22333d(Chat chat) {
        this.f20721J = ChatActivator.m22929a(chat);
        this.f20722K = chat;
    }

    public void m22334e(String str) {
        this.f20721J = ChatActivator.m22930a(str);
    }

    public void m22335f(String str) {
        this.f20721J = ChatActivator.m22931b(str);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f20723e = new ConnectionStatusIndicator(getActivity(), SingApplication.j());
    }

    public void onStart() {
        super.onStart();
        this.f20721J.m22933a(getActivity(), this);
    }

    public void onResume() {
        super.onResume();
        this.f20721J.m22934b();
        this.f20723e.m22693b();
    }

    public void onPause() {
        super.onPause();
        this.f20723e.m22695c();
    }

    public void onStop() {
        super.onStop();
        this.f20721J.m22932a();
    }

    public void s_() {
    }

    public void mo6551a(ChatStatus chatStatus) {
    }

    public void a_(Chat chat) {
        this.f20722K = chat;
    }

    public void mo6553c(Chat chat) {
    }

    public void mo6550a(Chat chat, ChatStatus chatStatus) {
    }

    public void t_() {
    }
}
