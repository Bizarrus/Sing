package com.smule.chat;

import com.smule.android.network.managers.SparkManager.OfflineMessageCallback;
import com.smule.android.network.managers.SparkManager.OfflineMessageResponse;
import com.smule.chat.Chat.Bucket;

class ChatManager$8 implements OfflineMessageCallback {
    final /* synthetic */ ChatManager f18077a;

    ChatManager$8(ChatManager chatManager) {
        this.f18077a = chatManager;
    }

    public void handleResponse(OfflineMessageResponse offlineMessageResponse) {
        if (!this.f18077a.a() && offlineMessageResponse.a()) {
            ChatManager.a(this.f18077a, Bucket.INBOX, offlineMessageResponse.inbox);
            ChatManager.a(this.f18077a, Bucket.OTHER, offlineMessageResponse.other);
        }
    }
}
