package com.smule.chat;

class ChatManager$13 implements Runnable {
    final /* synthetic */ ChatManager$ChatCallback f18024a;
    final /* synthetic */ ChatManager f18025b;

    ChatManager$13(ChatManager chatManager, ChatManager$ChatCallback chatManager$ChatCallback) {
        this.f18025b = chatManager;
        this.f18024a = chatManager$ChatCallback;
    }

    public void run() {
        this.f18024a.mo6326a(null, ChatStatus.BAD_REQUEST);
    }
}
