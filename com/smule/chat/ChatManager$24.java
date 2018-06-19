package com.smule.chat;

import java.lang.ref.WeakReference;

class ChatManager$24 implements Runnable {
    final /* synthetic */ GroupChat f18057a;
    final /* synthetic */ ChatManager f18058b;

    ChatManager$24(ChatManager chatManager, GroupChat groupChat) {
        this.f18058b = chatManager;
        this.f18057a = groupChat;
    }

    public void run() {
        for (WeakReference weakReference : ChatManager.k(this.f18058b)) {
            ChatManagerListener chatManagerListener = (ChatManagerListener) weakReference.get();
            if (chatManagerListener != null) {
                chatManagerListener.mo6321a(this.f18057a);
            }
        }
    }
}
