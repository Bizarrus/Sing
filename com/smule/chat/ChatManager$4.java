package com.smule.chat;

import com.smule.chat.Chat.Type;

class ChatManager$4 implements Completion<ChatStatus> {
    final /* synthetic */ Completion f18066a;
    final /* synthetic */ Chat f18067b;
    final /* synthetic */ ChatManager f18068c;

    ChatManager$4(ChatManager chatManager, Completion completion, Chat chat) {
        this.f18068c = chatManager;
        this.f18066a = completion;
        this.f18067b = chat;
    }

    public void m19337a(ChatStatus chatStatus) {
        if (this.f18066a != null) {
            this.f18066a.mo6329a(chatStatus);
        }
        if (this.f18067b.mo6335a() == Type.PEER) {
            ChatManager.a(this.f18068c, this.f18067b);
        } else if (this.f18068c.d(this.f18067b.m19209c()).m19579b()) {
            ChatManager.a(this.f18068c, (GroupChat) this.f18067b);
        } else {
            ChatManager.a(this.f18068c, this.f18067b);
        }
    }
}
