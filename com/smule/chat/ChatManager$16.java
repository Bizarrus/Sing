package com.smule.chat;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.SparkManager.ActiveChatsUpdateCallback;

class ChatManager$16 implements ActiveChatsUpdateCallback {
    final /* synthetic */ Options f18032a;
    final /* synthetic */ ChatManager$ChatCallback f18033b;
    final /* synthetic */ ChatManager f18034c;

    ChatManager$16(ChatManager chatManager, Options options, ChatManager$ChatCallback chatManager$ChatCallback) {
        this.f18034c = chatManager;
        this.f18032a = options;
        this.f18033b = chatManager$ChatCallback;
    }

    public void handleResponse(NetworkResponse networkResponse) {
        Chat chat = (Chat) ChatManager.c(this.f18034c).get(this.f18032a.f17954b);
        if (networkResponse.c()) {
            this.f18033b.mo6326a(ChatManager.a(this.f18034c, chat, this.f18032a), ChatStatus.OK);
            return;
        }
        this.f18033b.mo6326a(null, ChatStatus.NETWORK_ERROR);
    }
}
