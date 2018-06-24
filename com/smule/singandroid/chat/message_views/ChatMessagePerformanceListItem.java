package com.smule.singandroid.chat.message_views;

import android.content.Context;
import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ChatMessagePerformanceListItem extends ChatMessageBaseListItem {
    @ViewById
    ChatMessagePerformanceBody f21406o;

    public ChatMessagePerformanceListItem(Context context) {
        super(context);
    }

    public static ChatMessagePerformanceListItem m23066a(Context context) {
        return ChatMessagePerformanceListItem_.m23069b(context);
    }

    public void mo6769a(Chat chat, ChatMessage chatMessage, int i) {
        super.mo6769a(chat, chatMessage, i);
        this.f21406o.m23060a(chatMessage, this.k, chat);
    }

    public void mo6771b(Chat chat, ChatMessage chatMessage, int i) {
        super.mo6771b(chat, chatMessage, i);
        this.f21406o.m23060a(chatMessage, this.k, chat);
    }

    public ChatMessagePerformanceBody getBody() {
        return this.f21406o;
    }

    public void u_() {
        this.f21406o.u_();
    }

    public String getMediaKey() {
        return this.f21406o.getMediaKey();
    }
}
