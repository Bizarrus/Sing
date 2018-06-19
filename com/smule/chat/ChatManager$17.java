package com.smule.chat;

import java.util.Comparator;

class ChatManager$17 implements Comparator<Chat> {
    final /* synthetic */ ChatManager f18035a;

    ChatManager$17(ChatManager chatManager) {
        this.f18035a = chatManager;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m19329a((Chat) obj, (Chat) obj2);
    }

    public int m19329a(Chat chat, Chat chat2) {
        if (ChatManager.g(this.f18035a).contains(chat.m19209c()) != ChatManager.g(this.f18035a).contains(chat2.m19209c())) {
            if (ChatManager.g(this.f18035a).contains(chat.m19209c())) {
                return -1;
            }
            return 1;
        } else if (chat.m19230r() == chat2.m19230r()) {
            ChatMessage l = chat.m19224l();
            ChatMessage l2 = chat2.m19224l();
            if (l != null && l2 != null) {
                return -l.mo6767c().compareTo(l2.mo6767c());
            }
            if (l != null) {
                return -1;
            }
            if (l2 != null) {
                return 1;
            }
            return chat.m19209c().compareTo(chat2.m19209c());
        } else if (chat.m19230r()) {
            return -1;
        } else {
            return 1;
        }
    }
}
