package com.smule.chat;

import java.lang.ref.WeakReference;

class ChatManager$21 implements Runnable {
    final /* synthetic */ ChatManager f18052a;

    ChatManager$21(ChatManager chatManager) {
        this.f18052a = chatManager;
    }

    public void run() {
        for (WeakReference weakReference : ChatManager.k(this.f18052a)) {
            ChatManagerListener chatManagerListener = (ChatManagerListener) weakReference.get();
            if (chatManagerListener != null) {
                chatManagerListener.mo6319a();
            }
        }
    }
}
