package com.smule.chat;

class ChatManager$15 implements Runnable {
    final /* synthetic */ ChatManager$ChatCallback f18030a;
    final /* synthetic */ ChatManager f18031b;

    ChatManager$15(ChatManager chatManager, ChatManager$ChatCallback chatManager$ChatCallback) {
        this.f18031b = chatManager;
        this.f18030a = chatManager$ChatCallback;
    }

    public void run() {
        this.f18030a.mo6326a(null, ChatStatus.CHAT_NOT_FOUND);
    }
}
