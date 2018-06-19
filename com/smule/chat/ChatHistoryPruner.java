package com.smule.chat;

import java.util.List;

public class ChatHistoryPruner extends ChatContainer {
    private int f18017a;

    public ChatHistoryPruner(int i) {
        super(0);
        this.f18017a = i;
    }

    protected void mo6318c(Chat chat) {
        super.mo6318c(chat);
        List e = m19297e();
        for (int i = this.f18017a; i < e.size(); i++) {
            ((Chat) e.get(i)).mo6351w();
        }
    }
}
