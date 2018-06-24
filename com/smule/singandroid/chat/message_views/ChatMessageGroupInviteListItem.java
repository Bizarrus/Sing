package com.smule.singandroid.chat.message_views;

import android.content.Context;
import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;
import com.smule.singandroid.chat.message_views.ChatMessageBaseListItem.ChatMessageViewListener;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ChatMessageGroupInviteListItem extends ChatMessageBaseListItem {
    @ViewById
    ChatMessageGroupInviteBody f21355o;

    public ChatMessageGroupInviteListItem(Context context) {
        super(context);
    }

    public static ChatMessageGroupInviteListItem m23022a(Context context, ChatMessageViewListener chatMessageViewListener) {
        ChatMessageGroupInviteListItem a = ChatMessageGroupInviteListItem_.m23025a(context);
        a.f21355o.m23018a(chatMessageViewListener);
        return a;
    }

    public void mo6769a(Chat chat, ChatMessage chatMessage, int i) {
        super.mo6769a(chat, chatMessage, i);
        this.f21355o.m23016a(chatMessage, this.k, chat);
    }

    public void mo6771b(Chat chat, ChatMessage chatMessage, int i) {
        super.mo6771b(chat, chatMessage, i);
        this.f21355o.m23016a(chatMessage, this.k, chat);
    }
}
