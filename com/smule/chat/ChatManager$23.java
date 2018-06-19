package com.smule.chat;

import java.lang.ref.WeakReference;

class ChatManager$23 implements Runnable {
    final /* synthetic */ Chat f18055a;
    final /* synthetic */ ChatManager f18056b;

    ChatManager$23(ChatManager chatManager, Chat chat) {
        this.f18056b = chatManager;
        this.f18055a = chat;
    }

    public void run() {
        for (WeakReference weakReference : ChatManager.k(this.f18056b)) {
            ChatManagerListener chatManagerListener = (ChatManagerListener) weakReference.get();
            if (chatManagerListener != null) {
                chatManagerListener.mo6324g(this.f18055a);
            }
        }
    }
}
