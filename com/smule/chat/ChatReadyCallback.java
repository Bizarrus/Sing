package com.smule.chat;

import com.smule.chat.Chat.ChatState;
import java.util.HashSet;
import java.util.Set;

class ChatReadyCallback extends ChatListenerAdapter implements ChatManager$ChatCallback {
    private static final Set<ChatReadyCallback> f18129b = new HashSet();
    private ChatManager$ChatCallback f18130a;

    public ChatReadyCallback(ChatManager$ChatCallback chatManager$ChatCallback) {
        this.f18130a = chatManager$ChatCallback;
        f18129b.add(this);
    }

    public void mo6326a(Chat chat, ChatStatus chatStatus) {
        if (chat == null || chat.m19212d() != ChatState.LOADING) {
            this.f18130a.mo6326a(chat, chatStatus);
            f18129b.remove(this);
            return;
        }
        chat.m19192a((ChatListener) this);
    }

    public void m19409a(Chat chat, ChatState chatState) {
        if (chatState == ChatState.ERROR) {
            chat.m19205b((ChatListener) this);
            f18129b.remove(this);
            this.f18130a.mo6326a(null, chat.m19214e());
        } else if (chatState == ChatState.READY) {
            chat.m19205b((ChatListener) this);
            f18129b.remove(this);
            this.f18130a.mo6326a(chat, ChatStatus.OK);
        }
    }
}
