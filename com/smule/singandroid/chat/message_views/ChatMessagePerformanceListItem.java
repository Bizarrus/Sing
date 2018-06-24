/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.chat.message_views;

import android.content.Context;
import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;
import com.smule.singandroid.chat.message_views.ChatMessageBaseListItem;
import com.smule.singandroid.chat.message_views.ChatMessagePerformanceBody;
import com.smule.singandroid.chat.message_views.ChatMessagePerformanceListItem_;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ChatMessagePerformanceListItem
extends ChatMessageBaseListItem {
    @ViewById
    ChatMessagePerformanceBody o;

    public ChatMessagePerformanceListItem(Context context) {
        super(context);
    }

    public static ChatMessagePerformanceListItem a(Context context) {
        return ChatMessagePerformanceListItem_.b(context);
    }

    @Override
    public void a(Chat chat, ChatMessage chatMessage, int n) {
        super.a(chat, chatMessage, n);
        this.o.a(chatMessage, this.k, chat);
    }

    @Override
    public void b(Chat chat, ChatMessage chatMessage, int n) {
        super.b(chat, chatMessage, n);
        this.o.a(chatMessage, this.k, chat);
    }

    public ChatMessagePerformanceBody getBody() {
        return this.o;
    }

    @Override
    public String getMediaKey() {
        return this.o.getMediaKey();
    }

    @Override
    public void r_() {
        this.o.r_();
    }
}

