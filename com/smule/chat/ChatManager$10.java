package com.smule.chat;

import org.jivesoftware.smack.packet.Message;

class ChatManager$10 implements ChatManager$ChatCallback {
    final /* synthetic */ Message f18019a;
    final /* synthetic */ boolean f18020b;
    final /* synthetic */ ChatManager f18021c;

    ChatManager$10(ChatManager chatManager, Message message, boolean z) {
        this.f18021c = chatManager;
        this.f18019a = message;
        this.f18020b = z;
    }

    public void mo6326a(Chat chat, ChatStatus chatStatus) {
        if (chat != null) {
            chat.mo6336a(this.f18019a, this.f18020b);
        }
    }
}
