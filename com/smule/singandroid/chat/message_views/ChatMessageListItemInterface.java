/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.chat.message_views;

import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;
import com.smule.singandroid.chat.ChatFragment;
import com.smule.singandroid.chat.message_views.ChatMessageBaseListItem;

public interface ChatMessageListItemInterface {
    public void a(Chat var1, ChatMessage var2, int var3);

    public void a(ChatMessageBaseListItem.ChatMessageViewListener var1, ChatFragment.ChatAdapter var2);

    public void b(Chat var1, ChatMessage var2, int var3);

    public void e();

    public ChatMessage getMessage();
}

