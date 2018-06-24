/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.os.Bundle
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.InstanceState
 */
package com.smule.singandroid.chat.activator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.smule.chat.Chat;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatStatus;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.ConnectionStatusIndicator;
import com.smule.singandroid.chat.activator.ChatActivator;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;

@EFragment
public abstract class ChatActivatorFragment
extends BaseFragment
implements ChatActivator.ChatActivatorInterface {
    @InstanceState
    protected ChatActivator L = new ChatActivator();
    protected Chat M;
    private ConnectionStatusIndicator g;

    @Override
    public void a(Chat chat, ChatStatus chatStatus) {
    }

    @Override
    public void a(ChatStatus chatStatus) {
    }

    @Override
    public void b(Chat chat) {
        this.M = chat;
    }

    @Override
    public void c(Chat chat) {
    }

    public void e(Chat chat) {
        this.L = ChatActivator.a(chat);
        this.M = chat;
    }

    public void e(String string2) {
        this.L = ChatActivator.a(string2);
    }

    public void f(String string2) {
        this.L = ChatActivator.b(string2);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.g = new ConnectionStatusIndicator((Context)this.getActivity(), SingApplication.k());
    }

    @Override
    public void onPause() {
        super.onPause();
        this.g.c();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.L.b();
        this.g.b();
    }

    @Override
    public void onStart() {
        super.onStart();
        this.L.a((Context)this.getActivity(), this);
    }

    @Override
    public void onStop() {
        super.onStop();
        this.L.a();
    }

    @Override
    public void w_() {
    }

    @Override
    public void y_() {
    }
}

