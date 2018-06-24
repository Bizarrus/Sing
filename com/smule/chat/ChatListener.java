/*
 * Decompiled with CFR 0_123.
 */
package com.smule.chat;

import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;

public interface ChatListener {
    public void a(Chat var1);

    public void a(Chat var1, Chat.ChatState var2);

    public void a(Chat var1, ChatMessage var2);

    public void a(Chat var1, ChatMessage var2, boolean var3);

    public void b(Chat var1);

    public void b(Chat var1, ChatMessage var2);

    public void c(Chat var1);

    public void c(Chat var1, ChatMessage var2);

    public void d(Chat var1);
}

