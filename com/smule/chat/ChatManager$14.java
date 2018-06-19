package com.smule.chat;

class ChatManager$14 implements Runnable {
    final /* synthetic */ ChatManager$ChatCallback f18026a;
    final /* synthetic */ Chat f18027b;
    final /* synthetic */ ChatStatus f18028c;
    final /* synthetic */ ChatManager f18029d;

    ChatManager$14(ChatManager chatManager, ChatManager$ChatCallback chatManager$ChatCallback, Chat chat, ChatStatus chatStatus) {
        this.f18029d = chatManager;
        this.f18026a = chatManager$ChatCallback;
        this.f18027b = chat;
        this.f18028c = chatStatus;
    }

    public void run() {
        this.f18026a.mo6326a(this.f18027b, this.f18028c);
    }
}
