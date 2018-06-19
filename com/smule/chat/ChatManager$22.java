package com.smule.chat;

import java.lang.ref.WeakReference;

class ChatManager$22 implements Runnable {
    final /* synthetic */ Chat f18053a;
    final /* synthetic */ ChatManager f18054b;

    ChatManager$22(ChatManager chatManager, Chat chat) {
        this.f18054b = chatManager;
        this.f18053a = chat;
    }

    public void run() {
        for (WeakReference weakReference : ChatManager.k(this.f18054b)) {
            ChatManagerListener chatManagerListener = (ChatManagerListener) weakReference.get();
            if (chatManagerListener != null) {
                chatManagerListener.mo6325h(this.f18053a);
            }
        }
    }
}
