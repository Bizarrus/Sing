/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 */
package com.smule.chat;

import android.content.SharedPreferences;
import com.smule.android.utils.SimpleBarrier;
import com.smule.chat.Chat;
import com.smule.chat.ChatConfiguration;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.PeerChat;
import com.smule.chat.PerformanceChatMessage;
import com.smule.chat.TextChatMessage;
import com.smule.chat.XMPPDelegate;

public class FakePeerChat
extends PeerChat {
    private ChatConfiguration i;

    FakePeerChat(XMPPDelegate xMPPDelegate, Chat.Options options, ChatConfiguration chatConfiguration) {
        super(xMPPDelegate, options);
        this.i = chatConfiguration;
        xMPPDelegate = chatConfiguration.b();
        this.a = xMPPDelegate.getBoolean(FakePeerChat.c(this.f()), true);
        this.b = xMPPDelegate.getBoolean(FakePeerChat.d(this.f()), true);
    }

    private void T() {
        this.a(this.i.e());
    }

    private void U() {
        SharedPreferences.Editor editor = this.i.b().edit();
        editor.putBoolean(FakePeerChat.c(this.f()), this.m());
        editor.putBoolean(FakePeerChat.d(this.f()), this.n());
        editor.apply();
    }

    private void a(String string2) {
        this.h(new PerformanceChatMessage(string2));
    }

    private void a(String[] arrstring) {
        int n = arrstring.length;
        for (int i = 0; i < n; ++i) {
            this.h(new TextChatMessage(arrstring[i]));
        }
    }

    static boolean a(SharedPreferences sharedPreferences, long l) {
        return sharedPreferences.getBoolean(FakePeerChat.b(l), false);
    }

    private static String b(long l) {
        return "chat.deleted." + l;
    }

    private static void b(SharedPreferences sharedPreferences, long l) {
        sharedPreferences = sharedPreferences.edit();
        sharedPreferences.putBoolean(FakePeerChat.b(l), true);
        sharedPreferences.apply();
    }

    private static String c(long l) {
        return "chat.read." + l;
    }

    private void c(final SimpleBarrier simpleBarrier) {
        simpleBarrier.d();
        this.d.b(new Runnable(){

            @Override
            public void run() {
                FakePeerChat.this.D();
                FakePeerChat.this.a(FakePeerChat.this.i.d());
                String string2 = FakePeerChat.this.i.f();
                if (string2 != null) {
                    FakePeerChat.this.a(string2);
                }
                simpleBarrier.a();
            }
        });
    }

    private static String d(long l) {
        return "chat.read-unlocked." + l;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void h(ChatMessage chatMessage) {
        chatMessage.a(this.f());
        this.e(chatMessage);
        boolean bl = !this.r() && !this.m();
        this.a(chatMessage, bl);
        this.b(chatMessage, bl);
    }

    @Override
    void A() {
    }

    @Override
    protected void I() {
        this.U();
        super.I();
    }

    @Override
    protected void a(int n, int n2, Runnable runnable) {
        runnable.run();
    }

    @Override
    protected void a(SimpleBarrier simpleBarrier) {
        this.b(simpleBarrier);
        this.c(simpleBarrier);
    }

    @Override
    public void a(ChatMessage chatMessage) {
        super.a(chatMessage);
        this.d.a(500, new Runnable(){

            @Override
            public void run() {
                FakePeerChat.this.T();
            }
        });
    }

    @Override
    public Chat.Bucket b() {
        return Chat.Bucket.a;
    }

    @Override
    void b(Completion<ChatStatus> completion) {
        FakePeerChat.b(this.i.b(), this.f());
        super.b(completion);
    }

    @Override
    public void c(Completion<ChatStatus> completion) {
        super.c(completion);
        this.U();
    }

    @Override
    protected void f(ChatMessage chatMessage) {
        chatMessage.h();
        this.g(chatMessage);
    }

    @Override
    public boolean t() {
        return false;
    }

    @Override
    public boolean v() {
        return true;
    }

    @Override
    public void w() {
    }

    @Override
    void x() {
        super.x();
        this.U();
    }

}

