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
import com.smule.singandroid.chat.message_views.ChatMessageGroupInviteBody;
import com.smule.singandroid.chat.message_views.ChatMessageGroupInviteListItem_;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ChatMessageGroupInviteListItem
extends ChatMessageBaseListItem {
    @ViewById
    ChatMessageGroupInviteBody o;

    public ChatMessageGroupInviteListItem(Context context) {
        super(context);
    }

    public static ChatMessageGroupInviteListItem a(Context object, ChatMessageBaseListItem.ChatMessageViewListener chatMessageViewListener) {
        object = ChatMessageGroupInviteListItem_.a((Context)object);
        object.o.a(chatMessageViewListener);
        return object;
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
}

