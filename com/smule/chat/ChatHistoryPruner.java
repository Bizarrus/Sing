/*
 * Decompiled with CFR 0_123.
 */
package com.smule.chat;

import com.smule.chat.Chat;
import com.smule.chat.ChatContainer;
import java.util.List;

public class ChatHistoryPruner
extends ChatContainer {
    private int a;

    public ChatHistoryPruner(int n) {
        super(0);
        this.a = n;
    }

    @Override
    protected void c(Chat object) {
        super.c((Chat)object);
        object = this.e();
        for (int i = this.a; i < object.size(); ++i) {
            ((Chat)object.get(i)).w();
        }
    }
}

