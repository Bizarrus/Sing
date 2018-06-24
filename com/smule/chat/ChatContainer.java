/*
 * Decompiled with CFR 0_123.
 */
package com.smule.chat;

import com.smule.chat.Chat;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatListenerAdapter;
import com.smule.chat.ChatMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatContainer {
    private int a;
    private ArrayList<Chat> b;
    private ChatListener c;

    public ChatContainer(int n) {
        this.a = n;
        this.b = new ArrayList(this.a);
        this.c = new ChatListenerAdapter(){

            @Override
            public void a(Chat chat, ChatMessage chatMessage) {
                if (chatMessage == chat.l()) {
                    ChatContainer.this.d(chat);
                }
            }

            @Override
            public void a(Chat chat, ChatMessage chatMessage, boolean bl) {
                if (chatMessage == chat.l()) {
                    ChatContainer.this.d(chat);
                }
            }
        };
    }

    private void d(Chat chat) {
        this.c(chat);
    }

    public void a(Chat chat) {
        chat.a(this.c);
        this.c(chat);
    }

    public boolean a() {
        if (this.b.size() > this.a) {
            return true;
        }
        return false;
    }

    public void b(Chat chat) {
        chat.b(this.c);
        this.b.remove(chat);
    }

    public boolean b() {
        if (this.b.size() >= this.a) {
            return true;
        }
        return false;
    }

    protected void c(Chat chat) {
        this.b.remove(chat);
        int n = Collections.binarySearch(this.b, chat);
        if (n < 0) {
            this.b.add(- n - 1, chat);
            return;
        }
        this.b.add(chat);
    }

    public boolean c() {
        return this.b.isEmpty();
    }

    public Chat d() {
        if (this.b.size() > 0) {
            return this.b.get(this.b.size() - 1);
        }
        return null;
    }

    public List<Chat> e() {
        return Collections.unmodifiableList(this.b);
    }

}

