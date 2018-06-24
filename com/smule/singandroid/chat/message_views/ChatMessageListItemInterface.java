package com.smule.singandroid.chat.message_views;

import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;
import com.smule.singandroid.chat.ChatFragment.ChatAdapter;
import com.smule.singandroid.chat.message_views.ChatMessageBaseListItem.ChatMessageViewListener;

public interface ChatMessageListItemInterface {
    void mo6769a(Chat chat, ChatMessage chatMessage, int i);

    void mo6770a(ChatMessageViewListener chatMessageViewListener, ChatAdapter chatAdapter);

    void mo6771b(Chat chat, ChatMessage chatMessage, int i);

    void mo6772e();

    ChatMessage getMessage();
}
